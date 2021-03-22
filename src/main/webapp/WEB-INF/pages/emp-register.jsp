<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!-- compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<html>
<head>
<title>Add Employee</title>
<style>
span {
	margin-left: 10px;
}

td {
	height: 40px;
	padding-left: 40px;
	padding-bottom: 0px;
}

input[type='text'] {
	width: 300px;
}

#ctr {
	margin-top: 20px;
	background-color: windowframe;
	width: 70%;
	padding: 40px;
	border-radius: 10px;
}

#header {
	color: olive;
}
</style>
</head>
<body>
	<div id="header">
		<center>
			<h1>
				<c:if test="${uMsg == null}">
ADD EMPLOYEE
</c:if>
				<c:if test="${uMsg != null}">
EDIT EMPLOYEE
</c:if>
			</h1>
		</center>
	</div>
	<center>
		<div id="ctr">
			<frm:form modelAttribute="emp" enctype="multipart/form-data">
				<c:if test="${uMsg == null}">
					<frm:hidden path="id" />
				</c:if>
				<table>
					<c:if test="${uMsg != null}">
						<tr>
							<td>ID</td>
							<td><frm:input path="id" readonly="true"
									class="form-control" /></td>
						</tr>
					</c:if>
					<tr>
						<td>NAME</td>
						<td><frm:input path="name" class="form-control" /> <frm:errors
								path="name" class="text-danger" />
						<td rowspan=4><center>
								<img id="output" height="140" width="150" src="${imageStr}" />
							</center></td>
					</tr>
					<tr>
						<td>GENDER</td>
						<td><frm:radiobuttons path="gender" element="span"
								items="${gender}" />
					</tr>
					<tr>
						<td>ADDRESS</td>
						<td><frm:input path="address" class="form-control" /> <frm:errors
								path="address" class="text-danger" />
					</tr>
					<tr>
						<td>EMAIL</td>
						<td><frm:input path="email" class="form-control" /> <frm:errors
								path="email" class="text-danger" />
					</tr>
					<tr>
						<td>PHONE</td>
						<td><frm:input path="phone" class="form-control" /> <frm:errors
								path="phone" class="text-danger" />
						<td><input type="file" accept="image" name="f"
							value="${photo}" onchange="loadFile(event)"
							class="btn btn-primary form-control" /></td>
					</tr>
					<tr>
						<td>PASSWORD</td>
						<td><frm:password path="password" class="form-control" /> <frm:errors
								path="password" class="text-danger" />
					</tr>
					<tr>
						<td>QUALIFICATION</td>
						<td><frm:select path="qualification" class="form-control">
								<frm:options items="${qualification}" />
							</frm:select></td>
					</tr>
					<tr>
						<td>Programming Language</td>
						<td colspan="2"><frm:checkboxes items="${pLanguage}"
								path="planguage" /></td>
					</tr>
					<tr>
						<td>No.of Project</td>
						<td><frm:input path="projCount"
								placeholder="How many project you did?" class="form-control" />
							<frm:errors path="projCount" class="text-danger" /></td>
					</tr>
					<tr>
						<td></td>
						<td><br> <input type="submit" value="Submit"
							class="btn btn-primary form-control" /></td>
					</tr>
				</table>
			</frm:form>
		</div>
		<a href="home.htm"><span class="glyphicon glyphicon-home"></span></a>
	</center>
</body>
<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>
</html>