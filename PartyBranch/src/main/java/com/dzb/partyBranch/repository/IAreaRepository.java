package com.dzb.partyBranch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dzb.partyBranch.model.po.Area;
import com.dzb.partyBranch.model.po.PartyBranch;

/**
 * 党建
 * @author yhj
 * @date 2019年8月7日
 */
@Repository
public interface IAreaRepository extends JpaRepository<Area, Integer> {
	
	List<Area> findAll();
	
	Optional<Area> findById(Integer id);
	
	
	@Query(value=" SELECT * FROM part_area where city_id=:cityId  limit :start,:end  " ,nativeQuery=true)
	List<Area> getPageData(@Param("cityId")Integer cityId,@Param("start")Integer start,@Param("end")Integer end);
	@Query(value=" SELECT * FROM part_area  limit :start,:end  " ,nativeQuery=true)
	List<Area> getPageData(@Param("start")Integer start,@Param("end")Integer end);
	
	@Query(value=" SELECT count(*) FROM part_area "
			+ "where city_id=:cityId ",nativeQuery=true)
	Integer getPageCount(@Param("cityId")Integer cityId);
	@Query(value=" SELECT count(*) FROM part_area ",nativeQuery=true)
	Integer getPageCount();
	
	
}
