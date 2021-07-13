package com.template.demo.controller;

import com.template.demo.model.filledtemplate.FilledTemplateRequest;
import com.template.demo.model.filledtemplate.FilledTemplateResponse;
import com.template.demo.model.filledtemplate.FilledTemplateUpdateRequest;
import com.template.demo.service.FilledTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/filled-template")
@CrossOrigin(origins = "*")
public class FilledTemplateController {

    private FilledTemplateService templateService;

    @GetMapping
    public List<FilledTemplateResponse> getTemplates(){
        return templateService.getAllFilledTemplates();
    }

    @PostMapping
    public void addTemplate(@RequestBody FilledTemplateRequest templateAddRequest){
        templateService.addFilledTemplate(templateAddRequest);
    }

    @DeleteMapping("{id}")
    public void deleteTemplate(@PathVariable Integer id){
        templateService.deleteFilledTemplate(id);
    }

    @PutMapping("{id}")
    public void updateTemplate(
            @PathVariable Long id,
            @RequestBody FilledTemplateUpdateRequest templateAddRequest
    ){
        templateService.updateFilledTemplate(templateAddRequest);
    }




    @Autowired
    public void setTemplateService(FilledTemplateService templateService) {
        this.templateService = templateService;
    }

}
