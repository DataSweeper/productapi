package com.shelfcare.myapp.action;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.shelfcare.util.UniqueID;
import com.shelfcare.myapp.model.LoginBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import org.apache.log4j.Logger;

import com.shelfcare.myapp.model.SearchBean;

public class Tracker extends ActionSupport {

  private String tomail;
  private double desiredprice;
  private String asin;
  private String vendor_flag;
  private static final Logger logger = Logger.getLogger(Tracker.class);
  
  public String putTrack() {
  logger.info("inside putTracker.....");
    try {
	  Class.forName("org.postgresql.Driver");
	  Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/track","psql", "siva@123"); 
	  Statement stmt = con.createStatement();
	  String sql = "INSERT INTO tracker(asin, tomail, desiredprice, fromdate, id, vendor_flag) VALUES(?, ?, ?, ?, ?, ?)";
	  PreparedStatement pst = con.prepareStatement(sql);
	  pst.setString(1, getAsin());
	  pst.setString(2, getTomail());
	  pst.setDouble(3, getDesiredprice());
	  pst.setString(4, Long.toString(new Date().getTime()));
	  pst.setString(5, UniqueID.generateUniqueString());
	  pst.setString(6, getVendor_flag());
	  pst.executeUpdate();
	  stmt.close();
	  con.close();
	  //rs.close();
	}
    catch (Exception e) {
	  e.printStackTrace();
    }
    return SUCCESS;
  }

  public String getTomail() {
	return tomail;
  }
  public void setTomail(String email) {
	this.tomail = email;
  }

  public double getDesiredprice() {
	return desiredprice;
  }
  public void setDesiredprice(String des) {
	this.desiredprice = Double.parseDouble(des);
  }
  
  public String getAsin() {
	return asin;
  }
  public void setAsin(String asin) {
	this.asin = asin;
  }
  
  public String getVendor_flag() {
	return vendor_flag;
  }
  public void setVendor_flag(String flag) {
	this.vendor_flag = flag;
  }
}

