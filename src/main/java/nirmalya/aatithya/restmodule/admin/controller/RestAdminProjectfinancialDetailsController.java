package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectfinancialDetailsDao;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminProjectfinancialDetailsController {
	Logger logger = LoggerFactory.getLogger(RestProjectPhysicalStatusController.class);

	@Autowired
	RestAdminProjectfinancialDetailsDao restAdminProjectfinancialDetailsDao;
	
	// View Details
	@GetMapping(value = "rest-viewProjectFinancialStatus")
	public JsonResponse<List<RestProjectPhysicalStatusModel>> ProjectFinancialStatusDetails(@RequestParam Integer pageno,
			@RequestParam String district,@RequestParam String jeNum) {
		logger.info("Method :ProjectFinancialStatusDetails starts");

		logger.info("Method :ProjectFinancialStatusDetails endss");
		return restAdminProjectfinancialDetailsDao.viewProjectFinancialStatusDetails(pageno,district,jeNum);

	}	
	
	
}
