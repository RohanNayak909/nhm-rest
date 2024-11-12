package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminDashboardModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Repository
public class RestAdminDashboadDao {
	Logger logger = LoggerFactory.getLogger(RestAdminDashboadDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	//dashboard count details
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminDashboardModel>> restGetAdminCountDetails(String district, String block) {
		logger.info("Method :  getAdminCountDetails starts");

		List<RestAdminDashboardModel> admin = new ArrayList<RestAdminDashboardModel>();
		JsonResponse<List<RestAdminDashboardModel>> resp = new JsonResponse<List<RestAdminDashboardModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_workprogress_count")
					.setParameter("district", district)
					.setParameter("block", block)
					.getResultList();

			for (Object[] m : x) {
				RestAdminDashboardModel restAdminDashboardModel = new RestAdminDashboardModel(m[0], m[1], m[2], m[3],
						m[4], m[5], m[6], m[7],m[8],m[9],m[10],m[11],m[12]);
				admin.add(restAdminDashboardModel);
				
			}
			resp.setBody(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method :  getAdminCountDetails ends"+resp);
		return resp;
	}
	
	
	//
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestAdminDashboardModel>>> restProjectDetails(String district,String block) {
		logger.info("Method : ProjectDetailsDao starts");
		List<RestAdminDashboardModel> req = new ArrayList<RestAdminDashboardModel>();
		JsonResponse<List<RestAdminDashboardModel>> resp = new JsonResponse<List<RestAdminDashboardModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_dashboard_pie")
					.setParameter("district", district)
					.setParameter("block", block)
					.getResultList();
			for (Object[] m : x) {
				RestAdminDashboardModel reqemp = new RestAdminDashboardModel(m[0],m[1]);
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<RestAdminDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestAdminDashboardModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method : ProjectDetailsDao ends" + response);
		return response;
	}
	
	
	//admin dashbord bar chart
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestAdminDashboardModel>>> restAdminBarchart(String district , String block) {
		logger.info("Method : barchartDetails starts");
		List<RestAdminDashboardModel> req = new ArrayList<RestAdminDashboardModel>();
		JsonResponse<List<RestAdminDashboardModel>> resp = new JsonResponse<List<RestAdminDashboardModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_dashboard_bar")
					.setParameter("district", district)
					.setParameter("block", block)
					.getResultList();
			for (Object[] m : x) {
				RestAdminDashboardModel reqemp = new RestAdminDashboardModel(m[0],m[1]);
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<RestAdminDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestAdminDashboardModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method : barchartDetails ends" + response);
		return response;
	}
	
	
	
	//get district list
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDistrictsList() {
		logger.info("Method : getDistrictsListDao starts");

		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("district_list_dropdown_dashboard")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				specialityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDistrictsListDao ends"+specialityList);

		return specialityList;
	}
	
	
	
	//get block list
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getBlockListDropdownDao(Integer id) {

		logger.info("Method : getBlockListDropdown Dao starts");
		List<DropDownModel> blockList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_admin_block_list_dashboard")
					.setParameter("district", id)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				blockList.add(dropDownModel);
			}

			resp.setBody(blockList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getBlockListDropdown Dao ends" + resp);
		return resp;
	}
}
