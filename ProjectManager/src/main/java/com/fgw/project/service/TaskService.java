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
import org.springframework.util.comparator.Comparators;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.ProjectStatusEnum;
import com.fgw.project.constant.TaskPriorityEnum;
import com.fgw.project.constant.TaskStageEnum;
import com.fgw.project.constant.TaskStatusEnum;
import com.fgw.project.model.po.Category;
import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.Project;
import com.fgw.project.model.po.Task;
import com.fgw.project.model.vo.Annex;
import com.fgw.project.model.vo.ProjectVo;
import com.fgw.project.model.vo.TaskVo;
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
	private ITaskRepository taskR;
	
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
				return tv;
			}).collect(Collectors.toList());
			
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
						List<TaskVo> no = cs.stream().filter((TaskVo t )-> t.getStageId().equals(1)).collect(Collectors.toList());
						gs.put("value", no);
					}
					if(Integer.parseInt(j) == 2) {
						gs.put("id", "2");
						gs.put("name", "立项阶段");
						List<TaskVo> lx = cs.stream().filter((TaskVo t )-> t.getStageId().equals(2)).collect(Collectors.toList());
						gs.put("value", lx);
					}
					if(Integer.parseInt(j) == 3) {
						gs.put("id", "3");
						gs.put("name", "执行阶段");
						List<TaskVo> zx = cs.stream().filter((TaskVo t )-> t.getStageId().equals(3)).collect(Collectors.toList());
						
						gs.put("value", zx);
					}
					if(Integer.parseInt(j) == 4) {
						gs.put("id", "4");
						gs.put("name", "验收阶段");
						List<TaskVo> ys = cs.stream().filter((TaskVo t )-> t.getStageId().equals(4)).collect(Collectors.toList());
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
		Task tne = new Task();
		tne.setId(0);
		tne.setTitle("无");
		cs.add(tne);
		cs.addAll(taskR.findByProIdAndStatus(projectId,0));
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
		return RetKit.okData(rt);
	}

	public RetKit addTask(String param) {
		JSONObject jb = JSONObject.parseObject(param);
		Integer id = jb.getInteger("id");
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
//		Integer status = jb.getInteger("status");
		String remark = jb.getString("remark");
//		String annex = jb.getString("annex");
		String preTasks = jb.getString("preTasks");
		
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
		t.setExecutOrg(executOrg);
		t.setTitle(title);
		t.setExecutor(executor);
		t.setExecutorMobile(executorMobile);
		t.setStageId(stageId);
		t.setStartDate(startDate);
		t.setEndDate(endDate);
		t.setPriority(priority);
		t.setStatus(executor!=null?1:0);
		t.setRemark(remark);
		t.setPreTasks(preTasks);
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

	public RetKit confirmTask(String param) {
		JSONObject jb = JSONObject.parseObject(param);
		Integer id = jb.getInteger("id");
		if(id!=null) {
			Optional<Task> t_ = taskR.findById(id);
			if(t_.isPresent()) {
				Task task = t_.get();
				String comContent = jb.getString("comContent");
				String fileInfos = jb.getString("fileInfos");
				task.setComDate(new Date());
				task.setComContent(comContent);
				task.setAnnex(fileInfos);
				task.setStatus(TaskStatusEnum.COMPLETE.getId());
				taskR.save(task);
			}else {
				return RetKit.fail("任务不存在！");
			}
		}else {
			return RetKit.fail("任务ID不能为空！");
		}
		return RetKit.okData("完成任务！");
	}

	
	
	


	
	
}
