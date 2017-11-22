package cn.com.entity;

import java.io.Serializable;

public class Roster implements Serializable{
	
	/**
	 * 序列号ID
	 */
	private static final long serialVersionUID = 4844035653355863829L;

	/** 付款账户payAcc. */
	private String payAcc;
	
	/** 收款账户reyAcc. */
	private String reyAcc;
	
	/** 备注remark. */
	private String remark;
	
	/** 生效时间enableTime. */
	private Long enableTime;

	public Roster() {
		super();
	}

	public Roster(String payAcc, String reyAcc, String remark, Long enableTime) {
		super();
		this.payAcc = payAcc;
		this.reyAcc = reyAcc;
		this.remark = remark;
		this.enableTime = enableTime;
	}

	public String getPayAcc() {
		return payAcc;
	}

	public void setPayAcc(String payAcc) {
		this.payAcc = payAcc;
	}

	public String getReyAcc() {
		return reyAcc;
	}

	public void setReyAcc(String reyAcc) {
		this.reyAcc = reyAcc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getEnableTime() {
		return enableTime;
	}

	public void setEnableTime(Long enableTime) {
		this.enableTime = enableTime;
	}

	@Override
	public String toString() {
		return "Roster [payAcc=" + payAcc + ", reyAcc=" + reyAcc + ", remark=" + remark + ", enableTime=" + enableTime
				+ "]";
	}

	

}
