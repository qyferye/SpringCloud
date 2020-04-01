package com.cloud.springboot.service.impl;

import com.cloud.springboot.service.CalcService;
import org.springframework.stereotype.Service;

@Service
public class CalcServiceImpl implements CalcService {
    @Override
    public int add(int a, int b) {
        int result = a + b;
        System.out.println("a:"+a+",b:"+b+",result:"+result);
        /*调用方法内的方法不会走代理，同样事务不会生效，要想生效：@EnableAspectJAutoProxy(exposeProxy=true) 再使用下面方式调用*/
       // CalcServiceImpl proxy=(CalcServiceImpl) AopContext.currentProxy();
       // proxy.show();
        show();
        return result;
    }

    @Override
    public void show(){
        System.out.println("service 内的方法");
    }
}
