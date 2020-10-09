package com.yhj.pdj.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yhj.pdj.kit.RetKit;
import com.yhj.pdj.kit.StrKit;
import com.yhj.pdj.kit.UploadFile;
import com.yhj.pdj.model.po.User;
import com.yhj.pdj.model.vo.AdminRole;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

/**
 * 	用户管理
 * @author Administrator
 *
 */
@Service
public class UserService {

	@Value("${getFilePath}")
	private String getFilePath;
	
	/**
	 * 
	 * @param pn
	 * @param ps
	 * @param account 用户名
	 * @param recommendUser 推荐人
	 * @param isAuth 是否认证
	 * @param isEnabled 是否激活
	 * @param isLock 是否锁定
	 * @param level 级别 （  普通 vip 至尊 ）
	 * @return
	 */
	public Map<String, Object> getUserList(Integer pn, Integer ps, String account, String recommendUser, 
			Integer isAuth, Integer isEnabled, Integer isLock, Integer level) {
		try {
			String where = "";
			where += StrKit.notBlank(account)?" AND account = "+account : "";
			where += StrKit.notBlank(recommendUser)?" AND account = "+account : "";
			where += isAuth!=null?" AND is_auth = "+isAuth : "";
			where += isEnabled!=null?" AND is_enabled = "+isEnabled : "";
			where += isLock!=null?" AND is_lock = "+isLock : "";
			where += level!=null?" AND level = "+level : "";
			Map<String, Object> result = new HashMap<>();
			List<User> users = Db.use().query("SELECT * FROM cat_user WHERE 1=1 "+where, User.class);
			Integer count = users.size();
			users = users.stream().skip(ps * pn).limit(ps).collect(Collectors.toList());
			result.put("list", users);
			result.put("count", count); // 总条数
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}


	
	
	public RetKit del(List<Integer> ids) {
		try {
			Db.use().tx(db->{
				for (Integer id : ids) {
					Db.use().del(Entity.create("cat_user")
						    .set("id", id));
				}
			});
			return RetKit.ok("删除成功！");
		} catch (Exception e) {
			return RetKit.fail(e.getMessage());
		}
		
	}

	public RetKit isAuth(Integer id,Integer status) {
		try {
			Db.use().update(
				    Entity.create("cat_user")
				    .set("is_auth", status==1?1:3),
				    Entity.create("cat_user")
				    .set("id", id)
			);
			return RetKit.ok(status==1?"认证成功！":"已拒绝！");
		} catch (SQLException e) {
			return RetKit.fail(e.getMessage());
		}
	}


	public RetKit isEnabled(Integer id,Integer status) {
		try {
			Db.use().update(
				    Entity.create("cat_user")
				    .set("is_enabled", status),
				    Entity.create("cat_user")
				    .set("id", id)
			);
			return RetKit.ok(status==1?"已激活！":"已关闭！");
		} catch (SQLException e) {
			return RetKit.fail(e.getMessage());
		}
	}

	public RetKit isLock(Integer id,Integer status) {
		try {
			Db.use().update(
				    Entity.create("cat_user")
				    .set("is_lock", status),
				    Entity.create("cat_user")
				    .set("id", id)
			);
			return RetKit.ok(status==1?"已锁定！":"已解锁！");
		} catch (SQLException e) {
			return RetKit.fail(e.getMessage());
		}
	}


	
}
