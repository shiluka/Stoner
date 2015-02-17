package com.softzone.stoner.login;

public class User {

	private String userName, password, authority;
	

	public User() {
	}

	public User(String userName, String password, String authority) {

		this.userName = userName;
		this.password = password;
		this.authority = authority;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
