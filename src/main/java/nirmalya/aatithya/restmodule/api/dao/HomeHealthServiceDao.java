package nirmalya.aatithya.restmodule.api.dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import nirmalya.aatithya.restmodule.api.model.LabDashboardCountModel;
import nirmalya.aatithya.restmodule.api.model.LabDashboardModel;
import nirmalya.aatithya.restmodule.api.model.LabTestViewReportModel;
import nirmalya.aatithya.restmodule.api.model.NurseAideModel;
import nirmalya.aatithya.restmodule.api.model.PaymentGatewayAPIModel;
import nirmalya.aatithya.restmodule.api.model.APIDoctorMonthlyOverviewModel;
import nirmalya.aatithya.restmodule.api.model.AmbulanceAPIModel;
import nirmalya.aatithya.restmodule.api.model.CaseStudyModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyAddtoCartModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyPaymentModel;
import nirmalya.aatithya.restmodule.api.model.DoctorDashboardAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.DoctorProfileModel;
import nirmalya.aatithya.restmodule.api.model.DoctorRatingModel;
import nirmalya.aatithya.restmodule.api.model.HomeHealthServiceModel;
import nirmalya.aatithya.restmodule.api.model.HomeServiceDocumentationModel;
import nirmalya.aatithya.restmodule.api.model.HomeServicePaymentModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.MailService;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.PushNotification;
import nirmalya.aatithya.restmodule.util.SMSGatewayMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;

@Repository
public class HomeHealthServiceDao {

	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	ServerDao serverDao;

	@Value("${razorpay_keyid}")
	private String key_id;

	@Value("${razorpay_keysecret}")
	private String key_secret;

	PushNotification pushNotification = new PushNotification();
	Logger logger = LoggerFactory.getLogger(HomeHealthServiceDao.class);

	// get lab dashboard count
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<HomeHealthServiceModel>> getHomeServiceDashboardcount(String serviceId) {
		logger.info("Method : getHomeServiceDashboardcount Dao starts");

		HomeHealthServiceModel getLabDashboardcount = new HomeHealthServiceModel();
		JsonResponse<HomeHealthServiceModel> jsonResponse = new JsonResponse<HomeHealthServiceModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_homeservice_count")
					.setParameter("serviceId", serviceId).getResultList();
			for (Object[] m : x) {
				HomeHealthServiceModel labDashboardCountModel = new HomeHealthServiceModel(m[0], null, m[1], m[2], m[3],
						m[4], null, m[5], m[6]);
				getLabDashboardcount = labDashboardCountModel;
			}
			jsonResponse.setBody(getLabDashboardcount);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<HomeHealthServiceModel>> response = new ResponseEntity<JsonResponse<HomeHealthServiceModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getHomeServiceDashboardcount Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getServiceDropdown() {
		logger.info("Method : getServiceDropdown Dao starts");

		List<DropDownModel> labsts = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_homeservice_status").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				labsts.add(dropDownModel);
			}
			jsonResponse.setBody(labsts);

			if (labsts.size() > 0) {
				Util.setJsonResponse(jsonResponse, labsts, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getServiceDropdown Dao ends");

		return response;
	}

	// get lab dashboard details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<HomeHealthServiceModel>>> getHomeServiceDetails(String serviceId,
			String status, String fromDate, String toDate) {
		logger.info("Method : getHomeServiceDetails Dao starts");

		List<HomeHealthServiceModel> getLabDashboardcount = new ArrayList<HomeHealthServiceModel>();
		JsonResponse<List<HomeHealthServiceModel>> jsonResponse = new JsonResponse<List<HomeHealthServiceModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_homeservice_dashboard_details")
					.setParameter("serviceId", serviceId).setParameter("status", status)
					.setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
			for (Object[] m : x) {

				HomeHealthServiceModel labDashboardCountModel = new HomeHealthServiceModel(m[0], m[1], m[2], m[3], m[4],
						m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16], m[17], m[18],
						null, null, null, null, null);
				getLabDashboardcount.add(labDashboardCountModel);
			}
			jsonResponse.setBody(getLabDashboardcount);
			if (getLabDashboardcount.size() > 0) {
				Util.setJsonResponse(jsonResponse, getLabDashboardcount, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<HomeHealthServiceModel>>> response = new ResponseEntity<JsonResponse<List<HomeHealthServiceModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getHomeServiceDetails Dao ends" + response);

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<HomeHealthServiceModel>> postUpdateHomeServiceStatus(
			HomeHealthServiceModel labmodel) {
		logger.info("Method : postUpdateHomeServiceStatus Dao starts");
		System.out.println(labmodel);
		JsonResponse<HomeHealthServiceModel> jsonResponse = new JsonResponse<HomeHealthServiceModel>();
		HomeHealthServiceModel payments = new HomeHealthServiceModel();

		String orderId = "";
		String paymentDate = "";
		String paymentTime = "";
		String userName = "";
		String email = "";
		String partnerName = "";
		String DeviceIdKey = "";
		String responsecode = "";
		String labStatus = "";
		String labMob = "";
		String userId = "";
		String patientName = "";
		String mobNo = "";
		String serviceName = "";
		String usertoken = "";
		String description = "";
		String dataList = "";
		try {
			if (labmodel.getHomeServiceId() != null && labmodel.getHomeServiceId() != "") {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_update_homeservice_status")
						.setParameter("userid", labmodel.getHomeServiceId())
						.setParameter("homeserviceId", labmodel.getOrderId())
						.setParameter("status", labmodel.getStatus()).setParameter("remark", labmodel.getRemark())
						// .setParameter("rejected", labmodel.getRejected())
						.getResultList();
				Object statussAll = null;
				for (Object[] m : x) {
					String statuss = m[6].toString();
					System.out.println("statuss" + statuss);
					Integer assign = Integer.parseInt(statuss);

					System.out.println("assign" + assign);
					if (assign.equals(10)) {
						statussAll = "Homeservice Order Accepted";
					} else if (assign.equals(12)) {
						statussAll = "Homeservice Documentation Done";
					} else if (assign.equals(13)) {
						statussAll = "Homeservice Order In Progress";
					} else if (assign.equals(14)) {
						statussAll = "Homeservice Completed";
					} else if (assign.equals(15)) {
						statussAll = "Homeservice Closed";
					} else if (assign.equals(11)) {
						statussAll = "Homeservice Order Rejected";
					}
					HomeHealthServiceModel dropDownModel = new HomeHealthServiceModel(m[0], m[1], m[2], m[3], m[4],
							m[5], statussAll, m[7]);
					payments = dropDownModel;
					System.out.println("statussAll" + statussAll);
					orderId = payments.getOrderId();
					patientName = payments.getPatientname();
					mobNo = payments.getPatientMobNo();
					serviceName = payments.getServiceName();
					email = payments.getEmail();
					usertoken = payments.getUserToken();
					// description=payments.getStatus();
					userId = payments.getPatientId();

					dataList = "Hi " + patientName + "," + "Your " + serviceName + "." + "is " + statussAll + "."
							+ "Please Fill Up the Form" + "." + System.lineSeparator() + System.lineSeparator()
							+ "Thanks, Team CureEZ.";

					try {
						List<Object[]> x1 = em.createNamedStoredProcedureQuery("homeservice_accept_notification")
								.setParameter("responsecode", responsecode).setParameter("userid", userId)
								.setParameter("dataList", dataList).setParameter("type", statussAll).getResultList();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				String to = email;
				String username = userName;
				String sub = "Cureez Mail";
				String msg = "Hi  " + username + "," + "Your " + orderId + "." + "at " + paymentTime + "."
						+ "Please Process the request" + "." + System.lineSeparator() + System.lineSeparator()
						+ "Thanks, Team CureEZ.";

				// Integer types=Integer.parseInt(description);
				try {
					responsecode = pushNotification.pushFCMNotifications(DeviceIdKey, dataList);
					System.out.println("msgIdddddddddddddd" + responsecode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// String type="";

				jsonResponse.setBody(payments);
			}
			jsonResponse.setCode("success");
			jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_FAILED);
		}

		ResponseEntity<JsonResponse<HomeHealthServiceModel>> response = new ResponseEntity<JsonResponse<HomeHealthServiceModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postUpdateHomeServiceStatus Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<HomeHealthServiceModel>> postUserDocumentation(HomeHealthServiceModel labmodel) {
		logger.info("Method : postUserDocumentation Dao starts");
		System.out.println(labmodel);
		JsonResponse<HomeHealthServiceModel> jsonResponse = new JsonResponse<HomeHealthServiceModel>();
		HomeHealthServiceModel payments = new HomeHealthServiceModel();

		try {
			if (labmodel.getHomeServiceId() != null && labmodel.getHomeServiceId() != "") {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_user_documentation_status")
						.setParameter("userid", labmodel.getHomeServiceId())
						.setParameter("homeserviceId", labmodel.getOrderId())
						.setParameter("status", labmodel.getStatus()).getResultList();

				for (Object[] m : x) {
					HomeHealthServiceModel dropDownModel = new HomeHealthServiceModel(m[0], m[1]);
					payments = dropDownModel;
				}
				jsonResponse.setBody(payments);
			}
			jsonResponse.setCode("success");
			jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_FAILED);
		}

		ResponseEntity<JsonResponse<HomeHealthServiceModel>> response = new ResponseEntity<JsonResponse<HomeHealthServiceModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postUserDocumentation Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<HomeServiceDocumentationModel>> postHomeserviceDocumentation(
			HomeServiceDocumentationModel homeServiceDocumentationModel) {
		logger.info("Method : postHomeserviceDocumentation Dao starts" + homeServiceDocumentationModel);
		JsonResponse<HomeServiceDocumentationModel> jsonResponse = new JsonResponse<HomeServiceDocumentationModel>();
		HomeServiceDocumentationModel payments = new HomeServiceDocumentationModel();
		System.out.println("%%%%" + homeServiceDocumentationModel.getNurseList());
		System.out.println("%%%%" + homeServiceDocumentationModel.getPcaList());

		List<String> nurseList = new ArrayList<>(homeServiceDocumentationModel.getNurseList());
		System.out.println("nurseList" + nurseList);
		// find first element
		String nurse12HrsPrice = nurseList.get(0);
		String nurse24HrsPrice = nurseList.get(1);
		String nurseRegdFee = nurseList.get(2);
		String nurseGst = nurseList.get(3);

		List<String> pcaList = new ArrayList<>(homeServiceDocumentationModel.getPcaList());

		String pca12HrsPrice = pcaList.get(0);
		String pca24HrsPrice = pcaList.get(1);
		String pcaRegdFee = pcaList.get(2);
		String pcaGst = pcaList.get(3);

		String str = "";
		String nurse = "";
		String nurselist = "";
		String type1 = "nurseType";
		for (String keyy : nurseList) {
			str += keyy + ",";
		}
		String[] strdata = str.split(",");

		try {
			if (homeServiceDocumentationModel.getHomeserviceId() != null
					&& homeServiceDocumentationModel.getHomeserviceId() != "") {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_homeservice_documentation")
						.setParameter("homeserviceId", homeServiceDocumentationModel.getHomeserviceId())
						.setParameter("homeserviceType", homeServiceDocumentationModel.getHomeserviceType())
						.setParameter("patientId", homeServiceDocumentationModel.getPatientId())
						.setParameter("nurseName", homeServiceDocumentationModel.getNurseName())
						.setParameter("coOrdinatorName", homeServiceDocumentationModel.getCoOrdinatorName())
						.setParameter("dateOfVisit",
								DateFormatter.getStringDate(homeServiceDocumentationModel.getDateOfVisit()))
						.setParameter("patientName", homeServiceDocumentationModel.getPatientName())
						.setParameter("phNo", homeServiceDocumentationModel.getPhno())
						.setParameter("age", homeServiceDocumentationModel.getAge())
						.setParameter("gender", homeServiceDocumentationModel.getGender())
						.setParameter("nationality", homeServiceDocumentationModel.getNationality())
						.setParameter("mainComplaint", homeServiceDocumentationModel.getMainComplaint())
						.setParameter("currentMedication", homeServiceDocumentationModel.getCurrentMedication())
						.setParameter("drugAllergy", homeServiceDocumentationModel.getDrugAllergy())
						.setParameter("recentAdmsn", homeServiceDocumentationModel.getRecentAdmsn())
						.setParameter("treatmentProcedure", homeServiceDocumentationModel.getTreatmentProcedure())
						.setParameter("bp", homeServiceDocumentationModel.getBp())
						.setParameter("tempPulse", homeServiceDocumentationModel.getTempPulse())
						.setParameter("respRate", homeServiceDocumentationModel.getRespiratoryRate())
						.setParameter("generalBuild", homeServiceDocumentationModel.getGeneralBuild())
						.setParameter("localExam", homeServiceDocumentationModel.getLocalExam())
						.setParameter("diagnosis", homeServiceDocumentationModel.getDiagnosis())
						.setParameter("additinalComment", homeServiceDocumentationModel.getAdditionalComment())
						.setParameter("treatment", homeServiceDocumentationModel.getTreatment())
						.setParameter("givenPlan", homeServiceDocumentationModel.getGivenPlan())
						.setParameter("durationCare", homeServiceDocumentationModel.getDurationCare())
						.setParameter("allShift", homeServiceDocumentationModel.getAllShift())
						.setParameter("durationShift", homeServiceDocumentationModel.getDurationShift())
						.setParameter("otherDetails", homeServiceDocumentationModel.getOtherDetails())
						.setParameter("patientCare", homeServiceDocumentationModel.getPatientCare())
						.setParameter("informedSme", homeServiceDocumentationModel.getInformedSme())
						.setParameter("commentSme", homeServiceDocumentationModel.getCommentSme())
						.setParameter("nurse12", nurse12HrsPrice).setParameter("nurse24", nurse24HrsPrice)
						.setParameter("nurseRegd", nurseRegdFee).setParameter("nurseGst", nurseGst)
						.setParameter("pca12", pca12HrsPrice).setParameter("pca24", pca24HrsPrice)
						.setParameter("pcaRegdFee", pcaRegdFee).setParameter("pcaGst", pcaGst)
						.setParameter("feedback", homeServiceDocumentationModel.getFeedback())
						.setParameter("address", homeServiceDocumentationModel.getAddress())
						.setParameter("homeServiceOrderId", homeServiceDocumentationModel.getHomeServiceOrderId())
						.setParameter("followUpDate", homeServiceDocumentationModel.getFollowDate())

						.getResultList();

				try {
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("post_update_documentation")
							.setParameter("homeserviceOrderId", homeServiceDocumentationModel.getHomeServiceOrderId())
							.getResultList();

				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse.setCode("failed");
					jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_FAILED);
				}
			}

			jsonResponse.setCode("success");
			jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_FAILED);
		}

		ResponseEntity<JsonResponse<HomeServiceDocumentationModel>> response = new ResponseEntity<JsonResponse<HomeServiceDocumentationModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postHomeserviceDocumentation Dao ends");
		return response;
	}

	public String saveAllImage2(byte[] imageBytes, String ext, String pId) {
		logger.info("Method : saveAllImage starts");
		String imageName = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				if (ext.contentEquals("jpeg")) {
					imageName = pId + "_" + nowTime + ".jpg";
					System.out.println("ifimagename" + imageName);
				} else {
					imageName = pId + "_" + nowTime + "." + ext;
					System.out.println("elseimagename" + imageName);
				}

			}

			Path path = Paths.get(env.getDocumentUpload() + imageName);
			System.out.println("path" + path);
			if (imageBytes != null) {

				if (pId != null && pId != "") {
					Files.write(path, imageBytes);

					ByteArrayInputStream in = new ByteArrayInputStream(imageBytes);
					Integer height = 50;
					Integer width = 50;

					try {
						BufferedImage img = ImageIO.read(in);
						if (height == 0) {
							height = (width * img.getHeight()) / img.getWidth();
						}
						if (width == 0) {
							width = (height * img.getWidth()) / img.getHeight();
						}

						BufferedImage outputImage = new BufferedImage(width, height, img.getType());

						Graphics2D g2d = outputImage.createGraphics();
						g2d.drawImage(img, 0, 0, width, height, null);
						g2d.dispose();
						String outputImagePath = env.getDocumentUpload() + "thumb/" + imageName;
						System.out.println("outputImagePath" + outputImagePath);
						ImageIO.write(outputImage, ext, new File(outputImagePath));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : saveAllImage ends");
		return imageName;
	}
	// save
	/*
	 * @SuppressWarnings({ "unlikely-arg-type", "unchecked" })
	 * 
	 * public ResponseEntity<JsonResponse<PatientDocumentationModel>>
	 * addPatientDocumetation( PatientDocumentationModel patientDocumentationModel)
	 * {
	 * 
	 * logger.info("Method : addPatientDocumetation starts" +
	 * patientDocumentationModel);
	 * 
	 * System.out.println("regdModel===" + patientDocumentationModel);
	 * JsonResponse<PatientDocumentationModel> resp = new
	 * JsonResponse<PatientDocumentationModel>(); PatientDocumentationModel dlist =
	 * new PatientDocumentationModel();
	 * 
	 * String medicalProblemList = ""; if
	 * (patientDocumentationModel.getGetMedicalProbem() != null) { if
	 * (patientDocumentationModel.getGetMedicalProbem().size() > 0) { for
	 * (DropDownModel m : patientDocumentationModel.getGetMedicalProbem()) {
	 * medicalProblemList = medicalProblemList + "(\'" +
	 * patientDocumentationModel.getHomeServiceOrderId() + "\'" + ",\'" +
	 * m.getName() + "\',\'" + m.getCode() + "\'),";
	 * 
	 * logger.info("medicalProblemList" + medicalProblemList); } medicalProblemList
	 * = medicalProblemList.substring(0, medicalProblemList.length() - 1); } else {
	 * medicalProblemList = ""; } }
	 * 
	 * String currentMedicalCondition = ""; if
	 * (patientDocumentationModel.getGetCurrentMedication() != null) { if
	 * (patientDocumentationModel.getGetCurrentMedication().size() > 0) { for
	 * (DropDownModel m : patientDocumentationModel.getGetCurrentMedication()) {
	 * currentMedicalCondition = currentMedicalCondition + "(\'" +
	 * patientDocumentationModel.getHomeServiceOrderId() + "\'" + ",\'" +
	 * m.getName() + "\',\'" + m.getCode() + "\'),";
	 * 
	 * logger.info("currentMedicalCondition" + currentMedicalCondition); }
	 * currentMedicalCondition = currentMedicalCondition.substring(0,
	 * currentMedicalCondition.length() - 1); } else { currentMedicalCondition = "";
	 * } }
	 * 
	 * String familyDetails = ""; if
	 * (patientDocumentationModel.getGetFamilyDetails() != null) { if
	 * (patientDocumentationModel.getGetFamilyDetails().size() > 0) { for
	 * (FamilyDetailsModel m : patientDocumentationModel.getGetFamilyDetails()) {
	 * familyDetails = familyDetails + "(\'" +
	 * patientDocumentationModel.getHomeServiceOrderId() + "\'" + ",\'" +
	 * m.getRelation() + "\',\'" + m.getName() + "\',\'" + m.getEmail() + "\',\'" +
	 * m.getAge() + "\',\'" + m.getMobile() + "\'),";
	 * 
	 * logger.info("familyDetails" + familyDetails); } familyDetails =
	 * familyDetails.substring(0, familyDetails.length() - 1); } else {
	 * familyDetails = ""; } }
	 * 
	 * try { byte[] bytes =
	 * Base64.getDecoder().decode(patientDocumentationModel.getConfirmPatientSign())
	 * ; String imageName = null; imageName = saveAllImage2(bytes,
	 * patientDocumentationModel.getExtension(),
	 * patientDocumentationModel.getPatientId());
	 * patientDocumentationModel.setConfirmPatientSign(imageName); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * try {
	 * 
	 * List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("post_patient_documentation")
	 * .setParameter("patientId", patientDocumentationModel.getPatientId())
	 * .setParameter("patientName", patientDocumentationModel.getPatientName())
	 * .setParameter("email", patientDocumentationModel.getEmail())
	 * .setParameter("bldGrp", patientDocumentationModel.getBldGrp())
	 * .setParameter("age", patientDocumentationModel.getAge())
	 * .setParameter("height", patientDocumentationModel.getHeight())
	 * .setParameter("weight", patientDocumentationModel.getWeight())
	 * .setParameter("normalHealth", patientDocumentationModel.getNormalHealth())
	 * .setParameter("ongoingHealth", patientDocumentationModel.getOngoingHealth())
	 * .setParameter("allergy", patientDocumentationModel.getAllergy())
	 * .setParameter("others", patientDocumentationModel.getOthers())
	 * .setParameter("hereditaryProblem",
	 * patientDocumentationModel.getHereditaryProblem()) .setParameter("date",
	 * DateFormatter.getStringDate(patientDocumentationModel.getDate()))
	 * 
	 * .setParameter("consentPatientName",
	 * patientDocumentationModel.getConsentPatientName())
	 * .setParameter("consentAge", patientDocumentationModel.getConsentAge())
	 * .setParameter("consentAddress",
	 * patientDocumentationModel.getConsentAddress()) .setParameter("treatmentBy",
	 * patientDocumentationModel.getTreatmentBy()) .setParameter("attendantSign",
	 * patientDocumentationModel.getAttendantSign()) .setParameter("mobile",
	 * patientDocumentationModel.getMobile()) .setParameter("consentDate",
	 * DateFormatter.getStringDate(patientDocumentationModel.getConsentDate()))
	 * .setParameter("patientRelationship",
	 * patientDocumentationModel.getPatientRelationship())
	 * 
	 * .setParameter("confirmationPatientName",
	 * patientDocumentationModel.getConfirmationPatientName())
	 * .setParameter("serviceType", patientDocumentationModel.getServiceType())
	 * .setParameter("serviceHours", patientDocumentationModel.getServiceHours())
	 * .setParameter("price", patientDocumentationModel.getPrice())
	 * .setParameter("regdFee", patientDocumentationModel.getRegdFee())
	 * .setParameter("gst", patientDocumentationModel.getGst())
	 * .setParameter("confirmDate",
	 * DateFormatter.getStringDate(patientDocumentationModel.getConfirmDate()))
	 * .setParameter("location", patientDocumentationModel.getLocation())
	 * .setParameter("confirmPatientSign",
	 * patientDocumentationModel.getConfirmPatientSign())
	 * .setParameter("medicalProblemList", medicalProblemList)
	 * .setParameter("currentMedicalCondition", currentMedicalCondition)
	 * 
	 * .setParameter("familyDetails", familyDetails)
	 * .setParameter("homeserviceorderid",
	 * patientDocumentationModel.getHomeServiceOrderId()) .getResultList();
	 * 
	 * logger.info("medicalProblemList" + medicalProblemList);
	 * 
	 * try { List<Object[]> x1 =
	 * em.createNamedStoredProcedureQuery("patient_documentation")
	 * .setParameter("homeserviceOrderId",
	 * patientDocumentationModel.getHomeServiceOrderId()) .getResultList();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * // resp.setBody(x.get(0));
	 * 
	 * if (resp.getMessage() == null) {
	 * 
	 * resp.setBody(x.get(0)); logger.info("iddvle" + resp); }
	 * 
	 * resp.setCode("success");
	 * resp.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY); } catch
	 * (Exception e) { try { String[] err = serverDao.errorProcedureCall(e);
	 * 
	 * resp.setCode(err[0]); resp.setMessage(err[1]);
	 * 
	 * } catch (Exception e1) { e1.printStackTrace(); } }
	 * 
	 * ResponseEntity<JsonResponse<PatientDocumentationModel>> response = new
	 * ResponseEntity<JsonResponse<PatientDocumentationModel>>( resp,
	 * HttpStatus.OK); logger.info("Method : addPatientDocumetation ends"); return
	 * response; }
	 */

	// get home service search
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getNurseDetails(String type, String hours,
			String orderId) {
		logger.info("Method : getNurseDetails Dao starts" + type + "  " + hours);

		List<DropDownModel> homeservicesearch = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_nurse_details").setParameter("type", type)
					.setParameter("hours", hours).setParameter("orderId", orderId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1], m[2]);
				homeservicesearch.add(dropDownModel);
			}
			jsonResponse.setBody(homeservicesearch);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getNurseDetails Dao ends");

		return response;
	}

	/*
	 * 
	 * Api to return order id
	 */
	public String getOrderHomeservice(String order_id, Double amount) {
		logger.info("Method : getOrderHomeservice Dao starts");

		RazorpayClient razorpayClient = null;
		try {
			razorpayClient = new RazorpayClient(key_id, key_secret);
			System.out.println("key_id" + key_id);
			System.out.println("key_secret" + key_secret);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		JSONObject options = new JSONObject();
		options.put("amount", amount * 100);
		options.put("currency", "INR");
		options.put("receipt", order_id);

		JSONObject json = new JSONObject();
		json.put("status", "failed");

		Order order = null;
		try {
			order = razorpayClient.Orders.create(options);
		} catch (Exception e) {
			e.printStackTrace();
			return json.toString();
		}

		logger.info("Method : getOrderHomeservice Dao ends");
		return !StringUtil.isNull(order) ? order.toString() : json.toString();
	}

	// Lab Payment Save
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> savePaymentHomeService(PaymentGatewayAPIModel payment) {
		logger.info("Method : savePaymentHomeService Dao starts");
		logger.info("savePaymentHomeService Dao " + payment);

		JsonResponse<PaymentGatewayAPIModel> jsonResponse = new JsonResponse<PaymentGatewayAPIModel>();
		PaymentGatewayAPIModel payments = new PaymentGatewayAPIModel();
		Boolean validation = true;
		String date = null;
		String payment_for = "Home Service";
		String bookinstatus = null;

		if (StringUtil.isNull(payment.getOrder_id())) {
			validation = false;
			jsonResponse.setMessage("Order Id Required");
		}

		if (StringUtil.isNull(payment.getCreatedOn())) {
			validation = false;
			jsonResponse.setMessage("Date time Required");
		}

		if (StringUtil.isNull(payment.getPayment_id())) {
			validation = false;
			jsonResponse.setMessage("Payment Id Required");
		}

		if (StringUtil.isNull(payment.getStatus())) {
			validation = false;
			jsonResponse.setMessage("Status Required");
		}

		if (StringUtil.isNull(payment.getAmount())) {
			validation = false;
			jsonResponse.setMessage("Amount Required");
		}
		if (StringUtil.isNull(payment.getPay_mode())) {
			validation = false;
			jsonResponse.setMessage("Payment Mode Required");
		}

		if (!StringUtil.isNull(payment.getCreatedOn())) {
			date = DateFormatter.getStringDateNew(payment.getCreatedOn());
		}

		if (payment.getStatus().equals("success")) {
			bookinstatus = "1";
		} else {
			bookinstatus = "0";
		}
		String orderId = "";
		String paymentDate = "";
		String paymentTime = "";
		String userName = "";
		String email = "";
		String partnerName = "";
		String DeviceIdKey = "";
		String responsecode = "";
		String mob = "";
		if (validation) {
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_payment_details_homeservice")
						.setParameter("orderid", payment.getOrder_id())
						.setParameter("paymentid", payment.getPayment_id())
						.setParameter("payment_status", payment.getStatus()).setParameter("payment_for", payment_for)
						.setParameter("total_amount", payment.getAmount()).setParameter("bookingstatus", bookinstatus)
						.setParameter("created_on", date).setParameter("userid", payment.getUserid())
						.setParameter("payment_mode", payment.getPay_mode()).getResultList();
				logger.info("x" + x);

				jsonResponse.setBody(payments);
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.success,
						ApiResponseMessage.PAYMENT_DETAILS_SUCCESSFULL);

			} catch (Exception e) {
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}
		ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> response = new ResponseEntity<JsonResponse<PaymentGatewayAPIModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : savePaymentHomeService Dao starts");
		return response;
	}

	// get lab dashboard details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<HomeServiceDocumentationModel>>> getHomeServiceDocumentation(String userId,
			String homeserviceOrderId) {
		logger.info("Method : getHomeServiceDocumentation Dao starts" + homeserviceOrderId);

		List<HomeServiceDocumentationModel> getLabDashboardcount = new ArrayList<HomeServiceDocumentationModel>();
		JsonResponse<List<HomeServiceDocumentationModel>> jsonResponse = new JsonResponse<List<HomeServiceDocumentationModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_homeservice_documentation_details")
					.setParameter("userId", userId).setParameter("homeserviceOrderId", homeserviceOrderId)
					.getResultList();
			for (Object[] m : x) {

				HomeServiceDocumentationModel labDashboardCountModel = new HomeServiceDocumentationModel(m[0], m[1],
						m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16],
						m[17], m[18], m[19], m[20], m[21], m[22], m[23], m[24], m[25], m[26], m[27], m[28], m[29],
						m[30], m[31], m[32], m[33], m[34], m[35], m[36], m[37], m[38], m[39], m[40], m[41], m[42],
						m[43], m[44]);
				getLabDashboardcount.add(labDashboardCountModel);
			}
			jsonResponse.setBody(getLabDashboardcount);
			jsonResponse.setMessage("Data Fetched Successfully");
			jsonResponse.setCode("Success");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<HomeServiceDocumentationModel>>> response = new ResponseEntity<JsonResponse<List<HomeServiceDocumentationModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getHomeServiceDocumentation Dao ends" + response);

		return response;
	}

	@SuppressWarnings({ "unlikely-arg-type", "unchecked" })

	public ResponseEntity<JsonResponse<NurseAideModel>> addNurseAideDetails(NurseAideModel nurseAideModel) {

		logger.info("Method : addPatientDocumetation starts" + nurseAideModel);

		System.out.println("regdModel===" + nurseAideModel);
		JsonResponse<NurseAideModel> resp = new JsonResponse<NurseAideModel>();
		NurseAideModel dlist = new NurseAideModel();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("nurse_aide_details")
					.setParameter("name", nurseAideModel.getName()).setParameter("age", nurseAideModel.getAge())
					.setParameter("gender", nurseAideModel.getGender())
					.setParameter("contactNumber", nurseAideModel.getContactNumber())
					.setParameter("foodHabit", nurseAideModel.getFoodHabit())
					.setParameter("familyMemberNo", nurseAideModel.getFamilyMemberNo())
					.setParameter("occupation", nurseAideModel.getOccupation())
					.setParameter("typeOfResidence", nurseAideModel.getTypeOfResidence())
					.setParameter("policeStation", nurseAideModel.getPoliceStation())
					.setParameter("hospital", nurseAideModel.getHospital())
					.setParameter("busStop", nurseAideModel.getBusStop())
					.setParameter("address", nurseAideModel.getAddress())
					.setParameter("fluentCommunication", nurseAideModel.getFluentCommunication())
					.setParameter("aideRest", nurseAideModel.getAideRest())
					.setParameter("foodArrangement", nurseAideModel.getFoodArrangement())
					.setParameter("workHygienic", nurseAideModel.getWorkHygienic())
					.setParameter("toiletsFacilities", nurseAideModel.getToiletsFacilities())
					.setParameter("nursingAideProvided", nurseAideModel.getNursingAideProvided())
					.setParameter("nursingAideHostility", nurseAideModel.getNursingAideHostility())
					.setParameter("drugAbuse", nurseAideModel.getDrugAbuse())
					.setParameter("femaleEmployeeThreat", nurseAideModel.getFemaleEmployeeThreat())
					.setParameter("stayAvailable", nurseAideModel.getStayAvailable())
					.setParameter("longDurationWork", nurseAideModel.getLongDurationWork())
					.setParameter("riskWise", nurseAideModel.getRiskWise())
					.setParameter("homeserviceOrderId", nurseAideModel.getHomeserviceOrderId()).getResultList();

			resp.setCode("success");
			resp.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		ResponseEntity<JsonResponse<NurseAideModel>> response = new ResponseEntity<JsonResponse<NurseAideModel>>(resp,
				HttpStatus.OK);
		logger.info("Method : addPatientDocumetation ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<HomeServiceDocumentationModel>> updateHomeserviceDocumentation(
			HomeServiceDocumentationModel homeServiceDocumentationModel) {
		logger.info("Method : postHomeserviceDocumentation Dao starts" + homeServiceDocumentationModel);
		JsonResponse<HomeServiceDocumentationModel> jsonResponse = new JsonResponse<HomeServiceDocumentationModel>();
		HomeServiceDocumentationModel payments = new HomeServiceDocumentationModel();
		System.out.println("%%%%" + homeServiceDocumentationModel.getNurseList());
		System.out.println("%%%%" + homeServiceDocumentationModel.getPcaList());

		List<String> nurseList = new ArrayList<>(homeServiceDocumentationModel.getNurseList());
		System.out.println("nurseList" + nurseList);
		// find first element
		String nurse12HrsPrice = nurseList.get(0);
		String nurse24HrsPrice = nurseList.get(1);
		String nurseRegdFee = nurseList.get(2);
		String nurseGst = nurseList.get(3);

		List<String> pcaList = new ArrayList<>(homeServiceDocumentationModel.getPcaList());

		String pca12HrsPrice = pcaList.get(0);
		String pca24HrsPrice = pcaList.get(1);
		String pcaRegdFee = pcaList.get(2);
		String pcaGst = pcaList.get(3);

		String str = "";
		String nurse = "";
		String nurselist = "";
		String type1 = "nurseType";
		for (String keyy : nurseList) {
			str += keyy + ",";
		}
		String[] strdata = str.split(",");

		try {
			if (homeServiceDocumentationModel.getHomeserviceId() != null
					&& homeServiceDocumentationModel.getHomeserviceId() != "") {
				List<Object[]> x = em.createNamedStoredProcedureQuery("update_homeservice_documentation")
						.setParameter("homeserviceId", homeServiceDocumentationModel.getHomeserviceId())
						.setParameter("homeserviceType", homeServiceDocumentationModel.getHomeserviceType())
						.setParameter("patientId", homeServiceDocumentationModel.getPatientId())
						.setParameter("nurseName", homeServiceDocumentationModel.getNurseName())
						.setParameter("coOrdinatorName", homeServiceDocumentationModel.getCoOrdinatorName())
						.setParameter("dateOfVisit",
								DateFormatter.getStringDate(homeServiceDocumentationModel.getDateOfVisit()))
						.setParameter("patientName", homeServiceDocumentationModel.getPatientName())
						.setParameter("phNo", homeServiceDocumentationModel.getPhno())
						.setParameter("age", homeServiceDocumentationModel.getAge())
						.setParameter("gender", homeServiceDocumentationModel.getGender())
						.setParameter("nationality", homeServiceDocumentationModel.getNationality())
						.setParameter("mainComplaint", homeServiceDocumentationModel.getMainComplaint())
						.setParameter("currentMedication", homeServiceDocumentationModel.getCurrentMedication())
						.setParameter("drugAllergy", homeServiceDocumentationModel.getDrugAllergy())
						.setParameter("recentAdmsn", homeServiceDocumentationModel.getRecentAdmsn())
						.setParameter("treatmentProcedure", homeServiceDocumentationModel.getTreatmentProcedure())
						.setParameter("bp", homeServiceDocumentationModel.getBp())
						.setParameter("tempPulse", homeServiceDocumentationModel.getTempPulse())
						.setParameter("respRate", homeServiceDocumentationModel.getRespiratoryRate())
						.setParameter("generalBuild", homeServiceDocumentationModel.getGeneralBuild())
						.setParameter("localExam", homeServiceDocumentationModel.getLocalExam())
						.setParameter("diagnosis", homeServiceDocumentationModel.getDiagnosis())
						.setParameter("additinalComment", homeServiceDocumentationModel.getAdditionalComment())
						.setParameter("treatment", homeServiceDocumentationModel.getTreatment())
						.setParameter("givenPlan", homeServiceDocumentationModel.getGivenPlan())
						.setParameter("durationCare", homeServiceDocumentationModel.getDurationCare())
						.setParameter("allShift", homeServiceDocumentationModel.getAllShift())
						.setParameter("durationShift", homeServiceDocumentationModel.getDurationShift())
						.setParameter("otherDetails", homeServiceDocumentationModel.getOtherDetails())
						.setParameter("patientCare", homeServiceDocumentationModel.getPatientCare())
						.setParameter("informedSme", homeServiceDocumentationModel.getInformedSme())
						.setParameter("commentSme", homeServiceDocumentationModel.getCommentSme())
						.setParameter("nurse12", nurse12HrsPrice).setParameter("nurse24", nurse24HrsPrice)
						.setParameter("nurseRegd", nurseRegdFee).setParameter("nurseGst", nurseGst)
						.setParameter("pca12", pca12HrsPrice).setParameter("pca24", pca24HrsPrice)
						.setParameter("pcaRegdFee", pcaRegdFee).setParameter("pcaGst", pcaGst)
						.setParameter("feedback", homeServiceDocumentationModel.getFeedback())
						.setParameter("address", homeServiceDocumentationModel.getAddress())
						.setParameter("homeServiceOrderId", homeServiceDocumentationModel.getHomeServiceOrderId())
						.setParameter("followUpDate", homeServiceDocumentationModel.getFollowDate())

						.getResultList();

			}
			jsonResponse.setCode("success");
			jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_FAILED);
		}

		ResponseEntity<JsonResponse<HomeServiceDocumentationModel>> response = new ResponseEntity<JsonResponse<HomeServiceDocumentationModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postHomeserviceDocumentation Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postHomeserviceDocumentation(DoctorRatingModel doctorProfileModel) {
		logger.info("Method : postHomeserviceDocumentation Dao starts" + doctorProfileModel);

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String timeSlotList = "";
		String timeslotdat = "";
		System.out.println("%%%%" + doctorProfileModel.getLikeType());

		List<String> tokens = new ArrayList<>(doctorProfileModel.getLikeType());

		System.out.println("tokens" + tokens);

		String str = "";
		for (String keyy : tokens) {
			str += keyy + ",";
		}
		String[] strdata = str.split(",");

		for (String s : strdata) {
			timeslotdat = s;
			timeSlotList = timeSlotList + "(\'" + doctorProfileModel.getDrid() + "\',\'"
					+ doctorProfileModel.getUserid() + "\'" + ",\'" + doctorProfileModel.getRating() + "\',\'"
					+ doctorProfileModel.getAppno() + "\'" + ",\'" + doctorProfileModel.getReviews() + "\',\'"
					+ timeslotdat + "\'),";

		}
		timeSlotList = timeSlotList.substring(0, timeSlotList.length() - 1);

		try {

			if (doctorProfileModel.getDrid() != null && doctorProfileModel.getDrid() != "") {
				List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_rating")

						.setParameter("likeType", timeSlotList).getResultList();

				jsonResponse.setBody(x.get(0));
				Util.setJsonResponse(jsonResponse, x.get(0), ResponseStatus.success,
						ApiResponseMessage.UPDATED_SUCCESSFULLY);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_FAILURE);
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postHomeserviceDocumentation Dao ends" + response);
		return response;
	}

	// get home service search
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CaseStudyModel>>> getOrderDetails(String orderId) {
		logger.info("Method : getOrderDetails Dao starts" + orderId);

		List<CaseStudyModel> homeservicesearch = new ArrayList<CaseStudyModel>();
		JsonResponse<List<CaseStudyModel>> jsonResponse = new JsonResponse<List<CaseStudyModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_order_details").setParameter("orderId", orderId)
					.getResultList();
			for (Object[] m : x) {
				Object regDt = null;
				if (m[4] != null) {
					regDt = "Case Study" + "_" + m[4];
				}
				CaseStudyModel dropDownModel = new CaseStudyModel(m[0], m[1], m[2], m[3], regDt);
				homeservicesearch.add(dropDownModel);
			}
			jsonResponse.setBody(homeservicesearch);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CaseStudyModel>>> response = new ResponseEntity<JsonResponse<List<CaseStudyModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getOrderDetails Dao ends" + response);

		return response;
	}

	/*
	 * @SuppressWarnings("unchecked") public ResponseEntity<JsonResponse<Object>>
	 * posthomeServicePayment( HomeServicePaymentModel homeServicePaymentModel) {
	 * logger.info("Method : posthomeServicePayment Dao starts" +
	 * homeServicePaymentModel);
	 * 
	 * JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
	 * 
	 * try {
	 * 
	 * if (homeServicePaymentModel.getOrderId() != null &&
	 * homeServicePaymentModel.getOrderId() != "") { List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("post_homeservice_amount")
	 * .setParameter("orderId", homeServicePaymentModel.getOrderId())
	 * .setParameter("dates",
	 * DateFormatter.getStringDate(homeServicePaymentModel.getDate()))
	 * .setParameter("amount", homeServicePaymentModel.getAmount())
	 * 
	 * .getResultList();
	 * 
	 * } jsonResponse.setCode("success");
	 * jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
	 * ApiResponseMessage.UNKNOWN_FAILURE); }
	 * 
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK);
	 * 
	 * logger.info("Method : posthomeServicePayment Dao ends" + response); return
	 * response; }
	 */

	@SuppressWarnings("unchecked")
	public JsonResponse<HomeServicePaymentModel> posthomeServicePayment(HomeServicePaymentModel prescriptionModel) {

		logger.info("Method : posthomeServicePayment starts" + prescriptionModel);

		JsonResponse<HomeServicePaymentModel> resp = new JsonResponse<HomeServicePaymentModel>();
		HomeServicePaymentModel dlist = new HomeServicePaymentModel();
		String paymentlist = "";
		String amounts = "";
		if (prescriptionModel.getPaylist() != null) {
			if (prescriptionModel.getPaylist().size() > 0) {
				for (DropDownModel m : prescriptionModel.getPaylist()) {
					paymentlist = paymentlist + "(order_id1,\'" + m.getKey() + "\',\'" + m.getName() + "\',\'"
							+ m.getCode() + "\',\'" + m.getImage() + "\'),";
					amounts = m.getKey();
					logger.info("lossssst" + paymentlist);

				}
				paymentlist = paymentlist.substring(0, paymentlist.length() - 1);
			} else {
				paymentlist = "";
			}

		}
		// logger.info("Method : posthomeServicePayment starts" +
		// prescriptionModel.getPaylist().get(1));

		try {
			// if (prescriptionModel.getOrderId() != null) {
			List<Object[]> x = em.createNamedStoredProcedureQuery("post_homeservice_amount")
					.setParameter("paymentlist", paymentlist).setParameter("amounts", amounts).getResultList();
			resp.setCode("success");
			resp.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			/*
			 * } else {
			 * 
			 * }
			 */
		} catch (Exception e) {
			logger.info("posthomeServicePayments: " + e);
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : posthomeServicePayment ends" + resp);
		return resp;
	}

	// get home service search
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<HomeServicePaymentModel>>> getHomeServicePayment(String orderId) {
		logger.info("Method : getHomeServicePayment Dao starts" + orderId);

		List<HomeServicePaymentModel> homeservicesearch = new ArrayList<HomeServicePaymentModel>();
		JsonResponse<List<HomeServicePaymentModel>> jsonResponse = new JsonResponse<List<HomeServicePaymentModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_payment_homeservice_view")
					.setParameter("orderId", orderId).getResultList();
			for (Object[] m : x) {

				HomeServicePaymentModel dropDownModel = new HomeServicePaymentModel(m[0], m[1], m[2], m[3], m[4]);
				homeservicesearch.add(dropDownModel);
			}
			jsonResponse.setBody(homeservicesearch);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<HomeServicePaymentModel>>> response = new ResponseEntity<JsonResponse<List<HomeServicePaymentModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getHomeServicePayment Dao ends" + response);

		return response;
	}

}
