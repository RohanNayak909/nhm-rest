package nirmalya.aatithya.restmodule.districtlevel.dao;

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

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectUpdateDetailsDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDownloadExcelModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictProjectReportModel;
import nirmalya.aatithya.restmodule.util.StringUtil;
@Repository
public class RestDistrictProjectUpdateDao {
	Logger logger = LoggerFactory.getLogger(RestDistrictProjectUpdateDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restViewProjectDetails(String pageno,String district,String block,
			String category,String institution,String scheme,String agency,String sanctionYear) {
		logger.info("Method : restViewProjectDetails Dao starts");
		System.out.println("district"+district);
		System.out.println("block"+block);
		System.out.println("category"+category);
		System.out.println("institution"+institution);
		System.out.println("scheme"+scheme);
		System.out.println("agency"+agency);
		System.out.println("sanctionYear"+sanctionYear);
		List<RestAdminProjectUpdateDetailsModel> admin = new ArrayList<RestAdminProjectUpdateDetailsModel>();
		JsonResponse<List<RestAdminProjectUpdateDetailsModel>> resp = new JsonResponse<List<RestAdminProjectUpdateDetailsModel>>();

		try {
			if(StringUtil.isNull(district)) {
			List<Object[]> x = em.createNamedStoredProcedureQuery("district_project_details_view")
					.setParameter("pageno",pageno)
					.setParameter("district",district)
					.setParameter("block",block)
					.setParameter("category",category)
					.setParameter("institution",institution)
					.setParameter("scheme",scheme)
					.setParameter("agency",agency)
					.setParameter("sanctionYear",sanctionYear)
					.getResultList();

			for (Object[] m : x) {
				RestAdminProjectUpdateDetailsModel so = new RestAdminProjectUpdateDetailsModel(m[0],m[1],m[2],m[3],m[4],m[5],
						m[6],m[7],m[8],m[9],m[10],m[11],m[12],m[13],m[14],m[15],null);
				admin.add(so);
				
			}
			}
			else {
				List<Object[]> x = em.createNamedStoredProcedureQuery("district_manage_project_view")
						.setParameter("pageno",pageno)
						.setParameter("district",district)
						.setParameter("block",block)
						.setParameter("category",category)
						.setParameter("institution",institution)
						.setParameter("scheme",scheme)
						.setParameter("agency",agency)
						.setParameter("sanctionYear",sanctionYear)
						.getResultList();

				for (Object[] m : x) {
					RestAdminProjectUpdateDetailsModel so = new RestAdminProjectUpdateDetailsModel(m[0],m[1],m[2],m[3],m[4],m[5],
							m[6],m[7],m[8],m[9],m[10],m[11],m[12],m[13],m[14],m[15],null);
					admin.add(so);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : restViewProjectDetails Dao ends"+resp);
		return resp;
	}
	//get institution list
	
		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> getInstitutionListsDao(Integer district, Integer block) {

			logger.info("Method : getInstitutionListsDao starts");
			List<DropDownModel> blockList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("district_dropdown_institution_list")
						.setParameter("district", district)
						.setParameter("block", block)
						.getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					blockList.add(dropDownModel);
					
					//logger.info("dropDownModel" + dropDownModel);
				}

				resp.setBody(blockList);

			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : getInstitutionListsDao  ends" + resp);
			return resp;
		}
		
		
		// project details for download excel
		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestAdminDownloadExcelModel>> restviewProjectDetailsForExcelDownload() {
			logger.info("Method : restviewProjectDetailsForExcelDownload Dao starts");
		
			List<RestAdminDownloadExcelModel> admin = new ArrayList<RestAdminDownloadExcelModel>();
			JsonResponse<List<RestAdminDownloadExcelModel>> resp = new JsonResponse<List<RestAdminDownloadExcelModel>>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("project_details_for_excel_download")
						.getResultList();

				for (Object[] m : x) {
					RestAdminDownloadExcelModel so = new RestAdminDownloadExcelModel(m[0],m[1],m[2],m[3],m[4],m[5],
							m[6],m[7],m[8],m[9],m[10],m[11],m[12],m[13]);
					admin.add(so);
					resp.setMessage("Success");
					resp.setCode("10");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(admin);
			logger.info("Method : restviewProjectDetailsForExcelDownload Dao ends"+resp);
			return resp;
		}
		
		
		
	
		

//		@SuppressWarnings("unchecked")
//		public JsonResponse<Object> restviewProjectDetailsForExcelDownload() {
//			logger.info("Method : restviewProjectDetailsForExcelDownload Dao ends");
//			
//			JsonResponse<Object> resp = new JsonResponse<Object>();
//			try {
//				List<Object[]> x = em.createNamedStoredProcedureQuery("project_details_for_excel_download")
//						.getResultList();
//				
//				resp.setBody(x.get(0));
//				resp.setCode("10");
//				resp.setMessage("Data fetched successfully");
//				System.out.println("Response>>>>>>>== "+resp);
//			}
//			catch (Exception e) {
//				resp.setCode("failed");
//				resp.setMessage(e.getMessage());
//				e.printStackTrace();
//			}
//			logger.info("Method :restviewProjectDetailsForExcelDownload Dao ends"+ resp);
//			return resp;
//			
//		}
		
		
		
		
		
}
