package com.mynote.vo;

public class Study {
	private int s_id;
	private int s_uid;
	private String name;
	private String content;
	private String type;
	private String time;
	@Override
	public String toString() {
		return "Study [s_id=" + s_id + ", s_uid=" + s_uid + ", name=" + name
				+ ", content=" + content + ", type=" + type + ", time=" + time
				+ "]";
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public int getS_uid() {
		return s_uid;
	}
	public void setS_uid(int s_uid) {
		this.s_uid = s_uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Study() {

	}
	
}
