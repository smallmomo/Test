package com.mynote.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mynote.service.CollectService;
import com.mynote.vo.Collect;

public class CollectServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String topic = request.getParameter("topic");
		
		if (topic == null || topic.equals("")) {
			out.println("<script>alert('非法访问');window.location='"
					+ request.getContextPath() + "/login.htm';</script>");
			return;
		} 
		topic = java.net.URLDecoder.decode(topic, "utf-8");
		switch (topic) {
		case "添加":
			addCollect(request,response);
			break;

		default:
			break;
		}
		out.flush();
		out.close();
	}
	public void addCollect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Integer u_id = (Integer) session.getAttribute("u_id");
		if (u_id == null) {
			out.println("<script>alert('请先登录 ');window.location='login.htm';</script>");
			return;
		}
		String name = request.getParameter("name");
		name = java.net.URLDecoder.decode(name,"utf-8");
		String url = request.getParameter("url");
		Collect c = new Collect();
		c.setC_uid(u_id);
		c.setName(name);
		c.setUrl(url);
		CollectService cService = new CollectService();
		int i = cService.add(c);
		if(i==1){
			out.println("<script>alert('您已经收藏该网址 ');window.location='"
					+ request.getContextPath() + "/article.jsp';</script>");
			return;
		}else if(i==2){
			out.println("<script>alert('收藏成功 ');window.location='"
					+ request.getContextPath() + "/article.jsp';</script>");
			return;
		}else if(i==3){
			out.println("<script>alert('收藏失败 ');window.location='"
					+ request.getContextPath() + "/article.jsp';</script>");
			return;
		}
	} 
}
