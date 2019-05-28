package com.yuanmaxinxi.service.ulogpay;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanmaxinxi.dao.ubalance.UbalanceDAO;
import com.yuanmaxinxi.dao.ulogpay.UlogpayDAO;
import com.yuanmaxinxi.dao.user.UserDAO;
import com.yuanmaxinxi.entity.ubalance.Ubalance;
import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.smallsoft.user.UserController;
import com.yuanmaxinxi.util.payWeixin.PayWeixin;

@Service
public class UlogpayService{
	@Autowired
	private UlogpayDAO ulogpayDAO;
	@Autowired
	private UbalanceDAO ubalanceDAO;
	@Autowired
	private UserDAO userDAO;

	@Transactional
	public void insert(Ulogpay ulogpay){

		try {
			int i = ulogpayDAO.insert(ulogpay);
			if(i!=1){
				throw new RuntimeException("添加失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("添加失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("添加失败.");
		}
	}
	@Transactional
	public void update(Ulogpay ulogpay){

		try {
			int i = ulogpayDAO.update(ulogpay);
			if(i!=1){
				throw new RuntimeException("修改失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("修改失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("修改失败.");
		}
	}
	@Transactional
	public void delete(Long id){
		try {
			int i = ulogpayDAO.delete(id);
			if(i!=1){
				throw new RuntimeException("删除失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("删除失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("删除失败.");
		}
	}
	public Ulogpay selectOneById(Long id){
		try {
			Ulogpay ulogpay= ulogpayDAO.selectOneById(id);

			return ulogpay;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}
	public List<Ulogpay> selectAll(){
		try {
			List<Ulogpay> list = ulogpayDAO.selectAll();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}
	@Transactional
	public Map<String, String> payWeixin(Ulogpay ulogpay) {
		//补充订单详情
		ulogpay.setTitle("充值");
		ulogpay.setPaytype(1);
		ulogpay.setPaytime(new Date());
		ulogpay.setType(1);
		//默认是充值订单
		Map<String, String> map = new HashMap<String, String>();
		try {
			ulogpay.setNumber(new Date().getTime());//订单号
			ulogpay.setPaytime(new Date());
			map = PayWeixin.payWeixin(ulogpay);
			if("success".equals(map.get("responseState"))) {
				//形成微信流水订单
				User user = userDAO.selectOneByOpenid(ulogpay.getOpenid());
				ulogpay.setuId(user.getId());
				int row = ulogpayDAO.insert(ulogpay);
				if(row!=1) {
					throw new RuntimeException("添加订单失败");
				}
				//设置外部微信商户订单
				Ulogpay ulogpay2 = new Ulogpay();
				ulogpay2.setId(ulogpay.getId());
				ulogpay2.setOutNumber(map.get("package"));
				int row1 = ulogpayDAO.updateOutNumber(ulogpay2);
				if(row1!=1) {
					throw new RuntimeException("修改订单失败");
				}
			}
			//获取微信access_token
			Map<String, String> getmap = getToken();
			Map<String, String> ticket = getTicket(getmap);
			String url = ulogpay.getUrl();
			String string1 = "jsapi_ticket=" + ticket.get("ticket") +
					                   "&noncestr=" + map.get("nonceStr") +
					                   "&timestamp=" + map.get("timeStamp") +
					                   "&url=" + "www.methodol-edu.com";
			 MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		     crypt.reset();
			 crypt.update(string1.getBytes("UTF-8"));
			 byte[] signature = crypt.digest();
			 Formatter formatter = new Formatter();
			for (byte b : signature) {
				formatter.format("%02x", b);
			}
			String result = formatter.toString();
			formatter.close();
			 map.put("signature", result);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
//			throw new RuntimeException("微信支付错误，请稍后重试");
			throw new RuntimeException(e.getMessage());
		}
	}
	@Transactional
	public void finish(Ulogpay ulogpay) {
		//更改订单状态
		String outNumber = ulogpay.getOutNumber();
		Ulogpay obj = ulogpayDAO.selectOneByOutNumber(outNumber);
		int row = ulogpayDAO.updateStatus(obj.getId());
		if(row!=1) {
			throw new RuntimeException("更新账户订单错误");
		}
		//更改积分余额
		Ubalance selectUbla = ubalanceDAO.selectOneByuId(obj.getuId());
		if(selectUbla==null) {
			throw new RuntimeException("未创建改用户账户");
		}
		BigDecimal money = selectUbla.getMoney();
		int number = selectUbla.getNumber();
		selectUbla.setNumber(number+1);
		BigDecimal add = money.add(obj.getValue());//增加余额
		selectUbla.setMoney(add);
		int update = ubalanceDAO.update(selectUbla);
		if(update!=1) {
			throw new RuntimeException("更新账户余额错误");
		}
		User user = userDAO.selectOneById(obj.getuId());
		if(user.getVip()==0) {
			int row1 =userDAO.updateVip(selectUbla.getuId());
			if(row1!=1) {
				throw new RuntimeException("更新账户余额错误");
			}
		}
	}
	
	public void pay(Ulogpay ulogpay) {
		ulogpay.setPaytime(new Date());
		int row = ulogpayDAO.insert(ulogpay);
		if(row!=1) {
			throw new RuntimeException("更新账户余额错误");
		}
		Ubalance selectUbla = ubalanceDAO.selectOneByuId(ulogpay.getuId());
		if(selectUbla==null) {
			throw new RuntimeException("未创建改用户账户");
		}
		BigDecimal money = selectUbla.getMoney();
		BigDecimal add = money.subtract(ulogpay.getValue());//减少余额
		selectUbla.setMoney(add);
		int update = ubalanceDAO.update(selectUbla);
		if(update!=1) {
			throw new RuntimeException("更新账户余额错误");
		}
		
	}
	public List<Ulogpay> selectAllByOpenId(String openid) {
		List<Ulogpay>  list = ulogpayDAO.selectAllByOpenId(openid);
		return list;
	}

	//微信支付工具
	private static JSONObject doGet(String requestUrl) {
    	HttpClient httpClient = new DefaultHttpClient();
    	HttpResponse response = null;
        String responseContent  = null;
        com.alibaba.fastjson.JSONObject result = null;
        try {
            //创建Get请求，
            HttpGet httpGet = new HttpGet(requestUrl);
            //执行Get请求，
            response = httpClient.execute(httpGet);
            //得到响应体
            HttpEntity entity = response.getEntity();
            //获取响应内容
            responseContent  = EntityUtils.toString(entity,"UTF-8");
            //转换为map
            result = JSON.parseObject(responseContent);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return result;
    }
	public static Map<String,String> getToken() {
		Map<String, String> map = new HashMap<String,String>();
	     String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	     String appid="wx4567caad1b60b9e1";
	     String appsecret="75f903c2c84c191b7da4ca506ff35e70";
	      String requestUrl = url.replace("APPID", appid).replace("APPSECRET", appsecret);
	      // 发起GET请求获取凭证
	      JSONObject jsonObject = doGet(requestUrl);
	     if (null != jsonObject) {
	         try {
	        	 map.put("access_token", jsonObject.getString("access_token"));
	        	 map.put("expires_in", jsonObject.getString("expires_in"));
	         } catch (Exception e) {
	             // 获取token失败
	        	 e.printStackTrace();
	        	 throw new RuntimeException("获取access_token错误");
	         }
	    }
	     return map;
	}

//	public static void main(String[] args) {
//		Map<String, String> token = getToken();
//		System.out.println(token.toString());
//		Map<String, String> ticket = getTicket(token);
//		System.out.println(ticket.toString());
//	}
	public static Map<String,String> getTicket(Map<String,String> map) {
		Map<String,String> map2 = new HashMap<String,String>();
        //Constants.ticket_url = https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
		String url ="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		String requestUrl = url.replace("ACCESS_TOKEN", map.get("access_token"));
		JSONObject jsonObject = doGet(requestUrl);
        if (null != jsonObject) {
            try {
            	 map2.put("ticket", jsonObject.getString("ticket"));
	        	 map2.put("expires_in", jsonObject.getString("expires_in"));
            } catch (Exception e) {
                // 获取失败
            	 e.printStackTrace();
	        	 throw new RuntimeException("获取ticket错误");
            }
        }
        return map2;
    }
}