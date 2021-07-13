package com.template.demo.controller;

import com.template.demo.model.template.CompileResponse;
import com.template.demo.model.template.TemplateResponse;
import com.template.demo.service.CompileService;
import com.template.demo.service.FilledTemplateService;
import com.template.demo.service.MailSenderService;
import com.template.demo.service.TemplateService;
import com.template.demo.storage.entity.FilledTemplate;
import com.template.demo.storage.entity.Template;
import com.template.demo.storage.entity.TemplateTypeEnum;
import com.template.demo.utils.TemplateConvertors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/v1/compile")
@CrossOrigin(origins = "*")
@SuppressWarnings("unchecked")
public class CompileController {

    private FilledTemplateService filledTemplateService;

    private TemplateService templateService;

    private ApplicationContext ctx;

    private MailSenderService mailSenderService;


    @GetMapping("{id}")
    public CompileResponse compile(@PathVariable Integer id){
        FilledTemplate filledTemplate = filledTemplateService.getFilledTemplate(id);
        CompileService<FilledTemplate> service = (CompileService<FilledTemplate>) ctx.getBean(
                "service_"+filledTemplate.getTemplate().getTemplateTypeEnum()
        );
        return service.compile(filledTemplate);
    }

    @GetMapping("manual/{keyword}")
    public CompileResponse compileWithKeyword(@PathVariable String keyword,
                                              @RequestParam(defaultValue = "true") boolean isText,
                                              @RequestBody Map<String,String> map){

        Template template = templateService.getTemplate(keyword);
        CompileService<FilledTemplate> service = (CompileService<FilledTemplate>) ctx.getBean(
                "service_" + (isText ? TemplateTypeEnum.TEXT_TEMPLATE.name() : TemplateTypeEnum.BILL_TEMPLATE.name())
        );

        return service.compile(new FilledTemplate(null, LocalDateTime.now(), template, TemplateConvertors.mapToString(map)));
    }

    @GetMapping("send-email/{id}")
    public void senEmail(@PathVariable Integer id){
        mailSenderService.sendEmail(id);
    }

    @Autowired
    public void setFilledTemplateService(FilledTemplateService filledTemplateService) {
        this.filledTemplateService = filledTemplateService;
    }

    @Autowired
    public void setCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Autowired
    public void setMailSenderService(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @Autowired
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
