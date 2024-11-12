package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminAgencyDao;
import nirmalya.aatithya.restmodule.admin.dao.RestAdminUserDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDistrictModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminUserController {
	Logger logger = LoggerFactory.getLogger(RestAdminUserController.class);

	@Autowired
	RestAdminUserDao restAdminUserDao;
	
	@RequestMapping(value = "adminUserAdd", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestAdminUserModel>> addAdminUser(
			@RequestBody RestAdminUserModel restAdminUserModel) {
		logger.info("Method : addAdminProjectUpdate starts");

		logger.info("Method : addAdminProjectUpdate ends"+restAdminUserDao.addAdminUser(restAdminUserModel));
		return restAdminUserDao.addAdminUser(restAdminUserModel);
	}
	      
	
	
	//view user details
	
	@GetMapping(value = "rest-viewUserDetails")
	public JsonResponse<List<RestAdminUserModel>> restViewUserDetails(@RequestParam Integer pageno,@RequestParam String userType,
			@RequestParam String district) {
		logger.info("Method :restViewUserDetails start");
																																																																																																																																																																																								
		logger.info("Method :restViewUserDetails ends");
		return restAdminUserDao.restViewUserDetails(pageno,userType,district);

	}
 
	
	// edit

//			@RequestMapping(value = "editUserDetails", method = { RequestMethod.GET })
//			public ResponseEntity<JsonResponse<RestAdminUserModel>> editUserDetails(@RequestParam String id,@RequestParam String mob) {
//				logger.info("Method : editLabTestDetails rest starts");
//
//				logger.info("Method :editLabTestDetails rest ends");
//				return restAdminUserDao.editUserDetails(id,mob);
//			}
	
	@PostMapping(value = "rest-editUserDetails-Admin")
	public ResponseEntity<JsonResponse<List<RestAdminUserModel>>> editUserDetails(@RequestBody Map<String, String> payload) {
	    String id = payload.get("id");
	    String mob = payload.get("mob");

	    logger.info("Method : editUserDetails starts with id: " + id + " and mob: " + mob);

	    return restAdminUserDao.editUserDetails(id, mob);
	}

	
	
	
	
	@RequestMapping(value = "rest-ModifyUserDetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestAdminUserModel>> ModifyUserDetails(
			@RequestBody RestAdminUserModel restAdminUserModel) {
		logger.info("Method : ModifyUserDetails starts");

		logger.info("Method : ModifyUserDetails ends");
		return restAdminUserDao.restModifyUserDetails(restAdminUserModel);
	}
	@PostMapping(value = "rest-getBlockListEdit")
	public JsonResponse<List<DropDownModel>> getBlockListEdit(@RequestBody Map<String, Object> payload) {
	    Integer id = null;
	    String districtStr = payload.get("district").toString(); // Get district as String
	    String mob = (String) payload.get("mob");

	    try {
	        // Parse the district string into an integer
	        id = Integer.parseInt(districtStr);
	    } catch (NumberFormatException e) {
	        // Handle parsing error, log it, and return an error response
	        logger.error("Invalid district ID format", e);
	        JsonResponse<List<DropDownModel>> errorResponse = new JsonResponse<>();
	        errorResponse.setMessage("Invalid district ID format");
	        return errorResponse;
	    }

	    logger.info("Method : getBlockLists starts, District: " + id + ", Mob: " + mob);

	    return restAdminUserDao.getBlockListEdit(id, mob);
	}



	
	
	//reset password
	
	@RequestMapping(value = "rest-resetUserPassword", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> resetUserPasswordDao(@RequestParam String mobileNum) {
		logger.info("Method : resetUserPassword starts");

		logger.info("Method : resetUserPassword ends");
		return restAdminUserDao.resetUserPasswordDao(mobileNum);
	}
	
	
	// delete user
	
	@RequestMapping(value = "rest-deleteUser", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteUserDao(@RequestParam String mobileNum) {
		logger.info("Method : deleteUserDao starts");

		logger.info("Method : deleteUserDao ends");
		return restAdminUserDao.deleteUserDao(mobileNum);
	}
	
	//check validate username
	
	@GetMapping(value = "rest-getValidateUserName")
	public JsonResponse<DropDownModel> restGetValidateUserName(@RequestParam String userName) {
		logger.info("Method : restGetValidateUserName starts");

		logger.info("Method : restGetValidateUserName ends");
		return restAdminUserDao.restGetValidateUserNameDao(userName);
	}
	
	// check validate mobile number
	
	@GetMapping(value = "rest-getValidateMobNo")
	public JsonResponse<DropDownModel> restGetValidateMobNo(@RequestParam String mobNo) {
		logger.info("Method : restGetValidateMobNo starts");
		
		logger.info("Method : restGetValidateMobNo ends");
		return restAdminUserDao.restGetValidateMobNoDao(mobNo);
	}
}
