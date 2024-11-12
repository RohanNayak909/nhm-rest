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
import nirmalya.aatithya.restmodule.common.utils.GenerateAuthorityParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.RestProcessMasterModel;
import nirmalya.aatithya.restmodule.user.model.UserSetAuthority;

@Repository
public class SetAuthorityDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	Logger logger = LoggerFactory.getLogger(SetAuthorityDao.class);
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getUserRoleDropDown() {
		logger.info("Method : getUserRoleDropDown starts");

		List<DropDownModel> roleList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("userAuthorityRoutines")
					.setParameter("actionType", "getRoleList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				roleList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getUserRoleDropDown ends");
		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestProcessMasterModel>>> getProcessMasterList() {
		logger.info("Method : getProcessMasterList starts");
		
		JsonResponse<List<RestProcessMasterModel>> resp = new JsonResponse<List<RestProcessMasterModel>>();
		resp.setMessage("");
		resp.setCode("");
		List<RestProcessMasterModel> roleList = new ArrayList<RestProcessMasterModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("userAuthorityRoutines")
					.setParameter("actionType", "getProcessList").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				
				Object createdDate = null;
				
				if(m[5] != null) {
					createdDate =m[5].toString();
				}
				
				RestProcessMasterModel dropDownModel = new RestProcessMasterModel(m[0], m[1], m[2], m[3], m[4], createdDate);
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
		
		ResponseEntity<JsonResponse<List<RestProcessMasterModel>>> response = new ResponseEntity<JsonResponse<List<RestProcessMasterModel>>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : getProcessMasterList ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<UserSetAuthority>>> getUserAuthorityList(String id) {
		logger.info("Method : getUserAuthorityList starts");
		
		JsonResponse<List<UserSetAuthority>> resp = new JsonResponse<List<UserSetAuthority>>();
		resp.setMessage("");
		resp.setCode("");
		List<UserSetAuthority> roleList = new ArrayList<UserSetAuthority>();
		
		try {
			String value = "SET @P_ProcessId='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("userAuthorityRoutines")
					.setParameter("actionType", "getAuthorityList").setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				
				Object createdDate = null;
				
				if(m[6] != null) {
					createdDate =m[6].toString();
				}
				
				UserSetAuthority dropDownModel = new UserSetAuthority(m[0], m[1], m[2], m[3].toString(), m[4].toString(), m[5], createdDate, m[7], m[8]);
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
		
		ResponseEntity<JsonResponse<List<UserSetAuthority>>> response = new ResponseEntity<JsonResponse<List<UserSetAuthority>>>(
				resp, HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : getUserAuthorityList ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getEmployeeListByRole(String id) {
		logger.info("Method : getEmployeeListByRole starts");
		
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		resp.setMessage("");
		resp.setCode("");
		List<DropDownModel> roleList = new ArrayList<DropDownModel>();
		
		try {
			String value = "SET @P_RoleId='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("userAuthorityRoutines")
					.setParameter("actionType", "getEmpListByRole").setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
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
		
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : getEmployeeListByRole ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> saveProcessMaster(RestProcessMasterModel id) {
		logger.info("Method : saveProcessMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			
			String values = GenerateAuthorityParameter.saveProcessMaster(id);
			if(id.gettProcess() != null && id.gettProcess() != "") {
				em.createNamedStoredProcedureQuery("userAuthorityRoutines").setParameter("actionType", "modifyProcess")
				.setParameter("actionValue", values).execute();
			} else {
				em.createNamedStoredProcedureQuery("userAuthorityRoutines").setParameter("actionType", "addProcess")
				.setParameter("actionValue", values).execute();
			}
			

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

		logger.info("Method : saveProcessMaster ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> saveAuthorityMasterData(UserSetAuthority id) {
		logger.info("Method : saveAuthorityMasterData starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
		
		try {
			
			String values = GenerateAuthorityParameter.saveAuthorityMaster(id);
			if(id.getIsEdit() != null && id.getIsEdit() != "") {
				System.out.println("if");
				em.createNamedStoredProcedureQuery("userAuthorityRoutines").setParameter("actionType", "modifyAuthority")
				.setParameter("actionValue", values).execute();
			} else {
				em.createNamedStoredProcedureQuery("userAuthorityRoutines").setParameter("actionType", "addAuthority")
				.setParameter("actionValue", values).execute();
			}
			
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
		
		logger.info("Method : saveAuthorityMasterData ends");
		return response;
	}
}
