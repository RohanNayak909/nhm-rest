/**
 * Repository for user handling related call
**/
package nirmalya.aatithya.restmodule.user.dao;

import java.net.InetAddress;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import nirmalya.aatithya.restmodule.api.model.ApiUserModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.GenerateUserParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.User;
import nirmalya.aatithya.restmodule.util.SessionManager;

/**
 * @author Nirmalya Labs
 *
 */
@Repository
public class UserLoginDao {

	Logger logger = LoggerFactory.getLogger(UserLoginDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	
	public static String getClientPublicIp()
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://ifconfig.io/ip", String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK)
		{
			if (responseEntity.getBody() != null)
			{
				return responseEntity.getBody().trim();
			}
			else {
				return "Error retrieving client IP address";
			}
		}
		else {
			return "Error retrieving client IP address";
		}
	}

	/**
	 * function to return user by name
	 *
	 */
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<User>> getUserByUsername(String username) {
		logger.info("Method : getUserByUsername Dao starts"+username);
		
		JsonResponse<User> jsonResponse = new JsonResponse<User>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");
		
		List<User> userArray = new ArrayList<User>();
		List<String> userRole = new ArrayList<String>();
		
//		get random number
	    SecureRandom secureRandom = new SecureRandom();
	    StringBuilder stringBuilder = new StringBuilder(32);
	    for (int i = 0; i < 32; i++) {
	        int randomDigit = secureRandom.nextInt(10);
	        stringBuilder.append(randomDigit);
	    }
	    String token = stringBuilder.toString();
	    System.out.println("Random 32-digit number: " + token);
		
	    
	    JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
	    try {
			em.createNamedStoredProcedureQuery("update_login_token")
			.setParameter("p_userId", username)
			.setParameter("p_token", token)
			.execute();
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
	    
	    
	    
		
		
		try {
			System.out.println(username);
			List<Object[]> x = em.createNamedStoredProcedureQuery("getLogInDetails")
					.setParameter("p_username", username).getResultList();
			
			for (Object[] m : x) {
				String role = (String) m[6];
				
				if (role != null && role.length() > 0) {
					String[] roles = role.split(",");
					userRole = Arrays.asList(roles);
				}
				Boolean boolean1 = false;
				if(m[5].toString() != null) { 
					String data = m[5].toString();
					System.out.println("USER DETAILS==== :"+data);
					if(data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}
				
				User user = new User(m[0].toString(), m[1], m[2], m[3], m[4],boolean1,
						userRole, m[7], m[8], m[9], m[10], m[11],m[12],m[13],m[14]);
				userArray.add(user);
				
				System.out.println("#USER======= "+user);
				System.out.println("#userm[12]======= "+m[12]);
				System.out.println("#userm[14]======= "+m[14]);
				
				jsonResponse.setCode("100");				
				jsonResponse.setMessage("Success");
				user.setToken(user.getToken());
				
				
				
			}
			
			if (userArray.size() > 0) {
				jsonResponse.setBody(userArray.get(0));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		ResponseEntity<JsonResponse<User>> response = new ResponseEntity<JsonResponse<User>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : getUserByUsername Dao ends"+response);
		System.out.println(response);
		return response;
		
	}
	
	
	
//	@SuppressWarnings("unchecked")
//	public ResponseEntity<JsonResponse<User>> getUserByUsername(HttpServletRequest request, HttpSession session, String username) {
//	    logger.info("Method: getUserByUsername Dao starts " + username);
//
//	    JsonResponse<User> jsonResponse = new JsonResponse<>();
//	    jsonResponse.setCode("");
//	    jsonResponse.setMessage("");
//
//	    List<User> userArray = new ArrayList<>();
//	    List<String> userRole = new ArrayList<>();
//
//	    // Generate a random 32-digit token
//	    SecureRandom secureRandom = new SecureRandom();
//	    StringBuilder stringBuilder = new StringBuilder(32);
//	    for (int i = 0; i < 32; i++) {
//	        int randomDigit = secureRandom.nextInt(10);
//	        stringBuilder.append(randomDigit);
//	    }
//	    String token = stringBuilder.toString();
//	    System.out.println("Random 32-digit number: " + token);
//
//	    try {
//	        // Update login token in the database
//	        em.createNamedStoredProcedureQuery("update_login_token")
//	                .setParameter("p_userId", username)
//	                .setParameter("p_token", token)
//	                .execute();
//
//	        // Fetch user details
//	        List<Object[]> userDetails = em.createNamedStoredProcedureQuery("getLogInDetails")
//	                .setParameter("p_username", username)
//	                .getResultList();
//
//	        for (Object[] m : userDetails) {
//	            String role = (String) m[6];
//	            if (role != null && !role.isEmpty()) {
//	                userRole = Arrays.asList(role.split(","));
//	            }
//	            boolean boolean1 = m[5] != null && m[5].toString().equals("1");
//	            User user = new User(m[0].toString(), m[1], m[2], m[3], m[4], boolean1,
//	                    userRole, m[7], m[8], m[9], m[10], m[11], m[12], m[13], m[14]);
//	            userArray.add(user);
//	        }
//
//	        if (!userArray.isEmpty()) {
//	            System.out.println("Session login check.");
//
//	            // Invalidate the old session if it exists
//	            HttpSession oldSession = SessionManager.getSessionByUsername(username);
//	            String oldSessionId = oldSession != null ? oldSession.getId() : "No old session";
//	            System.out.println("Old Session ID: " + oldSessionId);
//
//	            if (oldSession != null) {
//	                synchronized (oldSession) {
//	                    try {
//	                        oldSession.invalidate();
//	                        SessionManager.removeSession(username);
//	                    } catch (IllegalStateException e) {
//	                        System.out.println("Session already invalidated: " + oldSessionId);
//	                    }
//	                }
//	            }
//
//	            // Create a new session and set attributes
//	            session = request.getSession(true);
//	            session.setAttribute("LOGIN_TOKEN", token);
//	            session.setAttribute("username", username);
//	            SessionManager.registerSession(username, session);
//
//	            // Print current session ID
//	            String currentSessionId = session.getId();
//	            System.out.println("Current Session ID: " + currentSessionId);
//
//	            // Print session timeout
//	            int sessionTimeout = session.getMaxInactiveInterval();
//	            System.out.println("Session Timeout (seconds): " + sessionTimeout);
//
//	            jsonResponse.setCode("100");
//	            jsonResponse.setMessage("Success");
//	            jsonResponse.setBody(userArray.get(0));
//	        }
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//
//	    ResponseEntity<JsonResponse<User>> response = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
//	    logger.info("Method: getUserByUsername Dao ends");
//	    System.out.println(response);
//	    return response;
//	}




	
	
	
	
	
	

	/**
	 * function to register user
	 *
	 */
	public ResponseEntity<JsonResponse<String>> registerUser(User user) {
		logger.info("Method : registerUser starts");

		JsonResponse<String> jsonResponse = new JsonResponse<String>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		try {
			String value = GenerateUserParameter.getUserParam(user);

			em.createNamedStoredProcedureQuery("userRoutines").setParameter("actionType", "getByName")
					.setParameter("actionValue", value).execute();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				String[] err = serverDao.errorProcedureCall(e);

				jsonResponse.setCode(err[0]);
				jsonResponse.setMessage(err[1]);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		ResponseEntity<JsonResponse<String>> response = new ResponseEntity<JsonResponse<String>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : registerUser ends");

		return response;

	}
	
	@SuppressWarnings("unchecked")
	public User loadUserByName(String username) {
		logger.info("Method : loadUserByUsername starts");

		List<User> userArray = new ArrayList<User>();
		List<String> userRole = new ArrayList<String>();
		
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getLogInDetails")
					.setParameter("p_username", username).getResultList();

			for (Object[] m : x) {
				String role = (String) m[6];

				if (role != null && role.length() > 0) {
					String[] roles = role.split(",");
					userRole = Arrays.asList(roles);
				}
				
				Boolean boolean1 = false;
				if(m[5].toString() != null) { 
					String data = m[5].toString();
					System.out.println(data);
					if(data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}
				User user = new User(m[0].toString(), m[1], m[2], m[3], m[4], null, null, null, null, null, null, boolean1, null, null,
						userRole, m[7], m[8], m[9], m[10], m[11],m[12], m[13],null);
				userArray.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		logger.info("Method : loadUserByUsername ends"+userArray);

		return userArray.get(0);

	}

}
