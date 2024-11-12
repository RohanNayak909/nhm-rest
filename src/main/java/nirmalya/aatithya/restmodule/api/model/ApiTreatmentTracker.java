package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiTreatmentTracker {
	private String userId;
	private String status;
	private String yesId;
	private String noId;
	private String dosageTime;
	private String dosageDate;
	
	
	
	/**
	 * @return the dosageTime
	 */
	public String getDosageTime() {
		return dosageTime;
	}

	/**
	 * @param dosageTime the dosageTime to set
	 */
	public void setDosageTime(String dosageTime) {
		this.dosageTime = dosageTime;
	}

	/**
	 * @return the dosageDate
	 */
	public String getDosageDate() {
		return dosageDate;
	}

	/**
	 * @param dosageDate the dosageDate to set
	 */
	public void setDosageDate(String dosageDate) {
		this.dosageDate = dosageDate;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the yesId
	 */
	public String getYesId() {
		return yesId;
	}

	/**
	 * @param yesId the yesId to set
	 */
	public void setYesId(String yesId) {
		this.yesId = yesId;
	}

	/**
	 * @return the noId
	 */
	public String getNoId() {
		return noId;
	}

	/**
	 * @param noId the noId to set
	 */
	public void setNoId(String noId) {
		this.noId = noId;
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
