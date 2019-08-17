package com.dzb.partyBranch.config;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dzb.partyBranch.kit.BeanKit;
import com.dzb.partyBranch.model.vo.UserAuthentic;
import com.dzb.partyBranch.repository.IUserRepository;


@Component
@Service(value="userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
			Map<String,Object> u = userRepository.findUser(name);
			if(!u.isEmpty()) {
				UserAuthentic user = BeanKit.changeRecordToBean(u, UserAuthentic.class);
				return user;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("用户不存在");
		}
	}

}
