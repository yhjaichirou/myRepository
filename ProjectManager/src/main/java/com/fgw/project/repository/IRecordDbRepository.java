package com.fgw.project.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.RecordDb;

@Repository
public interface IRecordDbRepository extends JpaRepository<RecordDb , Integer> {

	List<RecordDb> findAllByDbProId(Integer orgId);
	
	@Query(value="SELECT "
			+ " rd.id,rd.db_pro_id as dbProId,rd.db_task_id as dbTaskId,rd.db_time as dbTime,rd.db_count as dbCount ,rd.db_org as dbOrg,"
			+ " rd.db_pel as dbPel,rd.db_otices_pel as dbOticesPel,rd.db_create_org as dbCreateOrg,rd.db_creater as dbCreater,rd.status,"
			+ " p.name as proName,t.title as taskName,o1.name as dbOrgName,o2.name as dbCreateOrgName,p1.name as dbPelName,p2.name as dbCreaterName "
			+ " FROM record_db rd "
			+ " LEFT JOIN project p on p.id = rd.db_pro_id "
			+ " LEFT JOIN task t on t.id = rd.db_task_id "
			+ " LEFT JOIN org o1 on o1.id = rd.db_org "
			+ " LEFT JOIN org o2 on o2.id = rd.db_create_org "
			+ " LEFT JOIN people p1 on p1.id = rd.db_pel "
			+ " LEFT JOIN people p2 on p2.id = rd.db_creater "
			+ " WHERE 1=1 AND rd.db_org=:dbOrg AND rd.status=:status AND t.name LIKE %:search% ",nativeQuery=true)
	List<Map<String,Object>> getAllByDbOrgAndStatusAndSearch(@Param("dbOrg")Integer dbOrg,@Param("status")String status,@Param("search")String search);
	
	@Query(value="SELECT "
			+ " rd.id,rd.db_pro_id as dbProId,rd.db_task_id as dbTaskId,rd.db_time as dbTime,rd.db_count as dbCount ,rd.db_org as dbOrg,"
			+ " rd.db_pel as dbPel,rd.db_otices_pel as dbOticesPel,rd.db_create_org as dbCreateOrg,rd.db_creater as dbCreater,rd.status,"
			+ " p.name as proName,t.title as taskName,o1.name as dbOrgName,o2.name as dbCreateOrgName,p1.name as dbPelName,p2.name as dbCreaterName "
			+ " FROM record_db rd "
			+ " LEFT JOIN project p on p.id = rd.db_pro_id "
			+ " LEFT JOIN task t on t.id = rd.db_task_id "
			+ " LEFT JOIN org o1 on o1.id = rd.db_org "
			+ " LEFT JOIN org o2 on o2.id = rd.db_create_org "
			+ " LEFT JOIN people p1 on p1.id = rd.db_pel "
			+ " LEFT JOIN people p2 on p2.id = rd.db_creater "
			+ " WHERE 1=1 AND rd.db_org=:dbOrg AND  t.name LIKE %:search% ",nativeQuery=true)
	List<Map<String,Object>> getAllByDbOrgAndSearch(@Param("dbOrg")Integer dbOrg,@Param("search")String search);
	
	@Query(value="SELECT "
			+ " rd.id,rd.db_pro_id as dbProId,rd.db_task_id as dbTaskId,rd.db_time as dbTime,rd.db_count as dbCount ,rd.db_org as dbOrg,"
			+ " rd.db_pel as dbPel,rd.db_otices_pel as dbOticesPel,rd.db_create_org as dbCreateOrg,rd.db_creater as dbCreater,rd.status,"
			+ " p.name as proName,t.title as taskName,o1.name as dbOrgName,o2.name as dbCreateOrgName,p1.name as dbPelName,p2.name as dbCreaterName "
			+ " FROM record_db rd "
			+ " LEFT JOIN project p on p.id = rd.db_pro_id "
			+ " LEFT JOIN task t on t.id = rd.db_task_id "
			+ " LEFT JOIN org o1 on o1.id = rd.db_org "
			+ " LEFT JOIN org o2 on o2.id = rd.db_create_org "
			+ " LEFT JOIN people p1 on p1.id = rd.db_pel "
			+ " LEFT JOIN people p2 on p2.id = rd.db_creater "
			+ " WHERE 1=1 AND rd.db_org=:dbOrg AND rd.status=:status ",nativeQuery=true)
	List<Map<String,Object>> getAllByDbOrgAndStatus(@Param("dbOrg")Integer dbOrg,@Param("status")String status);
	
	@Query(value="SELECT "
			+ " rd.id,rd.db_pro_id as dbProId,rd.db_task_id as dbTaskId,rd.db_time as dbTime,rd.db_count as dbCount ,rd.db_org as dbOrg,"
			+ " rd.db_pel as dbPel,rd.db_otices_pel as dbOticesPel,rd.db_create_org as dbCreateOrg,rd.db_creater as dbCreater,rd.status,"
			+ " p.name as proName,t.title as taskName,o1.name as dbOrgName,o2.name as dbCreateOrgName,p1.name as dbPelName,p2.name as dbCreaterName "
			+ " FROM record_db rd "
			+ " LEFT JOIN project p on p.id = rd.db_pro_id "
			+ " LEFT JOIN task t on t.id = rd.db_task_id "
			+ " LEFT JOIN org o1 on o1.id = rd.db_org "
			+ " LEFT JOIN org o2 on o2.id = rd.db_create_org "
			+ " LEFT JOIN people p1 on p1.id = rd.db_pel "
			+ " LEFT JOIN people p2 on p2.id = rd.db_creater "
			+ " WHERE 1=1 AND rd.db_org=:dbOrg ",nativeQuery=true)
	List<Map<String,Object>> getAllByDbOrg(@Param("dbOrg")Integer dbOrg);
	
}
