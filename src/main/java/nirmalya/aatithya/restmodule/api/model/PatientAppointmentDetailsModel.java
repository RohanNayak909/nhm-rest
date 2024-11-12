package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientAppointmentDetailsModel {
	private String patientName;
	private String date;
	private String time;
	private String purpose;
	private String phoneNumber;
	private String language;
	private String bookingType;
	private String consultingCharges;
	private String total;
	private String document;
	private String apptId;
	private String status;
	private String consulttype;
	public PatientAppointmentDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PatientAppointmentDetailsModel(Object patientName, Object date, Object time, Object purpose,
			Object phoneNumber, Object language, Object bookingType, Object consultingCharges, Object total, Object document
			, Object apptId, Object status,Object consulttype) {
		super();
		this.patientName = (String) patientName;
		this.date = (String) date;
		this.time = (String) time;
		this.purpose = (String) purpose;
		this.phoneNumber = (String) phoneNumber;
		this.language = (String) language;
		this.bookingType = (String) bookingType;
		this.consultingCharges = (String) consultingCharges;
		this.total = (String) total;
		this.document = (String) document;
		this.apptId = (String) apptId;
		this.status = (String) status;
		this.consulttype = (String) consulttype;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBookingType() {
		return bookingType;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public String getConsultingCharges() {
		return consultingCharges;
	}
	public void setConsultingCharges(String consultingCharges) {
		this.consultingCharges = consultingCharges;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
	
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	
	public String getApptId() {
		return apptId;
	}
	public void setApptId(String apptId) {
		this.apptId = apptId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getConsulttype() {
		return consulttype;
	}
	public void setConsulttype(String consulttype) {
		this.consulttype = consulttype;
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
