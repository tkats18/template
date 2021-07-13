package com.template.demo.service.impl;

import com.template.demo.model.filledtemplate.FilledTemplateRequest;
import com.template.demo.model.filledtemplate.FilledTemplateResponse;
import com.template.demo.model.filledtemplate.FilledTemplateUpdateRequest;
import com.template.demo.service.FilledTemplateService;
import com.template.demo.storage.entity.FilledTemplate;
import com.template.demo.storage.entity.Template;
import com.template.demo.storage.repository.FilledTemplateRepository;
import com.template.demo.storage.repository.TemplateRepository;
import com.template.demo.utils.TemplateConvertors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilledTemplateServiceImpl implements FilledTemplateService {

    private FilledTemplateRepository filledTemplateRepository;

    private TemplateRepository templateRepository;


    @Override
    public List<FilledTemplateResponse> getAllFilledTemplates() {
        return filledTemplateRepository.findAll().stream().map(FilledTemplateResponse::new).collect(Collectors.toList());
    }

    @Override
    public void addFilledTemplate(FilledTemplateRequest templateAddRequest) {
        Template template = templateRepository.findById(templateAddRequest.getTemplateId()).orElseThrow(RuntimeException::new);

        FilledTemplate filledTemplate = new FilledTemplate(
                templateAddRequest.getEmail(),
                LocalDateTime.now(),
                template,
                TemplateConvertors.mapToString(templateAddRequest.getData())
        );

        filledTemplateRepository.save(filledTemplate);
    }

    @Override
    public void deleteFilledTemplate(Integer id) {
        FilledTemplate filledTemplate = filledTemplateRepository.findById(id).orElseThrow(RuntimeException::new);
        filledTemplateRepository.delete(filledTemplate);
    }

    @Override
    public void updateFilledTemplate(FilledTemplateUpdateRequest templateAddRequest) {
        FilledTemplate filledTemplate = filledTemplateRepository.findById(templateAddRequest.getId()).orElseThrow(RuntimeException::new);
        Template template = templateRepository.findById(templateAddRequest.getId()).orElseThrow(RuntimeException::new);

        filledTemplate.setEmail(templateAddRequest.getEmail());
        filledTemplate.setLocalDateTime(LocalDateTime.now());
        filledTemplate.setTemplateData(TemplateConvertors.mapToString(templateAddRequest.getData()));
        filledTemplate.setTemplate(template);

        filledTemplateRepository.save(filledTemplate);
    }

    @Override
    public FilledTemplate getFilledTemplate(Integer id) {
        return filledTemplateRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Autowired
    public void setFilledTemplateRepository(FilledTemplateRepository filledTemplateRepository) {
        this.filledTemplateRepository = filledTemplateRepository;
    }

    @Autowired
    public void setTemplateRepository(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }
}
