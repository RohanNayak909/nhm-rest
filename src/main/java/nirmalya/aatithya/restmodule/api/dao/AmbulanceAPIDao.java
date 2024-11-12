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

import nirmalya.aatithya.restmodule.api.model.APIDoctorMonthlyOverviewModel;
import nirmalya.aatithya.restmodule.api.model.AmbulanceAPIModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;

@Repository
public class AmbulanceAPIDao {

	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(AmbulanceAPIDao.class);

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> ambulanceOrgList() {
		logger.info("Method : ambulanceOrgList Dao starts");

		List<DropDownModel> orgList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_ambulance_org_list_api").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				orgList.add(dropDownModel);
			}
			if (orgList.size() > 0) {
				Util.setJsonResponse(jsonResponse, orgList, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, orgList, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, orgList, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : ambulanceOrgList Dao ends");

		return response;
	}

	// Add Ambulance Details
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> postAmbulanceList(AmbulanceAPIModel data) {
		logger.info("Method : postAmbulanceList Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		Boolean validation = true;
		String date = null;

		if (StringUtil.isNull(data.getToDestination())) {
			validation = false;
			jsonResponse.setMessage("Destination Required");
		}
		if (StringUtil.isNull(data.getAmbulanceId())) {
			validation = false;
			jsonResponse.setMessage("Ambulance Name Required");
		}

		if (StringUtil.isNull(data.getFromLocation())) {
			validation = false;
			jsonResponse.setMessage("Location Required");
		}
		if (StringUtil.isNull(data.getFromLatitude())) {
			validation = false;
			jsonResponse.setMessage("From Latitude Required");
		}
		if (StringUtil.isNull(data.getFromLongitude())) {
			validation = false;
			jsonResponse.setMessage("From Longitude Required");
		}

		if (StringUtil.isNull(data.getToLatitude())) {
			validation = false;
			jsonResponse.setMessage("To Latitude Required");
		}
		if (StringUtil.isNull(data.getBookingTime())) {
			validation = false;
			jsonResponse.setMessage("Booking Time Required");
		}

		if (StringUtil.isNull(data.getToLongitude())) {
			validation = false;
			jsonResponse.setMessage("To Longitude Required");
		}

		//logger.debug("date"+data.getBookedDate());
		if (!StringUtil.isNull(data.getBookedDate())) {
			date = DateFormatter.getStringDateNew(data.getBookedDate());
		}
		// Ambulance details list
		int status = 1;
		String ambulancedetails = "(" + data.getAmbulanceId() + ",'" + data.getFromLocation() + "','"
				+ data.getToDestination() + "','" + date + "','" + status + "','" + data.getPatientNote() + "','"
				+ data.getPatientId() + "','" + data.getToLatitude() + "','" + data.getToLongitude() + "','"
				+ data.getFromLongitude() + "','" + data.getBookingTime() + "','"+ data.getFromLatitude() + "')";

		if (validation) {

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("post_ambulance_details_api")
						.setParameter("ambulancedetails", ambulancedetails).getResultList();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.success, ApiResponseMessage.AMBULANCE_BOOKED);

			} catch (Exception e) {
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postAmbulanceList Dao ends");
		return response;
	}

	// Get patient ambulance details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AmbulanceAPIModel>>> getPatientAmbulanceList(String userid) {
		logger.info("Method : getPatientAmbulanceList Dao starts");

		List<AmbulanceAPIModel> patientAmbulanceDetails = new ArrayList<AmbulanceAPIModel>();
		JsonResponse<List<AmbulanceAPIModel>> jsonResponse = new JsonResponse<List<AmbulanceAPIModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_ambulance_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				AmbulanceAPIModel dropDownModel = new AmbulanceAPIModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9]);
				patientAmbulanceDetails.add(dropDownModel);
			}

			if (patientAmbulanceDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, patientAmbulanceDetails, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, patientAmbulanceDetails, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, patientAmbulanceDetails, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AmbulanceAPIModel>>> response = new ResponseEntity<JsonResponse<List<AmbulanceAPIModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getPatientAmbulanceList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AmbulanceAPIModel>>> ambulanceActionList(String userid, String status) {
		logger.info("Method : ambulanceActionList Dao starts");

		List<AmbulanceAPIModel> patientDetails = new ArrayList<AmbulanceAPIModel>();
		JsonResponse<List<AmbulanceAPIModel>> jsonResponse = new JsonResponse<List<AmbulanceAPIModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("view_patient_ambulance_request_list")
					.setParameter("uid", userid).setParameter("pstatus", status).getResultList();
			for (Object[] m : x) {

				String appstatus = null;
				if (m[6] != null) {
					if (m[6].toString().contentEquals("1")) {
						appstatus = "Requested";
					}
					if (m[6].toString().contentEquals("4")) {
						appstatus = "Accepted";
					}
					if (m[6].toString().contentEquals("6")) {
						appstatus = "Rejected";
					}
				}

				AmbulanceAPIModel dropDownModel = new AmbulanceAPIModel(m[0].toString(), m[1], m[2], m[3], m[4], m[5], appstatus,m[7]);
				patientDetails.add(dropDownModel);
			}

			if (patientDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, patientDetails, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, patientDetails, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, patientDetails, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AmbulanceAPIModel>>> response = new ResponseEntity<JsonResponse<List<AmbulanceAPIModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : ambulanceActionList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changeAmbulanceStatus(String orderid, String status) {
		logger.info("Method : changeAmbulanceStatus DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		if (validity) {
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("change_ambulance_status")
						.setParameter("orderid", orderid).setParameter("status", status).getResultList();
				if (x.size() > 0) {
					if (status.equals("4")) {
						Util.setJsonResponse(jsonResponse, x.get(0), ResponseStatus.success, ApiResponseMessage.REQUEST_ACCEPTED);
					} else {
						Util.setJsonResponse(jsonResponse, x.get(0), ResponseStatus.success, ApiResponseMessage.REQUEST_REJECTED);
					}
				} else {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : changeAmbulanceStatus DAO ends");
		return response;
	}

	// Get Ambulace monthly overview list
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<APIDoctorMonthlyOverviewModel>> getAmbulanceMyMonthlyOverviewListDao(
			String ambid,String fromdate,String todate) {
		logger.info("Method : getDoctorMyMonthlyOverviewListDao Dao starts");

		JsonResponse<APIDoctorMonthlyOverviewModel> jsonResponse = new JsonResponse<APIDoctorMonthlyOverviewModel>();
		try {
			
			if(!StringUtil.isNull(fromdate)) {
				fromdate = DateFormatter.getStringDate(fromdate);
			}
			if(!StringUtil.isNull(todate)) {
				todate = DateFormatter.getStringDate(todate);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_ambulance_monthlyoverview_api")
					.setParameter("ambid", ambid)
					.setParameter("fromdate", fromdate)
					.setParameter("todate", todate)
					.getResultList();
			for (Object[] m : x) {

				APIDoctorMonthlyOverviewModel dropDownModel = new APIDoctorMonthlyOverviewModel(m[0], m[1], m[2], m[3],
						m[4]);
				Util.setJsonResponse(jsonResponse, dropDownModel, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		
		ResponseEntity<JsonResponse<APIDoctorMonthlyOverviewModel>> response = new ResponseEntity<JsonResponse<APIDoctorMonthlyOverviewModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getDoctorMyMonthlyOverviewListDao Dao ends");
		return response;
	}

	// Get doctor monthly patient details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIDoctorMonthlyOverviewModel>>> getAmbulanceMonthlyOverviewByStatusDao(
			String ambid, String status, String frmdt, String todt) {
		logger.info("Method : getAmbulanceMonthlyOverviewByStatusDao Dao starts");

		List<APIDoctorMonthlyOverviewModel> patientMonthlyDetails = new ArrayList<APIDoctorMonthlyOverviewModel>();
		JsonResponse<List<APIDoctorMonthlyOverviewModel>> jsonResponse = new JsonResponse<List<APIDoctorMonthlyOverviewModel>>();

		if (frmdt != null && frmdt != "") {
			frmdt = DateFormatter.getStringDate(frmdt);
		}

		if (todt != null && todt != "") {
			todt = DateFormatter.getStringDate(todt);
		}

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_ambulancemonthlyoverview_bystatus_list")
					.setParameter("ambid", ambid).setParameter("apptstatus", status).setParameter("frmdt", frmdt)
					.setParameter("todt", todt).getResultList();

			for (Object[] m : x) {

				APIDoctorMonthlyOverviewModel dropDownModel = new APIDoctorMonthlyOverviewModel(m[0], m[1], m[2]);
				patientMonthlyDetails.add(dropDownModel);
			}

			if (patientMonthlyDetails.size() > 0) {
				Util.setJsonResponse(jsonResponse, patientMonthlyDetails, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, patientMonthlyDetails, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIDoctorMonthlyOverviewModel>>> response = new ResponseEntity<JsonResponse<List<APIDoctorMonthlyOverviewModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getAmbulanceMonthlyOverviewByStatusDao Dao ends");
		return response;
	}

}
