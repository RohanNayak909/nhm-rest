package nirmalya.aatithya.restmodule.api.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.DoctorRestAPIDao;
import nirmalya.aatithya.restmodule.api.model.APIDoctorMonthlyOverviewModel;
import nirmalya.aatithya.restmodule.api.model.APILabTestReportModel;
import nirmalya.aatithya.restmodule.api.model.APIMedicationModel;
import nirmalya.aatithya.restmodule.api.model.APIShareAppointmentModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentListModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.DoctorRegistrationModel;
import nirmalya.aatithya.restmodule.api.model.MyPatientViewModel;
import nirmalya.aatithya.restmodule.api.model.OPDResponseModel;
import nirmalya.aatithya.restmodule.api.model.PersonalInformationModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class DoctorRestAPIController {

	@Autowired
	DoctorRestAPIDao dctorRestAPIDao;

	@Autowired
	EnvironmentVaribles env;
	
	Logger logger = LoggerFactory.getLogger(DoctorRestAPIController.class);

	@GetMapping(value = "/view-doctor-appointment-list")
	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> viewDoctorAppointmentList(
			@RequestParam String userid, @RequestParam String date, @RequestParam String status) {
		logger.info("Method : viewDoctorAppointmentList starts");

		logger.info("Method : viewDoctorAppointmentList ends");
		return dctorRestAPIDao.viewDoctorAppointmentList(userid, date, status);
	}
	@GetMapping(value = "/change-user-appointment-status")
	public ResponseEntity<JsonResponse<Object>> changeUserAppointmentStatus(@RequestParam String appid,
			@RequestParam String appstatus) {
		logger.info("Method : changeUserAppointmentStatus starts");

		logger.info("Method : changeUserAppointmentStatus ends");
		return dctorRestAPIDao.changeUserAppointmentStatus(appid, appstatus);
	}

	/** Doctor Registration **/
	@PostMapping(value = "/doctor-registration-details")
	public ResponseEntity<JsonResponse<Object>> doctorRegistration(@RequestBody DoctorRegistrationModel data) {
		logger.info("Method : doctorRegistration starts");

		logger.info("Method : doctorRegistration ends");
		return dctorRestAPIDao.doctorRegistrationDao(data);
	}

	/** User Personal Information Registration **/
	@GetMapping(value = "/user-personal-information-doctor")
	public ResponseEntity<JsonResponse<List<PersonalInformationModel>>> userpersonalInformation(
			@RequestParam String userid) {
		logger.info("Method : userpersonalInformation starts");

		logger.info("Method : userpersonalInformation ends");
		return dctorRestAPIDao.userpersonalInformationDao(userid);
	}

	@GetMapping(value = "/get-medicine-list-with-type")
	public ResponseEntity<JsonResponse<List<CountryModel>>> medicineListWithType() {
		logger.info("Method : medicineListWithType starts");

		logger.info("Method : medicineListWithType ends");
		return dctorRestAPIDao.getMedicineListWithType();
	}

	@GetMapping(value = "/user-labtest-report-doctor")
	public ResponseEntity<JsonResponse<List<APILabTestReportModel>>> userLabTestReportDoctor(
			@RequestParam String userid) {
		logger.info("Method : userLabTestReportDoctor starts");

		logger.info("Method : userLabTestReportDoctor ends");
		return dctorRestAPIDao.userLabTestReportDoctor(userid);
	}

	@GetMapping(value = "/user-medication-doctor")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> userMedicationData(@RequestParam String userid) {
		logger.info("Method : userMedicationData starts");

		logger.info("Method : userMedicationData ends");
		return dctorRestAPIDao.userMedicationData(userid);
	}

	@PostMapping(value = "/post-user-medication-doctor")
	public ResponseEntity<JsonResponse<Object>> postUserMedicationDoctor(@RequestBody List<APIMedicationModel> data) {
		logger.info("Method : postUserMedicationDoctor starts");

		logger.info("Method : postUserMedicationDoctor ends");
		return dctorRestAPIDao.postUserMedicationDoctor(data);
	}

	@PostMapping(value = "/post-user-test-by-doctor")
	public ResponseEntity<JsonResponse<Object>> postUserTestByDoctor(@RequestBody List<APIMedicationModel> data) {
		logger.info("Method : postUserTestByDoctor starts");

		logger.info("Method : postUserTestByDoctor ends");
		return dctorRestAPIDao.postUserTestByDoctor(data);
	}
	@GetMapping(value = "/delete-test-by-app-no")
	public ResponseEntity<Object> deleteTestByAppNo(@RequestParam String appno, @RequestParam String srlone,
			@RequestParam String srltwo) {
		logger.info("Method : deleteTestByAppNo starts");

		logger.info("Method : deleteTestByAppNo ends");
		return dctorRestAPIDao.deleteTestByAppNo(appno, srlone, srltwo);
	}

	@GetMapping(value = "/get-otp-emergency-access")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getOtpEmergencyAccess(@RequestParam String userid,
			@RequestParam String drid) {
		logger.info("Method : getOtpEmergencyAccess starts");

		logger.info("Method : getOtpEmergencyAccess ends");
		return dctorRestAPIDao.getOtpEmergencyAccessDao(userid, drid);
	}

	@GetMapping(value = "/get-doctor-my-patientlist")
	public ResponseEntity<JsonResponse<List<MyPatientViewModel>>> getDoctorMyPatientList(@RequestParam String drid) {
		logger.info("Method : getDoctorMyPatientList starts");

		logger.info("Method : getDoctorMyPatientList ends");
		return dctorRestAPIDao.getDoctorMyPatientList(drid);
	}

	@GetMapping(value = "/get-doctor-month-overviewlist")
	public ResponseEntity<JsonResponse<APIDoctorMonthlyOverviewModel>> getDoctorMyMonthlyOverviewList(
			@RequestParam String drid, @RequestParam String fromdate, @RequestParam String todate) {
		logger.info("Method : getDoctorMyMonthlyOverviewList starts");

		logger.info("Method : getDoctorMyMonthlyOverviewList ends");
		return dctorRestAPIDao.getDoctorMyMonthlyOverviewListDao(drid, fromdate, todate);
	}

	@GetMapping(value = "/get-doctor-overviewlist-bystatus")
	public ResponseEntity<JsonResponse<List<APIDoctorMonthlyOverviewModel>>> getDoctorMonthlyOverviewByStatus(
			@RequestParam String drid, @RequestParam String status, @RequestParam String frmdt,
			@RequestParam String todt) {
		logger.info("Method : getDoctorMonthlyOverviewByStatus starts");

		logger.info("Method : getDoctorMonthlyOverviewByStatus ends");
		return dctorRestAPIDao.getDoctorMonthlyOverviewByStatusDao(drid, status, frmdt, todt);
	}

	@GetMapping(value = "/get-confirmed-patient-list")
	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> getConfirmedPatientList(@RequestParam String drid) {
		logger.info("Method : getConfirmedPatientList starts");

		logger.info("Method : getConfirmedPatientList ends");
		return dctorRestAPIDao.getConfirmedPatientList(drid);
	}

	@GetMapping(value = "/get-doctorreceptionist-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorReceptionistListRoleWise(
			@RequestParam String drid, @RequestParam String roleid) {
		logger.info("Method : getDoctorReceptionistListRoleWise starts");

		logger.info("Method : getDoctorReceptionistListRoleWise ends");
		return dctorRestAPIDao.getDoctorReceptionistListRoleWise(drid, roleid);
	}

	@PostMapping(value = "/post-share-appointment-api")
	public ResponseEntity<JsonResponse<Object>> postShareAppointmentAPI(@RequestBody APIShareAppointmentModel data) {
		logger.info("Method : postShareAppointmentAPI starts");

		logger.info("Method : postShareAppointmentAPI ends");
		return dctorRestAPIDao.postShareAppointmentAPI(data);
	}

	/** Update Doctor Ambulance BloodBank Receptionist Profile **/
	@PostMapping(value = "/update-doctor-profile")
	public ResponseEntity<JsonResponse<Object>> updateDoctorProfileAPI(@RequestBody DoctorRegistrationModel data) {
		logger.info("Method : updateUserProfileAPI starts");

		logger.info("Method : updateUserProfileAPI ends");
		return dctorRestAPIDao.updateDoctorProfileAPIDao(data);
	}
	/* Update Doctor digital signature */
	
	public String saveAllSignImage(byte[] imageBytes, String ext, String pId) {
		logger.info("Method : saveAllSignImage starts");

		String imageName = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				
				if (ext.contentEquals("jpeg")) {
					imageName = pId + "_" + nowTime + ".jpg";
				} else {
					imageName = pId + "_" + nowTime + "." + ext;
				}
			}

			Path path = Paths.get(env.getFileUploadProfile() + imageName);
			if (imageBytes != null) {
				if (pId != null && pId != "") {
					Files.write(path, imageBytes);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : saveAllSignImage ends");
		return imageName;
	}

	/*
	 * @PostMapping(value = "/update-doctor-digitalsignature", headers =
	 * "content-type=multipart/*", consumes = { "application/*" }) public
	 * ResponseEntity<JsonResponse<Object>>
	 * doctordigitalSignature(MedicalDataUploadModel medicalDataUploadModel) {
	 * logger.info("Method : doctordigitalSignature starts");
	 * 
	 * MultipartFile x = medicalDataUploadModel.getMulFile(); String fileName =
	 * null; if (x != null) { byte[] bytes = null; try { bytes = x.getBytes(); }
	 * catch (IOException e) { e.printStackTrace(); } fileName =
	 * saveAllSignImage(bytes, medicalDataUploadModel.getExtension(),
	 * medicalDataUploadModel.getUserid()); }
	 * medicalDataUploadModel.setFileName(fileName);
	 * 
	 * logger.info("Method : medicalDataUpload ends"); return
	 * dctorRestAPIDao.doctordigitalSignatureDao(medicalDataUploadModel); }
	 */
	
	/* Update Doctor digital signature */
	
	@PostMapping(value = "/update-doctor-digitalsignature")
	public ResponseEntity<JsonResponse<Object>> doctordigitalSignature(@RequestBody DoctorRegistrationModel data) {
		logger.info("Method : doctordigitalSignature starts");

		logger.info("Method : doctordigitalSignature ends");
		return dctorRestAPIDao.doctordigitalSignatureDao(data);
	}
	
	@PostMapping(value = "/update-other-profileimage")
    public ResponseEntity<JsonResponse<Object>> otherProfileImage(@RequestBody DoctorRegistrationModel data) {

        logger.info("Method : otherProfileImage starts");


        logger.info("Method : otherProfileImage ends");

        return dctorRestAPIDao.otherProfileImageDao(data);

    }
	
	@GetMapping(value = "/doctor-opd-weekly")
	public ResponseEntity<JsonResponse<List<OPDResponseModel>>> doctorOPDWeekly(@RequestParam String drid) {
		logger.info("Method : doctorOPDWeekly starts");

		logger.info("Method : doctorOPDWeekly ends");
		return dctorRestAPIDao.doctorOPDWeekly(drid);
	}
	
	
}
