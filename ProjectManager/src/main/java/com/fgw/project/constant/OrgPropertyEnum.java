package com.fgw.project.constant;

/**
 * 
 * @author yhj
 * @date 2020年12月24日
 */
public enum OrgPropertyEnum {

	FGW(1,"发改委"),DEPART(1,"政府部门"),QY(2,"企业");
	
	private Integer id;
	private String text;
	
	OrgPropertyEnum(Integer id ,String text){
		this.setId(id);
		this.setText(text);
	}

	// 遍历使用
	public static OrgPropertyEnum getByValue(Integer id) {
		for (OrgPropertyEnum tarEnum : values()) {
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
