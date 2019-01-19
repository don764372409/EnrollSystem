package com.yuanmaxinxi.entity.major;

import java.util.ArrayList;
import java.util.List;

public class Major2 {
	private Long id;
	private String name;//专业名称
	private String no;//专业代码
	private String jianjie;//简介
	private String mubiao;//培养目标
	private String yaoqiu;//培养要求
	private String minren;//名人学者
	private String kecheng;//主干课程
	private String xueke;//学科要求
	private String zhishi;//知识能力
	private String pNo;//父级专业编号
	private String url;//详情页 url
	private String content;//职业相关
	private int type;//0-本科专业 1-专科专业
	private int layer;//1- 1级专业  2-2级专业 3-3级专业
	private List<Major2> children = new ArrayList<>();
	
	public List<Major2> getChildren() {
		return children;
	}
	public void setChildren(List<Major2> children) {
		this.children = children;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
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
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getJianjie() {
		return jianjie;
	}
	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}
	public String getMubiao() {
		return mubiao;
	}
	public void setMubiao(String mubiao) {
		this.mubiao = mubiao;
	}
	public String getYaoqiu() {
		return yaoqiu;
	}
	public void setYaoqiu(String yaoqiu) {
		this.yaoqiu = yaoqiu;
	}
	public String getMinren() {
		return minren;
	}
	public void setMinren(String minren) {
		this.minren = minren;
	}
	public String getKecheng() {
		return kecheng;
	}
	public void setKecheng(String kecheng) {
		this.kecheng = kecheng;
	}
	public String getXueke() {
		return xueke;
	}
	public void setXueke(String xueke) {
		this.xueke = xueke;
	}
	public String getZhishi() {
		return zhishi;
	}
	public void setZhishi(String zhishi) {
		this.zhishi = zhishi;
	}
	public String getpNo() {
		return pNo;
	}
	public void setpNo(String pNo) {
		this.pNo = pNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Major2 [id=" + id + ", name=" + name + ", no=" + no + ", jianjie=" + jianjie + ", mubiao=" + mubiao
				+ ", yaoqiu=" + yaoqiu + ", minren=" + minren + ", kecheng=" + kecheng + ", xueke=" + xueke
				+ ", zhishi=" + zhishi + ", pNo=" + pNo + ", url=" + url + ", content=" + content + "]";
	}
}
