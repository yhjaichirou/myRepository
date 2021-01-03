package com.fgw.project.constant;

/**
 * 
 * @author yhj
 * @date 2020年12月24日
 */
public enum TaskStageEnum {

	NOMACK(1,"无所属阶段"),LX(2,"立项阶段"),ZX(3,"执行阶段"),YS(4,"验收阶段");
	
	private Integer id;
	private String text;
	
	TaskStageEnum(Integer id ,String text){
		this.setId(id);
		this.setText(text);
	}

	// 遍历使用
	public static TaskStageEnum getByValue(Integer id) {
		for (TaskStageEnum tarEnum : values()) {
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
