package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminFinancialModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.api.model.APIProjectStatusModel;
import nirmalya.aatithya.restmodule.api.model.RestFinancialModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestProjectUpdateDao;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;

@Repository
public class RestAdminProjectUpdatesDao {
	Logger logger = LoggerFactory.getLogger(RestAdminProjectUpdatesDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDistrictLists() {
		logger.info("Method : getDistrictListsDao starts");

		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_dropdown_district_list")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				specialityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDistrictListsDao ends"+specialityList);

		return specialityList;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getBlockListsDao(Integer id) {

		logger.info("Method : getBlockLists Dao starts"+id);
		List<DropDownModel> blockList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_dropdown_block_list")
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

		logger.info("Method : getBlockLists Dao ends" + resp);
		return resp;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getInstitutionListsDao(Integer district, Integer block,Integer category,
			String categoryName) {

		logger.info("Method : getInstitutionListsDao starts"+district +" "+block+" "+category+" "+categoryName);
		List<DropDownModel> blockList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_dropdown_institution_list")
					.setParameter("district", district)
					.setParameter("block", block)
					.setParameter("category", category)
					.setParameter("categoryName", categoryName)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				blockList.add(dropDownModel);
				
				logger.info("dropDownModel" + dropDownModel);
			}

			resp.setBody(blockList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getInstitutionListsDao  ends" + resp);
		return resp;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCatagoryDao(Integer id) {

		logger.info("Method : getCatagoryDao starts");
		List<DropDownModel> blockList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_category")
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

		logger.info("Method : getCatagoryDao  ends" + resp);
		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getSchemeLists() {
		logger.info("Method : getSchemeListsDao starts");

		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("nhm_admin_scheme_list")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				specialityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getSchemeListsDao ends"+specialityList);

		return specialityList;
	}
	
	//get agency list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getAgencyLists() {
		logger.info("Method : getAgencyListsDao starts");
		
		List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("nhm_admin_agency_list")
					.getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				specialityList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getAgencyListsDao ends"+specialityList);
		
		return specialityList;
	}
	
	
//	public ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>> addAdminProjectUpdate(RestAdminProjectUpdateDetailsModel restNhmModel) {
//		logger.info("Method : enterProjectUpdatesDao starts"+restNhmModel);
//		RestAdminProjectUpdateDetailsModel pur = new RestAdminProjectUpdateDetailsModel();
//		JsonResponse<RestAdminProjectUpdateDetailsModel> resp = new JsonResponse<RestAdminProjectUpdateDetailsModel>();
//		
//		String financialList = "";
//		String order_id1 = "";
//		
//		if(restNhmModel.getFinancialList()!= null) {
//		if (restNhmModel.getFinancialList().size() > 0) {
//
//			for (RestAdminFinancialModel m : restNhmModel.getFinancialList()) {
//				//String ProjectId = restNhmModel.getProjectId();
//				//String ProjectName = restNhmModel.getNameOfProject();
//				
//				financialList = financialList + "(order_id1,\'" + m.getScheme() + "\',\'" + m.getYearOfSanction() + "\',\'" + m.getApprovedAmount() + "\',"
//								+ "\'" +  m.getAvailableCost() + "\' )";
//				System.out.println("addlabtestpackageDetails@@@@@@@@@@@@@@@@@@@@"+financialList);
//			}
//			
//			financialList = financialList.substring(0, financialList.length() - 1);
//
//		} 
//			
//			  else { financialList = ""; 
//			  }
//			 
//		}
//		
//		try {
//			if (restNhmModel.getBlock() == null ) {
//				em.createNamedStoredProcedureQuery("admin_add_project_update")
//				        //.setParameter("p_projectId", restNhmModel.getProjectId())
//				        .setParameter("p_districtId", restNhmModel.getDistrict())
//				        .setParameter("p_blockId", restNhmModel.getBlock())
//						.setParameter("p_institude", restNhmModel.getInstitute())
//						.setParameter("p_category", restNhmModel.getCategory())
//						.setParameter("p_agency", restNhmModel.getAgency())
//						.setParameter("p_projectName", restNhmModel.getNameOfProject())
//						.setParameter("p_userId",restNhmModel.getUserId())
//						.setParameter("p_fundRelease",restNhmModel.getFundRelease())
//						.setParameter("financialList",financialList)
//						.getResultList();
//
//			} 
//
//		} catch (Exception e) {
//			try {
//				String[] err = serverDao.errorProcedureCall(e);
//
//				resp.setCode(err[0]);
//				resp.setMessage(err[1]);
//
//				System.out.println("DAO" + pur);
//
//				resp.setBody(pur);
//
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		}
//
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.set("MyResponseHeader", "MyValue");
//
//		ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>> response = new ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>>(resp,
//				responseHeaders, HttpStatus.CREATED);
//
//		logger.info("Method : enterProjectUpdatesDao ends"+resp);
//		return response;
//	}
	
	
	
	public ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>> addAdminProjectUpdate(RestAdminProjectUpdateDetailsModel restNhmModel) {
		logger.info("Method : enterProjectUpdatesDao starts"+restNhmModel);
		RestAdminProjectUpdateDetailsModel pur = new RestAdminProjectUpdateDetailsModel();
		JsonResponse<RestAdminProjectUpdateDetailsModel> resp = new JsonResponse<RestAdminProjectUpdateDetailsModel>();
		
		String financialList = "";
		String order_id1 = "";

		
		if (restNhmModel.getFinancialList() != null) {
			if (restNhmModel.getFinancialList().size() > 0) {
				for (RestAdminFinancialModel m : restNhmModel.getFinancialList()) {
					financialList = financialList + "(order_id1,\'" + m.getScheme() + "\',\'" + m.getYearOfSanction() + "\',\'"
							+ m.getApprovedAmount() + "\',\'"+restNhmModel.getNameOfProject()+"\',\'"+m.getFundRelease()+"\'),";
				}
				financialList = financialList.substring(0, financialList.length() - 1);
			} else {
				financialList = "";
			}
		}
		System.out.println("financialList"+financialList);
		String distrcitId="";
		if(!StringUtil.isNull(restNhmModel.getDistrict())) {
			distrcitId=restNhmModel.getDistrict();
		}
		else {
			System.out.println("else"+restNhmModel.getUser());
			distrcitId=restNhmModel.getUser();
		}
		try {
			if (StringUtil.isNull(restNhmModel.getProjectId())) {
				System.out.println("if"+restNhmModel.getBlock());
				em.createNamedStoredProcedureQuery("admin_add_project_update")
				        //.setParameter("p_projectId", restNhmModel.getProjectId())
				        .setParameter("p_districtId", distrcitId)
				        .setParameter("p_blockId", restNhmModel.getBlock())
						.setParameter("p_institude", restNhmModel.getInstitute())
						.setParameter("p_category", restNhmModel.getCategory())
						.setParameter("p_agency", restNhmModel.getAgency())
						.setParameter("p_projectName", restNhmModel.getNameOfProject())
						.setParameter("p_latitude", restNhmModel.getLatitude())
						.setParameter("p_longitude", restNhmModel.getLongitude())
						.setParameter("p_userId",restNhmModel.getUserId())
						.setParameter("p_availableCost",restNhmModel.getAvailableCost())
						.setParameter("p_reverserabyacost",restNhmModel.getReverserabyacost())
						.setParameter("financialList",financialList)
						.getResultList();

			} else {
				System.out.println("else");
				em.createNamedStoredProcedureQuery("admin_modify_project_update")
		        .setParameter("p_projectId", restNhmModel.getProjectId())
		        .setParameter("p_districtId", distrcitId)
		        .setParameter("p_blockId", restNhmModel.getBlock())
				.setParameter("p_institude", restNhmModel.getInstitute())
				.setParameter("p_category", restNhmModel.getCategory())
				.setParameter("p_agency", restNhmModel.getAgency())
				.setParameter("p_projectName", restNhmModel.getNameOfProject())
				.setParameter("p_userId",restNhmModel.getUserId())
				.setParameter("p_availableCost",restNhmModel.getAvailableCost())
				.setParameter("p_reverserabyacost",restNhmModel.getReverserabyacost())
			//	.setParameter("p_fundRelease",restNhmModel.getFundRelease())
				.setParameter("financialList",financialList)
				.getResultList();
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

		ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>> response = new ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>>(resp,
				responseHeaders, HttpStatus.CREATED);

		logger.info("Method : enterProjectUpdatesDao ends"+resp);
		return response;
	}
	
	
	
	
	//check sanction year
	@SuppressWarnings("unchecked")
	public JsonResponse<DropDownModel> restGetSanctionYearAdmin(String id,String blockidds,String userId) {

		logger.info("Method : restGetSanctionYearsDao starts");
		DropDownModel sanctionYear = new DropDownModel();
		JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_sanction_year_admin")
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

		logger.info("Method : restGetSanctionYearsDao ends");
		logger.info("!@#$%%$##$#$%$$" + id);
		return resp;
	}
	
	
	
	
	
	//add scheme details
	
	public ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>> addSchemeDetails(RestAdminProjectUpdateDetailsModel restDistrictNhmModel) {
		logger.info("Method : enterProjectUpdatesDao starts"+restDistrictNhmModel);
		RestAdminProjectUpdateDetailsModel pur = new RestAdminProjectUpdateDetailsModel();

		JsonResponse<RestAdminProjectUpdateDetailsModel> resp = new JsonResponse<RestAdminProjectUpdateDetailsModel>();
		try {
			if (restDistrictNhmModel.getProjectId() != null ) {
				em.createNamedStoredProcedureQuery("admin_add_project_update")
						.setParameter("p_scheme", restDistrictNhmModel.getScheme())
						.setParameter("p_sanctionYear", restDistrictNhmModel.getYearOfSanction())
						.setParameter("p_projectName", restDistrictNhmModel.getNameOfProject())
						.setParameter("p_approvedAmt", restDistrictNhmModel.getApprovedAmount())
						.setParameter("p_abyaCost",restDistrictNhmModel.getAvailableCost())
//						.setParameter("p_floor",restDistrictNhmModel.getFloor())
						.setParameter("p_userId",restDistrictNhmModel.getUserId())
//						.setParameter("p_phyStatus",restDistrictNhmModel.getPhyStatus())
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

		ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>> response = new ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>>(resp,
				responseHeaders, HttpStatus.CREATED);

		logger.info("Method : enterProjectUpdatesDao ends"+resp);
		return response;
	}
	
	
	//get category list
	
		@SuppressWarnings("unchecked")
		public List<DropDownModel> getCategoryLists() {
			logger.info("Method : getCategoryListsDao starts");

			List<DropDownModel> specialityList = new ArrayList<DropDownModel>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("admin_dropdown_category_list")
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
		
		
		
		//get address
		
//		@SuppressWarnings("unchecked")
//		public JsonResponse<DropDownModel> restGetAddressDao(String institution) {
//
//			logger.info("Method : restGetSanctionYearsDao starts");
//			DropDownModel address = new DropDownModel();
//			JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();
//			try {
//				List<Object[]> x = em.createNamedStoredProcedureQuery("admin_get_address")
//						.setParameter("institution", institution)
//						.getResultList();
//				for (Object[] m : x) {
//					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
//					address=dropDownModel;
//				}
//
//				resp.setBody(address);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			logger.info("Method : restGetSanctionYearsDao ends");
//			logger.info("!@#$%%$##$#$%$$" + institution);
//			return resp;
//		}
		
		
		
		
		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> restGetAddressDao(String institution) {

			logger.info("Method : getAddressDao starts");
			List<DropDownModel> blockList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("admin_get_address")
						.setParameter("institution", institution)
						.getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					blockList.add(dropDownModel);
				}

				resp.setBody(blockList);

			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : getAddressDao ends" + resp);
			return resp;
		}
	
}
