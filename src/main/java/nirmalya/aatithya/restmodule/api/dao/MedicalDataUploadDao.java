package nirmalya.aatithya.restmodule.api.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.MedicalDataUploadModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class MedicalDataUploadDao {

	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(MedicalDataUploadDao.class);

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> documentTypeList() {
		logger.info("Method : documentTypeList Dao starts");

		List<DropDownModel> typeList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_document_typelist_api").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1],m[2]);
				typeList.add(dropDownModel);
			}
			jsonResponse.setBody(typeList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : documentTypeList Dao ends");

		return response;
	}

	
	
	// Add Medical data upload
		@SuppressWarnings({ "unchecked", "unused" })
		public ResponseEntity<JsonResponse<Object>> medicalDataUpload(MedicalDataUploadModel data) {
			logger.info("Method : medicalDataUpload Dao starts");

			JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
			Boolean validation = true;
			String docdate = null;
			if (data.getUploadDate() != null && data.getUploadDate() != "") {
				docdate = DateFormatter.getStringDateNew(data.getUploadDate());
			}
			if (StringUtil.isNull(data.getUserid())) {
				validation = false;
				jsonResponse.setMessage("Patient id Required");
			}
			if(data.getUploadedBy().equals("1")) {
				data.setUploadedBy("user");
			}else if(data.getUploadedBy().equals("2"))  {
				data.setUploadedBy("doctor");
			}else {
				data.setUploadedBy("other");
			}
			
			// DataUpload Details list
			String dataUploadDetails = "('" + data.getUserid() + "','" + data.getDocName() + "','"
					+ data.getDocType() + "','"+ data.getFileName() + "','"+ data.getUploadedBy() + "','"  + docdate + "')";

			if (validation) {

				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_medical_dataupload_api")
							.setParameter("dataUploaddetails", dataUploadDetails).getResultList();
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.success,ApiResponseMessage.UPLOADED_SUCCESSFULLY);

				} catch (Exception e) {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,e.getMessage());
					
				}
			}
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
					HttpStatus.OK);

			logger.info("Method : medicalDataUpload Dao ends");
			return response;
		}

		// Get patient Document details
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<MedicalDataUploadModel>>> getMedicalDataUploadList(String userid,String page,String typeid) {
			logger.info("Method : getMedicalUploadist Dao starts");

			List<MedicalDataUploadModel> patientuploadDetails = new ArrayList<MedicalDataUploadModel>();
			JsonResponse<List<MedicalDataUploadModel>> jsonResponse = new JsonResponse<List<MedicalDataUploadModel>>();
			try {
				Integer pageno = Integer.parseInt(page);
				Integer typeidno = Integer.parseInt(typeid);
				
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_medicalupload_details")
						.setParameter("userid", userid)
						.setParameter("pageno", pageno)
						.setParameter("typeidno", typeidno).getResultList();
				String fileName=null;
				for (Object[] m : x) {

					MedicalDataUploadModel dropDownModel = new MedicalDataUploadModel(m[0], m[1], m[2],null,null,null);
					patientuploadDetails.add(dropDownModel);
				}  
					for(MedicalDataUploadModel a:patientuploadDetails) {
						fileName = env.getBaseURL()+"cureeazyrest/document/record/"+a.getFileName();
						//fileName = env.getBaseURL()+"nirmalyaRest/document/record/" +a.getFileName();
							a.setFileName(fileName);
					
				}

				if (patientuploadDetails.size() > 0) {
					Util.setJsonResponse(jsonResponse, patientuploadDetails, ResponseStatus.success,ApiResponseMessage.DATA_FETCH_SUCCESS);
				} else {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,ApiResponseMessage.DATA_FECTH_FAILED);
				}

			} catch (Exception e) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,e.getMessage());
			}
			ResponseEntity<JsonResponse<List<MedicalDataUploadModel>>> response = new ResponseEntity<JsonResponse<List<MedicalDataUploadModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getMedicalUploadist Dao ends");
			return response;
		}
		

		// Get patient Document by limit
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<MedicalDataUploadModel>>> getDocumentListByLimit(String userid) {
			logger.info("Method : getDocumentListByLimit Dao starts");

			List<MedicalDataUploadModel> patientuploadDetails = new ArrayList<MedicalDataUploadModel>();
			JsonResponse<List<MedicalDataUploadModel>> jsonResponse = new JsonResponse<List<MedicalDataUploadModel>>();
			try {
				
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_medicalupload_details_bylimit")
						.setParameter("userid", userid).getResultList();
				
				String fileName=null;
				for (Object[] m : x) {

					MedicalDataUploadModel dropDownModel = new MedicalDataUploadModel(m[0], m[1], m[2],m[3], m[4],null);
					patientuploadDetails.add(dropDownModel);
				}  
					for(MedicalDataUploadModel a:patientuploadDetails) {
						//fileName = env.getBaseURL()+"nirmalyaRest/document/record/"+a.getFileName();
						fileName = env.getBaseURL()+"cureeazyrest/document/record/" +a.getFileName();
							a.setFileName(fileName);
					
				}

				if (patientuploadDetails.size() > 0) {
					Util.setJsonResponse(jsonResponse, patientuploadDetails, ResponseStatus.success,ApiResponseMessage.DATA_FETCH_SUCCESS);
				} else {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,ApiResponseMessage.DATA_FECTH_FAILED);
				}

			} catch (Exception e) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,e.getMessage());
			}
			ResponseEntity<JsonResponse<List<MedicalDataUploadModel>>> response = new ResponseEntity<JsonResponse<List<MedicalDataUploadModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getDocumentListByLimit Dao ends");
			return response;
		}


}
