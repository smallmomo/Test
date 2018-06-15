<%@page import="com.mynote.vo.Task"%>
<%@page import="com.mynote.service.TaskService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	int t_id = Integer.valueOf(request.getParameter("t_id"));
	
	TaskService taskService = new TaskService();
	Task task= taskService.getTask(t_id);
	if(task==null||task.gettName()==null){
		out.println("<script>alert('出现错误啦!');window.location='tasks.jsp';</script>");
		return;
	}
%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="ie6 ielt7 ielt8 ielt9"><![endif]--><!--[if IE 7 ]><html lang="en" class="ie7 ielt8 ielt9"><![endif]--><!--[if IE 8 ]><html lang="en" class="ie8 ielt9"><![endif]--><!--[if IE 9 ]><html lang="en" class="ie9"> <![endif]--><!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en"><!--<![endif]--> 
	<head>
		<meta charset="utf-8">
		<title>Dashboard - Akira</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="css/site.css" rel="stylesheet">
		<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
		<script>
		function doCheck(){
			if(document.getElementById('taskname').value==''){
				document.getElementById('tasknameTip').innerHTML="请输入活动名称";
				return false;
			}else if(document.getElementById('content').value==''){
				document.getElementById('contentTip').innerHTML="请输入活动内容";
				return false;
			}
		}
		</script>
	</head>
	<body>
		<div class="container">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="#">Akira</a>
						<div class="nav-collapse">
							<ul class="nav">
								<li class="active">
									<a href="index.html">Dashboard</a>
								</li>
								<li>
									<a href="settings.htm">Account Settings</a>
								</li>
								<li>
									<a href="help.htm">Help</a>
								</li>
								<li class="dropdown">
									<a href="help.htm" class="dropdown-toggle" data-toggle="dropdown">Tours <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li>
											<a href="help.htm">Introduction Tour</a>
										</li>
										<li>
											<a href="help.htm">Project Organisation</a>
										</li>
										<li>
											<a href="help.htm">Task Assignment</a>
										</li>
										<li>
											<a href="help.htm">Access Permissions</a>
										</li>
										<li class="divider">
										</li>
										<li class="nav-header">
											Files
										</li>
										<li>
											<a href="help.htm">How to upload multiple files</a>
										</li>
										<li>
											<a href="help.htm">Using file version</a>
										</li>
									</ul>
								</li>
							</ul>
							<form class="navbar-search pull-left" action="">
								<input type="text" class="search-query span2" placeholder="Search" />
							</form>
							<ul class="nav pull-right">
								<li>
									<a href="profile.htm">@username</a>
								</li>
								<li>
									<a href="login.htm">Logout</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span3">
					<div class="well" style="padding: 8px 0;">
						<ul class="nav nav-list">
							<li class="nav-header">
								Akira
							</li>
							<li class="active">
								<a href="index.htm"><i class="icon-white icon-home"></i> Dashboard</a>
							</li>
							<li>
								<a href="projects.htm"><i class="icon-folder-open"></i> Projects</a>
							</li>
							<li>
								<a href="tasks.jsp"><i class="icon-check"></i> Tasks</a>
							</li>
							<li>
								<a href="messages.htm"><i class="icon-envelope"></i> Messages</a>
							</li>
							<li>
								<a href="files.htm"><i class="icon-file"></i> Files</a>
							</li>
							<li>
								<a href="activity.htm"><i class="icon-list-alt"></i> Activity</a>
							</li>
							<li class="nav-header">
								Your Account
							</li>
							<li>
								<a href="profile.htm"><i class="icon-user"></i> Profile</a>
							</li>
							<li>
								<a href="settings.htm"><i class="icon-cog"></i> Settings</a>
							</li>
							<li class="divider">
							</li>
							<li>
								<a href="help.htm"><i class="icon-info-sign"></i> Help</a>
							</li>
							<li class="nav-header">
								Bonus Templates
							</li>
							<li>
								<a href="gallery.htm"><i class="icon-picture"></i> Gallery</a>
							</li>
							<li>
								<a href="blank.htm"><i class="icon-stop"></i> Blank Slate</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="span9">
					<h1>
						修改内容
					</h1>
					<% 
						String result = java.net.URLEncoder.encode("修改","utf-8");
						result = java.net.URLEncoder.encode(result,"utf-8");
					%>
					<form action="Servlet/TaskServlet?t_id=<%=t_id %>&topic=<%=result%>" method="POST" onSubmit="return doCheck();" >
						<fieldset>
							<div class="control-group">
								
								<label class="control-label" for="textarea">活动名称</label>
								<div class="controls">
									<input type="text" id="taskname" name="taskname" value="<%=task.gettName() %>"/><span id="tasknameTip" style="color:red;"></span>
								</div>
								<label class="control-label" for="textarea" >活动内容</label>
								<div class="controls">
									<input type="text" id="content" name="content" value="<%=task.gettContent()%>" /><span id="contentTip" style="color:red;"></span>
								</div>
								<label class="control-label" for="textarea" >任务状态</label>
								<select name="tag">
									<option value="0">待完成</option>
									<option value="1">已完成</option>
								</select>
							</div>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">修改</button> 
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/site.js"></script>
	</body>
</html>