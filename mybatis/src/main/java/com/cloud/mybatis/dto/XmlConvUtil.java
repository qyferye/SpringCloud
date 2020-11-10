package com.cloud.mybatis.dto;


import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * XmlConvertUtil工具类
 * 
 */
@Slf4j
public class XmlConvUtil {
	private static String xmlPrefix = "<?xml version = '1.0' encoding = 'UTF-8' ?><xml><request>";
	private static String xmlSuffix = "</data></xml>";
	/**
	 * JavaBean转换成xml
	 *
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			log.error("convertToXml异常信息{}",e);
		}

		return result;
	}

	/**
	 * xml转换成JavaBean
	 *
	 * @param xml
	 * @param c
	 * @return
	 */
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			System.out.println("old -------------------------xml转换成JavaBean 异常信息{}"+e);
		}

		return t;
	}
	static Map<String, JAXBContext> jaxbContextMap = new HashMap<String, JAXBContext>();
	/**
	 * xml转java实体类
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXML(String xml, Class<T> valueType) {
		try {
			JAXBContext jaxbContext = jaxbContextMap.get(valueType.getName());
			if(jaxbContext == null){
				jaxbContext = JAXBContext.newInstance(valueType);
				jaxbContextMap.put(valueType.getName(), jaxbContext);
			}
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			System.out.println("new -------------------------xml转换成JavaBean 异常信息{}"+e);
		}
		return null;
	}


   public static void main(String[] args) throws UnsupportedEncodingException {
		/*SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 tlvmap.put("A1", "11");//接口类型  默认 A-交易
		 tlvmap.put("A2", "22");//商户号 米雅提供的商户号 
		 tlvmap.put("A3", "33");//门店账号 通常为商户门店号
		 tlvmap.put("A4", "44");//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", "55");//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", "66");//操作类型  默认 B-支付
		 tlvmap.put("A7", "77");//版本号 默认 1.5 
		 tlvmap.put("B1", "78");//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B2", "79");//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B3", "74");//商户订单号 商户侧生成的订单号，不可重复
		 
		 mapToXml(tlvmap);
		 String utf_8 = toUTF_8("中文测试");
		 System.out.println(utf_8);*/
		 
//		 XmlConvertUtil.setPayAmount(new BigDecimal("0"));
//		 String str = "adadsauo";//定义一个字符串
//		 String fir = str.substring(0,1);//获取字符串的第一个字符
//		 System.out.println(fir);
}

}
