package com.dzb.partyBranch.serviceImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzb.partyBranch.kit.BeanKit;
import com.dzb.partyBranch.model.po.Menu;
import com.dzb.partyBranch.model.vo.NavVo;
import com.dzb.partyBranch.model.vo.UserVo;
import com.dzb.partyBranch.repository.IMenuRepository;
import com.dzb.partyBranch.repository.IUserRepository;
import com.dzb.partyBranch.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IMenuRepository menuRepository;

	@Override
	public UserVo getUserInfo(Integer userId) {
		Map<String, Object> userVo = userRepository.getUserInfo(userId);
		UserVo vo = null;
		try {
			vo = BeanKit.changeRecordToBean(userVo, UserVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo != null ? vo : null;
	}

	@Override
	public List<NavVo> getMenus(Integer roleId) {
		try {
			List<Menu> allMenu = menuRepository.findAll();
			List<NavVo> navlists = BeanKit.copyBeanList(allMenu, NavVo.class);
			navlists = navlists.stream().map((NavVo vo)->{
				vo.setTitle(vo.getName());
				return vo;
			}).collect(Collectors.toList());
			List<NavVo> navlistParents = navlists.stream().filter(e -> e.getPid() == 0).collect(Collectors.toList());
			
			for (NavVo navVo : navlistParents) {
				List<NavVo> childList = getChild(navVo.getId(), navlists);
				navVo.setChildren(childList);
			}
			
			if(!roleId.equals(0)) {
				List<Integer> oneIds = menuRepository.getMenusOfId(roleId);
				navlistParents = navlistParents.stream().filter((NavVo m) -> (oneIds.contains(m.getId())))
						.collect(Collectors.toList());
			}
			return navlistParents;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			return null;
		}
	}

	public List<NavVo> getChild(Integer id, List<NavVo> allMenu) {
		// 子菜单
		List<NavVo> childList = new ArrayList<NavVo>();
		for (NavVo nav : allMenu) {
			// 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
			// 相等说明：为该根节点的子节点。
			if (nav.getPid().equals(id)) {
				childList.add(nav);
			}
		}
		// 递归
		for (NavVo nav : childList) {
			nav.setChildren(getChild(nav.getId(), allMenu));
		}
		Collections.sort(childList, order());// 排序
		// 如果节点下没有子节点，返回一个空List（递归退出）
		if (childList.size() == 0) {
			return new ArrayList<NavVo>();
		}
		return childList;
	}

	/*
	 * 排序,根据order排序
	 */
	public Comparator<NavVo> order() {
		Comparator<NavVo> comparator = new Comparator<NavVo>() {
			@Override
			public int compare(NavVo o1, NavVo o2) {
				if (o1.getSort() != o2.getSort()) {
					return o1.getSort() - o2.getSort();
				}
				return 0;
			}
		};
		return comparator;
	}

}
