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

import nirmalya.aatithya.restmodule.admin.dao.RestAdminSchemeDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminSchemeModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminSchemeController {
	Logger logger = LoggerFactory.getLogger(RestAdminSchemeController.class);

	@Autowired
	RestAdminSchemeDao restAdminSchemeDao;
	
	//view scheme
	@GetMapping(value = "rest-viewSchemeDetails")
	public JsonResponse<List<RestAdminSchemeModel>> restViewSchemeDetails(@RequestParam Integer pageno) {
		logger.info("Method :restViewSchemeDetails starts");

		logger.info("Method :restViewSchemeDetails endss");
		return restAdminSchemeDao.restViewSchemeDetails(pageno);

	}
	//edit
	@RequestMapping(value = "editSchemeDetails", method = RequestMethod.POST)
	public ResponseEntity<JsonResponse<RestAdminSchemeModel>> editSchemeDetails(@RequestBody Map<String, String> payload) {
	    logger.info("Method : editSchemeDetails rest starts");

	    String id = payload.get("id");  // Extract the 'id' from the request body

	    logger.info("Method :editSchemeDetails rest ends");
	    return restAdminSchemeDao.editSchemeDetails(id);
	}

		
		// modify
		@RequestMapping(value = "modifySchemeDetails", method = { RequestMethod.POST })
		public ResponseEntity<JsonResponse<RestAdminSchemeModel>> modifyBlockDetails(
				@RequestBody RestAdminSchemeModel adminModel) {
			logger.info("Method : modifySchemeDetails starts");

			logger.info("Method : modifySchemeDetails ends");
			return restAdminSchemeDao.modifySchemeDetails(adminModel);
		}
		
		
		// validate scheme name 
		
					@GetMapping(value = "rest-getValidateSchemeName")
					public JsonResponse<DropDownModel> restGetValidateSchemeName(@RequestParam String schemeName) {
						logger.info("Method : restGetValidateSchemeName starts");

						logger.info("Method : restGetValidateSchemeName ends");
						return restAdminSchemeDao.restGetValidateSchemeNameDao(schemeName);
					}
}
