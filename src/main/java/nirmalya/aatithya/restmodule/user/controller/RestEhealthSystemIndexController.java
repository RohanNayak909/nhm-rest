package nirmalya.aatithya.restmodule.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.RestEhealthSystemIndexDao;
@RestController
@RequestMapping(value = { "user/" })
public class RestEhealthSystemIndexController {
	Logger logger = LoggerFactory.getLogger(RestEhealthSystemIndexController.class);

	@Autowired
	RestEhealthSystemIndexDao restEhealthSystemIndexDao;
	/*
	 * for State list
	 */

	@RequestMapping(value = "getStateLists-doctor", method = { RequestMethod.GET })
	public List<DropDownModel> getState() {
		logger.info("Method : getState starts");

		logger.info("Method : getState ends");
		return restEhealthSystemIndexDao.getState();
	}
	
	/**
	 * Rest Controller - Get Districct List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistrictList")
	public JsonResponse<List<DropDownModel>> getDistrictLists(@RequestParam Integer id) {
		logger.info("Method : getDistrictLists starts");

		logger.info("Method : getDistrictLists ends");
		return restEhealthSystemIndexDao.getDistrictLists(id);
	}
	

	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityLists")
	public JsonResponse<List<DropDownModel>> getCityLists(@RequestParam String dist) {
		logger.info("Method : getCityLists starts");

		logger.info("Method : getCityLists ends");
		return restEhealthSystemIndexDao.getCityLists(dist);
	}
}
