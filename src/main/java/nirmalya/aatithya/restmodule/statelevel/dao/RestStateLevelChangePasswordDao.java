package nirmalya.aatithya.restmodule.statelevel.dao;

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

@Repository
public class RestStateLevelChangePasswordDao {
	Logger logger = LoggerFactory.getLogger(RestStateLevelChangePasswordDao.class);

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
	
	
	//update password
	
		public ResponseEntity<JsonResponse<Object>> updateStatePassword(String id,String confirmpassword) {
			logger.info("Method : updateStatePasswordDao starts");

			Boolean validity = true;
			JsonResponse<Object> resp = new JsonResponse<Object>();
			resp.setMessage("");
			resp.setCode("");
			

			if (validity)
				try {
					String encodednewPassword = passEncoder.encode(confirmpassword);
					em.createNamedStoredProcedureQuery("update_state_admin_password")
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

			logger.info("Method : updateStatePasswordDao ends");
			System.out.println("QWEDFGBDEFGDEFV" + response);
			return response;
		}
		
		
		
		
		//get old password
		
		@SuppressWarnings("unchecked")
		public JsonResponse<DropDownModel> restgetOldPasswordDetailsState(String oldpassword) {

			logger.info("Method : restgetOldPasswordDetailsDao starts");
			DropDownModel blockList = new DropDownModel();
			JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();
			try {
				String encodedOldPassword = passEncoder.encode(oldpassword);
				System.out.println("encode password is"+encodedOldPassword);
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_oldpassword_details_state")
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
}
