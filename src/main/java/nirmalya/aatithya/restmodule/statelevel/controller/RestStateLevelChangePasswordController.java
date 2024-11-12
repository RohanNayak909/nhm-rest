package nirmalya.aatithya.restmodule.statelevel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestChangePasswordDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.statelevel.dao.RestStateLevelChangePasswordDao;

@RestController
@RequestMapping(value = "statelevel/")
public class RestStateLevelChangePasswordController {
	Logger logger = LoggerFactory.getLogger(RestStateLevelChangePasswordController.class);

	@Autowired
	RestStateLevelChangePasswordDao restStateLevelChangePasswordDao;
		
		//update password
		@RequestMapping(value = "rest-updatePassswordState", method = { RequestMethod.GET })
		public ResponseEntity<JsonResponse<Object>> updateStatePassword(@RequestParam String id,@RequestParam String confirmpassword) {
			logger.info("Method : updateStatePassword starts"+id);

			logger.info("Method : updateStatePassword ends");
			return restStateLevelChangePasswordDao.updateStatePassword(id,confirmpassword);
		}
		
		
		//GET OLD PASSWORD
		@GetMapping(value = "rest-getOldPassword-state")
		public JsonResponse<DropDownModel> restGetOldPasswordState(@RequestParam String oldpassword) {
			logger.info("Method : restGetOldPasswordState starts");

			logger.info("Method : restGetOldPasswordState ends");
			return restStateLevelChangePasswordDao.restgetOldPasswordDetailsState(oldpassword);
		}
	
}
