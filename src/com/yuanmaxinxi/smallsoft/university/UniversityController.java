package com.yuanmaxinxi.smallsoft.university;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.dto.universitydto.UniversityQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.UniversityService;
@RequestMapping("/soft/university")
@Controller
public class UniversityController{
	@Autowired
	private UniversityService universityService;
	@RequestMapping("/selectEnrollByTwoUni")
	@ResponseBody
	public List<Enroll> selectEnrollByTwoUni(String batch,String mId,String uIds){
		List<Enroll> ens = universityService.selectEnrollByTwoUni(batch,mId,uIds);
		return ens;
	}
	@RequestMapping("/selectProvince")
	@ResponseBody
	public List<Province> selectProvince(){
		List<Province> provinces = universityService.selectProvince();
		return provinces;
	}
	@RequestMapping("/selectPropertys")
	@ResponseBody
	public List<String> selectPropertys(){
		List<String> propertys = universityService.selectPropertys();
		return propertys;
	}
	@RequestMapping("/selectUnisByIds")
	@ResponseBody
	public List<University> selectUnisByIds(String ids){
		List<University> list = universityService.selectUnisByIds(ids);
		return list;
	}
	@RequestMapping("/selectMajor")
	@ResponseBody
	public List<Major> selectMajor(String ids){
		List<Major> majors = universityService.selectMajor(ids);
		return majors;
	}
	@RequestMapping("/addShoucang")
	@ResponseBody
	public ResultDTO addShoucang(String id,String uId){
		ResultDTO dto;
		try {
			universityService.addShoucang(uId,id);
			dto = ResultDTO.newInstance(true, "收藏成功!");
		} catch (Exception e) {
			dto = ResultDTO.newInstance(false, e.getMessage());
		}
		return dto;
	}
	@RequestMapping("/selectShoucangUnis")
	@ResponseBody
	public List<University> selectShoucangUnis(String uId){
		List<University> unis = universityService.selectShoucangUnis(uId);
		return unis;
	}
	@RequestMapping("/unShoucang")
	@ResponseBody
	public ResultDTO unShoucang(String id,String uId){
		ResultDTO dto;
		try {
			universityService.unShoucang(uId,id);
			dto = ResultDTO.newInstance(true, "取消收藏成功!");
		} catch (Exception e) {
			dto = ResultDTO.newInstance(true, "取消收藏失败!"+e.getMessage());
		}
		return dto;
	}
	@RequestMapping("/shoucang")
	@ResponseBody
	public University shoucang(String id,String uId){
		University uni = universityService.selectOneShoucang(uId,id);
		return uni;
	}
	@RequestMapping("/luquYear")
	@ResponseBody
	public List<Enroll> luquYear(String id,String mId,String activBatch){
		List<Enroll> majors = universityService.selectYearByMajorAndBidAndId(id,activBatch,mId);
		return majors;
	}
	@RequestMapping("/luquMajors")
	@ResponseBody
	public List<Major> luquMajors(String id,String activBatch){
		List<Major> majors = universityService.selectMajorsById(id,activBatch);
		return majors;
	}
	@RequestMapping("/details1")
	@ResponseBody
	public University details1(Long id){
		University uni = universityService.selectOneById(id);//查询t_university表
		//院校简介数据处理
		String remark = uni.getRemark();
		String replace = remark.replace(" 　　", "\n&emsp;&emsp;");
		uni.setRemark(replace);
		//院校师资力量数据处理
		String teachers = uni.getTeachers();
		if(teachers.contains("下面")) {
			int indexOf = teachers.indexOf("下面");
			teachers=teachers.substring(0, indexOf);
			uni.setTeachers(teachers);
		}
		return uni;
	}
	@RequestMapping("/list")
	@ResponseBody
	public UniversityQueryPageDTO list(UniversityQueryPageDTO dto){
			universityService.queryPage(dto);//0-10条数据
			return dto;
	}
}
