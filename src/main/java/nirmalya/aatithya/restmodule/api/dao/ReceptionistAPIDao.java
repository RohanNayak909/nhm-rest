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

import nirmalya.aatithya.restmodule.api.model.APIShareAppointmentModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentListModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class ReceptionistAPIDao {

	
	@Autowired
	EntityManager em;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;
	
	Logger logger = LoggerFactory.getLogger(ReceptionistAPIDao.class);
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> viewReceptionistAppointmentList(String userid,
			String date, String status) {
		logger.info("Method : viewReceptionistAppointmentList Dao starts");

		List<AppointmentListModel> countryList = new ArrayList<AppointmentListModel>();
		JsonResponse<List<AppointmentListModel>> jsonResponse = new JsonResponse<List<AppointmentListModel>>();
		try {

			if (date != null) {
				date = DateFormatter.getStringDateNew(date);
			}
			System.out.println(userid + " " + date + " " + status);
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_receptionist_appointment_list")
					.setParameter("userid", userid).setParameter("date", date).setParameter("status", status)
					.getResultList();
			for (Object[] m : x) {

				String appstatus = null;
				if (m[6] != null) {
					if (m[6].toString().contentEquals("7")) {
						appstatus = "Requested";
					}
					if (m[6].toString().contentEquals("2")) {
						appstatus = "Confirmed";
					}
					if (m[6].toString().contentEquals("4")) {
						appstatus = "Cancelled";
					}
					if (m[6].toString().contentEquals("5")) {
						appstatus = "Treated";
					}
				}

				AppointmentListModel dropDownModel = new AppointmentListModel(m[0], m[1], m[2], m[3], m[4], m[5],
						appstatus, m[7], m[8], m[9], m[10],null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");

			if (countryList.size() > 0) {
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
		ResponseEntity<JsonResponse<List<AppointmentListModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentListModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : viewReceptionistAppointmentList Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIShareAppointmentModel>>> getReferredUserList(String userid) {
		logger.info("Method : getReferredUserList Dao starts");

		List<APIShareAppointmentModel> userlist = new ArrayList<APIShareAppointmentModel>();
		JsonResponse<List<APIShareAppointmentModel>> jsonResponse = new JsonResponse<List<APIShareAppointmentModel>>();
		try {
			System.out.println(userid);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_referred_user_list_api")
					.setParameter("userid", userid).getResultList();
			
			for (Object[] m : x) {
				APIShareAppointmentModel dropDownModel = new APIShareAppointmentModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8]);
				userlist.add(dropDownModel);
			}
			
			jsonResponse.setBody(userlist);

			if (userlist.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIShareAppointmentModel>>> response = new ResponseEntity<JsonResponse<List<APIShareAppointmentModel>>>(
				jsonResponse, HttpStatus.OK);

		System.out.println(response);

		logger.info("Method : getReferredUserList Dao ends");
		return response;
	}

}
