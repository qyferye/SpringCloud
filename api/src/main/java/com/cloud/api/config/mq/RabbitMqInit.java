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
    public static final String QUEUE_DIRECT_CLOUD = "queue.direct_cloud";
    public static final String QUEUE_FANOUT_CLOUD = "queue.fanout_cloud";


    /**
     * 设置路由key
     */
    public static final String ROUTINGKEY_DIRECT_CLOUD = "queue.direct_cloud";



    /**
     * 交换空间名称(点对点)
     */
    public static final String DIRECT_EXCHANGE_CLOUD = "cloud.exchange.direct";
    /**
     * 交换空间名称(广播)
     */
    public static final String FANOUT_EXCHANGE_CLOUD = "cloud.exchange.fanout";



    /**
     * 点对点模式 交换机
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_CLOUD, true, false);
    }

    /**
     * 广播模式  交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_CLOUD, true, false);
    }


    /**
     * 声明队列名称
     *
     * @return
     */
    @Bean(name = "direct")
    public Queue direct() {
        return new Queue(QUEUE_DIRECT_CLOUD);
    }

    @Bean(name = "fanout")
    public Queue fanout() {
        return new Queue(QUEUE_FANOUT_CLOUD);
    }


    /**
     * ----------------------------------将队列通过路由key到绑定交互机上DirectExchange-----------------------------------------------------------*/
    @Bean
    public Binding bindingDirectExchangeQueue(DirectExchange directExchange, @Qualifier("direct") Queue direct) {
        return BindingBuilder.bind(direct).to(directExchange).with(ROUTINGKEY_DIRECT_CLOUD);
    }
/**---------------------------------------------------------DirectExchange---------------------------------------------------------------**/





    /**
     * ----------------------------------将队列通过路由key到绑定交互机上FanoutExchange-----------------------------------------------------------*/

    @Bean
    public Binding bindingFanoutExchangeQueue(FanoutExchange fanoutExchange, @Qualifier("fanout") Queue fanout) {
        return BindingBuilder.bind(fanout).to(fanoutExchange);
    }

/**---------------------------------------------------------FanoutExchange---------------------------------------------------------------**/





}
