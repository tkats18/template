package com.template.demo.controller;

import com.template.demo.model.template.TemplateAddRequest;
import com.template.demo.model.template.TemplateResponse;
import com.template.demo.model.template.TemplateUpdateRequest;
import com.template.demo.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/template")
@CrossOrigin(origins = "*")
public class TemplateController {

    private TemplateService templateService;

    @GetMapping
    public List<TemplateResponse> getTemplates(){
        return templateService.getAllTemplates();
    }

    @GetMapping("search")
    public List<TemplateResponse> searchTemplates(@RequestParam String keyWord){
        return templateService.searchByKeyword(keyWord);
    }

    @PostMapping
    public void addTemplate(@RequestBody  TemplateAddRequest templateAddRequest){
        templateService.addTemplate(templateAddRequest);
    }

    @DeleteMapping("{id}")
    public void deleteTemplate(@PathVariable Integer id){
        templateService.deleteTemplate(id);
    }

    @PutMapping("{id}")
    public void updateTemplate(
            @PathVariable Long id,
            @RequestBody TemplateUpdateRequest templateAddRequest
    ){
        templateService.updateTemplate(templateAddRequest);
    }






    @Autowired
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
