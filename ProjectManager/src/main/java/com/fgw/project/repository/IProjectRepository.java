package com.fgw.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Project;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface IProjectRepository extends JpaRepository<Project , Integer> {

	List<Project> findAllByOrgIdAndStatus(Integer orgId,Integer status);

}
