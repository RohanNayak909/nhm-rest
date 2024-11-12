package nirmalya.aatithya.restmodule.districtlevel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.model.RestProjectUpdationReportModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestAdminChangePasswordDao;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictProjectUpdationReportDao;

@RestController
@RequestMapping(value = "districtlevel/")
public class RestDistrictProjectUpdationReportController {
	

	Logger logger = LoggerFactory.getLogger(RestDistrictProjectUpdationReportController.class);
	
	@Autowired
	RestDistrictProjectUpdationReportDao restDistrictProjectUpdationReportDao;
	
	
	@GetMapping(value = "rest-getBlockJEName")
	public JsonResponse<List<DropDownModel>> getBlockJEName(@RequestParam String block) {
		logger.info("Method : getAdminJeList starts");

		logger.info("Method : getAdminJeList ends");
		return restDistrictProjectUpdationReportDao.getJeListDao(block);
	}
	
	
	//project updation report
	
		@GetMapping(value = "rest-projectUpdationReport")
		public JsonResponse<List<RestProjectUpdationReportModel>> distProjectUpdationReportView(@RequestParam Integer pageno,
				@RequestParam String district) {
			logger.info("Method :distProjectUpdationReportView starts");

			logger.info("Method :distProjectUpdationReportView endss");
			return restDistrictProjectUpdationReportDao.distProjectUpdationReportView(pageno,district);

		}
	
	
	
	
}
