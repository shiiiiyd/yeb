package com.xxxx.generator;

import com.xxxx.generator.service.Imp.IMailServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
//监听队列
@RabbitListener(queues = "queue")
/**
 * 消费者
 */
public class Consumer {

    @Resource
    private IMailServiceImpl mailService;

    @RabbitHandler
    /**
     * 接收传过来的用户邮箱地址
     */
    public void receive(String address){
        //发送邮件，通知用户
        mailService.sendSimpleMail(address, "Welcome to Yeb!", "来了，老弟");
    }

}
