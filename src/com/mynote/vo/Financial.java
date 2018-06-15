package com.mynote.vo;

public class Financial {
	private int f_id;
	private String datetime;
	private String type;
	private double money;
	private double allMoney;
	private String other;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(double allMoney) {
		this.allMoney = allMoney;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Financial() {
	}
	@Override
	public String toString() {
		return "Financial [f_id=" + f_id + ", datetime=" + datetime + ", type="
				+ type + ", money=" + money + ", allMoney=" + allMoney
				+ ", other=" + other + ", id=" + id + "]";
	}
	
}
