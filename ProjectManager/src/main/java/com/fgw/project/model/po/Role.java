package com.fgw.project.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_role")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String roleName;
	private String rolePrimary;
	private String roleDescribe;
	private String menus;
	private Integer status;
	
	@Column(name = "role_name")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "role_primary")
	public String getRolePrimary() {
		return rolePrimary;
	}

	public void setRolePrimary(String rolePrimary) {
		this.rolePrimary = rolePrimary;
	}

	@Column(name = "role_describe")
	public String getRoleDescribe() {
		return roleDescribe;
	}

	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}

	@Column(name = "menus")
	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	

}
