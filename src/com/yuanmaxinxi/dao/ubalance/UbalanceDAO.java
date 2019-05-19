package com.yuanmaxinxi.dao.ubalance;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yuanmaxinxi.entity.ubalance.Ubalance;
@Mapper
@Repository
public interface UbalanceDAO{
	int insert(Ubalance ubalance);
	int update(Ubalance ubalance);
	int delete(Long id);
	Ubalance selectOneById(Long id);
	List<Ubalance> selectAll();
	Ubalance selectOneByuId(Long uId);
	Ubalance selectOneByOpenId(String openid);
}