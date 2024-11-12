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
import nirmalya.aatithya.restmodule.api.model.LabDashboardCountModel;
import nirmalya.aatithya.restmodule.api.model.LabDashboardModel;
import nirmalya.aatithya.restmodule.api.model.LabTestViewReportModel;
import nirmalya.aatithya.restmodule.api.model.PaymentGatewayAPIModel;
import nirmalya.aatithya.restmodule.api.model.APIDoctorMonthlyOverviewModel;
import nirmalya.aatithya.restmodule.api.model.AmbulanceAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyLabTestModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.MailService;
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
public class PartnerDashboardDao {

	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;
	PushNotification pushNotification = new PushNotification();
	CommonUsed commonUsed = new CommonUsed();
	
	@Autowired
	MailService mailService;
	Logger logger = LoggerFactory.getLogger(PartnerDashboardDao.class);

	
	
	//get lab dashboard count
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<LabDashboardCountModel>> getLabDashboardcount(String labId) {
		logger.info("Method : getLabDashboardcount Dao starts");

		LabDashboardCountModel getLabDashboardcount = new LabDashboardCountModel();
		JsonResponse<LabDashboardCountModel> jsonResponse = new JsonResponse<LabDashboardCountModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_lab_dashboard_count")
					.setParameter("labId", labId).getResultList();
			for (Object[] m : x) {
				LabDashboardCountModel labDashboardCountModel = new LabDashboardCountModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7]);
				getLabDashboardcount=labDashboardCountModel;
			}
			jsonResponse.setBody(getLabDashboardcount);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<LabDashboardCountModel>> response = new ResponseEntity<JsonResponse<LabDashboardCountModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getLabDashboardcount Dao ends");

		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getLabStatusDao() {
		logger.info("Method : getLabStatusDao Dao starts");

		List<DropDownModel> labsts = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_lab_status").getResultList();
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
		logger.info("Method : getLabStatusDao Dao ends");

		return response;
	}
	
	//get lab dashboard details
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<LabDashboardModel>>> getLabDashboardDetails(String labId,String status
				,String fromDate,String toDate) {
			logger.info("Method : getLabDashboardDetails Dao starts");

			List<LabDashboardModel> getLabDashboardcount = new ArrayList<LabDashboardModel>();
			JsonResponse<List<LabDashboardModel>> jsonResponse = new JsonResponse<List<LabDashboardModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_lab_dashboard_details")
						.setParameter("labId", labId).setParameter("status", status)
						.setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
				for (Object[] m : x) {
					Object date = null;

					if (m[7] != null) {
						date = DateFormatter.returnStringDateNew(m[7].toString());
					}
					Object orderDate = null;

					if (m[11] != null) {
						orderDate = DateFormatter.returnStringDateNew(m[11].toString());
					}
					LabDashboardModel labDashboardCountModel = new LabDashboardModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],date, m[8], m[9], m[10],orderDate);
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
			ResponseEntity<JsonResponse<List<LabDashboardModel>>> response = new ResponseEntity<JsonResponse<List<LabDashboardModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getLabDashboardDetails Dao ends"+response);

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

				Path path = Paths.get(env.getPrescriptionUpload() + imageName);
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
								String outputImagePath = env.getPrescriptionUpload() + "thumb/" + imageName;
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
		// post update lab status

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
								String outputImagePath = env.getDocumentUpload() + "thumb/" + imageName;
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
		public String saveAllImage2(byte[] imageBytes, String ext, String pId) {
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
		public ResponseEntity<JsonResponse<LabDashboardModel>> postUpdateLabStatus(LabDashboardModel labmodel) {
			logger.info("Method : postUpdateLabStatus Dao starts");
			System.out.println(labmodel);
			JsonResponse<LabDashboardModel> jsonResponse = new JsonResponse<LabDashboardModel>();
			LabDashboardModel payments=new LabDashboardModel();
			LabDashboardModel userToken = new LabDashboardModel();
			//String docName=
			if (labmodel.getDocName() != null && labmodel.getDocName() != "") {
				try {
					byte[] bytes = Base64.getDecoder().decode(labmodel.getDocName());
					String imageName = null;
					imageName = saveAllImage2(bytes, labmodel.getExtension(), labmodel.getLabId());
					labmodel.setDocName(imageName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				String img="abc";
				labmodel.setDocName(img);
			}
			
			String orderId="";
			String paymentDate="";
			String paymentTime="";
			String userName="";
			String email="";
			String partnerName="";
			String DeviceIdKey="";
			String responsecode="";
			String labStatus="";
			String labMob="";
			String userId="";
			String status="";
			String type="";
				try {
					if (labmodel.getLabId() != null && labmodel.getLabId() != "") {
						List<Object[]> x = em.createNamedStoredProcedureQuery("post_update_lab_status")
								.setParameter("orderId", labmodel.getOrderId())
								.setParameter("labId", labmodel.getLabId())
								.setParameter("status", labmodel.getStatus())
								.setParameter("remark", labmodel.getRemark())
								.setParameter("doc", labmodel.getDocName())
								.getResultList();
						
						for(Object [] m:x) {
							String date = null;
							if (m[1] != null) {
								date = DateFormatter.returnStringDateNew(m[1].toString());
							}
							LabDashboardModel dropDownModel = new LabDashboardModel(m[0], date, m[2], m[3], m[4],m[5],m[6],m[7],m[8],m[9]);
							payments = dropDownModel;
							
							orderId=dropDownModel.getOrder_id();
							paymentDate=dropDownModel.getPaymentdate();
							paymentTime=dropDownModel.getPaymenttime();
							userName=dropDownModel.getUserName();
							email=dropDownModel.getEmail();
							partnerName=dropDownModel.getPartnerName();
							DeviceIdKey=dropDownModel.getDeviceToken();
							labStatus=dropDownModel.getStatus();
							labMob=dropDownModel.getMobile();
							userId=dropDownModel.getUserId();
							System.out.println("LabToken"+DeviceIdKey);
						}
						System.out.println("DevicelabStatusIdKey"+labStatus);
						String to = email;
						String username=userName;
						String sub = "Cureez Mail";
						String msg  = "Dear Partner  "+partnerName+","+ 
								"We have received lab test order No "+orderId+"."+
								"at "+paymentTime+"." +" "+"From User "+username+"."+"Please Process the request"+"."+ System.lineSeparator()+System.lineSeparator()+
								"Thanks, Team CureEZ.";
	
						String dataList="Dear Partner  "+partnerName+","+ 
								"We have received lab test order No "+orderId+","+ 
								"at "+paymentTime+"." +" "+"From User "+username+"."+"Please Process the request"+"."+ System.lineSeparator()+System.lineSeparator()+
								"Thanks, Team CureEZ.";
						System.out.println("userName"+userName);
						System.out.println("paymentTime"+paymentTime);
						System.out.println("username"+username);
						
						
						String message = SMSGatewayMessage.ORDERCONFIRM.replace("{name}", userName).replace("{time}", paymentTime).replace("{status}", status).replace("{paymentDate}", paymentDate);
						//.replace("{paymentDate}", paymentDate);
						System.out.println("message"+message);
						String mobileNo=labMob;
						System.out.println("mobileNo"+mobileNo);
						mailService.sendEmail(to,sub,msg); 
						
						if(labStatus.equals("2")) {
							 status="Accepted";
							 type="Order Accepted";
						}
						else if(labStatus.equals("3")) {
							 status="Completed";
							 type="Order Completed";
							 try {
								 responsecode = pushNotification.pushFCMNotifications(DeviceIdKey,dataList);
								System.out.println("msgIdddddddddddddd"+responsecode);
							} catch (Exception e) {
								e.printStackTrace(); 
							}
							try {
							List<Object[]> x1 = em.createNamedStoredProcedureQuery("post_lab_complete_notification")
									.setParameter("responsecode", responsecode)
									.setParameter("labId", labmodel.getLabId())
									.setParameter("dataList", dataList)
									.setParameter("type", type)

									.getResultList();
						
							}
							catch (Exception e) {
								e.printStackTrace(); 
							}
							
							String userDeviceToken="";
							System.out.println("All Notification");
							try {
								System.out.println("try");
								List<Object[]> x5 = em.createNamedStoredProcedureQuery("get_user_token_details")
										.setParameter("userId", userId)
										.getResultList();
								for (Object m : x5) {
									System.out.println("forloop");
									LabDashboardModel dropDownModel = new LabDashboardModel(m);
									userToken = dropDownModel;
									System.out.println("userTokensdcfvgcxsdfdcxzsdf"+dropDownModel.getUsertoken());
									System.out.println("dfbdsbfg"+userToken.getUsertoken());
									userDeviceToken=dropDownModel.getUsertoken();
									
								}
							} catch (Exception e) {
								jsonResponse.setCode("failed");
								jsonResponse.setMessage(e.getMessage());
							}
							System.out.println("userDeviceToken"+userDeviceToken);
							String type1="";
							String status1="";
							
							String responsecode3="";
							String dataListToken="Hi "+userName+","+" Your " +paymentTime+" ,"+"appointment is "+status+" for "+paymentDate+"."+" Thanks, Team CureEZ.";
							try {
								responsecode3 = pushNotification.pushFCMNotificationToken(userDeviceToken,dataListToken);
								System.out.println("msgIdddddddddddddd"+responsecode);
							} catch (Exception e) {
								e.printStackTrace(); 
							}
							
							
							try {
							List<Object[]> x1 = em.createNamedStoredProcedureQuery("post_lab_user_notification")
									.setParameter("responsecode", responsecode3)
									.setParameter("userId", userId)
									.setParameter("dataList", dataListToken)
									.setParameter("type", type)

									.getResultList();
						
							}
							catch (Exception e) {
								e.printStackTrace(); 
							}
						}
						else if(labStatus.equals("4")) {
							 status="Cancelled";
							 type="Order Cancelled";
						}
						else if(labStatus.equals("5")) {
							 status="Assigned";
							 type="Order Assigned";
						}
						else if(labStatus.equals("6")) {
							 status="Sample Collect";
							 type="Order Sample Collect";
						}
						else if(labStatus.equals("7")) {
							 status="Result Pending";
							 type="Order Result Pending";
						}
						else if(labStatus.equals("8")) {
							 status="Sample Under Processing";
							 type="Order Sample Under Processing";
						}
						try {
							String responsecodesms = commonUsed.sendCureezSmss(mobileNo,message);
							System.out.println("responsecodesms"+responsecodesms);
						} catch (Exception e) {
							e.printStackTrace(); 
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
				if(labStatus.equals("2") ||labStatus.equals("4")||labStatus.equals("5")||labStatus.equals("6")
						||labStatus.equals("7")||labStatus.equals("8")) {
					String userDeviceToken="";
					System.out.println("All Notification");
					try {
						System.out.println("try");
						List<Object[]> x5 = em.createNamedStoredProcedureQuery("get_user_token_details")
								.setParameter("userId", userId)
								.getResultList();
						for (Object m : x5) {
							System.out.println("forloop");
							LabDashboardModel dropDownModel = new LabDashboardModel(m);
							userToken = dropDownModel;
							System.out.println("userToken"+dropDownModel);
							
							userDeviceToken=dropDownModel.getUsertoken();
							
							System.out.println("userToken7"+userDeviceToken);
						}
					} catch (Exception e) {
						jsonResponse.setCode("failed");
						jsonResponse.setMessage(e.getMessage());
					}
					String type1="";
					String status1="";
					
					String responsecode3="";
					String dataListToken="Hi "+userName+","+" Your " +paymentTime+" ,"+"appointment is "+status+" for "+paymentDate+"."+" Thanks, Team CureEZ.";
					try {
						responsecode3 = pushNotification.pushFCMNotificationToken(userDeviceToken,dataListToken);
						System.out.println("msgIdddddddddddddd"+responsecode3);
					} catch (Exception e) {
						e.printStackTrace(); 
					}
					
					
					try {
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("post_lab_user_notification")
							.setParameter("responsecode", responsecode3)
							.setParameter("userId", userId)
							.setParameter("dataList", dataListToken)
							.setParameter("type", type)

							.getResultList();
				
					}
					catch (Exception e) {
						e.printStackTrace(); 
					}
				}
			ResponseEntity<JsonResponse<LabDashboardModel>> response = new ResponseEntity<JsonResponse<LabDashboardModel>>(jsonResponse,
					HttpStatus.OK);
			logger.info("Method : postUpdateLabStatus Dao ends");
			return response;
		}
		//get-view-labtest-report
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<LabTestViewReportModel>>> getviewlabtestreport(String orderId, 
				String labId) {
			logger.info("Method : getviewlabtestreport Dao starts"+orderId);
			List<LabTestViewReportModel> getviewlabtestreport = new ArrayList<LabTestViewReportModel>();
			JsonResponse<List<LabTestViewReportModel>> jsonResponse = new JsonResponse<List<LabTestViewReportModel>>();
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_view_labtest_report")
						.setParameter("orderId", orderId)
						.setParameter("labId", labId).getResultList();
				for (Object[] m : x1) {
					
					Object date = null;

					if (m[3] != null) {
						date = DateFormatter.returnStringDateNew(m[3].toString());
					}
					 
					
					LabTestViewReportModel labTestViewReportModel = new LabTestViewReportModel(m[0],m[1],m[2],date,m[4],m[5],m[6],m[7],m[8],m[9],m[10],m[11],m[12],null,m[13]);
					getviewlabtestreport.add(labTestViewReportModel);
				}
			jsonResponse.setBody(getviewlabtestreport);

			if (getviewlabtestreport.size() > 0) {
				Util.setJsonResponse(jsonResponse, getviewlabtestreport, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
			
			ResponseEntity<JsonResponse<List<LabTestViewReportModel>>> response = new ResponseEntity<JsonResponse<List<LabTestViewReportModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getviewlabtestreport Dao ends"+response);
				return response;
		}
		
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<LabDashboardModel>>> getSearchLabDetails(
				String searchdata) {
			logger.info("Method : getSearchLabDetails Dao starts");

			List<LabDashboardModel> doctorList = new ArrayList<LabDashboardModel>();
			JsonResponse<List<LabDashboardModel>> jsonResponse = new JsonResponse<List<LabDashboardModel>>();
			Boolean validity = true;
			if (searchdata == null || searchdata == "") {
				jsonResponse.setMessage("Search Data Required");
				jsonResponse.setCode("failed");
				validity = false;
			}
			if (validity) {
				try {
					System.out.println(searchdata);
					List<Object[]> x = em.createNamedStoredProcedureQuery("get_lab_search_details")
							.setParameter("searchdata", searchdata).getResultList();
					for (Object[] m : x) {
						Object date = null;

						if (m[7] != null) {
							date = DateFormatter.returnStringDateNew(m[7].toString());
						}
						LabDashboardModel dropDownModel = new LabDashboardModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],date, m[8], m[9], m[10],null);
						
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
			}
			ResponseEntity<JsonResponse<List<LabDashboardModel>>> response = new ResponseEntity<JsonResponse<List<LabDashboardModel>>>(
					jsonResponse, HttpStatus.OK);
			System.out.println(response);
			logger.info("Method : getSearchLabDetails Dao ends");
			return response;
		}
		
		//get lab dashboard count
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<DropDownModel>> getNotificationCount(String userId) {
			logger.info("Method : getNotificationCount Dao starts");

			DropDownModel getLabDashboardcount = new DropDownModel();
			JsonResponse<DropDownModel> jsonResponse = new JsonResponse<DropDownModel>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_notification_count")
						.setParameter("userId", userId).getResultList();
				for (Object m : x) {
					DropDownModel labDashboardCountModel = new DropDownModel(m,null);
					getLabDashboardcount=labDashboardCountModel;
				}
				jsonResponse.setBody(getLabDashboardcount);
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			ResponseEntity<JsonResponse<DropDownModel>> response = new ResponseEntity<JsonResponse<DropDownModel>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getNotificationCount Dao ends");

			return response;
		}
		
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getNotification(String userId) {
			logger.info("Method : getNotification Dao starts");

			List<DropDownModel> labsts = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_notification_message")
						.setParameter("userId", userId).getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0], m[1],m[2]);
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
			logger.info("Method : getNotification Dao ends");

			return response;
		}
		
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getNotificationSeen(String id,String userid) {
			logger.info("Method : getNotificationSeen Dao starts");

			List<DropDownModel> labsts = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_notification_seen")
						.setParameter("id", id).setParameter("userid", userid).getResultList();
				for (Object m : x) {
					DropDownModel dropDownModel = new DropDownModel(m,null);
					labsts.add(dropDownModel);
				}
				jsonResponse.setBody(labsts);
			
				
				if (labsts.size() > 0) {
					jsonResponse.setMessage("Notification Will be Seen");;
				} else {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
				}
			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getNotificationSeen Dao ends");

			return response;
		}
		
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getNotificationRemove(String id) {
			logger.info("Method : getNotificationRemove Dao starts");

			List<DropDownModel> labsts = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_notification_remove")
						.setParameter("id", id).getResultList();
				for (Object m : x) {
					DropDownModel dropDownModel = new DropDownModel(m, null,null);
					labsts.add(dropDownModel);
				}
				jsonResponse.setBody(labsts);
			
				
				if (labsts.size() > 0) {
					jsonResponse.setMessage("Notification Deleted Successfully");
				} else {
					jsonResponse.setMessage("Notification Already Exist");
				}
			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getNotificationRemove Dao ends");

			return response;
		}
}
