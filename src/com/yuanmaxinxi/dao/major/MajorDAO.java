package com.yuanmaxinxi.dao.major;

import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.major.Major2;
public interface MajorDAO extends BaseDAO<Major>{
	/**
	 * 爬虫爬数据的时候  需要先验证是否存在
	 * @param obj
	 * @return
	 */
	public int save(Major2 obj);
	public List<Major2> selectFirstMajor(int type);
	/**
	 * 根据父级专业代码获取儿子们
	 * @param no
	 * @return
	 */
	public List<Major2> selectChildrenByPNo(String no);
	public List<Major2> selectJianjieById(long id);
	/**
	 * 得到专业  详细信息页面的 url
	 * @return
	 */
	public List<Major2> selectAllMajor2();
	public void updateInfo(Major2 mj);


}