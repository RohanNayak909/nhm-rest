package nirmalya.aatithya.restmodule.districtlevel.controller;

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

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestAdminChangePasswordDao;

@RestController
@RequestMapping(value = "districtlevel/")
public class RestAdminChangePasswordController {
	
	Logger logger = LoggerFactory.getLogger(RestAdminChangePasswordController.class);

	@Autowired
	RestAdminChangePasswordDao restAdminChangePasswordDao;
	
	
	/** Change Password **/
	//update district password
		@RequestMapping(value = "rest-updateDistPasssword", method = { RequestMethod.GET })
		public ResponseEntity<JsonResponse<Object>> updateDistPassword(@RequestParam String id,@RequestParam String confirmpassword) {
			logger.info("Method : updateDistPassword starts");

			logger.info("Method : updateDistPassword ends");
			return restAdminChangePasswordDao.updateDistPassword(id,confirmpassword);
		}
		
		@GetMapping(value = "rest-getOldPassword")
		public JsonResponse<DropDownModel> getMonthsYear(@RequestParam String oldpassword) {
			logger.info("Method : restGetOldPassword starts");

			logger.info("Method : restGetOldPassword ends");
			return restAdminChangePasswordDao.restgetOldPasswordDetails(oldpassword);
		}

}
