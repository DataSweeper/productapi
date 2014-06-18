/**
 * signup action class it will take care about a new users signup process
 * @author sivagnanam <Mon Jun 16 18:58:29 IST 2014>
 */
package com.shelfcare.myapp.action;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.ActionContext;

import org.apache.struts2.interceptor.SessionAware;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import com.shelfcare.myapp.model.SignupBean;

public class SignupAction extends ActionSupport implements SessionAware{
	private SignupBean signupBean;
	private String errormessage = "User Already exist";
	private Map<String, Object> sessionMap;
	
	@Override
	public String execute() {
		ValueStack stack = ActionContext.getContext().getValueStack();
	    Map<String, Object> context = new HashMap<String, Object>();
	    context.put("tot", "db" + getdbno());
	    context.put("errormsg", errormessage);
	    stack.push(context);
	    if (checkuserexist()) {
	    	return "error";
	    } else {
	    createuserdb();
	    sessionMap.put("email", signupBean.getEmail());
		return SUCCESS;
	    }
	}
	
	public SignupBean getSignupBean() {
		return signupBean;
	}
	public void setSignupBean(SignupBean signupBean) {
		this.signupBean = signupBean;
	}
	
	private void createuserdb() {
		String dbname = "db" + getdbno();
		String UUid = UUID.randomUUID().toString();
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBInfo","psql", "siva@123"); 
			Statement stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE " + dbname);
			stmt.execute("UPDATE NOOFDB SET total = " + (getdbno() + 1));
			String sql = "INSERT INTO USER_DB_MAP(userid, email, database) VALUES(?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, UUid);
			pst.setString(2, signupBean.getEmail());
			pst.setString(3, dbname);
			pst.executeUpdate();
			stmt.close();
			con.close();
			createUsertables(dbname, UUid);
			//rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private int getdbno() {
		int totdb = 0;
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBInfo","psql", "siva@123"); 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM NOOFDB");
			while (rs.next()) {
				totdb = rs.getInt("total");
			}
			rs.close();
			stmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return totdb;
	}
	
	private void createUsertables(String dbname, String UUid) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname,"psql", "siva@123"); 
			Statement stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE ProfileInfo(UserID varchar(100), UserName varchar(50), Email varchar(50), Password varchar(50))");
			stmt.executeUpdate("CREATE TABLE ShelfInfo(ASIN varchar(100) DEFAULT NULL)");
			String sql = "INSERT INTO ProfileInfo(UserID, UserName, Email, Password) VALUES(?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, UUid);
			pst.setString(2, signupBean.getName());
			pst.setString(3, signupBean.getEmail());
			pst.setString(4, signupBean.getPassword());
			pst.executeUpdate();
			stmt.close();
			con.close();
			//rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private boolean checkuserexist() {
			boolean status = false;
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBInfo","psql", "siva@123"); 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER_DB_MAP WHERE email = '" + signupBean.getEmail() + "';");
			status = rs.next();
			rs.close();
			stmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public void validate() {
		if (signupBean.getEmail().length() == 0) {
			addFieldError("signupBean.email", "Email required");
		}
		if (signupBean.getName().length() == 0) {
			addFieldError("signupBean.name", "Name required");
		}
		if (signupBean.getPassword().length() <= 6) {
			addFieldError("signupBean.password", "Password length must be more than 6");
		}	
	}
	
	@Override
	public void setSession(Map<String, Object> sessionObj) {
		this.sessionMap = sessionObj;
	}

}
