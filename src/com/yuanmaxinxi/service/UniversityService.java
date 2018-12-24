package com.yuanmaxinxi.service;

import java.net.URLEncoder;
import java.util.List;

import com.yuanmaxinxi.dao.dictionary.DictionaryDAO;
import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.util.CrawUniversityAllUtil;
import com.yuanmaxinxi.util.CrawUniversityUtil;
import com.yuanmaxinxi.util.StringUtil;


//主要进行非空验证
public class UniversityService {
	private UniversityDao universityDAO = new UniversityDao();
	private ProvinceDao provinceDao = new ProvinceDao();
	//验证服务器中的数据是否为空
	public void insert(University obj) {
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("院校名称不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getAddress())) {
			throw new RuntimeException("院校所在地不能空");
		}
		if(obj.getQuality()==0||obj.getQuality()==null) {
			throw new RuntimeException("院校水平不能空");
		}
		if(obj.getType()==0||obj.getType()==null) {
			throw new RuntimeException("院校类型不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("院校简介不能空");
		}
		if(obj.getRanking()==0||obj.getType()==null) {
			throw new RuntimeException("院校排名不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getTeachers())) {
			throw new RuntimeException("院校师资团队不能空");
		}
		if(obj.getRecord()==0||obj.getRecord()==null) {
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


	public void update(University obj) {
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("院校名称不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getAddress())) {
			throw new RuntimeException("院校所在地不能空");
		}
		if(obj.getQuality()==0||obj.getQuality()==null) {
			throw new RuntimeException("院校水平不能空");
		}
		if(obj.getType()==0||obj.getType()==null) {
			throw new RuntimeException("院校类型不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("院校简介不能空");
		}
		if(obj.getRanking()==0) {
			throw new RuntimeException("院校排名不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getTeachers())) {
			throw new RuntimeException("院校师资团队不能空");
		}
		if(obj.getRecord()==0||obj.getRecord()==null) {
			throw new RuntimeException("院校学历不能空");
		}
		if(StringUtil.isNullOrEmpty(obj.getSubject())) {
			throw new RuntimeException("院校学科建设不能空");
		}
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
		DictionaryDAO dao=DictionaryDAO.getDictionaryDao();
		List<University> list = universityDAO.selectAll();
		//再后台进行省份确定
		for (University obj : list) {
			//做回显处理
			Province pro = provinceDao.selectOneById(obj.getpId());
			obj.setPro(pro);
			//院校水平，有问题？？？
			Dictionary quality = dao.selectOneById(obj.getQuality());
			obj.setQualityDic(quality);
			//院校类型
			Dictionary type = dao.selectOneById(obj.getType());
			obj.setTypeDic(type);
			//学历
			Dictionary record = dao.selectOneById(obj.getRecord());
			obj.setRecordDic(record);
		}
		if (list==null) {
			throw new RuntimeException("院校信息不存在.");
		}
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
	
	public List<University> queryPage(BaseQueryPageDTO dto) {
		return null;
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
	

	public void craw() {
		//查到所有的省
		List<Province> pros = provinceDao.selectAll();
		try {
			for (Province pro : pros) {
				//将省名称进行URL编码
				String proName = URLEncoder.encode(pro.getName(), "utf-8");
				//默认将第一页的URL放进队列中
				CrawUniversityUtil.que.put("https://gkcx.eol.cn/soudaxue/queryschool.html?&page="+1+"&province="+proName);
			}
			CrawUniversityUtil.startCraw();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 爬取学校
	 */
	public void craw1() {
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
}
