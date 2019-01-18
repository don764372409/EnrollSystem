package com.yuanmaxinxi.dto;

import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.util.StringUtil;

public class PayRecordDTO extends BaseQueryPageDTO<PayRecord>{
	private String name;//学校名称

	public PayRecordDTO() {
		super("t_payrecod");
	}

	@Override
	protected void coverSqls() {
		if (StringUtil.isNotNullAndEmpty(name)) {
			getSqls().add("instr(name,?)");
			getParams().add(name);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
