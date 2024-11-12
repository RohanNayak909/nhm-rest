package nirmalya.aatithya.restmodule.user.controller;
import java.util.List;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.CmDashboardDao;
import nirmalya.aatithya.restmodule.user.model.DmDashboardRestModel;
import nirmalya.aatithya.restmodule.user.model.RestCmDashboardModel;

@RestController
@RequestMapping("user/")
public class RestCmDashboardController {
Logger logger = LoggerFactory.getLogger(RestCmDashboardController.class);
	
	@Autowired
	
	CmDashboardDao cmDashboardDao;	
	
	@GetMapping(value = "viewcmDashboardCount")
	public ResponseEntity<JsonResponse<RestCmDashboardModel>> viewcmDashboardCount(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardCount starts");

		logger.info("Method :viewcmDashboardCount ends");
		return cmDashboardDao.viewcmDashboardCount(stateId);
	}

	/*
	 * for State list id
	 */
	

	@RequestMapping(value = "cmDashboardGetIdd", method = { RequestMethod.GET })
	public List<RestCmDashboardModel> cmDashboardGetIdd1(@RequestParam String id) {
		logger.info("Method : cmDashboardGetIdd starts");

		logger.info("Method : cmDashboardGetIdd ends");
		return cmDashboardDao.cmDashboardGetIdd(id);
	}
	
	/*
	 * for patient details
	 */

	@GetMapping(value = "cmDashboardViewRegd")
	public JsonResponse<List<RestCmDashboardModel>> viewcmDashboardTotalRegd(@RequestParam String stateId,@RequestParam String typeId) {
		logger.info("Method : viewcmDashboardTotalRegd starts");

		logger.info("Method :viewcmDashboardTotalRegd ends");
		return cmDashboardDao.viewcmDashboardTotalRegdDao(stateId,typeId);
	}
	
	/*
	 * for Age wise total test
	 */
	@GetMapping(value = "rest-viewcmDashboardAgeWiseTotalTest")
	public ResponseEntity<JsonResponse<RestCmDashboardModel>> viewcmDashboardAgeWiseTotalTest(@RequestParam String stateId) {
		logger.info("Method : viewdmDashboardAgeWiseTotalTest starts");

		logger.info("Method :viewdmDashboardAgeWiseTotalTest ends");
		return cmDashboardDao.viewcmDashboardAgeWiseTotalTestDao(stateId);
	}
	/**
	 * Rest Controller - Area wise
	 *
	 */
	@GetMapping(value = "rest-viewcmDashboardAreaWiseTotalTest")
	public JsonResponse<List<DropDownModel>> viewcmDashboardAreaWiseTotalTest(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseTotalTest starts");

		logger.info("Method : viewcmDashboardAreaWiseTotalTest ends");
		return cmDashboardDao.viewcmDashboardAreaWiseTotalTestDao(stateId);
	}
	/**
	 * Rest Controller - disease wise total high risk
	 *
	 */
	@GetMapping(value = "rest-viewcmDashboardDiseaseWiseTotalHighRisk")
	public JsonResponse<List<DropDownModel>> viewdmDashboardDiseaseWiseTotalHighRisk(@RequestParam String stateId) {
		logger.info("Method : viewdmDashboardDiseaseWiseTotalHighRisk starts");

		logger.info("Method : viewdmDashboardDiseaseWiseTotalHighRisk ends");
		return cmDashboardDao.viewcmDashboardDiseaseWiseTotalHighRiskDao(stateId);
	}
	/**
	 * Rest Controller - disease wise total abnormal
	 *
	 */
	@GetMapping(value = "rest-viewcmDashboardDiseaseWiseTotalAbnormal")
	public JsonResponse<List<DropDownModel>> viewcmDashboardDiseaseWiseTotalAbnormal(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardDiseaseWiseTotalAbnormal starts");

		logger.info("Method : viewcmDashboardDiseaseWiseTotalAbnormal ends");
		return cmDashboardDao.viewcmDashboardDiseaseWiseTotalAbnormalDao(stateId);
	}
	/*************************************************
	 * Rest function to get pie chart for male and female in cm dashboard
	 */
	@GetMapping(value = "rest-viewcmDashboardMaleFemale")
	public JsonResponse<List<DropDownModel>> viewcmDashboardMaleFemale(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardMaleFemale starts");

		logger.info("Method : viewcmDashboardMaleFemale ends");
		return cmDashboardDao.viewcmDashboardMaleFemaleDao( stateId);
	}
	/*************************************************
	 * function to get pie chart for normal and abnormal in cm dashboard
	 */
	@GetMapping(value = "rest-viewcmDashboardNormalAbNormal")
	public JsonResponse<List<DropDownModel>> viewcmDashboardNormalAbNormal(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardNormalAbNormal starts");

		logger.info("Method : viewcmDashboardNormalAbNormal ends");
		return cmDashboardDao.viewcmDashboardNormalAbNormalDao( stateId);
	}
	/*************************************************
	 * function to get pie chart for normal and abnormal in cm dashboard
	 */
	@GetMapping(value = "rest-viewcmDashboardAreaWiseCheckUp")
	public JsonResponse<List<DropDownModel>> viewcmDashboardAreaWiseCheckUp(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseCheckUp starts");

		logger.info("Method : viewcmDashboardAreaWiseCheckUp ends");
		return cmDashboardDao.viewcmDashboardAreaWiseCheckUpDao( stateId);
	}
	/*************************************************
	 * function to get pie chart for normal area wise in cm dashboard
	 */
	@GetMapping(value = "rest-viewcmDashboardAreaWiseNormalCheckUp")
	public JsonResponse<List<DropDownModel>> viewcmDashboardAreaWiseNormalCheckUp(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseNormalCheckUp starts");

		logger.info("Method : viewcmDashboardAreaWiseNormalCheckUp ends");
		return cmDashboardDao.viewcmDashboardAreaWiseNormalCheckUpDao(stateId);
	}
	/*************************************************
	 * function to get pie chart for highrisk in dm dashboard
	 */
	@GetMapping(value = "rest-viewcmDashboardAreaWiseHighriskCheckUp")
	public JsonResponse<List<DropDownModel>> viewcmDashboardAreaWiseHighriskCheckUp(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseHighriskCheckUp starts");

		logger.info("Method : viewcmDashboardAreaWiseHighriskCheckUp ends");
		return cmDashboardDao.viewcmDashboardAreaWiseHighriskCheckUpDao(stateId);
	}
	
}
