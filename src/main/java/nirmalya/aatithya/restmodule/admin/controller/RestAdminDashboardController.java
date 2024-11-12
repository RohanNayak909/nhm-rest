package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminDashboadDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDashboardModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminDashboardController {
	Logger logger = LoggerFactory.getLogger(RestAdminDashboardController.class);

	@Autowired
	RestAdminDashboadDao restAdminDashboadDao;
	
	
	//dashboard count details
	
	@GetMapping(value = "rest-getAdminCountDetails")
	public JsonResponse<List<RestAdminDashboardModel>> getAdminCountDetails(@RequestParam String district,@RequestParam String block) {
		logger.info("Method :getAdminCountDetails starts");

		logger.info("Method :getAdminCountDetails endss");
		return restAdminDashboadDao.restGetAdminCountDetails(district,block);
}
	
	//
	//
	@GetMapping(value = "rest-adminPieChart")
	public ResponseEntity<JsonResponse<List<RestAdminDashboardModel>>> restProjectDetails(@RequestParam String district, @RequestParam String block){
			
		
		logger.info("Method : restProjectDetails starts");

		logger.info("Method :restProjectDetails ends");
		return restAdminDashboadDao.restProjectDetails(district,block);
	}
	
	
	//admin dashboard bar chart
	@GetMapping(value = "rest-barchartDetails")
	public ResponseEntity<JsonResponse<List<RestAdminDashboardModel>>> restAdminBarchart(@RequestParam String district,@RequestParam String block){
			
		
		logger.info("Method : restAdminBarchart starts");

		logger.info("Method :restAdminBarchart ends");
		return restAdminDashboadDao.restAdminBarchart(district,block);
	}
	
	
	@GetMapping(value = "getDistrictsList")
	public List<DropDownModel> getDistrictsList() {
		logger.info("Method : getDistrictsList starts");

		logger.info("Method : getDistrictsList ends");
		return restAdminDashboadDao.getDistrictsList();
	}
	
	 
	//get block list
	
	@GetMapping(value = "rest-getBlockListDropdown")
	public JsonResponse<List<DropDownModel>> getBlockListDropdown(@RequestParam Integer id) {
		logger.info("Method : getBlockListDropdown starts");

		logger.info("Method : getBlockListDropdown ends");
		return restAdminDashboadDao.getBlockListDropdownDao(id);
	}
	
	
}
