package nirmalya.aatithya.restmodule.api.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.APIMedicineReminder;
import nirmalya.aatithya.restmodule.api.model.ApiTreatmentTracker;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class MedicineReminderAPIDao {

	@Autowired
	EntityManager em;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(MedicineReminderAPIDao.class);

	// Add Medicine Reminder Details
	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> postMedicineReminderApi(APIMedicineReminder data) {
		logger.info("Method : postMedicineReminderApi Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		String startDate = null;
		Boolean validation = true;

		if (StringUtil.isNull(data.getUserId())) {
			validation = false;
			jsonResponse.setMessage("User id required");
		} else if (StringUtil.isNull(data.getMedType())) {
			validation = false;
			jsonResponse.setMessage("Medicine type required");
		} else if (StringUtil.isNull(data.getMedName())) {
			validation = false;
			jsonResponse.setMessage("Medicine name required");
		} else if (StringUtil.isNull(data.getMedDosage())) {
			validation = false;
			jsonResponse.setMessage("Dosage required");
		} else if (StringUtil.isNull(data.getDosagePerDay())) {
			validation = false;
			jsonResponse.setMessage("Dosage per day required");
		} else if (StringUtil.isNull(data.getStartTime())) {
			validation = false;
			jsonResponse.setMessage("Start time required");
		} else if (StringUtil.isNull(data.getEndTime())) {
			validation = false;
			jsonResponse.setMessage("End Time required");
		} else if (StringUtil.isNull(data.getFrequency())) {
			validation = false;
			jsonResponse.setMessage("Frequency required");
		} else if (StringUtil.isNull(data.getStartDate())) {
			validation = false;
			jsonResponse.setMessage("Start Date required");
		} else if (StringUtil.isNull(data.getTotalDays())) {
			validation = false;
			jsonResponse.setMessage("Total Days required");
		} else if (StringUtil.isNull(data.getInstructions())) {
			validation = false;
			jsonResponse.setMessage("Instruction required");
		}

		if (!StringUtil.isNull(data.getStartDate())) {
			startDate = DateFormatter.getStringDate(data.getStartDate());
		}

		int dosagePerDay = Integer.parseInt(data.getDosagePerDay());
		int totalDays = Integer.parseInt(data.getTotalDays());

		int totalRow = dosagePerDay * totalDays;

		String medReminderDetails = null;

		if (validation) {

			for (int i = 0; i < totalDays; i++) {
				String curDate = null;
				try {
					// Increment of days according to total days
					Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(data.getStartDate());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					c.setTime(date1);
					
					if(data.getFrequency().equals("Daily")) {
						c.add(Calendar.DATE, i); // Adding 1 day
					} else if(data.getFrequency().equals("Weekly")) {
						c.add(Calendar.WEEK_OF_YEAR, i); // Add 1 week
					} else if(data.getFrequency().equals("Monthly")) {
						c.add(Calendar.MONTH, i); // Add 1 month
					}
					
					curDate = sdf.format(c.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}

				for (int j = 0; j < dosagePerDay; j++) {

					logger.info("Date====" + curDate + " Dose Time = " + data.getDoseTime().get(j));

					medReminderDetails = "('" + data.getUserId() + "','" + data.getMedName() + "','" + data.getMedType()
							+ "','" + data.getMedDosage() + "','" + data.getDosagePerDay() + "','" + data.getStartTime()
							+ "','" + data.getEndTime() + "','" + data.getFrequency() + "','" + startDate + "','"
							+ data.getTotalDays() + "','" + data.getInstructions() + "','" + data.getUserId() + "','"
							+ curDate + "','" + data.getDoseTime().get(j) + "')";

					logger.info("medreminderdetails====" + medReminderDetails);
					
					String med_reminder_msg = ApiResponseMessage.MEDICINE_REMINDER;
					med_reminder_msg = med_reminder_msg.replace("{MED}", "\""+data.getMedName()+"\"");

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("post_medicine_reminder_api")
								.setParameter("medreminderdetails", medReminderDetails).getResultList();

						Util.setJsonResponse(jsonResponse, null, ResponseStatus.success,
								med_reminder_msg);

					} catch (Exception e) {
						logger.error("Error " + e.getMessage());
						Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
								ApiResponseMessage.UNKNOWN_EXCEPTION);
					}
				}
			}
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postMedicineReminderApi Dao ends");
		return response;
	}
	@SuppressWarnings("unchecked")
	public void updateMedicineReminderIdToNotified(String id) {
		logger.info("Method : updateMedicineReminderIdToNotified Dao starts");
		
		try {
			
			@SuppressWarnings("unused")
			List<Object[]> x = em.createNamedStoredProcedureQuery("update_medicine_reminder_id_api")
					.setParameter("medicineReminderId", id)
					.getResultList();
			
		} catch (Exception e) {
			logger.error("Error : "+e.getMessage());
		}
		
		logger.info("Method : updateMedicineReminderIdToNotified Dao starts");
	}
	
	/*
	 * @SuppressWarnings("unchecked") public List<APIMedicineReminder>
	 * getMedicineReminderList(String before15Min, String after15Min) {
	 * logger.info("Method : getMedicineReminderList Dao starts");
	 * 
	 * logger.info(before15Min+" "+after15Min);
	 * 
	 * List<APIMedicineReminder> medicineReminderList = new
	 * ArrayList<APIMedicineReminder>(); try {
	 * 
	 * List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("get_medicine_reminder_list_api")
	 * .setParameter("before15Min", before15Min) .setParameter("after15Min",
	 * after15Min) .getResultList(); for (Object[] m : x) { APIMedicineReminder
	 * dropDownModel = new APIMedicineReminder(m[0], m[1], m[2], m[3], m[4], m[5],
	 * m[6], m[7],m[8],m[9], m[10]); medicineReminderList.add(dropDownModel); }
	 * 
	 * } catch (Exception e) { logger.error("Error : "+e.getMessage()); }
	 * 
	 * logger.info("Method : getMedicineReminderList Dao ends"); return
	 * medicineReminderList; }
	 */
	
	//Get details for medicine reminder by id
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicineReminder>>> viewMedicineReminderList(String userid, String remdate) {
		logger.info("Method : viewMedicineReminderList Dao starts");

		List<APIMedicineReminder> reminderList = new ArrayList<APIMedicineReminder>();
		JsonResponse<List<APIMedicineReminder>> jsonResponse = new JsonResponse<List<APIMedicineReminder>>();
		
		String pdate = null;
		if (remdate != null && remdate != "") {
			pdate = DateFormatter.getStringDate(remdate);
		}

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_medicine_reminder_list_api")
					.setParameter("userid", userid).setParameter("pdate", pdate).getResultList();
			for (Object[] m : x) {

				APIMedicineReminder dropDownModel = new APIMedicineReminder(m[0], m[1], m[2],m[3], m[4], m[5],m[6], m[7],m[8],m[9],m[10],null);
				reminderList.add(dropDownModel);
			}

			if (reminderList.size() > 0) {
				Util.setJsonResponse(jsonResponse, reminderList, ResponseStatus.success, ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, reminderList, ResponseStatus.failed,ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIMedicineReminder>>> response = new ResponseEntity<JsonResponse<List<APIMedicineReminder>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewMedicineReminderList Dao ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> postTreatMentTrackerApi(ApiTreatmentTracker data) {
		// TODO Auto-generated method stub
		logger.info("Method : postTreatMentTrackerApi Dao starts");
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();


		if (!StringUtil.isNull(data)) {
			//logger.debug("date"+data.getBookedDate());
			String date = null;
			date = DateFormatter.getStringDate(data.getDosageDate());
			String [] yesid = data.getYesId().split(",");
			String [] noid = data.getNoId().split(",");
			String yes = "yes";
			String no = "no";
			String s = "";
			if(yesid.length >= 1 ) {
				if(yesid[0] != "" ) {
					for(int i = 0; i< yesid.length; i++) {
						s = s+"('"+data.getUserId()+"',"+yesid[i]+",'"+yes+"','"+date+"','"+data.getDosageTime()+"'),";
					} 
				}
			}
			if(noid.length  >= 1 ) {
				if(noid[0] != "" ) {
					for(int i = 0; i< noid.length; i++) {
						s = s+"('"+data.getUserId()+"',"+noid[i]+",'"+no+"','"+date+"','"+data.getDosageTime()+"'),";
					} 
				}
			}
			 
			s = s.substring(0, s.length() - 1);
			try {
				em.createNamedStoredProcedureQuery("post_medicine_treatment_tracker_api").setParameter("p_vlaues", s).execute();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.success, ApiResponseMessage.SAVED_SUCCESSFULLY);
			}catch (Exception e) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
			}
			
		}else {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, "Minimum data required");
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : postTreatMentTrackerApi Dao Ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> deleteMedicineReminderById(String reminderId) {
		logger.info("Method : deleteMedicineReminderById Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("delete_medicine_reminder_byid").setParameter("reminderid", reminderId).getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Reminder deleted successfully");
			} else {
				obj.put("status", "failed");
				obj.put("message", ApiResponseMessage.UNKNOWN_EXCEPTION);
			}

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", ApiResponseMessage.UNKNOWN_EXCEPTION);
		}

		logger.info("Method : deleteMedicineReminderById Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	/*
	 * @SuppressWarnings("unchecked") public List<DropDownModel>
	 * getMedicineReminderList(String before5Min) {
	 * logger.info("Method : getMedicineReminderList Dao starts");
	 * 
	 * 
	 * List<DropDownModel> medicineReminderList = new ArrayList<DropDownModel>();
	 * try {
	 * 
	 * List<Object[]> x = em.createNamedStoredProcedureQuery("get_doctor_token_api")
	 * .setParameter("before5Min", before5Min).getResultList(); for (Object[] m : x)
	 * { DropDownModel dropDownModel = new DropDownModel(m, null);
	 * medicineReminderList.add(dropDownModel); }
	 * 
	 * } catch (Exception e) { logger.error("Error : "+e.getMessage()); }
	 * 
	 * logger.info("Method : getMedicineReminderList Dao ends"); return
	 * medicineReminderList; }
	 */
	
//	@SuppressWarnings("unchecked")
//	public List<DropDownModel> getTimeSlotList(String currentDate) {
//		logger.info("Method : getMedicineReminderList Dao starts");
//		
//
//		List<DropDownModel> slotList = new ArrayList<DropDownModel>();
//		try {
//
//			List<Object[]> x = em.createNamedStoredProcedureQuery("get_doctor_token_api")
//					.setParameter("currentDate", currentDate).getResultList();
//			for (Object[] m : x) {
//				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
//				slotList.add(dropDownModel);
//				System.out.println("slotList"+dropDownModel);
//			} 
//			
//		} catch (Exception e) {
//			logger.error("Error : "+e.getMessage());
//		}
//		
//		logger.info("Method : getMedicineReminderList Dao ends");
//		return slotList;
//	}
}
