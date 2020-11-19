package com.yhj.pdj.model.po;

/**
 * 任务
 * @author Administrator
 *
 */
public class PdjTask {

	private Integer id;
	private String taskName;
	private String taskId;
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
