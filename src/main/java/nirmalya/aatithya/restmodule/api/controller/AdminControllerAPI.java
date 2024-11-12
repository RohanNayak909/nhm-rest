package nirmalya.aatithya.restmodule.api.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nirmalya.aatithya.restmodule.api.dao.AdminDaoAPI;
import nirmalya.aatithya.restmodule.api.model.APIAdminModel;
import nirmalya.aatithya.restmodule.api.model.VleTestAPIModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.util.DocumentUpload;
import nirmalya.aatithya.restmodule.util.Util;

@RestController
@RequestMapping(value = "api")
public class AdminControllerAPI {

	@Autowired
	AdminDaoAPI adminDaoAPI;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	DocumentUpload documentUpload;

	Logger logger = LoggerFactory.getLogger(AdminControllerAPI.class);

 
	

	@GetMapping(value = "/get-banner-details-api")
	public JsonResponse<List<DropDownModel>> getBannerDetailsApi() {
		logger.info("Method : getBannerDetailsApi starts");
		logger.info("Method : getBannerDetailsApi endss");
		return adminDaoAPI.getBannerDetailsApi();
	}

	@GetMapping(value = "/get-banner-doctor-api")
	public JsonResponse<List<DropDownModel>> getDoctorBannerDetailsApi() {
		logger.info("Method : getDoctorBannerDetailsApi starts");
		logger.info("Method : getDoctorBannerDetailsApi endss");
		return adminDaoAPI.getDoctorBannerDetailsApi();
	}
	/*
	 * @GetMapping(value = "/get-banner-details-api") public
	 * JsonResponse<List<VleTestAPIModel>> viewVleVenderTestList(@RequestParam
	 * String patientId) { logger.info("Method : viewVleVenderTestList starts");
	 * 
	 * logger.info("Method : viewVleVenderTestList ends");
	 * 
	 * return vleTestAPIDao.viewVleVenderTestListDao(patientId); }
	 */
	
	/* Forgot Password - Change Password */
	@PostMapping(value = "/doctor-change-password")
	public ResponseEntity<JsonResponse<Object>> changePassword(@RequestBody DropDownModel data) {
		logger.info("Method : changePassword starts");

		logger.info("Method : changePassword ends");
		return adminDaoAPI.changePassword(data);
	}
	


 
}
