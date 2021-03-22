<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<style>
    body{
    	background-color: threedhighlight;
    }
    #link{
    	background-color: maroon;
    	padding-left: 60%;
    }
    #hd1:link, #hd1:visited{
    	font-weight: bold;
    	font-family: monospace;
    	background-color: red;
    	color: white;
    	padding: 14px 25px;
    	text-align: center;
    	text-decoration: none;
    	display: inline-block;
    }
    #hd1:hover, #hd1:active{
    	background-color: red;
    }
    #d3{
    	padding-top: 20%;
    	float: left;
    	height: 300px;
    	width: 20%;
    	background-color: maroon;
    }
    #d1{
    	margin-left: 41%;
    	margin-top: 10%;
    	color: olive;
    }
     #d2{
    	margin-left: 51%;
    	color: purple;
    }
    #hd2{
    	font-weight: bold;
    	font-family: monospace;
    	color: white;
    	padding: 14px 25px;
    	text-align: center;
    	text-decoration: none;
    	display: inline-block;
    }
</style>
<title>Home</title>
</head>
<body>
<h1><center>eManager</center></h1>
<div id="link">

<a href="insert.htm" id="hd1">ADD EMPLOYEE</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="edit.htm" id="hd1">EDIT EMPLOYEE</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="show.htm" id="hd1">SHOW ALL EMPLOYEES</a>

</div>
<div id="d3">
<a href="insert.htm" id="hd2">ADD EMPLOYEE</a><br>
<a href="edit.htm" id="hd2">EDIT EMPLOYEE</a><br>
<a href="show.htm" id="hd2">SHOW ALL EMPLOYEES</a>
</div>
<div id="bd">
	<div id="d1"><h1>Welcome to</h1></div>
	<br>
	<div id="d2"><h1><i>eManager</i></h1></div>
</div>
</body>
</html>