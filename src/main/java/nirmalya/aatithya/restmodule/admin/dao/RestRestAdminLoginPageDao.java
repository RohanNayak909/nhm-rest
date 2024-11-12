package nirmalya.aatithya.restmodule.admin.dao;

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

import nirmalya.aatithya.restmodule.admin.model.RestAdminDashboardModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestRestAdminLoginPageDao {
	Logger logger = LoggerFactory.getLogger(RestRestAdminLoginPageDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;
	
	@Autowired
	EnvironmentVaribles env;
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> restGetAdminCountDetails() {
		logger.info("Method :  getAdminCountDetails starts");

		List<DropDownModel> admin = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_loginpage_count")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1], m[2], m[3]);
				admin.add(dropDownModel);
				
			}
			resp.setBody(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method :  getAdminCountDetails ends"+resp);
		return resp;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> restLoginPieChart() {
		logger.info("Method : ProjectDetailsDao starts");
		List<DropDownModel> req = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_login_piechart")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel reqemp = new DropDownModel(m[0],m[1]);
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method : ProjectDetailsDao ends" + response);
		return response;
	}
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> loginProjects() {
		logger.info("Method : loginProjects starts");
		List<DropDownModel> req = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_login_projects")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel reqemp = new DropDownModel(m[0], m[1]);
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method : loginProjects ends" + response);
		return response;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> projectAgencyCountDao(String district,String block) {
		logger.info("Method : projectAgencyCountDao starts");
		List<DropDownModel> req = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("projects_agency_count")
					.setParameter("district",district)
					.setParameter("block",block)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel reqemp = new DropDownModel(m[0], m[1]);
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method : projectAgencyCountDao ends" + response);
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> projectSchemeCountDao(String district,String block) {
		logger.info("Method : projectSchemeCountDao starts");
		List<DropDownModel> req = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("projects_scheme_count")
					.setParameter("district",district)
					.setParameter("block",block)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel reqemp = new DropDownModel(m[0], m[1]);
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method : projectSchemeCountDao ends" + response);
		return response;
	}
	
	
}
