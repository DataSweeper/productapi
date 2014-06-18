<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<H1>result page</H1>
<s:property value="searchBean.type" />
<s:property value="searchBean.pageno" />
<s:property value="searchBean.text" />
<s:property value="searchBean.name" />

<s:iterator value="key1" var="arr">
      <s:property value="#arr[0][0]"/>
      <img src='<s:property value="#arr[0][1]"/>'>
      <s:property value="#arr[0][2]"/> <BR />
</s:iterator>
    
<s:property value="url" />

</body>
</html>