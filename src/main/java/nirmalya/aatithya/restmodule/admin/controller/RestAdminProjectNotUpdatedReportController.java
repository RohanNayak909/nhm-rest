package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectNotUpdatedReportDao;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.controller.RestDistrictProjectUpdationReportController;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictProjectNotUpdatedReportDao;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminProjectNotUpdatedReportController {
Logger logger = LoggerFactory.getLogger(RestDistrictProjectUpdationReportController.class);
	
	@Autowired
	RestAdminProjectNotUpdatedReportDao restAdminProjectNotUpdatedReportDao;
	
	// View not updated projects Details
		@GetMapping(value = "rest-adminViewProjectNotUpdatedList")
		public JsonResponse<List<RestProjectPhysicalStatusModel>> ViewProjectNotUpdatedListAdmin(String fromDate,String toDate) {
			logger.info("Method :ViewProjectNotUpdatedListAdmin starts");

			logger.info("Method :ViewProjectNotUpdatedListAdmin endss");
			return restAdminProjectNotUpdatedReportDao.ViewProjectNotUpdatedListAdminDao(fromDate,toDate);

		}	
}
