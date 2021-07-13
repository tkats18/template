package com.template.demo.service;


import com.template.demo.model.template.TemplateAddRequest;
import com.template.demo.model.template.TemplateResponse;
import com.template.demo.model.template.TemplateUpdateRequest;
import com.template.demo.storage.entity.Template;

import java.util.List;

public interface TemplateService {

    void addTemplate(TemplateAddRequest templateAddRequest);

    void deleteTemplate(Integer id);

    void updateTemplate(TemplateUpdateRequest templateAddRequest);

    TemplateResponse getTemplate(Integer id);

    Template getTemplate(String keyWord);

    List<TemplateResponse> getAllTemplates();

    List<TemplateResponse> searchByKeyword(String keyWord);
}
