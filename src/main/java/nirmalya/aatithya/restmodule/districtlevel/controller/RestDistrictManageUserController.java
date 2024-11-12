package nirmalya.aatithya.restmodule.districtlevel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictManageUserDao;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestProjectUpdateDao;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistUserModel;

@RestController
@RequestMapping(value = "districtlevel/")
public class RestDistrictManageUserController {
	Logger logger = LoggerFactory.getLogger(RestDistrictManageUserController.class);

	@Autowired
	RestDistrictManageUserDao restDistrictManageUserDao;
	
	
	//view District user details
	
	@GetMapping(value = "rest-viewUserDetailsDist")
	public JsonResponse<List<RestDistUserModel>> restViewUserDistDetails(@RequestParam Integer pageno,
			@RequestParam String userId) {
		logger.info("Method :restViewUserDistDetails start");

		logger.info("Method :restViewUserDistDetails ends");
		return restDistrictManageUserDao.restViewUserDistDetails(pageno,userId);

	}
	
	
	//edit district user
	
	@GetMapping(value = "rest-editUserDetails-District")
	public ResponseEntity<JsonResponse<List<RestDistUserModel>>> editUserDetailsDist(@RequestParam String id,
			@RequestParam String mob,@RequestParam String userId) {
		logger.info("Method :editUserDetailsDist starts");

		logger.info("Method :editUserDetailsDist ends" + id);
		logger.info("Method :editUserDetailsDist ends" + mob);
		logger.info("Method :editUserDetailsDist ends" + userId);
		return restDistrictManageUserDao.editUserDetailsDist(id,mob,userId);

	}
	
}
