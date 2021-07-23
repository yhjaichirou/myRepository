package com.fgw.project.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
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
	private Date delayDate;
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
	
	private String ydcardIsHascard; //用地预审和规划选址意见书
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
	private String proManager;//项目主管部门联系人
	private String proManagerMobile;//项目主管部门联系人电话
	private String proManager2;//项目主管部门联系人
	private String proManagerMobile2;//项目主管部门联系人电话
	private Integer proEnter;//项目主管单位
	private String proManagerTing;//主管厅局
	private Integer enterManager;
	private String enterManagerMobile;
	private String stage;//项目建设阶段
	private Integer status;//
	private Date completeDate;//完成时间
	private Integer orgId;
	private String nopass;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "industry_category")
	public Integer getIndustryCategory() {
		return industryCategory;
	}
	public void setIndustryCategory(Integer industryCategory) {
		this.industryCategory = industryCategory;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "number")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "maturity")
	public Integer getMaturity() {
		return maturity;
	}
	public void setMaturity(Integer maturity) {
		this.maturity = maturity;
	}

	@Column(name = "docking_date")
	public Date getDockingDate() {
		return dockingDate;
	}
	public void setDockingDate(Date dockingDate) {
		this.dockingDate = dockingDate;
	}

	@Column(name = "leader")
	public Integer getLeader() {
		return leader;
	}
	public void setLeader(Integer leader) {
		this.leader = leader;
	}

	@Column(name = "leadenter")
	public Integer getLeadenter() {
		return leadenter;
	}
	public void setLeadenter(Integer leadenter) {
		this.leadenter = leadenter;
	}

	@Column(name = "coordinate")
	public Integer getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Integer coordinate) {
		this.coordinate = coordinate;
	}

	@Column(name = "task_prefix")
	public String getTaskPrefix() {
		return taskPrefix;
	}
	public void setTaskPrefix(String taskPrefix) {
		this.taskPrefix = taskPrefix;
	}

	@Column(name = "visible_range")
	public String getVisibleRange() {
		return visibleRange;
	}
	public void setVisibleRange(String visibleRange) {
		this.visibleRange = visibleRange;
	}

	@Column(name = "joiners")
	public String getJoiners() {
		return joiners;
	}
	public void setJoiners(String joiners) {
		this.joiners = joiners;
	}

	@Column(name = "process")
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "invest")
	public String getInvest() {
		return invest;
	}
	public void setInvest(String invest) {
		this.invest = invest;
	}

	@Column(name = "expected_date")
	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "approve_code")
	public String getApproveCode() {
		return approveCode;
	}
	public void setApproveCode(String approveCode) {
		this.approveCode = approveCode;
	}

	@Column(name = "lx_is_comapprove")
	public String getLxIsComapprove() {
		return lxIsComapprove;
	}
	public void setLxIsComapprove(String lxIsComapprove) {
		this.lxIsComapprove = lxIsComapprove;
	}

	@Column(name = "lx_handle_level")
	public String getLxHandleLevel() {
		return lxHandleLevel;
	}
	public void setLxHandleLevel(String lxHandleLevel) {
		this.lxHandleLevel = lxHandleLevel;
	}

	@Column(name = "lx_is_sendappdepart")
	public String getLxIsSendappdepart() {
		return lxIsSendappdepart;
	}
	public void setLxIsSendappdepart(String lxIsSendappdepart) {
		this.lxIsSendappdepart = lxIsSendappdepart;
	}

	@Column(name = "ydcard_is_hascard")
	public String getYdcardIsHascard() {
		return ydcardIsHascard;
	}
	public void setYdcardIsHascard(String ydcardIsHascard) {
		this.ydcardIsHascard = ydcardIsHascard;
	}

	@Column(name = "ydcard_handle_level")
	public String getYdcardHandleLevel() {
		return ydcardHandleLevel;
	}
	public void setYdcardHandleLevel(String ydcardHandleLevel) {
		this.ydcardHandleLevel = ydcardHandleLevel;
	}

	@Column(name = "ydcard_is_sendappdepart")
	public String getYdcardIsSendappdepart() {
		return ydcardIsSendappdepart;
	}
	public void setYdcardIsSendappdepart(String ydcardIsSendappdepart) {
		this.ydcardIsSendappdepart = ydcardIsSendappdepart;
	}

	@Column(name = "energy_is_censor")
	public String getEnergyIsCensor() {
		return energyIsCensor;
	}
	public void setEnergyIsCensor(String energyIsCensor) {
		this.energyIsCensor = energyIsCensor;
	}

	@Column(name = "energy_handle_level")
	public String getEnergyHandleLevel() {
		return energyHandleLevel;
	}
	public void setEnergyHandleLevel(String energyHandleLevel) {
		this.energyHandleLevel = energyHandleLevel;
	}

	@Column(name = "energy_is_sendappdepart")
	public String getEnergyIsSendappdepart() {
		return energyIsSendappdepart;
	}
	public void setEnergyIsSendappdepart(String energyIsSendappdepart) {
		this.energyIsSendappdepart = energyIsSendappdepart;
	}

	@Column(name = "lc_is_bl")
	public String getLcIsBl() {
		return lcIsBl;
	}
	public void setLcIsBl(String lcIsBl) {
		this.lcIsBl = lcIsBl;
	}

	@Column(name = "lc_handle_level")
	public String getLcHandleLevel() {
		return lcHandleLevel;
	}
	public void setLcHandleLevel(String lcHandleLevel) {
		this.lcHandleLevel = lcHandleLevel;
	}

	@Column(name = "lc_is_sendappdepart")
	public String getLcIsSendappdepart() {
		return lcIsSendappdepart;
	}
	public void setLcIsSendappdepart(String lcIsSendappdepart) {
		this.lcIsSendappdepart = lcIsSendappdepart;
	}

	@Column(name = "envir_is_bl")
	public String getEnvirIsBl() {
		return envirIsBl;
	}
	public void setEnvirIsBl(String envirIsBl) {
		this.envirIsBl = envirIsBl;
	}

	@Column(name = "envir_handle_level")
	public String getEnvirHandleLevel() {
		return envirHandleLevel;
	}
	public void setEnvirHandleLevel(String envirHandleLevel) {
		this.envirHandleLevel = envirHandleLevel;
	}

	@Column(name = "envir_is_sendappdepart")
	public String getEnvirIsSendappdepart() {
		return envirIsSendappdepart;
	}
	public void setEnvirIsSendappdepart(String envirIsSendappdepart) {
		this.envirIsSendappdepart = envirIsSendappdepart;
	}

	@Column(name = "sg_is_bl")
	public String getSgIsBl() {
		return sgIsBl;
	}
	public void setSgIsBl(String sgIsBl) {
		this.sgIsBl = sgIsBl;
	}

	@Column(name = "sg_handle_level")
	public String getSgHandleLevel() {
		return sgHandleLevel;
	}
	public void setSgHandleLevel(String sgHandleLevel) {
		this.sgHandleLevel = sgHandleLevel;
	}
	
	@Column(name = "sg_is_sendappdepart")
	public String getSgIsSendappdepart() {
		return sgIsSendappdepart;
	}
	public void setSgIsSendappdepart(String sgIsSendappdepart) {
		this.sgIsSendappdepart = sgIsSendappdepart;
	}

	@Column(name = "xf_is_bl")
	public String getXfIsBl() {
		return xfIsBl;
	}
	public void setXfIsBl(String xfIsBl) {
		this.xfIsBl = xfIsBl;
	}

	@Column(name = "xf_handle_level")
	public String getXfHandleLevel() {
		return xfHandleLevel;
	}
	public void setXfHandleLevel(String xfHandleLevel) {
		this.xfHandleLevel = xfHandleLevel;
	}

	@Column(name = "xf_is_sendappdepart")
	public String getXfIsSendappdepart() {
		return xfIsSendappdepart;
	}
	public void setXfIsSendappdepart(String xfIsSendappdepart) {
		this.xfIsSendappdepart = xfIsSendappdepart;
	}

	@Column(name = "rf_is_bl")
	public String getRfIsBl() {
		return rfIsBl;
	}
	public void setRfIsBl(String rfIsBl) {
		this.rfIsBl = rfIsBl;
	}

	@Column(name = "rf_handle_level")
	public String getRfHandleLevel() {
		return rfHandleLevel;
	}
	public void setRfHandleLevel(String rfHandleLevel) {
		this.rfHandleLevel = rfHandleLevel;
	}

	@Column(name = "rf_is_sendappdepart")
	public String getRfIsSendappdepart() {
		return rfIsSendappdepart;
	}
	public void setRfIsSendappdepart(String rfIsSendappdepart) {
		this.rfIsSendappdepart = rfIsSendappdepart;
	}

	@Column(name = "other_bl")
	public String getOtherBl() {
		return otherBl;
	}
	public void setOtherBl(String otherBl) {
		this.otherBl = otherBl;
	}

	@Column(name = "diff_and_problem")
	public String getDiffAndProblem() {
		return diffAndProblem;
	}
	public void setDiffAndProblem(String diffAndProblem) {
		this.diffAndProblem = diffAndProblem;
	}

	@Column(name = "pro_manager")
	public String getProManager() {
		return proManager;
	}

	public void setProManager(String proManager) {
		this.proManager = proManager;
	}

	@Column(name = "pro_manager_mobile")
	public String getProManagerMobile() {
		return proManagerMobile;
	}

	public void setProManagerMobile(String proManagerMobile) {
		this.proManagerMobile = proManagerMobile;
	}

	@Column(name = "enter_manager")
	public Integer getEnterManager() {
		return enterManager;
	}
	public void setEnterManager(Integer enterManager) {
		this.enterManager = enterManager;
	}

	@Column(name = "enter_manager_mobile")
	public String getEnterManagerMobile() {
		return enterManagerMobile;
	}
	public void setEnterManagerMobile(String enterManagerMobile) {
		this.enterManagerMobile = enterManagerMobile;
	}

	@Column(name = "stage")
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "complete_date")
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	@Column(name = "org_Id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "td_is_bl")
	public String getTdIsBl() {
		return tdIsBl;
	}
	public void setTdIsBl(String tdIsBl) {
		this.tdIsBl = tdIsBl;
	}

	@Column(name = "td_handle_level")
	public String getTdHandleLevel() {
		return tdHandleLevel;
	}
	public void setTdHandleLevel(String tdHandleLevel) {
		this.tdHandleLevel = tdHandleLevel;
	}

	@Column(name = "td_is_sendappdepart")
	public String getTdIsSendappdepart() {
		return tdIsSendappdepart;
	}
	public void setTdIsSendappdepart(String tdIsSendappdepart) {
		this.tdIsSendappdepart = tdIsSendappdepart;
	}

	@Column(name = "image")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "process_condition")
	public String getProcessCondition() {
		return processCondition;
	}

	public void setProcessCondition(String processCondition) {
		this.processCondition = processCondition;
	}
	
	@Column(name = "invest_type")
	public String getInvestType() {
		return investType;
	}

	public void setInvestType(String investType) {
		this.investType = investType;
	}

	@Column(name = "invest_thisyear")
	public String getInvestThisyear() {
		return investThisyear;
	}

	public void setInvestThisyear(String investThisyear) {
		this.investThisyear = investThisyear;
	}

	@Column(name = "invest_com")
	public String getInvestCom() {
		return investCom;
	}

	public void setInvestCom(String investCom) {
		this.investCom = investCom;
	}

	@Column(name = "is_open")
	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	@Column(name = "thisyear_opentime")
	public Date getThisyearOpentime() {
		return thisyearOpentime;
	}

	public void setThisyearOpentime(Date thisyearOpentime) {
		this.thisyearOpentime = thisyearOpentime;
	}

	@Column(name = "lx_type")
	public String getLxType() {
		return lxType;
	}

	public void setLxType(String lxType) {
		this.lxType = lxType;
	}

	@Column(name = "build_address")
	public String getBuildAddress() {
		return buildAddress;
	}

	public void setBuildAddress(String buildAddress) {
		this.buildAddress = buildAddress;
	}

	@Column(name = "lx_bao")
	public String getLxBao() {
		return lxBao;
	}

	public void setLxBao(String lxBao) {
		this.lxBao = lxBao;
	}

	@Column(name = "lx_bao_no_msg")
	public String getLxBaoNoMsg() {
		return lxBaoNoMsg;
	}

	public void setLxBaoNoMsg(String lxBaoNoMsg) {
		this.lxBaoNoMsg = lxBaoNoMsg;
	}

	@Column(name = "yd_area")
	public String getYdArea() {
		return ydArea;
	}

	public void setYdArea(String ydArea) {
		this.ydArea = ydArea;
	}

	@Column(name = "yd_area_ld")
	public String getYdAreaLd() {
		return ydAreaLd;
	}

	public void setYdAreaLd(String ydAreaLd) {
		this.ydAreaLd = ydAreaLd;
	}

	@Column(name = "yd_area_cd")
	public String getYdAreaCd() {
		return ydAreaCd;
	}

	public void setYdAreaCd(String ydAreaCd) {
		this.ydAreaCd = ydAreaCd;
	}

	@Column(name = "yd_bao")
	public String getYdBao() {
		return ydBao;
	}

	public void setYdBao(String ydBao) {
		this.ydBao = ydBao;
	}

	@Column(name = "yd_bao_no_msg")
	public String getYdBaoNoMsg() {
		return ydBaoNoMsg;
	}

	public void setYdBaoNoMsg(String ydBaoNoMsg) {
		this.ydBaoNoMsg = ydBaoNoMsg;
	}

	@Column(name = "energy_area")
	public String getEnergyArea() {
		return energyArea;
	}

	public void setEnergyArea(String energyArea) {
		this.energyArea = energyArea;
	}

	@Column(name = "energy_water_area")
	public String getEnergyWaterArea() {
		return energyWaterArea;
	}

	public void setEnergyWaterArea(String energyWaterArea) {
		this.energyWaterArea = energyWaterArea;
	}

	@Column(name = "energy_bao")
	public String getEnergyBao() {
		return energyBao;
	}

	public void setEnergyBao(String energyBao) {
		this.energyBao = energyBao;
	}

	@Column(name = "energy_bao_no_msg")
	public String getEnergyBaoNoMsg() {
		return energyBaoNoMsg;
	}

	public void setEnergyBaoNoMsg(String energyBaoNoMsg) {
		this.energyBaoNoMsg = energyBaoNoMsg;
	}

	@Column(name = "lc_bao")
	public String getLcBao() {
		return lcBao;
	}

	public void setLcBao(String lcBao) {
		this.lcBao = lcBao;
	}

	@Column(name = "lc_bao_no_msg")
	public String getLcBaoNoMsg() {
		return lcBaoNoMsg;
	}

	public void setLcBaoNoMsg(String lcBaoNoMsg) {
		this.lcBaoNoMsg = lcBaoNoMsg;
	}

	@Column(name = "td_bao")
	public String getTdBao() {
		return tdBao;
	}

	public void setTdBao(String tdBao) {
		this.tdBao = tdBao;
	}

	@Column(name = "td_bao_no_msg")
	public String getTdBaoNoMsg() {
		return tdBaoNoMsg;
	}

	public void setTdBaoNoMsg(String tdBaoNoMsg) {
		this.tdBaoNoMsg = tdBaoNoMsg;
	}

	@Column(name = "envir_bao")
	public String getEnvirBao() {
		return envirBao;
	}

	public void setEnvirBao(String envirBao) {
		this.envirBao = envirBao;
	}

	@Column(name = "envir_bao_no_msg")
	public String getEnvirBaoNoMsg() {
		return envirBaoNoMsg;
	}

	public void setEnvirBaoNoMsg(String envirBaoNoMsg) {
		this.envirBaoNoMsg = envirBaoNoMsg;
	}

	@Column(name = "sg_bao")
	public String getSgBao() {
		return sgBao;
	}

	public void setSgBao(String sgBao) {
		this.sgBao = sgBao;
	}

	@Column(name = "sg_bao_no_msg")
	public String getSgBaoNoMsg() {
		return sgBaoNoMsg;
	}

	public void setSgBaoNoMsg(String sgBaoNoMsg) {
		this.sgBaoNoMsg = sgBaoNoMsg;
	}

	@Column(name = "pro_enter")
	public Integer getProEnter() {
		return proEnter;
	}

	public void setProEnter(Integer proEnter) {
		this.proEnter = proEnter;
	}

	@Column(name = "submitted")
	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	@Column(name = "pro_manager_ting")
	public String getProManagerTing() {
		return proManagerTing;
	}

	public void setProManagerTing(String proManagerTing) {
		this.proManagerTing = proManagerTing;
	}

	@Column(name = "td_provide")
	public String getTdProvide() {
		return tdProvide;
	}

	public void setTdProvide(String tdProvide) {
		this.tdProvide = tdProvide;
	}

	@Column(name = "yd_area_cy")
	public String getYdAreaCy() {
		return ydAreaCy;
	}

	public void setYdAreaCy(String ydAreaCy) {
		this.ydAreaCy = ydAreaCy;
	}

	@Column(name = "nopass")
	public String getNopass() {
		return nopass;
	}

	public void setNopass(String nopass) {
		this.nopass = nopass;
	}

	@Column(name = "delay_date")
	public Date getDelayDate() {
		return delayDate;
	}

	public void setDelayDate(Date delayDate) {
		this.delayDate = delayDate;
	}


	@Column(name = "pro_manager2")
	public String getProManager2() {
		return proManager2;
	}

	public void setProManager2(String proManager2) {
		this.proManager2 = proManager2;
	}

	@Column(name = "pro_manager_mobile2")
	public String getProManagerMobile2() {
		return proManagerMobile2;
	}

	public void setProManagerMobile2(String proManagerMobile2) {
		this.proManagerMobile2 = proManagerMobile2;
	}
	
	
}