package com.mynote.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mynote.service.GetUserService;
import com.mynote.service.LoginService;
import com.mynote.vo.User;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String topic = request.getParameter("topic");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		topic = java.net.URLDecoder.decode(topic, "utf-8");
		if(topic==null||topic.equals(""))
		{
			out.println("<script>alert('非法访问');window.location='"+request.getContextPath()+"/login.htm';</script>");
			return;
		}
		switch (topic) {
		case "登录":
			dologin(request,response);
			break;
		case "注册":
			doRegister(request,response);
			break;
		default:
			break;
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	/*
	 *登录
	 */
	public void dologin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//接受参数
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//转到业务层
		User user = new User(email,password);
		LoginService lService = new LoginService(user);
		int rtn = lService.isExist();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(rtn==-1){
			out.println("<script>alert('email或密码不能为空1');window.location='"+request.getContextPath()+"/login.htm';</script>");
			return;
		}else if(rtn==-2){
			out.println("<script>alert('email或密码不正确');window.location='"+request.getContextPath()+"/login.htm';</script>");
			return;
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("u_id",rtn);
			response.sendRedirect(request.getContextPath()+"/index.html");
		}
		out.close();
	}
	/*
	 *注册
	 */
	public void doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		System.out.print(phone);
		if(email==null||email.equals("")||
		password==null||password.equals("")||
		name==null||name.equals("")||
		phone==null||phone.equals("")){
			out.println("<script>alert('输入不能为空');window.location='"+request.getContextPath()+"/register.jsp';</script>");
			return;
		}else{
			User u = new User();
			u.setName(name);
			u.setEmail(email);
			u.setPassword(password);
			u.setPhone(phone);
			GetUserService gService = new GetUserService();
			if(gService.addUser(u)){
				out.println("<script>alert('注册成功');window.location='"+request.getContextPath()+"/login.htm';</script>");
				return;
			}else{
				out.println("<script>alert('注册失败');window.location='"+request.getContextPath()+"/register.jsp';</script>");
				return;
			}
		}
	}
}
