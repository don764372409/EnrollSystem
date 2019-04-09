package com.yuanmaxinxi.entity.enroll;

import java.math.BigDecimal;

import com.yuanmaxinxi.entity.batch.Batch;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;

public class Enroll {
	private Long id;
	private Long uId;
	private University university;
	private Long pId;
	private Province province;
	private Long mId;
	private Major major;
	private Long bId;
	private Batch batch;
	private int number;
	private int maxNumber;
	private int minNumber;
	private int avgNumber;
	private int maxRanking;
	private int minRanking;
	private int avgRanking;
	private int time;
	private BigDecimal tuition;
	private BigDecimal studyYear;
	private int uniNumber;//同一个学校的出现次数
	private int uniAndMajorNumber;//同一个学校 同一个专业的出现次数
	
	public int getUniNumber() {
		return uniNumber;
	}
	public void setUniNumber(int uniNumber) {
		this.uniNumber = uniNumber;
	}
	public int getUniAndMajorNumber() {
		return uniAndMajorNumber;
	}
	public void setUniAndMajorNumber(int uniAndMajorNumber) {
		this.uniAndMajorNumber = uniAndMajorNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public Long getmId() {
		return mId;
	}
	public void setmId(Long mId) {
		this.mId = mId;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public Long getbId() {
		return bId;
	}
	public void setbId(Long bId) {
		this.bId = bId;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	public int getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}
	public int getAvgNumber() {
		return avgNumber;
	}
	public void setAvgNumber(int avgNumber) {
		this.avgNumber = avgNumber;
	}
	public int getMaxRanking() {
		return maxRanking;
	}
	public void setMaxRanking(int maxRanking) {
		this.maxRanking = maxRanking;
	}
	public int getMinRanking() {
		return minRanking;
	}
	public void setMinRanking(int minRanking) {
		this.minRanking = minRanking;
	}
	public int getAvgRanking() {
		return avgRanking;
	}
	public void setAvgRanking(int avgRanking) {
		this.avgRanking = avgRanking;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public BigDecimal getTuition() {
		return tuition;
	}
	public void setTuition(BigDecimal tuition) {
		this.tuition = tuition;
	}
	public BigDecimal getStudyYear() {
		return studyYear;
	}
	public void setStudyYear(BigDecimal studyYear) {
		this.studyYear = studyYear;
	}
}
