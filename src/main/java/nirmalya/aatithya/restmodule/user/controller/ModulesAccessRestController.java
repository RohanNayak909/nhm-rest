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
import nirmalya.aatithya.restmodule.user.dao.ModulesAccessDao;
import nirmalya.aatithya.restmodule.user.model.ModulesAccessModel;

@RestController
@RequestMapping(value = "user/")
public class ModulesAccessRestController {

	@Autowired
	ModulesAccessDao modulesAccessDao;

	Logger logger = LoggerFactory.getLogger(ModulesAccessRestController.class);
	
	@RequestMapping(value = "getModuleListForAccess", method = { RequestMethod.GET })
	public List<ModulesAccessModel> getModuleListForAccess() {
		logger.info("Method : getModuleListForAccess starts");
		
		logger.info("Method : getModuleListForAccess ends");
		return modulesAccessDao.getModuleListForAccess();
	}
	
	@RequestMapping(value = "getComponentListForAccess", method = { RequestMethod.GET })
	public List<ModulesAccessModel> getComponentListForAccess(@RequestParam String id) {
		logger.info("Method : getComponentListForAccess starts");
		
		logger.info("Method : getComponentListForAccess ends");
		return modulesAccessDao.getComponentListForAccess(id);
	}
	
	@RequestMapping(value = "getSubComponentListForAccess", method = { RequestMethod.GET })
	public List<ModulesAccessModel> getSubComponentListForAccess(@RequestParam String id,@RequestParam String comp) {
		logger.info("Method : getSubComponentListForAccess starts");
		
		logger.info("Method : getSubComponentListForAccess ends");
		return modulesAccessDao.getSubComponentListForAccess(id,comp);
	}
	
	@RequestMapping(value = "updateActivity", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ModulesAccessModel>> updateActivity(@RequestParam String id, @RequestParam String status, @RequestParam String modifiedBy) {
		logger.info("Method : updateActivity starts");
		
		logger.info("Method : updateActivity ends");
		return modulesAccessDao.updateActivity(id,status,modifiedBy);
	}
	
	@RequestMapping(value = "updateFunction", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ModulesAccessModel>> updateFunction(@RequestParam String id, @RequestParam String status, @RequestParam String modifiedBy) {
		logger.info("Method : updateFunction starts");
		
		logger.info("Method : updateFunction ends");
		return modulesAccessDao.updateFunction(id,status,modifiedBy);
	}
	
	@RequestMapping(value = "updateModule", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ModulesAccessModel>> updateModule(@RequestParam String id, @RequestParam String status, @RequestParam String modifiedBy) {
		logger.info("Method : updateModule starts");
		
		logger.info("Method : updateModule ends");
		return modulesAccessDao.updateModule(id,status,modifiedBy);
	}
	
	@RequestMapping(value = "updateData", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ModulesAccessModel>> updateData(@RequestBody ModulesAccessModel id) {
		logger.info("Method : updateData starts");
		
		logger.info("Method : updateData ends");
		return modulesAccessDao.updateData(id);
	}
	
	@RequestMapping(value = "getAvailableFunctionLists", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ModulesAccessModel>>> getAvailableFunctionLists() {
		logger.info("Method : getAvailableFunctionLists starts");
		
		logger.info("Method : getAvailableFunctionLists ends");
		return modulesAccessDao.getAvailableFunctionLists();
	}
	
	@RequestMapping(value = "editViewSliderDetails", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ModulesAccessModel>> editViewSliderDetails(@RequestParam String id,@RequestParam String name) {
		logger.info("Method : editViewSliderDetails starts");
		
		logger.info("Method : editViewSliderDetails ends");
		return modulesAccessDao.editViewSliderDetails(id,name);
	}
}
