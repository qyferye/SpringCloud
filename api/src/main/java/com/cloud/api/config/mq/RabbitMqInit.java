package com.cloud.api.config.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2019-11-08 16:17
 */
@Configuration
public class RabbitMqInit {

    /**
     * 队列名称
     */
    public static final String QUEUE_TEST_A = "queue.cloud_a";


    /**
     * 设置路由key
     */
    public static final String ROUTINGKEY_TEST_A = "queue.cloud_a";



    /**
     * 交换空间名称(点对点)
     */
    public static final String DIRECT_EXCHANGE_TEST = "exchange.direct_cloud";
    /**
     * 交换空间名称(广播)
     */
    public static final String FANOUT_EXCHANGE_TEST = "store.fanout_cloud";


    /**
     * 声明队列名称
     *
     * @return
     */
    @Bean(name = "queueA")
    public Queue queueA() {
        return new Queue(QUEUE_TEST_A);
    }


    /**
     * 将队列通过路由key到绑定交互机上
     *
     * @param directExchange
     * @param queueA
     * @return
     */
    @Bean
    public Binding bindingExchangeQueueA(DirectExchange directExchange, @Qualifier("queueA") Queue queueA) {
        return BindingBuilder.bind(queueA).to(directExchange).with(ROUTINGKEY_TEST_A);
    }




    /**
     * 将队列通过路由key到绑定交互机上(广播)
     *
     * @param
     * @return
     */
/*

    @Bean
    public Binding bindingExchangeQueueD(FanoutExchange fanoutExchange,@Qualifier("queueA") Queue queueD) {
        return BindingBuilder.bind(queueD).to(fanoutExchange);
    }
*/




    /**
     * 点对点模式
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_TEST, true, false);
    }

    /**
     * 广播模式
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_TEST, true, false);
    }
}
