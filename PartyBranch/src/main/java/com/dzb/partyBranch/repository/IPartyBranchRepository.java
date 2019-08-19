package com.dzb.partyBranch.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dzb.partyBranch.model.po.PartyBranch;

/**
 * 党支部
 * @author yhj
 * @date 2019年8月7日
 */
@Repository
public interface IPartyBranchRepository extends JpaRepository<PartyBranch, Integer>,JpaSpecificationExecutor<PartyBranch> {
	
	List<PartyBranch> findAll();
	
	List<PartyBranch> findAllByEnterpriseId(Integer enterId);
	
	@Query(value="SELECT id,part_name as partName, enterprise_id as enterpriseId  "
			+ "FROM part_branch WHERE enterprise_id=:enterpriseId",
			countQuery = "SELECT count(*)  " + 
					" FROM part_branch  WHERE enterprise_id=:enterpriseId",nativeQuery=true)
	Page<PartyBranch> getPageDataNo(@Param("enterpriseId")Integer enterpriseId,Pageable pageable);
	
	
	@Query(value=" SELECT * FROM part_branch "
			+ "where enterprise_id=:enterpriseId  limit :start,:end  " ,nativeQuery=true)
	List<PartyBranch> getPageData(@Param("enterpriseId")Integer enterpriseId,@Param("start")Integer start,@Param("end")Integer end);
	@Query(value=" SELECT * FROM part_branch  limit :start,:end  " ,nativeQuery=true)
	List<PartyBranch> getPageData(@Param("start")Integer start,@Param("end")Integer end);
	
	@Query(value=" SELECT count(*) FROM part_branch "
			+ "where enterprise_id=:enterpriseId ",nativeQuery=true)
	Integer getPageCount(@Param("enterpriseId")Integer enterpriseId);
	@Query(value=" SELECT count(*) FROM part_branch ",nativeQuery=true)
	Integer getPageCount();
	
/*	@Transactional
	@Modifying
	@Query(value="insert ignore into emp(create, modified, user_id, user_name, user_nickname, user_mail) values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true))
	void addConst(@Param("userId") Integer userId, @Param("dchip") Integer dchip);*/
	
	
/*	@Transactional
	@Modifying
	@Query(value="delete from user_of_dchip where user_id = :userId and dchip_id = :dchip",nativeQuery=true)
	void addDel(@Param("userId") Integer userId, @Param("dchip") Integer dchip);*/
	
	
}
