<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<html>
<head>
<style>
#header {
	color: olive;
}

#ctr {
	margin-top: 20px;
	background-color: windowframe;
	width: 90%;
	padding: 40px;
	border-radius: 10px;
}

table th {
	text-align: center;
}

#td1 {
	text-align: center;
	vertical-align: middle;
}
</style>
<title>Show All Employee</title>
</head>
<body>
	<center>
		<div id="header">
			<h1>SHOW ALL EMPLOYEES</h1>
		</div>
		<div id="ctr">
			<table class="table table-striped">
				<tr id="header">
					<th>ID</th>
					<th>NAME</th>
					<th>GENDER</th>
					<th>ADDRESS</th>
					<th>EMAIL</th>
					<th>PHONE</th>
					<th>PASSWORD</th>
					<th>PHOTO</th>
					<th>QR CODE</th>
					<c:if test="${eMsg == 'edit'}">
						<th>EDIT</th>
					</c:if>
					<c:if test="${eMsg == null}">
						<th>SHOW IN DETAILS</th>
					</c:if>
				</tr>
				<c:forEach var="e" items="${emp}">
					<tr>
						<td id="td1">${e.id}</td>
						<td id="td1">${e.name}</td>
						<td id="td1">${e.gender}</td>
						<td id="td1">${e.address}</td>
						<td id="td1">${e.email}</td>
						<td id="td1">${e.phone}</td>
						<td id="td1">${e.password}</td>
						<td id="td1"><img src="${e.imageStr}" height="100"
							width="100"></td>
						<td id="td1"><img src="${e.qrcode}" height="100" width="100"></td>
						<c:if test="${eMsg == 'edit'}">
							<td id="td1"><a href="show-in-detail.htm?id=${e.id}"><button
										class="btn btn-primary btn-xs">Show in detail</button></a><br>
								<br> <a href="update.htm?id=${e.id}"><button
										class="btn btn-warning btn-xs">UPDATE</button></a><br> <br>
								<a href="delete.htm?id=${e.id}"
								onclick="return confirm('Are you sure to delete ID : ${e.id} Employee')"><button
										class="btn btn-danger btn-xs">DELETE</button></a></td>
						</c:if>
						<c:if test="${eMsg == null}">
							<td id="td1"><a href="show-in-detail.htm?id=${e.id}"><button
										class="btn btn-primary btn-xs">Click here</button></a></td>
						</c:if>
					<tr>
				</c:forEach>
			</table>
		</div>
		<a href="home.htm"><span class="glyphicon glyphicon-home"></span></a>
	</center>
</body>
</html>