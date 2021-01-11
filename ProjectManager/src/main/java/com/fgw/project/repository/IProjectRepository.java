package com.fgw.project.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fgw.project.model.po.Group;
import com.fgw.project.model.po.Project;

/**
 * 用户表
 * @author yhj
 * @date 2020年12月17日
 */
@Repository
public interface IProjectRepository extends JpaRepository<Project , Integer> {
	
	List<Project> findAllByOrgId(Integer orgId);
	
	List<Project> findAllByOrgIdAndStatus(Integer orgId,Integer status);

	@Query(value="SELECT "
			+ " p.id,p.name,p.org_id as orgId,p.industry_category as industryCategory,p.content,p.number,p.maturity,p.docking_date as dockingDate,p.leader,p.leadenter,"
			+ " p.coordinate,p.task_prefix as taskPrefix,p.visible_range as visibleRange,p.joiners,p.process,p.remarks,p.invest,p.expected_date as expectedDate,"
			+ " p.start_date as startDate,p.approve_code as approveCode,p.lx_is_comapprove as lxIsComapprove,p.lx_handle_level as lxHandleLevel,p.lx_is_sendappdepart as lxIsSendappdepart,"
			+ " p.ydcard_is_hascard as ydcardIsHascard,p.ydcard_handle_level as ydcardHandleLevelmp,p.ydcard_is_sendappdepart as ydcardIsSendappdepart,"
			+ " p.energy_is_censor as energyIsCensor,p.energy_handle_level as energyHandleLevel,p.energy_is_sendappdepart as energyIsSendappdepart,"
			+ " p.lc_is_bl as lcIsBl,p.lc_handle_level as lcHandleLevel,p.lc_is_sendappdepart as lcIsSendappdepart,"
			+ " p.td_is_bl as tdIsBl,p.td_handle_level as tdHandleLevel,p.td_is_sendappdepart as tdIsSendappdepart,"
			+ " p.envir_is_bl as envirIsBl,p.envir_handle_level as envirHandleLevel,p.envir_is_sendappdepart as envirIsSendappdepart,"
			+ " p.sg_is_bl as sgIsBl,p.sg_handle_level as sgHandleLevel,p.sg_is_sendappdepart as sgIsSendappdepart,"
			+ " p.xf_is_bl as xfIsBl,p.xf_handle_level as xfHandleLevel,p.xf_is_sendappdepart as xfIsSendappdepart,"
			+ " p.rf_is_bl as rfIsBl,p.rf_handle_level as rf_handleLevel,p.rf_is_sendappdepart as rfIsSendappdepart,"
			+ " p.other_bl as otherBl,p.diff_and_problem as diffAndProblem,p.pro_manager as proManager,"
			+ " p.pro_manager_mobile as proManagerMobile,p.stage,p.status,p.complete_date as completeDate,   "
			+ " o.name as orgName , ic.category_name as categoryName "
			+ " FROM project p "
			+ " LEFT JOIN org o on o.id = p.org_id "
			+ " LEFT JOIN industry_category ic on ic.id = p.industry_category "
			+ " WHERE p.name like %:search% AND p.status =:status AND p.org_id=:orgId ",nativeQuery=true)
	List<Map<String, Object>> getAllProjectOfOrgIdAndSearch(@Param("orgId")Integer orgId,@Param("status")Integer status,@Param("search")String search);
	
	@Query(value="SELECT "
			+ " p.id,p.name,p.org_id as orgId,p.industry_category as industryCategory,p.content,p.number,p.maturity,p.docking_date as dockingDate,p.leader,p.leadenter,"
			+ " p.coordinate,p.task_prefix as taskPrefix,p.visible_range as visibleRange,p.joiners,p.process,p.remarks,p.invest,p.expected_date as expectedDate,"
			+ " p.start_date as startDate,p.approve_code as approveCode,p.lx_is_comapprove as lxIsComapprove,p.lx_handle_level as lxHandleLevel,p.lx_is_sendappdepart as lxIsSendappdepart,"
			+ " p.ydcard_is_hascard as ydcardIsHascard,p.ydcard_handle_level as ydcardHandleLevelmp,p.ydcard_is_sendappdepart as ydcardIsSendappdepart,"
			+ " p.energy_is_censor as energyIsCensor,p.energy_handle_level as energyHandleLevel,p.energy_is_sendappdepart as energyIsSendappdepart,"
			+ " p.lc_is_bl as lcIsBl,p.lc_handle_level as lcHandleLevel,p.lc_is_sendappdepart as lcIsSendappdepart,"
			+ " p.td_is_bl as tdIsBl,p.td_handle_level as tdHandleLevel,p.td_is_sendappdepart as tdIsSendappdepart,"
			+ " p.envir_is_bl as envirIsBl,p.envir_handle_level as envirHandleLevel,p.envir_is_sendappdepart as envirIsSendappdepart,"
			+ " p.sg_is_bl as sgIsBl,p.sg_handle_level as sgHandleLevel,p.sg_is_sendappdepart as sgIsSendappdepart,"
			+ " p.xf_is_bl as xfIsBl,p.xf_handle_level as xfHandleLevel,p.xf_is_sendappdepart as xfIsSendappdepart,"
			+ " p.rf_is_bl as rfIsBl,p.rf_handle_level as rf_handleLevel,p.rf_is_sendappdepart as rfIsSendappdepart,"
			+ " p.other_bl as otherBl,p.diff_and_problem as diffAndProblem,p.pro_manager as proManager,"
			+ " p.pro_manager_mobile as proManagerMobile,p.stage,p.status,p.complete_date as completeDate,   "
			+ " o.name as orgName , ic.category_name as categoryName "
			+ " FROM project p "
			+ " LEFT JOIN org o on o.id = p.org_id "
			+ " LEFT JOIN industry_category ic on ic.id = p.industry_category "
			+ " WHERE  p.status =:status AND p.org_id=:orgId ",nativeQuery=true)
	List<Map<String, Object>> getAllProjectOfOrgIdAndStatus(@Param("orgId")Integer orgId,@Param("status")Integer status);
	
	@Query(value="SELECT "
			+ " p.id,p.name,p.org_id as orgId,p.industry_category as industryCategory,p.content,p.number,p.maturity,p.docking_date as dockingDate,p.leader,p.leadenter,"
			+ " p.coordinate,p.task_prefix as taskPrefix,p.visible_range as visibleRange,p.joiners,p.process,p.remarks,p.invest,p.expected_date as expectedDate,"
			+ " p.start_date as startDate,p.approve_code as approveCode,p.lx_is_comapprove as lxIsComapprove,p.lx_handle_level as lxHandleLevel,p.lx_is_sendappdepart as lxIsSendappdepart,"
			+ " p.ydcard_is_hascard as ydcardIsHascard,p.ydcard_handle_level as ydcardHandleLevelmp,p.ydcard_is_sendappdepart as ydcardIsSendappdepart,"
			+ " p.energy_is_censor as energyIsCensor,p.energy_handle_level as energyHandleLevel,p.energy_is_sendappdepart as energyIsSendappdepart,"
			+ " p.lc_is_bl as lcIsBl,p.lc_handle_level as lcHandleLevel,p.lc_is_sendappdepart as lcIsSendappdepart,"
			+ " p.td_is_bl as tdIsBl,p.td_handle_level as tdHandleLevel,p.td_is_sendappdepart as tdIsSendappdepart,"
			+ " p.envir_is_bl as envirIsBl,p.envir_handle_level as envirHandleLevel,p.envir_is_sendappdepart as envirIsSendappdepart,"
			+ " p.sg_is_bl as sgIsBl,p.sg_handle_level as sgHandleLevel,p.sg_is_sendappdepart as sgIsSendappdepart,"
			+ " p.xf_is_bl as xfIsBl,p.xf_handle_level as xfHandleLevel,p.xf_is_sendappdepart as xfIsSendappdepart,"
			+ " p.rf_is_bl as rfIsBl,p.rf_handle_level as rf_handleLevel,p.rf_is_sendappdepart as rfIsSendappdepart,"
			+ " p.other_bl as otherBl,p.diff_and_problem as diffAndProblem,p.pro_manager as proManager,"
			+ " p.pro_manager_mobile as proManagerMobile,p.stage,p.status,p.complete_date as completeDate,   "
			+ " o.name as orgName , ic.category_name as categoryName "
			+ " FROM project p "
			+ " LEFT JOIN org o on o.id = p.org_id "
			+ " LEFT JOIN industry_category ic on ic.id = p.industry_category "
			+ " WHERE p.name like %:search%  AND p.org_id=:orgId ",nativeQuery=true)
	List<Map<String, Object>> getAllProjectOfOrgIdAndSearch(@Param("orgId")Integer orgId,@Param("search")String search);
	
	@Query(value="SELECT "
			+ " p.id,p.name,p.org_id as orgId,p.industry_category as industryCategory,p.content,p.number,p.maturity,p.docking_date as dockingDate,p.leader,p.leadenter,"
			+ " p.coordinate,p.task_prefix as taskPrefix,p.visible_range as visibleRange,p.joiners,p.process,p.remarks,p.invest,p.expected_date as expectedDate,"
			+ " p.start_date as startDate,p.approve_code as approveCode,p.lx_is_comapprove as lxIsComapprove,p.lx_handle_level as lxHandleLevel,p.lx_is_sendappdepart as lxIsSendappdepart,"
			+ " p.ydcard_is_hascard as ydcardIsHascard,p.ydcard_handle_level as ydcardHandleLevelmp,p.ydcard_is_sendappdepart as ydcardIsSendappdepart,"
			+ " p.energy_is_censor as energyIsCensor,p.energy_handle_level as energyHandleLevel,p.energy_is_sendappdepart as energyIsSendappdepart,"
			+ " p.lc_is_bl as lcIsBl,p.lc_handle_level as lcHandleLevel,p.lc_is_sendappdepart as lcIsSendappdepart,"
			+ " p.td_is_bl as tdIsBl,p.td_handle_level as tdHandleLevel,p.td_is_sendappdepart as tdIsSendappdepart,"
			+ " p.envir_is_bl as envirIsBl,p.envir_handle_level as envirHandleLevel,p.envir_is_sendappdepart as envirIsSendappdepart,"
			+ " p.sg_is_bl as sgIsBl,p.sg_handle_level as sgHandleLevel,p.sg_is_sendappdepart as sgIsSendappdepart,"
			+ " p.xf_is_bl as xfIsBl,p.xf_handle_level as xfHandleLevel,p.xf_is_sendappdepart as xfIsSendappdepart,"
			+ " p.rf_is_bl as rfIsBl,p.rf_handle_level as rf_handleLevel,p.rf_is_sendappdepart as rfIsSendappdepart,"
			+ " p.other_bl as otherBl,p.diff_and_problem as diffAndProblem,p.pro_manager as proManager,"
			+ " p.pro_manager_mobile as proManagerMobile,p.stage,p.status,p.complete_date as completeDate,   "
			+ " o.name as orgName , ic.category_name as categoryName "
			+ " FROM project p "
			+ " LEFT JOIN org o on o.id = p.org_id "
			+ " LEFT JOIN industry_category ic on ic.id = p.industry_category "
			+ " WHERE p.status in(1,2,3,4,5,6,7,8,9,10) AND p.org_id=:orgId ",nativeQuery=true)
	List<Map<String, Object>> getAllProjectOfOrgId(@Param("orgId")Integer orgId);

	
	@Query(value="SELECT "
			+ " p.id,p.name,p.org_id as orgId,p.industry_category as industryCategory,p.content,p.number,p.maturity,p.docking_date as dockingDate,p.leader,p.leadenter,"
			+ " p.coordinate,p.task_prefix as taskPrefix,p.visible_range as visibleRange,p.joiners,p.process,p.remarks,p.invest,p.expected_date as expectedDate,"
			+ " p.start_date as startDate,p.approve_code as approveCode,p.lx_is_comapprove as lxIsComapprove,p.lx_handle_level as lxHandleLevel,p.lx_is_sendappdepart as lxIsSendappdepart,"
			+ " p.ydcard_is_hascard as ydcardIsHascard,p.ydcard_handle_level as ydcardHandleLevelmp,p.ydcard_is_sendappdepart as ydcardIsSendappdepart,"
			+ " p.energy_is_censor as energyIsCensor,p.energy_handle_level as energyHandleLevel,p.energy_is_sendappdepart as energyIsSendappdepart,"
			+ " p.lc_is_bl as lcIsBl,p.lc_handle_level as lcHandleLevel,p.lc_is_sendappdepart as lcIsSendappdepart,"
			+ " p.td_is_bl as tdIsBl,p.td_handle_level as tdHandleLevel,p.td_is_sendappdepart as tdIsSendappdepart,"
			+ " p.envir_is_bl as envirIsBl,p.envir_handle_level as envirHandleLevel,p.envir_is_sendappdepart as envirIsSendappdepart,"
			+ " p.sg_is_bl as sgIsBl,p.sg_handle_level as sgHandleLevel,p.sg_is_sendappdepart as sgIsSendappdepart,"
			+ " p.xf_is_bl as xfIsBl,p.xf_handle_level as xfHandleLevel,p.xf_is_sendappdepart as xfIsSendappdepart,"
			+ " p.rf_is_bl as rfIsBl,p.rf_handle_level as rf_handleLevel,p.rf_is_sendappdepart as rfIsSendappdepart,"
			+ " p.other_bl as otherBl,p.diff_and_problem as diffAndProblem,p.pro_manager as proManager,"
			+ " p.pro_manager_mobile as proManagerMobile,p.enter_manager as enterManager,p.enter_manager_mobile as enterManagerMobile,p.stage,p.status,p.complete_date as completeDate,   "
			+ " o.name as orgName , ic.category_name as categoryName,pel1.name as leaderName ,o2.name as leadenterName ,pel2.name as coordinateName ,"
			+ " pel3.name as proManagerName , pel4.name as enterManagerName "
			+ " FROM project p "
			+ " LEFT JOIN org o on o.id = p.org_id "
			+ " LEFT JOIN org o2 on o2.id = p.leadenter "
			+ " LEFT JOIN industry_category ic on ic.id = p.industry_category "
			+ " LEFT JOIN people pel1 on pel1.id = p.leader "
			+ " LEFT JOIN people pel2 on pel2.id = p.coordinate "
			+ " LEFT JOIN people pel3 on pel3.id = p.pro_manager "
			+ " LEFT JOIN people pel4 on pel4.id = p.enter_manager "
			+ " WHERE p.id=:id ",nativeQuery=true)
	Map<String, Object> getProjectById(Integer id);

	List<Project> findAllByStatusNot(Integer status);

	List<Project> findAllByStatusIn(List<Integer> statuss);

	
	
}
