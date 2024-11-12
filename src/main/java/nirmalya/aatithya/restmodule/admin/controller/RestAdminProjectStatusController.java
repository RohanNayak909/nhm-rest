package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectStatusDao;
import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectUpdateDetailsDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminProjectStatusController {
	Logger logger = LoggerFactory.getLogger(RestAdminProjectStatusController.class);

	@Autowired
	RestAdminProjectStatusDao restAdminProjectStatusDao;
	
	
	@GetMapping(value = "rest-projectStatus")
	public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restProjectStatus(@RequestParam String userId) {
		logger.info("!@#%$#@!$#@#@@#$"+userId);
		logger.info("Method :restProjectStatus starts");

		logger.info("Method :restProjectStatus endss");
		return restAdminProjectStatusDao.restProjectStatus(userId);

	}
}
