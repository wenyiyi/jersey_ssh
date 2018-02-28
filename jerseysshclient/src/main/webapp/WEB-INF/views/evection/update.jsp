<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  type="text/css"  href="css/update.css"/>
<title>evectionInfo</title>
</head>
<body>
	<!--添加用户信息-->
	<div class="AddOrEdit" id="addRecord">
	<form action="http://localhost:9090/jerseysshserver/record/save" method="post" enctype="multipart/form-data">  
    <table>   
        <tr>  
            <td>employee id:</td><td><input type="text" name="e_id" value="${e_id}"></td>  
        </tr> 
        <tr>  
            <td>name:</td><td><input type="text" name="name" value="${name}" maxlength="10"></td>  
        </tr> 
        <tr>  
            <td>start:</td><td><input id="calend1" name="start" class="calend" value="选择日期"></td>  
        </tr>  
        <tr>  
            <td>end:</td><td><input id="calend2" name="end" class="calend" value="选择日期"></td>  
        </tr>  
        <tr>  
            <td>location:</td><td><input type="text" name="location" value="${location}" maxlength="15"></td>  
        </tr>  
        <tr>  
            <td>room:</td><td><input type="text" name="room" value="${room}" maxlength="5"></td>  
        </tr>  
        <tr>  
            <td>folio:</td><td><input type="text" name="folio" value="${folio}" maxlength="6"></td>  
        </tr>   
        <tr>  
            <td colspan="2"><input type="submit" value="提交"/></td>  
        </tr>  
    </table> 
    </form>  
	</div>
	<script type="text/javascript" src="js/update.js"></script>
</body>
</html>

