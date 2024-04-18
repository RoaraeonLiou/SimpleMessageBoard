<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>管理</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/holder.min.js"></script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default navbar-fixed-top container-fluid" style="background:#000;">
			<div class="navbar-header">
		    	<a href="/MessageBoard/admin" class="navbar-brand" style="width:250px;color:#FFFFFF;"><b>留言板管理</b></a>
		  	</div>
		  	<div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
			    	<li><a href="/MessageBoard/admin/logout" style="color:#FFFFFF;" ><b>退出</b></a></li>
			    </ul>
			</div>
		</nav>
		
		<div style="height:80px;"></div>
		
		<div class="row">
		  <div class="col-md-3">
		  	<div class="list-group">
			  <a href="#" class="list-group-item active" style="background:#000;">
			    管理列表
			  </a>
			  <a href="/MessageBoard/admin/messageManager" class="list-group-item">留言管理</a>
			  <a href="/MessageBoard/admin/userManager" class="list-group-item">用户管理</a>
			</div>
		  </div>
		  
		  <div class="col-md-9">
				<div class="jumbotron">
				  	<h1>Hello &nbsp;${admin.name}!</h1>
				  	<p>欢迎来到留言板管理界面...</p>
				</div>
			</div>
		  </div>
		</div>
	</div>
</body>
</html>