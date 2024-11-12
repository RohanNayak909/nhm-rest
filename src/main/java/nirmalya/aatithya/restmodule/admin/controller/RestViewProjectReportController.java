package nirmalya.aatithya.restmodule.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestViewProjectReportDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "admin/")
public class RestViewProjectReportController {

	Logger logger = LoggerFactory.getLogger(RestViewProjectReportController.class);

	@Autowired
	RestViewProjectReportDao restViewProjectReportDao;
	 
	
//	@GetMapping("rest-ProjectDetails-Pdf")
//	public JsonResponse<List<RestProjectPhysicalStatusModel>> ProjectReportDeatailsDao(@RequestParam String id) {
//	    logger.info("Method : ProjectReportDeatailsDao starts");
//
//	    JsonResponse<List<RestProjectPhysicalStatusModel>> resp = restViewProjectReportDao.ProjectReportDeatailsDao(id);
//
//	    logger.info("Method : ProjectReportDeatailsDao ends");
//	    return resp;
//	}
	
	
	
	@PostMapping("rest-ProjectDetails-Pdf")
		
		public ResponseEntity<JsonResponse<List<RestProjectPhysicalStatusModel>>> ProjectReportDeatailsDao(@RequestBody Map<String, String> requestBody) {
		
	    String id = requestBody.get("id");
	    logger.info("Method : ProjectReportDeatailsDao starts with ID: " + id);


	    logger.info("Method : ProjectReportDeatailsDao ends"+restViewProjectReportDao.ProjectReportDeatailsDao(id));
	    return restViewProjectReportDao.ProjectReportDeatailsDao(id);
	}
	
	
	
	



}
