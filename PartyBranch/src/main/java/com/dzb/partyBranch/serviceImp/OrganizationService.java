package com.dzb.partyBranch.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dzb.partyBranch.kit.JpaUtils;
import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.model.po.Area;
import com.dzb.partyBranch.model.po.PartyBranch;
import com.dzb.partyBranch.repository.IAreaRepository;
import com.dzb.partyBranch.repository.IEnterPriseRepository;
import com.dzb.partyBranch.repository.IPartyBranchRepository;
import com.dzb.partyBranch.service.IOrganizationService;

@Service
public class OrganizationService implements IOrganizationService {

	@Autowired
	private IAreaRepository areaRepository;
	@Autowired
	private IEnterPriseRepository enterPriseRepository;
	@Autowired
	private IPartyBranchRepository partyBranchRepository;
	
	@Override
	public Map<String,Object> getAreas(Integer pn, Integer ps, Sort sort, Integer cityId) {
		List<Area> bps = null;
		String sortsql = JpaUtils.getSortSQL(sort);
		Integer count = null;
		if(cityId==null) {
			count = areaRepository.getPageCount();
			bps = areaRepository.getPageData(ps*pn, (pn+1)*ps);
		}else {
			count = areaRepository.getPageCount(cityId);
			bps = areaRepository.getPageData(cityId,ps*pn, (pn+1)*ps);
		}
		Map<String, Object> result = new HashMap<>();

		result.put("list", bps);
		result.put("count", count); // 总条数
		return result;
	}

	@Override
	public RetKit addOrUpdate(Integer id, String isPrimary, String name) {
		Area area = new Area();
		if(id!=null) {
			area.setId(id);
		}
		area.setAreaName(name);
		area.setPrimaryall(isPrimary==null?false:true);
		area.setCityId(1);
		try {
			areaRepository.save(area);
		} catch (Exception e) {
			RetKit.fail();
		}
		return RetKit.ok();
	}

	@Override
	public RetKit delArea(Integer id) {
		try {
			areaRepository.deleteById(id);
		} catch (Exception e) {
			RetKit.fail();
		}
		return RetKit.ok();
	}

	@Override
	public Map<String, Object> getEntersList(Integer pn, Integer ps, Sort sort, Integer areaId,Integer typeId) {
		List<Map<String,Object>> bps = null;
		String sortsql = JpaUtils.getSortSQL(sort);
		Integer count = null;
		if(areaId==null && typeId==null) {
			count = enterPriseRepository.getPageCount();
			bps = enterPriseRepository.getPageData(ps*pn, (pn+1)*ps);
		}else {
			count = enterPriseRepository.getPageCount(typeId,areaId);
			bps = enterPriseRepository.getPageData(typeId,areaId,ps*pn, (pn+1)*ps);
		}
		Map<String, Object> result = new HashMap<>();

		result.put("list", bps);
		result.put("count", count); // 总条数
		return result;
		
	}

	@Override
	public RetKit addOrUpdateEnters(Integer id, String isPrimary, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RetKit delEnter(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PartyBranch> getBranchsByEnters(Integer enterId) {
		return partyBranchRepository.findAllByEnterpriseId(enterId);
	}


	

}
