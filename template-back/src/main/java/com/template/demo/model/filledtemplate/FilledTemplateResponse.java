package com.template.demo.model.filledtemplate;

import com.template.demo.storage.entity.FilledTemplate;
import com.template.demo.utils.TemplateConvertors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilledTemplateResponse {

    private Integer id;

    private String email;

    private LocalDateTime createTime;

    private String keyword;

    private Map<String,String> data;

    private String templateType;

    public FilledTemplateResponse (FilledTemplate filledTemplate){
        this.id = filledTemplate.getId();
        this.email = filledTemplate.getEmail();
        this.data = TemplateConvertors.stringToMap(filledTemplate.getTemplateData());
        this.keyword = filledTemplate.getTemplate().getKeyword();
        this.createTime = filledTemplate.getLocalDateTime();
        this.templateType = filledTemplate.getTemplate().getTemplateTypeEnum().name();
    }

}
