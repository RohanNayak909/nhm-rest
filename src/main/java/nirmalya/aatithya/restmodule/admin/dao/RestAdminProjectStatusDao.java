package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestAdminProjectStatusDao {
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
	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restProjectStatus(String userId) {
		logger.info("Method : projectStatusDao starts");
		List<RestAdminProjectUpdateDetailsModel> admin = new ArrayList<RestAdminProjectUpdateDetailsModel>();
		JsonResponse<List<RestAdminProjectUpdateDetailsModel>> resp = new JsonResponse<List<RestAdminProjectUpdateDetailsModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_project_status")
					.setParameter("userId",userId)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestAdminProjectUpdateDetailsModel so = new RestAdminProjectUpdateDetailsModel(m[0],m[1],m[2]);
				admin.add(so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : projectStatusDao ends"+resp);
		return resp;
	}
}
