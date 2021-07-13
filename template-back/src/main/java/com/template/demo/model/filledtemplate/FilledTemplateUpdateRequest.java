package com.template.demo.model.filledtemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilledTemplateUpdateRequest extends FilledTemplateRequest{

    private Integer id;

}
