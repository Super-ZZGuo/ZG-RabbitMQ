//package com.zzg.rabbitmq.springbootrabbitmq.controller;/*
//@date 2021/9/8 - 12:00 下午
//*/
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.annotation.PostConstruct;
//import java.util.UUID;
//
///**
// * 发布确认(回退消息版本) 消息生产者
// */
//@Slf4j
//@Component
//public class ConfirmProducerMandatory implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnCallback {
//    c
//    @GetMapping("sendMessage")
//    public void sendMessage(String message){
//        //让消息绑定一个 id 值
//        CorrelationData correlationData1 = new CorrelationData(UUID.randomUUID().toString());
//        rabbitTemplate.convertAndSend("confirm.exchange","key1",message+"key1",correlationData1);
//        log.info("发送消息 id 为:{}内容为{}",correlationData1.getId(),message+"key1");
//        CorrelationData correlationData2 = new CorrelationData(UUID.randomUUID().toString());
//        rabbitTemplate.convertAndSend("confirm.exchange","key2",message+"key2",correlationData2);
//        log.info("发送消息 id 为:{}内容为{}",correlationData2.getId(),message+"key2");
//    }
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        String id = correlationData != null ? correlationData.getId() : "";
//        if (ack) {
//            log.info("交换机收到消息确认成功, id:{}", id);
//        } else {
//            log.error("消息 id:{}未成功投递到交换机,原因是:{}", id, cause);
//        }
//    }
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String
//            exchange, String routingKey) {
//        log.info("消息:{}被服务器退回，退回原因:{}, 交换机是:{}, 路由 key:{}", new String(message.getBody()),replyText, exchange, routingKey);
//    }
//}