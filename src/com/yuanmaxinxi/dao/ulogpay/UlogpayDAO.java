package com.yuanmaxinxi.dao.ulogpay;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
@Mapper
@Repository
public interface UlogpayDAO{
	int insert(Ulogpay ulogpay);
	int update(Ulogpay ulogpay);
	int delete(Long id);
	Ulogpay selectOneById(Long id);
	List<Ulogpay> selectAll();

	int updateOutNumber(Ulogpay ulogpay2);

	Ulogpay selectOneByOutNumber(String outNumber);
}