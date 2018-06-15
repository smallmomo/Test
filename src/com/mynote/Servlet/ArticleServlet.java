package com.mynote.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mynote.service.StudyService;
import com.mynote.vo.Study;

public class ArticleServlet extends HttpServlet {

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
			addArticle(request,response);
			break;

		default:
			break;
		}
		out.flush();
		out.close();
	}
	public void addArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("studyname");
		String content =request.getParameter("content");
		String tag = request.getParameter("tag");
		Integer u_id = Integer.valueOf(request.getParameter("u_id"));
		if(name==null||"".equals(name)){
			out.println("<script>alert('名称不能为空');window.location='"
					+ request.getContextPath() + "/article.jsp';</script>");
			return;
		}
		if(content==null||"".equals(content)){
			out.println("<script>alert('内容不能为空');window.location='"
					+ request.getContextPath() + "/article.jsp';</script>");
			return;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    String time=sdf.format(new Date());
	    Study s=new Study();
	    s.setContent(content);
	    s.setName(name);
	    s.setS_uid(u_id);
	    s.setTime(time);
	    s.setType(tag);
	    StudyService sService = new StudyService();
	    if(sService.add(s)){
	    	out.println("<script>alert('添加成功');window.location='"
					+ request.getContextPath() + "/article.jsp';</script>");
		
	    }else{
	    	out.println("<script>alert('添加失败');window.location='"
					+ request.getContextPath() + "/article.jsp';</script>");
			return;
	    }
	}
}
