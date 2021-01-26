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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.OrgPropertyEnum;
import com.fgw.project.constant.RoleEnum;
import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Menu;
import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.Role;
import com.fgw.project.model.po.User;
import com.fgw.project.model.vo.MenuVo;
import com.fgw.project.repository.IGroupRepository;
import com.fgw.project.repository.IMenuRepository;
import com.fgw.project.repository.IOrgRepository;
import com.fgw.project.repository.IRoleRepository;
import com.fgw.project.repository.IUserRepository;
import com.fgw.project.util.BeanKit;
import com.fgw.project.util.HashKit;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;
import com.fgw.project.util.TokenUtils;

@Service
public class UserService {

	@Autowired
	private IUserRepository userR;
	@Autowired
	private IMenuRepository menuR;
	@Autowired
	private IRoleRepository roleR;
	@Autowired
	private IOrgRepository orgR;
	@Autowired
	private IGroupRepository groupR;

	public User getUserInfo(Integer userId) {
		Optional<User> user_ = userR.findById(userId);
		if(user_.isPresent()) {
			return user_.get();
		}
		return null;
	}


	public RetKit getUser(String account,String psw) {
		User u = userR.findByAccount(account);
		if(u!=null) {
			String oldpsw = u.getPassword();
			if(psw.equals(oldpsw)) {
				String token = TokenUtils.token(account, psw);
				u.setToken(token);
				userR.save(u);
				Map<String,Object> rt = new HashMap<>();
				List<Menu> menus = menuR.findAll();
//				List<MenuVo> mvs = coverMenuVo(menus);
				rt.put("token", token);
				rt.put("userInfo", u);
//				rt.put("menuList", mvs);
				
				return RetKit.okData(rt);
			}else {
				return RetKit.fail("密码错误！");
			}
		}else {
			return RetKit.fail("用户不存在！");
		}
	}

	private List<MenuVo> coverMenuVo(List<Menu> menus,Integer roleId){
		List<MenuVo> mvs = BeanKit.copyBeanList(menus, MenuVo.class);
		List<MenuVo> mvsfist  = mvs.stream().filter((MenuVo m)->m.getPid().equals(0)).collect(Collectors.toList());
		for (MenuVo bhvo : mvsfist) {
			List<MenuVo> childList = getChild(bhvo.getId(), mvs,roleId);
			bhvo.setChildren(childList);
			bhvo.setRoleId(roleId);
		}
		mvsfist = mvsfist.stream().sorted(Comparator.comparing(MenuVo::getSort)).collect(Collectors.toList());
		return mvsfist;
	}
	private List<MenuVo> getChild(Integer id, List<MenuVo> all,Integer roleId) {
		// 子菜单
		List<MenuVo> childList = new ArrayList<MenuVo>();
		for (MenuVo pb : all) {
			if (pb.getPid().equals(id)) {
				pb.setChildren(getChild(pb.getId(),all,roleId));
				childList.add(pb);
			}
			pb.setRoleId(roleId);
		}
		if (childList.size() == 0) {
			return new ArrayList<MenuVo>();
		}
		childList = childList.stream().sorted(Comparator.comparing(MenuVo::getSort)).collect(Collectors.toList());
		return childList;
	}


	public RetKit getUserOfToken(String token) {
		Map<String,Object> u = userR.getAdminUserOfToken(token);
		if(u.isEmpty()) {
			return RetKit.fail(50008);
		}
		Map<String, Object> newMap = new HashMap<>(u);
		newMap.put("roleIds", new Integer[] {(Integer)newMap.get("roleId")});
		return RetKit.okData(newMap);
	}
	


	public RetKit getRouter(Integer roleId) {
		Optional<Role> r_ = roleR.findById(roleId);
		if(r_.isPresent()) {
			String menus_ = r_.get().getMenus();
			if(StrKit.notBlank(menus_)) {
				String[] sarr = menus_.split(",");
				if(sarr.length>0) {
					int[] menuIdArrs = Arrays.stream(sarr).mapToInt(Integer::parseInt).toArray();
					List<Integer> menuIds = new ArrayList<>();
					for (int i : menuIdArrs){
						menuIds.add(i);
					}
					List<Menu> menus = menuR.findAllByIdInAndStatus(menuIds,1);
					List<MenuVo> mvs = coverMenuVo(menus,roleId);
					return RetKit.okData(mvs);
				}
			}
		}
		return RetKit.okData(new ArrayList<>());
	}


	public RetKit logout(String token) {
		User u = userR.findByToken(token);
		if(u!=null) {
			u.setToken("");
			userR.save(u);
		}
		return RetKit.ok();
	}


	public RetKit getAllRouters() {
		List<Menu> menus = menuR.findAllByStatus(1);
		List<MenuVo> mvs = coverMenuVo(menus,1);
		return RetKit.okData(mvs);
	}


	public RetKit getRoles() {
//		Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
		List<Role> rs = roleR.findAllByStatus(1);
		return RetKit.okData(rs);
	}


	public RetKit addRole(String param) {
		String roleName = JSONObject.parseObject(param).getString("roleName");
		String rolePrimary = JSONObject.parseObject(param).getString("rolePrimary");
		String roleDescribe = JSONObject.parseObject(param).getString("roleDescribe");
		String menus = JSONObject.parseObject(param).getString("menus");
		Role r = new Role();
		r.setRoleName(roleName);
		r.setRoleDescribe(roleDescribe);
		r.setStatus(1);
		r.setMenus(menus);
		r.setRolePrimary(rolePrimary);
		roleR.save(r);
		return RetKit.okData(r.getId());
	}


	public RetKit updateRole(String param) {
		JSONObject jb = JSONObject.parseObject(param);
		Integer id = jb.getInteger("id");
		String roleName = jb.getString("roleName");
		String rolePrimary = jb.getString("rolePrimary");
		String roleDescribe = jb.getString("roleDescribe");
		String menusStr = jb.getString("menus");
		Optional<Role> r_ = roleR.findById(id);
		if(r_.isPresent()) {
			Role r = r_.get();
			r.setRoleName(roleName);
			r.setRoleDescribe(roleDescribe);
			r.setStatus(1);
			r.setMenus(menusStr);
			r.setRolePrimary(rolePrimary);
			roleR.save(r);
			return RetKit.ok("修改成功！");
		}
		return RetKit.fail("修改失败！");
	}


	public RetKit getUsers(String orgIdstr) {
		if(StrKit.isBlank(orgIdstr)) {
			return RetKit.fail("参数不正确！");
		}
		Integer orgId = Integer.parseInt(orgIdstr);
		List<Map<String, Object>> rs = new ArrayList<>();
		if (orgId == 0) {
			rs = userR.getAllByStatus();
		}else {
			rs = userR.getAllByOrgIdAndStatus(orgId);
		}
		return RetKit.okData(rs);
	}


	public RetKit addUser(String param) {
		String account = JSONObject.parseObject(param).getString("account");
		String password = JSONObject.parseObject(param).getString("password");
		String userName = JSONObject.parseObject(param).getString("userName");
		Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
		Integer roleId = JSONObject.parseObject(param).getInteger("roleId");
		Integer groupId = JSONObject.parseObject(param).getInteger("groupId");
		List<User> us = userR.findAllByAccount(account); 
		if(us.size()>0) {
			return RetKit.fail("用户已存在！");
		}
		User r = new User();
		r.setAccount(account);
		r.setPassword(password);
		r.setStatus(1);
		r.setRoleId(roleId);
		r.setName(userName);
		r.setOrgId(orgId);
		r.setGroupId(groupId);
		userR.save(r);
		return RetKit.okData(r.getId());
	}


	public RetKit updateUser(String param) {
		Integer id = JSONObject.parseObject(param).getInteger("id");
		String account = JSONObject.parseObject(param).getString("account");
		String password = JSONObject.parseObject(param).getString("password");
		String userName = JSONObject.parseObject(param).getString("userName");
		Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
		Optional<User> r_ = userR.findById(id);
		if(r_.isPresent()) {
			User r = r_.get();
			r.setAccount(account);
			r.setPassword(password);
			r.setStatus(1);
			r.setName(userName);
			userR.save(r);
			return RetKit.ok("修改成功！");
		}
		return RetKit.fail("用户不存在！");
	}


	public RetKit deleteUser(Integer userId) {
		userR.deleteById(userId);
		return RetKit.ok("删除成功！");
	}


	public RetKit getRoleList(Integer orgId,Integer roleId) {
		List<Role> ros = roleR.findAllByIdGreaterThanEqual(roleId);
		return RetKit.okData(ros);
	}

	public RetKit getOrgList(Integer loginOrgId,Integer roleId) {
		Optional<Org> o_ = orgR.findById(loginOrgId);
		List<Org> orgList = new ArrayList<>();
		if(o_.isPresent()){
			Org o = o_.get();
			//如果登录的组织是  系统管理员
			if(o.getProperty().equals(OrgPropertyEnum.FGW.getId())) {
				if(roleId.equals(RoleEnum.ADMIN.getId())) {
					orgList.add(o);
				}else if(roleId.equals(RoleEnum.DEPART.getId())) {
					List<Org> nextOrgList = orgR.findAllByStatusAndPropertyAndPid(1,roleId, 0);
					orgList.addAll(nextOrgList);
				}else if(roleId.equals(RoleEnum.ENTER.getId())) {
					List<Org> nextOrgList = orgR.findAllByStatusAndPropertyAndPid(1,roleId, loginOrgId);
					orgList.addAll(nextOrgList);
				}
			}else {
				orgList.add(o);
				List<Org> nextOrgList = orgR.findAllByStatusAndPropertyAndPid(1,roleId, loginOrgId);
				orgList.addAll(nextOrgList);
			}
		}
		return RetKit.okData(orgList);
	}

	public RetKit getGroupList(Integer orgId) {
		List<Group> grs = groupR.findAllByOrgId(orgId);
		return RetKit.okData(grs);
	}
	
}
