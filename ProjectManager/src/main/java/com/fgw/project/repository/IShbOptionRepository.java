package com.fgw.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.ShbOption;


/**
 * 审核表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface IShbOptionRepository extends JpaRepository<ShbOption , Integer> {


}
