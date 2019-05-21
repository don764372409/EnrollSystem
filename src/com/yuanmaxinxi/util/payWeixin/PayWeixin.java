/**
 * 
 */
package com.yuanmaxinxi.util.payWeixin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
import com.yuanmaxinxi.util.StringUtil;



/**
 * @author 灰飞的猪
 *
 */
public class PayWeixin {
	/**
	 * @Author: 灰飞的猪
	 * @Param （十个参数类型）
	 * @return void    返回类型
	 */
	private static final String appid = "wx934f1fc99b01220a";//商户APPID(已有)
	private static final String mch_id = "1534168251";//商户id(已有)
	private static final String paternerKey = "f754ea0ada879c69ce316ad7db1e19ec";//商户id(已有)
	private static final String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//下单接口
	private static String sign;	//签名验证
	public static Map<String, String> payWeixin(Ulogpay ulogpay) {
		String generateNonceStr = WXPayUtil.generateNonceStr();
		String nonce_str = generateNonceStr;//随机字符串  (已有)用WXPayUtil中的generateNonceStr()即可,就是生成UUID的方法；
		String body = ulogpay.getTitle();//支付的名称（未有）
		String out_trade_no = String.valueOf(ulogpay.getNumber()) ;//后台生成的订单号（未有）
		String total_fee  = String.valueOf(ulogpay.getValue().multiply(BigDecimal.valueOf(100.00)));//支付金额 单位：分,（未有）
//		String body ="这是一个测试";//支付的名称（未有）
//		String out_trade_no = "155541353137431143";//后台生成的订单号（未有）
//		String total_fee  = "1";//支付金额 单位：分,（未有）
		String spbill_create_ip = "183.67.49.14";//IP地址（已有）
		String notify_url = "https://www.baidu.com/";//回调地址（已有）
		String trade_type = "JSAPI";//支付类型 （已有）
		String openid = ulogpay.getOpenid();
		//map封装十条数据
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("appid", appid); //APPID （已有） 
		paraMap.put("mch_id", mch_id);  //mch_id 商户ID（已有）
		paraMap.put("nonce_str", nonce_str); //随机字符串 
		paraMap.put("body",body); //支付的名称（未有） 
		paraMap.put("out_trade_no", out_trade_no);//订单号
		paraMap.put("total_fee",total_fee);  //支付金额
		paraMap.put("spbill_create_ip", spbill_create_ip); //IP地址（已有） 
		paraMap.put("trade_type", trade_type);  //APP
		paraMap.put("notify_url",notify_url);// 此路径是微信服务器调用支付结果通知路径随意写
		paraMap.put("openid",openid);
		try {
			sign = WXPayUtil.generateSignature(paraMap, paternerKey);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException("获取签名失败");
		}
		paraMap.put("sign", sign);//获取签名
		String xmlStr = new String();
		try {
			//map转换成xml
			String xml = WXPayUtil.mapToXml(paraMap);
			//发送请求
			xmlStr = HttpRequest.sendPost(unifiedorder_url, xml);
			System.out.println("返回的结果："+xmlStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发送请求失败");
		}	
		//返回前端页面的json数据
		try {	
			Map<String, String> payMap;
			String prepay_id = "";
			if (xmlStr.indexOf("SUCCESS") != -1) {  
				Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);  
				System.out.println("转换成map:  "+map.toString());
				prepay_id = (String) map.get("prepay_id");  
				System.out.println("最终结果："+prepay_id);
			}
			payMap= new HashMap<String, String>();
			payMap.put("appId", appid);  
			payMap.put("timeStamp", String.valueOf(WXPayUtil.getCurrentTimestamp()));  
			payMap.put("nonceStr", WXPayUtil.generateNonceStr());  
			payMap.put("signType", "MD5");  
			payMap.put("package", "prepay_id=" + prepay_id);  
			payMap.put("paySign", WXPayUtil.generateSignature(payMap, paternerKey));
			payMap.put("responseState","success");
			return payMap;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}	
	}
	public static void main(String[] args) {
		Ulogpay ulogpay = new Ulogpay();
		payWeixin(ulogpay);
	}
	public static void noyify(Map<String,String> obj) {
		String sign1 = obj.get("sign");
		if(StringUtil.isNullOrEmpty(sign1)) {
			throw new RuntimeException("签名已经被不存在，或者被第三方篡改，请进行核对");
		}
	}
}
