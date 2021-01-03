package com.fgw.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.OrgPropertyEnum;
import com.fgw.project.model.po.Industry;
import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.OrgType;
import com.fgw.project.model.po.People;
import com.fgw.project.model.vo.IndustryVo;
import com.fgw.project.model.vo.MenuVo;
import com.fgw.project.repository.IIndustryRepository;
import com.fgw.project.repository.IOrgRepository;
import com.fgw.project.repository.IOrgTypeRepository;
import com.fgw.project.repository.IPeopleRepository;
import com.fgw.project.util.BeanKit;
import com.fgw.project.util.RetKit;


@Service
public class DepartService {

	@Autowired
	private IIndustryRepository industryR;
	@Autowired
	private IOrgRepository departR;
	@Autowired
	private IPeopleRepository peopleR;

	public RetKit getOrgtypes() {
		List<Industry> o_ = industryR.findAll();
		List<IndustryVo> ovs = BeanKit.copyBeanList(o_, IndustryVo.class);
		List<IndustryVo> fovs = ovs.stream().filter((IndustryVo ii)->ii.getPid().equals(0)).map((IndustryVo io)->{
			io.setLabel(io.getName());
			io.setValue(io.getId());
			List<IndustryVo> ss = getOrgtypeChilds(io.getId(),ovs);
			if(ss!=null) {
				io.setChildren(ss);
			}
			return io;
		}).collect(Collectors.toList());
		return RetKit.okData(fovs);
	}
	public List<IndustryVo> getOrgtypeChilds(Integer id ,List<IndustryVo> all){
		List<IndustryVo> childList = new ArrayList<>();
		for (IndustryVo pb : all) {
			if (pb.getPid().equals(id)) {
				pb.setLabel(pb.getName());
				pb.setValue(pb.getId());
				List<IndustryVo> ss = getOrgtypeChilds(pb.getId(),all);
				if(ss!=null) {
					pb.setChildren(ss);
				}
				childList.add(pb);
			}
		}
		if (childList.size() == 0) {
			return null;
		}
		childList = childList.stream().sorted(Comparator.comparing(IndustryVo::getId)).collect(Collectors.toList());
		return childList;
	}
	
	public RetKit getDepart(Integer orgId) {
		Optional<Org> o_ = departR.findById(orgId);
		List<Org> gs = new ArrayList<>();
		if(o_.isPresent()) {
			Org o = o_.get();
			if(o.getProperty() == 1) {
				gs = departR.findAllByPid(orgId);
			}else if(o.getProperty() == 2) {
				gs = departR.findAllByPidAndProperty(orgId,OrgPropertyEnum.QY.getId());
			}else {
				
			}
		}
		return RetKit.okData(gs);
	}

	public RetKit addDepart(String param) {
		JSONObject obj = JSONObject.parseObject(param);
		Integer id = obj.getInteger("id");
		String name = obj.getString("name");
		String position = obj.getString("position");
		String manager = obj.getString("manager");
		String managerMobile = obj.getString("managerMobile");
		Integer level = obj.getInteger("level");
		Integer pid = obj.getInteger("orgId");
		Integer property = obj.getInteger("property");
		Integer type = obj.getInteger("type");
		
		Org oldo = departR.findByNameAndPropertyAndType(name,property,type);
		Org depart = new Org();
		if(id !=null) {
			Optional<Org> o_ = departR.findById(id);
			if(o_.isPresent()) {
				depart = o_.get();
				if(oldo!=null && !oldo.equals(depart.getName())) {
					return RetKit.fail("组织已经存在！");
				}
			}
		}else {
			if(oldo!=null) {
				return RetKit.fail("组织已经存在！");
			}
			depart.setPid(pid);
			depart.setCapacityValue(100);
			depart.setStatus(1);
		}
		depart.setName(name);
		depart.setPosition(position);
		depart.setManager(manager);
		depart.setManagerMobile(managerMobile);
		depart.setLevel(level);
		depart.setProperty(property);
		depart.setType(type);
		
		departR.save(depart);
		return RetKit.okData(depart.getId());
	}

	public RetKit deleteDepart(Integer id) {
		List<Org> orgs = departR.findAllByPid(id);
		if(orgs.size()>0) {
			return RetKit.fail("存在下级组织，无法删除！");
		}
		List<People> ps = peopleR.findAllByOrgId(id);
		peopleR.deleteAll(ps);
		departR.deleteById(id);
		return RetKit.ok("删除成功！");
	}
	
	
}
