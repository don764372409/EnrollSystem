package com.yuanmaxinxi.dao.payrecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.dto.PayRecordDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.util.DBUtil;

public interface PayrecordDao extends BaseDAO<PayRecord>{

	public int insert(PayRecord obj);


	public int update(PayRecord obj);

	
	public int delete(Long id);

	
	public PayRecord selectOneById(int id);
		
	public List<PayRecord> selectAll();

//	@Override
//	public void queryPage(BaseQueryPageDTO<PayRecord> dto) {
//	}
//
//	public List<PayRecord> queryPageName(PayRecordDTO dto) {
//		List<PayRecord> list = new ArrayList<>();
//		PayRecord payrecord = new PayRecord();
//		String sql="select * from t_payment";
//		try {
//			PreparedStatement pre = conn.prepareStatement(sql);
//			ResultSet result = pre.executeQuery();
//			while(result.next()) {
//				payrecord.setId(result.getInt("id"));
//				payrecord.setTime(result.getString("time"));
//				payrecord.setMoney(result.getBigDecimal("money"));
//				payrecord.setRemark(result.getString("remark"));
//				list.add(payrecord);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();	
//		}
//		return list;
//		// TODO Auto-generated method stub
//		
//	}

}