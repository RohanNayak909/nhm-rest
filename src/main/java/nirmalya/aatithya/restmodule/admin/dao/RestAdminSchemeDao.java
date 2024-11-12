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

import nirmalya.aatithya.restmodule.admin.model.RestAdminDistrictModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminSchemeModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestAdminSchemeDao {
	Logger logger = LoggerFactory.getLogger(RestAdminSchemeDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
	//view scheme details
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminSchemeModel>> restViewSchemeDetails(Integer pageno)
 {
		logger.info("Method : viewSchemeDetailsDao starts");
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestAdminSchemeModel> admin = new ArrayList<RestAdminSchemeModel>();
		JsonResponse<List<RestAdminSchemeModel>> resp = new JsonResponse<List<RestAdminSchemeModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_scheme_view")
					.setParameter("pageno", pageno)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestAdminSchemeModel so = new RestAdminSchemeModel(m[0],m[1],m[2],m[3]);
				admin.add(so);
			//	System.out.println("RESRTTTTTTTTTT#" + so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : viewSchemeDetailsDao ends"+resp);
		return resp;
	}
	//edit
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestAdminSchemeModel>> editSchemeDetails(String id) {

		logger.info("Method : editSchemeDetails Dao starts");
		RestAdminSchemeModel restLabTestModel = new RestAdminSchemeModel();
		JsonResponse<RestAdminSchemeModel> resp = new JsonResponse<RestAdminSchemeModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("edit_scheme_Details")
					.setParameter("p_blockid", id)
					.getResultList();
			for (Object[] m : x) {
				
				RestAdminSchemeModel so = new RestAdminSchemeModel(m[0],m[1],m[2]);
				restLabTestModel = so;
			}
			resp.setBody(restLabTestModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestAdminSchemeModel>> response = new ResponseEntity<JsonResponse<RestAdminSchemeModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editSchemeDetails Dao ends");
		return response;

	}
	
	//modify
	public ResponseEntity<JsonResponse<RestAdminSchemeModel>> modifySchemeDetails(RestAdminSchemeModel adminModel) {
		logger.info("Method : modifySchemeDetails starts");
		RestAdminSchemeModel pur = new RestAdminSchemeModel();

		JsonResponse<RestAdminSchemeModel> resp = new JsonResponse<RestAdminSchemeModel>();
		try {
			System.out.println("fffffffffffffff"+adminModel);
			if (adminModel.getSchemeId() == null || adminModel.getSchemeId() == "") {
				em.createNamedStoredProcedureQuery("scheme_add")
				//.setParameter("p_schemeid", adminModel.getSchemeId())
				.setParameter("p_schemename", adminModel.getSchemeName())
				.setParameter("p_status", adminModel.getStatus()).execute();

			} else {
				em.createNamedStoredProcedureQuery("scheme_modify")
				.setParameter("p_schemeid", adminModel.getSchemeId())
				.setParameter("p_schemename", adminModel.getSchemeName())
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
		ResponseEntity<JsonResponse<RestAdminSchemeModel>> response = new ResponseEntity<JsonResponse<RestAdminSchemeModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : modifySchemeDetails ends");
		return response;
	}
	
	
	
	// validate agency name
	 

		@SuppressWarnings("unchecked")
		public JsonResponse<DropDownModel> restGetValidateSchemeNameDao(String schemeName) {
		    logger.info("Method : restGetValidateAgencyNameDao starts: " + schemeName);
		    JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();

		    // Regular expression to check if username contains only characters
		    String regex = "^[a-zA-Z\\s]+$"; // Regex pattern to allow only letters and spaces
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(schemeName);

		    // Print the district name and whether it matches the pattern
		    System.out.println("Agency Name: " + schemeName);
		    System.out.println("Matches pattern: " + matcher.matches());

		    // Validate username
		    if (schemeName.matches(regex)) {
		        // If the username is valid, return a success message
		        resp.setMessage("Success: Scheme is valid.");
		        resp.setCode("100");
		    } else {
		        // Return error message if validation fails
		        resp.setMessage("Error: Scheme should contain only characters without any numbers or special characters.");
		        resp.setCode("200");
		    }

		    logger.info("Method : restGetValidateAgencyNameDao ends with response: " + resp);
		    return resp;
		}
	
	
}
 