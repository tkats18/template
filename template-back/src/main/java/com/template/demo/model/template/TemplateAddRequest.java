package com.template.demo.model.template;

import com.template.demo.storage.entity.TemplateTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateAddRequest {

    private TemplateTypeEnum templateType;

    private String keyword;

    private String templateText;

    private List<String> labels;

}
