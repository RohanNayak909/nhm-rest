package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminAgencyAbstractModel;
import nirmalya.aatithya.restmodule.admin.dao.RestProjectAgencyAbstractReportDao;
import nirmalya.aatithya.restmodule.admin.dao.RestProjectSchemeAbstractReportDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDashboardModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestProjectAgencyAbstractReportController {
	Logger logger = LoggerFactory.getLogger(RestProjectSchemeAbstractReportController.class);

	@Autowired
	RestProjectAgencyAbstractReportDao restProjectAgencyAbstractReportDao;

	// project agency abstarction report
	

	@GetMapping(value = "rest-viewProjectAgencyAbstractReport")
	public JsonResponse<List<RestAdminAgencyAbstractModel>> projectSchemeAbstractReportView() {
		logger.info("Method :projectAgencyAbstractReportView starts");

		logger.info("Method :projectAgencyAbstractReportView endss");
		return restProjectAgencyAbstractReportDao.projectAgencyAbstractReportViewDao();

	}
}
