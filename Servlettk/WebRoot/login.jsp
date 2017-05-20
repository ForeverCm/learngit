<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<Style>
body{
margin:0 0 0 0;
width:1366px;
clear:both;
background-image:url(http://localhost:8080/tk/images/bg0.png);
height:600px;
}

form{
position:relative;
top:150px;
left:500px;
}
.mrow{
margin:10px;
}

h1{
margin:50 auto;
font-size:3em;
color:white;
position:relative;
top:50px;
left:33%;
}
</Style>

<script type="text/javascript" src="./jquery-2.1.4.js"></script>

<script type="text/javascript">
	$(document).ready(function(e) {
    	
			$("#zhuce").click(function(){
				console.log(location.href);
				location.href = "./register.jsp";
			});
  });
</script>


</head>
<body>
<h1>机票预订信息系统</h1>
<form name="form" method="post" action="LoginServlet" >

	<div class="mrow">
		用户名：<input type="text" name="pid" ><span style="color:red;">${tip }</span><br>
	</div>
	
	<div class="mrow">
		密&nbsp;码：<input type="password" name="psw" ><br>
	</div>
	
	<div class="mrow">
		身&nbsp;份：<select name = "iden"> 
					<option>user</option>
					<option>manager</option>
					</select>
	</div>
	<div class="mrow">
		<input type="submit" name="Submit" value="登录">
		<input type="reset" name="Reset" value="重置">
		<input id = "zhuce" type="button" name="Zhu" value="注册">
	</div>
	
</form>

</body>
</html>