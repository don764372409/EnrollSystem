package com.yuanmaxinxi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public void xxxx() {
		try {
			List<Enroll> ens = enrollDAO.selectAll();
			for (Enroll en : ens) {
				if (en.getAvgRanking()==0&&en.getMaxRanking()>0&&en.getMinRanking()>0) {
					en.setAvgRanking((en.getMaxRanking()+en.getMinRanking()));
					enrollDAO.update(en);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("报错了");
		}
	}
	
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

	public List<Province> selectEnrollProvince() {
		return enrollDAO.selectEnrollProvince();
	}
	/**
	 * select en from ( 
	 * 		select * from t_enroll where pId = ? and minRanking>=(?-range) and maxRanking >=(?-range) and bId = ?
	 * )en where en.minNumber >=? and en.maxNumber >= ?
	 * @param map
	 * @return
	 */
	public Map<String,Map<String,List<Enroll>>> serch(Map<String, Object> map) {
		Batch batch = batchDAO.selectOneByName(map.get("bath")+" "+map.get("type"));
		map.put("bId", batch.getId());
		List<Enroll> list = enrollDAO.serch(map);
		//学校缓存
		Map<Long,University> uniCache = new HashMap<>();
		//专业缓存
		Map<Long,Major> marjorCache = new HashMap<>();
		//批次缓存
		Map<Long,Batch> batchCache = new HashMap<>();
		//省份缓存
		Map<Long,Province> proviceCache = new HashMap<>();
		Map<String,Map<String,List<Enroll>>> result = new HashMap<>();
		for (Enroll er : list) {
			String year = er.getTime()+"";
			//判断年份是否已经存在  不存在就放进去  存在就获取集合
			Map<String, List<Enroll>> res = result.get(year);
			if (res==null) {
				res = new HashMap<>();
				result.put(year, res);
			}
			//获取学校名称
			Long uId = er.getuId();
			University uni = uniCache.get(uId);
			if (uni==null) {
				uni = universityDao.selectOneById(uId);
				uniCache.put(uId, uni);
			}
			er.setUniversity(uni);
			//先从集合中根据学校名称获取集合  不存在就创建集合并加入map
			List<Enroll> ens = res.get(uni.getName());
			if (ens==null) {
				ens = new ArrayList<>();
				res.put(uni.getName(), ens);
			}
			//获取专业名称
			Long mId = er.getmId();
			Major major = marjorCache.get(mId);
			if (major==null) {
				major = majorDAO.selectOneById(mId);
				marjorCache.put(mId, major);
			}
			er.setMajor(major);
			//获取批次
			Long bId = er.getbId();
			Batch bt = batchCache.get(bId);
			if (bt==null) {
				bt = batchDAO.selectOneById(bId);
				batchCache.put(bId, bt);
			}
			er.setBatch(bt);
			//获取省份
			Long pId = er.getpId();
			Province province = proviceCache.get(pId);
			if (province==null) {
				province = provinceDao.selectOneById(pId);
				proviceCache.put(pId, province);
			}
			er.setProvince(province);
			ens.add(er);
		}
		return result;
	}
}