package com.cloud.api.test.beantest;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @ Description   :  PrototypeBean
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2019-11-11 14:04
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
public class PrototypeBean {
    private String name;
}
