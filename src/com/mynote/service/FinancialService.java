package com.mynote.service;


import java.util.ArrayList;

import com.mynote.DAO.FinancialDAO;
import com.mynote.DTO.FinancialDTO;
import com.mynote.vo.Financial;

public class FinancialService {

	private FinancialDAO fDAO = new FinancialDAO();
	/*
	 * 获取所有的页面个数
	 */
	public int getTotalPages(int id){
		int totalPages = fDAO.getTotalPages(id); 
		return totalPages;
	}
	/*
	 * 添加数据
	 */
	public boolean add(Financial financial){
		boolean b = false;
		FinancialDTO fDTO = new FinancialDTO();
		fDTO.setF_type(financial.getType());
		fDTO.setF_money(financial.getMoney());
		fDTO.setF_other(financial.getOther());
		fDTO.setF_allmoney(financial.getAllMoney());
		fDTO.setF_time(financial.getDatetime());
		fDTO.setF_uid(financial.getId());
		b = fDAO.add(fDTO);
		return b;
	}
	/*
	 * 根据页数获取8条数据
	 */
	public ArrayList<Financial> getFinancials(int id,int page){
		ArrayList<Financial> fList = new ArrayList<Financial>();
		ArrayList<FinancialDTO> fDList = new ArrayList<FinancialDTO>();
		fDList = fDAO.getFinancials(id, page);
		for(FinancialDTO fDto:fDList){
			Financial f = new Financial();
			f.setF_id(fDto.getF_id());
			f.setDatetime(fDto.getF_time());
			f.setAllMoney(fDto.getF_allmoney());
			f.setMoney(fDto.getF_money());
			f.setOther(fDto.getF_other());
			f.setType(fDto.getF_type());
			fList.add(f);
		}
		return fList;
	}
	/*
	 * 根据一个ID查询数据
	 * 
	 */
	public Financial getFinancial(int i){
		Financial f = new Financial();
		FinancialDTO fDto = new FinancialDTO();
		fDto = fDAO.getFinancial(i);
		f.setF_id(fDto.getF_id());
		f.setDatetime(fDto.getF_time());
		f.setAllMoney(fDto.getF_allmoney());
		f.setMoney(fDto.getF_money());
		f.setOther(fDto.getF_other());
		f.setType(fDto.getF_type());
		return f;
	}
	/*
	 * 根据f_id修改数据
	 * 
	 */
	public boolean  changeFinancial(Financial f){
		FinancialDTO financialDTO = new FinancialDTO();
		financialDTO.setF_id(f.getF_id());
		financialDTO.setF_money(f.getMoney());
		financialDTO.setF_other(f.getOther());
		financialDTO.setF_type(f.getType());
		financialDTO.setF_time(f.getDatetime());
		boolean rtn = fDAO.changeFinancial(financialDTO);
		return rtn;
	}
	/**
	 * 判断是否为数字
	 */
	public static boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
}
