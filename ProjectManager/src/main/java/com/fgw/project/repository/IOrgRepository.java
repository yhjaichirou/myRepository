package com.fgw.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.People;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface IOrgRepository extends JpaRepository<Org , Integer> {

	List<Org> findAllByStatus(Integer status);

	@Query(value="SELECT * FROM org "
			+ " WHERE id=:id ",nativeQuery=true)
	Org getById(@Param("id")Integer id);


	List<Org> findAllByPidAndProperty(Integer orgId, Integer property);

	List<Org> findAllByPid(Integer orgId);

	Org findByNameAndPropertyAndType(String name, Integer property, Integer type);

	List<Org> findAllByStatusAndPropertyAndPid(Integer status, Integer roleId, Integer currOrgId);


}
