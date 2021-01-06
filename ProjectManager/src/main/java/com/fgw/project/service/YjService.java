package com.fgw.project.service;

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
	
	
}
