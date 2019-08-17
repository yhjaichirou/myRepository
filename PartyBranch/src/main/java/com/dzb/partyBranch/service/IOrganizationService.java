package com.dzb.partyBranch.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.model.po.Area;
import com.dzb.partyBranch.model.po.EnterPrise;
import com.dzb.partyBranch.model.po.Type;

/**
 * 党建服务
 * @author yhj
 *
 */
public interface IOrganizationService {
	
	Map<String, Object> getAreas(Integer pn, Integer ps, Sort sort, Integer cityId);
	
	RetKit addOrUpdate(Integer id,String isPrimary,String name);
	
	RetKit delArea(Integer id);
	
	
	Map<String, Object> getEntersList(Integer pn, Integer ps, Sort sort, Integer areaId, Integer typeId);
	
	RetKit addOrUpdateEnters(Integer id,String isPrimary,String name);
	
	RetKit delEnter(Integer id);

	

}
