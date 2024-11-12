package nirmalya.aatithya.restmodule.blocklevel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.blocklevel.dao.RestBlockAdminChangePasswordDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestAdminChangePasswordDao;

@RestController
@RequestMapping(value = "blocklevel/")
public class RestBlockAdminChangePasswordController {

	Logger logger = LoggerFactory.getLogger(RestBlockAdminChangePasswordController.class);

	@Autowired
	RestBlockAdminChangePasswordDao restAdminChangePasswordDao;
	
	/** Change Password **/
	//update district password
		@RequestMapping(value = "rest-updateBlockPasssword", method = { RequestMethod.GET })
		public ResponseEntity<JsonResponse<Object>> updateBlockPassword(@RequestParam String id,@RequestParam String confirmpassword) {
			logger.info("Method : updateBlockPassword starts");

			logger.info("Method : updateBlockPassword ends");
			return restAdminChangePasswordDao.updateBlockPassword(id,confirmpassword);
		}
		
		@GetMapping(value = "rest-getOldPassword")
		public JsonResponse<DropDownModel> getMonthsYear(@RequestParam String oldpassword) {
			logger.info("Method : restGetOldPassword starts");

			logger.info("Method : restGetOldPassword ends");
			return restAdminChangePasswordDao.restgetOldPasswordDetails(oldpassword);
		}
	
}
