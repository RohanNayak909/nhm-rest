package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.CouponApiModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyTestListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.MedicineApiModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionTestModel;
import nirmalya.aatithya.restmodule.api.model.WalletListApiModel;
import nirmalya.aatithya.restmodule.api.model.WalletModel;
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
public class CouponDao {
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(CouponDao.class);
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<CouponApiModel>> getCouponList(String userid) {
		logger.info("Method : getCouponList Dao starts");

		List<CouponApiModel> couponList = new ArrayList<CouponApiModel>();
		JsonResponse<List<CouponApiModel>> jsonResponse = new JsonResponse<List<CouponApiModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_coupon_list")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				String image = null;
				if (m[4] != null && m[4] != "" && m[4] != " " && !m[4].toString().equals(" ")
						&& !m[4].toString().equals(null) && !m[4].toString().equals("")) {
					image = env.getBaseURL() + "cureeazyrest/document/document/" + m[4].toString();
				} else {
					image = "";
				}
				Object startDate = null;
				if (m[5] != null) {
					startDate = DateFormatter.returnStringDateNew(m[5].toString());
				}
				Object endDate = null;
				if (m[6] != null) {
					endDate = DateFormatter.returnStringDateNew(m[6].toString());
				}
				Object expiryDate = null;
				if (m[7] != null) {
					expiryDate = DateFormatter.returnStringDateNew(m[7].toString());
				}
				CouponApiModel dropDownModel = new CouponApiModel(m[0], m[1], m[2], m[3],
						image, startDate,endDate,  expiryDate, m[8],m[9]);
				couponList.add(dropDownModel);
				}
			jsonResponse.setBody(couponList);
			

			 if (couponList.size() > 0) { 
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
		
		System.out.println(jsonResponse);
		logger.info("Method : getCouponList Dao ends");
		return jsonResponse;
	}

	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<CouponApiModel>> getCouponCode(String userid,String couponCode) {
		logger.info("Method : getCouponCode Dao starts");

		List<CouponApiModel> couponList = new ArrayList<CouponApiModel>();
		JsonResponse<List<CouponApiModel>> jsonResponse = new JsonResponse<List<CouponApiModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_coupon_code")
					.setParameter("userid", userid).setParameter("couponCode", couponCode).getResultList();
			
			String code="";
			for (Object[] m : x) {
			
				CouponApiModel dropDownModel = new CouponApiModel(m[0], m[1],m[2],m[3]);
				couponList.add(dropDownModel);
				
				code=dropDownModel.getCouponName();
				}
			System.out.println("code"+code);
			if(couponCode.equals(code) && !StringUtil.isNull(code)) {
				jsonResponse.setBody(couponList);
				jsonResponse.setMessage("Coupon Applied Successfully");
				jsonResponse.setCode("Success");
			}
			
			else if(!couponCode.equals(code)) {
				
				
				jsonResponse.setMessage("Invalid Coupon");
				jsonResponse.setCode("failed");
			}
			else if(StringUtil.isNull(code)){
				
				jsonResponse.setMessage("Please Enter Coupon Code");
				jsonResponse.setCode("failed");
			}
			
		
			/*
			 * if (couponList.size() > 0) { jsonResponse.setCode("success");
			 * jsonResponse.setMessage("Data Fetched Successfully"); } else {
			 * jsonResponse.setCode("success"); jsonResponse.setMessage("No Data Found"); }
			 */
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		
		System.out.println(jsonResponse);
		logger.info("Method : getCouponCode Dao ends");
		return jsonResponse;
	}
//Wallet Price
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<WalletModel>> getWalletPrice(String userId) {
		logger.info("Method : getWalletPrice Dao starts");

		WalletModel walletModel = new WalletModel();
		JsonResponse<WalletModel> jsonResponse = new JsonResponse<WalletModel>();
		try {
			System.out.println(userId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_wallet_price")
					.setParameter("userid", userId).getResultList();
			for (Object[] m : x) {
				WalletModel dropDownModel = new WalletModel(null, null, null, m[0], m[1], null);
				walletModel = dropDownModel;
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}

		// Description
		List<WalletListApiModel> description = new ArrayList<WalletListApiModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_wallet_description")
					.setParameter("userid", userId).getResultList();
			for (Object[] m : x1) {
				WalletListApiModel dropDownModel = new WalletListApiModel(null, null, m[0], m[1], m[2],null);
				description.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	
		jsonResponse.setBody(walletModel);
		walletModel.setWalletDesc(description);
		ResponseEntity<JsonResponse<WalletModel>> response = new ResponseEntity<JsonResponse<WalletModel>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully");
		logger.info("Method : getWalletPrice Dao ends");
		return response;
	}
	
}
