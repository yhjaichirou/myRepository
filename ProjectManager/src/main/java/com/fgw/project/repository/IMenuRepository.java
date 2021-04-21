package com.fgw.project.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Menu;
import com.fgw.project.model.po.User;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface IMenuRepository extends JpaRepository<Menu	, Integer> {

	List<Menu> findAllByIdInAndStatus(List<Integer> ids,Integer status);
//	@Modifying
//	@Query(value = "delete from base_user where ding_userid=:dingUserid",nativeQuery = true)
//	int deleteUsers(@Param("dingUserid") String dingUserid);

	List<Menu> findAllByStatus(Integer status);

	List<Menu> findAllByPidAndStatus(Integer pid, Integer status);
	
//	User findUserByName(String name);
//	
//	//系统用户登录
//	@Query(value="SELECT u.id,u.name as userName,r.id as selfRoleId, r.system_role as roleId,r.role_name as roleName,r.role_primary as rolePrimary,u.pwd as password,"
//			+ " u.branch_id as branchId,u.remark_name as remarkName  "
//			+ " FROM base_user u"
//			+ " LEFT JOIN base_user_role ur on u.id = ur.user_id"
//			+ " LEFT JOIN base_role r on r.id = ur.role_id"
//			+ " WHERE u.name=:name AND u.enterprise_id=0  AND u.branch_id=0 AND u.org_id is null ",nativeQuery=true)
//	Map<String, Object> findAdminUser(@Param("name")String name);
}
