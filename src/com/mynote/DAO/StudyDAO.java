package com.mynote.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mynote.DTO.StudyDTO;

public class StudyDAO {
	public ArrayList<StudyDTO> getList(int id){
		ArrayList<StudyDTO> sList = new ArrayList<StudyDTO>();
		// 从数据库中获取开始
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				try{
					Class.forName("com.mysql.jdbc.Driver");
					
					con=DriverManager.getConnection(
							   "jdbc:mysql://localhost:3306/mynote","root","root");
					st = con.createStatement();
					String sql = "select * from study where s_uid = "+id;
					rs = st.executeQuery(sql);
					while(rs.next()){
						StudyDTO study = new StudyDTO();
						study.setS_name(rs.getString("s_name"));
						study.setS_content(rs.getString("s_content"));
						study.setS_type(rs.getString("s_type"));
						study.setS_time(rs.getString("s_time"));
						study.setS_id(Integer.valueOf(rs.getString("s_id")));
						sList.add(study);
					}
				}catch(Exception e){
					System.out.println(e);
				}finally{
					try{
						if(rs!=null){
							rs.close();
						}
						if(st!=null){
							st.close();
						}
						if(con!=null){
							con.close();
						}
					}catch(Exception ex){
						System.out.println(ex);
					}
				}
				//从数据库中获取结束
		return sList;
	}
	/*
	 * 根据分类获取列表
	 * 
	 */
	public ArrayList<StudyDTO> getList(int id,String tag){
		ArrayList<StudyDTO> sList = new ArrayList<StudyDTO>();
		// 从数据库中获取开始
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				try{
					Class.forName("com.mysql.jdbc.Driver");
					
					con=DriverManager.getConnection(
							   "jdbc:mysql://localhost:3306/mynote","root","root");
					st = con.createStatement();
					String sql = "select * from study where s_uid = "+id+" and s_type = '"+tag+"'";
					rs = st.executeQuery(sql);
					while(rs.next()){
						StudyDTO study = new StudyDTO();
						study.setS_name(rs.getString("s_name"));
						study.setS_content(rs.getString("s_content"));
						study.setS_type(rs.getString("s_type"));
						study.setS_time(rs.getString("s_time"));
						study.setS_id(Integer.valueOf(rs.getString("s_id")));
						sList.add(study);
					}
				}catch(Exception e){
					System.out.println(e);
				}finally{
					try{
						if(rs!=null){
							rs.close();
						}
						if(st!=null){
							st.close();
						}
						if(con!=null){
							con.close();
						}
					}catch(Exception ex){
						System.out.println(ex);
					}
				}
				//从数据库中获取结束
		return sList;
	}
	/*
	 * 添加数据
	 */
	public boolean add(StudyDTO s){
		Connection con = null;
		Statement st = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			String sql = "insert into study (s_name,s_content,s_type,s_time,s_uid) value ('"
					+s.getS_name()+"','"
					+s.getS_content()+"','"
					+s.getS_type()+"','"
					+s.getS_time()+"',"
					+s.getS_uid()+
					")";
			System.out.println(sql);
			st.executeUpdate(sql);
			return true;
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				if(st!=null){
					st.close();
				}
				if(con!=null){
					con.close();
				}
			}catch(Exception ex){
				System.out.println(ex);
			}
		}
		return false;
	}
}
