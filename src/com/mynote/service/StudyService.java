package com.mynote.service;

import java.util.ArrayList;

import com.mynote.DAO.StudyDAO;
import com.mynote.DTO.StudyDTO;
import com.mynote.vo.Study;
	
public class StudyService {
	private StudyDAO sDAO = new StudyDAO();
	public ArrayList<Study> getList(int id){
		ArrayList<Study> sList = new ArrayList<Study>();
		ArrayList<StudyDTO> sDList = new ArrayList<StudyDTO>();
		sDList = sDAO.getList(id);
		for(StudyDTO sDTO:sDList){
			Study s=new Study();
			s.setS_id(sDTO.getS_id());
			s.setName(sDTO.getS_name());
			s.setContent(sDTO.getS_content());
			s.setTime(sDTO.getS_time());
			s.setType(sDTO.getS_type());
			sList.add(s);
		}
		return sList;
	}
	/*
	 * 根据分类获取列表
	 * 
	 */
	public ArrayList<Study> getList(int id,String tag){
		ArrayList<Study> sList = new ArrayList<Study>();
		ArrayList<StudyDTO> sDList = new ArrayList<StudyDTO>();
		sDList = sDAO.getList(id, tag);
		for(StudyDTO sDTO:sDList){
			Study s=new Study();
			s.setS_id(sDTO.getS_id());
			s.setName(sDTO.getS_name());
			s.setContent(sDTO.getS_content());
			s.setTime(sDTO.getS_time());
			s.setType(sDTO.getS_type());
			sList.add(s);
		}
		return sList;
	}
	/*
	 * 添加数据
	 */
	public boolean add(Study s){
		StudyDTO sDTO = new StudyDTO();
		sDTO.setS_uid(s.getS_uid());
		sDTO.setS_name(s.getName());
		sDTO.setS_content(s.getContent());
		sDTO.setS_time(s.getTime());
		sDTO.setS_type(s.getType());
		boolean rtn = sDAO.add(sDTO);
		return rtn;
	}
}
