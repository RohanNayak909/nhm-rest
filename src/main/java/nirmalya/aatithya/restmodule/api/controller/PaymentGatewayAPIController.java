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

import nirmalya.aatithya.restmodule.api.dao.PaymentGatewayAPIDao;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.PaymentGatewayAPIModel;
import nirmalya.aatithya.restmodule.api.model.RestPaymentGatewayModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class PaymentGatewayAPIController {

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	PaymentGatewayAPIDao paymentDao;

	Logger logger = LoggerFactory.getLogger(PaymentGatewayAPIController.class);

	@GetMapping("/create-order")
	public String getOrderDetails(@RequestParam String order_id, Double amount) {
		logger.info("Method : getOrderDetails Rest controller starts");

		logger.info("Method : getOrderDetails Rest controller ends");
		return paymentDao.getOrderDetailsDao(order_id, amount);
	}

	@PostMapping("/save-payment-details")
	public ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> savePaymentDetails(@RequestBody PaymentGatewayAPIModel payment) {
		logger.info("Method : getOrderDetails Rest controller starts");

		logger.info("Method : getOrderDetails Rest controller ends");
		return paymentDao.savePaymentDetails(payment);
	}

	//Get join url start consultation
	@GetMapping(value = "/get-joinUrl-startConsultation")
	public ResponseEntity<JsonResponse<List<PaymentGatewayAPIModel>>> startConsultation(@RequestParam String orderid) {
		logger.info("Method : startConsultation starts");
		
		logger.info("Method : startConsultation ends");
		return paymentDao.startConsultation(orderid);
	}
	//Lab Payment Gateway Order
	@GetMapping("/create-order-lab")
	public String getOrderDetailsLab(@RequestParam String order_id, Double amount) {
		logger.info("Method : getOrderDetailsLab Rest controller starts");

		logger.info("Method : getOrderDetailsLab Rest controller ends");
		return paymentDao.getOrderDetailsLab(order_id, amount);
	}
	//Lab Payment Save
	@PostMapping("/save-payment-details-lab")
	public ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> savePaymentDetailsLab(@RequestBody PaymentGatewayAPIModel payment) {
		logger.info("Method : savePaymentDetailsLab Rest controller starts");

		logger.info("Method : savePaymentDetailsLab Rest controller ends");
		return paymentDao.savePaymentDetailsLab(payment);
	}
	
	

}
