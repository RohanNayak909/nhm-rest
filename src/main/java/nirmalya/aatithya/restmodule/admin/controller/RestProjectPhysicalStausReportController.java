package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectUpdatesDao;
import nirmalya.aatithya.restmodule.admin.dao.RestProjectPhysicalStausReportDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestProjectPhysicalStausReportController {
	Logger logger = LoggerFactory.getLogger(RestProjectPhysicalStausReportController.class);

	@Autowired
	RestProjectPhysicalStausReportDao restProjectPhysicalStausReportDao;
	
	//get physical status list
	@GetMapping(value = "getPhysicalStatusLists")
	public List<DropDownModel> getPhysicalStatusLists() {
		logger.info("Method : getPhysicalStatusLists starts");
		
		logger.info("Method : getPhysicalStatusLists ends");
		return restProjectPhysicalStausReportDao.getPhysicalStatusListsDao();
	}
	
	
	//get project physical status report 
	
		@GetMapping(value = "restViewProjectPhyStatusLists")
		public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restViewPhyStatusDetails(@RequestParam String pageno,
				@RequestParam String district,@RequestParam String block,@RequestParam String category,@RequestParam String scheme,
				@RequestParam String agency,@RequestParam String sanctionYear,@RequestParam String phyStatus) {
			//logger.info("!@#%$#@!$#@#@@#$"+userId);
			logger.info("Method :restViewPhyStatusDetails starts");

			logger.info("Method :restViewPhyStatusDetails endss");
			return restProjectPhysicalStausReportDao.restViewPhyStatusDetailsDao(pageno,district,block,category,scheme,agency,
					sanctionYear,phyStatus);

		}
	
}
