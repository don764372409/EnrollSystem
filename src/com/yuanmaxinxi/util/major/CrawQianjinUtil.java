package com.yuanmaxinxi.util.major;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.service.MajorService;

public class CrawQianjinUtil {
	public static void main(String[] args) throws Exception {
		craw();
	}

	public static void craw() throws Exception {
		MajorService mjs = new MajorService();
		List<Major> list = mjs.selectAll();
		for (Major mj : list) {
			System.err.println("开始爬取专业:"+mj.getName());
			//页码
			int totalPage = 100;
			for(int i = 1;i<=totalPage;i++) {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				try {
					// 创建httpget.
					HttpGet httpget = new HttpGet(
							"http://kaoshi.edu.sina.com.cn/?p=college&s=api2015&a=get_major_college_list&majorid="+mj.getNo()+"&page="+i+"&size=8"
									+ "");
					System.out.println("executing request " + httpget.getURI());
					// 执行get请求.
					CloseableHttpResponse response = httpclient.execute(httpget);
					try {
						// 获取响应实体
						HttpEntity entity = response.getEntity();
						// 打印响应状态
						if (entity != null) {
							// 打印响应内容长度
							// 打印响应内容
							String result = EntityUtils.toString(entity);
							System.out.println(result);
							JSONObject obj = JSON.parseObject(result);
							//请求成功
							if (obj.getString("msg").equals("ok")) {
								JSONObject data = obj.getJSONObject("data");
								//有数据
								if (data==null) {
									//重新赋值最大页码
									System.err.println("跳过当前专业,爬取下一个专业");
									break;
								}
								totalPage = data.getInteger("totalPage");
								//获取数据集合
								JSONArray arr = data.getJSONArray("list");
								if (arr==null||arr.size()==0) {
									System.err.println("跳过当前专业,爬取下一个专业");
									break;
								}
								for (Object o : arr) {
									JSONObject ob = (JSONObject)o;
									String name = ob.getString("name");
									mjs.addMajorAndUniversity(mj.getNo(),name);
								}
							}else {
								//请求失败
								System.err.println("跳过当前专业,爬取下一个专业");
								break;
							}
						}
					} finally {
						response.close();
					}
					Thread.sleep(1000*3);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
