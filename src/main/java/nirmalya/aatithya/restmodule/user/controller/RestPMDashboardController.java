package nirmalya.aatithya.restmodule.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.RestPMDashboardDao;
import nirmalya.aatithya.restmodule.user.model.DmDashboardRestModel;
import nirmalya.aatithya.restmodule.user.model.RestPMDashboardModel;
@RestController
@RequestMapping("user/")
public class RestPMDashboardController {
	Logger logger = LoggerFactory.getLogger(RestPMDashboardController.class);

	@Autowired

	RestPMDashboardDao restPMDashboardDao;
	/**
	 *
	 * @return Appointment Count
	 */
	
	@GetMapping(value = "rest-viewpmDashboardCount")
	public ResponseEntity<JsonResponse<RestPMDashboardModel>> viewpmDashboardCount(@RequestParam String countryId) {
		logger.info("Method : viewpmDashboardCount starts");

		logger.info("Method :viewpmDashboardCount ends");
		return restPMDashboardDao.viewpmDashboardCount(countryId);
	}
	
	/*
	 * for State list id
	 */
	

	@RequestMapping(value = "rest-pmDashboardgetId", method = { RequestMethod.GET })
	public List<DropDownModel> pmDashboardgetId(@RequestParam String id) {
		logger.info("Method : pmDashboardgetId starts");

		logger.info("Method : pmDashboardgetId ends");
		return restPMDashboardDao.pmDashboardgetId(id);
	}
}
