<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List" %>

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
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<title>evectionInfo</title>
</head>
<body>
	<div class="listDiv">
	 	<table class="tablelist" border="15" align="center">
	 	    <caption><b>出差记录表</b></caption>
			<thead>        
				<tr>
				    <th>record id</th>
					<th>employee id</th>
					<th>name</th>
					<th>start</th>
					<th>end</th>
					<th>location</th>
					<th>printTime</th>
					<th>room</th>
					<th>folio</th>
					<th>days</th>
					<th><font color="red">export pdf</font></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="Record">
					<tr>
					    <td>${Record.r_id}</a></td>
						<td>${Record.employee.e_id}</td>
						<td>${Record.employee.name}</td>
						<td>${Record.start}</td>
						<td>${Record.end}</td>
						<td>${Record.location}</td>
						<td>${Record.printTime}</td>
						<td>${Record.room}</td>
						<td>${Record.folio}</td>
						<td>${Record.days}</td>
						<td><a href="http://localhost:9090/jerseysshserver/file/exportPdf/${Record.r_id}/${Record.employee.e_id}">export</a> </td>
					</tr>
				</c:forEach>
 	</tbody>
		</table> 
</div>
        <ul style="list-style-type:none" align="center">
			<li><a href="employeeUpdate.do">继续添加出差记录</a></li>
		</ul>
</body>
</html>

