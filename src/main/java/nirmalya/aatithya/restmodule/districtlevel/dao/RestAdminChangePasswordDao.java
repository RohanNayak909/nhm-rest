package nirmalya.aatithya.restmodule.districtlevel.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Repository
public class RestAdminChangePasswordDao {
	Logger logger = LoggerFactory.getLogger(RestAdminChangePasswordDao.class);

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
	
	//update dist password
		public ResponseEntity<JsonResponse<Object>> updateDistPassword(String id,String confirmpassword) {
			logger.info("Method : updatedistrictpasswordDao starts");

			Boolean validity = true;
			JsonResponse<Object> resp = new JsonResponse<Object>();
			resp.setMessage("");
			resp.setCode("");
			

			if (validity)
				try {
					String encodednewPassword = passEncoder.encode(confirmpassword);
					em.createNamedStoredProcedureQuery("update_dist_password")
					.setParameter("p_distid", id)
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

			logger.info("Method : updatedistrictpasswordDao ends");
			System.out.println("QWEDFGBDEFGDEFV" + response);
			return response;
		}
		
		
		
		@SuppressWarnings("unchecked")
		public JsonResponse<DropDownModel> restgetOldPasswordDetails(String oldpassword) {

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
	
	
}
