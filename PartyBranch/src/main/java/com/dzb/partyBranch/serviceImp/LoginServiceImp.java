package com.dzb.partyBranch.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzb.partyBranch.kit.HashKit;
import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.model.po.User;
import com.dzb.partyBranch.repository.IUserRepository;
import com.dzb.partyBranch.service.ILoginService;


@Service
public class LoginServiceImp implements ILoginService {

	@Autowired
	private IUserRepository userRepository;
	
//	@Autowired
//	private ILogRepository logRepository;
	
	@Override
	public Boolean hasUserName(String userName) {
		Boolean flag = new Boolean(false);
		User user = userRepository.findUserByName(userName);
		if(user != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Boolean validatePwdAndName(String userName, String pwd) {
		Boolean flag = new Boolean(false);
		User user = userRepository.findUserByName(userName);
		if(user != null) {
			String hashedPass = HashKit.md5(pwd);
			if (user.getPwd().equals(hashedPass)) {
				//验证通过
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public RetKit login(String userName, String password, boolean remember, String loginIp) {
		
		try {
			userName = userName.toLowerCase().trim();
			password = password.trim();
			User loginUser = userRepository.findUserByName(userName);
			if (loginUser == null) {
				return RetKit.fail("用户名或密码不正确");
			}
			String hashedPass = HashKit.md5(password);
			// 未通过密码验证
			if (loginUser.getPwd().equals(hashedPass) == false) {
				return RetKit.fail("用户名或密码不正确");
			}
			
//			Session session = new Session();
//			String sessionId = StrKit.getRandomUUID();
//			session.setId(sessionId);
//			session.setUserId(loginUser.getId());
//			if (sessionRepository.save(session) != null) {
//				return RetKit.fail("保存 session 到数据库失败，请联系管理员");
//			}
			return RetKit.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail();
		}
	}

	@Override
	public void createLoginLog(Integer userId, String loginIp) {
//		try {
//			LoginLog log = new LoginLog();
//			log.setUserId(userId);
//			log.setIp(loginIp);
//			log.setLoginAt(new Date());
//			logRepository.save(log);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(MessageUtil.loadMessage("loggerError"));
//		}
	}
	
	@Override
	public RetKit updataPwd(Integer id,String oldP,String newP) {
		Optional<User> user0 = userRepository.findById(id);
		User user = user0.get();
		if(HashKit.md5(oldP).equals(user.getPwd())) {
			user.setPwd(HashKit.md5(newP));
			userRepository.save(user);
		}else{
			return RetKit.fail("旧密码输出错误");
		}
		return RetKit.ok();
	}

	@Override
	public boolean isLoginIn(Integer userId, Integer roleId) {
		// TODO Auto-generated method stub
		return false;
	}


}
