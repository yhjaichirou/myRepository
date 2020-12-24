package com.fgw.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Task;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface ITaskRepository extends JpaRepository<Task , Integer> {

	List<Task> findAllByOrgId(Integer orgId);

	List<Task> findAllByProId(Integer projectId);

}
