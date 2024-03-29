package com.fgw.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.OrgPropertyEnum;
import com.fgw.project.constant.RoleEnum;
import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Menu;
import com.fgw.project.model.po.Org;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.Role;
import com.fgw.project.model.po.User;
import com.fgw.project.model.vo.MenuVo;
import com.fgw.project.model.vo.UserVo;
import com.fgw.project.repository.IGroupRepository;
import com.fgw.project.repository.IMenuRepository;
import com.fgw.project.repository.IOrgRepository;
import com.fgw.project.repository.IRoleRepository;
import com.fgw.project.repository.IUserRepository;
import com.fgw.project.util.BeanKit;
import com.fgw.project.util.HashKit;
import com.fgw.project.util.MDateUtil;
import com.fgw.project.util.MakeMD5;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;
import com.fgw.project.util.TokenUtils;

import cn.hutool.core.bean.BeanUtil;

@Service
public class UserService {
	@Value("${getFileUrl}")
	private String getFileUrl;
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

	public RetKit getUserInfo(Integer userId) {
		Optional<User> user_ = userR.findById(userId);
		if(user_.isPresent()) {
			UserVo uv = new UserVo();
			BeanUtil.copyProperties(user_.get(), uv);
			uv.setAvater(StrKit.isBlank(uv.getAvater())?"":getFileUrl+uv.getAvater());
			Optional<Role> roe = roleR.findById(uv.getRoleId());
			if(roe.isPresent()) {
				uv.setRoleName(roe.get().getRoleName());
			}
			Optional<Org> org_ = orgR.findById(uv.getOrgId());
			if(org_.isPresent()) {
				uv.setOrgName(org_.get().getName());
			}
			return RetKit.okData(uv);
		}
		return RetKit.fail("获取失败！");
	}


	public RetKit getUser(String account,String psw,String orgId) {
		List<User> us = new ArrayList<>();
		if(StrKit.isBlank(orgId)) {
			us = userR.findByAccount(account);
		}else {
			us = userR.findAllByAccountAndOrgId(account,Integer.parseInt(orgId));
		}
		
		if(us.size() == 1) {
			User u = us.get(0);
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
		}else if(us.size() > 1) {
			List<UserVo> uvs = BeanKit.copyBeanList(us, UserVo.class);
			for (UserVo uv : uvs) {
				Optional<Org> _org = orgR.findById(uv.getOrgId());
				if(_org.isPresent()) {
					uv.setOrgName(_org.get().getName());
				}else {
					return RetKit.fail("获取登录部门失败！");
				}
			}
			return RetKit.failData(509,"请选择登录入口！",uvs);
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


	public RetKit getRoles(String param) {
		JSONObject obj = JSONObject.parseObject(param);
		Integer pn = obj.getInteger("pn");
		Integer ps = obj.getInteger("ps");
//		Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
		List<Role> rs = roleR.findAllByStatus(1);
		//筛查 有子类的父类ID 不添加到menus中
//		rs = rs.stream().map((Role r)->{
//			if(StrKit.notBlank(r.getMenus())) {
//				String newmenus = "";
//				String om = r.getMenus();
//				List<Integer> newImenus = new ArrayList<>();
//				List<String> mnus = Arrays.asList(om.split(","));
//				outloopA:for (String m : mnus) {
//					Optional<Menu> _menu = menuR.findById(Integer.parseInt(m));
//					if(_menu.isPresent()) {
//						Menu menu = _menu.get();
//						if(menu.getPid().equals(0)) {//父类
//							//判断是否有子ID存在与列表中，如果有，不添加父类ID
//							List<Menu> childMenus = menuR.findAllByPidAndStatus(menu.getId(),1);
//							boolean noHas = true;
//							outloopB:for (Menu cm : childMenus) {
//								if(newImenus.contains(cm.getId())) {
//									noHas = false;
//									break outloopB;
//								}
//							}
//							if(noHas) {
//								newImenus.add(menu.getId());
//							}
//						}else {
//							//子类判断其 父类是否已经存在与列表中，
//							if(newImenus.contains(menu.getPid())) {
//								newImenus.remove(menu.getPid());
//							}
//							newImenus.add(menu.getId());
//						}
//					}
//				}
//				for (Integer newId : newImenus) {
//					newmenus += ","+newId;
//				}
//				r.setMenus(StrKit.notBlank(newmenus)?newmenus.substring(1):"");
//			}
//			return r;
//		}).collect(Collectors.toList());
		
		Integer total = rs.size();
		rs = rs.stream().skip((pn-1)*ps).limit(ps).collect(Collectors.toList());
		Map<String,Object> rt = new HashMap<>();
		rt.put("pn", pn);
		rt.put("ps", ps);
		rt.put("total", total);
		rt.put("list", rs);
		return RetKit.okData(rt);
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


	public RetKit getUsers(String param) {
		JSONObject obj = JSONObject.parseObject(param);
		Integer orgId = obj.getInteger("orgId");
		String search = obj.getString("search");
		Integer pn = obj.getInteger("pn");
		Integer ps = obj.getInteger("ps");
		if(pn==null || ps==null) {
			pn = 1;
			ps = 20;
		}
		List<Map<String, Object>> rs = new ArrayList<>();
		if(StrKit.notBlank(search)) {
			if(orgId == null || orgId == 0 || orgId == 1) {
				rs = userR.getAllByStatusAndNameContaining(search);
			}else if(orgId != null && orgId != 0 && orgId != 1){
				rs = userR.getAllByOrgIdAndStatusAndNameContaining(orgId,search);
			}
		}else {
			if(orgId == null || orgId == 0 || orgId == 1) {
				rs = userR.getAllByStatus();
			}else if(orgId != null && orgId != 0 && orgId != 1){
				rs = userR.getAllByOrgIdAndStatus(orgId);
			}
		}
		Integer total = rs.size();
		rs = rs.stream().skip((pn-1)*ps).limit(ps).collect(Collectors.toList());
		Map<String,Object> rt = new HashMap<>();
		rt.put("pn", pn);
		rt.put("ps", ps);
		rt.put("total", total);
		rt.put("list", rs);
		return RetKit.okData(rt);
	}

	@Transactional
	public RetKit addUser(String param) {
		String account = JSONObject.parseObject(param).getString("account");
		String userName = JSONObject.parseObject(param).getString("userName");
		Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
		Integer roleId = JSONObject.parseObject(param).getInteger("roleId");
		Integer loginUserId = JSONObject.parseObject(param).getInteger("userId");
		Integer groupId = JSONObject.parseObject(param).getInteger("groupId");
		Integer loginRole = JSONObject.parseObject(param).getInteger("loginRole");
		List<User> us = userR.findAllByAccountAndOrgId(account,orgId);
		if(us.size()>0) {
			return RetKit.fail("用户已存在！");
		}
		User r = new User();
		r.setAccount(account);
		r.setPassword(MakeMD5.makeMD5(MakeMD5.md5DefaultSource));
		r.setStatus(1);
		r.setRoleId(roleId);
		r.setName(userName);
		r.setOrgId(orgId);
		if(loginUserId!=null ) {
			Optional<User> u_ = userR.findById(loginUserId);
			if(u_.isPresent()) {
				r.setCreateUserId(loginUserId);
				r.setCreateOrgId(loginUserId);
			}else {
				return RetKit.fail("登录身份异常！");
			}
		}else {
			r.setCreateUserId(0);
			r.setCreateOrgId(0);
		}
		if(groupId!=null) {
			r.setGroupId(groupId);
		}
		boolean isSystemAdmin = loginRole==null?false: loginRole.equals(RoleEnum.SUPERADMIN) || loginRole.equals(RoleEnum.ADMIN) ?true : false;
		r.setIsAdmin(isSystemAdmin?1:0);
		userR.save(r);
		
		Map<String, Object> m = new HashMap<>();
		m.put("id", r.getId());
		m.put("account", account);
		m.put("userName", userName);
		m.put("status", 1);
		m.put("orgId", 	orgId);
		m.put("roleId", roleId);
		if(groupId!=null) {
			m.put("groupId", groupId);
			Optional<Group> g_ = groupR.findById(groupId);
			m.put("groupName", g_.isPresent()?g_.get().getGroupName() : "");
		}
		m.put("isAdmin", 0);
		Optional<Role> r_ = roleR.findById(roleId);
		m.put("roleName", r_.isPresent()?r_.get().getRoleName() : "");
		m.put("rolePrimary", r_.isPresent()?r_.get().getRolePrimary() : "");
		m.put("roleDescribe", r_.isPresent()?r_.get().getRoleDescribe() : "");
		
		return RetKit.okData(m);
	}


	public RetKit updateUser(String param) {
		Integer id = JSONObject.parseObject(param).getInteger("id");
		String avater = JSONObject.parseObject(param).getString("avater");
		String account = JSONObject.parseObject(param).getString("account");
		String userName = JSONObject.parseObject(param).getString("userName");
		Integer orgId = JSONObject.parseObject(param).getInteger("orgId");
		Integer roleId = JSONObject.parseObject(param).getInteger("roleId");
		Integer groupId = JSONObject.parseObject(param).getInteger("groupId");
		Optional<User> r_ = userR.findById(id);
		if(r_.isPresent()) {
			User r = r_.get();
			if(StrKit.notBlank(avater)) {
				r.setAvater(avater);
			}
			if(StrKit.notBlank(account)) {
				r.setAccount(account);
			}
			if(StrKit.notBlank(userName)) {
				r.setName(userName);
			}
			if(roleId != null) {
				r.setRoleId(roleId);
			}
			if(orgId != null) {
				r.setOrgId(orgId);
			}
			if(groupId != null) {
				r.setGroupId(groupId);
			}
			userR.save(r);
			r.setAvater(StrKit.notBlank(avater)?getFileUrl+avater :"");
			return RetKit.okData(r);
		}
		return RetKit.fail("用户不存在！");
	}
	
	public RetKit updateUserPwd(String param) {
		Integer id = JSONObject.parseObject(param).getInteger("id");
		String password = JSONObject.parseObject(param).getString("password");
		if(StrKit.isBlank(password)) {
			return RetKit.fail("密码不能为空！");
		}
		Optional<User> r_ = userR.findById(id);
		if(r_.isPresent()) {
			User r = r_.get();
			r.setPassword(password);
			userR.save(r);
			return RetKit.okData(r);
		}
		return RetKit.fail("修改密码成功！");
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
			if(o.getProperty().equals(OrgPropertyEnum.FGWC.getId()) ||  o.getProperty().equals(OrgPropertyEnum.FGW.getId())) {
				if(roleId.equals(RoleEnum.ADMIN.getId())) {
					orgList.add(o);
					List<Org> nextOrgList = orgR.findAllByStatusAndProperty(1,roleId);
					orgList.addAll(nextOrgList);
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
