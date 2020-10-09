package com.yhj.pdj.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yhj.pdj.kit.MDateUtil;
import com.yhj.pdj.kit.RetKit;
import com.yhj.pdj.kit.StrKit;
import com.yhj.pdj.model.po.PdjRecord;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

@Service
public class RecordService {
	
	
	public Map<String, Object> getRecordrList(Integer pn, Integer ps, Integer type, Integer status, String startTime,
			String endTime)  {
		try {
			StringBuffer whereSql = new StringBuffer();
			
			if(StrKit.notBlank(startTime) && StrKit.notBlank(endTime)) {
				whereSql.append(" AND TO_DAYS(create_time)>= TO_DAYS('"+ startTime+"') AND TO_DAYS(create_time)<= TO_DAYS('"+ endTime+"')");
			}else if(StrKit.notBlank(startTime) && StrKit.isBlank(endTime)) {
				whereSql.append(" AND TO_DAYS(create_time) >= TO_DAYS('"+ startTime+"')");
			}else if(StrKit.isBlank(startTime) && StrKit.notBlank(endTime)) {
				whereSql.append(" AND TO_DAYS(create_time) <= TO_DAYS('"+ endTime+"')");
			}
			whereSql.append(status==null?"":" AND status ="+status);
			whereSql.append(type==null?"":" AND type ="+type);
			
			Map<String, Object> result = new HashMap<>();
			List<PdjRecord> users = Db.use().query("SELECT * FROM pdj_record WHERE 1=1 "+whereSql.toString(), PdjRecord.class);
			Integer count = users.size();
			users = users.stream().skip(ps * pn).limit(ps).collect(Collectors.toList());
			users = users.stream().map((PdjRecord r)->{
				r.setCreateTimeStr(MDateUtil.dateToString(r.getCreateTime(), null));
				return r;
			}).collect(Collectors.toList());
			result.put("list", users);
			result.put("count", count); // 总条数
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}
	public RetKit upload(List<Integer> ids) {
		try {
			Db.use().tx(db->{
				for (Integer id : ids) {
					Db.use().del(Entity.create("pdj_record")
						    .set("id", id));
				}
			});
			return RetKit.ok("上报成功！");
		} catch (Exception e) {
			return RetKit.fail(e.getMessage());
		}
		
	}
	
	

}
