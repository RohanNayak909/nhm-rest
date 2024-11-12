package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;
import java.util.Map;

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

import nirmalya.aatithya.restmodule.admin.dao.RestAdminInstitutionDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminBlockModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminInstitutionModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminInstitutionController {
	
	Logger logger = LoggerFactory.getLogger(RestAdminInstitutionController.class);

	@Autowired
	RestAdminInstitutionDao restAdminInstitutionDao;
	
	// View Details
	@GetMapping(value = "rest-viewInstitutionDetails")
	public JsonResponse<List<RestAdminInstitutionModel>> viewInstitutionDetails(@RequestParam Integer pageno,
			@RequestParam String district,@RequestParam String block) {
		logger.info("Method :viewInstitutionDetails starts");

		logger.info("Method :viewInstitutionDetails endss");
		return restAdminInstitutionDao.viewInstitutionDetails(pageno,district,block);

	}
	
	//edit
	@RequestMapping(value = "editInstitutionDetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestAdminInstitutionModel>> editInstitutionDetails(@RequestBody Map<String, String> payload) {
	    logger.info("Method : editInstitutionDetails rest starts");
	    String id = payload.get("id");
	     
	    ResponseEntity<JsonResponse<RestAdminInstitutionModel>> response = restAdminInstitutionDao.editInstitutionDetails(id);
	    
	    logger.info("Method : editInstitutionDetails rest ends");
	    return response;
	}

		
		// modify 
		@RequestMapping(value = "modifyInstitutionDetails", method = { RequestMethod.POST })
		public ResponseEntity<JsonResponse<RestAdminInstitutionModel>> modifyInstitutionDetails(
				@RequestBody RestAdminInstitutionModel adminModel) {
			logger.info("Method : modifyInstitutionDetails starts");

			logger.info("Method : modifyInstitutionDetails ends");
			return restAdminInstitutionDao.modifyInstitutionDetails(adminModel);
		}
		
		
		//for block list edit
		@PostMapping(value = "rest-getBlockListEditInstList")
		public JsonResponse<List<DropDownModel>> getBlockListEdit(@RequestBody Map<String, Object> payload) {
		    Integer id = null;
		    String districtStr = payload.get("district").toString(); // Get district as String
		    

		    try {
		        // Parse the district string into an integer
		        id = Integer.parseInt(districtStr);
		    } catch (NumberFormatException e) {
		        // Handle parsing error, log it, and return an error response
		        logger.error("Invalid district ID format", e);
		        JsonResponse<List<DropDownModel>> errorResponse = new JsonResponse<>();
		        errorResponse.setMessage("Invalid district ID format");
		        return errorResponse;
		    }

		    logger.info("Method : getBlockLists starts, District: " + id );

		    return restAdminInstitutionDao.getBlockListEditInstListDao(id);
		}

			
}
