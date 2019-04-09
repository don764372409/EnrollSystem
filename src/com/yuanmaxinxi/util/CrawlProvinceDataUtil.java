package com.yuanmaxinxi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 获取省的爬虫
 * @author 微笑ぃ一刀
 */
public class CrawlProvinceDataUtil {
	/**
	 * 爬取省
	 */
	public static List<String> craw() {
		String url = "https://baike.baidu.com/item/省";
		List<String> list = new ArrayList<>();
		//获取链接对象
		Connection conn = Jsoup.connect(url);
		try {
			//去链接这个url get post 得到一个文档对象
			Document doc = conn.get();
			//找属性名叫href=#3_1  href=#3_34
			for (int i = 1; i <= 34; i++) {
				//得到a标签 返回集合
				Elements eles = doc.getElementsByAttributeValue("href", "#3_"+i);
				//从集合中获取第0个
				Element ele = eles.get(0);
				//得到内容
				String name = ele.text();
				list.add(name.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		craw();	
	}
}
