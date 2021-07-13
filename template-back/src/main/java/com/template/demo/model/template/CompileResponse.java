package com.template.demo.model.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompileResponse {

    private String header;

    private Map<String,String> billParams;

    private boolean isBill;

}
