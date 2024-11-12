package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorDashboardAppointmentDetailsModel {
	private String patientName;
	private String date;
	private String time;
	private String purpose;
	private String amount;
	private String orderid;
	private String status;
	private String profimg;
	private String appointmentId;
	private String phno;
	private String closeConsultationStatus;
	public DoctorDashboardAppointmentDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorDashboardAppointmentDetailsModel(Object patientName, Object date, Object time, Object purpose,
			Object amount,Object orderid,Object status,Object profimg,Object appointmentId,Object phno,Object closeConsultationStatus) {
		super();
		this.patientName = (String) patientName;
		this.date = (String) date;
		this.time = (String) time;
		this.purpose = (String) purpose;
		this.amount = (String) amount;
		this.orderid = (String) orderid;
		this.status = (String) status;
		this.profimg = (String) profimg;
		this.appointmentId = (String) appointmentId;
		this.phno = (String) phno;
		this.closeConsultationStatus = (String) closeConsultationStatus;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	

	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProfimg() {
		return profimg;
	}
	public void setProfimg(String profimg) {
		this.profimg = profimg;
	}
	
	public String getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	
	public String getCloseConsultationStatus() {
		return closeConsultationStatus;
	}
	public void setCloseConsultationStatus(String closeConsultationStatus) {
		this.closeConsultationStatus = closeConsultationStatus;
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
