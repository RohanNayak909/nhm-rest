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
import nirmalya.aatithya.restmodule.common.utils.GenerateRoleMasterParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.RolesAccessModel;

@Repository
public class RolesAccessDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	Logger logger = LoggerFactory.getLogger(RolesAccessDao.class);

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RolesAccessModel>>> getRolesList() {
		logger.info("Method : getRolesList starts");

		JsonResponse<List<RolesAccessModel>> resp = new JsonResponse<List<RolesAccessModel>>();
		resp.setMessage("");
		resp.setCode("");
		List<RolesAccessModel> roleList = new ArrayList<RolesAccessModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines")
					.setParameter("actionType", "getRolesList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				
				Object createdDate = null;
				
				if(m[5] != null) {
					createdDate = DateFormatter.returnStringDateMonth(m[5]);
				}
				
				RolesAccessModel dropDownModel = new RolesAccessModel(m[0], m[1], m[2], m[3], m[4], createdDate);
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

		ResponseEntity<JsonResponse<List<RolesAccessModel>>> response = new ResponseEntity<JsonResponse<List<RolesAccessModel>>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : getRolesList ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> saveRoleMaster(RolesAccessModel id) {
		logger.info("Method : saveRoleMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			
			String values = "SET @P_RoleId='" + id.getRoleId() + "', @P_Status=" + id.getRoleStatus() + ", @P_CreatedBy='"
					+ id.getCreatedBy() + "', @P_RoleName='" + id.getRoleName() + "', @P_RoleDesc='" + id.getRoleDesc() + "';";
			if(id.getRoleId() != null && id.getRoleId() != "") {
				em.createNamedStoredProcedureQuery("userroleRoutines").setParameter("actionType", "modifyUserRole")
				.setParameter("actionValue", values).execute();
			} else {
				em.createNamedStoredProcedureQuery("userroleRoutines").setParameter("actionType", "addUserRole")
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

		logger.info("Method : saveRoleMaster ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RolesAccessModel>> editRoleMaster(String id) {
		logger.info("Method : editRoleMaster starts");
		
		JsonResponse<RolesAccessModel> resp = new JsonResponse<RolesAccessModel>();
		resp.setMessage("");
		resp.setCode("");
		
		List<RolesAccessModel> dataList = new ArrayList<RolesAccessModel>();
		
		try {
			
			String values = "SET @P_RoleId='" + id + "';";
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("userroleRoutines").setParameter("actionType", "editRole")
			.setParameter("actionValue", values).getResultList();
			
			for (Object[] m : x) {
				RolesAccessModel dropDownModel = new RolesAccessModel(m[0], m[1], m[2], m[3], null, null);
				dataList.add(dropDownModel);
			}
			resp.setBody(dataList.get(0));
			
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
		
		ResponseEntity<JsonResponse<RolesAccessModel>> response = new ResponseEntity<JsonResponse<RolesAccessModel>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : editRoleMaster ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> deleteRoleMaster(List<DropDownModel> id) {
		logger.info("Method : deleteRoleMaster starts");
		
		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
		
		if (validity)
			try {
				
				String value = GenerateRoleMasterParameter.getRoleIdList(id);
				em.createNamedStoredProcedureQuery("userroleRoutines").setParameter("actionType", "deleteRole")
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
		
		logger.info("Method : deleteRoleMaster ends");
		return response;
	}
}
