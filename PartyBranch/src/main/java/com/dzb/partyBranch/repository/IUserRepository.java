package com.dzb.partyBranch.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dzb.partyBranch.model.po.User;

/**
 * 用户表
 * @author yhj
 * @date 2018年10月24日
 */
@Repository
public interface IUserRepository extends JpaRepository<User	, Integer> {
	
	User findUserByName(String name);
	
	@Query(value="SELECT u.id,u.name as userName,r.id as roleId,r.role_name as roleName,u.pwd as password,"
			+ "u.enterprise_id as enterpriseId,enter.name as enterprisesName ,a.id as areaId  FROM base_user u"
			+ " LEFT JOIN base_user_role ur on u.id = ur.user_id"
			+ " LEFT JOIN base_role r on r.id = ur.role_id"
			+ " LEFT JOIN part_enterprise enter on enter.id = u.enterprise_id"
			+ " LEFT JOIN part_area a on a.id = enter.area_id"
			+ " WHERE u.name=:name",nativeQuery=true)
	Map<String, Object> findUser(@Param("name")String name); 
	
	@Query(value="SELECT u.id,u.name as userName,r.id as roleId,r.role_name as roleName,u.pwd as password  FROM base_user u"
			+ " LEFT JOIN base_user_role ur on u.id = ur.user_id"
			+ " LEFT JOIN base_role r on r.id = ur.role_id"
			+ " WHERE u.name=:userId",nativeQuery=true)
	Map<String, Object> getUserInfo(@Param("userId")Integer userId); 
	
	@Query(value="SELECT u.id,u.name as userName,r.id as roleId,r.role_name as roleName,u.pwd as password  FROM base_user u"
			+ " LEFT JOIN base_user_role ur on u.id = ur.user_id"
			+ " LEFT JOIN base_role r on r.id = ur.role_id"
			+ " WHERE u.name=:userId",nativeQuery=true)
	List<Map<String, Object>> getMenus(@Param("userId")Integer userId); 
	
}
