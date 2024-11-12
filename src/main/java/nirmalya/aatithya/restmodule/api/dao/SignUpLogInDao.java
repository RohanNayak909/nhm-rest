package nirmalya.aatithya.restmodule.api.dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.core.net.SyslogOutputStream;
import nirmalya.aatithya.restmodule.api.model.APIActivityLogModel;
import nirmalya.aatithya.restmodule.api.model.APIEmergencyModel;
import nirmalya.aatithya.restmodule.api.model.APIOrganizationModel;
import nirmalya.aatithya.restmodule.api.model.APITestDropdownModel;
import nirmalya.aatithya.restmodule.api.model.ApiUserModel;
import nirmalya.aatithya.restmodule.api.model.CatagoryModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.OtherUsersProfileModel;
import nirmalya.aatithya.restmodule.api.model.PatientDetailsAPI;
import nirmalya.aatithya.restmodule.api.model.PatientFamilyDetailsModel;
import nirmalya.aatithya.restmodule.api.model.SignUpModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileAPIModel;
import nirmalya.aatithya.restmodule.api.model.UsernameModel;
import nirmalya.aatithya.restmodule.api.model.VersionModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.security.config.JwtTokenUtil;
import nirmalya.aatithya.restmodule.service.JwtUserDetailsService;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.GenerateRandomValue;
import nirmalya.aatithya.restmodule.util.PushNotification;
import nirmalya.aatithya.restmodule.util.SMSGatewayMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;
import java.net.InetAddress;


@Repository
public class SignUpLogInDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;
	CommonUsed commonUsed = new CommonUsed();
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

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ApiUserModel>> getLoginDetails(String mobileNo, String password) {
		logger.info("Method : getLoginDetails starts"+mobileNo +" "+password);

		JsonResponse<ApiUserModel> jsonResponse = new JsonResponse<ApiUserModel>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		List<ApiUserModel> userArray = new ArrayList<ApiUserModel>();
		List<String> userRole = new ArrayList<String>();
String userStatus="";
String loginTypeStatus="";
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("getLogInDetails")
					.setParameter("p_username", mobileNo).getResultList();
			System.out.println("userfvgbhk"+x);
			for (Object[] m : x) {
				String role = (String) m[6];
				//System.out.println("!@#$%T^%$#@#$%$#@#$%^%$#@#@$%^%$#@#$%^^%$#"+role);
				if (role != null && role.length() > 0) {
					String[] roles = role.split(",");
					userRole = Arrays.asList(roles);
				}

				Boolean boolean1 = false;
				if (m[5].toString() != null) {
					String data = m[5].toString();
					if (data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}

				// boolean b1=Boolean.getBoolean((String) m[11]);
				ApiUserModel user = new ApiUserModel(m[0].toString(), m[1], m[2], m[3], m[4], null, null, null, null,
						null, null, boolean1, null, null, userRole, m[7], m[8], m[9], m[10], m[11], null, null, m[12],m[13]);
				userArray.add(user);
				userArray.add(user);
				userStatus=user.getUserStatus().toString();
				loginTypeStatus=user.getLoginType();
				System.out.println("user################33"+user);
				System.out.println("!@#$%^&*(&^%$#@!@#$%^&loginTypeStatus!@#$%^&*((0-"+loginTypeStatus);
				System.out.println("!@#$%^&*(&^%$#@!@#$%^&loginTypeStatus!@#$%^&*((0-"+userStatus);
				
			}
			final UserDetails userDetails = userDetailsService.loadUserByUsername(mobileNo);

			logger.info("Method : getLoginDetails ends");
			if (userArray.size() > 0) {
			//	userArray.get(0).setToken("Bearer " + jwtTokenUtil.generateToken(userDetails));
				if (passEncoder.matches(password, userArray.get(0).getUserPassword())) {
					jsonResponse.setBody(userArray.get(0));
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Password is incorrect");

					ResponseEntity<JsonResponse<ApiUserModel>> response = new ResponseEntity<JsonResponse<ApiUserModel>>(
							jsonResponse, HttpStatus.OK);
					logger.info("Method : getLoginDetails ends");

					return response;
				}
				 if(userStatus.equals("false")){
					 System.out.println("false");
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("You Are Not Registered");
					
				}
				 
				 
				 else {
					System.out.println("true");
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
				}
				 
				 if(!"1".equals(loginTypeStatus)) {
					 System.out.println("userType");
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("You Are Not a Junior Engineer");
						
				 }else {
						System.out.println("true");
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Data Fetched Successfully");
						}
				 
//				 if("2".equals(loginTypeStatus)) {
//					 System.out.println("userType");
//						jsonResponse.setCode("failed");
//						jsonResponse.setMessage("You Are Not eligible to login");
//						
//				 }else {
//						System.out.println("true");
//						jsonResponse.setCode("success");
//						jsonResponse.setMessage("Data Fetched Successfully");
//						}
			
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Mobile number is incorrect");
		}

		ResponseEntity<JsonResponse<ApiUserModel>> response = new ResponseEntity<JsonResponse<ApiUserModel>>(
				jsonResponse, HttpStatus.OK);
		System.out.println("response"+response);
		logger.info("Method : getLoginDetails ends"+response);

		return response;

	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ApiUserModel>> getLoginDetailsByMob(String mobileNo,String otpKey) {
		logger.info("Method : getLoginDetailsByMob starts");

		JsonResponse<ApiUserModel> jsonResponse = new JsonResponse<ApiUserModel>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		List<ApiUserModel> userArray = new ArrayList<ApiUserModel>();
		List<String> userRole = new ArrayList<String>();
String userName="";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("getLogInDetails")
					.setParameter("p_username", mobileNo).getResultList();

			for (Object[] m : x) {
				String role = (String) m[6];

				if (role != null && role.length() > 0) {
					String[] roles = role.split(",");
					userRole = Arrays.asList(roles);
				}

				Boolean boolean1 = false;
				if (m[5].toString() != null) {
					String data = m[5].toString();
					if (data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}

				// boolean b1=Boolean.getBoolean((String) m[11]);
				ApiUserModel user = new ApiUserModel(m[0].toString(), m[1], m[2], m[3], m[4], null, null, null, null,
						null, null, boolean1, null, null, userRole, m[7], m[8], m[9], m[10], m[11], null, null, null,null);
				userArray.add(user);
				System.out.println("user"+user);
				userName=user.getUserName();
			}
			final UserDetails userDetails = userDetailsService.loadUserByUsername(mobileNo);

			logger.info("Method : getLoginDetails ends");
			if (userArray.size() > 0) {
				userArray.get(0).setToken("Bearer " + jwtTokenUtil.generateToken(userDetails));
				jsonResponse.setBody(userArray.get(0));
				String otp = generateRandom(4);
				userArray.get(0).setOtp(otp);
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");

				//String msg = "Welcome to cureEZ. Your OTP is " + otp;
				String msg = userName;
				String patientName= userName;
				String encodemsg = URLEncoder.encode(msg, "UTF-8");
				System.out.println("msg" + msg);
				System.out.println("encodemsg" + encodemsg);
				//SignUpLogInDao.sendSMSCureEz(otp, mobileNo);
				//String smsId=CommonUsed.sendSMSs(otp, mobileNo);
				
				//String message = SMSGatewayMessage.OTP.replace("{name}", "Rasmita").replace("{otp}", otp).replace("{verify}", "Rasmita1");
				String message = SMSGatewayMessage.OTP.replace("{name}", patientName).replace("{otp}", otp).replace("{verify}", otpKey);
				
				try {
					String responsecode = commonUsed.sendCureezSmss(mobileNo,message);
					System.out.println("msgIdddddddddddddd"+responsecode);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Mobile number is incorrect");
		}

		ResponseEntity<JsonResponse<ApiUserModel>> response = new ResponseEntity<JsonResponse<ApiUserModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getLoginDetailsByMob ends"+response);

		return response;

	}

	
	

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getCountryList() {
		logger.info("Method : getCountryList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getCountryList").getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], m[2].toString());
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getCountryList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getUserTitleList() {
		logger.info("Method : getUserTitleList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_title_list").getResultList();
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
		logger.info("Method : getUserTitleList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getHospitalList() {
		logger.info("Method : getHospitalList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_hospital_for_doc_app").getResultList();
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
		logger.info("Method : getHospitalList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getGenderList() {
		logger.info("Method : getGenderList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getGenderList").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getGenderList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getBloodGroupList() {
		logger.info("Method : getBloodGroupList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_bloodGroup_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
					ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getBloodGroupList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getStateList(Integer country) {
		logger.info("Method : getStateList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_state_list").setParameter("country", country)
					.getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], m[2].toString());
				countryList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
					ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getStateList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getDistrictList(Integer state) {
		logger.info("Method : getDistrictList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_district_list")
					.setParameter("stateId", state.toString()).getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				countryList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
					ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getDistrictList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getCityList(Integer district) {
		logger.info("Method : getCityList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_city_list")
					.setParameter("districtId", district.toString()).getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				countryList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
					ApiResponseMessage.UNKNOWN_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getCityList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getHospitalList(String doctor) {
		logger.info("Method : getHospitalList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("hospital_for_appointment")
					.setParameter("doctor", doctor).getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				countryList.add(dropDownModel);
			}

			if (countryList.size() > 0) {
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.failed,
						ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getHospitalList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> organizationList() {
		logger.info("Method : organizationList Dao starts");

		List<CountryModel> organizationList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_all_organization_list").getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				organizationList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, organizationList, ResponseStatus.success,
					ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : organizationList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorSpecialityList() {
		logger.info("Method : getDoctorSpecialityList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_speciality_list").setParameter("roleid", 2)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getDoctorSpecialityList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getHealthProviderList() {
		logger.info("Method : getHealthProviderList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("health_provider_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getHealthProviderList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getRelationList() {
		logger.info("Method : getRelationList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_emercontact_drpdwn_details")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getRelationList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPharmacyList() {
		logger.info("Method : getPharmacyList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("pharmacy_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPharmacyList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getAdmEquipmentList() {
		logger.info("Method : getAdmEquipmentList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_bname_drpdwn_details").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getAdmEquipmentList Dao ends");
		return response;
	}

	// get AllergyName List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getallergyNameListDao() {
		logger.info("Method : allergyNameList Dao starts");

		List<DropDownModel> allergyNameList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_allername_drpdwn_details")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				allergyNameList.add(dropDownModel);
			}
			jsonResponse.setBody(allergyNameList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : allergyNameList Dao ends");
		return response;
	}

	// get AllergyType List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getallergyTypeListDao() {
		logger.info("Method : getallergyTypeList Dao starts");

		List<DropDownModel> allergyTypeList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_allertype_drpdwn_details")
//					.setParameter("userid",userid)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				allergyTypeList.add(dropDownModel);
			}
			jsonResponse.setBody(allergyTypeList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : allergyNameList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getDoctorList(Integer type, Integer city) {
		logger.info("Method : getDoctorList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_doctor_list")
					.setParameter("specialistId", type.toString()).setParameter("cityId", city.toString())
					.getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getDoctorList Dao ends");
		return response;
	}

	/*
	 * @SuppressWarnings("unchecked") public ResponseEntity<JsonResponse<Object>>
	 * signUpByPathologistDao(SignUpModel data) {
	 * logger.info("Method : signUpByPathologistDao Dao starts");
	 * 
	 * JsonResponse<Object> jsonResponse = new JsonResponse<Object>(); //
	 * JsonResponse<Object> jsonResponse1 = new JsonResponse<Object>();
	 * 
	 * Boolean validity = true; if (data.getMobile() == null || data.getMobile() ==
	 * "") { jsonResponse.setMessage("Mobile Number Required"); validity = false; }
	 * else if (data.getfName() == null || data.getfName() == "") {
	 * jsonResponse.setMessage("Name Required"); validity = false; } else if
	 * (data.getAge() == null) { jsonResponse.setMessage("Age Required"); validity =
	 * false; } else if (data.getCountry() == null) {
	 * jsonResponse.setMessage("Country Required"); validity = false; } else if
	 * (data.getState() == null) { jsonResponse.setMessage("State Required");
	 * validity = false; } else if (data.getDistrictid() == null) {
	 * jsonResponse.setMessage("District Required"); validity = false; } else if
	 * (data.getCityid() == null) { jsonResponse.setMessage("City Required");
	 * validity = false; } else if (data.getHeight() == null) {
	 * jsonResponse.setMessage("Height Required"); validity = false; } else if
	 * (data.getWeight() == null) { jsonResponse.setMessage("Weight Required");
	 * validity = false; } Boolean isMobileExist = false;
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
	 * data.getStateCode(); } else { scode = "99"; } } else { ccode = "99"; scode =
	 * "99"; }
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
	 * { e.printStackTrace(); } } } String patEmail = ""; if (data.getEmail() !=
	 * null && data.getEmail() != "") { patEmail = data.getEmail(); }
	 * 
	 * String patAadhar = ""; if (data.getAadhar() != null && data.getAadhar() !=
	 * "") { patAadhar = data.getAadhar(); }
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
	 * x = em.createNamedStoredProcedureQuery("registerPatientByPathologist")
	 * .setParameter("regId", pId).setParameter("userId", data.getUserId())
	 * .setParameter("patName", data.getfName()).setParameter("patMobile",
	 * data.getMobile()) .setParameter("profileImage", image).setParameter("patAge",
	 * data.getAge()) .setParameter("patGender",
	 * data.getGender()).setParameter("state", data.getState())
	 * .setParameter("country", data.getCountry()).setParameter("height",
	 * data.getHeight()) .setParameter("weight",
	 * data.getWeight()).setParameter("patEmail", patEmail) .setParameter("aadhar",
	 * patAadhar) .setParameter("password",
	 * "$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6")
	 * .setParameter("enteredBy", data.getEnteredBy()) .setParameter("dist",
	 * data.getDistrictid()).setParameter("city", data.getCityid())
	 * .setParameter("qrcode", qrcodename).getResultList();
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
	 * String camp = null;
	 * 
	 * try { camp = checkDuplicateDao.getLTPHCLocation(data.getEnteredBy()); } catch
	 * (Exception e) { camp = null; e.printStackTrace(); }
	 * 
	 * // if you need to pass form parameters in request with headers. Map<String,
	 * String> map = new HashMap<String, String>();
	 * 
	 * map.put("REG_UID", pId); map.put("REG_NAME", data.getfName());
	 * map.put("REG_PHONE", data.getMobile()); map.put("REG_DATE", reg_date);
	 * map.put("REG_TIME", reg_time); map.put("REG_CAMP", camp);
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
	 * jsonResponse.setCode("success");
	 * jsonResponse.setMessage("User Registered Successfully");
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
	 * env.getUserQrCode());
	 * 
	 * } }
	 * 
	 * } else { jsonResponse.setCode("success");
	 * jsonResponse.setMessage("User Registered Successfully");
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
	 * } catch (Exception e) { e.printStackTrace(); jsonResponse.setCode("failed");
	 * jsonResponse.setMessage("Something went wrong. Registration failed."); } } }
	 * 
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK);
	 * 
	 * logger.info("Method : signUpByPathologistDao Dao ends"); return response; }
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> signUpByPathologistDao(SignUpModel data) {
		logger.info("Method : signUpByPathologistDao Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (StringUtil.isNull(data.getMobile())) {
			jsonResponse.setMessage("Mobile Number Required");
			validity = false;
		} else if (StringUtil.isNull(data.getfName())) {
			jsonResponse.setMessage("Name Required");
			validity = false;
		} else if (StringUtil.isNull(data.getAge())) {
			jsonResponse.setMessage("Age Required");
			validity = false;
		} else if (StringUtil.isNull(data.getCountry())) {
			jsonResponse.setMessage("Country Required");
			validity = false;
		} else if (StringUtil.isNull(data.getState())) {
			jsonResponse.setMessage("State Required");
			validity = false;
		} else if (StringUtil.isNull(data.getDistrictid())) {
			jsonResponse.setMessage("District Required");
			validity = false;
		} else if (StringUtil.isNull(data.getCityid())) {
			jsonResponse.setMessage("City Required");
			validity = false;
		} else if (StringUtil.isNull(data.getHeight())) {
			jsonResponse.setMessage("Height Required");
			validity = false;
		} else if (StringUtil.isNull(data.getWeight())) {
			jsonResponse.setMessage("Weight Required");
			validity = false;
		}
		Boolean isMobileExist = false;

		Boolean isAadharExist = false;

		String password = GenerateRandomValue.generateAlphanumericWithSpecialCharacter(8);
		logger.info("password===" + password);
		String encodedPassword = null;
		if (!StringUtil.isNull(password)) {
			encodedPassword = passEncoder.encode(password);
			logger.info("encodedPassword===" + encodedPassword);
		}

		if (!isMobileExist && !isAadharExist) {
			if (!StringUtil.isNull(data.getfName())) {
				String noSpaceStr = data.getfName().replaceAll("\\s", "");
				String substr = noSpaceStr.substring(0, 3);
				substr = substr.toUpperCase();
				String random = GenerateRandomValue.generateRandom(5);
				data.setUserId(substr.concat(random));
			}
			String pId = null;
			if (StringUtil.isNull(data.getId())) {
				String ccode = "";
				String scode = "";
				if (!StringUtil.isNull(data.getCountryCode())) {
					ccode = data.getCountryCode();
					ccode = ccode.substring(0, 2);
					if (!StringUtil.isNull(data.getStateCode())) {
						scode = data.getStateCode();
					} else {
						scode = "99";
					}
				} else {
					ccode = "99";
					scode = "99";
				}

				String random = GenerateRandomValue.generateRandom(12);
				pId = ccode.concat(scode).concat(random);
			}

			String image = "";
			if (data.getProfileImage().size() > 0) {
				if (!StringUtil.isNull(data.getProfileImage().get(0))) {
					try {
						byte[] bytes = Base64.getDecoder().decode(data.getProfileImage().get(0));
						String imageName = saveAllImage(bytes, data.getProfileImageType(), pId);
						data.setProfileImageName(imageName);
						image = imageName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			String patEmail = null;
			if (!StringUtil.isNull(data.getEmail())) {
				patEmail = data.getEmail();
			}

			String patAadhar = null;
			if (!StringUtil.isNull(data.getAadhar())) {
				patAadhar = data.getAadhar();
			}

			String qrcodename = "QR" + pId + ".png";

			Date date = new Date();
			String reg_date = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String reg_time = new SimpleDateFormat("HH:mm").format(date);

			DropDownModel responseObject = new DropDownModel();

			List<Object[]> x = null;

			if (validity) {
				try {
					if (StringUtil.isNull(data.getId())) {

						x = em.createNamedStoredProcedureQuery("registerPatientByPathologist")
								.setParameter("regId", pId).setParameter("userId", data.getUserId())
								.setParameter("patName", data.getfName()).setParameter("patMobile", data.getMobile())
								.setParameter("profileImage", image).setParameter("patAge", data.getAge())
								.setParameter("patGender", data.getGender()).setParameter("state", data.getState())
								.setParameter("country", data.getCountry()).setParameter("height", data.getHeight())
								.setParameter("weight", data.getWeight()).setParameter("patEmail", patEmail)
								.setParameter("aadhar", patAadhar).setParameter("password", encodedPassword)
								.setParameter("enteredBy", data.getEnteredBy())
								.setParameter("dist", data.getDistrictid()).setParameter("city", data.getCityid())
								.setParameter("qrcode", qrcodename).getResultList();

						jsonResponse.setBody(x.get(0));

					}

					JSONObject obres = null;
					if (!StringUtil.isNull(jsonResponse.getBody())) {

						responseObject.setKey(jsonResponse.getBody().toString());
						responseObject.setName(password);

						if (data.getState().toString().contentEquals("35")) {

							String camp = null;

							try {
								camp = checkDuplicateDao.getLTPHCLocation(data.getEnteredBy());
							} catch (Exception e) {
								camp = null;
								e.printStackTrace();
							}

							// if you need to pass form parameters in request with headers.
							Map<String, String> map = new HashMap<String, String>();

							map.put("REG_UID", pId);
							map.put("REG_NAME", data.getfName());
							map.put("REG_PHONE", data.getMobile());
							map.put("REG_DATE", reg_date);
							map.put("REG_TIME", reg_time);
							map.put("REG_CAMP", camp);

							map.put("REG_USER", "EHEALTH@NOVI000456");
							map.put("REG_PWD", "Chitnis@eHalth");
							map.put("REG_KEY", "cEWC43748dawCSA@@iihd@@");

							JSONObject obj = new JSONObject(map);

							String url = "https://pceterp.com/oraerp/sbpj/req/listenReg.php";

							HttpHeaders requestHeaders = new HttpHeaders();

							requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

							HttpEntity<String> httpEntity = new HttpEntity<String>(obj.toString(), requestHeaders);

							ResponseEntity<String> st = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
									String.class);

							obres = new JSONObject(st.getBody());

							JSONObject objectresponse = null;
							if (!StringUtil.isNull(obres)) {
								objectresponse = obres.getJSONObject("RESP");
							}

							if (!StringUtil.isNull(objectresponse)) {
								if (objectresponse.getString("TR_STATUS").equals("SUCCESS")) {

									Util.setJsonResponse(jsonResponse, responseObject, ResponseStatus.success,
											ApiResponseMessage.USER_SAVED_SUCCESSFULLY);

									String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
											+ pId + " or " + data.getMobile() + " and password is " + password;

									String encodemsg = URLEncoder.encode(msg, "UTF-8");

									CommonUsed.sendSMS(data.getMobile(), encodemsg);

									CommonUsed.generateQRCode(qrcodename, pId, data.getMobile(), data.getfName(),
											env.getUserQrCode());

								}
							}

						} else {
							Util.setJsonResponse(jsonResponse, responseObject, ResponseStatus.success,
									ApiResponseMessage.USER_SAVED_SUCCESSFULLY);

							String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
									+ pId + " or " + data.getMobile() + " and password is " + password;

							String encodemsg = URLEncoder.encode(msg, "UTF-8");

							CommonUsed.sendSMS(data.getMobile(), encodemsg);

							CommonUsed.generateQRCode(qrcodename, pId, data.getMobile(), data.getfName(),
									env.getUserQrCode());
						}
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

		logger.info("Method : signUpByPathologistDao Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<SignUpModel>>> getPatientRegistrationList(String userid) {
		logger.info("Method : getPatientRegistrationList Dao starts");

		List<SignUpModel> countryList = new ArrayList<SignUpModel>();
		JsonResponse<List<SignUpModel>> jsonResponse = new JsonResponse<List<SignUpModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getPatientRegistrationList")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				String gender = null;
				String email = null;
				String aadhar = null;

				if (m[5] != null) {
					if (m[5].toString().contentEquals("1")) {
						gender = "Male";
					} else if (m[5].toString().contentEquals("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				if (m[9] == null || m[9] == "") {
					email = "N/A";
				} else {
					email = m[9].toString();
				}
				if (m[10] == null || m[10] == "") {
					aadhar = "N/A";
				} else {
					aadhar = m[10].toString();
				}

				String imgUrl = null;

				if (m[11] != null && m[11] != "" && !m[11].toString().contentEquals("null")) {
					imgUrl = env.getBaseURL() + "cureeazyrest/document/profile/" + m[11].toString();
				}

				SignUpModel dropDownModel = new SignUpModel(m[0], m[1], m[2], m[3], m[4], gender, m[6], m[7], m[8],
						email, aadhar, imgUrl, null, null, null);
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
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
		}
		ResponseEntity<JsonResponse<List<SignUpModel>>> response = new ResponseEntity<JsonResponse<List<SignUpModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getPatientRegistrationList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<UserProfileAPIModel>> getPatientDetailsRestApi(String userid) {
		logger.info("Method : getPatientDetailsRestApi Dao starts");
		List<UserProfileAPIModel> profileData = new ArrayList<UserProfileAPIModel>();
		List<APIEmergencyModel> emergencyList = new ArrayList<APIEmergencyModel>();
		List<APIEmergencyModel> familyDoctorList = new ArrayList<APIEmergencyModel>();
		List<PatientFamilyDetailsModel> familyDetailsList = new ArrayList<PatientFamilyDetailsModel>();
		JsonResponse<UserProfileAPIModel> jsonResponse = new JsonResponse<UserProfileAPIModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getPatientDetailsAPI").setParameter("userid", userid)
					.getResultList();

			for (Object[] m : x) {

				String imgUrl = null;

				if (m[1] != null && m[1] != "" && !m[1].toString().contentEquals("null")
						&& !m[1].toString().equals("")) {
					imgUrl = env.getBaseURL() + "cureeazyrest/document/profile/" + m[1].toString();
				}

				String fullname = null;
				if (m[8] != null) {
					String lastname = m[8].toString();
					String firstname = m[7].toString();
					String title = null;
					if (m[6] != null) {
						title = m[6].toString();
						fullname = title.concat(" ").concat(firstname).concat(" ").concat(lastname);
					} else {
						fullname = firstname.concat(" ").concat(lastname);
					}

				} else {
					String title = null;
					if (m[6] != null) {
						title = m[6].toString();
						fullname = title.concat(" ").concat(m[7].toString());
					} else {
						fullname = m[7].toString();
					}
				}
				String dob = null;
				if (m[2] != null && m[2] != "") {
					dob = DateFormatter.returnStringDateNew(m[2].toString());
				}
				String gender = null;
				if (m[4] != null) {
					if (m[4].toString().toUpperCase().equals("MALE")) {
						gender = "1";
					} else if (m[4].toString().toUpperCase().equals("FEMALE")) {
						gender = "2";
					} else {
						gender = "3";
					}
				}
				UserProfileAPIModel dropDownModel = new UserProfileAPIModel(m[0], imgUrl, fullname, dob, m[3], m[4],
						m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16], m[17], m[18],
						m[19], m[20], m[21], m[22], m[23], m[24], m[25], m[26], m[27], m[28], m[29], m[30], m[31],
						gender, m[32]);
				profileData.add(dropDownModel);
			}
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_user_emergency_list_api")
						.setParameter("userid", userid).getResultList();
				for (Object[] m : x1) {
					APIEmergencyModel dd = new APIEmergencyModel(m[0], m[1], m[2], m[3], m[4]);
					emergencyList.add(dd);
				}
			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			try {
				List<Object[]> x2 = em.createNamedStoredProcedureQuery("get_user_family_doctor_list_api")
						.setParameter("userid", userid).getResultList();
				for (Object[] m : x2) {
					APIEmergencyModel dd = new APIEmergencyModel(m[0], m[1], m[2], m[3], m[4]);
					familyDoctorList.add(dd);
				}
			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			try {
				List<Object[]> x3 = em.createNamedStoredProcedureQuery("get_patient_family_details_api")
						.setParameter("userid", userid).getResultList();
				for (Object[] m : x3) {
					PatientFamilyDetailsModel dd = new PatientFamilyDetailsModel(m[0], m[1], m[2], m[3], m[4], m[5]);
					familyDetailsList.add(dd);
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			profileData.get(0).setEmergenceList(emergencyList);
			profileData.get(0).setFamilyDoctorList(familyDoctorList);
			profileData.get(0).setFamilyDetailsList(familyDetailsList);

			if (profileData.size() > 0) {

				Util.setJsonResponse(jsonResponse, profileData.get(0), ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);

			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<UserProfileAPIModel>> response = new ResponseEntity<JsonResponse<UserProfileAPIModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getPatientDetailsRestApi Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<OtherUsersProfileModel>> othersUserProfile(String userid) {
		logger.info("Method : othersUserProfile Dao starts");

		List<OtherUsersProfileModel> countryList = new ArrayList<OtherUsersProfileModel>();
		JsonResponse<OtherUsersProfileModel> jsonResponse = new JsonResponse<OtherUsersProfileModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("other_users_profile").setParameter("userid", userid)
					.getResultList();
			for (Object[] m : x) {

				String profileimg = null;
				if (m[3] != null && m[3] != "" && m[3] != " " && !m[3].toString().equals(" ")
						&& !m[3].toString().equals(null) && !m[3].toString().equals("")) {
					profileimg = env.getBaseURL() + "cureeazyrest/document/profile/" + m[3].toString();
				} else {
					profileimg = "";
				}

				String dob = null;
				if (m[6] != null && m[6] != "") {
					dob = DateFormatter.returnStringDateNew(m[6].toString());
				} else {
					dob = "";
				}

				String email = null;
				if (m[7] != null && m[7] != "") {
					email = m[7].toString();
				} else {
					email = "";
				}

				String state = null;
				if (m[8] != null && m[8] != "") {
					state = m[8].toString();
				} else {
					state = "";
				}

				String country = null;
				if (m[9] != null && m[9] != "") {
					country = m[9].toString();
				} else {
					country = "";
				}

				String edu = null;
				if (m[10] != null && m[10] != "") {
					edu = m[10].toString();
				} else {
					edu = "";
				}

				String aadhaar = null;
				if (m[13] != null && m[13] != "") {
					aadhaar = m[13].toString();
				} else {
					aadhaar = "";
				}

				String ima = null;
				if (m[14] != null && m[14] != "") {
					ima = m[14].toString();
				} else {
					ima = "";

				}

				String pancard = null;
				if (m[15] != null && m[15] != "") {
					pancard = m[15].toString();
				} else {
					pancard = "";
				}

				String passport = null;
				if (m[16] != null && m[16] != "") {
					passport = m[16].toString();
				} else {
					passport = "";
				}

				String votercard = null;
				if (m[17] != null && m[17] != "") {
					votercard = m[17].toString();
				} else {
					votercard = "";
				}

				String licence = null;
				if (m[18] != null && m[18] != "") {
					licence = m[18].toString();
				} else {
					licence = "";
				}

				String licenceauth = null;
				if (m[23] != null && m[23] != "") {
					licenceauth = m[23].toString();
				} else {
					licenceauth = "";
				}
				String experience = null;
				if (m[25] != null && m[25] != "") {
					experience = m[25].toString();
				} else {
					experience = "";
				}

				String mergedAddress = null;
				if (m[26] != null && m[26] != "") {
					mergedAddress = m[26].toString();
				} else {
					mergedAddress = "";
				}

				String fileName = null;
				if (m[27] != null && m[27] != "") {
					fileName = env.getBaseURL() + "cureeazyrest/document/profile/" + m[27].toString();
					// env.getBaseURL()+"nirmalyaRest/document/profile/"+m[27].toString();
				} else {
					fileName = "";
				}
				String bldgrid = null;
				if (m[20] != null && m[20] != "") {
					bldgrid = m[20].toString();
				} else {
					bldgrid = "";
				}
				String bldgrname = null;
				if (m[22] != null && m[22] != "") {
					bldgrname = m[22].toString();
				} else {
					bldgrname = "";
				}

				String gender = null;
				if (m[4] != null && m[4] != "") {
					gender = m[4].toString();
				} else {
					gender = "";
				}

				String mobile = null;
				if (m[5] != null && m[5] != "") {
					mobile = m[5].toString();
				} else {
					mobile = "";
				}

				String speciality = null;
				if (m[11] != null && m[11] != "") {
					speciality = m[11].toString();
				} else {
					speciality = "";
				}

				String org = null;
				if (m[12] != null && m[12] != "") {
					org = m[12].toString();
				} else {
					org = "";
				}

				String role = null;
				if (m[19] != null && m[19] != "") {
					role = m[19].toString();
				} else {
					role = "";
				}

				String gendername = null;
				if (m[21] != null && m[21] != "") {
					gendername = m[21].toString();
				} else {
					gendername = "";
				}

				String address = null;
				if (m[24] != null && m[24] != "") {
					address = m[24].toString();
				} else {
					address = "";
				}

				String city = null;
				if (m[28] != null && m[28] != "") {
					city = m[28].toString();
				} else {
					city = "";
				}

				String dist = null;
				if (m[29] != null && m[29] != "") {
					dist = m[29].toString();
				} else {
					dist = "";
				}

				String countryname = null;
				if (m[30] != null && m[30] != "") {
					countryname = m[30].toString();
				} else {
					countryname = "";
				}

				String statename = null;
				if (m[31] != null && m[31] != "") {
					statename = m[31].toString();
				} else {
					statename = "";
				}

				String distname = null;
				if (m[32] != null && m[32] != "") {
					distname = m[32].toString();
				} else {
					distname = "";
				}

				String cityname = null;
				if (m[33] != null && m[33] != "") {
					cityname = m[33].toString();
				} else {
					cityname = "";
				}

				String pincode = null;
				if (m[34] != null && m[34] != "") {
					pincode = m[34].toString();
				} else {
					pincode = "";
				}

				OtherUsersProfileModel dropDownModel = new OtherUsersProfileModel(m[0], m[1], m[2], profileimg, gender,
						mobile, dob, email, state, country, edu, speciality, org, aadhaar, ima, pancard, passport,
						votercard, licence, role, bldgrid, gendername, bldgrname, licenceauth, address, experience,
						mergedAddress, fileName, city, dist, countryname, statename, distname, cityname, pincode);

				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList.get(0));
			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data fetched successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<OtherUsersProfileModel>> response = new ResponseEntity<JsonResponse<OtherUsersProfileModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : othersUserProfile Dao ends");
		return response;
	}

	// get Ambulance List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> ambulanceList() {
		logger.info("Method : getAmbulanceList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ambulance_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data fetched successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getAmbulanceList Dao ends");
		return response;
	}

	// get Bloodbank List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> bloodbankList() {
		logger.info("Method : getBloodbankList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("bloodbank_list").getResultList();
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
		logger.info("Method : getBloodbankList Dao ends");
		return response;
	}

	// get NGO List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> ngoList() {
		logger.info("Method : getNgoList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ngo_list").getResultList();
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
		logger.info("Method : getNgoList Dao ends");
		return response;
	}

	// get Pathology Lab list
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getpathologylabList() {
		logger.info("Method : getpathologylabList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("pathology_lab_list").getResultList();
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
		logger.info("Method : getpathologylabList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APITestDropdownModel>>> gettestNameList() {
		logger.info("Method : gettestNameList Dao starts");

		List<APITestDropdownModel> testnameList = new ArrayList<APITestDropdownModel>();
		JsonResponse<List<APITestDropdownModel>> jsonResponse = new JsonResponse<List<APITestDropdownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("test_name_list").getResultList();
			for (Object[] m : x) {
				APITestDropdownModel dropDownModel = new APITestDropdownModel(m[0], m[1], m[2], m[3], m[4]);
				testnameList.add(dropDownModel);
			}
			jsonResponse.setBody(testnameList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data fetched successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APITestDropdownModel>>> response = new ResponseEntity<JsonResponse<List<APITestDropdownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : gettestNameList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getOrganList() {
		logger.info("Method : getOrganList Dao starts");

		List<DropDownModel> testnameList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("organ_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				testnameList.add(dropDownModel);
			}
			jsonResponse.setBody(testnameList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getOrganList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getTissueList() {
		logger.info("Method : getTissueList Dao starts");

		List<DropDownModel> testnameList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("tissue_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				testnameList.add(dropDownModel);
			}
			jsonResponse.setBody(testnameList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getTissueList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getOTPForFOrgotPassword(DropDownModel data) {
		logger.info("Method : getOTPForFOrgotPassword Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getKey() == null || data.getKey() == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("UHID or Mobile Number Required");
			validity = false;
		}
		if (validity) {
			try {
				if (data.getKey() != null && data.getKey() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("check_userid_exist")
							.setParameter("userid", data.getKey()).getResultList();

					if (x.size() > 0) {
						String otp = generateRandom(4);
						DropDownModel dd = new DropDownModel();
						dd.setKey(x.get(0)[1].toString());
						dd.setCode(otp);

						jsonResponse.setBody(dd);
						jsonResponse.setCode("success");
						jsonResponse.setMessage("OTP sent successfully");

						String msg = "Welcome to eHealthSystem. Your OTP is " + otp;
						String encodemsg = URLEncoder.encode(msg, "UTF-8");

						CommonUsed.sendSMS(x.get(0)[1].toString(), encodemsg);

					} else {
						jsonResponse.setCode("failed");
						jsonResponse.setMessage(data.getName() + " is not exist");
					}

				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("UHID or Mobile number required");
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : getOTPForFOrgotPassword Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changePassword(DropDownModel data) {
		logger.info("Method : changeJEPasswordDao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
//		if (data.getKey() == null || data.getKey() == "") {
//			
//			jsonResponse.setCode("failed");
//			jsonResponse.setMessage("UHID required");
//			validity = false;
//		}

		if (validity) {
			
			try {
				if (data.getKey() != null && data.getKey() != "") {
					String password = null;
					if (data.getCode() != null && data.getCode() != "") {
						password = passEncoder.encode(data.getCode());
						
					}

					List<Object[]> x = em.createNamedStoredProcedureQuery("change_je_password")
							.setParameter("p_userid", data.getKey())
							.setParameter("p_password", password).getResultList();

					if (x.size() > 0) {
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Password updated successfully");

						
						
						
//						String msg = "Welcome to eHealthSystem. Your OTP is "+otp;
//						String encodemsg = URLEncoder.encode(msg, "UTF-8");
//						
//						CommonUsed.sendSMS(x.get(0)[0].toString(), encodemsg);

					} else {
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Password not updated");
					}

				}
//					else {
//					jsonResponse.setCode("failed");
//					jsonResponse.setMessage("UHID required");
//				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Password not updated");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : changeJEPasswordDao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changePasswordUserWiseAfterLogIn(DropDownModel data) {
		logger.info("Method : changePasswordUserWiseAfterLogIn Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getKey() == null || data.getKey() == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Mobile number required");
			validity = false;
		}

		if (!StringUtil.isNull(data.getName()) && !StringUtil.isNull(data.getCode())) {
			if (data.getName().equals(data.getCode())) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
						"New password can't be same as old password");
				validity = false;
			}
		}

		if (validity) {
			try {
				if (data.getKey() != null && data.getKey() != "") {

					String password = null;
					if (data.getName() != null && data.getName() != "" && data.getCode() != null
							&& data.getCode() != "") {
						String encodePassword = checkDuplicateDao.getUserPassword(data.getKey());
						Boolean status = passEncoder.matches(data.getName(), encodePassword);
						if (status) {
							password = passEncoder.encode(data.getCode());

							List<Object[]> x = em.createNamedStoredProcedureQuery("change_password")
									.setParameter("userid", data.getKey()).setParameter("password", password)
									.getResultList();

							if (x.size() > 0) {
								jsonResponse.setCode("success");
								jsonResponse.setMessage("Password changed successfully");

							} else {
								jsonResponse.setCode("failed");
								jsonResponse.setMessage("Password not changed");
							}

						} else {
							jsonResponse.setCode("failed");
							jsonResponse.setMessage("Current password does not match");
						}

					}
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Mobile number required");
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Password not changed");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : changePasswordUserWiseAfterLogIn Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getOTPForForgotUserid(DropDownModel data) {
		logger.info("Method : getOTPForForgotUserid Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		List<DropDownModel> ddlist = new ArrayList<DropDownModel>();
		Boolean validity = true;
		if (data.getKey() == null || data.getKey() == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Email or Mobile Number Required");
			validity = false;
		}
		if (validity) {
			try {
				if (data.getKey() != null && data.getKey() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("check_emailmobile_exist")
							.setParameter("userid", data.getKey()).getResultList();

					if (x.size() > 0) {
						String otp = generateRandom(4);
						Integer intotp = Integer.parseInt(otp);

						for (Object[] m : x) {
							DropDownModel dd = new DropDownModel(m[0], m[1], m[2]);
							ddlist.add(dd);
						}
						jsonResponse.setBody(ddlist);
						jsonResponse.setCode("success");
						jsonResponse.setTotal(intotp);
						jsonResponse.setMessage("OTP sent successfully");

						String msg = "Welcome to eHealthSystem. Your OTP is " + otp;
						String encodemsg = URLEncoder.encode(msg, "UTF-8");

						CommonUsed.sendSMS(x.get(0)[2].toString(), encodemsg);

					} else {
						jsonResponse.setCode("failed");
						jsonResponse.setMessage(data.getName() + " is not exist");
					}

				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("UHID or Mobile number required");
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : getOTPForForgotUserid Dao ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> sensSMSToUserForForgotUserid(DropDownModel data) {
		logger.info("Method : sensSMSToUserForForgotUserid Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		String msg = "Your Username for eHealthSystem for registered mobile number is " + data.getKey();
		String encodemsg = null;
		try {
			encodemsg = URLEncoder.encode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			int res = CommonUsed.sendSMSReturnResponse(data.getName(), encodemsg);

			if (res == 200) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Message sent successfully");
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Message not sent");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Message not sent");
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : sensSMSToUserForForgotUserid Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> getDiseaseInfoList() {
		logger.info("Method : getDiseaseInfoList Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> jsonArray1 = new ArrayList<Map<String, Object>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_disease_info_list_api").getResultList();
			if (x.size() > 0) {
				for (Object[] m : x) {
					Map<String, Object> ob = new HashMap<String, Object>();

					ob.put("name", m[0]);
					ob.put("url", m[1]);
					ob.put("details", m[2]);

					jsonArray.add(ob);

				}
				obj.put("body", jsonArray);
				obj.put("status", "success");
				obj.put("message", "Data fetched successfully");
			} else {
				obj.put("body", jsonArray1);
				obj.put("status", "failed");
				obj.put("message", "Data not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", "Data not found");
		}

		logger.info("Method : getDiseaseInfoList Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPharmacyListBySearchValue(String search) {
		logger.info("Method : getPharmacyListBySearchValue Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("pharmacy_list_by_searchvalue")
					.setParameter("search", search).getResultList();
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

		logger.info("Method : getPharmacyListBySearchValue Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getLabListBySearchValue(String search) {
		logger.info("Method : getLabListBySearchValue Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("pathology_lab_list_by_searchvalue")
					.setParameter("search", search).getResultList();
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

		logger.info("Method : getLabListBySearchValue Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getDoctorListByOrganization(String userid) {
		logger.info("Method : getDoctorListByOrganization Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_doctor_list_by_org")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getDoctorListByOrganization Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPhcListByidDao(String userid) {
		logger.info("Method : getPhcListByidDao Dao starts");

		List<DropDownModel> phcList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_phc_list_api").setParameter("userid", userid)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[1], m[0]);
				phcList.add(dropDownModel);
			}
			jsonResponse.setBody(phcList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getPhcListByidDao Dao ends");
		return response;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> updateUserPHCAPI(DropDownModel data) {
		logger.info("Method : updateUserPHCAPI DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (validity) {
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("update_user_phc_api")
						.setParameter("userid", data.getKey()).setParameter("phc", data.getName()).getResultList();

				jsonResponse.setCode("success");
				jsonResponse.setMessage("PHC Updated Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : updateUserPHCAPI DAO ends");
		return response;
	}

	public String saveAllOrganizationDocument(byte[] imageBytes, String ext, String pId) {
		logger.info("Method : saveAllOrganizationDocument starts");

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

			Path path = Paths.get(env.getOrganisationUpload() + imageName);
			if (imageBytes != null) {

				if (pId != null && pId != "") {
					Files.write(path, imageBytes);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : saveAllOrganizationDocument ends");
		return imageName;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postAddOrganizationAPI(APIOrganizationModel data) {
		logger.info("Method : postAddOrganizationAPI Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (StringUtil.isNull(data.getOrgname())) {
			jsonResponse.setMessage("Oganization Name Required");
			validity = false;
		} else if (StringUtil.isNull(data.getLicno())) {
			jsonResponse.setMessage("Licence Number Required");
			validity = false;
		} else if (StringUtil.isNull(data.getAddress())) {
			jsonResponse.setMessage("Address Required");
			validity = false;
		} else if (StringUtil.isNull(data.getCountry())) {
			jsonResponse.setMessage("Country Required");
			validity = false;
		} else if (StringUtil.isNull(data.getState())) {
			jsonResponse.setMessage("State Required");
			validity = false;
		} else if (StringUtil.isNull(data.getDist())) {
			jsonResponse.setMessage("District Required");
			validity = false;
		} else if (StringUtil.isNull(data.getCity())) {
			jsonResponse.setMessage("City Required");
			validity = false;
		} else if (StringUtil.isNull(data.getHealthprovider())) {
			jsonResponse.setMessage("Health Care Provider Required");
			validity = false;
		}

		String orgname = null;
		String uniqueid = null;
		if (!StringUtil.isNull(data.getOrgname())) {
			orgname = data.getOrgname().replace(" ", "").toUpperCase();
			long nowTime = new Date().getTime();
			uniqueid = orgname + nowTime + generateRandom(5);
		}

		byte[] byteone = null;
		if (!Util.isNull(data.getFileone())) {
			try {
				byteone = data.getFileone().getBytes();
				data.setDocone(saveAllOrganizationDocument(byteone, data.getExtone(), orgname));
			} catch (IOException e) {
				logger.error("File One Error = " + e.getMessage());
				e.printStackTrace();
			}
		}

		byte[] bytetwo = null;
		if (!Util.isNull(data.getFiletwo())) {
			try {
				bytetwo = data.getFiletwo().getBytes();
				data.setDoctwo(saveAllOrganizationDocument(bytetwo, data.getExttwo(), orgname));
			} catch (IOException e) {
				logger.error("File Two Error = " + e.getMessage());
				e.printStackTrace();
			}
		}

		byte[] bytethree = null;
		if (!Util.isNull(data.getFilethree())) {
			try {
				bytethree = data.getFilethree().getBytes();
				data.setDocthree(saveAllOrganizationDocument(bytethree, data.getExtthree(), orgname));
			} catch (IOException e) {
				logger.error("File Three Error = " + e.getMessage());
				e.printStackTrace();
			}
		}

		byte[] bytefour = null;
		if (!Util.isNull(data.getFilefour())) {
			try {
				bytefour = data.getFilefour().getBytes();
				data.setDocfour(saveAllOrganizationDocument(bytefour, data.getExtfour(), orgname));
			} catch (IOException e) {
				logger.error("File Four Error = " + e.getMessage());
				e.printStackTrace();
			}
		}

		List<Object[]> x = null;

		if (validity) {
			try {
				if (StringUtil.isNull(data.getOrgid())) {

					x = em.createNamedStoredProcedureQuery("post_add_organization_api")
							.setParameter("uniqueid", uniqueid).setParameter("orgname", data.getOrgname())
							.setParameter("licno", data.getLicno()).setParameter("address", data.getAddress())
							.setParameter("country", data.getCountry()).setParameter("state", data.getState())
							.setParameter("dist", data.getDist()).setParameter("city", data.getCity())
							.setParameter("pincode", data.getPincode()).setParameter("gst", data.getGst())
							.setParameter("healthprovider", data.getHealthprovider())
							.setParameter("docnameone", data.getDocnameone()).setParameter("fileone", data.getDocone())
							.setParameter("docnametwo", data.getDocnametwo()).setParameter("filetwo", data.getDoctwo())
							.setParameter("docnamethree", data.getDocnamethree())
							.setParameter("filethree", data.getDocthree())
							.setParameter("docnamefour", data.getDocnamefour())
							.setParameter("filefour", data.getDocfour()).getResultList();

					Util.setJsonResponse(jsonResponse, x.get(0), ResponseStatus.success,
							"Organization registered successfully.");

				}

			} catch (Exception e) {
				logger.error("Error " + e.getMessage());
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
						"Something went wrong. Registration failed.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postAddOrganizationAPI Dao ends");
		return response;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<List<ApiUserModel>>> getLoginMultipleUserDetails(String mobileNo,
			String password) {
		logger.info("Method : getLoginMultipleUserDetails starts");

		JsonResponse<List<ApiUserModel>> jsonResponse = new JsonResponse<List<ApiUserModel>>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		List<ApiUserModel> userArray = new ArrayList<ApiUserModel>();
		List<String> userRole = new ArrayList<String>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_login_details_multiple_user")
					.setParameter("p_username", mobileNo).getResultList();

			for (Object[] m : x) {
				String role = (String) m[6];

				if (role != null && role.length() > 0) {
					String[] roles = role.split(",");
					userRole = Arrays.asList(roles);
				}

				Boolean boolean1 = false;
				if (m[5].toString() != null) {
					String data = m[5].toString();
					if (data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}

				Object profileImg = null;
				if (!StringUtil.isNull(m[12])) {
					profileImg = env.getBaseURL() + "cureeazyrest/document/profile/" + m[12].toString();
				}

				ApiUserModel user = new ApiUserModel(m[0].toString(), m[1], m[2], m[3], m[4], null, null, m[13], m[14],
						null, null, boolean1, null, null, userRole, m[7], m[8], m[9], m[10], m[11], profileImg, m[15],
						m[16],null);
				userArray.add(user);

				logger.info("useruser" + user);
			}

			String encodePassword = userArray.get(0).getUserPassword();

			logger.info("Method : getLoginMultipleUserDetails ends");

			int i = 0;

			if (userArray.size() > 0) {
				for (ApiUserModel m : userArray) {
					final UserDetails userDetails = userDetailsService.loadUserByUsername(m.getUser());
					m.setToken("Bearer " + jwtTokenUtil.generateToken(userDetails));

					if (passEncoder.matches(password, m.getUserPassword())) {
						i = i + 1;
					}

				}

				if (i > 0) {
					jsonResponse.setBody(userArray);
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Password is incorrect");

					ResponseEntity<JsonResponse<List<ApiUserModel>>> response = new ResponseEntity<JsonResponse<List<ApiUserModel>>>(
							jsonResponse, HttpStatus.OK);
					logger.info("Method : getLoginDetails ends");

					return response;
				}

				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("User name is incorrect");
		}

		ResponseEntity<JsonResponse<List<ApiUserModel>>> response = new ResponseEntity<JsonResponse<List<ApiUserModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getLoginMultipleUserDetails ends");

		return response;

	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ApiUserModel>>> getLoginMultipleUserDetailsByMob(String mobileNo) {
		logger.info("Method : getLoginMultipleUserDetailsByMob starts");

		JsonResponse<List<ApiUserModel>> jsonResponse = new JsonResponse<List<ApiUserModel>>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		List<ApiUserModel> userArray = new ArrayList<ApiUserModel>();
		List<String> userRole = new ArrayList<String>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_login_details_multiple_user")
					.setParameter("p_username", mobileNo).getResultList();

			for (Object[] m : x) {
				String role = (String) m[6];

				if (role != null && role.length() > 0) {
					String[] roles = role.split(",");
					userRole = Arrays.asList(roles);
				}

				Boolean boolean1 = false;
				if (m[5].toString() != null) {
					String data = m[5].toString();
					if (data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}

				Object profileImg = null;
				if (!StringUtil.isNull(m[12])) {
					profileImg = env.getBaseURL() + "cureeazyrest/document/profile/" + m[12].toString();
				}

				ApiUserModel user = new ApiUserModel(m[0].toString(), m[1], m[2], m[3], m[4], null, null, m[13], m[14],
						null, null, boolean1, null, null, userRole, m[7], m[8], m[9], m[10], m[11], profileImg, m[15],
						m[16],null);
				userArray.add(user);
			}
			// final UserDetails userDetails =
			// userDetailsService.loadUserByUsername(mobileNo);

			logger.info("Method : getLoginMultipleUserDetailsByMob ends");
			if (userArray.size() > 0) {
				String otp = generateRandom(4);
				for (ApiUserModel m : userArray) {
					final UserDetails userDetails = userDetailsService.loadUserByUsername(m.getUser());
					m.setToken("Bearer " + jwtTokenUtil.generateToken(userDetails));
					m.setOtp(otp);
				}
				jsonResponse.setBody(userArray);

				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");

				String msg = "Welcome to eHealthSystem. Your OTP is " + otp;
				String encodemsg = URLEncoder.encode(msg, "UTF-8");

				CommonUsed.sendSMS(mobileNo, encodemsg);

			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Mobile number is incorrect");
		}

		ResponseEntity<JsonResponse<List<ApiUserModel>>> response = new ResponseEntity<JsonResponse<List<ApiUserModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getLoginMultipleUserDetailsByMob ends");

		return response;

	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getRoleAPI(String id) {
		logger.info("Method : getRoleAPI starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_role_api").setParameter("p_id", id)
					.getResultList();

			String roleid = x.get(0)[0].toString();
			String rolename = x.get(0)[1].toString();

			if (!StringUtil.isNull(roleid) && !StringUtil.isNull(rolename)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleid", roleid);
				map.put("rolename", rolename);
				jsonResponse.setBody(map);
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Role found");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Role not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Role not found");
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : getRoleAPI ends");
		return response;

	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getSyndicateOrganizationList() {
		logger.info("Method : getSyndicateOrganizationList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_syndicate_organization_api").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}

			if (countryList.size() > 0) {
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.failed,
						ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.failed, e.getMessage());
		}

		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getSyndicateOrganizationList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<PatientDetailsAPI>>> getPatientDetailsThroughId(String userid) {
		logger.info("Method : getPatientDetailsThroughId Dao starts");

		List<PatientDetailsAPI> patientDetails = new ArrayList<PatientDetailsAPI>();
		JsonResponse<List<PatientDetailsAPI>> jsonResponse = new JsonResponse<List<PatientDetailsAPI>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_details_throughid_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				PatientDetailsAPI dropDownModel = new PatientDetailsAPI(m[0], m[1], m[2], m[3], m[4], m[5], null);
				patientDetails.add(dropDownModel);
			}
			if (patientDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, patientDetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, patientDetails, ResponseStatus.failed,
						ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, patientDetails, ResponseStatus.failed, e.getMessage());
		}

		ResponseEntity<JsonResponse<List<PatientDetailsAPI>>> response = new ResponseEntity<JsonResponse<List<PatientDetailsAPI>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientDetailsThroughId Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<VersionModel>> getVersion() {
		logger.info("Method : getVersion Dao starts");

		List<VersionModel> version = new ArrayList<VersionModel>();
		JsonResponse<VersionModel> jsonResponse = new JsonResponse<VersionModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_version").getResultList();
			for (Object[] m : x) {
				VersionModel dropDownModel = new VersionModel(m[0], m[1], m[2]);
				version.add(dropDownModel);
			}

			if (version.size() > 0) {
				Util.setJsonResponse(jsonResponse, version.get(0), ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, version.get(0), ResponseStatus.failed,
						ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, version.get(0), ResponseStatus.failed, e.getMessage());
		}

		ResponseEntity<JsonResponse<VersionModel>> response = new ResponseEntity<JsonResponse<VersionModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getVersion Dao ends");
		return response;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> postActivityLogAPI(APIActivityLogModel data) {
		logger.info("Method : postActivityLogAPI DAO starts" + data);

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validation = true;

		/*
		 * if (StringUtil.isNull(data.getUserId())) { validation = false;
		 * jsonResponse.setMessage("User ID Required");
		 * 
		 * } else if (StringUtil.isNull(data.getVersion())) { validation = false;
		 * jsonResponse.setMessage("Version Required");
		 * 
		 * } else if (StringUtil.isNull(data.getImeiNo())) { validation = false;
		 * jsonResponse.setMessage("IMEI No Required");
		 * 
		 * } else if (StringUtil.isNull(data.getActivityDate())) { validation = false;
		 * jsonResponse.setMessage("Activity Date Required");
		 * 
		 * } else if (StringUtil.isNull(data.getActivityTime())) { validation = false;
		 * jsonResponse.setMessage("Time Required");
		 * 
		 * } else if (StringUtil.isNull(data.getType())) { validation = false;
		 * jsonResponse.setMessage("Type Required");
		 * 
		 * } else if (StringUtil.isNull(data.getStatus())) { validation = false;
		 * jsonResponse.setMessage("Status Required");
		 * 
		 * } else if (StringUtil.isNull(data.getDeviceToken())) { validation = false;
		 * jsonResponse.setMessage("Status Required"); }
		 * 
		 * if(StringUtil.isNull(data.getImeiNo())){
		 * data.setImeiNoOrDeviceId(data.getDeviceId()); }else {
		 * data.setImeiNoOrDeviceId(data.getImeiNo()); }
		 */

		// }
		String actDate = null;
		// if (StringUtil.isNull(data.getActivityDate())) {
		actDate = DateFormatter.getStringDate(data.getActivityDate());
		// }

		System.out.println("actDate" + actDate);
		// ActivityLog Details details list
		String activityLogDetails = "(" + data.getUserId() + ",'" + data.getVersion() + "','"
				+ data.getImeiNoOrDeviceId() + "','" + actDate + "','" + data.getActivityTime() + "','"
				+ data.getType() + "','" + data.getStatus() + "','" + data.getUserId() + "')";

		logger.info("activityLogDetails" + activityLogDetails);

		if (validation) {
			logger.info("activityLogDetails" + activityLogDetails);
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_activity_log_api")
						.setParameter("activitylogdetails", activityLogDetails)
						.setParameter("puserid", data.getUserId()).setParameter("pdeviceid", data.getImeiNoOrDeviceId())
						.setParameter("pdevicetoken", data.getDeviceToken()).setParameter("pstatus", data.getStatus())
						.setParameter("ptype", data.getType()).setParameter("pimeino", data.getImeiNoOrDeviceId())
						.getResultList();

				Util.setJsonResponse(jsonResponse, null, ResponseStatus.success, ApiResponseMessage.SAVED_SUCCESSFULLY);

			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_FAILURE);
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postActivityLogAPI DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> organizationListApi(String p_place) {
		logger.info("Method : organizationListApi Dao starts");

		List<CountryModel> organizationListApi = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();

		try {
			logger.info(p_place);
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_get_lab_list_api")
					.setParameter("p_place", p_place).getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				organizationListApi.add(dropDownModel);
			}

			if (organizationListApi.size() > 0) {
				Util.setJsonResponse(jsonResponse, organizationListApi, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, organizationListApi, ResponseStatus.failed,
						ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : organizationListApi Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<CatagoryModel>> getUserInterestDao() {
		logger.info("Method in Dao: getUserInterestDao starts");

		List<CatagoryModel> interestList = new ArrayList<CatagoryModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_user_interest_catagorylist").getResultList();

			List<String> categoryList = new ArrayList<String>();

			for (Object[] m : x) {
				if (!StringUtil.isNull(m[0])) {
					categoryList.add(m[0].toString());
				}
			}

			HashSet<String> uniqueCategoryList = new HashSet<String>(categoryList);

			for (String p : uniqueCategoryList) {
				CatagoryModel interest = new CatagoryModel();
				List<String> subCategory = new ArrayList<String>();
				for (Object[] m : x) {
					if (!StringUtil.isNull(m[0])) {
						interest.setCatagory(p);
						if (m[0].toString().equals(p) && !StringUtil.isNull(m[1])) {
							subCategory.add(m[1].toString());
						}
					}
					interest.setSubCatagoryList(subCategory);
				}
				interestList.add(interest);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		JsonResponse<List<CatagoryModel>> resp = new JsonResponse<List<CatagoryModel>>();

		if (interestList.size() > 0) {
			Util.setJsonResponse(resp, interestList, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
		} else {
			Util.setJsonResponse(resp, interestList, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
		}

		logger.info("Method in Dao: getUserInterestDao ends");
		return resp;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getPetList() {
		logger.info("Method in Dao: getPetList starts");

		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		List<DropDownModel> petList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_pet_list").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				petList.add(dropDownModel);
			}

			if (petList.size() > 0) {
				Util.setJsonResponse(jsonResponse, petList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, petList, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, petList, ResponseStatus.failed, e.getMessage());
			logger.error(e.getMessage());
		}

		logger.info("Method in Dao: getPetList ends");
		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<Object> getOTPForUserName(String uhid, String username) {
		logger.info("Method in Dao: getOTPForUserName starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		List<DropDownModel> petList = new ArrayList<DropDownModel>();

		Boolean boolval = checkDuplicateDao.isValidUHIDNumber(uhid);

		if (boolval) {
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("username_is_exist").setParameter("uhid", uhid)
						.setParameter("username", username).getResultList();

				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0], m[1].toString());
					petList.add(dropDownModel);
				}

				if (petList.size() > 0) {

					String bool = petList.get(0).getName();
					String mobile = petList.get(0).getKey();

					if (!StringUtil.isNull(bool)) {
						if (bool.equals("true")) {

							String otp = GenerateRandomValue.generateRandom(4);

							String msg = SMSGatewayMessage.OTP;
							msg = msg.replace("{otp}", otp);
							String encodemsg = URLEncoder.encode(msg, "UTF-8");

							try {
								CommonUsed.sendSMS(mobile, encodemsg);
							} catch (Exception e) {
								e.printStackTrace();
								Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
							}

							Map<String, Object> map = new HashMap<String, Object>();
							map.put("otp", otp);

							Util.setJsonResponse(jsonResponse, map, ResponseStatus.success,
									ApiResponseMessage.OTP_SEND);
						} else {
							Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
									ApiResponseMessage.USERNAME_INVALID);
						}
					} else {
						Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
								ApiResponseMessage.UNKNOWN_FAILURE);
					}

				} else {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_FAILURE);
				}

			} catch (Exception e) {
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
				logger.error(e.getMessage());
			}
		} else {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UHID_NOT_EXIST);
		}

		logger.info("Method in Dao: getOTPForUserName ends");
		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<Object> postUpdateUsername(UsernameModel data) {
		logger.info("Method in Dao: postUpdateUsername starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		try {
			@SuppressWarnings("unused")
			List<Object[]> x = em.createNamedStoredProcedureQuery("post_update_username")
					.setParameter("uhid", data.getUhid()).setParameter("username", data.getUsername()).getResultList();

			Util.setJsonResponse(jsonResponse, null, ResponseStatus.success, ApiResponseMessage.USERNAME_UPDATED);

		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
			logger.error(e.getMessage());
		}

		logger.info("Method in Dao: postUpdateUsername ends");
		return jsonResponse;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getOTPFOrgotPassword(DropDownModel data) {
		logger.info("Method : getOTPFOrgotPassword Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getKey() == null || data.getKey() == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("User Id or Mobile Number Required");
			validity = false;
		}
		String mobileNo=data.getKey();
		String patientName =data.getName();
		if (validity) {
			try {
				if (data.getKey() != null && data.getKey() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("check_userid_exist")
							.setParameter("userid", data.getKey()).getResultList();

					if (x.size() > 0) {
						String otp = generateRandom(4);
						DropDownModel dd = new DropDownModel();
						dd.setKey(x.get(0)[1].toString());
						dd.setCode(otp);

						jsonResponse.setBody(dd);
						jsonResponse.setCode("success");
						jsonResponse.setMessage("OTP sent successfully");

						String msg = "Welcome to CureeZ. Your OTP is " + otp;
						String encodemsg = URLEncoder.encode(msg, "UTF-8");

						CommonUsed.sendSMS(x.get(0)[1].toString(), encodemsg);
						
						String message = SMSGatewayMessage.OTP.replace("{name}", patientName).replace("{otp}", otp).replace("{verify}", "verify");
						
						try {
							String responsecode = commonUsed.sendCureezSmss(mobileNo,message);
							System.out.println("msgIdddddddddddddd"+responsecode);
						} catch (Exception e) {
							e.printStackTrace(); 
						}

					} else {
						jsonResponse.setCode("failed");
						jsonResponse.setMessage(data.getName() + " is not exist");
					}

				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("UHID or Mobile number required");
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : getOTPFOrgotPassword Dao ends");
		return response;
	}
}
