package com.mynote.service;
import com.mynote.DAO.UserDAO;
import com.mynote.DTO.UserDTO;
import com.mynote.vo.User;

public class GetUserService {
	private UserDAO userDAO = new UserDAO();
	
	public User getUser(int userId ){
		User user = new User();
		UserDTO userD = new UserDTO();
		userD = userDAO.getUserById(userId);
		user.setName(userD.getU_name());
		user.setEmail(user.getEmail());
		user.setID(userId);
		return user;
	}
	public GetUserService() {
		
	}
	public boolean addUser(User u){
		UserDTO userD = new UserDTO();
		userD.setU_name(u.getName());
		userD.setU_email(u.getEmail());
		userD.setU_password(u.getPassword());
		userD.setU_phone(u.getPhone());
		boolean rtn = userDAO.addUser(userD);
		return rtn;
	}
	
}
