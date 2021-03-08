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
	private Date expectedDate;//预计完成时间
	private Date startDate;//开始时间
	private String approveCode;//项目审批监管平台代码
	private String image;
	private String processCondition;
	
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
	public Integer getLxIsComapprove() {
		return lxIsComapprove;
	}
	public void setLxIsComapprove(Integer lxIsComapprove) {
		this.lxIsComapprove = lxIsComapprove;
	}

	@Column(name = "lx_handle_level")
	public Integer getLxHandleLevel() {
		return lxHandleLevel;
	}
	public void setLxHandleLevel(Integer lxHandleLevel) {
		this.lxHandleLevel = lxHandleLevel;
	}

	@Column(name = "lx_is_sendappdepart")
	public Integer getLxIsSendappdepart() {
		return lxIsSendappdepart;
	}
	public void setLxIsSendappdepart(Integer lxIsSendappdepart) {
		this.lxIsSendappdepart = lxIsSendappdepart;
	}

	@Column(name = "ydcard_is_hascard")
	public Integer getYdcardIsHascard() {
		return ydcardIsHascard;
	}
	public void setYdcardIsHascard(Integer ydcardIsHascard) {
		this.ydcardIsHascard = ydcardIsHascard;
	}

	@Column(name = "ydcard_handle_level")
	public Integer getYdcardHandleLevel() {
		return ydcardHandleLevel;
	}
	public void setYdcardHandleLevel(Integer ydcardHandleLevel) {
		this.ydcardHandleLevel = ydcardHandleLevel;
	}

	@Column(name = "ydcard_is_sendappdepart")
	public Integer getYdcardIsSendappdepart() {
		return ydcardIsSendappdepart;
	}
	public void setYdcardIsSendappdepart(Integer ydcardIsSendappdepart) {
		this.ydcardIsSendappdepart = ydcardIsSendappdepart;
	}

	@Column(name = "energy_is_censor")
	public Integer getEnergyIsCensor() {
		return energyIsCensor;
	}
	public void setEnergyIsCensor(Integer energyIsCensor) {
		this.energyIsCensor = energyIsCensor;
	}

	@Column(name = "energy_handle_level")
	public Integer getEnergyHandleLevel() {
		return energyHandleLevel;
	}
	public void setEnergyHandleLevel(Integer energyHandleLevel) {
		this.energyHandleLevel = energyHandleLevel;
	}

	@Column(name = "energy_is_sendappdepart")
	public Integer getEnergyIsSendappdepart() {
		return energyIsSendappdepart;
	}
	public void setEnergyIsSendappdepart(Integer energyIsSendappdepart) {
		this.energyIsSendappdepart = energyIsSendappdepart;
	}

	@Column(name = "lc_is_bl")
	public Integer getLcIsBl() {
		return lcIsBl;
	}
	public void setLcIsBl(Integer lcIsBl) {
		this.lcIsBl = lcIsBl;
	}

	@Column(name = "lc_handle_level")
	public Integer getLcHandleLevel() {
		return lcHandleLevel;
	}
	public void setLcHandleLevel(Integer lcHandleLevel) {
		this.lcHandleLevel = lcHandleLevel;
	}

	@Column(name = "lc_is_sendappdepart")
	public Integer getLcIsSendappdepart() {
		return lcIsSendappdepart;
	}
	public void setLcIsSendappdepart(Integer lcIsSendappdepart) {
		this.lcIsSendappdepart = lcIsSendappdepart;
	}

	@Column(name = "envir_is_bl")
	public Integer getEnvirIsBl() {
		return envirIsBl;
	}
	public void setEnvirIsBl(Integer envirIsBl) {
		this.envirIsBl = envirIsBl;
	}

	@Column(name = "envir_handle_level")
	public Integer getEnvirHandleLevel() {
		return envirHandleLevel;
	}
	public void setEnvirHandleLevel(Integer envirHandleLevel) {
		this.envirHandleLevel = envirHandleLevel;
	}

	@Column(name = "envir_is_sendappdepart")
	public Integer getEnvirIsSendappdepart() {
		return envirIsSendappdepart;
	}
	public void setEnvirIsSendappdepart(Integer envirIsSendappdepart) {
		this.envirIsSendappdepart = envirIsSendappdepart;
	}

	@Column(name = "sg_is_bl")
	public Integer getSgIsBl() {
		return sgIsBl;
	}
	public void setSgIsBl(Integer sgIsBl) {
		this.sgIsBl = sgIsBl;
	}

	@Column(name = "sg_handle_level")
	public Integer getSgHandleLevel() {
		return sgHandleLevel;
	}
	public void setSgHandleLevel(Integer sgHandleLevel) {
		this.sgHandleLevel = sgHandleLevel;
	}
	
	@Column(name = "sg_is_sendappdepart")
	public Integer getSgIsSendappdepart() {
		return sgIsSendappdepart;
	}
	public void setSgIsSendappdepart(Integer sgIsSendappdepart) {
		this.sgIsSendappdepart = sgIsSendappdepart;
	}

	@Column(name = "xf_is_bl")
	public Integer getXfIsBl() {
		return xfIsBl;
	}
	public void setXfIsBl(Integer xfIsBl) {
		this.xfIsBl = xfIsBl;
	}

	@Column(name = "xf_handle_level")
	public Integer getXfHandleLevel() {
		return xfHandleLevel;
	}
	public void setXfHandleLevel(Integer xfHandleLevel) {
		this.xfHandleLevel = xfHandleLevel;
	}

	@Column(name = "xf_is_sendappdepart")
	public Integer getXfIsSendappdepart() {
		return xfIsSendappdepart;
	}
	public void setXfIsSendappdepart(Integer xfIsSendappdepart) {
		this.xfIsSendappdepart = xfIsSendappdepart;
	}

	@Column(name = "rf_is_bl")
	public Integer getRfIsBl() {
		return rfIsBl;
	}
	public void setRfIsBl(Integer rfIsBl) {
		this.rfIsBl = rfIsBl;
	}

	@Column(name = "rf_handle_level")
	public Integer getRfHandleLevel() {
		return rfHandleLevel;
	}
	public void setRfHandleLevel(Integer rfHandleLevel) {
		this.rfHandleLevel = rfHandleLevel;
	}

	@Column(name = "rf_is_sendappdepart")
	public Integer getRfIsSendappdepart() {
		return rfIsSendappdepart;
	}
	public void setRfIsSendappdepart(Integer rfIsSendappdepart) {
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
	public Integer getProManager() {
		return proManager;
	}

	public void setProManager(Integer proManager) {
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
	public Integer getTdIsBl() {
		return tdIsBl;
	}
	public void setTdIsBl(Integer tdIsBl) {
		this.tdIsBl = tdIsBl;
	}

	@Column(name = "td_handle_level")
	public Integer getTdHandleLevel() {
		return tdHandleLevel;
	}
	public void setTdHandleLevel(Integer tdHandleLevel) {
		this.tdHandleLevel = tdHandleLevel;
	}

	@Column(name = "td_is_sendappdepart")
	public Integer getTdIsSendappdepart() {
		return tdIsSendappdepart;
	}
	public void setTdIsSendappdepart(Integer tdIsSendappdepart) {
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
	
}