package com.template.demo.service;

import com.template.demo.model.filledtemplate.FilledTemplateRequest;
import com.template.demo.model.filledtemplate.FilledTemplateResponse;
import com.template.demo.model.filledtemplate.FilledTemplateUpdateRequest;
import com.template.demo.storage.entity.FilledTemplate;

import java.util.List;

public interface FilledTemplateService {

    List<FilledTemplateResponse> getAllFilledTemplates();

    void addFilledTemplate(FilledTemplateRequest templateAddRequest);

    void deleteFilledTemplate(Integer id);

    void updateFilledTemplate(FilledTemplateUpdateRequest templateAddRequest);

    FilledTemplate getFilledTemplate(Integer id);
}
