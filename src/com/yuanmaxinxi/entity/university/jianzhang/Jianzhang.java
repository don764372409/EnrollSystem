/**
 *@晓风猪猪 
 *@param:
 *@data:2019年1月8日
 */
package com.yuanmaxinxi.entity.university.jianzhang;

public class Jianzhang {
	private String name ; //学校名字
	private String zhangshengName; //招生简章名称，如：18年，19年
	private String text;//招生简章文档
	public String getZhangshengName() {
		return zhangshengName;
	}
	public void setZhangshengName(String zhangshengName) {
		this.zhangshengName = zhangshengName;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Jianzhang [name=" + name + ", zhangshengName=" + zhangshengName + ", text=" + text + "]";
	}
}
