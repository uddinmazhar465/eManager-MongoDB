<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
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
<title>Project</title>
</head>
<body>
<center>
<h1 id="header">Add Projects</h1>
</center>
<center>
	<div id="ctr">
		<table>
			<frm:form modelAttribute="proj">
				<tr>
					<th></th>
					<th>SNo.</th>
					<th>Project Name</th>
					<th>Language</th>
					<th>Database</th>
					<th>Team Size</th>
				</tr>
				<c:if test="${projExist == null }">
					<c:forEach var="p" begin="1" end="${count}">
						<tr>
							<td>Project</td><td><frm:input path="projno" value="${p}" size="5"
									readonly="true" class="form-control"/></td>
							<td><frm:input path="projName" class="form-control"/></td>
							<td><frm:input path="lang" class="form-control"/></td>
							<td><frm:input path="database" class="form-control"/></td>
							<td><frm:input path="teamSize" class="form-control"/></td>
						</tr>
					</c:forEach>
				</c:if>

				<c:if test="${projExist != null }">
					<c:forEach var="p" items="${eDTOproj}">
						<tr>
							<td>Project</td><td><frm:input path="projno" value="${p.projno}" size="5"
									readonly="true" class="form-control"/></td>
							<td><frm:input path="projName" value="${p.projName}" class="form-control"/></td>
							<td><frm:input path="lang" value="${p.lang}" class="form-control"/></td>
							<td><frm:input path="database" value="${p.database}" class="form-control"/></td>
							<td><frm:input path="teamSize" value="${p.teamSize}" class="form-control"/></td>
						</tr>
					</c:forEach>
				</c:if>
				<tr>
					<td></td><td colspan="5"><br><center><input type="submit" value="Register" class="btn btn-primary"/></center>
				</tr>
			</frm:form>
		</table>
	</div>
	</center>
</body>
</html>