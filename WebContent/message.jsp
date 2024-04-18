<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title> ${message.title} </title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/holder.min.js"></script>
	<style type="text/css">
          input[readonly]{background:#fff;opacity:1;color:black;}
          input[disabled]{background:#fff;opacity:1}
  	</style>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default navbar-fixed-top container-fluid" style="background:#007c89;">
			<div class="navbar-header">
		    	<a href="/MessageBoard" class="navbar-brand" style="width:250px;color:#FFFFFF;"><b>留言板</b></a>
		  	</div>
		  	<div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
			    	<li><a href="logout" style="color:#FFFFFF;" ><b>退出</b></a></li>
			    </ul>
			</div>
			  
		  	<div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
			    	<li><a href="writeMessage" style="color:#FFFFFF;" ><b>写留言</b></a></li>
			    </ul>
			</div>
			  
		  	<div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
			    	<li><a href="home" style="color:#FFFFFF;" ><b>个人中心</b></a></li>
			    </ul>
			</div>
		</nav>
		
		<div style="height:80px;"></div>
		
		
		<div class="col-md">			
			<form role="form" action="" method="post" id="message_form">
			<c:choose>
				<c:when test="${ message.email eq sessionScope.user.email }">
					<div class="form-group">
						<h3><span  class="label label-default" style="background:#007c89;">留言主题</span></h3>
					    <input type="text" class="form-control" name="title" value="${ message.title }">
					</div>
					<div class="form-group">
						<h3><span  class="label label-default" style="background:#007c89;">留言作者</span></h3>
				    	<input type="text" class="form-control" value="${ message.email }" style="background:#FFFFFF;cursor:default" readonly/>
					</div>
					<div class="form-group">
					    <h3><span  class="label label-default" style="background:#007c89;">留言内容</span></h3>
						<textarea class="form-control" rows="10" name="content" style="resize:none;">${ message.content }</textarea>
					</div>
					<input type="hidden" id="submit_action" name="action" value="update"/>
					<input type="hidden" name="id" value=${ message.id }/>
					<input type="button" class="btn btn-default pull-right" value="修改" onclick="update_message();" style="background:#007c89;color:#FFFFFF;">
					<input type="button" class="btn btn-default pull-right" value="删除" onclick="delete_message();" style="background:#FF5555;color:#FFFFFF;">
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<h3><span  class="label label-default" style="background:#007c89;">留言主题</span></h3>
				    	<input type="text" class="form-control" value="${ message.title }" style="background:#FFFFFF;cursor:default" readonly/>
					</div>
					<div class="form-group">
						<h3><span  class="label label-default" style="background:#007c89;">留言作者</span></h3>
				    	<input type="text" class="form-control" value="${ message.email }" style="background:#FFFFFF;cursor:default" readonly/>
					</div>
					<div class="form-group">
					    <h3><span  class="label label-default" style="background:#007c89;">留言内容</span></h3>
						<textarea class="form-control" rows="10" style="background:#FFFFFF;cursor:default;resize:none" readonly>${ message.content }</textarea>
					</div>
				</c:otherwise>
			</c:choose>
			</form>
		</div>
	</div>
	<script>
		function delete_message(){
			document.getElementById("submit_action").value="delete";
			document.getElementById("message_form").submit(); 
		}
		function update_message(){
			document.getElementById("submit_action").value="update";
			document.getElementById("message_form").submit(); 
		}
	</script>
</body>
</html>