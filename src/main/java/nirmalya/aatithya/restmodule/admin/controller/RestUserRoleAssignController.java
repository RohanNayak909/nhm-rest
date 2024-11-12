package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminUserDao;
import nirmalya.aatithya.restmodule.admin.dao.RestUserRoleAssignDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminAgencyModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestUserRoleAssignController {
	Logger logger = LoggerFactory.getLogger(RestUserRoleAssignController.class);

	@Autowired
	RestUserRoleAssignDao restUserRoleAssignDao;

	//view user roll assign
	@GetMapping(value = "rest-viewUserRoleAssign")
	public JsonResponse<List<RestAdminUserModel>> editAgencyDetails(@RequestParam String userType) {
		logger.info("Method : getBlockLists starts");

		logger.info("Method : getBlockLists ends");
		return restUserRoleAssignDao.restViewUserRoleAssign(userType);
	}
	
	//submit role assign
	
	@RequestMapping(value = "rest-ModifyUserRoleAssign", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestAdminUserModel>> ModifyUserRoleAssign(
			@RequestBody RestAdminUserModel restAdminUserModel) {
		logger.info("Method : ModifyUserRoleAssign starts");

		logger.info("Method : ModifyUserRoleAssign ends");
		return restUserRoleAssignDao.restModifyUserRoleAssign(restAdminUserModel);
	}
	
}
