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

import nirmalya.aatithya.restmodule.api.model.DeliveryProcessAPIModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class DeliveryProcessAPIDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(DeliveryProcessAPIDao.class);
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DeliveryProcessAPIModel>>> viewMedicineDetailsByOrgIdDao(DropDownModel dropDownModel) {
		logger.info("Method : viewMedicineDetailsByOrgIdDao Dao starts");
		
		List<DeliveryProcessAPIModel> medicinelist = new ArrayList<DeliveryProcessAPIModel>();
		JsonResponse<List<DeliveryProcessAPIModel>> jsonResponse = new JsonResponse<List<DeliveryProcessAPIModel>>();
		String[] arr = dropDownModel.getKey().split(",");
		String[] arr1 = dropDownModel.getName().split(",");
		String medid = "";
		String appointid = "";
		if(!StringUtil.isNull(dropDownModel.getKey())) {
			for(String m : arr) {
				medid = medid + "'" + m + "',";
			}
			medid = "("+medid.substring(0, medid.length() - 1)+")";
			//medid =medid.substring(0, medid.length() - 2);
		}
		if(!StringUtil.isNull(dropDownModel.getName())) {
			for(String m : arr1) {
				appointid = appointid + "'" + m + "',";
			}
			appointid = "("+appointid.substring(0, appointid.length() - 1)+")";
			//appointid =appointid.substring(0, appointid.length() - 2);
		}
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_medicine_details_for_delivery_process")
					.setParameter("organizationid", dropDownModel.getCode())
					.setParameter("medicineid", medid)
					.setParameter("appointmentid", appointid)
					.getResultList();
			for (Object[] m : x) {
				DeliveryProcessAPIModel dropDownModel1 = new DeliveryProcessAPIModel(m[0], m[1], m[2], m[3], m[4],m[5],m[6],null);
				medicinelist.add(dropDownModel1);
			} 
			jsonResponse.setBody(medicinelist);
			if (medicinelist.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
			logger.info("Message :"+jsonResponse.getMessage());
		}
		ResponseEntity<JsonResponse<List<DeliveryProcessAPIModel>>> response = new ResponseEntity<JsonResponse<List<DeliveryProcessAPIModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewMedicineDetailsByOrgIdDao Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DeliveryProcessAPIModel>>> viewTestDetailsByOrgIdDao(DropDownModel dropDownModel) {
		logger.info("Method : viewTestDetailsByOrgIdDao Dao starts");
		
		List<DeliveryProcessAPIModel> medicinelist = new ArrayList<DeliveryProcessAPIModel>();
		JsonResponse<List<DeliveryProcessAPIModel>> jsonResponse = new JsonResponse<List<DeliveryProcessAPIModel>>();
		try {
			String[] arr = dropDownModel.getKey().split(",");
			String testid = "";
			if(!StringUtil.isNull(dropDownModel.getKey())) {
				for(String m : arr) {
					testid = testid + "'" + m + "',";
				}
				testid = "("+testid.substring(0, testid.length() - 1)+")";
				//medid =medid.substring(0, medid.length() - 2);
			}
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_test_details_for_delivery_process")
					 .setParameter("p_testid", testid).setParameter("p_labid", dropDownModel.getName())
					.getResultList();
			if(!StringUtil.isNull(x)) {
				for (Object[] m : x) {
					DeliveryProcessAPIModel dropDownModel1 = new DeliveryProcessAPIModel(m[0], m[1], m[2], m[3], m[4],m[5],m[6],m[7],null);
					medicinelist.add(dropDownModel1);
				} 
			}
			jsonResponse.setBody(medicinelist);
			if (medicinelist.size() > 0) {
				Util.setJsonResponse(jsonResponse, medicinelist, ResponseStatus.success,  ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,  ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,  ApiResponseMessage.DATA_FECTH_FAILED);
		}
		ResponseEntity<JsonResponse<List<DeliveryProcessAPIModel>>> response = new ResponseEntity<JsonResponse<List<DeliveryProcessAPIModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewTestDetailsByOrgIdDao Dao ends");
		return response;
	}
}
