package com.yuanmaxinxi.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.entity.university.jianzhang.Jianzhang;
import com.yuanmaxinxi.util.StringUtil;

@Service
@Transactional
public class UniversityService {
	@Autowired
	private UniversityDao universityDAO;
	@Autowired
	private ProvinceDao provinceDAO;
	public static LinkedBlockingQueue<String> urls = new LinkedBlockingQueue<>();
	public static LinkedBlockingQueue<String> jianzhangUrls1 = new LinkedBlockingQueue<>();
	//baidu学校校徽的urls
	public static LinkedBlockingQueue<String> baiduUrls= new LinkedBlockingQueue<>();
	//学校师资力量的urls
	public static LinkedBlockingQueue<String> jianzhangUrals = new LinkedBlockingQueue<>();
	public static LinkedBlockingQueue<String> jianjieUrals = new LinkedBlockingQueue<>();
	private static Object msgUrls;

	@Transactional	
	public void insert(University obj) {
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("院校名称不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getAddress())) {
			throw new RuntimeException("院校所在地不能空");
		}
//		if(obj.getQuality()==0||obj.getQuality()==null) {
//			throw new RuntimeException("院校水平不能空");
//		}
//		if(obj.getType()==0||obj.getType()==null) {
//			throw new RuntimeException("院校类型不能空");
//		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("院校简介不能空");
		}
		if(obj.getRanking()==0||obj.getType()==null) {
			throw new RuntimeException("院校排名不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getTeachers())) {
			throw new RuntimeException("院校师资团队不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRecord())){
			throw new RuntimeException("院校学历不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getSubject())) {
			throw new RuntimeException("院校学科建设不能空");
		}
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
	
	public List<String> selectUniversityProperty() {
		return universityDAO.selectUniversityProperty();
	}
	@Transactional	
	public void update(University obj) {
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("院校名称不能空");
		}
//		if(StringUtil.isNullOrEmpty(obj.getAddress())) {
//			throw new RuntimeException("院校所在地不能空");
//		}
//		if(obj.getQuality()==0||obj.getQuality()==null) {
//			throw new RuntimeException("院校水平不能空");
//		}
		if(StringUtil.isNullOrEmpty(obj.getProperty())) {
			throw new RuntimeException("院校类型不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("院校简介不能空");
		}
//		if(obj.getRanking()==0||obj.getType()==null) {
//			throw new RuntimeException("院校排名不能空");
//		}
		if(StringUtil.isNullOrEmpty(obj.getTeachers())) {
			throw new RuntimeException("院校师资团队不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRecord())){
			throw new RuntimeException("院校学历不能空");
		}
//		if(StringUtil.isNullOrEmpty(obj.getSubject())) {
//			throw new RuntimeException("院校学科建设不能空");
//		}
		University selectOneByName = universityDAO.selectOneByName(obj.getName());
		if(selectOneByName!=null) {
			throw new RuntimeException("添加院校名称重复");
		}
		try {
			int i = universityDAO.update(obj);
			if (i!=1) {
				throw new RuntimeException("请重新添加");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加失败,请稍后重试.");
		}
	}
//	}	public void insert(Map uu) {
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
//	}
	

//	public void update(University obj) {
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
//		try {
//			int i = universityDAO.update(obj);
//			if (i!=1) {
//				throw new RuntimeException("");
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("修改失败,请稍后重试.");
//		}
//	}

	public void deleteById(int id) {
		int row;
		try {
			row = universityDAO.deleteById(id);
			if(row!=1) {
				throw new RuntimeException("删除院校信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("删除院校信息失败");
		}
	}
	//根据id查询t_university所有的院校信息
	public University selectOneById(Long id) {
		University uni = universityDAO.selectOneById(id);
		if(uni==null) {
			throw new RuntimeException("查询院校信息为空");
		}
		//获取简章数据
		List<Jianzhang> jianzhangs = universityDAO.selectAllJianZhangById(id);
		uni.setList(jianzhangs);
		System.err.println(jianzhangs);
		//获取城市
		Province province = provinceDAO.selectOneById(uni.getpId());
		uni.setPro(province);
		return uni;
	}
	//查询所有的院校信息
	public List<University> selectAll() {
		List<University> list = universityDAO.selectAll();
		return list;
	}
	//查询所有省份表
//	public  List<University> selectAllByProvince() {
//		List<University> selectAllByProvince = universityDAO.selectAllByProvince();
//		if (selectAllByProvince==null) {
//			throw new RuntimeException("省份信息不存在.");
//		}
//		return selectAllByProvince;
//	}
	
	public void queryPage(MyBatisQueryPageDTO<University> dto) {
		int count = universityDAO.selectCount(dto);
		dto.setCount(count);
		List<University> list = universityDAO.queryPage(dto);
		dto.setRows(list);
	}


	//根据名字获取学校信息
//	public List selectOneByName(String name) {
//		try {
//			List list = universityDAO.selectOneByName(name);
//			if(list==null) {
//				throw new RuntimeException("没有相关的数据");
//			}
//			return list;
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("没有相关的数据");
//		}
//	}
	/**
	 * 爬取学校
	 */
//	public void craw() {
//		//查到所有的省
//		List<Province> pros = provinceDao.selectAll();
//		try {
//			for (Province pro : pros) {
//				//将省名称进行URL编码
//				String proName = URLEncoder.encode(pro.getName(), "utf-8");
//				//默认将第一页的URL放进队列中
//				CrawUniversityAllUtil.urls.put("https://gkcx.eol.cn/soudaxue/queryschool.html?&page="+1+"&province="+proName);
//			}
//			CrawUniversityAllUtil.startCraw();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	//讲需要爬去百度网页学校校徽的连接放入队列中imgurls
//	public LinkedBlockingQueue getBaiduUrls() {
//		List<UniversityImgSrc> lists = UniversityUpdateImgesrcDAO.getInstance().getUniversityName();
//		for (UniversityImgSrc list : lists) {
//			try {
//				String uniName = URLEncoder.encode(list.getName(), "utf-8");
//				baiduUrls.put("https://baike.baidu.com/item/"+uniName);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return baiduUrls;
//	}
	//讲需要爬去http://college.gaokao.com/schlist/的url中放入对列中
//	public LinkedBlockingQueue getJianJieUrls() {
//		for (int i = 1; i < 2668; i++) {
//			try {
//				jianjieUrals.put("http://college.gaokao.com/school/tinfo/"+i+"/intro/");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return jianjieUrals;
//	}
	
//	public LinkedBlockingQueue getZhiYuanWang() {
//		//https://gkcx.eol.cn/schoolhtm/schoolInfo/307/10071/list_1.htm
//		for (int i = 30; i <= 2700; i++) {
//			try {
//				String url = "https://gkcx.eol.cn/schoolhtm/schoolInfo/"+i+"/10071/list_1.htm";
//				jianzhangUrals.put(url);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		return jianzhangUrals;
//	}
	
	
//	public static void crawl() {
//		List<Province> pros = provinceDao.selectAll();
//		Iterator<Province> iterator = pros.iterator();
//		while(iterator.hasNext()) {
//			try {
//				String name = URLEncoder.encode(iterator.next().getName(),"utf-8");
//				urls.put("https://gkcx.eol.cn/soudaxue/queryschool.html?&page=1&province="+name);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

//	public void updateCrawSchool(){
//		CrawUniversityAllUtil.isDAO = false;
//		Set<JSONObject> schools = CrawUniversityAllUtil.schools;
//		LinkedBlockingQueue<String> msgs = CrawUniversityAllUtil.msgs;
//		for (JSONObject jo : schools) {
//			/**
//				{"f211":"0",
//				"f985":"0",
//				"guanwang":"http://www.jhun.edu.cn",
//				"membership":"湖北省教育厅",  部门
//				"jianjie":, 简介
//				"province":"湖北",
//				"schoolnature":"公办", 公办 民办
//				"level":"本科",  学历
//				"schoolname":"江汉大学", 学校名称
//				"schoolproperty":"综合类",
//				"schooltype":"普通本科", 院校水平
//			}
//			 */
//			System.err.println(jo);
//			University uni = universityDAO.selectOneByName(jo.getString("schoolname"));
//			if (uni!=null) {
//				//更新
//				try {
//					uni.setName(jo.getString("schoolname"));
//					uni.setF211(jo.getIntValue("f211"));
//					uni.setF985(jo.getIntValue("f985"));
//					uni.setGuanwang(jo.getString("guanwang"));
//					uni.setProperty(jo.getString("schoolproperty"));
//					uni.setType(jo.getString("schooltype"));
//					uni.setRemark(jo.getString("jianjie"));
//					uni.setNature(jo.getString("schoolnature"));
//					uni.setRecord(jo.getString("level"));
//					uni.setDept(jo.getString("membership"));
//					Province pro = provinceDao.selectOneById(jo.getString("province"));
//					uni.setpId(pro.getId());
//					update(uni);
//					msgs.put("更新["+uni.getName()+"]成功!");
//				} catch (Exception e) {
//					try {
//						msgs.put("更新["+uni.getName()+"]失败!");
//					} catch (Exception e2) {
//						System.err.println("将消息放入队列失败.");
//					}
//				}
//			}else {
//				//添加
//				try {
//					uni = new University();
//					uni.setName(jo.getString("schoolname"));
//					uni.setF211(jo.getIntValue("f211"));
//					uni.setF985(jo.getIntValue("f985"));
//					uni.setGuanwang(jo.getString("guanwang"));
//					uni.setProperty(jo.getString("schoolproperty"));
//					uni.setType(jo.getString("schooltype"));
//					uni.setRemark(jo.getString("jianjie"));
//					uni.setNature(jo.getString("schoolnature"));
//					uni.setRecord(jo.getString("level"));
//					uni.setDept(jo.getString("membership"));
//					Province pro = provinceDao.selectOneById(jo.getString("province"));
//					uni.setpId(pro.getId());
//					insert(uni);
//					msgs.put("添加["+jo.getString("schoolname")+"]成功!");
//				} catch (Exception e) {
//					e.printStackTrace();
//					try {
//						msgs.put("添加["+jo.getString("schoolname")+"]失败!");
//					} catch (Exception e2) {
//						System.err.println("将消息放入队列失败.");
//					}
//				}
//			}
//		}
//		CrawUniversityAllUtil.flag = true;
//	}
	/**
	 * 爬取院校排名
	 */
//	public void downReload() {
//		List<University> craw = CrawUniversityRankingUtil.craw();
//		try {
//			DBUtil.getConn().setAutoCommit(false);
//			for (University uni : craw) {
//				universityDAO.updateRanking(uni);
//			}
//			//提交数据
//			DBUtil.getConn().commit();
//			//将自动提交设置为true  不影响其他操作
//			DBUtil.getConn().setAutoCommit(true);
//		} catch (SQLException e) {
//			try {
//				DBUtil.getConn().rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}
//	}
	/**
	 * 根据学校ID查询学校的录取专业
	 * @param id
	 * @param activBatch 批次代号 1->10,11  2->8,9  3->14-15  4,5->12,13 
	 * @param pId 招生地区省份
	 * @return
	 */
	public List<Major> selectMajorsById(String id, String activBatch, Long pId) {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		//默认提前批
		String bId1 = "6" ;
		String bId2 = "7" ;
		switch (activBatch) {
		case "0":
			bId1 = "6" ;
			bId2 = "7" ;
			break;
		case "1":
			bId1 = "10" ;
			bId2 = "11" ;
			break;
		case "2":
			bId1 = "8" ;
			bId2 = "9" ;
			break;
		case "3":
			bId1 = "14" ;
			bId2 = "15" ;
			break;
		case "4":
		case "5":
			bId1 = "12" ;
			bId2 = "13" ;
			break;

		default:
			break;
		}
		map.put("bId1", bId1);
		map.put("bId2", bId2);
		map.put("pId", pId+"");
		System.err.println(map);
		return universityDAO.selectMajorsById(map);
	}
	/**
	 * 根据学校ID获取录取数据 指定批次指定专业最新的五个年份
	 * @param id
	 * @param pId 招生地区
	 * @return
	 */
	public List<Enroll> selectYearByMajorAndBidAndId(String id,String activBatch,String mId, Long pId) {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		//默认提前批
		String bId1 = "6" ;
		String bId2 = "7" ;
		switch (activBatch) {
		case "0":
			bId1 = "6" ;
			bId2 = "7" ;
			break;
		case "1":
			bId1 = "10" ;
			bId2 = "11" ;
			break;
		case "2":
			bId1 = "8" ;
			bId2 = "9" ;
			break;
		case "3":
			bId1 = "14" ;
			bId2 = "15" ;
			break;
		case "4":
		case "5":
			bId1 = "12" ;
			bId2 = "13" ;
			break;

		default:
			break;
		}
		map.put("bId1", bId1);
		map.put("bId2", bId2);
		map.put("mId", mId);
		map.put("pId", pId+"");
		return universityDAO.selectYearByMajorAndBidAndId(map);
	}
	/**
	 * 根据用户ID和学校ID获取收藏的学校
	 * @param uId
	 * @param id
	 * @return
	 */
	public University selectOneShoucang(String uId, String id) {
		Map<String,String> map = new HashMap<>();
		map.put("uId", uId);
		map.put("id", id);
		return universityDAO.selectOneShoucang(map);
	}

	public void addShoucang(String uId, String id) {
		University uni = selectOneShoucang(uId,id);
		if (uni!=null) {
			//已经收藏成功
			return;
		}
		try {
			Map<String,String> map = new HashMap<>();
			map.put("uId", uId);
			map.put("id", id);
			int i = universityDAO.addShoucang(map);
			if (i!=1) {
				throw new RuntimeException("添加收藏失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加收藏失败!");
		}
	}

	public void unShoucang(String uId, String id) {
		University uni = selectOneShoucang(uId,id);
		if (uni==null) {
			//已经取消收藏成功
			return;
		}
		try {
			Map<String,String> map = new HashMap<>();
			map.put("uId", uId);
			map.put("id", id);
			int i = universityDAO.unShoucang(map);
			if (i!=1) {
				throw new RuntimeException("取消收藏失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("取消收藏失败!");
		}
	}
	/**
	 * 获取当前用户的收藏院校
	 * @param uId
	 * @return
	 */
	public List<University> selectShoucangUnis(String uId) {
		
		return universityDAO.selectShoucangUnis(uId);
	}
	/**
	 * 获取对比的两所学校基本信息
	 * @param ids
	 * @return
	 */
	public List<University> selectUnisByIds(String ids) {
		try {
			//"2409,333"
			if (StringUtil.isNotNullAndEmpty(ids)) {
				String[] uIds = ids.split(",");
				return universityDAO.selectUnisByIds(uIds);
			}
		} catch (Exception e) {
			System.err.println("没有查到对比院校");
		}
		return new ArrayList<>();
	}
	/**
	 * 获取所有学校类型
	 * @return
	 */
	public List<String> selectPropertys() {
		List<String> list = universityDAO.selectPropertys();
		list.set(0, "不限类型");
		return list;
	}
	/**
	 * 查所有省  插入一个不限省份
	 * @return
	 */
	public List<Province> selectProvince() {
		List<Province> list = universityDAO.selectProvince();
		Province province = new Province();
		province.setId(0L);
		province.setName("不限省份");
		list.set(0, province);
		return list;
	}
	/**
	 * 根据两个学校ID 去录取数据中查询相同的专业
	 * @param ids
	 * @param pId 招生地区id
	 */
	public List<Major> selectMajor(String ids, Long pId) {
		try {
			//"2409,333"
			if (StringUtil.isNotNullAndEmpty(ids)) {
				String[] uIds = ids.split(",");
				Map<String,String> map = new HashMap<>();
				map.put("id1", uIds[0]);
				map.put("id2", uIds[1]);
				map.put("pId", pId+"");
				return universityDAO.selectMajor(map);
			}
		} catch (Exception e) {
			System.err.println("没有查到相同专业");
		}
		return new ArrayList<>();
	}
	/**
	 * 批次代号 0-6,7  1->10,11  2->8,9  3->14-15  4,5->12,13 
	 * @param batch
	 * @param numberActive
	 * @param mId
	 * @param pId 
	 * @param uIds 
	 * @return
	 */
	public List<Enroll> selectEnrollByTwoUni(String batch,String mId, String ids, Long pId) {
		Map<String,String> map = new HashMap<>();
		//默认提前批
		String bId1 = "6" ;
		String bId2 = "7" ;
		switch (batch) {
		case "0":
			bId1 = "6" ;
			bId2 = "7" ;
			break;
		case "1":
			bId1 = "10" ;
			bId2 = "11" ;
			break;
		case "2":
			bId1 = "8" ;
			bId2 = "9" ;
			break;
		case "3":
			bId1 = "14" ;
			bId2 = "15" ;
			break;
		case "4":
		case "5":
			bId1 = "12" ;
			bId2 = "13" ;
			break;

		default:
			break;
		}
		map.put("bId1", bId1);
		map.put("bId2", bId2);
		map.put("mId", mId);
		
		if (StringUtil.isNotNullAndEmpty(ids)) {
			String[] uIds = ids.split(",");
			map.put("id1", uIds[0]);
			map.put("id2", uIds[1]);
			map.put("pId", pId+"");
		}
		
		return universityDAO.selectEnrollByTwoUni(map);
	}
	public University selectOneByName(String name) {
		return universityDAO.selectOneByName(name);
	}
	
	public List<University> selectUniList() {
		return universityDAO.selectUnisList();
	}
}
