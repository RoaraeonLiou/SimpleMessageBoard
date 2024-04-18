<%@ page language="java" import="tyut.myBean.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>留言板</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/holder.min.js"></script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default navbar-fixed-top container-fluid" style="background:#007c89;">
		  <div class="navbar-header">
		    <a href="#" class="navbar-brand" style="width:250px;color:#FFFFFF;"><b>留言板</b></a>
		  </div>
		  
		  <% 
		  	User user = (User) session.getAttribute("user"); 
		  	if(user!=null){
		  %>
		  	  <div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
			    <ul class="nav navbar-nav">
			      <li><a href="logout" style="color:#FFFFFF;" ><b>退出</b></a></li>
			    </ul>
			  </div>
			  
		  	  <div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
			    <ul class="nav navbar-nav">
			      <li><a href="writeMessage" style="color:#FFFFFF;" ><b>发布留言</b></a></li>
			    </ul>
			  </div>
			  
		  	  <div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
			    <ul class="nav navbar-nav">
			      <li><a href="home" style="color:#FFFFFF;" ><b>个人中心</b></a></li>
			    </ul>
			  </div>
		  <% }else{ %>
			  <div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
			    <ul class="nav navbar-nav">
			      <li><a href="register" style="color:#FFFFFF;" ><b>注册</b></a></li>
			    </ul>
			  </div>
			  
			  <div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
			    <ul class="nav navbar-nav">
			      <li><a href="login" style="color:#FFFFFF;" ><b>登录</b></a></li>
			    </ul>
			  </div>
		  <% } %>
		</nav>
		
		<div style="height:80px;"></div>
		<div class="row">
			<div class="col-md-1"></div>
		  	<div class="col-md-10">
			<c:forEach var="message" items="${ messages }">
				<div class="panel panel-primary" style="border:#007c89 1px solid;">
					<div class="panel-heading" style="background:#007c89;">
						<a href="/MessageBoard/message?id=${ message.id }" style="color:#FFFFFF"> ${ message.title } </a>
					</div>
					<div class="panel-body" style="height:70px;"> ${ message.content } </div>
					<div class="panel-footer text-right">来自 ${ message.email } </div>
				</div>
			</c:forEach>
			

			<ul class="pager" style="color:#007c89">
			  <li class="previous"><a href="/MessageBoard" style="color:#007c89;">&larr; 首页</a></li>
			  <% if((int)request.getAttribute("pre_page")<0) {%>
			  	<li><a href="/MessageBoard?page=0" style="color:#007c89;" disabled>上一页</a></li>
			  <% }else{ %>
			  	<li><a href="/MessageBoard?page=${pre_page}" style="color:#007c89;">上一页</a></li>
			  <% } %>
			  <% if((int)request.getAttribute("next_page")<0) {%>
			  	<li><a href="/MessageBoard?page=${last_page}" style="color:#007c89;" disabled>下一页</a></li>
			  <% }else{ %>
			  	<li><a href="/MessageBoard?page=${next_page}" style="color:#007c89;">下一页</a></li>
			  <% } %>
			  <li class="next"><a href="/MessageBoard?page=${last_page}" style="color:#007c89;">末页 &rarr;</a></li>
			</ul>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
</body>
</html>
