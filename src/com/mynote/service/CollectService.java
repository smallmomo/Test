package com.mynote.service;

import java.util.ArrayList;

import com.mynote.DAO.CollectDAO;
import com.mynote.DTO.CollectDTO;
import com.mynote.vo.Collect;

public class CollectService {
	CollectDAO cDAO = new CollectDAO();
	public ArrayList<Collect> getList(int id){
	
		ArrayList<Collect> cList =new ArrayList<Collect>();
		ArrayList<CollectDTO> cDList =new ArrayList<CollectDTO>();
		cDList = cDAO.getList(id);
		for(CollectDTO cDTO:cDList){
			Collect c =new Collect(); 
			c.setC_id(cDTO.getC_id());
			c.setName(cDTO.getC_name());
			c.setUrl(cDTO.getC_url());
			cList.add(c);
		}
		return cList;
	}
	/*
	 * 添加数据
	 */
	public int add(Collect c){
		CollectDTO cDTO = new CollectDTO();
		cDTO.setC_name(c.getName());
		cDTO.setC_uid(c.getC_uid());
		cDTO.setC_url(c.getUrl());
		int i = cDAO.add(cDTO);
		return i;
	}
}
