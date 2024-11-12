/**
 *  Defines the dummy entity  for Stored Procedure. 
 */
package nirmalya.aatithya.restmodule.common;

import java.io.IOException;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity

@NamedStoredProcedureQueries({
	
	
	
	
	@NamedStoredProcedureQuery(name = "district_dropdown_institution_list", procedureName = "district_dropdown_institution_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class),
	}),
	@NamedStoredProcedureQuery(name = "district_project_not_updated_list", procedureName = "district_project_not_updated_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "admin_project_not_updated_list", procedureName = "admin_project_not_updated_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "project_details_for_excel_download", procedureName = "project_details_for_excel_download", parameters = {
	}),
	@NamedStoredProcedureQuery(name = "district_project_details_for_excel_download", procedureName = "district_project_details_for_excel_download", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "project_status_update_view", procedureName = "project_status_update_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "institution", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "scheme", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "agency", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "sanctionYear", type = String.class),
			
	}),
	@NamedStoredProcedureQuery(name = "admin_dropdown_block_list_edit", procedureName = "admin_dropdown_block_list_edit", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "mob", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "edit_user_district", procedureName = "edit_user_district", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "edit_user_state", procedureName = "edit_user_state", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class)
	}),
	
	@NamedStoredProcedureQuery(name = "modify_user", procedureName = "modify_user", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "checkedCheckboxes", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "distid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "mob", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_jeIds", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "modify_user_role_assign", procedureName = "modify_user_role_assign", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "checkedCheckboxes", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "roleType", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "modify_user_district", procedureName = "modify_user_district", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_districtName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobileNumber", type = String.class),
	}),
	

	@NamedStoredProcedureQuery(name = "modify_user_state", procedureName = "modify_user_state", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_adminId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobileNumber", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = String.class),
	}),
	

	@NamedStoredProcedureQuery(name = "modify_user_admin", procedureName = "modify_user_admin", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_adminId", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_district", type = String.class),
//			@StoredProcedureParameter(mode s= ParameterMode.IN, name = "p_districtName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobileNumber", type = String.class),
	}),
	
	
	@NamedStoredProcedureQuery(name = "edit_user_admin", procedureName = "edit_user_admin", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "edit_user_je", procedureName = "edit_user_je", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class)
	}),
	//nhm admin
	@NamedStoredProcedureQuery(name = "get_district_name_check", procedureName = "get_district_name_check", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtName", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "admin_workprogress_count", procedureName = "admin_workprogress_count", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class)
	}),
	
	@NamedStoredProcedureQuery(name = "get_assigned_block", procedureName = "get_assigned_block", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)
	}),
	
	@NamedStoredProcedureQuery(name = "admin_project_status", procedureName = "admin_project_status", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "admin_project_physical_abstract_report", procedureName = "admin_project_physical_abstract_report", parameters = {}),
	
	@NamedStoredProcedureQuery(name = "admin_project_details_view", procedureName = "admin_project_details_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "institution", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "scheme", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "agency", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "sanctionYear", type = String.class),
	}),	
	
	
	@NamedStoredProcedureQuery(name = "admin_dashboard_pie", procedureName = "admin_dashboard_pie", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class)
	}),
	
	@NamedStoredProcedureQuery(name = "admin_dashboard_bar", procedureName = "admin_dashboard_bar", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class)
	}),
	
	@NamedStoredProcedureQuery(name = "district_workprogress_count", procedureName = "district_workprogress_count", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "district_dashboard_pie", procedureName = "district_dashboard_pie", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "district_dashboard_bar", procedureName = "district_dashboard_bar", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "admin_district_view", procedureName = "admin_district_view", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class)
	}),
	
	@NamedStoredProcedureQuery(name = "admin_block_view", procedureName = "admin_block_view", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class)
	}),
	
	@NamedStoredProcedureQuery(name = "dist_block_view", procedureName = "dist_block_view", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class)
	}),
	
		/*
		 * @NamedStoredProcedureQuery(name = "project_status_update_view", procedureName
		 * = "project_status_update_view", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "institution", type
		 * = String.class)
		 * 
		 * }),
		 */
	
	@NamedStoredProcedureQuery(name = "admin_institution_view", procedureName = "admin_institution_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class)
	}),
	
	
	@NamedStoredProcedureQuery(name = "admin_scheme_view", procedureName = "admin_scheme_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class)
	}),
	
	@NamedStoredProcedureQuery(name = "admin_agency_view", procedureName = "admin_agency_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class)
	}),
	
	@NamedStoredProcedureQuery(name = "admin_physical_status_view", procedureName = "admin_physical_status_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class)
	}),
	
	@NamedStoredProcedureQuery(name = "admin_project_view", procedureName = "admin_project_view", parameters = { }),
	@NamedStoredProcedureQuery(name = "admin_project_scheme_abstract_report", procedureName = "admin_project_scheme_abstract_report", parameters = { }),
	@NamedStoredProcedureQuery(name = "admin_project_agency_abstract_report", procedureName = "admin_project_agency_abstract_report", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "admin_dropdow_physical_status_list", procedureName = "admin_dropdow_physical_status_list", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "get_district_list", procedureName = "get_district_list", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "admin_block_list", procedureName = "admin_block_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class) }),
	
	@NamedStoredProcedureQuery(name = "get_institude_list", procedureName = "get_institude_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class) }),
	
	
	@NamedStoredProcedureQuery(name = "get_Scheme_list", procedureName = "get_Scheme_list", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "get_Agency_list", procedureName = "get_Agency_lists", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "nhm_physical_status_list", procedureName = "nhm_physical_status_list", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "add_admin_project_update", procedureName = "add_admin_project_update", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_districtId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_institude", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_scheme", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_agency", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sanctionYear", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_projectName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_approvedAmt", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_abyaCost", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_floor", type = String.class),
			
	}),
	@NamedStoredProcedureQuery(name = "adminprojectphysicalstatusview", procedureName = "adminprojectphysicalstatusview", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "category", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "scheme", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "agency", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "sanctionYear", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "physicalStatus", type = String.class),
			
	}),
	@NamedStoredProcedureQuery(name = "distprojectphysicalstatusview", procedureName = "distprojectphysicalstatusview", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "physicalStatus", type = String.class)
			
	}),
	
	@NamedStoredProcedureQuery(name = "admin_sanction_year", procedureName = "admin_sanction_year", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "year", type = String.class)
	}),
	
	
	
	
	@NamedStoredProcedureQuery(name = "update_dist_password", procedureName = "update_dist_password", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_distid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "update_block_password", procedureName = "update_block_password", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "get_oldpassword", procedureName = "get_oldpassword", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "oldpassword", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "get_oldpassword_details", procedureName = "get_oldpassword_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "oldpassword", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "nhm_district_list", procedureName = "nhm_district_list", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "nhm_Scheme_list", procedureName = "nhm_Scheme_list", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "nhm_Agency_list", procedureName = "nhm_Agency_list", parameters = { }),
	
	
	@NamedStoredProcedureQuery(name = "get_block_list", procedureName = "get_block_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "get_institude", procedureName = "get_institude", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class) }),
	
	@NamedStoredProcedureQuery(name = "admin_user_role_assign", procedureName = "admin_user_role_assign", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userType", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "add_project_update", procedureName = "add_project_update", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_institude", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_scheme", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_category", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_agency", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sanctionYear", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_projectName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_approvedAmt", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_abyaCost", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
	}),

	@NamedStoredProcedureQuery(name = "get_sanction_year", procedureName = "get_sanction_year", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "year", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "blockidds", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
	}),
	
	
	@NamedStoredProcedureQuery(name = "get_block_dropdown_list", procedureName = "get_block_dropdown_list", parameters = {}),
	
	@NamedStoredProcedureQuery(name = "block_institution_list", procedureName = "block_institution_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class),
	}),
	
	@NamedStoredProcedureQuery(name = "block_catagory_list", procedureName = "block_catagory_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class),
	}),
	
	@NamedStoredProcedureQuery(name = "block_agency_dropdown_list", procedureName = "block_agency_dropdown_list", parameters = {}),
	
	@NamedStoredProcedureQuery(name = "get_mobile_version_list", procedureName = "get_mobile_version_list", parameters = {}),
	
	@NamedStoredProcedureQuery(name = "block_physicalstatus_dropdown_list", procedureName = "block_physicalstatus_dropdown_list", parameters = {
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "projectId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "floorNo", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "post_project_status_api", procedureName = "post_project_status_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "valuess", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "projectId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "projectIdList", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "projectPhysicalStatus", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "projectFloorList", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "block_Project_list_api", procedureName = "block_Project_list_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class),
	}),
	
	@NamedStoredProcedureQuery(name = "dropdown_district_list", procedureName = "dropdown_district_list", parameters = {}),
	
	@NamedStoredProcedureQuery(name = "project_details_list_api", procedureName = "project_details_list_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniqueId", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "project_financial_details", procedureName = "project_financial_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniqueId", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "post_Financial_status_api", procedureName = "post_Financial_status_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "valuess", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "projectId", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "get_category", procedureName = "get_category", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class),
	}),
	@NamedStoredProcedureQuery(name = "get_admin_block_list", procedureName = "get_admin_block_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class),
	}),
	@NamedStoredProcedureQuery(name = "get_admin_institution_list", procedureName = "get_admin_institution_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class),
	}),
	@NamedStoredProcedureQuery(name = "admin_dropdown_district_list", procedureName = "admin_dropdown_district_list", parameters = {}),
	
	@NamedStoredProcedureQuery(name = "admin_dropdown_block_list", procedureName = "admin_dropdown_block_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class),
	}),
	@NamedStoredProcedureQuery(name = "admin_dropdown_institution_list", procedureName = "admin_dropdown_institution_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "category", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "categoryName", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "nhm_admin_scheme_list", procedureName = "nhm_admin_scheme_list", parameters = {}),
	
	@NamedStoredProcedureQuery(name = "nhm_admin_agency_list", procedureName = "nhm_admin_agency_list", parameters = {}),
	
	@NamedStoredProcedureQuery(name = "admin_add_project_update", procedureName = "admin_add_project_update", parameters = {
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_projectId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_districtId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_institude", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_category", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_agency", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_projectName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_latitude", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_longitude", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_availableCost", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_reverserabyacost", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "financialList", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "admin_modify_project_update", procedureName = "admin_modify_project_update", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_projectId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_districtId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_institude", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_category", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_agency", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_projectName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_availableCost", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_reverserabyacost", type = String.class),
		//	@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fundRelease", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "financialList", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "get_sanction_year_admin", procedureName = "get_sanction_year_admin", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "year", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "blockidds", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
	}),
	
	
	@NamedStoredProcedureQuery(name = "get_dist_category", procedureName = "get_dist_category", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class),
	}),
//	@NamedStoredProcedureQuery(name = "block_physical_status_list", procedureName = "block_physical_status_list", parameters = {
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "floorNo", type = Integer.class),
//	}),
//	
	
	
	@NamedStoredProcedureQuery(name = "admin_physical_status", procedureName = "admin_physical_status", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "jeNum", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "admin_project_financial_status", procedureName = "admin_project_financial_status", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "jeNum", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "admin_loginpage_count", procedureName = "admin_loginpage_count", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "admin_login_piechart", procedureName = "admin_login_piechart", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "dist_dropdown_district_list", procedureName = "dist_dropdown_district_list", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "dist_dropdown_block_list", procedureName = "dist_dropdown_block_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class),
	}),
	
	@NamedStoredProcedureQuery(name = "dist_dropdown_institution_list", procedureName = "dist_dropdown_institution_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = Integer.class),
	}),
	
	@NamedStoredProcedureQuery(name = "district_project_report", procedureName = "district_project_report", parameters = {
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "institution", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)
			
	}),
	
	@NamedStoredProcedureQuery(name = "get_je_assinged_block", procedureName = "get_je_assinged_block", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "api_block_Project_list", procedureName = "api_block_Project_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "api_Project_details", procedureName = "api_Project_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniqueId", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "api_project_financial_details", procedureName = "api_project_financial_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniqueId", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "api_phystatus_dropdown", procedureName = "api_phystatus_dropdown", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "projectid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "floorno", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "api_floor_dropdown", procedureName = "api_floor_dropdown", parameters = {
	}),
	
	@NamedStoredProcedureQuery(name = "edit_project_details", procedureName = "edit_project_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_projectId", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "district_modify", procedureName = "district_modify", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_distid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "edit_district_Details", procedureName = "edit_district_Details", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_distid", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "update_login_token", procedureName = "update_login_token", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_token", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class)
	}),
@NamedStoredProcedureQuery(name = "district_add", procedureName = "district_add", parameters = { 
			
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class)
	}),

@NamedStoredProcedureQuery(name = "audit_details_add", procedureName = "audit_details_add", parameters = { 
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_browserName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ipAddress", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_event", type = String.class),
		//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_token", type = String.class),
}),

@NamedStoredProcedureQuery(name = "audit_add_logout_details", procedureName = "audit_add_logout_details", parameters = { 
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_browserName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ipAddress", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_event", type = String.class),
}),

@NamedStoredProcedureQuery(name = "edit_block_Details", procedureName = "edit_block_Details", parameters = { 
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockid", type = String.class)
}),
@NamedStoredProcedureQuery(name = "edit_block_Details_dist", procedureName = "edit_block_Details_dist", parameters = { 
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockid", type = String.class)
}),
@NamedStoredProcedureQuery(name = "block_modify", procedureName = "block_modify", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockid", type = String.class),
		//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockname", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
}),
@NamedStoredProcedureQuery(name = "block_add", procedureName = "block_add", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockname", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
}),
@NamedStoredProcedureQuery(name = "edit_institution_Details", procedureName = "edit_institution_Details", parameters = { 
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_instId", type = String.class)
}),
@NamedStoredProcedureQuery(name = "institution_modify", procedureName = "institution_modify", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_instId", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_instName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_latitude", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_longitude", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryId", type = String.class),
		
}),
@NamedStoredProcedureQuery(name = "institution_add", procedureName = "institution_add", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_districtId", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockId", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryId", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_instName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_latitude", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_longitude", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
		
}),
@NamedStoredProcedureQuery(name = "edit_scheme_Details", procedureName = "edit_scheme_Details", parameters = { 
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_blockid", type = String.class)
}),
@NamedStoredProcedureQuery(name = "scheme_modify", procedureName = "scheme_modify", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_schemeid", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_schemename", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
}),
@NamedStoredProcedureQuery(name = "scheme_add", procedureName = "scheme_add", parameters = { 
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_schemename", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class)
}),
	
@NamedStoredProcedureQuery(name = "edit_agency_Details", procedureName = "edit_agency_Details", parameters = { 
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_agencyid", type = String.class)
}),
@NamedStoredProcedureQuery(name = "agency_modify", procedureName = "agency_modify", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_agencyid", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_agencyname", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class)
}),
@NamedStoredProcedureQuery(name = "agency_add", procedureName = "agency_add", parameters = { 
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_agencyname", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class)
}),
	
@NamedStoredProcedureQuery(name = "edit_physical_status_Details", procedureName = "edit_physical_status_Details", parameters = { 
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phyid", type = String.class)
}),
@NamedStoredProcedureQuery(name = "physical_status_modify", procedureName = "physical_status_modify", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phyid", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phyname", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class)
		
}),
@NamedStoredProcedureQuery(name = "physical_status_add", procedureName = "physical_status_add", parameters = { 
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phyname", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class)
}),
	@NamedStoredProcedureQuery(name = "district_list_dropdown_dashboard", procedureName = "district_list_dropdown_dashboard", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "get_admin_block_list_dashboard", procedureName = "get_admin_block_list_dashboard", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class)
	}),
	
	
	@NamedStoredProcedureQuery(name = "add_user", procedureName = "add_user", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "checkedCheckboxes", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobileNo", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "add_user_district", procedureName = "add_user_district", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_districtName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobileNumber", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
	}),
	

	@NamedStoredProcedureQuery(name = "add_user_state", procedureName = "add_user_state", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_adminId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobileNumber", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_stateName", type = String.class),
		
	}),
	

	@NamedStoredProcedureQuery(name = "add_user_admin", procedureName = "add_user_admin", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_adminId", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_district", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_districtName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobileNumber", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
	}),
	
		/*
		 * @NamedStoredProcedureQuery(name = "admin_user_view", procedureName =
		 * "admin_user_view", parameters = { // @StoredProcedureParameter(mode =
		 * ParameterMode.IN, name = "pageno", type = Integer.class), }),
		 */
	
	
	@NamedStoredProcedureQuery(name = "admin_user_view", procedureName = "admin_user_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "typesss", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
		}),
		
	
	@NamedStoredProcedureQuery(name = "district_project_details_view", procedureName = "district_project_details_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "category", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "institution", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "scheme", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "agency", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "sanctionYear", type = String.class),
	}),


	@NamedStoredProcedureQuery(name = "district_manage_project_view", procedureName = "district_manage_project_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "category", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "institution", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "scheme", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "agency", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "sanctionYear", type = String.class),
	}),
//	@NamedStoredProcedureQuery(name = "admin_instwise_project_details", procedureName = "admin_instwise_project_details", parameters = {
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "category", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "institution", type = String.class),
//	}),
	@NamedStoredProcedureQuery(name = "admin_instwise_project_details", procedureName = "admin_instwise_project_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "get_district_dropdown_exist", procedureName = "get_district_dropdown_exist", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtCode", type = String.class),
	}),
	
	
	
	@NamedStoredProcedureQuery(name = "admin_dropdown_category_list", procedureName = "admin_dropdown_category_list", parameters = {}),
	
	@NamedStoredProcedureQuery(name = "get_je_name", procedureName = "get_je_name", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class)
	}),
	
	@NamedStoredProcedureQuery(name = "admin_login_projects", procedureName = "admin_login_projects", parameters = {
	}),
	@NamedStoredProcedureQuery(name = "projects_agency_count", procedureName = "projects_agency_count", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "projects_scheme_count", procedureName = "projects_scheme_count", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class)
	}),
//	
//	@NamedStoredProcedureQuery(name = "district_financial_report", procedureName = "district_financial_report", parameters = { 
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "scheme", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "sanctionYear", type = String.class),
//	}),
	
	
	@NamedStoredProcedureQuery(name = "admin_get_address", procedureName = "admin_get_address", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "institution", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "longitude_latitude_report", procedureName = "longitude_latitude_report", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "jeNum", type = String.class),
	}),
	
	
	@NamedStoredProcedureQuery(name = "project_report_details_view", procedureName = "project_report_details_view", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class)
	}),
	
	
	@NamedStoredProcedureQuery(name = "delete_project", procedureName = "delete_project", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "projectId", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "reject_project_details", procedureName = "reject_project_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "UserMobile", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "change_je_password", procedureName = "change_je_password", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
	}),
	
	
	@NamedStoredProcedureQuery(name = "get_oldpassword_details_admin", procedureName = "get_oldpassword_details_admin", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "oldpassword", type = String.class),
	}),
	@NamedStoredProcedureQuery(name = "get_oldpassword_details_state", procedureName = "get_oldpassword_details_state", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "oldpassword", type = String.class),
	}),
	
	
	@NamedStoredProcedureQuery(name = "update_admin_password", procedureName = "update_admin_password", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_adminid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "update_state_admin_password", procedureName = "update_state_admin_password", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_adminid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
	}),
	
	
	@NamedStoredProcedureQuery(name = "reset_user_password", procedureName = "reset_user_password", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobno", type = String.class),
	}),
	
	
	
	@NamedStoredProcedureQuery(name = "district_user_view", procedureName = "district_user_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtidsss", type = String.class),
		}),
	
	
	@NamedStoredProcedureQuery(name = "edit_dist_user_je", procedureName = "edit_dist_user_je", parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_distid", type = String.class),
	}),
	
	
	
	@NamedStoredProcedureQuery(name = "delete_user", procedureName = "delete_user", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobno", type = String.class),
	}),
	
	
	@NamedStoredProcedureQuery(name = "get_blockwise_je_name", procedureName = "get_blockwise_je_name", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "admin_project_updation_report", procedureName = "admin_project_updation_report", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "district_project_updation_report", procedureName = "district_project_updation_report", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
	}),
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//#########################################################  PLP #############################################################################
	
	@NamedStoredProcedureQuery(name = "get_cho_details_view", procedureName = "get_cho_details_view", parameters = { }),
	
	
	@NamedStoredProcedureQuery(name = "get_plp_calculation_data", procedureName = "get_plp_calculation_data", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_plpId", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "get_plp_calculation_data_mo", procedureName = "get_plp_calculation_data_mo", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_plpId", type = String.class) }),
	
	
	
	@NamedStoredProcedureQuery(name = "edit_plp_details", procedureName = "edit_plp_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_plpId", type = String.class) }),

	

	@NamedStoredProcedureQuery(name = "get_cho_report", procedureName = "get_cho_report", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			}),
	

	@NamedStoredProcedureQuery(name = "get_cho_days_report", procedureName = "get_cho_days_report", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			}),
	
	@NamedStoredProcedureQuery(name = "cho_days_district_list", procedureName = "cho_days_district_list", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "get_block_list_onchange", procedureName = "get_block_list_onchange", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class) }),
	
	@NamedStoredProcedureQuery(name = "get_monthoryear_details", procedureName = "get_monthoryear_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "month", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "view_mo_verify_plp", procedureName = "view_mo_verify_plp", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "update_mo_remark", procedureName = "update_mo_remark", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_plpId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_moRemark", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "get_mo_verify_plp_pdf", procedureName = "get_mo_verify_plp_pdf", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class),
	}),
	
	@NamedStoredProcedureQuery(name = "cho_district_list_mo", procedureName = "cho_district_list_mo", parameters = { }),
	

	
	@NamedStoredProcedureQuery(name = "get_cho_report_mo", procedureName = "get_cho_report_mo", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			}),
	
	@NamedStoredProcedureQuery(name = "cho_days_district_list_mo", procedureName = "cho_days_district_list_mo", parameters = { }),
	
	@NamedStoredProcedureQuery(name = "get_block_list_onchange_mo", procedureName = "get_block_list_onchange_mo", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = Integer.class) }),
	
	@NamedStoredProcedureQuery(name = "get_cho_days_report_mo", procedureName = "get_cho_days_report_mo", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "district", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "block", type = String.class),
			}),
	
	//edit plpdetils
			@NamedStoredProcedureQuery(name = "delete_lab_conditiontest_details", procedureName = "delete_lab_conditiontest_details", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class) }),
	
	// payment status update
	@NamedStoredProcedureQuery(name = "save_payment_status_homeservice", procedureName = "save_payment_status_homeservice", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "razor_ordrid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "razor_payid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),
	//CureEazy post_favorite_doctor_api
		@NamedStoredProcedureQuery(name = "check_userid_exist", procedureName = "check_userid_exist", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),}),
	//CureEazy post_favorite_doctor_api
	@NamedStoredProcedureQuery(name = "post_community_request_api", procedureName = "post_community_request_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userids", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "unitId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bldGrpId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "valuess", type = String.class),}),
	//get blood unit dropdown

		@NamedStoredProcedureQuery(name = "get_bloodunit_dropdown_list", procedureName = "get_bloodunit_dropdown_list", parameters = {}),
		
	//get community dropdown

	@NamedStoredProcedureQuery(name = "get_community_dropdown_list", procedureName = "get_community_dropdown_list", parameters = {}),
	//10-11-2022
	@NamedStoredProcedureQuery(name = "post_create_community", procedureName = "post_create_community", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "communityName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "communityDesc", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "get_doctor_time", procedureName = "get_doctor_time", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class)
	}),
	//CureEazy get test details
			@NamedStoredProcedureQuery(name = "get_condition_test", procedureName = "get_condition_test", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "testId", type = String.class)}),
		/*
		 * @NamedStoredProcedureQuery(name = "labTestPackage_test_edit_list",
		 * procedureName = "labTestPackage_test_edit_list", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name =
		 * "selectedRowsString", type = String.class) }),
		 */
	//CureEazy get test details
		@NamedStoredProcedureQuery(name = "get_packagewisetest_details", procedureName = "get_packagewisetest_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "packageId", type = String.class)}),
	@NamedStoredProcedureQuery(name = "labtest_package_add", procedureName = "labtest_package_add", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testList", type = String.class)
	}),
	//for assign coupon add
		@NamedStoredProcedureQuery(name = "patient_assigned_coupon_add", procedureName = "patient_assigned_coupon_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "couponList", type = String.class) }),
	@NamedStoredProcedureQuery(name = "get_conditiontest_details", procedureName = "get_conditiontest_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testid", type = String.class)
	}),

@NamedStoredProcedureQuery(name = "labtest_package_view_details", procedureName = "labtest_package_view_details", parameters = { }),
	


	@NamedStoredProcedureQuery(name = "labTestPackage_test_edit_list", procedureName = "labTestPackage_test_edit_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "selectedRowsString", type = String.class) }),
	//CureEazy post_doctor_data_api
	@NamedStoredProcedureQuery(name = "post_homeservice_data_api", procedureName = "post_homeservice_data_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "feedback", type = String.class),
		}),
	//get rating doctor api
	@NamedStoredProcedureQuery(name = "get_rating_details_doctor_api", procedureName = "get_rating_details_doctor_api", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class), }),
	//Cureeazy Nurse Details
	
	@NamedStoredProcedureQuery(name = "get_payment_homeservice_view", procedureName = "get_payment_homeservice_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),
		}),
	
	@NamedStoredProcedureQuery(name = "post_homeservice_amount", procedureName = "post_homeservice_amount", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "paymentlist", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "dates", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "amounts", type = String.class),
		}),
	@NamedStoredProcedureQuery(name = "get_case_study_report", procedureName = "get_case_study_report", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),}),
	//Cureeazy Nurse Details
	
	@NamedStoredProcedureQuery(name = "get_order_details", procedureName = "get_order_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),
		}),
	//patient details edit 
	
			@NamedStoredProcedureQuery(name = "get_patient_details_pdf", procedureName = "get_patient_details_pdf", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class), }),	
			
	//medical problem
			@NamedStoredProcedureQuery(name = "get_patient_medical_problem", procedureName = "get_patient_medical_problem", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class), }),	
			
			@NamedStoredProcedureQuery(name = "get_patient_current_medication", procedureName = "get_patient_current_medication", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class), }),	
			
			
			@NamedStoredProcedureQuery(name = "get_patient_family_details", procedureName = "get_patient_family_details", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class), }),
	//get rating api
	@NamedStoredProcedureQuery(name = "get_rating_details_api", procedureName = "get_rating_details_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class), }),
	@NamedStoredProcedureQuery(name = "get_patient_homeservice_documenattion", procedureName = "get_patient_homeservice_documenattion", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "time", type = String.class),}),
	@NamedStoredProcedureQuery(name = "get_patient_nursing_aide_details", procedureName = "get_patient_nursing_aide_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),}),
	@NamedStoredProcedureQuery(name = "get_patient_authorization_details", procedureName = "get_patient_authorization_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),}),
	@NamedStoredProcedureQuery(name = "get_patient_confirmation_details", procedureName = "get_patient_confirmation_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),}),
	@NamedStoredProcedureQuery(name = "lab_Payment_report", procedureName = "lab_Payment_report", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),}),
	@NamedStoredProcedureQuery(name = "lab_Payment_all_Details", procedureName = "lab_Payment_all_Details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),}),
	@NamedStoredProcedureQuery(name = "doctor_rating", procedureName = "doctor_rating", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "likeType", type = String.class),
			
		}),
	@NamedStoredProcedureQuery(name = "payment_to_cureez", procedureName = "payment_to_cureez", parameters = { }),

	
	@NamedStoredProcedureQuery(name = "payment_to_cureez_view_data", procedureName = "payment_to_cureez_view_data", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class) }),
	@NamedStoredProcedureQuery(name = "update_labpayment_tocureez", procedureName = "update_labpayment_tocureez", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testPrice", type = String.class) }),
	//CureEazy lab Dahboard Details
		@NamedStoredProcedureQuery(name = "reject_rating", procedureName = "reject_rating", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointNo", type = String.class)}),
		
	@NamedStoredProcedureQuery(name = "nurse_aide_details", procedureName = "nurse_aide_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "contactNumber", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "foodHabit", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "familyMemberNo", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "occupation", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "typeOfResidence", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "policeStation", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "hospital", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "busStop", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fluentCommunication", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "aideRest", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "foodArrangement", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "workHygienic", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toiletsFacilities", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "nursingAideProvided", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "nursingAideHostility", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "drugAbuse", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "femaleEmployeeThreat", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "stayAvailable", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "longDurationWork", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "riskWise", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceOrderId", type = String.class),
		}),
	//CureEazy lab Dahboard Details
	@NamedStoredProcedureQuery(name = "get_homeservice_documentation_details", procedureName = "get_homeservice_documentation_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceOrderId", type = String.class)}),
	//doctor orders
			@NamedStoredProcedureQuery(name = "doctor_orders", procedureName = "doctor_orders", parameters = { }),
			@NamedStoredProcedureQuery(name = "doctor_payment_report_all_details", procedureName = "doctor_payment_report_all_details", parameters = { 

					@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class)	
					
			}),
			
			
			@NamedStoredProcedureQuery(name = "doctor_payment_report", procedureName = "doctor_payment_report", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),}),
			//for fromdate todate
			@NamedStoredProcedureQuery(name = "doctor_orders_report", procedureName = "doctor_orders_report", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class),}),
	@NamedStoredProcedureQuery(name = "top_doctor_add", procedureName = "top_doctor_add", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctotIdd", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_topddoctors", type = String.class),

	}),
	@NamedStoredProcedureQuery(name = "patient_documentation", procedureName = "patient_documentation", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceOrderId", type = String.class),
		}),
	
	//CureEazy post payment details 
	@NamedStoredProcedureQuery(name = "post_payment_details_homeservice", procedureName = "post_payment_details_homeservice", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "paymentid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_for", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "total_amount", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bookingstatus", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "created_on", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_mode", type = String.class)}),
	//Nationality Dropdown
	@NamedStoredProcedureQuery(name = "get_nationality", procedureName = "get_nationality", parameters = {}),
	//Blood Group
	@NamedStoredProcedureQuery(name = "get_bldGroup", procedureName = "get_bldGroup", parameters = {}),
	//Cureeazy Nurse Details
	
			@NamedStoredProcedureQuery(name = "get_nurse_details", procedureName = "get_nurse_details", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "hours", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),
				}),
			@NamedStoredProcedureQuery(name = "post_update_documentation", procedureName = "post_update_documentation", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceOrderId", type = String.class),
				}),
			@NamedStoredProcedureQuery(name = "admin_Location_List", procedureName = "admin_Location_List", parameters = { }),
			
			@NamedStoredProcedureQuery(name = "admin_Country_List", procedureName = "admin_Country_List", parameters = { }),
			
			@NamedStoredProcedureQuery(name = "get_state_list", procedureName = "get_state_list", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = Integer.class) }),
			
			
			@NamedStoredProcedureQuery(name = "admin_city_list", procedureName = "admin_city_list", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "dist", type = Integer.class) }),
			
			
	//Cureeazy user profile update
	
			@NamedStoredProcedureQuery(name = "post_patient_documentation", procedureName = "post_patient_documentation", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientName", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "email", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "bldGrp", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "height", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "weight", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "normalHealth", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "ongoingHealth", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "allergy", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "others", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "hereditaryProblem", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
					//@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientSign", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "consentPatientName", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "consentAge", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "consentAddress", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "treatmentBy", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "attendantSign", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "consentDate", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientRelationship", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "confirmationPatientName", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "serviceType", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "serviceHours", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "price", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "regdFee", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "gst", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "confirmDate", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "location", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "confirmPatientSign", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "medicalProblemList", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "currentMedicalCondition", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "familyDetails", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceorderid", type = String.class),
				}),
	//CureEazy get report
		@NamedStoredProcedureQuery(name = "get_patientReport", procedureName = "get_patientReport", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class)}),
	
	//Cureeazy user profile update
	
		@NamedStoredProcedureQuery(name = "post_homeservice_documentation", procedureName = "post_homeservice_documentation", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurseName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "coOrdinatorName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dateOfVisit", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "phNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nationality", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mainComplaint", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "currentMedication", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "drugAllergy", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "recentAdmsn", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "treatmentProcedure", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bp", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "tempPulse", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "respRate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "generalBuild", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "localExam", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "diagnosis", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "additinalComment", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "treatment", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "givenPlan", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "durationCare", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "allShift", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "durationShift", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "otherDetails", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientCare", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "informedSme", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "commentSme", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurse12", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurse24", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurseRegd", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurseGst", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pca12", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pca24", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pcaRegdFee", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pcaGst", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "feedback", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeServiceOrderId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "followUpDate", type = String.class),}),
		 
		
		@NamedStoredProcedureQuery(name = "update_homeservice_documentation", procedureName = "update_homeservice_documentation", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurseName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "coOrdinatorName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dateOfVisit", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "phNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nationality", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mainComplaint", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "currentMedication", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "drugAllergy", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "recentAdmsn", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "treatmentProcedure", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bp", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "tempPulse", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "respRate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "generalBuild", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "localExam", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "diagnosis", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "additinalComment", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "treatment", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "givenPlan", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "durationCare", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "allShift", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "durationShift", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "otherDetails", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientCare", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "informedSme", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "commentSme", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurse12", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurse24", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurseRegd", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nurseGst", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pca12", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pca24", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pcaRegdFee", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pcaGst", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "feedback", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeServiceOrderId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "followUpDate", type = String.class),
			}),
	//CureEazy get Off day
		@NamedStoredProcedureQuery(name = "get_offday", procedureName = "get_offday", parameters = {}),
	//CureEazy get search wise doctor details
	@NamedStoredProcedureQuery(name = "get_search_wise_patient_list", procedureName = "get_search_wise_patient_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchdata", type = String.class)}),
	//Cureeazy Pincode Dropdown
	@NamedStoredProcedureQuery(name = "get_check_status", procedureName = "get_check_status", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointId", type = String.class),
	}),
//	@NamedStoredProcedureQuery(name = "get_doctor_token_api", procedureName = "get_doctor_token_api", parameters = {
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "currentDate", type = String.class)
//	}),
	@NamedStoredProcedureQuery(name = "get_timeslot", procedureName = "get_timeslot", parameters = {}),
	//CureEazy post payment details 
	@NamedStoredProcedureQuery(name = "post_payment_details_web", procedureName = "post_payment_details_web", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "paymentid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_for", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "total_amount", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bookingstatus", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_mode", type = String.class)}),
	@NamedStoredProcedureQuery(name = "patient_details", procedureName = "patient_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)}),
	@NamedStoredProcedureQuery(name = "coupon_assign", procedureName = "coupon_assign", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "couponlist", type = String.class)}),
	@NamedStoredProcedureQuery(name = "edit_coupon", procedureName = "edit_coupon", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_coupon", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "admin_assign_coupon_view", procedureName = "admin_assign_coupon_view", parameters = { }),

	@NamedStoredProcedureQuery(name = "doctor_rating_list", procedureName = "doctor_rating_list", parameters = { }),

	//hwc details add
			@NamedStoredProcedureQuery(name = "hwc_details_add", procedureName = "hwc_details_add", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_choId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_choName", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_contactNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwfNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwmNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ashaNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_targetedPopulation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_thirtyAbovePopulation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "hwcDetailsList", type = String.class),
				}),
			
			
			
			//hwc all details
			@NamedStoredProcedureQuery(name = "hwc_all_details_add", procedureName = "hwc_all_details_add", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwType", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwName", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwPhone", type = String.class),
				}),
			//view for hwc details
			@NamedStoredProcedureQuery(name = "hwc_details_view", procedureName = "hwc_details_view", parameters = {
			}),
			
			//enter plp
			@NamedStoredProcedureQuery(name = "enter_plp_details_view", procedureName = "enter_plp_details_view", parameters = {
			}),
			
			
			//save plp details
			@NamedStoredProcedureQuery(name = "plp_details_add", procedureName = "plp_details_add", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_plpId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_choId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_choName", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_contactNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwfNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwmNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ashaNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_targetedPopulation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_thirtyAbovePopulation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectmonth", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_noofdayopd", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numernoofpregnant", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denonoofpregnant", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerhighriskpregnant", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denohighriskpregnant", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerpmsma", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denopmsma", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numervhsnd", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denovhsnd", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numernewborn", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denonewborn", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerprochild", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denoprochild", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerantibiotic", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denoantibiotic", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalopdcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numertbcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denotbcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numersuspectedtbcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denosuspectedtbcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numertbpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denotbpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numertbdrugs", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denotbdrugs", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numercbac", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denocbac", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerhypertension", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denohypertension", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerhtnpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denohtnpatients", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerdiabetes", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denodiabetes", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerdmpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denodmpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_teleconsultation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_hwcsession", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerjasmeet", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denojasmeet", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numermalariaexam", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denomalariaexam", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numermalariacase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denomalariacase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerifasyrup", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denoifasyrup", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numervhsnc", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denovhsnc", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_diagnostic", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_servicepackage", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectkayakalp", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalmpwmamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalmpwfamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ashaamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalchoamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalmpwamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalashaamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalgrossamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHwcPortal", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectPregnantRedg", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHighRiskPregnancy", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectPMSMAClinic", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectVHSND", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHighRiskNewborn", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectChildUptoTwoYears", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectU5Pnemonia", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectNoOfFootfall", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectTBScreening", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selctDirectBenifitTransfer", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHomeVisitToPatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectProvingDrugs", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectCBACForm", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectScreenForHypertension", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHTNPatientTreatment", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectScreeningForDiabetes", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectDmPatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectTeleconsolation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectWellnessSession", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectMonthlyJASMeeting", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectSuspectedMalaria", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectMalariaCase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectPercentageOfIFASyrup", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectVillageMeetings", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectDiagnosticsServices", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectAdvancedFunctionality", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectKayakalpAward", type = String.class),
				}),
			
			//modify plp
			@NamedStoredProcedureQuery(name = "plp_details_modify", procedureName = "plp_details_modify", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_plpId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_choName", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_contactNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwfNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwmNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ashaNo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_targetedPopulation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_thirtyAbovePopulation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectmonth", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_noofdayopd", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numernoofpregnant", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denonoofpregnant", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerhighriskpregnant", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denohighriskpregnant", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerpmsma", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denopmsma", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numervhsnd", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denovhsnd", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numernewborn", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denonewborn", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerprochild", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denoprochild", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerantibiotic", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denoantibiotic", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalopdcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numertbcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denotbcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numersuspectedtbcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denosuspectedtbcase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numertbpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denotbpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numertbdrugs", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denotbdrugs", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numercbac", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denocbac", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerhypertension", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denohypertension", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerhtnpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denohtnpatients", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerdiabetes", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denodiabetes", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerdmpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denodmpatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_teleconsultation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_hwcsession", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerjasmeet", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denojasmeet", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numermalariaexam", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denomalariaexam", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numermalariacase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denomalariacase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numerifasyrup", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denoifasyrup", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_numervhsnc", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_denovhsnc", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_diagnostic", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_servicepackage", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectkayakalp", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mpwamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalmpwmamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalmpwfamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ashaamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalchoamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalmpwamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalashaamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalgrossamount", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHwcPortal", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectPregnantRedg", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHighRiskPregnancy", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectPMSMAClinic", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectVHSND", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHighRiskNewborn", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectChildUptoTwoYears", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectU5Pnemonia", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectNoOfFootfall", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectTBScreening", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selctDirectBenifitTransfer", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHomeVisitToPatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectProvingDrugs", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectCBACForm", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectScreenForHypertension", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectHTNPatientTreatment", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectScreeningForDiabetes", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectDmPatient", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectTeleconsolation", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectWellnessSession", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectMonthlyJASMeeting", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectSuspectedMalaria", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectMalariaCase", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectPercentageOfIFASyrup", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectVillageMeetings", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectDiagnosticsServices", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectAdvancedFunctionality", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_selectKayakalpAward", type = String.class),
				}),
			
			//cureeasy coupon view
			@NamedStoredProcedureQuery(name = "admin_coupon_view", procedureName = "admin_coupon_view", parameters = { }),
			
			//cureasyy coupon edit
			@NamedStoredProcedureQuery(name = "edit_admin_Coupon", procedureName = "edit_admin_Coupon", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_couponId", type = String.class) }),
			
			//cureeasy coupon modify
			@NamedStoredProcedureQuery(name = "coupon_modify", procedureName = "coupon_modify", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_couponId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cName", type = String.class),
				//	@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dPrice", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fDate", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tDate", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_percentage", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_absoluteValue", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_types", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = String.class),
					
			}),
			
			//cureeasyy coupon delete
			@NamedStoredProcedureQuery(name = "delete_admin_coupon", procedureName = "delete_admin_coupon", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_couponId", type = String.class) }),
		
		
		
	@NamedStoredProcedureQuery(name = "post_doctor_notification", procedureName = "post_doctor_notification", parameters = {

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "responsecode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataList", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
		}),
	@NamedStoredProcedureQuery(name = "post_lab_user_notification", procedureName = "post_lab_user_notification", parameters = {

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "responsecode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataList", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
		}),
	//Cureeazy  doctor appointment history
	@NamedStoredProcedureQuery(name = "get_user_token_details", procedureName = "get_user_token_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
		}),
	//Cureeazy  doctor appointment history
	@NamedStoredProcedureQuery(name = "get_doctor_token", procedureName = "get_doctor_token", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
		}),
	//Cureeazy user profile update
	
	@NamedStoredProcedureQuery(name = "update_doctor_profile", procedureName = "update_doctor_profile", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getPhone", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getGender", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getEducation", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getLanguage", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getClinicName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getAddress", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getCity", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getAudioFee", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getVideoFee", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getPhysicalFee", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getAccountName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getIfscCode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getAccountNumber", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getOpeningTime", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getClosingTime", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getBreakFrom", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getBreakTime", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getSlotBooking", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getOffDay", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "getDoctorId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "packageList", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "first", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "last", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorSign", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lastTimeslot", type = String.class),
		}),	
	//Cureeazy  doctor appointment history
	@NamedStoredProcedureQuery(name = "doctor_appointment_complete_details", procedureName = "doctor_appointment_complete_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointId", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
		}),
	//Cureeazy  doctor appointment history
	@NamedStoredProcedureQuery(name = "doctor_appointment_complete_rejected", procedureName = "doctor_appointment_complete_rejected", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointId", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
		}),
	 //Cureeazy lab status update
	
	@NamedStoredProcedureQuery(name = "post_user_documentation_status", procedureName = "post_user_documentation_status", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
		}),
	//CureEazy get profile doctor
	@NamedStoredProcedureQuery(name = "get_doctor_timeslot_list_doctor", procedureName = "get_doctor_timeslot_list_doctor", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "slotDate", type = String.class)}),
	@NamedStoredProcedureQuery(name = "get_home_service_details_List", procedureName = "get_home_service_details_List", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class)}),
	 //Cureeazy lab status update
	
	@NamedStoredProcedureQuery(name = "post_update_homeservice_status", procedureName = "post_update_homeservice_status", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "homeserviceId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "remark", type = String.class),
		//	@StoredProcedureParameter(mode = ParameterMode.IN, name = "rejected", type = String.class),
		}),
	//CureEazy lab Dahboard Details
	@NamedStoredProcedureQuery(name = "get_homeservice_dashboard_details", procedureName = "get_homeservice_dashboard_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "serviceId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class)}),
	 //Cureeazy lab status
	 @NamedStoredProcedureQuery(name = "get_homeservice_status", procedureName = "get_homeservice_status", parameters = {}),
	//CureEazy get_lab_dashboard_count
		@NamedStoredProcedureQuery(name = "get_homeservice_count", procedureName = "get_homeservice_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "serviceId", type = String.class)}),
	 //Doctor Accept Notification
	
		@NamedStoredProcedureQuery(name = "user_appoint_notification", procedureName = "user_appoint_notification", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "responsecode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataList", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
			}),
	 //CureEazy get_lab_dashboard_count
	@NamedStoredProcedureQuery(name = "get_addtocart_count", procedureName = "get_addtocart_count", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)}),
	//Cureeazy Location Dropdown
		@NamedStoredProcedureQuery(name = "get_showcode", procedureName = "get_showcode", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "showcode", type = String.class),
		}),
	 //Doctor Accept Notification
	
	@NamedStoredProcedureQuery(name = "doctor_accept_notification", procedureName = "doctor_accept_notification", parameters = {

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "responsecode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataList", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
		}),
	 //CureEazy Notification Message
	@NamedStoredProcedureQuery(name = "get_notification_remove", procedureName = "get_notification_remove", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class)}),
	 //CureEazy Notification Message
	@NamedStoredProcedureQuery(name = "get_notification_seen", procedureName = "get_notification_seen", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)}),
	@NamedStoredProcedureQuery(name = "patient_consultation_complete_notification", procedureName = "patient_consultation_complete_notification", parameters = {

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "responsecode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataList", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
		}),
	 //CureEazy Notification Message
	@NamedStoredProcedureQuery(name = "get_notification_message", procedureName = "get_notification_message", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)}),
	 //CureEazy get_lab_dashboard_count
		@NamedStoredProcedureQuery(name = "get_notification_count", procedureName = "get_notification_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)}),
	 //Cureeazy Lab Book Notification
	
	@NamedStoredProcedureQuery(name = "lab_book_notification", procedureName = "lab_book_notification", parameters = {

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "responsecode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataList", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
		}),
	 //Cureeazy Lab Book Notification
	
	@NamedStoredProcedureQuery(name = "homeservice_accept_notification", procedureName = "homeservice_accept_notification", parameters = {

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "responsecode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataList", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
		}),
	 //Cureeazy Lab Confirm notification
	
	@NamedStoredProcedureQuery(name = "post_lab_complete_notification", procedureName = "post_lab_complete_notification", parameters = {

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "responsecode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "labId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataList", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
		}),
	//Cureeazy  Lab Search
	@NamedStoredProcedureQuery(name = "get_search_lab_details", procedureName = "get_search_lab_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchdata", type = String.class),}),
	//Cureeazy  wallet description
		@NamedStoredProcedureQuery(name = "get_wallet_description", procedureName = "get_wallet_description", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),}),
	//Cureeazy  wallet price
	@NamedStoredProcedureQuery(name = "get_wallet_price", procedureName = "get_wallet_price", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),}),
	//CureEazy post_doctor_data_api
	@NamedStoredProcedureQuery(name = "user_cancel_appointment_reason", procedureName = "user_cancel_appointment_reason", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "apptId", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "reason", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
		}),
	//Coupon Code
	@NamedStoredProcedureQuery(name = "get_coupon_code", procedureName = "get_coupon_code", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "couponCode", type = String.class)
	}),
	//getToken
	@NamedStoredProcedureQuery(name = "get_user_profile_tokenId", procedureName = "get_user_profile_tokenId", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)
	}),
	//Coupon List
	@NamedStoredProcedureQuery(name = "get_coupon_list", procedureName = "get_coupon_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)
	}),
	//CureEazy post_favorite_doctor_api
			@NamedStoredProcedureQuery(name = "post_medicine", procedureName = "post_medicine", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchdata", type = String.class)}),
	//CureEazy post_favorite_doctor_api
		@NamedStoredProcedureQuery(name = "get_show_code", procedureName = "get_show_code", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "apptId", type = String.class)}),
	//CureEazy post_favorite_doctor_api
	@NamedStoredProcedureQuery(name = "post_join_meeting", procedureName = "post_join_meeting", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "key", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "code", type = String.class)}),
	//Cureeazy  view lab test report
			@NamedStoredProcedureQuery(name = "get_view_patientReport_complete", procedureName = "get_view_patientReport_complete", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),}),
	//Cureeazy Location Dropdown
	@NamedStoredProcedureQuery(name = "get_location_dropdown", procedureName = "get_location_dropdown", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "locations", type = String.class),
	}),
	//Cureeazy Pincode Dropdown
			@NamedStoredProcedureQuery(name = "get_pincode_dropdown", procedureName = "get_pincode_dropdown", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "pincode", type = String.class),
			}),
			//Cureeazy Location Dropdown
			@NamedStoredProcedureQuery(name = "get_location_dropdown_lab", procedureName = "get_location_dropdown_lab", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "locations", type = String.class),
			}),
			//Cureeazy Pincode Dropdown
					@NamedStoredProcedureQuery(name = "get_pincode_dropdown_lab", procedureName = "get_pincode_dropdown_lab", parameters = {
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "pincode", type = String.class),
					}),
	//Cureeazy Gender Dropdown
		@NamedStoredProcedureQuery(name = "get_gender_dropdown", procedureName = "get_gender_dropdown", parameters = {}),
	//Cureeazy Time slot mstr
	@NamedStoredProcedureQuery(name = "get_lab_timeslot_mstr", procedureName = "get_lab_timeslot_mstr", parameters = {}),
	//Cureeazy Lab Search Details
			@NamedStoredProcedureQuery(name = "get_lab_search_details", procedureName = "get_lab_search_details", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchdata", type = String.class),}),
	//CureEazy post payment details 
	@NamedStoredProcedureQuery(name = "post_payment_details_lab", procedureName = "post_payment_details_labs", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "paymentid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_for", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "total_amount", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bookingstatus", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "created_on", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_mode", type = String.class)}),
	//Cureeazy Medicine Prescription
		@NamedStoredProcedureQuery(name = "get_patient_medicine_prescription", procedureName = "get_patient_medicine_prescriptions", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointId", type = String.class),}),
	//Cureeazy  Test Prescription
	@NamedStoredProcedureQuery(name = "get_patient_test_prescription", procedureName = "get_patient_test_prescriptions", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointId", type = String.class),}),
	//Cureeazy  view lab test report
			@NamedStoredProcedureQuery(name = "get_patient_details_prescription", procedureName = "get_patient_details_prescriptions", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointId", type = String.class),}),
	//Cureeazy  view lab test report
		@NamedStoredProcedureQuery(name = "get_view_patientReport", procedureName = "get_view_patientReports", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),}),
	@NamedStoredProcedureQuery(name = "post_prescription_manual", procedureName = "post_prescription_manual", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "drid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doc", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "consultType", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "showCode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointmentId", type = String.class)}),
	//Cureeazy doctor test drop down
	 @NamedStoredProcedureQuery(name = "get_test_name", procedureName = "get_test_name", parameters = {}),
	//Cureeazy  view lab test report
	@NamedStoredProcedureQuery(name = "get_view_labtest_report", procedureName = "get_view_labtest_report", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "labId", type = String.class),
		}),
	 //Cureeazy lab status update
	
	@NamedStoredProcedureQuery(name = "post_update_lab_status", procedureName = "post_update_lab_statusss", parameters = {

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "labId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "remark", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doc", type = String.class),
		}),
	//CureEazy lab Dahboard Details
			@NamedStoredProcedureQuery(name = "get_lab_dashboard_details", procedureName = "get_lab_dashboard_details", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "labId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromDate", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "toDate", type = String.class)}),
	 //Cureeazy lab status
	 @NamedStoredProcedureQuery(name = "get_lab_status", procedureName = "get_lab_status", parameters = {}),
	//CureEazy get doctor view appointment details 
		@NamedStoredProcedureQuery(name = "doctor_appoint_history_details", procedureName = "doctor_appoint_history_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),}),
	 //CureEazy get_lab_dashboard_count
	@NamedStoredProcedureQuery(name = "get_lab_dashboard_count", procedureName = "get_lab_dashboard_count", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "labId", type = String.class)}),

	// Search Patient Name and Number Wise
	@NamedStoredProcedureQuery(name = "get_search_patientname_withnumber", procedureName = "get_search_patientname_withnumber", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchdata", type = String.class),

		}),
	// Start Consultation For Join Url
	@NamedStoredProcedureQuery(name = "get_joinUrl_startConsultation", procedureName = "get_joinUrl_startConsultation", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class),

		}),
	@NamedStoredProcedureQuery(name = "change_password", procedureName = "change_password", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),

		}),
	@NamedStoredProcedureQuery(name = "post_prescription_api", procedureName = "post_prescription_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userids", type = String.class),
		//	@StoredProcedureParameter(mode = ParameterMode.IN, name = "dates", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointmentid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "specialadvice", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "consultType", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "showCode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dobs", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "genders", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "medicinelist", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testlist", type = String.class),

		}),
	//CureEazy get testname autosearch
	@NamedStoredProcedureQuery(name = "get_medicine_autosearch", procedureName = "get_medicine_autosearchs", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchdata", type = String.class)}),
	//get medicine dropdown

	@NamedStoredProcedureQuery(name = "get_medicine_dropdown_list", procedureName = "get_medicine_dropdown_list", parameters = {}),

	@NamedStoredProcedureQuery(name = "doctor_feedback_api", procedureName = "doctor_feedback_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "feedback", type = String.class),
			
		}),
	
	//homecare_service_modify
			@NamedStoredProcedureQuery(name = "homecare_service_details_modify", procedureName = "homecare_service_details_modify", parameters = {
					 @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_serviceId", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_frequentlybooked", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_featured", type = String.class),

			}),
			
			
	//CureEazy post payment details 
	@NamedStoredProcedureQuery(name = "post_payment_details", procedureName = "post_payment_detailsst_user", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "paymentid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_for", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "total_amount", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bookingstatus", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "created_on", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_mode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "areaString", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "startUrl", type = String.class)}),
	//Cureeazy  doctor appointment history
	@NamedStoredProcedureQuery(name = "doctor_appointment_history", procedureName = "doctor_appointment_history", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
		}),
	//Cureeazy doctor profile update
	
	@NamedStoredProcedureQuery(name = "post_update_doctor_profile", procedureName = "post_update_doctor_profile", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorProfile", type = String.class),
		}),
	
	//CureEazy post_doctor_data_api
	@NamedStoredProcedureQuery(name = "doctor_cancel_appointment_reason", procedureName = "doctor_cancel_appointment_reason", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "apptId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "reason", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class),
		}),
	//CureEazy get doctor view appointment details 
	@NamedStoredProcedureQuery(name = "patient_view_appointment_details", procedureName = "patient_view_appointment_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderId", type = String.class),}),
	//CureEazy post_doctor_data_api
	@NamedStoredProcedureQuery(name = "update_doctor_appoint_status", procedureName = "update_doctor_appoint_statuss", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "apptId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class),
		}),
	//CureEazy get doctor dashboard appointment details 
	@NamedStoredProcedureQuery(name = "doctor_dashboard_appointment_details", procedureName = "doctor_dashboard_appointment_detailss", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
		}),
	//CureEazy get most care Home packages
	@NamedStoredProcedureQuery(name = "get_most_carehomepackages", procedureName = "get_most_carehomepackages",parameters = {}),
	
	//CureEazy get most care Home services
	@NamedStoredProcedureQuery(name = "get_most_carehomeservices", procedureName = "get_most_carehomeservices",parameters = {}),
	//Cureeazy doctor profile img view
	
	@NamedStoredProcedureQuery(name = "doctor_profile_img_view", procedureName = "doctor_profile_img_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
		}),																																								
	
	//Cureeazy doctor profile update
	
	@NamedStoredProcedureQuery(name = "doctor_profile_details", procedureName = "doctor_profile_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
		}),																																								
	
	// Document Type Dropdown

	@NamedStoredProcedureQuery(name = "get_selfdocument_dropdown", procedureName = "get_selfdocument_dropdown", parameters = {}),
	// Language dropdown

	@NamedStoredProcedureQuery(name = "get_language_dropdown_list", procedureName = "get_language_dropdown_list", parameters = {}),

	//Cureeazy user profile update
	
	@NamedStoredProcedureQuery(name = "post_update_user_profile", procedureName = "post_update_user_profile", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userProfile", type = String.class),
		}),																																								
	
	//CureEazy get doctor appointment details 
	@NamedStoredProcedureQuery(name = "doctor_appointment_details", procedureName = "doctor_appointment_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "apptId", type = String.class),}),
	
	@NamedStoredProcedureQuery(name = "update_user_profile", procedureName = "update_user_profile", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fname", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "email", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "language", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "patDOB", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "genders", type = String.class) }),
	//CureEazy get user profile updt
	@NamedStoredProcedureQuery(name = "update_profile_image_api", procedureName = "update_profile_image_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "regid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "profileimage", type = String.class) }),
	//CureEazy get user edit profile
		@NamedStoredProcedureQuery(name = "user_edit_profile", procedureName = "user_edit_profile",parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)}),
	//CureEazy get user self document view
	@NamedStoredProcedureQuery(name = "user_self_document_view", procedureName = "user_self_document_view",parameters = {
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)}),
	
	//CureEazy get Home service auto search
	@NamedStoredProcedureQuery(name = "get_homeservice_search", procedureName = "get_homeservice_search",parameters = {
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class)}),
	//CureEazy post_self_document
	@NamedStoredProcedureQuery(name = "post_self_document", procedureName = "post_self_document", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "docName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctypes", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "additionalNotes", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class)}),
	//CureEazy get favourite test
		@NamedStoredProcedureQuery(name = "get_labtest_favourite_list", procedureName = "get_labtest_favourite_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)}),
	//CureEazy post_favorite_test_api
	@NamedStoredProcedureQuery(name = "post_favorite_test_api", procedureName = "post_favorite_test_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "favstatus", type = String.class)}),
	//CureEazy lab_dashboard_details
	@NamedStoredProcedureQuery(name = "lab_dashboard_details", procedureName = "lab_dashboard_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)}),

		//Cureeazy home service list
		@NamedStoredProcedureQuery(name = "get_frequently_booked_service_list", procedureName = "get_frequently_booked_service_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "get_homecare_service_list", procedureName = "get_homecare_service_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "get_packages_list", procedureName = "get_packages_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "get_featured_service_list", procedureName = "get_featured_service_list", parameters = {}),
		
		//service details lists
		@NamedStoredProcedureQuery(name = "get_service_details_List", procedureName = "get_service_details_List",parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "serviceId", type = String.class)}),
	/* Search test */
	@NamedStoredProcedureQuery(name = "get_searchwise_test_list", procedureName = "get_searchwise_test_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchdata", type = String.class) }),
	/* Delete add to cart */
	@NamedStoredProcedureQuery(name = "delete_addtocart", procedureName = "delete_addtocart", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "cart_ids", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "post_request_to_service", procedureName = "post_request_to_service", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "serviceId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "addressOne", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "addressTwo", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "locality", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pinCode", type = String.class)}),
	
	@NamedStoredProcedureQuery(name = "get_user_appointment_dtls", procedureName = "get_user_appointment_dtlss", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "statuss", type = String.class)}),
	
	@NamedStoredProcedureQuery(name = "get_home_service_order_List", procedureName = "get_home_service_order_List", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)}),
	//CureEazy get add to cart list details
		@NamedStoredProcedureQuery(name = "lab_add_tocart_list", procedureName = "lab_add_tocart_list",parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)}),
		
		//Cureeazy doctor  details
				@NamedStoredProcedureQuery(name = "get_dashboard_doctor_list", procedureName = "get_dashboard_doctor_list", parameters = {}),
				
				
		//CureEazy post_doctor_data_api
				@NamedStoredProcedureQuery(name = "post_doctor_data_api", procedureName = "post_doctor_data_api", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "feedback", type = String.class),
					}),
				//CureEazy get profile user
				@NamedStoredProcedureQuery(name = "get_user_profile", procedureName = "get_user_profile", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)}),
			
		
		//CureEazy get_order_lab_List
				@NamedStoredProcedureQuery(name = "get_order_lab_List", procedureName = "get_order_lab_Lists", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)}),

	//CureEazy get test details
	@NamedStoredProcedureQuery(name = "get_test_details", procedureName = "get_test_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testid", type = String.class),
			//@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class)
			}),
	
	//CureEazy get test details
	@NamedStoredProcedureQuery(name = "get_package_test_details", procedureName = "get_package_test_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "packageid", type = String.class)}),
	//CureEazy get favorite doctor details
	@NamedStoredProcedureQuery(name = "get_favorite_doctor_list", procedureName = "get_favorite_doctor_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)}),
	
	//CureEazy post_favorite_doctor_api
	@NamedStoredProcedureQuery(name = "post_favorite_doctor_api", procedureName = "post_favorite_doctor_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "drid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "favstatus", type = String.class)}),
	
	//CureEazy post_favorite_doctor_api
		@NamedStoredProcedureQuery(name = "post_labtest_payment_apis", procedureName = "post_labtest_payment_apis", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userids", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dates", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "times", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "adderssOne", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "addressTwo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "location", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pincode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "othercharges", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "coupon", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "totalprice", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "valuess", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testType", type = String.class)}),
			
	//CureEazy post_doctor_appointment_api
	@NamedStoredProcedureQuery(name = "post_doctor_appointment_payment_details", procedureName = "post_doctor_appointment_payment_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "conCharge", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "coupon", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "couponAmt", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "othCharge", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "totCharge", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "payid", type = String.class)}),
	
	//CureEazy post_doctor_appointment_api
	@NamedStoredProcedureQuery(name = "post_doctor_appointment_api", procedureName = "post_doctor_appointment_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "drid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "booktype", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doc", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "hissue", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "sid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "sdate", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "stime", type = String.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)}),
	
	//CureEazy get profile doctor
	@NamedStoredProcedureQuery(name = "get_doctor_timeslot_list", procedureName = "get_doctor_timeslot_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "slotDate", type = String.class)}),
	
	//CureEazy get profile doctor
	@NamedStoredProcedureQuery(name = "get_doctor_profile", procedureName = "get_doctor_profile", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type = String.class),
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class)}),
	
	//CureEazy get search wise doctor details
	@NamedStoredProcedureQuery(name = "get_search_wise_doctor_list", procedureName = "get_search_wise_doctor_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchdata", type = String.class)}),
	
	//CureEazy get specialization wise doctor details
	@NamedStoredProcedureQuery(name = "get_specialization_wise_doctor_list", procedureName = "get_specialization_wise_doctor_list", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "specializationId", type = String.class)}),
	
	
//Cureeazy lab details
	@NamedStoredProcedureQuery(name = "lab_frequently_test_list", procedureName = "lab_frequently_test_list", parameters = {}),
	@NamedStoredProcedureQuery(name = "get-condition-based-list", procedureName = "lab_conditionbased_test_list", parameters = {}),
	@NamedStoredProcedureQuery(name = "lab_featured_test_list", procedureName = "lab_featured_test_list", parameters = {}),
	@NamedStoredProcedureQuery(name = "get-test-package-list", procedureName = "get_test_package_list", parameters = {}),
	@NamedStoredProcedureQuery(name = "get_consultedsymptoms_list", procedureName = "get_consultedsymptoms_list", parameters = {}),
	@NamedStoredProcedureQuery(name = "get_commonsymptoms_list", procedureName = "get_commonsymptoms_list", parameters = {}),
	@NamedStoredProcedureQuery(name = "get_specialization_list", procedureName = "get_specialization_list", parameters = {}),

	
	
	//CureEazy post_favorite_doctor_api
	@NamedStoredProcedureQuery(name = "post_labtest_addtocart_api", procedureName = "post_labtest_addtocart_api", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "price", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testName", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dicountPrice", type = String.class)
			}),
	
	
	
	
	/*
	 * View Patient details for billing
	 */
	@NamedStoredProcedureQuery(name = "patient_details_billing", procedureName = "patient_details_billing", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bookingId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bookingDate", type = String.class)  }),
	
	/*
	 * View  billing test list
	 */
	@NamedStoredProcedureQuery(name = "patient_billing_test_details", procedureName = "patient_billing_test_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bookingId", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bookingDate", type = String.class)  }),
		@NamedStoredProcedureQuery(name = "equipment_test_name_lists", procedureName = "equipment_test_name_lists", parameters = {}),

		// vle list

		@NamedStoredProcedureQuery(name = "vleadmin_vle_list", procedureName = "vleadmin_vle_list", parameters = {}),

		// vle equipment assign

		@NamedStoredProcedureQuery(name = "vleadmin_vle_assign_list", procedureName = "vleadmin_vle_assign_list", parameters = {}),

		// vle approve list

		@NamedStoredProcedureQuery(name = "vleadmin_vle_approve_list", procedureName = "vleadmin_vle_approve_list", parameters = {}),

		// vle profile

		@NamedStoredProcedureQuery(name = "vle_view_home_address", procedureName = "vle_view_home_address", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctOrId", type = String.class) }),

		// vle edit profile

		@NamedStoredProcedureQuery(name = "vle_edit_home_address", procedureName = "vle_edit_home_address", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctOrId", type = String.class) }),

		// modify vle profile

		

		// vle equipment add

		@NamedStoredProcedureQuery(name = "vle_equipment_add", procedureName = "vle_equipment_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "value", type = String.class) }),

		// vle scheme add
		@NamedStoredProcedureQuery(name = "vle_to_scheme_add", procedureName = "vle_to_scheme_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "value", type = String.class), }),

		// scheme add details
		@NamedStoredProcedureQuery(name = "scheme_add_details", procedureName = "scheme_add_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "value", type = String.class), }),

		// scheme list
		@NamedStoredProcedureQuery(name = "user_scheme_list", procedureName = "user_scheme_list", parameters = {}),

		// Test name list
		@NamedStoredProcedureQuery(name = "user_test_name_list_scheme", procedureName = "user_test_name_list_scheme", parameters = {}),

		// Scheme to vle list
		@NamedStoredProcedureQuery(name = "vleadmin_scheme_to_vle_list", procedureName = "vleadmin_scheme_to_vle_list", parameters = {}),

		// Scheme ttest
		@NamedStoredProcedureQuery(name = "scheme_test_list_view", procedureName = "scheme_test_list_view", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_add_prof_registration", procedureName = "user_add_prof_registration", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profileImg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_hsId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_exp", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_role", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_speciality", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_jobType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_gender", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pin", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_village", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_block", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_location", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_file", type = String.class) }),

		// vle Approve

		@NamedStoredProcedureQuery(name = "vle_approve_update", procedureName = "vle_approve_update", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "vleId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),

		@NamedStoredProcedureQuery(name = "get_patient_all_lab_request_list_api", procedureName = "get_patient_all_lab_request_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class) }),

		@NamedStoredProcedureQuery(name = "get_patient_all_medicine_request_list_api", procedureName = "get_patient_all_medicine_request_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class) }),

		@NamedStoredProcedureQuery(name = "delete_medicine_reminder_byid", procedureName = "delete_medicine_reminder_byid", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "reminderid", type = String.class) }),

		// treatment tracker add
		@NamedStoredProcedureQuery(name = "post_medicine_treatment_tracker_api", procedureName = "post_medicine_treatment_tracker_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_vlaues", type = String.class) }),

		@NamedStoredProcedureQuery(name = "get_news_media_details_api", procedureName = "get_news_media_details_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "lab_count_box_aggrid_details", procedureName = "lab_count_box_aggrid_details", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "typeId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "reception_count_box_aggrid_details", procedureName = "reception_count_box_aggrid_details", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "typeId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_wrong_credentialdetail_details_id", procedureName = "patient_wrong_credentialdetail_details_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ip", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_browserName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_wrong", type = String.class), }),

		@NamedStoredProcedureQuery(name = "patient_login_details_id", procedureName = "patient_login_details_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ip", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_browserName", type = String.class), }),
		@NamedStoredProcedureQuery(name = "country_dashboard_view_count", procedureName = "country_dashboard_view_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		// Total Pharmacy
		@NamedStoredProcedureQuery(name = "mys_dashboard_medical_emergencies", procedureName = "mys_dashboard_medical_emergencies", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		@NamedStoredProcedureQuery(name = "mys_dashboard_total_accidental_case", procedureName = "mys_dashboard_total_accidental_case", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		// Diagnostic Lab
		@NamedStoredProcedureQuery(name = "mys_dashboard_total_ambulance_called", procedureName = "mys_dashboard_total_ambulance_called", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		@NamedStoredProcedureQuery(name = "mys_dashboard_organ_donated", procedureName = "mys_dashboard_organ_donated", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		@NamedStoredProcedureQuery(name = "mys_dashboard_total_life_saved", procedureName = "mys_dashboard_total_life_saved", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		// Pathology Lab
		@NamedStoredProcedureQuery(name = "mys_dashboard_total_blood_donated", procedureName = "mys_dashboard_total_blood_donated", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		@NamedStoredProcedureQuery(name = "mys_dashboard_total_deliveries", procedureName = "mys_dashboard_total_deliveries", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		@NamedStoredProcedureQuery(name = "mys_dashboard_total_admission", procedureName = "mys_dashboard_total_admission", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		@NamedStoredProcedureQuery(name = "mys_dashboard_avg_waiting_time_division", procedureName = "mys_dashboard_avg_waiting_time_division", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		@NamedStoredProcedureQuery(name = "mys_dashboard_patient_by_division", procedureName = "mys_dashboard_patient_by_division", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		@NamedStoredProcedureQuery(name = "mys_dashboard_patient_by_gender", procedureName = "mys_dashboard_patient_by_gender", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		@NamedStoredProcedureQuery(name = "mys_dashboard_patient_by_hospital", procedureName = "mys_dashboard_patient_by_hospital", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		@NamedStoredProcedureQuery(name = "get_user_some_details_api", procedureName = "get_user_some_details_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "lab_package_add", procedureName = "lab_package_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getTestName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getPackagePrice", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getTestDiscount", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getLabId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "view_medicine_reminder_list_api", procedureName = "view_medicine_reminder_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pdate", type = String.class) }),

		@NamedStoredProcedureQuery(name = "get_pet_list", procedureName = "get_pet_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "post_update_username", procedureName = "post_update_username", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "uhid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class) }),

		@NamedStoredProcedureQuery(name = "username_is_exist", procedureName = "username_is_exist", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "uhid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class) }),

		@NamedStoredProcedureQuery(name = "admin_calender_add", procedureName = "admin_calender_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getCountry", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getState", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getDist", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getCity", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getChangeDay", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getDocName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getFileupload", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getUserId", type = String.class), }),

		@NamedStoredProcedureQuery(name = "update_medicine_reminder_id_api", procedureName = "update_medicine_reminder_id_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "medicineReminderId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "check_aadhar_id_exists", procedureName = "check_aadhar_id_exists", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "aadhaar", type = String.class) }),

		@NamedStoredProcedureQuery(name = "delete_news_media_api", procedureName = "delete_news_media_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class) }),

		@NamedStoredProcedureQuery(name = "dashboard_health_username", procedureName = "dashboard_health_username", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class), }),

		@NamedStoredProcedureQuery(name = "get_medicine_reminder_list_api", procedureName = "get_medicine_reminder_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "before15Min", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "after15Min", type = String.class) }),
		@NamedStoredProcedureQuery(name = "save_nasan_test_report_data", procedureName = "save_nasan_test_report_data", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "screeningDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testResult", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testUnit", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "refRange", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "sampleNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "jsondata", type = String.class) }),

		@NamedStoredProcedureQuery(name = "doctor_profile_modify_main", procedureName = "doctor_profile_modify_main", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctorId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_education", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_proimg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dobid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fee", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_experience", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_profile_modify", procedureName = "doctor_profile_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctorId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_education", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_proimg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dobid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_experience", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_get_lab", procedureName = "patient_get_lab", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_place", type = String.class), }),

		@NamedStoredProcedureQuery(name = "patient_packagelist_view", procedureName = "patient_packagelist_view", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_add_registration_new", procedureName = "user_add_registration_new", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profileImg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientId", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_gender", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_countryName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_stateName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qrCode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "interesList", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "catagoryList", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "abha_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "aadhaarNoAbha", type = String.class) }),

		@NamedStoredProcedureQuery(name = "view_user_interest_catagorylist", procedureName = "view_user_interest_catagorylist", parameters = {}),

		// Add Macfos by manmayee
		@NamedStoredProcedureQuery(name = "macfos_add", procedureName = "macfos_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_schemename", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_schemetype", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fromdate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_todate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_price", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_discountprice", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_couponcode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pin", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_location", type = String.class),

		}),
		// macfos view by manmayee

		@NamedStoredProcedureQuery(name = "macfos_user_organization", procedureName = "macfos_user_organization", parameters = {}),

		// macfos edit by manmayee
		@NamedStoredProcedureQuery(name = "macfos_organization_edit", procedureName = "macfos_organization_edit", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ords", type = String.class) }),

		// modify Macfos by manmayee
		@NamedStoredProcedureQuery(name = "macfos_modify", procedureName = "macfos_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_schemeId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_schemename", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_schemetype", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fromdate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_todate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_price", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_discountprice", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_couponcode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pin", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_location", type = String.class) }),

		@NamedStoredProcedureQuery(name = "post_medicine_reminder_api", procedureName = "post_medicine_reminder_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "medreminderdetails", type = String.class) }),

		@NamedStoredProcedureQuery(name = "post_activity_log_api", procedureName = "post_activity_log_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "activitylogdetails", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "puserid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pdeviceid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pdevicetoken", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pstatus", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "ptype", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pimeino", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_get_lab_list_api", procedureName = "patient_get_lab_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_place", type = String.class) }),

		@NamedStoredProcedureQuery(name = "get_version", procedureName = "get_version", parameters = {}),

		@NamedStoredProcedureQuery(name = "get_patient_details_throughid_api", procedureName = "get_patient_details_throughid_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "post_news_media_data_api", procedureName = "post_news_media_data_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataUploaddetails", type = String.class) }),
		@NamedStoredProcedureQuery(name = "post_news_media_data_web", procedureName = "post_news_media_data_web", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataUploaddetails", type = String.class) }),
		@NamedStoredProcedureQuery(name = "view_patient_order_list", procedureName = "view_patient_order_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "post_user_health_record_api", procedureName = "post_user_health_record_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "recorddate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "recordtime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testresult", type = String.class) }),
		@NamedStoredProcedureQuery(name = "get_patient_medicalupload_details_bylimit", procedureName = "get_patient_medicalupload_details_bylimit", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "view_govt_scheme_content", procedureName = "view_govt_scheme_content", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class) }),
		// allergy modify
		/*
		 * 04-01-2022
		 */
		@NamedStoredProcedureQuery(name = "get_syndicate_organization_api", procedureName = "get_syndicate_organization_api", parameters = {}),
		@NamedStoredProcedureQuery(name = "patient_prescription_testlist_details", procedureName = "patient_prescription_testlist_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dr_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "prescr_date", type = String.class) }),
		@NamedStoredProcedureQuery(name = "post_medicine_reminder_details_api", procedureName = "post_medicine_reminder_details_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "medid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "med_date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "med_time", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_patient_details_alergy_modify", procedureName = "user_patient_details_alergy_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alernm", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alertype", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alerseverty", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alerrect", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alerupdatedby", type = String.class) }),
		@NamedStoredProcedureQuery(name = "lab_test_add", procedureName = "lab_test_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "value", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "labid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_group_list", procedureName = "user_group_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "user_testname_list", procedureName = "user_testname_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "packagelist", type = String.class) }),

		@NamedStoredProcedureQuery(name = "lab_packagelist_view", procedureName = "lab_packagelist_view", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "view_chemist_medicine_list", procedureName = "view_chemist_medicine_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class) }),
		@NamedStoredProcedureQuery(name = "view_medicine_stockdata", procedureName = "view_medicine_stockdata", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "uid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "save_medicine_details", procedureName = "save_medicine_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stripprice", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "disc", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "batchno", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "manfacdt", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "expdt", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "manfacno", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stripqua", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "medstock", type = String.class) }),

		@NamedStoredProcedureQuery(name = "view_chemist_medicinelist_autosearch", procedureName = "view_chemist_medicinelist_autosearch", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchValue", type = String.class) }),

		@NamedStoredProcedureQuery(name = "save_allergies_api_data", procedureName = "save_allergies_api_data", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "allid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "allnameid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "alltypeid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "severity", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "reaction", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "updatedby", type = String.class) }),

		@NamedStoredProcedureQuery(name = "view_govt_scheme_list_api", procedureName = "view_govt_scheme_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type = String.class) }),
		/*
		 * View current doctor Details(Medication)
		 */
		@NamedStoredProcedureQuery(name = "current_doctor_list_details", procedureName = "current_doctor_list_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_medicine_details", procedureName = "patient_medicine_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dr_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "prescr_date", type = String.class) }),
		@NamedStoredProcedureQuery(name = "get_role_api", procedureName = "get_role_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class) }),
		
// cureEazy Api --------------------------------------
		
		
		@NamedStoredProcedureQuery(name = "userSelfRegistrationAPI", procedureName = "user_selfregistration_mobile_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "usertype", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patfname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patlname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patmobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patemail", type = String.class), 
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),}),
		@NamedStoredProcedureQuery(name = "get_login_details_multiple_user", procedureName = "get_login_details_multiple_user", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_username", type = String.class) }),
		
		
		
		@NamedStoredProcedureQuery(name = "patient_email_exist", procedureName = "patient_email_exist", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class) }),
		
		@NamedStoredProcedureQuery(name = "get_banner_details_api", procedureName = "get_banner_details_api", parameters = {}),

		@NamedStoredProcedureQuery(name = "get_banner_doctor_api", procedureName = "get_banner_doctor_api", parameters = {}),
		/*
		 * View prescription
		 */
		@NamedStoredProcedureQuery(name = "patient_prescription_drlist_details", procedureName = "patient_prescription_drlist_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dr_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "prescr_date", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_prescription_medcnlist_details", procedureName = "patient_prescription_medcnlist_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dr_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "prescr_date", type = String.class) }),

		/** 28-12-2021 **/
		@NamedStoredProcedureQuery(name = "post_add_organization_api", procedureName = "post_add_organization_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniqueid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "orgname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "licno", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dist", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pincode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "gst", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "healthprovider", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "docnameone", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "fileone", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "docnametwo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "filetwo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "docnamethree", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "filethree", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "docnamefour", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "filefour", type = String.class) }),

		@NamedStoredProcedureQuery(name = "get_password", procedureName = "get_password", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class) }),

		@NamedStoredProcedureQuery(name = "post_doctor_rating_api", procedureName = "post_doctor_rating_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "drid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "rating", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "reviews", type = String.class) }),
		/* 23-12-2021 */
		@NamedStoredProcedureQuery(name = "view_medicine_details_for_delivery_process", procedureName = "view_medicine_details_for_delivery_process", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "organizationid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "medicineid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointmentid", type = String.class) }),

		/** 21-12-2021 **/
		@NamedStoredProcedureQuery(name = "delete_from_list_api", procedureName = "delete_from_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		/* 20-12-2021 */
		@NamedStoredProcedureQuery(name = "post_family_doctor_api", procedureName = "post_family_doctor_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "speciality", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "apifor", type = String.class) }),
		@NamedStoredProcedureQuery(name = "get_ehealth_user_api", procedureName = "get_ehealth_user_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchval", type = String.class) }),
		@NamedStoredProcedureQuery(name = "post_family_details_api", procedureName = "post_family_details_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "memid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "relation", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "apifor", type = String.class) }),

		/* 16-12-2021 */
		@NamedStoredProcedureQuery(name = "post_other_profileimage_api", procedureName = "post_other_profileimage_api", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "docid", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "primg", type = String.class) }),

		@NamedStoredProcedureQuery(name = "update_doctor_profile_api", procedureName = "update_doctor_profile_api", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "drid", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "ddob", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bloodgrp", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dgender", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "education", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "expr", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "imano", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "panno", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "voteno", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "passportno", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "licence", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "licenceauthority", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "adhaarno", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dcountry", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dstate", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "ddist", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dcity", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "daddress", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pincode", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mob", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "email", type = String.class) }),

		@NamedStoredProcedureQuery(name = "updateUserProfile", procedureName = "update_user_profile_api", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regid", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patdob", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bloodgroup", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "maritialstatus", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "occupation", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "qualification", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "specialization", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pancard", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "passport", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "adharcard", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "votercard", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "licence", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "licenceauth", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "email", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pin", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dist", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "fname", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "lname", type = String.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class) }),

		@NamedStoredProcedureQuery(name = "get_user_emergency_list_api", procedureName = "get_user_emergency_list_api", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "get_user_family_doctor_list_api", procedureName = "get_user_family_doctor_list_api", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		/* 15-12-2021 */
		@NamedStoredProcedureQuery(name = "post_doctor_digitalsignature_api", procedureName = "post_doctor_digitalsignature_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "docid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dsign", type = String.class) }),

		/** 09-12-2021 (Modified) **/
		@NamedStoredProcedureQuery(name = "update_user_phc_api", procedureName = "update_user_phc_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "phc", type = String.class) }),

		@NamedStoredProcedureQuery(name = "get_phc_list_api", procedureName = "get_phc_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "get_patient_medicalupload_details", procedureName = "get_patient_medicalupload_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageno", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "typeidno", type = Integer.class) }),

		/* 10-12-2021 */
		@NamedStoredProcedureQuery(name = "view_organDonor_details", procedureName = "view_organDonor_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_organ_donation_details_pdf", procedureName = "user_organ_donation_details_pdf", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "user_organ_wittnessList", procedureName = "user_organ_wittnessList", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "view_organDonor_witness", procedureName = "view_organDonor_witness", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "organ_list_id", procedureName = "organ_list_id", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "tissue_list_id", procedureName = "tissue_list_id", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/** 08-12-2021 **/
		@NamedStoredProcedureQuery(name = "get_referred_user_list_api", procedureName = "get_referred_user_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "user_doctor_list_by_org", procedureName = "user_doctor_list_by_org", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		/** 06-12-2021 **/
		@NamedStoredProcedureQuery(name = "view_receptionist_appointment_list", procedureName = "view_receptionist_appointment_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),

		/** 04-12-2021 **/
		@NamedStoredProcedureQuery(name = "get_lt_phc_location", procedureName = "get_lt_phc_location", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "get_user_current_medicine_list_api", procedureName = "get_user_current_medicine_list_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		/** 30-11-2021 **/
		@NamedStoredProcedureQuery(name = "get_confirmed_patient_list", procedureName = "get_confirmed_patient_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "drid", type = String.class) }),


		/* Mobile Sign Up & Log In */
		@NamedStoredProcedureQuery(name = "getCountryList", procedureName = "user_country_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "getUserTitleList", procedureName = "user_title_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "getGenderList", procedureName = "user_gender_list", parameters = {}),
	
	
		@NamedStoredProcedureQuery(name = "registerPatientByPathologist", procedureName = "user_registration_mobile", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patMobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "profileImage", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patAge", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patGender", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "height", type = Double.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "weight", type = Double.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patEmail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "aadhar", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "enteredBy", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dist", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "qrcode", type = String.class) }),
		/* 9 */



		@NamedStoredProcedureQuery(name = "user_speciality_list", procedureName = "user_speciality_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "roleid", type = Integer.class) }),

		@NamedStoredProcedureQuery(name = "user_bloodGroup_list", procedureName = "user_bloodGroup_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_roleUser_list", procedureName = "user_roleUser_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_patient_profile_gender_drpdwn", procedureName = "user_patient_profile_gender_drpdwn", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_state_list", procedureName = "user_state_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = Integer.class) }),

		@NamedStoredProcedureQuery(name = "user_city_list", procedureName = "user_city_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_autoSearch_organization_list", procedureName = "user_autoSearch_organization_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchValue", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "roleid", type = Integer.class) }),

		@NamedStoredProcedureQuery(name = "user_plan_card", procedureName = "user_plan_card", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "descName", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_add_registration", procedureName = "user_add_registration", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profileImg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientId", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_gender", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_countryName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_stateName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qrCode", type = String.class), }),
	
		
		/**
		 * for User Login-------
		 */

		@NamedStoredProcedureQuery(name = "getLogInDetails", procedureName = "get_login_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_username", type = String.class) }),
		@NamedStoredProcedureQuery(name = "getUserMenu", procedureName = "get_user_menu", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "roles", type = String.class) }),
		@NamedStoredProcedureQuery(name = "getUserFunction", procedureName = "get_user_function", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "roles", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "moduleid", type = String.class) }),

	

		/*
		 * Login Details
		 * 
		 */

		@NamedStoredProcedureQuery(name = "patient_login_details", procedureName = "patient_login_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),


	
		@NamedStoredProcedureQuery(name = "doctor_patient_details_by_id", procedureName = "doctor_patient_details_by_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_update_patient_details_by_id", procedureName = "doctor_update_patient_details_by_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "note", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "opdid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_booking_count", procedureName = "doctor_booking_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),
		// doctor dashboard
		@NamedStoredProcedureQuery(name = "doctor_dashboardbooking_count", procedureName = "doctor_dashboardbooking_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_dashboard_table", procedureName = "doctor_dashboard_table", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_action_list", procedureName = "doctor_action_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "doctor_dashboardpiechart_count", procedureName = "doctor_dashboardpiechart_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),

	
		@NamedStoredProcedureQuery(name = "admin_user_organization", procedureName = "admin_user_organization", parameters = {}),

		@NamedStoredProcedureQuery(name = "view_test_details_for_delivery_process", procedureName = "view_test_details_for_delivery_process", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_testid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labid", type = String.class), }),
		@NamedStoredProcedureQuery(name = "view_patient_invoice_details", procedureName = "view_patient_invoice_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "uid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "oid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mdate", type = String.class) }),
		@NamedStoredProcedureQuery(name = "view_patient_invoice_medicine_details", procedureName = "view_patient_invoice_medicine_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "uid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "oid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mdate", type = String.class) }),
		// payment details save
		/*
		 * @NamedStoredProcedureQuery(name = "save_payment_details_dao", procedureName =
		 * "save_payment_details_dao", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "order_id", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "statuss", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type =
		 * String.class) ,
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_mode",
		 * type = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_for", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type =
		 * String.class)}),
		 */
		
		@NamedStoredProcedureQuery(name = "save_payment_details_dao", procedureName = "save_payment_details_dao", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "order_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "razor_ordrid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "statuss", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = String.class) ,
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_mode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_for", type = String.class)}),
		
		// payment status update
		@NamedStoredProcedureQuery(name = "save_payment_status", procedureName = "save_payment_status", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "razor_ordrid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "razor_payid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "areaString", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "startUrl", type = String.class) }),

		
		
		@NamedStoredProcedureQuery(name = "save_payment_details_lab", procedureName = "save_payment_details_lab", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "order_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "razor_ordrid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "statuss", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = String.class) ,
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_mode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "payment_for", type = String.class)}),
		
		// payment status update
		@NamedStoredProcedureQuery(name = "save_payment_status_lab", procedureName = "save_payment_status_lab", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "razor_ordrid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "razor_payid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),
		
		
	@NamedStoredProcedureQuery(name = "lab_booking_test_add", procedureName = "lab_booking_test_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "packageList", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testList", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "labid", type = String.class),

		}),
		
		@NamedStoredProcedureQuery(name = "lab_booking_package_add", procedureName = "lab_booking_package_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "packageList", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "labid", type = String.class),

		}),
		
		@NamedStoredProcedureQuery(name = "lab_booking_package_test_add", procedureName = "lab_booking_package_test_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testList", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "labid", type = String.class),

		}),
		
		// Cure Eazy Web 
		
		@NamedStoredProcedureQuery(name = "edit_doctorDetails", procedureName = "edit_doctorDetails", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docId", type = String.class) }),
		
		
		@NamedStoredProcedureQuery(name = "delete_doctorDetails", procedureName = "delete_doctorDetails", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docId", type = String.class) }),
		
		@NamedStoredProcedureQuery(name = "admin_getCityList", procedureName = "admin_getCityList", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_searchvalue", type = String.class) }),
		@NamedStoredProcedureQuery(name = "location_add", procedureName = "location_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pincode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_locFor", type = String.class),

		}),
		@NamedStoredProcedureQuery(name = "location_modify", procedureName = "location_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pincode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_locFor", type = String.class),

		}),
		//doctor listing view
		
		@NamedStoredProcedureQuery(name = "admin_Doctor_view", procedureName = "admin_Doctor_view", parameters = { }),
		
		
		//patient listing view
		
		@NamedStoredProcedureQuery(name = "admin_Patient_view", procedureName = "admin_Patient_view", parameters = { }),


		@NamedStoredProcedureQuery(name = "doctor_add", procedureName = "doctor_add", parameters = {
				//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docId", type = String.class),
				//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docUniqId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docReg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docClinic", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docSpe", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docEmail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docMob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_age", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_gender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qualif", type = String.class),						
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_about", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pyear", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docLang", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doccity", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docAdd", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docLong", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docLat", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docAudio", type = String.class),						
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docVdo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docPhy", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_oTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fTime", type = String.class),						
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sBook", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_offDays", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_aName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_aNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ifsc", type = String.class),
				//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lfrom", type = String.class),
			//	@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lto", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_first", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_last", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lastTimeslot", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "packagelist", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_experience", type = String.class),
		}),
		@NamedStoredProcedureQuery(name = "admin_Gender_List", procedureName = "admin_Gender_List", parameters = { }),
		@NamedStoredProcedureQuery(name = "labtest_package_view", procedureName = "labtest_package_view", parameters = { }),
		
@NamedStoredProcedureQuery(name = "doctor_modify", procedureName = "doctor_modify", parameters = {
				
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docReg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docClinic", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docSpe", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docEmail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docMob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_age", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_gender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qualif", type = String.class),						
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_about", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pyear", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docLang", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class),						
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doccity", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docAdd", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docLong", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docLat", type = String.class),
				
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docAudio", type = String.class),						
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docVdo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_docPhy", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_oTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fTime", type = String.class),						
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sBook", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_offDays", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_aName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_aNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ifsc", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_first", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_last", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lastTimeslot", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "packagelist", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_experience", type = String.class),
		}),

//Timeslot dropdown
	@NamedStoredProcedureQuery(name = "admin_TimeSlot_List", procedureName = "admin_TimeSlot_List", parameters = { }),

//Offdays dropdown
	@NamedStoredProcedureQuery(name = "admin_OffDays_List", procedureName = "admin_OffDays_List", parameters = { }),
	
	//assign coupon message
	@NamedStoredProcedureQuery(name = "admin_assign_coupon_message_view", procedureName = "admin_assign_coupon_message_view", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pid", type = String.class),
		}),
		@NamedStoredProcedureQuery(name = "banner_add", procedureName = "banner_add", parameters = {
			    @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerTitle", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerLink", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerCaption", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class),

		}),
		@NamedStoredProcedureQuery(name = "banner_modify", procedureName = "banner_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerTitle", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerLink", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bannerCaption", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class),

		}),

		@NamedStoredProcedureQuery(name = "admin_Banner_view", procedureName = "admin_Banner_view", parameters = { }),
		


		@NamedStoredProcedureQuery(name = "edit_admin_banner", procedureName = "edit_admin_banner", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bnrId", type = String.class) }),
		
		@NamedStoredProcedureQuery(name = "delete_admin_banner", procedureName = "delete_admin_banner", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bnrId", type = String.class) }),

		
		@NamedStoredProcedureQuery(name = "category_add", procedureName = "category_add", parameters = {
				//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryTitle", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class),

		}),

		@NamedStoredProcedureQuery(name = "category_modify", procedureName = "category_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryTitle", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class),

		}),
		
		@NamedStoredProcedureQuery(name = "admin_category_view", procedureName = "admin_category_view", parameters = { }),
		
	

		@NamedStoredProcedureQuery(name = "edit_admin_categoryDetails", procedureName = "edit_admin_categoryDetails", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ctrId", type = String.class) }),
		
		
		@NamedStoredProcedureQuery(name = "delete_admin_category", procedureName = "delete_admin_category", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ctrId", type = String.class) }),
		
		
		@NamedStoredProcedureQuery(name = "symptom_modify", procedureName = "symptom_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_symptomId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_speciality", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_desc", type = String.class),

		}),
		@NamedStoredProcedureQuery(name = "symptom_add", procedureName = "symptom_add", parameters = {
				//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_symptomId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_speciality", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_desc", type = String.class),

		}),
		@NamedStoredProcedureQuery(name = "admin_Symptom_view", procedureName = "admin_Symptom_view", parameters = { }),
		
		@NamedStoredProcedureQuery(name = "admin_Symptom_specialityList", procedureName = "admin_Symptom_specialityList", parameters = { }),
		
	
		@NamedStoredProcedureQuery(name = "edit_admin_symptomDetails", procedureName = "edit_admin_symptomDetails", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_symId", type = String.class) }),
		
		
		@NamedStoredProcedureQuery(name = "delete_admin_symptom", procedureName = "delete_admin_symptom", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_symId", type = String.class) }),
		

		
		@NamedStoredProcedureQuery(name = "admin_Location_view", procedureName = "admin_Location_view", parameters = { }),

		@NamedStoredProcedureQuery(name = "edit_admin_location", procedureName = "edit_admin_location", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_locId", type = String.class) }),
		
		
		@NamedStoredProcedureQuery(name = "delete_admin_location", procedureName = "delete_admin_location", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_locId", type = String.class) }),
		
		@NamedStoredProcedureQuery(name = "admin_Patient_bookingList_view", procedureName = "admin_Patient_bookingList_view", parameters = { }),
		
//lab_testorder_view
		
		@NamedStoredProcedureQuery(name = "lab_testorder_view", procedureName = "lab_testorder_view", parameters = { }),
		
//admin_Dashboard_view
		
		@NamedStoredProcedureQuery(name = "admin_Dashboard_view", procedureName = "admin_Dashboard_view", parameters = { }),

		
		
        //delete_lab_testpackage_details
		
		@NamedStoredProcedureQuery(name = "delete_lab_testpackage_details", procedureName = "delete_lab_testpackage_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class) }),
	

		
		//edit_LabtestpackageDetails
		@NamedStoredProcedureQuery(name = "edit_LabtestpackageDetails", procedureName = "edit_LabtestpackageDetails", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class) }),
		
		
		
		@NamedStoredProcedureQuery(name = "labtest_package_modify", procedureName = "labtest_package_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testList", type = String.class),
			

		}),
		/*
		 * @NamedStoredProcedureQuery(name = "labtest_package_add", procedureName =
		 * "labtest_package_add", parameters = { //@StoredProcedureParameter(mode =
		 * ParameterMode.IN, name = "p_categoryId", type = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labtestName",
		 * type = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_image", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description",
		 * type = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_price", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_discountprice",
		 * type = String.class),
		 * 
		 * }),
		 */
//delete_lab_test_details
		
		@NamedStoredProcedureQuery(name = "delete_lab_timeslot_details", procedureName = "delete_lab_timeslot_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class) }),

		
		@NamedStoredProcedureQuery(name = "lab_timeslot_details_modify", procedureName = "lab_timeslot_details_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fromTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_toTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),

		}),
		
		//lab_timeslot_details_add
		
		@NamedStoredProcedureQuery(name = "lab_timeslot_details_add", procedureName = "lab_timeslot_details_add", parameters = {
				//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fromTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_toTime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				

		}),
		
		//delete_lab_test_details
		
		@NamedStoredProcedureQuery(name = "delete_lab_test_details", procedureName = "delete_lab_test_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class) }),
		
		//edit_LabDetails
		@NamedStoredProcedureQuery(name = "edit_LabTest_Details", procedureName = "edit_LabTest_Details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class) }),
		//lab_tests_view_details
		
		@NamedStoredProcedureQuery(name = "viewsTestsDetails_lab_test", procedureName = "viewsTestsDetails_lab_test", parameters = { }),
		
		//lab_test_details_modify
		@NamedStoredProcedureQuery(name = "lab_test_details_modify", procedureName = "lab_test_details_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_code", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_methodology", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sampletypevolume", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_processon", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tat", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_frequently", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_featured", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_price", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_discountprice", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_b2b", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_yoursaving", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_yoursavingspercentage", type = String.class),

		}),
		
		@NamedStoredProcedureQuery(name = "lab_tests_adds_details", procedureName = "lab_tests_adds_details", parameters = {
				//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_code", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_methodology", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sampletypevolume", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_processon", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tat", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_frequently", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_featured", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_price", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_discountprice", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_b2b", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_yoursaving", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_yoursavingspercentage", type = String.class),

		}),
		
		
		
				
				
		//edit_LabDetails
		@NamedStoredProcedureQuery(name = "edit_LabDetails", procedureName = "edit_LabDetails", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class) }),
		
		
		
		
		
		  //lab_details_view
		
		@NamedStoredProcedureQuery(name = "lab_details_view", procedureName = "lab_details_view", parameters = { }),
		
		


	
		@NamedStoredProcedureQuery(name = "lab_details_add", procedureName = "lab_details_add", parameters = {
				//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_location", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phoneNumber", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bankAccName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bankAccNumber", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bankIfscCode", type = String.class),

		}),

		
		@NamedStoredProcedureQuery(name = "lab_details_modify", procedureName = "lab_details_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_location", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phoneNumber", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bankAccName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bankAccNumber", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bankIfscCode", type = String.class),

		}),	
		
		// city	
		
		@NamedStoredProcedureQuery(name = "city_add", procedureName = "city_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
			

		}),
		@NamedStoredProcedureQuery(name = "city_modify", procedureName = "city_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
			

		}),
		
		
		@NamedStoredProcedureQuery(name = "admin_city_view", procedureName = "admin_city_view", parameters = { }),

		@NamedStoredProcedureQuery(name = "edit_admin_city", procedureName = "edit_admin_city", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cityId", type = String.class) }),
		
		
		@NamedStoredProcedureQuery(name = "delete_admin_city", procedureName = "delete_admin_city", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cityId", type = String.class) }),
		
		// language	
		
		@NamedStoredProcedureQuery(name = "Language_add", procedureName = "Language_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lstatus", type = String.class),
			

		}),
		@NamedStoredProcedureQuery(name = "language_modify", procedureName = "language_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sstatus", type = String.class),
			

		}),
		
		
		@NamedStoredProcedureQuery(name = "admin_Language_view", procedureName = "admin_Language_view", parameters = { }),

		@NamedStoredProcedureQuery(name = "edit_admin_Language", procedureName = "edit_admin_Language", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_languageId", type = String.class) }),
		
		
		@NamedStoredProcedureQuery(name = "delete_admin_Language", procedureName = "delete_admin_Language", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_languageId", type = String.class) }),
		
		@NamedStoredProcedureQuery(name = "laTestOrder_modify", procedureName = "laTestOrder_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_Id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sts", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rmrks", type = String.class),
			

		}),
		
		@NamedStoredProcedureQuery(name = "view_LabTestOrder_ById", procedureName = "view_LabTestOrder_ById", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orderId", type = String.class) }),
		
		// Home Care Order view

				@NamedStoredProcedureQuery(name = "homecare_order_view", procedureName = "homecare_order_view", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dashbooardId", type = String.class) }),

				@NamedStoredProcedureQuery(name = "admin_getServiceNameList", procedureName = "admin_getServiceNameList", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_searchvalue", type = String.class) }),

				// homecare_homecarepackage_details_add

				@NamedStoredProcedureQuery(name = "homecare_homecarepackage_details_add", procedureName = "homecare_homecarepackage_details_add", parameters = {
						// @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryId",
						// type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_serviceName", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description", type = String.class),

				}),

				@NamedStoredProcedureQuery(name = "homecare_homecarepackage_details_modify", procedureName = "homecare_homecarepackage_details_modify", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_srId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_serviceName", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description", type = String.class),

				}),

				// homecare_package_view

				@NamedStoredProcedureQuery(name = "homecare_package_view", procedureName = "homecare_package_view", parameters = {}),

		//delete_homecare_package_details
				
				@NamedStoredProcedureQuery(name = "delete_homecare_package_details", procedureName = "delete_homecare_package_details", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_srId", type = String.class) }),

				// edit_HomeCarePackageDetails
				@NamedStoredProcedureQuery(name = "edit_HomeCarePackageDetails", procedureName = "edit_HomeCarePackageDetails", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_srId", type = String.class) }),

				// homecare_service_details_add

				@NamedStoredProcedureQuery(name = "homecare_service_details_add", procedureName = "homecare_service_details_add", parameters = {
						// @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_categoryId",
						// type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_description", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_frequentlybooked", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_featured", type = String.class),

				}),

				// homecare_service_view

				@NamedStoredProcedureQuery(name = "homecare_service_view", procedureName = "homecare_service_view", parameters = {}),

				// edit_HomeCareServiceDetails
				@NamedStoredProcedureQuery(name = "edit_HomeCareServiceDetails", procedureName = "edit_HomeCareServiceDetails", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_serviceId", type = String.class) }),

				// delete_homecare_service_details

				@NamedStoredProcedureQuery(name = "delete_homecare_service_details", procedureName = "delete_homecare_service_details", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_serviceId", type = String.class) }),

				@NamedStoredProcedureQuery(name = "admin_homeServiceDashBoard_Count", procedureName = "admin_homeServiceDashBoard_Count", parameters = {}),

				@NamedStoredProcedureQuery(name = "homeCare_Service_List", procedureName = "homeCare_Service_List", parameters = {}),

	
				@NamedStoredProcedureQuery(name = "assign_homecare_service", procedureName = "assign_homecare_service", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_oId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sts", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_service", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_remark", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_document", type = String.class), }),

				@NamedStoredProcedureQuery(name = "homecare_partner_add", procedureName = "homecare_partner_add", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_loc", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_accno", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pass", type = String.class), }),

				@NamedStoredProcedureQuery(name = "homecare_partner_modify", procedureName = "homecare_partner_modify", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_loc", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_accno", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pass", type = String.class), }),

				@NamedStoredProcedureQuery(name = "homecare_partner_view", procedureName = "homecare_partner_view", parameters = {}),

				@NamedStoredProcedureQuery(name = "viewHomeService_PartnerAssignDetails", procedureName = "viewHomeService_PartnerAssignDetails", parameters = {}),

				@NamedStoredProcedureQuery(name = "viewHomeService_PartnerAcceptDetails", procedureName = "viewHomeService_PartnerAcceptDetails", parameters = {}),

				@NamedStoredProcedureQuery(name = "viewHomeService_PartnerDetails", procedureName = "viewHomeService_PartnerDetails", parameters = {}),

				@NamedStoredProcedureQuery(name = "edit_HomeCarePartnerDetails", procedureName = "edit_HomeCarePartnerDetails", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_partnerId", type = String.class) }),

				@NamedStoredProcedureQuery(name = "delete_homeCareServicePartner_details", procedureName = "delete_homeCareServicePartner_details", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_partnerId", type = String.class) }),

				// scheme add
				@NamedStoredProcedureQuery(name = "assign_partner", procedureName = "assign_partner", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_oId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_remark", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_serviceId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "partnerlist", type = String.class) }),

				@NamedStoredProcedureQuery(name = "homecarePartner_service_documentation", procedureName = "homecarePartner_service_documentation", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_oId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sts", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_service", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_desc", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phn", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_price", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_days", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sdate", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_edate", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_document", type = String.class), }),

				@NamedStoredProcedureQuery(name = "viewHomeService_RejectDetails", procedureName = "viewHomeService_RejectDetails", parameters = {}),

				@NamedStoredProcedureQuery(name = "viewHomeService_DocumentationDetails", procedureName = "viewHomeService_DocumentationDetails", parameters = {}),

				@NamedStoredProcedureQuery(name = "viewHomeService_InProgressDetails", procedureName = "viewHomeService_InProgressDetails", parameters = {}),

				@NamedStoredProcedureQuery(name = "viewHomeService_FeedBackDetails", procedureName = "viewHomeService_FeedBackDetails", parameters = {}),

				@NamedStoredProcedureQuery(name = "homecarePartner_service_Completedstatus", procedureName = "homecarePartner_service_Completedstatus", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_oId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sts", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_remark", type = String.class) }),

				@NamedStoredProcedureQuery(name = "homecarePartner_service_Closedstatus", procedureName = "homecarePartner_service_Closedstatus", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_oId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pId", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sts", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_remark", type = String.class) }),

				@NamedStoredProcedureQuery(name = "viewHomeService_CompletedDetails", procedureName = "viewHomeService_CompletedDetails", parameters = {}),

				@NamedStoredProcedureQuery(name = "viewHomeService_ClosedDetails", procedureName = "viewHomeService_ClosedDetails", parameters = {}),

				@NamedStoredProcedureQuery(name = "lab_test_timeslot", procedureName = "lab_test_timeslot", parameters = {}),

				
				@NamedStoredProcedureQuery(name = "edit_LabtimeslotDetails", procedureName = "edit_LabtimeslotDetails", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type = String.class) }),

		/*
		 * @NamedStoredProcedureQuery(name = "delete_lab_timeslot_details",
		 * procedureName = "delete_lab_timeslot_details", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type =
		 * String.class) }),
		 */
		/*
		 * @NamedStoredProcedureQuery(name = "lab_timeslot_details_modify",
		 * procedureName = "lab_timeslot_details_modify", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_labId", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fromTime", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_toTime", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type =
		 * String.class),
		 * 
		 * }),
		 */

			

				

})

/**
 * @author NirmalyaLabs
 *
 */
public class BaseEntityClass {

	@Id
	private Integer pKey;

	public BaseEntityClass() {
		super();
	}

	public Integer getpKey() {
		return pKey;
	}

	public void setpKey(Integer pKey) {
		this.pKey = pKey;
	}

	/**
	 * Overrides toString method for converting class to string and back
	 **/
	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}
}
