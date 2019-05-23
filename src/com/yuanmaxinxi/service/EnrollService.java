package com.yuanmaxinxi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
import com.yuanmaxinxi.dto.UniAndMajorDTO;
import com.yuanmaxinxi.dto.UniNumberDTO;
import com.yuanmaxinxi.dto.enroll.EnrollQueryPageDTO;
import com.yuanmaxinxi.entity.batch.Batch;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.enroll.EnrollMajor;
import com.yuanmaxinxi.entity.enroll.UniEnrollMajor;
import com.yuanmaxinxi.entity.error.ErrorContent;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.provincescore.Provincescore;
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

	public List<UniEnrollMajor> queryUniANDMajorByRank(EnrollQueryPageDTO page) {
		Long uId;
//		Map<University,List<String>> umMap = new HashMap<>();
		Map<Long, List<Enroll>> ueMap = new HashMap<>();
		for (Enroll e : enrollDAO.queryEnrollByRankANDMajor(page)) {
			uId = e.getuId();
			if (!ueMap.containsKey(uId)) {
				ueMap.put(uId, new ArrayList<>());
			}
			ueMap.get(uId).add(e);
		}

		int i = page.getPageSize();
		List<UniEnrollMajor> uems = new ArrayList<>();
		for (Enroll e : enrollDAO.queryEnrollByRankUni()) {
			uId = e.getuId();
			if (ueMap.containsKey(uId) && ueMap.get(uId) != null) {
				System.err.println(uId);

				University uni = universityDao.selectOneById(uId);
				uni.setRemark("");
				List<EnrollMajor> ems = new ArrayList<>();
				List<Enroll> es = ueMap.get(uId);
				for (int j = 0; j < (es.size() > 6 ? 6 : es.size()); j++) {
					Enroll ee=es.get(j);
					EnrollMajor em = new EnrollMajor();
					em.setAvgRank(ee.getAvgRanking());
					em.setMaxRank(ee.getMaxRanking());
					em.setMinRank(ee.getMinRanking());
					em.setmId(ee.getmId());
					Major major = majorDAO.selectOneById(ee.getmId());
					em.setName(major.getName());
					em.setNo(major.getNo());
					ems.add(em);
				}
				/*
				for (Enroll ee : ueMap.get(uId)) {
				
				}
				*/

				UniEnrollMajor uem = new UniEnrollMajor();
				uem.setUni(uni);
				uem.setEms(ems);
				uems.add(uem);
				if (--i <= 0) {
					break;
				}
			}
		}
		return uems;
	}

	public List<UniEnrollMajor> queryUniANDMajorByRankANDMajor(EnrollQueryPageDTO page) {
		// 查询学校参数
		Map<Long, University> uMap = new HashMap<>();
		for (University u : universityDao.selectAllEnrollMajor()) {
			uMap.put(u.getId(), u);
		}

		// 查询录取数据里的学校专业
		Map<Long, List<Enroll>> ueMap = new HashMap<>();
		for (Enroll e : enrollDAO.queryEnrollByRankANDMajor(page)) {
			Long uId = e.getuId();
			if (!ueMap.containsKey(uId)) {
				ueMap.put(uId, new ArrayList<>());
			}
			ueMap.get(uId).add(e);
		}

		// 根据查询参数计数
		Map<Long, Integer> uniSortCount = new HashMap<>();
		for (Long uId : ueMap.keySet()) {
			System.err.println(uId);
			if (ueMap.containsKey(uId) && ueMap.get(uId) != null) {
				int count = ueMap.get(uId).size();
				University u = uMap.get(uId);

				if (page.getuPId() == u.getpId()) {
					count++;
				}
				if (page.getProperty() != null && u.getProperty() != null
						&& page.getProperty().equals(u.getProperty())) {
					count++;
				}

				int type = page.getuType();
				if (type == 1 && u.getF985() == 1) {
					count++;
				} else if (type == 2 && u.getF211() == 1) {
					count++;
				} else if (type == 3 && u.getF211() == 0 && u.getF985() == 0) {
					count++;
				}
				uniSortCount.put(uId, count);
			}
		}
		if (uniSortCount.size() == 0) {
			return null;
		}

		// 计算计数最多的学校,如果计数相同,选择排名高的学校
		LinkedList<Long> uIds = new LinkedList<>();
		int size = page.getPageSize();
		for (Enroll e : enrollDAO.queryEnrollByRankUni()) {
			Long uId = e.getuId();
			if (uniSortCount.containsKey(uId)) {
				if (uIds.size() < size) {
					uIds.addLast(uId);
				} else {
					Long puId = uIds.getFirst();
					for (int i = 0; i < size; i++) {
						if (uniSortCount.get(uId) > uniSortCount.get(puId)) {
							uIds.add(i, uId);
							break;
						}
					}
				}
			}
		}
		if (size > uIds.size()) {
			size = uIds.size();
		}

		List<UniEnrollMajor> uems = new ArrayList<>();
		// 封装学校和专业
		for (int i = 0; i < size; i++) {
			Long uId = uIds.get(i);
			University uni = uMap.get(uId);
			List<EnrollMajor> ems = new ArrayList<>();
			for (Enroll ee : ueMap.get(uId)) {
				EnrollMajor em = new EnrollMajor();
				em.setAvgRank(ee.getAvgRanking());
				em.setMaxRank(ee.getMaxRanking());
				em.setMinRank(ee.getMinRanking());
				em.setmId(ee.getmId());
				Major major = majorDAO.selectOneById(ee.getmId());
				if (major != null) {
					em.setName(major.getName());
					em.setNo(major.getNo());
				}
				ems.add(em);
			}
			UniEnrollMajor uem = new UniEnrollMajor();
			uem.setUni(uni);
			uem.setEms(ems);
			uems.add(uem);
		}
		return uems;
	}

	public void xxxx() {

	}

	public int queryProvinceScore(Provincescore ps) {
		Provincescore score = enrollDAO.queryProvinceScore(ps);
		return score.getScore();
	}

	public Batch selectOneByName(String name) {
		return batchDAO.selectOneByName(name);
	}

	public void insert(Enroll obj) throws Exception {
		enrollDAO.insert(obj);
	}

	public void update(Enroll obj) throws Exception {
		enrollDAO.update(obj);
	}

	public void delete(Long id) throws Exception {
		enrollDAO.delete(id);
	}

	public Enroll selectOneById(Long id) {
		return enrollDAO.selectOneById(id);
	}

	public List<Enroll> selectAll() {
		return enrollDAO.selectAll();
	}

	public List<Enroll> queryPage(EnrollQueryPageDTO dto) {
		int count = enrollDAO.selectCount(dto);
		dto.setCount(count);
		List<Enroll> list = enrollDAO.queryPage(dto);
		dto.setRows(list);
		return list;
	}

	public List<University> queryUniversity(String name) {
		return enrollDAO.queryUniversity(name);
	}
	/**
	 * 根据招生地区和学校id查询录取数据里面的专业
	 * @param map
	 * @return
	 */
	public List<Major> queryMajorByuId(Long uId,Long pId) {
		Map<String,Long> map = new HashMap<>();
		map.put("uId", uId);
		map.put("pId", pId);
		return enrollDAO.queryMajorByuId(map);
	}

	public int importEnroll(Map<String, String> map) {
		try {
			ErrorContent ec = new ErrorContent();
			// 获取学校
			University uni = universityDao.selectOneByName(map.get("universityName"));
			// 获取地区
			Province pro = provinceDao.selectOneByName(map.get("province"));
			// 获取批次
			Batch bt = batchDAO.selectOneByName(map.get("batch"));
			// 获取专业
			Major major = majorDAO.selectOneByOn(map.get("no"));
			if (major == null) {
				// 保存major
				String majorName = map.get("major");
				major = new Major();
				major.setName(majorName);
				majorDAO.insert(major);
			}
			// 如果根据名称没有能获取学校 那么后面的步骤都毫无意义
			// 没有地区 存到errorcontent中
			// 如果没有批次 存在error...
			if (uni == null || pro == null || bt == null) {
				// 直接将数据存到errorContent中
				ec.setContent(map.toString());
				errorContentDAO.insert(ec);
				return 1;
			}
			// 获取专业
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
			if (i != 1) {
				throw new RuntimeException("插入数据库失败.");
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			EnrollController.currentNumber = 0;
			EnrollController.msg = "";
			EnrollController.rownum = 0;
			throw new RuntimeException("导入失败.");
		}
	}

	public List<Province> selectEnrollProvince() {
		return enrollDAO.selectEnrollProvince();
	}

	public List<UniNumberDTO> serch2(Map<String, Object> map) {
		Batch batch = batchDAO.selectOneByName(map.get("bath") + " " + map.get("type"));
		map.put("bId", batch.getId());
		List<Enroll> list = enrollDAO.serch(map);
		// 学校缓存
		Map<Long, University> uniCache = new HashMap<>();
		// 专业缓存
		Map<Long, Major> marjorCache = new HashMap<>();
		// 省份缓存
		Map<Long, Province> proviceCache = new HashMap<>();
		// 装学校和专业出现次数的
		List<UniNumberDTO> uniNumberDTOs = new ArrayList<>();
		Set<Integer> years = new HashSet<>();
		for (Enroll er : list) {
			years.add(er.getTime());
			// 获取学校名称
			Long uId = er.getuId();
			University uni = uniCache.get(uId);
			if (uni == null) {
				uni = universityDao.selectOneById(uId);
				uniCache.put(uId, uni);
			}
			er.setUniversity(uni);
			// 获取专业名称
			Long mId = er.getmId();
			Major major = marjorCache.get(mId);
			if (major == null) {
				major = majorDAO.selectOneById(mId);
				marjorCache.put(mId, major);
			}
			er.setMajor(major);

			// 出现次数
			int isExitIndex = -1;// 没有出现
			for (int i = 0; i < uniNumberDTOs.size(); i++) {
				UniNumberDTO unDTO = uniNumberDTOs.get(i);
				if (unDTO.getuId().equals(uId)) {
					// 出现了
					System.err.println(uni.getName() + "的出现索引:" + i);
					isExitIndex = i;
					break;
				}
			}
			// 没有存起来
			if (isExitIndex == -1) {
				UniNumberDTO unDTO = new UniNumberDTO();
				unDTO.setuId(uId);
				unDTO.setUniNumber(1);
				unDTO.setName(uni.getName());
				List<UniAndMajorDTO> uams = new ArrayList<>();
				unDTO.setUams(uams);
				UniAndMajorDTO uamDTO = new UniAndMajorDTO();
				uamDTO.setmId(mId);
				uamDTO.setName(major.getName());
				uamDTO.setMinNumber(er.getMinNumber());
				uamDTO.setMaxNumber(er.getMaxNumber());
				uamDTO.setNumber(1);
				uams.add(uamDTO);
				Long pId = er.getpId();
				Province province = proviceCache.get(pId);
				if (province == null) {
					province = provinceDao.selectOneById(pId);
					proviceCache.put(pId, province);
				}
				unDTO.setProvinceName(province.getName());
				uniNumberDTOs.add(unDTO);
			} else {
				UniNumberDTO numberDTO = uniNumberDTOs.get(isExitIndex);
				numberDTO.setUniNumber(numberDTO.getUniNumber() + 1);
				List<UniAndMajorDTO> uams = numberDTO.getUams();
				int isExitIndex2 = -1;
				for (int j = 0; j < uams.size(); j++) {
					UniAndMajorDTO uamDTO = uams.get(j);
					if (mId.equals(uamDTO.getmId())) {
						isExitIndex2 = j;
						break;
					}
				}
				if (isExitIndex2 == -1) {
					UniAndMajorDTO uamDTO = new UniAndMajorDTO();
					uamDTO.setmId(mId);
					uamDTO.setName(major.getName());
					uamDTO.setMinNumber(er.getMinNumber());
					uamDTO.setMaxNumber(er.getMaxNumber());
					uamDTO.setNumber(1);
					uams.add(uamDTO);
				} else {
					UniAndMajorDTO uamDTO = uams.get(isExitIndex2);
					if (er.getMinNumber() < uamDTO.getMinNumber()) {
						uamDTO.setMinNumber(er.getMinNumber());
					}
					;
					if (er.getMaxNumber() > uamDTO.getMaxNumber()) {
						uamDTO.setMaxNumber(er.getMaxNumber());
					}
					uamDTO.setNumber(uamDTO.getNumber() + 1);
				}
			}
		}
		int total = years.size();
		for (UniNumberDTO dto : uniNumberDTOs) {
			int number = dto.getUniNumber();
			if (total <= number - 1) {
				dto.setLv("color_green");
			} else if (number == 1) {
				dto.setLv("color_red");
			} else if (number == 2) {
				dto.setLv("color_yellow");
			} else {
				dto.setLv("color_blue");
			}
		}
		return uniNumberDTOs;
	}

	/**
	 * select en from ( select * from t_enroll where pId = ? and
	 * minRanking>=(?-range) and maxRanking >=(?-range) and bId = ? )en where
	 * en.minNumber >=? and en.maxNumber >= ?
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Map<String, List<Enroll>>> serch(Map<String, Object> map) {
		Batch batch = batchDAO.selectOneByName(map.get("bath") + " " + map.get("type"));
		map.put("bId", batch.getId());
		List<Enroll> list = enrollDAO.serch(map);
		// 学校缓存
		Map<Long, University> uniCache = new HashMap<>();
		// 专业缓存
		Map<Long, Major> marjorCache = new HashMap<>();
		// 批次缓存
		Map<Long, Batch> batchCache = new HashMap<>();
		// 省份缓存
		Map<Long, Province> proviceCache = new HashMap<>();
		Map<String, Map<String, List<Enroll>>> result = new HashMap<>();
		// 同一个学校的 出现次数
		// 同一个学校 同一个专业 将次数放进录取数据中
		// key: uId:xx,mId:yy
		Map<Long, Integer> uniNumbers = new HashMap<>();
		Map<String, Integer> uniAndMajorNumbers = new HashMap<>();

		for (Enroll er : list) {
			// 次数获取
			String year = er.getTime() + "";
			// 判断年份是否已经存在 不存在就放进去 存在就获取集合
			Map<String, List<Enroll>> res = result.get(year);
			if (res == null) {
				res = new HashMap<>();
				result.put(year, res);
			}
			// 获取学校名称
			Long uId = er.getuId();
			University uni = uniCache.get(uId);
			if (uni == null) {
				uni = universityDao.selectOneById(uId);
				uniCache.put(uId, uni);
			}
			er.setUniversity(uni);
			// 先从集合中根据学校名称获取集合 不存在就创建集合并加入map
			List<Enroll> ens = res.get(uni.getName());
			if (ens == null) {
				ens = new ArrayList<>();
				res.put(uni.getName(), ens);
			}
			// 获取专业名称
			Long mId = er.getmId();
			Major major = marjorCache.get(mId);
			if (major == null) {
				major = majorDAO.selectOneById(mId);
				marjorCache.put(mId, major);
			}
			er.setMajor(major);

			// 出现次数
			Integer uniNumber = uniNumbers.get(uId);
			if (uniNumber == null || uniNumber == 0) {
				uniNumber = 1;
				uniNumbers.put(uId, uniNumber);
			} else {
				++uniNumber;
				uniNumbers.put(uId, uniNumber);
			}
			Integer uniAndMajorNumber = uniAndMajorNumbers.get("uId:" + uId + ",mId:" + mId);
			if (uniAndMajorNumber == null || uniAndMajorNumber == 0) {
				uniAndMajorNumber = 1;
				uniAndMajorNumbers.put("uId:" + uId + ",mId:" + mId, uniAndMajorNumber);
			} else {
				++uniAndMajorNumber;
				uniAndMajorNumbers.put("uId:" + uId + ",mId:" + mId, uniAndMajorNumber);
			}
			// 获取批次
			Long bId = er.getbId();
			Batch bt = batchCache.get(bId);
			if (bt == null) {
				bt = batchDAO.selectOneById(bId);
				batchCache.put(bId, bt);
			}
			er.setBatch(bt);
			// 获取省份
			Long pId = er.getpId();
			Province province = proviceCache.get(pId);
			if (province == null) {
				province = provinceDao.selectOneById(pId);
				proviceCache.put(pId, province);
			}
			er.setProvince(province);
			ens.add(er);
		}
		for (Enroll er : list) {
			Long uId = er.getuId();
			Long mId = er.getmId();
			Integer uniNumber = uniNumbers.get(uId);
			Integer uniAndMajorNumber = uniAndMajorNumbers.get("uId:" + uId + ",mId:" + mId);
			er.setUniNumber(uniNumber);
			er.setUniAndMajorNumber(uniAndMajorNumber);
		}
//		Set<String> keySet = result.keySet();
//		for (String key : keySet) {
//			System.err.println(key+"年-----------");
//			Map<String, List<Enroll>> map2 = result.get(key);
//			Set<String> keySet2 = map2.keySet();
//			for (String name : keySet2) {
//				System.err.println("学校："+name);
//				List<Enroll> list2 = map2.get(name);
//				for (Enroll enroll : list2) {
//					System.err.println(enroll.getMajor().getName()+",排名:"+enroll.getAvgRanking());
//				}
//			}
//		}
		return result;
	}

	/**
	 * 获取录取可能性较大院校 当前排名 >= 最小排名 当前排名<=最大排名 加上批次 加上理科文科 -->得到数据 -->再次得到的数据中获取以下消息
	 * 当前分数 >=最小分数 当前分数<=最大分数
	 * 
	 * @param map
	 * @return
	 */
	public List<Enroll> enrollBigUni(Map<String, Object> map) {
		List<Enroll> list = enrollDAO.enrollBigUni(map);
		Map<Long, University> uniCache = new HashMap<>();
		List<Enroll> exit = new ArrayList<>();
		for (Enroll er : list) {
			// 获取学校名称
			Long uId = er.getuId();
			University uni = uniCache.get(uId);
			if (uni == null) {
				uni = universityDao.selectOneById(uId);
				uniCache.put(uId, uni);
			} else {
				exit.add(er);
			}
			er.setUniversity(uni);
		}
		// 删除已经存在的学校
		for (Enroll enroll : exit) {
			list.remove(enroll);
		}
		return list;
	}

	public List<Province> selectEnrollProvinceByUniId(Long uniId) {
		return enrollDAO.selectEnrollProvinceByUniId(uniId);
	}
}