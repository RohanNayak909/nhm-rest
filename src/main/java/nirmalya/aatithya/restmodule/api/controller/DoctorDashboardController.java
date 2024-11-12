package nirmalya.aatithya.restmodule.api.controller;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import nirmalya.aatithya.restmodule.api.dao.DoctorDashboardDao;
import nirmalya.aatithya.restmodule.api.model.DoctorAppointmentHistoryModel;
import nirmalya.aatithya.restmodule.api.model.DoctorDashboardAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.DoctorProfileModel;
import nirmalya.aatithya.restmodule.api.model.DoctorProfileUpdateModel;
import nirmalya.aatithya.restmodule.api.model.PatientAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.PaymentGatewayAPIModel;
import nirmalya.aatithya.restmodule.api.model.TestNamedropdownModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;

@RestController
@RequestMapping(value = "api")
public class DoctorDashboardController {
	@Autowired
	DoctorDashboardDao doctorDashboardDao;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(DoctorDashboardController.class);

	// get doctor profile details
	@GetMapping(value = "/get-doctor-profile-list")
	public ResponseEntity<JsonResponse<List<DoctorProfileModel>>> getdoctorprofileview(@RequestParam String doctorId) {
		logger.info("Method : getdoctorprofileview starts");
		logger.info("Method : getdoctorprofileview ends");
		return doctorDashboardDao.getdoctorprofileview(doctorId);
	}

	/** Update User Profile **/
	@PostMapping(value = "/update-doctor-profile-cureez")
	public ResponseEntity<JsonResponse<Object>> updateDoctorProfile(
			@RequestBody DoctorProfileModel doctorProfileModel) {
		logger.info("Method : updateDoctorProfile starts");

		logger.info("Method : updateDoctorProfile ends");
		return doctorDashboardDao.updateDoctorProfile(doctorProfileModel);
	}

	// get doctor profileimg view
	@GetMapping(value = "/get-doctor-profileimg-view")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctProfileImgView(@RequestParam String doctorId) {
		logger.info("Method :view getDoctProfileImgView starts");
		logger.info("Method :view getDoctProfileImgView ends");
		return doctorDashboardDao.getDoctProfileImgView(doctorId);
	}

	// get doctor dashboard appointment details
	@GetMapping(value = "/get-doctor-dashboard-appointment-details")
	public ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>> getdoctordashboardappointmentdetails(
			@RequestParam String doctorId, @RequestParam String status) {
		logger.info("Method : getdoctorappointmentdetails starts");
		logger.info("Method : getdoctorappointmentdetails ends");
		return doctorDashboardDao.getdoctordashboardappointmentdetails(doctorId, status);
	}

	// post update appointment
	@GetMapping(value = "/getDoctorAppointmentstatus-update-api")
	public ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> getDoctorAppointmentstatus(@RequestParam String apptId,
			@RequestParam String status, @RequestParam String doctorid) {
		logger.info("Method : getDoctorAppointmentstatus starts");
		logger.info("Method : getDoctorAppointmentstatus ends");
		return doctorDashboardDao.getDoctorAppointmentstatus(apptId, status, doctorid);
	}

	// get view doctor appointment details
	@GetMapping(value = "/get-view-patient-appointment-details")
	public ResponseEntity<JsonResponse<List<PatientAppointmentDetailsModel>>> getviewpatientappointmentdetails(
			@RequestParam String doctorId, @RequestParam String orderId) {
		logger.info("Method : getviewpatientappointmentdetails starts");
		logger.info("Method : getviewpatientappointmentdetails ends");
		return doctorDashboardDao.getviewpatientappointmentdetails(doctorId, orderId);
	}

	// Cancel Appointment
	@GetMapping(value = "/doctor-cancel-appointment-api")
	public ResponseEntity<JsonResponse<Object>> getDoctorCancelAppointment(@RequestParam String apptId,
			@RequestParam String status, @RequestParam String reason, @RequestParam String doctorid) {
		logger.info("Method : getDoctorCancelAppointment starts");
		logger.info("Method : getDoctorCancelAppointment ends");
		return doctorDashboardDao.getDoctorCancelAppointment(apptId, status, reason, doctorid);
	}

	// Cancel Appointment
	@GetMapping(value = "/user-cancel-appointment-api")
	public ResponseEntity<JsonResponse<Object>> getUserCancelAppointment(@RequestParam String apptId,
			@RequestParam String reason, @RequestParam String userid) {
		logger.info("Method : getUserCancelAppointment starts");
		logger.info("Method : getUserCancelAppointment ends");
		return doctorDashboardDao.getUserCancelAppointment(apptId, reason, userid);
	}

	// post post-update-doctor-profile
	@PostMapping(value = "/post-update-doctor-profile")
	public ResponseEntity<JsonResponse<DoctorProfileUpdateModel>> postUpdateDoctorProfile(
			@RequestBody DoctorProfileUpdateModel doctorProfileUpdateModel) {
		logger.info("Method : postUpdateDoctorProfile start");
		logger.info("Method : postUpdateDoctorProfile End");
		return doctorDashboardDao.postUpdateDoctorProfile(doctorProfileUpdateModel);
	}

	// get doctor appointment history
	@GetMapping(value = "/get-doctor-appointment-history")
	public ResponseEntity<JsonResponse<List<DoctorAppointmentHistoryModel>>> getdoctorappointmenthistory(
			@RequestParam String doctorId, @RequestParam String status) {
		logger.info("Method : getdoctorappointmenthistory starts" + doctorId + status);
		logger.info("Method : getdoctorappointmenthistory ends");
		return doctorDashboardDao.getdoctorappointmenthistory(doctorId, status);
	}

	// get doctor appointment history
	@GetMapping(value = "/get-doctor-appointment-viewDetails")
	public ResponseEntity<JsonResponse<List<DoctorAppointmentHistoryModel>>> getDoctorAppointmentDetails(
			@RequestParam String appointId) {
		logger.info("Method : getdoctorappointmenthistory starts" + appointId);
		logger.info("Method : getdoctorappointmenthistory ends");
		return doctorDashboardDao.getDoctorAppointmentDetails(appointId);
	}

	// get doctor feedback
	@GetMapping(value = "/get-doctor-feedback-save-api")
	public ResponseEntity<JsonResponse<DropDownModel>> getDoctorfeedbackApi(@RequestParam String doctorid,
			@RequestParam String feedback) {
		logger.info("Method : postDoctorDataApi starts");
		logger.info("Method : postDoctorDataApi ends");
		return doctorDashboardDao.getDoctorfeedbackApi(doctorid, feedback);
	}

	// get doctor appointhistory details
	@GetMapping(value = "/get-doctorAppointHistory-details")
	public ResponseEntity<JsonResponse<List<PatientAppointmentDetailsModel>>> doctorAppointHistory(
			@RequestParam String doctorId, @RequestParam String status) {
		logger.info("Method : doctorAppointHistory starts");
		logger.info("Method : doctorAppointHistory ends");
		return doctorDashboardDao.doctorAppointHistory(doctorId, status);
	}

	// get doctor drop down
	@GetMapping(value = "/get-test-name")
	public ResponseEntity<JsonResponse<List<TestNamedropdownModel>>> gettestname() {
		logger.info("Method : gettestname starts");

		logger.info("Method : gettestname ends");
		return doctorDashboardDao.gettestname();
	}

	// get doctor drop down
	@GetMapping(value = "/get-timeslot-duration")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getTimeslot() {
		logger.info("Method : getTimeslot starts");

		logger.info("Method : getTimeslot ends");
		return doctorDashboardDao.getTimeslot();
	}

	// get doctor Off day
	@GetMapping(value = "/get-doctor-offdays")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorOffday() {
		logger.info("Method : getDoctorOffday starts");

		logger.info("Method : getDoctorOffday ends");
		return doctorDashboardDao.getDoctorOffday();
	}

	// get doctor drop down
	@GetMapping(value = "/get-nationality-dropdown")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getNationality() {
		logger.info("Method : getNationality starts");

		logger.info("Method : getNationality ends");
		return doctorDashboardDao.getNationality();
	}

	// get doctor Off day
	@GetMapping(value = "/get-bldGroup-dropdown")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getBldGroup() {
		logger.info("Method : getBldGroup starts");

		logger.info("Method : getBldGroup ends");
		return doctorDashboardDao.getBldGroup();
	}
}
