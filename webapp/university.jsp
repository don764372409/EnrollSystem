<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>id</th>
			<th>pId</th>
			<th>地址(address)</th>
			<th>院校水平(quality)</th>
			<th>院校类型（type）</th>
			<th>简介(remake)</th>
			<th>院校排名（ranking）</th>
			<th>师资团队（theachers）</th>
			<th>院校学历（record）</th>
			<th>学科建设（subject）</th>
		</tr>
		<tr>
			<c:forEach items="${uni}" var="university">
			<td>${university.id}</td>
			<td>${university.pId}</td>
			<td>${university.adress}</td>
			<td>${university.quali}</td>
			<td>${university.id}</td>
			<td>${university.id}</td>
			<td>${university.id}</td>
			<td>${university.id}</td>
			<td>${university.id}</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>