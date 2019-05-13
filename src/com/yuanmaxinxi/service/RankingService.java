package com.yuanmaxinxi.service;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.ranking.RankingDAO;
import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.dto.RankingPageDTO;
import com.yuanmaxinxi.entity.ranking.Ranking;
import com.yuanmaxinxi.entity.university.University;

@Service
public class RankingService{
	@Autowired
	private RankingDAO rankingDAO;
	@Autowired
	private UniversityDao universityDAO;

	@Transactional
	public void insert(Ranking ranking){

		try {
			int i = rankingDAO.insert(ranking);
			if(i!=1){
				throw new RuntimeException("添加失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("添加失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("添加失败.");
		}
	}
	@Transactional
	public void update(Ranking ranking){

		try {
			int i = rankingDAO.update(ranking);
			if(i!=1){
				throw new RuntimeException("修改失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("修改失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("修改失败.");
		}
	}
	@Transactional
	public void delete(Long id){
		try {
			int i = rankingDAO.delete(id);
			if(i!=1){
				throw new RuntimeException("删除失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("删除失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("删除失败.");
		}
	}
	public Ranking selectOneById(Long id){
		try {
			Ranking ranking= rankingDAO.selectOneById(id);
			University uIdUniversity = universityDAO.selectOneById(ranking.getuId());
			ranking.setuIdUniversity(uIdUniversity);

			return ranking;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}
	public List<Ranking> selectAll(){
		try {
			List<Ranking> list = rankingDAO.selectAll();
			for(Ranking ranking:list){
				University universityObj = universityDAO.selectOneById(ranking.getuId());
				ranking.setuIdUniversity(universityObj);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}
	public int selectCount(RankingPageDTO dto){
		try {
			return rankingDAO.selectCount(dto);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}
	public void queryPage(RankingPageDTO dto){
		try {
			int count = selectCount(dto);
			dto.setCount(count);
			List<Ranking> list = rankingDAO.queryPage(dto);
			for(Ranking ranking:list){
				University universityObj = universityDAO.selectOneById(ranking.getuId());
				ranking.setuIdUniversity(universityObj);
			}

			dto.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}
	public List<Ranking> selectAllRankingsByUniversityId(Long id){
		try {
			return rankingDAO.selectAllRankingsByUniversityId(id);
		} catch (Exception e) {
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}

}