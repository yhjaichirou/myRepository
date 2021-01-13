package com.fgw.project.constant;

/**
 * 
 * @author yhj
 * @date 2020年12月24日
 */
public enum TaskStatusEnum {

	NOMACK(0,"未分配"),NOCOM(1,"未完成"),COMPLETE(2,"已完成"),DELAY(3,"已延期"),OVERDUE(4,"已逾期"),YESMACK(5,"分配未开始");
	
	private Integer id;
	private String text;
	
	TaskStatusEnum(Integer id ,String text){
		this.setId(id);
		this.setText(text);
	}

	// 遍历使用
	public static TaskStatusEnum getByValue(Integer id) {
		for (TaskStatusEnum tarEnum : values()) {
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
