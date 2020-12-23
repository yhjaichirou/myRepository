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
			+ " WHERE p.name like %:search% AND p.status in(:status) AND p.org_id=:orgId ",nativeQuery=true)
	List<Map<String, Object>> getAllProjectOfOrgIdAndSearch(@Param("orgId")Integer orgId,@Param("status")String status,@Param("search")String search);
	
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
			+ " WHERE p.status in(:status) AND p.org_id=:orgId ",nativeQuery=true)
	List<Map<String, Object>> getAllProjectOfOrgId(@Param("orgId")Integer orgId,@Param("status")String status);

	
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
			+ " o.name as orgName , ic.category_name as categoryName"
			+ " FROM project p "
			+ " LEFT JOIN org o on o.id = p.org_id "
			+ " LEFT JOIN industry_category ic on ic.id = p.industry_category "
			+ " WHERE p.id=:id ",nativeQuery=true)
	Map<String, Object> getProjectById(Integer id);
	
}
