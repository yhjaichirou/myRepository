package com.dzb.partyBranch.serviceImp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.query.internal.QueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.CollectionUtils;

import com.dzb.partyBranch.kit.MessageUtil;
import com.dzb.partyBranch.repository.IPagenationRepositoryCustom;


public class PagenationRepositoryCustomImpl<T, ID extends Serializable> extends  SimpleJpaRepository<T,ID> implements IPagenationRepositoryCustom<T,ID> {
	private static Logger logger = LoggerFactory.getLogger(PagenationRepositoryCustomImpl.class);//指定类初始化日志对象，可以打印出日志信息所在类
	@PersistenceContext
	private  EntityManager em;
	private  EntityTransaction  transaction ;
	
	public PagenationRepositoryCustomImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}
	
	/* (non-Javadoc)
	 * @see com.dchip.parking.boot.IRepository.IPagenationRepositoryCustom#pagenation(java.lang.StringBuffer, java.lang.StringBuffer, java.util.Map, org.springframework.data.domain.Sort, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> getPageDate(StringBuffer querySql, StringBuffer countSql, Map<String, Object> params, Sort sort,
			Integer startPage, Integer pageSize) {
			Map<String, Object> rt = new HashMap<>();
			transaction = em.getTransaction();
			transaction.begin();
			Query dataQuery = null;
			Query countQuery = null;
			try {
				if (sort != null ) {
					//排序
					  List<Order> orders = sort.stream().collect(Collectors.toList());//收集结果（collect方法）
					  if(orders.size() >0) {
						  for (Order order : orders) {
							  if(querySql.indexOf("order by") != -1) {
								  querySql.append("	,	");
							  }else {
								  querySql.append("	order by  ");
							  }
							  querySql.append(order.getProperty());
							  querySql.append("	");
							  querySql.append(order.getDirection().name());
						}
					  }
				}
				
//				//拼分页
//				querySql.append("	limit	");
//				querySql.append(pageSize);
//				querySql.append("	offset	");
//				querySql.append(startPage*pageSize);
				
				String querySqlStr = querySql.toString();
				String countSqlStr = countSql.toString();
				logger.info("nativesql:"+querySql.toString());
				dataQuery = em.createQuery(querySqlStr);
				dataQuery.setFirstResult(startPage);
				dataQuery.setMaxResults(pageSize);
//				if(clazz instanceof Map) {//instanceof运算符是用来在运行时指出对象是否是特定类的一个实例。instanceof通过返回一个布尔值来指出，这个对象是否是这个特定类或者是它的子类的一个实例。
////					dataQuery = em.createQuery(querySqlStr).unwrap(org.hibernate.query.Query.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//					dataQuery = em.createQuery(querySqlStr);
//					dataQuery.setFirstResult(startPage);
//					dataQuery.setMaxResults(pageSize);
//					
//				}else {//unwrap需要获取数据库连接，setResultTransformer这个方法的作用就是使查询到的list与数据库字段对应
//					dataQuery = em.createQuery(querySqlStr);
//				}
				System.out.println(querySql.toString());
			   countQuery = em.createNativeQuery(countSqlStr);//返回一个List<Object[]>
			   //fill param 
				Iterator<Entry<String, Object>> it = params.entrySet().iterator();//entrySet().iterator()是去获得这个集合的迭代器，保存在it里面
				if(querySqlStr.indexOf("?") != -1) {
					throw new RuntimeException(MessageUtil.loadMessage("sql.param.error.type"));
				}else if(!CollectionUtils.isEmpty(params.values())){
					//迭代器
					
					while (it.hasNext()) {
						Map.Entry<String, Object> entry = (Entry<String, Object>) it.next(); 
						//就能获得map中的每一个键值对
						String key = entry.getKey();  
						Object value = entry.getValue(); 
						dataQuery.setParameter(key, value);
						countQuery.setParameter(key, value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
			
		   @SuppressWarnings("unchecked")
		   List<T> contents = dataQuery.getResultList();//读取
		   transaction.commit();
		   long totalSize = ((BigInteger)countQuery.getSingleResult()).longValue();//longValue()是Long类的一个方法，用来得到Long类中的数值。
//		   Page<T> page = new PageImpl<T>(contents,PageRequest.of(startPage, pageSize,sort), totalSize);
			rt.put("list", contents);
			rt.put("total", totalSize);
			return rt;
	}
	
	
//	/**
//	 * 
//	 * @param keys :查询的字段  ，如果某字段需要去重，则keys里面不需要添加这个字段
//	 * @param fromStart :from开始之后的部分 截止到where之前
//	 * @param ps :页大小
//	 * @param pn :第几页
//	 * @param sort :排序
//	 * @param param :模糊map
//	 * @param fixConditoin :模糊之外固定条件拼接的String  
//	 * @param countDistinct :需要去重的String 例：DISTINCT a.order_seq，不需要时传""即可。 
//	 * @return
//	 */
//	@Override
//	public Page<Map<String, Object>> getPageData(String keys,String fromStart,int ps ,int pn,Sort sort,Map<String, Object> param,String fixConditoin,String countDistinct){
//		StringBuffer qSql = new StringBuffer();	
//		StringBuffer cSql = new StringBuffer();
//		Map<String, Object> params = new HashMap<>();//模糊查询条件组装
//		
//		String countS = "select count(*) ";
//		if(countDistinct!=null&&countDistinct!=""&&!countDistinct.isEmpty()) {
//			countS = countS.replaceAll("\\*",countDistinct);
//			qSql.append("select " + countDistinct +" , "+ keys + fromStart);
//		}else {
//			qSql.append("select " + keys + fromStart);
//		}
//		cSql.append(countS + fromStart);
//		
//		// 拼where条件
//		Set<String> paramKeys = param.keySet();//like条件
//		if (!CollectionUtils.isEmpty(paramKeys) && !CollectionUtils.isEmpty(param.values())) {
//			qSql.append("	WHERE	");
//			cSql.append("	WHERE	");
//
//			StringBuffer conditions = new StringBuffer();
//			String  fix = "";
//			if(fixConditoin!=null&&fixConditoin!=""&& !fixConditoin.isEmpty()) {//固定条件不为空
//				conditions.append("	( ");
//				fix = " ) "+" and "+fixConditoin;
//			}
//			for (String key : paramKeys) {
//				if (conditions.length() > 3) {
//					conditions.append("	or	");
//				}
//				conditions.append("	");
//				conditions.append(key);
//				conditions.append("	");
//				conditions.append("like	");
//				conditions.append("'%" + param.get(key) + "%'");
//			}
//			conditions.append(fix);
//			qSql.append(conditions);
//			cSql.append(conditions);
//			params = param;
//		}else {
//			if(fixConditoin.trim()!=null && fixConditoin.trim()!=""&& !fixConditoin.isEmpty()) {//固定条件不为空
//				qSql.append("	WHERE	"+fixConditoin);
//				cSql.append("	WHERE	"+fixConditoin);
//			}
//		}
//		//调用分页接口
//		return pagenation(qSql, cSql, params, sort, pn, ps, new HashMap<String,Object>());
//	}

}
