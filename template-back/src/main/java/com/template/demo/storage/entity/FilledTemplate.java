package com.template.demo.storage.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "template_sc", name = "filled_template")
@SequenceGenerator(name = "filled_template_id_seq", sequenceName = "filled_template_id_seq", allocationSize = 1)
public class FilledTemplate {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "filled_template_id_seq")
    private Integer id;

    private String email;

    private LocalDateTime localDateTime;

    @ManyToOne(targetEntity = Template.class)
    @JoinColumn(name = "template_id")
    private Template template;

    private String templateData;

    public FilledTemplate(String email, LocalDateTime localDateTime, Template template, String templateData) {
        this.email = email;
        this.localDateTime = localDateTime;
        this.template = template;
        this.templateData = templateData;
    }

    public FilledTemplate() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getTemplateData() {
        return templateData;
    }

    public void setTemplateData(String templateData) {
        this.templateData = templateData;
    }
}
