package com.cloud.api.test.beantest;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ Description   :  SingleBean2
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2019-11-11 14:20
 */
@Component
@Data
public class SingleBean2 {
    private PrototypeBean prototypeBean;
    @Autowired
    public SingleBean2(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
        this.prototypeBean.setName("prototypebean");
        System.out.println(prototypeBean);
    }

    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }


}
