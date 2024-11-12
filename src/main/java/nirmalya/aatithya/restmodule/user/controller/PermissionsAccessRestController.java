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
import nirmalya.aatithya.restmodule.user.dao.PermissionsAccessDao;
import nirmalya.aatithya.restmodule.user.model.ActivityAvlFunctionModel;
import nirmalya.aatithya.restmodule.user.model.AvailableFunctionModel;
import nirmalya.aatithya.restmodule.user.model.HeaderAccessModel;

@RestController
@RequestMapping(value = "user/")
public class PermissionsAccessRestController {

	@Autowired
	PermissionsAccessDao permissionsAccessDao;

	Logger logger = LoggerFactory.getLogger(PermissionsAccessRestController.class);
	
	@RequestMapping(value = "getPermissionsList", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<HeaderAccessModel>>> getPermissionsList() {
		logger.info("Method : getPermissionsList starts");
		
		logger.info("Method : getPermissionsList ends");
		return permissionsAccessDao.getPermissionsList();
	}
	
	@RequestMapping(value = "editPermissionData", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<AvailableFunctionModel>> editPermissionData(@RequestParam String id, @RequestParam String name) {
		logger.info("Method : editPermissionData starts");
		 
		logger.info("Method : editPermissionData ends");
		return permissionsAccessDao.editPermissionData(id,name);
	}
	
	@RequestMapping(value = "assignRoleAvlFunction", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> assignRoleAvlFunction(@RequestBody AvailableFunctionModel id) {
		logger.info("Method : assignRoleAvlFunction starts");
		
		logger.info("Method : assignRoleAvlFunction ends");
		return permissionsAccessDao.assignRoleAvlFunction(id);
	}
	
	@RequestMapping(value = "getCompChildrenList", method = { RequestMethod.GET })
	public List<HeaderAccessModel> getCompChildrenList(@RequestParam String id) {
		logger.info("Method : getCompChildrenList starts");
		
		logger.info("Method : getCompChildrenList ends");
		return permissionsAccessDao.getCompChildrenList(id);
	}
	
	@RequestMapping(value = "getAvlFuncList", method = { RequestMethod.GET })
	public List<HeaderAccessModel> getAvlFuncList(@RequestParam String id) {
		logger.info("Method : getAvlFuncList starts");
		
		logger.info("Method : getAvlFuncList ends");
		return permissionsAccessDao.getAvlFuncList(id);
	}
	
	@RequestMapping(value = "getPermissionLevelList", method = { RequestMethod.GET })
	public List<DropDownModel> getPermissionLevelList() {
		logger.info("Method : getPermissionLevelList starts");
		
		logger.info("Method : getPermissionLevelList ends");
		return permissionsAccessDao.getPermissionLevelList();
	}
	
	@RequestMapping(value = "getDataFilterList", method = { RequestMethod.GET })
	public List<DropDownModel> getDataFilterList() {
		logger.info("Method : getDataFilterList starts");
		
		logger.info("Method : getDataFilterList ends");
		return permissionsAccessDao.getDataFilterList();
	}
	
	@RequestMapping(value = "getAvlFuncPermissionList", method = { RequestMethod.GET })
	public List<AvailableFunctionModel> getAvlFuncPermissionList(@RequestParam String id) {
		logger.info("Method : getAvlFuncPermissionList starts");
		
		logger.info("Method : getAvlFuncPermissionList ends");
		return permissionsAccessDao.getAvlFuncPermissionList(id);
	}
	
	@RequestMapping(value = "getAvlFunctionByActivityRole", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<List<ActivityAvlFunctionModel>>> getAvlFunctionByActivityRole(@RequestBody List<DropDownModel> data) {
		logger.info("Method : getAvlFunctionByActivityRole starts");
		 
		logger.info("Method : getAvlFunctionByActivityRole ends");
		return permissionsAccessDao.getAvlFunctionByActivityRole(data);
	}
}
