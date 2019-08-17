package com.dzb.partyBranch.config;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dzb.partyBranch.kit.BeanKit;
import com.dzb.partyBranch.kit.HashKit;
import com.dzb.partyBranch.model.vo.UserAuthentic;

@Component("myAuthenticationManager")
public class MyAuthenticationManager implements AuthenticationManager {
	
	@Resource(name="userDetailServiceImpl")
	private  UserDetailsService userDetailsService;  
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String username = token.getName(); 
		//从数据库找到的用户
        UserDetails userDetails = null;  
        if(username != null) {
        	userDetails = this.userDetailsService.loadUserByUsername(username);
        }
        if(userDetails == null) {
        	throw new UsernameNotFoundException("用户名不存在！");
        }
        String pwd  = userDetails.getPassword();
        String s =HashKit.md5(token.getCredentials().toString());
        //验证
        if(pwd!=null && !pwd.equals(HashKit.md5(token.getCredentials().toString()))) {
        	throw new BadCredentialsException("密码错误！");
        }
		return new UsernamePasswordAuthenticationToken(userDetails,pwd,userDetails.getAuthorities());
	}

}
