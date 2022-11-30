package com.xxxx.yeb.rabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
/**
 * 生产者
 */
public class Sender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String address){
        /**
         * 交换机名称
         * 路由key的名称
         * 消息
         */
        rabbitTemplate.convertAndSend("topicExchange","topic.msg",address);
    }

}
