package com.zzg.rabbitmq.two;/*
@date 2021/9/3 - 9:18 下午
*/

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.zzg.rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

public class Task01 {
    private static final String TASK_QUEUE_NAME = "ack_queue";
    public static void main(String[] argv) throws Exception {
        try (Channel channel = RabbitMqUtils.getChannel()) {
            //durable 信道可持久化
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入信息");
            while (sc.hasNext()) {
                String message = sc.nextLine();
                //MessageProperties.PERSISTENT_TEXT_PLAIN 生产者消息可持久化
                channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println("生产者发出消息" + message);
            }
        }
    }
}