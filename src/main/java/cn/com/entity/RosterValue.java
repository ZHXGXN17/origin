package cn.com.entity;

import java.io.Serializable;

public class RosterValue implements Serializable{
	
	private Integer rostervalueId;
	
	private Integer rosterId;
	
	private String rosterValue;

    private String remark;

    private Long enableTime;

    private Long disableTime;

    private Long createTime;

    private Long modifyTime;

	public RosterValue() {
		super();
	}

	public RosterValue(Integer rostervalueId, Integer rosterId, String rosterValue, String remark, Long enableTime,
			Long disableTime, Long createTime, Long modifyTime) {
		super();
		this.rostervalueId = rostervalueId;
		this.rosterId = rosterId;
		this.rosterValue = rosterValue;
		this.remark = remark;
		this.enableTime = enableTime;
		this.disableTime = disableTime;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	public Integer getRostervalueId() {
		return rostervalueId;
	}

	public void setRostervalueId(Integer rostervalueId) {
		this.rostervalueId = rostervalueId;
	}

	public Integer getRosterId() {
		return rosterId;
	}

	public void setRosterId(Integer rosterId) {
		this.rosterId = rosterId;
	}

	public String getRosterValue() {
		return rosterValue;
	}

	public void setRosterValue(String rosterValue) {
		this.rosterValue = rosterValue;
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

	public Long getDisableTime() {
		return disableTime;
	}

	public void setDisableTime(Long disableTime) {
		this.disableTime = disableTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "RosterValue [rostervalueId=" + rostervalueId + ", rosterId=" + rosterId + ", rosterValue=" + rosterValue
				+ ", remark=" + remark + ", enableTime=" + enableTime + ", disableTime=" + disableTime + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + "]";
	}
    
}
