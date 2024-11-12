package nirmalya.aatithya.restmodule.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.RestUserRegistrationDao;
import nirmalya.aatithya.restmodule.user.model.RestGuestRegdModel;
import nirmalya.aatithya.restmodule.user.model.RestUserRegistrationModel;

@RestController
@RequestMapping(value = { "user/" })
public class RestUserRegistrationController {
	Logger logger = LoggerFactory.getLogger(RestUserRegistrationController.class);

	@Autowired
	RestUserRegistrationDao restUserRegistrationDao;

	/*
	 * for title list
	 */

	@RequestMapping(value = "geTitleLists", method = { RequestMethod.GET })
	public List<DropDownModel> geTitle() {
		logger.info("Method : geTitle starts");

		logger.info("Method : geTitle ends");
		return restUserRegistrationDao.geTitleLists();
	}
	/*
	 * for Country list
	 */

	@RequestMapping(value = "getCountryLists", method = { RequestMethod.GET })
	public List<DropDownModel> getCountry() {
		logger.info("Method : getCountry starts");

		logger.info("Method : getCountry ends");
		return restUserRegistrationDao.getCountryLists();
	}
	/*
	 * for BloodGroup list
	 */

	@RequestMapping(value = "getBloodGrpLists", method = { RequestMethod.GET })
	public List<DropDownModel> getBloodGrpList() {
		logger.info("Method : getBloodGrpList starts");

		logger.info("Method : getBloodGrpList ends");
		return restUserRegistrationDao.getBloodGrpLists();
	}
	/*
	 * for Role User list
	 */

	@RequestMapping(value = "getroleUserLists", method = { RequestMethod.GET })
	public List<DropDownModel> getroleUserList() {
		logger.info("Method : getroleUserList starts");

		logger.info("Method : getroleUserList ends");
		return restUserRegistrationDao.getroleUserLists();
	}
	/*
	 * for Gender list
	 */

	@RequestMapping(value = "getGenderLists", method = { RequestMethod.GET })
	public List<DropDownModel> getGenderList() {
		logger.info("Method : getGenderList starts");

		logger.info("Method : getGenderList ends");
		return restUserRegistrationDao.getGenderLists();
	}
	/*
	 * Organization Name autoSearch
	 */

	@GetMapping(value = "rest-getOrganizationAutoSearchList")
	public JsonResponse<List<RestUserRegistrationModel>> getOrganizationAutoSearchList(@RequestParam String id,@RequestParam Integer role) {
		logger.info("Method : getOrganizationAutoSearchList starts");

		logger.info("Method :getOrganizationAutoSearchList endss");
		return restUserRegistrationDao.getOrganizationAutoSearchListDao(id,role);
	}

	// get Plan Card
	@RequestMapping(value = "rest-getPlanCard", method = { RequestMethod.GET })
	public JsonResponse<List<RestUserRegistrationModel>> getPlanCard(@RequestParam String id) {
		logger.info("Method : getPlanCard starts");

		logger.info("Method :getPlanCard ends");
		return restUserRegistrationDao.getPlanCardDao(id);
	}
	/**
	 * Rest Controller - Get Speciality List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getSpecialityList")
	public JsonResponse<List<DropDownModel>> getSpecialityList(@RequestParam Integer id) {
		logger.info("Method : getSpecialityList starts");

		logger.info("Method : getSpecialityList ends");
		return restUserRegistrationDao.getSpecialityListDao(id);
	}
	/**
	 * Rest Controller - Get State List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getStateList")
	public JsonResponse<List<DropDownModel>> getStateList(@RequestParam Integer id) {
		logger.info("Method : getStateList starts");

		logger.info("Method : getStateList ends");
		return restUserRegistrationDao.getStateListDao(id);
	}

	/**
	 * Rest Controller - Get district List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistList")
	public JsonResponse<List<DropDownModel>> getDistList(@RequestParam String state) {
		logger.info("Method : getDistList starts");

		logger.info("Method : getDistList ends");
		return restUserRegistrationDao.getDistListDao(state);
	}

	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityList")
	public JsonResponse<List<DropDownModel>> getCityList(@RequestParam String dist) {
		logger.info("Method : getCityList starts");

		logger.info("Method : getCityList ends");
		return restUserRegistrationDao.getCityListDao(dist);
	}
	/*
	 * post mapping for add User Registration
	 */

	@RequestMapping(value = "rest-addUserRegistration", method = { RequestMethod.POST })
	public JsonResponse<Object> addUserRegistration(@RequestBody RestUserRegistrationModel regdModel) {
		logger.info("Method : addUserRegistration starts");

		logger.info("Method : addUserRegistration ends");
		return restUserRegistrationDao.addUserRegistrationDao(regdModel);
	}
	/*
	 * post mapping for add ProfRegistration
	 */

	@RequestMapping(value = "rest-addProfRegistration", method = { RequestMethod.POST })
	public JsonResponse<Object> addProfRegistration(@RequestBody RestUserRegistrationModel regdModel) {
		logger.info("Method : addProfRegistration starts");

		logger.info("Method : addProfRegistration ends");
		return restUserRegistrationDao.addProfRegistrationDao(regdModel);
	}
	
	/**
	 * Rest Controller - Get State List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getStateList-org")
	public JsonResponse<List<DropDownModel>> getStateListOrgDao(@RequestParam Integer id) {
		logger.info("Method : getStateList starts");

		logger.info("Method : getStateList ends");
		return restUserRegistrationDao.getStateListOrgDao(id);
	}
	
	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityList-org")
	public JsonResponse<List<DropDownModel>> getCityListsDao(@RequestParam String dist) {
		logger.info("Method : getCityList starts");

		logger.info("Method : getCityList ends");
		return restUserRegistrationDao.getCityListsDao(dist);
	}
	
	/**
	 * Rest Controller - Get district List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistList-org")
	public JsonResponse<List<DropDownModel>> getDistLists(@RequestParam String state) {
		logger.info("Method : getDistList starts");

		logger.info("Method : getDistList ends");
		return restUserRegistrationDao.getDistLists(state);
	}
	/*
	 * for Organisation Type list
	 */

	@RequestMapping(value = "getOrganisationTypeListLists", method = { RequestMethod.GET })
	public List<DropDownModel> organisationTypeListLists() {
		logger.info("Method : organisationTypeListLists starts");

		logger.info("Method : organisationTypeListLists ends");
		return restUserRegistrationDao.organisationTypeListLists();
	}
	/*rest-view-interest
	 * Post mapping for Add Organisation
	 */
	/*
	 * @PostMapping(value = "rest-addOrganisation") public JsonResponse<Object>
	 * addOrganisation(@RequestBody RestUserRegistrationModel employee) {
	 * logger.info("Method : addOrganisation starts");
	 * 
	 * logger.info("Method : addOrganisation ends"); return
	 * restUserRegistrationDao.addOrganisation(employee); }
	 */
	/*
	 * Post mapping for Add Guest Registration
	 */
	@PostMapping(value = "rest-guestRegd")
	public JsonResponse<Object> guestRegd(@RequestBody RestGuestRegdModel restGuestRegdModel) {
		logger.info("Method : guestRegd starts");
		
		logger.info("Method : guestRegd ends");
		return restUserRegistrationDao.guestRegd(restGuestRegdModel);
	}
	/*
	 * View User Interest
	 */
	@GetMapping(value = "rest-view-interest")
	public JsonResponse<List<RestUserRegistrationModel>> getUserInterest() {
		logger.info("Method : getUserInterest starts");

		logger.info("Method : getUserInterest ends");

		return restUserRegistrationDao.getUserInterestDao();
	}
	
	@RequestMapping(value="browserdDetails-ehealths", method={ RequestMethod.GET })
	public JsonResponse<Object> browserdDetails(@RequestParam String userId, @RequestParam String ip,
			@RequestParam String browserName) {
		logger.info("Method :browserdDetails starts");
		logger.info("Method :browserdDetails endss");
		return restUserRegistrationDao.browserdDetails(userId, ip, browserName);
	}
	
	
	@RequestMapping(value = "browserdDetails-ehealths-wrongcredential", method = { RequestMethod.GET })
	public JsonResponse<Object> browserdwrongcredential(@RequestParam String userId, @RequestParam String ip,

			@RequestParam String browserName, @RequestParam String wrong) {
		logger.info("Method :browserdwrongcredential starts");

		logger.info("Method :browserdwrongcredential ends");
		return restUserRegistrationDao.browserdwrongcredential(userId, ip, browserName, wrong);
	}
	
	@GetMapping(value = "rest-user-aadhaar-exists")
	public JsonResponse<List<DropDownModel>> getAadhaarExistsForReg(@RequestParam String aadhar) {
		logger.info("Method : getAadhaarExistsForReg starts");

		logger.info("Method : getAadhaarExistsForReg ends");

		return restUserRegistrationDao.getAadhaarExistsForRegDao(aadhar);
	}
	 
}
