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
		
		$("#back").click(function(){
			console.log(location.href);
			location.href = "./login.jsp";
		});
		
			$("#register").click(function(){
				if( $("#mima").val() == $("#mima2").val() ){
					var name = $("#name").val();
					var mima =  $("#mima").val();
					$.ajax({
						url : "RegisterAction",
						data : {
							Name : name,
							Mima : mima
						},
						type : "post",
						success : function(res){
							res = JSON.parse(res)
							if(res.sign == "one"){
								alert("注册成功");
								location.href = res.url;
							}
							else{
								alert("重新注册");
								$("#name").val("");
								$("#mima").val("");
								$("#mima2").val("");
							}
						}
					})
				}
				else{
					alert("两次密码不同")
					$("#mima").val("");
					$("#mima2").val("");
				}
			});
  });
</script>


</head>
<body>
<h1>用户注册界面</h1>
<form name="form" >

	<div class="mrow">
		&nbsp用户名：<input id = "name" type="text" name="pid" ><span style="color:red;">${tip }</span><br>
	</div>
	
	<div class="mrow">
		&nbsp&nbsp密码：<input id = "mima" type="password" name="psw" ><br>
	</div>
	
	<div class="mrow">
		确认密码：<input id = "mima2" type="password" name="QueRenPsw" ><br>
	</div>
	
	<div class="mrow">
		<input id = "register" type="button" name="QueDing" value="确定">
		<input id = "back" type="button" name="FanHui" value="返回">
	</div>
	
</form>

</body>
</html>