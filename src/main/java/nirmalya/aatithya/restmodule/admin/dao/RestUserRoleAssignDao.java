package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminAgencyModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestUserRoleAssignDao {
	Logger logger = LoggerFactory.getLogger(RestAdminUserDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;

	
//	@SuppressWarnings("unchecked")
//	public JsonResponse<List<RestAdminUserModel>> restViewUserRoleAssign(String userType) {
//	    logger.info("Method : restViewUserRoleAssignDao starts");
//	    List<RestAdminUserModel> admin = new ArrayList<RestAdminUserModel>();
//	    JsonResponse<List<RestAdminUserModel>> resp = new JsonResponse<List<RestAdminUserModel>>();
//	    String roleType = "";
//	    if (userType.equals("1")) {
//	        roleType = "13";
//	        System.out.println("13");
//	    } else if (userType.equals("2")) {
//	        roleType = "11";
//	        System.out.println("11");
//	    } else {
//	        roleType = "8";
//	        System.out.println("8");
//	    }
//	    try {
//	        List<Object[]> x = em.createNamedStoredProcedureQuery("admin_user_role_assign")
//	            .setParameter("userType", roleType)
//	            .getResultList();
//
//	        for (Object[] m : x) {
//
//	            RestAdminUserModel so = new RestAdminUserModel(m[0], m[1], m[2], m[3]);
//	            admin.add(so);
//	            // Set the code and message fields of the resp object
//	            resp.setCode("success");
//	            resp.setMessage("Data Fetched Successfully");
//	            System.out.println("RestAdminUserModel" + so);
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	    resp.setBody(admin);
//	    logger.info("Method : restViewUserRoleAssignDao ends" + resp);
//	    return resp;
//	}

	
	
	
	
//	@SuppressWarnings("unchecked")
//	public ResponseEntity<JsonResponse<RestAdminUserModel>> restViewUserRoleAssign(String userType) {
//
//		logger.info("Method : restViewUserRoleAssignDao starts");
//		RestAdminUserModel restLabTestModel = new RestAdminUserModel();
//		JsonResponse<RestAdminUserModel> resp = new JsonResponse<RestAdminUserModel>();
//		String roleType = "";
//	    if (userType.equals("1")) {
//	        roleType = "13";
//	        System.out.println("13");
//	    } else if (userType.equals("2")) {
//	        roleType = "11";
//	        System.out.println("11");
//	    } else {
//	        roleType = "8";
//	        System.out.println("8");
//	    }
//		try {
//			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_user_role_assign")
//					.setParameter("userType", roleType)
//					.getResultList();
//			for (Object[] m : x) {
//				
//				RestAdminUserModel so = new RestAdminUserModel(m[0],m[1],m[2],m[3]);
//				restLabTestModel = so;
//				System.out.println("restLabTestModel>>>>>>="+restLabTestModel);
//				resp.setCode("success");
//				resp.setMessage("Data Fetched Successfully");
//			}
//			resp.setBody(restLabTestModel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("MyResponseHeader", "MyValue");
//
//		ResponseEntity<JsonResponse<RestAdminUserModel>> response = new ResponseEntity<JsonResponse<RestAdminUserModel>>(
//				resp, responseHeaders, HttpStatus.CREATED);
//
//		logger.info("Method : restViewUserRoleAssignDao ends"+response);
//		return response;
//
//	}
	
	
	
	
	

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminUserModel>> restViewUserRoleAssign(String userType) {

		logger.info("Method : restViewUserRoleAssignDao starts"+userType);
		List<RestAdminUserModel> userList = new ArrayList<RestAdminUserModel>();
		JsonResponse<List<RestAdminUserModel>> resp = new JsonResponse<List<RestAdminUserModel>>();
		String roleType = "";
	    if (userType.equals("1")) {
	        roleType = "13";
	        System.out.println("13");
	    } else if (userType.equals("2")) {
	        roleType = "11";
	        System.out.println("11");
	    } else {
	        roleType = "8";
	        System.out.println("8");
	    }
	    
	    System.out.println("roleType"+roleType);
	    
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_user_role_assign")
					.setParameter("userType", roleType)
					.getResultList();
			for (Object[] m : x) {
				RestAdminUserModel dropDownModel = new RestAdminUserModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7]);
				userList.add(dropDownModel);
				resp.setCode("success");
				resp.setMessage("Data Fetched Successfully");
			}

			resp.setBody(userList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : restViewUserRoleAssignDao ends" + resp);
		return resp;
	}
	
	
	
	// submit user role assign
	
	public ResponseEntity<JsonResponse<RestAdminUserModel>> restModifyUserRoleAssign(
			RestAdminUserModel restAdminUserModel) {
		logger.info("Method : restModifyUserDetails starts" + restAdminUserModel);
		RestAdminUserModel pur = new RestAdminUserModel();
		JsonResponse<RestAdminUserModel> resp = new JsonResponse<RestAdminUserModel>();

		String checkedCheckboxes = "";
		String roleType = "";
	    if (restAdminUserModel.getRoleId().equals("1")) {
	        roleType = "13";
	        System.out.println("13");
	    } else if (restAdminUserModel.getRoleId().equals("2")) {
	        roleType = "11";
	        System.out.println("11");
	    } else {
	        roleType = "8";
	        System.out.println("8");
	    }
		
		if (restAdminUserModel.getCheckedCheckboxes() != null) {
			if (restAdminUserModel.getCheckedCheckboxes().size() > 0) {
				for (DropDownModel m : restAdminUserModel.getCheckedCheckboxes()) {
					checkedCheckboxes = checkedCheckboxes + "(\'" + m.getName() + "\',\'"
							+ m.getCode() + "\',\'" + m.getLanguage() + "\',\'"
							+ m.getKey() + "\',\'" + m.getPass() + "\'),";
					
					
				}
				
				
				System.out.println("checkedCheckboxes>>>>>>>>>++++++= "+checkedCheckboxes);

				checkedCheckboxes = checkedCheckboxes.substring(0, checkedCheckboxes.length() - 1);
			} else {
				checkedCheckboxes = "";
			}

		}

		String userTypes = restAdminUserModel.getUserType();

		System.out.println("roleType" + roleType);

		try {
			if (roleType != null) {
				
					
					em.createNamedStoredProcedureQuery("modify_user_role_assign")
							.setParameter("checkedCheckboxes", checkedCheckboxes)
							.setParameter("roleType", roleType)
							.getResultList();
					System.out.println("restAdminUserModel.getStatus()"+restAdminUserModel.getStatus());

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

				System.out.println("DAO" + pur);

				resp.setBody(pur);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestAdminUserModel>> response = new ResponseEntity<JsonResponse<RestAdminUserModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : restModifyUserDetails ends" + resp);
		return response;
	}
	
}
