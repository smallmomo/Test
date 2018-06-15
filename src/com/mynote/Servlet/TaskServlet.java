package com.mynote.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mynote.service.GetUserService;
import com.mynote.service.TaskService;
import com.mynote.vo.Task;
import com.mynote.vo.User;

public class TaskServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String topic = request.getParameter("topic");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (topic == null || topic.equals("")) {
			out.println("<script>alert('非法访问');window.location='"
					+ request.getContextPath() + "/login.htm';</script>");
			return;
		}
		topic = java.net.URLDecoder.decode(topic, "utf-8");
		switch (topic) {
		case "取消置顶":
			deleteTop(request, response);
			break;
		case "置顶":
			Top(request, response);
			break;
		case "修改":
			taskChange(request, response);
			break;
		case "删除":
			taskDelete(request, response);
			break;
		case "添加":
			taskAdd(request, response);
			break;
		default:
			break;
		}
		
		response.setContentType("text/html");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/*
	 * 取消置顶
	 */
	public void deleteTop(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer t_id = Integer.valueOf(request.getParameter("t_id"));
		TaskService tService = new TaskService();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (tService.deleteTopTask(t_id)) {
			out.println("<script>alert('取消置顶成功');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
		} else {
			out.println("<script>alert('取消置顶失败');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
		}
	}

	/*
	 * 置顶
	 */
	public void Top(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer t_id = Integer.valueOf(request.getParameter("t_id"));
		TaskService tService = new TaskService();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (tService.setTopTask(t_id)) {
			out.println("<script>alert('置顶成功');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
		} else {
			out.println("<script>alert('置顶失败');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
		}
	}

	/*
	 * 修改
	 */
	public void taskChange(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session=request.getSession();
		
		int u_id = (Integer)session.getAttribute("u_id");
		GetUserService getUser = new GetUserService();
		User user = getUser.getUser(u_id);
		if(user==null){
			out.println("<script>alert('请重新登录');window.location='"
					+ request.getContextPath() + "/login.htm';</script>");
			return;
		}
		String name = user.getName();
		
		int t_id = Integer.valueOf(request.getParameter("t_id"));
		String tName = request.getParameter("taskname");
		String tContent = request.getParameter("content");
		String isfinish = request.getParameter("tag");
		if(tName!=null&&!"".equals("tName")
			&&tContent!=null&&!"".equals("tContent")
			&&isfinish!=null&&!"".equals("isfinish")
		){
			Task task = new Task();
			task.setT_id(t_id);
			task.settName(tName);
			task.settContent(tContent);
			task.settFinish(isfinish);
			task.setFinishBy(name);
			
			TaskService taskService = new TaskService();
			if(taskService.changeTask(task)){
				out.println("<script>alert('修改成功');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
			}else{
				out.println("<script>alert('修改失败');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");

			}
		}else{
			out.println("<script>alert('出现错误啦!');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
			return;
		}
	}
	/*
	 * 删除
	 */
	public void taskDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int t_id = Integer.parseInt(request.getParameter("t_id"));
		TaskService tService = new TaskService();
		if(tService.deleteTask(t_id)){
			out.println("<script>alert('删除成功');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
		}else{
			out.println("<script>alert('删除失败');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
		}
	}
	/*
	 * 新增
	 */
	public void taskAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求编码
		request.setCharacterEncoding("utf-8");	
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		//接受参数,初始化task
		Task task = new Task();
		Integer u_id = (Integer)session.getAttribute("u_id");
		GetUserService getUser = new GetUserService();
		User user = getUser.getUser(u_id);
		if(user==null){
			out.println("<script>alert('请重新登录');window.location='"
					+ request.getContextPath() + "/login.htm';</script>");
			return;
		}
		String tName = request.getParameter("taskname");
		String tContent = request.getParameter("content");
		if(tName==null||"".equals(tName)){
			out.println("<script>alert('活动名称不能为空');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
			return;
		}
		if(tContent==null||"".equals(tContent)){
			out.println("<script>alert('活动内容不能为空');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
			return;
		}
		task.settName(tName);
		task.settContent(tContent);
		
		
		String name = user.getName();
		task.setCreateBy(name);
		task.setT_uid(u_id);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    String addtime=sdf.format(new Date());
	    task.settCreateTime(addtime);
	    //调用service，将其添加到数据库
		TaskService tService = new TaskService();
	    boolean rtn = tService.addTask(task);
	    if(rtn){
	    	out.println("<script>alert('添加成功');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
			return;
	    }else{
	    	out.println("<script>alert('添加失败');window.location='"
					+ request.getContextPath() + "/tasks.jsp';</script>");
			
	    }
	}
}
