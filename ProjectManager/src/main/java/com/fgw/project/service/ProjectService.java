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
import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Project;
import com.fgw.project.repository.IGroupRepository;
import com.fgw.project.repository.IProjectRepository;
import com.fgw.project.util.RetKit;

/**
 * 项目管理 服务
 * @author yhj
 * @date 2020年12月21日
 */
@Service
public class ProjectService {

	@Autowired
	private IProjectRepository projectR;

	public RetKit getPorjects(Integer orgId) {
		List<Project> gs = projectR.findAllByOrgIdAndStatus(orgId, 1);
		return RetKit.okData(gs);
	}

	public RetKit addProject(String param) {
		JSONObject jb = JSONObject.parseObject(param);
		String name = jb.getString("name");
		Integer orgId = jb.getInteger("orgId");
		Integer industryCategory = jb.getInteger("industryCategory");
		String content = jb.getString("name");
		String number = jb.getString("number");
		Integer maturity = jb.getInteger("maturity");
		Date dockingDate = jb.getDate("dockingDate");
		String leader = jb.getString("leader");
		String leadenter = jb.getString("leadenter");
		String coordinate = jb.getString("coordinate");
		String taskPrefix = jb.getString("taskPrefix");
		String visibleRange = jb.getString("visibleRange");
		String joiners = jb.getString("joiners");
		Integer process = jb.getInteger("process");
		String remarks = jb.getString("remarks");
		String invest = jb.getString("invest");//投资情况
		Date expectedDate = jb.getDate("expectedDate");
		Date startDate = jb.getDate("startDate");
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
		String proManager = jb.getString("proManager");
		String proManagerMobile = jb.getString("proManagerMobile");
		String enterManager = jb.getString("enterManager");
		String enterManagerMobile = jb.getString("enterManagerMobile");
		String stage = jb.getString("stage");
		
		Project pro = new Project();
		pro.setName(name);
		pro.setIndustryCategory(industryCategory);
		pro.setOrgId(orgId);
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
		pro.setStartDate(startDate);
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
		pro.setStatus(1);
		
		
		
//		pro.setGroupName(groupName);
//		pro.setGroupDecript(groupDecript);
//		pro.setOrgId(orgId);
		projectR.save(pro);
		return RetKit.okData(pro.getId());
	}

	public RetKit updateGroup(String param) {
		String id = JSONObject.parseObject(param).getString("id");
		Optional<Project> g_ = projectR.findById(Integer.parseInt(id));
		if(g_.isPresent()) {
			Project g = g_.get();
			String groupName = JSONObject.parseObject(param).getString("groupName");
			String groupDecript = JSONObject.parseObject(param).getString("groupDecript");
			Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
//			g.setGroupName(groupName);
//			g.setGroupDecript(groupDecript);
			g.setOrgId(orgId);
			projectR.save(g);
			return RetKit.ok("修改成功！");
		}
		return RetKit.fail("项目不存在！");
	}

	public RetKit deleteGroup(int id) {
		projectR.deleteById(id);
		return RetKit.ok("删除成功！");
	}

	
	
}