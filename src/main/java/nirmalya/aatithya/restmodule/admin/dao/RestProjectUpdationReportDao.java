package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectUpdationReportModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestProjectUpdationReportDao {
	Logger logger = LoggerFactory.getLogger(RestProjectUpdationReportDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;
	
	@Autowired
	EnvironmentVaribles env;
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getJeListDao(String district,String block) {

		logger.info("Method : getBlockJENameDao starts"+district);
		List<DropDownModel> jeName = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_blockwise_je_name")
					.setParameter("district", district)
					.setParameter("block", block)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				jeName.add(dropDownModel);
			}

			resp.setBody(jeName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getBlockJENameDao ends" + resp);
		return resp;
	}
	
	
	//project updation report view
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestProjectUpdationReportModel>> projectUpdationReportViewDao(String fromDate,String toDate) {
		logger.info("Method : restProjectUpdationReportViewDao starts");
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestProjectUpdationReportModel> admin = new ArrayList<RestProjectUpdationReportModel>();
		JsonResponse<List<RestProjectUpdationReportModel>> resp = new JsonResponse<List<RestProjectUpdationReportModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_project_updation_report")
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.getResultList();

			for (Object[] m : x) {
				
				RestProjectUpdationReportModel so = new RestProjectUpdationReportModel(m[0],m[1],m[2],
						m[3], m[4], m[5], m[6], m[7],m[8]);
				admin.add(so);
				//System.out.println("RESRTTTTTTTTTT#" + so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : restProjectUpdationReportViewDao ends"+resp);
		return resp;
	}
}
