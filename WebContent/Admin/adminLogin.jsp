<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<title>留言板管理员登录</title>
	<link rel="stylesheet" href="../static/css/adminLogin.css">
</head>

<body>
	<div class="content">
		<div class="content-left">
			<div class="form-wrapper">
				<form action="" method="post">
					<div class="logo">
						<h1 class="text-title" style="display:inline" >Admin</h1>
					</div>
					
					<div class="field-group">
						<label class="label" for="txt-email">邮箱</label>
						<input class="input" type="email" id="txt-email" name="email" value="" required/>
					</div>
					
					<div class="field-group">
						<div class="label-wrapper">
							<label class="label" for="txt-password">密码</label>
						</div>
						<input class="input" type="password" id="txt-password" name="password" value="" required/>
					</div>
					<div class="field-group" style="height:20px"></div>
					<div class="field-group">
						<input class="btn-submit" type="submit" value="登录" onclick="checkUser();" />
					</div>
				</form>
				<input type="hidden" id="message" value="${message}" />
				<% request.removeAttribute("message"); %>
			</div>
		</div>
		
		<div class="content-right">
			<div class="content-right-text">
				<h2>留言板后台管理</h2>
				<span>这是一个后台管理登录页面.</span>
			</div>
			<a class="link-custom-domain" href="/MessageBoard">
				回到留言板
			</a>
		</div>
	</div>

	<script>
		window.onload=function(){
			var message = document.getElementById("message").value;
			if(message!=""){
				alert(message);
			}
		}
		
		function checkUser(){
			var result = document.getElementById("txt-email").value;
			var password = document.getElementById("txt-password").value;
			if(!result){
				alert("邮箱不能为空");
				return false;
			}
			if(!password){
				alert("密码不能为空");
				return false;
			}
		}
	</script>
</body>

</html>
