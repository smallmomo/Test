<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<title>Register - Akira</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">
<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script>
	function checkMobile(str) {
		var re = /^13\d{9}$/gi;
		if (re.test(str)) {
			return true;
		} else {
			return false;
		}
	}
	function checkEmail(str) {
		var re =/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
		alert(re.test(str));
		if (re.test(str)) {
			return true;
		} else {
			return false;
		}
	}
	function check(){
		var email = document.getElementById('email');
		var phone = document.getElementById('phone');
		if(email.value==''){
		    document.getElementById('emailTip').innerHTML = "该空不能为空";
			return false;
		} else if (document.getElementById('name').value == '') {
			document.getElementById('nameTip').innerHTML = "该空不能为空";
			return false;
		} else if (phone.value == '') {
			document.getElementById('phoneTip').innerHTML = "该空不能为空";
			return false;
		} else if (document.getElementById('password').value == '') {
			document.getElementById('passwordTip').innerHTML = "该空不能为空";
			return false;
		} else if (!checkEmail(email)){
			document.getElementById('emailTip').innerHTML = "email格式不正确";
			return false;
		}else if (!checkMobile(phone)){
			document.getElementById('phoneTip').innerHTML = "手机号格式不正确";
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div id="login-page" class="container">
		<h1>日记本注册</h1>
		<!-- onSubmit="return check(); -->
		<%
			String change  = java.net.URLEncoder.encode("注册", "utf-8");
						change = java.net.URLEncoder.encode(change, "utf-8");
		 %>
		<form id="login-form" class="well" action="Servlet/UserServlet?&topic=<%=change %>" method="POST">
			Email :<br /> <input type="text" class="span2" placeholder="Email"
				id="email" name="email" /><span id="emailTip" style="color:red;"></span><br />
			name :<br /> <input type="text" class="span2" placeholder="Email"
				id="name" name="name" /><span id="nameTip" style="color:red;"></span><br />
				
			 phone :<br /> <input type="text" class="span2" placeholder="Email" 
			     id="phone" name="phone" /><span id="phoneTip" style="color:red;"></span><br />
			password :<br /> <input type="password" class="span2"
				placeholder="Password" id="password" name="password" /><span id="passwordTip" style="color:red;"></span><br />
			<button type="submit" class="btn btn-primary">注册</button>
		</form>
	</div>
</body>
</html>
