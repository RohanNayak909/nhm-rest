package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import nirmalya.aatithya.restmodule.util.Util;
import javax.persistence.EntityManager;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.CureEasyDrAppointBookingAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyHomeServiceListAPIModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyHomeServiceOrderListModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyRequestToServiceModel;
import nirmalya.aatithya.restmodule.api.model.CureEasyServicedDetailsModel;
import nirmalya.aatithya.restmodule.api.model.HomeHealthServiceModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;

@Repository
public class HomeServiceAPIDao {
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(HomeServiceAPIDao.class);
	
	//get homeservice list
	@SuppressWarnings("unchecked")
	public JsonResponse<CureEasyHomeServiceListAPIModel> homeServiceList() {
		logger.info("Method in Dao: labTestList Dao starts");
		CureEasyHomeServiceListAPIModel CureEasyHomeServiceListAPIModel = new CureEasyHomeServiceListAPIModel();
// Frequently Booked Service List
		List<DropDownModel> frequentlyBookedServiceList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_frequently_booked_service_list").getResultList();
			for (Object[] m : x1) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				frequentlyBookedServiceList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

// Homecare Service List
		List<DropDownModel> homecareServiceList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_homecare_service_list").getResultList();
			for (Object[] m : x1) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				homecareServiceList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Get Packages List

		List<DropDownModel> pPackagesList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_packages_list").getResultList();
			for (Object[] m : x1) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				pPackagesList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Featured Service List
		List<DropDownModel> featuredServiceList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_featured_service_list").getResultList();
			for (Object[] m : x1) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				featuredServiceList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		CureEasyHomeServiceListAPIModel.setGetFrequentlyBookedServiceList(frequentlyBookedServiceList);
		CureEasyHomeServiceListAPIModel.setGetHomecareServiceList(homecareServiceList);
		CureEasyHomeServiceListAPIModel.setGetPPackagesList(pPackagesList);
		CureEasyHomeServiceListAPIModel.setGetFeaturedServiceList(featuredServiceList);
		JsonResponse<CureEasyHomeServiceListAPIModel> resp = new JsonResponse<CureEasyHomeServiceListAPIModel>();
		resp.setBody(CureEasyHomeServiceListAPIModel);
		resp.setCode("success");
		resp.setMessage("Data Fetched Successfully");
		logger.info("Method in Dao: labTestList Dao ends");
		return resp;
	}

//get service details	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CureEasyServicedDetailsModel>>> serviceDetailsList(String serviceId) {
		logger.info("Method : addToCartLabList Dao starts"+serviceId);

		List<CureEasyServicedDetailsModel> serviceDetailsList = new ArrayList<CureEasyServicedDetailsModel>();
		JsonResponse<List<CureEasyServicedDetailsModel>> jsonResponse = new JsonResponse<List<CureEasyServicedDetailsModel>>();
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_service_details_List")
					.setParameter("serviceId", serviceId).getResultList();
			for (Object[] m : x1) {
				CureEasyServicedDetailsModel CureEasyServicedDetailsModel = new CureEasyServicedDetailsModel(m[0], m[1],m[2], m[3]);
				serviceDetailsList.add(CureEasyServicedDetailsModel)
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResponse.setBody(serviceDetailsList);
		ResponseEntity<JsonResponse<List<CureEasyServicedDetailsModel>>> response = new ResponseEntity<JsonResponse<List<CureEasyServicedDetailsModel>>>(
				jsonResponse, HttpStatus.OK);
		
		logger.info("Method : addToCartLabList Dao ends");
		System.out.println(response);
		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully");

		return response;
	}	
	//isExistUserEmail
	@SuppressWarnings("unchecked")
	public Boolean isExistPinCode(String pincode) {
		logger.info("Method : isExistUserEmail Dao starts"+pincode);
		
		Boolean isExist = false;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_pincode_dropdown")
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
		logger.info("dropDownModel"+dataList);
		logger.info("Method : isExistUserEmail Dao ends"+isExist);
		return isExist;
	}

	@SuppressWarnings("unchecked")
	public Boolean isExistLocation(String locations) {
		logger.info("Method : isExistLocation Dao starts"+locations);
		
		Boolean isExist = false;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_location_dropdown")
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
		logger.info("dropDownModel"+dataList);
		logger.info("Method : isExistLocation Dao ends"+isExist);
		return isExist;
	}
// post request to service
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postRequestToServiceApi(CureEasyRequestToServiceModel data) {
		logger.info("Method : postDoctorAppointmentPaymentDtls Dao starts");
		System.out.println(data);
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validity = true;
		
		if (data.getUserId() == null || data.getUserId() == "") {
			jsonResponse.setMessage("Appointment Id Required");
			validity = false;
		}
		
 
		if (data.getUserId() == null || data.getUserId() == "") {
			jsonResponse.setMessage("User Id Required");
			validity = false;
		}
		if (data.getServiceId() == null || data.getServiceId() == "") {
			jsonResponse.setMessage("Service Id Required");
			validity = false;
		}
		if (data.getAddressOne() == null || data.getAddressOne() == "") {
			jsonResponse.setMessage("Address One Required");
			validity = false;
		}
		if (data.getAddressTwo() == null || data.getAddressTwo() == "") {
			jsonResponse.setMessage("Address Two Required");
			validity = false;
		}
		if (data.getLocality() == null || data.getLocality() == "") {
			jsonResponse.setMessage("Location Id Required");
			validity = false;
		}
		if (data.getCity() == null || data.getCity() == "") {
			jsonResponse.setMessage("City Required");
			validity = false;
		}
		if (data.getPinCode() == null || data.getPinCode() == "") {
			jsonResponse.setMessage("Pin Required");
			validity = false;
		}
		Boolean pin;
		Boolean location;
		
		pin=isExistPinCode(data.getPinCode());	
			logger.info("Pin"+pin);
		if(pin) {
			jsonResponse.setMessage("Service Not Available on This Pincode");
			validity = false;
			logger.info("ifpin"+pin);
		}
		
		location=isExistLocation(data.getLocality());
		logger.info("location"+location);
		if(location) {
			jsonResponse.setMessage("Service Not Available on This Pincode");
			validity = false;
			logger.info("iflocation"+location);
		}
		if(pin==true && location==true) {
		//if (validity) {
			System.out.println("ssss");
			try {
				//if (data.getUserId() != null && data.getUserId() != "") {
					
					
					
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_request_to_service")
							.setParameter("userId", data.getUserId())
							.setParameter("serviceId", data.getServiceId())
							.setParameter("addressOne", data.getAddressOne())
							.setParameter("addressTwo",data.getAddressTwo())
							.setParameter("locality", data.getLocality())
							.setParameter("city", data.getCity())
							.setParameter("pinCode", data.getPinCode())
							.getResultList();
					jsonResponse.setBody(x.get(0));
			//	}
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_FAILED);
			}
		//}
		}else {
			System.out.println("else");
			jsonResponse.setMessage("Service Not Available on This Pincode");
		}
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : postDoctorAppointmentPaymentDtls Dao ends");
		return response;
	}


	//get home service order list
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CureEasyHomeServiceOrderListModel>>> homeServiceOrderList(String userId) {
		logger.info("Method : addToCartLabList Dao starts"+userId);

		List<CureEasyHomeServiceOrderListModel> serviceDetailsList = new ArrayList<CureEasyHomeServiceOrderListModel>();
		JsonResponse<List<CureEasyHomeServiceOrderListModel>> jsonResponse = new JsonResponse<List<CureEasyHomeServiceOrderListModel>>();
	
		try {
			List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_home_service_order_List")
					.setParameter("userId", userId).getResultList();
			Object statussAll = null;
		
			for (Object[] m : x1) {
				System.out.println("m[3]"+m[3]);
				
				//Object statuss1 = null;
				
					String statuss = m[3].toString();
				
			Integer assign=Integer.parseInt(statuss);
			if(assign.equals(2)) {
				statussAll="Assigned";
			}else if(assign.equals(10)) {
				statussAll="Accepted";
			}else if(assign.equals(11)) {
				statussAll="Rejected";
			}else if(assign.equals(12)) {
				statussAll="Documetation Done";
			}else if(assign.equals(13)) {
				statussAll="In Progress";
			}else if(assign.equals(14)) {
				statussAll="Service Completed";
			}else {
				statussAll="Service Closed";
			}
				
				CureEasyHomeServiceOrderListModel CureEasyHomeServiceOrderListModel = new CureEasyHomeServiceOrderListModel(m[0], m[1],m[2], statussAll, m[4], m[5], m[6],m[7],m[8],m[9]);
				serviceDetailsList.add(CureEasyHomeServiceOrderListModel)
				;
			}
			
			if (serviceDetailsList.size() > 0) {
				jsonResponse.setBody(serviceDetailsList);
				Util.setJsonResponse(jsonResponse, serviceDetailsList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<List<CureEasyHomeServiceOrderListModel>>> response = new ResponseEntity<JsonResponse<List<CureEasyHomeServiceOrderListModel>>>(
				jsonResponse, HttpStatus.OK);
		
		logger.info("Method : addToCartLabList Dao ends"+response);
		
		System.out.println(response);
		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully");

		return response;
	}	

	
	//get home service order list
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<HomeHealthServiceModel>>> homeServiceOrderLists(String orderId) {
			logger.info("Method : homeServiceOrderLists Dao starts");

			List<HomeHealthServiceModel> serviceDetailsList = new ArrayList<HomeHealthServiceModel>();
			JsonResponse<List<HomeHealthServiceModel>> jsonResponse = new JsonResponse<List<HomeHealthServiceModel>>();
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("get_home_service_details_List")
						.setParameter("orderId",orderId).getResultList();
				for (Object[] m : x1) {
					HomeHealthServiceModel CureEasyHomeServiceOrderListModel = new HomeHealthServiceModel(m[0], null,m[1], m[2],
							m[3], m[4], m[5], null,m[6],null,null,null,null,null,null,null,null,null,null,m[7],m[8],m[9],m[10],m[11]);
					serviceDetailsList.add(CureEasyHomeServiceOrderListModel)
					;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonResponse.setBody(serviceDetailsList);
			ResponseEntity<JsonResponse<List<HomeHealthServiceModel>>> response = new ResponseEntity<JsonResponse<List<HomeHealthServiceModel>>>(
					jsonResponse, HttpStatus.OK);
			
			logger.info("Method : homeServiceOrderLists Dao ends");
			System.out.println(response);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

			return response;
		}	
	// get home service search
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> gethomeservicesearch(String name) {
		logger.info("Method : gethomeservicesearch Dao starts");

		List<DropDownModel> homeservicesearch = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_homeservice_search")
					.setParameter("name", name).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				homeservicesearch.add(dropDownModel);
			}
			jsonResponse.setBody(homeservicesearch);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : gethomeservicesearch Dao ends");

		return response;
	}

}
