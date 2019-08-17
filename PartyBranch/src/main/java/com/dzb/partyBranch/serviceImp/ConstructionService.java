package com.dzb.partyBranch.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.dzb.partyBranch.kit.BeanKit;
import com.dzb.partyBranch.kit.JpaUtils;
import com.dzb.partyBranch.model.po.Area;
import com.dzb.partyBranch.model.po.EnterPrise;
import com.dzb.partyBranch.model.po.PartyBranch;
import com.dzb.partyBranch.model.po.Type;
import com.dzb.partyBranch.model.vo.PartyBranchVo;
import com.dzb.partyBranch.repository.IAreaRepository;
import com.dzb.partyBranch.repository.IEnterPriseRepository;
import com.dzb.partyBranch.repository.IPartyBranchRepository;
import com.dzb.partyBranch.repository.ITypeRepository;
import com.dzb.partyBranch.service.IConstructionService;

@Service
public class ConstructionService implements IConstructionService {

	@Autowired
	private IAreaRepository areaRepository;
	@Autowired
	private ITypeRepository typeRepository;
	@Autowired
	private IEnterPriseRepository enterPriseRepository;
	@Autowired
	private IPartyBranchRepository partyBranchRepository;

	@Override
	public List<Area> getAreas() {
		return areaRepository.findAll();
	}

	@Override
	public List<Type> getTypes() {
		return typeRepository.findAll();
	}
	
	@Override
	public List<EnterPrise> getEnters() {
		return enterPriseRepository.findAll();
	}

	@Override
	public List<EnterPrise> getEnterPrises(Integer areaId, Integer typeId) {
		List<EnterPrise> enters = new ArrayList<>();
		if(areaId==null) {
			enters = enterPriseRepository.findByTypeId(typeId);
		}
		else if(typeId==null) {
			enters = enterPriseRepository.findByAreaId(areaId);
		}else {
			enters = enterPriseRepository.getEnterPrises(typeId, areaId);
		}
		return enters;
	}

	@Override
	public Area getAreaById(Integer areaId) {
		Optional<Area> _area = areaRepository.findById(areaId);
		return _area.get();
	}

	@Override
	public Map<String, Object> getEnterBranchList(Integer pn, Integer ps, Sort sort, Integer enterId) {
		List<PartyBranch> bps = null;
		String sortsql = JpaUtils.getSortSQL(sort);
		Integer count = null;
		if(enterId==null) {
			count = partyBranchRepository.getPageCount();
			bps = partyBranchRepository.getPageData(ps*pn, (pn+1)*ps);
		}else {
			count = partyBranchRepository.getPageCount(enterId);
			bps = partyBranchRepository.getPageData(enterId,ps*pn, (pn+1)*ps);
		}
		
		
		Map<String, Object> result = new HashMap<>();

		result.put("list", bps);
		result.put("count", count); // 总条数
		//
		return result;
	}

}
