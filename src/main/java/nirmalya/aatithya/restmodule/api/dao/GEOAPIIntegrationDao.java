package nirmalya.aatithya.restmodule.api.dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import nirmalya.aatithya.restmodule.api.model.APIPharmacyModel;
import nirmalya.aatithya.restmodule.api.model.APIStatusModel;
import nirmalya.aatithya.restmodule.api.model.DoctorAppointmentModel;
import nirmalya.aatithya.restmodule.api.model.GEOAPIMedDetailsModel;
import nirmalya.aatithya.restmodule.api.model.GEOAPIMedicationModel;
import nirmalya.aatithya.restmodule.api.model.UserRegistrationGeoModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class GEOAPIIntegrationDao {
	
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	Logger logger = LoggerFactory.getLogger(SignUpLogInDao.class);

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
					imageName = nowTime + ".jpg";
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
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> userRegistrationByGeo(UserRegistrationGeoModel m) {
		logger.info("Method : userRegistrationByGeo Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
//		for (UserRegistrationGeoModel m : data) {
		if (m.getMobile() == null || m.getMobile() == "") {
			jsonResponse.setMessage("Mobile Number Required");
			validity = false;
		} else if (m.getFirstname() == null || m.getFirstname() == "") {
			jsonResponse.setMessage("Name Required");
			validity = false;
		} else if (m.getAge() == null && m.getDob() == null) {
			jsonResponse.setMessage("Age/DOB Required");
			validity = false;
		} else if (m.getCountryid() == null) {
			jsonResponse.setMessage("Country Required");
			validity = false;
		} else if (m.getStateid() == null) {
			jsonResponse.setMessage("State Required");
			validity = false;
		} else if (m.getDistrictid() == null) {
			jsonResponse.setMessage("District Required");
			validity = false;
		} else if (m.getCityid() == null) {
			jsonResponse.setMessage("City Required");
			validity = false;
		}
//		}

		Boolean isMobileExist = false;

		Boolean isAadharExist = false;

//		for (UserRegistrationGeoModel m : data) {
		if (!isMobileExist && !isAadharExist) {
			if (m.getFirstname() != null && m.getFirstname() != "" && m.getLastname() != null
					&& m.getLastname() != "") {
				String name = m.getFirstname().concat(m.getLastname());
				String noSpaceStr = name.replaceAll("\\s", "");
				String substr = noSpaceStr.substring(0, 3);
				substr = substr.toUpperCase();
				String random = generateRandom(5);
				m.setUserid(substr.concat(random));
			}


			String gender1 = null;

			if (m.getGender() != null) {
				if (m.getGender().toUpperCase().replace(" ", "").equals("MALE")) {
					gender1 = "1";
				} else if (m.getGender().toUpperCase().replace(" ", "").equals("FEMALE")) {
					gender1 = "2";
				} else {
					gender1 = "3";
				}
			}

			if (m.getMaritialstatus() != null) {
				if (m.getMaritialstatus().toUpperCase().replace(" ", "").equals("MARRIED")) {
					gender1 = "1";
				} else if (m.getMaritialstatus().toUpperCase().replace(" ", "").equals("SINGLE")) {
					gender1 = "2";
				} else if (m.getMaritialstatus().toUpperCase().replace(" ", "").equals("WIDOW")) {
					gender1 = "4";
				} else {
					gender1 = "3";
				}
			}

			String pId = null;
			if (m.getPatientid() == null || m.getPatientid() == "") {
				String ccode = "";
				String scode = "";
				if (m.getCountrycode() != null && m.getCountrycode() != "") {
					ccode = m.getCountrycode();
					ccode = ccode.substring(0, 2);
					if (m.getStatecode() != null && m.getStatecode() != "") {
						scode = m.getStatecode();
					} else {
						scode = "50";
					}
				} else {
					ccode = "10";
					scode = "50";
				}

				String random = generateRandom(12);
				pId = ccode.concat(scode).concat(random);
			}
			
			String qrcodename = "QR"+pId+".png";

//			String image = "";
//			if (data.getProfileImage().size() > 0) {
//
//				if (data.getProfileImage().get(0) != null && data.getProfileImage().get(0) != "") {
//					try {
//						byte[] bytes = Base64.getDecoder().decode(data.getProfileImage().get(0));
//						String imageName = saveAllImage(bytes, data.getProfileImageType(), pId);
//						data.setProfileImageName(imageName);
//						image = imageName;
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			String patEmail = "";
//			if (data.getEmail() != null && data.getEmail() != "") {
//				patEmail = data.getEmail();
//			}
//
//			String patAadhar = "";
//			if (data.getAadhar() != null && data.getAadhar() != "") {
//				patAadhar = data.getAadhar();
//			}

			if (validity) {
				try {
					if (m.getPatientid() == null || m.getPatientid() == "") {

						System.out.println(m);

						String value1 = "";

						if (m.getHeight() != null) {
							value1 = value1 + "(4,current_timestamp," + m.getHeight()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getWeight() != null) {
							value1 = value1 + "(5,current_timestamp," + m.getWeight()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getBmi() != null) {
							value1 = value1 + "(6,current_timestamp," + m.getBmi()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getTempincel() != null) {
							value1 = value1 + "(1,current_timestamp," + m.getTempincel()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getSystolicbp() != null) {
							value1 = value1 + "(4001,current_timestamp," + m.getSystolicbp()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getDiastolicbp() != null) {
							value1 = value1 + "(6005,current_timestamp," + m.getDiastolicbp()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getPulse() != null) {
							value1 = value1 + "(2001,current_timestamp," + m.getPulse()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getRespiration() != null) {
							value1 = value1 + "(2,current_timestamp," + m.getRespiration()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getOxygensaturation() != null) {
							value1 = value1 + "(3,current_timestamp," + m.getOxygensaturation()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}

						if (value1 != "" && value1 != null) {
							value1 = value1.substring(0, value1.length() - 1);
						}

						String value2 = "";

						if (m.getEmername() != null && m.getEmername() != "" && m.getEmercontact() != null
								&& m.getEmercontact() != "" && m.getRelation() != null) {
							value2 = value2 + "(" + pId + "," + m.getRelation() + ",\'" + m.getEmername() + "\',\'"
									+ m.getEmercontact() + "\')";
						}

						String value3 = "";

						if (m.getFamilydoctorname() != null && m.getFamilydoctorname() != ""
								&& m.getFamilydoctorcontactno() != null && m.getFamilydoctorcontactno() != ""
								&& m.getSpeciality() != null) {
							value3 = value3 + "(" + pId + "," + m.getSpeciality() + ",\'" + m.getFamilydoctorname()
									+ "\',\'" + m.getFamilydoctorcontactno() + "\')";
						}

						System.out.println(value1);
						System.out.println(value2);
						System.out.println(value3);

						List<Object[]> x = em.createNamedStoredProcedureQuery("save_patient_registration_by_geo")
								.setParameter("patientid", pId).setParameter("userid", m.getUserid())
								.setParameter("firstname", m.getFirstname()).setParameter("lastname", m.getLastname())
								.setParameter("countryid", m.getCountryid().toString())
								.setParameter("countryname", m.getCountryname())
								.setParameter("stateid", m.getStateid().toString())
								.setParameter("statename", m.getStatename())
								.setParameter("districtid", m.getDistrictid().toString())
								.setParameter("cityid", m.getCityid().toString())
								.setParameter("address", m.getAddress()).setParameter("pincode", m.getPincode())
								.setParameter("mobile", m.getMobile()).setParameter("mail", m.getMailid())
								.setParameter("bloodgroup", m.getBloodgroup()).setParameter("occp", m.getOccupation())
								.setParameter("age", m.getAge().toString()).setParameter("dob", m.getDob())
								.setParameter("maritialstatus", m.getMaritialstatus())
								.setParameter("pancard", m.getPancard()).setParameter("votercard", m.getVotercard())
								.setParameter("aadhaar", m.getAadhaarcard()).setParameter("licenceno", m.getLicenceno())
//								.setParameter("height", data.getHeight())
//								.setParameter("weight", data.getWeight())
//								.setParameter("bmi", data.getBmi())
//								.setParameter("pulse", data.getPulse())
//								.setParameter("respiration", data.getRespiration())
//								.setParameter("systolicbp", data.getSystolicbp())
//								.setParameter("diastolicbp", data.getDiastolicbp())
//								.setParameter("oxygensaturation", data.getOxygensaturation())
//								.setParameter("tempincel", data.getTempincel())
								.setParameter("insertvitalquery", value1)
								.setParameter("qualification", m.getQualification())
								.setParameter("specialization", m.getSpecialization())
//								.setParameter("emername", data.getEmername())
//								.setParameter("emercontact", data.getEmercontact())
//								.setParameter("relation", data.getRelation())
								.setParameter("insertemercontactquery", value2)
//								.setParameter("familydoctorname", data.getFamilydoctorname())
//								.setParameter("familydoctorcontactno", data.getFamilydoctorcontactno())
//								.setParameter("speciality", data.getSpeciality())
								.setParameter("insertfamilydocquery", value3)
								.setParameter("password",
										"$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6")
								.setParameter("gender", gender1).setParameter("hospital", m.getHospitalId().toString())
								.setParameter("qrcode", qrcodename)
								.getResultList();

						jsonResponse.setBody(x.get(0));

					}

					jsonResponse.setCode("success");
					jsonResponse.setMessage("User Registered Successfully");
					
					CommonUsed.generateQRCode(qrcodename, pId, m.getMobile(), m.getFirstname(), env.getUserQrCode());
					
					String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "+pId+" or "+m.getMobile()+" and password is User@123 ";
					
					String encodemsg = URLEncoder.encode(msg, "UTF-8" );
					
					CommonUsed.sendSMS(m.getMobile(), encodemsg);
					
				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Something went wrong. Registration failed.");
				}
			}
		}
//		}
 
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : userRegistrationByGeo Dao ends");
		return response;
	}
	**/
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> userRegistrationByGeo(UserRegistrationGeoModel m) {
		logger.info("Method : userRegistrationByGeo Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
//		for (UserRegistrationGeoModel m : data) {
		if (m.getMobile() == null || m.getMobile() == "") {
			jsonResponse.setMessage("Mobile Number Required");
			validity = false;
		} else if (m.getFirstname() == null || m.getFirstname() == "") {
			jsonResponse.setMessage("Name Required");
			validity = false;
		} else if (m.getAge() == null && m.getDob() == null) {
			jsonResponse.setMessage("Age/DOB Required");
			validity = false;
		} else if (m.getCountryid() == null) {
			jsonResponse.setMessage("Country Required");
			validity = false;
		} else if (m.getStateid() == null) {
			jsonResponse.setMessage("State Required");
			validity = false;
		} else if (m.getDistrictid() == null) {
			jsonResponse.setMessage("District Required");
			validity = false;
		} else if (m.getCityid() == null) {
			jsonResponse.setMessage("City Required");
			validity = false;
		}
//		}

		Boolean isMobileExist = false;

		Boolean isAadharExist = false;

//		for (UserRegistrationGeoModel m : data) {
		if (!isMobileExist && !isAadharExist) {
			if (m.getFirstname() != null && m.getFirstname() != "" && m.getLastname() != null
					&& m.getLastname() != "") {
				String name = m.getFirstname().concat(m.getLastname());
				String noSpaceStr = name.replaceAll("\\s", "");
				String substr = noSpaceStr.substring(0, 3);
				substr = substr.toUpperCase();
				String random = generateRandom(5);
				m.setUserid(substr.concat(random));
			}


			String gender1 = null;

			if (m.getGender() != null) {
				if (m.getGender().toUpperCase().replace(" ", "").equals("MALE")) {
					gender1 = "1";
				} else if (m.getGender().toUpperCase().replace(" ", "").equals("FEMALE")) {
					gender1 = "2";
				} else {
					gender1 = "3";
				}
			}

			if (m.getMaritialstatus() != null) {
				if (m.getMaritialstatus().toUpperCase().replace(" ", "").equals("MARRIED")) {
					gender1 = "1";
				} else if (m.getMaritialstatus().toUpperCase().replace(" ", "").equals("SINGLE")) {
					gender1 = "2";
				} else if (m.getMaritialstatus().toUpperCase().replace(" ", "").equals("WIDOW")) {
					gender1 = "4";
				} else {
					gender1 = "3";
				}
			}

			String pId = null;
			if (m.getPatientid() == null || m.getPatientid() == "") {
				String ccode = "";
				String scode = "";
				if (m.getCountrycode() != null && m.getCountrycode() != "") {
					ccode = m.getCountrycode();
					ccode = ccode.substring(0, 2);
					if (m.getStatecode() != null && m.getStatecode() != "") {
						scode = m.getStatecode();
					} else {
						scode = "99";
					}
				} else {
					ccode = "99";
					scode = "99";
				}

				String random = generateRandom(12);
				pId = ccode.concat(scode).concat(random);
			}
			
			String qrcodename = "QR"+pId+".png";

//			String image = "";
//			if (data.getProfileImage().size() > 0) {
//
//				if (data.getProfileImage().get(0) != null && data.getProfileImage().get(0) != "") {
//					try {
//						byte[] bytes = Base64.getDecoder().decode(data.getProfileImage().get(0));
//						String imageName = saveAllImage(bytes, data.getProfileImageType(), pId);
//						data.setProfileImageName(imageName);
//						image = imageName;
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			String patEmail = "";
//			if (data.getEmail() != null && data.getEmail() != "") {
//				patEmail = data.getEmail();
//			}
//
//			String patAadhar = "";
//			if (data.getAadhar() != null && data.getAadhar() != "") {
//				patAadhar = data.getAadhar();
//			}

			Date date = new Date();
			String reg_date = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String reg_time = new SimpleDateFormat("HH:mm").format(date);

			List<Object[]> x = null;
			
			if (validity) {
				try {
					if (m.getPatientid() == null || m.getPatientid() == "") {

						System.out.println(m);

						String value1 = "";

						if (m.getHeight() != null) {
							value1 = value1 + "(4,current_timestamp," + m.getHeight()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getWeight() != null) {
							value1 = value1 + "(5,current_timestamp," + m.getWeight()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getBmi() != null) {
							value1 = value1 + "(6,current_timestamp," + m.getBmi()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getTempincel() != null) {
							value1 = value1 + "(1,current_timestamp," + m.getTempincel()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getSystolicbp() != null) {
							value1 = value1 + "(4001,current_timestamp," + m.getSystolicbp()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getDiastolicbp() != null) {
							value1 = value1 + "(6005,current_timestamp," + m.getDiastolicbp()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getPulse() != null) {
							value1 = value1 + "(2001,current_timestamp," + m.getPulse()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getRespiration() != null) {
							value1 = value1 + "(2,current_timestamp," + m.getRespiration()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}
						if (m.getOxygensaturation() != null) {
							value1 = value1 + "(3,current_timestamp," + m.getOxygensaturation()
									+ ",P_CASEPAPERNO,current_timestamp,\'" + pId + "\'),";
						}

						if (value1 != "" && value1 != null) {
							value1 = value1.substring(0, value1.length() - 1);
						}

						String value2 = "";

						if (m.getEmername() != null && m.getEmername() != "" && m.getEmercontact() != null
								&& m.getEmercontact() != "" && m.getRelation() != null) {
							value2 = value2 + "(" + pId + "," + m.getRelation() + ",\'" + m.getEmername() + "\',\'"
									+ m.getEmercontact() + "\')";
						}

						String value3 = "";

						if (m.getFamilydoctorname() != null && m.getFamilydoctorname() != ""
								&& m.getFamilydoctorcontactno() != null && m.getFamilydoctorcontactno() != ""
								&& m.getSpeciality() != null) {
							value3 = value3 + "(" + pId + "," + m.getSpeciality() + ",\'" + m.getFamilydoctorname()
									+ "\',\'" + m.getFamilydoctorcontactno() + "\')";
						}

						System.out.println(value1);
						System.out.println(value2);
						System.out.println(value3);

						x = em.createNamedStoredProcedureQuery("save_patient_registration_by_geo")
								.setParameter("patientid", pId).setParameter("userid", m.getUserid())
								.setParameter("firstname", m.getFirstname()).setParameter("lastname", m.getLastname())
								.setParameter("countryid", m.getCountryid().toString())
								.setParameter("countryname", m.getCountryname())
								.setParameter("stateid", m.getStateid().toString())
								.setParameter("statename", m.getStatename())
								.setParameter("districtid", m.getDistrictid().toString())
								.setParameter("cityid", m.getCityid().toString())
								.setParameter("address", m.getAddress()).setParameter("pincode", m.getPincode())
								.setParameter("mobile", m.getMobile()).setParameter("mail", m.getMailid())
								.setParameter("bloodgroup", m.getBloodgroup()).setParameter("occp", m.getOccupation())
								.setParameter("age", m.getAge().toString()).setParameter("dob", m.getDob())
								.setParameter("maritialstatus", m.getMaritialstatus())
								.setParameter("pancard", m.getPancard()).setParameter("votercard", m.getVotercard())
								.setParameter("aadhaar", m.getAadhaarcard()).setParameter("licenceno", m.getLicenceno())
//								.setParameter("height", data.getHeight())
//								.setParameter("weight", data.getWeight())
//								.setParameter("bmi", data.getBmi())
//								.setParameter("pulse", data.getPulse())
//								.setParameter("respiration", data.getRespiration())
//								.setParameter("systolicbp", data.getSystolicbp())
//								.setParameter("diastolicbp", data.getDiastolicbp())
//								.setParameter("oxygensaturation", data.getOxygensaturation())
//								.setParameter("tempincel", data.getTempincel())
								.setParameter("insertvitalquery", value1)
								.setParameter("qualification", m.getQualification())
								.setParameter("specialization", m.getSpecialization())
//								.setParameter("emername", data.getEmername())
//								.setParameter("emercontact", data.getEmercontact())
//								.setParameter("relation", data.getRelation())
								.setParameter("insertemercontactquery", value2)
//								.setParameter("familydoctorname", data.getFamilydoctorname())
//								.setParameter("familydoctorcontactno", data.getFamilydoctorcontactno())
//								.setParameter("speciality", data.getSpeciality())
								.setParameter("insertfamilydocquery", value3)
								.setParameter("password",
										"$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6")
								.setParameter("gender", gender1).setParameter("hospital", m.getHospitalId().toString())
								.setParameter("qrcode", qrcodename)
								.getResultList();

						jsonResponse.setBody(x.get(0));

					}
					
					JSONObject obres = null;

					if (jsonResponse.getBody() != null) {

						if (m.getStateid().toString().contentEquals("35")) {

							// if you need to pass form parameters in request with headers.
							Map<String, String> map = new HashMap<String, String>();
							
							String fullname = m.getFirstname() + " " + m.getLastname();

							map.put("REG_UID", pId);
							map.put("REG_NAME", fullname);
							map.put("REG_PHONE", m.getMobile());
							map.put("REG_DATE", reg_date);
							map.put("REG_TIME", reg_time);
							map.put("REG_CAMP", "");

							map.put("REG_USER", "EHEALTH@NOVI000456");
							map.put("REG_PWD", "Chitnis@eHalth");
							map.put("REG_KEY", "cEWC43748dawCSA@@iihd@@");

							JSONObject obj = new JSONObject(map);

							System.out.println(obj);

							String url = "https://pceterp.com/oraerp/sbpj/req/listenReg.php";

							HttpHeaders requestHeaders = new HttpHeaders();

							requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

							HttpEntity<String> httpEntity = new HttpEntity<String>(obj.toString(), requestHeaders);

							ResponseEntity<String> st = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
									String.class);

							System.out.println(st.getBody());

							obres = new JSONObject(st.getBody());

							System.out.println(obres);
							JSONObject objectresponse = null;
							if (obres != null) {
								objectresponse = obres.getJSONObject("RESP");
							}

							System.out.println(objectresponse);

							if (objectresponse != null) {
								if (objectresponse.getString("TR_STATUS").equals("SUCCESS")) {

									jsonResponse.setCode("success");
									jsonResponse.setMessage("User Registered Successfully");
									
									CommonUsed.generateQRCode(qrcodename, pId, m.getMobile(), m.getFirstname(), env.getUserQrCode());
									
									String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "+pId+" or "+m.getMobile()+" and password is User@123 ";
									
									String encodemsg = URLEncoder.encode(msg, "UTF-8" );
									
									CommonUsed.sendSMS(m.getMobile(), encodemsg);

								}
							}

						} else {
							jsonResponse.setCode("success");
							jsonResponse.setMessage("User Registered Successfully");
							
							CommonUsed.generateQRCode(qrcodename, pId, m.getMobile(), m.getFirstname(), env.getUserQrCode());
							
							String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "+pId+" or "+m.getMobile()+" and password is User@123 ";
							
							String encodemsg = URLEncoder.encode(msg, "UTF-8" );
							
							CommonUsed.sendSMS(m.getMobile(), encodemsg);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Something went wrong. Registration failed.");
				}
			}
		}
//		}
 
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : userRegistrationByGeo Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> geoPostDoctorAppointment(DoctorAppointmentModel data) {
		logger.info("Method : geoPostDoctorAppointment Dao starts");

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
		System.out.println(data.getUserid());
		String casepaper = checkDuplicateDao.getCasePaperNumber(data.getUserid());
		System.out.println(casepaper);
		Boolean isMobileExist = false;

		if (!isMobileExist) {

			String notes = "";
			if (data.getNotes() != null) {
				notes = data.getNotes();
			}

			String hospital = "";
			if (data.getHospitalid() != null) {
				hospital = data.getHospitalid().toString();
			}

			String doctor = "";
			if (data.getDoctor() != null) {
				doctor = data.getDoctor().toString();
			}

			String casep = "";
			if (casepaper != null) {
				casep = casepaper.toString();
			}

			String opdid = "";
			Integer opddata = 0;
			if (data.getOpdid() != null) {
				opdid = data.getOpdid().toString();
				opddata = Integer.parseInt(opdid);
			}

			String date = "";
			if (data.getDate() != null) {
				date = DateFormatter.getStringDateMMM(data.getDate());
			}

			if (validity) {
				try {
					if (data.getUserid() != null || data.getUserid() != "") {

						System.out.println(date);
						System.out.println(casep);
						System.out.println(opdid);
						System.out.println(data.getTime());
						System.out.println(data.getNotes());
						System.out.println(hospital);
						System.out.println(doctor);

						String time = null;
						String ftime = null;
						String type = null;
						String newtime = null;
						if (data.getTime() != null && data.getTime() != "") {
							time = data.getTime();
							time = time.replace(" ", "");
							ftime = time.substring(0, 5);
							type = time.substring(5, 7);
							String fulltime[] = null;
							if (type.equals("PM")) {
								fulltime = ftime.split(":");
								if (fulltime[0].equals("12")) {
									newtime = ftime;
								} else {
									Integer t = Integer.parseInt(fulltime[0]);
									t = t + 12;
									newtime = t.toString().concat(":").concat(fulltime[1]);
								}
							} else {
								newtime = ftime;
							}

							System.out.println(ftime + "   " + type + "   " + newtime + "   " + date);
						}

						List<Object[]> x = em.createNamedStoredProcedureQuery("geo_post_doctor_appointment")
								.setParameter("p_datefrom", date).setParameter("p_caspaper", casep)
								.setParameter("p_opdid", opddata).setParameter("p_timefrom", newtime)
								.setParameter("p_doctor", doctor).setParameter("p_notes", notes)
								.setParameter("p_appid", data.getAppointid().toString())
								.setParameter("p_hospitalname", hospital).getResultList();

						jsonResponse.setBody(x.get(0));

					}

					jsonResponse.setCode("success");
					jsonResponse.setMessage("Appointment Booked Successfully");
				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Something went wrong.");
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : geoPostDoctorAppointment Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> geoUpdateUserAppointmentStatus(APIStatusModel data) {
		logger.info("Method : geoUpdateUserAppointmentStatus DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		if (validity) {
			try {
				
				String status = null;
				
				if(data.getAppstatus() != null && data.getAppstatus() != "") {
					String appstatus = data.getAppstatus();
					if(appstatus.toUpperCase().equals("PENDING")) {
						status = "7";
					} else if(appstatus.toUpperCase().equals("CONFIRM")) {
						status = "2";
					} else if(appstatus.toUpperCase().equals("REJECTED")) {
						status = "4";
					} else {
						status = "5";
					}
				}
				System.out.println(status);
				List<Object[]> x = em.createNamedStoredProcedureQuery("geo_update_appointment_status")
						.setParameter("userid", data.getUserid()).setParameter("appid", data.getAppid())
						.setParameter("appstatus", status).getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Status updated successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : geoUpdateUserAppointmentStatus DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> geoPostUserMedicationDoctor(GEOAPIMedicationModel data) {
		logger.info("Method : geoPostUserMedicationDoctor Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		Double noofdose = 0.0;
		String duration = null;
		@SuppressWarnings("unused")
		String remarks = "";
		for (GEOAPIMedDetailsModel m : data.getChildDetail()) {
			if (m.getMedicineid() == null) {
				jsonResponse.setMessage("Medicine Name Required");
				validity = false;
			}
		}

		if (validity) {
			try {
				if (data.getDoctor() != null) {
					
					String appno = generateRandom(4);

					for (GEOAPIMedDetailsModel m : data.getChildDetail()) {
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
						} else if(m.getMorning().contentEquals("1/2")) {
							noofdose = noofdose + 0.5;
						}
						if (m.getAfternoon().contentEquals("1")) {
							noofdose = noofdose + 1;
						} else if(m.getMorning().contentEquals("1/2")) {
							noofdose = noofdose + 0.5;
						}
						if (m.getEvening().contentEquals("1")) {
							noofdose = noofdose + 1;
						} else if(m.getMorning().contentEquals("1/2")) {
							noofdose = noofdose + 0.5;
						}

						if (m.getDays() != null && m.getDays() != "") {
							duration = m.getDays();
						}

						if (m.getRemarks() != null && m.getRemarks() != "") {
							remarks = m.getRemarks();
						}
						
						String fulldata = null;
						
						if(m.getMedicineadvice() != null) {
							fulldata = m.getMedicineadvice();
							if(m.getRemarks() != null) {
								fulldata.concat(" ").concat(m.getRemarks());
							}
						}
						
						System.out.println("Here");

						List<Object[]> x = em.createNamedStoredProcedureQuery("geo_post_doctor_medication")
								.setParameter("userid", data.getUserid()).setParameter("appno", appno)
								.setParameter("medname", m.getMedicineid().toString()).setParameter("noofdose", noofdose.toString())
								.setParameter("duration", duration).setParameter("remarks", fulldata)
								.setParameter("doctor", data.getDoctor().toString()).setParameter("datamorn", m.getMorning())
								.setParameter("datanoon", m.getAfternoon()).setParameter("dataeven", m.getEvening())
								.setParameter("quantity", m.getQty().toString())
								.setParameter("doctorname", data.getDoctorname())
								.getResultList();

						jsonResponse.setBody(x.get(0));

					}

				}

				jsonResponse.setCode("success");
				jsonResponse.setMessage("Medicine added successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : geoPostUserMedicationDoctor Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> geoPostUserTestByDoctor(GEOAPIMedicationModel data) {
		logger.info("Method : postUgeoPostUserTestByDoctorserTestByDoctor Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		for (GEOAPIMedDetailsModel m : data.getChildDetail()) {
			if (m.getTestgroup() == null || m.getTestgroup() == "") {
				jsonResponse.setMessage("Test Group Required");
				validity = false;
			} else if (m.getTestname() == null || m.getTestname() == "") {
				jsonResponse.setMessage("Test Name Required");
				validity = false;
			}

		}

		if (validity) {
			try {
				if (data.getDoctor() != null) {

//					String casepaper = checkDuplicateDao.getCasePaperNumber(data.get(0).getUserid());

//					String value = "";

					List<Object[]> x = null;
					
					String appno = generateRandom(4);

					for (GEOAPIMedDetailsModel m : data.getChildDetail()) {

//						value = value + "(srlnodata,newcount,casepaper,opddate,opdid,appno,CURRENT_TIMESTAMP,\'"
//								+ data.get(0).getDoctor() + "\',\'" + data.get(0).getRemarks() + "\',1,\'"
//								+ m.getTestgroup() + "\',\'" + m.getTestname() + "\'),";

						x = em.createNamedStoredProcedureQuery("geo_post_doctor_test_details")
								.setParameter("userid", data.getUserid())
								.setParameter("appno", appno)
								.setParameter("doctor", data.getDoctor().toString())
								.setParameter("remarks", m.getRemarks())
								.setParameter("testgroup", m.getTestgroup()).setParameter("testname", m.getTestname())
								.setParameter("doctorname", data.getDoctorname())
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
				jsonResponse.setMessage("Something went wrong.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : geoPostUserTestByDoctor Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> geoPostPharmacyRequestApi(APIPharmacyModel data) {
		logger.info("Method : geoPostPharmacyRequestApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		System.out.println(data);
		try {
			List<Object[]> x = null;
			String value = "";
//			long nowTime = new Date().getTime();
//			String orderid = "ORDR" + generateRandom(5)  + nowTime;
//			System.out.println(orderid);
			String casepaper = checkDuplicateDao.getCasePaperNumber(data.getUserid());
//			if (data.getMeddetails().size() > 0) {
//				for (CountryModel m : data.getMeddetails()) {
//					value = value + "(\'" + data.getOrderid() + "\',\'" + casepaper + "\',\'" + data.getPharmacistid() + "\',srlno,srno,\'" + m.getKey() + "\',\'" + data.getPatientnote()
//							+ "\',0,1,CURRENT_TIMESTAMP),";
//				}
//
//				value = value.substring(0, value.length() - 1);
//			}
//			if (data.getMednamelist().size() > 0) {
//				for (String m : data.getMednamelist()) {
//					value = value + "(\'" + data.getOrderid() + "\',\'" + casepaper + "\',\'" + data.getPharmacistid() + "\',srlno,srno,\'" + m + "\',\'" + data.getPatientnote()
//					+ "\',0,1,CURRENT_TIMESTAMP,"+data.getAppno()+"),";
//				}
//				
//				value = value.substring(0, value.length() - 1);
//			}

			System.out.println(value);
			if (data.getMednamelist().size() > 0) {
				for (String m : data.getMednamelist()) {
					x = em.createNamedStoredProcedureQuery("geo_post_pharmacy_request_byuser")
							.setParameter("orderid", data.getOrderid())
							.setParameter("appno", data.getAppno().toString())
							.setParameter("pharmid", data.getPharmacistid().toString()).setParameter("medname", m)
							.setParameter("pnote", data.getPatientnote()).setParameter("userid", casepaper)
							.getResultList();
				}
			}
			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Medicine requested successfully.");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Something went wrong.");
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", "Something went wrong.");
		}

		logger.info("Method : geoPostPharmacyRequestApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
}
