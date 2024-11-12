package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorAppointmentDetailsModel {
	private String userId;
	private String doctorId;
	private String bookingType;
	private String purpose;
	private String appointmentStartTime;
	private String appointmentEndTime;
	private String docName;
	private String mobNo;
	private String address;
	public DoctorAppointmentDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorAppointmentDetailsModel(Object userId, Object doctorId, Object bookingType, Object purpose,
			Object appointmentStartTime, Object appointmentEndTime, Object docName, Object mobNo
			, Object address) {
		super();
		this.userId = (String) userId;
		this.doctorId = (String) doctorId;
		this.bookingType = (String) bookingType;
		this.purpose = (String) purpose;
		this.appointmentStartTime = (String) appointmentStartTime;
		this.appointmentEndTime = (String) appointmentEndTime;
		this.docName = (String) docName;
		this.mobNo = (String) mobNo;
		this.address = (String) address;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getBookingType() {
		return bookingType;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}
	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}
	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}
	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}
	
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
