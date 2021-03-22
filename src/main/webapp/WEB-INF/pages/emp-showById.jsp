<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<html>
<head>
<style>
#ctr {
	margin-top: 20px;
	background-color: windowframe;
	width: 90%;
	padding: 40px;
	border-radius: 10px;
}

th {
	padding-left: 20px;
	padding-right: 20px;
}

td {
	height: 30px;
}

#ht {
	background-color: threedshadow;
}
</style>
<title>Show Employee by Id</title>
</head>
<body>
	<center>
		<div>
			<h1>SHOW EMPLOYEE IN DETAIL</h1>
		</div>
		<div id="ctr">
			<table>

				<tr>
					<th>Id</th>
					<td>${showEmpById.id}</td>
					<td rowspan="5"><img src="${showEmpById.imageStr}"
						height="150" width="150"></td>
					<td rowspan="5"><img src="${showEmpById.qrcode}" height="150"
						width="150"></td>
				</tr>
				<tr>
					<th>Name</th>
					<td>${showEmpById.name}</td>
				</tr>
				<tr>
					<th>Genger</th>
					<td>${showEmpById.gender}</td>
				</tr>
				<tr>
					<th>Address</th>
					<td>${showEmpById.address}</td>
				</tr>
				<tr>
					<th>Email</th>
					<td>${showEmpById.email}</td>
				</tr>
				<tr>
					<th>Phone No</th>
					<td>${showEmpById.phone}</td>
				</tr>
				<tr>
					<th>Password</th>
					<td>${showEmpById.password}</td>
				</tr>
				<tr>
					<th colspan="4" id="ht"><center>QUALIFICATION</center></th>
				</tr>
				<tr>
					<td colspan="4">
						<center>
							<table>
								<tr>
									<c:forEach var="quali" items="${showEmpById.qualification}">
										<td>${quali}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</c:forEach>
								</tr>
							</table>
						</center>
					</td>
				</tr>
				<tr>
					<th colspan="4" id="ht"><center>Programming Language
							you known</center></th>
				</tr>
				<tr>
					<td colspan="4">
						<center>
							<table>
								<tr>
									<c:forEach var="plang" items="${showEmpById.planguage}">
										<td>${plang}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</c:forEach>
								</tr>
							</table>
						</center>
					</td>
				</tr>
				<tr>
					<th colspan="4" id="ht"><center>Project you done</center></th>
				</tr>
				<tr>
					<td colspan="4">
						<center>
							<table>
								<tr>
									<th><center>
											<i>SNo.</i>
										</center></th>
									<th><center>
											<i>Project Name</i>
										</center></th>
									<th><center>
											<i>Language</i>
										</center></th>
									<th><center>
											<i>Database</i>
										</center></th>
									<th><center>
											<i>Team Size</i>
										</center></th>
								</tr>
								<c:forEach var="proj" items="${showEmpById.project}">
									<tr>
										<td><center>${proj.projno}&nbsp;&nbsp;</center></td>
										<td><center>${proj.projName}&nbsp;&nbsp;</center></td>
										<td><center>${proj.lang}&nbsp;&nbsp;</center></td>
										<td><center>${proj.database}&nbsp;&nbsp;</center></td>
										<td><center>${proj.teamSize}&nbsp;&nbsp;</center></td>
									</tr>
								</c:forEach>
							</table>
						</center>
					</td>
				</tr>
			</table>
		</div>
		<br> <a href="update.htm?id=${showEmpById.id}"><button
				class="btn btn-warning btn-xs">UPDATE</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="delete.htm?id=${showEmpById.id}"
			onclick="return confirm('Are you sure to delete ID : ${showEmpById.id} Employee')"><button
				class="btn btn-danger btn-xs">DELETE</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="JavaScript:doPrint()"><button
				class="btn btn-warning btn-xs">PRINT</button></a> <br> <br> <a
			href="home.htm"><span class="glyphicon glyphicon-home"></span></a>
	</center>
</body>
<script>
	function doPrint() {
		window.print();
	}
</script>
</html>