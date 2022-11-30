package com.xxxx.yeb.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//监听队列
@RabbitListener(queues = "queue")
/**
 * 消费者
 */
public class Consumer {

    @RabbitHandler
    /**
     * 接收传过来的用户邮箱地址
     */
    public void receive(String address){
        System.out.println(address);
    }

}
