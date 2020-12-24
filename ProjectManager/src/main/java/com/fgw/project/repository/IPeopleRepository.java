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

	List<People> findAllByOrgIdIn(List<Integer> orgIdList);

	@Query(value="SELECT "
			+ " p.id,p.mobile,p.org_id as orgId,p.sex,p.job,p.age,p.status,p.openid"
			+ " FROM people p "
			+ " LEFT JOIN org o on o.id = p.org_id "
			+ " WHERE o.property = 3 AND p.status=1 AND o.pid=:orgId ",nativeQuery=true)
	List<Map<String, Object>> getAllPeopleOfEnter(@Param("orgId")Integer orgId);
}
