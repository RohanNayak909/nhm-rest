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

import nirmalya.aatithya.restmodule.admin.model.RestAdminBlockModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
@Repository
public class RestAdminProjectUpdateDetailsDao {
	Logger logger = LoggerFactory.getLogger(RestAdminProjectUpdateDetailsDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restViewProjectDetails(String pageno,String district,String block,
			String institution,String scheme,String agency,String sanctionYear) {
		logger.info("Method : viewBlockDetails Dao starts");
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestAdminProjectUpdateDetailsModel> admin = new ArrayList<RestAdminProjectUpdateDetailsModel>();
		JsonResponse<List<RestAdminProjectUpdateDetailsModel>> resp = new JsonResponse<List<RestAdminProjectUpdateDetailsModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_project_details_view")
					.setParameter("pageno",pageno)
					.setParameter("district",district)
					.setParameter("block",block)
					.setParameter("institution",institution)
					.setParameter("scheme",scheme)
					.setParameter("agency",agency)
					.setParameter("sanctionYear",sanctionYear)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestAdminProjectUpdateDetailsModel so = new RestAdminProjectUpdateDetailsModel(m[0],m[1],m[2],m[3],m[4],m[5],
						m[6],m[7],m[8],m[9],m[10],m[11],m[12]);
				admin.add(so);
				//System.out.println("RESRTTTTTTTTTT#" + so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : viewBlockDetails Dao ends");
		return resp;
	}
	
	
	//edit project details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestAdminProjectUpdateDetailsModel>>> restProjectDetailsEdit(String id) {

		logger.info("Method : ProjectDetailsEdit Dao starts");
		List<RestAdminProjectUpdateDetailsModel> purchaseOrder = new ArrayList<RestAdminProjectUpdateDetailsModel>();
		JsonResponse<List<RestAdminProjectUpdateDetailsModel>> resp = new JsonResponse<List<RestAdminProjectUpdateDetailsModel>>();
		System.out.println("ID" + id);
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("edit_project_details").setParameter("p_projectId", id)
					.getResultList();
			for (Object[] m : x) {
			
				RestAdminProjectUpdateDetailsModel so = new RestAdminProjectUpdateDetailsModel(m[0],m[1],m[2],m[3],m[4],m[5],
						m[6],m[7],m[8],m[9],m[10],m[11],m[12],m[13],m[14],m[15]);

				purchaseOrder.add(so);
			}

			System.out.println("DAO" + purchaseOrder);

			resp.setBody(purchaseOrder);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// resp.setBody(purchaseOrder);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<RestAdminProjectUpdateDetailsModel>>> response = new ResponseEntity<JsonResponse<List<RestAdminProjectUpdateDetailsModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : ProjectDetailsEdit Dao ends");

		System.out.println("RESPP" + response);
		return response;

	}
	
	
	
	//delete project
	
	public ResponseEntity<JsonResponse<Object>> deleteprojectDetails(String projectId) {
		logger.info("Method : deleteProjectDao starts");
		logger.info("!@#$%^&*(&^%$#@!@"+projectId);
		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
		if (validity)
			try {
				em.createNamedStoredProcedureQuery("delete_project")
				.setParameter("projectId", projectId)
						.execute();
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		logger.info("Method : deleteProjectDao ends"+response);
		return response;
	}
}
