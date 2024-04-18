<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<title>留言板登录</title>
	<link rel="stylesheet" href="static/css/login.css">
</head>

<body>
	<div class="content">
		<div class="content-left">
			<div class="form-wrapper">
				<form action="" method="post">
					<div class="logo">
						<img src="static/img/logo.svg" alt="Logo">
						<h1 class="text-title" style="display:inline" >Log In</h1>
					</div>
					
					<div class="field-group">
						<label class="label" for="txt-email">邮箱</label>
						<input class="input" type="email" id="txt-email" name="email" value="${cookie.email.value}" required/>
					</div>
					
					<div class="field-group">
						<div class="label-wrapper">
							<label class="label" for="txt-password">密码</label>
						</div>
						<input class="input" type="password" id="txt-password" name="password" value="${cookie.password.value}" required/>
					</div>
					
					<div class="field-group">
						<label class="checkbox-label">记住我<input type="checkbox"><span class="checkbox-checkmark" id="checkmark" onClick="change();"></span></label>
						<input type="hidden" id="remember_flag" name="remember_me" value="No"/>
					</div>
					
					<div class="field-group">
						<input class="btn-submit" type="submit" value="登录" onclick="checkUser();" />
					</div>
					<label>还没有账户？</label> <a href="register">注册</a>
				</form>
				<!--
					<div class="field-group text-center">
						<a href="#forgot-username">Forgot username?</a><span> Â· </span><a href="#forgot-password">Forgot password?</a>
					</div>
				-->
				<input type="hidden" id="message" value="${message}" />
				<% request.removeAttribute("message"); %>
			</div>
		</div>
		
		<div class="content-right">
			<div class="content-right-text">
				<h2>留言板</h2>
				<span>这是一个留言板.</span>
			</div>
			<a class="link-custom-domain">
				简易的留言板
			</a>
		</div>
	</div>

	<script>
		window.onload=function(){
			if(getcookie('remember')=='Yes'){
				document.getElementById("checkmark").click();
			}
			var message = document.getElementById("message").value;
			if(message!=""){
				alert(message);
			}
		}
		
		function getcookie(keys){
			var arr=document.cookie.split(";");
				for(var i=0; i<arr.length; i++){
					var ass=arr[i].split("=");
					if(ass[0].trim()==keys){
						return ass[1];
				}
			}
			return false;
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
		
		function change(){
			var flag = document.getElementById("remember_flag").value;
			if(flag=='Yes'){
				document.getElementById("remember_flag").value='No';
			}else{
				document.getElementById("remember_flag").value='Yes';
			}
		}
	</script>
</body>

</html>
