package nirmalya.aatithya.restmodule.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.SetAuthorityDao;
import nirmalya.aatithya.restmodule.user.model.RestProcessMasterModel;
import nirmalya.aatithya.restmodule.user.model.UserSetAuthority;

@RestController
@RequestMapping(value = "user/")
public class SetAuthorityUsersRestController {

	@Autowired
	SetAuthorityDao setAuthorityDao;

	Logger logger = LoggerFactory.getLogger(SetAuthorityUsersRestController.class);
	
	@GetMapping(value = "getUserRoleDropDown")
	public List<DropDownModel> getUserRoleDropDown() {
		logger.info("Method : getUserRoleDropDown starts");
		
		logger.info("Method : getUserRoleDropDown ends");
		return setAuthorityDao.getUserRoleDropDown();
	}
	
	@GetMapping(value = "getProcessMasterList")
	public ResponseEntity<JsonResponse<List<RestProcessMasterModel>>> getProcessMasterList() {
		logger.info("Method : getProcessMasterList starts");
		
		logger.info("Method : getProcessMasterList ends");
		return setAuthorityDao.getProcessMasterList();
	}
	
	@GetMapping(value = "getUserAuthorityList")
	public ResponseEntity<JsonResponse<List<UserSetAuthority>>> getUserAuthorityList(@RequestParam String id) {
		logger.info("Method : getUserAuthorityList starts");
		
		logger.info("Method : getUserAuthorityList ends");
		return setAuthorityDao.getUserAuthorityList(id);
	}
	
	@GetMapping(value = "getEmployeeListByRole")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getEmployeeListByRole(@RequestParam String id) {
		logger.info("Method : getEmployeeListByRole starts");
		
		logger.info("Method : getEmployeeListByRole ends");
		return setAuthorityDao.getEmployeeListByRole(id);
	}
	
	@PostMapping(value = "saveProcessMaster")
	public ResponseEntity<JsonResponse<Object>> saveProcessMaster(@RequestBody RestProcessMasterModel id) {
		logger.info("Method : saveProcessMaster starts");
		
		logger.info("Method : saveProcessMaster ends");
		return setAuthorityDao.saveProcessMaster(id);
	}
	
	@PostMapping(value = "saveAuthorityMasterData")
	public ResponseEntity<JsonResponse<Object>> saveAuthorityMasterData(@RequestBody UserSetAuthority id) {
		logger.info("Method : saveAuthorityMasterData starts");
		
		logger.info("Method : saveAuthorityMasterData ends");
		return setAuthorityDao.saveAuthorityMasterData(id);
	}
	
}
