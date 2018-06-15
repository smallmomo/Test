<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Integer u_id = (Integer)session.getAttribute("u_id");
	if(u_id==null){
		out.println("<script>alert('请先登录 ');window.location='login.htm';</script>");
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
		<script src="js/My97DatePicker/WdatePicker.js" type="text/javascript">
			function doCheck1(){
				alert(qqq);
				if(document.getElementById('ftime1').value==''){
					document.getElementById('ftimeTip1').innerHTML="该空不能为空";
					return false;
				}else if(document.getElementById('money1').value==''){
					document.getElementById('moneyTip1').innerHTML="该空不能为空";
					return false;
				}else if(document.getElementById('othe1r').value==''){
					document.getElementById('otherTip1').innerHTML="该空不能为空";
					return false;
				}
				return true;
			}
		</script>
		<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
		
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
								<a href="activity.jsp"><i class="icon-list-alt"></i> Activity</a>
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
						新增内容
					</h1>
					<%
					String add  = java.net.URLEncoder.encode("添加", "utf-8");
					add = java.net.URLEncoder.encode(add, "utf-8");
					 %>
					<form action="Servlet/FinancialServlet?topic=<%=add %>" method="POST" onSubmit="return doCheck1();" >
						<fieldset>
							<div class="control-group">
								
								<label class="control-label" for="textarea">时间</label>
								<div class="controls">
									<input type="text" name="ftime1" id="ftime1" onClick="WdatePicker()" value=""/><span id="ftimeTip1" style="color:red;"></span>
								</div>
								<label class="control-label" for="textarea" >收入/支出</label>
								<select name="tag1">
									
									<option value="支出">支出</option>
									<option value="收入">收入</option>
									
								</select>
								<label class="control-label" for="textarea" >金额</label>
								<div class="controls">
									<input type="text" id="money1" name="money1" value="" /><span id="moneyTip1" style="color:red;"></span>
								</div>
								<label class="control-label" for="textarea" >备注</label>
								<div class="controls">
									<input type="text" id="other1" name="other1" value="" /><span id="otherTip1" style="color:red;"></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">添加</button> 
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