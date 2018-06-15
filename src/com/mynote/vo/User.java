package com.mynote.vo;

public class User {
	private String email;
	private String password;
	private String phone;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int ID;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public User(String email, String password, String phone) {
		this.email = email;
		this.password = password;
		this.phone = phone;
	}
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public User() {

	}
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", phone="
				+ phone + ", name=" + name + ", ID=" + ID + "]";
	}
	
}
