package com.fgw.project.constant;

/**
 * 
 * @author yhj
 * @date 2020年12月24日
 */
public enum YjTypeEnum {

	PROJECT(1,"项目"),TASK(2,"任务"),OVERDUE(3,"项目逾期"),TASKOVERDUE(4,"任务逾期");
	
	private Integer id;
	private String text;
	
	YjTypeEnum(Integer id ,String text){
		this.setId(id);
		this.setText(text);
	}

	// 遍历使用
	public static YjTypeEnum getByValue(Integer id) {
		for (YjTypeEnum tarEnum : values()) {
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
