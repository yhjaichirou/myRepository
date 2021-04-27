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

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.comparator.Comparators;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.ProjectStatusEnum;
import com.fgw.project.constant.TaskPriorityEnum;
import com.fgw.project.constant.TaskStageEnum;
import com.fgw.project.constant.TaskStatusEnum;
import com.fgw.project.constant.YjTypeEnum;
import com.fgw.project.model.po.Category;
import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.Project;
import com.fgw.project.model.po.Shb;
import com.fgw.project.model.po.Task;
import com.fgw.project.model.vo.Annex;
import com.fgw.project.model.vo.MenuVo;
import com.fgw.project.model.vo.ProjectVo;
import com.fgw.project.model.vo.TaskVo;
import com.fgw.project.repository.ICategoryRepository;
import com.fgw.project.repository.IGroupRepository;
import com.fgw.project.repository.IOrgRepository;
import com.fgw.project.repository.IPeopleRepository;
import com.fgw.project.repository.IProjectRepository;
import com.fgw.project.repository.IShbRepository;
import com.fgw.project.repository.ITaskRepository;
import com.fgw.project.util.BeanKit;
import com.fgw.project.util.MDateUtil;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;

import cn.hutool.core.date.DateUtil;

/**
 * 项目管理 服务
 * @author yhj
 * @date 2020年12月21日
 */
@Service
public class TaskService {

	@Autowired
	private IProjectRepository projectR;
	@Autowired
	private IOrgRepository orgR;
	@Autowired
	private ICategoryRepository categoryR;
	@Autowired
	private IPeopleRepository peopleR;
	@Autowired
	private IShbRepository shbR;
	@Autowired
	private ITaskRepository taskR;
	@Resource
	private CommonService comService;
	
	public RetKit getAllTaskList(Integer projectId,Integer typeId) {
		Map<String,Object> rtss = new HashMap<>();
		List<Map<String,Object>> tr = new ArrayList<>();
		List<Map<String,Object>> cs_ = new ArrayList<>();
		if(typeId!=null && typeId!=10) {
			cs_ = taskR.getByProIdAndStatus(projectId,typeId);
		}else {
			cs_ = taskR.getByProId(projectId);
		}
		try {
			List<TaskVo> cs = BeanKit.changeToListBean(cs_, TaskVo.class);
			cs.stream().map((TaskVo tv)->{
				tv.setStartDateStr(tv.getStartDate()==null?"":MDateUtil.dateToString(tv.getStartDate(), MDateUtil.formatDate));
				tv.setStatusStr(TaskStatusEnum.getByValue(tv.getStatus()).getText());
				boolean isE = true;
				
				Date now = new Date();
				//当前时间大于开始日期无法修改
				if(now.after(tv.getStartDate())) {
					isE = false;
				}
				tv.setEdit(isE);
				boolean isD = true;
				List<Task> chilTask = taskR.findAllByPid(tv.getId());
				if(now.after(tv.getStartDate()) || tv.getStatus() > TaskStatusEnum.NOMACK.getId() || chilTask.size()>0) {
					isD = false;
				}
				tv.setDel(isD);
				return tv;
			}).collect(Collectors.toList());
			
			List<TaskVo> firstTasks =  cs.stream().filter((TaskVo ttt)-> ttt.getPid().equals(0)).collect(Collectors.toList());
			for (TaskVo taskVo : firstTasks) {
				List<TaskVo> ctasks = getTaskChild(taskVo,cs);
				if(ctasks!=null && ctasks.size()>0) {
					taskVo.setChildren(ctasks);
				}
			}
			
			Project p = projectR.findById(projectId).get();
			String stage = p.getStage();
			if(StrKit.notBlank(stage)) {
				stage = stage.substring(1,stage.length()-1);
				rtss.put("activeNamesTask", stage.split(","));
				for (String j : stage.split(",")) {
					Map<String,Object> gs = new HashMap<>();
					if(Integer.parseInt(j) == 1) {
						gs.put("id", "1");
						gs.put("name", "无所属阶段");
						List<TaskVo> no = firstTasks.stream().filter((TaskVo t )-> t.getStageId().equals(1)).collect(Collectors.toList());
						gs.put("value", no);
					}
					if(Integer.parseInt(j) == 2) {
						gs.put("id", "2");
						gs.put("name", "立项阶段");
						List<TaskVo> lx = firstTasks.stream().filter((TaskVo t )-> t.getStageId().equals(2)).collect(Collectors.toList());
						gs.put("value", lx);
					}
					if(Integer.parseInt(j) == 3) {
						gs.put("id", "3");
						gs.put("name", "执行阶段");
						List<TaskVo> zx = firstTasks.stream().filter((TaskVo t )-> t.getStageId().equals(3)).collect(Collectors.toList());
						
						gs.put("value", zx);
					}
					if(Integer.parseInt(j) == 4) {
						gs.put("id", "4");
						gs.put("name", "验收阶段");
						List<TaskVo> ys = firstTasks.stream().filter((TaskVo t )-> t.getStageId().equals(4)).collect(Collectors.toList());
						gs.put("value", ys);
					}
					tr.add(gs);
				}
			}
			rtss.put("list", tr);
			return RetKit.okData(rtss);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}
	public List<TaskVo> getTaskChild(TaskVo taskVo, List<TaskVo> all) {
		List<TaskVo> childList = new ArrayList<TaskVo>();
		for (TaskVo pb : all) {
			if (pb.getPid().equals(taskVo.getId())) {
				pb.setChildren(getTaskChild(pb,all));
				childList.add(pb);
			}
		}
		if (childList.size() <= 0) {
			return null;
		}else {
			childList = childList.stream().sorted(Comparator.comparing(TaskVo::getStartDate)).collect(Collectors.toList());
			return childList;
		}
	}
//	private List<TaskVo> getTaskChild(TaskVo taskVo, List<TaskVo> all) {
//		List<TaskVo> childList = new ArrayList<TaskVo>();
//		String preTaskStr = taskVo.getPreTasks();
//		if(StrKit.notBlank(preTaskStr) && !preTaskStr.substring(1, preTaskStr.length()-1).equals(0)) {//存在子任务
//			List<Integer> chil_ids = JSONObject.parseArray(preTaskStr, Integer.class);
//			if(chil_ids.size()>0) {
//				childList = all.stream().filter((TaskVo tb)-> chil_ids.contains(tb.getId())).sorted(Comparator.comparing(TaskVo::getStartDate)).collect(Collectors.toList());
//				for (TaskVo child : childList) {
//					List<TaskVo> ccs = getTaskChild(child,all);
//					if(ccs!=null && ccs.size()>0) {
//						child.setChildren(ccs);
//					}
//				}
//				taskVo.setChildren(childList);
//				return childList;
//			}
//		}
//		return null;
//	}
	
	public RetKit getAllCountMap(Integer projectId) {
		Map<String,Object> rtss = new HashMap<>();
		List<Map<String,Object>>  cs_ = taskR.getByProId(projectId);
		try {
			List<TaskVo> cs = BeanKit.changeToListBean(cs_, TaskVo.class);
			List<TaskVo> NOMACK = cs.stream().filter((TaskVo tv)->tv.getStatus().equals(TaskStatusEnum.NOMACK.getId())).collect(Collectors.toList());
			List<TaskVo> YESMACK = cs.stream().filter((TaskVo tv)->tv.getStatus().equals(TaskStatusEnum.YESMACK.getId())).collect(Collectors.toList());
			List<TaskVo> NOCOM = cs.stream().filter((TaskVo tv)->tv.getStatus().equals(TaskStatusEnum.NOCOM.getId())).collect(Collectors.toList());
			List<TaskVo> COMPLETE = cs.stream().filter((TaskVo tv)->tv.getStatus().equals(TaskStatusEnum.COMPLETE.getId())).collect(Collectors.toList());
			List<TaskVo> DELAY = cs.stream().filter((TaskVo tv)->tv.getStatus().equals(TaskStatusEnum.DELAY.getId())).collect(Collectors.toList());
			List<TaskVo> OVERDUE = cs.stream().filter((TaskVo tv)->tv.getStatus().equals(TaskStatusEnum.OVERDUE.getId())).collect(Collectors.toList());
			rtss.put("ALL", cs.size());
			rtss.put("NOMACK", NOMACK.size());
			rtss.put("YESMACK", YESMACK.size());
			rtss.put("NOCOM", NOCOM.size());
			rtss.put("COMPLETE", COMPLETE.size());
			rtss.put("DELAY", DELAY.size());
			rtss.put("OVERDUE", OVERDUE.size());
			return RetKit.okData(rtss);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}
	
	public RetKit getTask(Integer id) {
		Map<String,Object> gs = taskR.getTaskById(id);
		try {
			TaskVo pv = BeanKit.changeRecordToBean(gs, TaskVo.class);
			if(StrKit.notBlank(pv.getAnnex())) {
				List<Annex> annexs = JSONObject.parseArray(pv.getAnnex(), Annex.class);
				pv.setFileInfos(annexs);
			}else {
				pv.setFileInfos(new ArrayList<>());
			}
			List<Map<String, Object>> childMs = taskR.getByProIdAndPid(pv.getProId(),id);
			List<TaskVo> childs = BeanKit.changeToListBean(childMs, TaskVo.class);
			childs = childs.stream().map((TaskVo tv)->{
				tv.setStartDateStr(tv.getStartDate()==null?"":MDateUtil.dateToString(tv.getStartDate(), MDateUtil.formatDate));
				tv.setStatusStr(TaskStatusEnum.getByValue(tv.getStatus()).getText());
				return tv;
			}).collect(Collectors.toList());
			pv.setChildTask(childs);
			pv.setStartDateStr(pv.getStartDate()==null?"":MDateUtil.dateToString(pv.getStartDate(), MDateUtil.formatDate));
			pv.setEndDateStr(pv.getEndDate()==null?"":MDateUtil.dateToString(pv.getEndDate(), MDateUtil.formatDate));
			pv.setPriorityStr(pv.getPriority().equals(1)?"一级":pv.getPriority().equals(2)?"二级":pv.getPriority().equals(3)?"三级":"无");
			pv.setStageStr(pv.getStageId().equals(1)?"无":pv.getStageId().equals(2)?"立项阶段":pv.getStageId().equals(3)?"执行阶段":"验收阶段");
			pv.setStatusStr(TaskStatusEnum.getByValue(pv.getStatus()).getText());
			return RetKit.okData(pv);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}
	
	//任务拆分
	public RetKit getAllTaskFormParam(Integer projectId) {
		if(projectId ==null) {
			return RetKit.fail("获取表单参数失败！");
		}
		Map<String,Object> rt = new HashMap<>();
		List<Task> cs = new ArrayList<>();
		List<Integer> statuss = new ArrayList<>();
		statuss.add(TaskStatusEnum.NOMACK.getId());
		statuss.add(TaskStatusEnum.YESMACK.getId());
		statuss.add(TaskStatusEnum.NOCOM.getId());
		Task tne = new Task();
		tne.setId(0);
		tne.setTitle("无");
		cs.add(tne);
		cs.addAll(taskR.findByProIdAndStatusIn(projectId,statuss));
		rt.put("preTasks", cs);
		
		Project p = projectR.findById(projectId).get();
		String stage = p.getStage();
		if(StrKit.notBlank(stage)) {
			stage = stage.substring(1,stage.length()-1);
			List<Map<String,Object>> tt = new ArrayList<>();
			for (String j : stage.split(",")) {
				Map<String,Object> gs = new HashMap<>();
				if(Integer.parseInt(j) == 1) {
					gs.put("id", 1);
					gs.put("name", "无所属阶段");
				}
				if(Integer.parseInt(j) == 2) {
					gs.put("id", 2);
					gs.put("name", "立项阶段");
				}
				if(Integer.parseInt(j) == 3) {
					gs.put("id", 3);
					gs.put("name", "执行阶段");
				}
				if(Integer.parseInt(j) == 4) {
					gs.put("id", 4);
					gs.put("name", "验收阶段");
				}
				tt.add(gs);
			}
			rt.put("stages", tt);
		}else {
			rt.put("stages", new ArrayList<>());
		}
		List<Shb> shbList = shbR.findAll();
		rt.put("shbList", shbList);
		return RetKit.okData(rt);
	}

	public RetKit addTask(String param) {
		JSONObject jb = JSONObject.parseObject(param);
		Integer id = jb.getInteger("id");
		Integer pid = jb.getInteger("pid");
		String title = jb.getString("title");
		Integer orgId = jb.getInteger("orgId");
		Integer proId = jb.getInteger("proId");
		Integer executOrg = jb.getInteger("executOrg");
		Integer executor = jb.getInteger("executor");
		String executorMobile = jb.getString("executorMobile");
		Integer stageId = jb.getInteger("stageId");
		Date startDate = MDateUtil.stringToDate(jb.getString("startDate"), MDateUtil.formatDate);
		Date endDate = MDateUtil.stringToDate(jb.getString("endDate"), MDateUtil.formatDate);
		Integer priority = jb.getInteger("priority");
		String isShb = jb.getString("isShb");
		Integer shb = jb.getInteger("shb");
//		Integer status = jb.getInteger("status");
		String remark = jb.getString("remark");
//		String annex = jb.getString("annex");
//		String preTasks = jb.getString("preTasks");
		
		Task t = new Task();
		if(id!=null) {
			Optional<Task> t_ = taskR.findById(id);
			if(t_.isPresent()) {
				t = t_.get();
			}
		}else {
			t.setOrgId(orgId);
			t.setProId(proId);
			Project p = projectR.findById(proId).get();
			String prefix = StrKit.notBlank(p.getTaskPrefix())?p.getTaskPrefix():"TASK";
			t.setCode(prefix+"-"+DateUtil.date().toDateStr()+StrKit.getRandomString(6));
			t.setCreateDate(new Date());
		}
		t.setPid(pid==null?0:pid);
		t.setExecutOrg(executOrg);
		t.setTitle(title);
		t.setExecutor(executor);
		t.setExecutorMobile(executorMobile);
		t.setStageId(stageId);
		t.setStartDate(startDate);
		t.setEndDate(endDate);
		t.setPriority(priority);
		t.setStatus(executor!=null?TaskStatusEnum.YESMACK.getId():TaskStatusEnum.NOMACK.getId());
		t.setRemark(remark);
		t.setShb(shb);
		t.setIsShb(isShb);
//		t.setPreTasks(preTasks);
		taskR.save(t);
		return RetKit.okData(t.getId());
	}


	public RetKit deleteTask(Integer taskId) {
		Optional<Task> t_ = taskR.findById(taskId);
		if(t_.isPresent()) {
			Task p = t_.get();
			if(p.getStatus()!=ProjectStatusEnum.NEW.getId()) {
				return RetKit.fail("任务处于不可删除状态！");
			}
			if(StrKit.notBlank(p.getPreTasks())) {
				List<Task> ts = taskR.findAllByProId(p.getProId());
				for (Task othert : ts) {
					if(StrKit.notBlank(othert.getPreTasks())) {
						String prtasks = othert.getPreTasks().substring(1,othert.getPreTasks().length()-1);
						String[] tarr = prtasks.split(",");
						for (int i = 0; i < tarr.length; i++) {
							if(Integer.parseInt(tarr[i]) == p.getId()) {
								return RetKit.fail("该任务为“"+othert.getTitle()+"”的前置任务无法删除！");
							}
						}
					}
				}
			}
			taskR.deleteById(taskId);
			return RetKit.ok("删除成功！");
		}
		return RetKit.fail("提交失败，任务不存在！");
	}

	public RetKit getExecutorList(Integer orgId) {
		if(orgId == null || orgId == 0) {
			return RetKit.fail("参数错误！");
		}
		List<People> ps = peopleR.findAllByOrgIdAndStatus(orgId,1);
		return RetKit.okData(ps);
	}

	public RetKit getAllTasksOfProject(Integer orgId,Integer projectId) {
		List<Map<String,Object>> cs_ = taskR.getByProId(projectId);
		try {
			List<TaskVo> cs = BeanKit.changeToListBean(cs_, TaskVo.class);
			cs.stream().map((TaskVo tv)->{
				tv.setStartDateStr(tv.getStartDate()==null?"":MDateUtil.dateToString(tv.getStartDate(), MDateUtil.formatDate));
				tv.setEndDateStr(tv.getEndDate()==null?"":MDateUtil.dateToString(tv.getEndDate(), MDateUtil.formatDate));
				tv.setComDateStr(tv.getComDate()==null?"":MDateUtil.dateToString(tv.getComDate(), MDateUtil.formatDate));
				tv.setStatusStr(TaskStatusEnum.getByValue(tv.getStatus()).getText());
				tv.setStageStr(TaskStageEnum.getByValue(tv.getStageId()).getText());
				tv.setPriorityStr(TaskPriorityEnum.getByValue(tv.getPriority()).getText());
				return tv;
			}).sorted(Comparator.comparing(TaskVo::getOrgId,(x,y)->{
				if(x == orgId) {
					return 0;
				}else {
					return 1;
				}
			}).thenComparing(TaskVo::getStartDate)).collect(Collectors.toList());
			return RetKit.okData(cs);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}

	public RetKit taskMyList(Integer orgId, Integer projectId) {
		List<Map<String,Object>> cs_ = taskR.getByProId(orgId,projectId);
		try {
			List<TaskVo> cs = BeanKit.changeToListBean(cs_, TaskVo.class);
			cs.stream().map((TaskVo tv)->{
				tv.setStartDateStr(tv.getStartDate()==null?"":MDateUtil.dateToString(tv.getStartDate(), MDateUtil.formatDate));
				tv.setEndDateStr(tv.getEndDate()==null?"":MDateUtil.dateToString(tv.getEndDate(), MDateUtil.formatDate));
				tv.setComDateStr(tv.getComDate()==null?"":MDateUtil.dateToString(tv.getComDate(), MDateUtil.formatDate));
				tv.setStatusStr(TaskStatusEnum.getByValue(tv.getStatus()).getText());
				tv.setStageStr(TaskStageEnum.getByValue(tv.getStageId()).getText());
				tv.setPriorityStr(TaskPriorityEnum.getByValue(tv.getPriority()).getText());
				return tv;
			}).sorted(Comparator.comparing(TaskVo::getStartDate)).collect(Collectors.toList());
			return RetKit.okData(cs);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}

	
	//执行任务
	
	/**
	 * 删除附件内容
	 * @param taskId
	 * @param fileId
	 * @return
	 */
	public RetKit fileDelete(Integer taskId, Integer fileId) {
		try {
			Optional<Task> cs_ = taskR.findById(taskId);
			if(cs_.isPresent()) {
				Task ta = cs_.get();
				String annex = ta.getAnnex();
				List<Annex> anxs =  StrKit.notBlank(annex)?JSONObject.parseArray(annex, Annex.class):new ArrayList<>();
				List<Annex> newanxs = new ArrayList<>(); 
				for (int i = 0; i < anxs.size(); i++) {
					if(!(anxs.get(i).getId()).equals(fileId)) {
						newanxs.add(anxs.get(i));
					}
				}
				String annexNewStr =  "";
				if(newanxs.size()>0) {
					annexNewStr = JSONObject.toJSONString(newanxs);
				}
				ta.setAnnex(annexNewStr);
				taskR.save(ta);
			}
			return RetKit.okData("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}

	@Transactional
	public RetKit confirmTask(String param) {
		JSONObject jb = JSONObject.parseObject(param);
		Integer id = jb.getInteger("id");
		if(id!=null) {
			Optional<Task> t_ = taskR.findById(id);
			if(t_.isPresent()) {
				Task task = t_.get();
				Optional<Project> pro_ = projectR.findById(task.getProId());
				if(pro_.isPresent()) {
					String comContent = jb.getString("comContent");
					String fileInfos = jb.getString("fileInfos");
					Integer shb = jb.getInteger("shb");
					task.setComDate(new Date());
					task.setComContent(comContent);
					task.setAnnex(fileInfos);
					task.setStatus(TaskStatusEnum.COMPLETE.getId());
					taskR.save(task);
					
					if(StrKit.notBlank(task.getIsShb()) && task.getIsShb().equals("是")) {
						Project pro = pro_.get();
						switch (shb) {
						case 1:
							String lxIsComapprove = jb.getString("lxIsComapprove");
							String lxHandleLevel = jb.getString("lxHandleLevel");
							String lxIsSendappdepart = jb.getString("lxIsSendappdepart");
							String lxBao = jb.getString("lxBao");
							String lxBaoNoMsg = jb.getString("lxBaoNoMsg");
							pro.setLxIsComapprove(lxIsComapprove);
							pro.setLxHandleLevel(lxHandleLevel);
							pro.setLxIsSendappdepart(lxIsSendappdepart);
							pro.setLxBao(lxBao);
							pro.setLxBaoNoMsg(lxBaoNoMsg);
							break;
						case 2:
							String ydcardIsHascard = jb.getString("ydcardIsHascard");
							String ydcardHandleLevel = jb.getString("ydcardHandleLevel");
							String ydcardIsSendappdepart = jb.getString("ydcardIsSendappdepart");
							String ydBao = jb.getString("ydBao");
							String ydBaoNoMsg = jb.getString("ydBaoNoMsg");
							pro.setYdcardIsHascard(ydcardIsHascard);
							pro.setYdcardHandleLevel(ydcardHandleLevel);
							pro.setYdcardIsSendappdepart(ydcardIsSendappdepart);
							pro.setYdBao(ydBao);
							pro.setYdBaoNoMsg(ydBaoNoMsg);	
							break;
						case 3:
							String energyHandleLevel = jb.getString("energyHandleLevel");
							String energyIsCensor = jb.getString("energyIsCensor");
							String energyIsSendappdepart = jb.getString("energyIsSendappdepart");
							String energyBao = jb.getString("energyBao");
							String energyBaoNoMsg = jb.getString("energyBaoNoMsg");
							pro.setEnergyHandleLevel(energyHandleLevel);
							pro.setEnergyIsCensor(energyIsCensor);
							pro.setEnergyIsSendappdepart(energyIsSendappdepart);
							pro.setEnergyBao(energyBao);
							pro.setEnergyBaoNoMsg(energyBaoNoMsg);
							break;
						case 4:
							String lcHandleLevel = jb.getString("lcHandleLevel");
							String lcIsBl = jb.getString("lcIsBl");
							String lcIsSendappdepart = jb.getString("lcIsSendappdepart");
							String lcBao = jb.getString("lcBao");
							String lcBaoNoMsg = jb.getString("lcBaoNoMsg");
							pro.setLcHandleLevel(lcHandleLevel);
							pro.setLcIsBl(lcIsBl);
							pro.setLcIsSendappdepart(lcIsSendappdepart);
							pro.setLcBao(lcBao);
							pro.setLcBaoNoMsg(lcBaoNoMsg);
							break;
						case 5:
							String tdProvide = jb.getString("tdProvide");
							String tdHandleLevel = jb.getString("tdHandleLevel");
							String tdIsBl = jb.getString("tdIsBl");
							String tdIsSendappdepart = jb.getString("tdIsSendappdepart");
							String tdBao = jb.getString("tdBao");
							String tdBaoNoMsg = jb.getString("tdBaoNoMsg");
							pro.setTdIsBl(tdIsBl);
							pro.setTdHandleLevel(tdHandleLevel);
							pro.setTdIsSendappdepart(tdIsSendappdepart);
							pro.setTdBao(tdBao);
							pro.setTdBaoNoMsg(tdBaoNoMsg);
							pro.setTdProvide(tdProvide);
							break;
						case 6:
							String envirHandleLevel = jb.getString("envirHandleLevel");
							String envirIsBl = jb.getString("envirIsBl");
							String envirIsSendappdepart = jb.getString("envirIsSendappdepart");
							String envirBao = jb.getString("envirBao");
							String envirBaoNoMsg = jb.getString("envirBaoNoMsg");
							pro.setEnvirHandleLevel(envirHandleLevel);
							pro.setEnvirIsBl(envirIsBl);
							pro.setEnvirIsSendappdepart(envirIsSendappdepart);
							pro.setEnvirBao(envirBao);
							pro.setEnvirBaoNoMsg(envirBaoNoMsg);
							break;
						case 7:
							String sgHandleLevel = jb.getString("sgHandleLevel");
							String sgIsBl = jb.getString("sgIsBl");
							String sgIsSendappdepart = jb.getString("sgIsSendappdepart");
							String sgBao = jb.getString("sgBao");
							String sgBaoNoMsg = jb.getString("sgBaoNoMsg");
							pro.setSgHandleLevel(sgHandleLevel);
							pro.setSgIsBl(sgIsBl);
							pro.setSgIsSendappdepart(sgIsSendappdepart);
							pro.setSgBao(sgBao);
							pro.setSgBaoNoMsg(sgBaoNoMsg);
							break;
						case 8:
							String xfHandleLevel = jb.getString("xfHandleLevel");
							String xfIsBl = jb.getString("xfIsBl");
							String xfIsSendappdepart = jb.getString("xfIsSendappdepart");
							pro.setXfHandleLevel(xfHandleLevel);
							pro.setXfIsBl(xfIsBl);
							pro.setXfIsSendappdepart(xfIsSendappdepart);
							break;
						case 9:
							String rfHandleLevel = jb.getString("rfHandleLevel");
							String rfIsBl = jb.getString("rfIsBl");
							String rfIsSendappdepart = jb.getString("rfIsSendappdepart");
							pro.setRfHandleLevel(rfHandleLevel);
							pro.setRfIsBl(rfIsBl);
							pro.setRfIsSendappdepart(rfIsSendappdepart);
							break;
						default:
							break;
						}
						projectR.save(pro);
					}
				}else {
					return RetKit.fail("项目异常！");
				}
//				isComParentTask(task.getPid());
			}else {
				return RetKit.fail("任务不存在！");
			}
		}else {
			return RetKit.fail("任务ID不能为空！");
		}
		return RetKit.okData("完成任务！");
	}
	
	
	
//	private void isComParentTask(Integer pid) throws Exception {
//		if(pid!=0) {
//			Optional<Task> t_ = taskR.findById(pid);
//			if(t_.isPresent()) {
//				Task task = t_.get();
//				String comContent = "自动完成";
//				String fileInfos = "";
//				task.setComDate(new Date());
//				task.setComContent(comContent);
//				task.setAnnex(fileInfos);
//				task.setStatus(TaskStatusEnum.COMPLETE.getId());
//				taskR.save(task);
//				isComParentTask(task.getPid());
//			}else {
//				throw new RuntimeException("暂无父类任务可完成");
//			}
//		}
//	}

	/**
	 *	 任务督办
	 * @param taskId
	 * @return
	 */
	public RetKit taskDb(Integer taskId) {
		Optional<Task> t_ = taskR.findById(taskId);
		if(t_.isPresent()) {
			Task task = t_.get();
			Optional<Project> pro_ = projectR.findById(task.getProId());
			if(pro_.isPresent()) {
				Integer dbcount = task.getDbCount()==null?0:task.getDbCount();
				task.setDbCount(dbcount+1);
				task.setDbDate(new Date());
				taskR.save(task);
				Project pro = pro_.get();
				String stip = "项目《"+pro.getName()+"》关于任务“"+task.getTitle()+"”督办通知！请尽快完成任务！";
				comService.sendNotice(task.getExecutorMobile(),YjTypeEnum.TASK.getText(),stip);
				return RetKit.ok("已通知任务负责人！");
			}
			return RetKit.fail("项目不存在！");
		}else {
			return RetKit.fail("任务不存在！");
		}
		
	}

	
	
}
