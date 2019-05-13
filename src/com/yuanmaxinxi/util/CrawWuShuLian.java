package com.yuanmaxinxi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬取武书连数据
 * @author 微笑ぃ一刀
 *
 */
public class CrawWuShuLian {
	public static List<Map<String, Object>> crawWUShuLianData() {
		String url = "http://edu.sina.com.cn/gaokao/2016-12-26/doc-ifxyxury8701224.shtml";
		List<Map<String,Object>> list = new ArrayList<>();
		//获取链接对象
		Connection conn = Jsoup.connect(url);
		try {
			//去链接这个url get post 得到一个文档对象
			Document doc = conn.get();
			//找属性名叫href=#3_1  href=#3_34
				//得到a标签 返回集合
			Elements trs = doc.getElementsByTag("table").get(0).getElementsByTag("tr");
			for (int i = 0; i < trs.size(); i++) {
				if (i>0) {
					//tr
					Element ele = trs.get(i);
					//td
					Elements tds = ele.getElementsByTag("td");
					Map<String,Object> map = new HashMap<>();
					for (int j = 0; j < tds.size(); j++) {
						Element tdEl = tds.get(j);
						if (j>2) {
							break;
						}
						if (j==0) {
							map.put("ranking", tdEl.text());
						}
						if (j==1) {
							String name = tdEl.text();
							int k = name.indexOf("大学");
							map.put("name", name.substring(0,k+2));
						}
						if (j==2) {
							map.put("number",tdEl.text());
						}
					}
					list.add(map);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<Map<String, Object>> crawXiaoYouHuiData() {
		String url = "http://edu.sina.com.cn/gaokao/2014-12-29/1015451762.shtml";
		List<Map<String,Object>> list = new ArrayList<>();
		//获取链接对象
		Connection conn = Jsoup.connect(url);
		try {
			//去链接这个url get post 得到一个文档对象
			Document doc = conn.get();
			//找属性名叫href=#3_1  href=#3_34
			//得到a标签 返回集合
			Elements trs = doc.getElementsByTag("table").get(0).getElementsByTag("tr");
			for (int i = 0; i < trs.size(); i++) {
				if (i>0) {
					//tr
					Element ele = trs.get(i);
					//td
					Elements tds = ele.getElementsByTag("td");
					Map<String,Object> map = new HashMap<>();
					for (int j = 0; j < tds.size(); j++) {
						Element tdEl = tds.get(j);
						if (j>4) {
							break;
						}
						if (j==0) {
							map.put("ranking", tdEl.text());
						}
						if (j==1) {
							String name = tdEl.text();
							int k = name.indexOf("大学");
							if (k!=-1) {
								map.put("name", name.substring(0,k+2));
							}else {
								map.put("name", name);
							}
						}
						if (j==4) {
							map.put("number",tdEl.text());
						}
					}
					list.add(map);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		List<Map<String,Object>> huiData = crawXiaoYouHuiData();
		for (Map<String, Object> map : huiData) {
			System.out.println(map);
		}
	}
}
