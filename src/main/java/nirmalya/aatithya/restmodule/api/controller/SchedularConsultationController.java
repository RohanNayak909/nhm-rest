/*
 * package nirmalya.aatithya.restmodule.api.controller; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.scheduling.annotation.EnableScheduling; import
 * org.springframework.scheduling.annotation.Scheduled; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import java.text.SimpleDateFormat; import java.util.Calendar; import
 * java.util.Date;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
 * 
 * @EnableScheduling
 * 
 * @RestController
 * 
 * @RequestMapping(value = "api") public class SchedularConsultationController {
 * 
 * @Autowired EnvironmentVaribles env;
 * 
 * @Autowired //MedicineReminderAPIDao medicineReminderDao;
 * 
 * Logger logger =
 * LoggerFactory.getLogger(SchedularConsultationController.class);
 * 
 * 
 * @Scheduled(fixedRate = 300000) // @Scheduled(fixedRate = 900000) public void
 * scheduleFixedRateTask() throws Exception { logger.info("Scheduler");
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
 * 
 * 
 * logger.info("After Database"); //logger.info(""+medicineListByUesr); } }
 */