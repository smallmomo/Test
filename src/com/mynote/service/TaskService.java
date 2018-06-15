package com.mynote.service;


import java.util.ArrayList;

import com.mynote.DAO.TaskDAO;
import com.mynote.DTO.TaskDTO;
import com.mynote.vo.Task;

public class TaskService {
	TaskDAO tDAO = new TaskDAO();
	
	// 获取没完成的任务
	public ArrayList<Task> getNewTask(int uId) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		ArrayList<TaskDTO> taskDs = new ArrayList<TaskDTO>();
		taskDs=tDAO.getNewTask(uId);
		for(TaskDTO taskD:taskDs){
			Task task = new Task();
			task.setT_id(taskD.getT_id());
			task.settName(taskD.getT_name());
			task.settContent(taskD.getT_content());
			task.settCreateTime(taskD.getT_createtime());
			task.setCreateBy(taskD.getT_createby());
			tasks.add(task);
		}
		return tasks;
	}

	// 获取已经完成的任务
	public ArrayList<Task> getCompleteTask(int uId) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		ArrayList<TaskDTO> taskDs = new ArrayList<TaskDTO>();
		taskDs=tDAO.getCompleteTask(uId);
		for(TaskDTO taskD:taskDs){
			Task task = new Task();
			task.settName(taskD.getT_name());
			task.setFinishBy(taskD.getT_finishby());
			task.settCompleteTime(taskD.getT_comtime());
			tasks.add(task);
		}
		return tasks;
	}

	/*
	 * 添加日程 如果成功跳到已经新增的task的task页面 如果不成功弹出添加失败，继续跳到task页面
	 */
	public boolean addTask(Task task) {
		TaskDTO taskD = new TaskDTO();
		taskD.setT_createby(task.getCreateBy());
		taskD.setT_name(task.gettName());
		taskD.setT_content(task.gettContent());
		taskD.setT_uid(task.getT_uid());
		boolean rtn=tDAO.addTask(taskD);
		return rtn;
	}

	/*
	 * 获取日程 获取单个日程
	 */
	public Task getTask(int t_id) {
		Task task = new Task();
		TaskDTO taskD = tDAO.getTaskByPrimaryKey(t_id);
		task.settName(taskD.getT_name());
		task.settContent(taskD.getT_content());
		task.settCreateTime(taskD.getT_createtime());
		task.setCreateBy(taskD.getT_createby());
		task.setT_id(taskD.getT_id());
		return task;
	}

	/*
	 * 获取置顶日程
	 */
	public Task getTopTask(int uid) {
		Task task = new Task();
		TaskDTO taskD = tDAO.getTopTask(uid);
		task.setT_id(taskD.getT_id());
		task.settName(taskD.getT_name());
		task.settContent(taskD.getT_content());
		task.settCreateTime(taskD.getT_createtime());
		task.setCreateBy(taskD.getT_createby());
		task.settFinish(String.valueOf(taskD.getT_isfinish()));
		return task;
	}

	/*
	 * 设置置顶日程
	 */
	public boolean setTopTask(int t_id) {
		boolean rtn = tDAO.setTopTask(t_id);
		return rtn;
	}
	/*
	 * 取消置顶日程
	 */
	public boolean deleteTopTask(int t_id) {
		boolean rtn = tDAO.deleteTopTask(t_id);
		return rtn;
	}
	/*
	 * 修改日程 如果成功跳到已经修改的task的task页面 如果不成功弹出修改失败，继续跳到task页面
	 */
	public boolean changeTask(Task task) {
		TaskDTO taskD = new TaskDTO();
		taskD.setT_id(task.getT_id());
		taskD.setT_name(task.gettName());
		taskD.setT_content(task.gettContent());
		taskD.setT_isfinish(Integer.valueOf(task.gettFinish()));
		taskD.setT_finishby(task.getFinishBy());
		boolean rtn = tDAO.changeTask(taskD);
		return rtn;
	}

	/*
	 * 删除日程
	 */
	public boolean deleteTask(int t_id) {
		boolean rtn = tDAO.deleteTask(t_id);
		return rtn;
	}
}
