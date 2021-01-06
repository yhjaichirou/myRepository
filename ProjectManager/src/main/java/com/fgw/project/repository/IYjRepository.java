package com.fgw.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Yj;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface IYjRepository extends JpaRepository<Yj , Integer> {

	List<Yj> findAllByOrgId(Integer orgId);

	List<Yj> findAllByOrgIdAndStatus(Integer orgId, Integer status);


}
