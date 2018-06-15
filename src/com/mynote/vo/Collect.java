package com.mynote.vo;

public class Collect {
	private int c_id;
	private String name;
	private String url;
	private int c_uid;
	@Override
	public String toString() {
		return "Collect [c_id=" + c_id + ", name=" + name + ", url=" + url
				+ ", c_uid=" + c_uid + "]";
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getC_uid() {
		return c_uid;
	}
	public void setC_uid(int c_uid) {
		this.c_uid = c_uid;
	}
	public Collect() {

	}
	
}
