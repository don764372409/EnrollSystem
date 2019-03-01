package com.yuanmaxinxi.smallsoft.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.service.EnrollService;
import com.yuanmaxinxi.service.UniversityService;

/**
 * 会员功能
 * @author 微笑ぃ一刀
 *
 */
@Controller
@RequestMapping("/member")
@CrossOrigin
public class MemberController {
	@Autowired
	private UniversityService universityService;
	@Autowired
	private EnrollService enrollService;
	@RequestMapping("/xx")
	public @ResponseBody Object xx(HttpSession session,HttpServletResponse response) {
		enrollService.xxxx();
		return "login";
	}
	/**
	 * 获取招生省份  提供给用户做查询
	 * @return
	 */
	@RequestMapping("/selectEnrollProvince")
	@ResponseBody
	public List<Province> selectEnrollProvince(){
		return enrollService.selectEnrollProvince();
	}
	
	/**
	 * 历年录取匹配查询
	 * @return
	 */
	@RequestMapping("/serch")
	@ResponseBody
	public Map<String,Map<String,List<Enroll>>> serch(Long pId,String bath,String type,int ranking,int number,int range){
		Map<String,Object> map = new HashMap<>();
		map.put("pId", pId);
		map.put("bath", bath);
		map.put("type", type);
		map.put("ranking", ranking);
		map.put("number", number);
		map.put("range", range);
		return enrollService.serch(map);
	}
	
}
