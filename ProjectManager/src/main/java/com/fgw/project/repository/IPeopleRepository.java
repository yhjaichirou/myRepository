package com.fgw.project.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.People;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface IPeopleRepository extends JpaRepository<People , Integer> {

	List<People> findAllByOrgId(Integer orgId);
	List<People> findAllByOrgIdAndNameContaining(Integer orgId, String search);
	List<People> findAllByOrgIdInAndStatus(List<Integer> orgIdList,Integer status);

	@Query(value="SELECT "
			+ " p.id,p.name,p.mobile,p.org_id as orgId,p.sex,p.job,p.age,p.status,p.idcard,p.openid,p.is_leader as isLeader"
			+ " FROM people p "
			+ " LEFT JOIN org o on o.id = p.org_id "
			+ " WHERE o.property = 4 AND p.status=1 ",nativeQuery=true)
	List<Map<String, Object>> getAllPeopleOfEnterNoOrg();
	@Query(value="SELECT "
			+ " p.id,p.name,p.mobile,p.org_id as orgId,p.sex,p.job,p.age,p.status,p.idcard,p.openid,p.is_leader as isLeader"
			+ " FROM people p "
			+ " LEFT JOIN org o on o.id = p.org_id "
			+ " WHERE o.property = 4 AND p.status=1 AND o.pid=:orgId ",nativeQuery=true)
	List<Map<String, Object>> getAllPeopleOfEnter(@Param("orgId")Integer orgId);

	@Query(value="SELECT * FROM people "
			+ " WHERE id=:id ",nativeQuery=true)
	People getById(@Param("id")Integer id);

	List<People> findAllByOrgIdAndStatus(Integer orgId,Integer status);
	List<People> findAllByOrgIdAndStatusAndIsLeader(Integer orgId,Integer status,Integer isLeader);

	List<People> findAllByIdIn(List<Integer> defaultPelIds);

	List<People> findByNameAndMobileAndIdcard(String name, String mobile, String idcard);

	@Query(value="SELECT p.id,p.name,p.mobile,p.status,p.org_id as orgId,p.sex,p.job,p.age,p.idcard,p.openid,p.nick_name as nickName,p.avatar_url as avatarUrl,p.gender,p.is_leader as isLeader,"
			+ " u.id as userId,u.name as userName,u.account,u.status as userStatus,u.role_id as roleId,u.group_id as groupId,"
			+ " ro.role_name as roleName,ro.role_primary as rolePrimary,ro.role_describe as roleDescribe,g.group_name as groupName "
			+ " FROM people p  "
			+ " LEFT JOIN admin_user u on u.account = p.mobile"
			+ " LEFT JOIN org o  on o.id = p.org_id"
			+ " LEFT JOIN admin_role ro on u.role_id = ro.id"
			+ " LEFT JOIN org_group g on u.group_id = g.id"
			+ " WHERE p.status=:status AND p.openid=:openid ",nativeQuery=true)
	Map<String, Object> getByStatusAndOpenid(@Param("status")Integer status,@Param("openid")String openid );

	@Query(value="SELECT p.id,p.name,p.mobile,p.status,p.org_id as orgId,p.sex,p.job,p.age,p.idcard,p.openid,p.nick_name as nickName,p.avatar_url as avatarUrl,p.gender,p.is_leader as isLeader,"
			+ " u.id as userId,u.name as userName,u.account,u.status as userStatus,u.role_id as roleId,u.group_id as groupId,"
			+ " ro.role_name as roleName,ro.role_primary as rolePrimary,ro.role_describe as roleDescribe,g.group_name as groupName "
			+ " FROM people p  "
			+ " LEFT JOIN admin_user u on u.account = p.mobile"
			+ " LEFT JOIN org o  on o.id = p.org_id"
			+ " LEFT JOIN admin_role ro on u.role_id = ro.id"
			+ " LEFT JOIN org_group g on u.group_id = g.id"
			+ " WHERE p.status=:status AND p.openid=:openid AND p.mobile=:mobile",nativeQuery=true)
	Map<String, Object> getByStatusAndOpenidAndMobile(@Param("status")Integer status,@Param("openid")String openid,@Param("mobile")String mobile);

	People findByMobile(String phone);

	People findByOpenid(String openid);
	
	List<People> findByMobileAndOrgId(String mobile, Integer orgId);

	
}
