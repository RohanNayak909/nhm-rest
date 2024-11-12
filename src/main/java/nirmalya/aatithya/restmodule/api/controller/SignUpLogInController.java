package nirmalya.aatithya.restmodule.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.SignUpLogInDao;
import nirmalya.aatithya.restmodule.api.model.APIActivityLogModel;
import nirmalya.aatithya.restmodule.api.model.APIOrganizationModel;
import nirmalya.aatithya.restmodule.api.model.APITestDropdownModel;
import nirmalya.aatithya.restmodule.api.model.ApiUserModel;
import nirmalya.aatithya.restmodule.api.model.CatagoryModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.OtherUsersProfileModel;
import nirmalya.aatithya.restmodule.api.model.PatientDetailsAPI;
import nirmalya.aatithya.restmodule.api.model.SignUpModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileAPIModel;
import nirmalya.aatithya.restmodule.api.model.UsernameModel;
import nirmalya.aatithya.restmodule.api.model.VersionModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.Util;

@RestController
@RequestMapping(value = "api")
public class SignUpLogInController {

	@Autowired
	SignUpLogInDao sigUpLogInDao;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(SignUpLogInController.class);

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ApiUserModel>> getOtp(@RequestParam String mobileNo,
			@RequestParam String password) {
		logger.info("Method : getOtp starts"+mobileNo +password );
		logger.info("Method : getOtp ends");
		return sigUpLogInDao.getLoginDetails(mobileNo, password);
	}

	@RequestMapping(value = "/login-with-otp", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ApiUserModel>> getLogInWithOTP(@RequestParam String mobileNo,@RequestParam String otpKey) {
		logger.info("Method : getLogInWithOTP starts");
		System.out.println("mobileNo"+mobileNo);
		System.out.println("otpKey"+otpKey);
		logger.info("Method : getLogInWithOTP ends");
		return sigUpLogInDao.getLoginDetailsByMob(mobileNo,otpKey);
	}

	/*
	 * @RequestMapping(value = "/login", method = { RequestMethod.GET }) public
	 * ResponseEntity<JsonResponse<ApiUserModel>> getOtp(@RequestParam String
	 * mobileNo,
	 * 
	 * @RequestParam String password) { logger.info("Method : getOtp starts");
	 * 
	 * JsonResponse<ApiUserModel> jsonResponse = new JsonResponse<ApiUserModel>();
	 * 
	 * Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
	 * "Please update App");
	 * 
	 * ResponseEntity<JsonResponse<ApiUserModel>> response = new
	 * ResponseEntity<JsonResponse<ApiUserModel>>( jsonResponse, HttpStatus.OK);
	 * 
	 * logger.info("Method : getOtp ends"); return response; }
	 */

	/*
	 * @RequestMapping(value = "/login-with-otp", method = { RequestMethod.GET })
	 * public ResponseEntity<JsonResponse<ApiUserModel>>
	 * getLogInWithOTP(@RequestParam String mobileNo) {
	 * logger.info("Method : getLogInWithOTP ends"); JsonResponse<ApiUserModel>
	 * jsonResponse = new JsonResponse<ApiUserModel>();
	 * 
	 * Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
	 * "Please update App");
	 * 
	 * ResponseEntity<JsonResponse<ApiUserModel>> response = new
	 * ResponseEntity<JsonResponse<ApiUserModel>>( jsonResponse, HttpStatus.OK);
	 * 
	 * logger.info("Method : getLogInWithOTP ends"); return response; }
	 */

	/** Fetch Country List **/
	@GetMapping(value = "/get-country-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> countryList() {
		logger.info("Method : countryList starts");

//		URLShortener u = new URLShortener(5, "http://ehsystem.in/");
//		
//		String longurl = "https://ehealthsystem.com/user/view-patient-test-report-pdf-download?id=OTk5OTkxNzUwNTM4NzQzOQ==&id2=2021-10-21";
//		
//		String shorturl = u.shortenURL(longurl);
//		
//		System.out.println("Long URL = "+longurl);
//		System.out.println("Short URL = "+shorturl);

		logger.info("Method : countryList ends");
		return sigUpLogInDao.getCountryList();
	}

	/** Fetch User Title List **/
	@GetMapping(value = "/get-user-title-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> userTitleList() {
		logger.info("Method : userTitleList starts");

		logger.info("Method : userTitleList ends");
		return sigUpLogInDao.getUserTitleList();
	}

	/** Fetch Organization List **/
	@GetMapping(value = "/get-hospital-list-for-doc-app")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getHospitalList() {
		logger.info("Method : getHospitalList starts");

		logger.info("Method : getHospitalList ends");
		return sigUpLogInDao.getHospitalList();
	}

	/** Fetch Gender Type List **/
	@GetMapping(value = "/get-gender-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> genderList() {
		logger.info("Method : genderList starts");

		logger.info("Method : genderList ends");
		return sigUpLogInDao.getGenderList();
	}

	@GetMapping(value = "/get-bloodgroup-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> bloodGroupList() {
		logger.info("Method : bloodGroupList starts");

//		String msg = "Welcome to eHealthApp. Your userid is 8917225033 password is User@123. Please click on OK to activate your account.";
//		
//		try {
//			CommonUsed.sendSMS("918917225033", msg);
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		logger.info("Method : bloodGroupList ends");
		return sigUpLogInDao.getBloodGroupList();
	}

	/** Fetch User Title List **/
	@GetMapping(value = "/get-state-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> stateList(@RequestParam Integer country) {
		logger.info("Method : stateList starts");

		logger.info("Method : stateList ends");
		return sigUpLogInDao.getStateList(country);
	}

	/** Fetch User Title List **/
	@GetMapping(value = "/get-district-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> districtList(@RequestParam Integer state) {
		logger.info("Method : districtList starts");

		logger.info("Method : districtList ends");
		return sigUpLogInDao.getDistrictList(state);
	}

	/** Fetch User Title List **/
	@GetMapping(value = "/get-city-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> cityList(@RequestParam Integer district) {
		logger.info("Method : cityList starts");

		logger.info("Method : cityList ends");
		return sigUpLogInDao.getCityList(district);
	}

	@GetMapping(value = "/get-hospital-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> hospitalList(@RequestParam String doctor) {
		logger.info("Method : hospitalList starts");

		logger.info("Method : hospitalList ends");
		return sigUpLogInDao.getHospitalList(doctor);
	}

	/** Fetch all organization List **/
	@GetMapping(value = "/get-all-organization-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> organizationList() {
		logger.info("Method : organizationList starts");

		logger.info("Method : organizationList ends");
		return sigUpLogInDao.organizationList();
	}

	/** Fetch Gender Type List **/
	@GetMapping(value = "/get-doctor-speciality-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> doctorSpecialityList() {
		logger.info("Method : specialityList starts");

		logger.info("Method : specialityList ends");
		return sigUpLogInDao.getDoctorSpecialityList();
	}

	@GetMapping(value = "/get-health-provider-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> healthProviderList() {
		logger.info("Method : healthProviderList starts");

		logger.info("Method : healthProviderList ends");
		return sigUpLogInDao.getHealthProviderList();
	}

	@GetMapping(value = "/get-relation-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> relationList() {
		logger.info("Method : relationList starts");

		logger.info("Method : relationList ends");
		return sigUpLogInDao.getRelationList();
	}

	@GetMapping(value = "/get-pharmacy-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> pharmacyList() {
		logger.info("Method : pharmacyList starts");

		logger.info("Method : pharmacyList ends");
		return sigUpLogInDao.getPharmacyList();
	}

	@GetMapping(value = "/get-adm-equipment-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> admEquipmentList() {
		logger.info("Method : admEquipmentList starts");

		logger.info("Method : admEquipmentList ends");
		return sigUpLogInDao.getAdmEquipmentList();
	}

	@GetMapping(value = "/get-allergy-name-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> allergyNameList() {
		logger.info("Method : allergyNameList starts");

		logger.info("Method : allergyNameList ends");
		return sigUpLogInDao.getallergyNameListDao();
	}

	@GetMapping(value = "/get-allergy-type-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> allergyTypeList() {
		logger.info("Method : allergyTypeList starts");

		logger.info("Method : allergyTypeList ends");
		return sigUpLogInDao.getallergyTypeListDao();
	}

	@GetMapping(value = "/get-doctor-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> doctorList(@RequestParam Integer type,
			@RequestParam Integer city) {
		logger.info("Method : doctorList starts");

		logger.info("Method : doctorList ends");
		return sigUpLogInDao.getDoctorList(type, city);
	}

	/** Patient Register By Pathologist **/
	@PostMapping(value = "/signup-by-pathologist")
	public ResponseEntity<JsonResponse<Object>> signUpByPathologist(@RequestBody SignUpModel data) {
		logger.info("Method : signUpByPathologist starts");

		logger.info("Method : signUpByPathologist ends");
		return sigUpLogInDao.signUpByPathologistDao(data);
	}

	/** Fetch Patient Registration List **/
	@GetMapping(value = "/get-patient-registration-list")
	public ResponseEntity<JsonResponse<List<SignUpModel>>> getPatientRegistrationList(@RequestParam String userid) {
		logger.info("Method : getPatientRegistrationList starts");

		logger.info("Method : getPatientRegistrationList ends");
		return sigUpLogInDao.getPatientRegistrationList(userid);
	}

	/** Fetch Patient Registration List **/
	@GetMapping(value = "/get-patient-details")
	public ResponseEntity<JsonResponse<UserProfileAPIModel>> getPatientDetailsRestApi(@RequestParam String userid) {
		logger.info("Method : getPatientDetailsRestApi starts");

		logger.info("Method : getPatientDetailsRestApi ends");
		return sigUpLogInDao.getPatientDetailsRestApi(userid);
	}

	@GetMapping(value = "/other-user-profile")
	public ResponseEntity<JsonResponse<OtherUsersProfileModel>> othersUserProfile(@RequestParam String userid) {
		logger.info("Method : othersUserProfile starts");

		logger.info("Method : othersUserProfile ends");
		return sigUpLogInDao.othersUserProfile(userid);
	}

	@GetMapping(value = "/get-ambulance-org-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> ambulanceList() {
		logger.info("Method : ambulanceList starts");

		logger.info("Method : ambulanceList ends");
		return sigUpLogInDao.ambulanceList();
	}

	@GetMapping(value = "/get-bloodbank-org-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> bloodbankList() {
		logger.info("Method : bloodbankList starts");

		logger.info("Method : bloodbankList ends");
		return sigUpLogInDao.bloodbankList();
	}

	@GetMapping(value = "/get-ngo-org-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> ngoList() {
		logger.info("Method : ngoList  starts");

		logger.info("Method : ngoList ends");
		return sigUpLogInDao.ngoList();
	}

	@GetMapping(value = "/get-pathology-lab-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> pathologylabList() {
		logger.info("Method : pathologylabList  starts");

		logger.info("Method : pathologylabList ends");
		return sigUpLogInDao.getpathologylabList();
	}

	@GetMapping(value = "/get-testname-list")
	public ResponseEntity<JsonResponse<List<APITestDropdownModel>>> testnameList() {
		logger.info("Method : testnameList  starts");

		logger.info("Method : testnameList ends");
		return sigUpLogInDao.gettestNameList();
	}

	@GetMapping(value = "/get-organ-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> organList() {
		logger.info("Method : organList  starts");

		logger.info("Method : organList ends");
		return sigUpLogInDao.getOrganList();
	}

	@GetMapping(value = "/get-tissue-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> tissueList() {
		logger.info("Method : tissueList  starts");

		logger.info("Method : tissueList ends");
		return sigUpLogInDao.getTissueList();
	}

//	@GetMapping(value = "/get-organ-list")
//	public ResponseEntity<Object> getOrganList() {
//		logger.info("Method : getOrganList  starts");
//		
//		logger.info("Method : getOrganList ends");
//		return sigUpLogInDao.getOrganList();
//	}

	/** Forgot Password - Check **/
	@PostMapping(value = "/forgot-password-get-otp")
	public ResponseEntity<JsonResponse<Object>> getOTPForFOrgotPassword(@RequestBody DropDownModel data) {
		logger.info("Method : getOTPForFOrgotPassword starts");

		logger.info("Method : getOTPForFOrgotPassword ends");
		return sigUpLogInDao.getOTPForFOrgotPassword(data);
	}

	/** Forgot Password - Change Password **/
	@PostMapping(value = "/change-password")
	public ResponseEntity<JsonResponse<Object>> changePassword(@RequestBody DropDownModel data) {
		logger.info("Method : changePassword starts"+data);

		logger.info("Method : changePassword ends");
		return sigUpLogInDao.changePassword(data);
	}

	@PostMapping(value = "/change-password-user-wise")
	public ResponseEntity<JsonResponse<Object>> changePasswordUserWiseAfterLogIn(@RequestBody DropDownModel data) {
		logger.info("Method : changePasswordUserWiseAfterLogIn starts");

		logger.info("Method : changePasswordUserWiseAfterLogIn ends");
		return sigUpLogInDao.changePasswordUserWiseAfterLogIn(data);
	}

	@PostMapping(value = "/forgot-userid-get-otp")
	public ResponseEntity<JsonResponse<Object>> getOTPForForgotUserid(@RequestBody DropDownModel data) {
		logger.info("Method : getOTPForForgotUserid starts");

		logger.info("Method : getOTPForForgotUserid ends");
		return sigUpLogInDao.getOTPForForgotUserid(data);
	}

	@PostMapping(value = "/forgot-userid-send-sms")
	public ResponseEntity<JsonResponse<Object>> sensSMSToUserForForgotUserid(@RequestBody DropDownModel data) {
		logger.info("Method : sensSMSToUserForForgotUserid starts");

		logger.info("Method : sensSMSToUserForForgotUserid ends");
		return sigUpLogInDao.sensSMSToUserForForgotUserid(data);
	}

	@GetMapping(value = "/get-disease-info-list")
	public ResponseEntity<Object> getDiseaseInfoList() {
		logger.info("Method : getDiseaseInfoList starts");

		logger.info("Method : getDiseaseInfoList ends");
		return sigUpLogInDao.getDiseaseInfoList();
	}

	@GetMapping(value = "/get-pharmacy-list-by-searchvalue")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> pharmacyListBySearchValue(@RequestParam String search) {
		logger.info("Method : pharmacyListBySearchValue starts");

		logger.info("Method : pharmacyListBySearchValue ends");
		return sigUpLogInDao.getPharmacyListBySearchValue(search);
	}

	@GetMapping(value = "/get-lab-list-by-searchvalue")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> labListBySearchValue(@RequestParam String search) {
		logger.info("Method : labListBySearchValue starts");

		logger.info("Method : labListBySearchValue ends");
		return sigUpLogInDao.getLabListBySearchValue(search);
	}

	@GetMapping(value = "/get-doctor-list-by-org")
	public ResponseEntity<JsonResponse<List<CountryModel>>> doctorListByOrganization(@RequestParam String userid) {
		logger.info("Method : doctorListByOrganization starts");

		logger.info("Method : doctorListByOrganization ends");
		return sigUpLogInDao.getDoctorListByOrganization(userid);
	}

	@GetMapping(value = "/get-phc-list-by-id")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPhcListByid(@RequestParam String userid) {
		logger.info("Method : getPhcListByid starts");

		logger.info("Method : getPhcListByid ends");
		return sigUpLogInDao.getPhcListByidDao(userid);
	}

	@PostMapping(value = "/post-update-user-phc-api")
	public ResponseEntity<JsonResponse<Object>> updateUserPHCAPI(@RequestBody DropDownModel data) {
		logger.info("Method : updateUserPHCAPI starts");

		logger.info("Method : updateUserPHCAPI ends");
		return sigUpLogInDao.updateUserPHCAPI(data);
	}

	@PostMapping(value = "/post-add-organization-api", headers = "content-type=multipart/*", consumes = {
			"application/*" })
	public ResponseEntity<JsonResponse<Object>> postAddOrganizationAPI(APIOrganizationModel data) {
		logger.info("Method : postAddOrganizationAPI starts");

		logger.info("Method : postAddOrganizationAPI ends");
		return sigUpLogInDao.postAddOrganizationAPI(data);
	}

	@RequestMapping(value = "/login-multiple-user", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ApiUserModel>>> getLoginMultipleUser(@RequestParam String mobileNo,
			@RequestParam String password) {
		logger.info("Method : getLoginMultipleUser starts");
		logger.info("Method : getLoginMultipleUser ends");
		return sigUpLogInDao.getLoginMultipleUserDetails(mobileNo, password);
	}

	@RequestMapping(value = "/login-multiple-user-with-otp", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ApiUserModel>>> getLogInMultipleUserWithOTP(@RequestParam String mobileNo) {
		logger.info("Method : getLogInMultipleUserWithOTP starts");
		logger.info("Method : getLogInMultipleUserWithOTP ends");
		return sigUpLogInDao.getLoginMultipleUserDetailsByMob(mobileNo);
	}

	@RequestMapping(value = "/get-role-api", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> getRoleAPI(@RequestParam String id) {
		logger.info("Method : getRoleAPI starts");
		logger.info("Method : getRoleAPI ends");
		return sigUpLogInDao.getRoleAPI(id);
	}

	/* Fetch Syndicate Organization List */
	@GetMapping(value = "/get-syndicate-partner-organization")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getSyndicateOrganizationList() {
		logger.info("Method : getSyndicateOrganizationList starts");

		logger.info("Method : getSyndicateOrganizationList ends");
		return sigUpLogInDao.getSyndicateOrganizationList();
	}

	/* Fetch Patient Details through id */
	@GetMapping(value = "/get-patient-details-throughid")
	public ResponseEntity<JsonResponse<List<PatientDetailsAPI>>> getPatientDetailsThroughId(
			@RequestParam String userid) {
		logger.info("Method : getPatientDetailsThroughId starts");

		logger.info("Method : getPatientDetailsThroughId ends");
		return sigUpLogInDao.getPatientDetailsThroughId(userid);
	}

	@RequestMapping(value = "/get-version", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<VersionModel>> getVersion() {
		logger.info("Method : getVersion starts");
		logger.info("Method : getVersion ends");
		return sigUpLogInDao.getVersion();
	}

	@PostMapping(value = "/post-activity-log-api")
	public ResponseEntity<JsonResponse<Object>> postActivityLogAPI(@RequestBody APIActivityLogModel data) {
		logger.info("Method : postActivityLogAPI starts"+data);

		logger.info("Method : postActivityLogAPI ends");
		return sigUpLogInDao.postActivityLogAPI(data);
	}

	@GetMapping(value = "/get-organization-list-lab")
	public ResponseEntity<JsonResponse<List<CountryModel>>> organizationListApi(@RequestParam String cityId) {
		logger.info("Method : organizationListApi starts");

		logger.info("Method : organizationListApi ends");
		return sigUpLogInDao.organizationListApi(cityId);
	}

	@GetMapping(value = "/get-catagory-subcategory-list")
	public JsonResponse<List<CatagoryModel>> getCatagoryList() {
		logger.info("Method : getCatagoryList starts");

		logger.info("Method : getCatagoryList ends");
		return sigUpLogInDao.getUserInterestDao();
	}

	@GetMapping(value = "/get-pet-list")
	public JsonResponse<List<DropDownModel>> getPetList() {
		logger.info("Method : getPetList starts");

		logger.info("Method : getPetList ends");
		return sigUpLogInDao.getPetList();
	}

	@GetMapping(value = "/get-otp-for-username")
	public JsonResponse<Object> getOTPForUserName(@RequestParam String uhid, @RequestParam String username) {
		logger.info("Method : getOTPForUserName starts");

		logger.info("Method : getOTPForUserName ends");
		return sigUpLogInDao.getOTPForUserName(uhid, username);
	}

	@PostMapping(value = "/post-update-username")
	public JsonResponse<Object> postUpdateUsername(@RequestBody UsernameModel data) {
		logger.info("Method : postUpdateUsername starts");

		logger.info("Method : postUpdateUsername ends");
		return sigUpLogInDao.postUpdateUsername(data);
	}
	
	/* Forgot Password - Check */
	@PostMapping(value = "/forgot-password-get-user-otp")
	public ResponseEntity<JsonResponse<Object>> getOTPFOrgotPassword(@RequestBody DropDownModel data) {
		logger.info("Method : getOTPFOrgotPassword starts");

		logger.info("Method : getOTPFOrgotPassword ends");
		return sigUpLogInDao.getOTPFOrgotPassword(data);
	}
}
