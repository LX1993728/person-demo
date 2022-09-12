package com.example.demo.tasks;

import com.example.demo.entities.EmailEntity;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

public class AddPersonNotifyTask extends BaseTask<EmailEntity>{
    @Autowired
    private EmailService emailService;

    public AddPersonNotifyTask(EmailEntity emailEntity) {
       super(emailEntity);
    }

    @Override
    public void doRunnable(EmailEntity o){
        emailService.send(o);
    }

    @Override
    public String getDesc() {
        return "发送添加用户邮件";
    }
}
