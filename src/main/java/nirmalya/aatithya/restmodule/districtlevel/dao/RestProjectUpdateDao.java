package nirmalya.aatithya.restmodule.districtlevel.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictLevelDashboardModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Repository
public class RestProjectUpdateDao {
	Logger logger = LoggerFactory.getLogger(RestProjectUpdateDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;

	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getBlockList(String userId) {
		logger.info("Method : getBlockListDao starts");

		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_block_list")
					.setParameter("district", userId)
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				specialityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getBlockListDao ends"+specialityList);

		return specialityList;
	}
	
	
	//get block
//	@SuppressWarnings("unchecked")
//	public JsonResponse<List<DropDownModel>> getBlockList(Integer userId) {
//
//		logger.info("Method : getBlockList Dao starts");
//		List<DropDownModel> blockList = new ArrayList<DropDownModel>();
//		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
//		try {
//			List<Object[]> x = em.createNamedStoredProcedureQuery("get_block_list")
//					.setParameter("district", userId)
//					.getResultList();
//			for (Object[] m : x) {
//				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
//				blockList.add(dropDownModel);
//			}
//
//			resp.setBody(blockList);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		logger.info("Method : getBlockList Dao ends" + resp);
//		return resp;
//	}
	
	//get institute list
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getInstitudesDao(Integer id) {
		
		logger.info("Method : getInstitudesDao starts");
		List<DropDownModel> blockList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_institude_list")
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
		
		logger.info("Method : getInstitudesDao  ends" + resp);
		return resp;
	}
	
	//get scheme list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getSchemeList() {
		logger.info("Method : getSchemeListDao starts");

		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("nhm_Scheme_list")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				specialityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getSchemeListDao ends"+specialityList);

		return specialityList;
	}
	
	//get agency list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getAgencyList() {
		logger.info("Method : getAgencyListDao starts");
		
		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("nhm_Agency_list")
					.getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				specialityList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getAgencyListDao ends"+specialityList);
		
		return specialityList;
	}
	
	
	
	//get physical status
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getPhysicalStatusList() {
		logger.info("Method : getAgencyListDao starts");
		
		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("nhm_physical_status_list")
					.getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				specialityList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getAgencyListDao ends"+specialityList);
		
		return specialityList;
	}
	
	
	//submit project update
	public ResponseEntity<JsonResponse<RestDistrictLevelDashboardModel>> addProjectUpdate(RestDistrictLevelDashboardModel restDistrictNhmModel) {
		logger.info("Method : enterProjectUpdates starts"+restDistrictNhmModel);
		RestDistrictLevelDashboardModel pur = new RestDistrictLevelDashboardModel();

		JsonResponse<RestDistrictLevelDashboardModel> resp = new JsonResponse<RestDistrictLevelDashboardModel>();
		try {
			if (restDistrictNhmModel.getProjectId() != null ) {
				
				em.createNamedStoredProcedureQuery("add_project_update")
				        .setParameter("p_blockId", restDistrictNhmModel.getBlockId())
						.setParameter("p_institude", restDistrictNhmModel.getInstitude())
						.setParameter("p_scheme", restDistrictNhmModel.getScheme())
						.setParameter("p_category", restDistrictNhmModel.getCategory())
						.setParameter("p_agency", restDistrictNhmModel.getAgency())
						.setParameter("p_sanctionYear", restDistrictNhmModel.getSanctionYear())
						.setParameter("p_projectName", restDistrictNhmModel.getProjectName())
						.setParameter("p_approvedAmt", restDistrictNhmModel.getApprovedAmt())
						.setParameter("p_abyaCost",restDistrictNhmModel.getAbyaCost())
						.setParameter("p_userId",restDistrictNhmModel.getUserId())
						.execute();

			} 

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

				System.out.println("DAO" + pur);

				resp.setBody(pur);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestDistrictLevelDashboardModel>> response = new ResponseEntity<JsonResponse<RestDistrictLevelDashboardModel>>(resp,
				responseHeaders, HttpStatus.CREATED);

		logger.info("Method : enterProjectUpdates ends"+resp);
		return response;
	}
	
	//check sanction year
	@SuppressWarnings("unchecked")
	public JsonResponse<DropDownModel> restGetSanctionYear(String id,String blockidds,String userId) {

		logger.info("Method : restGetSanctionYearDao starts");
		DropDownModel sanctionYear = new DropDownModel();
		JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_sanction_year")
					.setParameter("year", id)
					.setParameter("blockidds", blockidds)
					.setParameter("userId", userId)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				sanctionYear=dropDownModel;
			}

			resp.setBody(sanctionYear);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : restGetSanctionYearDao ends");
		logger.info("!@#$%%$##$#$%$$" + id);
		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCatagoryDistDao(Integer id) {

		logger.info("Method : getCatagoryListDao starts");
		List<DropDownModel> blockList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_dist_category")
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

		logger.info("Method : getCatagoryListDao  ends" + resp);
		return resp;
	}
}
