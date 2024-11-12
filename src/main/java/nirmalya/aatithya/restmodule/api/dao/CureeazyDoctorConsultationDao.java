package nirmalya.aatithya.restmodule.api.dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.CureEasyDrAppointBookingAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasySpecializationListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.DoctorAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.DoctorDashboardAppointmentDetailsModel;
import nirmalya.aatithya.restmodule.api.model.LabDashboardModel;
import nirmalya.aatithya.restmodule.api.model.MedicineApiModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionTestModel;
import nirmalya.aatithya.restmodule.api.model.RestRatingDetailsModel;
import nirmalya.aatithya.restmodule.api.model.TimeSlotModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.PushNotification;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class CureeazyDoctorConsultationDao {
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;
	PushNotification pushNotification = new PushNotification();
	Logger logger = LoggerFactory.getLogger(CureeazyDoctorConsultationDao.class);

	// save All Image
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
//symptoms With Specialization

	@SuppressWarnings("unchecked")
	public JsonResponse<CureEasySpecializationListAPIModel> symptomsWithSpecialization() {
		logger.info("Method in Dao: symptomsWithSpecialization Dao starts");
		CureEasySpecializationListAPIModel cureEasySpecializationListAPIModel = new CureEasySpecializationListAPIModel();
		// Most Consulted Symptoms
		List<DropDownModel> frequenSymptomsList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_consultedsymptoms_list").getResultList();
			for (Object[] m : x1) {
				String prfileImg = null;
				if (m[3] != null && m[3] != "" && m[3] != " " && !m[3].toString().equals(" ")
						&& !m[3].toString().equals(null) && !m[3].toString().equals("")) {
					System.out.println("IMAGESS" + m[3]);

					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[3].toString();
					System.out.println("PROFILE IMAGESS" + prfileImg);
				} else {
					prfileImg = "";
				}
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString(), m[2], prfileImg,
						null);
				frequenSymptomsList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// common Symptoms
		List<DropDownModel> commonSymptomsList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_commonsymptoms_list").getResultList();
			for (Object[] m : x1) {
				String prfileImg = null;
				if (m[3] != null && m[3] != "" && m[3] != " " && !m[3].toString().equals(" ")
						&& !m[3].toString().equals(null) && !m[3].toString().equals("")) {
					System.out.println("IMAGESS" + m[3]);

					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[3].toString();
					System.out.println("PROFILE IMAGESS" + prfileImg);
				} else {
					prfileImg = "";
				}
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString(), m[2], prfileImg,
						null);
				commonSymptomsList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// selected Specilaization
		List<DropDownModel> selectedSpecilaizationList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_specialization_list").getResultList();
			for (Object[] m : x1) {
				String prfileImg = null;
				if (m[2] != null && m[2] != "" && m[2] != " " && !m[2].toString().equals(" ")
						&& !m[2].toString().equals(null) && !m[2].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[2].toString();
				} else {
					prfileImg = "";
				}
				DropDownModel dropDownModel = new DropDownModel(null, m[0].toString(), m[1], prfileImg, null);
				selectedSpecilaizationList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cureEasySpecializationListAPIModel.setGetConsultedSymptomList(frequenSymptomsList);
		cureEasySpecializationListAPIModel.setGetCommonSymptomList(commonSymptomsList);
		cureEasySpecializationListAPIModel.setGetSpecializationList(selectedSpecilaizationList);

		JsonResponse<CureEasySpecializationListAPIModel> resp = new JsonResponse<CureEasySpecializationListAPIModel>();
		resp.setBody(cureEasySpecializationListAPIModel);

		resp.setCode("success");
		resp.setMessage("Data Fetched Successfully");
		logger.info("Method in Dao: symptomsWithSpecialization Dao ends");
		return resp;
	}

//get Specialization Wise DoctorList	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>> getSpecializationWiseDoctorList(
			String specializationId) {
		logger.info("Method : getSpecializationWiseDoctorList Dao starts");

		List<CureeazyDoctorConsultationModel> doctorList = new ArrayList<CureeazyDoctorConsultationModel>();
		JsonResponse<List<CureeazyDoctorConsultationModel>> jsonResponse = new JsonResponse<List<CureeazyDoctorConsultationModel>>();
		try {
			System.out.println(specializationId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_specialization_wise_doctor_list")
					.setParameter("specializationId", specializationId).getResultList();
			for (Object[] m : x) {
				String prfileImg = null;
				if (m[8] != null && m[8] != "" && m[8] != " " && !m[8].toString().equals(" ")
						&& !m[8].toString().equals(null) && !m[8].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[8].toString();
				} else {
					prfileImg = "";
				}
				CureeazyDoctorConsultationModel dropDownModel = new CureeazyDoctorConsultationModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], m[7], prfileImg, null, m[9], m[10]);
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
		ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>> response = new ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getSpecializationWiseDoctorList Dao ends");
		return response;
	}

//get search Wise DoctorList	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>> getSearchWiseDoctorList(
			String searchdata) {
		logger.info("Method : getSearchWiseDoctorList Dao starts");

		List<CureeazyDoctorConsultationModel> doctorList = new ArrayList<CureeazyDoctorConsultationModel>();
		JsonResponse<List<CureeazyDoctorConsultationModel>> jsonResponse = new JsonResponse<List<CureeazyDoctorConsultationModel>>();
		Boolean validity = true;
		if (searchdata == null || searchdata == "") {
			jsonResponse.setMessage("Search Data Required");
			jsonResponse.setCode("failed");
			validity = false;
		}
		if (validity) {
			try {
				System.out.println(searchdata);
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_search_wise_doctor_list")
						.setParameter("searchdata", searchdata).getResultList();
				for (Object[] m : x) {
					String prfileImg = null;
					if (m[8] != null && m[8] != "" && m[8] != " " && !m[8].toString().equals(" ")
							&& !m[8].toString().equals(null) && !m[8].toString().equals("")) {
						prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[8].toString();
					} else {
						prfileImg = "";
					}
					CureeazyDoctorConsultationModel dropDownModel = new CureeazyDoctorConsultationModel(m[0], m[1],
							m[2], m[3], m[4], m[5], m[6], m[7], prfileImg, null, null, null);
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
		ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>> response = new ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getSearchWiseDoctorList Dao ends");
		return response;
	}

//getDoctorProfile

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CureeazyDoctorConsultationModel>> getDoctorProfile(String doctorId,
			String userId) {
		logger.info("Method : getDoctorProfile Dao starts"+userId);

		CureeazyDoctorConsultationModel cureEasyDoctorAPIModel = new CureeazyDoctorConsultationModel();
		JsonResponse<CureeazyDoctorConsultationModel> jsonResponse = new JsonResponse<CureeazyDoctorConsultationModel>();
		try {
			System.out.println(doctorId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_doctor_profile")
					.setParameter("doctorId", doctorId).setParameter("userId", userId).getResultList();
			for (Object[] m : x) {
				System.out.println(Arrays.toString(m));
				String prfileImg = null;
				if (m[8] != null && m[8] != "" && m[8] != " " && !m[8].toString().equals(" ")
						&& !m[8].toString().equals(null) && !m[8].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[8].toString();
				} else {
					prfileImg = "";
				}
				CureeazyDoctorConsultationModel dropDownModel = new CureeazyDoctorConsultationModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], m[7], prfileImg, m[9].toString(),m[10],m[11]);
				cureEasyDoctorAPIModel = dropDownModel;
				System.out.println("dropDownModel"+dropDownModel);
			}
			jsonResponse.setBody(cureEasyDoctorAPIModel);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<CureeazyDoctorConsultationModel>> response = new ResponseEntity<JsonResponse<CureeazyDoctorConsultationModel>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getDoctorProfile Dao ends"+response);
		return response;
	}

	// get Doctor TimeSlotList
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorTimeSlotList(String doctorId, String slotDate) {
		logger.info("Method : getDoctorTimeSlotList Dao starts");

		List<DropDownModel> sList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			System.out.println(doctorId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_doctor_timeslot_list")
					.setParameter("doctorId", doctorId).setParameter("slotDate", DateFormatter.getStringDate(slotDate))
					.getResultList();
			for (Object[] m : x) {
				Object sdate = null;
				if (m[1] != null) {
					sdate = DateFormatter.returnStringDateNew(m[1].toString());
				}

				logger.info("sdate" + sdate);
				DropDownModel dropDownModel = new DropDownModel(m[0], sdate, m[2], m[3], null);
				sList.add(dropDownModel);
				System.out.println("dropDownModel : " + dropDownModel);
				System.out.println("sList : " + sList);
			}
			String date2 = slotDate;
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-YYYY");
			System.out.println("ft : " + ft);
			String str = ft.format(new Date());

			// Printing the formatted date
			System.out.println("Formatted Date : " + str);
			System.out.println("date2" + date2);
			if (!date2.equals(str)) {

				System.out.println("if");
				jsonResponse.setBody(sList);

			} else {
				System.out.println("else");
				Integer currentHour = LocalDateTime.now().getHour();
				Integer currentMinute = LocalDateTime.now().getMinute();
				Integer m5=5;
				Integer m6=currentMinute-m5;
				System.out.println("currentHour" + currentHour);
				System.out.println("currentMinute" + currentMinute);
				System.out.println("Before5"+ m6);
				
				List<DropDownModel> currentList = new ArrayList<DropDownModel>();
				for (DropDownModel model : sList) {
					System.out.println("sdfgh" + sList);
					Integer hour = Integer.valueOf(model.getCode().substring(0, 2));
					Integer minute = Integer.valueOf(model.getCode().substring(3, 5));
					String type = model.getCode().substring(6, 7);
					System.out.println("type" + type);
					System.out.println("hour" + hour);
					System.out.println("minute" + minute);
					if (type.equalsIgnoreCase("P") && hour != 12) {
						hour = hour + 12;
					}

					if (type.equalsIgnoreCase("A") && hour == 12) {
						hour = 0;
					}
					if ((currentHour == hour && currentMinute < minute) || (currentHour < hour)) {
						currentList.add(model);

						System.out.println("currentList" + currentList);
					}

				}
				jsonResponse.setBody(currentList);
			}

			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
			if (sList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("No Data Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getDoctorTimeSlotList Dao ends");
		return response;
	}

	
	
	// get Doctor TimeSlotList
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorTimeSlotLists(String doctorId, String slotDate) {
		logger.info("Method : getDoctorTimeSlotList Dao starts");

		List<DropDownModel> sList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			System.out.println(doctorId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_doctor_timeslot_list_doctor")
					.setParameter("doctorId", doctorId).setParameter("slotDate", DateFormatter.getStringDate(slotDate))
					.getResultList();
			for (Object[] m : x) {
				Object sdate = null;
				if (m[1] != null) {
					sdate = DateFormatter.returnStringDateNew(m[1].toString());
				}

				logger.info("sdate" + sdate);
				DropDownModel dropDownModel = new DropDownModel(m[0], sdate, m[2], m[3], null);
				sList.add(dropDownModel);
				System.out.println("dropDownModel : " + dropDownModel);
				System.out.println("sList : " + sList);
			}
			String date2 = slotDate;
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-YYYY");
			System.out.println("ft : " + ft);
			String str = ft.format(new Date());

			// Printing the formatted date
			System.out.println("Formatted Date : " + str);
			System.out.println("date2" + date2);
			if (!date2.equals(str)) {

				System.out.println("if");
				jsonResponse.setBody(sList);

			} else {
				System.out.println("else");
				Integer currentHour = LocalDateTime.now().getHour();
				Integer currentMinute = LocalDateTime.now().getMinute();
				Integer m5=5;
				Integer m6=currentMinute-m5;
				System.out.println("currentHour" + currentHour);
				System.out.println("currentMinute" + currentMinute);
				System.out.println("Before5"+ m6);
				
				List<DropDownModel> currentList = new ArrayList<DropDownModel>();
				for (DropDownModel model : sList) {
					System.out.println("sdfgh" + sList);
					Integer hour = Integer.valueOf(model.getCode().substring(0, 2));
					Integer minute = Integer.valueOf(model.getCode().substring(3, 5));
					String type = model.getCode().substring(6, 7);
					System.out.println("type" + type);
					System.out.println("hour" + hour);
					System.out.println("minute" + minute);
					if (type.equalsIgnoreCase("P") && hour != 12) {
						hour = hour + 12;
					}

					if (type.equalsIgnoreCase("A") && hour == 12) {
						hour = 0;
					}
					if ((currentHour == hour && currentMinute < minute) || (currentHour < hour)) {
						currentList.add(model);

						System.out.println("currentList" + currentList);
					}

				}
				jsonResponse.setBody(currentList);
			}

			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
			if (sList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("No Data Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getDoctorTimeSlotList Dao ends");
		return response;
	}
		public String saveAllImage3(byte[] imageBytes, String ext, String pId) {
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
	// postDoctorAppointmentApi
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CureEasyDrAppointBookingAPIModel>> postDoctorAppointmentApi(
			CureEasyDrAppointBookingAPIModel data) {
		logger.info("Method : postDoctorAppointmentApi Dao starts");
		System.out.println(data);
		JsonResponse<CureEasyDrAppointBookingAPIModel> jsonResponse = new JsonResponse<CureEasyDrAppointBookingAPIModel>();
		CureEasyDrAppointBookingAPIModel dlist = new CureEasyDrAppointBookingAPIModel();
		Boolean validity = true;

		if (data.getCreatedBy() == null || data.getCreatedBy() == "") {
			jsonResponse.setMessage("User Id Required");
			validity = false;
		}
		if (data.getDoctorId() == null || data.getDoctorId() == "") {
			jsonResponse.setMessage("Doctor Id Required");
			validity = false;
		}
		if (data.getSlotId() == null || data.getSlotId() == "") {
			jsonResponse.setMessage("Slot Id Required");
			validity = false;
		}
		if (data.getDocName() != null && data.getDocName() != "") {
			try {
				byte[] bytes = Base64.getDecoder().decode(data.getDocName());
				String imageName = null;
				imageName = saveAllImage3(bytes, data.getExtension(), data.getCreatedBy());
				data.setDocName(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String drid = null;
		if (data.getDoctorId() != null && data.getDoctorId() != "") {
			drid = data.getDoctorId().toString();
		}
		String booktype = null;
		if (data.getBookingType() != null && data.getBookingType() != "") {
			booktype = data.getBookingType().toString();
		}
		String doc = null;
		if (data.getDocName() != null && data.getDocName() != "") {
			doc = data.getDocName().toString();
		}
		else {
			doc="abc";
		}
		String hissue = null;
		if (data.getHealthIssue() != null && data.getHealthIssue() != "") {
			hissue = data.getHealthIssue().toString();
		}
		String sid = null;
		if (data.getSlotId() != null && data.getSlotId() != "") {
			sid = data.getSlotId().toString();
		}
		String sdate = null;
		if (data.getSlotDate() != null && data.getSlotDate() != "") {
			sdate = DateFormatter.getStringDate(data.getSlotDate());
		}
		String stime = null;
		if (data.getSlotTime() != null && data.getSlotTime() != "") {
			stime = data.getSlotTime().toString();
		}

		String user = null;
		if (data.getCreatedBy() != null && data.getCreatedBy() != "") {
			user = data.getCreatedBy().toString();
		}

		if (validity) {
			System.out.println("ssss");
			try {
				if (data.getAppointmentId() == null || data.getAppointmentId() == "") {
					System.out.println(data.getAppointmentId());
					System.out.println(drid);
					System.out.println("booktype"+booktype);
					System.out.println(doc);
					System.out.println(hissue);
					System.out.println(sid);
					System.out.println(sdate);
					System.out.println(stime);
					System.out.println(user);

					List<Object[]> x = em.createNamedStoredProcedureQuery("post_doctor_appointment_api")
							.setParameter("drid", drid)
							.setParameter("booktype", booktype)
							.setParameter("doc", doc)
							.setParameter("hissue", hissue)
							.setParameter("sid", sid)
							.setParameter("sdate", sdate)
							.setParameter("stime", stime)
							.setParameter("userid", user)
							.getResultList();
					for (Object[] m : x) {
						System.out.println(Arrays.toString(m));
						Object adate = null;
						if (m[1] != null) {
							adate = DateFormatter.returnStringDateNew(m[1].toString());
						}
						CureEasyDrAppointBookingAPIModel dropDownModel = new CureEasyDrAppointBookingAPIModel(m[0],
								adate, m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], null, null);
						dlist = dropDownModel;
					}
					jsonResponse.setBody(dlist);
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_FAILED);
			}
		}
		ResponseEntity<JsonResponse<CureEasyDrAppointBookingAPIModel>> response = new ResponseEntity<JsonResponse<CureEasyDrAppointBookingAPIModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postDoctorAppointmentApi Dao ends");
		return response;
	}

	// post Doctor Appointment Payment Dtls
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postDoctorAppointmentPaymentDtls(
			CureEasyDrAppointBookingAPIModel data) {
		logger.info("Method : postDoctorAppointmentPaymentDtls Dao starts");
		System.out.println(data);
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;

		if (data.getAppointmentId() == null || data.getAppointmentId() == "") {
			jsonResponse.setMessage("Appointment Id Required");
			validity = false;
		}
		/*
		 * if (data.getPaymentId() == null || data.getPaymentId() == "") {
		 * jsonResponse.setMessage("Payment Id Required"); validity = false; }
		 */

		String conCharge = null;
		if (data.getConsultingCharges() != null && data.getConsultingCharges() != "") {
			conCharge = data.getConsultingCharges().toString();
		}
		String coupon = null;
		if (data.getCoupon() != null && data.getCoupon() != "") {
			coupon = data.getCoupon().toString();
		}
		String couponAmt = null;
		if (data.getCouponAmount() != null && data.getCouponAmount() != "") {
			couponAmt = data.getCouponAmount().toString();
		}
		String othCharge = null;
		if (data.getOtherCharges() != null && data.getOtherCharges() != "") {
			othCharge = data.getOtherCharges().toString();
		}
		String totCharge = null;
		if (data.getTotalCharges() != null && data.getTotalCharges() != "") {
			totCharge = data.getTotalCharges().toString();
		}
		String payid = null;
		if (data.getPaymentId() != null && data.getPaymentId() != "") {
			payid = data.getPaymentId().toString();
		}
		if (validity) {
			System.out.println("ssss");
			try {
				if (data.getAppointmentId() != null && data.getAppointmentId() != "") {
					System.out.println(data.getAppointmentId());
					System.out.println(conCharge);
					System.out.println(coupon);
					System.out.println(couponAmt);
					System.out.println(othCharge);
					System.out.println(totCharge);
					System.out.println(payid);

					List<Object[]> x = em.createNamedStoredProcedureQuery("post_doctor_appointment_payment_details")
							.setParameter("appid", data.getAppointmentId()).setParameter("conCharge", conCharge)
							.setParameter("coupon", coupon).setParameter("couponAmt", couponAmt)
							.setParameter("othCharge", othCharge).setParameter("totCharge", totCharge)
							.setParameter("payid", payid).getResultList();
					jsonResponse.setBody(x.get(0));
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DOCTOR_APPOINTMENT_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.DOCTOR_APPOINTMENT_FAILED);
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : postDoctorAppointmentPaymentDtls Dao ends");
		return response;
	}

	// post Favorite Doctor Api
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postFavoriteDoctorApi(CureeazyDoctorConsultationModel data) {
		logger.info("Method : postFavoriteDoctorApi Dao starts");
		System.out.println(data);
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;
		if (data.getUserId() == null || data.getUserId() == "") {
			jsonResponse.setMessage("User Id Required");
			validity = false;
		}
		if (data.getDoctorId() == null || data.getDoctorId() == "") {
			jsonResponse.setMessage("Doctor Id Required");
			validity = false;
		}
		String fstatus = null;
		if (data.getFavStatus() != null && data.getFavStatus() != "") {
			fstatus = data.getFavStatus().toString();
		} else {
			fstatus = "0";
		}
		if (validity) {
			System.out.println("ssss");
			try {
				if (fstatus != null && fstatus != "") {
					System.out.println(data.getUserId());
					System.out.println(data.getDoctorId());
					System.out.println(fstatus);
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_favorite_doctor_api")
							.setParameter("userid", data.getUserId()).setParameter("drid", data.getDoctorId())
							.setParameter("favstatus", fstatus).getResultList();
					jsonResponse.setBody(x.get(0));
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Favourite Doctor Added Successfully.");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : postFavoriteDoctorApi Dao ends");
		return response;
	}

	// get Favorite Doctor List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>> getFavoriteDoctorList(String userId) {
		logger.info("Method : getFavoriteDoctorList Dao starts");

		List<CureeazyDoctorConsultationModel> doctorList = new ArrayList<CureeazyDoctorConsultationModel>();
		JsonResponse<List<CureeazyDoctorConsultationModel>> jsonResponse = new JsonResponse<List<CureeazyDoctorConsultationModel>>();
		try {
			System.out.println(userId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_favorite_doctor_list")
					.setParameter("userid", userId).getResultList();
			for (Object[] m : x) {
				String prfileImg = null;
				if (m[8] != null && m[8] != "" && m[8] != " " && !m[8].toString().equals(" ")
						&& !m[8].toString().equals(null) && !m[8].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[8].toString();
				} else {
					prfileImg = "";
				}
				CureeazyDoctorConsultationModel dropDownModel = new CureeazyDoctorConsultationModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], m[7], prfileImg, null, m[9], m[10]);
				doctorList.add(dropDownModel);
			}
			jsonResponse.setBody(doctorList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

			if (doctorList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("No Data Found");
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>> response = new ResponseEntity<JsonResponse<List<CureeazyDoctorConsultationModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getFavoriteDoctorList Dao ends"+response);
		return response;
	}

	// get user appointment details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CureEasyDrAppointBookingAPIModel>>> getUserAppointmentDetails(String userId,
			String status) {
		logger.info("Method : getUserAppointmentDetails Dao starts");

		List<CureEasyDrAppointBookingAPIModel> doctorList = new ArrayList<CureEasyDrAppointBookingAPIModel>();
		JsonResponse<List<CureEasyDrAppointBookingAPIModel>> jsonResponse = new JsonResponse<List<CureEasyDrAppointBookingAPIModel>>();
		try {
			System.out.println(userId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_appointment_dtls")
					.setParameter("userid", userId).setParameter("statuss", status).getResultList();
			for (Object[] m : x) {
				Object appointDates=null;
				if (m[7] != null) {
					appointDates = DateFormatter.returnStringDateNew(m[7].toString());
				}
				CureEasyDrAppointBookingAPIModel dropDownModel = new CureEasyDrAppointBookingAPIModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], appointDates, m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15],m[16],null);
				doctorList.add(dropDownModel);
			}
			jsonResponse.setBody(doctorList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

			if (doctorList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("No Data Found");
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CureEasyDrAppointBookingAPIModel>>> response = new ResponseEntity<JsonResponse<List<CureEasyDrAppointBookingAPIModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getUserAppointmentDetails Dao ends" + response);
		return response;
	}

	// getdoctorappointmentdetails
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DoctorAppointmentDetailsModel>>> getdoctorappointmentdetails(String userId,
			String doctorid, String status) {
		logger.info("Method : getdoctorappointmentdetails Dao starts");

		List<DoctorAppointmentDetailsModel> getdoctorappointmentdetails = new ArrayList<DoctorAppointmentDetailsModel>();
		JsonResponse<List<DoctorAppointmentDetailsModel>> jsonResponse = new JsonResponse<List<DoctorAppointmentDetailsModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_appointment_details")
					.setParameter("userId", userId).setParameter("doctorid", doctorid).setParameter("status", status)
					.getResultList();
			for (Object[] m : x) {
				Object appointDates=null;
				if (m[6] != null) {
					appointDates = DateFormatter.returnStringDateNew(m[6].toString());
				}
				DoctorAppointmentDetailsModel doctorAppointmentDetailsModel = new DoctorAppointmentDetailsModel(m[0],
						m[1], m[2], m[3], m[4], m[5], appointDates, m[7], m[8]);
				getdoctorappointmentdetails.add(doctorAppointmentDetailsModel);
			}
			jsonResponse.setBody(getdoctorappointmentdetails);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

			if (getdoctorappointmentdetails.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("No Data Found");
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DoctorAppointmentDetailsModel>>> response = new ResponseEntity<JsonResponse<List<DoctorAppointmentDetailsModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getdoctorappointmentdetails Dao ends");
		return response;
	}

	// getdoctorappointmentdetails
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CureEasyDrAppointBookingAPIModel>>> getdoctorappointmentdetails(
			String userId, String apptId) {
		logger.info("Method : getdoctorappointmentdetails Dao starts");

		List<CureEasyDrAppointBookingAPIModel> getdoctorappointmentdetails = new ArrayList<CureEasyDrAppointBookingAPIModel>();
		JsonResponse<List<CureEasyDrAppointBookingAPIModel>> jsonResponse = new JsonResponse<List<CureEasyDrAppointBookingAPIModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("doctor_appointment_details")
					.setParameter("userId", userId).setParameter("apptId", apptId).getResultList();
			for (Object[] m : x1) {
				String prfileImg = null;
				if (m[8] != null && m[8] != "" && m[8] != " " && !m[8].toString().equals(" ")
						&& !m[8].toString().equals(null) && !m[8].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[8].toString();

				} else {
					prfileImg = "";
				}
				Object appointDates=null;
				if (m[6] != null) {
					appointDates = DateFormatter.returnStringDateNew(m[6].toString());
				}
				CureEasyDrAppointBookingAPIModel doctorAppointmentDetailsModel = new CureEasyDrAppointBookingAPIModel(
						m[0], m[1], m[2], m[3], m[4], m[5], appointDates, m[7], prfileImg, m[9], m[10], m[11], m[12], m[13],
						m[14], m[15], null);
				getdoctorappointmentdetails.add(doctorAppointmentDetailsModel);
			}
			if (getdoctorappointmentdetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, getdoctorappointmentdetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(getdoctorappointmentdetails);
		ResponseEntity<JsonResponse<List<CureEasyDrAppointBookingAPIModel>>> response = new ResponseEntity<JsonResponse<List<CureEasyDrAppointBookingAPIModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getdoctorappointmentdetails Dao ends");
		return response;
	}

	// get medicine dropdown
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getmedicinedropdownList() {
		logger.info("Method : getmedicinedropdownList Dao starts");

		List<DropDownModel> medicineList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_medicine_dropdown_list").getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				medicineList.add(dropDownModel);
			}
			jsonResponse.setBody(medicineList);

			if (medicineList.size() > 0) {
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
		logger.info("Method : getmedicinedropdownList Dao ends");
		return response;
	}

	// get test name autosearch
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> gettestnameautosearch(String searchdata) {
		logger.info("Method : gettestnameautosearch Dao starts");

		List<DropDownModel> testname = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		Boolean validity = true;
		if (searchdata == null || searchdata == "") {
			jsonResponse.setMessage("Search Data Required");
			jsonResponse.setCode("failed");
			validity = false;
		}
		String medicineName = "";
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_medicine_autosearch")
					.setParameter("searchdata", searchdata).getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				testname.add(dropDownModel);
				medicineName = dropDownModel.getName();
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		System.out.println("Name" + medicineName);

		List<DropDownModel> testnames = new ArrayList<DropDownModel>();
		if (searchdata.equalsIgnoreCase(medicineName)) {
			logger.info("ifff1" + searchdata.equals(medicineName));
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_medicine_autosearch")
						.setParameter("searchdata", searchdata).getResultList();
				for (Object[] m : x) {

					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					testnames.add(dropDownModel);
				}
				jsonResponse.setBody(testnames);

				if (testnames.size() > 0) {
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

		}

		else {
			System.out.println("ELSE");
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("post_medicine")
						.setParameter("searchdata", searchdata).getResultList();
				logger.info("Patient" + x.get(0));

			} catch (Exception e) {
				e.printStackTrace();

			}

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_medicine_autosearch")
						.setParameter("searchdata", searchdata).getResultList();
				for (Object[] m : x) {

					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					testnames.add(dropDownModel);
				}
				jsonResponse.setBody(testnames);

				if (testnames.size() > 0) {
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
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : gettestnameautosearch Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public Boolean isExistShowCode(String showcode) {
		logger.info("Method : isExistLocation Dao starts" + showcode);

		Boolean isExist = false;

		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_showcode").setParameter("showcode", showcode)
					.getResultList();
			for (Object m : x) {
				DropDownModel dropDownModel = new DropDownModel(m, null);
				dataList.add(dropDownModel);

			}
			if (dataList != null) {
				isExist = true;
			}
			if (dataList.size() > 0) {
				isExist = true;
			} else {
				isExist = false;
			}
		} catch (Exception e) {
			isExist = false;
		}
		logger.info("dropDownModel" + dataList);
		logger.info("Method : isExistLocation Dao ends" + isExist);
		return isExist;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<PrescriptionModel> postprescriptionApi(PrescriptionModel prescriptionModel) {

		logger.info("Method : postprescriptionApi starts" + prescriptionModel);

		JsonResponse<PrescriptionModel> resp = new JsonResponse<PrescriptionModel>();
		PrescriptionModel dlist = new PrescriptionModel();
		String medicinelist = "";

		if (prescriptionModel.getMedicinelist() != null) {
			if (prescriptionModel.getMedicinelist().size() > 0) {
				for (MedicineApiModel m : prescriptionModel.getMedicinelist()) {
					medicinelist = medicinelist + "(order_id1,\'" + m.getMedicineid() + "\',\'" + m.getDuration()
							+ "\',\'" + m.getMedicineName() + "\',\'" + m.getStrength() + "\',\'" + m.getDose()
							+ "\',\'" + m.getQuantity() + "\'),";

					logger.info("lossssst" + medicinelist);

				}
				// medicinelist = medicinelist.substring(0, medicinelist.length() - 1);
				medicinelist = medicinelist.substring(0, medicinelist.length() - 1);
			} else {
				medicinelist = "";
			}

		}

		String testlist = "";
		if (prescriptionModel.getTestlist() != null) {
			if (prescriptionModel.getTestlist().size() > 0) {

				for (PrescriptionTestModel m : prescriptionModel.getTestlist()) {
					testlist = testlist + "(order_id1,\'" + m.getTestid() + "\',\'" + m.getTestName() + "\'),";

					logger.info("lossssst" + testlist);
				}
				// testlist = testlist.substring(0, testlist.length() - 1);
				testlist = testlist.substring(0, testlist.length() - 1);
			}

			else {
				testlist = "";
			}

		}
		String dobs = null;
		if (prescriptionModel.getDob() != null && prescriptionModel.getDob() != "") {
			dobs = DateFormatter.getStringDateNew(prescriptionModel.getDob());
		}

		Boolean showcode;
		Boolean validity = true;

		showcode = isExistShowCode(prescriptionModel.getShowCode());
		if (showcode) {
			resp.setMessage("Invalid Showcode");
			validity = false;
		}
		logger.info("showcode" + showcode);
		logger.info("dob" + dobs);
		String doctorName = "";
		String patientName = "";
		String responsecode = "";
		String token = "";
		String responsecode1="";
		if (showcode == true) {

			try {
				if (prescriptionModel.getDoctorId() != null) {

					List<Object[]> x = em.createNamedStoredProcedureQuery("post_prescription_api")
							.setParameter("userids", prescriptionModel.getUserid())

							// .setParameter("dates", prescriptionModel.getDate())
							.setParameter("appointmentid", prescriptionModel.getAppointmentId())
							.setParameter("doctorId", prescriptionModel.getDoctorId())
							.setParameter("specialadvice", prescriptionModel.getSpecialadvice())
							.setParameter("consultType", prescriptionModel.getConsultType())
							.setParameter("showCode", prescriptionModel.getShowCode()).setParameter("dobs", dobs)
							.setParameter("genders", prescriptionModel.getGender())
							.setParameter("medicinelist", medicinelist).setParameter("testlist", testlist)
							.getResultList();

					for (Object[] m : x) {
						System.out.println("dcfgvh" + Arrays.toString(m));
						PrescriptionModel dropDownModel = new PrescriptionModel(m[0], m[1], m[2]);
						dlist = dropDownModel;

						doctorName = dropDownModel.getDoctorName();
						patientName = dropDownModel.getPatientName();
						token = dropDownModel.getToken();
					}
					String DeviceIdKey = token;
					String type = "Digital Consultation Completed";
					String dataList = "Hi " + patientName + "," + "Thank you for consulting with " + doctorName + "."
							+ "Your prescription will be uploaded in next 30 minutes" + "." + System.lineSeparator()
							+ System.lineSeparator() + "Thanks, Team CureEZ.";

					try {
						responsecode1 = pushNotification.pushFCMNotifications(DeviceIdKey, dataList);
						System.out.println("msgIdddddddddddddd" + responsecode1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						List<Object[]> x1 = em
								.createNamedStoredProcedureQuery("patient_consultation_complete_notification")
								.setParameter("responsecode", responsecode1)
								.setParameter("userId", prescriptionModel.getAppointmentId())
								.setParameter("dataList", dataList).setParameter("type", type)

								.getResultList();

					} catch (Exception e) {
						e.printStackTrace();
					}
					resp.setCode("success");
					resp.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
				} else {

				}

			}

			catch (Exception e) {
				logger.info("addSchemeToVle: " + e);
				try {
					String[] err = serverDao.errorProcedureCall(e);

					resp.setCode(err[0]);
					resp.setMessage(err[1]);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		}

		else {
			resp.setMessage("Invalid Showcode");
		}
		logger.info("Method : postprescriptionApi ends" + resp);
		return resp;
	}

	// get search Wise DoctorList
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>> searchPatientNameWithNumber(
			String searchdata) {
		logger.info("Method : searchPatientNameWithNumber Dao starts");

		List<DoctorDashboardAppointmentDetailsModel> doctorList = new ArrayList<DoctorDashboardAppointmentDetailsModel>();
		JsonResponse<List<DoctorDashboardAppointmentDetailsModel>> jsonResponse = new JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>();
		Boolean validity = true;
		if (searchdata == null || searchdata == "") {
			jsonResponse.setMessage("Search Data Required");
			jsonResponse.setCode("failed");
			validity = false;
		}
		if (validity) {
			try {
				System.out.println(searchdata);
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_search_patientname_withnumber")
						.setParameter("searchdata", searchdata).getResultList();
				for (Object[] m : x) {
					String prfileImg = null;
					if (m[7] != null && m[7] != "" && m[7] != " " && !m[7].toString().equals(" ")
							&& !m[7].toString().equals(null) && !m[7].toString().equals("")) {
						prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[7].toString();
					} else {
						prfileImg = "";
					}
					DoctorDashboardAppointmentDetailsModel doctorDashboardAppointmentDetailsModel = new DoctorDashboardAppointmentDetailsModel(
							m[0], m[1], m[2], m[3], m[4], m[5], m[6], prfileImg, null, null, null);
					doctorList.add(doctorDashboardAppointmentDetailsModel);
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
		ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>> response = new ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : searchPatientNameWithNumber Dao ends");
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

	// postDoctorAppointmentApi
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<PrescriptionModel>> postManualPrescription(PrescriptionModel data) {
		logger.info("Method : postManualPrescription Dao starts");
		System.out.println(data);
		JsonResponse<PrescriptionModel> jsonResponse = new JsonResponse<PrescriptionModel>();
		PrescriptionModel dlist = new PrescriptionModel();
		Boolean validity = true;

		if (data.getShowCode() == null || data.getShowCode() == "") {
			jsonResponse.setMessage("Code Required");
			validity = false;
		}

		if (data.getDocName() != null && data.getDocName() != "") {
			try {
				byte[] bytes = Base64.getDecoder().decode(data.getDocName());
				String imageName = null;
				imageName = saveAllImage2(bytes, data.getExtension(), data.getDoctorId());
				data.setDocName(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String drid = null;
		if (data.getDoctorId() != null && data.getDoctorId() != "") {
			drid = data.getDoctorId().toString();
		}
		String doc = null;
		if (data.getDocName() != null && data.getDocName() != "") {
			doc = data.getDocName().toString();
		}
		String user = null;
		if (data.getUserid() != null && data.getUserid() != "") {
			user = data.getUserid().toString();
		}
		String doctorName = "";
		String patientName = "";
		String responsecode = "";
		String token = "";
		String responsecode1="";
		Boolean showcode;

		showcode = isExistShowCode(data.getShowCode());
		if (showcode) {
			jsonResponse.setMessage("Invalid Showcode");
			validity = false;
		}
		logger.info("showcode" + showcode);
		
		//	if (validity) {
				if (showcode == true) {
				System.out.println("ssss");
				try {
					if (data.getDoctorId() != null || data.getDoctorId() != "") {

						List<Object[]> x = em.createNamedStoredProcedureQuery("post_prescription_manual")
								.setParameter("drid", drid).setParameter("doc", doc).setParameter("userid", user)
								.setParameter("consultType", data.getConsultType())
								.setParameter("showCode", data.getShowCode())
								.setParameter("appointmentId", data.getAppointmentId()).getResultList();

						for (Object[] m : x) {
							PrescriptionModel dropDownModel = new PrescriptionModel(m[0], m[1], m[2]);
							dlist = dropDownModel;

							doctorName = dropDownModel.getDoctorName();
							patientName = dropDownModel.getPatientName();
							token = dropDownModel.getToken();
						}
						String DeviceIdKey = token;
						String type = "Manual Consultation Completed";
						String dataList = "Hi " + patientName + "," + "Thank you for consulting with " + doctorName
								+ "." + "Your prescription will be uploaded in next 30 minutes" + "."
								+ System.lineSeparator() + System.lineSeparator() + "Thanks, Team CureEZ.";

						try {
							responsecode1 = pushNotification.pushFCMNotifications(DeviceIdKey, dataList);
							System.out.println("msgIdddddddddddddd" + responsecode1);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							List<Object[]> x1 = em
									.createNamedStoredProcedureQuery("patient_consultation_complete_notification")
									.setParameter("responsecode", responsecode1).setParameter("userId", data.getAppointmentId())
									.setParameter("dataList", dataList).setParameter("type", type)

									.getResultList();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					jsonResponse.setCode("success");
					jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse.setCode("failed");
					jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_FAILED);
				}
			}
				 else {
						jsonResponse.setMessage("Invalid Showcode");
					}
			//}

		
		ResponseEntity<JsonResponse<PrescriptionModel>> response = new ResponseEntity<JsonResponse<PrescriptionModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postManualPrescription Dao ends");
		return response;
	}

	// post Favorite Doctor Api
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postJoinMeeting(DropDownModel data) {
		logger.info("Method : postJoinMeeting Dao starts");
		System.out.println(data);
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("post_join_meeting")
					.setParameter("key", data.getKey()).setParameter("code", data.getCode()).getResultList();
			jsonResponse.setBody(x.get(0));

			jsonResponse.setCode("success");
			jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : postJoinMeeting Dao ends");
		return response;
	}

	// getdoctorappointmentdetails
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<DropDownModel>> getShowCode(String apptId) {
		logger.info("Method : getShowCode Dao starts");

		DropDownModel getdoctorappointmentdetails = new DropDownModel();
		JsonResponse<DropDownModel> jsonResponse = new JsonResponse<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_show_code").setParameter("apptId", apptId)
					.getResultList();
			for (Object m : x1) {
				DropDownModel doctorAppointmentDetailsModel = new DropDownModel(m, null);
				getdoctorappointmentdetails = doctorAppointmentDetailsModel;
			}
			jsonResponse.setBody(getdoctorappointmentdetails);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(getdoctorappointmentdetails);
		ResponseEntity<JsonResponse<DropDownModel>> response = new ResponseEntity<JsonResponse<DropDownModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getShowCode Dao ends");
		return response;
	}
	//get search Wise DoctorList	
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>> getSearchPatientList(
				String searchdata) {
			logger.info("Method : getSearchPatientList Dao starts");

			List<DoctorDashboardAppointmentDetailsModel> doctorList = new ArrayList<DoctorDashboardAppointmentDetailsModel>();
			JsonResponse<List<DoctorDashboardAppointmentDetailsModel>> jsonResponse = new JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>();
			Boolean validity = true;
			if (searchdata == null || searchdata == "") {
				jsonResponse.setMessage("Search Data Required");
				jsonResponse.setCode("failed");
				validity = false;
			}
			if (validity) {
				try {
					System.out.println(searchdata);
					List<Object[]> x = em.createNamedStoredProcedureQuery("get_search_wise_patient_list")
							.setParameter("searchdata", searchdata).getResultList();
					for (Object[] m : x) {
						String prfileImg = null;
						if (m[7] != null && m[7] != "" && m[7] != " " && !m[7].toString().equals(" ")
								&& !m[7].toString().equals(null) && !m[7].toString().equals("")) {
							prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[7].toString();
						} else {
							prfileImg = "";
						}
						Object appointDates=null;
						if (m[1] != null) {
							appointDates = DateFormatter.returnStringDateNew(m[1].toString());
						}
						DoctorDashboardAppointmentDetailsModel doctorDashboardAppointmentDetailsModel = new DoctorDashboardAppointmentDetailsModel(m[0],appointDates,m[2],m[3],m[4],m[5],m[6],prfileImg,m[8],m[9],m[10]);
						doctorList.add(doctorDashboardAppointmentDetailsModel)
						;
						logger.info("doctorDashboardAppointmentDetailsModel"+doctorDashboardAppointmentDetailsModel);
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
			ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>> response = new ResponseEntity<JsonResponse<List<DoctorDashboardAppointmentDetailsModel>>>(
					jsonResponse, HttpStatus.OK);
			System.out.println(response);
			logger.info("Method : getSearchPatientList Dao ends");
			return response;
		}
		
		//get rating api
				@SuppressWarnings("unchecked")
				public JsonResponse<List<RestRatingDetailsModel>> getRatingDetails(String pid, String doctorId) {
					logger.info("Method : getVleheaderList Dao starts");
					List<RestRatingDetailsModel> orgNameList = new ArrayList<RestRatingDetailsModel>();
					JsonResponse<List<RestRatingDetailsModel>> resp = new JsonResponse<List<RestRatingDetailsModel>>();
					try {
						
						List<Object[]> x = em.createNamedStoredProcedureQuery("get_rating_details_api")
								.setParameter("pid", pid)
								.setParameter("doctorId", doctorId)
								.getResultList();
						for (Object[] m : x) {
							RestRatingDetailsModel dropDownModel = new RestRatingDetailsModel(m[0], m[1],m[2],m[3],m[4],m[5]);
							orgNameList.add(dropDownModel);
						}
						resp.setBody(orgNameList);
						resp.setCode("success");
						resp.setMessage("Data Fetched Successfully");
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("getOrganizationAutoSearchListDao" + resp);
					logger.info("Method : getVleheaderList Dao ends");
					return resp;
				}
				
				//get rating api
				@SuppressWarnings("unchecked")
				public JsonResponse<List<RestRatingDetailsModel>> getRatingDetailsDoctor(String doctorId) {
					logger.info("Method : getVleheaderList Dao starts");
					List<RestRatingDetailsModel> orgNameList = new ArrayList<RestRatingDetailsModel>();
					JsonResponse<List<RestRatingDetailsModel>> resp = new JsonResponse<List<RestRatingDetailsModel>>();
					try {
						
						List<Object[]> x = em.createNamedStoredProcedureQuery("get_rating_details_doctor_api")
								.setParameter("doctorId", doctorId)
								.getResultList();
						for (Object[] m : x) {
							RestRatingDetailsModel dropDownModel = new RestRatingDetailsModel(m[0], m[1],m[2],m[3],m[4],m[5]);
							orgNameList.add(dropDownModel);
						}
						resp.setBody(orgNameList);
						resp.setCode("success");
						resp.setMessage("Data Fetched Successfully");
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("getOrganizationAutoSearchListDao" + resp);
					logger.info("Method : getVleheaderList Dao ends");
					return resp;
				}
}
