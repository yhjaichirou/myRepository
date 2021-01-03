package com.fgw.project.constant;

/**
 * 
 * @author yhj
 * @date 2020年12月24日
 */
public enum TaskPriorityEnum {

	ONE(1,"一级"),SECOND(2,"二级"),THREE(3,"三级");
	
	private Integer id;
	private String text;
	
	TaskPriorityEnum(Integer id ,String text){
		this.setId(id);
		this.setText(text);
	}

	// 遍历使用
	public static TaskPriorityEnum getByValue(Integer id) {
		for (TaskPriorityEnum tarEnum : values()) {
			if (tarEnum.getId().equals(id)) {
				return tarEnum;
			}
		}
		return null;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
