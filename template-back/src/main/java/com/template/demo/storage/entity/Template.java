package com.template.demo.storage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "template_sc", name = "template")
@SequenceGenerator(name = "your_table_id_seq", sequenceName = "your_table_id_seq", allocationSize = 1)
public class Template {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "your_table_id_seq")
    @Column(name = "template_id",columnDefinition = "INT")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "template_type",columnDefinition = "VARCHAR")
    private TemplateTypeEnum templateTypeEnum;

    @Column(name = "keyword", columnDefinition="VARCHAR")
    private String keyword;

    @Column(name = "template_text", columnDefinition="VARCHAR")
    private String templateText;

    @Column(name = "labels", columnDefinition="VARCHAR")
    private String labels;

}
