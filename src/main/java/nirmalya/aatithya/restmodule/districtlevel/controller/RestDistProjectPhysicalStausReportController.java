package nirmalya.aatithya.restmodule.districtlevel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestAdminChangePasswordDao;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistProjectPhysicalStausReportDao;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictProjectReportModel;
@RestController
@RequestMapping(value = "districtlevel/")
public class RestDistProjectPhysicalStausReportController {
	Logger logger = LoggerFactory.getLogger(RestDistProjectPhysicalStausReportController.class);

	@Autowired
	RestDistProjectPhysicalStausReportDao restDistProjectPhysicalStausReportDao;
	
	//get project physical status report 
	
			@GetMapping(value = "restViewProjectPhyStatusLists-dist")
			public JsonResponse<List<RestDistrictProjectReportModel>> restViewPhyStatusDetailsDist(@RequestParam String pageno,
					@RequestParam String district,@RequestParam String block,@RequestParam String phyStatus) {
				//logger.info("!@#%$#@!$#@#@@#$"+userId);
				logger.info("Method :restViewPhyStatusDetailsDist starts");

				logger.info("Method :restViewPhyStatusDetailsDist endss");
				return restDistProjectPhysicalStausReportDao.restDistViewPhyStatusDetailsDao(pageno,district,block,phyStatus);

			}
}
