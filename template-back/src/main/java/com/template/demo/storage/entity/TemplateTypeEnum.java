package com.template.demo.storage.entity;

public enum TemplateTypeEnum {
    TEXT_TEMPLATE("TEXT_TEMPLATE"),
    BILL_TEMPLATE("BILL_TEMPLATE");

    private String type;

    TemplateTypeEnum(String text_template) {
        this.type = text_template;
    }

    public String getTemplatePath() {
        if (type.equals("TEXT_TEMPLATE")){
            return "email/text_template.ftl";
        }else{
            return "email/bill_template.ftl";
        }
    }
}
