package com.cloud.api.listener;

import com.cloud.api.config.mq.RabbitMqInit;
import com.cloud.core.common.util.Json;
import com.cloud.core.dto.RabbitMqMsgDto;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RabbitMqReceiver {
    // queues是指要监听的队列的名字 当在配置rabbitTemplate时 不设置序列化器时可以直接使用类接受
   /* @RabbitListener(queues = RabbitMqInit.QUEUE_DIRECT_CLOUD)
    public void receiveDirect1(RabbitMqMsgDto msg) {
        System.out.println(RabbitMqInit.QUEUE_DIRECT_CLOUD+"【监听到消息】" + msg);
    }
 
    @RabbitListener(queues = RabbitMqInit.QUEUE_FANOUT_CLOUD)
    public void receiveDirect2(RabbitMqMsgDto msg) {
        System.out.println(RabbitMqInit.QUEUE_FANOUT_CLOUD+"【监听到消息】" + msg);
    }*/

    // queues是指要监听的队列的名字 当在配置rabbitTemplate时 设置序列化器时可以直接使用类接受会报错：Cannot convert from [[B] to [com.cloud.core.dto.RabbitMqMsgDto] for GenericMessage [payload=byte[78], headers={amqp_receivedDeliveryMode=PERSISTENT
    //要使用如下方式接收
    @RabbitListener(queues = RabbitMqInit.QUEUE_DIRECT_CLOUD)
    public void receiveDirect1(Message message, Channel channel) {
        String bodyMsg = getMsg(message.getBody());
        RabbitMqMsgDto rabbitMqMsgDto= Json.readVal(bodyMsg, RabbitMqMsgDto.class);
        try {
            System.out.println(RabbitMqInit.QUEUE_DIRECT_CLOUD+"【监听到消息】" + rabbitMqMsgDto);

            //手动确认在异常前，即使有异常也会确认，造成消息丢失，所以确认方法应放在最后
            int i = 1/0;

            //消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {

            try {

                //此处可以按照实际情况处理： 比如持久化到数据库、配置死信队列进行接收 。。。
                System.out.println(RabbitMqInit.QUEUE_DIRECT_CLOUD +"-----正常队列处理消息失败----开始发送到死信队列");
                //deliveryTag:该消息的index
                //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
                //requeue：被拒绝的是否重新入队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
               // deliveryTag:该消息的index
               //  requeue：被拒绝的是否重新入队列
               // channel.basicNack 与 channel.basicReject 的区别在于basicNack可以拒绝多条消息，而basicReject一次只能拒绝一条消息
               // channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = RabbitMqInit.QUEUE_FANOUT_CLOUD)
    public void receiveDirect2(Message message, Channel channel) {
        String bodyMsg = getMsg(message.getBody());
        RabbitMqMsgDto rabbitMqMsgDto= Json.readVal(bodyMsg, RabbitMqMsgDto.class);
        try {
            System.out.println(RabbitMqInit.QUEUE_DIRECT_CLOUD+"【监听到消息】" + rabbitMqMsgDto);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {

                //此处可以按照实际情况处理： 比如持久化到数据库、配置死信队列进行接收 。。。
                System.out.println(RabbitMqInit.QUEUE_DIRECT_CLOUD + "-----正常队列处理消息失败----开始发送到死信队列");

                //deliveryTag:该消息的index
                //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
                //requeue：被拒绝的是否重新入队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                // deliveryTag:该消息的index
                //  requeue：被拒绝的是否重新入队列
                // channel.basicNack 与 channel.basicReject 的区别在于basicNack可以拒绝多条消息，而basicReject一次只能拒绝一条消息
                // channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = RabbitMqInit.QUEUE_DEAD_CLOUD)
    public void receiveDead(Message message, Channel channel) {
        String bodyMsg = getMsg(message.getBody());
        RabbitMqMsgDto rabbitMqMsgDto= Json.readVal(bodyMsg, RabbitMqMsgDto.class);
        try {
            System.out.println(RabbitMqInit.QUEUE_DEAD_CLOUD+"【死信队列监听到消息】" + rabbitMqMsgDto);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {

                //此处可以按照实际情况处理： 比如持久化到数据库、配置死信队列进行接收 。。。
                System.out.println(RabbitMqInit.QUEUE_DEAD_CLOUD+"-----死信队列处理消息失败----");

                //deliveryTag:该消息的index
                //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
                //requeue：被拒绝的是否重新入队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                // deliveryTag:该消息的index
                //  requeue：被拒绝的是否重新入队列
                // channel.basicNack 与 channel.basicReject 的区别在于basicNack可以拒绝多条消息，而basicReject一次只能拒绝一条消息
                // channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }


    String getMsg( byte[] body){
        if(body == null){
            return "";
        }
        String bodyMsg = new String(body, StandardCharsets.UTF_8);
        return bodyMsg;
    }


}

