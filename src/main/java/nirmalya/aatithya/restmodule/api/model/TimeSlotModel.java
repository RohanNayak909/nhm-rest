package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TimeSlotModel {
	private String slotId;
	private String date;
	private String openTime;
	private String closeTime;
	private String breakFrom;
	private String breakTo;
	private String offDay;
	private String status;
	public TimeSlotModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TimeSlotModel(Object slotId, Object date, Object openTime, Object closeTime, Object breakFrom,
			Object breakTo, Object offDay, Object status) {
		super();
		this.slotId = (String)slotId;
		this.date = (String)date;
		this.openTime = (String)openTime;
		this.closeTime = (String)closeTime;
		this.breakFrom = (String)breakFrom;
		this.breakTo = (String)breakTo;
		this.offDay = (String)offDay;
		this.status = (String)status;
	}
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getBreakFrom() {
		return breakFrom;
	}
	public void setBreakFrom(String breakFrom) {
		this.breakFrom = breakFrom;
	}
	public String getBreakTo() {
		return breakTo;
	}
	public void setBreakTo(String breakTo) {
		this.breakTo = breakTo;
	}
	public String getOffDay() {
		return offDay;
	}
	public void setOffDay(String offDay) {
		this.offDay = offDay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
