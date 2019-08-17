package com.dzb.partyBranch.service;

import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.model.po.Menu;
import com.dzb.partyBranch.model.po.Role;

/**
 * 党建服务
 * @author yhj
 *
 */
public interface ISystemService {
	
	RetKit addOrUpdateMenu(Menu menu);
	
	RetKit delMenu(Integer menuId);
	
	RetKit addOrUpdateRole(Role menu);
	
	RetKit delRole(Integer menuId);

}
