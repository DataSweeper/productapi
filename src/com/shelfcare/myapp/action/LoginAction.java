package com.shelfcare.myapp.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.shelfcare.myapp.model.LoginBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import org.apache.struts2.interceptor.SessionAware;

public class LoginAction extends ActionSupport implements SessionAware{
	private LoginBean loginBean;
	private String errormessage = null; 
	private Map<String, Object> sessionMap;
	
	@Override
	public String execute() {
		ValueStack stack = ActionContext.getContext().getValueStack();
	    Map<String, Object> context = new HashMap<String, Object>();
	    if (validateuser().equals("OK")) {
	    	sessionMap.put("email", loginBean.getEmail());
	    	return SUCCESS;
	    } else {
		    context.put("errormsg", errormessage);
		    stack.push(context);
	    	return "error";
	    } 
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	private String validateuser() {
		if (checkuserexist()) {
			if (loginBean.getPassword().equals( getpassword( getdb() ) ) ) {
				return "OK";
			} else {
				errormessage = "Password not match";
				return "password";
			}
		}else {
			errormessage = "Username not exist, Signup please ";
			return "nouser";
		}
	}
	
	private boolean checkuserexist() {
		boolean status = false;
	try {
		Class.forName("org.postgresql.Driver");
		Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBInfo","psql", "siva@123"); 
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM USER_DB_MAP WHERE email = '" + loginBean.getEmail() + "';");
		status = rs.next();
		rs.close();
		stmt.close();
		con.close();
	}catch (Exception e) {
		e.printStackTrace();
		}
	return status;
	}
	
	public String getdb() {
		String dbname = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBInfo","psql", "siva@123"); 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER_DB_MAP WHERE email = '" + loginBean.getEmail() + "';");
			while(rs.next()) {
			dbname = rs.getString("database");
			}
			rs.close();
			stmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dbname;
	}
	
	private String getpassword(String dbname) {
		String passwd = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname,"psql", "siva@123"); 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ProfileInfo");
			while(rs.next()) {
			passwd = rs.getString("password");
			}
			rs.close();
			stmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return passwd;
	}
	
/*	@Override
	public void validate() {
		if (loginBean.getEmail().length() == 0) {
			addFieldError("loginBean.email", "Email required");
		}
		if (loginBean.getPassword().length() == 0) {
			addFieldError("loginBean.password", "Password required");
		}
	} */
	
	@Override
	public void setSession(Map<String, Object> sessionObj) {
		this.sessionMap = sessionObj;
	}
	
	public String logout() {
		if (sessionMap.containsKey("email")) {
			sessionMap.remove("email");
		}
		return SUCCESS;
	}
}
