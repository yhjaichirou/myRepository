package com.fgw.project.model.vo;

import java.util.Date;
import java.util.List;

import com.fgw.project.model.po.Invest;

public class ProjectVo<Industry> {

	private Integer id;
	private String name;
	private Integer industryCategory;//产业类型
	private String content;
	private String number;
	private Integer maturity;//项目成熟度
	private Date dockingDate;//对接日期
	private Integer leader;//牵头领导
	private Integer leadenter;//牵头单位
	private Integer coordinate;//协调负责人
	private String taskPrefix;//项目划分任务前缀
	private String visibleRange;//显示范围
	private String joiners;//项目参加人员
	private Integer process;//项目进展程度
	private String remarks;//备注
	private String invest;//投资情况
	
	private String investType;
	private String investThisyear;//当年预计完成投资
	private String investCom;//当年已完成投资
	private String isOpen;//是否开复工
	private Date thisyearOpentime;//当年预计开复工时间
	private String lxType;//项目立项类型（审批、核准、备案）
	private String buildAddress;//建设地点（旗县区）
	private String processCondition;
	private String submitted;//报送地区
	
	private Date expectedDate;//预计完成时间
	private Date startDate;//开始时间
	private String approveCode;//项目审批监管平台代码
	private String image;
	
	private String lxIsComapprove;//立项
	private String lxHandleLevel;
	private String lxIsSendappdepart;
	private String lxBao;//审批、转报部门意见（审批部门同意办理、审批部门不同意办理、转报部门同意转报、转报部门不同意转报）
	private String lxBaoNoMsg;//不同意办理或不同意转报原因（文字详细表述）
	private String ydArea;//项目整体建设用地规模（亩）
	private String ydAreaLd;//其中涉及林地规模
	private String ydAreaCd;//其中涉及草地规模
	private String ydAreaCy;//其中涉及草原用地
	private String ydcardIsHascard;
	private String ydcardHandleLevel;
	private String ydcardIsSendappdepart;
	private String ydBao;
	private String ydBaoNoMsg;
	
	private String energyArea;//项目整体能耗规模（等价值）
	private String energyWaterArea;//项目整体用水规模（立方米）
	private String energyIsCensor;
	private String energyHandleLevel;
	private String energyIsSendappdepart;
	private String energyBao;
	private String energyBaoNoMsg;
	
	private String lcIsBl;
	private String lcHandleLevel;
	private String lcIsSendappdepart;
	private String lcBao;
	private String lcBaoNoMsg;
	
	private String tdProvide;
	private String tdIsBl;
	private String tdHandleLevel;
	private String tdIsSendappdepart;
	private String tdBao;
	private String tdBaoNoMsg;
	
	private String envirIsBl;
	private String envirHandleLevel;
	private String envirIsSendappdepart;
	private String envirBao;
	private String envirBaoNoMsg;
	
	private String sgIsBl;
	private String sgHandleLevel;
	private String sgIsSendappdepart;
	private String sgBao;
	private String sgBaoNoMsg;
	
	private String xfIsBl;
	private String xfHandleLevel;
	private String xfIsSendappdepart;
	private String rfIsBl;
	private String rfHandleLevel;
	private String rfIsSendappdepart;
	
	private String otherBl;//其他需要解决的手续问题
	private String diffAndProblem;//存在的困难和问题
	private Integer proManager;//项目主管部门联系人
	private String proManagerMobile;//项目主管部门联系人电话
	private Integer proEnter;//项目主管单位
	private String proManagerTing;//主管厅局
	private Integer enterManager;
	private String enterManagerMobile;
	private String stage;//项目建设阶段
	private Integer status;//
	private Date completeDate;//完成时间
	private Integer orgId;
	
	
	
	
	
	//外键
	private String orgName;
	private String leadenterName;
	private String categoryName;
	private String leaderName;
	private String coordinateName;
	private String proEnterName;
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
	public String getLxIsComapprove() {
		return lxIsComapprove;
	}
	public void setLxIsComapprove(String lxIsComapprove) {
		this.lxIsComapprove = lxIsComapprove;
	}
	public String getLxHandleLevel() {
		return lxHandleLevel;
	}
	public void setLxHandleLevel(String lxHandleLevel) {
		this.lxHandleLevel = lxHandleLevel;
	}
	public String getLxIsSendappdepart() {
		return lxIsSendappdepart;
	}
	public void setLxIsSendappdepart(String lxIsSendappdepart) {
		this.lxIsSendappdepart = lxIsSendappdepart;
	}
	public String getYdcardIsHascard() {
		return ydcardIsHascard;
	}
	public void setYdcardIsHascard(String ydcardIsHascard) {
		this.ydcardIsHascard = ydcardIsHascard;
	}
	public String getYdcardHandleLevel() {
		return ydcardHandleLevel;
	}
	public void setYdcardHandleLevel(String ydcardHandleLevel) {
		this.ydcardHandleLevel = ydcardHandleLevel;
	}
	public String getYdcardIsSendappdepart() {
		return ydcardIsSendappdepart;
	}
	public void setYdcardIsSendappdepart(String ydcardIsSendappdepart) {
		this.ydcardIsSendappdepart = ydcardIsSendappdepart;
	}
	public String getEnergyIsCensor() {
		return energyIsCensor;
	}
	public void setEnergyIsCensor(String energyIsCensor) {
		this.energyIsCensor = energyIsCensor;
	}
	public String getEnergyHandleLevel() {
		return energyHandleLevel;
	}
	public void setEnergyHandleLevel(String energyHandleLevel) {
		this.energyHandleLevel = energyHandleLevel;
	}
	public String getEnergyIsSendappdepart() {
		return energyIsSendappdepart;
	}
	public void setEnergyIsSendappdepart(String energyIsSendappdepart) {
		this.energyIsSendappdepart = energyIsSendappdepart;
	}
	public String getLcIsBl() {
		return lcIsBl;
	}
	public void setLcIsBl(String lcIsBl) {
		this.lcIsBl = lcIsBl;
	}
	public String getLcHandleLevel() {
		return lcHandleLevel;
	}
	public void setLcHandleLevel(String lcHandleLevel) {
		this.lcHandleLevel = lcHandleLevel;
	}
	public String getLcIsSendappdepart() {
		return lcIsSendappdepart;
	}
	public void setLcIsSendappdepart(String lcIsSendappdepart) {
		this.lcIsSendappdepart = lcIsSendappdepart;
	}
	public String getEnvirIsBl() {
		return envirIsBl;
	}
	public void setEnvirIsBl(String envirIsBl) {
		this.envirIsBl = envirIsBl;
	}
	public String getEnvirHandleLevel() {
		return envirHandleLevel;
	}
	public void setEnvirHandleLevel(String envirHandleLevel) {
		this.envirHandleLevel = envirHandleLevel;
	}
	public String getEnvirIsSendappdepart() {
		return envirIsSendappdepart;
	}
	public void setEnvirIsSendappdepart(String envirIsSendappdepart) {
		this.envirIsSendappdepart = envirIsSendappdepart;
	}
	public String getSgIsBl() {
		return sgIsBl;
	}
	public void setSgIsBl(String sgIsBl) {
		this.sgIsBl = sgIsBl;
	}
	public String getSgHandleLevel() {
		return sgHandleLevel;
	}
	public void setSgHandleLevel(String sgHandleLevel) {
		this.sgHandleLevel = sgHandleLevel;
	}
	public String getSgIsSendappdepart() {
		return sgIsSendappdepart;
	}
	public void setSgIsSendappdepart(String sgIsSendappdepart) {
		this.sgIsSendappdepart = sgIsSendappdepart;
	}
	public String getXfIsBl() {
		return xfIsBl;
	}
	public void setXfIsBl(String xfIsBl) {
		this.xfIsBl = xfIsBl;
	}
	public String getXfHandleLevel() {
		return xfHandleLevel;
	}
	public void setXfHandleLevel(String xfHandleLevel) {
		this.xfHandleLevel = xfHandleLevel;
	}
	public String getXfIsSendappdepart() {
		return xfIsSendappdepart;
	}
	public void setXfIsSendappdepart(String xfIsSendappdepart) {
		this.xfIsSendappdepart = xfIsSendappdepart;
	}
	public String getRfIsBl() {
		return rfIsBl;
	}
	public void setRfIsBl(String rfIsBl) {
		this.rfIsBl = rfIsBl;
	}
	public String getRfHandleLevel() {
		return rfHandleLevel;
	}
	public void setRfHandleLevel(String rfHandleLevel) {
		this.rfHandleLevel = rfHandleLevel;
	}
	public String getRfIsSendappdepart() {
		return rfIsSendappdepart;
	}
	public void setRfIsSendappdepart(String rfIsSendappdepart) {
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
	
	
	public String getTdIsBl() {
		return tdIsBl;
	}
	public void setTdIsBl(String tdIsBl) {
		this.tdIsBl = tdIsBl;
	}
	public String getTdHandleLevel() {
		return tdHandleLevel;
	}
	public void setTdHandleLevel(String tdHandleLevel) {
		this.tdHandleLevel = tdHandleLevel;
	}
	public String getTdIsSendappdepart() {
		return tdIsSendappdepart;
	}
	public void setTdIsSendappdepart(String tdIsSendappdepart) {
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
	public String getLxBao() {
		return lxBao;
	}
	public void setLxBao(String lxBao) {
		this.lxBao = lxBao;
	}
	public String getLxBaoNoMsg() {
		return lxBaoNoMsg;
	}
	public void setLxBaoNoMsg(String lxBaoNoMsg) {
		this.lxBaoNoMsg = lxBaoNoMsg;
	}
	public String getYdArea() {
		return ydArea;
	}
	public void setYdArea(String ydArea) {
		this.ydArea = ydArea;
	}
	public String getYdAreaLd() {
		return ydAreaLd;
	}
	public void setYdAreaLd(String ydAreaLd) {
		this.ydAreaLd = ydAreaLd;
	}
	public String getYdAreaCd() {
		return ydAreaCd;
	}
	public void setYdAreaCd(String ydAreaCd) {
		this.ydAreaCd = ydAreaCd;
	}
	public String getYdBao() {
		return ydBao;
	}
	public void setYdBao(String ydBao) {
		this.ydBao = ydBao;
	}
	public String getYdBaoNoMsg() {
		return ydBaoNoMsg;
	}
	public void setYdBaoNoMsg(String ydBaoNoMsg) {
		this.ydBaoNoMsg = ydBaoNoMsg;
	}
	public String getEnergyArea() {
		return energyArea;
	}
	public void setEnergyArea(String energyArea) {
		this.energyArea = energyArea;
	}
	public String getEnergyWaterArea() {
		return energyWaterArea;
	}
	public void setEnergyWaterArea(String energyWaterArea) {
		this.energyWaterArea = energyWaterArea;
	}
	public String getEnergyBao() {
		return energyBao;
	}
	public void setEnergyBao(String energyBao) {
		this.energyBao = energyBao;
	}
	public String getEnergyBaoNoMsg() {
		return energyBaoNoMsg;
	}
	public void setEnergyBaoNoMsg(String energyBaoNoMsg) {
		this.energyBaoNoMsg = energyBaoNoMsg;
	}
	public String getLcBao() {
		return lcBao;
	}
	public void setLcBao(String lcBao) {
		this.lcBao = lcBao;
	}
	public String getLcBaoNoMsg() {
		return lcBaoNoMsg;
	}
	public void setLcBaoNoMsg(String lcBaoNoMsg) {
		this.lcBaoNoMsg = lcBaoNoMsg;
	}
	public String getTdBao() {
		return tdBao;
	}
	public void setTdBao(String tdBao) {
		this.tdBao = tdBao;
	}
	public String getTdBaoNoMsg() {
		return tdBaoNoMsg;
	}
	public void setTdBaoNoMsg(String tdBaoNoMsg) {
		this.tdBaoNoMsg = tdBaoNoMsg;
	}
	public String getEnvirBao() {
		return envirBao;
	}
	public void setEnvirBao(String envirBao) {
		this.envirBao = envirBao;
	}
	public String getEnvirBaoNoMsg() {
		return envirBaoNoMsg;
	}
	public void setEnvirBaoNoMsg(String envirBaoNoMsg) {
		this.envirBaoNoMsg = envirBaoNoMsg;
	}
	public String getSgBao() {
		return sgBao;
	}
	public void setSgBao(String sgBao) {
		this.sgBao = sgBao;
	}
	public String getSgBaoNoMsg() {
		return sgBaoNoMsg;
	}
	public void setSgBaoNoMsg(String sgBaoNoMsg) {
		this.sgBaoNoMsg = sgBaoNoMsg;
	}
	public Integer getProEnter() {
		return proEnter;
	}
	public void setProEnter(Integer proEnter) {
		this.proEnter = proEnter;
	}
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	public String getInvestThisyear() {
		return investThisyear;
	}
	public void setInvestThisyear(String investThisyear) {
		this.investThisyear = investThisyear;
	}
	public String getInvestCom() {
		return investCom;
	}
	public void setInvestCom(String investCom) {
		this.investCom = investCom;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public Date getThisyearOpentime() {
		return thisyearOpentime;
	}
	public void setThisyearOpentime(Date thisyearOpentime) {
		this.thisyearOpentime = thisyearOpentime;
	}
	public String getLxType() {
		return lxType;
	}
	public void setLxType(String lxType) {
		this.lxType = lxType;
	}
	public String getBuildAddress() {
		return buildAddress;
	}
	public void setBuildAddress(String buildAddress) {
		this.buildAddress = buildAddress;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
	public String getProManagerTing() {
		return proManagerTing;
	}
	public void setProManagerTing(String proManagerTing) {
		this.proManagerTing = proManagerTing;
	}
	public String getProEnterName() {
		return proEnterName;
	}
	public void setProEnterName(String proEnterName) {
		this.proEnterName = proEnterName;
	}
	public String getTdProvide() {
		return tdProvide;
	}
	public void setTdProvide(String tdProvide) {
		this.tdProvide = tdProvide;
	}
	public String getYdAreaCy() {
		return ydAreaCy;
	}
	public void setYdAreaCy(String ydAreaCy) {
		this.ydAreaCy = ydAreaCy;
	}
}
