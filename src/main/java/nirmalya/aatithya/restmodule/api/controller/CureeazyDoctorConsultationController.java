package nirmalya.aatithya.restmodule.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import nirmalya.aatithya.restmodule.api.dao.CureeazyDoctorConsultationDao;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyPaymentModel;
import nirmalya.aatithya.restmodule.api.model.DoctorAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.DoctorDashboardAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionModel;
import nirmalya.aatithya.restmodule.api.model.RestRatingDetailsModel;
import nirmalya.aatithya.restmodule.api.model.TimeSlotModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyDrAppointBookingAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasySpecializationListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyTestListAPIModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.util.DocumentUpload;

@RestController
@RequestMapping(value = "api")
public class CureeazyDoctorConsultationController {
	@Autowired
	EnvironmentVaribles env;

	@Autowired
	DocumentUpload documentUpload;

	@Autowired
	CureeazyDoctorConsultationDao doctorConsultationDao;

	Logger logger = LoggerFactory.getLogger(CureeazyDoctorConsultationController.class);

	// get-symptomsWithSpecialization-list
	// postman checked
	@GetMapping(value = "/get-symptomsWithSpecialization-list")
	public JsonResponse<CureEasySpecializationListAPIModel> symptomsWithSpecialization() {
		logger.info("Method :view symptomsWithSpecialization starts");
		logger.info("Method :view symptomsWithSpecialization ends");
		return doctorConsultationDao.symptomsWithSpecialization();
	}

	// get-specializationWiseDoctor-list
	@GetMapping(value = "/get-specializationWiseDoctor-list")
	public ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>> getSpecializationWiseDoctorList(
			@RequestParam String specializationId) {
		logger.info("Method : getSpecializationWiseDoctorList starts");

		logger.info("Method : getSpecializationWiseDoctorList ends");
		return doctorConsultationDao.getSpecializationWiseDoctorList(specializationId);
	}

	// get-searchWiseDoctor-list
	@GetMapping(value = "/get-searchWiseDoctor-list")
	public ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>> getSearchWiseDoctorList(
			@RequestParam String searchdata) {
		logger.info("Method : getSearchWiseDoctorList starts");

		logger.info("Method : getSearchWiseDoctorList ends");
		return doctorConsultationDao.getSearchWiseDoctorList(searchdata);
	}

	// get doctor profile
	@GetMapping(value = "/get-doctor-profile")
	public ResponseEntity<JsonResponse<CureeazyDoctorConsultationModel>> getDoctorProfile(@RequestParam String doctorId,
			String userId) {
		logger.info("Method :view getDoctorProfile starts");
		logger.info("Method :view getDoctorProfile ends");
		return doctorConsultationDao.getDoctorProfile(doctorId, userId);
	}

	// get-doctor-timeSlot-list
	@GetMapping(value = "/get-doctor-timeSlot-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorTimeSlotList(@RequestParam String doctorId,
			String slotDate) {
		logger.info("Method : getDoctorTimeSlotList starts");

		logger.info("Method : getDoctorTimeSlotList ends");
		return doctorConsultationDao.getDoctorTimeSlotList(doctorId, slotDate);
	}

	// get-doctor-timeSlot-list
	@GetMapping(value = "/get-doctor-timeSlot-list-doctor")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorTimeSlotLists(@RequestParam String doctorId,
			String slotDate) {
		logger.info("Method : getDoctorTimeSlotList starts");

		logger.info("Method : getDoctorTimeSlotList ends");
		return doctorConsultationDao.getDoctorTimeSlotLists(doctorId, slotDate);
	}
	// post Doctor Appointment Api
	@PostMapping(value = "/post-doctor-appointment-api")
	public ResponseEntity<JsonResponse<CureEasyDrAppointBookingAPIModel>> postDoctorAppointmentApi(
			@RequestBody CureEasyDrAppointBookingAPIModel drAppointmentBookApiModel) {
		logger.info("Method : postDoctorAppointmentApi starts");

		logger.info("Method : postDoctorAppointmentApi ends");
		return doctorConsultationDao.postDoctorAppointmentApi(drAppointmentBookApiModel);
	}

	// post Doctor Appointment payment Details Api
	@PostMapping(value = "/post-doctor-appointment-payment-dtls")
	public ResponseEntity<JsonResponse<Object>> postDoctorAppointmentPaymentDtls(
			@RequestBody CureEasyDrAppointBookingAPIModel drAppointmentBookApiModel) {
		logger.info("Method : postDoctorAppointmentPaymentDtls starts");

		logger.info("Method : postDoctorAppointmentPaymentDtls ends");
		return doctorConsultationDao.postDoctorAppointmentPaymentDtls(drAppointmentBookApiModel);
	}

	// post Favorite Doctor
	@PostMapping(value = "/post-favorite-doctor-api")
	public ResponseEntity<JsonResponse<Object>> postFavoriteDoctorApi(
			@RequestBody CureeazyDoctorConsultationModel drConsultationModel) {
		logger.info("Method : postFavoriteDoctorApi starts");

		logger.info("Method : postFavoriteDoctorApi ends");
		return doctorConsultationDao.postFavoriteDoctorApi(drConsultationModel);
	}

	// get-favorite-doctor-list
	@GetMapping(value = "/get-favorite-doctor-list")
	public ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>> getFavoriteDoctorList(
			@RequestParam String userId) {
		logger.info("Method : getFavoriteDoctorList starts");

		logger.info("Method : getFavoriteDoctorList ends");
		return doctorConsultationDao.getFavoriteDoctorList(userId);
	}

	// Get user appointment details
	@GetMapping(value = "/get-view-user-appoointment-details")
	public ResponseEntity<JsonResponse<List<CureEasyDrAppointBookingAPIModel>>> getUserAppointmentDetails(
			@RequestParam String userId, String status) {
		logger.info("Method : getUserAppointmentDetails starts");

		logger.info("Method : getUserAppointmentDetails ends");
		return doctorConsultationDao.getUserAppointmentDetails(userId, status);
	}

	// get doctor appointment details
	@GetMapping(value = "/get-doctor-appointment-details")
	public ResponseEntity<JsonResponse<List<CureEasyDrAppointBookingAPIModel>>> getdoctorappointmentdetails(
			@RequestParam String userId, @RequestParam String apptId) {
		logger.info("Method : getdoctorappointmentdetails starts");
		logger.info("Method : getdoctorappointmentdetails ends");
		return doctorConsultationDao.getdoctorappointmentdetails(userId, apptId);
	}

	// get medicine dropdown
	@GetMapping(value = "/get-medicinedropdown-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getmedicinedropdownList() {
		logger.info("Method : getmedicinedropdownList starts");

		logger.info("Method : getmedicinedropdownList ends");
		return doctorConsultationDao.getmedicinedropdownList();
	}

	// get test name autosearch
	@GetMapping(value = "/get-medicine-autosearch")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> gettestnameautosearch(@RequestParam String searchdata) {
		logger.info("Method : gettestnameautosearch starts");

		logger.info("Method : gettestnameautosearch ends");
		return doctorConsultationDao.gettestnameautosearch(searchdata);
	}

	/*
	 * post mapping for prescription 
	 */

	@RequestMapping(value = "/post-prescription-api", method = { RequestMethod.POST })
	public JsonResponse<PrescriptionModel> postprescriptionApi(@RequestBody PrescriptionModel prescriptionModel) {
		logger.info("Method : postprescriptionApi starts");

		logger.info("Method : postprescriptionApi ends");
		return doctorConsultationDao.postprescriptionApi(prescriptionModel);
	}
	
	//get search patient name and number wise
	@GetMapping(value = "/get-searchPatientNameWithNumber")
	public ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>> searchPatientNameWithNumber(@RequestParam String searchdata) {
		logger.info("Method : searchPatientNameWithNumber starts");
		
		logger.info("Method : searchPatientNameWithNumber ends");
		return doctorConsultationDao.searchPatientNameWithNumber(searchdata);
	}
	
	// post Doctor Appointment Api
	@PostMapping(value = "/post-prescription-manual")
	public ResponseEntity<JsonResponse<PrescriptionModel>> postManualPrescription(
			@RequestBody PrescriptionModel prescriptionModel) {
		logger.info("Method : postManualPrescription starts");

		logger.info("Method : postManualPrescription ends");
		return doctorConsultationDao.postManualPrescription(prescriptionModel);
	}
	
	// post Favorite Doctor
	@PostMapping(value = "/post-join-meeeting")
	public ResponseEntity<JsonResponse<Object>> postJoinMeeting(
			@RequestBody DropDownModel dropDownModel) {
		logger.info("Method : postJoinMeeting starts");

		logger.info("Method : postJoinMeeting ends");
		return doctorConsultationDao.postJoinMeeting(dropDownModel);
	}
	
	// get doctor appointment details
	@GetMapping(value = "/get-patient-showCode")
	public ResponseEntity<JsonResponse<DropDownModel>> getShowCode(@RequestParam String apptId) {
		logger.info("Method : getShowCode starts");
		logger.info("Method : getShowCode ends");
		return doctorConsultationDao.getShowCode(apptId);
	}
	// get-searchWiseDoctor-list
		@GetMapping(value = "/get-searchPatient-list")
		public ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>> getSearchPatientList(
				@RequestParam String searchdata) {
			logger.info("Method : getSearchPatientList starts");

			logger.info("Method : getSearchPatientList ends");
			return doctorConsultationDao.getSearchPatientList(searchdata);
		}
		//rating details api
				@GetMapping(value = "get-rating-details")
				public JsonResponse<List<RestRatingDetailsModel>> getRatingDetails(@RequestParam String pid,@RequestParam String doctorId) {
					logger.info("Method : getRatingDetails starts"+pid   +" "+doctorId);

					logger.info("Method :getRatingDetails endss");
					return doctorConsultationDao.getRatingDetails(pid,doctorId);
				}
				
				//rating details api
				@GetMapping(value = "get-rating-details-doctor")
				public JsonResponse<List<RestRatingDetailsModel>> getRatingDetailsDoctor(@RequestParam String doctorId) {
					logger.info("Method : getRatingDetailsDoctor starts"+doctorId);

					logger.info("Method :getRatingDetailsDoctor endss");
					return doctorConsultationDao.getRatingDetailsDoctor(doctorId);
				}
}
