package com.zzg.rabbitmq.four.fanout;/*
@date 2021/9/5 - 11:48 上午
*/

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.zzg.rabbitmq.utils.RabbitMqUtils;

public class ReceiveLogs02 {
    //声明信道名字
    private static final String EXCHANGE_NAME = "logs";
    public static  void main(String[] args) throws Exception{

        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        /**
         * 生成一个临时的队列 其中，队列的名称是随机的
         * 消费者断开和队列的连接时，队列自动删除
         */
        String queueName = channel.queueDeclare().getQueue();
        // 把临时队列绑定至routingkey
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        System.out.println("等待接收消息，把接收到的消息打印在屏幕上。。。");
        //消息接收回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("控制台打印接收到的消息"+message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

    }
}
