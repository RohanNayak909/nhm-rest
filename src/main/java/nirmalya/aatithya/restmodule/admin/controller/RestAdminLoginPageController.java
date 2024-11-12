package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminBlockDao;
import nirmalya.aatithya.restmodule.admin.dao.RestRestAdminLoginPageDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDashboardModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminLoginPageController {
	Logger logger = LoggerFactory.getLogger(RestAdminLoginPageController.class);

	@Autowired
	RestRestAdminLoginPageDao restRestAdminLoginPageDao;
	
	
	@GetMapping(value = "rest-getAdminloginPgeDetails")
	public JsonResponse<List<DropDownModel>> getAdminloginCountDetails() {
		logger.info("Method :getAdminloginCountDetails starts");

		logger.info("Method :getAdminloginCountDetails endss");
		return restRestAdminLoginPageDao.restGetAdminCountDetails();
}
	
	
	@GetMapping(value = "rest-loginPieChart")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> restProjectDetails(){
			
		
		logger.info("Method : restProjectDetails starts");

		logger.info("Method :restProjectDetails ends");
		return restRestAdminLoginPageDao.restLoginPieChart();
	}
	
	
	
	@GetMapping(value = "rest-loginProjects")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> loginProjects(){
			
		
		logger.info("Method : loginProjects starts");

		logger.info("Method :loginProjects ends");
		return restRestAdminLoginPageDao.loginProjects();
	}
	
	@GetMapping(value = "rest-projectAgencyCount")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> projectAgencyCounts(String district,String block){
		
		
		logger.info("Method : projectAgencyCounts starts");
		
		logger.info("Method :projectAgencyCounts ends");
		return restRestAdminLoginPageDao.projectAgencyCountDao(district,block);
	}
	@GetMapping(value = "rest-projectSchemeCount")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> projectSchemeCounts(String district,String block){
		
		
		logger.info("Method : projectSchemeCounts starts");
		
		logger.info("Method :projectSchemeCounts ends");
		return restRestAdminLoginPageDao.projectSchemeCountDao(district,block);
	}
	
}
