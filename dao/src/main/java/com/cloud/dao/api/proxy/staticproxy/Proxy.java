package com.cloud.dao.api.proxy.staticproxy;

/**
 * 代理角色：代理真实角色，代理真实角色之后，一般会做一些附属（增强的操作）
 *
 * @ Description   :  Proxy 代理角色：同真实角色实现同一个接口
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-25 14:06
 */
public class Proxy implements Rent {
    private Host host;//拥有被代理对象的私有对象

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        seeHouse();
        host.rent();
        fee();
    }
    //**************代理角色附带（增强）的一些功能**************//
    private void seeHouse(){
        System.out.println("带租客看房子");
    }

    private void fee(){
        System.out.println("和租客签订合同，收取费用");
    }
}
