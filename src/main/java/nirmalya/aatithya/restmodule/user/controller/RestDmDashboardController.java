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
import nirmalya.aatithya.restmodule.user.dao.DmDashboardDao;
import nirmalya.aatithya.restmodule.user.model.DmDashboardRestModel;

@RestController
@RequestMapping("user/")
public class RestDmDashboardController {
Logger logger = LoggerFactory.getLogger(RestDmDashboardController.class);
	
	@Autowired
	
	DmDashboardDao dmDashboardDao;	
	

	@GetMapping(value = "viewdmDashboardCount")
	public ResponseEntity<JsonResponse<DmDashboardRestModel>> viewdmDashboardCount(
			@RequestParam String countryId,@RequestParam String stateId,@RequestParam String districtId) {
		logger.info("Method : viewdmDashboardCount starts");

		logger.info("Method :viewdmDashboardCount ends");
		return dmDashboardDao.viewdmDashboardCount(countryId,stateId,districtId);
	}
	/*
	 * for State list id
	 */
	

	@RequestMapping(value = "dmDashboardGetIdd", method = { RequestMethod.GET })
	public List<DmDashboardRestModel> dmDashboardGetIdd1(@RequestParam String id) {
		logger.info("Method : dmDashboardGetIdd starts");

		logger.info("Method : dmDashboardGetIdd ends");
		return dmDashboardDao.dmDashboardGetIdd(id);
	}
	
	
	
	/*
	 * for Age wise total test
	 */
	@GetMapping(value = "rest-viewdmDashboardAgeWiseTotalTest")
	public ResponseEntity<JsonResponse<DmDashboardRestModel>> viewdmDashboardAgeWiseTotalTest(
			@RequestParam String countryId,@RequestParam String stateId,@RequestParam String districtId) {
		logger.info("Method : viewdmDashboardAgeWiseTotalTest starts");

		logger.info("Method :viewdmDashboardAgeWiseTotalTest ends");
		return dmDashboardDao.viewdmDashboardAgeWiseTotalTestDao(countryId,stateId,districtId);
	}
	/**
	 * Rest Controller - Area wise
	 *
	 */
	@GetMapping(value = "rest-viewdmDashboardAreaWiseTotalTest")
	public JsonResponse<List<DropDownModel>> viewdmDashboardAreaWiseTotalTest(
			@RequestParam String countryId,@RequestParam String stateId,@RequestParam String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseTotalTest starts");

		logger.info("Method : viewdmDashboardAreaWiseTotalTest ends");
		return dmDashboardDao.viewdmDashboardAreaWiseTotalTestDao(countryId,stateId,districtId);
	}
	/**
	 * Rest Controller - disease wise total abnormal
	 *
	 */
	@GetMapping(value = "rest-viewdmDashboardDiseaseWiseTotalAbnormal")
	public JsonResponse<List<DropDownModel>> viewdmDashboardDiseaseWiseTotalAbnormal(
			@RequestParam String countryId,@RequestParam String stateId,@RequestParam String districtId) {
		logger.info("Method : viewdmDashboardDiseaseWiseTotalAbnormal starts");

		logger.info("Method : viewdmDashboardDiseaseWiseTotalAbnormal ends");
		return dmDashboardDao.viewdmDashboardDiseaseWiseTotalAbnormalDao(countryId,stateId,districtId);
	}
	/**
	 * Rest Controller - disease wise total high risk
	 *
	 */
	@GetMapping(value = "rest-viewdmDashboardDiseaseWiseTotalHighRisk")
	public JsonResponse<List<DropDownModel>> viewdmDashboardDiseaseWiseTotalHighRisk(
			@RequestParam String countryId,@RequestParam String stateId,@RequestParam String districtId) {
		logger.info("Method : viewdmDashboardDiseaseWiseTotalHighRisk starts");

		logger.info("Method : viewdmDashboardDiseaseWiseTotalHighRisk ends");
		return dmDashboardDao.viewdmDashboardDiseaseWiseTotalHighRiskDao(countryId,stateId,districtId);
	}

	////////////////////////////////////////////////////////////////// rest
	////////////////////////////////////////////////////////////////// contrller
	////////////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////

	/*************************************************
	 * Rest function to get pie chart for male and female in dm dashboard
	 */
	@GetMapping(value = "rest-viewdmDashboardMaleFemale")
	public JsonResponse<List<DropDownModel>> viewdmDashboardMaleFemale(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardMaleFemale starts");

		logger.info("Method : viewdmDashboardMaleFemale ends");
		return dmDashboardDao.viewdmDashboardMaleFemaleDao(countryId, stateId, districtId);
	}

	/*************************************************
	 * function to get pie chart for normal and abnormal in dm dashboard
	 */
	@GetMapping(value = "rest-viewdmDashboardNormalAbNormal")
	public JsonResponse<List<DropDownModel>> viewdmDashboardNormalAbNormal(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardNormalAbNormal starts");

		logger.info("Method : viewdmDashboardNormalAbNormal ends");
		return dmDashboardDao.viewdmDashboardNormalAbNormalDao(countryId, stateId, districtId);
	}

	/*************************************************
	 * function to get pie chart for normal and abnormal in dm dashboard
	 */
	@GetMapping(value = "rest-viewdmDashboardAreaWiseCheckUp")
	public JsonResponse<List<DropDownModel>> viewdmDashboardAreaWiseCheckUp(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseCheckUp starts");

		logger.info("Method : viewdmDashboardAreaWiseCheckUp ends");
		return dmDashboardDao.viewdmDashboardAreaWiseCheckUpDao(countryId, stateId, districtId);
	}
	/*************************************************
	 * function to get pie chart for normal in dm dashboard
	 */
	@GetMapping(value = "rest-viewdmDashboardAreaWiseNormalCheckUp")
	public JsonResponse<List<DropDownModel>> viewdmDashboardAreaWiseNormalCheckUp(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseNormalCheckUp starts");

		logger.info("Method : viewdmDashboardAreaWiseNormalCheckUp ends");
		return dmDashboardDao.viewdmDashboardAreaWiseNormalCheckUpDao(countryId, stateId, districtId);
	}
	/*************************************************
	 * function to get pie chart for highrisk in dm dashboard
	 */
	@GetMapping(value = "rest-viewdmDashboardAreaWiseHighriskCheckUp")
	public JsonResponse<List<DropDownModel>> viewdmDashboardAreaWiseHighriskCheckUp(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseHighriskCheckUp starts");

		logger.info("Method : viewdmDashboardAreaWiseHighriskCheckUp ends");
		return dmDashboardDao.viewdmDashboardAreaWiseHighriskCheckUpDao(countryId, stateId, districtId);
	}
	/************************************************ function to get pie chart for normal and abnormal in dm dashboard */
	@GetMapping(value = "rest-genderWiseDiseaseCount")
	public JsonResponse<List<DropDownModel>> genderWiseDiseaseCount(
			@RequestParam String countryId,@RequestParam String stateId,@RequestParam String districtId) {
		logger.info("Method : genderWiseDiseaseCount starts");
		
		logger.info("Method : viewdmDashboardNormalAbNormal ends");
		return dmDashboardDao.genderWiseDiseaseCount(countryId,stateId,districtId);
	}
	/*
	 * for patient details
	 */

	@GetMapping(value = "dmDashboardViewRegd")
	public JsonResponse<List<DmDashboardRestModel>> viewdmDashboardTotalRegd(
			@RequestParam String countryId,@RequestParam String stateId,@RequestParam String districtId,@RequestParam String typeId) {
		logger.info("Method : viewdmDashboardTotalRegd starts");

		logger.info("Method :viewdmDashboardTotalRegd ends");
		return dmDashboardDao.viewdmDashboardTotalRegdDao(countryId,stateId,districtId,typeId);
	}

}
