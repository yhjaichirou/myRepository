package com.fgw.project.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Role;
import com.fgw.project.model.po.User;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface IUserRepository extends JpaRepository<User	, Integer> {
//	@Modifying
//	@Query(value = "delete from base_user where ding_userid=:dingUserid",nativeQuery = true)
//	int deleteUsers(@Param("dingUserid") String dingUserid);
	
	User findUserByName(String name);
	
	//系统用户登录
	@Query(value="SELECT u.id,u.name as userName,u.account,u.status,u.org_id as orgId, u.token,u.role_id as roleId,u.group_id as groupId,u.avater,u.is_admin as isAdmin,"
			+ "ro.role_name as roleName,ro.role_primary as rolePrimary,ro.role_describe as roleDescribe,g.group_name as groupName,o.name as orgName,o.property,o.type "
			+ " FROM admin_user u"
			+ " LEFT JOIN admin_role ro on u.role_id = ro.id"
			+ " LEFT JOIN org o on u.org_id = o.id"
			+ " LEFT JOIN org_group g on u.group_id = g.id"
			+ " WHERE u.token=:token ",nativeQuery=true)
	Map<String, Object> getAdminUserOfToken(@Param("token")String token);

	User findByAccount(String account);

	User findByToken(String token);

	User findById(int agentId);

	List<User> findAllByOrgIdAndStatus(Integer orgId, Integer status);

	List<User> findAllByAccount(String account);

	List<User> findAllByStatus(Integer status);
	
	@Query(value="SELECT u.id,u.name as userName,u.account,u.status,u.org_id as orgId, u.token,u.role_id as roleId,u.group_id as groupId,u.avater,u.is_admin as isAdmin,"
			+ "ro.role_name as roleName,ro.role_primary as rolePrimary,ro.role_describe as roleDescribe,g.group_name as groupName "
			+ " FROM admin_user u"
			+ " LEFT JOIN admin_role ro on u.role_id = ro.id"
			+ " LEFT JOIN org_group g on u.group_id = g.id"
			+ " WHERE u.status=1 ",nativeQuery=true)
	List<Map<String, Object>> getAllByStatus();
	@Query(value="SELECT u.id,u.name as userName,u.account,u.status,u.org_id as orgId, u.token,u.role_id as roleId,u.group_id as groupId,u.avater,u.is_admin as isAdmin,"
			+ "ro.role_name as roleName,ro.role_primary as rolePrimary,ro.role_describe as roleDescribe,g.group_name as groupName "
			+ " FROM admin_user u"
			+ " LEFT JOIN admin_role ro on u.role_id = ro.id"
			+ " LEFT JOIN org_group g on u.group_id = g.id"
			+ " WHERE u.status=1 AND u.org_id=:orgId ",nativeQuery=true)
	List<Map<String, Object>> getAllByOrgIdAndStatus(@Param("orgId")Integer orgId);

	List<User> findAllByRoleId(Integer roleId);

	
	
}
