<%@page import="com.mynote.service.CollectService"%>
<%@page import="com.mynote.vo.Collect"%>
<%@page import="com.mynote.vo.Study"%>
<%@page import="com.mynote.service.StudyService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";

	Integer u_id = (Integer) session.getAttribute("u_id");
	if (u_id == null) {
		out.println("<script>alert('请先登录 ');window.location='login.htm';</script>");
		return;
	}
	//获取所有收藏的网址
	ArrayList<Collect> cList = new ArrayList<Collect>();
	CollectService cService = new CollectService();
	cList = cService.getList(u_id);
	//获取当前页面的网址
   String url=request.getScheme()+"://";   
   url+=request.getHeader("host");   
   url+=request.getRequestURI();   
   if(request.getQueryString()!=null)   
   url+="?"+request.getQueryString();  
	String name = "学习笔记";

	//获取所有学习笔记
	ArrayList<Study> sList = new ArrayList<Study>();
	StudyService sService = new StudyService();
	sList = sService.getList(u_id);
	request.setCharacterEncoding("UTF-8");
	String tag = request.getParameter("tag");
	if (tag != null && !tag.equals("")) {
		sList = sService.getList(u_id, tag);
	}
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
<title>Messages - Akira</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">
<script>
	function doCheck() {
		if (document.getElementById('studyname').value == '') {
			document.getElementById('studynameTip').innerHTML = "请输入名称";
			return false;
		} else if (document.getElementById('content').value == '') {
			document.getElementById('contentTip').innerHTML = "请输入内容";
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
<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
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
									<li><a href="help.htm">添加到收藏夹</a></li>
									<%
										for(int i=0;i<cList.size();i++){
										Collect c=new Collect(); 
										c=cList.get(i);
									 %>
									<li><a href="<%=c.getUrl()%>"><%=c.getName()%></a></li>
									<%} %>
									
								</ul></li>
						</ul>
						<form class="navbar-search pull-left" action="">
							<input type="text" class="search-query span2"
								placeholder="Search" />
						</form>
						<!--  网址收藏夹begin -->
						<ul class="nav">
								<li class="dropdown"><a href="article.jsp"
								class="dropdown-toggle" data-toggle="dropdown">收藏 <b
									class="caret"></b></a>
								<ul class="dropdown-menu">
										<% name=java.net.URLEncoder.encode("学习笔记","utf-8");
										name=java.net.URLEncoder.encode(name,"utf-8");
										String add1  = java.net.URLEncoder.encode("添加", "utf-8");
										add1 = java.net.URLEncoder.encode(add1, "utf-8");
										%>
										<li><a href="servlet/CollectServlet?name=<%=name%>&url=<%=url%>&topic=<%=add1%>">添加到收藏夹</a></li>
									<%
										for(int i=0;i<cList.size();i++){
										Collect c=new Collect(); 
										c=cList.get(i);
									 %>
									<li><a href="<%=c.getUrl()%>"><%=c.getName()%></a></li>
									<%} %>
								</ul></li>
						</ul>
						<!--  网址收藏夹end -->
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
						<li><a href="tasks.jsp"><i class="icon-white icon-check"></i>
								日程</a></li>
						<li class="active"><a href="article.jsp"><i
								class="icon-envelope"></i> 文章</a></li>
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
				<h1>学习笔记</h1>
				<form action="article.jsp" method="POST">
					所属类别： <select name="tag">
						<option>请选择</option>
						<option value="C">C</option>
						<option value="C++">C++</option>
						<option value="C#">C#</option>
						<option value="java">java</option>
						<option value="js">js</option>
						<option value="CSS">CSS</option>
					</select> <input type="submit" class="btn btn-primary" value="查询" />
				</form>
				<!-- 文章列表 -->
				<ul class="messages">
					<%
						for (int i = 0; i < sList.size(); i++) {

							Study s = sList.get(i);
					%>
					<li class="well">
						<h4><%=s.getName()%></h4>
						<h5 style="color:#08c;"><%=s.getType()%></h5>
						<p class="message"><%=s.getContent()%></p> <span class="meta">写于
							<em><%=s.getTime()%></em>
					</span>
					</li>
					<%
						}
					%>
				</ul>
				<!-- 文章列表end -->
				<a class="toggle-link" href="#message-reply"><i
					class="icon-plus"></i> 新建笔记</a>
				<%
					String add  = java.net.URLEncoder.encode("添加", "utf-8");
						add = java.net.URLEncoder.encode(add, "utf-8");
				 %>
				<form id="message-reply" class="form-horizontal hidden"
					action="servlet/ArticleServlet?u_id=<%=u_id%>&topic=<%=add %>" method="POST"
					onSubmit="return doCheck();">
					<fieldset>
						<legend>新建笔记</legend>
						<div class="control-group">
							<label class="control-label" for="textarea">主题</label>
							<div class="controls">
								<input type="text" id="studyname" name="studyname" /><span
									id="studynameTip" style="color:red;"></span>
							</div>
							<label class="control-label" for="textarea"
								style="margin-top:40px">内容</label>
							<div class="controls" style="margin-top:40px">
								<textarea class="input-xlarge" id="content" name="content"
									rows="4"></textarea>
								<span id="contentTip" style="color:red;"></span>
							</div>
						</div>
						<label class="control-label" for="textarea">所属类别：</label>
						<div class="controls">
							<select name="tag">
								<option value="C">C</option>
								<option value="C++">C++</option>
								<option value="C#">C#</option>
								<option value="java">java</option>
								<option value="js">js</option>
								<option value="CSS">CSS</option>
							</select>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn btn-primary">新建</button>

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
