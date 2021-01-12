package com.fgw.project.constant;

/**
 * 
 * @author yhj
 * @date 2020年12月24日
 */
public enum RoleEnum {

	SUPERADMIN(1,"超级管理员"),ADMIN(2,"系统管理员"),DEPART(3,"政府部门"),ENTER(4,"企业");
	
	private Integer id;
	private String text;
	
	RoleEnum(Integer id ,String text){
		this.setId(id);
		this.setText(text);
	}

	// 遍历使用
	public static RoleEnum getByValue(Integer id) {
		for (RoleEnum tarEnum : values()) {
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
