package com.dzb.partyBranch.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IPagenationRepositoryCustom <T, ID extends Serializable>  extends JpaRepository<T, ID>{
	/**
	 * 自定义nativesql才可以调用此分页方法动态写sql
	 * @param querySql 查询数据的sql
	 * @param countSql 统计总记录数sql
	 * @param params 参数按key
	 * @param sort 排序字段
	 * @param startPage 起始页
	 * @param pageSize 页大小
	 * @return
	 */
	Map<String, Object> getPageDate(StringBuffer querySql, StringBuffer countSql, Map<String, Object> params, Sort sort,
			Integer startPage, Integer pageSize) ;
	
//	Page<Map<String, Object>> getPageData(String keys,String fromStart,int ps ,int pn,Sort sort,Map<String, Object> param,String fixConditoin,String countDistinct);
}
