package com.yuanmaxinxi.util.CrawlUniversityTeachers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yuanmaxinxi.dao.university.updateTeacher.UniversityUpdateTeacherDAO;
import com.yuanmaxinxi.entity.university.teacherSrc.TeacherSrc;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.util.StringUtil;
/*
 *@爬去学校师资力量
 */
public class CrawlUniversityTeachers extends Thread {
	private static UniversityService universityService = new UniversityService();
	private static LinkedBlockingQueue<String> urls = universityService.getBaiduUrls();
	public static List<TeacherSrc> lists = new ArrayList<>();
	private static int num;
	
	//开启多条线程
	public void startup() {
		for(int i =0;i<20;i++) {
			new CrawlUniversityTeachers().start();
			System.out.println("已经爬去"+num+"条数据");
		}
	}
	
	//开启一条线成
	public void run() {
		System.out.println("已启动第"+Thread.currentThread()+"线程");
		while(urls.size()>0) {
			String url = urls.poll();
			if(StringUtil.isNotNullAndEmpty(url)) {
				Crawl(url);
				num+=1;
			}
		}	
	}
	//爬去方法
	public void Crawl(String url) {
		Document doc= null;
		try {
			doc = Jsoup.connect(url).get();
			if(doc!=null) {
				TeacherSrc teacherSrc = new TeacherSrc();
				//获得学校名字
				Elements div = doc.getElementsByClass("lemmaWgt-lemmaTitle-title");
				if(div.size()>0) {
					Elements h1 = div.get(0).select("h1");
					String uniName = h1.get(0).text();
					if(StringUtil.isNotNullAndEmpty(uniName)){
						teacherSrc.setName(uniName);
					}
					String[] names = new String[] {"师资力量","师资队伍","师资队伍与教学条件"};
					Element ele = null;
					sing:
					for (String name : names) {
						Elements eles= doc.getElementsContainingOwnText(name);//直接含有师资力量文字的标签
						for (Element cEle : eles) {
							boolean hasClass = cEle.hasClass("title-text");
							if (hasClass) {
								if(name.equals("师资队伍与教学条件")) {
									ele = cEle;
								}else if (name.equals(cEle.ownText())) {
									ele = eles.get(1);
									break sing;
								}
							}
						}
					}
					//匹配师资力量
					if (ele!=null) {
						Element parent = ele.parent();//找到父级标签
						Element next = parent.nextElementSibling();//找到下一个标签
						if (next==null) {
							teacherSrc.setTeacherText("数据有待更新...");
							System.out.println("数据有待更新..."+2);
							lists.add(teacherSrc);
							return;
						}
						String text = next.text();
						if(StringUtil.isNotNullAndEmpty(text)) {
							String regEx="\\[[0-9]+\\]"; 
							Pattern p = Pattern.compile(regEx); 
							Matcher m = p.matcher(text);
							teacherSrc.setTeacherText(m.replaceAll("").trim());
							System.out.println(uniName);
							System.out.println(m.replaceAll("").trim());
						}
					}else {
						teacherSrc.setTeacherText("数据有待更新...");
						System.out.println("数据有待更新..."+3);
					}
					lists.add(teacherSrc);
				}
			}
		} catch (Exception e) {
			System.out.println(url);
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		new CrawlUniversityTeachers().start();
		new CrawlUniversityTeachers().startup();
		Scanner cc= new Scanner(System.in);
		while(true) {
			String next = cc.next();
			if("1".equals(next)) {
				int row = UniversityUpdateTeacherDAO.getInstance().updateTeacher();
				System.err.println(row);
			}
		}
	}
}
