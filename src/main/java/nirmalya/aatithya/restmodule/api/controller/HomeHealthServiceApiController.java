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

import nirmalya.aatithya.restmodule.api.model.NurseAideModel;
import nirmalya.aatithya.restmodule.api.dao.HomeHealthServiceDao;
import nirmalya.aatithya.restmodule.api.dao.PartnerDashboardDao;
import nirmalya.aatithya.restmodule.api.model.CaseStudyModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyPaymentModel;
import nirmalya.aatithya.restmodule.api.model.DoctorRatingModel;
import nirmalya.aatithya.restmodule.api.model.HomeHealthServiceModel;
import nirmalya.aatithya.restmodule.api.model.HomeServiceDocumentationModel;
import nirmalya.aatithya.restmodule.api.model.HomeServicePaymentModel;
import nirmalya.aatithya.restmodule.api.model.LabDashboardCountModel;
import nirmalya.aatithya.restmodule.api.model.LabDashboardModel;
import nirmalya.aatithya.restmodule.api.model.NurseDetailsModel;
import nirmalya.aatithya.restmodule.api.model.PatientAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.PaymentGatewayAPIModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class HomeHealthServiceApiController {
	@Autowired
	HomeHealthServiceDao homeHealthServiceDao;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(PartnerDashboardController.class);

	// get lab dashboard count

	@GetMapping(value = "/get-homesevice-dashboard-count")
	public ResponseEntity<JsonResponse<HomeHealthServiceModel>> getHomeServiceDashboardcount(
			@RequestParam String serviceId) {
		logger.info("Method :view getLabDashboardcount starts");
		logger.info("Method :view getLabDashboardcount ends");
		return homeHealthServiceDao.getHomeServiceDashboardcount(serviceId);
	}

	// get homeservice status
	@GetMapping(value = "/get-homeservice-dropdown")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getServiceDropdown() {
		logger.info("Method : getServiceDropdown starts");

		logger.info("Method : getServiceDropdown ends");
		return homeHealthServiceDao.getServiceDropdown();
	}

	// get lab dashboard count
	@GetMapping(value = "/get-homeservice-dashboard-details")
	public ResponseEntity<JsonResponse<List<HomeHealthServiceModel>>> getHomeServiceDetails(
			@RequestParam String serviceId, @RequestParam String status, @RequestParam String fromDate,
			@RequestParam String toDate) {
		logger.info("Method :view getHomeServiceDetails starts" + serviceId + status + fromDate + toDate);
		logger.info("Method :view getHomeServiceDetails ends");
		return homeHealthServiceDao.getHomeServiceDetails(serviceId, status, fromDate, toDate);
	}

	// post update lab status
	@PostMapping(value = "/post-update-homeservice-status")
	public ResponseEntity<JsonResponse<HomeHealthServiceModel>> postUpdateHomeServiceStatus(
			@RequestBody HomeHealthServiceModel dropDownModel) {
		logger.info("Method : postUpdateHomeServiceStatus start");
		logger.info("Method : postUpdateHomeServiceStatus End");
		return homeHealthServiceDao.postUpdateHomeServiceStatus(dropDownModel);
	}

	// post update user documentation
	@PostMapping(value = "/post-update-user-documentation")
	public ResponseEntity<JsonResponse<HomeHealthServiceModel>> postUserDocumentation(
			@RequestBody HomeHealthServiceModel dropDownModel) {
		logger.info("Method : postUserDocumentation start");
		logger.info("Method : postUserDocumentation End");
		return homeHealthServiceDao.postUserDocumentation(dropDownModel);
	}

	// post update homeservice documentation
	@PostMapping(value = "/post-homeservice-documentation")
	public ResponseEntity<JsonResponse<HomeServiceDocumentationModel>> postHomeserviceDocumentation(
			@RequestBody HomeServiceDocumentationModel homeServiceDocumentationModel) {
		logger.info("Method : postHomeserviceDocumentation start");
		logger.info("Method : postHomeserviceDocumentation End");
		return homeHealthServiceDao.postHomeserviceDocumentation(homeServiceDocumentationModel);
	}

	/*
	 * @RequestMapping(value = "/rest-addPatientDocumetation", method = {
	 * RequestMethod.POST }) public
	 * ResponseEntity<JsonResponse<PatientDocumentationModel>>
	 * addPatientDocumetation(
	 * 
	 * @RequestBody PatientDocumentationModel patientDocumentationModel) {
	 * logger.info("Method : addPatientDocumetation starts" +
	 * patientDocumentationModel);
	 * 
	 * logger.info("Method : addPatientDocumetation ends"); return
	 * homeHealthServiceDao.addPatientDocumetation(patientDocumentationModel); }
	 */

	// get Home Service Autosearch
	@GetMapping(value = "/get-nurseDetails")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getNurseDetails(@RequestParam String type,
			@RequestParam String hours,@RequestParam String orderId) {
		logger.info("Method : getNurseDetails starts");

		logger.info("Method : getNurseDetails ends");
		return homeHealthServiceDao.getNurseDetails(type, hours,orderId);
	}

	@GetMapping("/create-order-homeservice")
	public String getOrderHomeservice(@RequestParam String order_id, Double amount) {
		logger.info("Method : getOrderDetails Rest controller starts");

		logger.info("Method : getOrderDetails Rest controller ends");
		return homeHealthServiceDao.getOrderHomeservice(order_id, amount);
	}
	
	@PostMapping("/save-payment-details-homeservice")
	public ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> savePaymentHomeService(@RequestBody PaymentGatewayAPIModel payment) {
		logger.info("Method : savePaymentHomeService Rest controller starts");

		logger.info("Method : savePaymentHomeService Rest controller ends");
		return homeHealthServiceDao.savePaymentHomeService(payment);
	}
	// get lab dashboard count
	@GetMapping(value = "/get-homeservice-documentation-details")
	public ResponseEntity<JsonResponse<List<HomeServiceDocumentationModel>>> getHomeServiceDocumentation(
			@RequestParam String userId, @RequestParam String homeserviceOrderId) {
		logger.info("Method :view getHomeServiceDocumentation starts");
		logger.info("Method :view getHomeServiceDocumentation ends");
		return homeHealthServiceDao.getHomeServiceDocumentation(userId, homeserviceOrderId);
	}
	
	@RequestMapping(value = "post-nurseAideDetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<NurseAideModel>> addNurseAideDetails(
			@RequestBody NurseAideModel nurseAideModel) {
		logger.info("Method : addNurseAideDetails starts" + nurseAideModel);

		logger.info("Method : addNurseAideDetails ends");
		return homeHealthServiceDao.addNurseAideDetails(nurseAideModel);
	}
	

	// post update homeservice documentation
	@PostMapping(value = "/update-homeservice-documentation")
	public ResponseEntity<JsonResponse<HomeServiceDocumentationModel>> updateHomeserviceDocumentation(
			@RequestBody HomeServiceDocumentationModel homeServiceDocumentationModel) {
		logger.info("Method : postHomeserviceDocumentation start");
		logger.info("Method : postHomeserviceDocumentation End");
		return homeHealthServiceDao.updateHomeserviceDocumentation(homeServiceDocumentationModel);
	}
	
	// post update homeservice documentation
	@PostMapping(value = "/post-doctorRating")
	public ResponseEntity<JsonResponse<Object>> postDoctorRating(
			@RequestBody DoctorRatingModel homeServiceDocumentationModel) {
		logger.info("Method : postHomeserviceDocumentation start");
		logger.info("Method : postHomeserviceDocumentation End");
		return homeHealthServiceDao.postHomeserviceDocumentation(homeServiceDocumentationModel);
	}
	// get Home Service Autosearch
	@GetMapping(value = "/get-orderDetails-caseStudy")
	public ResponseEntity<JsonResponse<List<CaseStudyModel>>> getOrderDetails(@RequestParam String orderId) {
		logger.info("Method : getOrderDetails starts");

		logger.info("Method : getOrderDetails ends");
		return homeHealthServiceDao.getOrderDetails(orderId);
	}
	// post update homeservice documentation
	/*
	 * @PostMapping(value = "/post-homeServicePayment") public
	 * ResponseEntity<JsonResponse<Object>> posthomeServicePayment(
	 * 
	 * @RequestBody HomeServicePaymentModel homeServiceDocumentationModel) {
	 * logger.info("Method : postHomeserviceDocumentation start");
	 * logger.info("Method : postHomeserviceDocumentation End"); return
	 * homeHealthServiceDao.posthomeServicePayment(homeServiceDocumentationModel); }
	 */
	@RequestMapping(value = "/post-homeServicePayment", method = { RequestMethod.POST })
	public JsonResponse<HomeServicePaymentModel> posthomeServicePayment(@RequestBody HomeServicePaymentModel homeServiceDocumentationModel) {
		logger.info("Method : posthomeServicePayment starts");

		logger.info("Method : posthomeServicePayment ends");
		return homeHealthServiceDao.posthomeServicePayment(homeServiceDocumentationModel);
	}
	// get Home Service Payment
	@GetMapping(value = "/get-homeServicePayment-view")
	public ResponseEntity<JsonResponse<List<HomeServicePaymentModel>>> getHomeServicePayment(@RequestParam String orderId) {
		logger.info("Method : getHomeServicePayment starts");

		logger.info("Method : getHomeServicePayment ends");
		return homeHealthServiceDao.getHomeServicePayment(orderId);
	}
}
