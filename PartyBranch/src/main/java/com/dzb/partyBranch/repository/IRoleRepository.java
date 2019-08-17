package com.dzb.partyBranch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dzb.partyBranch.model.po.Menu;
import com.dzb.partyBranch.model.po.Role;

/**
 * 用户表
 * @author yhj
 * @date 2018年10月24日
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role	, Integer> {
	
	
	@Query(value="SELECT m.id FROM base_menu m join base_role_menu rm on rm.menu_id = m.id WHERE rm.role_id =:roleId",nativeQuery=true)
	List<Integer> getMenusOfId(@Param("roleId")Integer roleId); 
	
}
