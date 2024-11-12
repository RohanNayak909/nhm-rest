package nirmalya.aatithya.restmodule.report.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.report.dao.RestDoctorPaymentsDao;
import nirmalya.aatithya.restmodule.report.dao.RestDoctorReportsDao;
import nirmalya.aatithya.restmodule.report.dao.RestLabPaymentsDao;
import nirmalya.aatithya.restmodule.report.model.RestDoctorPaymentsModel;
import nirmalya.aatithya.restmodule.report.model.RestDoctorReportsModel;
import nirmalya.aatithya.restmodule.report.model.RestLabPaymentsModel;

@RestController
@RequestMapping("report/")
public class RestLabPaymentsController {
	Logger logger = LoggerFactory.getLogger(RestLabPaymentsController.class);

	@Autowired

	RestLabPaymentsDao restLabPaymentsDao;
	
	
		
		//for fromdate & todate aggrid1
	
	@GetMapping(value = "labPaymentsRestView")
	public JsonResponse<List<RestLabPaymentsModel>> getDoctorPayment(@RequestParam String fromDate,@RequestParam String toDate) {
		logger.info("Method : daoDoctorOrdersview starts");

		logger.info("Method :daoDoctorOrdersview ends");
		return restLabPaymentsDao.daoLabPaymentview(fromDate, toDate);
	}
	
		
		//for fromdate todate agggrid2
		//lab-payments-allDetails
		
	@GetMapping(value = "labPaymentsRestAllDetails")
	public JsonResponse<List<RestLabPaymentsModel>> daolabPaymentview(@RequestParam String fromDate,@RequestParam String toDate) {
		logger.info("Method : daolabPaymentview starts");

		logger.info("Method :daolabPaymentview ends");
		return restLabPaymentsDao.daoLabPaymentDetails(fromDate, toDate);
	}
	
	
	

	}