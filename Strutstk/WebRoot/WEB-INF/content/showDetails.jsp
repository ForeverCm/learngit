<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>showDetailPage</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<style type="text/css">
		.btn{
		display:block;
		padding: 4px 10px 4px;
		float:right;
		font-size:18px;
	    -webkit-border-radius: 7px;
	    -moz-border-radius: 7px;
	    border-radius: 7px;}
		body{font-family: "Microsoft_black";}
		tr{font-size:25px;height:60px;}
		input{font-size:0.7em;}
		form{text-align: center;}
		td{text-align: right;}
		th{background-color: #ddd;}
		.mtable1{float:left;width:50%;}
		.mtable2{width:90%;
				 float:right;
				 padding-right:5%;
				 }
		.mtable3{float:right;
				 height:450px;
				 width:90%;
				 overflow:scroll;}
		.mtable3 td,.mtable2 td{text-align:center;width:33%;}
		.mtable3 tr{border-bottom: 1px;border-bottom-color: black;}
		.rightbox{float:right;
				  width:40%;
				  height:500px;
				  border-left: 1px solid #999;
				  }
	</style>
	<script type="text/javascript">
		function deleteF(){
			var flag = confirm("确定删除该航班？");
			if(flag){
				var v = document.getElementById("detaileform");
				v.setAttribute("action", "DeleteflightServlet");
				v.submit();
				}
			else{return;}
		}
	</script>
  </head> 
  
  <body>
  	
    <h1 style="text-align: center;padding-top:30;font-size:40px;">航班详细信息</h1>
  	<div class="mtable1">
  		<form id="detaileform" action="UpdateflightServlet" method="post">
  			<table align="center">
	  				<tr>
			  			<td><label for="fid">航班号:</label></td>
				  		<td><input name="fid" readonly="readonly" type="text" id="fid" value="${details.fid}"></td>
			  		</tr>
			  		
			  		<tr>
			  			<td><label for="fstart">起飞地:</label></td>
			  			<td><input name="fstart" type="text" id="fstart" value="${details.fstart}"></td>
			  		</tr>
					<tr>
			  			<td><label for="fend">目的地:</label></td>
			  			<td><input name="fend" type="text" id="fend" value="${details.fend}"></td>
			  		</tr>
			  		<tr>
			  			<td><label for="fsum">总量:</label></td>
			  			<td><input name="fsum" type="text" id="fsum" value="${details.fsum}"></td>
					</tr>
			  		<tr>
			  			<td><label for="fnumber">余量:</label></td>
			  			<td><input name="fnumber" type="text" id="fnumber" value="${details.fnumber}"></td>
					</tr>
			  		<tr>
			  			<td><label for="price">价格:</label></td>
			  			<td><input name="price" type="text" id="price" value="${details.price}"></td>
					</tr>
					<tr>
			  			<td><label for="ftime">起飞时间:</label></td>
			  			<td><input name="ftime" type="text" id="ftime" value="${details.ftime}"></td>
			  		</tr>
	  		</table>
	  		<button class="btn" style="margin:30px 20px 0px 0px;" onclick="history.go(-1)">返回</button>
	  		<button class="btn" style="margin:30px 10px;" onclick="deleteF()">删除</button>
	  		<input class="btn" style="margin:30px 0px;" type="submit" value="保存">
  		</form>
  	</div>
  	<div class="rightbox">
  		<table class="mtable2">
  			<tr>
  				<th>账号</th>
  				<th>姓名</th>
  				<th>电话</th>
  			</tr>
  		</table>
  		<div class="mtable3">
	  		<table style="width:95%;">
	  			<c:forEach items="${details.passagers}" var="passager">
					<tr>
						<td>${passager.pid}</td>
						<td>${passager.pname}</td>
						<td>${passager.phone}</td>
					</tr>	  			
	  			</c:forEach>
	  		</table>
  		</div>
  	</div>
  </body>
</html>
