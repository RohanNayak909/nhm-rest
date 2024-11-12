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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nirmalya.aatithya.restmodule.api.dao.HomeServiceAPIDao;
import nirmalya.aatithya.restmodule.api.model.CureEasyDrAppointBookingAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyHomeServiceListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyHomeServiceOrderListModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyRequestToServiceModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyServicedDetailsModel;
import nirmalya.aatithya.restmodule.api.model.HomeHealthServiceModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.util.DocumentUpload;
@RestController
@RequestMapping(value = "api")
public class HomeServiceAPIController {
	@Autowired
	EnvironmentVaribles env;

	@Autowired
	DocumentUpload documentUpload;
	
	@Autowired
	HomeServiceAPIDao homeServiceDao;
	
	Logger logger = LoggerFactory.getLogger(HomeServiceAPIController.class);	
	//get home service list
	  @GetMapping(value = "/get-homeservice-list")
		public JsonResponse<CureEasyHomeServiceListAPIModel> homeServiceList() {
			logger.info("Method :view homeServiceList starts");
			logger.info("Method :view homeServiceList ends");
		return homeServiceDao.homeServiceList();
		}
//get service details
	  @GetMapping(value = "/get-service-details-list")
		public ResponseEntity<JsonResponse<List<CureEasyServicedDetailsModel>>> serviceDetailsList(@RequestParam String serviceId) {
				
			logger.info("Method :view serviceDetailsList starts");
			logger.info("Method :view serviceDetailsList ends");
		return homeServiceDao.serviceDetailsList(serviceId);
		}  
	//post request to service
	  @PostMapping(value = "/post-request-to-service-api")
		public ResponseEntity<JsonResponse<Object>> postRequestToServiceApi(@RequestBody 
				CureEasyRequestToServiceModel requestToServiceApiModel) {
			logger.info("Method : postDoctorAppointmentPaymentDtls starts");
			
			logger.info("Method : postDoctorAppointmentPaymentDtls ends");
			return homeServiceDao.postRequestToServiceApi(requestToServiceApiModel);
		}
	  //get home service order list
	  @GetMapping(value = "/get-home-service-order-list")
		public ResponseEntity<JsonResponse<List<CureEasyHomeServiceOrderListModel>>> homeServiceOrderList(@RequestParam String userId) {
				
			logger.info("Method :view serviceDetailsList starts");
			logger.info("Method :view serviceDetailsList ends");
		return homeServiceDao.homeServiceOrderList(userId);
		}
	  
	  //get home service order list
	  @GetMapping(value = "/get-home-service-order-viewDetails")
		public ResponseEntity<JsonResponse<List<HomeHealthServiceModel>>> homeServiceViewDetails(@RequestParam String orderId) {
				
			logger.info("Method :view homeServiceViewDetails starts");
			logger.info("Method :view homeServiceViewDetails ends");
		return homeServiceDao.homeServiceOrderLists(orderId);
		}
	//get Home Service Autosearch
	  @GetMapping(value = "/get-homeservice-search")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> gethomeservicesearch(@RequestParam String name) {
			logger.info("Method : homeservicesearch starts");

			logger.info("Method : homeservicesearch ends");
			return homeServiceDao.gethomeservicesearch(name);
		}
}
