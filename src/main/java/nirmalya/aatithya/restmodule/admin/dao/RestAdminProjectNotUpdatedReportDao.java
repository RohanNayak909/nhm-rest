package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictProjectNotUpdatedReportDao;

@Repository
public class RestAdminProjectNotUpdatedReportDao {
	Logger logger = LoggerFactory.getLogger(RestDistrictProjectNotUpdatedReportDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;
	
	@Autowired
	EnvironmentVaribles env;
	
	
	//view agency
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestProjectPhysicalStatusModel>> ViewProjectNotUpdatedListAdminDao(String fromDate,String toDate) {
		logger.info("Method : ViewProjectNotUpdatedListAdminDao starts");
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestProjectPhysicalStatusModel> admin = new ArrayList<RestProjectPhysicalStatusModel>();
		JsonResponse<List<RestProjectPhysicalStatusModel>> resp = new JsonResponse<List<RestProjectPhysicalStatusModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_project_not_updated_list")
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.getResultList();

			for (Object[] m : x) {
				
				RestProjectPhysicalStatusModel so = new RestProjectPhysicalStatusModel(m[0],m[1],m[2],
						m[3], m[4], m[5], m[6],m[7],m[8],m[9],null,null);
				admin.add(so);
				//System.out.println("RESRTTTTTTTTTT#" + so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : ViewProjectNotUpdatedListAdminDao ends"+resp);
		return resp;
	}
}
