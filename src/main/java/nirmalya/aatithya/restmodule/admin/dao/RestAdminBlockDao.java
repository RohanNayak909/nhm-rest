package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminBlockModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDistrictModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestAdminBlockDao {
	Logger logger = LoggerFactory.getLogger(RestAdminBlockDao.class);

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
	public JsonResponse<List<RestAdminBlockModel>> viewBlockDetails(Integer pageno,String district) {
		logger.info("Method : viewBlockDetails Dao starts");
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestAdminBlockModel> admin = new ArrayList<RestAdminBlockModel>();
		JsonResponse<List<RestAdminBlockModel>> resp = new JsonResponse<List<RestAdminBlockModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_block_view")
					.setParameter("pageno", pageno)
					.setParameter("district", district)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestAdminBlockModel so = new RestAdminBlockModel(m[0],m[1],m[2],m[3],m[4]);
				admin.add(so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : viewBlockDetails Dao ends"+resp);
		return resp;
	}
	//edit
			@SuppressWarnings("unchecked")
			public ResponseEntity<JsonResponse<RestAdminBlockModel>> editBlockDetails(String id) {

				logger.info("Method : editBlockDetails Dao starts");
				RestAdminBlockModel restLabTestModel = new RestAdminBlockModel();
				JsonResponse<RestAdminBlockModel> resp = new JsonResponse<RestAdminBlockModel>();
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("edit_block_Details")
							.setParameter("p_blockid", id)
							.getResultList();
					for (Object[] m : x) {
						
						RestAdminBlockModel so = new RestAdminBlockModel(m[0],m[1],m[2],m[3]);
						restLabTestModel = so;
					}
					resp.setBody(restLabTestModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("MyResponseHeader", "MyValue");

				ResponseEntity<JsonResponse<RestAdminBlockModel>> response = new ResponseEntity<JsonResponse<RestAdminBlockModel>>(
						resp, responseHeaders, HttpStatus.CREATED);

				logger.info("Method : editBlockDetails Dao ends"+response);
				return response;

			}
			
			//modify
			public ResponseEntity<JsonResponse<RestAdminBlockModel>> modifyBlockDetails(RestAdminBlockModel adminModel) {
				logger.info("Method : modifyBlockDetails starts");
				RestAdminBlockModel pur = new RestAdminBlockModel();
System.out.println("Block name=== "+adminModel.getBlockName());
				JsonResponse<RestAdminBlockModel> resp = new JsonResponse<RestAdminBlockModel>();
				
				// Validation for district name
			    String blockName = adminModel.getBlockName();
			    String regex = "^[a-zA-Z\\s]+$"; // Regex pattern to allow only letters and spaces
			    Pattern pattern = Pattern.compile(regex);
			    Matcher matcher = pattern.matcher(blockName);

			    // Print the district name and whether it matches the pattern
			    System.out.println("District Name: " + blockName);
			    System.out.println("Matches pattern: " + matcher.matches());

				
			    if (matcher.matches()) {
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
					
					// Set success code and message
		            resp.setCode("200");
		            resp.setMessage("Block details added/modified successfully.");
		            resp.setBody(adminModel);

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
			    }else {
			        // Block name is invalid
			        resp.setCode("400");
			        resp.setMessage("Invalid block name. It should not contain numbers or special characters.");
			        resp.setBody(pur);

			        HttpHeaders responseHeaders = new HttpHeaders();
			        responseHeaders.set("MyResponseHeader", "MyValue");
			        ResponseEntity<JsonResponse<RestAdminBlockModel>> response = new ResponseEntity<>(resp, responseHeaders, HttpStatus.BAD_REQUEST);

			        logger.info("Method : addDistrictDetailsDao ends due to invalid district name" + response);
			        return response;
			    }
				
				
		/*
		 * HttpHeaders responseHeaders = new HttpHeaders();
		 * responseHeaders.set("MyResponseHeader", "MyValue");
		 * ResponseEntity<JsonResponse<RestAdminBlockModel>> response = new
		 * ResponseEntity<JsonResponse<RestAdminBlockModel>>( resp, responseHeaders,
		 * HttpStatus.CREATED);
		 * 
		 * logger.info("Method : modifyBlockDetails ends"); return response;
		 */
				
				
				 HttpHeaders responseHeaders = new HttpHeaders();
				    responseHeaders.set("MyResponseHeader", "MyValue");
				    ResponseEntity<JsonResponse<RestAdminBlockModel>> response = new ResponseEntity<>(resp, responseHeaders, HttpStatus.CREATED);

				    logger.info("Method : addDistrictDetailsDao ends" + response);
				    return response;
				
			}

}
