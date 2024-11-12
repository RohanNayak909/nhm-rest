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
import nirmalya.aatithya.restmodule.report.dao.RestDoctorReportsDao;
import nirmalya.aatithya.restmodule.report.model.RestDoctorReportsModel;

@RestController
@RequestMapping("report/")
public class RestDoctorReportsController {
	Logger logger = LoggerFactory.getLogger(RestDoctorReportsController.class);

	@Autowired

	RestDoctorReportsDao restDoctorReportsDao;
	

	
	//for fromdate & todate
	@GetMapping(value = "rest-doctorOrder")
	public JsonResponse<List<RestDoctorReportsModel>> daoDoctorOrdersview(@RequestParam String fromDate,@RequestParam String toDate) {
		logger.info("Method : daoDoctorOrdersview starts");

		logger.info("Method :daoDoctorOrdersview ends");
		return restDoctorReportsDao.daoDoctorOrdersview(fromDate, toDate);
	}
		
	}
