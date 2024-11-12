package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDistrictModel;
//import nirmalya.aatithya.restmodule.admin.model.RestAdminFinancialModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestAdminUserDao {
	Logger logger = LoggerFactory.getLogger(RestAdminUserDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	StringBuilder errorMessage = new StringBuilder();


	// add user

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestAdminUserModel>> addAdminUser(RestAdminUserModel restAdminUserModel) {
		logger.info("Method : adminUserDao starts" + restAdminUserModel);
		RestAdminUserModel pur = new RestAdminUserModel();
		JsonResponse<RestAdminUserModel> resp = new JsonResponse<RestAdminUserModel>();

		String checkedCheckboxes = "";

		if (restAdminUserModel.getCheckedCheckboxes() != null) {
			if (restAdminUserModel.getCheckedCheckboxes().size() > 0) {
				for (DropDownModel m : restAdminUserModel.getCheckedCheckboxes()) {
					checkedCheckboxes = checkedCheckboxes + "(\'" + restAdminUserModel.getDistrictName() + "\',\'"
							+ restAdminUserModel.getUserName() + "\',\'" + restAdminUserModel.getMobileNum() + "\',\'"
							+ restAdminUserModel.getStatus() + "\',\'" + m.getKey() + "\',\'" + m.getName() + "\',\'"+restAdminUserModel.getDistrict()+"\'),";
					logger.info("!@#$%^&*(!@#$%^&*(@#$%^" + restAdminUserModel.getDistrict());

					logger.info("#$#$#$$#$#$#$#$#$#" + restAdminUserModel.getMobileNum());
				}

				checkedCheckboxes = checkedCheckboxes.substring(0, checkedCheckboxes.length() - 1);
			} else {
				checkedCheckboxes = "";
			}

		}

		String userTypes = restAdminUserModel.getUserType();

		System.out.println("checkedCheckboxes" + checkedCheckboxes);

		// districtName=isExistDistrict(restAdminUserModel.getDistrictName());
		// System.out.println("districtName"+districtName);
		
		try {
			if (restAdminUserModel.getDistrict() != null) {
				System.out.println("if" + restAdminUserModel.getUserId());
				if (userTypes.equals("3")) {
					List<Object[]> x =	em.createNamedStoredProcedureQuery("add_user")
							.setParameter("checkedCheckboxes", checkedCheckboxes)
					.setParameter("p_status", restAdminUserModel.getStatus())
					.setParameter("p_mobileNo", restAdminUserModel.getMobileNum()).getResultList();
					if(restAdminUserModel.getMobileNum().equals(x.get(0))) {
						resp.setMessage("data already exist");
					}
				} else if (userTypes.equals("2")) {

					List<Object[]> x =	em.createNamedStoredProcedureQuery("add_user_district")
							.setParameter("p_userId", restAdminUserModel.getSelectedName())
							.setParameter("p_districtName", restAdminUserModel.getDistrictName())
							.setParameter("p_district", restAdminUserModel.getDistrict())
							.setParameter("p_userName", restAdminUserModel.getUserName())
							.setParameter("p_mobileNumber", restAdminUserModel.getMobileNum()).getResultList();
					if(restAdminUserModel.getSelectedName().equals(x.get(0))) {
						resp.setMessage("data already exist");
					} 

				} else if (userTypes.equals("1")) {
					em.createNamedStoredProcedureQuery("add_user_state")
					        .setParameter("p_adminId", restAdminUserModel.getAdminId())
							.setParameter("p_userName", restAdminUserModel.getUserName())
							.setParameter("p_mobileNumber", restAdminUserModel.getMobileNum())
							.setParameter("p_state", restAdminUserModel.getState())
							//.setParameter("p_stateName", restAdminUserModel.getMobileNum())
							.getResultList();
				} else {
					List<Object[]> x =em.createNamedStoredProcedureQuery("add_user_admin")
							.setParameter("p_adminId", restAdminUserModel.getAdminId())
//							.setParameter("p_district", restAdminUserModel.getDistrict())
//							.setParameter("p_districtName", restAdminUserModel.getDistrictName())
							.setParameter("p_userName", restAdminUserModel.getUserName())
							.setParameter("p_mobileNumber", restAdminUserModel.getMobileNum()).getResultList();
					System.out.println("x"+x);
					System.out.println("x"+x.get(0));
					if(restAdminUserModel.getAdminId().equals(x.get(0))) {
						resp.setMessage("data already exist");
					}
				}

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);
				resp.setBody(pur);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestAdminUserModel>> response = new ResponseEntity<JsonResponse<RestAdminUserModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : enterProjectUpdatesDao ends" + resp);
		return response;
	}



//view user details

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminUserModel>> restViewUserDetails(Integer pageno,String userType,String district) {
		logger.info("Method : viewUserDetails Dao starts");
		List<RestAdminUserModel> admin = new ArrayList<RestAdminUserModel>();
		JsonResponse<List<RestAdminUserModel>> resp = new JsonResponse<List<RestAdminUserModel>>();
		String type="";
		if(userType.equals("0")) {
			 type="19";
			 System.out.println("19");
		}else if(userType.equals("1")) {
			 type="23";
			 System.out.println("23");
		}else if(userType.equals("2")) {
		type="21";
		System.out.println("21");
	    }
		else if(userType.equals("3")){
			 type="22";
			 System.out.println("22");
		}
		else {
			type="";
			 System.out.println("sxfvgb");
		}
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_user_view")
					.setParameter("pageno", pageno)
					.setParameter("typesss", type)
					.setParameter("district", district)
					.getResultList();

			for (Object[] m : x) {

				RestAdminUserModel so = new RestAdminUserModel(m[0],m[1],m[2],m[3],m[4], m[5],m[6],m[7],m[8],m[9]);
				admin.add(so);
				
				System.out.println("RestAdminUserModel"+so);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : viewUserDetails Dao ends" + resp);
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestAdminUserModel>>> editUserDetails(String id, String mob) {
	    logger.info("Method : editUserDetails starts with id: " + id + " and mob: " + mob);

	    JsonResponse<List<RestAdminUserModel>> resp = new JsonResponse<List<RestAdminUserModel>>();
	    List<RestAdminUserModel> rs = new ArrayList<RestAdminUserModel>();

	    try {
	        if (id.equalsIgnoreCase("Junior Engineer")) {
	            List<Object[]> x = em.createNamedStoredProcedureQuery("edit_user_je")
	                .setParameter("p_id", id)
	                .setParameter("p_mob", mob)
	                .getResultList();
	            for (Object[] m : x) {
	                RestAdminUserModel reqemp = new RestAdminUserModel(m[0], m[1], m[2], m[3], m[4], m[5], null, m[6], null);
	                rs.add(reqemp);
	            }
	        } else if (id.equalsIgnoreCase("District Admin")) {
	            List<Object[]> n = em.createNamedStoredProcedureQuery("edit_user_district")
	                .setParameter("p_id", id)
	                .setParameter("p_mob", mob)
	                .getResultList();
	            for (Object[] m : n) {
	                RestAdminUserModel reqemp = new RestAdminUserModel(m[0], m[1], m[2], m[3], null, null, null, null, null);
	                rs.add(reqemp);
	            }
	        } else if (id.equalsIgnoreCase("State Admin")) {
	            List<Object[]> n = em.createNamedStoredProcedureQuery("edit_user_state")
	                .setParameter("p_id", id)
	                .setParameter("p_mob", mob)
	                .getResultList();
	            for (Object[] m : n) {
	                RestAdminUserModel reqemp = new RestAdminUserModel(m[0], m[1], m[2], m[3], null, null, null, null, null);
	                rs.add(reqemp);
	            }
	        } else {
	            List<Object[]> n = em.createNamedStoredProcedureQuery("edit_user_admin")
	                .setParameter("p_id", id)
	                .setParameter("p_mob", mob)
	                .getResultList();
	            for (Object[] m : n) {
	                RestAdminUserModel reqemp = new RestAdminUserModel(m[0], null, m[1], m[2], null, null, null, null, null);
	                rs.add(reqemp);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    resp.setBody(rs);
	    return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.CREATED);
	}


	public ResponseEntity<JsonResponse<RestAdminUserModel>> restModifyUserDetails(
			RestAdminUserModel restAdminUserModel) {
		logger.info("Method : restModifyUserDetails starts" + restAdminUserModel);
		System.out.println("restAdminUserModel.getMobileNum()"+restAdminUserModel.getMobileNum());
		System.out.println("JEIDS>>>>>>>== "+restAdminUserModel.getJeIds());
		RestAdminUserModel pur = new RestAdminUserModel();
		JsonResponse<RestAdminUserModel> resp = new JsonResponse<RestAdminUserModel>();

		String checkedCheckboxes = "";
		String userRollId = "";
		// Assuming restAdminUserModel.getJeIds() returns a List<String> containing the jeIds
		String tokens = restAdminUserModel.getJeIds();
		String jeIdsString = String.join(",", tokens);

  
	  //  for (String jeId : jeIdsArray) {
	        System.out.println("tokens>>>>>>>>====: " + tokens);
	        System.out.println("jeId>>>>>>>>====: " + jeIdsString);
	    //}
		
		if (restAdminUserModel.getCheckedCheckboxes() != null) {
			if (restAdminUserModel.getCheckedCheckboxes().size() > 0) {
				for (DropDownModel m : restAdminUserModel.getCheckedCheckboxes()) {
					checkedCheckboxes = checkedCheckboxes + "(\'" + restAdminUserModel.getDistrictName() + "\',\'"
							+ restAdminUserModel.getUserName() + "\',\'" + restAdminUserModel.getMobileNum() + "\',\'"
							+ m.getKey() + "\',\'" + m.getName() + "\',\'"+restAdminUserModel.getDistrict()+"\'),";

					
				}
				
				
				logger.info("checkedCheckboxes" + checkedCheckboxes);

				checkedCheckboxes = checkedCheckboxes.substring(0, checkedCheckboxes.length() - 1);
			} else {
				checkedCheckboxes = "";
			}

		}

		String userTypes = restAdminUserModel.getUserType();

		System.out.println("userTypes" + userTypes);
		System.out.println("~!@#$%^&*(*&^%$#@!~!@#$%^&*(*&^%$#@!@#$" + restAdminUserModel.getAdminId());
		System.out.println("~!@#$%^&*" +restAdminUserModel.getUserName());
		System.out.println("~!@#$%^&*" +restAdminUserModel.getMobileNum());
		
		try {
			if (restAdminUserModel.getDistrict() != null || restAdminUserModel.getMobileNum() != null) {
				System.out.println("if" + restAdminUserModel.getDistrict());
				System.out.println("if-getMobileNum" + restAdminUserModel.getMobileNum());
				if (userTypes.equals("3")) {
					System.out.println("restAdminUserModel.getStatus()"+restAdminUserModel.getStatus());
					System.out.println("jeIdsString!@#$%^&*"+jeIdsString);

					em.createNamedStoredProcedureQuery("modify_user")
							.setParameter("checkedCheckboxes", checkedCheckboxes)
							.setParameter("distid", restAdminUserModel.getDistrictName())
							.setParameter("mob", restAdminUserModel.getMobileNum())
							.setParameter("p_status", restAdminUserModel.getStatus())
							.setParameter("p_jeIds", jeIdsString)
							.getResultList();
					System.out.println("restAdminUserModel.getStatus()"+restAdminUserModel.getStatus());
					
				} else if (userTypes.equals("2")) {
					em.createNamedStoredProcedureQuery("modify_user_district")
							.setParameter("p_userId", restAdminUserModel.getSelectedName())
							.setParameter("p_districtName", restAdminUserModel.getDistrictName())
							.setParameter("p_district", restAdminUserModel.getDistrict())
							.setParameter("p_userName", restAdminUserModel.getUserName())
							.setParameter("p_mobileNumber", restAdminUserModel.getMobileNum()).getResultList();
				} else if (userTypes.equals("1")) {
					System.out.println("USERTYPE>>>>=== "+restAdminUserModel.getUserType());
					System.out.println("ADMINID>>>= "+restAdminUserModel.getAdminId());
					System.out.println("USERNAME>>>>= "+restAdminUserModel.getUserName());
					System.out.println("MOBILENUM>>>>= "+restAdminUserModel.getMobileNum());
					System.out.println("STATE>>>.= "+restAdminUserModel.getState());
					em.createNamedStoredProcedureQuery("modify_user_state")
					.setParameter("p_adminId", restAdminUserModel.getAdminId())
					.setParameter("p_userName", restAdminUserModel.getUserName())
					.setParameter("p_mobileNumber", restAdminUserModel.getMobileNum())
					.setParameter("p_state", restAdminUserModel.getState())
					.getResultList();
				} else {
					em.createNamedStoredProcedureQuery("modify_user_admin")
							.setParameter("p_adminId", restAdminUserModel.getAdminId())
//							.setParameter("p_district", restAdminUserModel.getDistrict())
//							.setParameter("p_districtName", restAdminUserModel.getDistrictName())
							.setParameter("p_userName", restAdminUserModel.getUserName())
							.setParameter("p_mobileNumber", restAdminUserModel.getMobileNum()).getResultList();
				}

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

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getBlockListEdit(Integer id, String mob) {
	    logger.info("Method : getBlockLists Dao starts, District: " + id + ", Mob: " + mob);
	    
	    List<DropDownModel> blockList = new ArrayList<>();
	    JsonResponse<List<DropDownModel>> resp = new JsonResponse<>();
	    try {
	        List<Object[]> x = em.createNamedStoredProcedureQuery("admin_dropdown_block_list_edit")
	            .setParameter("district", id)
	            .setParameter("mob", mob)
	            .getResultList();

	        for (Object[] m : x) {
	            DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
	            blockList.add(dropDownModel);
	        }

	        resp.setBody(blockList);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    logger.info("Method : getBlockListEdit Dao ends, Response: " + resp);
	    return resp;
	}

	
	
	//Rest Password
	
	
	public ResponseEntity<JsonResponse<Object>> resetUserPasswordDao(String mobileNum) {
		logger.info("Method : resetUserPasswordDao starts");

		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
		if (validity)
			try {
				em.createNamedStoredProcedureQuery("reset_user_password")
				.setParameter("p_mobno", mobileNum).execute();
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
		logger.info("Method : resetUserPasswordDao ends");
		return response;
	}

	
	
	
	//delete password
	
	

	public ResponseEntity<JsonResponse<Object>> deleteUserDao(String mobileNum) {
		logger.info("Method : deleteUser dao starts");
		logger.info("!@#$%^&*(&^%$#@!@"+mobileNum);
		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
		if (validity)
			try {
				em.createNamedStoredProcedureQuery("delete_user")
				.setParameter("p_mobno", mobileNum)
						.execute();
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
		logger.info("Method : deleteUser dao ends"+response);
		return response;
	}
	
	
	
	// check validate user name
	
	@SuppressWarnings("unchecked")
	public JsonResponse<DropDownModel> restGetValidateUserNameDao(String userName) {
	    logger.info("Method : restGetValidateUserNameDao starts with userName: " + userName);
	    DropDownModel sanctionYear = new DropDownModel();
	    JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();

	    // Regular expression to check if username contains only characters
	    String regex = "^[a-zA-Z\\s]+$"; // Regex pattern to allow only letters and spaces
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(userName);

	    // Print the district name and whether it matches the pattern
	    System.out.println("District Name: " + userName);
	    System.out.println("Matches pattern: " + matcher.matches());

	    // Validate username
	    if (userName.matches(regex)) {
	        // If the username is valid, return a success message
	        resp.setMessage("Success: Username is valid.");
	        resp.setCode("100");
	    } else {
	        // Return error message if validation fails
	        resp.setMessage("Error: Username should contain only characters without any numbers or special characters.");
	        resp.setCode("200");
	    }

	    logger.info("Method : restGetValidateUserNameDao ends with response: " + resp);
	    return resp;
	}




	
	//check valid mobile number
	
	@SuppressWarnings("unchecked")
	public JsonResponse<DropDownModel> restGetValidateMobNoDao(String mobNo) {
		logger.info("Method : restGetValidateMobNoDao starts: " + mobNo);
		DropDownModel sanctionYear = new DropDownModel();
		JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();
		
		// Regular expression to check if username contains only characters
		String regex = "^[0-9]+$"; // Regex pattern to allow only letters and spaces
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mobNo);
		
		// Print the district name and whether it matches the pattern
		System.out.println("District Name: " + mobNo);
		System.out.println("Matches pattern: " + matcher.matches());
		
		// Validate username
		if (mobNo.matches(regex)) {
			// If the username is valid, return a success message
			resp.setMessage("Success: Mobile number is valid.");
			resp.setCode("100");
		} else {
			// Return error message if validation fails
			resp.setMessage("Error: Mobile number should contain only numbers.");
			resp.setCode("200");
		}
		
		logger.info("Method : restGetValidateMobNoDao ends: " + resp);
		return resp;
	}
	
	
	
	
	
	
}