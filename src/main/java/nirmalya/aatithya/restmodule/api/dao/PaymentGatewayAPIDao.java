package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.PaymentGatewayAPIModel;
import nirmalya.aatithya.restmodule.api.model.RestPaymentGatewayModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.MailService;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.PushNotification;
import nirmalya.aatithya.restmodule.util.SMSGatewayMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


@Repository
public class PaymentGatewayAPIDao {

	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;
	
	@Autowired
	MailService mailService;
	
	CommonUsed commonUsed = new CommonUsed();

	@Value("${razorpay_keyid}")
	private String key_id;

	@Value("${razorpay_keysecret}")
	private String key_secret;
	PushNotification pushNotification = new PushNotification();

	Logger logger = LoggerFactory.getLogger(PaymentGatewayAPIDao.class);

	/*
	 * 
	 * Api to return order id
	 */
	public String getOrderDetailsDao(String order_id, Double amount) {
		logger.info("Method : getOrderDetails Dao starts");

		RazorpayClient razorpayClient = null;
		try {
			razorpayClient = new RazorpayClient(key_id, key_secret);
			System.out.println("key_id"+key_id);
			System.out.println("key_secret"+key_secret);
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

		logger.info("Method : getOrderDetails Dao ends");
		return !StringUtil.isNull(order) ? order.toString() : json.toString();
	}

	/*
	 * Method to save payment details
	 * 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> savePaymentDetails(PaymentGatewayAPIModel payment) {
		logger.info("Method : savePaymentDetails Dao starts");
		logger.info("savePaymentDetails Dao "+payment);

		JsonResponse<PaymentGatewayAPIModel> jsonResponse = new JsonResponse<PaymentGatewayAPIModel>();
		PaymentGatewayAPIModel payments=new PaymentGatewayAPIModel();
		Boolean validation = true;
		String date = null;
		String payment_for = "Doctor Booking";
		String bookinstatus = null;

		if (StringUtil.isNull(payment.getOrder_id())) {
			validation = false;
			jsonResponse.setMessage("Order Id Required");
		}

		if (StringUtil.isNull(payment.getCreatedOn())) {
			validation = false;
			jsonResponse.setMessage("Status Date time Required");
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
		/*
		 * if (StringUtil.isNull(payment.getPay_mode())) { validation = false;
		 * jsonResponse.setMessage("Payment Mode Required"); }
		 */

		
		if (!StringUtil.isNull(payment.getCreatedOn())) {
			date = DateFormatter.getStringDateNew(payment.getCreatedOn());
		}

		if (payment.getStatus().equals("success")) {
			 bookinstatus = "1";
		} else {
			 bookinstatus = "0";
		}
		String appointTime="";
		String todayDate="";
		try {
			
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_doctor_time")
					.setParameter("orderId", payment.getOrder_id()).getResultList();
			for (Object[] m : x1) {
				PaymentGatewayAPIModel dropDownModel = new PaymentGatewayAPIModel(m[0],m[1],null);
				logger.info("Time"+dropDownModel);
				appointTime=dropDownModel.getAppointTime();
				todayDate=dropDownModel.getAppointDate();
				//appointTime="2023-02-11T09:00:00Z";
			}
			//System.out.println("appointTime"+todayDate+"T"+appointTime+"Z");
			//System.out.println("appointDate"+todayDate);
			//System.out.println("appointTime"+appointTime);
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		String timeForZoom=todayDate+"T"+appointTime+"Z";
		String postData="{\n    \"agenda\": \"My Meeting\",\n    \"default_password\": false,\n    \"duration\": 60,\n    \"password\": \"123456\",\n    \"pre_schedule\": false,\n    \"start_time\": \"{time1}\",\n    \"topic\": \"My Meeting\",\n    \"type\": 2}"; 
		String postData1 = postData.replace("{time1}",timeForZoom);
		System.out.println(postData1);
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
//				RequestBody body = RequestBody.create(mediaType, "{\n    \"agenda\": \"My Meeting\",\n    \"default_password\": false,\n    \"duration\": 60,\n    \"password\": \"123456\",\n    \"pre_schedule\": false,\n    \"start_time\": \"2022-03-25T07:32:55Z\",\n    \"topic\": \"My Meeting\",\n    \"type\": 2}");
				RequestBody body =  RequestBody.create(mediaType, postData1);
				
				Request request = new Request.Builder()
				  .url("https://api.zoom.us/v2/users/me/meetings")
				  .method("POST", body)
				  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOm51bGwsImlzcyI6Ik5YbElpWG52U25tTmNETzMxekJMZXciLCJleHAiOjE5MjIyNzg3NDAsImlhdCI6MTY2OTIxMzE4OX0.ggo0f8YALJq9KK__hAQ9carbBAz5Liqyntb3qjEgTcU")
				  .addHeader("Content-Type", "application/json")
				 // .addHeader("Cookie", "TS018dd1ba=01593b78b8dedab89e1649f65dcea78fbf3652c6a48983eacc07c613481c87a6a43c50d232d5caea6449a5209163c00f4e1bcaa777; cf_bm=XL9RKUltSNvhsJ1TEh6UIk1NCfjLL8kd06Pa_rYiCHM-1669212767-0-AVas8pqySmcBlM5kyxfyd32gjIgDZO1pMO5xxlhOqDZIkuEXhXsr+9WoTMfoSVFNmmzeZJQ5SugRdUp06D6jJGo=; zm_mtk_guid=79f779a9187b4a58966e53a22277a118; TS01f92dc5=01593b78b8dedab89e1649f65dcea78fbf3652c6a48983eacc07c613481c87a6a43c50d232d5caea6449a5209163c00f4e1bcaa777; cred=8994C5D2A431BDE6F45E63E95D0DB1AE; cf_bm=OdxeNCm0HlXAh8zV1So731dnydRfjagcYNNBk5lIPgM-1669437781-0-AW0Ry9FFXsqzAszGGXTMZSIFAaqgsAl8BbJFlaQ123BDtvhAvzQiDajC4UWXWQbdfutbwMXQuvgTuYsfo3Hno5E=; zm_mtk_guid=9986f7a9a1a94aecb0e2040bb5bb1008; cred=5FA0CEE99936B638B9B3B66E7A621BA1")
				  .build();
		 String userId="";
		 String userName="";
		 String phone="";
		 String email="";
		 String timeslot="";
		 String description="";
		 String appointDate="";
		 String token="";
		 String responsecode2="";
		 String doctorName="";
		 String doctorMobNo="";
		// String responsecode="";
		if (validation) {

			try {
				Response response = client.newCall(request).execute();
			
				
//System.out.println("Response is "+response.body().string());
				
				JSONObject jObj = new JSONObject(response.body().string());
				
				 // System.out.println("join_url"+jObj.toString(0));
				//System.out.println("jObj##########"+jObj.toString());
				 // System.out.println("join_url"+jObj.toString(0));
		       String areaString = jObj.getString("join_url");
		       String startUrl = jObj.getString("start_url");
		      // System.out.println("join_url "+areaString);
		       //System.out.println("startUrl "+startUrl);
				
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("post_payment_details")
						.setParameter("orderid", payment.getOrder_id())
						.setParameter("paymentid", payment.getPayment_id())
						.setParameter("payment_status", payment.getStatus())
						.setParameter("payment_for", payment_for)
						.setParameter("total_amount", payment.getAmount())
						.setParameter("bookingstatus", bookinstatus)
						.setParameter("created_on", date)
						.setParameter("userid", payment.getUserid())
						.setParameter("payment_mode", payment.getPay_mode())
						.setParameter("areaString", areaString)
						.setParameter("startUrl", startUrl).getResultList();
					logger.info("x"+x1);
					
					for (Object[] m : x1) {
						System.out.println("dfghgfdfghjkjhgfdfghj"+Arrays.toString(m));
						Object appointDates = null;

						if (m[7] != null) {
							appointDates = DateFormatter.returnStringDateNew(m[7].toString());
						}
						PaymentGatewayAPIModel dropDownModel = new PaymentGatewayAPIModel(m[0],m[1], m[2],m[3],m[4],m[5],m[6],appointDates,m[8],m[9]);
						payments = dropDownModel;
						
						userId=dropDownModel.getUserId();
						userName=dropDownModel.getUserName();
						phone=dropDownModel.getPhno();
						email=dropDownModel.getEmail();
						timeslot=dropDownModel.getPaymenttime();
						description=dropDownModel.getStatus();
						token=dropDownModel.getDeviceToken();
						appointDate=dropDownModel.getPaymentdate();
						doctorName=dropDownModel.getDoctorName();
						doctorMobNo=dropDownModel.getDoctorMob();
						System.out.println("dropDownModel"+dropDownModel);
					}
					
					String mobileNo=phone;
					String to = email;
					String username=userName;
					String DeviceIdKeys=token;
					String timeslots=timeslot;
					String descriptions=description;
					System.out.println("DeviceIdKey"+DeviceIdKeys);
					String sub = "Cureez Mail";
					String msg  = "Hi  "+username+","+ 
							"Your Appointment Details "+timeslots+","+ 
							"appointment is "+descriptions+" " +"for "+appointDate+". "+"Thanks, Team CureEZ.";
					
					String dataListAppoint="Hi  "+username+","+ 
							"Your Appointment Details "+timeslots+","+ 
							"appointment is "+descriptions+" " +"for "+appointDate+". "+"Thanks, Team CureEZ.";
					
					try {
						//  responsecode2 = pushNotification.pushFCMNotifications(DeviceIdKey,dataList);
						  responsecode2 = pushNotification.pushFCMNotificationAppoint(DeviceIdKeys,dataListAppoint);
						System.out.println("msgId1"+responsecode2);
					} catch (Exception e) {
						e.printStackTrace(); 
					}
					String type="Appointment Requested";
					try {
						List<Object[]> x2 = em.createNamedStoredProcedureQuery("user_appoint_notification")
								.setParameter("responsecode", responsecode2)
								.setParameter("userid", userId)
								.setParameter("dataList", dataListAppoint)
								.setParameter("type", type)
								.getResultList();
					
						}
						catch (Exception e) {
							e.printStackTrace(); 
						}
						/*
						 * String message = SMSGatewayMessage.ORDERCONFIRM.replace("{name}",
						 * username).replace("{appoint}", timeslots) .replace("{appointDate}",
						 * appointDate);
						 */
					String message = SMSGatewayMessage.ORDERCONFIRM.replace("{name}", username).replace("{time}", timeslots).replace("{status}", descriptions).replace("{paymentDate}", appointDate);
					
					try {
						String responsecode = commonUsed.sendCureezSmss(mobileNo,message);
						System.out.println("responsecode1"+responsecode);
					} catch (Exception e) {
						e.printStackTrace(); 
					}
				
					mailService.sendEmail(to,sub,msg); 
					jsonResponse.setBody(payments);
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.success,
						ApiResponseMessage.PAYMENT_DETAILS_SUCCESSFULL);

			} catch (Exception e) {
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
			String userDeviceToken="";
			
			String doctorNames="";
			List<PaymentGatewayAPIModel> doctorToken = new ArrayList<PaymentGatewayAPIModel>();
			try {
			
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_doctor_token")
						.setParameter("doctorid", payment.getOrder_id()).getResultList();
				for (Object m : x1) {
					PaymentGatewayAPIModel dropDownModel = new PaymentGatewayAPIModel(m,null);
					doctorToken.add(dropDownModel);
					userDeviceToken=dropDownModel.getDoctorToken();
					System.out.println("Doctor"+dropDownModel);
				}
		
			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			String mobileNo1 =doctorMobNo;
			String message1 = SMSGatewayMessage.ORDERCONFIRM.replace("{name}", doctorName).replace("{time}", timeslot).replace("{status}", description).replace("{paymentDate}", appointDate);
			
			try {
				String responsecode = commonUsed.sendCureezSmsDoctor(mobileNo1,message1);
				System.out.println("responsecode1"+responsecode);
			} catch (Exception e) {
				e.printStackTrace(); 
			}
			String type1="Appointment Requested";
			String responsecode3="";
			String dataListToken="Hi  "+doctorName+","+ 
					"Your Appointment Details "+timeslot+","+ 
					"appointment is "+description+" " +"for "+appointDate+". "+"Thanks, Team CureEZ.";
			System.out.println("dataListToken"+dataListToken);
			try {
				responsecode3 = pushNotification.pushFCMNotificationToken(userDeviceToken,dataListToken);
				System.out.println("msgIdddddddddddddd2"+responsecode3);
			} catch (Exception e) {
				e.printStackTrace(); 
			}
			
			
			try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("post_doctor_notification")
					.setParameter("responsecode", responsecode3)
					.setParameter("userId", payment.getOrder_id())
					.setParameter("dataList", dataListToken)
					.setParameter("type", type1)

					.getResultList();
		
			}
			catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> response = new ResponseEntity<JsonResponse<PaymentGatewayAPIModel>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : savePaymentDetails Dao starts");
		return response;
	}

	//get search Wise DoctorList	
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<PaymentGatewayAPIModel>>> startConsultation(
				String orderid) {
			logger.info("Method : startConsultation Dao starts");

			List<PaymentGatewayAPIModel> doctorList = new ArrayList<PaymentGatewayAPIModel>();
			JsonResponse<List<PaymentGatewayAPIModel>> jsonResponse = new JsonResponse<List<PaymentGatewayAPIModel>>();
		
	
				try {
					System.out.println(orderid);
					List<Object[]> x = em.createNamedStoredProcedureQuery("get_joinUrl_startConsultation")
							.setParameter("orderid", orderid).getResultList();
					for (Object m[] : x) {
						PaymentGatewayAPIModel dropDownModel = new PaymentGatewayAPIModel(null, null, null, null, null, null, null, m[0],m[1],m[2],m[3]);
						doctorList.add(dropDownModel);
					}
					jsonResponse.setBody(doctorList);
					jsonResponse.setCode("success");
					jsonResponse.setMessage("Data Fetched Successfully");

					if (doctorList.size() > 0) {
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Data Fetched Successfully");
					} else {
						jsonResponse.setCode("success");
						jsonResponse.setMessage("No Data Found");
					}
				} catch (Exception e) {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage(e.getMessage());
				}
		
			ResponseEntity<JsonResponse<List<PaymentGatewayAPIModel>>> response = new ResponseEntity<JsonResponse<List<PaymentGatewayAPIModel>>>(
					jsonResponse, HttpStatus.OK);
			System.out.println(response);
			logger.info("Method : startConsultation Dao ends");
			return response;
		}
		
		
		
		/*
		 * 
		 * Api to return order id
		 */
		public String getOrderDetailsLab(String order_id, Double amount) {
			logger.info("Method : getOrderDetailsLab Dao starts");

			RazorpayClient razorpayClient = null;
			try {
				razorpayClient = new RazorpayClient(key_id, key_secret);
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

			logger.info("Method : getOrderDetailsLab Dao ends");
			return !StringUtil.isNull(order) ? order.toString() : json.toString();
		}
		
		//Lab Payment Save
		@SuppressWarnings({ "unchecked", "unused" })
		public ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> savePaymentDetailsLab(PaymentGatewayAPIModel payment) {
			logger.info("Method : savePaymentDetailsLab Dao starts");
			logger.info("savePaymentDetailsLab Dao "+payment);

			JsonResponse<PaymentGatewayAPIModel> jsonResponse = new JsonResponse<PaymentGatewayAPIModel>();
			PaymentGatewayAPIModel payments=new PaymentGatewayAPIModel();
			Boolean validation = true;
			String date = null;
			String payment_for = "Lab Test";
			String bookinstatus = null;
			logger.info("xxxxxxxxxxx"+DateFormatter.getStringDateNew(payment.getCreatedOn()));
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

			/*
			 * if (!StringUtil.isNull(payment.getCreatedOn())) { date =
			 * DateFormatter.getStringDateNew(payment.getCreatedOn()); }
			 */

			if (payment.getStatus().equals("success")) {
				 bookinstatus = "1";
			} else {
				 bookinstatus = "0";
			}
			String orderId="";
			String paymentDate="";
			String paymentTime="";
			String userName="";
			String email="";
			String partnerName="";
			String DeviceIdKey="";
			String	responsecode="";
			String mob="";
			if (validation) {
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_payment_details_lab")
							.setParameter("orderid", payment.getOrder_id())
							.setParameter("paymentid", payment.getPayment_id())
							.setParameter("payment_status", payment.getStatus())
							.setParameter("payment_for", payment_for)
							.setParameter("total_amount", payment.getAmount())
							.setParameter("bookingstatus", bookinstatus)
							.setParameter("created_on", DateFormatter.getStringDateNew(payment.getCreatedOn()))
							.setParameter("userid", payment.getUserid())
							.setParameter("payment_mode", payment.getPay_mode()).getResultList();
						//System.out.println("DATE"+DateFormatter.getStringDateNew(payment.getCreatedOn()));
						for(Object [] m:x) {

							if (m[1] != null) {
								date = DateFormatter.returnStringDateNew(m[1].toString());
							}
							PaymentGatewayAPIModel dropDownModel = new PaymentGatewayAPIModel(m[0], date, m[2], m[3], m[4],m[5],m[6],m[7],null);
							payments = dropDownModel;
							
							orderId=dropDownModel.getOrder_id();
							paymentDate=dropDownModel.getPaymentdate();
							paymentTime=dropDownModel.getPaymenttime();
							userName=dropDownModel.getUserName();
							email=dropDownModel.getEmail();
							partnerName=dropDownModel.getPartnerName();
							DeviceIdKey=dropDownModel.getDeviceToken();
							mob=dropDownModel.getPhno();
						}
						String to = email;
						String username=userName;
						String sub = "Cureez Mail";
						String msg  = "Hi  "+username+","+
								"We have received lab test order No "+orderId+"."+ 
								"at "+paymentTime+"." +"Please Process the request"+"."+ System.lineSeparator()+System.lineSeparator()+
								"Thanks, Team CureEZ.";
	
						String dataList="Hi "+username+","+ 
								"We have received lab test order No "+orderId+"."+
								"at "+paymentTime+"."+"Please Process the request"+"."+ System.lineSeparator()+System.lineSeparator()+
								"Thanks, Team CureEZ.";
					
						
						try {
							 responsecode = pushNotification.pushFCMNotifications(DeviceIdKey,dataList);
							//System.out.println("msgIdddddddddddddd"+responsecode);
						} catch (Exception e) {
							e.printStackTrace(); 
						}
						String type="Lab Order Requested";
						try {
							List<Object[]> x1 = em.createNamedStoredProcedureQuery("lab_book_notification")
									.setParameter("responsecode", responsecode)
									.setParameter("userid", payment.getUserid())
									.setParameter("dataList", dataList)
									.setParameter("type", type)
									.getResultList();
						
							}
							catch (Exception e) {
								e.printStackTrace(); 
							}
						String mobileNo=mob;
						/*
						 * String message = SMSGatewayMessage.LABORDER.replace("{name}",
						 * partnerName).replace("{orderId}", orderId) .replace("{paymentDate}",
						 * paymentDate).replace("{paymentTime}", paymentTime).replace("{userName}",
						 * userName);
						 */
						String message = SMSGatewayMessage.ORDERCONFIRM.replace("{name}", username).replace("{time}", paymentTime).replace("{status}", "Requested Lab").replace("{paymentDate}", paymentDate);
						
						try {
							String responsecodesms = commonUsed.sendCureezSmss(mobileNo,message);
							//System.out.println("responsecodesms"+responsecodesms);
						} catch (Exception e) {
							e.printStackTrace(); 
						}
						mailService.sendEmail(to,sub,msg); 
						jsonResponse.setBody(payments);
						Util.setJsonResponse(jsonResponse, null, ResponseStatus.success,
							ApiResponseMessage.PAYMENT_DETAILS_SUCCESSFULL);

				} catch (Exception e) {
					e.printStackTrace();
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
				}
			}
			ResponseEntity<JsonResponse<PaymentGatewayAPIModel>> response = new ResponseEntity<JsonResponse<PaymentGatewayAPIModel>>(jsonResponse,
					HttpStatus.OK);

			logger.info("Method : savePaymentDetailsLab Dao starts");
			return response;
		}
		
		
}
