package com.yuanmaxinxi.web.ranking;
import java.util.List;

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

@RequestMapping("/ranking")
@Controller
public class RankingController {
	@Autowired
	private RankingService rankingService;
	@Autowired
	private UniversityService universityService;

	@RequestMapping("/list")
	public String list(HttpServletRequest req) {
		List<Ranking> list = rankingService.selectAll();
		req.setAttribute("list", list);
		return "ranking/list";
	}
	@RequestMapping("/showContent")
	public String showContent(Long id,HttpServletRequest req) {
		Ranking obj = rankingService.selectOneById(id);

		List<University> uIdUniversityList = universityService.selectAll();
		req.setAttribute("uIdUniversityList", uIdUniversityList);

		req.setAttribute("obj", obj);
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
		Ranking obj = rankingService.selectOneById(id);

		List<University> uIdUniversityList = universityService.selectAll();
		req.setAttribute("uIdUniversityList", uIdUniversityList);

		req.setAttribute("obj", obj);
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