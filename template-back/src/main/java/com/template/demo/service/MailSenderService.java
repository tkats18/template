package com.template.demo.service;

import com.template.demo.storage.entity.FilledTemplate;

public interface MailSenderService {

    void sendEmail(FilledTemplate filledTemplate);

    void sendEmail(Integer filledTemplateId);
}
