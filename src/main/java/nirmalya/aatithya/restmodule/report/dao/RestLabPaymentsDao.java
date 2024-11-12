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
import nirmalya.aatithya.restmodule.report.model.RestDoctorPaymentsModel;
import nirmalya.aatithya.restmodule.report.model.RestDoctorReportsModel;
import nirmalya.aatithya.restmodule.report.model.RestLabPaymentsModel;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class RestLabPaymentsDao {
	Logger logger = LoggerFactory.getLogger(RestLabPaymentsDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;

	// for fromdate & todate aggrid1\
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLabPaymentsModel>> daoLabPaymentview(String fromDate, String toDate) {

		logger.info("Method : getDoctorPayment Dao starts" + fromDate + "," + toDate);
		List<RestLabPaymentsModel> ledger = new ArrayList<RestLabPaymentsModel>();
		JsonResponse<List<RestLabPaymentsModel>> resp = new JsonResponse<List<RestLabPaymentsModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("lab_Payment_report")
					.setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
			for (Object[] m : x) {
				Object bookedDate = null;

				RestLabPaymentsModel so = new RestLabPaymentsModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8],
						m[9]);
				ledger.add(so);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(ledger);
		logger.info("@@@@@@#$%$###" + fromDate + "," + toDate);
		logger.info("@@@@@@#$%$###" + resp);
		return resp;

	}

	

	// fromdate todate grid2
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLabPaymentsModel>> daoLabPaymentDetails(String fromDate, String toDate) {

		logger.info("Method : getDoctorAllDetails Dao starts" + fromDate + "," + toDate);
		List<RestLabPaymentsModel> ledger = new ArrayList<RestLabPaymentsModel>();
		JsonResponse<List<RestLabPaymentsModel>> resp = new JsonResponse<List<RestLabPaymentsModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("lab_Payment_all_Details")
					.setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
			for (Object[] m : x) {
				Object bookedDate = null;

				if (m[6] != null) {
					bookedDate = DateFormatter.returnStringDateNew(m[6].toString());
				}
				RestLabPaymentsModel so = new RestLabPaymentsModel(m[0], m[1], m[2], m[3],
						  m[4], m[5], m[6], m[7], m[8], m[9], m[10], m[11]);
				ledger.add(so);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(ledger);
		//logger.info("@@@@@@#$%$###" + fromDate + "," + toDate);

		return resp;

	}

	
}
