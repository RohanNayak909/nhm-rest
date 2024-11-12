package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectUpdateDetailsDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminBlockModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminProjectUpdateDetailsController {
	Logger logger = LoggerFactory.getLogger(RestAdminBlockController.class);

	@Autowired
	RestAdminProjectUpdateDetailsDao restAdminProjectUpdateDetailsDao;
	
	// View Details
	@GetMapping(value = "rest-projectUpdateView")
	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restViewProjectDetails(@RequestParam String pageno,
			@RequestParam String district,@RequestParam String block,@RequestParam String institution,@RequestParam String scheme,
			@RequestParam String agency,@RequestParam String sanctionYear) {
		//logger.info("!@#%$#@!$#@#@@#$"+userId);
		logger.info("Method :viewProjectUpdateDetails starts");

		logger.info("Method :viewProjectUpdateDetails endss");
		return restAdminProjectUpdateDetailsDao.restViewProjectDetails(pageno,district,block,institution,scheme,agency,sanctionYear);

	}
	
				
				
				
	//edit project details
				
				@RequestMapping(value = "projectDetailsEdit", method = { RequestMethod.GET })
				public ResponseEntity<JsonResponse<List<RestAdminProjectUpdateDetailsModel>>> restProjectEditDetails(@RequestParam String id) {
					logger.info("Method : restProjectEditDetails rest starts");

					logger.info("Method :restProjectEditDetails rest ends");
					return restAdminProjectUpdateDetailsDao.restProjectDetailsEdit(id);
				}
				
				
	// delete project
				
				@RequestMapping(value = "rest-deleteProject", method = { RequestMethod.GET })
				public ResponseEntity<JsonResponse<Object>> deleteprojectDetails(@RequestParam String projectId) {
					logger.info("Method : deleteprojectDetails starts");

					logger.info("Method : deleteprojectDetails ends");
					return restAdminProjectUpdateDetailsDao.deleteprojectDetails(projectId);
				}
				
}
