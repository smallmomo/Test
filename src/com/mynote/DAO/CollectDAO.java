package com.mynote.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mynote.DTO.CollectDTO;


public class CollectDAO {
	public ArrayList<CollectDTO> getList(int id){
		ArrayList<CollectDTO> cList =new ArrayList<CollectDTO>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			String sql = "select * from collect where c_uid = "+id;
			rs = st.executeQuery(sql);
			while(rs.next()){
				CollectDTO c = new CollectDTO();
				c.setC_name(rs.getString("c_name"));
				c.setC_url(rs.getString("c_url"));
				c.setC_id(Integer.valueOf(rs.getString("c_id")));
				cList.add(c);
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
		
		return cList;
	}
	/*
	 * 添加数据
	 */
	public int add(CollectDTO c){

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			
			String sql = "SELECT * FROM collect WHERE c_url=1";
			System.out.println(sql);
			rs = st.executeQuery(sql);
			if(rs.next()){
				return 1;
			}else{
				String sql1= "insert into collect (c_name,c_url,c_uid) value ('"
						+c.getC_name()+"','"
						+c.getC_url()+"',"
						+c.getC_uid()+
						")";
				st.executeUpdate(sql1);	
				System.out.println(sql1);
				return 2;
			}
			
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
		
		return  3;
	}
}
