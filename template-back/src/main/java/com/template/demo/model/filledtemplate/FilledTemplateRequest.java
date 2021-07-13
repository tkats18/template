package com.template.demo.model.filledtemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilledTemplateRequest {

    private Integer templateId;

    private String email;

    private Map<String,String> data;

}
