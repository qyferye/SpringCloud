package com.cloud.api.test.beantest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @ Description   :  PrototypeBean2
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2019-11-11 16:15
 */
@Component
@Scope("prototype")
public class PrototypeBean2 {
/**
 *
 * 使用  @Lookup   ApplicationContext   两种方法在单例中获得多例   多例类上不能加 @Data 注解   可以加  @setter  @getter
 *
 * */
}
