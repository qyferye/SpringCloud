package com.cloud.api.test.beantest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public  class SingletonBean {
/**
 *
 * 使用以下两种方法在单例中获得多例   多例类上不能加 @Data 注解   可以加@setter  @getter
 *
 * */
    
    public PrototypeBean2 getBean() {
        PrototypeBean2 bean = getPrototypeBean();
        log.info("Bean SingletonBean's HashCode : {}",bean.hashCode());
        return bean;

    }
    public void print() {
        PrototypeBean2 bean = getPrototypeBean();
        log.info("Bean SingletonBean's HashCode : {}",bean.hashCode());

    }
    // 也可以写成 @Lookup("prototypeBean") 来指定需要注入的bean
    @Lookup
    protected  PrototypeBean2 getPrototypeBean(){return null;};

  /*  @Autowired
    private ApplicationContext context;

    public void print() {
        PrototypeBean2 bean = getFromApplicationContext();
        log.info("Bean SingletonBean's HashCode : {}",bean.hashCode());

    }

    *//**
     * 每次都从ApplicatonContext中获取新的bean引用
     * @return PrototypeBean instance
     *//*
    PrototypeBean2 getFromApplicationContext() {
        return this.context.getBean("prototypeBean2",PrototypeBean2.class);
    }*/
}