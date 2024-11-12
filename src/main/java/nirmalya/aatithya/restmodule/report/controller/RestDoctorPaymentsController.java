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
import nirmalya.aatithya.restmodule.report.model.RestDoctorPaymentsModel;
import nirmalya.aatithya.restmodule.report.model.RestDoctorReportsModel;

@RestController
@RequestMapping("report/")
public class RestDoctorPaymentsController {
	Logger logger = LoggerFactory.getLogger(RestDoctorPaymentsController.class);

	@Autowired

	RestDoctorPaymentsDao restDoctorPaymentsDao;
	
	//for fromdate & todate
	@GetMapping(value = "rest-getDoctorPayments")
	public JsonResponse<List<RestDoctorReportsModel>> getDoctorPayment(@RequestParam String fromDate,@RequestParam String toDate) {
		logger.info("Method : daoDoctorOrdersview starts");

		logger.info("Method :daoDoctorOrdersview ends");
		return restDoctorPaymentsDao.getDoctorPayment(fromDate, toDate);
	}
	
	//for fromdate & todate
	@GetMapping(value = "rest-getDoctorAllDetails")
	public JsonResponse<List<RestDoctorReportsModel>> getDoctorAllDetails(@RequestParam String fromDate,@RequestParam String toDate) {
		logger.info("Method : getDoctorAllDetails starts");

		logger.info("Method :getDoctorAllDetails ends");
		return restDoctorPaymentsDao.getDoctorAllDetails(fromDate, toDate);
	}

	}