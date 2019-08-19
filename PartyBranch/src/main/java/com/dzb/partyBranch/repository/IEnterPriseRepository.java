package com.dzb.partyBranch.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dzb.partyBranch.model.po.EnterPrise;

/**
 * 党建
 * @author yhj
 * @date 2019年8月7日
 */
@Repository
public interface IEnterPriseRepository extends JpaRepository<EnterPrise, Integer>,JpaSpecificationExecutor<EnterPrise> {
	
	List<EnterPrise> findAll();
	
	List<EnterPrise> findByTypeId(Integer typeId);
	List<EnterPrise> findByAreaId(Integer AreaId);
	
	@Query(value="SELECT * FROM part_enterprise WHERE type_id=:typeId and area_id=:areaId",nativeQuery=true)
	List<EnterPrise> getEnterPrises(@Param("typeId")Integer typeId,@Param("areaId")Integer areaId);
	
	
	//分页管理
	@Query(value="SELECT en.*,ty.prop_name,ar.area_name as areaName,ar.primaryall FROM part_enterprise en left join part_type ty on ty.id = en.type_id "
			+ " left join part_area ar on ar.id = en.area_id  WHERE ty.id=:typeId AND ar.id=:areaId limit :start,:end  " ,nativeQuery=true)
	List<Map<String, Object>> getPageData(@Param("typeId")Integer typeId,@Param("areaId")Integer areaId,@Param("start")Integer start,@Param("end")Integer end);
	@Query(value="SELECT en.*,ty.prop_name,ar.area_name as areaName,ar.primaryall FROM part_enterprise en left join part_type ty on ty.id = en.type_id "
			+ " left join part_area ar on ar.id = en.area_id  limit :start,:end  " ,nativeQuery=true)
	List<Map<String, Object>> getPageData(@Param("start")Integer start,@Param("end")Integer end);
	
	@Query(value="SELECT count(*) FROM part_enterprise en left join part_type ty on ty.id = en.type_id "
			+ " left join part_area ar on ar.id = en.area_id   WHERE ty.id=:typeId AND ar.id=:areaId ",nativeQuery=true)
	Integer getPageCount(@Param("typeId")Integer typeId,@Param("areaId")Integer areaId);
	@Query(value="SELECT count(*) FROM part_enterprise en left join part_type ty on ty.id = en.type_id "
			+ " left join part_area ar on ar.id = en.area_id  ",nativeQuery=true)
	Integer getPageCount();
	
	
}
