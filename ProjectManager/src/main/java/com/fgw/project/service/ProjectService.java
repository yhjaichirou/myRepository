package com.fgw.project.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.ProjectStatusEnum;
import com.fgw.project.constant.TaskStatusEnum;
import com.fgw.project.model.po.Category;
import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Industry;
import com.fgw.project.model.po.Invest;
import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.Project;
import com.fgw.project.model.po.Task;
import com.fgw.project.model.vo.Annex;
import com.fgw.project.model.vo.InvestVo;
import com.fgw.project.model.vo.ProjectVo;
import com.fgw.project.model.vo.TaskVo;
import com.fgw.project.repository.ICategoryRepository;
import com.fgw.project.repository.IGroupRepository;
import com.fgw.project.repository.IIndustryRepository;
import com.fgw.project.repository.IInvestRepository;
import com.fgw.project.repository.IOrgRepository;
import com.fgw.project.repository.IPeopleRepository;
import com.fgw.project.repository.IProjectRepository;
import com.fgw.project.repository.IShbRepository;
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
	private IIndustryRepository industryR;
	@Autowired
	private IPeopleRepository peopleR;
	@Autowired
	private ITaskRepository taskR;
	@Autowired
	private IShbRepository shbR;
	@Autowired
	private IInvestRepository investR;
	@Resource
	private CommonService comService;
	
	//投资情况
	public RetKit getTzqkList(Integer projectId) {
		List<Invest> ins = investR.findAllByProId(projectId);
		List<InvestVo> insv = BeanKit.copyBeanList(ins, InvestVo.class);
		insv = insv.stream().map((InvestVo m)->{
			m.setInvestDateStr(m.getInvestDate()==null?"":MDateUtil.dateToString(m.getInvestDate(), MDateUtil.formatDate));
			return m;
		}).collect(Collectors.toList());
		return RetKit.okData(insv);
	}
	
	public RetKit getAllMsg(Integer orgId) {
		Map<String,Object> rt = new HashMap<>();
		List<Project> sp = projectR.findAllByOrgId(orgId);
		List<Project> spc = sp.stream().filter((Project o)-> o.getStatus().equals(ProjectStatusEnum.COMPLETE.getId())).collect(Collectors.toList());
		BigDecimal allInvest = new BigDecimal(0);
		for (Project project : sp) {
			String ii= StrKit.isBlank(project.getInvest())?"0":project.getInvest();
			BigDecimal ss =new BigDecimal(ii);
			allInvest = allInvest.add(ss);
		}
		List<People> allPeople = peopleR.findAllByOrgId(orgId);
		rt.put("allProject", sp.size());
		rt.put("allInvest", allInvest);
		rt.put("allComProject", spc.size());
		rt.put("allPeople", allPeople.size());
		return RetKit.okData(rt);
	}

	public RetKit getAllProject(Integer orgId,String status,String search) {
		List<Map<String,Object>> gs = new ArrayList<>();
		if(StrKit.notBlank(status) && (Integer.parseInt(status))!=0 && StrKit.notBlank(search)) {
			gs = projectR.getAllProjectOfOrgIdAndSearch(orgId, Integer.parseInt(status),search);
		}else if(StrKit.notBlank(status) && (Integer.parseInt(status))!=0 && StrKit.isBlank(search)){
			gs = projectR.getAllProjectOfOrgIdAndStatus(orgId, Integer.parseInt(status));
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
				pv.setStatusStr(ProjectStatusEnum.getByValue(pv.getStatus()).getText());
				return pv;
			}).sorted(Comparator.comparing(ProjectVo::getStartDate)).collect(Collectors.toList());
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
			pv.setMaturityStr(pv.getMaturity().equals(1)?"加快前期":pv.getMaturity().equals(2)?"新开工":pv.getMaturity().equals(3)?"续建":"竣工");
			String vs = pv.getVisibleRange();
			String visibleRangeStr = "";
			if(StrKit.notBlank(vs)) {
				vs = vs.substring(1,vs.length()-1);
				for (String j : vs.split(",")) {
					Org p = orgR.getById(Integer.parseInt(j));
					if(p!=null) {
						visibleRangeStr += "、,"+p.getName();
					}
				}
			}
			pv.setVisibleRangeStr(StrKit.notBlank(visibleRangeStr)?visibleRangeStr.substring(1) : visibleRangeStr);
			String jos = pv.getJoiners();
			String joinersStr = "";
			if(StrKit.notBlank(jos)) {
				jos = jos.substring(1,jos.length()-1);
				for (String j : jos.split(",")) {
					People p = peopleR.getById(Integer.parseInt(j));
					if(p!=null) {
						joinersStr += "、,"+p.getName();
					}
				}
			}
			pv.setJoinersStr(StrKit.notBlank(joinersStr)?joinersStr.substring(1) : joinersStr);
			return RetKit.okData(pv);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}
	
	public RetKit getProjectAboutSHB(Integer projectId) {
		Project p = projectR.findById(projectId).get();
		List<Map<String,Object>> cs_ = taskR.getByProIdAndShb(projectId);
		try {
			List<Map<String,Object>> rt = new ArrayList<>();
			List<TaskVo> cs = BeanKit.changeToListBean(cs_, TaskVo.class);
			for (TaskVo taskVo : cs) {
				Map<String,Object> rtm = new HashMap<>();
				rtm.put("number",taskVo.getShb());
				rtm.put("name",taskVo.getShbName());
				rtm.put("setp1",taskVo.getStep1());
				rtm.put("setp2",taskVo.getStep2());
				rtm.put("setp3",taskVo.getStep3());
				int i = 0;
				switch (taskVo.getShb()) {
				case 1:
					if(p.getLxIsComapprove()!=null) {
						i = 1;
					}
					if(p.getLxHandleLevel()!=null) {
						i = 2;
					}
					if(p.getLxIsSendappdepart()!=null) {
						i = 3;
					}
					break;
				case 2:
					if(p.getYdcardIsHascard()!=null) {
						i = 1;
					}
					if(p.getYdcardHandleLevel()!=null) {
						i = 2;
					}
					if(p.getYdcardIsSendappdepart()!=null) {
						i = 3;
					}
					break;
				case 3:
					if(p.getEnergyIsCensor()!=null) {
						i = 1;
					}
					if(p.getEnergyHandleLevel()!=null) {
						i = 2;
					}
					if(p.getEnergyIsSendappdepart()!=null) {
						i = 3;
					}
					break;
				case 4:
					if(p.getLcIsBl()!=null) {
						i = 1;
					}
					if(p.getLcHandleLevel()!=null) {
						i = 2;
					}
					if(p.getLcIsSendappdepart()!=null) {
						i = 3;
					}
					break;
				case 5:
					if(p.getTdIsBl()!=null) {
						i = 1;
					}
					if(p.getTdHandleLevel()!=null) {
						i = 2;
					}
					if(p.getTdIsSendappdepart()!=null) {
						i = 3;
					}
					break;
				case 6:
					if(p.getEnvirIsBl()!=null) {
						i = 1;
					}
					if(p.getEnvirHandleLevel()!=null) {
						i = 2;
					}
					if(p.getEnvirIsSendappdepart()!=null) {
						i = 3;
					}
					break;
				case 7:
					if(p.getSgIsBl()!=null) {
						i = 1;
					}
					if(p.getSgHandleLevel()!=null) {
						i = 2;
					}
					if(p.getSgIsSendappdepart()!=null) {
						i = 3;
					}
					break;
				case 8:
					if(p.getXfIsBl()!=null) {
						i = 1;
					}
					if(p.getXfHandleLevel()!=null) {
						i = 2;
					}
					if(p.getXfIsSendappdepart()!=null) {
						i = 3;
					}
					break;
				case 9:
					if(p.getRfIsBl()!=null) {
						i = 1;
					}
					if(p.getRfHandleLevel()!=null) {
						i = 2;
					}
					if(p.getRfIsSendappdepart()!=null) {
						i = 3;
					}
					break;
				default:
					break;
				}
				rtm.put("index",i);
				rt.add(rtm);
			}
			return RetKit.okData(rt);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
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
	
	public RetKit getAllOrgs() {
		List<Org> orgs = orgR.findAllByStatus(1);
		return RetKit.okData(orgs);
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
		List<People> ps = peopleR.findAllByOrgIdInAndStatus(orgIdList,1);
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

	public RetKit getFileList(Integer projectId,Integer pn,Integer ps) {
		Map<String,Object> rt = new HashMap<>();
		List<Annex> allFiles = new ArrayList<>();
		List<Task> tasks = taskR.findAllByProIdAndAnnexIsNotNull(projectId);
		for (Task task : tasks) {
			String annexS = task.getAnnex();
			if(StrKit.notBlank(annexS)) {
				List<Annex> annexs = JSONObject.parseArray(annexS, Annex.class);
				for (Annex annex : annexs) {
					annex.setTaskName(task.getTitle());
				}
				allFiles.addAll(annexs);
			}
		}
		Integer total = allFiles.size();
		allFiles = allFiles.stream().skip((pn-1)*ps).limit(ps).collect(Collectors.toList());
		rt.put("pn", pn);
		rt.put("ps", ps);
		rt.put("total", total);
		rt.put("list", allFiles);
		return RetKit.okData(rt);
	}


	/**
	 *  ---------------------  甘特图  -------------------------
	 */
	public RetKit getProjectGanttData(Integer proId) {
		// TODO Auto-generated method stub
		List<Task> tasks = taskR.findAllByProId(proId);
		
		
		return null;
	}

	
	
	/**
	 * -----------------------------报表-------------------------------- 
	 * @return
	 */
	public RetKit getProjectForm(Integer pn,Integer ps,Integer orgId, Integer status, String search) {
		List<Project> prs = new ArrayList<>();
		if(status == null) {
			prs = projectR.findAllByOrgId(orgId);
		}else {
			prs = projectR.findAllByOrgIdAndStatus(orgId, status);
		}
		Map<String,Object> rt = new HashMap<>();
		Integer total = prs.size();
		prs = prs.stream().skip((pn-1)*ps).limit(ps).collect(Collectors.toList());
		List<ProjectVo> pvs = BeanKit.copyBeanList(prs, ProjectVo.class);
		final List<Industry> cas = industryR.findAll();
		pvs = pvs.stream().map((ProjectVo pv)->{
			List<String> categoryNames = comService.getIndustryParentName(industryR.findById(pv.getIndustryCategory()).get(),cas);
			String categoryName = categoryNames.stream().collect(Collectors.joining(">"));
			pv.setCategoryName(categoryName);
			pv.setDockingDateStr(pv.getDockingDate()==null?"":MDateUtil.dateToString(pv.getDockingDate(), MDateUtil.formatDate));
			return pv;
		}).collect(Collectors.toList());
		rt.put("pn", pn);
		rt.put("ps", ps);
		rt.put("total", total);
		rt.put("list", prs);
		return RetKit.okData(rt);
	}







	
	
}
