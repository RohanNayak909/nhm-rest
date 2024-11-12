package nirmalya.aatithya.restmodule.api.dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import nirmalya.aatithya.restmodule.api.model.APIAllergyModel;
import nirmalya.aatithya.restmodule.api.model.APIBioMedicalModel;
import nirmalya.aatithya.restmodule.api.model.APIEmergencyModel;
import nirmalya.aatithya.restmodule.api.model.APIMedicationModel;
import nirmalya.aatithya.restmodule.api.model.APIMedicineReminderModel;
import nirmalya.aatithya.restmodule.api.model.APIOrganDonationModel;
import nirmalya.aatithya.restmodule.api.model.APIPharmacyModel;
import nirmalya.aatithya.restmodule.api.model.APIVitalSignModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentListModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyTestListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyLabTestModel;
import nirmalya.aatithya.restmodule.api.model.DoctorAppointmentModel;
import nirmalya.aatithya.restmodule.api.model.DoctorRatingModel;
import nirmalya.aatithya.restmodule.api.model.EmergencyContactAPIModel;
import nirmalya.aatithya.restmodule.api.model.HealthRecordModel;
import nirmalya.aatithya.restmodule.api.model.IsEContactFDoctorValidModel;
import nirmalya.aatithya.restmodule.api.model.MedicineApiModel;
import nirmalya.aatithya.restmodule.api.model.PatientFamilyDetailsModel;
import nirmalya.aatithya.restmodule.api.model.PatientReportModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionTestModel;
import nirmalya.aatithya.restmodule.api.model.SearchDetailsModel;
import nirmalya.aatithya.restmodule.api.model.UserDetailsAPIModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileAPIModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileUpdateModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.GenerateRandomValue;
import nirmalya.aatithya.restmodule.util.SMSGatewayMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class MobileUserRestAPIDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	Logger logger = LoggerFactory.getLogger(MobileUserRestAPIDao.class);

	public static String generateRandom(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return new String(digits);
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

	/**
	 * @SuppressWarnings("unchecked") public ResponseEntity<JsonResponse<Object>>
	 * userSelfRegistrationApi(SignUpModel data) { logger.info("Method :
	 * userSelfRegistrationApi Dao starts");
	 * 
	 * JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
	 * 
	 * Boolean validity = true; if (data.getMobile() == null || data.getMobile() ==
	 * "") { jsonResponse.setMessage("Mobile Number Required"); validity = false; }
	 * else if (data.getfName() == null || data.getfName() == "") {
	 * jsonResponse.setMessage("First Name Required"); validity = false; } else if
	 * (data.getlName() == null || data.getlName() == "") {
	 * jsonResponse.setMessage("Last Name Required"); validity = false; } else if
	 * (data.getAge() == null && data.getDob() == null) {
	 * jsonResponse.setMessage("Age/DOB Required"); validity = false; } else if
	 * (data.getCountry() == null) { jsonResponse.setMessage("Country Required");
	 * validity = false; } else if (data.getState() == null) {
	 * jsonResponse.setMessage("State Required"); validity = false; } else if
	 * (data.getDistrictid() == null) { jsonResponse.setMessage("District
	 * Required"); validity = false; } else if (data.getCityid() == null) {
	 * jsonResponse.setMessage("City Required"); validity = false; }
	 * 
	 * Boolean isMobileExist = false;
	 * 
	 * Boolean isAadharExist = false;
	 * 
	 * if (!isMobileExist && !isAadharExist) { if (data.getfName() != null &&
	 * data.getfName() != "") { String noSpaceStr =
	 * data.getfName().replaceAll("\\s", ""); String substr =
	 * noSpaceStr.substring(0, 3); substr = substr.toUpperCase(); String random =
	 * generateRandom(5); data.setUserId(substr.concat(random)); } String pId =
	 * null; if (data.getId() == null || data.getId() == "") { String ccode = "";
	 * String scode = ""; if (data.getCountryCode() != null && data.getCountryCode()
	 * != "") { ccode = data.getCountryCode(); ccode = ccode.substring(0, 2); if
	 * (data.getStateCode() != null && data.getStateCode() != "") { scode =
	 * data.getStateCode(); } else { scode = "50"; } } else { ccode = "10"; scode =
	 * "50"; }
	 * 
	 * String random = generateRandom(12); pId = ccode.concat(scode).concat(random);
	 * }
	 * 
	 * String image = ""; if (data.getProfileImage().size() > 0) {
	 * 
	 * if (data.getProfileImage().get(0) != null && data.getProfileImage().get(0) !=
	 * "") { try { byte[] bytes =
	 * Base64.getDecoder().decode(data.getProfileImage().get(0)); String imageName =
	 * saveAllImage(bytes, data.getProfileImageType(), pId);
	 * data.setProfileImageName(imageName); image = imageName; } catch (Exception e)
	 * { e.printStackTrace(); } } }
	 * 
	 * String gender = ""; if (data.getGender() != null) { gender =
	 * data.getGender().toString(); }
	 * 
	 * String title = null; if (data.getTitle() != null) { title =
	 * data.getTitle().toString(); }
	 * 
	 * String city = ""; if (data.getCityid() != null) { city =
	 * data.getCityid().toString(); }
	 * 
	 * String dist = ""; if (data.getDistrictid() != null) { dist =
	 * data.getDistrictid().toString(); }
	 * 
	 * String state = ""; if (data.getState() != null) { state =
	 * data.getState().toString(); }
	 * 
	 * String country = ""; if (data.getCountry() != null) { country =
	 * data.getCountry().toString(); }
	 * 
	 * String ageyears = ""; if (data.getAgeYears() != null && data.getAgeYears() !=
	 * "") { ageyears = data.getAgeYears(); }
	 * 
	 * String patDOB = ""; if (data.getDob() != null && data.getDob() != "") {
	 * patDOB = DateFormatter.getStringDateNew(data.getDob()); } String age = ""; if
	 * (data.getAge() != null) { age = data.getAge().toString(); }
	 * 
	 * String qrcodename = "QR" + pId + ".png";
	 * 
	 * if (validity) { try { if (data.getId() == null || data.getId() == "") {
	 * 
	 * List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("userSelfRegistrationAPI")
	 * .setParameter("regid", pId).setParameter("userid", data.getUserId())
	 * .setParameter("patname", data.getfName()).setParameter("patlname",
	 * data.getlName()) .setParameter("patmobile",
	 * data.getMobile()).setParameter("profileimage", image) .setParameter("patage",
	 * age).setParameter("ageyears", ageyears) .setParameter("patdob",
	 * patDOB).setParameter("patgender", gender) .setParameter("state",
	 * state).setParameter("country", country) .setParameter("password",
	 * "$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6")
	 * .setParameter("enteredby", pId).setParameter("ttl",
	 * title).setParameter("dist", dist) .setParameter("city",
	 * city).setParameter("qrcode", qrcodename).getResultList();
	 * 
	 * jsonResponse.setBody(x.get(0));
	 * 
	 * }
	 * 
	 * jsonResponse.setCode("success"); jsonResponse.setMessage("User Registered
	 * Successfully");
	 * 
	 * String msg = "Welcome to eHealthSystem. You are registered successfully! Your
	 * UserId is " + pId + " or " + data.getMobile() + " and password is User@123 ";
	 * 
	 * String encodemsg = URLEncoder.encode(msg, "UTF-8");
	 * 
	 * CommonUsed.sendSMS(data.getMobile(), encodemsg);
	 * 
	 * CommonUsed.generateQRCode(qrcodename, pId, data.getMobile(), data.getfName(),
	 * env.getUserQrCode()); } catch (Exception e) { e.printStackTrace();
	 * jsonResponse.setCode("failed"); jsonResponse.setMessage("Something went
	 * wrong. Registration failed."); } } }
	 * 
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK);
	 * 
	 * logger.info("Method : userSelfRegistrationApi Dao ends"); return response; }
	 **/

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> updateUserProfileAPI(UserProfileAPIModel data) {
		logger.info("Method : updateUserProfileAPI Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
//		if (data.getfName() == null || data.getfName() == "") {
//			jsonResponse.setMessage("First Name Required");
//			validity = false;
//		} else if (data.getlName() == null || data.getlName() == "") {
//			jsonResponse.setMessage("Last Name Required");
//			validity = false;
//		} else 
		if (data.getDob() == null || data.getDob() == "") {
			jsonResponse.setMessage("Date Of Birth Required");
			validity = false;
		}

		Boolean isMobileExist = false;
		if (!isMobileExist) {

			String bgroup = null;
			if (data.getBloodGroup() != null) {
				bgroup = data.getBloodGroup().toString();
			}

			String ename = "";
			if (data.getfName() != null) {
				ename = data.getfName().toString();
			}

			String relation = "";
			if (data.geteRelation() != null) {
				relation = data.geteRelation().toString();
			}

			String emobile = "";
			if (data.geteMobile() != null) {
				emobile = data.geteMobile().toString();
			}

			String fdocname = "";
			if (data.getfDoctor() != null) {
				fdocname = data.getfDoctor().toString();
			}

			String speciality = "";
			if (data.getSpeciality() != null) {
				speciality = data.getSpeciality().toString();
			}

			String docnumber = "";
			if (data.getDocMobile() != null) {
				docnumber = data.getDocMobile().toString();
			}

			String patAddress = "";
			if (data.getAddress() != null && data.getAddress() != "") {
				patAddress = data.getAddress().toString();
			}

			String patDOB = null;
			if (data.getDob() != null && data.getDob() != "") {
				patDOB = DateFormatter.getStringDateNew(data.getDob());
			}

//			String image = "";
//			if (data.getpImage().size() > 0) {
//
//				if (data.getpImage().get(0) != null && data.getpImage().get(0) != "") {
//					try {
//						byte[] bytes = Base64.getDecoder().decode(data.getpImage().get(0));
//						String imageName = saveAllImage(bytes, data.getProfileImageType(), data.geteCardNo());
//						data.setProfileImageName(imageName);
//						image = imageName;
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
			if (validity) {
				try {

					if (data.geteCardNo() != null && data.geteCardNo() != "") {

						String gender = null;

						if (data.getGender().equals("1")) {
							gender = "Male";
						} else if (data.getGender().equals("2")) {
							gender = "Female";
						} else {
							gender = "Other";
						}

						List<Object[]> x = em.createNamedStoredProcedureQuery("updateUserProfile")
								.setParameter("regid", data.geteCardNo()).setParameter("patdob", patDOB)
								.setParameter("bloodgroup", bgroup).setParameter("address", patAddress)
								.setParameter("maritialstatus", data.getMaritialstatus())
								.setParameter("occupation", data.getOccupation())
								.setParameter("qualification", data.getQualification())
								.setParameter("specialization", data.getSpecialization())
								.setParameter("pancard", data.getPancardno())
								.setParameter("passport", data.getPassportno())
								.setParameter("adharcard", data.getAdharno())
								.setParameter("votercard", data.getVotercardno())
								.setParameter("licence", data.getLicenceno())
								.setParameter("licenceauth", data.getLicenceauthority())
								.setParameter("email", data.getEmail()).setParameter("pin", data.getPincode())
								.setParameter("city", data.getCityid()).setParameter("dist", data.getDistid())
								.setParameter("state", data.getStateid()).setParameter("country", data.getCountryid())
								.setParameter("mobile", data.getMobile()).setParameter("fname", data.getfName())
								.setParameter("lname", data.getlName()).setParameter("gender", gender).getResultList();

						jsonResponse.setBody(x.get(0));
						Util.setJsonResponse(jsonResponse, x.get(0), ResponseStatus.success,
								ApiResponseMessage.UPDATED_SUCCESSFULLY);
					}

				} catch (Exception e) {
					e.printStackTrace();
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_FAILURE);
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : updateUserProfileAPI Dao ends");
		return response;
	}

	/*
	 * @SuppressWarnings("unchecked") public ResponseEntity<JsonResponse<Object>>
	 * userSelfRegistrationApi(SignUpModel data) {
	 * logger.info("Method : userSelfRegistrationApi Dao starts");
	 * 
	 * JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
	 * 
	 * Boolean validity = true; if (StringUtil.isNull(data.getMobile())) {
	 * jsonResponse.setMessage("Mobile Number Required"); validity = false; } else
	 * if (StringUtil.isNull(data.getfName())) {
	 * jsonResponse.setMessage("First Name Required"); validity = false; } else if
	 * (StringUtil.isNull(data.getlName())) {
	 * jsonResponse.setMessage("Last Name Required"); validity = false; } else if
	 * (StringUtil.isNull(data.getCountry())) {
	 * jsonResponse.setMessage("Country Required"); validity = false; } else if
	 * (StringUtil.isNull(data.getState())) {
	 * jsonResponse.setMessage("State Required"); validity = false; } else if
	 * (StringUtil.isNull(data.getDistrictid())) {
	 * jsonResponse.setMessage("District Required"); validity = false; } else if
	 * (StringUtil.isNull(data.getCityid())) {
	 * jsonResponse.setMessage("City Required"); validity = false; }
	 * 
	 * Boolean isMobileExist = false;
	 * 
	 * Boolean isAadharExist = false;
	 * 
	 * if (!isMobileExist && !isAadharExist) { if
	 * (!StringUtil.isNull(data.getfName())) { String noSpaceStr =
	 * data.getfName().replaceAll("\\s", ""); String substr =
	 * noSpaceStr.substring(0, 3); substr = substr.toUpperCase(); String random =
	 * generateRandom(5); data.setUserId(substr.concat(random)); } String pId =
	 * null; if (StringUtil.isNull(data.getId())) { String ccode = ""; String scode
	 * = ""; if (!StringUtil.isNull(data.getCountryCode())) { ccode =
	 * data.getCountryCode(); ccode = ccode.substring(0, 2); if (data.getStateCode()
	 * != null && data.getStateCode() != "") { scode = data.getStateCode(); } else {
	 * scode = "99"; } } else { ccode = "99"; scode = "99"; }
	 * 
	 * String random = generateRandom(12); pId = ccode.concat(scode).concat(random);
	 * }
	 * 
	 * String image = ""; if (data.getProfileImage().size() > 0) {
	 * 
	 * if (data.getProfileImage().get(0) != null && data.getProfileImage().get(0) !=
	 * "") { try { byte[] bytes =
	 * Base64.getDecoder().decode(data.getProfileImage().get(0)); String imageName =
	 * saveAllImage(bytes, data.getProfileImageType(), pId);
	 * data.setProfileImageName(imageName); image = imageName; } catch (Exception e)
	 * { e.printStackTrace(); } } }
	 * 
	 * String gender = null; if (!StringUtil.isNull(data.getGender())) { gender =
	 * data.getGender().toString(); }
	 * 
	 * String title = null; if (!StringUtil.isNull(data.getTitle())) { title =
	 * data.getTitle().toString(); }
	 * 
	 * String city = null; if (!StringUtil.isNull(data.getCityid())) { city =
	 * data.getCityid().toString(); }
	 * 
	 * String dist = null; if (!StringUtil.isNull(data.getDistrictid())) { dist =
	 * data.getDistrictid().toString(); }
	 * 
	 * String state = null; if (!StringUtil.isNull(data.getState())) { state =
	 * data.getState().toString(); }
	 * 
	 * String country = null; if (!StringUtil.isNull(data.getCountry())) { country =
	 * data.getCountry().toString(); } String ageyears = null;
	 * 
	 * String patDOB = null;
	 * 
	 * String age = null;
	 * 
	 * String qrcodename = "QR" + pId + ".png";
	 * 
	 * Date date = new Date(); String reg_date = new
	 * SimpleDateFormat("yyyy-MM-dd").format(date); String reg_time = new
	 * SimpleDateFormat("HH:mm").format(date);
	 * 
	 * List<Object[]> x = null;
	 * 
	 * if (validity) { try { if (data.getId() == null || data.getId() == "") {
	 * 
	 * x =
	 * em.createNamedStoredProcedureQuery("userSelfRegistrationAPI").setParameter(
	 * "regid", pId) .setParameter("userid",
	 * data.getUserId()).setParameter("patname", data.getfName())
	 * .setParameter("patlname", data.getlName()).setParameter("patmobile",
	 * data.getMobile()) .setParameter("profileimage", image).setParameter("patage",
	 * age) .setParameter("ageyears", ageyears).setParameter("patdob", patDOB)
	 * .setParameter("patgender", gender).setParameter("state", state)
	 * .setParameter("country", country) .setParameter("password",
	 * "$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6")
	 * .setParameter("enteredby", pId).setParameter("ttl",
	 * title).setParameter("dist", dist) .setParameter("city",
	 * city).setParameter("qrcode", qrcodename).getResultList();
	 * 
	 * jsonResponse.setBody(x.get(0));
	 * 
	 * }
	 * 
	 * JSONObject obres = null;
	 * 
	 * if (jsonResponse.getBody() != null) {
	 * 
	 * if (data.getState().toString().contentEquals("35")) {
	 * 
	 * // if you need to pass form parameters in request with headers. Map<String,
	 * String> map = new HashMap<String, String>();
	 * 
	 * String fullname = data.getfName() + " " + data.getlName();
	 * 
	 * map.put("REG_UID", pId); map.put("REG_NAME", fullname); map.put("REG_PHONE",
	 * data.getMobile()); map.put("REG_DATE", reg_date); map.put("REG_TIME",
	 * reg_time); map.put("REG_CAMP", "");
	 * 
	 * map.put("REG_USER", "EHEALTH@NOVI000456"); map.put("REG_PWD",
	 * "Chitnis@eHalth"); map.put("REG_KEY", "cEWC43748dawCSA@@iihd@@");
	 * 
	 * JSONObject obj = new JSONObject(map);
	 * 
	 * String url = "https://pceterp.com/oraerp/sbpj/req/listenReg.php";
	 * 
	 * HttpHeaders requestHeaders = new HttpHeaders();
	 * 
	 * requestHeaders.add(HttpHeaders.CONTENT_TYPE,
	 * "application/x-www-form-urlencoded");
	 * 
	 * HttpEntity<String> httpEntity = new HttpEntity<String>(obj.toString(),
	 * requestHeaders);
	 * 
	 * ResponseEntity<String> st = restTemplate.exchange(url, HttpMethod.POST,
	 * httpEntity, String.class);
	 * 
	 * obres = new JSONObject(st.getBody());
	 * 
	 * JSONObject objectresponse = null; if (obres != null) { objectresponse =
	 * obres.getJSONObject("RESP"); }
	 * 
	 * if (objectresponse != null) { if
	 * (objectresponse.getString("TR_STATUS").equals("SUCCESS")) {
	 * 
	 * Util.setJsonResponse(jsonResponse, x.get(0), ResponseStatus.success,
	 * "User Registered Successfully");
	 * 
	 * String msg =
	 * "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
	 * + pId + " or " + data.getMobile() + " and password is User@123 ";
	 * 
	 * String encodemsg = URLEncoder.encode(msg, "UTF-8");
	 * 
	 * CommonUsed.sendSMS(data.getMobile(), encodemsg);
	 * 
	 * CommonUsed.generateQRCode(qrcodename, pId, data.getMobile(), data.getfName(),
	 * env.getUserQrCode()); } }
	 * 
	 * } else { Util.setJsonResponse(jsonResponse, x.get(0), ResponseStatus.success,
	 * "User Registered Successfully");
	 * 
	 * String msg =
	 * "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
	 * + pId + " or " + data.getMobile() + " and password is User@123 ";
	 * 
	 * String encodemsg = URLEncoder.encode(msg, "UTF-8");
	 * 
	 * CommonUsed.sendSMS(data.getMobile(), encodemsg);
	 * 
	 * CommonUsed.generateQRCode(qrcodename, pId, data.getMobile(), data.getfName(),
	 * env.getUserQrCode()); } }
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
	 * "Something went wrong. Registration failed."); } } }
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK);
	 * 
	 * logger.info("Method : userSelfRegistrationApi Dao ends"); return response; }
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> userSelfRegistrationApi(Map<String, Object> data) {
		logger.info("Method : userSelfRegistrationApi Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (StringUtil.isNull(data.get("mobile"))) {
			jsonResponse.setMessage("Mobile Number Required");
			validity = false;
		} else if (StringUtil.isNull(data.get("fName"))) {
			jsonResponse.setMessage("First Name Required");
			validity = false;
		} else if (StringUtil.isNull(data.get("lName"))) {
			jsonResponse.setMessage("Last Name Required");
			validity = false;
		}

		String userType = "";
		if (!data.get("userType").toString().isEmpty()) {
			userType = data.get("userType").toString();
		}
		String email = "";
		if (!data.get("email").toString().isEmpty()) {
			email = data.get("email").toString();
		}

		// Boolean isMobileExist = false;
		Boolean isMobileExist = checkDuplicateDao.isPatientMobileNumberExist(data.get("mobile"));

		if (isMobileExist) {

			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Mobile number is already exist.");

			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
					HttpStatus.OK);

			return response;
		}

		Boolean isEmailExist = checkDuplicateDao.isPatientEmailExist(data.get("email"));

		if (isEmailExist) {

			jsonResponse.setCode("failed");
			jsonResponse.setMessage("EmailId is already exist.");

			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
					HttpStatus.OK);

			return response;
		}

		// String password =
		// GenerateRandomValue.generateAlphanumericWithSpecialCharacter(8);
		String password = "User@123";
		logger.info("password===" + password);
		String encodedPassword = null;
		if (!StringUtil.isNull(password)) {
			encodedPassword = passEncoder.encode(password);
			logger.info("encodedPassword===" + encodedPassword);
		}

		if (!isMobileExist) {
			String random = GenerateRandomValue.generateRandom(6);
			if (!StringUtil.isNull(data.get("fName"))) {
				String noSpaceStr = data.get("fName").toString().replaceAll("\\s", "");
				String substr = noSpaceStr.substring(0, 3);
				substr = substr.toUpperCase();

				String randomCon = substr.concat(random);
				data.put("userId", random);
			}
			String pId = random;

			DropDownModel responseObject = new DropDownModel();

			List<Object[]> x = null;

			if (validity) {
				try {

					if (StringUtil.isNull(data.get("id"))) {
						logger.info("hyyyif" + x);
						x = em.createNamedStoredProcedureQuery("userSelfRegistrationAPI")
								.setParameter("userid", data.get("userId"))
								.setParameter("usertype", data.get("userType"))
								.setParameter("patfname", data.get("fName")).setParameter("patlname", data.get("lName"))
								.setParameter("patmobile", data.get("mobile"))
								.setParameter("patemail", data.get("email")).setParameter("password", encodedPassword)
								.getResultList();
						jsonResponse.setBody(x.get(0));
					}

					if (!StringUtil.isNull(jsonResponse.getBody())) {

						responseObject.setKey(jsonResponse.getBody().toString());
						responseObject.setName(password);

						Util.setJsonResponse(jsonResponse, responseObject, ResponseStatus.success,
								ApiResponseMessage.USER_SAVED_SUCCESSFULLY);

						String msg = "Welcome to CureEaZy. You are registered successfully! Your UserId is " + pId
								+ " or " + data.get("mobile") + " and password is " + password;

						String encodemsg = URLEncoder.encode(msg, "UTF-8");

						CommonUsed.sendSMS(data.get("mobile").toString(), encodemsg);
					}

				} catch (Exception e) {
					e.printStackTrace();
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
							ApiResponseMessage.REGISTRATION_FAILED);
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : userSelfRegistrationApi Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> updateUserProfileImageAPI(UserProfileAPIModel data) {
		logger.info("Method : updateUserProfileImageAPI Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		String image = "";
		if (data.getpImage().size() > 0) {

			if (data.getpImage().get(0) != null && data.getpImage().get(0) != "") {
				try {
					byte[] bytes = Base64.getDecoder().decode(data.getpImage().get(0));
					String imageName = saveAllImage(bytes, data.getProfileImageType(), data.geteCardNo());
					data.setProfileImageName(imageName);
					image = imageName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (validity) {
			try {

				if (data.geteCardNo() != null && data.geteCardNo() != "") {

					List<Object[]> x = em.createNamedStoredProcedureQuery("update_profile_image_api")
							.setParameter("regid", data.geteCardNo()).setParameter("profileimage", image)
							.getResultList();

					String profilePic = null;
					Object dataImg = x.get(0);
					if (!StringUtil.isNull(x.get(0)) && !dataImg.toString().equals(null)
							&& !dataImg.toString().equals("") && !dataImg.toString().equals(" ")
							&& !dataImg.toString().equals("null")) {
						profilePic = env.getBaseURL() + "cureeazyrest/document/profile/" + x.get(0);
					}

					Util.setJsonResponse(jsonResponse, profilePic, ResponseStatus.success,
							ApiResponseMessage.PROFILE_IMAGE_SAVED_SUCCESS);
				}

			} catch (Exception e) {
				e.printStackTrace();

				String filePath = env.getFileUploadEmployee() + image;

				File file = new File(filePath);

				if (file.delete()) {
					logger.info("File deleted.");
				} else {
					logger.info("File not deleted.");
				}

				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_FAILURE);
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : updateUserProfileImageAPI Dao ends");
		return response;
	}

	public ResponseEntity<JsonResponse<IsEContactFDoctorValidModel>> userDashboard(String userid) {
		logger.info("Method : getPatientDetailsRestApi Dao starts");

		IsEContactFDoctorValidModel data = new IsEContactFDoctorValidModel();
		JsonResponse<IsEContactFDoctorValidModel> jsonResponse = new JsonResponse<IsEContactFDoctorValidModel>();

		Boolean isEContactAdded = false;
		Boolean isFDoctorAdded = false;

		isEContactAdded = checkDuplicateDao.isEContactAdded(userid);

		isFDoctorAdded = checkDuplicateDao.isFDoctorAdded(userid);

		data.setIsEContactAdded(isEContactAdded);
		data.setIsFDoctorAdded(isFDoctorAdded);

		Util.setJsonResponse(jsonResponse, data, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);

		ResponseEntity<JsonResponse<IsEContactFDoctorValidModel>> response = new ResponseEntity<JsonResponse<IsEContactFDoctorValidModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : userDashboard Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> doctorAvailableByDate(String docid, String date) {
		logger.info("Method : doctorAvailableByDate Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> jsonArray1 = new ArrayList<Map<String, Object>>();

		if (date != null && date != "") {
			date = DateFormatter.getStringDateNew(date);
		}
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_time_slot_api").setParameter("doctorid", docid)
					.setParameter("date", date).getResultList();
			if (x.size() > 0) {
				for (Object[] m : x) {
					Map<String, Object> ob = new HashMap<String, Object>();
					String data = null;
					if (m[0] != null) {
						String d = m[0].toString();
						String arr[] = d.split(":");
						data = arr[0].concat(":").concat(arr[1]);
					}
					ob.put("name", data); // Time
					ob.put("key", m[1]); // Booking Status
					ob.put("code", m[2]); // OPD Id
					jsonArray.add(ob);
				}
				if (x.get(0)[2] != null) {
					obj.put("timelist", jsonArray);
				} else {
					obj.put("timelist", jsonArray1);
				}
				obj.put("status", "success");
				obj.put("message", ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				obj.put("status", "failed");
				obj.put("message", ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", "Data not found");
		}
		logger.info("Method : doctorAvailableByDate Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
//	@SuppressWarnings("unchecked")
//	public ResponseEntity<JsonResponse<Object>> doctorAvailableByDate(String userid, String date) {
//		logger.info("Method : doctorAvailableByDate Dao starts");
//		
//		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
//		List<CountryModel> ddList = new ArrayList<CountryModel>();
//		Boolean validity = true;
//		if (date == null || date == "") {
//			jsonResponse.setMessage("Appointment Date Required");
//			validity = false;
//		}
//		String prevDate = date;
//		
//		if (validity) {
//			try {
//				
//				if (date != null && date != "") {
//					date = DateFormatter.getStringDateNew(date);
//				}
//				List<Object[]> x = em.createNamedStoredProcedureQuery("doctorAvailableByDate")
//						.setParameter("doctor", userid).setParameter("date", date).getResultList();
//				
//				for (Object[] m : x) {
//					CountryModel dd = new CountryModel(m[0], m[1], m[2]);
//					ddList.add(dd);
//				}
//				
//				if (ddList.size() > 0) {
//					HashMap<String, String> map = new HashMap<>();
//					map.put("fromTime", DateFormatter.getStringTimeMS(ddList.get(0).getKey()));
//					map.put("toTime", DateFormatter.getStringTimeMS(ddList.get(0).getName()));
//					map.put("opdId", ddList.get(0).getCode());
//					jsonResponse.setBody(map);
//					jsonResponse.setCode("success");
//					jsonResponse.setMessage("Doctor is available on " + prevDate + ".");
//				} else {
//					jsonResponse.setCode("failed");
//					jsonResponse.setMessage("Doctor is not available on " + prevDate + ".");
//				}
//				
//			} catch (Exception e) {
//				jsonResponse.setCode("failed");
//				jsonResponse.setMessage("Doctor is not available on " + prevDate + ".");
//			}
//		}
//		
//		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
//				HttpStatus.OK);
//		
//		logger.info("Method : doctorAvailableByDate Dao ends");
//		System.out.println(response);
//		return response;
//	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> doctorAvailableByTime(String userid, String date, String time) {
		logger.info("Method : doctorAvailableByTime Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		List<CountryModel> ddList = new ArrayList<CountryModel>();
		Boolean validity = true;
		if (date == null || date == "") {
			jsonResponse.setMessage("Appointment Date Required");
			validity = false;
		} else if (time == null || time == "") {
			jsonResponse.setMessage("Appointment Time Required");
			validity = false;
		}
		String prevDate = date;

		if (validity) {
			try {

				if (date != null && date != "") {
					date = DateFormatter.getStringDateNew(date);
				}
				List<Object[]> x = em.createNamedStoredProcedureQuery("doctorAvailableByTime")
						.setParameter("doctor", userid).setParameter("date", date).setParameter("time", time)
						.getResultList();

				for (Object[] m : x) {
					CountryModel dd = new CountryModel(m[0], m[1], m[2]);
					ddList.add(dd);
				}

				if (ddList.size() > 0) {
					HashMap<String, String> map = new HashMap<>();
					map.put("fromTime", DateFormatter.getStringTimeMS(ddList.get(0).getKey()));
					map.put("toTime", DateFormatter.getStringTimeMS(ddList.get(0).getName()));
					map.put("opdId", ddList.get(0).getCode());
					jsonResponse.setBody(map);
					jsonResponse.setCode("success");
					jsonResponse.setMessage("Doctor is available on " + prevDate + " " + time + ".");
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Doctor is not available on " + prevDate + " " + time + ".");
				}

			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Doctor is not available on " + prevDate + " " + time + ".");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : doctorAvailableByTime Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postDoctorAppointment(DoctorAppointmentModel data) {
		logger.info("Method : postDoctorAppointment Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getDate() == null || data.getDate() == "") {
			jsonResponse.setMessage("Appointment Date Required");
			validity = false;
		} else if (data.getTime() == null || data.getTime() == "") {
			jsonResponse.setMessage("Appointment Time Required");
			validity = false;
		} else if (data.getOpdid() == null || data.getOpdid() == "") {
			jsonResponse.setMessage("OPD ID Required");
			validity = false;
		} else if (data.getDoctor() == null || data.getDoctor() == "") {
			jsonResponse.setMessage("Doctor Required");
			validity = false;
		} else if (data.getHospitalid() == null || data.getHospitalid() == "") {
			jsonResponse.setMessage("Hospital Required");
			validity = false;
		}
		String casepaper = data.getUserid();
//		String casepaper = checkDuplicateDao.getCasePaperNumber(data.getUserid());
		Boolean isMobileExist = false;

		if (!isMobileExist) {

			String notes = null;
			if (data.getNotes() != null) {
				notes = data.getNotes();
			}

			String hospital = null;
			if (data.getHospitalid() != null) {
				hospital = data.getHospitalid().toString();
			}

			String doctor = null;
			if (data.getDoctor() != null) {
				doctor = data.getDoctor().toString();
			}

			String casep = null;
			if (casepaper != null) {
				casep = casepaper.toString();
			}

			String opdid = null;
			Integer opddata = 0;
			if (data.getOpdid() != null) {
				opdid = data.getOpdid().toString();
				opddata = Integer.parseInt(opdid);
			}

			String date = null;
			String newdate = null;
			if (data.getDate() != null) {
				date = DateFormatter.getStringDateNew(data.getDate());
				newdate = DateFormatter.returnStringDateNew(date);
			}

			if (validity) {
				try {

					List<Object[]> x = null;

					if (data.getUserid() != null || data.getUserid() != "") {

						x = em.createNamedStoredProcedureQuery("post_doctor_appointment")
								.setParameter("p_datefrom", date).setParameter("p_caspaper", casep)
								.setParameter("p_opdid", opddata).setParameter("p_timefrom", data.getTime())
								.setParameter("p_doctor", doctor).setParameter("p_notes", notes)
								.setParameter("p_hospitalname", hospital).setParameter("p_apptype", data.getApptype())
								.getResultList();
						jsonResponse.setBody(x.get(0)[0]);

					}

					jsonResponse.setCode("success");
					jsonResponse.setMessage("Appointment Booked Successfully");

					String doctorname = x.get(0)[1].toString();

					String msg = "Dear User,\r\n" + "\r\n"
							+ "Your Request for Appointment sent successfully! Your Appointment Date and time is "
							+ newdate + " " + data.getTime() + " with Dr. " + doctorname + "\r\n" + "\r\n"
							+ "Regards,\r\n" + "eHealthSystem Team";

					String encodemsg = URLEncoder.encode(msg, "UTF-8");

					CommonUsed.sendSMS(x.get(0)[0].toString(), encodemsg);
				} catch (Exception e) {
					e.printStackTrace();
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postDoctorAppointment Dao ends");
		return response;
	}

//	@SuppressWarnings("unchecked")
//	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> getUserAppointmentList(String userid) {
//		logger.info("Method : getUserAppointmentList Dao starts");
//
//		List<AppointmentListModel> countryList = new ArrayList<AppointmentListModel>();
//		JsonResponse<List<AppointmentListModel>> jsonResponse = new JsonResponse<List<AppointmentListModel>>();
//		try {
//
//			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_appointment_list")
//					.setParameter("userid", userid).getResultList();
//			for (Object[] m : x) {
//
//				String appstatus = null;
//				if (m[5] != null) {
//					if (m[5].toString().contentEquals("7")) {
//						appstatus = "Requested";
//					}
//					if (m[5].toString().contentEquals("2")) {
//						appstatus = "Confirmed";
//					}
//					if (m[5].toString().contentEquals("4")) {
//						appstatus = "Cancelled";
//					}
//					if (m[5].toString().contentEquals("5")) {
//						appstatus = "Treated";
//					}
//				}
//				Object appdate = null;
//				if (m[2] != null) {
//					appdate = DateFormatter.returnStringDateNew(m[2].toString());
//				}
//
//				AppointmentListModel dropDownModel = new AppointmentListModel(userid, m[0], m[1], appdate, m[3], m[4],
//						appstatus, m[6], null);
//				countryList.add(dropDownModel);
//			}
//			jsonResponse.setBody(countryList);
//
//			if (countryList.size() > 0) {
//				jsonResponse.setCode("success");
//				jsonResponse.setMessage("Data Fetched Successfully");
//			} else {
//				jsonResponse.setCode("failed");
//				jsonResponse.setMessage("No Data Found");
//			}
//
//		} catch (Exception e) {
//			jsonResponse.setCode("failed");
//			jsonResponse.setMessage(e.getMessage());
//		}
//		ResponseEntity<JsonResponse<List<AppointmentListModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentListModel>>>(
//				jsonResponse, HttpStatus.OK);
//
//		logger.info("Method : getUserAppointmentList Dao ends");
//		return response;
//	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> getUserAppointmentList(String userid, String status,
			String date) {
		logger.info("Method : getUserAppointmentList Dao starts");

		List<AppointmentListModel> countryList = new ArrayList<AppointmentListModel>();
		JsonResponse<List<AppointmentListModel>> jsonResponse = new JsonResponse<List<AppointmentListModel>>();
		try {

			String cdate = null;

			if (date != null && date != "") {
				cdate = DateFormatter.getStringDateNew(date);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_appointment_list")
					.setParameter("userid", userid).setParameter("status", status).setParameter("date", cdate)
					.getResultList();
			for (Object[] m : x) {

				String appstatus = null;
				if (m[5] != null) {
					if (m[5].toString().contentEquals("7")) {
						appstatus = "Requested";
					}
					if (m[5].toString().contentEquals("2")) {
						appstatus = "Confirmed";
					}
					if (m[5].toString().contentEquals("4")) {
						appstatus = "Cancelled";
					}
					if (m[5].toString().contentEquals("5")) {
						appstatus = "Treated";
					}
				}
				Object appdate = null;
				if (m[2] != null) {
					appdate = DateFormatter.returnStringDateNew(m[2].toString());
				}
				String exp = null;
				if (m[10] != null) {
					exp = m[10].toString().concat(" Years");
				}

				AppointmentListModel dropDownModel = new AppointmentListModel(userid, m[0], m[1], appdate, m[3], m[4],
						appstatus, m[6], m[7], // null, null, null, null, null);
						m[8], m[9], exp, m[11], m[12], m[13], m[14], m[15]);
				countryList.add(dropDownModel);
			}

			if (countryList.size() > 0) {
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AppointmentListModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentListModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getUserAppointmentList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> getUserScreenTestAppointmentList(String userid,
			String appointDate) {
		logger.info("Method : getUserScreenTestAppointmentList Dao starts");

		List<AppointmentModel> countryList = new ArrayList<AppointmentModel>();
		JsonResponse<List<AppointmentModel>> jsonResponse = new JsonResponse<List<AppointmentModel>>();
		try {

			if (appointDate != null && appointDate != "") {
				appointDate = DateFormatter.getStringDateNew(appointDate);
			}
			int slno = 0;
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_appointment_screentest_list")
					.setParameter("userid", userid).setParameter("appointdate", appointDate).getResultList();
			for (Object[] m : x) {
				slno = slno + 1;
				String appStatusName = null;
				if (m[3] != null) {
					if (m[3].toString().contentEquals("0")) {
						appStatusName = "Booked";
					} else if (m[3].toString().contentEquals("1")) {
						appStatusName = "In Progress";
					} else {
						appStatusName = "Completed";
					}
				}

				Object appdate = null;

				if (m[2] != null) {
					appdate = DateFormatter.returnStringDateNew(m[2].toString());
				}

				AppointmentModel dropDownModel = new AppointmentModel(m[0], m[1], null, null, null, null, appdate, null,
						null, appStatusName, slno);
				countryList.add(dropDownModel);
			}

			if (countryList.size() > 0) {
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AppointmentModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getUserScreenTestAppointmentList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> getUserCheckUpAppointmentList(String userid,
			String appointDate) {
		logger.info("Method : getUserCheckUpAppointmentList Dao starts");

		List<AppointmentModel> countryList = new ArrayList<AppointmentModel>();
		JsonResponse<List<AppointmentModel>> jsonResponse = new JsonResponse<List<AppointmentModel>>();
		try {

			if (appointDate != null && appointDate != "") {
				appointDate = DateFormatter.getStringDateNew(appointDate);
			}
			int slno = 0;
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_appointment_checkup_list")
					.setParameter("userid", userid).setParameter("appointdate", appointDate).getResultList();
			for (Object[] m : x) {
				slno = slno + 1;
				String appStatusName = null;
				if (m[3] != null) {
					if (m[3].toString().contentEquals("0")) {
						appStatusName = "Booked";
					} else if (m[3].toString().contentEquals("1")) {
						appStatusName = "In Progress";
					} else {
						appStatusName = "Completed";
					}
				}

				Object appdate = null;

				if (m[2] != null) {
					appdate = DateFormatter.returnStringDateNew(m[2].toString());
				}

				AppointmentModel dropDownModel = new AppointmentModel(m[0], m[1], null, null, null, null, appdate, null,
						null, appStatusName, slno);
				countryList.add(dropDownModel);
			}

			if (countryList.size() > 0) {
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {

			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AppointmentModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getUserCheckUpAppointmentList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIVitalSignModel>>> getUserVitalSignDetails(String userid) {
		logger.info("Method : getUserVitalSignDetails Dao starts");

		List<APIVitalSignModel> countryList = new ArrayList<APIVitalSignModel>();
		JsonResponse<List<APIVitalSignModel>> jsonResponse = new JsonResponse<List<APIVitalSignModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_vitalsign_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				Double height = 0.00;
				if (m[0] != null && m[0] != "" && !m[0].toString().equals(null) && !m[0].toString().equals("")) {
					height = Double.parseDouble(m[0].toString());
				}

				Double weight = 0.00;
				if (m[1] != null && m[1] != "" && !m[1].toString().equals(null) && !m[1].toString().equals("")) {
					weight = Double.parseDouble(m[1].toString());
				}

				Double bmi = 0.00;
				if (m[2] != null && m[2] != "" && !m[2].toString().equals(null) && !m[2].toString().equals("")) {
					bmi = Double.parseDouble(m[2].toString());
				}

				Double tempcs = 0.00;
				if (m[3] != null && m[3] != "" && !m[3].toString().equals(null) && !m[3].toString().equals("")) {
					tempcs = Double.parseDouble(m[3].toString());
				}

				Double fh = 0.00;
				if (m[3] != null) {
					Double cs = (Double) m[3];
					fh = (cs * 1.8) + 32;
				}

				String val = String.format("%.2f", fh);
				Double newfh = Double.parseDouble(val);

				Integer sysbp = 0;
				if (m[4] != null && m[4] != "" && !m[4].toString().equals(null) && !m[4].toString().equals("")) {
					sysbp = Integer.parseInt(m[4].toString());
				}

				Integer diabp = 0;
				if (m[5] != null && m[5] != "" && !m[5].toString().equals(null) && !m[5].toString().equals("")) {
					diabp = Integer.parseInt(m[5].toString());
				}

				Integer pulse = 0;
				if (m[6] != null && m[6] != "" && !m[6].toString().equals(null) && !m[6].toString().equals("")) {
					pulse = Integer.parseInt(m[6].toString());
				}

				Integer resp = 0;
				if (m[7] != null && m[7] != "" && !m[7].toString().equals(null) && !m[7].toString().equals("")) {
					resp = Integer.parseInt(m[7].toString());
				}

				Integer oxy = 0;
				if (m[8] != null && m[8] != "" && !m[8].toString().equals(null) && !m[8].toString().equals("")) {
					oxy = Integer.parseInt(m[8].toString());
				}

				APIVitalSignModel dropDownModel = new APIVitalSignModel(height, weight, bmi, tempcs, newfh, sysbp,
						diabp, pulse, resp, oxy);
				countryList.add(dropDownModel);
			}

			if (countryList.size() > 0) {
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());

		}
		ResponseEntity<JsonResponse<List<APIVitalSignModel>>> response = new ResponseEntity<JsonResponse<List<APIVitalSignModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getUserVitalSignDetails Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> getEmergencyDetails(String userid) {
		logger.info("Method : getEmergencyDetails Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> jsonArray1 = new ArrayList<Map<String, Object>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_emergency_contactdetails_api")
					.setParameter("userid", userid).getResultList();

			if (x.size() > 0) {
				Integer i = 0;
				for (Object[] m : x) {
					Map<String, Object> ob = new HashMap<String, Object>();
					ob.put("name", m[0]);
					ob.put("relation", m[1]);
					ob.put("mobile", m[2]);
					ob.put("id", m[5]);
					ob.put("rel_id", m[6]);
					if ((m[0] == null || m[0] == "") && (m[1] == null || m[1] == "") && (m[2] == null || m[2] == "")
							&& (m[5] == null || m[5] == "") && (m[6] == null || m[6] == "")) {
						i++;
					} else {
						jsonArray.add(ob);
					}
				}
				if (i > 0) {
					obj.put("emergency", jsonArray1);
				} else {
					obj.put("emergency", jsonArray);
				}

				obj.put("ambulance", x.get(0)[3]);
				obj.put("police", x.get(0)[4]);
				if (x.get(0)[7] != null) {
					obj.put("emer_msg", x.get(0)[7]);
				} else {
					obj.put("emer_msg", "Emergency alert ! I need help");
				}
				obj.put("status", "success");
				obj.put("message", ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				obj.put("status", "failed");
				obj.put("message", ApiResponseMessage.DATA_FECTH_FAILED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", ApiResponseMessage.NO_DATA_FOUND);
		}
		logger.info("Method : getEmergencyDetails Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	/*
	 * @SuppressWarnings("unchecked") public ResponseEntity<Object>
	 * smsToEmergencyContactUsers(String userid, String mapurl, String longi, String
	 * lati) { logger.info("Method : smsToEmergencyContactUsers Dao starts");
	 * 
	 * Map<String, Object> myMap = new HashMap<String, Object>(); try {
	 * 
	 * List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("get_user_emergency_contactnumber_api")
	 * .setParameter("userid", userid).getResultList();
	 * 
	 * if (x.size() > 0) { String msg = null; if (x.get(0)[3] != null) { msg =
	 * x.get(0)[3].toString(); } else { msg = "Emeregency Alert ! I need help."; }
	 * List<String> mobNumList = new ArrayList<String>(); String numlist = null; for
	 * (Object[] m : x) { if (m[2] != null) { String mobileNumber = m[2].toString();
	 * mobNumList.add(mobileNumber); numlist = numlist + mobileNumber + ","; } }
	 * 
	 * if (numlist != null) { numlist = numlist.substring(0, numlist.length() - 1);
	 * }
	 * 
	 * System.out.println(mobNumList + " * " + msg + " * " + longi + " * " + lati);
	 * 
	 * String msg1 = "eHealthSystem Emergency Alert! I need help. My location is " +
	 * longi + " & " + lati + "."; // String msg1 =
	 * "Welcome to eHealthSystem. Your OTP is 1234"; String encodemsg =
	 * URLEncoder.encode(msg1, "UTF-8");
	 * 
	 * CommonUsed.sendSMS(numlist, encodemsg);
	 * 
	 * myMap.put("status", "success"); myMap.put("message",
	 * "Message sent successfully"); } else { myMap.put("status", "failed");
	 * myMap.put("message", "Something went wrong"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); myMap.put("status", "failed");
	 * myMap.put("message", "Something went wrong"); }
	 * 
	 * logger.info("Method : smsToEmergencyContactUsers Dao ends"); //
	 * HashMap<String, Object> result = new ObjectMapper().readValue(jsonString, new
	 * TypeReference<Map<String, Object>>(){})); return new ResponseEntity<>(myMap,
	 * HttpStatus.OK); }
	 */
	public String parseText(String postText) {

		Pattern p = Pattern.compile("\\[(.*)\\]\\((.*)\\)");
		Matcher m = p.matcher(postText);

		StringBuffer sb = new StringBuffer(postText.length());

		while (m.find()) {
			String found_text = m.group(1);
			String found_link = m.group(2);
			String replaceWith = "<a href=" + "\"" + found_link + "\"" + ">" + found_text + "</a>";
			m.appendReplacement(sb, replaceWith);
		}

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> smsToEmergencyContactUsers(String userid, String mapurl) {
		logger.info("Method : smsToEmergencyContactUsers Dao starts");

		logger.info("USER ID == " + userid);
		logger.info("MAP URL == " + mapurl);

		String fname = null;
		Map<String, Object> myMap = new HashMap<String, Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_emergency_contactnumber_api")
					.setParameter("userid", userid).getResultList();

			if (x.size() > 0) {
				List<String> mobNumList = new ArrayList<String>();
				String numlist = null;
				for (Object[] m : x) {
					if (m[2] != null) {
						String mobileNumber = m[2].toString();
						mobNumList.add(mobileNumber);
						numlist = numlist + mobileNumber + ",";
					}

					if (!StringUtil.isNull(m[4])) {
						fname = m[4].toString();
					}
				}

				if (numlist != null) {
					numlist = numlist.substring(0, numlist.length() - 1);
				}

				if (mobNumList.size() > 0) {

					String msg1 = SMSGatewayMessage.EMERGENCY_ALERT;

					msg1 = msg1.replace("{name}", fname);
					msg1 = msg1.replace("{msg}", mapurl);

					String encodemsg = URLEncoder.encode(msg1, "UTF-8");

					CommonUsed.sendSMS(numlist, encodemsg);

					myMap.put("status", ResponseStatus.success);
					myMap.put("message", ApiResponseMessage.MESSAGE_SENT);
				} else {
					myMap.put("status", ResponseStatus.failed);
					myMap.put("message", ApiResponseMessage.NO_EMEREGENCY_CONTACT_FOUND);
				}

			} else {
				myMap.put("status", ResponseStatus.failed);
				myMap.put("message", ApiResponseMessage.NO_EMEREGENCY_CONTACT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			myMap.put("status", ResponseStatus.failed);
			myMap.put("message", ApiResponseMessage.UNKNOWN_EXCEPTION);
		}

		logger.info("Method : smsToEmergencyContactUsers Dao ends");
		return new ResponseEntity<>(myMap, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postEmergencyContactApi(EmergencyContactAPIModel data) {
		logger.info("Method : postEmergencyContactApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean validity = true;
		if (data.getMobile() == null || data.getMobile() == "") {
			obj.put("status", "failed");
			obj.put("message", "Mobile number required");
			validity = false;
		}

		if (validity) {
			try {
				List<Object[]> x = null;
				if (data.getId() != null && data.getId() != "") {
					x = em.createNamedStoredProcedureQuery("update_emergency_contact_api_data")
							.setParameter("userid", data.getUserid()).setParameter("name", data.getName())
							.setParameter("relation", data.getRelation()).setParameter("mobile", data.getMobile())
							.setParameter("eid", data.getId()).getResultList();
					obj.put("userid", x.get(0));
					obj.put("status", "success");
					obj.put("message", "Data updated successfully");
				} else {
					x = em.createNamedStoredProcedureQuery("post_emergency_contact_api_data")
							.setParameter("userid", data.getUserid()).setParameter("name", data.getName())
							.setParameter("relation", data.getRelation()).setParameter("mobile", data.getMobile())
							.getResultList();
					obj.put("userid", x.get(0));
					obj.put("status", "success");
					obj.put("message", ApiResponseMessage.SAVED_SUCCESSFULLY);

					/*
					 * String msg = SMSGatewayMessage.OTP; msg = msg.replace("{otp}", "1234");
					 * String encodemsg = URLEncoder.encode(msg, "UTF-8");
					 * 
					 * CommonUsed.sendSMS(data.getMobile(), encodemsg);
					 */

				}
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "failed");
				obj.put("message", ApiResponseMessage.UNKNOWN_FAILURE);
			}
		}

		logger.info("Method : postEmergencyContactApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postFamilyDoctorApi(APIEmergencyModel data) {
		logger.info("Method : postFamilyDoctorApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean validity = true;
		if (data.getMobile() == null || data.getMobile() == "") {
			obj.put("status", "failed");
			obj.put("message", "Mobile Number Required");
			validity = false;
		}

		if (validity) {
			try {
				List<Object[]> x = null;
				if (data.getId() != null && data.getId() != "") {
					x = em.createNamedStoredProcedureQuery("post_family_doctor_api")
							.setParameter("userid", data.getUserid()).setParameter("name", data.getName())
							.setParameter("speciality", data.getType()).setParameter("mobile", data.getMobile())
							.setParameter("eid", data.getId()).setParameter("apifor", "EDIT").getResultList();

					obj.put("userid", x.get(0));
					obj.put("status", "success");
					obj.put("message", "Data updated successfully");

				} else {
					x = em.createNamedStoredProcedureQuery("post_family_doctor_api")
							.setParameter("userid", data.getUserid()).setParameter("name", data.getName())
							.setParameter("speciality", data.getType()).setParameter("mobile", data.getMobile())
							.setParameter("eid", data.getId()).setParameter("apifor", "ADD").getResultList();

					/*
					 * String msg = SMSGatewayMessage.OTP; msg = msg.replace("{otp}", "1234");
					 * String encodemsg = URLEncoder.encode(msg, "UTF-8");
					 * 
					 * CommonUsed.sendSMS(data.getMobile(), encodemsg);
					 */
					obj.put("userid", x.get(0));
					obj.put("status", "success");
					obj.put("message", "Data saved successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}
		}

		logger.info("Method : postFamilyDoctorApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postFamilyDetailsApi(PatientFamilyDetailsModel data) {
		logger.info("Method : postFamilyDetailsApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean validity = true;
//		if (data.getMobile() == null || data.getMobile() == "") {
//			obj.put("status", "failed");
//			obj.put("message", "Mobile Number Required");
//			validity = false;
//		}

		if (validity) {
			try {
				List<Object[]> x = null;
				if (data.getFamid() != null && data.getFamid() != "") {
					x = em.createNamedStoredProcedureQuery("post_family_details_api")
							.setParameter("userid", data.getUserid()).setParameter("memid", data.getMemberid())
							.setParameter("relation", data.getRelation()).setParameter("eid", data.getFamid())
							.setParameter("apifor", "EDIT").getResultList();
					obj.put("userid", x.get(0));
					obj.put("status", "success");
					obj.put("message", "Data updated successfully");
				} else {
					x = em.createNamedStoredProcedureQuery("post_family_details_api")
							.setParameter("userid", data.getUserid()).setParameter("memid", data.getMemberid())
							.setParameter("relation", data.getRelation()).setParameter("eid", data.getFamid())
							.setParameter("apifor", "ADD").getResultList();
					obj.put("userid", x.get(0));
					obj.put("status", "success");
					obj.put("message", "Data saved successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}
		}

		logger.info("Method : postFamilyDetailsApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<DropDownModel>> getEhealthUser(String data) {
		logger.info("Method : getEhealthUser Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<DropDownModel> jsonResponse = new JsonResponse<DropDownModel>();
		try {

			Boolean valid = checkDuplicateDao.isValidUHIDNumber(data);

			if (valid) {
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_ehealth_user_api")
						.setParameter("searchval", data).getResultList();
				for (Object[] m : x) {

					Object age = null;
					if (m[2] != null) {
						Double ageval = Double.parseDouble(m[2].toString());
						Integer a = ageval.intValue();
						age = a.toString();
					}

					DropDownModel dropDownModel = new DropDownModel(m[0], m[1], age);
					countryList.add(dropDownModel);
				}
				if (countryList.size() > 0) {
					jsonResponse.setBody(countryList.get(0));
					jsonResponse.setCode("success");
					jsonResponse.setMessage("Data Fetched Successfully");
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Data Not Found");
				}
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("UHID is not exist");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<DropDownModel>> response = new ResponseEntity<JsonResponse<DropDownModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getEhealthUser Dao ends");
		return response;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<Object> updateVitalSigns(List<CountryModel> data) {
		logger.info("Method : updateVitalSigns Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean validity = true;
		if (data.get(0).getName() == null || data.get(0).getName() == "") {
			obj.put("status", "failed");
			obj.put("message", "User ID Required");
			validity = false;
		}

		if (validity) {

//			String casepaper = checkDuplicateDao.getCasePaperNumber(data.get(0).getName());

			String value = "";
			String height = null;
			String weight = null;
			if (data.size() > 0) {
				for (CountryModel m : data) {
					DecimalFormat df = new DecimalFormat("0.00");
					if (m.getKey().equals("4")) {
						height = m.getCode();
					}
					if (m.getKey().equals("5")) {
						weight = m.getCode();
					}
					String bmi = null;
					if (m.getKey().equals("6")) {
						Double bmidata = 0.0;
						bmidata = Double.parseDouble(weight)
								/ ((Double.parseDouble(height) / 100) * (Double.parseDouble(height) / 100));
						bmi = df.format(bmidata);
						m.setCode(bmi);
					}
					if (m.getCode() != null && m.getCode() != "" && !m.getCode().equals(" ") && !m.getCode().equals("")
							&& !m.getCode().equals(null)) {
						value = value + "(" + m.getKey() + ",CURRENT_TIMESTAMP," + m.getCode() + ",'"
								+ data.get(0).getName() + "','" + data.get(0).getName()
								+ "',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),";
					}
				}
				value = value.substring(0, value.length() - 1);
			}

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("update_vital_sign_details")
						.setParameter("subquery", value).setParameter("casepaper", data.get(0).getName())
						.setParameter("height", height).setParameter("weight", weight).getResultList();

//				obj.put("userid", x.get(0));
				obj.put("status", "success");
				obj.put("message", "Vital sign details updated successfully");
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}
		}

		logger.info("Method : updateVitalSigns Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postUserEmergencyMessage(EmergencyContactAPIModel data) {
		logger.info("Method : postUserEmergencyMessage Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean validity = true;
		if (data.getUserid() == null || data.getUserid() == "") {
			obj.put("status", "failed");
			obj.put("message", "User ID Required");
			validity = false;
		} else if (data.getMsg() == null || data.getMsg() == "") {
			obj.put("status", "failed");
			obj.put("message", "Message Content Required");
			validity = false;
		}

		if (validity) {
			try {
				List<Object[]> x = null;
				x = em.createNamedStoredProcedureQuery("post_user_emergency_message")
						.setParameter("userid", data.getUserid()).setParameter("message", data.getMsg())
						.getResultList();
				obj.put("userid", x.get(0));
				obj.put("status", "success");
				obj.put("message", "Data saved successfully");
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}
		}

		logger.info("Method : postUserEmergencyMessage Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> getUserMedicineDetailsByAppNo(String appno) {
		logger.info("Method : getUserMedicineDetailsByAppNo Dao starts");

		List<APIMedicationModel> countryList = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_user_meddtls_byappno")
					.setParameter("appno", appno).getResultList();
			for (Object[] m : x) {

				Boolean status = false;

				if (m[13] != null) {
					if (m[13].toString().contentEquals("1")) {
						status = true;
					} else {
						status = false;
					}
				}

				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12], status, m[13], null, m[14]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getUserMedicineDetailsByAppNo Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<SearchDetailsModel>>> findHealthProviderDetails(String longi, String lati,
			String addr, String city, String healthpro, String type) {
		logger.info("Method : findHealthProviderDetails Dao starts");

		List<SearchDetailsModel> countryList = new ArrayList<SearchDetailsModel>();
		JsonResponse<List<SearchDetailsModel>> jsonResponse = new JsonResponse<List<SearchDetailsModel>>();

		Boolean validity = true;
		if (longi == null || longi == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Longitude Required");
			validity = false;
		} else if (lati == null || lati == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Latitude Required");
			validity = false;
		} else if (addr == null || addr == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Current Address Required");
			validity = false;
		} else if (city == null || city == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Current City Required");
			validity = false;
		} else if (healthpro == null || healthpro == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Health Provider Required");
			validity = false;
		}

		if (validity) {
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("find_health_provider_details")
						.setParameter("longi", longi).setParameter("lati", lati).setParameter("addr", addr)
						.setParameter("city", city).setParameter("healthpro", healthpro).setParameter("type", type)
						.getResultList();
				for (Object[] m : x) {

					Object path = null;

					if (m[4] != null) {
						path = env.getBaseURL() + "cureeazyrest/document/profile/" + m[4].toString();
					}

					SearchDetailsModel dropDownModel = new SearchDetailsModel(m[0], m[1], m[2], m[3], path, m[5]);
					countryList.add(dropDownModel);
				}
				jsonResponse.setBody(countryList);

				if (countryList.size() > 0) {
					jsonResponse.setCode("success");
					jsonResponse.setMessage("Data Fetched Successfully");
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Data not found");
				}

			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}

		ResponseEntity<JsonResponse<List<SearchDetailsModel>>> response = new ResponseEntity<JsonResponse<List<SearchDetailsModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : findHealthProviderDetails Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> deleteMedicineByAppNo(String appno, String srlone, String srltwo) {
		logger.info("Method : deleteMedicineByAppNo Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("delete_medicine_byappno")
					.setParameter("appno", appno).setParameter("srlone", srlone).setParameter("srltwo", srltwo)
					.getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Medicine deleted successfully.");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Something went wrong.");
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", "Something went wrong.");
		}

		logger.info("Method : deleteMedicineByAppNo Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPharmacyListByLocation(SearchDetailsModel data) {
		logger.info("Method : getPharmacyListByLocation Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("pharmacy_list_by_location")
					.setParameter("location", data.getAddress()).setParameter("city", data.getCity()).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getPharmacyListByLocation Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getLabListByLocation(SearchDetailsModel data) {
		logger.info("Method : getLabListByLocation Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("lab_list_by_location")
					.setParameter("location", data.getAddress()).setParameter("city", data.getCity()).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
					ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getLabListByLocation Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postPharmacyRequestApi(APIPharmacyModel data) {
		logger.info("Method : postPharmacyRequestApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			List<Object[]> x = null;
			String value = "";
			long nowTime = new Date().getTime();
			String orderid = "ORDR" + generateRandom(5) + nowTime;
			String casepaper = checkDuplicateDao.getCasePaperNumber(data.getUserid());
			if (data.getMeddetails().size() > 0) {
				for (CountryModel m : data.getMeddetails()) {
					value = value + "(\'" + orderid + "\',\'" + casepaper + "\',\'" + data.getPharmacistid() + "\',"
							+ m.getName() + "," + m.getCode() + ",\'" + m.getKey() + "\',\'" + data.getPatientnote()
							+ "\',0,1,CURRENT_TIMESTAMP),";
				}

				value = value.substring(0, value.length() - 1);
			}

			x = em.createNamedStoredProcedureQuery("post_pharmacy_request_byuser").setParameter("testsubquery", value)
					.setParameter("userid", data.getUserid()).getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Medicine requested successfully");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Something went wrong.");
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", "Something went wrong.");
		}

		logger.info("Method : postPharmacyRequestApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postLabRequestApi(APIPharmacyModel data) {
		logger.info("Method : postLabRequestApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			List<Object[]> x = null;
			String value = "";
			long nowTime = new Date().getTime();
			String orderid = "ORDR" + generateRandom(5) + nowTime;
			if (data.getMeddetails().size() > 0) {
				for (CountryModel m : data.getMeddetails()) {
					value = value + "(\'" + orderid + "\',\'" + data.getUserid() + "\',\'" + data.getPharmacistid()
							+ "\',\'" + m.getKey() + "\',\'" + m.getName() + "\',\'" + data.getPatientnote()
							+ "\',0,1,CURRENT_TIMESTAMP," + m.getCode() + "," + m.getData() + "),";
				}

				value = value.substring(0, value.length() - 1);
			}

			x = em.createNamedStoredProcedureQuery("post_lab_request_byuser").setParameter("testsubquery", value)
					.setParameter("userid", data.getUserid()).getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Test requested successfully");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", "Something went wrong");
		}

		logger.info("Method : postLabRequestApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIAllergyModel>>> viewUserAllergyListApi(String userid) {
		logger.info("Method : viewUserAllergyListApi Dao starts");

		List<APIAllergyModel> infrmtnList = new ArrayList<APIAllergyModel>();
		JsonResponse<List<APIAllergyModel>> jsonResponse = new JsonResponse<List<APIAllergyModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_allergies_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				APIAllergyModel dropDownModel = new APIAllergyModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7]);
				infrmtnList.add(dropDownModel);
			}
			if (infrmtnList.size() > 0) {
				Util.setJsonResponse(jsonResponse, infrmtnList, ResponseStatus.success, "Data Fetched Successfully");
			} else {
				Util.setJsonResponse(jsonResponse, infrmtnList, ResponseStatus.failed, "No Data Found");
			}
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, infrmtnList, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIAllergyModel>>> response = new ResponseEntity<JsonResponse<List<APIAllergyModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewUserAllergyListApi Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIBioMedicalModel>>> viewUserBioMedicalImplantListApi(String userid) {
		logger.info("Method : viewUserBioMedicalImplantListApi Dao starts");

		List<APIBioMedicalModel> infrmtnList = new ArrayList<APIBioMedicalModel>();
		JsonResponse<List<APIBioMedicalModel>> jsonResponse = new JsonResponse<List<APIBioMedicalModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_biomedical_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				Object date = null;

				if (m[2] != null) {
					date = DateFormatter.returnStringDateNew(m[2].toString());
				}

				APIBioMedicalModel dropDownModel = new APIBioMedicalModel(m[0], m[1], date, m[3], m[4]);
				infrmtnList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, infrmtnList, ResponseStatus.success,
					ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIBioMedicalModel>>> response = new ResponseEntity<JsonResponse<List<APIBioMedicalModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : viewUserBioMedicalImplantListApi Dao ends");
		return response;
	}

	/*
	 * Post and Update function for allergy
	 * 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<Object> postAllergiesApiDao(APIAllergyModel data) {

		logger.info("Method : postAllergiesApiDao Dao starts");
		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean isexist = false;
		isexist = checkDuplicateDao.isAllergyExist(data.getUserid(), data.getAllnameid(), data.getAlltypeid());

		try {
			List<Object[]> x = null;
			if (StringUtil.isNull(data.getId())) {
				if (isexist) {
					x = em.createNamedStoredProcedureQuery("save_allergies_api_data")
							.setParameter("allid", data.getId()).setParameter("userid", data.getUserid())
							.setParameter("allnameid", data.getAllnameid())
							.setParameter("alltypeid", data.getAlltypeid()).setParameter("severity", data.getSeverity())
							.setParameter("reaction", data.getReaction()).setParameter("updatedby", data.getUpdatedby())
							.getResultList();

					obj.put("status", "success");
					obj.put("message", ApiResponseMessage.SAVED_SUCCESSFULLY);
				} else {
					obj.put("status", ApiResponseMessage.UNKNOWN_FAILURE);
					obj.put("message", "Same allergy name or allergen already exist");
				}
			} else {
				x = em.createNamedStoredProcedureQuery("save_allergies_api_data").setParameter("allid", data.getId())
						.setParameter("userid", data.getUserid()).setParameter("allnameid", data.getAllnameid())
						.setParameter("alltypeid", data.getAlltypeid()).setParameter("severity", data.getSeverity())
						.setParameter("reaction", data.getReaction()).setParameter("updatedby", data.getUpdatedby())
						.getResultList();

				obj.put("status", "success");
				obj.put("message", ApiResponseMessage.UPDATED_SUCCESSFULLY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", "Something went wrong");
		}

		logger.info("Method : postAllergiesApiDao Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<Object> postBioMedicalImplantsApi(APIBioMedicalModel data) {
		logger.info("Method : postBioMedicalImplantsApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			List<Object[]> x = null;
			if (StringUtil.isNull(data.getId())) {
				x = em.createNamedStoredProcedureQuery("save_biomed_implants_api_data")
						.setParameter("bioid", data.getId()).setParameter("userid", data.getUserid())
						.setParameter("bioname", data.getBioMName())
						.setParameter("biodate", DateFormatter.getStringDate(data.getBioMDate()))
						.setParameter("bioreason", data.getBioMReason()).getResultList();

				obj.put("status", "success");
				obj.put("message", "Data saved successfully");
			} else {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("save_biomed_implants_api_data")
						.setParameter("bioid", data.getId()).setParameter("userid", data.getUserid())
						.setParameter("biodate", DateFormatter.getStringDate(data.getBioMDate()))
						.setParameter("bioname", data.getBioMName()).setParameter("bioreason", data.getBioMReason())
						.getResultList();

				obj.put("status", "success");
				obj.put("message", "Updated successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", "Something went wrong");
		}

		logger.info("Method : postBioMedicalImplantsApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPathologyListByLocation(SearchDetailsModel data) {
		logger.info("Method : getPathologyListByLocation Dao starts");

		List<DropDownModel> pathologyList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("pathology_list_by_location")
					.setParameter("location", data.getAddress()).setParameter("city", data.getCity()).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				pathologyList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, pathologyList, ResponseStatus.success,
					ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}

		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getPathologyListByLocation Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> getUserTestDetailsByAppNo(String appno) {
		logger.info("Method : getUserTestDetailsByAppNo Dao starts");

		List<APIMedicationModel> testList = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("view_user_testdtls_byappno")
					.setParameter("appno", appno).getResultList();
			for (Object[] m : x) {

				Boolean boolean1 = false;
				if (m[7].toString() != null) {
					String data = m[7].toString();
					if (data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}

				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						boolean1, m[8], m[9]);
				testList.add(dropDownModel);
			}

			if (testList.size() > 0) {
				Util.setJsonResponse(jsonResponse, testList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
		}
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getUserTestDetailsByAppNo Dao ends");
		return response;
	}

	// Prescription details for medicines
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> viewUserPrescriptionDetails(String userid) {
		logger.info("Method : viewUserPrescriptionDetails Dao starts");

		List<APIMedicationModel> priscriptionDetails = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("view_user_priscription_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						null);
				priscriptionDetails.add(dropDownModel);
			}

			if (priscriptionDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, priscriptionDetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewUserPrescriptionDetails Dao ends");
		return response;
	}

	// Prescription details for test
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> viewUserTestDetails(String userid) {
		logger.info("Method : viewUserTestDetails Dao starts");

		List<APIMedicationModel> testDetails = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("view_user_test_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2], m[3]);
				testDetails.add(dropDownModel);
			}

			if (testDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, testDetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewUserTestDetails Dao ends");
		return response;
	}

	// Add Organ Donation Details
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> addOrganDonorDetails(APIOrganDonationModel data) {
		logger.info("Method : addOrganDonorDetails Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		String date = null;

		if (data.getDob() != null && data.getDob() != "") {
			date = DateFormatter.getStringDateNew(data.getDob());
		}
		// donor details list

		String donorDetails = "('" + data.getDonorName() + "','" + data.getDonorType() + "','" + data.getTypeUserName()
				+ "','" + date + "'," + data.getAge() + "," + data.getBldGr() + ",'" + data.getMob() + "','"
				+ data.getEmail() + "','" + data.getAddress() + "','" + data.getPatientId() + "')";

		logger.error("DonorDetails" + donorDetails);
		Boolean validation = true;

		String organList = "";
		// Organ or Tissue List
		if (data.getOrganList().size() > 0 || data.getTissueList().size() > 0) {

			for (String m : data.getOrganList()) {
				organList = organList + "(var_donorid," + m + ",pat_id),";

			}

			for (String m : data.getTissueList()) {
				organList = organList + "(var_donorid," + m + ",pat_id),";

			}
			organList = organList.substring(0, organList.length() - 1);

		} else {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, "Tissue list or organ list required");
			validation = false;
		}
		logger.error("organList" + organList);

		// witness List
		String witnessList = "";
		if (data.getWitnessList().size() > 0) {
			for (APIOrganDonationModel m : data.getWitnessList()) {
				witnessList = witnessList + "(var_donorid,pat_id,\'" + m.getDonorName() + "\',\'" + m.getDonorType()
						+ "\',\'" + m.getTypeUserName() + "\',\'" + m.getMob() + "\',\'" + m.getEmail() + "\',"
						+ m.getAge() + ",\'" + m.getAddress() + "\',pat_id," + m.getRelation() + "),";
			}
			witnessList = witnessList.substring(0, witnessList.length() - 1);

		} else {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, "Witness list required");
			validation = false;
		}

		logger.error("witnessList" + witnessList);

		if (validation) {
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_organ_details_api")
						.setParameter("donordetails", donorDetails).setParameter("organlist", organList)
						.setParameter("witnesslist", witnessList).setParameter("patient_id", data.getPatientId())
						.getResultList();

				Util.setJsonResponse(jsonResponse, null, ResponseStatus.success, ApiResponseMessage.SAVED_SUCCESSFULLY);

			} catch (Exception e) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : addOrganDonorDetails Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<APIOrganDonationModel>> viewUserDonatedOrganList(String userid) {
		logger.info("Method : viewUserDonatedOrganList Dao starts");

		List<APIOrganDonationModel> userDetails = new ArrayList<APIOrganDonationModel>();
		List<DropDownModel> organList = new ArrayList<DropDownModel>();
		JsonResponse<APIOrganDonationModel> jsonResponse = new JsonResponse<APIOrganDonationModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("view_user_organ_donation")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				APIOrganDonationModel dropDownModel = new APIOrganDonationModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6]);
				userDetails.add(dropDownModel);
			}

			List<Object[]> x1 = em.createNamedStoredProcedureQuery("view_user_donated_organ_list")
					.setParameter("userid", userid).getResultList();

			for (Object[] m : x1) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				organList.add(dropDownModel);
			}

			userDetails.get(0).setDonatedOrganList(organList);

			jsonResponse.setBody(userDetails.get(0));

			if (userDetails.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<APIOrganDonationModel>> response = new ResponseEntity<JsonResponse<APIOrganDonationModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewUserDonatedOrganList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> getUserCurrentMedicineList(String userid) {
		logger.info("Method : getUserCurrentMedicineList Dao starts");

		List<APIMedicationModel> medlist = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_current_medicine_list_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], null, m[2], m[3], m[4], m[5],
						m[6], m[7]);
				medlist.add(dropDownModel);
			}
			jsonResponse.setBody(medlist);

			if (medlist.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getUserCurrentMedicineList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> viewGovernmentSchemeList(String country, String state) {
		logger.info("Method : viewGovernmentSchemeList Dao starts");

		List<DropDownModel> govtschemelist = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("view_govt_scheme_list_api")
					.setParameter("country", country).setParameter("state", state).getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2], null, null);
				govtschemelist.add(dropDownModel);
			}

			if (govtschemelist.size() > 0) {
				Util.setJsonResponse(jsonResponse, govtschemelist, ResponseStatus.success, "Data Fetched Successfully");
			} else {
				Util.setJsonResponse(jsonResponse, govtschemelist, ResponseStatus.failed, "Data not found");
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewGovernmentSchemeList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> deleteListAPI(String id, String type) {
		logger.info("Method : deleteListAPI Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("delete_from_list_api").setParameter("id", id)
					.setParameter("type", type).getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Data deleted successfully");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", "Something went wrong");

		}

		logger.info("Method : deleteListAPI Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postDoctorRatingAPI(DoctorRatingModel data) {
		logger.info("Method : postDoctorRatingAPI Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("post_doctor_rating_api")
					.setParameter("userid", data.getUserid()).setParameter("drid", data.getDrid())
					.setParameter("rating", data.getRating()).setParameter("appno", data.getAppno())
					.setParameter("reviews", data.getReviews()).getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", ApiResponseMessage.SAVE_RATING);
			} else {
				obj.put("status", "failed");
				obj.put("message", ApiResponseMessage.UNKNOWN_EXCEPTION);
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", ApiResponseMessage.UNKNOWN_EXCEPTION);
		}

		logger.info("Method : postDoctorRatingAPI Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<APIMedicineReminderModel>> postMedicineReminderDetails(
			APIMedicineReminderModel data) {
		logger.info("Method : postMedicineReminderDetails Dao starts");

		JsonResponse<APIMedicineReminderModel> jsonResponse = new JsonResponse<APIMedicineReminderModel>();

		Boolean validation = true;

		Date date = new Date();

		String taken_date = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String taken_time = new SimpleDateFormat("HH:mm:ss").format(date);

		if (validation) {
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_medicine_reminder_details_api")
						.setParameter("userid", data.getUserid()).setParameter("medid", data.getMedid())
						.setParameter("med_date", taken_date).setParameter("med_time", taken_time)
						.setParameter("status", data.getStatus()).getResultList();

				if (x.size() > 0) {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.success,
							ApiResponseMessage.SAVED_SUCCESSFULLY);
				} else {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_FAILURE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<APIMedicineReminderModel>> response = new ResponseEntity<JsonResponse<APIMedicineReminderModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : postMedicineReminderDetails Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postUserHealthRecordAPI(HealthRecordModel data) {
		logger.info("Method : postUserHealthRecordAPI Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean validity = true;
		if (StringUtil.isNull(data.getUserid())) {
			obj.put("status", "failed");
			obj.put("message", "UHID Required");
			validity = false;
		} else if (StringUtil.isNull(data.getRecorddate())) {
			obj.put("status", "failed");
			obj.put("message", "Date Required");
			validity = false;
		} else if (StringUtil.isNull(data.getRecordtime())) {
			obj.put("status", "failed");
			obj.put("message", "Time Required");
			validity = false;
		} else if (StringUtil.isNull(data.getTestname())) {
			obj.put("status", "failed");
			obj.put("message", "Test Name Required");
			validity = false;
		} else if (StringUtil.isNull(data.getTestresult())) {
			obj.put("status", "failed");
			obj.put("message", "Test Value Required");
			validity = false;
		}

		if (validity) {
			try {

				if (!StringUtil.isNull(data.getRecorddate())) {
					data.setRecorddate(DateFormatter.getStringDate(data.getRecorddate()));
				}

				List<Object[]> x = em.createNamedStoredProcedureQuery("post_user_health_record_api")
						.setParameter("userid", data.getUserid()).setParameter("recorddate", data.getRecorddate())
						.setParameter("recordtime", data.getRecordtime()).setParameter("testname", data.getTestname())
						.setParameter("testresult", data.getTestresult()).getResultList();

				if (x.size() > 0) {
					obj.put("status", "success");
					obj.put("message", ApiResponseMessage.SAVED_SUCCESSFULLY);
				} else {
					obj.put("status", "failed");
					obj.put("message", ApiResponseMessage.UNKNOWN_EXCEPTION);
				}

			} catch (Exception e) {
				obj.put("status", "failed");
				obj.put("message", ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}

		logger.info("Method : postUserHealthRecordAPI Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<UserDetailsAPIModel> getUserSomeDetailsAPI(String userid) {
		logger.info("Method : getUserSomeDetailsAPI Dao starts");

		JsonResponse<UserDetailsAPIModel> jsonResponse = new JsonResponse<UserDetailsAPIModel>();

		List<UserDetailsAPIModel> dataList = new ArrayList<UserDetailsAPIModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_some_details_api")
					.setParameter("userid", userid).getResultList();

			for (Object[] m : x) {
				UserDetailsAPIModel userDetailsAPIModel = new UserDetailsAPIModel(m[0], m[1], m[2], m[3]);
				dataList.add(userDetailsAPIModel);
			}

			if (dataList.size() > 0) {
				Util.setJsonResponse(jsonResponse, dataList.get(0), ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}

		logger.info("Method : getUserSomeDetailsAPI Dao ends");
		return jsonResponse;
	}

	// Get patient all lab details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> getPatientAllLabRequestList(String userid,
			String page) {
		logger.info("Method : getPatientAllLabRequestList Dao starts");

		List<APIMedicationModel> patientLabDetails = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {
			Integer pageno = Integer.parseInt(page);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_all_lab_request_list_api")
					.setParameter("userid", userid).setParameter("pageno", pageno).getResultList();
			for (Object[] m : x) {

				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], null, null);
				patientLabDetails.add(dropDownModel);
			}

			if (patientLabDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, patientLabDetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientAllLabRequestList Dao ends");
		return response;
	}

	// Get patient all lab details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> getPatientAllMedicineRequestList(String userid,
			String page) {
		logger.info("Method : getPatientAllMedicineRequestList Dao starts");

		List<APIMedicationModel> patientLabDetails = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {
			Integer pageno = Integer.parseInt(page);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_all_medicine_request_list_api")
					.setParameter("userid", userid).setParameter("pageno", pageno).getResultList();
			for (Object[] m : x) {

				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						null, null, null, null);
				patientLabDetails.add(dropDownModel);
			}

			if (patientLabDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, patientLabDetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientAllMedicineRequestList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Boolean> getPatientAadhaarExistsApi(String aadhaar) {

		logger.info("Method : getAadhaarExistsForRegDao starts");
		List<DropDownModel> aadhaarDetails = new ArrayList<DropDownModel>();
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("check_aadhar_id_exists")
					.setParameter("aadhaar", aadhaar).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1], m[3]);
				aadhaarDetails.add(dropDownModel);
			}

			if (aadhaarDetails.size() > 0) {
				result = true;
			} else {
				result = false;
			}

		} catch (Exception e) {
			String[] err = serverDao.errorProcedureCall(e);
			logger.error("Error " + err[1]);
			e.printStackTrace();
			result = false;
		}

		map.put("result", result);

		logger.info("Method : getAadhaarExistsForRegDao ends");
		return map;
	}

	// Get patient all lab details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<UserProfileModel>>> getUserProfileEdit(String userid) {
		logger.info("Method : getUserProfileEdit Dao starts");

		List<UserProfileModel> patient = new ArrayList<UserProfileModel>();
		JsonResponse<List<UserProfileModel>> jsonResponse = new JsonResponse<List<UserProfileModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_edit_profile").setParameter("userid", userid)
					.getResultList();
			for (Object[] m : x) {
				String prfileImg = null;
				if (m[6] != null && m[6] != "" && m[6] != " " && !m[6].toString().equals(" ")
						&& !m[6].toString().equals(null) && !m[6].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[6].toString();
				} else {
					prfileImg = "";
				}
				Object date = null;

				if (m[8] != null) {
					date = DateFormatter.returnStringDateNew(m[8].toString());
				}
				UserProfileModel userProfileModel = new UserProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], prfileImg,
						m[7], date, m[9],m[10]);
				patient.add(userProfileModel);
			}

			if (patient.size() > 0) {
				Util.setJsonResponse(jsonResponse, patient, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<UserProfileModel>>> response = new ResponseEntity<JsonResponse<List<UserProfileModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getUserProfileEdit Dao ends");
		return response;
	}

	public String saveAllImage1(byte[] imageBytes, String ext, String pId) {
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

			Path path = Paths.get(env.getDocumentUpload() + imageName);
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

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> updateUserProfile(UserProfileModel data) {
		logger.info("Method : updateUserProfileAPI Dao starts" + data);

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		/*
		 * Boolean validity = true; if (data.getMobile() == null || data.getMobile() ==
		 * "") { jsonResponse.setMessage("Mobile Number Required"); validity = false; }
		 */

		String userid = null;
		if (data.getUserid() == null) {
			userid = data.getUserid().toString();
		}
		
		System.out.println("userid"+data.getUserid());
		System.out.println("userid"+userid);
		String fname = null;
		if (data.getFname() == null) {
			fname = data.getFname().toString();
		}

		String mobile = null;
		if (data.getMobile() == null) {
			mobile = data.getMobile().toString();
		}
		String email = null;
		if (data.getEmail() == null) {
			email = data.getEmail().toString();
		}
		String language = null;
		if (data.getLangaugeKnown() == null) {
			language = data.getLangaugeKnown().toString();
		}
		String patDOB = null;
		
		if (data.getDob() == null && data.getDob() == "") {
			patDOB = DateFormatter.getStringDateNew(data.getDob()); 
			// patDOB =data.getDob().toString();
		}
		 
		
		
		logger.info("patDOB"+patDOB);
		String gender = null;
		if (data.getGender() == null && data.getGender() == "") {
			gender = data.getGender().toString();
		}
		try {

			if (data.getUserid() != null && data.getUserid() != "") {
				List<Object[]> x = em.createNamedStoredProcedureQuery("update_user_profile")
									.setParameter("userid", data.getUserid())
									.setParameter("fname", data.getFname())
									.setParameter("mobile", data.getMobile())
									.setParameter("email", data.getEmail())
									.setParameter("language", data.getLangaugeKnown())
									.setParameter("patDOB", DateFormatter.getStringDateNew(data.getDob()))
									.setParameter("genders", data.getGender()).getResultList();

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

		logger.info("Method : updateUserProfileAPI Dao ends" + response);
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
					System.out.println("ifimagename"+imageName);
				} else {
					imageName = pId + "_" + nowTime + "." + ext;
					System.out.println("elseimagename"+imageName);
				}

			}

			Path path = Paths.get(env.getDocumentUpload() + imageName);
			System.out.println("path"+path);
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
						System.out.println("outputImagePath"+outputImagePath);
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
	public ResponseEntity<JsonResponse<UserProfileUpdateModel>> postUpdateUserProfile(
			UserProfileUpdateModel usermodel) {
		logger.info("Method : postUpdateUserProfile Dao starts");
		System.out.println(usermodel);
		JsonResponse<UserProfileUpdateModel> jsonResponse = new JsonResponse<UserProfileUpdateModel>();
		Boolean validity = true;

		if (usermodel.getUserId() == null || usermodel.getUserId() == "") {
			jsonResponse.setMessage("postUpdateUserProfile");
			validity = false;
		}
		if (usermodel.getUserProfileImg() != null && usermodel.getUserProfileImg() != "") {
			try {
				byte[] bytes = Base64.getDecoder().decode(usermodel.getUserProfileImg());
				String imageName = null;
				imageName = saveAllImage2(bytes, usermodel.getExtension(), usermodel.getUserId());
				usermodel.setUserProfileImg(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (validity) {
			System.out.println("usermodel===" + usermodel);
			try {
				if (usermodel.getUserId() != null && usermodel.getUserId() != "") {

					List<UserProfileUpdateModel[]> x = em.createNamedStoredProcedureQuery("post_update_user_profile")
							.setParameter("userId", usermodel.getUserId())
							.setParameter("userProfile", usermodel.getUserProfileImg()).getResultList();
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
		ResponseEntity<JsonResponse<UserProfileUpdateModel>> response = new ResponseEntity<JsonResponse<UserProfileUpdateModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postUpdateUserProfile Dao ends");
		return response;
	}

	// Language dropdown
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getLanguageDropdownList() {
		logger.info("Method : getLanguageDropdownList Dao starts");

		List<DropDownModel> userList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_language_dropdown_list").getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				userList.add(dropDownModel);
			}
			jsonResponse.setBody(userList);

			if (userList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getLanguageDropdownList Dao ends");
		return response;
	}

	// Language dropdown
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getSelfDocTypeDropdown() {
		logger.info("Method : getSelfDocTypeDropdown Dao starts");

		List<DropDownModel> userList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_selfdocument_dropdown").getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				userList.add(dropDownModel);
			}
			jsonResponse.setBody(userList);

			if (userList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getSelfDocTypeDropdown Dao ends");
		return response;
	}

	// Get patient all lab details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<PatientReportModel>>> patientReportApi(String userId) {
		logger.info("Method : patientReportApi Dao starts");

		List<PatientReportModel> patient = new ArrayList<PatientReportModel>();
		JsonResponse<List<PatientReportModel>> jsonResponse = new JsonResponse<List<PatientReportModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_view_patientReport")
					.setParameter("userid", userId).getResultList();
			for (Object[] m : x) {
				String prfileImg = null;
				if (m[4] != null && m[4] != "" && m[4] != " " && !m[4].toString().equals(" ")
						&& !m[4].toString().equals(null) && !m[4].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[4].toString();
				} else {
					prfileImg = "";
				}
				PatientReportModel userProfileModel = new PatientReportModel(m[0], m[1], m[2], m[3], prfileImg);
				patient.add(userProfileModel);
			}

			if (patient.size() > 0) {
				Util.setJsonResponse(jsonResponse, patient, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<PatientReportModel>>> response = new ResponseEntity<JsonResponse<List<PatientReportModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : patientReportApi Dao ends" + response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<PrescriptionModel>> getPrescriptionReport(String userId, String appointId) {
		logger.info("Method : getPrescriptionReport Dao starts"+userId +","+appointId);

		PrescriptionModel cureeazyLabTestModel = new PrescriptionModel();
		JsonResponse<PrescriptionModel> jsonResponse = new JsonResponse<PrescriptionModel>();
		try {
			System.out.println(userId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_details_prescription")
					.setParameter("userid", userId).setParameter("appointId", appointId).getResultList();
			for (Object[] m : x) {
				PrescriptionModel dropDownModel = new PrescriptionModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9], m[10], m[11], m[12], m[13]);
				cureeazyLabTestModel = dropDownModel;
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}

		// Test List
		List<PrescriptionTestModel> testlist = new ArrayList<PrescriptionTestModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_patient_test_prescription")
					.setParameter("userid", userId).setParameter("appointId", appointId).getResultList();
			for (Object[] m : x1) {
				PrescriptionTestModel dropDownModel = new PrescriptionTestModel(m[0], m[1]);
				testlist.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Medicine List
		List<MedicineApiModel> medicineList = new ArrayList<MedicineApiModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_patient_medicine_prescription")
					.setParameter("userid", userId).setParameter("appointId", appointId).getResultList();
			for (Object[] m : x1) {
				MedicineApiModel dropDownModel = new MedicineApiModel(m[0], m[1], m[2], m[3], m[4], m[5]);
				medicineList.add(dropDownModel);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(cureeazyLabTestModel);
		cureeazyLabTestModel.setTestlist(testlist);
		cureeazyLabTestModel.setMedicinelist(medicineList);
		ResponseEntity<JsonResponse<PrescriptionModel>> response = new ResponseEntity<JsonResponse<PrescriptionModel>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully");
		logger.info("Method : getPrescriptionReport Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getGenderDropdown() {
		logger.info("Method : getGenderDropdown Dao starts");

		List<DropDownModel> userList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_gender_dropdown").getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				userList.add(dropDownModel);
			}
			jsonResponse.setBody(userList);

			if (userList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getGenderDropdown Dao ends");
		return response;
	}
	
	
	// Get patient all lab details
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<PatientReportModel>>> patientReportCompleteApi(String userId) {
			logger.info("Method : patientReportCompleteApi Dao starts");

			List<PatientReportModel> patient = new ArrayList<PatientReportModel>();
			JsonResponse<List<PatientReportModel>> jsonResponse = new JsonResponse<List<PatientReportModel>>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_view_patientReport_complete")
						.setParameter("userid", userId).getResultList();
				for (Object[] m : x) {
					String prfileImg = null;
					if (m[3] != null && m[3] != "" && m[3] != " " && !m[3].toString().equals(" ")
							&& !m[3].toString().equals(null) && !m[3].toString().equals("")) {
						prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[3].toString();
					} else {
						prfileImg = "";
					}
					Object date = null;
					if (m[1] != null) {
						date = DateFormatter.returnStringDateNew(m[1].toString());
					}
					PatientReportModel userProfileModel = new PatientReportModel(m[0], date, m[2], prfileImg);
					patient.add(userProfileModel);
				}

				if (patient.size() > 0) {
					Util.setJsonResponse(jsonResponse, patient, ResponseStatus.success,
							ApiResponseMessage.DATA_FETCH_SUCCESS);
				} else {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
				}

			} catch (Exception e) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
			}
			ResponseEntity<JsonResponse<List<PatientReportModel>>> response = new ResponseEntity<JsonResponse<List<PatientReportModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : patientReportCompleteApi Dao ends" + response);
			return response;
		}
}
