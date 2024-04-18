<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>发布留言</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/holder.min.js"></script>
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
			<form role="form" action="" method="post">
				<div class="form-group">
					<h3><span  class="label label-default" style="background:#007c89;">留言主题</span></h3>
				    <input type="text" class="form-control" name="title">
				</div>
				
				<div class="form-group">
				    <h3><span  class="label label-default" style="background:#007c89;">留言内容</span></h3>
					<textarea class="form-control" rows="10" name="content" style="resize:none;"></textarea>
				</div>
				
			  	<input type="submit" class="btn btn-default pull-right" value="发表" style="background:#007c89;color:#FFFFFF;">
			</form>
		</div>
	</div>
</body>
</html>