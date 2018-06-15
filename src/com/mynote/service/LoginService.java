package com.mynote.service;


import com.mynote.DAO.UserDAO;
import com.mynote.DTO.UserDTO;
import com.mynote.vo.User;

public class LoginService {
	private User user;
	private UserDAO userD = new UserDAO();
	private UserDTO userDTO = new UserDTO();
	public LoginService(User user) {
		this.user = user;
	}
	public int isExist(){
		if(user.getEmail()==null||user.getPassword()==null||user.getEmail()==""||user.getPassword()==""){
			//用户名或密码为空
			return -1;
		}else{
			userDTO.setU_email(user.getEmail());
			userDTO.setU_password(user.getPassword());
			boolean rtn =userD.doCheck(userDTO).isExist();
			if(rtn){
				//帐号存在
				user.setID(userD.doCheck(userDTO).getU_id());
				return userD.doCheck(userDTO).getU_id();
			}else{
				//email或密码不正确
				return -2;
			}
		}
		
	}
}
