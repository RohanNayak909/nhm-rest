package nirmalya.aatithya.restmodule.api.dao;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.log.SysoLogger;

import nirmalya.aatithya.restmodule.api.model.APIMajorSurgeryModel;
import nirmalya.aatithya.restmodule.api.model.APIMedicalConditionModel;
import nirmalya.aatithya.restmodule.api.model.APIPatientHistoryModel;
import nirmalya.aatithya.restmodule.api.model.APIPatientMedicalHistoryModel;
import nirmalya.aatithya.restmodule.api.model.ApiMemberModel;
import nirmalya.aatithya.restmodule.api.model.ApiMemberPedModel;
import nirmalya.aatithya.restmodule.api.model.ApiMemberProductComponentModel;
import nirmalya.aatithya.restmodule.api.model.ApiMemberQuestionDetail;
import nirmalya.aatithya.restmodule.api.model.ApiPolicyProductComponents;
import nirmalya.aatithya.restmodule.api.model.InstamozoApiModel;
import nirmalya.aatithya.restmodule.api.model.InsuranceAPIModel;
import nirmalya.aatithya.restmodule.api.model.InsuranceAdbsModel;
import nirmalya.aatithya.restmodule.api.model.PatientFamilyDetailsModel;
import nirmalya.aatithya.restmodule.api.model.PatientImmunizationDetailsModel;
import nirmalya.aatithya.restmodule.api.model.PatientLifeStyleHistoryModel;
import nirmalya.aatithya.restmodule.api.model.SignUpModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.GenerateRandomValue;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class ShowEmrAPIDao {
	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(ShowEmrAPIDao.class);

	public static String generateRandom(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return new String(digits);
	}

	// Smoking list dropdown
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getSmokingList() {
		logger.info("Method : getSmokingList Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_smoking_dropdown").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				orgList.add(dropDownModel);
			}
			jsonResponse.setBody(orgList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getSmokingList Dao ends");

		return response;
	}

	// alcohol list dropdown
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getAlcoholList() {
		logger.info("Method : getAlcoholList Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_alcohol_list_api").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				orgList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, orgList, ResponseStatus.success, ApiResponseMessage.DATA_FECTH_FAILED);
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getAlcoholList Dao ends");

		return response;
	}

	// diet list dropdown
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPatientDietList() {
		logger.info("Method : getPatientDietList Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_alcohol_list_api").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				orgList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, orgList, ResponseStatus.success, ApiResponseMessage.DATA_FECTH_FAILED);
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientDietList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPatientInsuranceListApi(String userid) {
		logger.info("Method : getPatientInsuranceListApi Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_insurance_list_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2]);
				orgList.add(dropDownModel);
			}

			jsonResponse.setBody(orgList);

			if (orgList.size() > 0) {
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
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientInsuranceListApi Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<InsuranceAPIModel>> getPatientInsuranceDetailsApi(String key) {
		logger.info("Method : getPatientInsuranceDetailsApi Dao starts");

		List<InsuranceAPIModel> orgList = new ArrayList<InsuranceAPIModel>();
		JsonResponse<InsuranceAPIModel> jsonResponse = new JsonResponse<InsuranceAPIModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_insurance_details_api")
					.setParameter("key", key).getResultList();
			for (Object[] m : x) {
				InsuranceAPIModel dropDownModel = new InsuranceAPIModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9], m[10], m[11], m[12], m[13], m[14]);
				orgList.add(dropDownModel);
			}

			jsonResponse.setBody(orgList.get(0));

			if (orgList.size() > 0) {
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
		ResponseEntity<JsonResponse<InsuranceAPIModel>> response = new ResponseEntity<JsonResponse<InsuranceAPIModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientInsuranceDetailsApi Dao ends");

		return response;
	}

	// Medical data upload image function

	public String saveAllInsuranceDocuments(byte[] imageBytes, String ext, String pId) {
		logger.info("Method : saveAllMedicalDocuments starts");

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

			Path path = Paths.get(env.getInsuranceUpload() + imageName);
			if (imageBytes != null) {
				if (pId != null && pId != "") {
					Files.write(path, imageBytes);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : saveAllMedicalDocuments ends");
		return imageName;
	}

	// Add Insurance request Details
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> postInsuranceDetails(InsuranceAPIModel data) {
		logger.info("Method : postInsuranceDetails Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validation = true;
		String policyStDate = null;
		String policyEndDate = null;
		String premiumDueDate = null;

		if (data.getPatientId() == null || data.getPatientId() == "") {
			validation = false;
			jsonResponse.setMessage("Patient ID Required");
		}

		if (data.getPolicyStartDt() != null && data.getPolicyStartDt() != "") {
			policyStDate = DateFormatter.getStringDateNew(data.getPolicyStartDt());
		}

		if (data.getPolicyEndDt() != null && data.getPolicyEndDt() != "") {
			policyEndDate = DateFormatter.getStringDateNew(data.getPolicyEndDt());
		}
		if (data.getPremiumDueDt() != null && data.getPremiumDueDt() != "") {
			premiumDueDate = DateFormatter.getStringDateNew(data.getPremiumDueDt());
		}

		/**************************************
		 * UniqueId for insurance id creation
		 **************************************/
		String uniqueId = null;
		String uniqueInsuranceId = null;
		if (StringUtil.isNull(data.getInsCompany())) {
			validation = false;
			jsonResponse.setMessage("Insurancde company  Required");
		} else {
			uniqueInsuranceId = data.getInsCompany().replace(" ", "").toUpperCase();
			long nowTime = new Date().getTime();
			uniqueId = uniqueInsuranceId + nowTime + generateRandom(5);
		}

		byte[] byteone = null;
		if (!Util.isNull(data.getImg1())) {
			try {
				byteone = data.getImg1().getBytes();
				data.setFileName1(saveAllInsuranceDocuments(byteone, data.getExt1(), uniqueInsuranceId));
			} catch (IOException e) {
				logger.error("File One Error = " + e.getMessage());
				e.printStackTrace();
			}
		}

		byte[] bytetwo = null;
		if (!Util.isNull(data.getImg2())) {
			try {
				bytetwo = data.getImg2().getBytes();
				data.setFileName2(saveAllInsuranceDocuments(bytetwo, data.getExt2(), uniqueInsuranceId));
			} catch (IOException e) {
				logger.error("File Two Error = " + e.getMessage());
				e.printStackTrace();
			}
		}

		byte[] bytethree = null;
		if (!Util.isNull(data.getImg3())) {
			try {
				bytethree = data.getImg3().getBytes();
				data.setFileName3(saveAllInsuranceDocuments(bytethree, data.getExt3(), uniqueInsuranceId));
			} catch (IOException e) {
				logger.error("File Three Error = " + e.getMessage());
				e.printStackTrace();
			}
		}

		byte[] bytefour = null;
		if (!Util.isNull(data.getImg4())) {
			try {
				bytefour = data.getImg4().getBytes();
				data.setFileName4(saveAllInsuranceDocuments(bytefour, data.getExt4(), uniqueInsuranceId));
			} catch (IOException e) {
				logger.error("File Four Error = " + e.getMessage());
				e.printStackTrace();
			}
		}

		// Insurance details list
		String insuranceDetails = "('" + data.getPatientId() + "','" + data.getPolicyNo() + "','" + policyStDate + "','"
				+ policyEndDate + "','" + premiumDueDate + "'," + data.getPremiumAmount() + ","
				+ data.getSumAssuredAmt() + "," + data.getTotalInsAmount() + ",'" + data.getInsCompany() + "','"
				+ data.getInsType() + "','" + data.getHealthInsType() + "','" + uniqueId + "','"
				+ data.getThirdPartyAdm() + "')";

		if (validation) {

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_insurance_details_api")
						.setParameter("insuranceDetails", insuranceDetails).setParameter("ins_uniqueid", uniqueId)
						.setParameter("fileone", data.getFileName1()).setParameter("filetwo", data.getFileName2())
						.setParameter("filethree", data.getFileName3()).setParameter("filefour", data.getFileName4())
						.setParameter("docnameone", data.getDocName1()).setParameter("docnametwo", data.getDocName2())
						.setParameter("docnamethree", data.getDocName3())
						.setParameter("docnamefour", data.getDocName4()).getResultList();

				Util.setJsonResponse(jsonResponse, x.get(0), ResponseStatus.success,
						"Insurance registered successfully.");

			} catch (Exception e) {
				logger.error("Error " + e.getMessage());
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, "Something went wrong. failed.");
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postInsuranceDetails Dao ends");
		return response;
	}

	// Add Patient Life style history

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> postPatientLifeStyle(PatientLifeStyleHistoryModel data) {
		logger.info("Method : postInsuranceDetails Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validation = true;

		if (data.getPatientId() == null || data.getPatientId() == "") {
			validation = false;
			jsonResponse.setMessage("Patient ID Required");
		}

		// Patiet lifestyle history details list
		String lifestylehistory = "(" + data.getSmokingId() + ",'" + data.getPatientId() + "'," + data.getAlcoholId()
				+ ",'" + data.getDiet() + "','" + data.getExercise() + "','" + data.getPets() + "')";
		if (validation) {

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_lifestyle_details_api")
						.setParameter("lifestylehistory", lifestylehistory).setParameter("uid", data.getPatientId())
						.getResultList();
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Saved Successfully");

			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postInsuranceDetails Dao ends");
		return response;
	}

	// Get patient Lifestyle history details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<PatientLifeStyleHistoryModel>> getPatientLifeStyleList(String userid) {
		logger.info("Method : getPatientAmbulanceList Dao starts");

		// List<PatientLifeStyleHistoryModel> patientlifestyleDetails = new
		// ArrayList<PatientLifeStyleHistoryModel>();
		JsonResponse<PatientLifeStyleHistoryModel> jsonResponse = new JsonResponse<PatientLifeStyleHistoryModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_lifestyle_details_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				PatientLifeStyleHistoryModel dropDownModel = new PatientLifeStyleHistoryModel(m[0], m[1], m[2], m[3],
						m[4], m[5], m[6], m[7], m[8], m[9], m[10]);
				jsonResponse.setBody(dropDownModel);

				jsonResponse.setMessage("Data fetched Successfully");
				jsonResponse.setCode("success");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<PatientLifeStyleHistoryModel>> response = new ResponseEntity<JsonResponse<PatientLifeStyleHistoryModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientAmbulanceList Dao ends");
		return response;
	}

	// immunization type list dropdown
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getimmunizationType() {
		logger.info("Method : getimmunizationType Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_immunization_type_api").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				orgList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, orgList, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, orgList, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getimmunizationType Dao ends");

		return response;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> postPatientImmunizationDetails(PatientImmunizationDetailsModel data) {
		logger.info("Method : postPatientImmunizationDetails Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validation = true;
		String date = null;

		if (data.getPatientId() == null || data.getPatientId() == "") {
			validation = false;
			jsonResponse.setMessage("Patient ID Required");
		}

		if (data.getImmunizationDate() != null && data.getImmunizationDate() != "") {
			date = DateFormatter.getStringDateNew(data.getImmunizationDate());
		}

		// immunizationDetails list
		String immunizationDetails = "('" + data.getPatientId() + "','" + data.getImmunizationDetails() + "','" + date
				+ "','" + data.getStatus() + "','" + data.getDoctorName() + "','" + data.getImmunizationId() + "')";

		if (validation) {

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_immunization_details_api")
						.setParameter("immunizationdetails", immunizationDetails).getResultList();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.success, ApiResponseMessage.SAVED_SUCCESSFULLY);

			} catch (Exception e) {
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_FAILURE);
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postPatientImmunizationDetails Dao ends");
		return response;
	}

	// Get patient immunization details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<PatientImmunizationDetailsModel>>> getPatientImmunizationDetails(
			String userid) {
		logger.info("Method : getPatientImmunizationDetails Dao starts");

		List<PatientImmunizationDetailsModel> patientAmbulanceDetails = new ArrayList<PatientImmunizationDetailsModel>();
		JsonResponse<List<PatientImmunizationDetailsModel>> jsonResponse = new JsonResponse<List<PatientImmunizationDetailsModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_immunization_details_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				PatientImmunizationDetailsModel dropDownModel = new PatientImmunizationDetailsModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], m[7]);
				patientAmbulanceDetails.add(dropDownModel);
			}

			if (patientAmbulanceDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, patientAmbulanceDetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, "Failed");
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<PatientImmunizationDetailsModel>>> response = new ResponseEntity<JsonResponse<List<PatientImmunizationDetailsModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientImmunizationDetails Dao ends");
		return response;
	}

	// Get patient immunization details
	public ResponseEntity<JsonResponse<PatientImmunizationDetailsModel>> getPatientImmunizationStatus(String slno,
			String status) {
		logger.info("Method : getPatientImmunizationStatus Dao starts");

		JsonResponse<PatientImmunizationDetailsModel> jsonResponse = new JsonResponse<PatientImmunizationDetailsModel>();
		try {

			em.createNamedStoredProcedureQuery("update_patient_immunization_status_api").setParameter("imslno", slno)
					.setParameter("imstatus", status).getResultList();
			jsonResponse.setMessage("Data fetched successfully");
			jsonResponse.setCode("success");

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<PatientImmunizationDetailsModel>> response = new ResponseEntity<JsonResponse<PatientImmunizationDetailsModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientImmunizationStatus Dao ends");
		return response;
	}

	// get patient family details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<PatientFamilyDetailsModel>>> getPatientFamilyDetailsDao(String userid) {
		logger.info("Method : getPatientFamilyDetailsDao Dao starts");

		List<PatientFamilyDetailsModel> familyHisList = new ArrayList<PatientFamilyDetailsModel>();
		JsonResponse<List<PatientFamilyDetailsModel>> jsonResponse = new JsonResponse<List<PatientFamilyDetailsModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_family_details_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				PatientFamilyDetailsModel dropDownModel = new PatientFamilyDetailsModel(m[0], m[1], m[2], m[3], m[4],
						m[5]);

				familyHisList.add(dropDownModel);

			}
			if (familyHisList.size() > 0) {
				Util.setJsonResponse(jsonResponse, familyHisList, ResponseStatus.success,
						ApiResponseMessage.SAVED_SUCCESSFULLY);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<PatientFamilyDetailsModel>>> response = new ResponseEntity<JsonResponse<List<PatientFamilyDetailsModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientFamilyDetailsDao Dao ends");

		return response;
	}
	// Patient All Medical History

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<APIPatientHistoryModel>> getPatientMedicalHistoryDao(String userid) {
		logger.info("Method : getPatientMedicalHistoryDao Dao starts");

		APIPatientHistoryModel mainList = new APIPatientHistoryModel();

		List<APIPatientMedicalHistoryModel> medicalDetailsList = new ArrayList<APIPatientMedicalHistoryModel>();
		List<APIMajorSurgeryModel> majorSurgeryDetailsList = new ArrayList<APIMajorSurgeryModel>();
		List<APIMedicalConditionModel> medicalConditionList = new ArrayList<APIMedicalConditionModel>();

		JsonResponse<APIPatientHistoryModel> jsonResponse = new JsonResponse<APIPatientHistoryModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_majorillness_history_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				APIPatientMedicalHistoryModel dropDownModel = new APIPatientMedicalHistoryModel(m[0], m[1], m[2]);
				medicalDetailsList.add(dropDownModel);
			}
			mainList.setMedicalHistory(medicalDetailsList);

		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_majorsurgery_history_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				APIMajorSurgeryModel dropDownModel = new APIMajorSurgeryModel(m[0], m[1], m[2], m[3], m[4], m[5]);
				majorSurgeryDetailsList.add(dropDownModel);
			}
			mainList.setMajorsurgery(majorSurgeryDetailsList);

		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_medicalcondition_history_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				APIMedicalConditionModel dropDownModel = new APIMedicalConditionModel(m[0], m[1], m[2]);
				medicalConditionList.add(dropDownModel);
			}
			mainList.setMedicalCondition(medicalConditionList);
		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}

		if (medicalDetailsList.size() > 0 || majorSurgeryDetailsList.size() > 0 || medicalConditionList.size() > 0) {
			Util.setJsonResponse(jsonResponse, mainList, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
		} else {
			Util.setJsonResponse(jsonResponse, mainList, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
		}

		ResponseEntity<JsonResponse<APIPatientHistoryModel>> response = new ResponseEntity<JsonResponse<APIPatientHistoryModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientMedicalHistoryDao Dao ends");
		return response;
	}

	// *for Aditya Birla Insurance*//

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> gettitleList() {
		logger.info("Method : gettitleList Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_insurance_title_dropdown")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				orgList.add(dropDownModel);
			}
			jsonResponse.setBody(orgList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : gettitleList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getrelationCodeList() {
		logger.info("Method : getrelationCodeList Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_insurance_relationcode_dropdown")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				orgList.add(dropDownModel);
			}
			jsonResponse.setBody(orgList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getrelationCodeList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getmaritalStatusList() {
		logger.info("Method : getmaritalStatusList Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_insurance_maritalstatus_dropdown")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				orgList.add(dropDownModel);
			}
			jsonResponse.setBody(orgList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getmaritalStatusList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getoccupationList() {
		logger.info("Method : getoccupationList Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_insurance_occupation_dropdown")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				orgList.add(dropDownModel);
			}
			jsonResponse.setBody(orgList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getoccupationList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> insurancePincodeAutosearch(String id) {
		logger.info("Method : insurancePincodeAutosearch Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_insurance_pincode_dropdown")
					.setParameter("search", id).getResultList();
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

		logger.info("Method : insurancePincodeAutosearch Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> registerinsuranceAbsDao(JsonNode data) {
		logger.info("Method : registerinsuranceAbsDao Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> result = mapper.convertValue(data, new TypeReference<Map<String, Object>>() {
		});
		Map<String, Object> client = mapper.convertValue(result.get("ClientCreation"),
				new TypeReference<Map<String, Object>>() {
				});
		Map<String, Object> policyCreationRequest = mapper.convertValue(result.get("PolicyCreationRequest"),
				new TypeReference<Map<String, Object>>() {
				});
		Map<String, Object> memObj = mapper.convertValue(result.get("MemObj"),
				new TypeReference<Map<String, Object>>() {
				});
		Map<String, Object> receiptCreation = mapper.convertValue(result.get("ReceiptCreation"),
				new TypeReference<Map<String, Object>>() {
				});
		// InsuranceAdbsModel objectmap = mapper.convertValue(data, new
		// TypeReference<InsuranceAdbsModel>() {
//		});


		// logger.info("policyCreationRequest "+
		// policyCreationRequest.get("PolicyproductComponents"));
		List<Map<String, Object>> policyproductComponents = mapper.convertValue(
				policyCreationRequest.get("PolicyproductComponents"), new TypeReference<List<Map<String, Object>>>() {
				});
		List<Map<String, Object>> member = mapper.convertValue(memObj.get("Member"),
				new TypeReference<List<Map<String, Object>>>() {
				});
		logger.info("member" + member);
		List<Map<String, Object>> memberproductComponents = mapper.convertValue(
				member.get(0).get("MemberproductComponents"), new TypeReference<List<Map<String, Object>>>() {
				});
		logger.info("MemberproductComponents" + memberproductComponents);

		
		
		
		
		List<Map<String, Object>> memberQuestionDetails = mapper.convertValue(
				memberproductComponents.get(0).get("MemberQuestionDetails"), new TypeReference<List<Map<String, Object>>>() {
				});
		logger.info("memberQuestionDetails" + memberQuestionDetails);

		List<Map<String, Object>> memberPED = mapper.convertValue(member.get(0).get("MemberPED"),
				new TypeReference<List<Map<String, Object>>>() {
				});
		logger.info("memberPED" + memberPED);

		/*
		 * try { InsuranceAdbsModel objectmap = mapper.treeToValue(data,
		 * InsuranceAdbsModel.class);
		 * logger.info("data"+mapper.writeValueAsString(objectmap)); } catch
		 * (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		
		  String uniqueId = null;
		  
			UUID uuid=UUID.randomUUID();
			
			uniqueId= uuid.toString();
			logger.info("uuid====="+uniqueId);
			
			String docdate = null;
			if (client.get("dateofBirth") != null && client.get("dateofBirth") != "") {
				docdate = DateFormatter.getStringDateNew(client.get("dateofBirth").toString());
			}
			
			/*
			 * String startdates = null; if (client.get("startDate") != null &&
			 * client.get("startDate") != "") { startdates =
			 * DateFormatter.getStringDateNew(client.get("startDate").toString()); } 
			 */
			String enddates = null;
			if (client.get("endDate") != null && client.get("endDate") != "") {
				enddates = (client.get("endDate").toString());
			}
			
			
logger.info("docdate"+docdate);
		Boolean validity = true;

		
		List<Object[]> x = null;

		if (validity) {
			try {
				if (!StringUtil.isNull(client.get("Member_Customer_ID"))) {
					System.out.println("sal====" + client.get("dateofBirth"));
					
					x = em.createNamedStoredProcedureQuery("register_Patient_By_Insuranse")
							// .setParameter("client", client.get("Member_Customer_ID"))

							.setParameter("Member_Customer_ID", client.get("Member_Customer_ID"))
							.setParameter("salutation", client.get("salutation"))
							.setParameter("firstName", client.get("firstName"))
							.setParameter("middleName", client.get("middleName"))
							.setParameter("lastName", client.get("lastName"))
							.setParameter("dateofBirth", docdate)
							.setParameter("genderId", client.get("gender"))
							.setParameter("educationalQualification", client.get("educationalQualification"))
							.setParameter("pinCode", client.get("pinCode"))
							.setParameter("uhidNo", client.get("uidNo"))
							.setParameter("maritalstatus", client.get("maritalStatus"))
							.setParameter("natiality", client.get("nationality"))
							.setParameter("occupation", client.get("occupation"))
							.setParameter("primaryEmailID", client.get("primaryEmailID"))
							.setParameter("contactMobileNo", client.get("contactMobileNo"))
							.setParameter("stdLandlineNo", client.get("stdLandlineNo"))
							.setParameter("panNo", client.get("panNo"))
							.setParameter("passportNumber", client.get("passportNumber"))
							.setParameter("contactPerson", client.get("contactPerson"))
							.setParameter("annualIncome", client.get("annualIncome"))

							.setParameter("remarks", client.get("remarks"))
							.setParameter("startDate", client.get("startDate"))
							.setParameter("endDate",enddates)
							.setParameter("IdProof", client.get("IdProof"))
							.setParameter("residenceProof", client.get("residenceProof"))
							.setParameter("ageProof", client.get("ageProof"))
							.setParameter("others", client.get("others"))
							.setParameter("homeAddressLine1", client.get("homeAddressLine1"))
							.setParameter("homeAddressLine2", client.get("homeAddressLine2"))
							.setParameter("homeAddressLine3", client.get("homeAddressLine3"))
							.setParameter("homePinCode", client.get("homePinCode"))
							.setParameter("homeArea", client.get("homeArea"))
							.setParameter("homeContactMobileNo", client.get("homeContactMobileNo"))

							.setParameter("homeContactMobileNo2", client.get("homeContactMobileNo2"))
							.setParameter("homeSTDLandlineNo", client.get("homeSTDLandlineNo"))
							.setParameter("homeFaxNo", client.get("homeFaxNo"))
							.setParameter("sameAsHomeAddress", client.get("sameAsHomeAddress"))
							.setParameter("mailingAddressLine1", client.get("mailingAddressLine1"))
							.setParameter("mailingAddressLine2", client.get("mailingAddressLine2"))
							.setParameter("mailingAddressLine3", client.get("mailingAddressLine3"))
							.setParameter("mailingPinCode", client.get("mailingPinCode"))
							.setParameter("mailingArea", client.get("mailingArea"))
							.setParameter("mailingContactMobileNo", client.get("mailingContactMobileNo"))
							.setParameter("mailingContactMobileNo2", client.get("mailingContactMobileNo2"))
							.setParameter("mailingSTDLandlineNo", client.get("mailingSTDLandlineNo"))
							.setParameter("mailingSTDLandlineNo2", client.get("mailingSTDLandlineNo2"))
							.setParameter("mailingFaxNo", client.get("mailingFaxNo"))

							.setParameter("bankAccountType", client.get("bankAccountType"))
							.setParameter("bankAccountNo", client.get("bankAccountNo"))
							.setParameter("ifscCode", client.get("ifscCode")).setParameter("gstin", client.get("GSTIN"))
							.setParameter("GSTRegistrationStatus", client.get("GSTRegistrationStatus"))
							.setParameter("IsEIAavailable", client.get("IsEIAavailable"))
							.setParameter("ApplyEIA", client.get("ApplyEIA"))
							.setParameter("EIAAccountNo", client.get("EIAAccountNo"))
							.setParameter("EIAWith", client.get("EIAWith"))
							.setParameter("AccountType", client.get("AccountType"))
							.setParameter("AddressProof", client.get("AddressProof"))
							.setParameter("DOBProof", client.get("DOBProof"))
							.setParameter("IdentityProof", client.get("IdentityProof"))
							
							.setParameter("uuidq", uniqueId)
							.setParameter("quotation_Number", policyCreationRequest.get("Quotation_Number"))
							.setParameter("msterPolicyNumber", policyCreationRequest.get("MasterPolicyNumber"))
							.setParameter("groupID", policyCreationRequest.get("GroupID"))
							.setParameter("product_Code", policyCreationRequest.get("Product_Code"))
							.setParameter("intermediaryCode", policyCreationRequest.get("intermediaryCode"))
							/*
							 * .setParameter("imdCode", policyCreationRequest.get("ImdCode"))
							 * .setParameter("productCode", policyCreationRequest.get("ProductCode"))
							 * .setParameter("smCode", policyCreationRequest.get("SmCode"))
							 * 
							 * .setParameter("smName", policyCreationRequest.get("SmName"))
							 */
							.setParameter("autoRenewal", policyCreationRequest.get("AutoRenewal"))
							.setParameter("intermediaryBranchCode", policyCreationRequest.get("IntermediaryBranchCode"))
							.setParameter("agentSignatureDate", policyCreationRequest.get("agentSignatureDate"))
							.setParameter("customer_Signature_Date",policyCreationRequest.get("Customer_Signature_Date"))
							.setParameter("businessSourceChannel", policyCreationRequest.get("businessSourceChannel"))
							.setParameter("assignPolicy", policyCreationRequest.get("AssignPolicy"))
							.setParameter("assigneeName", policyCreationRequest.get("AssigneeName"))
							.setParameter("leadID", policyCreationRequest.get("leadID"))
							.setParameter("source_Name", policyCreationRequest.get("Source_Name"))
							.setParameter("sPID", policyCreationRequest.get("SPID"))
							.setParameter("tCN", policyCreationRequest.get("TCN"))
							.setParameter("cRTNO", policyCreationRequest.get("CRTNO"))
							.setParameter("refCode1", policyCreationRequest.get("RefCode1"))
							.setParameter("refCode2", policyCreationRequest.get("RefCode2"))
							.setParameter("employee_Number", policyCreationRequest.get("Employee_Number"))
							.setParameter("enumIsEmployeeDiscount", policyCreationRequest.get("enumIsEmployeeDiscount"))
							.setParameter("quoteDate", policyCreationRequest.get("QuoteDate"))
							.setParameter("isPayment", policyCreationRequest.get("IsPayment"))
							.setParameter("paymentMode", policyCreationRequest.get("PaymentMode"))
							
							.setParameter("PlanCode", policyproductComponents.get(0).get("PlanCode"))
							.setParameter("SumInsured", policyproductComponents.get(0).get("SumInsured"))
							.setParameter("SchemeCode", policyproductComponents.get(0).get("SchemeCode"))
							.getResultList();
					
							if (StringUtil.isNull(data.get("MemberNo"))) {
								x = em.createNamedStoredProcedureQuery("register_Patient_By_Insuranse_memberList")
										.setParameter("memno",member.get(0).get("MemberNo"))
										.setParameter("salution", member.get(0).get("Salutation"))
										.setParameter("fnamee", member.get(0).get("First_Name"))
										.setParameter("mnamee", member.get(0).get("Middle_Name"))
										.setParameter("lanamee",member.get(0).get("Last_Name"))
										.setParameter("gndr", member.get(0).get("Gender"))
										.setParameter("dobb", member.get(0).get("DateOfBirth"))
										.setParameter("relcd", member.get(0).get("Relation_Code"))
										.setParameter("marsts", member.get(0).get("Marital_Status"))
										.setParameter("ht", member.get(0).get("height"))
										.setParameter("wt", member.get(0).get("weight"))
										.setParameter("occp", member.get(0).get("occupation"))
										.setParameter("primemb", member.get(0).get("PrimaryMember"))
										
									     .setParameter("extdignosis",member.get(0).get("exactDiagnosis"))
										.setParameter("dtdignsis", member.get(0).get("dateOfDiagnosis"))
										.setParameter("lastdtcons", member.get(0).get("lastDateConsultation"))
										.setParameter("detailstrtment", member.get(0).get("detailsOfTreatmentGiven"))
										.setParameter("docnm",member.get(0).get("doctorName"))
										.setParameter("hospitalnm", member.get(0).get("hospitalName"))
										.setParameter("phnnumbhos", member.get(0).get("phoneNumberHosital"))
										.setParameter("nominneefname", member.get(0).get("Nominee_First_Name"))
										.setParameter("nomineelname", member.get(0).get("Nominee_Last_Name"))
										.setParameter("nomineecntnumb", member.get(0).get("Nominee_Contact_Number"))
										.setParameter("nomineehmadrr", member.get(0).get("Nominee_Home_Address"))
										.setParameter("nomineerelcd", member.get(0).get("Nominee_Relationship_Code"))
										
										
										.setParameter("plncd",memberproductComponents.get(0).get("PlanCode"))
										
										.setParameter("qescd", memberQuestionDetails.get(0).get("QuestionCode"))
										.setParameter("ans", memberQuestionDetails.get(0).get("Answer"))
										.setParameter("remks", memberQuestionDetails.get(0).get("Remarks"))
										
										.setParameter("pedcd", memberPED.get(0).get("PEDCode"))
										.setParameter("remkss", memberPED.get(0).get("PaymentMode"))
										
										.setParameter("uuidqs", uniqueId)
										.setParameter("offcloc",receiptCreation.get("officeLocation"))
										.setParameter("mdfentry", receiptCreation.get("modeOfEntry"))
										.setParameter("cdcno", receiptCreation.get("cdAcNo"))
										.setParameter("expdt", receiptCreation.get("expiryDate"))
										.setParameter("pyrtyp",receiptCreation.get("payerType"))
										.setParameter("pyrcd", receiptCreation.get("payerCode"))
										.setParameter("pymtby", receiptCreation.get("paymentBy"))
										.setParameter("pymtnm", receiptCreation.get("paymentByName"))
										.setParameter("pymtbyrelcd", receiptCreation.get("paymentByRelationship"))
										.setParameter("collectnamt",receiptCreation.get("collectionAmount"))
										.setParameter("collectmd", receiptCreation.get("collectionMode"))
										.setParameter("rems", receiptCreation.get("remarks"))
										.setParameter("instrumntno", receiptCreation.get("instrumentNumber"))
										.setParameter("instrumntdt", receiptCreation.get("instrumentDate"))
										.setParameter("bnknam", receiptCreation.get("bankName"))
										.setParameter("brnhnm", receiptCreation.get("branchName"))
										.setParameter("bnkloc", receiptCreation.get("bankLocation"))
										.setParameter("micrn", receiptCreation.get("micrNo"))
										.setParameter("chktyp", receiptCreation.get("chequeType"))
										.setParameter("ifccd", receiptCreation.get("ifscCode"))
										.setParameter("pymntgtwaynm", receiptCreation.get("PaymentGatewayName"))
										.setParameter("terminlid", receiptCreation.get("TerminalID"))
										.setParameter("crdno", receiptCreation.get("CardNo"))
										
										.getResultList();
										
							}
							

					jsonResponse.setBody(x.get(0));
				}
				
				if (!StringUtil.isNull(jsonResponse)) {
					
						Util.setJsonResponse(jsonResponse, uniqueId, ResponseStatus.success,
								ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
						
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.REGISTRATION_FAILED);
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : signUpByPathologistDao Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> registerpaymentgatewayDao(InstamozoApiModel order) {
		logger.info("Method : registerpaymentgatewayDao Dao starts");

		/*
		 * JsonResponse<Object> jsonResponse = new JsonResponse<Object>(); ObjectMapper
		 * mapper = new ObjectMapper();
		 * 
		 * Map<String, Object> result = mapper.convertValue(order, new
		 * TypeReference<Map<String, Object>>() { }); logger.info("0rder====="+result);
		 * Boolean validity = true;
		 * 
		 * List<Object[]> x = null;
		 * 
		 * try {
		 * 
		 * if (validity) { try { if (StringUtil.isNull(order.get("transaction_id"))) {
		 * logger.info("dataaa222===="+order.get("transaction_id")); x =
		 * em.createNamedStoredProcedureQuery("insert_instamozo_payment_status")
		 * .setParameter("payment_id",order.get("payment_id"))
		 * .setParameter("payment_status", order.get("payment_status"))
		 * .setParameter("id", order.get("id")) .setParameter("transaction_id",
		 * order.get("transaction_id")) .setParameter("uniqueid",order.get("uniqueid"))
		 * 
		 * 
		 * 
		 * .getResultList();
		 * 
		 * 
		 * 
		 * jsonResponse.setBody(x.get(0));
		 * 
		 * }
		 * 
		 * if (!StringUtil.isNull(jsonResponse)) {
		 * 
		 * Util.setJsonResponse(jsonResponse, null, ResponseStatus.success,
		 * ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
		 * 
		 * } } catch (Exception e) { e.printStackTrace();
		 * Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
		 * ApiResponseMessage.REGISTRATION_FAILED); } } }catch(Exception e){
		 * logger.error("e"+e); e.printStackTrace(); }
		 * 
		 * 
		 * 
		 * ResponseEntity<JsonResponse<Object>> response = new
		 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK);
		 * 
		 * 
		 */
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validation = true;

		if (validation) {

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("insert_instamozo_payment_status")
						.setParameter("payment_id",order.getPayment_id())
						.setParameter("payment_status", order.getPayment_status())
						.setParameter("id", order.getId())
						.setParameter("transaction_id", order.getTransaction_id())
						.setParameter("uniqueid",order.getUniqueid())
						.getResultList();
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Saved Successfully");

			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);


		logger.info("Method : registerpaymentgatewayDao Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<InstamozoApiModel>>> getinsuranceAdityapatientdetails(
			String userid) {
		logger.info("Method : getinsuranceAdityapatientdetails Dao starts");

		List<InstamozoApiModel> patientAmbulanceDetails = new ArrayList<InstamozoApiModel>();
		JsonResponse<List<InstamozoApiModel>> jsonResponse = new JsonResponse<List<InstamozoApiModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_adityabirla_insurance_details_api")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				InstamozoApiModel dropDownModel = new InstamozoApiModel(null, m[0], null,
						null, m[1], m[2], m[3],m[4]);
				patientAmbulanceDetails.add(dropDownModel);
			}

			if (patientAmbulanceDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, patientAmbulanceDetails, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, "Failed");
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<InstamozoApiModel>>> response = new ResponseEntity<JsonResponse<List<InstamozoApiModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getinsuranceAdityapatientdetails Dao ends");
		return response;
	}

}
