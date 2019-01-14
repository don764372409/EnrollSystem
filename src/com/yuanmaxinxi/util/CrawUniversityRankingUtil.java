package com.yuanmaxinxi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yuanmaxinxi.entity.university.University;

public class CrawUniversityRankingUtil {
	public static List<University> craw(){
		String url = "http://www.gaosan.com/gaokao/84019.html";
		List<University> list = new ArrayList<>();
		//获取连接对象
		Connection connect = Jsoup.connect(url);
		try {
			Document document = connect.get();
			Elements tbody = document.getElementsByTag("tbody");
			Elements trs = tbody.get(0).getElementsByTag("tr");
			int ranking = 1;
			
			for (int i = 1; i < trs.size(); i++) {
				Element tr = trs.get(i);
				Elements tds = tr.getElementsByTag("td");
				Element nameTd = tds.get(0);
				University uni = new University();
				uni.setName(nameTd.text());
				uni.setRanking(ranking++);
				System.err.println("ranking:::::::::::::::"+uni);
				list.add(uni);
			}
			//找属性名叫<td class="t1  top10"><span>1</span></td><tr>
//			<td>北京大学</td><td>1</td><td>北京</td><td>1</td><td>综合</td><td>1</td></tr>
//			for(int i = 1;i<= 715;i++) {
//				Elements elements = document.getElementsByAttributeValue("tr","td");
//				System.err.println(elements);
//				Element element = elements.get(0);
//				String text = element.text();
//				list.add(text.trim());
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		craw();
	}
}
