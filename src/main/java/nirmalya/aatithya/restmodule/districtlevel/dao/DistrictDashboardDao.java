package nirmalya.aatithya.restmodule.districtlevel.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictLevelDashboardModel;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
@Repository
public class DistrictDashboardDao {
	Logger logger = LoggerFactory.getLogger(DistrictDashboardDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestDistrictLevelDashboardModel>> restGetDistrictCountDetails(String block,String userId) {
		logger.info("Method :  restGetDistrictCountDetailsDao starts");

		List<RestDistrictLevelDashboardModel> admin = new ArrayList<RestDistrictLevelDashboardModel>();
		JsonResponse<List<RestDistrictLevelDashboardModel>> resp = new JsonResponse<List<RestDistrictLevelDashboardModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("district_workprogress_count")
					.setParameter("block",block)
					.setParameter("userId", userId)
					.getResultList();

			for (Object[] m : x) {
				RestDistrictLevelDashboardModel restDistrictDashboardModel = new RestDistrictLevelDashboardModel(m[0], m[1], m[2], m[3],
						m[4], m[5], m[6], m[7],m[8]);
				admin.add(restDistrictDashboardModel);
				
			}
			resp.setBody(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method :  restGetDistrictCountDetailsDao ends"+resp);
		return resp;
	}

	
	//
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestDistrictLevelDashboardModel>>> districtPiachart(String block,String userId) {
		logger.info("Method : districtPiachart starts"+userId);
		List<RestDistrictLevelDashboardModel> req = new ArrayList<RestDistrictLevelDashboardModel>();
		JsonResponse<List<RestDistrictLevelDashboardModel>> resp = new JsonResponse<List<RestDistrictLevelDashboardModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("district_dashboard_pie")
					.setParameter("block", block)
					.setParameter("userId", userId)
					.getResultList();
			for (Object[] m : x) {
				RestDistrictLevelDashboardModel reqemp = new RestDistrictLevelDashboardModel(m[0],m[1]);
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<RestDistrictLevelDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestDistrictLevelDashboardModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method : districtPiachart ends" + response);
		return response;
	}
	//
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestDistrictLevelDashboardModel>>> districtBarchart(String block,String userId) {
		logger.info("Method : districtBarchart starts");
		List<RestDistrictLevelDashboardModel> req = new ArrayList<RestDistrictLevelDashboardModel>();
		JsonResponse<List<RestDistrictLevelDashboardModel>> resp = new JsonResponse<List<RestDistrictLevelDashboardModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("district_dashboard_bar")
					.setParameter("block", block)
					.setParameter("userId", userId)
					.getResultList();
			for (Object[] m : x) {
				RestDistrictLevelDashboardModel reqemp = new RestDistrictLevelDashboardModel(m[0],m[1]);
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<RestDistrictLevelDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestDistrictLevelDashboardModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method : districtBarchart ends" + response);
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDashboardBlockList(String userId) {
		logger.info("Method : getDashboardBlockList starts");

		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("dashboard_get_block_list")
					.setParameter("block", userId)
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),m[1].toString());
				specialityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDashboardBlockList ends"+specialityList);

		return specialityList;
	}
	
	

}
