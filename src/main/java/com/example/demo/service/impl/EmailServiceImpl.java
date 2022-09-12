package com.example.demo.service.impl;

import com.example.demo.entities.EmailEntity;
import com.example.demo.repository.EmailRepository;
import com.example.demo.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Resource
    private JavaMailSender mailSender;

    @Resource
    private MailProperties mailProperties;

    @Resource
    private EmailRepository emailRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void send(EmailEntity emailEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setTo(emailEntity.getReceiver());
        message.setSubject(emailEntity.getTitle());
        message.setText(emailEntity.getContent());
        mailSender.send(message);

        emailRepository.save(emailEntity);

        log.info("mail send success email={}", emailEntity.toString());
    }
}
