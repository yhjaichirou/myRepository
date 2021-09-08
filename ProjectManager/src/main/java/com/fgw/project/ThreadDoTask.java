package com.fgw.project;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.OtherConstant;
import com.fgw.project.constant.ProjectStatusEnum;
import com.fgw.project.constant.TaskStatusEnum;
import com.fgw.project.constant.YjTypeEnum;
import com.fgw.project.model.po.Config;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.Project;
import com.fgw.project.model.po.Task;
import com.fgw.project.model.po.User;
import com.fgw.project.model.po.Yj;
import com.fgw.project.repository.IConfigRepository;
import com.fgw.project.repository.IPeopleRepository;
import com.fgw.project.repository.IProjectRepository;
import com.fgw.project.repository.ITaskRepository;
import com.fgw.project.repository.IUserRepository;
import com.fgw.project.repository.IYjRepository;
import com.fgw.project.service.CommonService;
import com.fgw.project.service.YjService;
import com.fgw.project.util.MDateUtil;
import com.fgw.project.util.StrKit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;

@Component
@Service(value = "ThreadDoTask")
public class ThreadDoTask {

	private Log log = LogFactory.getLog(ThreadDoTask.class);

	
	@Autowired
	private IYjRepository yjR;
	@Autowired
	private IProjectRepository proR;
	@Autowired
	private ITaskRepository taskR;
	@Autowired
	private IConfigRepository configR;
	@Resource
	private YjService yjservice;
	@Resource
	private CommonService comService;
	@Autowired
	private IPeopleRepository peopleR;
	@Autowired
	private IUserRepository userR;
	
	/**
	 * 异步 - 钉钉用户删除单位，数据同步后台清理
	 */
	public void delDingUserMsg(String dingUserid) {
		Runnable run = () -> {
			try {
//				memberRepository.deleteUsers(dingUserid);
//				userRepository.deleteUsers(dingUserid);
//				dingRepository.deleteUsers(dingUserid);
//
//				// 增加操作记录
//				String remark = "【系统】数据同步清理人员信息！";
//				branchService.addSysOperRecord(remark);

			} catch (Exception e) {
				log.error("网络异常，删除用户信息失败！");
			}
		};
		ThreadUtil.execute(run);
	}
	
	/**
	 * 判断过期
	 * 
	 * @return
	 */
	@Transactional
	public void targetOverdue() {
		switch (YjTypeEnum.getByValue(1)) {
		case PROJECT:
			nearProject();
			break;
		case TASK: //任务临期提醒
			nearTask();
			break;
		case OVERDUE:
			overProject();
			break;
		case TASKOVERDUE://任务超期修改状态
			overTask();
			break;
		default:
			break;
		}
	}
	
	public void nearTask() {
		List<Integer> statuss = new ArrayList<>();
		statuss.add(TaskStatusEnum.NOCOM.getId());
		statuss.add(TaskStatusEnum.DELAY.getId());
		List<Task> ts = taskR.findAllByStatusIn(statuss);
		Date now = new Date();
		for (Task task : ts) {
			if(task.getStatus().equals(TaskStatusEnum.NOCOM.getId())) { //未完成任务
				if(now.before(task.getStartDate())) {//未超出 开始时间
					long neardays = DateUtil.between(now, task.getStartDate(), DateUnit.DAY);
					if(neardays<=1) {
						Optional<People> peo_ = peopleR.findById(task.getExecutor());
						People peo = null;
						if(peo_.isPresent()) {
							peo = peo_.get();
						}
						yjservice.addYjRecord(task.getOrgId(), task.getId(), YjTypeEnum.TASK.getId(), YjTypeEnum.TASK.getText(), "《"+task.getTitle()+"》" + YjTypeEnum.TASK.getSkip() + " "+MDateUtil.dateToString(now, null), task.getExecutorMobile(), peo==null?task.getExecutorMobile() : peo.getName());
						//通知
//						comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
					}
				}else {//逾期
					task.setStatus(TaskStatusEnum.OVERDUE.getId());
					taskR.save(task);
					//通知
//					comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
				}
			}else if(task.getStatus().equals(TaskStatusEnum.DELAY.getId())){//逾期任务
				if(now.before(task.getDelayDate())) {//未超出 开始时间
					long neardays = DateUtil.between(now, task.getStartDate(), DateUnit.DAY);
					if(neardays<=1) {
						Optional<People> peo_ = peopleR.findById(task.getExecutor());
						People peo = null;
						if(peo_.isPresent()) {
							peo = peo_.get();
						}
						yjservice.addYjRecord(task.getOrgId(), task.getId(), YjTypeEnum.TASK.getId(), YjTypeEnum.TASK.getText(), "《"+task.getTitle()+"》" + YjTypeEnum.TASK.getSkip() + " "+MDateUtil.dateToString(now, null), task.getExecutorMobile(), peo==null?task.getExecutorMobile() : peo.getName());
						//通知
//						comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
					}
				}else {//逾期
					task.setStatus(TaskStatusEnum.OVERDUE.getId());
					taskR.save(task);
					//通知
//					comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
				}
			}
		}
	}
	
	public void overTask() {
		List<Integer> statuss = new ArrayList<>();
		statuss.add(TaskStatusEnum.YESMACK.getId());
		statuss.add(TaskStatusEnum.NOCOM.getId());
		statuss.add(TaskStatusEnum.DELAY.getId());
		List<Task> ts = taskR.findAllByStatusIn(statuss);
		Date now = new Date();
		for (Task task : ts) {
			if(now.after(task.getStartDate()) && now.before(task.getEndDate())) {//超出 开始时间 小于结束时间
				task.setStatus(TaskStatusEnum.NOCOM.getId());
				taskR.save(task);
				//通知
//				comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
			}else if(now.after(task.getDelayDate()) ) {//超出结束时间
				task.setStatus(TaskStatusEnum.OVERDUE.getId());
				taskR.save(task);
				//通知
//				comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
			}else if(now.after(task.getEndDate()) ) {//超出结束时间
				task.setStatus(TaskStatusEnum.OVERDUE.getId());
				taskR.save(task);
				//通知
//				comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
			}
		}
	}
	
	public void nearProject() {
		List<Integer> statuss = new ArrayList<>();
		statuss.add(ProjectStatusEnum.RUNGING.getId());
		statuss.add(ProjectStatusEnum.DELAY.getId());
		List<Project> ps = proR.findAllByStatusIn(statuss);
		
		Date now = new Date();
		Optional<Config> c_ = configR.findById(1);
		Integer yjDaty = c_.isPresent()?c_.get().getYjDay():OtherConstant.yjDay;
		for (Project project : ps) {
			Date exDate = project.getExpectedDate();
			long betweenDay = DateUtil.between(DateUtil.date(), project.getExpectedDate(), DateUnit.DAY);
			if(now.before(exDate)) {//当前日期 小于  预计完成日期  //项目临期预警
				if(betweenDay<yjDaty) { //小于30天时开始预警
					String noticePeople = "",noticePeopleName="";//电话号码拼接
					Optional<People> pel_ = peopleR.findById(project.getLeader());
					if(pel_.isPresent()) {//牵头领导  ，  协调负责人
						People pel = pel_.get();
						noticePeople+=","+pel.getMobile();
						noticePeopleName+=","+pel.getName();
					}
					Optional<People> cpel_ = peopleR.findById(project.getCoordinate());
					if(cpel_.isPresent()) {
						People pel = cpel_.get();
						noticePeople+=","+pel.getMobile();
						noticePeopleName+=","+pel.getName();
					}
					//通知创建项目的组织下的所有管理员
					List<User> userpel = userR.findAllByOrgIdAndStatus(project.getOrgId(), 1);
					for (User u : userpel) {
						noticePeople+=","+u.getAccount();
						noticePeopleName+=","+u.getName();
					}
					
					Config c = comService.getConfig();
					if(StrKit.notBlank(c.getMesDefaultPel())) {
						List<Integer> defaultPelIds = JSONObject.parseArray(c.getMesDefaultPel(), Integer.class); // id拼接
						List<People> pels = peopleR.findAllByIdIn(defaultPelIds);
						for (People pel : pels) {
							noticePeople+=","+pel.getMobile();
							noticePeopleName+=","+pel.getName();
						}
					}
					String pelNames = StrKit.isBlank(noticePeopleName)?"":noticePeopleName.substring(1);
					String pelMobiles = StrKit.isBlank(noticePeople)?"":noticePeople.substring(1);
					String stip = "项目距离预计完成期限还剩余"+betweenDay+"天，请及时处理！\\r 通知人员：" + pelNames ;
					yjservice.addYjRecord( project.getOrgId(), project.getId(), YjTypeEnum.PROJECT.getId(), YjTypeEnum.TASK.getText() , "《"+project.getName()+"》" + stip + " "+MDateUtil.dateToString(now, null), pelMobiles,pelNames);
					comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
				}
			}else{
				//项目临期预警
				if(betweenDay>0) { //小于30天时开始预警
					
				}
			}
		}
	}
	
	public void overProject() {
		List<Integer> statuss = new ArrayList<>();
		statuss.add(ProjectStatusEnum.APPROVALOK.getId());
		statuss.add(ProjectStatusEnum.RUNGING.getId());
		statuss.add(ProjectStatusEnum.DELAY.getId());
		List<Project> ps = proR.findAllByStatusIn(statuss);//proR.findAllByStatusNot(ProjectStatusEnum.COMPLETE.getId());
		Date now = DateUtil.date();
		for (Project project : ps) {
			if(now.after(project.getStartDate()) && now.before(project.getCompleteDate())) {//超出 开始时间 小于结束时间
				project.setStatus(ProjectStatusEnum.RUNGING.getId());
				proR.save(project);
//				Config c = comService.getConfig();
//				if(StrKit.notBlank(c.getMesDefaultPel())) {
//					List<Integer> defaultPelIds = JSONObject.parseArray(c.getMesDefaultPel(), Integer.class); // id拼接
//					List<People> pels = peopleR.findAllByIdIn(defaultPelIds);
//					for (People pel : pels) {
//						noticePeople+=","+pel.getMobile();
//						noticePeopleName+=","+pel.getName();
//					}
//				}
//				String pelNames = StrKit.isBlank(noticePeopleName)?"":noticePeopleName.substring(1);
//				String pelMobiles = StrKit.isBlank(noticePeople)?"":noticePeople.substring(1);
//				String stip = "项目距离预计完成期限还剩余"+betweenDay+"天，请及时处理！\\r 通知人员：" + pelNames ;
//				yjservice.addYjRecord( project.getOrgId(), project.getId(), YjTypeEnum.OVERDUE.getId(), YjTypeEnum.TASK.getText() , "《"+project.getName()+"》" + stip + " "+MDateUtil.dateToString(now, null), pelMobiles,pelNames);
				//通知
//				comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
			}else if(now.after(project.getDelayDate()) ) {//超出延期时间
				project.setStatus(ProjectStatusEnum.OVERDUE.getId());
				proR.save(project);
				
//				yjservice.addYjRecord( project.getOrgId(), project.getId(), YjTypeEnum.OVERDUE.getId(), YjTypeEnum.TASK.getText() , "《"+project.getName()+"》" + stip + " "+MDateUtil.dateToString(now, null), pelMobiles,pelNames);
				//通知
//				comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
			}else if(now.after(project.getCompleteDate()) ) {//超出结束时间
				project.setStatus(ProjectStatusEnum.OVERDUE.getId());
				proR.save(project);

//				yjservice.addYjRecord( project.getOrgId(), project.getId(), YjTypeEnum.OVERDUE.getId(), YjTypeEnum.TASK.getText() , "《"+project.getName()+"》" + stip + " "+MDateUtil.dateToString(now, null), pelMobiles,pelNames);
				//通知
//				comService.sendNotice(pelMobiles,YjTypeEnum.PROJECT.getText(), stip);
			}
		}
	}
	
	// 添加预警通知人员
	public Map<String, Object> addNoticeUser(String area, String areaZzbflex) {
		String noticeDingUserids = "";
		String userNames = "";
	
		Map<String, Object> re = new HashMap<>();
		re.put("noticeUserids", StrKit.notBlank(noticeDingUserids) ? noticeDingUserids.substring(1) : "");
		re.put("userNames", StrKit.notBlank(userNames) ? userNames.substring(1) : "");
		return re;
	}

	/**
	 * 发送通知
	 * 
	 * @return
	 */
	public void sendMsg(String pels, String content, String title, Integer num, Integer typeId, Integer meetTypeId) {
//		String goDingUrl = PushMsg.appTaskUrl;
		
//		PushMsg pushMsg = new PushMsg();
//		if (num.equals(9) || num.equals(10) || num.equals(11) ||num.equals(12) || num.equals(3) || num.equals(4) || num.equals(7)||num.equals(8) ) {
//
//			Map<String, Object> markdown = new HashMap<>();
//			markdown.put(PushMsg.markdown_title, title);
//			markdown.put(PushMsg.markdown_text, content);
//			pushMsg.setMsgtype(PushMsg.t_markdown);
//			pushMsg.setMarkdown(markdown);
//		} else {
//			Map<String, Object> link = new HashMap<>();
//			link.put(PushMsg.link_messageUrl, goDingUrl);
//			link.put(PushMsg.link_picUrl, OtherConstant.systemPath + pic);
//			link.put(PushMsg.link_text, content);
//			link.put(PushMsg.link_title, title);
//			pushMsg.setMsgtype(PushMsg.t_link);
//			pushMsg.setLink(link);
//		}
		// 推送 --异步的
//		apiService.sendOverTimeTask(pels, null, false, pushMsg);
	}

}
