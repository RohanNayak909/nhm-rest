package nirmalya.aatithya.restmodule.user.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.dao.CureeazyDoctorConsultationDao;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.UserProfileModel;
import nirmalya.aatithya.restmodule.util.PushNotification;

@Repository
public class UserProfileDao {

	
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;
	PushNotification pushNotification = new PushNotification();
	
	Logger logger = LoggerFactory.getLogger(UserProfileDao.class);
	
	
	//getUserProfile

		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<UserProfileModel>> getUserProfile(String userId) {
			logger.info("Method : getUserProfile Dao starts");

			UserProfileModel cureEasyDoctorAPIModel = new UserProfileModel();
			JsonResponse<UserProfileModel> jsonResponse = new JsonResponse<UserProfileModel>();
			try {
				System.out.println(userId);
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_profile")
						.setParameter("userId", userId)
						.getResultList();
				String DeviceIdKey="";
				for (Object[] m : x) {
					System.out.println(Arrays.toString(m));
					String prfileImg = null;
					if (m[1] != null && m[1] != "" && m[1] != " " && !m[1].toString().equals(" ")
							&& !m[1].toString().equals(null) && !m[1].toString().equals("")) {
						prfileImg = env.getBaseURL() + "cureeazyrest/document/document/" + m[1].toString();
					} else {
						prfileImg = "";
					}
					UserProfileModel dropDownModel = new UserProfileModel(m[0],prfileImg, m[2], m[3], m[4], m[5],null,m[6],m[7],m[8],m[9],m[10]);
					cureEasyDoctorAPIModel = dropDownModel;
					DeviceIdKey=dropDownModel.getDeviceTokenId();
					System.out.println("DeviceIdKey"+dropDownModel);
				}
				
				String dataList="Team Cureez";
				//String DeviceIdKey="enVky_HURdiYfHBklxjVIm:APA91bFFuDdWjhiQorIQ4E6ZCITMDJDKxyhGxsMxdBFkZhO5N6YvEnyx1Y9tprmnJcBA74f27HFIEsH6eMfxZBtIVRvssQmfS1n2nFL5PbIuY1OTHhbr4JD71NeIDhPB_pJOw70cJ9wd";
				/*
				 * try { String messageId =
				 * pushNotification.pushFCMNotifications(DeviceIdKey,dataList);
				 * System.out.println("msgIdddddddddddddd"+messageId); } catch (Exception e) {
				 * e.printStackTrace(); }
				 */
				jsonResponse.setBody(cureEasyDoctorAPIModel);
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");

			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			ResponseEntity<JsonResponse<UserProfileModel>> response = new ResponseEntity<JsonResponse<UserProfileModel>>(
					jsonResponse, HttpStatus.OK);
			System.out.println(response);
			logger.info("Method : getUserProfile Dao ends"+response);
			return response;
		}

}
