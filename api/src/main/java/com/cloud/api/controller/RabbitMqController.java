package com.cloud.api.controller;

/**
 * @ Description   :  类作用描述
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2019-11-08$ 16:31$
 */

import com.cloud.api.config.mq.RabbitMqInit;
import com.cloud.api.listener.MsgProducer;
import com.cloud.core.dto.DefaultResult;
import com.cloud.core.dto.RabbitMqMsgDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/ignore/mq")
@Api("测试mq接口")
@Slf4j
public class RabbitMqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired(required = false)
    private MsgProducer msgProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value = "发送mq消息")
    @PostMapping(value = "/sendMq")
    public DefaultResult<RabbitMqMsgDto> sendMq(String data) {
        RabbitMqMsgDto rabbitMqMsgDto = new RabbitMqMsgDto();
        rabbitMqMsgDto.setMsgBody(data);
        try {

            // 消息持久化
         /* rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(RabbitMqInit.DIRECT_EXCHANGE_CLOUD);
            rabbitTemplate.setRoutingKey(RabbitMqInit.ROUTINGKEY_DIRECT_CLOUD);
            Message message= MessageBuilder.withBody(objectMapper.writeValueAsBytes("userLog")).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
            rabbitTemplate.convertAndSend(message);*/

            //发送消息到 directExchange
            rabbitTemplate.convertAndSend(RabbitMqInit.DIRECT_EXCHANGE_CLOUD,RabbitMqInit.ROUTINGKEY_DIRECT_CLOUD,rabbitMqMsgDto);
            System.out.println("发送消息到 directExchange");
            //发送消息到 fanoutExchange
            CorrelationData correlationData = new CorrelationData();
            String id = UUID.randomUUID().toString();
            correlationData.setId(id);
            rabbitTemplate.convertAndSend(RabbitMqInit.FANOUT_EXCHANGE_CLOUD,"",rabbitMqMsgDto,correlationData);
            System.out.println("发送消息到 fanoutExchange id:"+id);
            return DefaultResult.success(rabbitMqMsgDto);
        } catch (AmqpException e) {//到了重连次数了，还是没连上怎么办呢？造成这种情况通常是服务器宕机等环境问题，这时候会报AmqpException，我们可以捕获这个异常，然后把消息存入缓存中。等环境正常后，做消息补发。
        // 存缓存操作
        System.out.println(e.getMessage() + "发送失败:原因重连10次都没连上。");
            return DefaultResult.fail();
        }
    }
    @ApiOperation(value = "发送mq消息")
    @PostMapping(value = "/sendMqPrototype")
    public DefaultResult<RabbitMqMsgDto> sendMqPrototype(String data) {
        try {
            RabbitMqMsgDto rabbitMqMsgDto = new RabbitMqMsgDto();
            rabbitMqMsgDto.setMsgBody(data);
            msgProducer.sendMsg(rabbitMqMsgDto);
            return DefaultResult.success(rabbitMqMsgDto);
        } catch (AmqpException e) {//到了重连次数了，还是没连上怎么办呢？造成这种情况通常是服务器宕机等环境问题，这时候会报AmqpException，我们可以捕获这个异常，然后把消息存入缓存中。等环境正常后，做消息补发。
            // 存缓存操作
            System.out.println(e.getMessage() + "发送失败:原因重连10次都没连上。");
            return DefaultResult.fail();
        }
    }

}
