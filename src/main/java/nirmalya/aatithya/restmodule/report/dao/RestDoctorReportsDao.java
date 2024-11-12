package nirmalya.aatithya.restmodule.report.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.report.model.RestDoctorReportsModel;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class RestDoctorReportsDao {
	Logger logger = LoggerFactory.getLogger(RestDoctorReportsDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	

	
	
	//for fromdate & todate
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestDoctorReportsModel>> daoDoctorOrdersview(String fromDate, String toDate) {

		logger.info("Method : gettransactionledgerview Dao starts");
		List<RestDoctorReportsModel> ledger = new ArrayList<RestDoctorReportsModel>();
		JsonResponse<List<RestDoctorReportsModel>> resp = new JsonResponse<List<RestDoctorReportsModel>>();
		try {
		
			
			List<Object[] >x = em.createNamedStoredProcedureQuery("doctor_orders_report")
					.setParameter("fromDate", fromDate).setParameter("toDate", toDate)
					.getResultList();
			for (Object[] m : x) {
				Object bookedDate = null;

				if (m[5] != null) {
					bookedDate = DateFormatter.returnStringDateNew(m[5].toString());
				}
				RestDoctorReportsModel so = new RestDoctorReportsModel(m[0], m[1], m[2],m[3],m[4],bookedDate, m[6], m[7]);
				ledger.add(so);
				
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(ledger);
		System.out.println("======>>>>>"+resp);
		logger.info("Method : gettransactionledgerview Dao ends"+resp);

		return resp;

	}
}
