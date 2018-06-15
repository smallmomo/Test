package com.mynote.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mynote.DTO.TaskDTO;

public class TaskDAO {
	/*
	 *添加Task
	 */
	public boolean addTask(TaskDTO taskDTO) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String addtime = sdf.format(new Date());
		
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mynote", "root", "root");
			st = con.createStatement();
			String sql = "insert into tasks (t_name,t_createtime,t_createby,t_uid,t_content) value ('"
					+ taskDTO.getT_name()
					+ "','"
					+ addtime
					+ "','"
					+ taskDTO.getT_createby()
					+ "',"
					+ taskDTO.getT_uid()
					+ ",'"
					+ taskDTO.getT_content() + "')";
			System.out.println(sql);
			st.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		return false;
	}
	/*
	 * 获取日程 获取单个日程
	 */
	public TaskDTO getTaskByPrimaryKey(int t_id) {
		TaskDTO task = new TaskDTO();

		// 从数据库中获取开始
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mynote", "root", "root");
			st = con.createStatement();
			String sql = "select * from tasks where t_id = " + t_id;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				task.setT_name(rs.getString("t_name"));
				task.setT_createby(rs.getString("t_createby"));
				task.setT_createtime(rs.getString("t_createtime"));
				task.setT_finishby(rs.getString("t_finishby"));
				task.setT_isfinish(Integer.valueOf(rs.getString("t_isfinish")));
				task.setT_content(rs.getString("t_content"));
				task.setT_comtime(rs.getString("t_comtime"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

		return task;
	}
	/*
	 * 获取已经完成的任务
	 */
		public ArrayList<TaskDTO> getCompleteTask(int uId) {
			ArrayList<TaskDTO> tasks = new ArrayList<TaskDTO>();
			// 从数据库中获取开始
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mynote", "root", "root");
				st = con.createStatement();
				String sql = "select * from tasks where t_isfinish = " + 1
						+ " and t_uid = " + uId;
				rs = st.executeQuery(sql);
				while (rs.next()) {
					TaskDTO task = new TaskDTO();
					task.setT_name(rs.getString("t_name"));
					task.setT_createby(rs.getString("t_createby"));
					task.setT_createtime(rs.getString("t_createtime"));
					task.setT_finishby(rs.getString("t_finishby"));
					task.setT_content(rs.getString("t_content"));
					task.setT_comtime(rs.getString("t_comtime"));
					task.setT_id(rs.getInt("t_id"));
					tasks.add(task);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			// 从数据库中获取结束
			return tasks;
		}
		/*
		 *  获取没完成的任务
		 */
		public ArrayList<TaskDTO> getNewTask(int uId) {
			ArrayList<TaskDTO> tasks = new ArrayList<TaskDTO>();
			// 从数据库中获取开始
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mynote", "root", "root");
				st = con.createStatement();
				String sql = "select * from tasks where t_isfinish = " + 0
						+ " and t_uid = " + uId + " and t_top = 0";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					TaskDTO task = new TaskDTO();
					task.setT_name(rs.getString("t_name"));
					task.setT_createby(rs.getString("t_createby"));
					task.setT_createtime(rs.getString("t_createtime"));
					task.setT_finishby(rs.getString("t_finishby"));
					task.setT_content(rs.getString("t_content"));
					task.setT_comtime(rs.getString("t_comtime"));
					task.setT_id(rs.getInt("t_id"));
					tasks.add(task);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			// 从数据库中获取结束
			return tasks;
		}
		/*
		 * 获取置顶日程
		 */
		public TaskDTO getTopTask(int uid) {
			TaskDTO task = new TaskDTO();

			// 从数据库中获取开始
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mynote", "root", "root");
				st = con.createStatement();
				String sql = "select * from tasks where t_top = 1 and t_uid = "+uid;
				rs = st.executeQuery(sql);
				if (rs.next()) {
					task.setT_name(rs.getString("t_name"));
					task.setT_createby(rs.getString("t_createby"));
					task.setT_createtime(rs.getString("t_createtime"));
					task.setT_finishby(rs.getString("t_finishby"));
					task.setT_content(rs.getString("t_content"));
					task.setT_comtime(rs.getString("t_comtime"));
					task.setT_id(Integer.valueOf(rs.getString("t_id")));
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}

			return task;
		}
		/*
		 * 设置置顶日程
		 */
		public boolean setTopTask(int t_id) {
			Connection con = null;

			Statement st = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mynote", "root", "root");
				st = con.createStatement();

				String sql = "update tasks set t_top =0 where t_top =1";
				System.out.println(sql);
				st.executeUpdate(sql);
				
				String sql1="update tasks set t_top =1 where t_id = "+t_id;
				System.out.println(sql1);
				st.executeUpdate(sql1);
				return true;
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			return false;
		}
		/*
		 * 取消置顶日程
		 */
		public boolean deleteTopTask(int t_id) {
			Connection con = null;

			Statement st = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mynote", "root", "root");
				st = con.createStatement();

				String sql = "update tasks set t_top =0 where t_id = "+t_id;
				System.out.println(sql);
				st.executeUpdate(sql);
				return true;
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			return false;
		}
		/*
		 * 修改日程 如果成功跳到已经修改的task的task页面 如果不成功弹出修改失败，继续跳到task页面
		 */
		public boolean changeTask(TaskDTO task) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String addtime = sdf.format(new Date());

			Connection con = null;

			Statement st = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mynote", "root", "root");
				st = con.createStatement();

				String sql = "update tasks set t_name ='" + task.getT_name()
						+ "',t_isfinish='" + task.getT_isfinish() + "',t_content='"
						+ task.getT_content() + "',t_comtime='" + addtime
						+ "',t_finishby='" + task.getT_finishby() + "' where t_id ="
						+ task.getT_id();
				System.out.println(sql);
				st.executeUpdate(sql);
				return true;
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			return false;
		}

		/*
		 * 删除日程
		 */
		public boolean deleteTask(int t_id) {
			Connection con = null;
			Statement st = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mynote", "root", "root");
				st = con.createStatement();
				String sql = "delete from tasks where t_id =" + t_id;
				System.out.println(sql);
				st.executeUpdate(sql);
				return true;
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			return false;
		}
}
