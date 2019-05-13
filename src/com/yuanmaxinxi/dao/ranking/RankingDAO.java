package com.yuanmaxinxi.dao.ranking;
import java.util.List;

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
	Ranking selectOneById(Long id);
	List<Ranking> selectAll();
	int selectCount(RankingPageDTO dto);
	List<Ranking> queryPage(RankingPageDTO dto);
	List<Ranking> selectAllRankingsByUniversityId(Long id);
}