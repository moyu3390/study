package com.nijunyang.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: 
 * Created by nijunyang on 2019/12/16 11:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void testSend() throws Exception {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("bluedarkni@qq.com");
        mailMessage.setTo("n260450135@163.com");
        mailMessage.setSubject("test");
        mailMessage.setText("你好！");
        mailSender.send(mailMessage);
    }


}
