package com.fgw.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.ProjectStatusEnum;
import com.fgw.project.constant.TaskStatusEnum;
import com.fgw.project.constant.URLConstant;
import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.Project;
import com.fgw.project.model.po.Task;
import com.fgw.project.model.po.Yj;
import com.fgw.project.model.vo.Annex;
import com.fgw.project.model.vo.ProjectVo;
import com.fgw.project.model.vo.TaskVo;
import com.fgw.project.model.vo.WxUserInfo;
import com.fgw.project.repository.IOrgRepository;
import com.fgw.project.repository.IPeopleRepository;
import com.fgw.project.repository.IProjectRepository;
import com.fgw.project.repository.ITaskRepository;
import com.fgw.project.repository.IYjRepository;
import com.fgw.project.util.BeanKit;
import com.fgw.project.util.MDateUtil;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;
import com.fgw.project.util.WxResult;

import cn.hutool.http.HttpUtil;

@Service
public class ApiService {

	@Value("${appid}")
	private String appid;
	@Value("${secret}")
	private String secret;
	@Autowired
	private IPeopleRepository peoR;
	@Autowired
	private IYjRepository yR;
	@Autowired
	private IProjectRepository projectR;
	@Autowired
	private IOrgRepository orgR;
	@Resource
	private ProjectService proService;
	@Autowired
	private ITaskRepository taskR;
	

//	@Autowired
//	private CacheManager cacheManager;

	/**
	 * ---------------------------------微信服务接口---------------------------------
	 */
	public RetKit getAccessToken() {
		try {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("appid", appid);
			paramMap.put("grant_type", "client_credential");
			paramMap.put("secret", secret);
			String result3 = HttpUtil.get(URLConstant.URL_GET_TOKKEN, paramMap);
			WxResult appuser = JSONObject.parseObject(result3, WxResult.class);
//			Cache tokenCache = cacheManager.getCache("accessToken");
//			tokenCache.put(openid+"_token", appuser.getAccessToken());

			if (appuser.getErrcode() != null && appuser.getErrcode() != 0) {
				return RetKit.fail(appuser.getErrmsg());
			}
			return RetKit.okData(appuser.getAccess_token());
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail("网络错误！");
		}
	}
	
	public RetKit addWxUserInfo(WxUserInfo userinfo, String openid) {
		try {
			People p = peoR.findByOpenid(openid);
			p.setNickName(userinfo.getNickName());
			p.setAvatarUrl(userinfo.getAvatarUrl());
			p.setGender(userinfo.getGender());
			peoR.save(p);
			return RetKit.ok("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail("网络错误！");
		}
	}

	
	/**
	 * ---------------------------------业务---------------------------------
	 */
	
	public RetKit getNoticeList(Integer orgId, Integer status,Integer peopleId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RetKit getYjList(Integer orgId, Integer status,Integer peopleId) {
		List<Yj> ys = yR.findAllByOrgId(orgId);
		if(status == null) {
			ys = yR.findAllByOrgId(orgId);
		}else {
			ys = yR.findAllByOrgIdAndStatus(orgId,status);
		}
		ys = ys.stream().filter((Yj y)->(StrKit.isBlank(y.getNoticePeople())?false:Arrays.asList(y.getNoticePeople().split(",")).contains(peopleId.toString()))).collect(Collectors.toList());
		return RetKit.okData(ys);
	}

	public RetKit getProjectList(Integer orgId, Integer status, Integer peopleId,String search) {
		List<Map<String,Object>> gs = new ArrayList<>();
		if(status!=null && status!=0 && StrKit.notBlank(search)) {
			gs = projectR.getAllProjectOfOrgIdAndSearch(orgId, status,search);
		}else if(status!=null && status !=0 && StrKit.isBlank(search)){
			gs = projectR.getAllProjectOfOrgIdAndStatus(orgId, status);
		}else if((status==null|| status==0 ) && StrKit.notBlank(search)){
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

	public RetKit getProject(Integer projectId) {
		Map<String,Object> gs = projectR.getProjectById(projectId);
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
					People p = peoR.getById(Integer.parseInt(j));
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

	public RetKit getFileList(Integer projectId, Integer pn, Integer ps) {
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
	private List<TaskVo> getTaskChild(TaskVo taskVo, List<TaskVo> all) {
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
		return RetKit.okData(rt);
	}
	
	
	public RetKit getExecutorList(Integer orgId) {
		if(orgId == null || orgId == 0) {
			return RetKit.fail("参数错误！");
		}
		List<People> ps = peoR.findAllByOrgIdAndStatus(orgId,1);
		return RetKit.okData(ps);
	}
	
//
//
//	/**
//	 * ---------------------------------业务---------------------------------
//	 */
//
//	public RetKit getUserDetail(String openid) {
//		try {
////			List<UserInfo> us = Db.use().find(Entity.create("handle_userinfo").set("openid", openid),UserInfo.class);
//			List<WxUserInfo> us = Db.use().query("SELECT info.*,r.room_name as room_name,r.room_type as roomType,r.room_status as roomStatus,r.room_code as roomCode,r.room_create_time as roomCreateTime "
//					+ " FROM kefu_customer info "
//					+ " left join kefu_room r on r.id = info.room_id "
//					+ " WHERE info.openid=?", WxUserInfo.class, openid);
//			
//			return us.size()>0 ?RetKit.okData(us.get(0)): RetKit.fail("用户不存在！");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return RetKit.fail("网络错误！");
//		}
//	}
//	
//	public RetKit getRoomList() {
//		try {
//			List<Room> rooms = Db.use().findAll(Entity.create("kefu_room"),Room.class);
//			rooms = rooms.stream().map((Room r )->{
//				r.setRoomImg(getphoto+r.getRoomImg());
//				return r;
//			}).collect(Collectors.toList());
//			return RetKit.okData(rooms);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return RetKit.fail("网络错误！");
//		}
//	}
//
//	public RetKit getKefuList() {
//		try {
//			List<WxUserInfo> us = Db.use().query("SELECT info.*,r.room_name as room_name,r.room_type as roomType,r.room_status as roomStatus,r.room_code as roomCode,r.room_create_time as roomCreateTime "
//					+ " FROM kefu_customer info "
//					+ " left join kefu_room r on r.id = info.room_id "
//					+ " WHERE info.room_id is not null ", WxUserInfo.class);
//			return us.size()>0 ?RetKit.okData(us): RetKit.fail("客服房间暂停服务！");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return RetKit.fail("网络错误！");
//		}
//	}
//
//	public RetKit isBusy(Integer roomCode,Integer roomId,String openid,Integer status) {
//		try {
//			List<Room> rooms = Db.use().findAll(Entity.create("kefu_room").set("room_code", roomCode),Room.class);
//			if(rooms.size()>0) {
//				Db.use().update(Entity.create("kefu_room").set("room_status", status), Entity.create("kefu_room").set("room_code", roomCode));
//				return RetKit.ok();
//			}else {
//				return RetKit.fail("该房间不存在！");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return RetKit.fail("网络错误！");
//		}
//	}

}
