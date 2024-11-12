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
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestProjectPhysicalStausReportDao {
	Logger logger = LoggerFactory.getLogger(RestAdminProjectUpdatesDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	
	// get physical status
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getPhysicalStatusListsDao() {
		logger.info("Method : getPhysicalStatusListsDao starts");

		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_dropdow_physical_status_list")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				specialityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getCategoryListsDao ends"+specialityList);

		return specialityList;
	}
	
	
	
	// view physical status report of project
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restViewPhyStatusDetailsDao(
			String pageno,String district,String block,String category,String scheme,String agency,String sanctionYear,
			String phyStatus) {
		logger.info("Method : viewBlockDetails Dao starts"+phyStatus);
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestAdminProjectUpdateDetailsModel> admin = new ArrayList<RestAdminProjectUpdateDetailsModel>();
		JsonResponse<List<RestAdminProjectUpdateDetailsModel>> resp = new JsonResponse<List<RestAdminProjectUpdateDetailsModel>>();
        String physicalStatus = "";
        if (phyStatus.equals("1")) {
            physicalStatus = "not started";
        }
        else {
        	 physicalStatus = phyStatus;
        }

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("adminprojectphysicalstatusview")
					.setParameter("pageno",pageno)
					.setParameter("district",district)
					.setParameter("block",block)
					.setParameter("category",category)
					.setParameter("scheme",scheme)
					.setParameter("agency",agency)
					.setParameter("sanctionYear",sanctionYear)
					.setParameter("physicalStatus",physicalStatus)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestAdminProjectUpdateDetailsModel so = new RestAdminProjectUpdateDetailsModel(m[0],m[1],m[2],m[3],m[4],m[5],
						m[6],m[7],m[8],m[9],null);
				admin.add(so);
				System.out.println("RESRTTTTTTTTTT#" + so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		
		// Update phyStatus attribute to "Not Started" if it's null
	    for (RestAdminProjectUpdateDetailsModel model : admin) {
	        if (model.getPhyStatus() == null) {
	            model.setPhyStatus("Not Started");
	        }
	    }
		
		logger.info("Method : viewBlockDetails Dao ends"+resp);
		return resp;
	}
}
