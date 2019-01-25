package com.yuanmaxinxi.util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.entity.major.Major;
/**
 * 爬取专业简介等信息
 * @author 微笑ぃ一刀
 *
 */
public class CrawMajorInfoUtil {
	public static void main(String[] args) {
		//本科专业684个
		//专科专业 1059个
		//先获取所有专业的信息页面的url
		SqlSession session = DBUtil.openSession();
		MajorDAO dao = session.getMapper(MajorDAO.class);
		List<Major> urls= dao.selectAllMajor();
		for (Major mj : urls) {
			crawInfo(mj);
			//修改
			dao.updateInfo(mj);
		}
		session.commit();
	}
	private static void crawInfo(Major mj) {
		Connection conn = Jsoup.connect(mj.getUrl());
		try {
			Document doc = conn.get();
			Elements boxs = doc.getElementsByClass("introduce");
			for (int i = 0; i < boxs.size(); i++) {//2
				Element box = boxs.get(i);
				if (i==0) {
					//专业介绍
					Elements mt20s = box.getElementsByClass("mt20");
					for (int j = 0; j < mt20s.size(); j++) {//4
						Element mt20 = mt20s.get(j);
						if (j==0) {
							//专业简介
							String text = mt20.getElementsByTag("p").get(1).text();
							mj.setJianjie(text);
						}
						if (j==1) {
							//培养目标
							String text = mt20.getElementsByTag("p").get(1).text();
							mj.setMubiao(text);
						}
						if (j==2) {
							//培养要求
							String text = mt20.getElementsByTag("p").get(1).text();
							mj.setYaoqiu(text);
						}
						if (j==3) {
							//名人学者
							String text = mt20.getElementsByTag("p").get(1).text();
							mj.setMinren(text);
						}
					}
				}
				if (i==1) {
					//课程要求
					Elements mt20s = box.getElementsByClass("mt20");
					for (int j = 0; j < mt20s.size(); j++) {//3
						Element mt20 = mt20s.get(j);
						if (j==0) {
							//主干课程
							String text = mt20.getElementsByTag("p").get(1).text();
							mj.setKecheng(text);
						}
						if (j==1) {
							//学科要求
							String text = mt20.getElementsByTag("p").get(1).text();
							mj.setXueke(text);
						}
						if (j==2) {
							//知识能力
							Elements ps = mt20.getElementsByTag("p");
							String zhishi = "[";
							for (int k = 1; k < ps.size(); k++) {
								zhishi += "\""+ps.get(k).text()+"\",";
							}
							zhishi+="]";
							mj.setZhishi(zhishi);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
