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
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictProjectUpdateDao;
import nirmalya.aatithya.restmodule.util.StringUtil;

@Repository
public class RestAdminProjectInstitutionWiseReportDao {
	Logger logger = LoggerFactory.getLogger(RestDistrictProjectUpdateDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	

//	@SuppressWarnings("unchecked")
//	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restInstitutionWiseProjectDetailsDao(String pageno,String district,String block,
//			String category,String institution) {
//		logger.info("Method : restInstitutionWiseProjectDetails Dao starts");
//		List<RestAdminProjectUpdateDetailsModel> admin = new ArrayList<RestAdminProjectUpdateDetailsModel>();
//		JsonResponse<List<RestAdminProjectUpdateDetailsModel>> resp = new JsonResponse<List<RestAdminProjectUpdateDetailsModel>>();
//
//		try {
////			if(StringUtil.isNull(district)) {
//			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_instwise_project_details")
//					.setParameter("pageno",pageno)
//					.setParameter("district",district)
//					.setParameter("block",block)
//					.setParameter("category",category)
//					.setParameter("institution",institution)
//					.getResultList();
//
//			for (Object[] m : x) {
//				RestAdminProjectUpdateDetailsModel so = new RestAdminProjectUpdateDetailsModel(m[0],m[1],m[2],m[3],m[4],m[5],
//						m[6],m[7],m[8],m[9],m[10],m[11],m[12],m[13],m[14],m[15],null);
//				admin.add(so);
//				
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		resp.setBody(admin);
//		logger.info("Method : restInstitutionWiseProjectDetails Dao ends"+resp);
//		return resp;
//	}
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restInstitutionWiseProjectDetailsDao(String pageno) {
		logger.info("Method : restInstitutionWiseProjectDetails Dao starts");
		List<RestAdminProjectUpdateDetailsModel> admin = new ArrayList<RestAdminProjectUpdateDetailsModel>();
		JsonResponse<List<RestAdminProjectUpdateDetailsModel>> resp = new JsonResponse<List<RestAdminProjectUpdateDetailsModel>>();
		
		try {
//			if(StringUtil.isNull(district)) {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_instwise_project_details")
					.setParameter("pageno",pageno)
					.getResultList();
			
			for (Object[] m : x) {
				RestAdminProjectUpdateDetailsModel so = new RestAdminProjectUpdateDetailsModel(m[0],m[1],m[2],m[3],m[4],m[5],
						m[6]);
				admin.add(so);
				
			}
//			}
//			else {
//				List<Object[]> x = em.createNamedStoredProcedureQuery("district_manage_project_view")
//						.setParameter("pageno",pageno)
//						.setParameter("district",district)
//						.setParameter("block",block)
//						.setParameter("category",category)
//						.setParameter("institution",institution)
//						.getResultList();
//
//				for (Object[] m : x) {
//					RestAdminProjectUpdateDetailsModel so = new RestAdminProjectUpdateDetailsModel(m[0],m[1],m[2],m[3],m[4],m[5],
//							m[6],m[7],m[8],m[9],m[10],m[11],m[12],m[13],m[14],m[15],null);
//					admin.add(so);
//			}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : restInstitutionWiseProjectDetails Dao ends"+resp);
		return resp;
	}
	
}
