package com.yuanmaxinxi.entity.ranking;
import java.math.BigDecimal;

import com.yuanmaxinxi.entity.university.University;
public class Ranking{
	private Long id;
	private Long uId;//学校id
	private University uIdUniversity;
	private BigDecimal number1;//武书连排名得分
	private BigDecimal number2;//录取难度分数
	private BigDecimal number3;//就业指数分数
	private BigDecimal number4;//校友会分数
	private int ranking1;//武书连排名
	private int ranking2;//录取难度排名
	private int ranking3;//就业指数排名
	private int ranking4;//校友会排名

	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return this.id;
	}
	public void setuId(Long uId){
		this.uId=uId;
	}
	public Long getuId(){
		return this.uId;
	}
	public void setuIdUniversity(University uIdUniversity){
		this.uIdUniversity=uIdUniversity;
	}
	public University getuIdUniversity(){
		return this.uIdUniversity;
	}
	public void setNumber1(BigDecimal number1){
		this.number1=number1;
	}
	public BigDecimal getNumber1(){
		return this.number1;
	}
	public void setNumber2(BigDecimal number2){
		this.number2=number2;
	}
	public BigDecimal getNumber2(){
		return this.number2;
	}
	public void setNumber3(BigDecimal number3){
		this.number3=number3;
	}
	public BigDecimal getNumber3(){
		return this.number3;
	}
	public void setNumber4(BigDecimal number4){
		this.number4=number4;
	}
	public BigDecimal getNumber4(){
		return this.number4;
	}
	public void setRanking1(int ranking1){
		this.ranking1=ranking1;
	}
	public int getRanking1(){
		return this.ranking1;
	}
	public void setRanking2(int ranking2){
		this.ranking2=ranking2;
	}
	public int getRanking2(){
		return this.ranking2;
	}
	public void setRanking3(int ranking3){
		this.ranking3=ranking3;
	}
	public int getRanking3(){
		return this.ranking3;
	}
	public void setRanking4(int ranking4){
		this.ranking4=ranking4;
	}
	public int getRanking4(){
		return this.ranking4;
	}
}