package nirmalya.aatithya.restmodule.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.DmDashboardRestModel;
import nirmalya.aatithya.restmodule.user.model.RestPMDashboardModel;
@Repository
public class RestPMDashboardDao {
	
	Logger logger = LoggerFactory.getLogger(RestPMDashboardDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/**
	 * Dao Appointment
	 *
	 * @return
	 */
	
	/*
	Dashboard  count list
	
	*/
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestPMDashboardModel>> viewpmDashboardCount( String countryId) {
		logger.info("Method : viewpmDashboardCount starts");

		RestPMDashboardModel req = new RestPMDashboardModel();
		JsonResponse<RestPMDashboardModel> resp = new JsonResponse<RestPMDashboardModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("pm_dashboard_view_count").
					setParameter("countryId", countryId).getResultList();

			for (Object[] m : x) {
				RestPMDashboardModel reqemp = new RestPMDashboardModel(m[0],null,null, m[1], m[2], m[3], m[4],null);
				
				req = reqemp;
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPMDashboardModel>> response = new ResponseEntity<JsonResponse<RestPMDashboardModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		
		logger.info("Method : viewpmDashboardCount ends")	;
		return response;
	}
	// Dashboard  GetIdd  Country list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> pmDashboardgetId(String id) {
		logger.info("Method : pmDashboardgetId Dao starts");
		
		List<DropDownModel> pmDashboardgetId = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("pm_dashboard_get_idd").setParameter("pmid", id).getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				pmDashboardgetId.add(dropDownModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
   	logger.info("Method : getCountryLists Dao ends");

		return pmDashboardgetId;
	}
}
