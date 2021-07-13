package com.template.demo.service.impl;

import com.template.demo.model.template.CompileResponse;
import com.template.demo.service.CompileService;
import com.template.demo.storage.entity.FilledTemplate;
import com.template.demo.utils.TemplateConvertors;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("service_BILL_TEMPLATE")
public class BillCompilerService implements CompileService<FilledTemplate> {
    
    private final TemplateEngine templateEngine;
    
    private final Context context;


    public BillCompilerService() {
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(new StringTemplateResolver());
        context = new Context();
    }

    @Override
    public CompileResponse compile(FilledTemplate data) {
        List<String> labels = TemplateConvertors.stringToList(data.getTemplate().getLabels());
        Map<String, String> values = TemplateConvertors.stringToMap(data.getTemplateData());
        Map<String,String> billLabels = new HashMap<>();

        for (String label : labels) {
            if (!data.getTemplate().getTemplateText().contains(label)){
                billLabels.put(label, values.get(label));
            }
            context.setVariable(label, values.get(label));
        }

        return new CompileResponse(
                templateEngine.process(data.getTemplate().getTemplateText(),context),
                billLabels,
                true
        );
    }

}
