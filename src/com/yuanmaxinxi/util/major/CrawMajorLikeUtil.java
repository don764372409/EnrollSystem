package com.yuanmaxinxi.util.major;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dto.LikeMajor;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.util.DBUtil;

/**
 * 爬取专业的相近专业
 * @author 微笑ぃ一刀
 */
public class CrawMajorLikeUtil {
	public static void main(String[] args) throws Exception {
		SqlSession session = DBUtil.openSession();
		MajorDAO dao = session.getMapper(MajorDAO.class);
		List<Major> majors = dao.selectAllMajor();
		int i  = 0;
		for (Major mj : majors) {
			System.err.println("开始爬取数据");
			List<String> nos = craw(mj);
			for (String no : nos) {
				System.err.println("开始添加数据");
				LikeMajor lm = new LikeMajor();
				lm.setNo1(no);
				lm.setNo2(mj.getNo());
				LikeMajor lm2 = dao.selectLikeMajor(lm);
				//已经存在
				if (lm2!=null) {
					System.err.println("已存在,不用添加");
					continue;
				}
				int j = dao.insertLikeMajor(lm);
				i+=j;
				System.err.println("加入"+i+"条");
			}
		}
		System.err.println("总共加入："+i+"条");
		session.commit();
	}

	private static List<String> craw(Major mj) throws Exception {
		List<String> nos = new ArrayList<>();
		Connection conn = Jsoup.connect(mj.getUrl());
		Document doc = conn.get();
		Elements intro = doc.getElementsByClass("intro");
		if (intro.size()>0) {
			Elements lis = intro.get(0).getElementsByTag("li");
			if (lis.size()>0) {
				for (Element li : lis) {
					//相近专业名称
					String name = li.text();
					String href = li.getElementsByTag("a").get(0).attr("href");
					int i = href.indexOf("mc=");
					href = href.substring(i+3);
					System.err.println(name+"----"+href);
					nos.add(href);
				}
			}
		}
		
		return nos;
	}
}
