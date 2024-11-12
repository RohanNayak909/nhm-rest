package nirmalya.aatithya.restmodule.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.GEOAPIIntegrationDao;
import nirmalya.aatithya.restmodule.api.model.APIPharmacyModel;
import nirmalya.aatithya.restmodule.api.model.APIStatusModel;
import nirmalya.aatithya.restmodule.api.model.DoctorAppointmentModel;
import nirmalya.aatithya.restmodule.api.model.GEOAPIMedicationModel;
import nirmalya.aatithya.restmodule.api.model.UserRegistrationGeoModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class GEOAPIIntegrationController {
	
	@Autowired
	GEOAPIIntegrationDao gEOAPIIntegrationDao;
	
	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(SignUpLogInController.class);
	
	/** Patient Register By GeoMitra **/
	@PostMapping(value = "/geo-user-registration")
	public ResponseEntity<JsonResponse<Object>> userRegistrationByGeo(@RequestBody UserRegistrationGeoModel data) {
		logger.info("Method : userRegistrationByGeo starts");
		
		logger.info("Method : userRegistrationByGeo ends");
		return gEOAPIIntegrationDao.userRegistrationByGeo(data);
	}
	
	/** POST DOC APPOINTMENT By GeoMitra **/
	@PostMapping(value = "/geo-post-doctor-appointment")
	public ResponseEntity<JsonResponse<Object>> geoPostDoctorAppointment(@RequestBody DoctorAppointmentModel data) {
		logger.info("Method : geoPostDoctorAppointment starts");
		
		logger.info("Method : geoPostDoctorAppointment ends");
		return gEOAPIIntegrationDao.geoPostDoctorAppointment(data);
	}
	
	/** Change APPOINTMENT Status By GeoMitra **/
	@PostMapping(value = "/geo-update-user-appointment-status")
	public ResponseEntity<JsonResponse<Object>> geoUpdateUserAppointmentStatus(@RequestBody APIStatusModel data) {
		logger.info("Method : geoUpdateUserAppointmentStatus starts");
		
		logger.info("Method : geoUpdateUserAppointmentStatus ends");
		return gEOAPIIntegrationDao.geoUpdateUserAppointmentStatus(data);
	}
	
	/** POST DOCTOR MEDICATION By GeoMitra **/
	@PostMapping(value = "/geo-post-user-medication-doctor")
	public ResponseEntity<JsonResponse<Object>> geoPostUserMedicationDoctor(@RequestBody GEOAPIMedicationModel data) {
		logger.info("Method : geoPostUserMedicationDoctor starts");
		
		logger.info("Method : geoPostUserMedicationDoctor ends");
		return gEOAPIIntegrationDao.geoPostUserMedicationDoctor(data);
	}
	
	/** POST DOCTOR TEST By GeoMitra **/
	@PostMapping(value = "/geo-post-user-test-by-doctor")
	public ResponseEntity<JsonResponse<Object>> geoPostUserTestByDoctor(@RequestBody GEOAPIMedicationModel data) {
		logger.info("Method : geoPostUserTestByDoctor starts");
		
		logger.info("Method : geoPostUserTestByDoctor ends");
		return gEOAPIIntegrationDao.geoPostUserTestByDoctor(data);
	}
	
	/** POST Pharmacy request By GeoMitra **/
	@PostMapping(value = "/geo-post-user-pharmacy-request")
	public ResponseEntity<Object> geoPostPharmacyRequestApi(@RequestBody APIPharmacyModel data) {
		logger.info("Method : geoPostPharmacyRequestApi starts");
		
		logger.info("Method : geoPostPharmacyRequestApi ends");
		return gEOAPIIntegrationDao.geoPostPharmacyRequestApi(data);
	}
	
}
