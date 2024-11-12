/**
 * Repository for user menu related call
**/
package nirmalya.aatithya.restmodule.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.Menu;
import nirmalya.aatithya.restmodule.user.model.User;
import nirmalya.aatithya.restmodule.user.model.UserRolesAndModuleIdModel;

/**
 * @author Nirmalya Labs
 *
 */
@Repository
public class MenuDao {

	Logger logger = LoggerFactory.getLogger(User.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<Menu>>> getUserMenu(List<String> role) {
		logger.info("Method : getUserMenu starts"+role);

		JsonResponse<List<Menu>> jsonResponse = new JsonResponse<List<Menu>>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		List<Menu> mList = new ArrayList<Menu>();

		try {

//			String value = "";
//
//			for (String s : role) {
//				value = value + "'" + s + "',";
//			}
//
//			if (value.length() > 0) {
//				value = value.substring(0, value.length() - 1);

			List<Object[]> x = em.createNamedStoredProcedureQuery("getUserMenu").setParameter("roles", role.get(0))
					.getResultList();
			System.out.println("x"+x);
			System.out.println("role.get(0)"+role.get(0));
				for (Object[] m : x) {
					System.out.println("m"+m);
					Menu menu = new Menu(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7] ,m[8]);
					System.out.println("ROLEEEE"+menu);
					mList.add(menu);
					
				}

				jsonResponse.setBody(mList);

//			} else {
//				logger.warn("Method : getUserMenu : No role assigned to user.");
//			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		ResponseEntity<JsonResponse<List<Menu>>> response = new ResponseEntity<JsonResponse<List<Menu>>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : getUserMenu ends");

		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<Menu>>> getFunctionList(UserRolesAndModuleIdModel userModel) {
		logger.info("Method : getFunctionList starts");

		JsonResponse<List<Menu>> jsonResponse = new JsonResponse<List<Menu>>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		List<Menu> mList = new ArrayList<Menu>();

		try {

			if (userModel.getUserRoleList() != null) {

				List<Object[]> x = em.createNamedStoredProcedureQuery("getUserFunction")
						.setParameter("roles", ((List<Menu>) userModel.getUserRoleList()).get(0))
						.setParameter("moduleid", userModel.getModuleId()).getResultList();
				for (Object[] m : x) {
					Menu menu = new Menu(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], null);
					mList.add(menu);
				}
				jsonResponse.setBody(mList);

			} else {
				logger.warn("Method : getUserMenu : No role assigned to user.");
			}
			jsonResponse.setBody(mList);

		} catch (Exception e) {
			e.printStackTrace();

		}

		ResponseEntity<JsonResponse<List<Menu>>> response = new ResponseEntity<JsonResponse<List<Menu>>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : getFunctionList ends");

		return response;
	}

}
