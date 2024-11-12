package nirmalya.aatithya.restmodule.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import nirmalya.aatithya.restmodule.api.dao.DoctorDashboardDao;
import nirmalya.aatithya.restmodule.api.dao.PartnerDashboardDao;
import nirmalya.aatithya.restmodule.api.model.CureeazyDoctorConsultationModel;
import nirmalya.aatithya.restmodule.api.model.LabDashboardCountModel;
import nirmalya.aatithya.restmodule.api.model.LabDashboardModel;
import nirmalya.aatithya.restmodule.api.model.LabTestViewReportModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class PartnerDashboardController {
	@Autowired
	PartnerDashboardDao partnerDashboardDao;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(PartnerDashboardController.class);

	// get lab dashboard count
	@GetMapping(value = "/get-lab-dashboard-count")
	public ResponseEntity<JsonResponse<LabDashboardCountModel>> getLabDashboardcount(@RequestParam String labId) {
		logger.info("Method :view getLabDashboardcount starts");
		logger.info("Method :view getLabDashboardcount ends");
		return partnerDashboardDao.getLabDashboardcount(labId);
	}

	// get lab status
	@GetMapping(value = "/get-labstatus-dropdown")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getlabstatus() {
		logger.info("Method : getlabstatus starts");

		logger.info("Method : getlabstatus ends");
		return partnerDashboardDao.getLabStatusDao();
	}

	// get lab dashboard count
	@GetMapping(value = "/get-lab-dashboard-details")
	public ResponseEntity<JsonResponse<List<LabDashboardModel>>> getLabDashboardDetails(@RequestParam String labId,
			@RequestParam String status, @RequestParam String fromDate, @RequestParam String toDate) {
		logger.info("Method :view getLabDashboardcount starts"+ labId  + status + fromDate + toDate );
		logger.info("Method :view getLabDashboardcount ends");
		return partnerDashboardDao.getLabDashboardDetails(labId, status, fromDate, toDate);
	}

	// post update lab status
	@PostMapping(value = "/post-update-lab-status")
	public ResponseEntity<JsonResponse<LabDashboardModel>> postUpdateLabStatus(
			@RequestBody LabDashboardModel labstatusModel) {
		logger.info("Method : postUpdateLabStatus start");
		logger.info("Method : postUpdateLabStatus End");
		return partnerDashboardDao.postUpdateLabStatus(labstatusModel);
	}

	// get get-view-labtest-report
	@GetMapping(value = "/get-view-labtest-report")
	public ResponseEntity<JsonResponse<List<LabTestViewReportModel>>> getviewlabtestreport(@RequestParam String orderId,
			@RequestParam String labId) {
		logger.info("Method : getviewlabtestreport starts" );
		logger.info("Method : getviewlabtestreport ends");
		return partnerDashboardDao.getviewlabtestreport(orderId, labId);
	}
	// get-searchWiseDoctor-list
	@GetMapping(value = "/get-searchWiseLab-list")
	public ResponseEntity<JsonResponse<List<LabDashboardModel>>> getSearchLabDetails(
			@RequestParam String searchdata) {
		logger.info("Method : getSearchLabDetails starts");

		logger.info("Method : getSearchLabDetails ends");
		return partnerDashboardDao.getSearchLabDetails(searchdata);
	}
	// get lab dashboard count
	@GetMapping(value = "/get-notificationCount")
	public ResponseEntity<JsonResponse<DropDownModel>> getNotificationCount(@RequestParam String userId) {
		logger.info("Method :view getNotificationCount starts");
		logger.info("Method :view getNotificationCount ends");
		return partnerDashboardDao.getNotificationCount(userId);
	}
	
	// get lab status
	@GetMapping(value = "/get-notofication-view")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getNotification(@RequestParam String userId) {
		logger.info("Method : getNotification starts");

		logger.info("Method : getNotification ends");
		return partnerDashboardDao.getNotification(userId);
	}
	
	// get Notification seen
	@GetMapping(value = "/get-notofication-seen")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getNotificationSeen(@RequestParam String id,@RequestParam String userid) {
		logger.info("Method : getNotificationSeen starts"+id);

		logger.info("Method : getNotificationSeen ends");
		return partnerDashboardDao.getNotificationSeen(id,userid);
	}
	
	// get Notification seen
		@GetMapping(value = "/get-notofication-remove")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getNotificationRemove(@RequestParam String id) {
			logger.info("Method : getNotificationRemove starts");

			logger.info("Method : getNotificationRemove ends");
			return partnerDashboardDao.getNotificationRemove(id);
		}
}
