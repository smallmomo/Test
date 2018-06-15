package com.mynote.vo;

public class Task {
	private String tName;
	private String tCreateTime;
	private String tCompleteTime;
	private String tFinish;
	private String createBy;
	private String finishBy;
	private int t_uid;
	private int t_id;
	private String tContent;
	
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String gettContent() {
		return tContent;
	}
	public void settContent(String tContent) {
		this.tContent = tContent;
	}
	public int getT_uid() {
		return t_uid;
	}
	public void setT_uid(int t_uid) {
		this.t_uid = t_uid;
	}
	public Task() {
		
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String gettCreateTime() {
		return tCreateTime;
	}
	public void settCreateTime(String tCreateTime) {
		this.tCreateTime = tCreateTime;
	}
	public String gettCompleteTime() {
		return tCompleteTime;
	}
	public void settCompleteTime(String tCompleteTime) {
		this.tCompleteTime = tCompleteTime;
	}
	public String gettFinish() {
		return tFinish;
	}
	public void settFinish(String tFinish) {
		this.tFinish = tFinish;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getFinishBy() {
		return finishBy;
	}
	public void setFinishBy(String finishBy) {
		this.finishBy = finishBy;
	}
	@Override
	public String toString() {
		return "Task [tName=" + tName + ", tCreateTime=" + tCreateTime
				+ ", tCompleteTime=" + tCompleteTime + ", tFinish=" + tFinish
				+ ", createBy=" + createBy + ", finishBy=" + finishBy
				+ ", t_uid=" + t_uid + ", t_id=" + t_id + ", tContent="
				+ tContent + "]";
	}

}
