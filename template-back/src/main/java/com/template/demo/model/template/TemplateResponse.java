package com.template.demo.model.template;

import com.template.demo.storage.entity.Template;
import com.template.demo.storage.entity.TemplateTypeEnum;
import com.template.demo.utils.TemplateConvertors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateResponse {

    private Integer id;

    private TemplateTypeEnum templateTypeEnum;

    private String keyword;

    private String templateText;

    private List<String> labels;

    public TemplateResponse(Template template) {
        this.setId(template.getId());
        this.setTemplateTypeEnum(template.getTemplateTypeEnum());
        this.setKeyword(template.getKeyword());
        this.setTemplateText(template.getTemplateText());
        this.setLabels(TemplateConvertors.stringToList(template.getLabels()));
    }

}
