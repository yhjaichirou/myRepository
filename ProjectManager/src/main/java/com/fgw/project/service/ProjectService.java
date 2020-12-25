package com.fgw.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.ProjectStatusEnum;
import com.fgw.project.model.po.Category;
import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.Project;
import com.fgw.project.model.po.Task;
import com.fgw.project.model.vo.ProjectVo;
import com.fgw.project.repository.ICategoryRepository;
import com.fgw.project.repository.IGroupRepository;
import com.fgw.project.repository.IOrgRepository;
import com.fgw.project.repository.IPeopleRepository;
import com.fgw.project.repository.IProjectRepository;
import com.fgw.project.repository.ITaskRepository;
import com.fgw.project.util.BeanKit;
import com.fgw.project.util.MDateUtil;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;

/**
 * 项目管理 服务
 * @author yhj
 * @date 2020年12月21日
 */
@Service
public class ProjectService {

	@Autowired
	private IProjectRepository projectR;
	@Autowired
	private IOrgRepository orgR;
	@Autowired
	private ICategoryRepository categoryR;
	@Autowired
	private IPeopleRepository peopleR;
	@Autowired
	private ITaskRepository taskR;
	

	public RetKit getAllProject(Integer orgId,String status,String search) {
		List<Map<String,Object>> gs = new ArrayList<>();
		Integer statusS = Integer.parseInt(status);
		if(StrKit.notBlank(status) && (Integer.parseInt(status))!=0 && StrKit.notBlank(search)) {
			gs = projectR.getAllProjectOfOrgIdAndSearch(orgId, statusS,search);
		}else if(StrKit.notBlank(status) && (Integer.parseInt(status))!=0 && StrKit.isBlank(search)){
			gs = projectR.getAllProjectOfOrgIdAndStatus(orgId, statusS);
		}else if((StrKit.isBlank(status) || (Integer.parseInt(status))==0 ) && StrKit.notBlank(search)){
			gs = projectR.getAllProjectOfOrgIdAndSearch(orgId,search);
		}else {
			gs = projectR.getAllProjectOfOrgId(orgId);
		}
		try {
			List<ProjectVo> pvs = BeanKit.changeToListBean(gs, ProjectVo.class);
			pvs = pvs.stream().map((ProjectVo pv)->{
				pv.setCompleteDateStr(pv.getCompleteDate()==null?"":MDateUtil.dateToString(pv.getCompleteDate(), MDateUtil.formatDate));
				pv.setDockingDateStr(pv.getDockingDate()==null?"":MDateUtil.dateToString(pv.getDockingDate(), MDateUtil.formatDate));
				pv.setExpectedDateStr(pv.getExpectedDate()==null?"":MDateUtil.dateToString(pv.getExpectedDate(), MDateUtil.formatDate));
				pv.setStartDateStr(pv.getStartDate()==null?"":MDateUtil.dateToString(pv.getStartDate(), MDateUtil.formatDate));
				return pv;
			}).collect(Collectors.toList());
			return RetKit.okData(pvs);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RetKit.fail("获取失败！");
	}
	
	public RetKit getProject(String projectId) {
		Map<String,Object> gs = projectR.getProjectById(Integer.parseInt(projectId));
		try {
			ProjectVo pv = BeanKit.changeRecordToBean(gs, ProjectVo.class);
			pv.setCompleteDateStr(pv.getCompleteDate()==null?"":MDateUtil.dateToString(pv.getCompleteDate(), MDateUtil.formatDate));
			pv.setDockingDateStr(pv.getDockingDate()==null?"":MDateUtil.dateToString(pv.getDockingDate(), MDateUtil.formatDate));
			pv.setExpectedDateStr(pv.getExpectedDate()==null?"":MDateUtil.dateToString(pv.getExpectedDate(), MDateUtil.formatDate));
			pv.setStartDateStr(pv.getStartDate()==null?"":MDateUtil.dateToString(pv.getStartDate(), MDateUtil.formatDate));
			return RetKit.okData(pv);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RetKit.okData(gs);
	}
	
	public RetKit clickUpdateStatus(String projectId) {
		Map<String,Object> gs = projectR.getProjectById(Integer.parseInt(projectId));
		return RetKit.okData(gs);
	}

	public RetKit addProject(String param) {
		JSONObject jb = JSONObject.parseObject(param);
		Integer id = jb.getInteger("id");
		String name = jb.getString("name");
		Integer orgId = jb.getInteger("orgId");
		Integer industryCategory = jb.getInteger("industryCategory");
		String content = jb.getString("name");
		String number = jb.getString("number");
		Integer maturity = jb.getInteger("maturity");
		Date dockingDate = MDateUtil.stringToDate(jb.getString("dockingDate"), MDateUtil.formatDate);
		Integer leader = jb.getInteger("leader");
		Integer leadenter = jb.getInteger("leadenter");
		Integer coordinate = jb.getInteger("coordinate");
		String taskPrefix = jb.getString("taskPrefix");
		String visibleRange = jb.getString("visibleRange");
		String joiners = jb.getString("joiners");
		Integer process = jb.getInteger("process");
		String remarks = jb.getString("remarks");
		String invest = jb.getString("invest");//投资情况
		Date expectedDate = MDateUtil.stringToDate(jb.getString("expectedDate"), MDateUtil.formatDate);
		String approveCode = jb.getString("approveCode");
		
		Integer lxIsComapprove = jb.getInteger("lxIsComapprove");
		Integer lxHandleLevel = jb.getInteger("lxHandleLevel");
		Integer lxIsSendappdepart = jb.getInteger("lxIsSendappdepart");
		Integer ydcardIsHascard = jb.getInteger("ydcardIsHascard");
		Integer ydcardHandleLevel = jb.getInteger("ydcardHandleLevel");
		Integer ydcardIsSendappdepart = jb.getInteger("ydcardIsSendappdepart");
		Integer energyHandleLevel = jb.getInteger("energyHandleLevel");
		Integer energyIsCensor = jb.getInteger("energyIsCensor");
		Integer energyIsSendappdepart = jb.getInteger("energyIsSendappdepart");
		Integer lcHandleLevel = jb.getInteger("lcHandleLevel");
		Integer lcIsBl = jb.getInteger("lcIsBl");
		Integer lcIsSendappdepart = jb.getInteger("lcIsSendappdepart");
		Integer envirHandleLevel = jb.getInteger("envirHandleLevel");
		Integer envirIsBl = jb.getInteger("envirIsBl");
		Integer envirIsSendappdepart = jb.getInteger("envirIsSendappdepart");
		Integer sgHandleLevel = jb.getInteger("sgHandleLevel");
		Integer sgIsBl = jb.getInteger("sgIsBl");
		Integer sgIsSendappdepart = jb.getInteger("sgIsSendappdepart");
		Integer xfHandleLevel = jb.getInteger("xfHandleLevel");
		Integer xfIsBl = jb.getInteger("xfIsBl");
		Integer xfIsSendappdepart = jb.getInteger("xfIsSendappdepart");
		Integer rfHandleLevel = jb.getInteger("rfHandleLevel");
		Integer rfIsBl = jb.getInteger("rfIsBl");
		Integer rfIsSendappdepart = jb.getInteger("rfIsSendappdepart");
		String otherBl = jb.getString("otherBl");
		String diffAndProblem = jb.getString("diffAndProblem");
		Integer proManager = jb.getInteger("proManager");
		String proManagerMobile = jb.getString("proManagerMobile");
		Integer enterManager = jb.getInteger("enterManager");
		String enterManagerMobile = jb.getString("enterManagerMobile");
		String stage = jb.getString("stage");
		
		Project pro = new Project();
		if(id!=null) {
			Optional<Project> pro_ = projectR.findById(id);
			if(pro_.isPresent()) {
				pro = pro_.get();
			}
		}else {
			pro.setOrgId(orgId);
			pro.setStartDate(new Date());
		}
		pro.setName(name);
		pro.setIndustryCategory(industryCategory);
		pro.setContent(content);
		pro.setNumber(number);
		pro.setMaturity(maturity);
		pro.setDockingDate(dockingDate);
		pro.setLeader(leader);
		pro.setLeadenter(leadenter);
		pro.setCoordinate(coordinate);
		pro.setTaskPrefix(taskPrefix);
		pro.setVisibleRange(visibleRange);
		pro.setJoiners(joiners);
		pro.setProcess(process);
		pro.setRemarks(remarks);
		pro.setInvest(invest);
		pro.setExpectedDate(expectedDate);
		pro.setApproveCode(approveCode);
		
		pro.setLxIsComapprove(lxIsComapprove);
		pro.setLxHandleLevel(lxHandleLevel);
		pro.setLxIsSendappdepart(lxIsSendappdepart);
		
		pro.setYdcardIsHascard(ydcardIsHascard);
		pro.setYdcardHandleLevel(ydcardHandleLevel);
		pro.setYdcardIsSendappdepart(ydcardIsSendappdepart);
		
		pro.setEnergyHandleLevel(energyHandleLevel);
		pro.setEnergyIsCensor(energyIsCensor);
		pro.setEnergyIsSendappdepart(energyIsSendappdepart);
		
		pro.setLcHandleLevel(lcHandleLevel);
		pro.setLcIsBl(lcIsBl);
		pro.setLcIsSendappdepart(lcIsSendappdepart);
		
		pro.setEnvirHandleLevel(envirHandleLevel);
		pro.setEnvirIsBl(envirIsBl);
		pro.setEnvirIsSendappdepart(envirIsSendappdepart);
		
		pro.setSgHandleLevel(sgHandleLevel);
		pro.setSgIsBl(sgIsBl);
		pro.setSgIsSendappdepart(sgIsSendappdepart);
		
		pro.setXfHandleLevel(xfHandleLevel);
		pro.setXfIsBl(xfIsBl);
		pro.setXfIsSendappdepart(xfIsSendappdepart);
		
		pro.setRfHandleLevel(rfHandleLevel);
		pro.setRfIsBl(rfIsBl);
		pro.setRfIsSendappdepart(rfIsSendappdepart);
		
		pro.setOtherBl(otherBl);
		pro.setDiffAndProblem(diffAndProblem);
		pro.setProManager(proManager);
		pro.setProManagerMobile(proManagerMobile);
		pro.setEnterManager(enterManager);
		pro.setEnterManagerMobile(enterManagerMobile);
		pro.setStage(stage);
		pro.setStatus(ProjectStatusEnum.NEW.getId());
		
		
		
//		pro.setGroupName(groupName);
//		pro.setGroupDecript(groupDecript);
//		pro.setOrgId(orgId);
		projectR.save(pro);
		return RetKit.okData(pro.getId());
	}

//	public RetKit updateGroup(String param) {
//		String id = JSONObject.parseObject(param).getString("id");
//		Optional<Project> g_ = projectR.findById(Integer.parseInt(id));
//		if(g_.isPresent()) {
//			Project g = g_.get();
//			String groupName = JSONObject.parseObject(param).getString("groupName");
//			String groupDecript = JSONObject.parseObject(param).getString("groupDecript");
//			Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
////			g.setGroupName(groupName);
////			g.setGroupDecript(groupDecript);
//			g.setOrgId(orgId);
//			projectR.save(g);
//			return RetKit.ok("修改成功！");
//		}
//		return RetKit.fail("项目不存在！");
//	}

	public RetKit deleteGroup(int id) {
		projectR.deleteById(id);
		return RetKit.ok("删除成功！");
	}

	public RetKit getAllFormParam(Integer orgId) {
		if(orgId ==null) {
			return RetKit.fail("获取表单参数失败！");
		}
		Map<String,Object> rt = new HashMap<>();
		List<Category> cs = categoryR.findAllByStatus(1);
		List<People> ps = peopleR.findAllByOrgId(orgId);
		List<Map<String, Object>> qyList = peopleR.getAllPeopleOfEnter(orgId);
		List<Org> orgs = orgR.findAllByStatus(1);
		orgs = orgs.stream().filter((Org o)-> !o.getId().equals(orgId)).collect(Collectors.toList());
		rt.put("categorys", cs);
		rt.put("peoples", ps);
		rt.put("orgs", orgs);
		rt.put("qys", qyList);
		
		return RetKit.okData(rt);
	}

	public RetKit getJoiners(String orgIds) {
		if(StrKit.isBlank(orgIds)) {
			return RetKit.fail("获取表单参数失败！");
		}
		String[] ids = orgIds.split(",");
		List<Integer> orgIdList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			orgIdList.add(Integer.parseInt(ids[i]));
		}
		List<People> ps = peopleR.findAllByOrgIdIn(orgIdList);
		return RetKit.okData(ps);
	}

	public RetKit authProject(Integer projectId) {
		Optional<Project> pr_ = projectR.findById(projectId);
		if(pr_.isPresent()) {
			List<Task> tasks = taskR.findAllByProId(projectId);
			if(tasks.size()<=0) {
				return RetKit.fail("该项目未添加任何任务项，无法提交审核！");
			}
			Project p = pr_.get();
			p.setStatus(ProjectStatusEnum.APPROVAL.getId());
			projectR.save(p);
			return RetKit.ok("提交审核成功！");
		}
		return RetKit.fail("提交失败，项目不存在！");
	}

	public RetKit deleteProject(Integer projectId) {
		Optional<Project> pr_ = projectR.findById(projectId);
		if(pr_.isPresent()) {
			Project p = pr_.get();
			if(p.getStatus()!=ProjectStatusEnum.NEW.getId()) {
				return RetKit.fail("项目处于不可删除状态！");
			}
			projectR.deleteById(projectId);
			return RetKit.ok("删除成功！");
		}
		return RetKit.fail("提交失败，项目不存在！");
	}



	
	
}
