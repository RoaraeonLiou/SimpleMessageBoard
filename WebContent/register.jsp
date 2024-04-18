<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<title>留言板注册</title>
	<link rel="stylesheet" href="static/css/register.css">
</head>

<body>
	<div class="content">
		<div class="content-left">
			<div class="form-wrapper">
				<form action="" method="post" id="register-form">
					<div class="logo">
						<img src="static/img/logo.svg" alt="Logo">
						<h1 class="text-title" style="display:inline" >Sign up</h1>
					</div>
					
					<div class="field-group">
						<label class="label" for="txt-email">邮箱</label>
						<input class="input" type="email" id="txt-email" name="email" required/>
					</div>
					
					<div class="field-group">
						<div class="label-wrapper">
							<label class="label" for="txt-password">密码</label>
						</div>
						<input class="input" type="password" id="txt-password" name="password" maxlength="18" required/>
					</div>
					
					<div class="field-group">
						<div class="label-wrapper">
							<label class="label" for="txt-password">确认密码</label>
						</div>
						<input class="input" type="password" id="txt-password-2" name="password_check" maxlength="18" required/>
					</div>
					
					<div class="field-group">
						<input class="btn-submit" type="button" value="注册" onclick="checkUser();" />
					</div>
				</form>
				<input type="hidden" id="message" value="${message}" />
				<% request.removeAttribute("message"); %>
			</div>
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
			var password2 = document.getElementById("txt-password-2").value;
			if(!result){
				alert("邮箱不能为空");
				return false;
			}
			if(!password){
				alert("密码不能为空");
				return false;
			}
			if(!password2){
				alert("请确认密码");
				return false;
			}
			if(password!=password2){
				alert("两次输入密码不一致");
				return false;
			}
			document.getElementById("register-form").submit(); 
		}
	</script>
</body>

</html>
