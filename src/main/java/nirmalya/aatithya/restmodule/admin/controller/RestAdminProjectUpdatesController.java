package nirmalya.aatithya.restmodule.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminProjectUpdatesDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminProjectUpdateDetailsModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.controller.RestProjectUpdateController;
import nirmalya.aatithya.restmodule.districtlevel.dao.RestProjectUpdateDao;

@RestController
@RequestMapping(value = "admin/")
public class RestAdminProjectUpdatesController {
	Logger logger = LoggerFactory.getLogger(RestAdminProjectUpdatesController.class);

	@Autowired
	RestAdminProjectUpdatesDao restAdminProjectUpdatesDao;
	
	@GetMapping(value = "getDistrictLists")
	public List<DropDownModel> getDistrictList() {
		logger.info("Method : getDistrictLists starts");

		logger.info("Method : getDistrictLists ends");
		return restAdminProjectUpdatesDao.getDistrictLists();
	}
	 
	@GetMapping(value = "rest-getBlockLists")
	public JsonResponse<List<DropDownModel>> getBlockLists(@RequestParam Integer id) {
		logger.info("Method : getBlockLists starts");

		logger.info("Method : getBlockLists ends");
		return restAdminProjectUpdatesDao.getBlockListsDao(id);
	}
	
	
	@GetMapping(value = "rest-getinstitutionList")
	public JsonResponse<List<DropDownModel>> getInstitutionLists(@RequestParam Integer district,@RequestParam Integer block,
			@RequestParam Integer category,@RequestParam String categoryName) {
		logger.info("Method : getInstitutionLists starts");

		logger.info("Method : getInstitutionLists ends");
		return restAdminProjectUpdatesDao.getInstitutionListsDao(district,block,category,categoryName);
	}
	
	 
	@GetMapping(value = "rest-getCategory")
	public JsonResponse<List<DropDownModel>> getCatagory(@RequestParam Integer id) {
		logger.info("Method : getCatagory starts");

		logger.info("Method : getCatagory ends");
		return restAdminProjectUpdatesDao.getCatagoryDao(id);
	}
	
	
	@GetMapping(value = "getSchemeLists")
	public List<DropDownModel> getSchemeLists() {
		logger.info("Method : getSchemeLists starts");

		logger.info("Method : getSchemeLists ends");
		return restAdminProjectUpdatesDao.getSchemeLists();
	}
	
	//get agency list
	@GetMapping(value = "getAgencyLists")
	public List<DropDownModel> getAgencyLists() {
		logger.info("Method : getAgencyLists starts");
		
		logger.info("Method : getAgencyLists ends");
		return restAdminProjectUpdatesDao.getAgencyLists();
	}
	
	
	@RequestMapping(value = "adminProjectUpdateDetailsAdd", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>> addAdminProjectUpdate(
			@RequestBody RestAdminProjectUpdateDetailsModel restDistrictNhmModel) {
		logger.info("Method : addAdminProjectUpdate starts");

		logger.info("Method : addAdminProjectUpdate ends");
		return restAdminProjectUpdatesDao.addAdminProjectUpdate(restDistrictNhmModel);
	}
	
	
	//check sanction year
	@GetMapping(value = "rest-getSanctionYearAdmin")
	public JsonResponse<DropDownModel> getSanctionYearAdmin(@RequestParam String id,@RequestParam String blockidds,@RequestParam String userId) {
		logger.info("Method : getSanctionYearAdmin starts");

		logger.info("Method : getSanctionYearAdmin ends");
		return restAdminProjectUpdatesDao.restGetSanctionYearAdmin(id,blockidds,userId);
	} 
	
	
	
	//add scheme list
	@RequestMapping(value = "adminSchemeDetailsAdd", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestAdminProjectUpdateDetailsModel>> addSchemeDetails(
			@RequestBody RestAdminProjectUpdateDetailsModel restDistrictNhmModel) {
		logger.info("Method : addSchemeDetails starts");

		logger.info("Method : addSchemeDetails ends");
		return restAdminProjectUpdatesDao.addSchemeDetails(restDistrictNhmModel);
	}
	
	
	//get category list
			@GetMapping(value = "getCategoryLists")
			public List<DropDownModel> getCategoryLists() {
				logger.info("Method : getCategoryLists starts");
				
				logger.info("Method : getCategoryLists ends");
				return restAdminProjectUpdatesDao.getCategoryLists();
			}
			
//			@GetMapping(value = "rest-getAddress")
//			public JsonResponse<DropDownModel> restGetAddress(@RequestParam String institution) {
//				logger.info("Method : restGetAddress starts");
//
//				logger.info("Method : restGetAddress ends");
//				return restAdminProjectUpdatesDao.restGetAddressDao(institution);
//			}
			
			
			@GetMapping(value = "rest-getAddress")
			public JsonResponse<List<DropDownModel>> restGetAddress(@RequestParam String institution) {
				logger.info("Method : restGetAddress starts");

				logger.info("Method : restGetAddress ends");
				return restAdminProjectUpdatesDao.restGetAddressDao(institution);
			}
			
			
			
			
			
	
}
