package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestProjectStatusAbstractReportDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDashboardModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectUpdationReportModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestProjectStatusAbstractReportController {
	Logger logger = LoggerFactory.getLogger(RestProjectStatusAbstractReportController.class);

	@Autowired
	RestProjectStatusAbstractReportDao restProjectStatusAbstractReportDao;

	// project physical abstarction report
	

	@GetMapping(value = "rest-viewProjectPhyAbstractReport")
	public JsonResponse<List<RestAdminDashboardModel>> projectPhyAbstractReportView() {
		logger.info("Method :projectPhyAbstractReportView starts");

		logger.info("Method :projectPhyAbstractReportView endss");
		return restProjectStatusAbstractReportDao.projectPhyAbstractReportViewDao();

	}
}
