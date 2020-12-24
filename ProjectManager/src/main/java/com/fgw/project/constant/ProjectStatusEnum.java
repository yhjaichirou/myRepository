package com.fgw.project.constant;

/**
 * 
 * @author yhj
 * @date 2020年12月24日
 */
public enum ProjectStatusEnum {

	RUNGING(1,"进行中"),COMPLETE(2,"已完成"),DELAY(3,"已延期"),OVERDUE(4,"已逾期"),NEW(7,"新建项目"),APPROVAL(8,"审批中"),APPROVALFAIL(9,"审批失败"),APPROVALOK(10,"审批通过");
	
	private Integer id;
	private String text;
	
	ProjectStatusEnum(Integer id ,String text){
		this.setId(id);
		this.setText(text);
	}

	// 遍历使用
	public static ProjectStatusEnum getByValue(Integer id) {
		for (ProjectStatusEnum tarEnum : values()) {
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
