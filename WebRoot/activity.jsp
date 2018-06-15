<%@page import="com.mynote.service.FinancialService"%>
<%@page import="com.mynote.vo.Financial"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	request.setCharacterEncoding("UTF-8");
	Integer u_id = (Integer) session.getAttribute("u_id");
	if (u_id == null) {
		out.println("<script>alert('请先登录 ');window.location='login.htm';</script>");
		return;
	}
	FinancialService fService = new FinancialService();

	int page1 = 1;
	String strpage = request.getParameter("page");
	if (strpage != null && !strpage.equals("")) {
		page1 = Integer.valueOf(strpage);
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
<title>Activity - Akira</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">
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
						<li><a href="tasks.jsp"><i
								class="icon-white icon-check"></i> 日程</a></li>
						<li><a href="article.jsp"><i class="icon-envelope"></i>
								文章</a></li>
						<li><a href="files.htm"><i class="icon-file"></i> 文件</a></li>
						<li class="active"><a href="activity.jsp"><i class="icon-list-alt"></i>
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
				<h1>
					财务记录 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<a href="financial_add.jsp" class="btn btn-primary">新建财务</a>
				</h1>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>时间</th>
							<th>收入/支出</th>
							<th>金额</th>
							<th>备注</th>
							<th>总金额</th>
							<th>详情</th>
						</tr>
					</thead>
					<tbody>
						<%
							ArrayList<Financial> fList = new ArrayList<Financial>();
							fList = fService.getFinancials(u_id, page1);
							for (int i = 0; i < fList.size(); i++) {
								Financial f = fList.get(i);
						%>
						<tr>
							<td><%=f.getDatetime()%></td>
							<td><%=f.getType()%></td>
							<td><%=f.getMoney()%></td>
							<td><%=f.getOther()%></td>
							<td><%=f.getAllMoney()%></td>
							<td><a href="financial_change.jsp?f_id=<%=f.getF_id()%>"><i
									class="icon-white icon-check"></i> 修改</a> <a
								href="financial_delete.jsp?f_id=<%=f.getF_id()%>"><i
									class="icon-white icon-check"></i> 删除</a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<!-- 显示页数栏开始 -->
				<%
					int totalPages = fService.getTotalPages(u_id);
					if (page1 == 1) {
				%>
				<div class="pagination">
					<ul>
						<li class="disabled"><a href="#">&laquo;</a></li>
						<li class="active"><a href="activity.jsp?page=1">1</a></li>
						<li><a href="activity.jsp?page=2">2</a></li>
						<li><a href="activity.jsp?page=3">3</a></li>
						<li><a href="activity.jsp?page=4">4</a></li>
						<li><a>...</a></li>
						<li><a href="activity.jsp?page=<%=totalPages%>"><%=totalPages%></a>
						</li>
						<li><a href="activity.jsp?page=<%=page1 + 1%>">&raquo;</a></li>
					</ul>
				</div>
				<%
					} else if (page1 == 2) {
				%>
				<div class="pagination">
					<ul>
						<li><a href="activity.jsp?page=<%=page1 - 1%>">&laquo;</a></li>
						<li><a href="activity.jsp?page=1">1</a></li>
						<li class="active"><a href="activity.jsp?page=2">2</a></li>
						<li><a href="activity.jsp?page=3">3</a></li>
						<li><a href="activity.jsp?page=4">4</a></li>
						<li><a>...</a></li>
						<li><a href="activity.jsp?page=<%=totalPages%>"><%=totalPages%></a>
						</li>
						<li><a href="activity.jsp?page=<%=page1 + 1%>">&raquo;</a></li>
					</ul>
				</div>
				<%
					} else if (page1 == 3) {
				%>
				<div class="pagination">
					<ul>
						<li><a href="activity.jsp?page=<%=page1 - 1%>">&laquo;</a></li>
						<li><a href="activity.jsp?page=1">1</a></li>
						<li><a href="activity.jsp?page=2">2</a></li>
						<li class="active"><a href="activity.jsp?page=3">3</a></li>
						<li><a href="activity.jsp?page=4">4</a></li>
						<li><a>...</a></li>
						<li><a href="activity.jsp?page=<%=totalPages%>"><%=totalPages%></a>
						</li>
						<li><a href="activity.jsp?page=<%=page1 + 1%>">&raquo;</a></li>
					</ul>
				</div>
				<%
					} else if (page1 == totalPages) {
				%>
				<div class="pagination">
					<ul>
						<li><a href="activity.jsp?page=<%=page1 - 1%>">&laquo;</a></li>
						<li><a href="activity.jsp?page=1">1</a></li>
						<li><a>...</a></li>
						<li><a href="activity.jsp?page=<%=totalPages - 3%>"><%=totalPages - 3%></a>
						</li>
						<li><a href="activity.jsp?page=<%=totalPages - 2%>"><%=totalPages - 2%></a>
						</li>
						<li><a href="activity.jsp?page=<%=totalPages - 1%>"><%=totalPages - 1%></a>
						</li>
						<li class="active"><a
							href="activity.jsp?page=<%=totalPages%>"><%=totalPages%></a></li>
						<li class="disabled"><a href="#">&raquo;</a></li>
					</ul>
				</div>
				<%
					} else if (page1 == totalPages - 1) {
				%>
				<div class="pagination">
					<ul>
						<li><a href="activity.jsp?page=<%=page1 - 1%>">&laquo;</a></li>
						<li><a href="activity.jsp?page=1">1</a></li>
						<li><a>...</a></li>
						<li><a href="activity.jsp?page=<%=totalPages - 3%>"><%=totalPages - 3%></a>
						</li>
						<li><a href="activity.jsp?page=<%=totalPages - 2%>"><%=totalPages - 2%></a>
						</li>
						<li class="active"><a
							href="activity.jsp?page=<%=totalPages - 1%>"><%=totalPages - 1%></a>
						</li>
						<li><a href="activity.jsp?page=<%=totalPages%>"><%=totalPages%></a>
						</li>
						<li><a href="activity.jsp?page=<%=page1 + 1%>">&raquo;</a></li>
					</ul>
				</div>
				<%
					} else if (page1 == totalPages - 2) {
				%>
				<div class="pagination">
					<ul>
						<li><a href="activity.jsp?page=<%=page1 - 1%>">&laquo;</a></li>
						<li><a href="activity.jsp?page=1">1</a></li>
						<li><a>...</a></li>
						<li><a href="activity.jsp?page=<%=totalPages - 3%>"><%=totalPages - 3%></a>
						</li>
						<li class="active"><a
							href="activity.jsp?page=<%=totalPages - 2%>"><%=totalPages - 2%></a>
						</li>
						<li><a href="activity.jsp?page=<%=totalPages - 1%>"><%=totalPages - 1%></a>
						</li>
						<li><a href="activity.jsp?page=<%=totalPages%>"><%=totalPages%></a>
						</li>
						<li><a href="activity.jsp?page=<%=page1 + 1%>">&raquo;</a></li>
					</ul>
				</div>
				<%
					} else {
				%>
				<div class="pagination">
					<ul>
						<li><a href="activity.jsp?page=<%=page1 - 1%>">&laquo;</a></li>
						<li><a href="activity.jsp?page=1">1</a></li>
						<li><a>...</a></li>
						<li><a href="activity.jsp?page=<%=page1 - 1%>"><%=page1 - 1%></a>
						</li>
						<li class="active"><a href="activity.jsp?page=<%=page1%>"><%=page1%></a>
						</li>
						<li><a href="activity.jsp?page=<%=page1 + 1%>"><%=page1 + 1%></a>
						</li>
						<li><a>...</a></li>
						<li><a href="activity.jsp?page=<%=totalPages%>"><%=totalPages%></a>
						</li>
						<li><a href="activity.jsp?page=<%=page1 + 1%>">&raquo;</a></li>
					</ul>
				</div>
				<%
					}
				%>
				<!-- 显示页数栏结束 -->
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/site.js"></script>
</body>
</html>
