package com.fgw.project.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fgw.project.model.po.Yj;
import com.fgw.project.repository.IYjRepository;
import com.fgw.project.util.RetKit;


@Service
public class YjService {

	@Autowired
	private IYjRepository yR;

	public RetKit getYjs(Integer orgId, Integer status, String search) {
		List<Yj> ys = yR.findAllByOrgId(orgId);
		if(status !=null) {
			ys = yR.findAllByOrgId(orgId);
		}else {
			ys = yR.findAllByOrgIdAndStatus(orgId,status);
		}
		return RetKit.okData(ys);
	}
	
	
	//增加预警通知
	/**
	 * 
	 * @param orgId
	 * @param gid   项目 或者 任务id 
	 * @param type
	 * @param title
	 * @param stip
	 * @param noticePeople
	 * @return
	 */
	public RetKit addYjRecord(Integer orgId,Integer gid, Integer type, String title,String stip,String noticePeople) {
		List<Yj> ys = yR.findAllByOrgIdAndGidAndType(orgId,gid,type);
		if(ys.size()>0) {
			
		}else {
			Yj yj = new Yj();
			yj.setGid(gid);
			yj.setTime(new Date());
			yj.setNoticePeople(noticePeople);
			yj.setOrgId(orgId);
			yj.setStatus(1);
			yj.setStip(stip);
			yj.setTitle(title);
			yj.setType(type);
			yR.save(yj);
		}
		return RetKit.okData(ys);
	}
	
	
	
}
