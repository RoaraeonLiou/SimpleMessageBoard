<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>留言板后台管理</title>
	<link rel="stylesheet" href="../static/css/bootstrap.min.css">
	<script src="../static/js/jquery.min.js"></script>
	<script src="../static/js/bootstrap.min.js"></script>
	<script src="../static/js/holder.min.js"></script>
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
		  
		  <div class="col-md-9" style="border:#007c89 1 solid;">
			
			<div class="panel panel-warning">
				<div class="panel-heading" style="background:#000;color:#FFFFFF">
					留言管理
				</div>
			    <div class="panel-body" >
			   		<table class="table table-bordered" >
					  <tr>
					  	<th>序号</th>
					  	<th>标题</th>
					  	<th>作者</th>
					  	<th>操作</th>
					  </tr>
					  <c:forEach var="message" items="${ messages }" varStatus="status">
						<tr>
					  	<td>${status.index+1}</td>
					  	<td><a href="/MessageBoard/message?id=${ message.id }">${message.title}</a></td>
					  	<td>${message.email}</td>
					  	<td>
							<form method="post" action="/MessageBoard/admin/messageManager?id=${message.id}" style="display:inline">
								<input type="hidden" id="action" name="action" value="delete">
								<input type="submit" onclick="return confirm('确定要删除吗？');" value="删除"/>
							</form>
					  	</td>
					  </tr>
					</c:forEach>
					  
					</table>
			    </div>
			    <div class="panel-footer">
					<ul class="pager">
					  	<li class="previous"><a href="/MessageBoard/admin/messageManager" style="color:#007c89;">&larr; 首页</a></li>
					  <% if((int)request.getAttribute("pre_page")<0) {%>
					  	<li><a href="/MessageBoard/admin/messageManager?page=0" style="color:#007c89;" disabled>上一页</a></li>
					  <% }else{ %>
					  	<li><a href="/MessageBoard/admin/messageManager?page=${pre_page}" style="color:#007c89;">上一页</a></li>
					  <% } %>
					  <% if((int)request.getAttribute("next_page")<0) {%>
					  	<li><a href="/MessageBoard/admin/messageManager?page=${last_page}" style="color:#007c89;" disabled>下一页</a></li>
					  <% }else{ %>
					  	<li><a href="/MessageBoard/admin/messageManager?page=${next_page}" style="color:#007c89;">下一页</a></li>
					  <% } %>
		  				<li class="next"><a href="/MessageBoard/admin/messageManager?page=${last_page}" style="color:#007c89;">末页 &rarr;</a></li>
					
					</ul>
			    </div>
			</div>
		  </div>
		</div>
	</div>
</body>
</html>