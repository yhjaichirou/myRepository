package com.fgw.project;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TargetQuartz {
	private Log log = LogFactory.getLog(TargetQuartz.class);
	

	@Resource
	private ThreadDoTask dotask;

	/**
	 * 每天 1点  定期 -过期  检测指标过期  
	 */
	@Scheduled(cron = "0 0 1 1/1 * ? ")
	public void targetOverdue() {
		log.info("----检测是否过期----");
		dotask.targetOverdue();
	}
	
	/**
	 * 每月1号 一次评星定级       
	 */
	@Scheduled(cron = "0 0 0 1 1/1 ? ")
	public void doMarkLevel() {
		log.info("----每月初评星定级一次----");
//		dotask.diskYj();
	}
	
}
