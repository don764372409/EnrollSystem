package com.yuanmaxinxi.dao.ranking;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yuanmaxinxi.dto.RankingPageDTO;
import com.yuanmaxinxi.entity.ranking.Ranking;
@Mapper
@Repository
public interface RankingDAO{
	int insert(Ranking ranking);
	int update(Ranking ranking);
	int delete(Long id);
	Ranking selectOneById(Ranking r);
	int selectCount(RankingPageDTO dto);
	List<Ranking> queryPage(RankingPageDTO dto);
	List<Ranking> selectAllRankingsByUniversityId(Long id);
	int insert2(Map<String, Object> map);
	List<Ranking> selectAll(int type);
}