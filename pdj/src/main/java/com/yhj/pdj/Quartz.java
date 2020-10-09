package com.yhj.pdj;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 定时任务
 * @author admin
 *
 */
@Component
public class Quartz {
	private static final Log log = LogFactory.get();

	
	/**
	 * 定期   每隔一分钟 检查accessToken
	 */
	@Scheduled(cron = "0 0/1 * * * ? ")
	public void targetOverdue() {
		log.info("----检测accessToken是否过期----");
		
	}
	
//	/**
//	 * 定期   每天凌晨1点  计算佣金收益
//	 */
//	@Scheduled(cron = "0 0 1 * * ?")
//	public void calcRetail() {
//		log.info("----定期   每天凌晨1点  计算佣金收益----");
//		comService.calcCommodityRetail(userId, priceAll);
//	}
	
}
