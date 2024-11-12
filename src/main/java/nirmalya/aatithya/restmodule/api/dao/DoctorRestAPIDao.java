package nirmalya.aatithya.restmodule.api.dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.controller.GetDaysOfWeek;
import nirmalya.aatithya.restmodule.api.model.APIAllergyModel;
import nirmalya.aatithya.restmodule.api.model.APIBioMedicalModel;
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
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.GenerateRandomValue;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class DoctorRestAPIDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	Logger logger = LoggerFactory.getLogger(DoctorRestAPIDao.class);

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

					if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png")) {

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

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : saveAllImage ends");
		return imageName;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> viewDoctorAppointmentList(String userid,
			String date, String status) {
		logger.info("Method : viewDoctorAppointmentList Dao starts");

		List<AppointmentListModel> countryList = new ArrayList<AppointmentListModel>();
		JsonResponse<List<AppointmentListModel>> jsonResponse = new JsonResponse<List<AppointmentListModel>>();
		try {
			if (!StringUtil.isNull(date)) {
				date = DateFormatter.getStringDateNew(date);
			}

			System.out.println(userid + " " + date + " " + status);
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_doctor_appointment_list")
					.setParameter("userid", userid).setParameter("date", date).setParameter("status", status)
					.getResultList();
			for (Object[] m : x) {

				String appstatus = null;
				if (m[6] != null) {
					if (m[6].toString().contentEquals("7")) {
						appstatus = "Requested";
					}
					if (m[6].toString().contentEquals("2")) {
						appstatus = "Confirmed";
					}
					if (m[6].toString().contentEquals("4")) {
						appstatus = "Cancelled";
					}
					if (m[6].toString().contentEquals("5")) {
						appstatus = "Treated";
					}
				}

				AppointmentListModel dropDownModel = new AppointmentListModel(m[0], m[1], m[2], m[3], m[4], m[5],
						appstatus, m[7], m[8], m[9], m[10], m[11], null ,m[12]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("No Data Found");
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AppointmentListModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentListModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : viewDoctorAppointmentList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changeUserAppointmentStatus(String appid, String appstatus) {
		logger.info("Method : changeUserAppointmentStatus DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		if (validity) {
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("changeUserAppointmentStatus")
						.setParameter("appid", appid).setParameter("appstatus", appstatus).getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Status Changed Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : changeUserAppointmentStatus DAO ends");
		return response;
	}

	/*
	 * @SuppressWarnings("unchecked") public ResponseEntity<JsonResponse<Object>>
	 * doctorRegistrationDao(DoctorRegistrationModel data) {
	 * logger.info("Method : doctorRegistrationDao Dao starts");
	 * 
	 * JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
	 * 
	 * Boolean validity = true; if (data.getAddress() == null || data.getAddress()
	 * == "") { jsonResponse.setMessage("Address Required"); validity = false; }
	 * else if (data.getBloodgroup() == null || data.getBloodgroup() == "") {
	 * jsonResponse.setMessage("Blood Group Required"); validity = false; } else if
	 * (data.getOrganizationid() == null || data.getOrganizationid() == "") {
	 * jsonResponse.setMessage("Organization Required"); validity = false; } else if
	 * (data.getGender() == null || data.getGender() == "") {
	 * jsonResponse.setMessage("Gender Required"); validity = false; } else if
	 * (data.getTitleid() == null || data.getTitleid() == "") {
	 * jsonResponse.setMessage("Title Required"); validity = false; // } else if
	 * (data.getAddress() == null || data.getAddress() == "") { //
	 * jsonResponse.setMessage("Address Required"); // validity = false; } else if
	 * (data.getDocname() == null || data.getDocname() == "") {
	 * jsonResponse.setMessage("Name Required"); validity = false; } else if
	 * (data.getSpeciality() == null || data.getSpeciality() == "") {
	 * jsonResponse.setMessage("Speciality Required"); validity = false; } else if
	 * (data.getCountryid() == null) { jsonResponse.setMessage("Country Required");
	 * validity = false; } else if (data.getStateid() == null) {
	 * jsonResponse.setMessage("State Required"); validity = false; } else if
	 * (data.getDistrictid() == null) {
	 * jsonResponse.setMessage("District Required"); validity = false; } else if
	 * (data.getCityid() == null) { jsonResponse.setMessage("City Required");
	 * validity = false; } else if (data.getPincode() == null || data.getPincode()
	 * == "") { jsonResponse.setMessage("Pin Required"); validity = false; } else if
	 * (data.getDob() == null || data.getDob() == "") {
	 * jsonResponse.setMessage("DOB Required"); validity = false; }
	 * 
	 * 
	 * else if (data.getEducationid() == null) {
	 * jsonResponse.setMessage("Education Required"); validity = false; } else if
	 * (data.getEmail() == null) { jsonResponse.setMessage("Email id Required");
	 * validity = false; } else if (data.getRole() == null) {
	 * jsonResponse.setMessage("Role Required"); validity = false; } Boolean
	 * isMobileExist = false; // Boolean isMobileExist =
	 * checkDuplicateDao.isPatientMobileNumberExist(data.getMobno()); // // if
	 * (isMobileExist) { // // jsonResponse.setCode("failed"); //
	 * jsonResponse.setMessage("Mobile number is already exist."); // //
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, // HttpStatus.OK); // //
	 * return response; // } if (!isMobileExist) { if (data.getDocname() != null &&
	 * data.getDocname() != "") { System.out.println("Doc Name=========" +
	 * data.getDocname()); String noSpaceStr = data.getDocname().replaceAll("\\s",
	 * ""); String substr = noSpaceStr.substring(0, 3); substr =
	 * substr.toUpperCase(); String random = generateRandom(5);
	 * data.setUserid(substr.concat(random)); }
	 * 
	 * String image = ""; if (data.getProfileImage().size() > 0) {
	 * 
	 * if (data.getProfileImage().get(0) != null && data.getProfileImage().get(0) !=
	 * "") { try { byte[] bytes =
	 * Base64.getDecoder().decode(data.getProfileImage().get(0)); String imageName =
	 * null; // if (data.getProfileImageType().equals("jpg") ||
	 * data.getProfileImageType().equals("jpeg") // ||
	 * data.getProfileImageType().equals("png")) { imageName = saveAllImage(bytes,
	 * data.getProfileImageType(), data.getUserid()); // } else { // imageName =
	 * saveAllImage(bytes, data.getProfileImageType(), data.getUserid()); // }
	 * 
	 * data.setProfileImageName(imageName); image = imageName; } catch (Exception e)
	 * { e.printStackTrace(); } } }
	 * 
	 * String docEmail = ""; if (data.getEmail() != null && data.getEmail() != "") {
	 * docEmail = data.getEmail(); } String docAltEmail = ""; if
	 * (data.getAlteremail() != null && data.getAlteremail() != "") { docAltEmail =
	 * data.getAlteremail(); }
	 * 
	 * String address = ""; if (data.getAddress() != null && data.getAddress() !=
	 * "") { address = data.getAddress(); }
	 * 
	 * String experience = ""; if (data.getExperience() != null &&
	 * data.getExperience() != "") { experience = data.getExperience(); }
	 * 
	 * String date = null; if (data.getDob() != null && data.getDob() != "") { date
	 * = DateFormatter.getStringDateNew(data.getDob()); }
	 * 
	 * String offNo = ""; if (data.getOfficephone() != null && data.getOfficephone()
	 * != "") { offNo = data.getOfficephone(); }
	 * 
	 * String homeNo = ""; if (data.getHomephone() != null && data.getHomephone() !=
	 * "") { homeNo = data.getHomephone(); } String pin = ""; if (data.getPincode()
	 * != null && data.getPincode() != "") { pin = data.getPincode(); } String
	 * bldgrp = null; if (data.getBloodgroup() != null && data.getBloodgroup() !=
	 * "") { bldgrp = data.getBloodgroup(); } String edu = null; if
	 * (data.getEducationid() != null && data.getEducationid() != "") { edu =
	 * data.getEducationid(); } String title = ""; if (data.getTitleid() != null &&
	 * data.getTitleid() != "") { title = data.getTitleid(); }
	 * 
	 * if (validity) { try { if (data.getDctrid() == null || data.getDctrid() == "")
	 * {
	 * 
	 * List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("doctor_registration_mobile")
	 * .setParameter("organizationid",
	 * data.getOrganizationid()).setParameter("titleid", title)
	 * .setParameter("docname", data.getDocname()).setParameter("educationid", edu)
	 * .setParameter("speciality", data.getSpeciality()).setParameter("dob", date)
	 * .setParameter("gender", data.getGender()).setParameter("address", address)
	 * .setParameter("countryid", data.getCountryid()).setParameter("bloodgroup",
	 * bldgrp) .setParameter("stateid", data.getStateid())
	 * .setParameter("districtid", data.getDistrictid()) .setParameter("cityid",
	 * data.getCityid()).setParameter("pin", pin) .setParameter("homephone",
	 * homeNo).setParameter("officephone", offNo) .setParameter("mobno",
	 * data.getMobno()).setParameter("email", docEmail) .setParameter("alteremail",
	 * docAltEmail).setParameter("userid", data.getUserid())
	 * .setParameter("password",
	 * "$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6")
	 * .setParameter("profileImage", image).setParameter("role", data.getRole())
	 * .setParameter("experience", experience).getResultList();
	 * 
	 * jsonResponse.setBody(x.get(0));
	 * 
	 * }
	 * 
	 * jsonResponse.setCode("success"); if (data.getRole().equals("2")) {
	 * jsonResponse.setMessage("Doctor Registered Successfully"); } else if
	 * (data.getRole().equals("5")) {
	 * jsonResponse.setMessage("Receptionist Registered Successfully"); } else if
	 * (data.getRole().equals("22")) {
	 * jsonResponse.setMessage("Syndicate Partner Registered Successfully"); } else
	 * { jsonResponse.setMessage("Lab Technician Registered Successfully"); }
	 * 
	 * String msg =
	 * "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
	 * + data.getMobno() + " and password is User@123 ";
	 * 
	 * String encodemsg = URLEncoder.encode(msg, "UTF-8");
	 * 
	 * CommonUsed.sendSMS(data.getMobno(), encodemsg);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); jsonResponse.setCode("failed");
	 * jsonResponse.setMessage("Something went wrong. Registration failed."); } } }
	 * 
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK);
	 * 
	 * logger.info("Method : doctorRegistrationDao Dao ends"); return response; }
	 */
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> doctorRegistrationDao(DoctorRegistrationModel data) {
		logger.info("Method : doctorRegistrationDao Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (StringUtil.isNull(data.getAddress())) {
			jsonResponse.setMessage("Address Required");
			validity = false;
		} else if (StringUtil.isNull(data.getOrganizationid())) {
			jsonResponse.setMessage("Organization Required");
			validity = false;
		} else if (StringUtil.isNull(data.getGender())) {
			jsonResponse.setMessage("Gender Required");
			validity = false;
//		} else if (StringUtil.isNull(data.getTitleid())) {
//			jsonResponse.setMessage("Title Required");
//			validity = false;
		} else if (StringUtil.isNull(data.getDocname())) {
			jsonResponse.setMessage("Name Required");
			validity = false;
		} else if (StringUtil.isNull(data.getSpeciality())) {
			jsonResponse.setMessage("Speciality Required");
			validity = false;
		} else if (StringUtil.isNull(data.getCountryid())) {
			jsonResponse.setMessage("Country Required");
			validity = false;
		} else if (StringUtil.isNull(data.getStateid())) {
			jsonResponse.setMessage("State Required");
			validity = false;
		} else if (StringUtil.isNull(data.getDistrictid())) {
			jsonResponse.setMessage("District Required");
			validity = false;
		} else if (StringUtil.isNull(data.getCityid())) {
			jsonResponse.setMessage("City Required");
			validity = false;
		} else if (StringUtil.isNull(data.getPincode())) {
			jsonResponse.setMessage("Pin Required");
			validity = false;
		} else if (StringUtil.isNull(data.getEmail())) {
			jsonResponse.setMessage("Email id Required");
			validity = false;
		} else if (StringUtil.isNull(data.getRole())) {
			jsonResponse.setMessage("Role Required");
			validity = false;
		}
		Boolean isMobileExist = false;
//		Boolean isMobileExist = checkDuplicateDao.isPatientMobileNumberExist(data.getMobno());
//
//		if (isMobileExist) {
//
//			jsonResponse.setCode("failed");
//			jsonResponse.setMessage("Mobile number is already exist.");
//
//			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
//					HttpStatus.OK);
//
//			return response;
//		}
		if (!isMobileExist) {
			if (!StringUtil.isNull(data.getDocname())) {
				String noSpaceStr = data.getDocname().replaceAll("\\s", "");
				String substr = noSpaceStr.substring(0, 3);
				substr = substr.toUpperCase();
				String random = GenerateRandomValue.generateRandom(5);
				data.setUserid(substr.concat(random));
			}

			String image = "";
			if (data.getProfileImage().size() > 0) {
				if (!StringUtil.isNull(data.getProfileImage().get(0))) {
					try {
						byte[] bytes = Base64.getDecoder().decode(data.getProfileImage().get(0));
						String imageName = null;
						imageName = saveAllImage(bytes, data.getProfileImageType(), data.getUserid());
						data.setProfileImageName(imageName);
						image = imageName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			String docEmail = null;
			if (!StringUtil.isNull(data.getEmail())) {
				docEmail = data.getEmail();
			}
			String docAltEmail = null;
			if (!StringUtil.isNull(data.getAlteremail())) {
				docAltEmail = data.getAlteremail();
			}

			String address = null;
			if (!StringUtil.isNull(data.getAddress())) {
				address = data.getAddress();
			}

			String experience = null;
			if (!StringUtil.isNull(data.getExperience())) {
				experience = data.getExperience();
			}

			String date = null;
			if (!StringUtil.isNull(data.getDob())) {
				date = DateFormatter.getStringDateNew(data.getDob());
			}

			String offNo = null;
			if (!StringUtil.isNull(data.getOfficephone())) {
				offNo = data.getOfficephone();
			}

			String homeNo = null;
			if (!StringUtil.isNull(data.getHomephone())) {
				homeNo = data.getHomephone();
			}
			String pin = null;
			if (!StringUtil.isNull(data.getPincode())) {
				pin = data.getPincode();
			}
			String bldgrp = null;
			if (!StringUtil.isNull(data.getBloodgroup())) {
				bldgrp = data.getBloodgroup();
			}
			String edu = null;
			if (!StringUtil.isNull(data.getEducationid())) {
				edu = data.getEducationid();
			}
			String title = null;
			if (!StringUtil.isNull(data.getTitleid())) {
				title = data.getTitleid();
			}
			
			String password = GenerateRandomValue.generateAlphanumericWithSpecialCharacter(8);
			logger.info("password==="+password);
			String encodedPassword = null;
			if(!StringUtil.isNull(password)) {
				encodedPassword = passEncoder.encode(password);
				logger.info("encodedPassword==="+encodedPassword);
			}

			if (validity) {
				try {
					if (data.getDctrid() == null || data.getDctrid() == "") {

						List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_registration_mobile")
								.setParameter("organizationid", data.getOrganizationid()).setParameter("titleid", title)
								.setParameter("docname", data.getDocname()).setParameter("educationid", edu)
								.setParameter("speciality", data.getSpeciality()).setParameter("dob", date)
								.setParameter("gender", data.getGender()).setParameter("address", address)
								.setParameter("countryid", data.getCountryid()).setParameter("bloodgroup", bldgrp)
								.setParameter("stateid", data.getStateid())
								.setParameter("districtid", data.getDistrictid())
								.setParameter("cityid", data.getCityid()).setParameter("pin", pin)
								.setParameter("homephone", homeNo).setParameter("officephone", offNo)
								.setParameter("mobno", data.getMobno()).setParameter("email", docEmail)
								.setParameter("alteremail", docAltEmail).setParameter("userid", data.getUserid())
								.setParameter("password",
										encodedPassword)
								.setParameter("profileImage", image).setParameter("role", data.getRole())
								.setParameter("experience", experience).getResultList();

						jsonResponse.setBody(x.get(0));

					}
					
					DropDownModel responseObject = new DropDownModel();
					
					responseObject.setKey(jsonResponse.getBody().toString());
					responseObject.setName(password);

//					jsonResponse.setCode("success");
					if (data.getRole().equals("2")) {
						Util.setJsonResponse(jsonResponse, responseObject, ResponseStatus.success, ApiResponseMessage.DOCTOR_REGISTRATION_SUCCESS);
//						jsonResponse.setMessage("Doctor Registration Successfully");
					} else if (data.getRole().equals("5")) {
						Util.setJsonResponse(jsonResponse, responseObject, ResponseStatus.success, ApiResponseMessage.RECEPTIONIST_REGISTRATION_SUCCESS);
//						jsonResponse.setMessage("Receptionist Registration Successfully");
					} else if (data.getRole().equals("22")) {
						Util.setJsonResponse(jsonResponse, responseObject, ResponseStatus.success, ApiResponseMessage.SYNDICATE_PARTNER_SAVED_SUCCESSFULLY);
//						jsonResponse.setMessage("Syndicate Partner Registration Successfully");
					} else {
						Util.setJsonResponse(jsonResponse, responseObject, ResponseStatus.success, ApiResponseMessage.LAB_TECH_REGISTRATION_SUCCESS);
//						jsonResponse.setMessage("Lab Technician Registration Successfully");
					}

					String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
							+ data.getMobno() + " and password is "+password;

					String encodemsg = URLEncoder.encode(msg, "UTF-8");

					CommonUsed.sendSMS(data.getMobno(), encodemsg);

				} catch (Exception e) {
					e.printStackTrace();
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.REGISTRATION_FAILED);
//					jsonResponse.setCode("failed");
//					jsonResponse.setMessage("Something went wrong. Registration failed.");
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : doctorRegistrationDao Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<PersonalInformationModel>>> userpersonalInformationDao(String userid) {
		logger.info("Method : userpersonalInformationDao Dao starts");

		List<PersonalInformationModel> infrmtnList = new ArrayList<PersonalInformationModel>();
		JsonResponse<List<PersonalInformationModel>> jsonResponse = new JsonResponse<List<PersonalInformationModel>>();

		List<APIAllergyModel> allergy = userAllergistAPI(userid);
		List<APIBioMedicalModel> bioMedical = userBioMedicalAPI(userid);

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_personal_information_list")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				String fht = null;
				Double fh = 0.0;
				if (m[24] != null) {
					Double cs = Double.parseDouble(m[24].toString());
					fh = (cs * 1.8) + 32;
					fht = fh.toString();
				}

				Object date = null;
				if (m[4] != null) {
					date = DateFormatter.returnStringDateNew(m[4].toString());
				}

				Object ms = "N/A";
				Object occp = "N/A";
				Object age = "N/A";

				if (m[5] != null) {
					double ag = Double.parseDouble(m[5].toString());
					age = (int) ag;
				}

				if (m[7] != null) {
					ms = m[7];
				}
				if (m[8] != null) {
					occp = m[8];
				}
				Object gender = "N/A";
				if (m[6] != null) {
					if (m[6].toString().contains("1")) {
						gender = "Male";
					} else if (m[6].toString().contains("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				PersonalInformationModel dropDownModel = new PersonalInformationModel(m[0], m[1], m[2], m[3], date,
						age.toString(), gender, ms, occp, m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16], m[17],
						m[18], m[19], m[20], m[21], m[22], m[23], m[24], fht, m[25], m[26], m[27], m[28], m[29], m[30],
						allergy, bioMedical);
				infrmtnList.add(dropDownModel);
			}
			jsonResponse.setBody(infrmtnList);

			jsonResponse.setCode("success");
			jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<PersonalInformationModel>>> response = new ResponseEntity<JsonResponse<List<PersonalInformationModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : userpersonalInformationDao Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<APIAllergyModel> userAllergistAPI(String userid) {
		logger.info("Method : userAllergistAPI Dao starts");

		List<APIAllergyModel> infrmtnList = new ArrayList<APIAllergyModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_allergies_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

//				String severity = null;
//				if (m[2] != null) {
//					if (m[2].toString().contentEquals("1")) {
//						severity = "High";
//					}
//					if (m[2].toString().contentEquals("2")) {
//						severity = "Medium";
//					}
//					if (m[2].toString().contentEquals("3")) {
//						severity = "Low";
//					}
//				}

				APIAllergyModel dropDownModel = new APIAllergyModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], null);
				infrmtnList.add(dropDownModel);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		logger.info("Method : userAllergistAPI Dao ends");
		return infrmtnList;
	}

	@SuppressWarnings("unchecked")
	public List<APIBioMedicalModel> userBioMedicalAPI(String userid) {
		logger.info("Method : userBioMedicalAPI Dao starts");

		List<APIBioMedicalModel> infrmtnList = new ArrayList<APIBioMedicalModel>();
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		logger.info("Method : userBioMedicalAPI Dao ends");
		return infrmtnList;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getMedicineListWithType() {
		logger.info("Method : getMedicineListWithType Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_medicinelist_withtype").getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], m[2]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("No Data Found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getMedicineListWithType Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APILabTestReportModel>>> userLabTestReportDoctor(String userid) {
		logger.info("Method : userLabTestReportDoctor Dao starts");

		List<APILabTestReportModel> infrmtnList = new ArrayList<APILabTestReportModel>();
		JsonResponse<List<APILabTestReportModel>> jsonResponse = new JsonResponse<List<APILabTestReportModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_lab_test_report").setParameter("userid", userid)
					.getResultList();
			for (Object[] m : x) {

				Object date = null;
				if (m[6] != null) {
					date = DateFormatter.returnStringDate(m[6].toString());
				}

				APILabTestReportModel dropDownModel = new APILabTestReportModel(m[0], m[1], m[2], m[3], m[4], m[5],
						date, null, m[7]);
				infrmtnList.add(dropDownModel);
			}
			jsonResponse.setBody(infrmtnList);

			if (infrmtnList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("No Data Found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APILabTestReportModel>>> response = new ResponseEntity<JsonResponse<List<APILabTestReportModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : userLabTestReportDoctor Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> userMedicationData(String userid) {
		logger.info("Method : userMedicationData Dao starts");

		List<APIMedicationModel> infrmtnList = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_medication_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6]);
				infrmtnList.add(dropDownModel);
			}
			jsonResponse.setBody(infrmtnList);

			if (infrmtnList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("No Data Found");
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : userMedicationData Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postUserMedicationDoctor(List<APIMedicationModel> data) {
		logger.info("Method : postUserMedicationDoctor Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		Integer noofdose = 0;
		String duration = null;
		String remarks = "";
		for (APIMedicationModel m : data) {
			if (m.getMedname() == null || m.getMedname() == "") {
				jsonResponse.setMessage("Medicine Name Required");
				validity = false;
			} else if (m.getDoctor() == null || m.getDoctor() == "") {
				jsonResponse.setMessage("Doctor Required");
				validity = false;
			} else if (m.getAppno() == null || m.getAppno() == "") {
				jsonResponse.setMessage("Appointment Number Required");
				validity = false;
			} else if (m.getUserid() == null || m.getUserid() == "") {
				jsonResponse.setMessage("User ID Required");
				validity = false;
			}

		}

		if (validity) {
			try {
				if (data.get(0).getDoctor() != null && data.get(0).getDoctor() != "") {

					for (APIMedicationModel m : data) {
						if (m.getMorning() == null || m.getMorning() == "") {
							m.setMorning("0");
						}

						if (m.getAfternoon() == null || m.getAfternoon() == "") {
							m.setAfternoon("0");
						}

						if (m.getEvening() == null || m.getEvening() == "") {
							m.setEvening("0");
						}

						if (m.getMorning().contentEquals("1")) {
							noofdose = noofdose + 1;
						}
						if (m.getAfternoon().contentEquals("1")) {
							noofdose = noofdose + 1;
						}
						if (m.getEvening().contentEquals("1")) {
							noofdose = noofdose + 1;
						}

						if (m.getDuration() != null && m.getDuration() != "") {
							duration = m.getDuration();
						}

						if (m.getRemarks() != null && m.getRemarks() != "") {
							remarks = m.getRemarks();
						}

						List<Object[]> x = em.createNamedStoredProcedureQuery("post_doctor_medication")
								.setParameter("userid", m.getUserid()).setParameter("appno", m.getAppno())
								.setParameter("medid", m.getMedname()).setParameter("noofdose", noofdose.toString())
								.setParameter("duration", duration).setParameter("remarks", remarks)
								.setParameter("doctor", m.getDoctor()).setParameter("datamorn", m.getMorning())
								.setParameter("datanoon", m.getAfternoon()).setParameter("dataeven", m.getEvening())
								.getResultList();

						jsonResponse.setBody(x.get(0));

					}

				}

				jsonResponse.setCode("success");
				jsonResponse.setMessage("Medicine added successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postUserMedicationDoctor Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postUserTestByDoctor(List<APIMedicationModel> data) {
		logger.info("Method : postUserTestByDoctor Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		for (APIMedicationModel m : data) {
			if (m.getTestgroup() == null || m.getTestgroup() == "") {
				jsonResponse.setMessage("Test Group Required");
				validity = false;
			} else if (m.getTestname() == null || m.getTestname() == "") {
				jsonResponse.setMessage("Test Name Required");
				validity = false;
			} else if (m.getDoctor() == null || m.getDoctor() == "") {
				jsonResponse.setMessage("Doctor Required");
				validity = false;
			} else if (m.getAppno() == null || m.getAppno() == "") {
				jsonResponse.setMessage("Appointment Number Required");
				validity = false;
			} else if (m.getUserid() == null || m.getUserid() == "") {
				jsonResponse.setMessage("User ID Required");
				validity = false;
			}

		}

		if (validity) {
			try {
				if (data.get(0).getDoctor() != null && data.get(0).getDoctor() != "") {

//					String casepaper = checkDuplicateDao.getCasePaperNumber(data.get(0).getUserid());

//					String value = "";

					List<Object[]> x = null;

					for (APIMedicationModel m : data) {

//						value = value + "(srlnodata,newcount,casepaper,opddate,opdid,appno,CURRENT_TIMESTAMP,\'"
//								+ data.get(0).getDoctor() + "\',\'" + data.get(0).getRemarks() + "\',1,\'"
//								+ m.getTestgroup() + "\',\'" + m.getTestname() + "\'),";

						System.out.println(data.get(0).getUserid() + " * " + data.get(0).getAppno() + " * "
								+ data.get(0).getDoctor() + " * " + data.get(0).getRemarks() + " * " + m.getTestgroup()
								+ " * " + m.getTestname());

						x = em.createNamedStoredProcedureQuery("post_doctor_test_details")
								.setParameter("userid", data.get(0).getUserid())
								.setParameter("appno", data.get(0).getAppno())
								.setParameter("doctor", data.get(0).getDoctor())
								.setParameter("remarks", data.get(0).getRemarks())
								.setParameter("testgroup", m.getTestgroup()).setParameter("testname", m.getTestname())
								.getResultList();

					}

//					value = value.substring(0, value.length() - 1);
//
//					System.out.println(value);
//
//					x = em.createNamedStoredProcedureQuery("post_doctor_test_details")
//							.setParameter("userid", data.get(0).getUserid())
//							.setParameter("appno", data.get(0).getAppno()).setParameter("testsubquery", value)
//							.getResultList();

					jsonResponse.setBody(x.get(0));

				}

				jsonResponse.setCode("success");
				jsonResponse.setMessage("Test added successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postUserTestByDoctor Dao ends");
		return response;
	}

	// delete test
	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> deleteTestByAppNo(String appno, String srlone, String srltwo) {
		logger.info("Method : deleteTestByAppNo Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("delete_test_byappno").setParameter("appno", appno)
					.setParameter("srlone", srlone).setParameter("srltwo", srltwo).getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Test deleted successfully");
			} else {
				obj.put("status", "failed");
				obj.put("message", ApiResponseMessage.UNKNOWN_EXCEPTION);
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", ApiResponseMessage.UNKNOWN_EXCEPTION);
		}

		logger.info("Method : deleteTestByAppNo Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	// OTP for emergency access
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getOtpEmergencyAccessDao(String userid, String drid) {
		logger.info("Method : getOtpEmergencyAccess Dao starts");

		List<DropDownModel> emergencyDetails = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();

		Boolean isExist = false;
		isExist = checkDuplicateDao.isValidUHIDNumber(userid);
		try {

			if (isExist) {
				System.out.println("Userid isExist");
				String otp = generateRandom(4);

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_drid_mobile_emergency")
						.setParameter("drid", drid).getResultList();

				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
					dropDownModel.setCode(otp);
					emergencyDetails.add(dropDownModel);

				}
				jsonResponse.setBody(emergencyDetails);
				jsonResponse.setCode("success");
				jsonResponse.setMessage("OTP sent successfully");

				String msg = "Welcome to eHealthSystem. Your OTP is " + otp;
				String encodemsg = URLEncoder.encode(msg, "UTF-8");

				CommonUsed.sendSMS(x.get(0)[1].toString(), encodemsg);

			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Not a valid UHID");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Data Not Found");
		}

		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getOtpEmergencyAccess Dao ends");
		return response;
	}

	// Get doctor my patient details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<MyPatientViewModel>>> getDoctorMyPatientList(String drid) {
		logger.info("Method : getDoctorMyPatientList Dao starts");

		List<MyPatientViewModel> patientAmbulanceDetails = new ArrayList<MyPatientViewModel>();
		JsonResponse<List<MyPatientViewModel>> jsonResponse = new JsonResponse<List<MyPatientViewModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_doctor_my_patient_list")
					.setParameter("drid", drid).getResultList();
			for (Object[] m : x) {

				MyPatientViewModel dropDownModel = new MyPatientViewModel(m[0], m[1], m[2], m[3], m[4]);
				patientAmbulanceDetails.add(dropDownModel);
			}
			jsonResponse.setBody(patientAmbulanceDetails);

			if (patientAmbulanceDetails.size() > 0) {
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
		ResponseEntity<JsonResponse<List<MyPatientViewModel>>> response = new ResponseEntity<JsonResponse<List<MyPatientViewModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getDoctorMyPatientList Dao ends");
		return response;
	}

	// Get Doctor monthly overview list
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<APIDoctorMonthlyOverviewModel>> getDoctorMyMonthlyOverviewListDao(String drid,
			String fromdate, String todate) {
		logger.info("Method : getDoctorMyMonthlyOverviewListDao Dao starts");

		JsonResponse<APIDoctorMonthlyOverviewModel> jsonResponse = new JsonResponse<APIDoctorMonthlyOverviewModel>();
		try {

			if (!StringUtil.isNull(fromdate)) {
				fromdate = DateFormatter.getStringDate(fromdate);
			}
			if (!StringUtil.isNull(todate)) {
				todate = DateFormatter.getStringDate(todate);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_doctor_monthlyoverview_api")
					.setParameter("drid", drid).setParameter("fromdate", fromdate).setParameter("todate", todate)
					.getResultList();
			for (Object[] m : x) {

				APIDoctorMonthlyOverviewModel dropDownModel = new APIDoctorMonthlyOverviewModel(m[0], m[1], m[2], m[3],
						m[4]);
				jsonResponse.setBody(dropDownModel);
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());

		}
		ResponseEntity<JsonResponse<APIDoctorMonthlyOverviewModel>> response = new ResponseEntity<JsonResponse<APIDoctorMonthlyOverviewModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getDoctorMyMonthlyOverviewListDao Dao ends");
		return response;
	}

	// Get doctor monthly patient details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIDoctorMonthlyOverviewModel>>> getDoctorMonthlyOverviewByStatusDao(
			String drid, String status, String frmdt, String todt) {
		logger.info("Method : getDoctorMonthlyOverviewByStatusDao Dao starts");

		List<APIDoctorMonthlyOverviewModel> patientMonthlyDetails = new ArrayList<APIDoctorMonthlyOverviewModel>();
		JsonResponse<List<APIDoctorMonthlyOverviewModel>> jsonResponse = new JsonResponse<List<APIDoctorMonthlyOverviewModel>>();

		if (frmdt != null && frmdt != "") {
			frmdt = DateFormatter.getStringDate(frmdt);
		}

		if (todt != null && todt != "") {
			todt = DateFormatter.getStringDate(todt);
		}

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_doctormonthlyoverview_bystatus_list")
					.setParameter("drid", drid).setParameter("apptstatus", status).setParameter("frmdt", frmdt)
					.setParameter("todt", todt).getResultList();

			for (Object[] m : x) {

				APIDoctorMonthlyOverviewModel dropDownModel = new APIDoctorMonthlyOverviewModel(m[0], m[1], m[2]);
				patientMonthlyDetails.add(dropDownModel);
			}
			jsonResponse.setBody(patientMonthlyDetails);

			if (patientMonthlyDetails.size() > 0) {
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
		ResponseEntity<JsonResponse<List<APIDoctorMonthlyOverviewModel>>> response = new ResponseEntity<JsonResponse<List<APIDoctorMonthlyOverviewModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getDoctorMonthlyOverviewByStatusDao Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> getConfirmedPatientList(String drid) {
		logger.info("Method : getConfirmedPatientList Dao starts");

		List<AppointmentListModel> patientList = new ArrayList<AppointmentListModel>();
		JsonResponse<List<AppointmentListModel>> jsonResponse = new JsonResponse<List<AppointmentListModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_confirmed_patient_list")
					.setParameter("drid", drid).getResultList();
			for (Object[] m : x) {

				AppointmentListModel dropDownModel = new AppointmentListModel(m[0], m[1], m[2], m[3]);
				patientList.add(dropDownModel);
			}
			jsonResponse.setBody(patientList);

			if (patientList.size() > 0) {
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
		ResponseEntity<JsonResponse<List<AppointmentListModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentListModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getConfirmedPatientList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorReceptionistListRoleWise(String drid,
			String roleid) {
		logger.info("Method : getDoctorReceptionistListRoleWise Dao starts");

		List<DropDownModel> userList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_doctor_receptionist_list")
					.setParameter("drid", drid).setParameter("roleid", roleid).getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1], m[2]);
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
		logger.info("Method : getDoctorReceptionistListRoleWise Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postShareAppointmentAPI(APIShareAppointmentModel data) {
		logger.info("Method : postShareAppointmentAPI Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		if (data.getPatientid() == null || data.getPatientid() == "") {
			jsonResponse.setMessage("Patient ID Required");
			validity = false;
		} else if (data.getDrid() == null || data.getDrid() == "") {
			jsonResponse.setMessage("Doctor/Receptionist Required");
			validity = false;
		} else if (data.getNotes() == null || data.getNotes() == "") {
			jsonResponse.setMessage("Notes Required");
			validity = false;
		} else if (data.getRefdrid() == null || data.getRefdrid() == "") {
			jsonResponse.setMessage("Referred Doctor Required");
			validity = false;
		} else if (data.getAppno() == null || data.getAppno() == "") {
			jsonResponse.setMessage("Appointment No Required");
			validity = false;
		}

		if (validity) {
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("post_share_appointment_api")
						.setParameter("drid", data.getDrid()).setParameter("userid", data.getPatientid())
						.setParameter("refdrid", data.getRefdrid()).setParameter("appno", data.getAppno())
						.setParameter("notes", data.getNotes()).getResultList();

				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Appointment shared successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postShareAppointmentAPI Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> updateDoctorProfileAPIDao(DoctorRegistrationModel data) {
		logger.info("Method : updateDoctorProfileAPIDao Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		if (data.getDctrid() == null || data.getDctrid() == "") {
			jsonResponse.setMessage("User Id Required");
			validity = false;
		}
		System.out.println(data);

		String dob = null;
		if (data.getDob() != null && data.getDob() != "") {
			dob = DateFormatter.getStringDateNew(data.getDob());
		}

		String gender = null;
		if (data.getGender() != null && data.getGender() != "") {
			gender = data.getGender().toString();
		}

		String education = null;
		if (data.getEducationid() != null && data.getEducationid() != "") {
			education = data.getEducationid().toString();
		}

		String experience = null;
		if (data.getExperience() != null && data.getExperience() != "") {
			experience = data.getExperience().toString();
		}

		String imaNo = null;
		if (data.getImaNo() != null && data.getImaNo() != "") {
			imaNo = data.getImaNo().toString();
		}

		String panNo = null;
		if (data.getPanNo() != null && data.getPanNo() != "") {
			panNo = data.getPanNo().toString();
		}

		String voteNo = null;
		if (data.getVotterId() != null && data.getVotterId() != "") {
			voteNo = data.getVotterId().toString();
		}

		String passportNo = null;
		if (data.getPassportNo() != null && data.getPassportNo() != "") {
			passportNo = data.getPassportNo().toString();
		}

		String licence = null;
		if (data.getLiceneceNo() != null && data.getLiceneceNo() != "") {
			licence = data.getLiceneceNo().toString();
		}

		String licenceAuthority = null;
		if (data.getLiceneceAuthority() != null && data.getLiceneceAuthority() != "") {
			licenceAuthority = data.getLiceneceAuthority().toString();
		}

		String adhaarNo = null;
		if (data.getAdhaarNo() != null && data.getAdhaarNo() != "") {
			adhaarNo = data.getAdhaarNo().toString();
		}

		String country = null;
		if (data.getCountryid() != null && data.getCountryid() != "") {
			country = data.getCountryid().toString();
		}
		String state = null;
		if (data.getStateid() != null && data.getStateid() != "") {
			state = data.getStateid().toString();
		}
		String dist = null;
		if (data.getDistrictid() != null && data.getDistrictid() != "") {
			dist = data.getDistrictid().toString();
		}
		String city = null;
		if (data.getCityid() != null && data.getCityid() != "") {
			city = data.getCityid().toString();
		}
		String address = null;
		if (data.getAddress() != null && data.getAddress() != "") {
			address = data.getAddress().toString();
		}
		String pin = null;
		if (data.getPincode() != null && data.getPincode() != "") {
			pin = data.getPincode().toString();
		}
		String mob = null;
		if (data.getMobno() != null && data.getMobno() != "") {
			mob = data.getMobno().toString();
		}
		String email = null;
		if (data.getEmail() != null && data.getEmail() != "") {
			email = data.getEmail().toString();
		}
		String bloodgrp = null;

		if (data.getBloodgroup() != null && data.getBloodgroup() != "") {

			bloodgrp = data.getBloodgroup().toString();

		}

		if (validity) {
			try {
				System.out.println(data.getDctrid());
				if (data.getDctrid() != null && data.getDctrid() != "") {

					System.out.println(data.getDctrid());
					System.out.println("dob" + dob);
					System.out.println(gender);
					System.out.println(education);
					System.out.println(experience);
					System.out.println(imaNo);
					System.out.println(panNo);
					System.out.println(voteNo);
					System.out.println(passportNo);
					System.out.println(licence);
					System.out.println(licenceAuthority);
					System.out.println(adhaarNo);

					List<Object[]> x = em.createNamedStoredProcedureQuery("update_doctor_profile_api")
							.setParameter("drid", data.getDctrid()).setParameter("ddob", dob)
							.setParameter("bloodgrp", bloodgrp).setParameter("dgender", gender)
							.setParameter("education", education).setParameter("expr", experience)
							.setParameter("imano", imaNo).setParameter("panno", panNo).setParameter("voteno", voteNo)
							.setParameter("passportno", passportNo).setParameter("licence", licence)
							.setParameter("licenceauthority", licenceAuthority).setParameter("adhaarno", adhaarNo)
							.setParameter("dcountry", country).setParameter("dstate", state).setParameter("ddist", dist)
							.setParameter("dcity", city).setParameter("daddress", address).setParameter("pincode", pin)
							.setParameter("mob", mob).setParameter("email", email).getResultList();

					jsonResponse.setBody(x.get(0));

				}

				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.USER_PROFILE_UPDATED);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.USER_PROFILE_FAILED);
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : updateDoctorProfileAPIDao Dao ends");
		return response;
	}
	/*
	 * Update Doctor digital signature
	 * 
	 * @SuppressWarnings({ "unchecked", "unused" }) public
	 * ResponseEntity<JsonResponse<Object>>
	 * doctordigitalSignatureDao(MedicalDataUploadModel data) {
	 * logger.info("Method : doctordigitalSignatureDao Dao starts");
	 * 
	 * JsonResponse<Object> jsonResponse = new JsonResponse<Object>(); Boolean
	 * validation = true;
	 * 
	 * if (data.getUserid() == null || data.getUserid() == "") { validation = false;
	 * jsonResponse.setMessage("doctor id Required"); } if (validation) {
	 * System.out.println("dataUploadDetails::");
	 * 
	 * try { List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("post_doctor_digitalsignature_api")
	 * .setParameter("docid", data.getUserid()) .setParameter("dsign",
	 * data.getFileName()).getResultList(); jsonResponse.setCode("success");
	 * jsonResponse.setMessage("Saved Successfully");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); jsonResponse.setCode("failed");
	 * jsonResponse.setMessage("Something went wrong."); } }
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK);
	 * 
	 * logger.info("Method : doctordigitalSignatureDao Dao ends"); return response;
	 * }
	 */

	/* Update Doctor digital signature */
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> doctordigitalSignatureDao(DoctorRegistrationModel data) {
		logger.info("Method : doctordigitalSignatureDao Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validation = true;
		if (data.getDctrid() == null || data.getDctrid() == "") {
			validation = false;
			jsonResponse.setMessage("doctor id Required");
		}
		String image = "";
		if (data.getDigSign().size() > 0) {
			if (data.getDigSign().get(0) != null && data.getDigSign().get(0) != "") {
				try {
					byte[] bytes = Base64.getDecoder().decode(data.getDigSign().get(0));
					String imageName = null;
					imageName = saveAllImage(bytes, data.getDigSignType(), data.getDctrid());
					data.setDigSignName(imageName);
					image = imageName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (validation) {
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_doctor_digitalsignature_api")
						.setParameter("docid", data.getDctrid()).setParameter("dsign", data.getDigSignName())
						.getResultList();
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DOCTOR_SIGNATURE_SAVED);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : doctordigitalSignatureDao Dao ends");
		return response;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> otherProfileImageDao(DoctorRegistrationModel data) {
		// TODO Auto-generated method stub
		logger.info("Method : otherProfileImageDao starts");
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validation = true;
		if (data.getDctrid() == null || data.getDctrid() == "") {
			validation = false;
			jsonResponse.setMessage("doctor id Required");
		}
		String image = "";
		if (data.getProfileImage().size() > 0) {
			if (data.getProfileImage().get(0) != null && data.getProfileImage().get(0) != "") {
				try {
					byte[] bytes = Base64.getDecoder().decode(data.getProfileImage().get(0));
					String imageName = null;
					imageName = saveAllImage(bytes, data.getProfileImageType(), data.getDctrid());
					data.setProfileImageName(imageName);
					image = imageName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (validation) {
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_other_profileimage_api")
						.setParameter("docid", data.getDctrid()).setParameter("primg", data.getProfileImageName())
						.getResultList();
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Image Saved Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : otherProfileImageDao ends");
		return response;

	}

	public ResponseEntity<JsonResponse<List<OPDResponseModel>>> doctorOPDWeekly(String drid) {
		logger.info("Method : doctorOPDWeekly Dao starts");

		JsonResponse<List<OPDResponseModel>> jsonResponse = new JsonResponse<List<OPDResponseModel>>();

		GetDaysOfWeek c = new GetDaysOfWeek();
		List<OPDResponseModel> userList = c.getCalendar();
		jsonResponse.setBody(userList);
		jsonResponse.setCode("success");
		jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);

		ResponseEntity<JsonResponse<List<OPDResponseModel>>> response = new ResponseEntity<JsonResponse<List<OPDResponseModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : doctorOPDWeekly Dao ends");
		return response;
	}
}
