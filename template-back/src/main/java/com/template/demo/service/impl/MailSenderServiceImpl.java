package com.template.demo.service.impl;

import com.template.demo.model.template.CompileResponse;
import com.template.demo.model.template.TemplateLabel;
import com.template.demo.service.CompileService;
import com.template.demo.service.MailSenderService;
import com.template.demo.service.StatisticService;
import com.template.demo.storage.entity.FilledTemplate;
import com.template.demo.storage.repository.FilledTemplateRepository;
import freemarker.template.Configuration;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@SuppressWarnings("unchecked")
public class MailSenderServiceImpl implements MailSenderService {


    private FilledTemplateRepository filledTemplateRepository;

    private JavaMailSender javaMailSender;

    private Configuration freemarkerConfig;

    private ApplicationContext ctx;

    private StatisticService statisticService;



    @Override
    public void sendEmail(FilledTemplate filledTemplate) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            CompileService<FilledTemplate> service = (CompileService<FilledTemplate>) ctx.getBean(
                    "service_"+filledTemplate.getTemplate().getTemplateTypeEnum()
            );

            CompileResponse compile = service.compile(filledTemplate);
            String mainText = compile.getHeader();
            List<TemplateLabel> templateLabels =new ArrayList<>();
            compile.getBillParams().forEach((s, s2) -> templateLabels.add(new TemplateLabel(s,s2)));

            Map<String,Object> map =new HashMap<>();
            map.put("mainText",mainText);
            map.put("data",templateLabels);


            StringWriter out = new StringWriter();
            Template temp = freemarkerConfig.getTemplate(filledTemplate.getTemplate().getTemplateTypeEnum().getTemplatePath());
            temp.process( map, out );

            String html = out.getBuffer().toString();
            helper.setTo(filledTemplate.getEmail());
            helper.setSubject("Template Mail");

            helper.setText(html,true);
            javaMailSender.send(mimeMessage);

            statisticService.addNewSentEmail();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmail(Integer filledTemplateId) {
        sendEmail(filledTemplateRepository.findById(filledTemplateId).orElseThrow(RuntimeException::new));
    }


    @Autowired
    public void setFilledTemplateRepository(FilledTemplateRepository filledTemplateRepository) {
        this.filledTemplateRepository = filledTemplateRepository;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ctx = applicationContext;
    }

    @Autowired
    public void setFreemarkerConfig(Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }
}
