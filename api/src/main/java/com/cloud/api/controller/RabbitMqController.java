package com.cloud.api.controller;

/**
 * @ Description   :  类作用描述
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2019-11-08$ 16:31$
 */

import com.cloud.api.config.mq.RabbitMqInit;
import com.cloud.core.dto.DefaultResult;
import com.cloud.core.dto.RabbitMqMsgDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ignore/mq")
@Api("测试mq接口")
@Slf4j
public class RabbitMqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "发送mq消息")
    @PostMapping(value = "/sendMq")
    public DefaultResult<RabbitMqMsgDto> sendMq(String data) {
        RabbitMqMsgDto rabbitMqMsgDto = new RabbitMqMsgDto();
        rabbitMqMsgDto.setMsgBody(data);
        //发送消息到 directExchange
        rabbitTemplate.convertAndSend(RabbitMqInit.DIRECT_EXCHANGE_CLOUD,RabbitMqInit.ROUTINGKEY_DIRECT_CLOUD,rabbitMqMsgDto);
        System.out.println("发送消息到 directExchange");
        //发送消息到 fanoutExchange
        rabbitTemplate.convertAndSend(RabbitMqInit.FANOUT_EXCHANGE_CLOUD,"",rabbitMqMsgDto);
        System.out.println("发送消息到 fanoutExchange");
        return DefaultResult.success(rabbitMqMsgDto);
    }


}
