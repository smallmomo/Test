<%@page import="com.mynote.vo.User"%>
<%@page import="com.mynote.vo.Task"%>
<%@page import="com.mynote.service.TaskService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Integer u_id = (Integer) session.getAttribute("u_id");
	if (u_id == null) {
		out.println("<script>alert('请先登录 ');window.location='login.htm';</script>");
		return;
	}
	String delete = java.net.URLEncoder.encode("删除","utf-8");
				delete = java.net.URLEncoder.encode(delete,"utf-8");
%>

<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="ie6 ielt7 ielt8 ielt9"><![endif]-->
<!--[if IE 7 ]><html lang="en" class="ie7 ielt8 ielt9"><![endif]-->
<!--[if IE 8 ]><html lang="en" class="ie8 ielt9"><![endif]-->
<!--[if IE 9 ]><html lang="en" class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<title>Tasks - Akira</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">
<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script>
	function doCheck() {
		if (document.getElementById('taskname').value == '') {
			document.getElementById('tasknameTip').innerHTML = "请输入活动名称";
			return false;
		} else if (document.getElementById('content').value == '') {
			document.getElementById('contentTip').innerHTML = "请输入活动内容";
			return false;
		}
	}
	function firm() {
		if (confirm("你确定要删除该文章？")) {
			return true;
		} else {
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
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a> <a class="brand" href="#">Akira</a>
					<div class="nav-collapse">
						<ul class="nav">
							<li><a href="index.html">Dashboard</a></li>
							<li><a href="settings.htm">Account Settings</a></li>
							<li><a href="help.htm">Help</a></li>
							<li class="dropdown"><a href="help.htm"
								class="dropdown-toggle" data-toggle="dropdown">Tours <b
									class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="help.htm">Introduction Tour</a></li>
									<li><a href="help.htm">Project Organisation</a></li>
									<li><a href="help.htm">Task Assignment</a></li>
									<li><a href="help.htm">Access Permissions</a></li>
									<li class="divider"></li>
									<li class="nav-header">Files</li>
									<li><a href="help.htm">How to upload multiple files</a></li>
									<li><a href="help.htm">Using file version</a></li>
								</ul></li>
						</ul>
						<form class="navbar-search pull-left" action="">
							<input type="text" class="search-query span2"
								placeholder="Search" />
						</form>
						<ul class="nav pull-right">
							<li><a href="profile.htm">@username</a></li>
							<li><a href="login.htm">Logout</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="span3">
				<div class="well" style="padding: 8px 0;">
					<ul class="nav nav-list">
						<li class="nav-header">Akira</li>
						<li><a href="index.html"><i class="icon-home"></i>
								Dashboard</a></li>
						<li><a href="projects.htm"><i class="icon-folder-open"></i>
								Projects</a></li>
						<li class="active"><a href="tasks.jsp"><i
								class="icon-white icon-check"></i> 日程</a></li>
						<li><a href="article.jsp"><i class="icon-envelope"></i>
								文章</a></li>
						<li><a href="files.htm"><i class="icon-file"></i> 文件</a></li>
						<li><a href="activity.jsp"><i class="icon-list-alt"></i>
								财务记录</a></li>
						<li class="nav-header">Your Account</li>
						<li><a href="profile.htm"><i class="icon-user"></i>
								Profile</a></li>
						<li><a href="settings.htm"><i class="icon-cog"></i>
								Settings</a></li>
						<li class="divider"></li>
						<li><a href="help.htm"><i class="icon-info-sign"></i>
								Help</a></li>
						<li class="nav-header">Bonus Templates</li>
						<li><a href="gallery.htm"><i class="icon-picture"></i>
								Gallery</a></li>
						<li><a href="blank.htm"><i class="icon-stop"></i> Blank
								Slate</a></li>
					</ul>
				</div>
			</div>
			<div class="span9">
				<h1>Tasks &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
				<!-- start task最近新建的 -->
				<%
					TaskService taskService = new TaskService();
					int userId = (Integer) session.getAttribute("u_id");
					ArrayList<Task> tasks = taskService.getNewTask(userId);
					ArrayList<Task> tasksFinish = taskService.getCompleteTask(userId);
				%>
				<ul class="tasks zebra-list">
					<%
						Task topTask = taskService.getTopTask(u_id);
						if (topTask != null && topTask.gettName() != null) {
							String result = java.net.URLEncoder.encode("取消置顶", "utf-8");
							result = java.net.URLEncoder.encode(result, "utf-8");
					%>
					<li><input type="checkbox" /> <span class="title"
						style="color:red;"><%=topTask.gettName()%></span> <br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="title"><%=topTask.gettContent()%></span>
						<span class="meta">Created <em><%=topTask.gettCreateTime()%></em>
							by <em><%=topTask.getCreateBy()%></em></span> <a
						href="taskchange.jsp?t_id=<%=topTask.getT_id()%>"><i
							class="icon-white icon-check"></i> 修改</a> <a onclick="firm()"
						href="Servlet/TaskServlet?t_id=<%=topTask.getT_id()%>&topic=<%=delete%>"><i
							class="icon-white icon-check"></i> 删除</a> <a
						href="Servlet/TaskServlet?t_id=<%=topTask.getT_id()%>&topic=<%=result%>"><i
							class="icon-white icon-check"></i> 取消置顶</a></li>
					<%
						}
					%>
					<%
						String result = java.net.URLEncoder.encode("置顶", "utf-8");
						result = java.net.URLEncoder.encode(result, "utf-8");
						for (int i = 0; i < tasks.size(); i++) {
							Task task = tasks.get(i);
					%>
					<li><input type="checkbox" /> <span class="title"><%=task.gettName()%></span>
						<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="title"><%=task.gettContent()%></span>
						<span class="meta">Created <em><%=task.gettCreateTime()%></em>
							by <em><%=task.getCreateBy()%></em></span> <a
						href="taskchange.jsp?t_id=<%=task.getT_id()%>"><i
							class="icon-white icon-check"></i> 修改</a> <a onclick="firm()"
						href="Servlet/TaskServlet?t_id=<%=task.getT_id()%>&topic=<%=delete%>"><i
							class="icon-white icon-check"></i> 删除</a> <a
						href="Servlet/TaskServlet?t_id=<%=task.getT_id()%>&topic=<%=result%>"><i
							class="icon-white icon-check"></i> 置顶</a></li>
					<%
						}
					%>
				</ul>
				<!-- end task最近新建的 -->
				<%
					String add  = java.net.URLEncoder.encode("添加", "utf-8");
						add = java.net.URLEncoder.encode(add, "utf-8");
				 %>
				<!-- 新建日程 -->
				<a class="toggle-link" href="#new-task"><i class="icon-plus"></i>
					New Task</a>
				<form id="new-task" class="form-horizontal hidden"
					action="Servlet/TaskServlet?topic=<%=add%>" method="POST" onSubmit="return doCheck();">
					<fieldset>
						<legend>New Task</legend>
						<div class="control-group">

							<label class="control-label" for="textarea">活动名称</label>
							<div class="controls">
								<input type="text" id="taskname" name="taskname" /><span
									id="tasknameTip" style="color:red;"></span>
							</div>
							<label class="control-label" style="margin-top:40px"
								for="textarea">活动内容</label>
							<div class="controls" style="margin-top:40px">
								<input type="text" id="content" name="content" /><span
									id="contentTip" style="color:red;"></span>
							</div>
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">新建</button>
						</div>
					</fieldset>
				</form>
				<h2>Completed Tasks</h2>
				<!-- start 完成的task -->
				<ul class="tasks done">
					<%
						for (int i = 0; i < tasksFinish.size(); i++) {
							Task task = tasksFinish.get(i);
					%>
					<li><i class="icon-ok"></i> <span class="title"><%=task.gettName()%></span>
						<span class="meta">Completed <em><%=task.gettCompleteTime()%></em>
							by <em><%=task.getFinishBy()%></em></span></li>
					<%
						}
					%>
				</ul>
				<!-- end 完成的task -->
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/site.js"></script>
</body>
</html>
