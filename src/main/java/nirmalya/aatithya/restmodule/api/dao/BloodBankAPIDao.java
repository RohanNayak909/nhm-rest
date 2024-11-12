package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.APIDoctorMonthlyOverviewModel;
import nirmalya.aatithya.restmodule.api.model.BloodBankAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyDrAppointBookingAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyAddtoCartModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyPaymentModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class BloodBankAPIDao {
	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;
	
	@Autowired
	ServerDao serverDao;

	Logger logger = LoggerFactory.getLogger(BloodBankAPIDao.class);

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postCreateCommunity(BloodBankAPIModel data) {
		logger.info("Method : postCreateCommunity Dao starts");
	
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;

		if (data.getCommunityName() == null || data.getCommunityName() == "") {
			jsonResponse.setMessage("Community Name Required");
			validity = false;
		}
	
		if (validity) {
			try {
				if (data.getUserId() != null && data.getUserId() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_create_community")
							.setParameter("userId", data.getUserId())
							.setParameter("communityName", data.getCommunityName())
							.setParameter("communityDesc", data.getCommunityDesc()).getResultList();
					jsonResponse.setBody(x.get(0));
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.UPLOADED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : postCreateCommunity Dao ends");
		return response;
	}
	// get medicine dropdown
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getCommunityList() {
			logger.info("Method : getCommunityList Dao starts");

			List<DropDownModel> communityList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_community_dropdown_list").getResultList();
				for (Object[] m : x) {

					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					communityList.add(dropDownModel);
				}
				jsonResponse.setBody(communityList);

				if (communityList.size() > 0) {
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
			logger.info("Method : getCommunityList Dao ends");
			return response;
		}
		
		// get medicine dropdown
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<DropDownModel>>> getBloodUnitList() {
					logger.info("Method : getBloodUnitList Dao starts");

					List<DropDownModel> bloodUnitList = new ArrayList<DropDownModel>();
					JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
					try {

						List<Object[]> x = em.createNamedStoredProcedureQuery("get_bloodunit_dropdown_list").getResultList();
						for (Object[] m : x) {

							DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
							bloodUnitList.add(dropDownModel);
						}
						jsonResponse.setBody(bloodUnitList);

						if (bloodUnitList.size() > 0) {
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
					logger.info("Method : getBloodUnitList Dao ends");
					return response;
				}
				
			// Community request api	
				@SuppressWarnings({ "unlikely-arg-type", "unchecked" })

				public ResponseEntity<JsonResponse<BloodBankAPIModel>> postRequestCommunity(BloodBankAPIModel bloodBankAPIModel) {

					logger.info("Method : postRequestCommunity starts" + bloodBankAPIModel);

					System.out.println("regdModel===" + bloodBankAPIModel);
					JsonResponse<BloodBankAPIModel> resp = new JsonResponse<BloodBankAPIModel>();
					BloodBankAPIModel dlist = new BloodBankAPIModel();
				
					String communityList = "";
				
					if (bloodBankAPIModel.getCommunityList() != null) {
						if (bloodBankAPIModel.getCommunityList().size() > 0) {
							for (DropDownModel m : bloodBankAPIModel.getCommunityList()) {
								communityList = communityList + "(order_id1,\'" + m.getKey() + "\',\'" + m.getName() + "\'),";
								logger.info("lossssst" + communityList);
							}
							communityList = communityList.substring(0, communityList.length() - 1);
						} else {
							communityList = "";
						}
					}
					System.out.println("communityList===" + communityList);
						try {
							List<Object[]> x = em.createNamedStoredProcedureQuery("post_community_request_api")
									.setParameter("userid", bloodBankAPIModel.getUserId())
									.setParameter("unitId",bloodBankAPIModel.getUnitId())
									.setParameter("bldGrpId", bloodBankAPIModel.getBldGroupId())
									.setParameter("valuess", communityList).getResultList();
							logger.info("Patient" + x);

							
							resp.setBody(dlist);
						/*
								 * if (resp.getMessage() == null) {
							 * 
							 * resp.setBody(x.get(0)); logger.info("iddvle" + resp); }
							 */
							 
							resp.setCode("success");
							resp.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
						} catch (Exception e) {
							try {
								String[] err = serverDao.errorProcedureCall(e);
								resp.setCode(err[0]);
								resp.setMessage(err[1]);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					ResponseEntity<JsonResponse<BloodBankAPIModel>> response = new ResponseEntity<JsonResponse<BloodBankAPIModel>>(
							resp, HttpStatus.OK);
					logger.info("Method : postRequestCommunity ends");
					return response;
				}
}
