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
import nirmalya.aatithya.restmodule.api.dao.CureeazyLabTestDao;
import nirmalya.aatithya.restmodule.api.model.CureEasyDrAppointBookingAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyTestListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyLabTestModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyPaymentModel;
import nirmalya.aatithya.restmodule.api.model.DashboardlabDetails;
import nirmalya.aatithya.restmodule.api.model.LabDashboardCountModel;
import nirmalya.aatithya.restmodule.api.model.LabDashboardModel;
import nirmalya.aatithya.restmodule.api.model.TestNamedropdownModel;
import nirmalya.aatithya.restmodule.api.model.UserDocumentModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyAddtoCartModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.LablistsModel;
import nirmalya.aatithya.restmodule.util.DocumentUpload;

@RestController
@RequestMapping(value = "api")
public class CureeazyLabTestController {
	@Autowired
	EnvironmentVaribles env;

	@Autowired
	DocumentUpload documentUpload;

	@Autowired
	CureeazyLabTestDao labTestDao;

	Logger logger = LoggerFactory.getLogger(CureeazyLabTestController.class);

	// Lab Tests & Packages
	@GetMapping(value = "/get-labtest-list")
	public JsonResponse<CureEasyTestListAPIModel> labTestList() {
		logger.info("Method :view labTestList starts");
		logger.info("Method :view labTestList ends");
		return labTestDao.labTestList();
	}

	// get test details
	@GetMapping(value = "/get-test-details")
	public ResponseEntity<JsonResponse<CureeazyLabTestModel>> getTestDetailsByTestId(@RequestParam String testId) {
		logger.info("Method :view getTestDetailsByTestId starts");
		logger.info("Method :view getTestDetailsByTestId ends");
		return labTestDao.getTestDetailsByTestId(testId);
	}
	


	// post add to cart lab list
	@PostMapping(value = "/post-labtest-addtocart-api")
	public ResponseEntity<JsonResponse<Object>> postLabtestAddtoCartApi(
			@RequestBody CureeazyAddtoCartModel cureeazyPaymentModel) {
		logger.info("Method : postLabtestAddtoCartApi starts");

		logger.info("Method : postLabtestAddtoCartApi ends");
		return labTestDao.postLabtestAddtoCartApi(cureeazyPaymentModel);
	}

	@GetMapping(value = "/get-labtest-addtocart-list")
	public ResponseEntity<JsonResponse<List<CureeazyAddtoCartModel>>> addToCartLabList(@RequestParam String userId) {

		logger.info("Method :view addToCartLabList starts");
		logger.info("Method :view addToCartLabList ends");
		return labTestDao.addToCartLabList(userId);
	}

	@GetMapping(value = "/delete-labtst-addtocart")
	public ResponseEntity<Object> deleteAddtoCart(@RequestParam String id) {
		logger.info("Method : deleteAddtoCart starts");

		logger.info("Method : deleteAddtoCart ends");
		return labTestDao.deleteAddtoCart(id);
	}

	/*
	 * post mapping for add ProfRegistration
	 */

	@RequestMapping(value = "/post-labtest-payment-api", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<CureeazyPaymentModel>> postLabtestPaymentApi(
			@RequestBody CureeazyPaymentModel cureeazyPaymentModel) {
		logger.info("Method : postLabtestPaymentApi starts");

		logger.info("Method : postLabtestPaymentApi ends");
		return labTestDao.postLabtestPaymentApi(cureeazyPaymentModel);
	}

	// get searchwise test list
	@GetMapping(value = "/get-searchwiseTest-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> bloodbankList(@RequestParam String searchdata) {
		logger.info("Method : bloodbankList starts");

		logger.info("Method : bloodbankList ends");
		return labTestDao.getSearchWiseTestListDao(searchdata);
	}

	// get order lab list
	@GetMapping(value = "/get-order-lab-list")
	public ResponseEntity<JsonResponse<List<LablistsModel>>> getOrderlabList(@RequestParam String userId) {

		logger.info("Method :view orderlabList starts");
		logger.info("Method :view orderlabList ends");
		return labTestDao.getOrderlabList(userId);
	}

	// post add to cart lab list
	@GetMapping(value = "/post-doctor-feedback-api")
	public ResponseEntity<JsonResponse<Object>> postDoctorDataApi(@RequestParam String userid,
			@RequestParam String feedback) {
		logger.info("Method : postDoctorDataApi starts");
		logger.info("Method : postDoctorDataApi ends");
		return labTestDao.postDoctorDataApi(userid, feedback);
	}

	// get labtest dashboard list
	@GetMapping(value = "/get-dashboard-lab-details")
	public ResponseEntity<JsonResponse<List<DashboardlabDetails>>> getDashboardlabDetails(@RequestParam String userId) {
		logger.info("Method :view dashbaordlabdetails starts");
		logger.info("Method :view dashbaordlabdetails ends");
		return labTestDao.getDashboardlabDetails(userId);
	}

	// post Favorite Test
	@PostMapping(value = "/post-favorite-test-api")
	public ResponseEntity<JsonResponse<Object>> postFavoriteTestApi(
			@RequestBody CureeazyLabTestModel cureeazyLabTestModel) {
		logger.info("Method : postFavoriteDoctorApi starts");

		logger.info("Method : postFavoriteDoctorApi ends");
		return labTestDao.postFavoriteTestApi(cureeazyLabTestModel);
	}

	@GetMapping(value = "/get-searchFavouriteTest-list")
	public ResponseEntity<JsonResponse<List<CureeazyLabTestModel>>> getFavouriteTestList(@RequestParam String userid) {
		logger.info("Method :view getFavouriteTestList starts");
		logger.info("Method :view getFavouriteTestList ends");
		return labTestDao.getFavouriteTestList(userid);
	}

	// post self document upload api
	@PostMapping(value = "/post-self-document-api")
	public ResponseEntity<JsonResponse<UserDocumentModel>> postSelfDocumentApi(
			@RequestBody UserDocumentModel userDocumentModel) {
		logger.info("Method : postSelfDocumentApi starts");

		logger.info("Method : postSelfDocumentApi ends");
		return labTestDao.postSelfDocumentApi(userDocumentModel);
	}

	// get user self document view
	@GetMapping(value = "/user-self-document-view")
	public ResponseEntity<JsonResponse<List<UserDocumentModel>>> getuserdocumentview(@RequestParam String userId) {
		logger.info("Method :view userdocumentview starts");
		logger.info("Method :view userdocumentview ends");
		return labTestDao.getuserdocumentview(userId);
	}

	// get Lab Timeslot Master
	@GetMapping(value = "/get-labTimeSlot")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> labTimeSlot() {
		logger.info("Method : labTimeSlot starts");

		logger.info("Method : labTimeSlot ends");
		return labTestDao.labTimeSlot();
	}
	
	// get search Lab Details
	@GetMapping(value = "/get-searchLabDetails")
	public ResponseEntity<JsonResponse<List<LabDashboardModel>>> searchLabDetails(@RequestParam String searchdata) {
		logger.info("Method : searchLabDetails starts");

		logger.info("Method : searchLabDetails ends");
		return labTestDao.searchLabDetails(searchdata);
	}
	
	// get lab dashboard count
	@GetMapping(value = "/get-addtocart-count")
	public ResponseEntity<JsonResponse<DropDownModel>> getAddtoCartcount(@RequestParam String userId) {
		logger.info("Method :view getAddtoCartcount starts");
		logger.info("Method :view getAddtoCartcount ends");
		return labTestDao.getAddtoCartcount(userId);
	}
	
	// post add to cart lab list
	@GetMapping(value = "/post-homeservice-feedback-api")
	public ResponseEntity<JsonResponse<Object>> postHomeServiceDataApi(@RequestParam String userid,
			@RequestParam String feedback) {
		logger.info("Method : postHomeServiceDataApi starts");
		logger.info("Method : postHomeServiceDataApi ends");
		return labTestDao.postHomeServiceDataApi(userid, feedback);
	}
	
	
	  // get test details packagewise
	  
		@GetMapping(value = "/get-packageWise-test-details")
		public ResponseEntity<JsonResponse<CureeazyLabTestModel>>getPackageWiseTest(@RequestParam String packageId) {
			logger.info("Method :view getPackageWiseTest starts");
			logger.info("Method :view getPackageWiseTest ends");
			return labTestDao.getPackageWiseTest(packageId);
		}
		 // get test details
		  
//		@GetMapping(value = "/get-conditionBasedtest-details")
//		public ResponseEntity<JsonResponse<List<CureeazyLabTestModel>>>getConditionBasedTest(@RequestParam String testId) {
//			logger.info("Method :view getPackageWiseTest starts");
//			logger.info("Method :view getPackageWiseTest ends");
//			return labTestDao.getConditionBasedTest(testId);
//		}
		
		  // get test details packagewise
		  
			@GetMapping(value = "/get-conditionBasedtest-details")
			public ResponseEntity<JsonResponse<CureeazyLabTestModel>>getConditionBasedTest(@RequestParam String testId) {
				logger.info("Method :view getConditionBasedTest starts");
				logger.info("Method :view getConditionBasedTest ends");
				return labTestDao.getConditionBasedTest(testId);
			}
}
