package com.dzb.partyBranch.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.dzb.partyBranch.model.po.Area;
import com.dzb.partyBranch.model.po.EnterPrise;
import com.dzb.partyBranch.model.po.PartyBranch;
import com.dzb.partyBranch.model.po.Type;

/**
 * 党建服务
 * @author yhj
 *
 */
public interface IConstructionService {
	
	List<Area> getAreas();
	
	List<Type> getTypes();
	List<EnterPrise> getEnters();
	
	
	List<EnterPrise> getEnterPrises(Integer areaId, Integer typeId);
	
	Area getAreaById(Integer areaId);
	
	Map<String, Object> getEnterBranchList(Integer pn, Integer ps, Sort sort, Integer enterId);

}
