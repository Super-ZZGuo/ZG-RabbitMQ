package com.zzg.rabbitmq.two;/*
@date 2021/9/3 - 9:29 下午
*/

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.zzg.rabbitmq.utils.RabbitMqUtils;
import com.zzg.rabbitmq.utils.SleepUtils;

public class Work01 {
    private static final String ACK_QUEUE_NAME="ack_queue";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C1 等待接收消息处理时间较短");
        //消息消费的时候如何处理消息
        DeliverCallback deliverCallback=(consumerTag, delivery)->{
            String message= new String(delivery.getBody());
            SleepUtils.sleep(1);
            System.out.println("接收到消息:"+message);
            /**
             * 1.消息标记 tag
             * 2.是否批量应答未应答消息
             */
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };
        //采用手动应答
        boolean autoAck=false;
        //消费者采用不公平(分发)接受
        // int perfetchCount = 1;
        // 预处理
        int perfetchCount = 2;
        channel.basicQos(perfetchCount);
        channel.basicConsume(ACK_QUEUE_NAME,autoAck,deliverCallback,(consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        }
        );
    }
}