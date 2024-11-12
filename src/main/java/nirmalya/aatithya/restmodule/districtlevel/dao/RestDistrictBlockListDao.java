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

import nirmalya.aatithya.restmodule.admin.dao.RestAdminBlockDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminBlockModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistBlockModel;

@Repository
public class RestDistrictBlockListDao {
	Logger logger = LoggerFactory.getLogger(RestDistrictBlockListDao.class);

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
	public JsonResponse<List<RestDistBlockModel>> viewBlockDetailsDist(Integer pageno,String district) {
		logger.info("Method : viewBlockDetailsDistDao starts");
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestDistBlockModel> admin = new ArrayList<RestDistBlockModel>();
		JsonResponse<List<RestDistBlockModel>> resp = new JsonResponse<List<RestDistBlockModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("dist_block_view")
					.setParameter("pageno", pageno)
					.setParameter("district", district)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestDistBlockModel so = new RestDistBlockModel(m[0],m[1],m[2],m[3],m[4]);
				admin.add(so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method :viewBlockDetailsDistDao ends"+resp);
		return resp;
	}
	//edit
			@SuppressWarnings("unchecked")
			public ResponseEntity<JsonResponse<RestDistBlockModel>> editBlockDetailsDist(String id) {

				logger.info("Method : editBlockDetails Dao starts");
				RestDistBlockModel restDistBlockModel = new RestDistBlockModel();
				JsonResponse<RestDistBlockModel> resp = new JsonResponse<RestDistBlockModel>();
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("edit_block_Details_dist")
							.setParameter("p_blockid", id)
							.getResultList();
					for (Object[] m : x) {
						
						RestDistBlockModel so = new RestDistBlockModel(m[0],m[1],m[2]);
						restDistBlockModel = so;
					}
					resp.setBody(restDistBlockModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("MyResponseHeader", "MyValue");

				ResponseEntity<JsonResponse<RestDistBlockModel>> response = new ResponseEntity<JsonResponse<RestDistBlockModel>>(
						resp, responseHeaders, HttpStatus.CREATED);

				logger.info("Method : editBlockDetails Dao ends"+response);
				return response;

			}
			
			//modify
			public ResponseEntity<JsonResponse<RestAdminBlockModel>> modifyBlockDetails(RestAdminBlockModel adminModel) {
				logger.info("Method : modifyBlockDetails starts");
				RestAdminBlockModel pur = new RestAdminBlockModel();

				JsonResponse<RestAdminBlockModel> resp = new JsonResponse<RestAdminBlockModel>();
				try {
					System.out.println("fffffffffffffff"+adminModel);
					System.out.println("fffffffffffffff"+adminModel.getDistrictId());
					if (adminModel.getBlockId() == null || adminModel.getBlockId() == "") {
						em.createNamedStoredProcedureQuery("block_add")
						.setParameter("p_dist", adminModel.getDistrictId())
						.setParameter("p_blockname", adminModel.getBlockName())
						.setParameter("p_status", adminModel.getStatus()).execute();

					} else {
						em.createNamedStoredProcedureQuery("block_modify")
						.setParameter("p_blockid", adminModel.getBlockId())
						//.setParameter("p_dist", adminModel.getDistrictId())
						.setParameter("p_blockname", adminModel.getBlockName())
						.setParameter("p_status", adminModel.getStatus()).execute();
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
				ResponseEntity<JsonResponse<RestAdminBlockModel>> response = new ResponseEntity<JsonResponse<RestAdminBlockModel>>(
						resp, responseHeaders, HttpStatus.CREATED);

				logger.info("Method : modifyBlockDetails ends");
				return response;
			}
	
}
