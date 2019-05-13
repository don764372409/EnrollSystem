package com.yuanmaxinxi.smallsoft.ranking;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.RankingPageDTO;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.ranking.Ranking;
import com.yuanmaxinxi.service.RankingService;
@RequestMapping("/soft/ranking")
@Controller
@CrossOrigin
public class APIRankingController {
	@Autowired
	private RankingService rankingService;
	//武书连排名
	@ResponseBody
	@RequestMapping("/list3")
	public ResultDTO list3() {
		try {
			List<Ranking> list = rankingService.selectAll(1);
			return ResultDTO.putSuccessObj("查询成功!",list);
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
	//校友会排名
	@ResponseBody
	@RequestMapping("/list4")
	public ResultDTO list4() {
		try {
			List<Ranking> list = rankingService.selectAll(2);
			return ResultDTO.putSuccessObj("查询成功!",list);
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
	@ResponseBody
	@RequestMapping("/list")
	public ResultDTO index(RankingPageDTO dto) {
		try {
			rankingService.queryPage(dto);
			return ResultDTO.putSuccessObj("查询成功!",dto);
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
	@ResponseBody
	@RequestMapping("/selectOne")
	public ResultDTO selectOne(Long id) {
		try {
//			Ranking obj = rankingService.selectOneById(id);
			return ResultDTO.putSuccessObj("查询成功!",null);
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
}