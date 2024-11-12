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

import nirmalya.aatithya.restmodule.admin.model.RestAdminBlockModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminInstitutionModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestAdminInstitutionDao {
	Logger logger = LoggerFactory.getLogger(RestAdminInstitutionDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	// view

	  
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminInstitutionModel>> viewInstitutionDetails(Integer pageno,String district,String block) {
		logger.info("Method : viewInstitutionDetails Dao starts");
		List<RestAdminInstitutionModel> admin = new ArrayList<RestAdminInstitutionModel>();
		JsonResponse<List<RestAdminInstitutionModel>> resp = new JsonResponse<List<RestAdminInstitutionModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_institution_view")
					.setParameter("pageno", pageno)
					.setParameter("district", district)
					.setParameter("block", block)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestAdminInstitutionModel so = new RestAdminInstitutionModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7],m[8]);
				admin.add(so);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : viewInstitutionDetails Dao ends"+resp);
		return resp;
	}
	
	
	
	//edit
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestAdminInstitutionModel>> editInstitutionDetails(String id) {
	    logger.info("Method : editInstitutionDetails Dao starts");
	    
	    RestAdminInstitutionModel restAdminInstitutionModel = new RestAdminInstitutionModel();
	    JsonResponse<RestAdminInstitutionModel> resp = new JsonResponse<RestAdminInstitutionModel>();
	    
	    try {
	        List<Object[]> x = em.createNamedStoredProcedureQuery("edit_institution_Details")
	                             .setParameter("p_instId", id)
	                             .getResultList();
	        
	        for (Object[] m : x) {
	            RestAdminInstitutionModel so = new RestAdminInstitutionModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7]);
	            restAdminInstitutionModel = so;
	        }
	        resp.setBody(restAdminInstitutionModel);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("MyResponseHeader", "MyValue");
	    
	    ResponseEntity<JsonResponse<RestAdminInstitutionModel>> response = new ResponseEntity<JsonResponse<RestAdminInstitutionModel>>(
	            resp, responseHeaders, HttpStatus.CREATED);
	    
	    logger.info("Method : editInstitutionDetails Dao ends" + response);
	    return response;
	}


	
	//modify
	public ResponseEntity<JsonResponse<RestAdminInstitutionModel>> modifyInstitutionDetails(RestAdminInstitutionModel adminModel) {
		logger.info("Method : modifyInstitutionDetails starts");
		RestAdminInstitutionModel pur = new RestAdminInstitutionModel();

		JsonResponse<RestAdminInstitutionModel> resp = new JsonResponse<RestAdminInstitutionModel>();
		try {
			System.out.println("fffffffffffffff"+adminModel);
			if (adminModel.getInstitutionId() == null || adminModel.getInstitutionId() == "") {
				em.createNamedStoredProcedureQuery("institution_add")
				
				.setParameter("p_districtId", adminModel.getDistrictId())
				.setParameter("p_blockId", adminModel.getBlockId())
				.setParameter("p_categoryId", adminModel.getCategoryId())
				.setParameter("p_instName", adminModel.getInstitutionname())
				.setParameter("p_latitude", adminModel.getLatitude()) 
				.setParameter("p_longitude", adminModel.getLongitude())
				.setParameter("p_status", adminModel.getStatus())
				.execute();
			} else {
				em.createNamedStoredProcedureQuery("institution_modify")
				.setParameter("p_instId", adminModel.getInstitutionId())
				.setParameter("p_instName", adminModel.getInstitutionname())
				.setParameter("p_latitude", adminModel.getLatitude())
				.setParameter("p_longitude", adminModel.getLongitude())
				.setParameter("p_categoryId", adminModel.getCategoryId())
				.execute();
			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
				resp.setBody(pur);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<RestAdminInstitutionModel>> response = new ResponseEntity<JsonResponse<RestAdminInstitutionModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : modifyInstitutionDetails ends");
		return response;
	}
	
	
	// edit block list 
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getBlockListEditInstListDao(Integer id) {
	    logger.info("Method : getBlockLists Dao starts, District: " + id);
	    
	    List<DropDownModel> blockList = new ArrayList<>();
	    JsonResponse<List<DropDownModel>> resp = new JsonResponse<>();
	    try {
	        List<Object[]> x = em.createNamedStoredProcedureQuery("get_admin_block_list_dashboard")
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

	    logger.info("Method : getBlockListEdit Dao ends, Response: " + resp);
	    return resp;
	}

}
