package com.dzb.partyBranch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dzb.partyBranch.model.po.Type;

/**
 * 党建
 * @author yhj
 * @date 2019年8月7日
 */
@Repository
public interface ITypeRepository extends JpaRepository<Type, Integer> {
	
	List<Type> findAll();
	
	
}
