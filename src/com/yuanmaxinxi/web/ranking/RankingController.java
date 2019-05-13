package com.yuanmaxinxi.web.ranking;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.ranking.Ranking;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.RankingService;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.util.CrawWuShuLian;

@RequestMapping("/ranking")
@Controller
public class RankingController {
	@Autowired
	private RankingService rankingService;
	@Autowired
	private UniversityService universityService;
	/**
	 * 爬取武书连
	 * @return
	 */
	@RequestMapping("/xx2")
	public String xx2() {
		//爬取排名数据
		List<Map<String,Object>> list = CrawWuShuLian.crawWUShuLianData();
		for (Map<String, Object> map : list) {
			if (map.get("name").equals("国防科学技术大学")) {
				continue;
			}
			if (map.get("name").equals("中国地质大学")) {
				continue;
			}
			if (map.get("name").equals("中国石油大学")) {
				continue;
			}
			if (map.get("name").equals("解放军信息工程大学")) {
				continue;
			}
			if (map.get("name").equals("第三军医大学")) {
				continue;
			}
			if (map.get("name").equals("第二军医大学")) {
				continue;
			}
			if (map.get("name").equals("第四军医大学")) {
				continue;
			}
			if (map.get("name").equals("解放军理工大学")) {
				continue;
			}
			University uni = universityService.selectOneByName((String)map.get("name"));
			Ranking r = new Ranking();
			r.setuId(uni.getId());
			r.setType(1);
			Ranking ran = rankingService.selectOneById(r);
			if (ran==null||ran.getRanking()<=0) {
				map.put("uId", uni.getId());
				map.put("type", 1);
				System.out.println(map);
				map.put("number",new BigDecimal((String)map.get("number")));
				map.put("ranking",Integer.parseInt((String)map.get("ranking")));
				rankingService.insert2(map);
				System.out.println("-----------------插入---------------------");
			}
		}
		return "";
	}
	@RequestMapping("/xx")
	public String xx() {
		//爬取排名数据
		List<Map<String,Object>> list = CrawWuShuLian.crawXiaoYouHuiData();
		for (Map<String, Object> map : list) {
			if (map.get("name").equals("国防科学技术大学")) {
				continue;
			}
			if (map.get("name").equals("中国地质大学")) {
				continue;
			}
			if (map.get("name").equals("中国石油大学")) {
				continue;
			}
			if (map.get("name").equals("解放军信息工程大学")) {
				continue;
			}
			if (map.get("name").equals("第三军医大学")) {
				continue;
			}
			if (map.get("name").equals("第二军医大学")) {
				continue;
			}
			if (map.get("name").equals("第四军医大学")) {
				continue;
			}
			if (map.get("name").equals("解放军理工大学")) {
				continue;
			}
			University uni = universityService.selectOneByName((String)map.get("name"));
			Ranking r = new Ranking();
			r.setuId(uni.getId());
			r.setType(2);
			Ranking ran = rankingService.selectOneById(r);
			if (ran==null||ran.getRanking()<=0) {
				map.put("uId", uni.getId());
				map.put("type", 2);
				System.out.println(map);
				map.put("number",new BigDecimal((String)map.get("number")));
				map.put("ranking",Integer.parseInt((String)map.get("ranking")));
				rankingService.insert2(map);
				System.out.println("-----------------插入---------------------");
			}
		}
		return "";
	}
	@RequestMapping("/list")
	public String list(HttpServletRequest req) {
		List<Ranking> list = rankingService.selectAll(2);
		req.setAttribute("list", list);
		return "ranking/list";
	}
	@RequestMapping("/showContent")
	public String showContent(Long id,HttpServletRequest req) {
//		Ranking obj = rankingService.selectOneById(id);
//
//		List<University> uIdUniversityList = universityService.selectAll();
//		req.setAttribute("uIdUniversityList", uIdUniversityList);
//
//		req.setAttribute("obj", obj);
		return "ranking/show";
	}
	@RequestMapping("/showAdd")
	public String showAdd(HttpServletRequest req) {

		List<University> uIdUniversityList = universityService.selectAll();
		req.setAttribute("uIdUniversityList", uIdUniversityList);

		return "ranking/add";
	}
	@RequestMapping("/add")
	@ResponseBody
	public ResultDTO add(Ranking ranking) {
		try {
			rankingService.insert(ranking);
			return ResultDTO.putSuccess("添加成功!");
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
	@RequestMapping("/showEdit")
	public String showEdit(Long id,HttpServletRequest req) {
//		Ranking obj = rankingService.selectOneById(id);
//
//		List<University> uIdUniversityList = universityService.selectAll();
//		req.setAttribute("uIdUniversityList", uIdUniversityList);
//
//		req.setAttribute("obj", obj);
		return "ranking/edit";
	}
	@RequestMapping("/edit")
	@ResponseBody
	public ResultDTO edit(Ranking ranking) {
		try {
			rankingService.update(ranking);
			return ResultDTO.putSuccess("修改成功!");
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
	@RequestMapping("/delete")
	@ResponseBody
	public ResultDTO delete(Long id) {
		try {
			rankingService.delete(id);
			return ResultDTO.putSuccess("删除成功!");
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
	@RequestMapping("/selectAllRankingsByUniversityId")
	@ResponseBody
	public ResultDTO selectAllRankingsByUniversityId(Long id){
		try {
			List<Ranking> list = rankingService.selectAllRankingsByUniversityId(id);
			return ResultDTO.putSuccessObj("查询成功!",list);
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}

}