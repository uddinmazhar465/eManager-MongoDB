<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
	width: 70%;
	padding: 40px;
	border-radius: 10px;
}
</style>
<title>Save Page</title>
</head>
<body>
	<center>
		<div id="header">
			<h1>Congratulations</h1>
		</div>
		<div id="ctr">
			<img src="${empShow.qrcode}" height="400" width="400"><br>
			<br> <i><b>${msg}</b></i>
		</div>
		<br> <a href="show-in-detail.htm?id=${empShow.id}"><button
				class="btn btn-primary btn-xs">Show in detail</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="update.htm?id=${empShow.id}"><button
				class="btn btn-warning btn-xs">UPDATE</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="delete.htm?id=${empShow.id}"
			onclick="return confirm('Are you sure to delete ID : ${empShow.id} Employee')"><button
				class="btn btn-danger btn-xs">DELETE</button></a><br>
		<br> <a href="home.htm"><span class="glyphicon glyphicon-home"></span></a>
	</center>
</body>
</html>