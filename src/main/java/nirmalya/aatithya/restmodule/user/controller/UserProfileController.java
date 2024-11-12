package nirmalya.aatithya.restmodule.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.controller.CureeazyDoctorConsultationController;
import nirmalya.aatithya.restmodule.api.dao.CureeazyDoctorConsultationDao;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.UserProfileDao;
import nirmalya.aatithya.restmodule.user.model.UserProfileModel;
import nirmalya.aatithya.restmodule.util.DocumentUpload;

@RestController
@RequestMapping(value = "api")
public class UserProfileController {
	
	@Autowired
	EnvironmentVaribles env;

	@Autowired
	DocumentUpload documentUpload;
	
	@Autowired
	UserProfileDao userProfileDao;
	
	Logger logger = LoggerFactory.getLogger(UserProfileController.class);
	 //get user profile
	@GetMapping(value = "/get-user-profile")
	public ResponseEntity<JsonResponse<UserProfileModel>> getUserProfile(@RequestParam String userId) {
		logger.info("Method :view getUserProfile starts");
		logger.info("Method :view getUserProfile ends");
		return userProfileDao.getUserProfile(userId);
}

}
