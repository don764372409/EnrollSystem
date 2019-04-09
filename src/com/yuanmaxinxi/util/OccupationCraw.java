package com.yuanmaxinxi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yuanmaxinxi.entity.occupation.Occupation;

/**
 * @author 微笑ぃ一刀
 * 职业爬虫
 */
public class OccupationCraw {
	static ArrayList<Occupation> occs = new ArrayList<>();
	public static void main(String[] args){
		selectMajor();
		//保存到数据库
		for (Occupation occ : occs) {
			DBUtil.insert(occ);
			List<Occupation> children = occ.getChildren();
			for (Occupation child : children) {
				child.setpId(occ.getId());
				DBUtil.insert(child);
				List<Occupation> children2 = child.getChildren();
				for (Occupation child2 : children2) {
					child2.setpId(child.getId());
					DBUtil.insert(child2);
				}
			}
		}
	}
	
	public static void selectMajor() {
		String url="http://career.eol.cn/html/sy/zhiye/";
		Connection c = Jsoup.connect(url);
		try {
			Document document = c.get();
			Elements elements = document.getElementsByClass("line_23");
			for (int i = 0; i < 2; i++) {
				Elements eles = elements.get(i).getElementsByTag("p");
				int index = 0;//0-代表1级职业  1-代表2级职业
				Occupation occ = null;
				for(int j=0;j<22;j++) {
					//获取单个标签
					Element element = eles.get(j);
					//1级职业
					if (index==0) {
						//第一级职业分类
						String text = element.text().replace(">", "");
						occ = new Occupation();
						occ.setName(text);
						index =1;
					}else {
						//二级职业
						Elements as = element.getElementsByTag("a");
						List<Occupation> children = occ.getChildren();
						for (int k = 0; k < as.size(); k++) {
							Element aEl = as.get(k);
							String text = aEl.text();
							Occupation occuChild = new Occupation();
							occuChild.setName(text);
							children.add(occuChild);
							//获取3级职业
							String uri = "http://career.eol.cn/html/sy/zhiye/"+aEl.attr("href");
							List<Occupation> children2 = selectMajorUrl(uri);
							occuChild.setChildren(children2);
						}
						index = 0;
						occs.add(occ);
						occ = null;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static List<Occupation> selectMajorUrl(String url) {
		List<Occupation> list = new ArrayList<>();
			Connection connect = Jsoup.connect(url);
			try {
				Document document = connect.get();
				Elements elemnt = document.getElementsByTag("a");
				
				for (Element e : elemnt) {
					if(e.text().equals("[详细]")) {
						String uri = "http://career.eol.cn/html/sy/zhiye/c/"+e.attr("href");
						Occupation occ = majorDetail(uri);
						list.add(occ);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return list;
	}
	
	public static Occupation majorDetail(String url) {
		try {
			Occupation occ = new Occupation();
			Connection c = Jsoup.connect(url);
			Document document = c.get();
			String name = document.getElementById("pagetitle").text();
			occ.setName(name);
			Elements elementsByAttributeValue = document.getElementsByAttributeValue("valign", "top");
			for (Element element : elementsByAttributeValue) {
				String remark = element.text();
				occ.setRemark(remark);
				System.out.println("爬取3级职业："+name+"\n介绍："+remark);
				return occ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
