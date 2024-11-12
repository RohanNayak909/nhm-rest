package nirmalya.aatithya.restmodule.admin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestChangePasswordDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.util.AESUtil;

@RestController
@RequestMapping(value = "admin/")
public class RestChangePasswordController {
	Logger logger = LoggerFactory.getLogger(RestChangePasswordController.class);

	@Autowired
	RestChangePasswordDao restChangePasswordDao;
	
	@Autowired
	private AESUtil AESUtil;
	
	
	//GET OLD PASSWORD
	@GetMapping(value = "rest-getOldPassword-admin")
	public JsonResponse<DropDownModel> getMonthsYear(@RequestParam String oldpassword) {
		logger.info("Method : restGetOldPassword starts");

		logger.info("Method : restGetOldPassword ends");
		return restChangePasswordDao.restgetOldPasswordDetailsAdmin(oldpassword);
	}
	
	
	//update password
	@RequestMapping(value = "rest-updatePassswordAdmin", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> updateAdminPassword(@RequestParam String id,@RequestParam String confirmpassword) {
		logger.info("Method : updateAdminPassword starts"+id);

		logger.info("Method : updateAdminPassword ends");
		return restChangePasswordDao.updateAdminPassword(id,confirmpassword);
	}
	
	

	@GetMapping(value = "rest-checkStrongPassword")
	public JsonResponse<DropDownModel> restcheckStrongPassword(@RequestParam String strongPassword) {
		logger.info("Method : restcheckStrongPassword starts");

		logger.info("Method : restcheckStrongPassword ends");
		return restChangePasswordDao.restcheckStrongPasswordDao(strongPassword);
	}
	
	
	// validate captcha
	@RequestMapping(value = "rest-validateCaptcha", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<Object>> restValidateCaptcha(@RequestParam String captchaInput,@RequestParam String sessionCaptcha, HttpSession session) {
	    logger.info("Method : restValidateCaptcha starts, input: " + captchaInput);

	    JsonResponse<Object> resp = new JsonResponse<Object>();
	    
	    // AES decryption key (must match the encryption key used on the client-side)
	    String aesKey = "abcdefghijklmnop"; // This should be securely stored and managed

	    // Check and decrypt the 'captchaInput' parameter
	    if (captchaInput == null || captchaInput.isEmpty()) {
	        logger.error("Missing or empty 'captchaInput' parameter");
	        resp.setMessage("Missing or empty 'captchaInput' parameter");
	        return ResponseEntity.badRequest().body(resp); // Return a bad request response
	    }

	    String decryptedCaptchaInput;
	    try {
	        // Decrypt the encrypted CAPTCHA input using AES
	        decryptedCaptchaInput = AESUtil.decrypt(captchaInput); // Pass the AES key to decrypt
	        logger.info("Decrypted Captcha Input: " + decryptedCaptchaInput);
	        
	        // Get the session CAPTCHA
	      //  String sessionCaptcha = (String) session.getAttribute("captcha");
	        logger.info("Session Captcha: " + sessionCaptcha);
	        
	        // Compare the decrypted CAPTCHA input with the session CAPTCHA
	        boolean isValid = sessionCaptcha != null && sessionCaptcha.equals(decryptedCaptchaInput);
	        if (isValid) {
	            logger.info("CAPTCHA validation succeeded.");
	            session.removeAttribute("captcha"); // Remove the CAPTCHA from the session
	            resp.setMessage("Success");
	        } else {
	            logger.info("CAPTCHA validation failed.");
	            resp.setMessage("Invalid CAPTCHA");
	        }
	        
	    } catch (Exception e) {
	        logger.error("Error during CAPTCHA decryption or validation", e);
	        resp.setMessage("Error during CAPTCHA validation");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp); // Return internal server error
	    }

	    // Prepare and return the response entity
	    return ResponseEntity.ok(resp);
	}



	

}
