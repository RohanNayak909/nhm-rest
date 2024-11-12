package nirmalya.aatithya.restmodule.districtlevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.DistrictDashboardDao;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictLevelDashboardModel;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictProjectReportModel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value = "districtlevel/")
public class RestDistrictDashboardController {
	Logger logger = LoggerFactory.getLogger(RestDistrictDashboardController.class);

	@Autowired
	DistrictDashboardDao districtDashboardDao;
	// district count deatails
	@GetMapping(value = "getDistrictCountDetails")
	public JsonResponse<List<RestDistrictLevelDashboardModel>> restGetDistrictCountDetails(@RequestParam String block,@RequestParam String userId) {
		logger.info("Method :restGetDistrictCountDetails starts");

		logger.info("Method :restGetDistrictCountDetails endss");
		return districtDashboardDao.restGetDistrictCountDetails(block,userId);

	}
	
	//
	@RequestMapping(value = "districtPiachart", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestDistrictLevelDashboardModel>>> editCategoryDetails(@RequestParam String block,@RequestParam String userId) {
	logger.info("Method : districtPiachart starts");

	logger.info("Method :districtPiachart ends");
	return districtDashboardDao.districtPiachart(block,userId);
}

	//
	
	@RequestMapping(value = "districtBarchart", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestDistrictLevelDashboardModel>>> districtBarchart(@RequestParam String block,@RequestParam String userId) {
	logger.info("Method : districtBarchart starts");

	logger.info("Method :districtBarchart ends");
	return districtDashboardDao.districtBarchart(block,userId);
}
	
	@GetMapping(value = "rest-getDashboardBlockList")
	public List<DropDownModel> getBlockList(@RequestParam String userId) {
		logger.info("Method : getDashboardBlockList starts");

		logger.info("Method : getDashboardBlockList ends");
		return districtDashboardDao.getDashboardBlockList(userId);
	}


}
