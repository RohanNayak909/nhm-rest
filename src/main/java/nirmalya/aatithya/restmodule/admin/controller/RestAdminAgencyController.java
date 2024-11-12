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

import nirmalya.aatithya.restmodule.admin.dao.RestAdminAgencyDao;
import nirmalya.aatithya.restmodule.admin.dao.RestAdminDistrictDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminAgencyModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminAgencyController {
	Logger logger = LoggerFactory.getLogger(RestAdminAgencyController.class);

	@Autowired
	RestAdminAgencyDao restAdminAgencyDao;
	  
	
	// View Details
			@GetMapping(value = "rest-viewAgencyDetails")
			public JsonResponse<List<RestAdminAgencyModel>> viewAgencyDetails(@RequestParam Integer pageno)
 {
				logger.info("Method :viewAgencyDetails starts");

				logger.info("Method :viewAgencyDetails endss");
				return restAdminAgencyDao.viewAgencyDetails(pageno);

			}
			 
			//edit
			@RequestMapping(value = "editAgencyDetails", method = RequestMethod.POST)
			public ResponseEntity<JsonResponse<RestAdminAgencyModel>> editAgencyDetails(@RequestBody Map<String, String> payload) {
			    logger.info("Method : editAgencyDetails rest starts");

			    String id = payload.get("id");  // Get id from request body

			    logger.info("Method : editAgencyDetails rest ends");
			    return restAdminAgencyDao.editAgencyDetails(id);
			}

			
			// modify
			@RequestMapping(value = "modifyAgencyDetails", method = { RequestMethod.POST })
			public ResponseEntity<JsonResponse<RestAdminAgencyModel>> modifyAgencyDetails(
					@RequestBody RestAdminAgencyModel adminModel) {
				logger.info("Method : modifyAgencyDetails starts");

				logger.info("Method : modifyAgencyDetails ends");
				return restAdminAgencyDao.modifyAgencyDetails(adminModel);
			}
			
			
			// validate agency name
			
			@GetMapping(value = "rest-getValidateAgencyName")
			public JsonResponse<DropDownModel> restGetValidateAgencyName(@RequestParam String agencyName) {
				logger.info("Method : restGetValidateAgencyName starts");

				logger.info("Method : restGetValidateAgencyName ends");
				return restAdminAgencyDao.restGetValidateAgencyNameDao(agencyName);
			} 


}
