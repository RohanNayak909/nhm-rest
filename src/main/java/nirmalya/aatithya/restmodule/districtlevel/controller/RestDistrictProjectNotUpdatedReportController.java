package nirmalya.aatithya.restmodule.districtlevel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictProjectNotUpdatedReportDao;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictProjectUpdationReportDao;

@RestController
@RequestMapping(value = "districtlevel/")
public class RestDistrictProjectNotUpdatedReportController {
Logger logger = LoggerFactory.getLogger(RestDistrictProjectUpdationReportController.class);
	
	@Autowired
	RestDistrictProjectNotUpdatedReportDao restDistrictProjectNotUpdatedReportDao;
	
	// View not updated projects Details
		@GetMapping(value = "rest-viewProjectNotUpdatedList")
		public JsonResponse<List<RestProjectPhysicalStatusModel>> ViewProjectNotUpdatedList(
				@RequestParam String district) {
			logger.info("Method :ViewProjectNotUpdatedList starts");

			logger.info("Method :ViewProjectNotUpdatedList endss");
			return restDistrictProjectNotUpdatedReportDao.ViewProjectNotUpdatedListDao(district);

		}	
	
}
