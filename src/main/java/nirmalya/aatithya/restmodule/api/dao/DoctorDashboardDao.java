package nirmalya.aatithya.restmodule.api.dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.CureEasyDrAppointBookingAPIModel;
import nirmalya.aatithya.restmodule.api.model.DoctorAppointmentHistoryModel;
import nirmalya.aatithya.restmodule.api.model.DoctorDashboardAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.DoctorProfileModel;
import nirmalya.aatithya.restmodule.api.model.DoctorProfileUpdateModel;
import nirmalya.aatithya.restmodule.api.model.PatientAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.PaymentGatewayAPIModel;
import nirmalya.aatithya.restmodule.api.model.TestNamedropdownModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.MailService;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.PushNotification;
import nirmalya.aatithya.restmodule.util.SMSGatewayMessage;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class DoctorDashboardDao {
	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	MailService mailService;
	CommonUsed commonUsed = new CommonUsed();
	PushNotification pushNotification = new PushNotification();
	Logger logger = LoggerFactory.getLogger(DashboardAPIDao.class);

	// getdoctorprofile
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DoctorProfileModel>>> getdoctorprofileview(String doctorId) {
		logger.info("Method : getdoctorprofileview Dao starts");
		System.out.println("doctorId====" + doctorId);
		List<DoctorProfileModel> getdoctorprofileview = new ArrayList<DoctorProfileModel>();
		JsonResponse<List<DoctorProfileModel>> jsonResponse = new JsonResponse<List<DoctorProfileModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("doctor_profile_details")
					.setParameter("doctorId", doctorId).getResultList();
			String fileName = null;
			for (Object[] m : x1) {

				String prfileImg = null;
				if (m[10] != null && m[10] != "" && m[10] != " " && !m[10].toString().equals(" ")
						&& !m[10].toString().equals(null) && !m[10].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[10].toString();
				} else {
					prfileImg = "";
				}

				String doctorSign = null;
				if (m[14] != null && m[14] != "" && m[14] != " " && !m[14].toString().equals(" ")
						&& !m[14].toString().equals(null) && !m[14].toString().equals("")) {
					doctorSign = env.getBaseURL() + "cureeazyrest/document/document/" + m[14].toString();
				} else {
					doctorSign = "";
				}
				DoctorProfileModel doctorProfileModel = new DoctorProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], prfileImg, m[11], m[12], m[13], m[14], m[15], m[16], m[17], m[18], m[19],
						m[20], m[21], m[22], m[23], m[24], m[25], m[26], m[27], m[28], m[29], m[30], m[31], doctorSign);
				getdoctorprofileview.add(doctorProfileModel);
			}

			if (getdoctorprofileview.size() > 0) {
				Util.setJsonResponse(jsonResponse, getdoctorprofileview, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(getdoctorprofileview);
		ResponseEntity<JsonResponse<List<DoctorProfileModel>>> response = new ResponseEntity<JsonResponse<List<DoctorProfileModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getdoctorprofileview Dao ends"+response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> updateDoctorProfile(DoctorProfileModel doctorProfileModel) {
		logger.info("Method : updateDoctorProfile Dao starts" + doctorProfileModel);

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String timeSlotList = "";
		String timeslotdat = "";
		System.out.println("%%%%" + doctorProfileModel.getTimeSlotList());

		List<String> tokens = new ArrayList<>(doctorProfileModel.getTimeSlotList());
		String lastTimeslot = tokens.get(tokens.size() - 1);
		System.out.println("tokens" + lastTimeslot);
		System.out.println("tokens" + tokens);
		List<String> breakList = new ArrayList<>(doctorProfileModel.getBreakList());
		System.out.println("breakList" + breakList);
		// find first element
		String first = breakList.get(0);
		System.out.println("first" + first);
		// find last element
		String last = breakList.get(breakList.size() - 1);
		System.out.println("last" + last);
		String str = "";
		for (String keyy : tokens) {
			str += keyy + ",";
		}
		String[] strdata = str.split(",");

		for (String s : strdata) {
			timeslotdat = s;
			timeSlotList = timeSlotList + "(\'" + doctorProfileModel.getDoctorId() + "\',\'" + timeslotdat + "\'),";

		}
		timeSlotList = timeSlotList.substring(0, timeSlotList.length() - 1);

		/*
		 * try { byte[] bytes =
		 * Base64.getDecoder().decode(doctorProfileModel.getDoctorSignature()); String
		 * imageName = null; imageName = saveAllImage2(bytes,
		 * doctorProfileModel.getExtension(), doctorProfileModel.getDoctorId());
		 * doctorProfileModel.setDoctorSignature(imageName); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		System.out.println("timeSlotList" + timeSlotList);
		try {

			if (doctorProfileModel.getDoctorId() != null && doctorProfileModel.getDoctorId() != "") {
				List<Object[]> x = em.createNamedStoredProcedureQuery("update_doctor_profile")
						.setParameter("getPhone", doctorProfileModel.getPhone())
						.setParameter("getGender", doctorProfileModel.getGenderId())
						.setParameter("getEducation", doctorProfileModel.getEducation())
						.setParameter("getLanguage", doctorProfileModel.getLanguage())
						.setParameter("getClinicName", doctorProfileModel.getClinicName())
						.setParameter("getAddress", doctorProfileModel.getAddress())
						.setParameter("getCity", doctorProfileModel.getCity())
						.setParameter("getAudioFee", doctorProfileModel.getAudioFee())
						.setParameter("getVideoFee", doctorProfileModel.getVideoFee())
						.setParameter("getPhysicalFee", doctorProfileModel.getPhysicalFee())
						.setParameter("getAccountName", doctorProfileModel.getAccountName())
						.setParameter("getIfscCode", doctorProfileModel.getIfscCode())
						.setParameter("getAccountNumber", doctorProfileModel.getAccountNumber())
						.setParameter("getOpeningTime", doctorProfileModel.getOpeningTime())
						.setParameter("getClosingTime", doctorProfileModel.getClosingTime())
						.setParameter("getBreakFrom", doctorProfileModel.getBreakFrom())
						.setParameter("getBreakTime", doctorProfileModel.getBreakTime())
						.setParameter("getSlotBooking", doctorProfileModel.getDurationId())
						.setParameter("getOffDay", doctorProfileModel.getOffDayId())
						.setParameter("getDoctorId", doctorProfileModel.getDoctorId())
						.setParameter("packageList", timeSlotList).setParameter("first", first)
						.setParameter("last", last)
						//.setParameter("doctorSign", doctorProfileModel.getDoctorSignature())
						.setParameter("p_lastTimeslot",lastTimeslot)
						.getResultList();

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

		logger.info("Method : updateDoctorProfile Dao ends" + response);
		return response;
	}

	// user document view
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctProfileImgView(String doctorId) {
		logger.info("Method : getDoctProfileImgView Dao starts");

		List<DropDownModel> getDoctProfileImgView = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("doctor_profile_img_view")
					.setParameter("userId", doctorId).getResultList();
			String fileName = null;
			for (Object[] m : x1) {
				DropDownModel doctorProfileImgModel = new DropDownModel(m[0], null, null, m[1], null);
				getDoctProfileImgView.add(doctorProfileImgModel);
			}

			for (DropDownModel a : getDoctProfileImgView) {
				// fileName =
				// env.getBaseURL()+"nirmalyaRest/document/newsmedia/"+a.getFileName();
				fileName = env.getBaseURL() + "cureeazyrest/document/document/" + a.getImage();
				a.setImage(fileName);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(getDoctProfileImgView);
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getuserdocumentview Dao ends");
		System.out.println(response);
		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully");

		return response;
	}

	// getdoctorappointmentdetails
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>> getdoctordashboardappointmentdetails(
			String doctorId, String status) {
		logger.info("Method : getdoctordashboardappointmentdetails Dao starts");
		System.out.println("doctorId##############" + doctorId);
		List<DoctorDashboardAppointmentDetailsModel> getdoctordashboardappointmentdetails = new ArrayList<DoctorDashboardAppointmentDetailsModel>();
		JsonResponse<List<DoctorDashboardAppointmentDetailsModel>> jsonResponse = new JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("doctor_dashboard_appointment_details")
					.setParameter("doctorId", doctorId).setParameter("status", status).getResultList();
			for (Object[] m : x1) {
				String prfileImg = null;
				if (m[7] != null && m[7] != "" && m[7] != " " && !m[7].toString().equals(" ")
						&& !m[7].toString().equals(null) && !m[7].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[7].toString();
				} else {
					prfileImg = "";
				}
				Object appointDates = null;
				if (m[1] != null) {
					appointDates = DateFormatter.returnStringDateNew(m[1].toString());
				}
				DoctorDashboardAppointmentDetailsModel doctorDashboardAppointmentDetailsModel = new DoctorDashboardAppointmentDetailsModel(
						m[0], appointDates, m[2], m[3], m[4], m[5], m[6], prfileImg, m[8], m[9], m[10]);
				getdoctordashboardappointmentdetails.add(doctorDashboardAppointmentDetailsModel);
				logger.info("doctorDashboardAppointmentDetailsModel" + doctorDashboardAppointmentDetailsModel);
			}

			if (getdoctordashboardappointmentdetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, getdoctordashboardappointmentdetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(getdoctordashboardappointmentdetails);
		ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>> response = new ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getdoctordashboardappointmentdetails Dao ends" + response);
		return response;
	}
	// post doctor data Api

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> getDoctorAppointmentstatus(String apptId, String status,
			String doctorid) {
		logger.info("Method : getDoctorAppointmentstatus Dao starts");

		JsonResponse<PaymentGatewayAPIModel> jsonResponse = new JsonResponse<PaymentGatewayAPIModel>();
		PaymentGatewayAPIModel list = new PaymentGatewayAPIModel();

		Boolean validity = true;
		String pname = "";
		String email = "";
		String statuss = "";
		String appointDate = "";
		String appointTime = "";
		String phno = "";
		String token = "";
		String userId = "";
		if (validity) {
			System.out.println("useridssss====" + apptId);
			System.out.println("feedback====" + status);
			try {
				if (apptId != null && apptId != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("update_doctor_appoint_status")
							.setParameter("apptId", apptId).setParameter("status", status)
							.setParameter("doctorid", doctorid).getResultList();

					for (Object[] m : x) {
						System.out.println(Arrays.toString(m));
						Object adate = null;
						if (m[7] != null) {
							adate = DateFormatter.returnStringDateNew(m[7].toString());
						}
						PaymentGatewayAPIModel dropDownModel = new PaymentGatewayAPIModel(m[0], m[1], m[2], m[3], m[4],
								m[5], m[6], adate, null, null);
						list = dropDownModel;
						userId = dropDownModel.getUserId();
						pname = dropDownModel.getUserName();
						email = dropDownModel.getEmail();
						statuss = dropDownModel.getStatus();
						appointDate = dropDownModel.getPaymentdate();
						appointTime = dropDownModel.getPaymenttime();
						phno = dropDownModel.getPhno();
						token = dropDownModel.getDeviceToken();
					}
					String to = email;
					String username = pname;
					String sub = "Cureez Mail";
					String DeviceIdKey = token;
					String mobileNo = phno;
					String responsecode = "";
					String responsecode1 = "";
					/*
					 * String msg = "Hi  "+username+","+ "Your Phone Number Is is "+phno+"."+
					 * "This is the Confirmation Message, Your appointment will be booked.";
					 */
					String msg = "Hi  " + username + "," + "Your Appointment Details " + appointTime + ","
							+ "appointment is " + statuss + "," + "for  " + appointDate + ". " + "Thanks, Team CureEZ.";

					String dataList = "Hi  " + username + "," + "Your Appointment Details " + appointTime + ","
							+ "appointment is " + statuss + "," + "for  " + appointDate + ". " + "Thanks, Team CureEZ.";

					try {
						responsecode1 = pushNotification.pushFCMNotifications(DeviceIdKey, dataList);
						System.out.println("msgIdddddddddddddd" + responsecode1);
					} catch (Exception e) {
						e.printStackTrace();
					}

					/*
					 * String message = SMSGatewayMessage.APPOINTMENT.replace("{name}",
					 * username).replace("{appoint}", appointTime) .replace("{appointDate}",
					 * appointDate);
					 */
					String message = SMSGatewayMessage.ORDERCONFIRM.replace("{name}", username)
							.replace("{time}", appointTime).replace("{status}", statuss)
							.replace("{paymentDate}", appointDate);
					// .replace("{paymentDate}", paymentDate);
					System.out.println("message" + message);

					try {
						responsecode = commonUsed.sendCureezSmss(mobileNo, message);
						System.out.println("msgIdddddddddddddd" + responsecode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					String type = "Doctor Accepted the Consultation";
					try {
						List<Object[]> x1 = em.createNamedStoredProcedureQuery("doctor_accept_notification")
								.setParameter("responsecode", responsecode1).setParameter("userid", userId)
								.setParameter("dataList", dataList).setParameter("type", type).getResultList();

					} catch (Exception e) {
						e.printStackTrace();
					}
					 mailService.sendEmail(to,sub,msg);
					jsonResponse.setBody(list);
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> response = new ResponseEntity<JsonResponse<PaymentGatewayAPIModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getDoctorAppointmentstatus Dao ends");
		return response;
	}

	// getviewpatientappointmentdetails
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<PatientAppointmentDetailsModel>>> getviewpatientappointmentdetails(
			String doctorId, String orderId) {
		logger.info("Method : getviewpatientappointmentdetails Dao starts");
		System.out.println("doctorId@@@@@@@@@@@@@@@" + doctorId);
		List<PatientAppointmentDetailsModel> getviewpatientappointmentdetails = new ArrayList<PatientAppointmentDetailsModel>();
		JsonResponse<List<PatientAppointmentDetailsModel>> jsonResponse = new JsonResponse<List<PatientAppointmentDetailsModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("patient_view_appointment_details")
					.setParameter("doctorId", doctorId).setParameter("orderId", orderId).getResultList();
			for (Object[] m : x1) {
				Object appointDates = null;
				if (m[1] != null) {
					appointDates = DateFormatter.returnStringDateNew(m[1].toString());
				}
				PatientAppointmentDetailsModel patientAppointmentDetailsModel = new PatientAppointmentDetailsModel(m[0],
						appointDates, m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12]);
				getviewpatientappointmentdetails.add(patientAppointmentDetailsModel);
			}

			for (PatientAppointmentDetailsModel a : getviewpatientappointmentdetails) {
				doctorId = env.getBaseURL() + "cureeazyrest/document/document/" + a.getDocument();
				a.setDocument(doctorId);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(getviewpatientappointmentdetails);
		ResponseEntity<JsonResponse<List<PatientAppointmentDetailsModel>>> response = new ResponseEntity<JsonResponse<List<PatientAppointmentDetailsModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getviewpatientappointmentdetails Dao ends");
		System.out.println(response);
		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully");
		return response;
	}

	// Cancel Reason

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getDoctorCancelAppointment(String apptId, String status, String reason,
			String doctorid) {
		logger.info("Method : getDoctorCancelAppointment Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;

		if (validity) {
			System.out.println("useridssss====" + apptId);
			System.out.println("feedback====" + status);
			try {
				if (apptId != null && apptId != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_cancel_appointment_reason")
							.setParameter("apptId", apptId).setParameter("status", status)
							.setParameter("reason", reason).setParameter("doctorid", doctorid).getResultList();
					jsonResponse.setBody(x.get(0));
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : getDoctorCancelAppointment Dao ends");
		return response;
	}

	// Cancel Reason

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getUserCancelAppointment(String apptId, String reason, String userid) {
		logger.info("Method : getUserCancelAppointment Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;

		if (validity) {
			System.out.println("useridssss====" + apptId);
			// System.out.println("feedback====" + status);
			try {
				if (apptId != null && apptId != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("user_cancel_appointment_reason")
							.setParameter("apptId", apptId)
							// .setParameter("status", status)
							.setParameter("reason", reason).setParameter("userid", userid).getResultList();
					jsonResponse.setBody(x.get(0));
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : getUserCancelAppointment Dao ends");
		return response;
	}

	public String saveAllImage(byte[] imageBytes, String ext, String pId) {
		logger.info("Method : saveAllImage starts");
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
						String outputImagePath = env.getFileUploadProfile() + "thumb/" + imageName;
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
// post update user profile

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<DoctorProfileUpdateModel>> postUpdateDoctorProfile(
			DoctorProfileUpdateModel doctmodel) {
		logger.info("Method : postUpdateDoctorProfile Dao starts");
		System.out.println(doctmodel);
		JsonResponse<DoctorProfileUpdateModel> jsonResponse = new JsonResponse<DoctorProfileUpdateModel>();
		Boolean validity = true;

		if (doctmodel.getDoctorId() == null || doctmodel.getDoctorId() == "") {
			jsonResponse.setMessage("postUpdateDoctorProfile");
			validity = false;
		}
		if (doctmodel.getDoctorProfileImg() != null && doctmodel.getDoctorProfileImg() != "") {
			try {
				byte[] bytes = Base64.getDecoder().decode(doctmodel.getDoctorProfileImg());
				String imageName = null;
				imageName = saveAllImage2(bytes, doctmodel.getExtension(), doctmodel.getDoctorId());
				doctmodel.setDoctorProfileImg(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (validity) {
			System.out.println("doctmodel===" + doctmodel);
			try {
				if (doctmodel.getDoctorId() != null && doctmodel.getDoctorId() != "") {

					List<Object[]> x = em.createNamedStoredProcedureQuery("post_update_doctor_profile")
							.setParameter("doctorId", doctmodel.getDoctorId())
							.setParameter("doctorProfile", doctmodel.getDoctorProfileImg()).getResultList();
					// jsonResponse.setBody(x.get(0));
				}

				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_FAILED);
			}
		}
		ResponseEntity<JsonResponse<DoctorProfileUpdateModel>> response = new ResponseEntity<JsonResponse<DoctorProfileUpdateModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postUpdateDoctorProfile Dao ends");
		return response;
	}

	// getdoctorappointmenthistory
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DoctorAppointmentHistoryModel>>> getdoctorappointmenthistory(
			String doctorId, String status) {
		logger.info("Method : getdoctorappointmenthistory Dao starts");
		List<DoctorAppointmentHistoryModel> getdoctorappointmenthistory = new ArrayList<DoctorAppointmentHistoryModel>();
		JsonResponse<List<DoctorAppointmentHistoryModel>> jsonResponse = new JsonResponse<List<DoctorAppointmentHistoryModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("doctor_appointment_history")
					.setParameter("doctorId", doctorId).setParameter("status", status).getResultList();
			for (Object[] m : x1) {
				Object completeStatus = null;

				if (m[3].equals("Confirmed")) {
					completeStatus = "Completed";
				} else {
					completeStatus = "Rejected";
				}
				DoctorAppointmentHistoryModel doctorAppointmentHistoryModel = new DoctorAppointmentHistoryModel(m[0],
						m[1], m[2], completeStatus, m[4], m[5], m[6]);
				getdoctorappointmenthistory.add(doctorAppointmentHistoryModel);

			}
			if (getdoctorappointmenthistory.size() > 0) {
				jsonResponse.setBody(getdoctorappointmenthistory);
				Util.setJsonResponse(jsonResponse, getdoctorappointmenthistory, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<DoctorAppointmentHistoryModel>>> response = new ResponseEntity<JsonResponse<List<DoctorAppointmentHistoryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getdoctorappointmenthistory Dao ends");
		return response;
	}

	// isExist Status
	@SuppressWarnings("unchecked")
	public Boolean isExistStatus(String appointId) {
		logger.info("Method : isExistStatus Dao starts" + appointId);

		Boolean isExist = false;

		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		String appoint = null;
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_check_status")
					.setParameter("appointId", appointId).getResultList();
			for (Object m : x) {
				DropDownModel dropDownModel = new DropDownModel(m, null);
				dataList.add(dropDownModel);
				appoint = dropDownModel.getKey();
				logger.info("appoint" + appoint);
			}

			if (appoint.equals("Confirmed")) {
				isExist = true;
			} else {
				isExist = false;
			}
		} catch (Exception e) {
			isExist = false;
		}
		logger.info("dropDownModel" + dataList);
		logger.info("Method : isExistStatus Dao ends" + isExist);
		return isExist;
	}

	// getdoctorappointmenthistory
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DoctorAppointmentHistoryModel>>> getDoctorAppointmentDetails(
			String appointId) {
		logger.info("Method : getDoctorAppointmentDetails Dao starts" + appointId);
		List<DoctorAppointmentHistoryModel> getdoctorappointmenthistory = new ArrayList<DoctorAppointmentHistoryModel>();
		JsonResponse<List<DoctorAppointmentHistoryModel>> jsonResponse = new JsonResponse<List<DoctorAppointmentHistoryModel>>();
		String statusCheck = null;

		Boolean checkstatus;
		checkstatus = isExistStatus(appointId);
		logger.info("checkstatus" + checkstatus);

		try {
			if (checkstatus.equals(true)) {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("doctor_appointment_complete_details")
						.setParameter("appointId", appointId)
						// .setParameter("status", status)
						.getResultList();
				Object completeStatus = null;
				for (Object[] m : x1) {

					if (m[4].equals("Confirmed")) {
						completeStatus = "Completed";
					} else {
						completeStatus = "Rejected";
					}
					Object date = null;
					if (m[6] != null) {
						date = DateFormatter.returnStringDateNew(m[6].toString());
					}
					String prfileImg = null;
					if (m[11] != null && m[11] != "" && m[11] != " " && !m[11].toString().equals(" ")
							&& !m[11].toString().equals(null) && !m[11].toString().equals("")) {
						prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[11].toString();
					} else {
						prfileImg = "";
					}
					String prescriptionImgs = null;
					if (m[14] != null && m[14] != "" && m[14] != " " && !m[14].toString().equals(" ")
							&& !m[14].toString().equals(null) && !m[14].toString().equals("")) {
						prescriptionImgs = env.getBaseURL() + "cureeazyrest/document/document/" + m[14].toString();
					} else {
						prescriptionImgs = "";
					}
					DoctorAppointmentHistoryModel doctorAppointmentHistoryModel = new DoctorAppointmentHistoryModel(
							m[0], m[1], m[2], m[3], completeStatus, m[5], date, m[7], m[8], m[9], m[10], prfileImg,
							m[12], m[13], prescriptionImgs);
					getdoctorappointmenthistory.add(doctorAppointmentHistoryModel);

				}
			} else {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("doctor_appointment_complete_rejected")
						.setParameter("appointId", appointId)
						// .setParameter("status", status)
						.getResultList();
				Object completeStatus = null;
				for (Object[] m : x1) {

					if (m[4].equals("Confirmed")) {
						completeStatus = "Completed";
					} else {
						completeStatus = "Rejected";
					}
					Object date = null;
					if (m[6] != null) {
						date = DateFormatter.returnStringDateNew(m[6].toString());
					}
					String prfileImg = null;
					if (m[11] != null && m[11] != "" && m[11] != " " && !m[11].toString().equals(" ")
							&& !m[11].toString().equals(null) && !m[11].toString().equals("")) {
						prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[11].toString();
					} else {
						prfileImg = "";
					}
					String prescriptionImgs = null;
					if (m[14] != null && m[14] != "" && m[14] != " " && !m[14].toString().equals(" ")
							&& !m[14].toString().equals(null) && !m[14].toString().equals("")) {
						prescriptionImgs = env.getBaseURL() + "cureeazyrest/document/document/" + m[14].toString();
					} else {
						prescriptionImgs = "";
					}
					DoctorAppointmentHistoryModel doctorAppointmentHistoryModel = new DoctorAppointmentHistoryModel(
							m[0], m[1], m[2], m[3], completeStatus, m[5], date, m[7], m[8], m[9], m[10], prfileImg,
							m[12], m[13], prescriptionImgs);
					getdoctorappointmenthistory.add(doctorAppointmentHistoryModel);

				}
			}

			if (getdoctorappointmenthistory.size() > 0) {
				jsonResponse.setBody(getdoctorappointmenthistory);
				Util.setJsonResponse(jsonResponse, getdoctorappointmenthistory, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<DoctorAppointmentHistoryModel>>> response = new ResponseEntity<JsonResponse<List<DoctorAppointmentHistoryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getDoctorAppointmentDetails Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<DropDownModel>> getDoctorfeedbackApi(String doctorid, String feedback) {
		logger.info("Method : postDoctorDataApi Dao starts");

		JsonResponse<DropDownModel> jsonResponse = new JsonResponse<DropDownModel>();
		DropDownModel dlist = new DropDownModel();
		Boolean validity = true;
		String UserId = "";
		String UserName = "";
		String Mail = "";

		if (validity) {
			System.out.println("useridssss====" + doctorid);
			System.out.println("feedback====" + feedback);
			try {
				if (doctorid != null && doctorid != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_feedback_api")
							.setParameter("doctorid", doctorid).setParameter("feedback", feedback).getResultList();

					for (Object[] m : x) {
						System.out.println(Arrays.toString(m));

						DropDownModel dropDownModel = new DropDownModel(m[0], m[1], m[2]);
						dlist = dropDownModel;
						System.out.println("Data" + dlist);
						UserId = dropDownModel.getKey();
						UserName = dropDownModel.getName();
						Mail = dropDownModel.getCode();
					}

					String to = Mail;
					String username = UserName;
					String sub = "Cureez Mail";
					String msg = "Hi  " + username + "," + System.lineSeparator() + System.lineSeparator()
							+ "Your Login Id And Password";

					System.out.println("TOOOOO" + to);
					System.out.println("sub" + sub);
					System.out.println("msg" + msg);
					mailService.sendEmail(to, sub, msg);
					jsonResponse.setBody(dlist);
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<DropDownModel>> response = new ResponseEntity<JsonResponse<DropDownModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postDoctorDataApi Dao ends");
		return response;

	}

	// getviewpatientappointmentdetails
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<PatientAppointmentDetailsModel>>> doctorAppointHistory(String doctorId,
			String status) {
		logger.info("Method : getviewpatientappointmentdetails Dao starts");
		List<PatientAppointmentDetailsModel> getviewpatientappointmentdetails = new ArrayList<PatientAppointmentDetailsModel>();
		JsonResponse<List<PatientAppointmentDetailsModel>> jsonResponse = new JsonResponse<List<PatientAppointmentDetailsModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("doctor_appoint_history_details")
					.setParameter("doctorId", doctorId).setParameter("status", status).getResultList();
			for (Object[] m : x1) {
				PatientAppointmentDetailsModel patientAppointmentDetailsModel = new PatientAppointmentDetailsModel(m[0],
						m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10], m[11], null);
				getviewpatientappointmentdetails.add(patientAppointmentDetailsModel);
			}
			if (getviewpatientappointmentdetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, getviewpatientappointmentdetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
			for (PatientAppointmentDetailsModel a : getviewpatientappointmentdetails) {
				doctorId = env.getBaseURL() + "cureeazyrest/document/document/" + a.getDocument();
				a.setDocument(doctorId);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(getviewpatientappointmentdetails);
		ResponseEntity<JsonResponse<List<PatientAppointmentDetailsModel>>> response = new ResponseEntity<JsonResponse<List<PatientAppointmentDetailsModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : doctorAppointHistory Dao ends");
		return response;
	}

	// get test name drop down model
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<TestNamedropdownModel>>> gettestname() {
		logger.info("Method : gettestname Dao starts");

		List<TestNamedropdownModel> testname = new ArrayList<TestNamedropdownModel>();
		JsonResponse<List<TestNamedropdownModel>> jsonResponse = new JsonResponse<List<TestNamedropdownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_test_name").getResultList();
			for (Object[] m : x) {
				TestNamedropdownModel testNamedropdownModel = new TestNamedropdownModel(m[0], m[1], m[2], m[3]);
				testname.add(testNamedropdownModel);
			}
			jsonResponse.setBody(testname);

			if (testname.size() > 0) {
				Util.setJsonResponse(jsonResponse, testname, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<TestNamedropdownModel>>> response = new ResponseEntity<JsonResponse<List<TestNamedropdownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : gettestname Dao ends");

		return response;
	}

	// get test name drop down model
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getTimeslot() {
		logger.info("Method : getTimeslot Dao starts");

		List<DropDownModel> testname = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_timeslot").getResultList();
			for (Object[] m : x) {
				DropDownModel testNamedropdownModel = new DropDownModel(m[0], m[1]);
				testname.add(testNamedropdownModel);
			}
			jsonResponse.setBody(testname);

			if (testname.size() > 0) {
				Util.setJsonResponse(jsonResponse, testname, ResponseStatus.success,
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
		logger.info("Method : getTimeslot Dao ends");

		return response;
	}

	// get test name drop down model
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorOffday() {
		logger.info("Method : getDoctorOffday Dao starts");

		List<DropDownModel> testname = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_offday").getResultList();
			for (Object[] m : x) {
				DropDownModel testNamedropdownModel = new DropDownModel(m[0], m[1]);
				testname.add(testNamedropdownModel);
			}
			jsonResponse.setBody(testname);

			if (testname.size() > 0) {
				Util.setJsonResponse(jsonResponse, testname, ResponseStatus.success,
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
		logger.info("Method : getDoctorOffday Dao ends");

		return response;
	}

	// get test name drop down model
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getNationality() {
		logger.info("Method : getNationality Dao starts");

		List<DropDownModel> testname = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_nationality").getResultList();
			for (Object[] m : x) {
				DropDownModel testNamedropdownModel = new DropDownModel(m[0], m[1]);
				testname.add(testNamedropdownModel);
			}
			jsonResponse.setBody(testname);

			if (testname.size() > 0) {
				Util.setJsonResponse(jsonResponse, testname, ResponseStatus.success,
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
		logger.info("Method : getNationality Dao ends");

		return response;
	}

	// get test name drop down model
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getBldGroup() {
		logger.info("Method : getBldGroup Dao starts");

		List<DropDownModel> testname = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_bldGroup").getResultList();
			for (Object[] m : x) {
				DropDownModel testNamedropdownModel = new DropDownModel(m[0], m[1]);
				testname.add(testNamedropdownModel);
			}
			jsonResponse.setBody(testname);

			if (testname.size() > 0) {
				Util.setJsonResponse(jsonResponse, testname, ResponseStatus.success,
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
		logger.info("Method : getBldGroup Dao ends");

		return response;
	}
}
