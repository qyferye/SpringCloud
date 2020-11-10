package com.cloud.mybatis.controller;

import com.cloud.mybatis.dto.MiyaPayOutputBean;
import com.cloud.mybatis.dto.XmlConvUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-11-10 11:29
 * @ Description：SpringCloud
 */
@Controller
@RequestMapping(value = "/xml")
@Slf4j
public class XmlTestController {
    @ResponseBody
    @GetMapping("/old")
    public Object getOld(){
        String xml = "<?xml version='1.0' encoding='UTF-8'?><xml> <C1>SUCCESS</C1> <C2>PAYWAIT</C2> <C5>13256383912637281111637</C5> <C6>wx09111637847051612f77104bc8571a0000</C6> <C7>2</C7> <C8>1</C8> <C9>wx273772ce26499402</C9> <C10>{\"status\":\"00\"trade_no\":\"wx09111637847051612f77104bc8571a0000\",\"wxsend\":{\"timeStamp\":\"1604891797874\",\"package\":\"prepay_id=wx09111637847051612f771bc8571a0000\",\"paySign\":\"82B3FB2B566D91B84FEC14FC5A492CF8\",\"appId\":\"wx9ad69d946a2cbf03\",\"signType\":\"MD5\",\"nonceStr\":\"N8OloyEgKaH4gF3rMZCkftKNBLZReR\"},\"payserial\":\"1325638391263457281111637\"}</C10> <C13>2020-11-09 11:16:37</C13> <C14>[微信]创建订单成功，等待用户支</C14> <C30>FF4AAB952CA9C0F483889053DDF7ACCD</C30> </xml>";
        Long start = System.currentTimeMillis();
        MiyaPayOutputBean miyaPayOutputBean = XmlConvUtil.converyToJavaBean(xml, MiyaPayOutputBean.class);
        Long end = (System.currentTimeMillis() - start);
        //System.out.println("old耗时-------------------------------------"+end+" ms");
        return miyaPayOutputBean;
    }

    @ResponseBody
    @GetMapping("/new")
    public Object getNew(){
        String xml = "<?xml version='1.0' encoding='UTF-8'?><xml> <C1>SUCCESS</C1> <C2>PAYWAIT</C2> <C5>13256383912637281111637</C5> <C6>wx09111637847051612f77104bc8571a0000</C6> <C7>2</C7> <C8>1</C8> <C9>wx273772ce26499402</C9> <C10>{\"status\":\"00\"trade_no\":\"wx09111637847051612f77104bc8571a0000\",\"wxsend\":{\"timeStamp\":\"1604891797874\",\"package\":\"prepay_id=wx09111637847051612f771bc8571a0000\",\"paySign\":\"82B3FB2B566D91B84FEC14FC5A492CF8\",\"appId\":\"wx9ad69d946a2cbf03\",\"signType\":\"MD5\",\"nonceStr\":\"N8OloyEgKaH4gF3rMZCkftKNBLZReR\"},\"payserial\":\"1325638391263457281111637\"}</C10> <C13>2020-11-09 11:16:37</C13> <C14>[微信]创建订单成功，等待用户支</C14> <C30>FF4AAB952CA9C0F483889053DDF7ACCD</C30> </xml>";
        Long start = System.currentTimeMillis();
        MiyaPayOutputBean miyaPayOutputBean = XmlConvUtil.fromXML(xml, MiyaPayOutputBean.class);
        Long end = (System.currentTimeMillis() - start);
        //System.out.println("new耗时-------------------------------------"+end+" ms");
        return miyaPayOutputBean;
    }

}
