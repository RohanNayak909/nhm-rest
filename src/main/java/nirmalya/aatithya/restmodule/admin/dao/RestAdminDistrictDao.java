package nirmalya.aatithya.restmodule.admin.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminDistrictModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Repository
public class RestAdminDistrictDao {
	Logger logger = LoggerFactory.getLogger(RestAdminDistrictDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	//view district list
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminDistrictModel>> viewDistrictDetails(Integer pageno) {
		logger.info("Method : viewDistrictDetails Dao starts");
		System.out.println("RESRTTTTTTTTTTttttttttttt>>>>>>>>>>>>>>>>>"+pageno);
		List<RestAdminDistrictModel> admin = new ArrayList<RestAdminDistrictModel>();
		JsonResponse<List<RestAdminDistrictModel>> resp = new JsonResponse<List<RestAdminDistrictModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_district_view")
					.setParameter("pageno", pageno)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestAdminDistrictModel so = new RestAdminDistrictModel(m[0],m[1],m[2].toString(),m[3].toString());
				admin.add(so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : viewDistrictDetails Dao ends"+resp);
		return resp;
	}
	//edit
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<RestAdminDistrictModel>> editDistrictDetails(String id) {

			logger.info("Method : editDistrictDetails Dao starts");
			RestAdminDistrictModel restLabTestModel = new RestAdminDistrictModel();
			JsonResponse<RestAdminDistrictModel> resp = new JsonResponse<RestAdminDistrictModel>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("edit_district_Details")
						.setParameter("p_distid", id)
						.getResultList();
				for (Object[] m : x) {
					
					RestAdminDistrictModel so = new RestAdminDistrictModel(m[0],m[1],m[2]);
					restLabTestModel = so;
				}
				resp.setBody(restLabTestModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");

			ResponseEntity<JsonResponse<RestAdminDistrictModel>> response = new ResponseEntity<JsonResponse<RestAdminDistrictModel>>(
					resp, responseHeaders, HttpStatus.CREATED);

			logger.info("Method : editDistrictDetails Dao ends");
			return response;

		}
		//modify
		public ResponseEntity<JsonResponse<RestAdminDistrictModel>> addDistrictDetailsDao(RestAdminDistrictModel adminModel) {
		    logger.info("Method : addDistrictDetailsDao starts");
		    RestAdminDistrictModel pur = new RestAdminDistrictModel();

		    System.out.println("addDistrictDetailsDao==== " + adminModel.getDistrictName());

		    JsonResponse<RestAdminDistrictModel> resp = new JsonResponse<RestAdminDistrictModel>();

		    // Validation for district name
		    String districtName = adminModel.getDistrictName();
		    String regex = "^[a-zA-Z\\s]+$"; // Regex pattern to allow only letters and spaces
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(districtName);

		    // Print the district name and whether it matches the pattern
		    System.out.println("District Name: " + districtName);
		    System.out.println("Matches pattern: " + matcher.matches());

		    if (matcher.matches()) {
		        System.out.println("matcher matched.");
		        try {
		            System.out.println("Executing stored procedure for " + adminModel);
		            if (adminModel.getDistrictId() == null || adminModel.getDistrictId().isEmpty()) {
		                em.createNamedStoredProcedureQuery("district_add")
		                    .setParameter("p_name", adminModel.getDistrictName())
		                    .setParameter("p_status", adminModel.getStatus())
		                    .execute();
		            } else {
		                em.createNamedStoredProcedureQuery("district_modify")
		                    .setParameter("p_distid", adminModel.getDistrictId())
		                    .setParameter("p_name", adminModel.getDistrictName())
		                    .setParameter("p_status", adminModel.getStatus())
		                    .execute();
		            }
		            
		            // Set success code and message
		            resp.setCode("200");
		            resp.setMessage("District details added/modified successfully.");
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
		    } else {
		        // District name is invalid
		        resp.setCode("400");
		        resp.setMessage("Invalid district name. It should not contain numbers or special characters.");
		        resp.setBody(pur);

		        HttpHeaders responseHeaders = new HttpHeaders();
		        responseHeaders.set("MyResponseHeader", "MyValue");
		        ResponseEntity<JsonResponse<RestAdminDistrictModel>> response = new ResponseEntity<>(resp, responseHeaders, HttpStatus.BAD_REQUEST);

		        logger.info("Method : addDistrictDetailsDao ends due to invalid district name" + response);
		        return response;
		    }

		    HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.set("MyResponseHeader", "MyValue");
		    ResponseEntity<JsonResponse<RestAdminDistrictModel>> response = new ResponseEntity<>(resp, responseHeaders, HttpStatus.CREATED);

		    logger.info("Method : addDistrictDetailsDao ends" + response);
		    return response;
		}

		
		
		
		// save audit details when login
		public ResponseEntity<JsonResponse<RestAdminUserModel>> saveAuditDetailsDao(RestAdminUserModel adminModel) {
			logger.info("Method : saveAuditDetailsDao starts");
			RestAdminUserModel pur = new RestAdminUserModel();
			
			JsonResponse<RestAdminUserModel> resp = new JsonResponse<RestAdminUserModel>();
			try {
				System.out.println("fffffffffffffff"+adminModel);
				System.out.println("p_event=== "+adminModel.getEvent());
				if (adminModel.getUserId() != null || adminModel.getUserId() != "") {
					em.createNamedStoredProcedureQuery("audit_details_add")
					.setParameter("p_userId", adminModel.getUserId())
					.setParameter("p_browserName", adminModel.getBrowserName())
					.setParameter("p_ipAddress", adminModel.getIpAddress())
					.setParameter("p_event", adminModel.getEvent())
					//.setParameter("p_token", adminModel.getToken())
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
			ResponseEntity<JsonResponse<RestAdminUserModel>> response = new ResponseEntity<JsonResponse<RestAdminUserModel>>(
					resp, responseHeaders, HttpStatus.CREATED);
			
			logger.info("Method : saveAuditDetailsDao ends");
			return response;
		}

		// save audit details when logout
		public ResponseEntity<JsonResponse<RestAdminUserModel>> saveAuditDetailsLogoutDao(RestAdminUserModel adminModel) {
			logger.info("Method : saveAuditDetailsLogoutDao starts");
			RestAdminUserModel pur = new RestAdminUserModel();
			
			JsonResponse<RestAdminUserModel> resp = new JsonResponse<RestAdminUserModel>();
			try {
				System.out.println("fffffffffffffff"+adminModel);
				System.out.println("p_event=== "+adminModel.getEvent());
				if (adminModel.getUserId() != null || adminModel.getUserId() != "") {
					em.createNamedStoredProcedureQuery("audit_add_logout_details")
					.setParameter("p_userId", adminModel.getUserId())
					.setParameter("p_browserName", adminModel.getBrowserName())
					.setParameter("p_ipAddress", adminModel.getIpAddress())
					.setParameter("p_event", adminModel.getEvent())
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
			ResponseEntity<JsonResponse<RestAdminUserModel>> response = new ResponseEntity<JsonResponse<RestAdminUserModel>>(
					resp, responseHeaders, HttpStatus.CREATED);
			
			logger.info("Method : saveAuditDetailsLogoutDao ends");
			return response;
		}
		
}
