package com.v2stech.fissara.model;

import javax.validation.constraints.NotBlank;

public class UserCredentialsDTO {
	String userId;
	@NotBlank(message = "cannot be blank")
	String username;
	@NotBlank(message = "cannot be blank")
	String password;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
