package com.cloud.api.test.beantest;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ Description   :  SingleBean
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2019-11-11 14:03
 */
@Component
@Data
public class SingleBean {
    private PrototypeBean prototypeBean;

    @Autowired
    public SingleBean(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
        this.prototypeBean.setName("singlebea");
        System.out.println(prototypeBean);
    }
    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }


}
