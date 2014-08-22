<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/shelfcare/js/jquery-1.11.1.js"></script>
<title>Insert title here</title>
<script>/*
$(document).ready(function(){
	$("#search_form").submit(function(){
		alert("work1");
		var formData = $(this).serialize();
		$.post('search', formData, processData).error('ouch');
		function processData(data) {
		$('#ajaxcontent').html(data);
		}
		return false;
	});
});*/
</script>
</head>
<body>

  <p> ${sessionScope.email} </p>

  <a href="logout">Logout</a>

</div>
</body>
</html>