package com.fgw.project.constant;

/**
 * 
 * @author yhj
 * @date 2020年12月24日
 */
public enum YjTypeEnum {

	PROJECT(1,"项目临期预警","请负责人及时完成！"),TASK(2,"任务临期预警","请负责人及时完成！"),OVERDUE(3,"项目逾期预警","项目已经逾期，请负责人处理！"),TASKOVERDUE(4,"任务逾期预警","任务已经逾期，请负责人处理！");
	
	private Integer id;
	private String text;
	private String skip;
	
	YjTypeEnum(Integer id ,String text,String skip){
		this.setId(id);
		this.setText(text);
		this.setSkip(skip);
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

	public String getSkip() {
		return skip;
	}

	public void setSkip(String skip) {
		this.skip = skip;
	}
	
	
	
}
