package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminDashboardModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectUpdationReportModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestProjectStatusAbstractReportDao {
	Logger logger = LoggerFactory.getLogger(RestProjectStatusAbstractReportDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;
	
	@Autowired
	EnvironmentVaribles env;
	
	
	//project physical abstract report view
	

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminDashboardModel>> projectPhyAbstractReportViewDao() {
		logger.info("Method : projectPhyAbstractReportViewDao starts");
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestAdminDashboardModel> admin = new ArrayList<RestAdminDashboardModel>();
		JsonResponse<List<RestAdminDashboardModel>> resp = new JsonResponse<List<RestAdminDashboardModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_project_physical_abstract_report")
					.getResultList();

			for (Object[] m : x) {
				
				RestAdminDashboardModel so = new RestAdminDashboardModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7],m[8],m[9],m[10],m[11],m[12],m[13],m[14],m[15],m[16],m[17],m[18],m[19],
						m[20],m[21],m[22]);
				admin.add(so);
				//System.out.println("RESRTTTTTTTTTT#" + so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : projectPhyAbstractReportViewDao ends"+resp);
		return resp;
	}
	
}
