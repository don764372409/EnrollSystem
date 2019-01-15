package com.yuanmaxinxi.service;



import java.net.URLEncoder;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import com.alibaba.fastjson.JSONObject;
import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.dao.university.updateImgesrc.UniversityUpdateImgesrcDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.entity.university.ImgSrc.UniversityImgSrc;
import com.yuanmaxinxi.util.CrawUniversityAllUtil;
import com.yuanmaxinxi.util.CrawUniversityRankingUtil;
import com.yuanmaxinxi.util.DBUtil;
import com.yuanmaxinxi.util.StringUtil;
public class UniversityService {
	private UniversityDao universityDAO = new UniversityDao();
	private static ProvinceDao provinceDao = new ProvinceDao();
	public static LinkedBlockingQueue<String> urls = new LinkedBlockingQueue<>();
	public static LinkedBlockingQueue<String> jianzhangUrls1 = new LinkedBlockingQueue<>();
	//baidu学校校徽的urls
	public static LinkedBlockingQueue<String> baiduUrls= new LinkedBlockingQueue<>();
	//学校师资力量的urls
	public static LinkedBlockingQueue<String> jianzhangUrals = new LinkedBlockingQueue<>();
	private static Object msgUrls;
	//验证服务器中的数据是否为空
	public void insert(University obj) {
//		if(StringUtil.isNullOrEmpty(obj.getName())) {
//			throw new RuntimeException("院校名称不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getAddress())) {
//			throw new RuntimeException("院校所在地不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
//			throw new RuntimeException("院校简介不能空");
//		}
//		if(obj.getRanking()==0||obj.getType()==null) {
//			throw new RuntimeException("院校排名不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getTeachers())) {
//			throw new RuntimeException("院校师资团队不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getSubject())) {
//			throw new RuntimeException("院校学科建设不能空");
//		}
		University selectOneByName = universityDAO.selectOneByName(obj.getName());
		if(selectOneByName!=null) {
			throw new RuntimeException("添加院校名称重复");
		}
		try {
			int i = universityDAO.insert(obj);
			if (i!=1) {
				throw new RuntimeException("请重新添加");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加失败,请稍后重试.");
		}
		
	}

	public void insert(Map uu) {
//		if(StringUtil.isNullOrEmpty(obj.getName())) {
//			throw new RuntimeException("院校名称不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getAddress())) {
//			throw new RuntimeException("院校所在地不能空");
//		}
//		if(obj.getQuality()==0||obj.getQuality()==null) {
//			throw new RuntimeException("院校水平不能空");
//		}
//		if(obj.getType()==0||obj.getType()==null) {
//			throw new RuntimeException("院校类型不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
//			throw new RuntimeException("院校简介不能空");
//		}
//		if(obj.getRanking()==0||obj.getType()==null) {
//			throw new RuntimeException("院校排名不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getTeachers())) {
//			throw new RuntimeException("院校师资团队不能空");
//		}
//		if(obj.getRecord()==0||obj.getRecord()==null) {
//			throw new RuntimeException("院校学历不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getSubject())) {
//			throw new RuntimeException("院校学科建设不能空");
//		}
//		University selectOneByName = universityDAO.selectOneByName(obj.getName());
//		if(selectOneByName!=null) {
//			throw new RuntimeException("添加院校名称重复");
//		}
//		try {
//			int i = universityDAO.insert(uu);
//			if (i!=1) {
//				throw new RuntimeException("请重新添加");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("添加失败,请稍后重试.");
//		}	
	}
	

	public void update(University obj) {
//		if(StringUtil.isNullOrEmpty(obj.getName())) {
//			throw new RuntimeException("院校名称不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getAddress())) {
//			throw new RuntimeException("院校所在地不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
//			throw new RuntimeException("院校简介不能空");
//		}
//		if(obj.getRanking()==0) {
//			throw new RuntimeException("院校排名不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getTeachers())) {
//			throw new RuntimeException("院校师资团队不能空");
//		}
//		if(StringUtil.isNullOrEmpty(obj.getSubject())) {
//			throw new RuntimeException("院校学科建设不能空");
//		}
		try {
			int i = universityDAO.update(obj);
			if (i!=1) {
				throw new RuntimeException("");
			}
		} catch (Exception e) {
			throw new RuntimeException("修改失败,请稍后重试.");
		}
	}

	public void delete(Long id) {
		int row = universityDAO.delete(id);
		if(row==0) {
			throw new RuntimeException("删除院校信息失败");
		}
	}

	public University selectOneById(Long id) {
		University selectOneById = universityDAO.selectOneById(id);
		if(selectOneById==null) {
			throw new RuntimeException("查询院校信息为空");
		}
		return selectOneById;
	}

	//查询所有的院校信息
	public List<University> selectAll() {
		List<University> list = universityDAO.selectAll();
		return list;
	}
	//查询所有省份表
	public  List<University> selectAllByProvince() {
		List<University> selectAllByProvince = universityDAO.selectAllByProvince();
//		if (selectAllByProvince==null) {
//			throw new RuntimeException("省份信息不存在.");
//		}
		return selectAllByProvince;
	}
	
	public List<University> queryPage(BaseQueryPageDTO dto,int str1,int str2) {
		return universityDAO.queryPage(dto,str1,str2);
	}
	public List<University> queryPageRangking(BaseQueryPageDTO dto) {
		return universityDAO.queryPageRangking(dto);
	}

	public List<Dictionary> selectAllByQuality() {
		List<Dictionary> selectAllByQuality = universityDAO.selectAllByQuality();
//		if (selectAllByQuality==null) {
//			throw new RuntimeException("院校水平信息不存在.");
//		}
		return selectAllByQuality;
	}

	public List<Dictionary> selectAllByType() {
		List<Dictionary> selectAllByType = universityDAO.selectAllByType();
//		if (selectAllByType==null) {
//			throw new RuntimeException("院校类型信息不存在.");
//		}
		return selectAllByType;
	}

	public List<Dictionary> selectAllByRecord() {
		List<Dictionary> selecetAllByRecord = universityDAO.selecetAllByRecord();
		if(selecetAllByRecord==null) {
			throw new RuntimeException("院校学历信息不存在");
		}
		return selecetAllByRecord;
	}
	public void selectOneByName(String name) {
		
	}
	/**
	 * 爬取学校
	 */
	public void craw() {
		//查到所有的省
		List<Province> pros = provinceDao.selectAll();
		try {
			for (Province pro : pros) {
				//将省名称进行URL编码
				String proName = URLEncoder.encode(pro.getName(), "utf-8");
				//默认将第一页的URL放进队列中
				CrawUniversityAllUtil.urls.put("https://gkcx.eol.cn/soudaxue/queryschool.html?&page="+1+"&province="+proName);
			}
			CrawUniversityAllUtil.startCraw();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//讲需要爬去百度网页学校校徽的连接放入队列中imgurls
	public LinkedBlockingQueue getBaiduUrls() {
		List<UniversityImgSrc> lists = UniversityUpdateImgesrcDAO.getInstance().getUniversityName();
		for (UniversityImgSrc list : lists) {
			try {
				String uniName = URLEncoder.encode(list.getName(), "utf-8");
				baiduUrls.put("https://baike.baidu.com/item/"+uniName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return baiduUrls;
	}
	
	public LinkedBlockingQueue getZhiYuanWang() {
		//https://gkcx.eol.cn/schoolhtm/schoolInfo/307/10071/list_1.htm
		for (int i = 30; i <= 2700; i++) {
			try {
				String url = "https://gkcx.eol.cn/schoolhtm/schoolInfo/"+i+"/10071/list_1.htm";
				jianzhangUrals.put(url);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return jianzhangUrals;
	}
	
	
	public static void crawl() {
		List<Province> pros = provinceDao.selectAll();
		Iterator<Province> iterator = pros.iterator();
		while(iterator.hasNext()) {
			try {
				String name = URLEncoder.encode(iterator.next().getName(),"utf-8");
				urls.put("https://gkcx.eol.cn/soudaxue/queryschool.html?&page=1&province="+name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void updateCrawSchool(){
		CrawUniversityAllUtil.isDAO = false;
		Set<JSONObject> schools = CrawUniversityAllUtil.schools;
		LinkedBlockingQueue<String> msgs = CrawUniversityAllUtil.msgs;
		for (JSONObject jo : schools) {
			/**
				{"f211":"0",
				"f985":"0",
				"guanwang":"http://www.jhun.edu.cn",
				"membership":"湖北省教育厅",  部门
				"jianjie":, 简介
				"province":"湖北",
				"schoolnature":"公办", 公办 民办
				"level":"本科",  学历
				"schoolname":"江汉大学", 学校名称
				"schoolproperty":"综合类",
				"schooltype":"普通本科", 院校水平
			}
			 */
			System.err.println(jo);
			University uni = universityDAO.selectOneByName(jo.getString("schoolname"));
			if (uni!=null) {
				//更新
				try {
					uni.setName(jo.getString("schoolname"));
					uni.setF211(jo.getIntValue("f211"));
					uni.setF985(jo.getIntValue("f985"));
					uni.setGuanwang(jo.getString("guanwang"));
					uni.setProperty(jo.getString("schoolproperty"));
					uni.setType(jo.getString("schooltype"));
					uni.setRemark(jo.getString("jianjie"));
					uni.setNature(jo.getString("schoolnature"));
					uni.setRecord(jo.getString("level"));
					uni.setDept(jo.getString("membership"));
					Province pro = provinceDao.selectOneById(jo.getString("province"));
					uni.setpId(pro.getId());
					update(uni);
					msgs.put("更新["+uni.getName()+"]成功!");
				} catch (Exception e) {
					try {
						msgs.put("更新["+uni.getName()+"]失败!");
					} catch (Exception e2) {
						System.err.println("将消息放入队列失败.");
					}
				}
			}else {
				//添加
				try {
					uni = new University();
					uni.setName(jo.getString("schoolname"));
					uni.setF211(jo.getIntValue("f211"));
					uni.setF985(jo.getIntValue("f985"));
					uni.setGuanwang(jo.getString("guanwang"));
					uni.setProperty(jo.getString("schoolproperty"));
					uni.setType(jo.getString("schooltype"));
					uni.setRemark(jo.getString("jianjie"));
					uni.setNature(jo.getString("schoolnature"));
					uni.setRecord(jo.getString("level"));
					uni.setDept(jo.getString("membership"));
					Province pro = provinceDao.selectOneById(jo.getString("province"));
					uni.setpId(pro.getId());
					insert(uni);
					msgs.put("添加["+jo.getString("schoolname")+"]成功!");
				} catch (Exception e) {
					e.printStackTrace();
					try {
						msgs.put("添加["+jo.getString("schoolname")+"]失败!");
					} catch (Exception e2) {
						System.err.println("将消息放入队列失败.");
					}
				}
			}
		}
		CrawUniversityAllUtil.flag = true;
	}
	/**
	 * 爬取院校排名
	 */
	public void downReload() {
		List<University> craw = CrawUniversityRankingUtil.craw();
		try {
			DBUtil.getConn().setAutoCommit(false);
			for (University uni : craw) {
				universityDAO.updateRanking(uni);
				
			}
			//提交数据
			DBUtil.getConn().commit();
			//将自动提交设置为true  不影响其他操作
			DBUtil.getConn().setAutoCommit(true);
		} catch (SQLException e) {
			try {
				DBUtil.getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	
}
