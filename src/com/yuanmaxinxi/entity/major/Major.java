<<<<<<< HEAD
package com.yuanmaxinxi.entity.major;
/**
 * 
 * @author 专业表
 *
 */
public class Major {
	private Long id;
	private String name;
	private Long pId;//父级id所属专业
	private Long type;//参照字典表中专业所属学历分类
	private String remark;//专业简介
	private String majorExplain;//专业解读
	private int ranking;//专业排名
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getMajorExplain() {
		return majorExplain;
	}
	public void setMajorExplain(String majorExplain) {
		this.majorExplain = majorExplain;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	
	
}
=======
package com.yuanmaxinxi.entity.major;
/**
 * 
 * @author 专业表
 *
 */
public class Major {
	private Long id;
	private String name;
	private Long pId;//父级id所属专业
	private Long type;//参照字典表中专业所属学历分类
	private String remark;//专业简介
	private String explain;//专业解读
	private String ranking;//专业排名
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	
	
}
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
