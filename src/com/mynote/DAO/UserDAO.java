package com.mynote.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mynote.DTO.UserDTO;


public class UserDAO {
	public UserDTO getUserById(int userId ){
		UserDTO user = new UserDTO();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://172.31.100.114:3306/mynote","root","root");
			st = con.createStatement();
			String sql="select * from user where u_id="+userId;
			rs = st.executeQuery(sql);
			if(rs.next()){
				user.setU_id(rs.getInt("u_id"));
				user.setU_email(rs.getString("u_email"));
				user.setU_name(rs.getString("u_name"));
				user.setU_phone(rs.getString("u_phone"));
				user.setU_password(rs.getString("u_password"));
			}
		}catch(Exception ex){
				System.out.println(ex);
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
		return user;
	}
	public boolean addUser(UserDTO u){
		Connection con = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://172.31.100.114:3306/mynote", "root", "root");
			st = con.createStatement();
			String sql = "insert into user (u_name,u_email,u_password,u_phone) value ('"
					+ u.getU_name()
					+ "','"
					+ u.getU_email()
					+ "','"
					+ u.getU_password()
					+ "','"
					+ u.getU_phone()
					+ "')";
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
	public UserDTO doCheck(UserDTO user){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://172.31.100.114:3306/mynote","root","root");
			st = con.createStatement();
			String sql="select * from user where u_email='"
						+user.getU_email()+"' and u_password='"+user.getU_password()+"'";
			rs = st.executeQuery(sql);
			if(rs.next()){
				user.setU_id(rs.getInt("u_id"));
				user.setExist(true);
				return user;
			}else{
				user.setExist(false);
				return user;
			}
		}catch(Exception ex){
				System.out.println(ex);
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
		return user;
	}
}
