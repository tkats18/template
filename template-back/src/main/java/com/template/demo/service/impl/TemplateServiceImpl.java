package com.template.demo.service.impl;

import com.template.demo.model.template.TemplateAddRequest;
import com.template.demo.model.template.TemplateResponse;
import com.template.demo.model.template.TemplateUpdateRequest;
import com.template.demo.service.TemplateService;
import com.template.demo.storage.entity.Template;
import com.template.demo.storage.repository.TemplateRepository;
import com.template.demo.utils.TemplateConvertors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemplateServiceImpl implements TemplateService {

    private TemplateRepository templateRepository;

    @Override
    @Transactional
    public void addTemplate(TemplateAddRequest templateAddRequest) {
        Template template = requestToTemplate(templateAddRequest);

        if (!template.getKeyword().chars().allMatch(value ->
                Character.isDigit(value) || Character.isLetter(value) || value=='-' || value=='_')){
            throw new RuntimeException("invalid keyword");
        }
        templateRepository.save(template);
    }

    private Template requestToTemplate(TemplateAddRequest templateAddRequest) {
        Template template = new Template();
        template.setKeyword(templateAddRequest.getKeyword());
        template.setTemplateText(templateAddRequest.getTemplateText());
        template.setTemplateTypeEnum(templateAddRequest.getTemplateType());
        template.setKeyword(templateAddRequest.getKeyword());
        template.setLabels(TemplateConvertors.listToString(templateAddRequest.getLabels()));
        return template;
    }

    @Override
    public void deleteTemplate(Integer id) {
        Template template = templateRepository.findById(id).orElseThrow(RuntimeException::new);
        templateRepository.delete(template);
    }

    @Override
    public void updateTemplate(TemplateUpdateRequest templateAddRequest) {
        if (!templateRepository.existsById(templateAddRequest.getId())) {
            throw new RuntimeException();
        }

        Template template = requestToTemplate(templateAddRequest);
        template.setId(templateAddRequest.getId());

        templateRepository.save(template);

    }

    @Override
    public TemplateResponse getTemplate(Integer id) {
        Optional<Template> template = templateRepository.findById(id);
        return template.map(TemplateResponse::new).orElse(null);
    }

    @Override
    public Template getTemplate(String keyWord) {
        return  templateRepository.findByKeyword(keyWord);
    }

    @Override
    public List<TemplateResponse> getAllTemplates() {
        return templateRepository.findAll().stream().map(TemplateResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<TemplateResponse> searchByKeyword(String keyWord) {
        return templateRepository.findByKeywordLike("%" + keyWord + "%").stream().map(TemplateResponse::new).collect(Collectors.toList());
    }

    @Autowired
    public void setTemplateRepository(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }
}
