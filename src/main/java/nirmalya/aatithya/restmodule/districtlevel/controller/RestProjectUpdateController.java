package nirmalya.aatithya.restmodule.districtlevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestProjectUpdateDao;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictLevelDashboardModel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "districtlevel/")
public class RestProjectUpdateController {
	
	Logger logger = LoggerFactory.getLogger(RestProjectUpdateController.class);

	@Autowired
	RestProjectUpdateDao restProjectUpdateDao;
	
	@GetMapping(value = "rest-getBlockList")
	public List<DropDownModel> getBlockList(@RequestParam String userId) {
		logger.info("Method : getBlockList starts");

		logger.info("Method : getBlockList ends");
		return restProjectUpdateDao.getBlockList(userId);
	}
	
	
//	@GetMapping(value = "getBlockList")
//	public JsonResponse<List<DropDownModel>> getBlockList(@RequestParam Integer userId) {
//		logger.info("Method : getBlockList starts");
//
//		logger.info("Method : getBlockList ends");
//		return restProjectUpdateDao.getBlockList(userId);
//	}
	
	@GetMapping(value = "rest-getinstitude")
	public JsonResponse<List<DropDownModel>> getInstitudes(@RequestParam Integer id) {
		logger.info("Method : getInstitudes starts");
		
		logger.info("Method : getInstitudes ends");
		return restProjectUpdateDao.getInstitudesDao(id);
	}
	
	
	@GetMapping(value = "rest-getCategoryDist")
	public JsonResponse<List<DropDownModel>> getCatagorys(@RequestParam Integer id) {
		logger.info("Method : getCatagorys starts");

		logger.info("Method : getCatagorys ends");
		return restProjectUpdateDao.getCatagoryDistDao(id);
	}
	
	
	
	@GetMapping(value = "getSchemeList")
	public List<DropDownModel> getSchemeList() {
		logger.info("Method : getSchemeList starts");

		logger.info("Method : getSchemeList ends");
		return restProjectUpdateDao.getSchemeList();
	}
	
	//get agency list
	@GetMapping(value = "getAgencyList")
	public List<DropDownModel> getAgencyList() {
		logger.info("Method : getAgencyList starts");
		
		logger.info("Method : getAgencyList ends");
		return restProjectUpdateDao.getAgencyList();
	}
	
	
	@GetMapping(value = "getPhysicalStatusList")
	public List<DropDownModel> getPhysicalStatusList() {
		logger.info("Method : getPhysicalStatusList starts");
		
		logger.info("Method : getPhysicalStatusList ends");
		return restProjectUpdateDao.getPhysicalStatusList();
	}
	
//submit project update
	@RequestMapping(value = "addProjectUpdateDetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestDistrictLevelDashboardModel>> addProjectUpdate(
			@RequestBody RestDistrictLevelDashboardModel restDistrictNhmModel) {
		logger.info("Method : addProjectUpdate starts");

		logger.info("Method : addProjectUpdate ends");
		return restProjectUpdateDao.addProjectUpdate(restDistrictNhmModel);
	}
	
	
	//check sanction year
	@GetMapping(value = "rest-getSanctionYear")
	public JsonResponse<DropDownModel> getMonthsYear(@RequestParam String id,@RequestParam String blockidds,@RequestParam String userId) {
		logger.info("Method : getMonthsYear starts");

		logger.info("Method : getMonthsYear ends");
		return restProjectUpdateDao.restGetSanctionYear(id,blockidds,userId);
	}
}
