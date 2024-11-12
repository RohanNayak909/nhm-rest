package nirmalya.aatithya.restmodule.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateAssignRoleAvlFuncParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.ActivityAvlFunctionModel;
import nirmalya.aatithya.restmodule.user.model.AvailableFunctionModel;
import nirmalya.aatithya.restmodule.user.model.HeaderAccessModel;

@Repository
public class PermissionsAccessDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	Logger logger = LoggerFactory.getLogger(PermissionsAccessDao.class);
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<HeaderAccessModel>>> getPermissionsList() {
		logger.info("Method : getPermissionsList starts");

		JsonResponse<List<HeaderAccessModel>> resp = new JsonResponse<List<HeaderAccessModel>>();
		resp.setMessage("");
		resp.setCode(""); 
		List<HeaderAccessModel> roleList = new ArrayList<HeaderAccessModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "getModuleList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				
				HeaderAccessModel dropDownModel = new HeaderAccessModel(m[0], m[1]);
				roleList.add(dropDownModel);
			}
			resp.setBody(roleList);
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<HeaderAccessModel>>> response = new ResponseEntity<JsonResponse<List<HeaderAccessModel>>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : getPermissionsList ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<AvailableFunctionModel>> editPermissionData(String id, String name) {
		logger.info("Method : editPermissionData starts");
		
		JsonResponse<AvailableFunctionModel> resp = new JsonResponse<AvailableFunctionModel>();
		resp.setMessage("");
		resp.setCode(""); 
		List<AvailableFunctionModel> roleList = new ArrayList<AvailableFunctionModel>();
		try {
			String value = "SET @P_RoleId = '" + id + "', @P_AvlFunc = '" + name + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "editPermissionData").setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				
				AvailableFunctionModel dropDownModel = new AvailableFunctionModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8]);
				roleList.add(dropDownModel);
			}
			resp.setBody(roleList.get(0));
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<AvailableFunctionModel>> response = new ResponseEntity<JsonResponse<AvailableFunctionModel>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : editPermissionData ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> assignRoleAvlFunction(AvailableFunctionModel id) {
		logger.info("Method : assignRoleAvlFunction starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode(""); 
		try {
			String value = GenerateAssignRoleAvlFuncParameter.assignRole(id);
			em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "assignRoleAvlFunction")
					.setParameter("actionValue", value).execute();
			
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : assignRoleAvlFunction ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<HeaderAccessModel> getCompChildrenList(String id) {
		logger.info("Method : getCompChildrenList starts");
		
		List<HeaderAccessModel> roleList = new ArrayList<HeaderAccessModel>();
		
		try {
			String value = "SET @P_Mod='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "getComponentList").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				
				HeaderAccessModel dropDownModel = new HeaderAccessModel(m[0], m[1]);
				roleList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getCompChildrenList ends");
		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	public List<HeaderAccessModel> getAvlFuncList(String id) {
		logger.info("Method : getAvlFuncList starts");
		
		List<HeaderAccessModel> roleList = new ArrayList<HeaderAccessModel>();
		
		try {
			String value = "SET @P_Comp='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "getAvlFuncList").setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				
				HeaderAccessModel dropDownModel = new HeaderAccessModel(m[0], m[1]);
				roleList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getAvlFuncList ends");
		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getPermissionLevelList() {
		logger.info("Method : getPermissionLevelList starts");
		
		List<DropDownModel> roleList = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "getPermissionLevel").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				roleList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getPermissionLevelList ends");
		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDataFilterList() {
		logger.info("Method : getDataFilterList starts");
		
		List<DropDownModel> roleList = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "getDataFilter").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				roleList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getDataFilterList ends");
		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	public List<AvailableFunctionModel> getAvlFuncPermissionList(String id) {
		logger.info("Method : getAvlFuncPermissionList starts");
		
		List<AvailableFunctionModel> roleList = new ArrayList<AvailableFunctionModel>();
		
		try {
			String value = "SET @P_RoleID = '" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "getAvlFuncPermissionList").setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				
				AvailableFunctionModel dropDownModel = new AvailableFunctionModel(m[0], m[1], m[2], m[3], m[4], null, null, null, null);
				roleList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getAvlFuncPermissionList ends");
		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ActivityAvlFunctionModel>>> getAvlFunctionByActivityRole(List<DropDownModel> data) {
		logger.info("Method : getAvlFunctionByActivityRole starts");
		
		JsonResponse<List<ActivityAvlFunctionModel>> resp = new JsonResponse<List<ActivityAvlFunctionModel>>();
		resp.setMessage("");
		resp.setCode(""); 
		List<ActivityAvlFunctionModel> avlFuncList = new ArrayList<ActivityAvlFunctionModel>();
		try {
			String value = GenerateAssignRoleAvlFuncParameter.getActivityRoleList(data);
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "getAvlFunctionByActivityRole").setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				ActivityAvlFunctionModel dropDownModel = new ActivityAvlFunctionModel(m[0], m[1], m[2], m[3]);
				avlFuncList.add(dropDownModel);
			}
			resp.setBody(avlFuncList);
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<List<ActivityAvlFunctionModel>>> response = new ResponseEntity<JsonResponse<List<ActivityAvlFunctionModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : getAvlFunctionByActivityRole ends");
		return response;
	}
}
