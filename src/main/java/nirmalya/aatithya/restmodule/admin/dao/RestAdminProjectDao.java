package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class RestAdminProjectDao {
	Logger logger = LoggerFactory.getLogger(RestAdminProjectDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;
	
	@Autowired
	EnvironmentVaribles env;
	
	
	// view

	  
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminProjectModel>> viewProjectDetails() {
		logger.info("Method : viewProjectDetails Dao starts");
		List<RestAdminProjectModel> admin = new ArrayList<RestAdminProjectModel>();
		JsonResponse<List<RestAdminProjectModel>> resp = new JsonResponse<List<RestAdminProjectModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_project_view")
					.getResultList();

			for (Object[] m : x) {
				
				
				RestAdminProjectModel so = new RestAdminProjectModel(m[0].toString() , m[1], m[2].toString());
				admin.add(so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : viewProjectDetails Dao ends"+resp);
		return resp;
	}

}
