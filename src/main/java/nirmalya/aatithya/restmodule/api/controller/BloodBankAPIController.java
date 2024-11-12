package nirmalya.aatithya.restmodule.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.BloodBankAPIDao;
import nirmalya.aatithya.restmodule.api.model.APIDoctorMonthlyOverviewModel;
import nirmalya.aatithya.restmodule.api.model.BloodBankAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyDrAppointBookingAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyPaymentModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class BloodBankAPIController {
	@Autowired
	BloodBankAPIDao bloodBankAPIDao;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(BloodBankAPIController.class);

	// post create community Api
	@PostMapping(value = "/post-create-community")
	public ResponseEntity<JsonResponse<Object>> postCreateCommunity(
			@RequestBody BloodBankAPIModel bloodBankAPIModel) {
		logger.info("Method : postCreateCommunity starts");

		logger.info("Method : postCreateCommunity ends");
		return bloodBankAPIDao.postCreateCommunity(bloodBankAPIModel);
	}

	// get community dropdown
	@GetMapping(value = "/get-community-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getCommunityList() {
		logger.info("Method : getCommunityList starts");

		logger.info("Method : getCommunityList ends");
		return bloodBankAPIDao.getCommunityList();
	}
	


	// get blood units dropdown
	@GetMapping(value = "/get-bloodunits-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getBloodUnitList() {
		logger.info("Method : getBloodUnitList starts");

		logger.info("Method : getBloodUnitList ends");
		return bloodBankAPIDao.getBloodUnitList();
	}
	
	// post request community Api

	@RequestMapping(value = "/post-request-community", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<BloodBankAPIModel>> postRequestCommunity(
			@RequestBody BloodBankAPIModel bloodBankAPIModel) {
		logger.info("Method : postRequestCommunity starts");

		logger.info("Method : postRequestCommunity ends");
		return bloodBankAPIDao.postRequestCommunity(bloodBankAPIModel);
	}
}
