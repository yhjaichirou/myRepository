package com.dzb.partyBranch.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.model.po.Menu;
import com.dzb.partyBranch.model.po.Role;
import com.dzb.partyBranch.repository.IMenuRepository;
import com.dzb.partyBranch.repository.IRoleRepository;
import com.dzb.partyBranch.service.ISystemService;

@Service
public class SystemService implements ISystemService {

	@Autowired
	private IMenuRepository menuRepository;
	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	public RetKit addOrUpdateMenu(Menu menu) {
		try {
			menuRepository.save(menu);
		} catch (Exception e) {
			return RetKit.fail();
		}
		return RetKit.ok();
	}


	@Override
	public RetKit delMenu(Integer menuId) {
		try {
			menuRepository.deleteById(menuId);
		} catch (Exception e) {
			return RetKit.fail();
		}
		return RetKit.ok();
	}
	
	@Override
	public RetKit addOrUpdateRole(Role role) {
		try {
			roleRepository.save(role);
		} catch (Exception e) {
			return RetKit.fail();
		}
		return RetKit.ok();
	}


	@Override
	public RetKit delRole(Integer roleId) {
		try {
			roleRepository.deleteById(roleId);
		} catch (Exception e) {
			return RetKit.fail();
		}
		return RetKit.ok();
	}

	

}
