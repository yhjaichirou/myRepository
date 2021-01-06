package com.fgw.project.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Invest;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface IInvestRepository extends JpaRepository<Invest , Integer> {

	List<Invest> findAllByProId(Integer projectId);

//	@Query(value="SELECT "
//			+ " i.invest_date as investDate , i.invest_type as investType,i.invest_money as investMoney,i.pro_id as proId "
//			+ " FROM invest i "
//			+ " LEFT JOIN project p on p.id = i.pro_id "
//			+ " WHERE i.pro_id=:projectId ",nativeQuery=true)
//	List<Map<String,Object>> getAllByProId(@Param("projectId")Integer projectId);
}
