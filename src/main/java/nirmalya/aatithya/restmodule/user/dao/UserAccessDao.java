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
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateUserMasterParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.ModulesAccessModel;
import nirmalya.aatithya.restmodule.user.model.UserAccessModel;

@Repository
public class UserAccessDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	Logger logger = LoggerFactory.getLogger(UserAccessDao.class);
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<UserAccessModel>>> getUsersList() {
		logger.info("Method : getUsersList starts");

		JsonResponse<List<UserAccessModel>> resp = new JsonResponse<List<UserAccessModel>>();
		resp.setMessage("");
		resp.setCode("");
		List<UserAccessModel> roleList = new ArrayList<UserAccessModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("userManageRoutines")
					.setParameter("actionType", "getUsersList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				
				Object createdDate = null;
				
				if(m[10] != null) {
					createdDate = m[10].toString();
				}
				
				UserAccessModel dropDownModel = new UserAccessModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], createdDate, null ,m[11]);
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

		ResponseEntity<JsonResponse<List<UserAccessModel>>> response = new ResponseEntity<JsonResponse<List<UserAccessModel>>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : getUsersList ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getUserEmployeeList() {
		logger.info("Method : getUserEmployeeList starts");
		
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		resp.setMessage("");
		resp.setCode("");
		List<DropDownModel> roleList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("userManageRoutines")
					.setParameter("actionType", "getUserEmployeeList").setParameter("actionValue", "").getResultList();
			
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
		
		logger.info("Method : getUserEmployeeList ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ModulesAccessModel>>> getUserEmployeeListByName(String id) {
		logger.info("Method : getUserEmployeeListByName starts");
		
		JsonResponse<List<ModulesAccessModel>> resp = new JsonResponse<List<ModulesAccessModel>>();
		resp.setMessage("");
		resp.setCode("");
		List<ModulesAccessModel> roleList = new ArrayList<ModulesAccessModel>();
		try {
			String value = "SET @P_SearchValue='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("userManageRoutines")
					.setParameter("actionType", "getUserEmployeeListByName").setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				
				ModulesAccessModel dropDownModel = new ModulesAccessModel(m[0], m[1], null, null, null);
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
		
		ResponseEntity<JsonResponse<List<ModulesAccessModel>>> response = new ResponseEntity<JsonResponse<List<ModulesAccessModel>>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : getUserEmployeeListByName ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<UserAccessModel>> editUserMaster(String id) {
		logger.info("Method : editUserMaster starts");
		
		JsonResponse<UserAccessModel> resp = new JsonResponse<UserAccessModel>();
		resp.setMessage("");
		resp.setCode("");
		List<UserAccessModel> roleList = new ArrayList<UserAccessModel>();
		try {
			String value = "SET @P_UserId='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("userManageRoutines")
					.setParameter("actionType", "editUserMaster").setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				
				UserAccessModel dropDownModel = new UserAccessModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], null, m[10], m[11]);
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
		
		ResponseEntity<JsonResponse<UserAccessModel>> response = new ResponseEntity<JsonResponse<UserAccessModel>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : editUserMaster ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getUserTypeForUser() {
		logger.info("Method : getUserTypeForUser starts");
		
		List<DropDownModel> userTypeList = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("userManageRoutines")
					.setParameter("actionType", "getUserType").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				userTypeList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getUserTypeForUser ends");
		return userTypeList;
	}
	
	public ResponseEntity<JsonResponse<Object>> saveUserMaster(UserAccessModel id) {
		logger.info("Method : saveUserMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			
			String values = GenerateUserMasterParameter.saveUserMaster(id);
			if(id.getUserId() != null && id.getUserId() != "") {
				em.createNamedStoredProcedureQuery("userManageRoutines").setParameter("actionType", "modifyUserMaster")
				.setParameter("actionValue", values).execute();
			} else {
				em.createNamedStoredProcedureQuery("userManageRoutines").setParameter("actionType", "addUserMaster")
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

		logger.info("Method : saveUserMaster ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> deleteUserMaster(List<DropDownModel> id) {
		logger.info("Method : deleteUserMaster starts");
		
		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
		
		if (validity)
			try {
				
				String value = GenerateUserMasterParameter.getUserIdList(id);
				em.createNamedStoredProcedureQuery("userManageRoutines").setParameter("actionType", "deleteUser")
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
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		
		logger.info("Method : deleteUserMaster ends");
		return response;
	}
	@SuppressWarnings("unchecked")
	public  JsonResponse<Object> profileSwap(String id) {
		// TODO Auto-generated method stub
		logger.info("Method : profileSwap starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		List<DropDownModel> list = new ArrayList<DropDownModel>();
		 
		try {
			List<Object[]> x =  em.createNamedStoredProcedureQuery("get_profileSwap_profiles").setParameter("userid",  id)
			 .getResultList();
			for(Object[] m:x) {
				DropDownModel dropDownModel = new DropDownModel(m[0],m[1],m[2],m[3], null);
				list.add(dropDownModel);
			}
			
		}catch (Exception e){
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		resp.setBody(list);
		logger.info("Method : profileSwap ends"+resp);
		return resp;
	}
}
