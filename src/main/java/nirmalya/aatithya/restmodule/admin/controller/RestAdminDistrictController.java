package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;
import java.util.Map;

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

import nirmalya.aatithya.restmodule.admin.dao.RestAdminDistrictDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDistrictModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminDistrictController {
	Logger logger = LoggerFactory.getLogger(RestAdminDistrictController.class);

	@Autowired
	RestAdminDistrictDao restAdminDistrictDao;
	
	//view district
	@GetMapping(value = "rest-viewDistrictDetails")
	public JsonResponse<List<RestAdminDistrictModel>> viewDistrictDetails(@RequestParam Integer pageno) {
		logger.info("Method :viewDistrictDetails starts");

		logger.info("Method :viewDistrictDetails endss");
		return restAdminDistrictDao.viewDistrictDetails(pageno);

	}
	// edit

	@RequestMapping(value = "editDistrictDetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestAdminDistrictModel>> editDistrictDetails(@RequestBody Map<String, String> requestBody) {
	    String id = requestBody.get("id");
	    return restAdminDistrictDao.editDistrictDetails(id);
	}

			// modify
			@RequestMapping(value = "modifyDistrictDetails", method = { RequestMethod.POST })
			public ResponseEntity<JsonResponse<RestAdminDistrictModel>> modifyDistrictDetails(
					@RequestBody RestAdminDistrictModel adminModel) {
				logger.info("Method : addCityDetails starts");

				logger.info("Method : addCityDetails ends");
				return restAdminDistrictDao.addDistrictDetailsDao(adminModel);
			}
// add audit details when login
			@RequestMapping(value = "rest-saveAuditDetails", method = { RequestMethod.POST })
			public ResponseEntity<JsonResponse<RestAdminUserModel>> saveAuditDetails(
					@RequestBody RestAdminUserModel adminModel) {
				logger.info("Method : saveAuditDetails starts");
				
				logger.info("Method : saveAuditDetails ends");
				return restAdminDistrictDao.saveAuditDetailsDao(adminModel);
			}
			
// add audit details when logout
			@RequestMapping(value = "rest-saveAuditDetails-logout", method = { RequestMethod.POST })
			public ResponseEntity<JsonResponse<RestAdminUserModel>> saveAuditDetailsLogout(
					@RequestBody RestAdminUserModel adminModel) {
				logger.info("Method : saveAuditDetailsLogout starts");
				
				logger.info("Method : saveAuditDetailsLogout ends");
				return restAdminDistrictDao.saveAuditDetailsLogoutDao(adminModel);
			}
			
}
