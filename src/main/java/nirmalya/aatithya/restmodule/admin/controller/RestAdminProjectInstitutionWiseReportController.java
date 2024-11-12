package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectInstitutionWiseReportDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminProjectInstitutionWiseReportController {
	Logger logger = LoggerFactory.getLogger(RestAdminProjectInstitutionWiseReportController.class);

	@Autowired
	RestAdminProjectInstitutionWiseReportDao restAdminProjectInstitutionWiseReportDao;
	
	
	// View Details institution wise projects
//	@GetMapping(value = "restProjectInstitutionWiseLists")
//	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restInstitutionWiseProjectDetails(@RequestParam String pageno,
//			@RequestParam String district,@RequestParam String block,@RequestParam String category,
//			@RequestParam String institution) {
//		//logger.info("!@#%$#@!$#@#@@#$"+userId);
//		logger.info("Method :restInstitutionWiseProjectDetails starts");
//
//		logger.info("Method :restInstitutionWiseProjectDetails endss");
//		return restAdminProjectInstitutionWiseReportDao.restInstitutionWiseProjectDetailsDao(pageno,district,block,category,institution);
//
//	}
	
	@GetMapping(value = "restProjectInstitutionWiseLists")
	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restInstitutionWiseProjectDetails(@RequestParam String pageno) {
		//logger.info("!@#%$#@!$#@#@@#$"+userId);
		logger.info("Method :restInstitutionWiseProjectDetails starts");
		
		logger.info("Method :restInstitutionWiseProjectDetails endss");
		return restAdminProjectInstitutionWiseReportDao.restInstitutionWiseProjectDetailsDao(pageno);
		
	}
	
}
