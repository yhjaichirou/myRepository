package com.fgw.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Dispatch;

/**
 * 调度表
 * @author yhj
 * @date 2021年9月7日
 */
@Repository
public interface IDispatchRepository extends JpaRepository<Dispatch , Integer> {

	List<Dispatch> findAllByStatus(Integer status);

	List<Dispatch> findAllByProIdAndStatus(Integer projectId, Integer status);


}
