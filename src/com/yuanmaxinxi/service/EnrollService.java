package com.yuanmaxinxi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.batch.BatchDAO;
import com.yuanmaxinxi.dao.enroll.EnrollDAO;
import com.yuanmaxinxi.dao.error.ErrorContentDAO;
import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.dto.enroll.EnrollQueryPageDTO;
import com.yuanmaxinxi.entity.batch.Batch;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.error.ErrorContent;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.web.enroll.EnrollController;
@Service
@Transactional
public class EnrollService {
	@Autowired
	private EnrollDAO enrollDAO;
	@Autowired
	private UniversityDao universityDao;
	@Autowired
	private ProvinceDao provinceDao;
	@Autowired
	private BatchDAO batchDAO;
	@Autowired
	private MajorDAO majorDAO;
	@Autowired
	private ErrorContentDAO errorContentDAO;
	public void insert(Enroll obj) throws Exception{
		enrollDAO.insert(obj);
	}

	public void update(Enroll obj) throws Exception{
		enrollDAO.update(obj);
	}

	public void delete(Long id) throws Exception{
		enrollDAO.delete(id);
	}

	public Enroll selectOneById(Long id) {
		return enrollDAO.selectOneById(id);
	}

	public List<Enroll> selectAll() {
		return enrollDAO.selectAll();
	}

	public void queryPage(EnrollQueryPageDTO dto) {
		int count = enrollDAO.selectCount(dto);
		dto.setCount(count);
		List<Enroll> list = enrollDAO.queryPage(dto);
		dto.setRows(list);
	}

	public int importEnroll(Map<String, String> map) {
		try {
			ErrorContent ec = new ErrorContent();
			//获取学校
			University uni = universityDao.selectOneByName(map.get("universityName"));
			//获取地区
			Province pro = provinceDao.selectOneByName(map.get("province"));
			//获取批次
			Batch bt = batchDAO.selectOneByName(map.get("batch"));
			//获取专业
			Major major = majorDAO.selectOneByOn(map.get("no"));
			if (major==null) {
				//保存major
				String majorName = map.get("major");
				major = new Major();
				major.setName(majorName);
				majorDAO.insert(major);
			}
			//如果根据名称没有能获取学校  那么后面的步骤都毫无意义  
			//没有地区 存到errorcontent中
			//如果没有批次 存在error...
			if (uni==null||pro==null||bt==null) {
				//直接将数据存到errorContent中
				ec.setContent(map.toString());
				errorContentDAO.insert(ec);
				return 1;
			}
			//获取专业
			Enroll enroll = new Enroll();
			enroll.setuId(uni.getId());
			enroll.setpId(pro.getId());
			enroll.setbId(bt.getId());
			enroll.setmId(major.getId());
			try {
				enroll.setMaxNumber(Integer.parseInt(map.get("maxNumber")));
			} catch (Exception e) {
				enroll.setMaxNumber(0);
			}
			try {
				enroll.setAvgNumber(Integer.parseInt(map.get("avgNumber")));
			} catch (Exception e) {
				enroll.setAvgNumber(0);
			}
			try {
				enroll.setMinNumber(Integer.parseInt(map.get("minNumber")));
			} catch (Exception e) {
				enroll.setMinNumber(0);
			}
			try {
				enroll.setNumber(Integer.parseInt(map.get("number")));
			} catch (Exception e) {
				enroll.setNumber(0);
			}
			try {
				enroll.setTime(Integer.parseInt(map.get("time")));
			} catch (Exception e) {
				ec.setContent(map.toString());
				errorContentDAO.insert(ec);
				return 1;
			}
			try {
				enroll.setMinRanking(Integer.parseInt(map.get("minRanking")));
			} catch (Exception e) {
				enroll.setMinRanking(0);
			}
			
			int i = enrollDAO.insert(enroll);
			if (i!=1) {
				throw new RuntimeException("插入数据库失败.");
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			EnrollController.currentNumber = 0;
			EnrollController.msg="";
			EnrollController.rownum = 0;
			throw new RuntimeException("导入失败.");
		}
	}
}