package com.mynote.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mynote.DTO.FinancialDTO;

public class FinancialDAO {
	private int limit = 8;
	/*
	 * 获取所有的页面个数
	 */
	public int getTotalPages(int id){
		int totalPages=1;
		int count=1;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			String sql="select count(*) from financial where f_uid = "+id;
			rs=st.executeQuery(sql);
			if(rs.next()){
				count=rs.getInt(1);
				totalPages=(int)Math.ceil(count/(limit*1.0));
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
		return totalPages;
	}
	/*
	 * 添加数据
	 */
	public boolean add(FinancialDTO financial){
		boolean b = false;
		Connection con = null;
		Statement st = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			String sql = "insert into financial (f_type,f_money,f_other,f_allmoney,f_time,f_uid) value ('"
					+financial.getF_type()+"',"
					+financial.getF_money()+",'"
					+financial.getF_other()+"',"
					+financial.getF_allmoney()+",'"
					+financial.getF_time()+"',"
					+financial.getF_uid()+
					")";
			st.executeUpdate(sql);
			System.out.println(sql);
			b=true;
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
		return b;
	}
	/*
	 * 根据页数获取8条数据
	 */
	public ArrayList<FinancialDTO> getFinancials(int id,int page){
		ArrayList<FinancialDTO> fList = new ArrayList<FinancialDTO>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			String sql="select * from financial where f_uid = "+id+" order by f_time desc limit "+(page-1)*limit+","+limit;
			System.out.println(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				FinancialDTO f = new FinancialDTO();
				f.setF_time(rs.getString("f_time"));
				f.setF_money(rs.getDouble("f_money"));
				f.setF_other(rs.getString("f_other"));
				f.setF_allmoney(rs.getDouble("f_allmoney"));
				f.setF_type(rs.getString("f_type"));
				f.setF_id(rs.getInt("f_id"));
				fList.add(f);
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
		
		return fList;
	}
	/*
	 * 根据一个ID查询数据
	 * 
	 */
	public FinancialDTO getFinancial(int i){
		FinancialDTO f = new FinancialDTO();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			String sql="select * from financial where f_id = "+i;
			System.out.println(sql);
			rs=st.executeQuery(sql);
			if(rs.next()){
				f.setF_time(rs.getString("f_time"));
				f.setF_money(rs.getDouble("f_money"));
				f.setF_other(rs.getString("f_other"));
				f.setF_allmoney(rs.getDouble("f_allmoney"));
				f.setF_type(rs.getString("f_type"));
				f.setF_id(rs.getInt("f_id"));
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
		return f;
	}
	/*
	 * 根据f_id修改数据
	 * 
	 */
	public boolean  changeFinancial(FinancialDTO f){
		Connection con = null;
		Statement st = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(
					   "jdbc:mysql://localhost:3306/mynote","root","root");
			st = con.createStatement();
			
			String sql = "update financial set f_type ='"
					+f.getF_type()+"',f_other='"
					+f.getF_other()+"',f_money="
					+f.getF_money()+",f_time='"
					+f.getF_time()
					+"' where f_id ="+f.getF_id();
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
