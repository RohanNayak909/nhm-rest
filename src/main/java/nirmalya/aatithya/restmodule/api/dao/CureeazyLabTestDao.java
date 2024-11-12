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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import nirmalya.aatithya.restmodule.api.model.CureEasyTestListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyLabTestModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyPaymentModel;
import nirmalya.aatithya.restmodule.api.model.DashboardlabDetails;
import nirmalya.aatithya.restmodule.api.model.LabDashboardCountModel;
import nirmalya.aatithya.restmodule.api.model.LabDashboardModel;
import nirmalya.aatithya.restmodule.api.model.MedicineApiModel;
import nirmalya.aatithya.restmodule.api.model.PackagewiseTestListModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionModel;
import nirmalya.aatithya.restmodule.api.model.PrescriptionTestModel;
import nirmalya.aatithya.restmodule.api.model.TestNamedropdownModel;
import nirmalya.aatithya.restmodule.api.model.UserDocumentModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyAddtoCartModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateLabTestPaymentParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.user.model.LablistsModel;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.GenerateRandomValue;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class CureeazyLabTestDao {
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(CureeazyLabTestDao.class);

	// Lab Tests & Packages
	@SuppressWarnings("unchecked")
	public JsonResponse<CureEasyTestListAPIModel> labTestList() {
		logger.info("Method in Dao: labTestList Dao starts");
		CureEasyTestListAPIModel cureEasyTestListAPIModel = new CureEasyTestListAPIModel();
		// Frequent Test List
		List<DropDownModel> frequenTestList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("lab_frequently_test_list").getResultList();
			for (Object[] m : x1) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1],m[2],null,null);
				frequenTestList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Condition Test List
		List<DropDownModel> conditionList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get-condition-based-list").getResultList();
			for (Object[] m : x1) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1],m[2],null,null);
				conditionList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Featured Test List

		List<DropDownModel> featuredList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("lab_featured_test_list").getResultList();
			for (Object[] m : x1) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1],m[2],null,null);
				featuredList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Test Package List
		/*
		 * JsonResponse<Object> jsonResponse1 = new JsonResponse<Object>(); try {
		 * List<Object[]> x1 =
		 * em.createNamedStoredProcedureQuery("get-test-package-list").getResultList();
		 * 
		 * for (Object[] m : x1) { DropDownModel dropDownModel = new
		 * DropDownModel(m[0].toString(), m[1]); testPackageList.add(dropDownModel); }
		 * 
		 * System.out.println("x1"+x1);
		 * 
		 * 
		 * if(x1.size()>0) { jsonResponse1.setBody(x1); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

		// Featured Test List

		List<DropDownModel> testPackage = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get-test-package-list").getResultList();
			for (Object[] m : x1) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1],m[2],null,null);
				testPackage.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cureEasyTestListAPIModel.setGetFrequentTestList(frequenTestList);
		cureEasyTestListAPIModel.setGetConditionBasedTest(conditionList);
		cureEasyTestListAPIModel.setGetFeaturedTest(featuredList);
		//cureEasyTestListAPIModel.setGetTestPackage((List<Object>) jsonResponse1.getBody());
		cureEasyTestListAPIModel.setGetTestPackage(testPackage);
		JsonResponse<CureEasyTestListAPIModel> resp = new JsonResponse<CureEasyTestListAPIModel>();
		resp.setBody(cureEasyTestListAPIModel);
		resp.setCode("success");
		resp.setMessage("Data Fetched Successfully");
		logger.info("Method in Dao: labTestList Dao ends");
		return resp;
	}
	// get Test Details

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CureeazyLabTestModel>> getTestDetailsByTestId(String testId) {
		logger.info("Method : getTestDetailsByTestId Dao starts" + testId);
		CureeazyLabTestModel cureeazyLabTestModel = new CureeazyLabTestModel();
		JsonResponse<CureeazyLabTestModel> jsonResponse = new JsonResponse<CureeazyLabTestModel>();
		try {
			System.out.println(testId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_test_details").setParameter("testid", testId)
					.getResultList();
			for (Object[] m : x) {
				System.out.println(Arrays.toString(m));
				String prfileImg = null;
				if (m[2] != null && m[2] != "" && m[2] != " " && !m[2].toString().equals(" ")
						&& !m[2].toString().equals(null) && !m[2].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[2].toString();
				} else {
					prfileImg = "";
				}
				CureeazyLabTestModel dropDownModel = new CureeazyLabTestModel(m[0], m[1], prfileImg, m[3], m[4], m[5],
						m[6], null, m[7]);
				cureeazyLabTestModel = dropDownModel;
			}
			jsonResponse.setBody(cureeazyLabTestModel);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<CureeazyLabTestModel>> response = new ResponseEntity<JsonResponse<CureeazyLabTestModel>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getTestDetailsByTestId Dao ends");
		return response;
	}
	


	// post Add to cart Api
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postLabtestAddtoCartApi(CureeazyAddtoCartModel data) {
		logger.info("Method : postLabtestAddtoCartApi Dao starts"+data);
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;
		if (data.getUserId() == null || data.getUserId() == "") {
			jsonResponse.setMessage("User Id Required");
			validity = false;
		}
		if (data.getTestId() == null || data.getTestId() == "") {
			jsonResponse.setMessage("Test Id Required");
			validity = false;
		}

		if (validity) {
			System.out.println("ssss");
			try {
				if (data.getTestId() != null && data.getTestId() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_labtest_addtocart_api")
							.setParameter("userid", data.getUserId())
							.setParameter("testId", data.getTestId())
							.setParameter("price", data.getPrice())
							.setParameter("testName", data.getTestName())
							.setParameter("dicountPrice", data.getDiscountPrice())
							.getResultList();
					jsonResponse.setBody(x.get(0));
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : postLabtestAddtoCartApi Dao ends");
		return response;
	}

	// add to cart list dropdown
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CureeazyAddtoCartModel>>> addToCartLabList(String userId) {
		logger.info("Method : addToCartLabList Dao starts");

		List<CureeazyAddtoCartModel> addToCartLabList = new ArrayList<CureeazyAddtoCartModel>();
		JsonResponse<List<CureeazyAddtoCartModel>> jsonResponse = new JsonResponse<List<CureeazyAddtoCartModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("lab_add_tocart_list").setParameter("userid", userId)
					.getResultList();
			for (Object[] m : x1) {
				CureeazyAddtoCartModel cureeazyPaymentModel = new CureeazyAddtoCartModel(m[0], m[1], m[2],
						m[3].toString(), m[4], m[5], null, null, null);
				addToCartLabList.add(cureeazyPaymentModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(addToCartLabList);
		ResponseEntity<JsonResponse<List<CureeazyAddtoCartModel>>> response = new ResponseEntity<JsonResponse<List<CureeazyAddtoCartModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : addToCartLabList Dao ends");
		System.out.println(response);
		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> deleteAddtoCart(String id) {
		logger.info("Method : deleteAddtoCart Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("delete_addtocart").setParameter("cart_ids", id)
					.getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Add to Cart Deleted Successfully.");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Something went wrong.");
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", "Something went wrong.");
		}

		logger.info("Method : deleteAddtoCart Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	// isExistUserEmail
	@SuppressWarnings("unchecked")
	public Boolean isExistPinCode(String pincode) {
		logger.info("Method : isExistUserEmail Dao starts" + pincode);

		Boolean isExist = false;

		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_pincode_dropdown_lab")
					.setParameter("pincode", pincode).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
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
		logger.info("Method : isExistUserEmail Dao ends" + isExist);
		return isExist;
	}

	@SuppressWarnings("unchecked")
	public Boolean isExistLocation(String locations) {
		logger.info("Method : isExistLocation Dao starts" + locations);

		Boolean isExist = false;

		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_location_dropdown_lab")
					.setParameter("locations", locations).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
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

	// save
	@SuppressWarnings({ "unlikely-arg-type", "unchecked" })

	public ResponseEntity<JsonResponse<CureeazyPaymentModel>> postLabtestPaymentApi(CureeazyPaymentModel cureeazyPaymentModel) {

		logger.info("Method : postLabtestPaymentApi starts" + cureeazyPaymentModel);

		System.out.println("regdModel===" + cureeazyPaymentModel);
		JsonResponse<CureeazyPaymentModel> resp = new JsonResponse<CureeazyPaymentModel>();
		CureeazyPaymentModel dlist = new CureeazyPaymentModel();
	
		String addtocart1 = "";
		String order_id1 = "";
		String type=null;
		String type1=null;
		String coupons=cureeazyPaymentModel.getCoupon();
		if(coupons==null || coupons=="") {
			 type="HAPPY";
		}else {
			 type=cureeazyPaymentModel.getCoupon();
		}
		
		String othercharges=cureeazyPaymentModel.getOthercharges();
		if(othercharges==null) {
			 type1="10";
		}else {
			 type1=cureeazyPaymentModel.getCoupon();
		}
		String types="";
		String testType="";
		logger.info("coupons" + coupons);
		if (cureeazyPaymentModel.getTestListDetails() != null) {
			if (cureeazyPaymentModel.getTestListDetails().size() > 0) {
				for (CureeazyAddtoCartModel m : cureeazyPaymentModel.getTestListDetails()) {
					
					 types=m.getTestId();
					 
				      char[] chars = types.toCharArray();
				      StringBuilder sb = new StringBuilder();
				      for(char c : chars){
				         if(Character.isDigit(c)){
				            sb.append(c);
				         }
				      }
				      String sbs=sb.toString();
				 if(types.startsWith("PKG")) {
						System.out.println("pkg");
						 testType="packageWise";
					}
					else if(types.startsWith("PKG") && (types.startsWith("test") || types.startsWith(sbs))) {
						System.out.println("pkgtest");
						 testType="package or test";
					}
					else {
						System.out.println("test");
						 testType="testWise";
					}
					
					addtocart1 = addtocart1 + "(order_id1,\'" + m.getTestId() + "\',\'" + m.getPrice() + "\',\'"
							+ m.getTestName() + "\',\'" +m.getDiscountPrice() + "\'),";
					
					logger.info("lossssst" + addtocart1);
					
				}
				addtocart1 = addtocart1.substring(0, addtocart1.length() - 1);
			} else {
				addtocart1 = "";
			}
		}
	
		Boolean pin;
		Boolean location;
		Boolean validity = true;
		pin=isExistPinCode(cureeazyPaymentModel.getPincode());	
			logger.info("Pin"+pin);
		
		
		location=isExistLocation(cureeazyPaymentModel.getLocation());	
	
		logger.info("location"+location);
		//if (!StringUtil.isNull(cureeazyPaymentModel.getUserid())) {
			//if(cureeazyPaymentModel.getPincode().equals(Pincode) && cureeazyPaymentModel.getLocation().equals(location)) {
		if(pin==true && location==true) {
			logger.info("ifff1"+validity);
			
			try {
				System.out.println("testType"+testType);
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_labtest_payment_apis")
						.setParameter("userids", cureeazyPaymentModel.getUserid())

						.setParameter("dates",cureeazyPaymentModel.getDate())
						.setParameter("times", cureeazyPaymentModel.getTime())
						.setParameter("adderssOne", cureeazyPaymentModel.getAddressOne())
						.setParameter("addressTwo", cureeazyPaymentModel.getAddressTwo())
						.setParameter("location", cureeazyPaymentModel.getLocation())
						.setParameter("city", cureeazyPaymentModel.getCity())
						.setParameter("pincode", cureeazyPaymentModel.getPincode())
						.setParameter("othercharges", type1)
						.setParameter("coupon",type)
						.setParameter("totalprice", cureeazyPaymentModel.getTotalprice())
						.setParameter("valuess", addtocart1)
						.setParameter("testType", testType).getResultList();
				logger.info("Patient" + x);

				for (Object[] m : x) {
					CureeazyPaymentModel dropDownModel = new CureeazyPaymentModel(m[0],m[1]);
					dlist = dropDownModel;
				}
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
		}else {
			resp.setMessage("Service Not Available on This Pincode");
		}
		
		ResponseEntity<JsonResponse<CureeazyPaymentModel>> response = new ResponseEntity<JsonResponse<CureeazyPaymentModel>>(
				resp, HttpStatus.OK);
		logger.info("Method : postLabtestPaymentApi ends");
		return response;
	}

	// get searchwise test list
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getSearchWiseTestListDao(String searchdata) {
		logger.info("Method : searchwiseTestList Dao starts" + searchdata);

		List<DropDownModel> testList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_searchwise_test_list")
					.setParameter("searchdata", searchdata).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1],m[3],m[4]);
				testList.add(dropDownModel);
			}
			jsonResponse.setBody(testList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		
		logger.info("Method : searchwiseTestList Dao ends"+response);

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<LablistsModel>>> getOrderlabList(String userId) {
		logger.info("Method : getOrderlabList Dao starts");

		List<LablistsModel> getOrderlabList = new ArrayList<LablistsModel>();
		JsonResponse<List<LablistsModel>> jsonResponse = new JsonResponse<List<LablistsModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_order_lab_List").setParameter("userId", userId)
					.getResultList();
			for (Object[] m : x) {
				String prfileImg = null;
				if (m[7] != null && m[7] != "" && m[7] != " " && !m[7].toString().equals(" ")
						&& !m[7].toString().equals(null) && !m[7].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[7].toString();
				} else {
					prfileImg = "";
				}
				LablistsModel LablistModel = new LablistsModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], prfileImg);
				getOrderlabList.add(LablistModel);
			}
			jsonResponse.setBody(getOrderlabList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

			if (getOrderlabList.size() > 0) {
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
		ResponseEntity<JsonResponse<List<LablistsModel>>> response = new ResponseEntity<JsonResponse<List<LablistsModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getOrderlabList Dao ends" + response);

		return response;
	}

	// post doctor data Api
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postDoctorDataApi(String userid, String feedback) {
		logger.info("Method : postDoctorDataApi Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;

		if (validity) {
			System.out.println("useridssss====" + userid);
			System.out.println("feedback====" + feedback);
			try {
				if (userid != null && userid != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_doctor_data_api")
							.setParameter("userid", userid).setParameter("feedback", feedback).getResultList();
					jsonResponse.setBody(x.get(0));
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : postDoctorDataApi Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DashboardlabDetails>>> getDashboardlabDetails(String userId) {
		logger.info("Method : getDashboardlabDetails Dao starts");

		List<DashboardlabDetails> getDashboardlabDetails = new ArrayList<DashboardlabDetails>();
		JsonResponse<List<DashboardlabDetails>> jsonResponse = new JsonResponse<List<DashboardlabDetails>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("lab_dashboard_details")
					.setParameter("userId", userId).getResultList();
			for (Object[] m : x1) {
				DashboardlabDetails DashboardlabDetails = new DashboardlabDetails(m[0], m[1], m[2], m[3]);
				getDashboardlabDetails.add(DashboardlabDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(getDashboardlabDetails);
		ResponseEntity<JsonResponse<List<DashboardlabDetails>>> response = new ResponseEntity<JsonResponse<List<DashboardlabDetails>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getDashboardlabDetails Dao ends");
		System.out.println(response);
		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully");

		return response;
	}

	// post Favorite Test Api
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postFavoriteTestApi(CureeazyLabTestModel data) {
		logger.info("Method : postFavoriteTestApi Dao starts");
		System.out.println(data);
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;
		if (data.getUserId() == null || data.getUserId() == "") {
			jsonResponse.setMessage("User Id Required");
			validity = false;
		}
		if (data.getTestId() == null || data.getTestId() == "") {
			jsonResponse.setMessage("Test Id Required");
			validity = false;
		}
		String fstatus = null;
		if (data.getStatus() != null && data.getStatus() != "") {
			fstatus = data.getStatus().toString();
		} else {
			fstatus = "0";
		}
		if (validity) {
			try {
				if (fstatus != null && fstatus != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_favorite_test_api")
							.setParameter("userid", data.getUserId()).setParameter("testid", data.getTestId())
							.setParameter("favstatus", fstatus).getResultList();
					jsonResponse.setBody(x.get(0));
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Favourite Test Added Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : postFavoriteTestApi Dao ends");
		return response;
	}

	// get lab favourite lab list

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CureeazyLabTestModel>>> getFavouriteTestList(String userid) {
		logger.info("Method : getFavouriteTestList Dao starts");

		List<CureeazyLabTestModel> cureeazyLabTestModel = new ArrayList<CureeazyLabTestModel>();
		JsonResponse<List<CureeazyLabTestModel>> jsonResponse = new JsonResponse<List<CureeazyLabTestModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_labtest_favourite_list")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				System.out.println(Arrays.toString(m));
				String prfileImg = null;
				if (m[2] != null && m[2] != "" && m[2] != " " && !m[2].toString().equals(" ")
						&& !m[2].toString().equals(null) && !m[2].toString().equals("")) {
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[2].toString();
				} else {
					prfileImg = "";
				}
				CureeazyLabTestModel dropDownModel = new CureeazyLabTestModel(m[0], m[1], prfileImg, m[3], m[4], m[5],
						m[6], null, m[7]);
				cureeazyLabTestModel.add(dropDownModel);
				System.out.println("dropDownModel" + dropDownModel);
			}

			// jsonResponse.setCode("success");
			// jsonResponse.setMessage("Data Fetched Successfully");
			if (cureeazyLabTestModel.size() > 0) {
				jsonResponse.setBody(cureeazyLabTestModel);
				Util.setJsonResponse(jsonResponse, cureeazyLabTestModel, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CureeazyLabTestModel>>> response = new ResponseEntity<JsonResponse<List<CureeazyLabTestModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getFavouriteTestList Dao ends");
		return response;
	}

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
						BufferedImage img = ImageIO.read(in);
						try {

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
						BufferedImage img = ImageIO.read(in);
						try {

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

	// post self document upload api
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<UserDocumentModel>> postSelfDocumentApi(UserDocumentModel data) {
		logger.info("Method : postSelfDocumentApi Dao starts");
		System.out.println(data);
		JsonResponse<UserDocumentModel> jsonResponse = new JsonResponse<UserDocumentModel>();
		UserDocumentModel dlist = new UserDocumentModel();
		Boolean validity = true;

		if (data.getUserId() == null || data.getUserId() == "") {
			jsonResponse.setMessage("User Id Required");
			validity = false;
		}
		if (data.getDocname() == null || data.getDocname() == "") {
			jsonResponse.setMessage("Document Name Required");
			validity = false;
		}
		if (data.getDocTypes() == null || data.getDocTypes() == "") {
			jsonResponse.setMessage("Document Type Required");
			validity = false;
		}
		if (data.getAdditionalNotes() == null || data.getAdditionalNotes() == "") {
			jsonResponse.setMessage("Additional Notes Required");
			validity = false;
		}
		if (data.getImage() != null && data.getImage() != "") {
			try {
				byte[] bytes = Base64.getDecoder().decode(data.getImage());
				String imageName = null;
				imageName = saveAllImage(bytes, data.getExtension(), data.getUserId());
				data.setImage(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String userid = null;
		if (data.getUserId() != null && data.getUserId() != "") {
			userid = data.getUserId().toString();
		}
		String docName = null;
		if (data.getDocname() != null && data.getDocname() != "") {
			docName = data.getDocname().toString();
		}
		String doctypes = null;
		if (data.getDocTypes() != null && data.getDocTypes() != "") {
			doctypes = data.getDocTypes().toString();
		}
		String additionalNotes = null;
		if (data.getAdditionalNotes() != null && data.getAdditionalNotes() != "") {
			additionalNotes = data.getAdditionalNotes().toString();
		}
		String img = null;
		if (data.getImage() != null && data.getImage() != "") {
			img = data.getImage().toString();
		}
		if (validity) {
			System.out.println("ssss");
			try {
				if (data.getUserId() != null || data.getUserId() != "") {

					List<Object[]> x = em.createNamedStoredProcedureQuery("post_self_document")
							.setParameter("userid", userid).setParameter("docName", docName)
							.setParameter("doctypes", doctypes).setParameter("additionalNotes", additionalNotes)
							.setParameter("p_img", img).getResultList();

					for (Object m : x) {
						UserDocumentModel dropDownModel = new UserDocumentModel(m, null, null, null, null, null, null);
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
		ResponseEntity<JsonResponse<UserDocumentModel>> response = new ResponseEntity<JsonResponse<UserDocumentModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postSelfDocumentApi Dao ends");
		return response;
	}

	// user document view
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<UserDocumentModel>>> getuserdocumentview(String userId) {
		logger.info("Method : getuserdocumentview Dao starts");

		List<UserDocumentModel> getuserdocumentview = new ArrayList<UserDocumentModel>();
		JsonResponse<List<UserDocumentModel>> jsonResponse = new JsonResponse<List<UserDocumentModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("user_self_document_view")
					.setParameter("userid", userId).getResultList();
			String fileName = null;
			for (Object[] m : x1) {
				UserDocumentModel UserDocumentModel = new UserDocumentModel(m[0], m[1], m[2], m[3], m[4], null, m[5]);
				getuserdocumentview.add(UserDocumentModel);
			}

			for (UserDocumentModel a : getuserdocumentview) {
				fileName = env.getBaseURL() + "cureeazyrest/document/document/" + a.getImage();
				a.setImage(fileName);

			}

			if (getuserdocumentview.size() > 0) {
				jsonResponse.setBody(getuserdocumentview);
				Util.setJsonResponse(jsonResponse, getuserdocumentview, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<UserDocumentModel>>> response = new ResponseEntity<JsonResponse<List<UserDocumentModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getuserdocumentview Dao ends");
		System.out.println(response);
		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully");

		return response;
	}

	// get test name drop down model
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> labTimeSlot() {
		logger.info("Method : labTimeSlot Dao starts");

		List<DropDownModel> testname = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_lab_timeslot_mstr").getResultList();
			for (Object[] m : x) {
				DropDownModel testNamedropdownModel = new DropDownModel(m[0], m[1]);
				testname.add(testNamedropdownModel);
			}
			jsonResponse.setBody(testname);

			if (testname.size() > 0) {
				Util.setJsonResponse(jsonResponse, testname, ResponseStatus.success,
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
		logger.info("Method : labTimeSlot Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<LabDashboardModel>>> searchLabDetails(String searchdata) {
		logger.info("Method : searchLabDetails Dao starts");

		List<LabDashboardModel> testList = new ArrayList<LabDashboardModel>();
		JsonResponse<List<LabDashboardModel>> jsonResponse = new JsonResponse<List<LabDashboardModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_search_lab_details")
					.setParameter("searchdata", searchdata).getResultList();
			for (Object[] m : x) {
				Object date = null;

				if (m[7] != null) {
					date = DateFormatter.returnStringDateNew(m[7].toString());
				}
				Object orderDate = null;

				if (m[11] != null) {
					orderDate = DateFormatter.returnStringDateNew(m[11].toString());
				}
				LabDashboardModel labDashboardCountModel = new LabDashboardModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], date, m[8], m[9], m[10], orderDate);
				testList.add(labDashboardCountModel);
			}
			jsonResponse.setBody(testList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<LabDashboardModel>>> response = new ResponseEntity<JsonResponse<List<LabDashboardModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : searchLabDetails Dao ends");

		return response;
	}

	// get addtocart count
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<DropDownModel>> getAddtoCartcount(String userId) {
		logger.info("Method : getAddtoCartcount Dao starts");

		DropDownModel getLabDashboardcount = new DropDownModel();
		JsonResponse<DropDownModel> jsonResponse = new JsonResponse<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_addtocart_count").setParameter("userId", userId)
					.getResultList();
			for (Object m : x) {
				DropDownModel labDashboardCountModel = new DropDownModel(m, null);
				getLabDashboardcount = labDashboardCountModel;
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
		logger.info("Method : getAddtoCartcount Dao ends");

		return response;
	}

	// post doctor data Api
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postHomeServiceDataApi(String userid, String feedback) {
		logger.info("Method : postHomeServiceDataApi Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;

		if (validity) {
			System.out.println("useridssss====" + userid);
			System.out.println("feedback====" + feedback);
			try {
				if (userid != null && userid != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_homeservice_data_api")
							.setParameter("userid", userid).setParameter("feedback", feedback).getResultList();
					jsonResponse.setBody(x.get(0));
				}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : postHomeServiceDataApi Dao ends");
		return response;
	}

	// get Test Details

	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CureeazyLabTestModel>> getPackageWiseTest(String packageId) {
		logger.info("Method : getTestDetailsByTestId Dao starts"+packageId);

		CureeazyLabTestModel cureeazyLabTestModel = new CureeazyLabTestModel();
		JsonResponse<CureeazyLabTestModel> jsonResponse = new JsonResponse<CureeazyLabTestModel>();
		//Test List
		List<PackagewiseTestListModel> testList = new ArrayList<PackagewiseTestListModel>();
		List<CureeazyLabTestModel> labTestModel = new ArrayList<CureeazyLabTestModel>();
	
		try {
			
		List<Object[]> x = em.createNamedStoredProcedureQuery("get_packagewisetest_details").setParameter("packageId", packageId)
						.getResultList();
				for (Object[] m : x) {
					System.out.println(Arrays.toString(m));
					String prfileImg = null;
					if (m[2] != null && m[2] != "" && m[2] != " " && !m[2].toString().equals(" ")
							&& !m[2].toString().equals(null) && !m[2].toString().equals("")) {
						prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[2].toString();
					} else {
						prfileImg = "";
					}
					CureeazyLabTestModel dropDownModel = new CureeazyLabTestModel(m[0],m[1], prfileImg, m[3],
							m[4], m[5]);
					cureeazyLabTestModel = dropDownModel;
				}
				
			
				try {
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_package_test_details")
							.setParameter("packageid", packageId).getResultList();
					for (Object[] m : x1) {
						String prfileImg = null;
						if (m[2] != null && m[2] != "" && m[2] != " " && !m[2].toString().equals(" ")
								&& !m[2].toString().equals(null) && !m[2].toString().equals("")) {
							prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[2].toString();
						} else {
							prfileImg = "";
						}
						PackagewiseTestListModel dropDownModel = new PackagewiseTestListModel(m[0], m[1], prfileImg, m[3], m[4], m[5],
								m[6], null, m[7],null);
						testList.add(dropDownModel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		
			jsonResponse.setBody(cureeazyLabTestModel);
			cureeazyLabTestModel.setGetTestList(testList);
			//cureeazyLabTestModel.setGetConditionTestList(labTestModel);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<CureeazyLabTestModel>> response = new ResponseEntity<JsonResponse<CureeazyLabTestModel>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getTestDetailsByTestId Dao ends");
		return response;
	}

	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CureeazyLabTestModel>> getConditionBasedTest(String testId) {
		logger.info("Method : getConditionBasedTest Dao starts"+testId);

		CureeazyLabTestModel cureeazyLabTestModel = new CureeazyLabTestModel();
		JsonResponse<CureeazyLabTestModel> jsonResponse = new JsonResponse<CureeazyLabTestModel>();
		//Test List
		List<PackagewiseTestListModel> testList = new ArrayList<PackagewiseTestListModel>();
		List<CureeazyLabTestModel> labTestModel = new ArrayList<CureeazyLabTestModel>();
	
		try {
			
		List<Object[]> x = em.createNamedStoredProcedureQuery("get_condition_test").setParameter("testId", testId)
						.getResultList();
				for (Object[] m : x) {
					System.out.println(Arrays.toString(m));
				
					CureeazyLabTestModel dropDownModel = new CureeazyLabTestModel(m[0],m[1]);
					cureeazyLabTestModel = dropDownModel;
				}
				
			
				try {
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_conditiontest_details")
							.setParameter("testid", testId).getResultList();
					for (Object[] m : x1) {
						String prfileImg = null;
						if (m[2] != null && m[2] != "" && m[2] != " " && !m[2].toString().equals(" ")
								&& !m[2].toString().equals(null) && !m[2].toString().equals("")) {
							prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[2].toString();
						} else {
							prfileImg = "";
						}
						PackagewiseTestListModel dropDownModel = new PackagewiseTestListModel(m[0], m[1], prfileImg, m[3], m[4], m[5],
								m[6], null, m[7],null);
						testList.add(dropDownModel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		
			jsonResponse.setBody(cureeazyLabTestModel);
			//cureeazyLabTestModel.setGetTestList(testList);
			cureeazyLabTestModel.setGetConditionTestList(testList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<CureeazyLabTestModel>> response = new ResponseEntity<JsonResponse<CureeazyLabTestModel>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getConditionBasedTest Dao ends");
		return response;
	}
}
