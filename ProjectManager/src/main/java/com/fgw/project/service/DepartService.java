package com.fgw.project.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.OrgPropertyEnum;
import com.fgw.project.interceptor.TokenInterceptor;
import com.fgw.project.model.po.Industry;
import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.OrgType;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.User;
import com.fgw.project.model.vo.IndustryVo;
import com.fgw.project.model.vo.MenuVo;
import com.fgw.project.model.vo.OrgVo;
import com.fgw.project.repository.IIndustryRepository;
import com.fgw.project.repository.IOrgRepository;
import com.fgw.project.repository.IOrgTypeRepository;
import com.fgw.project.repository.IPeopleRepository;
import com.fgw.project.repository.IUserRepository;
import com.fgw.project.util.BeanKit;
import com.fgw.project.util.MakeMD5;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;


@Service
public class DepartService {
	private Log logger = LogFactory.getLog(TokenInterceptor.class);
	@Autowired
	private IOrgRepository orgR;
	@Autowired
	private IUserRepository userR;
	@Autowired
	private IIndustryRepository industryR;
	@Autowired
	private IOrgRepository departR;
	@Autowired
	private IPeopleRepository peopleR;
	@Resource
	private CommonService comService;
	

	public RetKit getOrgtypes(Integer orgId) {
		List<Industry> o_ = new ArrayList<>();
		if(orgId != null) {
			Org o = orgR.getById(orgId);
			if(o.getProperty().equals(OrgPropertyEnum.FGW.getId())) {
				o_ = industryR.findAllByStatus(1);
			}else {
				//获取该组织顶级行业类型
				List<Industry> ins= industryR.findAllByStatus(1);
				Industry ii = industryR.findById(o.getType()).get();
				List<Integer> ids = comService.getIndustryParent(ii, ins);//oo.getType()
				Integer parentId0 = 0;
				if(ids ==null ||ids.size()<=0) {
					parentId0 = o.getType();
				}else {
					parentId0 = ids.get(0);
				}
				o_.add(industryR.findById(parentId0).get());
			}
		}else {
			o_ = industryR.findAllByStatus(1);
		}
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
	
	public RetKit getDepartList(Integer orgId,Integer pn,Integer ps,String searchContent,Integer searchStatus) {
		List<Org> gs = new ArrayList<>();
		if(orgId == null) {
			gs = departR.findAllByStatus(1);
		}else {
			Optional<Org> o_ = departR.findById(orgId);
			if(o_.isPresent()) {
				Org o = o_.get();
				if(o.getProperty() == OrgPropertyEnum.FGW.getId()) {//发改委
					if(StrKit.isBlank(searchContent)) {
						gs = departR.findAllByStatus(1);
					}else{
						gs = departR.findAllByStatusAndNameLike(1,searchContent);
					}
//					if(StrKit.isBlank(searchContent)) {
//						gs = departR.findAllByPid(orgId);
//					}else{
//						gs = departR.findAllByPidAndNameLike(orgId,searchContent);
//					}
				}else if(o.getProperty() == OrgPropertyEnum.DEPART.getId()) {//部门
					if(StrKit.isBlank(searchContent)) {
						gs = departR.findAllByPidAndProperty(orgId,OrgPropertyEnum.QY.getId());
					}else{
						gs = departR.findAllByPidAndPropertyAndNameLike(orgId,OrgPropertyEnum.QY.getId(),searchContent);
					}
				}else {
					
				}
			}else {
				gs = departR.findAllByStatus(1);
			}
		}
		List<OrgVo> ov = BeanKit.copyBeanList(gs, OrgVo.class);
		final List<Industry> ins= industryR.findAllByStatus(1);
		ov = ov.stream().map((OrgVo oo)->{
			oo.setPropertyStr(OrgPropertyEnum.getByValue(oo.getProperty()).getText());
			Industry ii = industryR.findById(oo.getType()).get();
			List<Integer> ids = comService.getIndustryParent(ii, ins);//oo.getType()
			ids.add(ii.getId());
			oo.setTypeArr(ids);
			oo.setTypeStr(ii.getName());
			
			return oo;
		}).collect(Collectors.toList());
		return RetKit.okData(ov);
	}

	@Transactional
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
		String typeArr = obj.getString("typeArr");
		Integer type = 0;
		if(StrKit.notBlank(typeArr)) {
			List<Integer> types = JSONObject.parseArray(typeArr, Integer.class);
			type = types.get(types.size()-1);
		}else {
			return RetKit.fail("请选择行业类型！");
		}
		Org oldo = departR.findByNameAndPropertyAndType(name,property,type);
		Org depart = new Org();
		if(id !=null) {
			Optional<Org> o_ = departR.findById(id);
			if(o_.isPresent()) {
				depart = o_.get();
				//删除旧的 管理员
				User oldUser = userR.findByAccount(depart.getManagerMobile());
				userR.delete(oldUser);
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
		depart.setLevel(level==null?1:level);
		depart.setProperty(property==null?OrgPropertyEnum.QY.getId():property);//如果为空 当前登录为部门 -只能为是企业属性
		depart.setType(type);
		
		departR.save(depart);
		//生成管理员
		User user = new User();
		user.setAccount(managerMobile);
		user.setIsAdmin(1);
		user.setName(manager);
		user.setOrgId(depart.getId());
		try {
			user.setPassword(MakeMD5.makeMD5(new String(MakeMD5.md5DefaultSource.getBytes("UTF-8"), "UTF-8")).toLowerCase());
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("单位管理员密码生成失败！");
			throw new RuntimeException("操作失败！");
		}
		user.setRoleId(property==null?OrgPropertyEnum.QY.getId():property);
		user.setStatus(1);
		userR.save(user);
		
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
	
	
	
	public RetKit getPeopleList(Integer orgId) {
		List<People> peoples = peopleR.findAllByOrgId(orgId);
		return RetKit.okData(peoples);
	}
	public RetKit getPeople(Integer peoId) {
		Optional<People> o_ = peopleR.findById(peoId);
		if(o_.isPresent()) {
			People o = o_.get();
			return RetKit.okData(o);
		}
		return RetKit.fail("该人员不存在！");
	}
	
	public RetKit addOrUpdateDepart(String param) {
		JSONObject obj = JSONObject.parseObject(param);
		Integer id = obj.getInteger("id");
		String name = obj.getString("name");
		String mobile = obj.getString("mobile");
		Integer orgId = obj.getInteger("orgId");
		String sex = obj.getString("sex");
		String job = obj.getString("job");
		Integer age = obj.getInteger("age");
		String idcard = obj.getString("idcard");
		Integer isLeader = obj.getInteger("isLeader");
		
		List<People> oldo = peopleR.findByNameAndMobileAndIdcard(name,mobile,idcard);
		People pel = new People();
		if(id !=null) {
			Optional<People> o_ = peopleR.findById(id);
			if(o_.isPresent()) {
				pel = o_.get();
				if(oldo!=null && oldo.size()>0 && !oldo.equals(pel.getName())) {
					return RetKit.fail("人员已经存在！");
				}
			}
		}else {
			if(oldo.size()>0) {
				return RetKit.fail("人员已经存在！");
			}
			pel.setOrgId(orgId);
			pel.setStatus(1);
		}
		pel.setName(name);
		pel.setMobile(mobile);
		pel.setJob(job);
		pel.setSex(sex);
		pel.setAge(age);
		pel.setIdcard(idcard);
		pel.setIsLeader(isLeader);
		peopleR.save(pel);
		return RetKit.okData(pel.getId());
	}
	public RetKit deletePeople(Integer peoId) {
		Optional<People> pel_ = peopleR.findById(peoId);
		if(pel_.isPresent()){
			departR.deleteById(peoId);
			return RetKit.ok("删除成功！");
		}
		return RetKit.fail("删除失败，该人员不存在！");
	}
	
	
}
