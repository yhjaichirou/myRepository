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
import com.fgw.project.constant.ProjectStatusEnum;
import com.fgw.project.model.po.Project;
import com.fgw.project.model.po.Yj;
import com.fgw.project.repository.IProjectRepository;
import com.fgw.project.repository.ITaskRepository;
import com.fgw.project.repository.IYjRepository;
import com.fgw.project.util.StrKit;
import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
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
		
		List<Project> ps = proR.findAllByStatusNot(ProjectStatusEnum.COMPLETE.getId());
		Date now = DateUtil.date();
		
		for (Project project : ps) {
			Date exDate = project.getExpectedDate();
			long betweenDay = DateUtil.between(DateUtil.date(), project.getExpectedDate(), DateUnit.DAY);
			if(now.before(exDate)) {//当前日期 小于  预计完成日期  //项目临期预警
				if(betweenDay<30) { //小于30天时开始预警
					
				}
			}else{
				//项目临期预警
				if(betweenDay>0) { //小于30天时开始预警
					
				}
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

	/**
	 * 增加预警 记录
	 * 
	 * type: 预警类型 1人员 2 换届  3 组织生活   4矿会人员提醒  5 党组织书记支委缺失预警
	 * @param title             预警标题
	 * @param type              1人员 2 换届  3 组织生活   4矿会人员提醒  5 党组织书记支委缺失预警 6 组织生活督导
	 * @param content           预警内容
	 * @param noticeDingUserids 通知人员
	 * @param isCancel          是否取消预警
	 * @param taskId 			type:3 此字段为任务ID type:6 为活动或会议ID
	 * @return 返回是否预警
	 */
	public boolean addYj(Integer orgId, Integer type, String title, String naContent, String noticeDingUserids,
			boolean isCancel,Integer yjOfId) {
		List<Integer> statuss = new ArrayList<>();
		statuss.add(1);// 预警中的预警
		statuss.add(0);// 被取消的预警
		List<Yj> olds = null;// 由此判断是否要新增 预警
		if (type == 1 || type == 5) {//人员预警，如果存在预警中的 继续增加预警次数，如果曾经预警过，现在又达到预警值，重新增加预警。
//			olds = yjR.findAllByYjBranchIdAndYjTypeAndYjStatusIn(branchId, type, statuss);
		}else if(type == 3 || type == 6){ // 任务
//			olds = yjR.findAllByYjTypeAndYjTaskId(type,taskId);
		}else {
//			olds = yjR.findAllByYjBranchIdAndYjTypeAndYjContentAndYjStatusIn(branchId, type, naContent,statuss);
		}

		if (olds != null && olds.size() > 0) {
			for (Yj yj : olds) {
				
			}
		} else {
			Yj yj = new Yj();
			
			
			yjR.save(yj);
		}
		return true;
	}

	

}
