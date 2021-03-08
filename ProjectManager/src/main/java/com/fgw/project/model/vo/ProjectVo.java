package com.fgw.project.model.vo;

import java.util.Date;
import java.util.List;

import com.fgw.project.model.po.Invest;

public class ProjectVo<Industry> {

	private Integer id;
	private String name;
	private Integer industryCategory;//产业类型
	private String content;
	private String number;//项目编码
	private Integer maturity;//项目成熟度
	private Date dockingDate;//对接日期
	private Integer leader;//牵头领导
	private Integer leadenter;//牵头单位
	private Integer coordinate;//协调负责人
	private String taskPrefix;//项目划分任务前缀   项目代号
	private String visibleRange;//显示范围
	private String joiners;//项目参加人员
	private Integer process;//项目进展程度
	private String remarks;//备注
	private String invest;//投资情况
	private Date expectedDate;//预计完成时间
	private Date startDate;//开始时间
	private String approveCode;//项目审批监管平台代码
	private String image;
	
	private Integer lxIsComapprove;
	private Integer lxHandleLevel;
	private Integer lxIsSendappdepart;
	private Integer ydcardIsHascard;
	private Integer ydcardHandleLevel;
	private Integer ydcardIsSendappdepart;
	private Integer energyIsCensor;
	private Integer energyHandleLevel;
	private Integer energyIsSendappdepart;
	private Integer lcIsBl;
	private Integer lcHandleLevel;
	private Integer lcIsSendappdepart;
	private Integer tdIsBl;
	private Integer tdHandleLevel;
	private Integer tdIsSendappdepart;
	private Integer envirIsBl;
	private Integer envirHandleLevel;
	private Integer envirIsSendappdepart;
	private Integer sgIsBl;
	private Integer sgHandleLevel;
	private Integer sgIsSendappdepart;
	private Integer xfIsBl;
	private Integer xfHandleLevel;
	private Integer xfIsSendappdepart;
	private Integer rfIsBl;
	private Integer rfHandleLevel;
	private Integer rfIsSendappdepart;
	
	private String otherBl;//其他需要解决的手续问题
	private String diffAndProblem;//存在的困难和问题
	private Integer proManager;//项目主管部门联系人
	private String proManagerMobile;//项目主管部门联系人电话
	private Integer enterManager;
	private String enterManagerMobile;
	private String stage;//项目建设阶段
	private Integer status;//
	private Date completeDate;//完成时间
	private Integer orgId;
	private String processCondition;
	
	//外键
	private String orgName;
	private String leadenterName;
	private String categoryName;
	private String leaderName;
	private String coordinateName;
	private String proManagerName;
	private String enterManagerName;
	
	private String dockingDateStr;
	private String expectedDateStr;
	private String startDateStr;
	private String completeDateStr;
	
	private String maturityStr;
	private String visibleRangeStr;
	private String joinersStr;
	private String statusStr;
	
	private List<Integer> typeArr;
	private List<Invest> investInfos;
	private String earlyStage;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIndustryCategory() {
		return industryCategory;
	}
	public void setIndustryCategory(Integer industryCategory) {
		this.industryCategory = industryCategory;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getMaturity() {
		return maturity;
	}
	public void setMaturity(Integer maturity) {
		this.maturity = maturity;
	}
	public Date getDockingDate() {
		return dockingDate;
	}
	public void setDockingDate(Date dockingDate) {
		this.dockingDate = dockingDate;
	}
	public Integer getLeader() {
		return leader;
	}
	public void setLeader(Integer leader) {
		this.leader = leader;
	}
	public Integer getLeadenter() {
		return leadenter;
	}
	public void setLeadenter(Integer leadenter) {
		this.leadenter = leadenter;
	}
	public Integer getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Integer coordinate) {
		this.coordinate = coordinate;
	}
	public String getTaskPrefix() {
		return taskPrefix;
	}
	public void setTaskPrefix(String taskPrefix) {
		this.taskPrefix = taskPrefix;
	}
	public String getVisibleRange() {
		return visibleRange;
	}
	public void setVisibleRange(String visibleRange) {
		this.visibleRange = visibleRange;
	}
	public String getJoiners() {
		return joiners;
	}
	public void setJoiners(String joiners) {
		this.joiners = joiners;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getInvest() {
		return invest;
	}
	public void setInvest(String invest) {
		this.invest = invest;
	}
	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getApproveCode() {
		return approveCode;
	}
	public void setApproveCode(String approveCode) {
		this.approveCode = approveCode;
	}
	public Integer getLxIsComapprove() {
		return lxIsComapprove;
	}
	public void setLxIsComapprove(Integer lxIsComapprove) {
		this.lxIsComapprove = lxIsComapprove;
	}
	public Integer getLxHandleLevel() {
		return lxHandleLevel;
	}
	public void setLxHandleLevel(Integer lxHandleLevel) {
		this.lxHandleLevel = lxHandleLevel;
	}
	public Integer getLxIsSendappdepart() {
		return lxIsSendappdepart;
	}
	public void setLxIsSendappdepart(Integer lxIsSendappdepart) {
		this.lxIsSendappdepart = lxIsSendappdepart;
	}
	public Integer getYdcardIsHascard() {
		return ydcardIsHascard;
	}
	public void setYdcardIsHascard(Integer ydcardIsHascard) {
		this.ydcardIsHascard = ydcardIsHascard;
	}
	public Integer getYdcardHandleLevel() {
		return ydcardHandleLevel;
	}
	public void setYdcardHandleLevel(Integer ydcardHandleLevel) {
		this.ydcardHandleLevel = ydcardHandleLevel;
	}
	public Integer getYdcardIsSendappdepart() {
		return ydcardIsSendappdepart;
	}
	public void setYdcardIsSendappdepart(Integer ydcardIsSendappdepart) {
		this.ydcardIsSendappdepart = ydcardIsSendappdepart;
	}
	public Integer getEnergyIsCensor() {
		return energyIsCensor;
	}
	public void setEnergyIsCensor(Integer energyIsCensor) {
		this.energyIsCensor = energyIsCensor;
	}
	public Integer getEnergyHandleLevel() {
		return energyHandleLevel;
	}
	public void setEnergyHandleLevel(Integer energyHandleLevel) {
		this.energyHandleLevel = energyHandleLevel;
	}
	public Integer getEnergyIsSendappdepart() {
		return energyIsSendappdepart;
	}
	public void setEnergyIsSendappdepart(Integer energyIsSendappdepart) {
		this.energyIsSendappdepart = energyIsSendappdepart;
	}
	public Integer getLcIsBl() {
		return lcIsBl;
	}
	public void setLcIsBl(Integer lcIsBl) {
		this.lcIsBl = lcIsBl;
	}
	public Integer getLcHandleLevel() {
		return lcHandleLevel;
	}
	public void setLcHandleLevel(Integer lcHandleLevel) {
		this.lcHandleLevel = lcHandleLevel;
	}
	public Integer getLcIsSendappdepart() {
		return lcIsSendappdepart;
	}
	public void setLcIsSendappdepart(Integer lcIsSendappdepart) {
		this.lcIsSendappdepart = lcIsSendappdepart;
	}
	public Integer getEnvirIsBl() {
		return envirIsBl;
	}
	public void setEnvirIsBl(Integer envirIsBl) {
		this.envirIsBl = envirIsBl;
	}
	public Integer getEnvirHandleLevel() {
		return envirHandleLevel;
	}
	public void setEnvirHandleLevel(Integer envirHandleLevel) {
		this.envirHandleLevel = envirHandleLevel;
	}
	public Integer getEnvirIsSendappdepart() {
		return envirIsSendappdepart;
	}
	public void setEnvirIsSendappdepart(Integer envirIsSendappdepart) {
		this.envirIsSendappdepart = envirIsSendappdepart;
	}
	public Integer getSgIsBl() {
		return sgIsBl;
	}
	public void setSgIsBl(Integer sgIsBl) {
		this.sgIsBl = sgIsBl;
	}
	public Integer getSgHandleLevel() {
		return sgHandleLevel;
	}
	public void setSgHandleLevel(Integer sgHandleLevel) {
		this.sgHandleLevel = sgHandleLevel;
	}
	public Integer getSgIsSendappdepart() {
		return sgIsSendappdepart;
	}
	public void setSgIsSendappdepart(Integer sgIsSendappdepart) {
		this.sgIsSendappdepart = sgIsSendappdepart;
	}
	public Integer getXfIsBl() {
		return xfIsBl;
	}
	public void setXfIsBl(Integer xfIsBl) {
		this.xfIsBl = xfIsBl;
	}
	public Integer getXfHandleLevel() {
		return xfHandleLevel;
	}
	public void setXfHandleLevel(Integer xfHandleLevel) {
		this.xfHandleLevel = xfHandleLevel;
	}
	public Integer getXfIsSendappdepart() {
		return xfIsSendappdepart;
	}
	public void setXfIsSendappdepart(Integer xfIsSendappdepart) {
		this.xfIsSendappdepart = xfIsSendappdepart;
	}
	public Integer getRfIsBl() {
		return rfIsBl;
	}
	public void setRfIsBl(Integer rfIsBl) {
		this.rfIsBl = rfIsBl;
	}
	public Integer getRfHandleLevel() {
		return rfHandleLevel;
	}
	public void setRfHandleLevel(Integer rfHandleLevel) {
		this.rfHandleLevel = rfHandleLevel;
	}
	public Integer getRfIsSendappdepart() {
		return rfIsSendappdepart;
	}
	public void setRfIsSendappdepart(Integer rfIsSendappdepart) {
		this.rfIsSendappdepart = rfIsSendappdepart;
	}
	public String getOtherBl() {
		return otherBl;
	}
	public void setOtherBl(String otherBl) {
		this.otherBl = otherBl;
	}
	public String getDiffAndProblem() {
		return diffAndProblem;
	}
	public void setDiffAndProblem(String diffAndProblem) {
		this.diffAndProblem = diffAndProblem;
	}
	public Integer getProManager() {
		return proManager;
	}
	public void setProManager(Integer proManager) {
		this.proManager = proManager;
	}
	public String getProManagerMobile() {
		return proManagerMobile;
	}
	public void setProManagerMobile(String proManagerMobile) {
		this.proManagerMobile = proManagerMobile;
	}
	public Integer getEnterManager() {
		return enterManager;
	}
	public void setEnterManager(Integer enterManager) {
		this.enterManager = enterManager;
	}
	public String getEnterManagerMobile() {
		return enterManagerMobile;
	}
	public void setEnterManagerMobile(String enterManagerMobile) {
		this.enterManagerMobile = enterManagerMobile;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getDockingDateStr() {
		return dockingDateStr;
	}
	public void setDockingDateStr(String dockingDateStr) {
		this.dockingDateStr = dockingDateStr;
	}
	public String getExpectedDateStr() {
		return expectedDateStr;
	}
	public void setExpectedDateStr(String expectedDateStr) {
		this.expectedDateStr = expectedDateStr;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getCompleteDateStr() {
		return completeDateStr;
	}
	public void setCompleteDateStr(String completeDateStr) {
		this.completeDateStr = completeDateStr;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	public Integer getTdIsBl() {
		return tdIsBl;
	}
	public void setTdIsBl(Integer tdIsBl) {
		this.tdIsBl = tdIsBl;
	}
	public Integer getTdHandleLevel() {
		return tdHandleLevel;
	}
	public void setTdHandleLevel(Integer tdHandleLevel) {
		this.tdHandleLevel = tdHandleLevel;
	}
	public Integer getTdIsSendappdepart() {
		return tdIsSendappdepart;
	}
	public void setTdIsSendappdepart(Integer tdIsSendappdepart) {
		this.tdIsSendappdepart = tdIsSendappdepart;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getLeadenterName() {
		return leadenterName;
	}
	public void setLeadenterName(String leadenterName) {
		this.leadenterName = leadenterName;
	}
	public String getCoordinateName() {
		return coordinateName;
	}
	public void setCoordinateName(String coordinateName) {
		this.coordinateName = coordinateName;
	}
	public String getProManagerName() {
		return proManagerName;
	}
	public void setProManagerName(String proManagerName) {
		this.proManagerName = proManagerName;
	}
	public String getEnterManagerName() {
		return enterManagerName;
	}
	public void setEnterManagerName(String enterManagerName) {
		this.enterManagerName = enterManagerName;
	}
	public String getMaturityStr() {
		return maturityStr;
	}
	public void setMaturityStr(String maturityStr) {
		this.maturityStr = maturityStr;
	}
	public String getVisibleRangeStr() {
		return visibleRangeStr;
	}
	public void setVisibleRangeStr(String visibleRangeStr) {
		this.visibleRangeStr = visibleRangeStr;
	}
	public String getJoinersStr() {
		return joinersStr;
	}
	public void setJoinersStr(String joinersStr) {
		this.joinersStr = joinersStr;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public List<Integer> getTypeArr() {
		return typeArr;
	}
	public void setTypeArr(List<Integer> typeArr) {
		this.typeArr = typeArr;
	}
	public List<Invest> getInvestInfos() {
		return investInfos;
	}
	public void setInvestInfos(List<Invest> investInfos) {
		this.investInfos = investInfos;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProcessCondition() {
		return processCondition;
	}
	public void setProcessCondition(String processCondition) {
		this.processCondition = processCondition;
	}
	public String getEarlyStage() {
		return earlyStage;
	}
	public void setEarlyStage(String earlyStage) {
		this.earlyStage = earlyStage;
	}
}
