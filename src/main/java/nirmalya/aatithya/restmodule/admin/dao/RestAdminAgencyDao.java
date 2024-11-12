package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminAgencyModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class RestAdminAgencyDao {
	Logger logger = LoggerFactory.getLogger(RestAdminAgencyDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;

	// view agency
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAdminAgencyModel>> viewAgencyDetails(Integer pageno) {
		logger.info("Method : viewAgencyDetails Dao starts");
		List<RestAdminAgencyModel> admin = new ArrayList<RestAdminAgencyModel>();
		JsonResponse<List<RestAdminAgencyModel>> resp = new JsonResponse<List<RestAdminAgencyModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_agency_view").setParameter("pageno", pageno)
					.getResultList();

			for (Object[] m : x) {

				RestAdminAgencyModel so = new RestAdminAgencyModel(m[0], m[1], m[2], m[3]);
				admin.add(so);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : viewAgencyDetails Dao ends" + resp);
		return resp;
	}

	//edit
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestAdminAgencyModel>> editAgencyDetails(String id) {

		logger.info("Method : editAgencyDetails Dao starts");
		RestAdminAgencyModel restLabTestModel = new RestAdminAgencyModel();
		JsonResponse<RestAdminAgencyModel> resp = new JsonResponse<RestAdminAgencyModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("edit_agency_Details")
					.setParameter("p_agencyid", id)
					.getResultList();
			for (Object[] m : x) {
				
				RestAdminAgencyModel so = new RestAdminAgencyModel(m[0],m[1],m[2]);
				restLabTestModel = so;
			}
			resp.setBody(restLabTestModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestAdminAgencyModel>> response = new ResponseEntity<JsonResponse<RestAdminAgencyModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editAgencyDetails Dao ends");
		return response;

	}
	
	//modify
	public ResponseEntity<JsonResponse<RestAdminAgencyModel>> modifyAgencyDetails(RestAdminAgencyModel adminModel) {
		logger.info("Method : modifyAgencyDetails starts");
		RestAdminAgencyModel pur = new RestAdminAgencyModel();

		JsonResponse<RestAdminAgencyModel> resp = new JsonResponse<RestAdminAgencyModel>();
		try {
			System.out.println("fffffffffffffff"+adminModel);
			if (adminModel.getAgencyId() == null || adminModel.getAgencyId() == "") {
				em.createNamedStoredProcedureQuery("agency_add")
				//.setParameter("p_agencyid", adminModel.getAgencyId())
				.setParameter("p_agencyname", adminModel.getAgencyName())
				.setParameter("p_status", adminModel.getStatus())
				.execute();

			} else {
				em.createNamedStoredProcedureQuery("agency_modify")
				.setParameter("p_agencyid", adminModel.getAgencyId())
				.setParameter("p_agencyname", adminModel.getAgencyName())
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
		ResponseEntity<JsonResponse<RestAdminAgencyModel>> response = new ResponseEntity<JsonResponse<RestAdminAgencyModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editAgencyDetails ends");
		return response;
	}
	
	
	
	// validate agency name
	

	@SuppressWarnings("unchecked")
	public JsonResponse<DropDownModel> restGetValidateAgencyNameDao(String agencyName) {
	    logger.info("Method : restGetValidateAgencyNameDao starts: " + agencyName);
	    JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();

	    // Regular expression to check if username contains only characters
	    String regex = "^[a-zA-Z\\s]+$"; // Regex pattern to allow only letters and spaces
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(agencyName);

	    // Print the district name and whether it matches the pattern
	    System.out.println("Agency Name: " + agencyName);
	    System.out.println("Matches pattern: " + matcher.matches());

	    // Validate username
	    if (agencyName.matches(regex)) {
	        // If the username is valid, return a success message
	        resp.setMessage("Success: Agency is valid.");
	        resp.setCode("100");
	    } else {
	        // Return error message if validation fails
	        resp.setMessage("Error: Agency should contain only characters without any numbers or special characters.");
	        resp.setCode("200");
	    }

	    logger.info("Method : restGetValidateAgencyNameDao ends with response: " + resp);
	    return resp;
	}

}
