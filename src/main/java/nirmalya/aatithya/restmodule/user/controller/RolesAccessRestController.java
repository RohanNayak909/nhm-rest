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
import nirmalya.aatithya.restmodule.user.dao.RolesAccessDao;
import nirmalya.aatithya.restmodule.user.model.RolesAccessModel;

@RestController
@RequestMapping(value = "user/")
public class RolesAccessRestController {

	@Autowired
	RolesAccessDao rolesAccessDao;

	Logger logger = LoggerFactory.getLogger(RolesAccessRestController.class);
	
	@RequestMapping(value = "getRolesList", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RolesAccessModel>>> getRolesList() {
		logger.info("Method : getRolesList starts");
		
		logger.info("Method : getRolesList ends");
		return rolesAccessDao.getRolesList();
	}
	
	@RequestMapping(value = "saveRoleMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> saveRoleMaster(@RequestBody RolesAccessModel id) {
		logger.info("Method : saveRoleMaster starts");
		
		logger.info("Method : saveRoleMaster ends");
		return rolesAccessDao.saveRoleMaster(id);
	}
	
	@RequestMapping(value = "editRoleMaster", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RolesAccessModel>> editRoleMaster(@RequestParam String id) {
		logger.info("Method : editRoleMaster starts");
		
		logger.info("Method : editRoleMaster ends");
		return rolesAccessDao.editRoleMaster(id);
	}
	
	@RequestMapping(value = "deleteRoleMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> deleteRoleMaster(@RequestBody List<DropDownModel> roleList) {
		logger.info("Method : deleteRoleMaster starts");
		
		logger.info("Method : deleteRoleMaster ends");
		return rolesAccessDao.deleteRoleMaster(roleList);
	}
}
