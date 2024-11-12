package nirmalya.aatithya.restmodule.districtlevel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.controller.RestAdminBlockController;
import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectUpdateDetailsDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminDownloadExcelModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestDistrictProjectUpdateDao;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistrictProjectReportModel;

@RestController
@RequestMapping(value = "districtlevel/")
public class RestDistrictProjectViewController {
	Logger logger = LoggerFactory.getLogger(RestDistrictProjectViewController.class);

	@Autowired
	RestDistrictProjectUpdateDao restDistrictProjectUpdateDao;
	
	
				
				// View Details
				@GetMapping(value = "restViewProjectDetailLists")
				public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restViewProjectDetails(@RequestParam String pageno,
						@RequestParam String district,@RequestParam String block,@RequestParam String category,
						@RequestParam String institution,@RequestParam String scheme,
						@RequestParam String agency,@RequestParam String sanctionYear) {
					//logger.info("!@#%$#@!$#@#@@#$"+userId);
					logger.info("Method :restViewProjectDetails starts");

					logger.info("Method :restViewProjectDetails endss");
					return restDistrictProjectUpdateDao.restViewProjectDetails(pageno,district,block,category,institution,
							scheme,agency,sanctionYear);

				}
				
				
				 //get institution details
				@GetMapping(value = "rest-getinstitutionList")
				public JsonResponse<List<DropDownModel>> getInstitutionLists(@RequestParam Integer district,@RequestParam Integer block) {
					logger.info("Method : getInstitutionLists starts");

					logger.info("Method : getInstitutionLists ends");
					return restDistrictProjectUpdateDao.getInstitutionListsDao(district,block);
				}
				
				
				
				// View Details
				@GetMapping(value = "restviewProjectDetailsForExcel")
				public JsonResponse<List<RestAdminDownloadExcelModel>> restviewProjectDetailsForExcel() {
					//logger.info("!@#%$#@!$#@#@@#$"+userId);
					logger.info("Method :restviewProjectDetailsForExcel starts");

					logger.info("Method :restviewProjectDetailsForExcel endss");
					return restDistrictProjectUpdateDao.restviewProjectDetailsForExcelDownload();

				}	
//				
				
//				@RequestMapping(value = "restviewProjectDetailsForExcel", method = { RequestMethod.GET })
//				public JsonResponse<Object> restviewProjectDetailsForExcel() {
//					logger.info("Method :restviewProjectDetailsForExcel start");
//					logger.info("Method :restviewProjectDetailsForExcel endss");
//					return restDistrictProjectUpdateDao.restviewProjectDetailsForExcelDownload();
//					
//				}
				
}
