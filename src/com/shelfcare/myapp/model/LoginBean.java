/**
 * login bean to host login credentials
 * @author siva-2356
 */
package com.shelfcare.myapp.model;

public class LoginBean {
	
	private String email;
	private String password;
	
	public String getEmail(){
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
	
}
