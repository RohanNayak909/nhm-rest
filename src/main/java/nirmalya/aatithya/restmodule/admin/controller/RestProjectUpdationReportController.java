package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestProjectPhysicalStatusDao;
import nirmalya.aatithya.restmodule.admin.dao.RestProjectUpdationReportDao;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectUpdationReportModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestProjectUpdationReportController {
	Logger logger = LoggerFactory.getLogger(RestProjectUpdationReportController.class);

	@Autowired
	RestProjectUpdationReportDao restProjectUpdationReportDao;

	
	// get JE name
	
	@GetMapping(value = "rest-getBlockJEName")
	public JsonResponse<List<DropDownModel>> getBlockJEName(@RequestParam String district,@RequestParam String block) {
		logger.info("Method : getAdminJeList starts");

		logger.info("Method : getAdminJeList ends");
		return restProjectUpdationReportDao.getJeListDao(district,block);
	}
	
	//project updation report
	
	@GetMapping(value = "rest-viewProjectUpdationReport")
	public JsonResponse<List<RestProjectUpdationReportModel>> projectUpdationReportView(@RequestParam String fromDate,@RequestParam String toDate) {
		logger.info("Method :projectUpdationReportView starts");

		logger.info("Method :projectUpdationReportView endss");
		return restProjectUpdationReportDao.projectUpdationReportViewDao(fromDate,toDate);

	}
}
