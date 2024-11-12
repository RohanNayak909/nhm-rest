package nirmalya.aatithya.restmodule.districtlevel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectUpdatesDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictProjectReportModel;

@Repository
public class RestDistProjectPhysicalStausReportDao {
	Logger logger = LoggerFactory.getLogger(RestDistProjectPhysicalStausReportDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	

	// view physical status report of project
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestDistrictProjectReportModel>> restDistViewPhyStatusDetailsDao(String pageno,String district,String block,
			String phyStatus) {
		logger.info("Method : viewBlockDetails Dao starts"+phyStatus);
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestDistrictProjectReportModel> admin = new ArrayList<RestDistrictProjectReportModel>();
		JsonResponse<List<RestDistrictProjectReportModel>> resp = new JsonResponse<List<RestDistrictProjectReportModel>>();
        String physicalStatus = "";
        if (phyStatus.equals("1")) {
            physicalStatus = "not started";
        }
        else {
        	 physicalStatus = phyStatus;
        }

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("distprojectphysicalstatusview")
					.setParameter("pageno",pageno)
					.setParameter("district",district)
					.setParameter("block",block)
					.setParameter("physicalStatus",physicalStatus)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestDistrictProjectReportModel so = new RestDistrictProjectReportModel(m[0],m[1],m[2],m[3],m[4],m[5],
						m[6],m[7],m[8]);
				admin.add(so);
				//System.out.println("RESRTTTTTTTTTT#" + so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		
		// Update phyStatus attribute to "Not Started" if it's null
	    for (RestDistrictProjectReportModel model : admin) {
	        if (model.getPhyStatus() == null) {
	            model.setPhyStatus("Not Started");
	        }
	    }
		
		logger.info("Method : viewBlockDetails Dao ends"+resp);
		return resp;
	}
}
