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

import nirmalya.aatithya.restmodule.admin.dao.RestAdminAgencyDao;
import nirmalya.aatithya.restmodule.admin.dao.RestProjectPhysicalStatusDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminAgencyModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestProjectPhysicalStatusController {
	Logger logger = LoggerFactory.getLogger(RestProjectPhysicalStatusController.class);

	@Autowired
	RestProjectPhysicalStatusDao restProjectPhysicalStatusDao;

	// View Details
	@GetMapping(value = "rest-viewProjectPhysicalStatus")
	public JsonResponse<List<RestProjectPhysicalStatusModel>> ProjectPhysicalStatusDetails(@RequestParam Integer pageno,
			@RequestParam String district, @RequestParam String jeNum, @RequestParam String fromDate,
			@RequestParam String toDate) {
		logger.info("Method :ProjectPhysicalStatusDetails starts");

		logger.info("Method :ProjectPhysicalStatusDetails endss");
		return restProjectPhysicalStatusDao.viewProjectPhysicalStatusDetails(pageno,district,jeNum,fromDate,toDate);

	}
	
	
	
	@GetMapping(value = "rest-getJEName")
	public List<DropDownModel> getBlockListDropdown(@RequestParam String district) {
		logger.info("Method : getJEName starts");

		logger.info("Method : getJEName ends");
		return restProjectPhysicalStatusDao.getJENameDao(district);
	}
	
	
	
	//get jename for admin
	
	
	@GetMapping(value = "rest-getAdminJEName")
	public JsonResponse<List<DropDownModel>> getAdminJeList(@RequestParam String district) {
		logger.info("Method : getAdminJeList starts");

		logger.info("Method : getAdminJeList ends");
		return restProjectPhysicalStatusDao.getAdminJeListDao(district);
	}
	
	
	
	// delete project
	
	@RequestMapping(value = "rest-rejectProjectDetails", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> rejectprojectDetails(@RequestParam String id,@RequestParam String UserMobile) {
		logger.info("Method : rejectprojectDetails starts");

		logger.info("Method : rejectprojectDetails ends");
		return restProjectPhysicalStatusDao.rejectprojectDetailsDao(id,UserMobile);
	}
	

}
