package com.zzg.rabbitmq.two;/*
@date 2021/9/3 - 9:18 下午
*/

import com.rabbitmq.client.Channel;
import com.zzg.rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

public class Task01 {
    private static final String TASK_QUEUE_NAME = "ack_queue";
    public static void main(String[] argv) throws Exception {
        try (Channel channel = RabbitMqUtils.getChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入信息");
            while (sc.hasNext()) {
                String message = sc.nextLine();
                channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes("UTF-8"));
                System.out.println("生产者发出消息" + message);
            }
        }
    }
}