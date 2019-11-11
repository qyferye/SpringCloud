package com.cloud.api.listener;

import com.cloud.api.config.mq.RabbitMqInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 *   使用此类需要将rabbit配置类中  rabbitTemplate.setConfirmCallback  rabbitTemplate.setReturnCallback  注释掉  并且将 RabbitTemplate 设置为多例
 *  将//@Component 放开
 * */
//@Component
@Slf4j
public class MsgProducer implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnCallback{
    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;
    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        System.out.println(rabbitTemplate);
    }

    public void sendMsg(Object content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitMqInit.FANOUT_EXCHANGE_CLOUD,RabbitMqInit.ROUTINGKEY_DIRECT_CLOUD, content, correlationId);
    }
    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(!ack){
            /*这时候需要根据实际情况对失败消息进行处理   如：把消息存到缓存，用另外线程重发。*/// 确认是否发到交换机，若没有则存缓存，用另外的线程重发，直接在里面用rabbitTemplate重发会抛出循环依赖错误
            log.info("producer——》broker消息发送到MQ失败:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
        }
        log.info("producer——》broker消息发送到MQ成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        /*这时候需要根据实际情况对失败消息进行处理   如：把消息存到缓存，用另外线程重发。*/
        log.info("producer——》broker消息发送到MQ丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
    }
}