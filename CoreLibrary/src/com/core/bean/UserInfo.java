package com.core.bean;

import java.io.Serializable;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = -8249979876707926450L;
	private String gender; // 性别
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
