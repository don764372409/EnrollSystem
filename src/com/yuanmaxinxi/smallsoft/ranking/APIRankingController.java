package com.yuanmaxinxi.smallsoft.ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.RankingPageDTO;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.ranking.Ranking;
import com.yuanmaxinxi.service.RankingService;
@RequestMapping("/soft/ranking")
@Controller
public class APIRankingController {
	@Autowired
	private RankingService rankingService;
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
			Ranking obj = rankingService.selectOneById(id);
			return ResultDTO.putSuccessObj("查询成功!",obj);
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
}