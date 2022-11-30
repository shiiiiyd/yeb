package com.xxxx.generator.recv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.xxxx.generator.service.Imp.IMailServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * 简单队列-消息消费者
 */
public class Recv {


   private final static String QUEUE_NAME = "hello";

    @Resource
    private  IMailServiceImpl iMailService;

    @Value("${spring.mail.from}")
    private  String from;
    static String address = null;
    @RabbitHandler
    public static void main(String[] args) throws Exception {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.10.100");
        factory.setUsername("root");
        factory.setVirtualHost("/root");
        factory.setPassword("root");
        factory.setPort(5672);
        //连接工厂创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //绑定队列
        channel.queueDeclare("hello", false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String address = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + address + "'");

        };
        //监听队列消费消息
        channel.basicConsume("hello", true, deliverCallback, consumerTag -> { });
    }



    /**
     * 普通文本的邮件
     *      创建一封普通文本的邮件
     * @param session
     * @return
     */
    private static Message createSimpleMail(Session session) throws MessagingException {

        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 设置邮件的发送人
        message.setFrom("1048751065@qq.com");
        // 设置邮件的接收人 （发件人和收件人是同一个账户，邮箱也是一样的）
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));

        // 设置邮件的主题
        message.setSubject("测试文本邮件");
        // 设置发送日期
        message.setSentDate(new Date());
        // 设置邮件的文本内容
        message.setText("你好，这是一封测试文本的邮件！");

        // 返回封装好的邮箱对象
        return message;
    }
}