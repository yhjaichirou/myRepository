package com.fgw.project.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Task;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface ITaskRepository extends JpaRepository<Task , Integer> {

	List<Task> findAllByOrgId(Integer orgId);

	List<Task> findAllByProId(Integer projectId);

	List<Task> findByProId(Integer projectId);

	List<Task> findByProIdAndStatus(Integer projectId, Integer status);
	
	List<Task> findByProIdAndStatusIn(Integer projectId, List<Integer> statuss);

	@Query(value="SELECT "
			+ " t.id,t.pid,t.pro_id as proId,t.title,t.org_id as orgId,t.executor,t.executor_mobile as executorMobile,t.stage_id as stageId,t.start_date as startDate,t.com_content as comContent,t.question,"
			+ " t.end_date as endDate,t.priority,t.status,t.remark,t.annex,t.pre_tasks as preTasks,	t.execut_org as executOrg,t.com_date as comDate,t.code,t.create_date as createDate,t.is_shb as isShb,t.shb,t.db_count as dbCount , t.db_date as dbDate ,"
			+ " o.name as executOrgName , p.name as executorName,pro.name as projectName ,shb.name as shbName,shb.step1,shb.step2,shb.step3,shb.step4,shb.step5,shb.number "
			+ " FROM task t "
			+ " LEFT JOIN org o on o.id = t.execut_org "
			+ " LEFT JOIN people p on p.id = t.executor "
			+ " LEFT JOIN project pro on pro.id = t.pro_id "
			+ " LEFT JOIN shb shb on shb.id = t.shb "
			+ " WHERE t.id=:id ",nativeQuery=true)
	Map<String, Object> getTaskById(@Param("id")Integer id);
	
	@Query(value="SELECT "
			+ " t.id,t.pid,t.pro_id as proId,t.title,t.org_id as orgId,t.executor,t.executor_mobile as executorMobile,t.stage_id as stageId,t.start_date as startDate,t.com_content as comContent,t.question,"
			+ " t.end_date as endDate,t.priority,t.status,t.remark,t.annex,t.pre_tasks as preTasks,	t.execut_org as executOrg,t.com_date as comDate,t.code,t.create_date as createDate,t.is_shb as isShb,t.shb,t.db_count as dbCount , t.db_date as dbDate ,"
			+ " o.name as executOrgName , p.name as executorName,pro.name as projectName ,shb.name as shbName,shb.step1,shb.step2,shb.step3,shb.step4,shb.step5,shb.number  "
			+ " FROM task t "
			+ " LEFT JOIN org o on o.id = t.execut_org "
			+ " LEFT JOIN people p on p.id = t.executor "
			+ " LEFT JOIN project pro on pro.id = t.pro_id "
			+ " LEFT JOIN shb shb on shb.id = t.shb "
			+ " WHERE t.pro_id=:proId AND t.pid=:pid  ",nativeQuery=true)
	List<Map<String, Object>> getByProIdAndPid(@Param("proId")Integer projectId, @Param("pid")Integer pid);

	@Query(value="SELECT "
			+ " t.id,t.pid,t.pro_id as proId,t.title,t.org_id as orgId,t.executor,t.executor_mobile as executorMobile,t.stage_id as stageId,t.start_date as startDate,t.com_content as comContent,t.question,"
			+ " t.end_date as endDate,t.priority,t.status,t.remark,t.annex,t.pre_tasks as preTasks,	t.execut_org as executOrg,t.com_date as comDate,t.code,t.create_date as createDate,t.is_shb as isShb,t.shb,t.db_count as dbCount , t.db_date as dbDate ,"
			+ " o.name as executOrgName , p.name as executorName,pro.name as projectName ,shb.name as shbName,shb.step1,shb.step2,shb.step3,shb.step4,shb.step5,shb.number  "
			+ " FROM task t "
			+ " LEFT JOIN org o on o.id = t.execut_org "
			+ " LEFT JOIN people p on p.id = t.executor "
			+ " LEFT JOIN project pro on pro.id = t.pro_id "
			+ " LEFT JOIN shb shb on shb.id = t.shb "
			+ " WHERE t.pro_id=:proId AND t.status=:status  ",nativeQuery=true)
	List<Map<String, Object>> getByProIdAndStatus(@Param("proId")Integer projectId, @Param("status")Integer status);
	
	@Query(value="SELECT "
			+ " t.id,t.pid,t.pro_id as proId,t.title,t.org_id as orgId,t.executor,t.executor_mobile as executorMobile,t.stage_id as stageId,t.start_date as startDate,t.com_content as comContent,t.question,"
			+ " t.end_date as endDate,t.priority,t.status,t.remark,t.annex,t.pre_tasks as preTasks,	t.execut_org as executOrg,t.com_date as comDate,t.code,t.create_date as createDate,t.is_shb as isShb,t.shb,t.db_count as dbCount , t.db_date as dbDate ,"
			+ " o.name as executOrgName , p.name as executorName,pro.name as projectName ,shb.name as shbName,shb.step1,shb.step2,shb.step3,shb.step4,shb.step5,shb.number  "
			+ " FROM task t "
			+ " LEFT JOIN org o on o.id = t.execut_org "
			+ " LEFT JOIN people p on p.id = t.executor "
			+ " LEFT JOIN project pro on pro.id = t.pro_id "
			+ " LEFT JOIN shb shb on shb.id = t.shb "
			+ " WHERE t.pro_id=:proId ",nativeQuery=true)
	List<Map<String, Object>> getByProId(@Param("proId")Integer projectId);

	@Query(value="SELECT "
			+ " t.id,t.pid,t.pro_id as proId,t.title,t.org_id as orgId,t.executor,t.executor_mobile as executorMobile,t.stage_id as stageId,t.start_date as startDate,t.com_content as comContent,t.question,"
			+ " t.end_date as endDate,t.priority,t.status,t.remark,t.annex,t.pre_tasks as preTasks,	t.execut_org as executOrg,t.com_date as comDate,t.code,t.create_date as createDate,t.is_shb as isShb,t.shb,t.db_count as dbCount , t.db_date as dbDate ,"
			+ " o.name as executOrgName , p.name as executorName,pro.name as projectName ,shb.name as shbName,shb.step1,shb.step2,shb.step3,shb.step4,shb.step5,shb.number  "
			+ " FROM task t "
			+ " LEFT JOIN org o on o.id = t.execut_org "
			+ " LEFT JOIN people p on p.id = t.executor "
			+ " LEFT JOIN project pro on pro.id = t.pro_id "
			+ " LEFT JOIN shb shb on shb.id = t.shb "
			+ " WHERE t.org_id=:orgId AND t.pro_id=:proId AND t.status in (1,3,4) ",nativeQuery=true)
	List<Map<String, Object>> getByProId(@Param("orgId")Integer orgId,@Param("proId")Integer projectId);
	
	@Query(value="SELECT "
			+ " t.id,t.pid,t.pro_id as proId,t.title,t.org_id as orgId,t.executor,t.executor_mobile as executorMobile,t.stage_id as stageId,t.start_date as startDate,t.com_content as comContent,t.question,"
			+ " t.end_date as endDate,t.priority,t.status,t.remark,t.annex,t.pre_tasks as preTasks,	t.execut_org as executOrg,t.com_date as comDate,t.code,t.create_date as createDate,t.is_shb as isShb,t.shb,t.db_count as dbCount , t.db_date as dbDate ,"
			+ " o.name as executOrgName , p.name as executorName,pro.name as projectName ,shb.name as shbName,shb.step1,shb.step2,shb.step3,shb.step4,shb.step5,shb.number  "
			+ " FROM task t "
			+ " LEFT JOIN org o on o.id = t.execut_org "
			+ " LEFT JOIN people p on p.id = t.executor "
			+ " LEFT JOIN project pro on pro.id = t.pro_id "
			+ " LEFT JOIN shb shb on shb.id = t.shb "
			+ " WHERE t.pro_id=:proId AND t.shb is not null ",nativeQuery=true)
	List<Map<String, Object>> getByProIdAndShb(@Param("proId")Integer projectId);

	List<Task> findAllByIdIn(List<Integer> chil_ids);

	List<Task> findAllByProIdAndAnnexIsNotNull(Integer projectId);
	
	
	
	/**
	 * gantt 使用
	 */
	@Query(value="SELECT "
			+ " t.id,t.pid,t.pro_id as proId,t.title as name,t.org_id as orgId,t.executor,t.executor_mobile as executorMobile,t.stage_id as stageId,t.start_date as startDate,t.com_content as comContent,t.question,"
			+ " t.end_date as endDate,t.priority,t.status,t.remark,t.annex,t.pre_tasks as preTasks,	t.execut_org as executOrg,t.com_date as comDate,t.code,t.create_date as createDate,t.is_shb as isShb,t.shb,t.db_count as dbCount , t.db_date as dbDate ,"
			+ " o.name as executOrgName , p.name as executorName,pro.name as projectName ,shb.name as shbName,shb.step1,shb.step2,shb.step3,shb.step4,shb.step5,shb.number  "
			+ " FROM task t "
			+ " LEFT JOIN org o on o.id = t.execut_org "
			+ " LEFT JOIN people p on p.id = t.executor "
			+ " LEFT JOIN project pro on pro.id = t.pro_id "
			+ " LEFT JOIN shb shb on shb.id = t.shb "
			+ " WHERE t.pro_id=:proId AND t.status=:status  ",nativeQuery=true)
	List<Map<String, Object>> getGanttByProIdAndStatus(@Param("proId")Integer projectId, @Param("status")Integer status);
	
	@Query(value="SELECT "
			+ " t.id,t.pid,t.pro_id as proId,t.title as name,t.org_id as orgId,t.executor,t.executor_mobile as executorMobile,t.stage_id as stageId,t.start_date as startDate,t.com_content as comContent,t.question,"
			+ " t.end_date as endDate,t.priority,t.status,t.remark,t.annex,t.pre_tasks as preTasks,	t.execut_org as executOrg,t.com_date as comDate,t.code,t.create_date as createDate,t.is_shb as isShb,t.shb,t.db_count as dbCount , t.db_date as dbDate ,"
			+ " o.name as executOrgName , p.name as executorName,pro.name as projectName ,shb.name as shbName,shb.step1,shb.step2,shb.step3,shb.step4,shb.step5,shb.number  "
			+ " FROM task t "
			+ " LEFT JOIN org o on o.id = t.execut_org "
			+ " LEFT JOIN people p on p.id = t.executor "
			+ " LEFT JOIN project pro on pro.id = t.pro_id "
			+ " LEFT JOIN shb shb on shb.id = t.shb "
			+ " WHERE t.pro_id=:proId ",nativeQuery=true)
	List<Map<String, Object>> getGanttByProId(@Param("proId")Integer projectId);

	List<Task> findAllByPid(Integer id);

	List<Task> findAllByProIdAndStatus(Integer proId, Integer status);

	List<Task> findAllByStatusIn(List<Integer> statuss);
	
}
