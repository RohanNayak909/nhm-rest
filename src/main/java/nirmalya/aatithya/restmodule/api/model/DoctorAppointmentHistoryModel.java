package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorAppointmentHistoryModel {
	
	private String patientName;
	private String bookingType;
	private String amount;
	private String status;
	private String patientId;
	private String purpose;
	private String phNo;
	private String languageKnown;
	private String appointTime;
	private String appointDate;
	private String consultCharges;
	private String totalPrice;
	private String appointId;
	private String image;
	private String id;
	private String consultType;
	private String prescriptionImg;
	
	public DoctorAppointmentHistoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorAppointmentHistoryModel(Object patientName, Object bookingType, Object amount, Object status,Object patientId,Object appointId,Object id) {
		super();
		this.patientName = (String) patientName;
		this.bookingType = (String) bookingType;
		this.amount = (String) amount;
		this.status = (String) status;
		this.patientId = (String) patientId;
		this.appointId = (String) appointId;
		this.id = (String) id;
	}
	
	public DoctorAppointmentHistoryModel(Object patientName, Object purpose, Object phNo, Object languageKnown,Object status
			, Object appointTime, Object appointDate,Object bookingType, Object consultCharges,Object totalPrice
			,Object appointId,Object image,Object patientId,Object consultType,Object prescriptionImg) {
		super();
		this.patientName = (String) patientName;
		this.purpose = (String) purpose;
		this.phNo = (String) phNo;
		this.languageKnown = (String) languageKnown;
		this.status = (String) status;
		this.appointTime = (String) appointTime;
		this.appointDate = (String) appointDate;
		this.bookingType = (String) bookingType;
		this.consultCharges = (String) consultCharges;
		this.totalPrice = (String) totalPrice;
		this.appointId = (String) appointId;
		this.image = (String) image;
		this.patientId = (String) patientId;
		this.consultType = (String) consultType;
		this.prescriptionImg = (String) prescriptionImg;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getBookingType() {
		return bookingType;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public String getLanguageKnown() {
		return languageKnown;
	}
	public void setLanguageKnown(String languageKnown) {
		this.languageKnown = languageKnown;
	}
	public String getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(String appointTime) {
		this.appointTime = appointTime;
	}
	public String getAppointDate() {
		return appointDate;
	}
	public void setAppointDate(String appointDate) {
		this.appointDate = appointDate;
	}
	public String getConsultCharges() {
		return consultCharges;
	}
	public void setConsultCharges(String consultCharges) {
		this.consultCharges = consultCharges;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAppointId() {
		return appointId;
	}
	public void setAppointId(String appointId) {
		this.appointId = appointId;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getConsultType() {
		return consultType;
	}
	public void setConsultType(String consultType) {
		this.consultType = consultType;
	}
	
	public String getPrescriptionImg() {
		return prescriptionImg;
	}
	public void setPrescriptionImg(String prescriptionImg) {
		this.prescriptionImg = prescriptionImg;
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
