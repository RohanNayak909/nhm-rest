package nirmalya.aatithya.restmodule.report.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDoctorReportsModel {
	
	private String doctorID;
	private String doctorName;
	private String city;
	private String address;
	private String patientName;
	private String bookedDate;
	private String amountReceived;
	private String status;
	private String regdNo;
	private String payToCurrez;
	private String payToDoctor;
	private String bankAccountName;
	private String bankAccountNo;
	private String ifscCode;
	
	private String appointId;
	private String patientMobNo;
	private String dateOfBooking;
	private String doctorFee;
	private String totalReceive;
	private String totalReceives;
	

	
	public  RestDoctorReportsModel() {
		super();
	}


	
	
	public RestDoctorReportsModel(Object doctorID, Object doctorName, Object city, Object address, Object patientName,
			Object bookedDate, Object amountReceived, Object status) {
		super();
		this.doctorID =(String) doctorID;
		this.doctorName = (String)doctorName;
		this.city = (String)city;
		this.address = (String)address;
		this.patientName = (String)patientName;
		this.bookedDate = (String)bookedDate;
		this.amountReceived = (String)amountReceived;
		this.status = (String)status;
	}

	public RestDoctorReportsModel(Object doctorID, Object doctorName, Object regdNo, Object amountReceived, Object payToCurrez,
			Object payToDoctor, Object bankAccountName, Object bankAccountNo, Object ifscCode) {
		super();
		this.doctorID =(String) doctorID;
		this.doctorName = (String)doctorName;
		this.regdNo = (String)regdNo;
		this.amountReceived = (String)amountReceived;
		this.payToCurrez = (String)payToCurrez;
		this.payToDoctor = (String)payToDoctor;
		this.bankAccountName = (String)bankAccountName;
		this.bankAccountNo = (String)bankAccountNo;
		this.ifscCode = (String)ifscCode;
		
	}
	public RestDoctorReportsModel(Object doctorID, Object doctorName, Object regdNo, Object appointId, Object patientName,
			Object patientMobNo, Object dateOfBooking, Object doctorFee, Object totalReceive, Object totalReceives) {
		super();
		this.doctorID =(String) doctorID;
		this.doctorName = (String)doctorName;
		this.regdNo = (String)regdNo;
		this.appointId = (String)appointId;
		this.patientName = (String)patientName;
		this.patientMobNo = (String)patientMobNo;
		this.dateOfBooking = (String)dateOfBooking;
		this.doctorFee = (String)doctorFee;
		this.totalReceive = (String)totalReceive;
		this.totalReceives = (String)totalReceives;
		
	}

	public String getDoctorID() {
		return doctorID;
	}


	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public String getBookedDate() {
		return bookedDate;
	}


	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}


	public String getAmountReceived() {
		return amountReceived;
	}


	public void setAmountReceived(String amountReceived) {
		this.amountReceived = amountReceived;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	public String getRegdNo() {
		return regdNo;
	}




	public void setRegdNo(String regdNo) {
		this.regdNo = regdNo;
	}




	public String getPayToCurrez() {
		return payToCurrez;
	}




	public void setPayToCurrez(String payToCurrez) {
		this.payToCurrez = payToCurrez;
	}




	public String getPayToDoctor() {
		return payToDoctor;
	}




	public void setPayToDoctor(String payToDoctor) {
		this.payToDoctor = payToDoctor;
	}




	public String getBankAccountName() {
		return bankAccountName;
	}




	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}




	public String getBankAccountNo() {
		return bankAccountNo;
	}




	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}




	public String getIfscCode() {
		return ifscCode;
	}




	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}




	public String getAppointId() {
		return appointId;
	}




	public void setAppointId(String appointId) {
		this.appointId = appointId;
	}




	public String getPatientMobNo() {
		return patientMobNo;
	}




	public void setPatientMobNo(String patientMobNo) {
		this.patientMobNo = patientMobNo;
	}




	public String getDateOfBooking() {
		return dateOfBooking;
	}




	public void setDateOfBooking(String dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}




	public String getDoctorFee() {
		return doctorFee;
	}




	public void setDoctorFee(String doctorFee) {
		this.doctorFee = doctorFee;
	}




	public String getTotalReceive() {
		return totalReceive;
	}




	public void setTotalReceive(String totalReceive) {
		this.totalReceive = totalReceive;
	}




	public String getTotalReceives() {
		return totalReceives;
	}




	public void setTotalReceives(String totalReceives) {
		this.totalReceives = totalReceives;
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
