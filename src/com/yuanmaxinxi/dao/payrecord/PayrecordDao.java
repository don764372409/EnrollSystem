package com.yuanmaxinxi.dao.payrecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.util.DBUtil;

public class PayrecordDao implements BaseDAO<PayRecord>{
	Connection conn = DBUtil.getConn();
	PreparedStatement sql;
	@Override
	public int insert(PayRecord obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(PayRecord obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PayRecord selectOneById(Long id) {
		// TODO Auto-generated method stub
		PayRecord payrecord = new PayRecord();
		String sql="select from t_province where id="+id;
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				payrecord.setId(result.getInt("id"));
				payrecord.setuId(result.getInt("uId"));
				payrecord.setMoney(result.getBigDecimal("money"));
				payrecord.setRemark(result.getString("remark"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return payrecord;
	}

	@Override
	public List<PayRecord> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<PayRecord> list = new ArrayList<>();
		PayRecord payrecord = new PayRecord();
		String sql="select * from t_province";
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				payrecord.setId(result.getInt("id"));
				payrecord.setuId(result.getInt("uId"));
				payrecord.setMoney(result.getBigDecimal("money"));
				payrecord.setRemark(result.getString("remark"));
				list.add(payrecord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return list;
	}

	@Override
	public List<PayRecord> queryPage(BaseQueryPageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}