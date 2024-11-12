package nirmalya.aatithya.restmodule.districtlevel.dao;

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
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictProjectReportModel;

@Repository
public class RestDistrictProjectReportDao {
	Logger logger = LoggerFactory.getLogger(RestDistrictProjectReportDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;
	
	@Autowired
	EnvironmentVaribles env;
	
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDistrictList() {
		logger.info("Method : getDistrictListDao starts");

		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("dist_dropdown_district_list")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				specialityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDistrictListDao ends"+specialityList);

		return specialityList;
	}
	
	
	//get block list
			@SuppressWarnings("unchecked")
			public JsonResponse<List<DropDownModel>> getDistBlockListDao(Integer id) {

				logger.info("Method : getDistBlockList Dao starts");
				List<DropDownModel> blockList = new ArrayList<DropDownModel>();
				JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("dist_dropdown_block_list")
							.setParameter("district", id)
							.getResultList();
					for (Object[] m : x) {
						DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
						blockList.add(dropDownModel);
					}

					resp.setBody(blockList);

				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("Method : getDistBlockList Dao ends" + resp);
				return resp;
			}
	
	
			
			@SuppressWarnings("unchecked")
			public JsonResponse<List<DropDownModel>> getInstitutionListsDistDao(Integer id) {

				logger.info("Method : getInstitutionListsDist Daostarts");
				List<DropDownModel> blockList = new ArrayList<DropDownModel>();
				JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("dist_dropdown_institution_list")
							.setParameter("block", id)
							.getResultList();
					for (Object[] m : x) {
						DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
						blockList.add(dropDownModel);
					}

					resp.setBody(blockList);

				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("Method : getInstitutionListsDist Dao ends" + resp);
				return resp;
			}
			
			
			
			
			
			@SuppressWarnings("unchecked")
			public JsonResponse<List<RestDistrictProjectReportModel>> restDistProjectStatusUpdate(String block,String institution,String userId) {
				logger.info("Method : pojectStatusUpdateDao starts");
				System.out.println("RESRTTTTTTTTTTttttttttttt");
				List<RestDistrictProjectReportModel> admin = new ArrayList<RestDistrictProjectReportModel>();
				JsonResponse<List<RestDistrictProjectReportModel>> resp = new JsonResponse<List<RestDistrictProjectReportModel>>();

				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("district_project_report")
//							.setParameter("district", district)
							.setParameter("block", block)
							.setParameter("institution", institution)
							.setParameter("userId", userId)
							.getResultList();

					for (Object[] m : x) {
						String imageOne = null;
						if (m[11] != null && m[11] != "" && m[11] != " " && !m[11].toString().equals(" ")
								&& !m[11].toString().equals(null) && !m[11].toString().equals("")) {
							imageOne = env.getBaseURL() + "nirmalyaRest/document/document/" + m[11].toString();
						} else {
							imageOne = "";
						}
						
						
						String imageTwo = null;
						if (m[12] != null && m[12] != "" && m[12] != " " && !m[12].toString().equals(" ")
								&& !m[12].toString().equals(null) && !m[12].toString().equals("")) {
							imageTwo = env.getBaseURL() + "nirmalyaRest/document/document/" + m[12].toString();
						} else {
							imageTwo = "";
						}
						
						RestDistrictProjectReportModel so = new RestDistrictProjectReportModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7],m[8],m[9],m[10]
								,imageOne,imageTwo,m[13],m[14],m[15],m[16],m[17],m[18]);
						admin.add(so);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				resp.setBody(admin);
				logger.info("Method : pojectStatusUpdateDao ends"+resp);
				return resp;
			}
}
