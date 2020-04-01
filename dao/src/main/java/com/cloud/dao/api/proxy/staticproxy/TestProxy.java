package com.cloud.dao.api.proxy.staticproxy;

/**
 * @ Description   :  TestProxy
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-25 14:15
 */
public class TestProxy {

    public static void main(String[] args) {

        /**
         *
         *  静态代理总结
         * 　　优点：
         * 　　　　使真实角色处理的业务更加的纯粹，不再关注一些公共的事；
         * 　　　　公共的业务由代理来完成，实现了业务的分工；
         * 　　　　公共业务的扩展变得更加集中和方便
         * 　　缺点：
         * 　　　　类变多了，多了代理类，工作量变大了，且不易扩展
         * 　　　　解决此问题的方案就是使用动态代理
         * */
        Host host = new Host();
        Proxy proxy = new Proxy(host);
        proxy.rent();
    }
}
