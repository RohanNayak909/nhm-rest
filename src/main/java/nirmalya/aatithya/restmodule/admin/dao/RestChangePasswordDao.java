package nirmalya.aatithya.restmodule.admin.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class RestChangePasswordDao {
	Logger logger = LoggerFactory.getLogger(RestChangePasswordDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	@Autowired
	PasswordEncoder passEncoder;
	
	//get old password
	
	@SuppressWarnings("unchecked")
	public JsonResponse<DropDownModel> restgetOldPasswordDetailsAdmin(String oldpassword) {

		logger.info("Method : restgetOldPasswordDetailsDao starts");
		DropDownModel blockList = new DropDownModel();
		JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();
		try {
			String encodedOldPassword = passEncoder.encode(oldpassword);
			System.out.println("encode password is"+encodedOldPassword);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_oldpassword_details")
					.setParameter("oldpassword", encodedOldPassword)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				blockList=dropDownModel;
			}

			resp.setBody(blockList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : restgetOldPasswordDetailsDao ends");
		logger.info("!@#$%%$##$#$%$$" + oldpassword);
		return resp;
	}
	
	
	
	//update password
	
	public ResponseEntity<JsonResponse<Object>> updateAdminPassword(String id,String confirmpassword) {
		logger.info("Method : updateAdminpasswordDao starts");

		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
		

		if (validity)
			try {
				String encodednewPassword = passEncoder.encode(confirmpassword);
				em.createNamedStoredProcedureQuery("update_admin_password")
				.setParameter("p_adminid", id)
				.setParameter("p_password", encodednewPassword)
				.getResultList();

			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);

		logger.info("Method : updateAdminpasswordDao ends");
		System.out.println("QWEDFGBDEFGDEFV" + response);
		return response;
	}
	
	
	
	// validate strong password
	


	@SuppressWarnings("unchecked")
	public JsonResponse<DropDownModel> restcheckStrongPasswordDao(String strongPassword) {
	    logger.info("Method : restcheckStrongPasswordDao starts: " + strongPassword);
	    JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();

	    // Initialize error message builder
	    StringBuilder errorMessage = new StringBuilder();

	    // Check for at least 8 characters
	    if (strongPassword.length() < 8) {
	        errorMessage.append("Password must be at least 8 characters long.\n");
	    }

	    // Check for at least one digit
	    if (!strongPassword.matches(".*[0-9].*")) {
	        errorMessage.append("Password must contain at least one digit (0-9).\n");
	    }

	    // Check for at least one lowercase letter
	    if (!strongPassword.matches(".*[a-z].*")) {
	        errorMessage.append("Password must contain at least one lowercase letter (a-z).\n");
	    }

	    // Check for at least one uppercase letter
	    if (!strongPassword.matches(".*[A-Z].*")) {
	        errorMessage.append("Password must contain at least one uppercase letter (A-Z).\n");
	    }

	    // Check for at least one special character
	    if (!strongPassword.matches(".*[@#$%^&+=].*")) {
	        errorMessage.append("Password must contain at least one special character (e.g., @, #, $, etc.).\n");
	    }

	    // Check for whitespace
	    if (strongPassword.matches(".*\\s.*")) {
	        errorMessage.append("Password must not contain any whitespace.\n");
	    }

	    // If errorMessage is empty, the password is strong
	    if (errorMessage.length() == 0) {
	        resp.setMessage("Success: Strong password is valid.");
	        resp.setCode("100");
	    } else {
	        // Set error message and code
	        resp.setMessage("Error: " + errorMessage.toString());
	        resp.setCode("200");
	    }

	    logger.info("Method : restcheckStrongPasswordDao ends with response: " + resp);
	    return resp;
	}

	
}
