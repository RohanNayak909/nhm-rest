package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminProjectController {
	Logger logger = LoggerFactory.getLogger(RestAdminProjectController.class);

	@Autowired
	RestAdminProjectDao restAdminProjectDao;
	
	
	// View Details
			@GetMapping(value = "rest-viewProjectDetails")
			public JsonResponse<List<RestAdminProjectModel>> viewProjectDetails() {
				logger.info("Method :viewProjectDetails starts");

				logger.info("Method :viewProjectDetails endss");
				return restAdminProjectDao.viewProjectDetails();

			}
}
