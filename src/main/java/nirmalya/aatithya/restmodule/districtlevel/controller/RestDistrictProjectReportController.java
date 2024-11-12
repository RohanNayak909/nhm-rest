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
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictProjectReportDao;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictProjectReportModel;

@RestController
@RequestMapping(value = "districtlevel/")
public class RestDistrictProjectReportController {
	Logger logger = LoggerFactory.getLogger(RestProjectUpdateController.class);

	@Autowired
	RestDistrictProjectReportDao restDistrictProjectReportDao;
	
	
	@GetMapping(value = "getDistrictList")
	public List<DropDownModel> getDistrictList() {
		logger.info("Method : getDistrictList starts");

		logger.info("Method : getDistrictList ends");
		return restDistrictProjectReportDao.getDistrictList();
	}
	
	
	
	@GetMapping(value = "rest-getBlockListDist")
	public JsonResponse<List<DropDownModel>> getDistBlockList(@RequestParam Integer id) {
		logger.info("Method : getDistBlockList starts");

		logger.info("Method : getDistBlockList ends");
		return restDistrictProjectReportDao.getDistBlockListDao(id);
	}
	
	
	@GetMapping(value = "rest-getInstitutionListsDist")
	public JsonResponse<List<DropDownModel>> getInstitutionListsDist(@RequestParam Integer id) {
		logger.info("Method : getInstitutionListsDist starts");

		logger.info("Method : getInstitutionListsDist ends");
		return restDistrictProjectReportDao.getInstitutionListsDistDao(id);
	}

	
	
	@GetMapping(value = "rest-DistProjectReport")
	public JsonResponse<List<RestDistrictProjectReportModel>> restProjectStatusUpdate(@RequestParam String block,
			@RequestParam String institution, @RequestParam String userId) {
		logger.info("Method :restProjectStatusUpdatesDist starts");

		logger.info("Method :restProjectStatusUpdateDist endss");
		return restDistrictProjectReportDao.restDistProjectStatusUpdate(block,institution,userId);

	}
}
