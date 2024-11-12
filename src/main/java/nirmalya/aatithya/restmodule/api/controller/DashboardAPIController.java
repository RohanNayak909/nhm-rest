package nirmalya.aatithya.restmodule.api.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.DashboardAPIDao;
import nirmalya.aatithya.restmodule.api.model.CureEasyDashboardModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyTestListAPIModel;
import nirmalya.aatithya.restmodule.api.model.DashboardAPIModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class DashboardAPIController {
	@Autowired
	DashboardAPIDao dashboardAPIDao;
	
	@Autowired
	EnvironmentVaribles env;
	

	Logger logger = LoggerFactory.getLogger(DashboardAPIController.class);
	
	@GetMapping(value = "/get-patients-testdetails")
	public ResponseEntity<JsonResponse<List<DashboardAPIModel>>> testDetailsList(@RequestParam String userid,@RequestParam String mob,@RequestParam String name) {
		logger.info("Method : testDetailsList starts");
		
		logger.info("Method : testDetailsList ends");
		return dashboardAPIDao.testDetailsListDao(userid,mob,name);
	}
	
	@GetMapping(value = "/get-dashboard-doctor-list")
	public JsonResponse<CureEasyDashboardModel> doctorlist(@RequestParam String userId) {
		logger.info("Method :view doctorlist starts");
		logger.info("Method :view doctorlist ends");
		return dashboardAPIDao.doctorlist(userId);
	}
	
	//get most care home services

		@GetMapping(value = "/get-most-careHomeandPackageServices")
		public JsonResponse<CureEasyTestListAPIModel> getCareHomeandPackageServices() {
			logger.info("Method :view getCareHomeandPackageServices starts");
			logger.info("Method :view getCareHomeandPackageServices ends");
			return dashboardAPIDao.getCareHomeandPackageServices();
		}
}
