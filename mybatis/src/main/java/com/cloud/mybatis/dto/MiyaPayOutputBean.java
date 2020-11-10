package com.cloud.mybatis.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="xml")
public class MiyaPayOutputBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String C1;//订单是否建立状态
	private String C2;//状态码
	private String C3;//渠道平台错误码
	private String C4;//渠道平台错误信息
	private String C5;//商户订单号
	private String C6;//渠道订单号 PAYSUCCESS 时不为空，创建成功后 返回的订单号
	private String C7;//交易金额
	private String C8;//渠道平台标志 1-微信,3-支付宝,
	private String C9;//微信服务商 appid
	private String C10;//微信公众号支付所需 参数 渠道为微信时，方式为 JSAPI 时返 回。微信内 H5 调起支付所需参数
	private String C11;//微信是否关注 
	private String C12;//渠道订单交易时间 
	private String C13;//米雅平台返回时间 
	private String C14;//米雅平台订单描述
	private String C15;//特殊商户平台标记
	private String C16;//优惠信息 [实扣金额|商户优惠金额|平台优惠金额|预 留|预留|预留]
	private String C17;//商户号 
	private String C18;//门店号 
	private String C19;//买家账号 支付宝返回buyer_user_id，大众为800 码
	private String C20;//
	private String C21;//
	private String C22;//
	private String C23;//单品优惠 支付宝 2.0 渠道返回，格式为 json, 例子见 3.1.3 接口说明
	private String C24;//平台名称 返回渠道名称，如支付宝，微信， 翼支付,
    private String C25;// 支付宝 fund_bill_list COUPON:支付宝红 包;ALIPAYACCOUNT:支付宝余 额;POINT:集分宝;DISCOUNT:折扣 券;PCARD:预付卡;FINANCEACCOUNT: 余额宝;MCARD:商家储值 卡;MDISCOUNT:商户优惠 券;MCOUPON:商户红包;PCREDIT:蚂 蚁花呗 例如 ALIPAYACCOUNT:0.01
    private String C26;//二维码码串 当前预下单请求生成的二维码码 串，可以用二维码生成工具根据该 码串值生成对应的二维码
    private String C27;//支付宝 voucher_detail_list
    private String C28;//渠道订单交易时间 
    private String C29;//米雅平台返回时间 
    private String C30;//签名
    
    @XmlElement(name = "C1") 
	public String getC1() {
		return C1;
	}
    @XmlElement(name = "C2") 
	public String getC2() {
		return C2;
	}
    @XmlElement(name = "C3") 
	public String getC3() {
		return C3;
	}
    @XmlElement(name = "C4") 
	public String getC4() {
		return C4;
	}
    @XmlElement(name = "C5") 
	public String getC5() {
		return C5;
	}
    @XmlElement(name = "C6") 
	public String getC6() {
		return C6;
	}
    @XmlElement(name = "C7") 
	public String getC7() {
		return C7;
	}
    @XmlElement(name = "C8") 
	public String getC8() {
		return C8;
	}
    @XmlElement(name = "C9") 
	public String getC9() {
		return C9;
	}
    @XmlElement(name = "C10") 
	public String getC10() {
		return C10;
	}
    @XmlElement(name = "C11") 
	public String getC11() {
		return C11;
	}
    @XmlElement(name = "C12") 
	public String getC12() {
		return C12;
	}
    @XmlElement(name = "C13") 
	public String getC13() {
		return C13;
	}
    @XmlElement(name = "C14") 
	public String getC14() {
		return C14;
	}
    @XmlElement(name = "C15") 
	public String getC15() {
		return C15;
	}
    @XmlElement(name = "C16") 
	public String getC16() {
		return C16;
	}
    @XmlElement(name = "C17") 
	public String getC17() {
		return C17;
	}
    @XmlElement(name = "C18") 
	public String getC18() {
		return C18;
	}
    @XmlElement(name = "C19") 
	public String getC19() {
		return C19;
	}
    @XmlElement(name = "C20") 
	public String getC20() {
		return C20;
	}
    @XmlElement(name = "C21") 
	public String getC21() {
		return C21;
	}
    @XmlElement(name = "C22") 
	public String getC22() {
		return C22;
	}
    @XmlElement(name = "C23") 
	public String getC23() {
		return C23;
	}
    @XmlElement(name = "C24") 
	public String getC24() {
		return C24;
	}
    @XmlElement(name = "C25") 
	public String getC25() {
		return C25;
	}
    @XmlElement(name = "C26") 
	public String getC26() {
		return C26;
	}
    @XmlElement(name = "C27") 
	public String getC27() {
		return C27;
	}
    @XmlElement(name = "C28") 
	public String getC28() {
		return C28;
	}
    @XmlElement(name = "C29") 
	public String getC29() {
		return C29;
	}
    @XmlElement(name = "C30") 
	public String getC30() {
		return C30;
	}
	public void setC1(String c1) {
		C1 = c1;
	}
	public void setC2(String c2) {
		C2 = c2;
	}
	public void setC3(String c3) {
		C3 = c3;
	}
	public void setC4(String c4) {
		C4 = c4;
	}
	public void setC5(String c5) {
		C5 = c5;
	}
	public void setC6(String c6) {
		C6 = c6;
	}
	public void setC7(String c7) {
		C7 = c7;
	}
	public void setC8(String c8) {
		C8 = c8;
	}
	public void setC9(String c9) {
		C9 = c9;
	}
	public void setC10(String c10) {
		C10 = c10;
	}
	public void setC11(String c11) {
		C11 = c11;
	}
	public void setC12(String c12) {
		C12 = c12;
	}
	public void setC13(String c13) {
		C13 = c13;
	}
	public void setC14(String c14) {
		C14 = c14;
	}
	public void setC15(String c15) {
		C15 = c15;
	}
	public void setC16(String c16) {
		C16 = c16;
	}
	public void setC17(String c17) {
		C17 = c17;
	}
	public void setC18(String c18) {
		C18 = c18;
	}
	public void setC19(String c19) {
		C19 = c19;
	}
	public void setC20(String c20) {
		C20 = c20;
	}
	public void setC21(String c21) {
		C21 = c21;
	}
	public void setC22(String c22) {
		C22 = c22;
	}
	public void setC23(String c23) {
		C23 = c23;
	}
	public void setC24(String c24) {
		C24 = c24;
	}
	public void setC25(String c25) {
		C25 = c25;
	}
	public void setC26(String c26) {
		C26 = c26;
	}
	public void setC27(String c27) {
		C27 = c27;
	}
	public void setC28(String c28) {
		C28 = c28;
	}
	public void setC29(String c29) {
		C29 = c29;
	}
	public void setC30(String c30) {
		C30 = c30;
	}
    
    
}
