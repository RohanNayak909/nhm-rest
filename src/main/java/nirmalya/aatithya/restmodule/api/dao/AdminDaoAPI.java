package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import nirmalya.aatithya.restmodule.api.model.APIAdminModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class AdminDaoAPI {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	
	@Autowired
	PasswordEncoder passEncoder;

	Logger logger = LoggerFactory.getLogger(AdminDaoAPI.class);

 

	// Get News & Media Document details

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getBannerDetailsApi() {

		logger.info("Method : getBannerDetailsApi Dao starts");
		
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
 
		List<DropDownModel> IssueNote = new ArrayList<DropDownModel>();
		/*
		 * List<DropDownModel> banner1 = new ArrayList<DropDownModel>();
		 * List<DropDownModel> banner2 = new ArrayList<DropDownModel>();
		 * List<DropDownModel> banner3 = new ArrayList<DropDownModel>();
		 */
		try {

			List<Object[]> y = em.createNamedStoredProcedureQuery("get_banner_details_api").getResultList();

			for (Object[] m : y) {
				String prfileImg = null;
				if (m[3] != null && m[3] != "" && m[3] != " " && !m[3].toString().equals(" ")
						&& !m[3].toString().equals(null) && !m[3].toString().equals("")) {
					System.out.println("IMAGESS"+m[3]);
					
					prfileImg = env.getBaseURL() + "cureeazyrest/document/document/"+ m[3].toString();
					//prfileImg = "";
				} else {
					prfileImg = "";
				}
				DropDownModel subItem = new DropDownModel(m[0], m[1], m[2], prfileImg, m[4]);
				System.out.println("subItem !!!!"+subItem);
				System.out.println("m[2] !!!!"+m[2].toString());
				IssueNote.add(subItem);
//				if (m[2].toString().equals("1")) {
//					System.out.println("m[2] bjdhksb!!!!"+m[2].toString());
//					//banner1.add(subItem);
//					IssueNote.get(0).setBanner1(subItem);
//				}else if(m[2].toString().equals("2")){
//					//banner2.add(subItem);
//					IssueNote.get(0).setBanner2(subItem);
//				}else if(m[2].toString().equals("3")) {
//					//banner3.add(subItem);
//					IssueNote.get(0).setBanner3(subItem);
//				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(IssueNote);
		System.out.println("RESPONSE !!!!"+resp);
		if (resp.getBody().size() > 0) {
			
			resp.setCode("Success");
			resp.setMessage("Data Fetched Successfully");
			
			

		} else {

			resp.setCode("Failed");
			resp.setMessage("Data Not Found");
		}

		logger.info("Method : getBannerDetailsApi Dao ends");
		return resp;

	}

	
	// Get News & Media Document details

		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> getDoctorBannerDetailsApi() {

			logger.info("Method : getDoctorBannerDetailsApi Dao starts");
			
			JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
	 
			List<DropDownModel> IssueNote = new ArrayList<DropDownModel>();
			/*
			 * List<DropDownModel> banner1 = new ArrayList<DropDownModel>();
			 * List<DropDownModel> banner2 = new ArrayList<DropDownModel>();
			 * List<DropDownModel> banner3 = new ArrayList<DropDownModel>();
			 */
			try {

				List<Object[]> y = em.createNamedStoredProcedureQuery("get_banner_doctor_api").getResultList();

				for (Object[] m : y) {
					String prfileImg = null;
					if (m[3] != null && m[3] != "" && m[3] != " " && !m[3].toString().equals(" ")
							&& !m[3].toString().equals(null) && !m[3].toString().equals("")) {
						System.out.println("IMAGESS"+m[3]);
						
						prfileImg = env.getBaseURL() + "cureeazyrest/document/document/"+ m[3].toString();
						//prfileImg = "";
					} else {
						prfileImg = "";
					}
					DropDownModel subItem = new DropDownModel(m[0], m[1], m[2], prfileImg, m[4]);
					System.out.println("subItem !!!!"+subItem);
					System.out.println("m[2] !!!!"+m[2].toString());
					IssueNote.add(subItem);
//					if (m[2].toString().equals("1")) {
//						System.out.println("m[2] bjdhksb!!!!"+m[2].toString());
//						//banner1.add(subItem);
//						IssueNote.get(0).setBanner1(subItem);
//					}else if(m[2].toString().equals("2")){
//						//banner2.add(subItem);
//						IssueNote.get(0).setBanner2(subItem);
//					}else if(m[2].toString().equals("3")) {
//						//banner3.add(subItem);
//						IssueNote.get(0).setBanner3(subItem);
//					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			resp.setBody(IssueNote);
			System.out.println("RESPONSE !!!!"+resp);
			if (resp.getBody().size() > 0) {
				
				resp.setCode("Success");
				resp.setMessage("Data Fetched Successfully");
				
				

			} else {

				resp.setCode("Failed");
				resp.setMessage("Data Not Found");
			}

			logger.info("Method : getDoctorBannerDetailsApi Dao ends");
			return resp;

		}
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changePassword(DropDownModel data) {
		logger.info("Method : changePassword Dao starts"+data);

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getKey() == null || data.getKey() == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Mobile Number required");
			validity = false;
		}

		if (validity) {
			try {
				if (data.getKey() != null && data.getKey() != "") {

					String password = null;
					if (data.getCode() != null && data.getCode() != "") {
						password = passEncoder.encode(data.getCode());
					}

					List<Object[]> x = em.createNamedStoredProcedureQuery("change_password")
							.setParameter("userid", data.getKey()).setParameter("password", password).getResultList();

					if (x.size() > 0) {
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Password updated successfully");

					} else {
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Password not updated");
					}

				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Mobile Number required");
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Password not updated");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : changePassword Dao ends");
		return response;
	}


 
}
