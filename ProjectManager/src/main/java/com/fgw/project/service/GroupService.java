package com.fgw.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.model.po.Group;
import com.fgw.project.repository.IGroupRepository;
import com.fgw.project.util.RetKit;


@Service
public class GroupService {

	@Autowired
	private IGroupRepository groupR;

	public RetKit getGroup(Integer orgId) {
		List<Group> gs = groupR.findAllByOrgId(orgId);
		return RetKit.okData(gs);
	}

	public RetKit addGroup(String param) {
		String groupName = JSONObject.parseObject(param).getString("groupName");
		String groupDecript = JSONObject.parseObject(param).getString("groupDecript");
		Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
		Group group = new Group();
		group.setGroupName(groupName);
		group.setGroupDecript(groupDecript);
		group.setOrgId(orgId);
		groupR.save(group);
		return RetKit.okData(group.getId());
	}

	public RetKit updateGroup(String param) {
		String id = JSONObject.parseObject(param).getString("id");
		Group g = groupR.findById(id);
		String groupName = JSONObject.parseObject(param).getString("groupName");
		String groupDecript = JSONObject.parseObject(param).getString("groupDecript");
		Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
		g.setGroupName(groupName);
		g.setGroupDecript(groupDecript);
		g.setOrgId(orgId);
		groupR.save(g);
		return RetKit.ok("修改成功！");
	}

	public RetKit deleteGroup(int id) {
		groupR.deleteById(id);
		return RetKit.ok("删除成功！");
	}
	
	
}
