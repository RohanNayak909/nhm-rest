package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminAgencyDao;
import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectStatusUpdateDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminAgencyModel;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminProjectStatusUpdateController {
	Logger logger = LoggerFactory.getLogger(RestAdminProjectStatusUpdateController.class);

	@Autowired
	RestAdminProjectStatusUpdateDao restAdminProjectStatusUpdateDao;
	
	// View Details
				@GetMapping(value = "rest-ProjectStatusUpdate")
				public JsonResponse<List<RestAdminProjectUpdateDetailsModel>> restProjectStatusUpdate(@RequestParam String pageno,
						@RequestParam String district,@RequestParam String block,@RequestParam String institution,@RequestParam String scheme,
						@RequestParam String agency,@RequestParam String sanctionYear) {
					logger.info("Method :restProjectStatusUpdate starts");

					logger.info("Method :restProjectStatusUpdate endss");
					return restAdminProjectStatusUpdateDao.restProjectStatusUpdate(pageno,district,block,institution,
							scheme,agency,sanctionYear);

				}
	
				
				@GetMapping(value = "getDistrictList")
				public List<DropDownModel> getDistrictList() {
					logger.info("Method : getDistrictList starts");

					logger.info("Method : getDistrictList ends");
					return restAdminProjectStatusUpdateDao.getDistrictList();
				}
				
				@GetMapping(value = "rest-getBlockList")
				public JsonResponse<List<DropDownModel>> getBlockList(@RequestParam Integer id) {
					logger.info("Method : getBlockList starts");

					logger.info("Method : getBlockList ends");
					return restAdminProjectStatusUpdateDao.getBlockListDao(id);
				}
				
				
				@GetMapping(value = "rest-getInstitutionLists")
				public JsonResponse<List<DropDownModel>> getInstitutionLists(@RequestParam Integer id) {
					logger.info("Method : getInstitutionLists starts");

					logger.info("Method : getInstitutionLists ends");
					return restAdminProjectStatusUpdateDao.getInstitutionListsDao(id);
				}
				
}
