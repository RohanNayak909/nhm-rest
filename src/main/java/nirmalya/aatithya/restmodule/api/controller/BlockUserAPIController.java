package nirmalya.aatithya.restmodule.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.BlockUserAPIDao;
import nirmalya.aatithya.restmodule.api.model.APIProjectStatusModel;
import nirmalya.aatithya.restmodule.api.model.ApiMobileVersionModel;
import nirmalya.aatithya.restmodule.api.model.BlockUserAPIModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyAddtoCartModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyLabTestModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.util.DocumentUpload;

@RestController
@RequestMapping(value = "api")
public class BlockUserAPIController {
	Logger logger = LoggerFactory.getLogger(BlockUserAPIController.class);
	
	@Autowired
	EnvironmentVaribles env;

	@Autowired
	DocumentUpload documentUpload;

	@Autowired
	BlockUserAPIDao blockUserAPIDao;
	
	
	//get assigned block by user id
	
//	@GetMapping(value = "/get-assigned-block")
//	public ResponseEntity<JsonResponse<List<BlockUserAPIModel>>> getAssignedBlockByUserId(@RequestParam String userId) {
//		logger.info("Method :view getAssignedBlockByUserId starts");
//		logger.info("Method :view getAssignedBlockByUserId ends");
//		return blockUserAPIDao.getAssignedBlockByUserId(userId);
//	}
	
	
	@GetMapping(value = "/get-assigned-block")
	public ResponseEntity<JsonResponse<List<BlockUserAPIModel>>> getAssignedBlockByUserId(@RequestParam String userId) {
		logger.info("Method : getAssignedBlockByUserId starts");

		logger.info("Method : getAssignedBlockByUserId ends");
		return blockUserAPIDao.getAssignedBlockByUserId(userId);
	}
	
	
	
	//get block dropdown
	@GetMapping(value = "/get-blockdropdown-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getBlockDropdownList() {
		logger.info("Method : getBlockDropdownList starts");

		logger.info("Method : getBlockDropdownList ends");
		return blockUserAPIDao.getBlockDropdownList();
	}
	
	
	
	//get institution dropdown
	@GetMapping(value = "/get-institution-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> institutionList(@RequestParam Integer block) {
		logger.info("Method : institutionList starts");

		logger.info("Method : institutionList ends");
		return blockUserAPIDao.getInstitutionList(block);
	}
	
	//get catagory dropdown
		@GetMapping(value = "/get-catagory-list")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> catagoryList(@RequestParam Integer block) {
			logger.info("Method : institutionList starts");

			logger.info("Method : institutionList ends");
			return blockUserAPIDao.getCatagoryList(block);
		}
		
		
		//get agency details
		@GetMapping(value = "/get-agencydropdown-list")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getAgencyDropdownList() {
			logger.info("Method : getAgencyDropdownList starts");

			logger.info("Method : getAgencyDropdownList ends");
			return blockUserAPIDao.getAgencyDropdownList();
		}
		
		//get physical update
		@GetMapping(value = "/get-physicalStatusdropdown-list")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> physicalUpdatedropdownList(@RequestParam String projectId,@RequestParam String floorNo) {
			logger.info("Method : physicalUpdatedropdownList starts");

			logger.info("Method : physicalUpdatedropdownList ends");
			return blockUserAPIDao.getphysicalUpdatedropdownList(projectId,floorNo);
		}
		
		
		//post project update
//		@PostMapping(value = "/post-project-status-upload")
//		public ResponseEntity<JsonResponse<Object>> postProjectStatusApi(
//				@RequestBody APIProjectStatusModel apiProjectStatusModel) {
//			logger.info("Method : postProjectStatusApi starts");
//
//			logger.info("Method : postProjectStatusApi ends");
//			return blockUserAPIDao.postProjectStatusApi(apiProjectStatusModel);
//		}
		
		
		@RequestMapping(value = "/post-project-status-upload", method = { RequestMethod.POST })
		public ResponseEntity<JsonResponse<Object>> postProjectStatusApi(
				@RequestBody APIProjectStatusModel apiProjectStatusModel) {
			logger.info("Method : postProjectStatusApi starts");

			logger.info("Method : postProjectStatusApi ends");
			return blockUserAPIDao.postProjectStatusApi(apiProjectStatusModel);
		}
		
		
		
		@GetMapping(value = "/get-project-list")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> projectList(@RequestParam Integer block) {
			logger.info("Method : projectList starts");

			logger.info("Method : projectList ends");
			return blockUserAPIDao.getProjectList(block);
		}
		
		//get details of the project
		@GetMapping(value = "/get-project-details-list")
		public ResponseEntity<JsonResponse<List<APIProjectStatusModel>>> projectDetailsList(@RequestParam String uniqueId) {
			logger.info("Method : institutionList starts");

			logger.info("Method : institutionList ends");
			return blockUserAPIDao.getProjectDetailsList(uniqueId);
		}
		
		
		//get financial details of project
				@GetMapping(value = "/get-project-financial-details")
				public ResponseEntity<JsonResponse<List<APIProjectStatusModel>>> projectFinancialDetails(@RequestParam String uniqueId) {
					logger.info("Method : projectFinancialDetails starts");

					logger.info("Method : projectFinancialDetails ends");
					return blockUserAPIDao.getProjectFinancialDetails(uniqueId);
				}
				
				
				
				//post financial report of project
//				@PostMapping(value = "/post-Financial-status-upload")
//				public ResponseEntity<JsonResponse<Object>> FinancialStatusApi(
//						@RequestBody APIProjectStatusModel apiProjectStatusModel) {
//					logger.info("Method : FinancialStatusApi starts");
//
//					logger.info("Method : FinancialStatusApi ends");
//					return blockUserAPIDao.postFinancialStatusApi(apiProjectStatusModel);
//				}
				
				@RequestMapping(value = "/post-Financial-status-upload", method = { RequestMethod.POST })
				public ResponseEntity<JsonResponse<Object>> FinancialStatusApi(
						@RequestBody APIProjectStatusModel apiProjectStatusModel) {
					logger.info("Method : postCreateDonationRequestApi starts");

					logger.info("Method : postCreateDonationRequestApi ends");
					return blockUserAPIDao.postFinancialStatusApi(apiProjectStatusModel);
				}
		
		//get physical status of floor
//				@GetMapping(value = "/get-physicalStatus-list-by-block")
//				public ResponseEntity<JsonResponse<List<DropDownModel>>> physicalStatusList(@RequestParam Integer floorNo) {
//					logger.info("Method : physicalStatusList starts");
//
//					logger.info("Method : physicalStatusList ends");
//					return blockUserAPIDao.getPhysicalStatusList(floorNo);
//				}	
				
				
//				@GetMapping(value = "/get-block-project-details")
//				public JsonResponse<List<APIProjectStatusModel>> getrequestedbloodbanklist(@RequestParam String userId) {
//					logger.info("Method :view getrequestedbloodbanklist starts");
//					logger.info("Method :view getrequestedbloodbanklist ends");
//					return blockUserAPIDao.getBlockProjectDeatails(userId);
//				}
//			
					
				
				@GetMapping(value = "/get-block-project-details-demo")
				public JsonResponse<List<APIProjectStatusModel>> getrequestedbloodbanklist(@RequestParam String userId) {
					logger.info("Method :view getrequestedbloodbanklist starts");
					logger.info("Method :view getrequestedbloodbanklist ends");
					return blockUserAPIDao.getBlockProjectDeatails(userId);
				}

				
				
				
				@GetMapping(value = "/get-block-project-details")
				public JsonResponse<List<APIProjectStatusModel>> getrequestedbloodbanklists(@RequestParam String userId) {
					logger.info("Method :view getrequestedbloodbanklist starts");
					logger.info("Method :view getrequestedbloodbanklist ends");
					return blockUserAPIDao.getBlockProjectDeatailsDemo(userId);
				}
				
				
				
				// get mobile version
				@GetMapping(value = "/get-mobile-version-list")
				public ResponseEntity<JsonResponse<List<ApiMobileVersionModel>>> getMobileVersionList() {
					logger.info("Method : getMobileVersionList starts");

					logger.info("Method : getMobileVersionList ends");
					return blockUserAPIDao.getMobileVersionListDao();
				}
				
}
