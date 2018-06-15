package com.mynote.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mynote.service.FinancialService;
import com.mynote.vo.Financial;

public class FinancialServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
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
			addFinancial(request, response);
			break;
		case "修改":
			changeFinancial(request, response);
			break;
		default:
			break;
		}
		
		out.flush();
		out.close();
	}

	/**
	 * 添加
	 */
	public void addFinancial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Integer u_id = (Integer) session.getAttribute("u_id");
		if (u_id == null) {
			out.println("<script>alert('请先登录 ');window.location='"
					+ request.getContextPath() + "/login.htm';</script>");
			return;
		}
		String datetime = request.getParameter("ftime1");
		String type = request.getParameter("tag1");
		String money1 = request.getParameter("money1");
		String other = request.getParameter("other1");

		if (!datetime.equals("") && datetime != null && !type.equals("")
				&& type != null && money1 != null && !money1.equals("")
				&& !other.equals("") && other != null) {
			if (!FinancialService.isNumeric(money1)) {
				out.println("<script>alert('金额必须为数字');window.location='"
					+ request.getContextPath() + "/financial_add.jsp';</script>");
				return;
			} else {
				Double money = Double.valueOf(money1);
				Financial financial = new Financial();
				financial.setDatetime(datetime);
				financial.setType(type);
				financial.setMoney(money);
				financial.setOther(other);
				financial.setId(u_id);
				System.out.println(u_id);

				FinancialService fService = new FinancialService();
				if (fService.add(financial)) {
					out.println("<script>alert('添加成功');window.location='"
					+ request.getContextPath() + "/activity.jsp';</script>");
				} else {
					out.println("<script>alert('添加失败');window.location='"
					+ request.getContextPath() + "/financial_add.jsp';</script>");
				}
			}
		} else {
			out.println("<script>alert('出入不能为空');window.location='"
					+ request.getContextPath() + "/financial_add.jsp';</script>");
		}
		
	}
	/**
	 * 修改
	 */
	public void changeFinancial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id=request.getParameter("f_id");
		if(id==null&&"".equals(id)){
			out.println("<script>alert('参数有误');window.location='"
					+ request.getContextPath() + "/activity.jsp';</script>");
			return;
		}
		int f_id = Integer.valueOf(request.getParameter("f_id"));
		
		String datetime = request.getParameter("ftime"); 
		String type = request.getParameter("tag");
		Double money = Double.valueOf(request.getParameter("money"));
		String other = request.getParameter("other");
		
		
		if(!datetime.equals("")&&datetime!=null
			&&!type.equals("")&&type!=null
			&&money!=null
			&&!other.equals("")&&other!=null
			){
			Financial financial = new Financial();
			financial.setDatetime(datetime);
			financial.setType(type);
			financial.setMoney(money);
			financial.setOther(other);
			financial.setF_id(f_id);
			
			FinancialService fService = new FinancialService();
			if(fService.changeFinancial(financial)){
				out.println("<script>alert('修改成功');window.location='"
					+ request.getContextPath() + "/activity.jsp';</script>");
			}else{
				out.println("<script>alert('修改失败');window.location='"
					+ request.getContextPath() + "/financial_change.jsp';</script>");
			}
		}else{
			out.println("<script>alert('修改出入不能为空');window.location='"
					+ request.getContextPath() + "/financial_change.jsp';</script>");
		}
		
	}
}
