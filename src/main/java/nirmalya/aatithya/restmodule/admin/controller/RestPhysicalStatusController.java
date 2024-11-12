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

import nirmalya.aatithya.restmodule.admin.dao.RestPhysicalStatusDao;
import nirmalya.aatithya.restmodule.admin.model.RestPhysicalStatusModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestPhysicalStatusController {
	Logger logger = LoggerFactory.getLogger(RestPhysicalStatusController.class);

	@Autowired
	RestPhysicalStatusDao restPhysicalStatusDao;
	
	// View Details
			@GetMapping(value = "rest-viewPhysicalDetails")
			public JsonResponse<List<RestPhysicalStatusModel>> viewPhysicalDetails(@RequestParam Integer pageno)
 {
				logger.info("Method :viewPhysicalDetails starts");

				logger.info("Method :viewPhysicalDetails endss");
				return restPhysicalStatusDao.viewPhysicalDetails(pageno);

			}
			//edit
			@RequestMapping(value = "editPhysicalStatusDetails", method = RequestMethod.POST)
			public ResponseEntity<JsonResponse<RestPhysicalStatusModel>> editPhysicalStatusDetails(@RequestBody Map<String, String> payload) {
			    logger.info("Method : editPhysicalStatusDetails rest starts");

			    String id = payload.get("id");  // Extract id from the request body

			    logger.info("Method : editPhysicalStatusDetails rest ends");
			    return restPhysicalStatusDao.editPhysicalStatusDetails(id);
			}


			
			// modify
			@RequestMapping(value = "modifyPhysicalStatusDetails", method = { RequestMethod.POST })
			public ResponseEntity<JsonResponse<RestPhysicalStatusModel>> modifyPhysicalStatusDetails(
					@RequestBody RestPhysicalStatusModel adminModel) {
				logger.info("Method : modifyPhysicalStatusDetails starts");

				logger.info("Method : modifyPhysicalStatusDetails ends");
				return restPhysicalStatusDao.modifyPhysicalStatusDetails(adminModel);
			}
			
			
			// validate physical status name 
			
			@GetMapping(value = "rest-getValidatePhyStatusName")
			public JsonResponse<DropDownModel> getValidatePhyStatusName(@RequestParam String physicalName) {
				logger.info("Method : getValidatePhyStatusName starts");

				logger.info("Method : getValidatePhyStatusName ends");
				return restPhysicalStatusDao.restgetValidatePhyStatusNameDao(physicalName);
			}

}
