package com.dzb.partyBranch.kit;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class JpaUtils {

//	private static EntityManagerFactory factory;
//	static {
//		// 1.加载配置文件，创建entityManagerFactory
//		factory = Persistence.createEntityManagerFactory("myJpa");
//	}
//
//	/**
//	 * 获取EntityManager对象
//	 */
//	public static EntityManager getEntityManager() {
//		return factory.createEntityManager();
//	}

	/**
	 * 	排序
	 */
	public static String getSortSQL(Sort sort) {
		
		StringBuffer querySql = new StringBuffer();
		List<Order> orders = sort.stream().collect(Collectors.toList());// 收集结果（collect方法）
		if (orders.size() > 0) {
			for (Order order : orders) {
				if (querySql!=null && querySql.indexOf("order by") != -1) {
					querySql.append("	,	");
				} else {
					querySql.append("	order by  ");
				}
				querySql.append(order.getProperty());
				querySql.append("	");
				querySql.append(order.getDirection().name());
			}
		}
		return querySql!=null?querySql.toString():"";
	}
}
