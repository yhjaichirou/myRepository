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

import com.fgw.project.model.po.Menu;
import com.fgw.project.model.po.Role;
import com.fgw.project.model.po.User;
import com.fgw.project.model.vo.MenuVo;
import com.fgw.project.repository.IMenuRepository;
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
					List<Menu> menus = menuR.findAllByIdIn(menuIds);
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
	
}
