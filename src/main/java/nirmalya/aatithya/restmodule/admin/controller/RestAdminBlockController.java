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

import nirmalya.aatithya.restmodule.admin.dao.RestAdminBlockDao;
import nirmalya.aatithya.restmodule.admin.dao.RestAdminDistrictDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminBlockModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDistrictModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminBlockController {
	Logger logger = LoggerFactory.getLogger(RestAdminBlockController.class);

	@Autowired
	RestAdminBlockDao restAdminBlockDao;
	
	
	// View Details
			@GetMapping(value = "rest-viewBlockDetails")
			public JsonResponse<List<RestAdminBlockModel>> viewBlockDetails(@RequestParam Integer pageno,
					@RequestParam String district) {
				logger.info("Method :viewBlockDetails starts");

				logger.info("Method :viewBlockDetails endss");
				return restAdminBlockDao.viewBlockDetails(pageno,district);

			}
			//edit
			@RequestMapping(value = "editBlockDetails", method = { RequestMethod.POST })
			public ResponseEntity<JsonResponse<RestAdminBlockModel>> editBlockDetails(@RequestBody Map<String, String> requestBody) {
			    logger.info("Method : editBlockDetails rest starts");

			    String id = requestBody.get("id");
			    logger.info("Received ID: " + id);

			    logger.info("Method : editBlockDetails rest ends");
			    return restAdminBlockDao.editBlockDetails(id);
			}

			
			// modify
			@RequestMapping(value = "modifyBlockDetails", method = { RequestMethod.POST })
			public ResponseEntity<JsonResponse<RestAdminBlockModel>> modifyBlockDetails(
					@RequestBody RestAdminBlockModel adminModel) {
				logger.info("Method : modifyBlockDetails starts");

				logger.info("Method : modifyBlockDetails ends"); 
				return restAdminBlockDao.modifyBlockDetails(adminModel);
			}

}
