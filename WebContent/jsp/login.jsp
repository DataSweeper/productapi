<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String host =  request.getScheme() + "://" +  request.getServerName()  + ":"  + request.getServerPort();
 %>
 <img src= <%= host + "/shelfcare/images/sign-in-with-google.png" %> height="50" width="205"/><br>
 <img src= <%= host + "/shelfcare/images/facebook-login-button.png"%> width="200" />
 <s:form action="loginaction">
 <s:label value="Email"></s:label>
 <s:textfield name="loginBean.email"></s:textfield>
 <s:label value="Password"></s:label>
<s:textfield name="loginBean.password"></s:textfield>
<s:submit value="login"></s:submit>
</s:form>
</body>
</html>