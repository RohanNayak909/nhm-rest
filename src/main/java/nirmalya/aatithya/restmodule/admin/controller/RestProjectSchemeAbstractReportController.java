package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestProjectSchemeAbstractReportDao;
import nirmalya.aatithya.restmodule.admin.dao.RestProjectStatusAbstractReportDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDashboardModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestProjectSchemeAbstractReportController {
	Logger logger = LoggerFactory.getLogger(RestProjectSchemeAbstractReportController.class);

	@Autowired
	RestProjectSchemeAbstractReportDao restProjectSchemeAbstractReportDao;

	// project physical abstarction report
	

	@GetMapping(value = "rest-viewProjectSchemeAbstractReport")
	public JsonResponse<List<RestAdminDashboardModel>> projectSchemeAbstractReportView() {
		logger.info("Method :projectSchemeAbstractReportView starts");

		logger.info("Method :projectSchemeAbstractReportView endss");
		return restProjectSchemeAbstractReportDao.projectSchemeAbstractReportViewDao();

	}
}
