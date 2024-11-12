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

import nirmalya.aatithya.restmodule.admin.model.RestPhysicalStatusModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestPhysicalStatusDao {
	Logger logger = LoggerFactory.getLogger(RestPhysicalStatusDao.class);

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
	public JsonResponse<List<RestPhysicalStatusModel>> viewPhysicalDetails(Integer pageno)
 {
		logger.info("Method : viewPhysicalDetails Dao starts");
		System.out.println("RESRTTTTTTTTTTttttttttttt");
		List<RestPhysicalStatusModel> admin = new ArrayList<RestPhysicalStatusModel>();
		JsonResponse<List<RestPhysicalStatusModel>> resp = new JsonResponse<List<RestPhysicalStatusModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("admin_physical_status_view")
					.setParameter("pageno", pageno)
					.getResultList();

			for (Object[] m : x) {
				
				
				RestPhysicalStatusModel so = new RestPhysicalStatusModel(m[0].toString(),m[1],m[2],m[3]);
				admin.add(so);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(admin);
		logger.info("Method : viewPhysicalDetails Dao ends"+resp);
		return resp;
	}
	
	
	//edit
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestPhysicalStatusModel>> editPhysicalStatusDetails(String id) {

		logger.info("Method : editPhysicalStatusDetails Dao starts");
		RestPhysicalStatusModel restLabTestModel = new RestPhysicalStatusModel();
		JsonResponse<RestPhysicalStatusModel> resp = new JsonResponse<RestPhysicalStatusModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("edit_physical_status_Details")
					.setParameter("p_phyid", id)
					.getResultList();
			for (Object[] m : x) {
				
				RestPhysicalStatusModel so = new RestPhysicalStatusModel(m[0],m[1],m[2]);
				restLabTestModel = so;
			}
			resp.setBody(restLabTestModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPhysicalStatusModel>> response = new ResponseEntity<JsonResponse<RestPhysicalStatusModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editPhysicalStatusDetails Dao ends");
		return response;

	}
	
	//modify
	public ResponseEntity<JsonResponse<RestPhysicalStatusModel>> modifyPhysicalStatusDetails(RestPhysicalStatusModel adminModel) {
		logger.info("Method : modifyPhysicalStatusDetails starts");
		RestPhysicalStatusModel pur = new RestPhysicalStatusModel();

		JsonResponse<RestPhysicalStatusModel> resp = new JsonResponse<RestPhysicalStatusModel>();
		try {
			System.out.println("fffffffffffffff"+adminModel);
			if (adminModel.getPhysicalId() == null || adminModel.getPhysicalId() == "") {
				em.createNamedStoredProcedureQuery("physical_status_add")
				//.setParameter("p_phyid", adminModel.getPhysicalId())
				.setParameter("p_phyname", adminModel.getPhysicalName())
				.setParameter("p_status", adminModel.getStatus()).execute();
			} else {
				em.createNamedStoredProcedureQuery("physical_status_modify")
				.setParameter("p_phyid", adminModel.getPhysicalId())
				.setParameter("p_phyname", adminModel.getPhysicalName())
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
		ResponseEntity<JsonResponse<RestPhysicalStatusModel>> response = new ResponseEntity<JsonResponse<RestPhysicalStatusModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : modifyPhysicalStatusDetails ends");
		return response;
	}
	
	
	
	// validate physical status name
	 

			@SuppressWarnings("unchecked")
			public JsonResponse<DropDownModel> restgetValidatePhyStatusNameDao(String physicalName) {
			    logger.info("Method : restgetValidatePhyStatusNameDao starts: " + physicalName);
			    JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();

			    // Regular expression to check if username contains only characters
			    String regex = "^[a-zA-Z\\s]+$"; // Regex pattern to allow only letters and spaces
			    Pattern pattern = Pattern.compile(regex);
			    Matcher matcher = pattern.matcher(physicalName);

			    // Print the district name and whether it matches the pattern
			    System.out.println("Agency Name: " + physicalName);
			    System.out.println("Matches pattern: " + matcher.matches());

			    // Validate username
			    if (physicalName.matches(regex)) {
			        // If the username is valid, return a success message
			        resp.setMessage("Success: Physical Status is valid.");
			        resp.setCode("100");
			    } else {
			        // Return error message if validation fails
			        resp.setMessage("Error: Physical Status contain only characters without any numbers or special characters.");
			        resp.setCode("200");
			    }

			    logger.info("Method : restgetValidatePhyStatusNameDao ends with response: " + resp);
			    return resp;
			}

}
