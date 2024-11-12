package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIMedicineReminder {

	private String id;
	private String userId;
	private String medType;
	private String medName;
	private String medDosage;
	private String dosagePerDay;
	private String startTime;
	private String endTime;
	private String frequency;
	private String startDate;
	private String totalDays;
	private String instructions;
	private Boolean isNotified;
	private List<String> doseTime;

	private String dosageDate;
	private String dosageTime;

	private String userName;
	private String token;
	private String meddtls;
	
	private String reminderType;
	private String endDate;
	private String dosageStartTime;
	private String dosageEndTime;

	public APIMedicineReminder(Object id, Object userId, Object medType, Object medName, Object medDosage,
			Object dosageDate, Object dosageTime, Object instructions, Object userName, Object token, Object meddtls) {
		super();
		this.id = (String) id;
		this.userId = (String) userId;
		this.medType = (String) medType;
		this.medName = (String) medName;
		this.medDosage = (String) medDosage;
		this.dosageDate = (String) dosageDate;
		this.dosageTime = (String) dosageTime;
		this.instructions = (String) instructions;
		this.userName = (String) userName;
		this.token = (String) token;
		this.meddtls = (String) meddtls;
	}
	
	public APIMedicineReminder(Object id, Object userId, Object reminderType, Object medName, Object medDosage,
			Object startDate, Object dosageStartTime,  Object dosageEndTime,Object instructions, Object frequency,Object dosageTime,Object userName) {
		super();
		this.id = (String) id;
		this.userId = (String) userId;
		this.reminderType = (String) reminderType;
		this.medName = (String) medName;
		this.medDosage = (String) medDosage;
		this.startDate = (String) startDate;
		this.endDate = (String) endDate;
		this.instructions = (String) instructions;
		this.frequency = (String) frequency;
		this.dosageStartTime = (String) dosageStartTime;
		this.dosageEndTime = (String) dosageEndTime;
		this.dosageTime = (String) dosageTime;
		this.userName = (String) userName;
	}

	
	
	public String getReminderType() {
		return reminderType;
	}

	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDosageStartTime() {
		return dosageStartTime;
	}

	public void setDosageStartTime(String dosageStartTime) {
		this.dosageStartTime = dosageStartTime;
	}

	public String getDosageEndTime() {
		return dosageEndTime;
	}

	public void setDosageEndTime(String dosageEndTime) {
		this.dosageEndTime = dosageEndTime;
	}

	public String getDosageDate() {
		return dosageDate;
	}

	public void setDosageDate(String dosageDate) {
		this.dosageDate = dosageDate;
	}

	public String getDosageTime() {
		return dosageTime;
	}

	public void setDosageTime(String dosageTime) {
		this.dosageTime = dosageTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getDoseTime() {
		return doseTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setDoseTime(List<String> doseTime) {
		this.doseTime = doseTime;
	}

	public String getMedType() {
		return medType;
	}

	public void setMedType(String medType) {
		this.medType = medType;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getMedDosage() {
		return medDosage;
	}

	public void setMedDosage(String medDosage) {
		this.medDosage = medDosage;
	}

	public String getDosagePerDay() {
		return dosagePerDay;
	}

	public void setDosagePerDay(String dosagePerDay) {
		this.dosagePerDay = dosagePerDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(String totalDays) {
		this.totalDays = totalDays;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Boolean getIsNotified() {
		return isNotified;
	}

	public void setIsNotified(Boolean isNotified) {
		this.isNotified = isNotified;
	}

	public APIMedicineReminder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMeddtls() {
		return meddtls;
	}

	public void setMeddtls(String meddtls) {
		this.meddtls = meddtls;
	}

	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}

}
