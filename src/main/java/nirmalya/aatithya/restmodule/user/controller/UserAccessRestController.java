package nirmalya.aatithya.restmodule.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.UserAccessDao;
import nirmalya.aatithya.restmodule.user.model.ModulesAccessModel;
import nirmalya.aatithya.restmodule.user.model.RolesAccessModel;
import nirmalya.aatithya.restmodule.user.model.UserAccessModel;

@RestController
@RequestMapping(value = "user/")
public class UserAccessRestController {
	
	@Autowired
	UserAccessDao userAccessDao;

	Logger logger = LoggerFactory.getLogger(UserAccessRestController.class);
	
	@RequestMapping(value = "getUsersList", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<UserAccessModel>>> getUsersList() {
		logger.info("Method : getUsersList starts");
		
		logger.info("Method : getUsersList ends");
		return userAccessDao.getUsersList();
	}
	
	@RequestMapping(value = "getUserEmployeeList", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getUserEmployeeList() {
		logger.info("Method : getUserEmployeeList starts");
		
		logger.info("Method : getUserEmployeeList ends");
		return userAccessDao.getUserEmployeeList();
	}
	
	@RequestMapping(value = "getUserEmployeeListByName", method = { RequestMethod.GET }) 
	public ResponseEntity<JsonResponse<List<ModulesAccessModel>>> getUserEmployeeListByName(@RequestParam String id) {
		logger.info("Method : getUserEmployeeListByName starts");
		
		logger.info("Method : getUserEmployeeListByName ends");
		return userAccessDao.getUserEmployeeListByName(id);
	}
	
	@RequestMapping(value = "editUserMaster", method = { RequestMethod.GET }) 
	public ResponseEntity<JsonResponse<UserAccessModel>> editUserMaster(@RequestParam String id) {
		logger.info("Method : editUserMaster starts");
		
		logger.info("Method : editUserMaster ends");
		return userAccessDao.editUserMaster(id);
	}
	
	@RequestMapping(value = "getUserTypeForUser", method = { RequestMethod.GET })
	public List<DropDownModel> getUserTypeForUser() {
		logger.info("Method : getUserTypeForUser starts");
		
		logger.info("Method : getUserTypeForUser ends");
		return userAccessDao.getUserTypeForUser();
	}
	
	@RequestMapping(value = "saveUserMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> saveUserMaster(@RequestBody UserAccessModel id) {
		logger.info("Method : saveUserMaster starts");
		
		logger.info("Method : saveUserMaster ends");
		return userAccessDao.saveUserMaster(id);
	}
	
	@RequestMapping(value = "deleteUserMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> deleteUserMaster(@RequestBody List<DropDownModel> roleList) {
		logger.info("Method : deleteUserMaster starts");
		
		logger.info("Method : deleteUserMaster ends");
		return userAccessDao.deleteUserMaster(roleList);
	}
	@RequestMapping(value = "rest-profileSwap", method = { RequestMethod.GET })
	public  JsonResponse<Object> profileSwap(@RequestParam String id) {
		logger.info("Method : profileSwap starts");
		
		logger.info("Method : profileSwap ends");
		return userAccessDao.profileSwap(id);
	}
}
