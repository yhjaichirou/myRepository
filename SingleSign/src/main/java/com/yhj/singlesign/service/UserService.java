package com.yhj.singlesign.service;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yhj.singlesign.repository.UserRepository;
import com.yhj.singlesign.utils.CookieUtil;
import com.yhj.singlesign.utils.EncryptDesUtils;
import com.yhj.singlesign.utils.RetKit;
import com.yhj.singlesign.vo.User;

/**
 * 	第一件事情：验证用户信息是否正确，并将登录成功的用户信息保存到Redis数据库中。
 *	第二件事情：负责判断用户令牌是否过期，若没有则刷新令牌存活时间。
 *	第三件事情：负责从Redis数据库中删除用户信息。
 * @author admin
 *
 */
@Service
@Transactional
@PropertySource(value = "classpath:redis.properties")
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JedisClientPool jedisClient;
    
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;
    
    public RetKit userLogin(String account, String password,
            HttpServletRequest request, HttpServletResponse response) {
        // 判断账号密码是否正确
        User user = userRepository.findByAccount(account);
        try {
			if (password.equals(EncryptDesUtils.decrypt(user.getPassword()))) {
			    return RetKit.fail(400, "账号名或密码错误");
			}
			 // 生成token
	        String token = UUID.randomUUID().toString();
	        // 清空密码和盐避免泄漏
	        String userPassword = user.getPassword();
	        String userSalt = user.getSalt();
	        user.setPassword(null);
	        user.setSalt(null);
	        // 把用户信息写入 redis
	        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JSONObject.toJSONString(user));
	        // user 已经是持久化对象，被保存在session缓存当中，若user又重新修改属性值，那么在提交事务时，此时 hibernate对象就会拿当前这个user对象和保存在session缓存中的user对象进行比较，如果两个对象相同，则不会发送update语句，否则会发出update语句。
	        user.setPassword(userPassword);
	        user.setSalt(userSalt);
	        // 设置 session 的过期时间
	        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
	        // 添加写 cookie 的逻辑，cookie 的有效期是关闭浏览器就失效。
	        CookieUtil.setCookie(request, response, "USER_TOKEN", token);
	        // 返回token
	        return RetKit.ok(token);
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail("登录失败！"+e.getMessage());
		}
       
    }
    
    public void logout(String token) {
        jedisClient.hdel(REDIS_USER_SESSION_KEY + ":" + token);
    }

    public RetKit queryUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        // 判断是否为空
        if (StringUtils.isEmpty(json)) {
            return RetKit.fail(400, "此session已经过期，请重新登录");
        }
        // 更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回用户信息
        return RetKit.ok(JSONObject.parseArray(json, User.class));
    }

	public User getUserByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}
}
