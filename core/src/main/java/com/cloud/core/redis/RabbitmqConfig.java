package com.cloud.core.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Slf4j
    public class RabbitmqConfig {

/*    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;*/

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.address}")
    private String address;


    /**
     * Rabbitmq 连接工厂
     * @return
     */
    @Bean(name = "connectionFactory")
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(port);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setConnectionTimeout(15000);
        //消息确认机制confirm-callback或return-callback,成功后confirm,失败后回调
        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

/*    *//**
     * 单一消费者
     * @return
     *//*
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConnectionFactory(connectionFactory);
       // factoryConfigurer.configure(factory,connectionFactory);
        //单台并发消费者数量
        factory.setConcurrentConsumers(10);
        //单台并发消费的最大消费者数量
        factory.setMaxConcurrentConsumers(10);
        //预取消费数量,unacked数量超过这个值broker将不会接收消息
        factory.setPrefetchCount(1);
        //有事务时处理的消息数
        factory.setTxSize(1);
        return factory;
    }*/


    /**
     * 多个消费者
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
       //factoryConfigurer.configure(factory,connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(3);
        factory.setPrefetchCount(5);
        factory.setTxSize(1);
        /*重试机制 见配置文件*/

        return factory;
    }


    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        //转换json序列化
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("producer——》broker消息发送到MQ成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                /**此处要对消息进行处理，否则会丢失消息*/
                log.info("producer——》broker消息发送到MQ丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }
}
