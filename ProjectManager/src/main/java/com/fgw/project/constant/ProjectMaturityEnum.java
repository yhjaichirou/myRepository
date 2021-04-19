package com.fgw.project.constant;

/**
 * 
 * @author yhj
 * @date 2020年12月24日
 */
public enum ProjectMaturityEnum {

	RUNPRO(1,"加快前期"),NEW(2,"新开工"),CONTINUE(3,"续建"),OVERDUE(4,"竣工");
	
	private Integer id;
	private String text;
	
	ProjectMaturityEnum(Integer id ,String text){
		this.setId(id);
		this.setText(text);
	}

	// 遍历使用
	public static ProjectMaturityEnum getByValue(Integer id) {
		for (ProjectMaturityEnum tarEnum : values()) {
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
