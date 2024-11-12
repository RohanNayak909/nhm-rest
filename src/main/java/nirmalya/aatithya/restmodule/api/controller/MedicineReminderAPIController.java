package nirmalya.aatithya.restmodule.api.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.MedicineReminderAPIDao;
import nirmalya.aatithya.restmodule.api.model.APIMedicineReminder;
import nirmalya.aatithya.restmodule.api.model.ApiTreatmentTracker;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.util.FCMNotification;
import nirmalya.aatithya.restmodule.util.StringUtil;

@EnableScheduling
@RestController
@RequestMapping(value = "api")
public class MedicineReminderAPIController {

	@Autowired
	EnvironmentVaribles env;
	
	@Autowired
	MedicineReminderAPIDao medicineReminderDao;

	Logger logger = LoggerFactory.getLogger(MedicineReminderAPIController.class);
	
	@PostMapping(value = "/post-medicine-reminder-api")
	public ResponseEntity<JsonResponse<Object>> postMedicineReminderApi(@RequestBody APIMedicineReminder data) {
		logger.info("Method : postMedicineReminderApi starts");

		logger.info("Method : postMedicineReminderApi ends");
		return medicineReminderDao.postMedicineReminderApi(data);
	}
	
	
	/*
	 * @Scheduled(fixedRate = 300000) public void scheduleFixedRateTask() throws
	 * Exception { logger.info("Scheduler");
	 * 
	 * SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Calendar
	 * cal = Calendar.getInstance(); long timeInSecs = cal.getTimeInMillis(); Date
	 * dateBefore15min = new Date(timeInSecs - (15 * 60 * 1000)); Date dateTime =
	 * new Date(timeInSecs); Date dateAfter15min = new Date(timeInSecs + (15 * 60 *
	 * 1000));
	 * 
	 * String before15Min = dtf.format(dateBefore15min); String currentTime =
	 * dtf.format(dateTime); String after15Min = dtf.format(dateAfter15min);
	 * 
	 * logger.info("Before 15 Min == " + before15Min); logger.info("Current == " +
	 * currentTime); logger.info("After 15 Min == " + after15Min);
	 * 
	 * List<APIMedicineReminder> medicineListByUesr =
	 * medicineReminderDao.getMedicineReminderList(before15Min, after15Min);
	 * 
	 * if (medicineListByUesr.size() > 0) { for (APIMedicineReminder m :
	 * medicineListByUesr) { System.out.println(m); int code =
	 * FCMNotification.pushFCMNotification(m.getToken(), m); if (code == 1) { if
	 * (!StringUtil.isNull(m.getId())) {
	 * medicineReminderDao.updateMedicineReminderIdToNotified(m.getId()); } } } }
	 * 
	 * logger.info("After Database"); logger.info("" + medicineListByUesr); }
	 */
	 
	
	@GetMapping(value= "/view-medicine-details-byid")
	public ResponseEntity<JsonResponse<List<APIMedicineReminder>>> viewMedicineReminderList(@RequestParam String userid,@RequestParam String date) {
		logger.info("Method : viewMedicineReminderList starts");
		
		logger.info("Method : viewMedicineReminderList ends");
		return medicineReminderDao.viewMedicineReminderList(userid,date);
	}
	
	//post method for treatment track
	@PostMapping(value = "/post-treatment-tracker")
	public ResponseEntity<JsonResponse<Object>> postTreatMentTrackerApi(@RequestBody ApiTreatmentTracker data){
		logger.info("method : postTreatMentTrackerApi strats");
		
		logger.info("method : postTreatMentTrackerApi ends");
		return medicineReminderDao.postTreatMentTrackerApi(data); 
	}
	
	@GetMapping(value = "/delete-medicine-reminder-details")
	public ResponseEntity<Object> deleteMedicineReminderById(@RequestParam String reminderId) {
		logger.info("Method : deleteMedicineReminderById starts");

		logger.info("Method : deleteMedicineReminderById ends");
		return medicineReminderDao.deleteMedicineReminderById(reminderId);
	}
	
	
	@Scheduled(fixedRate = 300000)
//	@Scheduled(fixedRate = 900000)
	public void scheduleFixedRateTask() throws Exception {
		logger.info("Scheduler");

		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dtf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal = Calendar.getInstance();
		long timeInSecs = cal.getTimeInMillis();
		Date dateBefore30min = new Date(timeInSecs - (30 * 60 * 1000));
		Date dateTime = new Date(timeInSecs);
		Date dateBefore5min = new Date(timeInSecs - (5 * 60 * 1000));
		
		String before30Min = dtf.format(dateBefore30min);
		String currentTime = dtf.format(dateTime);
		String before5Min = dtf.format(dateBefore5min);
		
		String currentDate = dtf1.format(new Date());
		logger.info("currentDateTime == " + currentDate);
		logger.info("Before 30 Min == " + before30Min);
		logger.info("Current == " + currentTime);
		logger.info("Before 5 Min == " + before5Min);

//		List<DropDownModel> slotListByUser = medicineReminderDao.getTimeSlotList(currentDate);
//		
//		if (slotListByUser.size() > 0) {
//			for (DropDownModel m : slotListByUser) {
//				System.out.println("################"+m);
//				
//			}
//		}
		 
		
		logger.info("After Database");
		//logger.info(""+medicineListByUesr);
	}
}
