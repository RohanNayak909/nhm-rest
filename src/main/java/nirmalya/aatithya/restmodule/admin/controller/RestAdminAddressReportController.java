package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminAddressReportDao;
import nirmalya.aatithya.restmodule.admin.dao.RestProjectPhysicalStatusDao;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminAddressReportController {
	
	Logger logger = LoggerFactory.getLogger(RestAdminAddressReportController.class);

	@Autowired
	RestAdminAddressReportDao restAdminAddressReportDao;
	
	
	// View Details
	@GetMapping(value = "rest-viewAddressDetails")
	public JsonResponse<List<RestProjectPhysicalStatusModel>> ProjectAddressDetails(@RequestParam Integer pageno,
			@RequestParam String district, @RequestParam String jeNum) {
		logger.info("Method :ProjectAddressDetails starts");

		logger.info("Method :ProjectAddressDetails endss");
		return restAdminAddressReportDao.ProjectAddressDetailsDao(pageno,district,jeNum);

	}

}
