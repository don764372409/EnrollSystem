package com.yuanmaxinxi.util.major;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.service.MajorService;
import com.yuanmaxinxi.util.StringUtil;

/**
 * 爬取专业的工具类
 * @author 微笑ぃ一刀
 *
 */
public class CrawMajorUtil extends Thread{
	private static LinkedBlockingQueue<Major> majors = new LinkedBlockingQueue<>();
	private	static MajorService service = new MajorService();
	public static void main(String[] args) throws Exception {
//		crawMajorBK();
//		crawMajorZK();
//		//保存到数据库
//		service.insert(majors);
//		List<Major2> list = service.selectAll();
//		for (Major2 mj : list) {
//			if (mj.getLayer()==3) {
//				crawMajorInfo(mj);
//			}
//		}
//		service.update(majors);
		
		crawMajorName();
		for(int i = 0;i<1643;i++) {
			Major mj = majors.poll();
			Major major2 = service.selectOneByOn(mj.getNo());
			if (major2==null) {
				majors.put(mj);
			}
		}
		System.err.println("剩下："+majors.size()+"个");
		while(majors.size()>0) {
			Major poll = majors.poll();
			String no = poll.getNo();
			String pNo = no.substring(0, 4);
			poll.setpNo(pNo);
			service.insert(poll);
		}
	}
	private static void crawMajorName() throws InterruptedException {
		Connection conn = Jsoup.connect("http://www.jumingshi.com/major/middle");
		try {
			Document doc = conn.get();
			Elements bk = doc.getElementsByClass("tab-bk");
			System.err.println(bk.size());
			Elements bk_dls = bk.get(0).getElementsByTag("dl");
			System.err.println(bk_dls.size()+"个1级本科专业");
			int ji1 = 0;
			int ji2 = 0;
			int ji3 = 0;
			for (Element dl : bk_dls) {
				Elements dt = dl.getElementsByTag("dt");
				String name1 = dt.get(0).getElementsByTag("a").text();
				System.err.println(name1);
				ji1+=dt.size();
				Elements h4 = dl.getElementsByTag("h4");
				ji2+=h4.size();
				Elements li = dl.getElementsByTag("li");
				for (Element ele : li) {
					Elements a = ele.getElementsByTag("a");
					String name = a.text();
					String href = a.attr("href");
					String no = StringUtil.majorNo2Filter(href);
					Major mj = new Major();
					mj.setName(name);
					mj.setNo(no);
					mj.setType(0);
					mj.setLayer(3);
					majors.put(mj);
				}
				ji3+=li.size();
			}
			System.err.println(ji1+"个1级本科专业");
			System.err.println(ji2+"个2级本科专业");
			System.err.println(ji3+"个3级本科专业");
			
			Elements zk = doc.getElementsByClass("tab-zk");
			System.err.println(zk.size());
			Elements zk_dls = zk.get(0).getElementsByTag("dl");
			System.err.println(zk_dls.size()+"个1级专科专业");
			ji1 = 0;
			ji2 = 0;
			ji3 = 0;
			for (Element dl : zk_dls) {
				Elements dt = dl.getElementsByTag("dt");
				ji1+=dt.size();
				Elements h4 = dl.getElementsByTag("h4");
				ji2+=h4.size();
				Elements li = dl.getElementsByTag("li");
				for (Element ele : li) {
					Elements a = ele.getElementsByTag("a");
					String name = a.text();
					String href = a.attr("href");
					String no = StringUtil.majorNo2Filter(href);
					Major mj = new Major();
					mj.setName(name);
					mj.setNo(no);
					mj.setType(1);
					mj.setLayer(3);
					majors.put(mj);
				}
				ji3+=li.size();
			}
			System.err.println(ji1+"个1级专科专业");
			System.err.println(ji2+"个2级专科专业");
			System.err.println(ji3+"个3级专科专业");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 爬取3级专业代码
	 * @param mj
	 * @throws Exception
	 */
	public static void crawMajorInfo(Major mj) throws Exception {
		Connection conn = Jsoup.connect(mj.getUrl());
		Document doc = conn.get();
		String text = doc.getElementsByClass("major-con").get(0).text();
		String no = StringUtil.majorNoFilter(text);
		mj.setNo(no);
		mj.setContent(text);
		majors.put(mj);
	}
	
	/**
	 * 爬取专科
	 * @throws Exception
	 */
	private static void crawMajorZK()throws Exception{
		/**
		 * 12个1级本科专业
			94个2级本科专业
			605个3级本科专业
			
			19个1级专科专业
			77个2级专科专业
			1038个3级专科专业
		 */
		try {
			Connection conn = Jsoup.connect("http://www.jumingshi.com/major/index/zk");
			Document doc = conn.get();
			Elements zk = doc.getElementsByClass("content");
			System.err.println(zk.size());
			int i1=0;
			int i3 = 0;
			for (Element p1 : zk) {
				Elements ele = p1.getElementsByClass("major-title");
				String name = ele.get(0).getElementsByClass("font").get(0).text();
				String no = StringUtil.majorNoFilter(name);
				name = StringUtil.majorNameFilter(name);
				System.err.println(name+","+no);
				Major mj1 = new Major();
				//本科专业
				mj1.setType(1);
				//1级专业
				mj1.setLayer(1);
				mj1.setName(name);
				mj1.setNo(no);
				
				//添加到队列中
				majors.put(mj1);
				i1++;
//				//找2级专业
				int k1 = 0;
				Elements major_nums = p1.getElementsByClass("major-num");
				for (Element num : major_nums) {
					String name2 = num.text();
					String no2 = StringUtil.majorNoFilter(name2);
					name2 = StringUtil.majorNameFilter(name2);
					System.err.println(name2+","+no2);
					//往队列中添加2级专业
					Major mj2 = new Major();
					mj2.setType(1);
					mj2.setLayer(2);
					mj2.setNo(no2);
					mj2.setpNo(no);
					mj2.setName(name2);
					majors.put(mj2);
					k1++;
					//拿3级专业
					Elements lis = num.nextElementSibling().getElementsByTag("li");
					for (Element li : lis) {
						Element a = li.getElementsByTag("a").get(0);
						String name3 = a.text();
						String href = a.attr("href");
						System.err.println(name3+"---------------------"+href);
						
						Major mj3 = new Major();
						mj3.setType(1);
						mj3.setLayer(3);
						mj3.setpNo(no2);
						mj3.setName(name3);
						mj3.setUrl("http://www.jumingshi.com"+href);
						
						majors.put(mj3);
						i3++;
					}
					System.err.println(mj2.getName()+"下有"+lis.size()+"个3级专业");
				}
				System.err.println(mj1.getName()+"的2级专业分类有:"+k1+"个");
			}
			System.err.println(i1);
			System.err.println("专科专业一共有"+i3+"个3级专业***************");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 爬取本科专业
	 * @param url
	 */
	private static void crawMajorBK()throws Exception{
		/**
		 * 12个1级本科专业
			94个2级本科专业
			605个3级本科专业
			
			19个1级专科专业
			77个2级专科专业
			1038个3级专科专业
		 */
		try {
			Connection conn = Jsoup.connect("http://www.jumingshi.com/major/index/bk");
			Document doc = conn.get();
			Elements bk = doc.getElementsByClass("content");
			System.err.println(bk.size());
			int i1=0;
			int i3 = 0;
			for (Element p1 : bk) {
				Elements ele = p1.getElementsByClass("major-title");
				String name = ele.get(0).getElementsByClass("font").get(0).text();
				String no = StringUtil.majorNoFilter(name);
				name = StringUtil.majorNameFilter(name);
				System.err.println(name+","+no);
				Major mj1 = new Major();
				//本科专业
				mj1.setType(0);
				//1级专业
				mj1.setLayer(1);
				mj1.setName(name);
				mj1.setNo(no);
				
				//添加到队列中
				majors.put(mj1);
				i1++;
//				//找2级专业
				int k1 = 0;
				Elements major_nums = p1.getElementsByClass("major-num");
				for (Element num : major_nums) {
					String name2 = num.text();
					String no2 = StringUtil.majorNoFilter(name2);
					name2 = StringUtil.majorNameFilter(name2);
					System.err.println(name2+","+no2);
					//往队列中添加2级专业
					Major mj2 = new Major();
					mj2.setType(0);
					mj2.setLayer(2);
					mj2.setNo(no2);
					mj2.setpNo(no);
					mj2.setName(name2);
					majors.put(mj2);
					k1++;
					//拿3级专业
					Elements lis = num.nextElementSibling().getElementsByTag("li");
					for (Element li : lis) {
						Element a = li.getElementsByTag("a").get(0);
						String name3 = a.text();
						String href = a.attr("href");
						System.err.println(name3+"---------------------"+href);
						
						Major mj3 = new Major();
						mj3.setType(0);
						mj3.setLayer(3);
						mj3.setpNo(no2);
						mj3.setName(name3);
						mj3.setUrl("http://www.jumingshi.com"+href);
						
						majors.put(mj3);
						i3++;
					}
					System.err.println(mj2.getName()+"下有"+lis.size()+"个3级专业");
				}
				System.err.println(mj1.getName()+"的2级专业分类有:"+k1+"个");
			}
			System.err.println(i1);
			System.err.println("本科专业一共有"+i3+"个3级专业***************");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
