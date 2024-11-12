package nirmalya.aatithya.restmodule.report.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDoctorPaymentsModel {

	private String doctorid ;
	private String doctorname ;
	private String regdno ;
	private String totalAmt ;
	private String payToCureez ;
	private String payToDoctor ;
	private String bankName ;
	private String accountNumber ;
	private String ifscCode ;
	private String appointId  ;
	private String userName  ;
	private String userMob  ;
	private String bookingDate  ;
	private String doctorFee  ;
	private String totalReceive  ;
	
	public  RestDoctorPaymentsModel() {
		super();
	}
	
	
	
	public RestDoctorPaymentsModel(Object doctorid, Object doctorname, Object regdno, Object totalAmt,
			Object payToCureez, Object payToDoctor, Object bankName, Object accountNumber, Object ifscCode) {
		super();
		this.doctorid = (String)doctorid;
		this.doctorname = (String)doctorname;
		this.regdno = (String)regdno;
		this.totalAmt = (String)totalAmt;
		this.payToCureez = (String)payToCureez;
		this.payToDoctor = (String)payToDoctor;
		this.bankName = (String)bankName;
		this.accountNumber = (String)accountNumber;
		this.ifscCode = (String)ifscCode;
	}
	
	
	



	public RestDoctorPaymentsModel(Object doctorid, Object doctorname, Object regdno, Object appointId, Object userName,
			Object userMob, Object bookingDate, Object doctorFee, Object totalReceive, Object ifscCode) {
		super();
		this.doctorid =(String) doctorid;
		this.doctorname = (String)doctorname;
		this.regdno = (String)regdno;
		this.appointId = (String)appointId;
		this.userName = (String)userName;
		this.userMob = (String)userMob;
		this.bookingDate = (String)bookingDate;
		this.doctorFee = (String)doctorFee;
		this.totalReceive = (String)totalReceive;
		this.ifscCode = (String)ifscCode;
	}



	public String getDoctorid() {
		return doctorid;
	}



	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}



	public String getDoctorname() {
		return doctorname;
	}



	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}



	public String getRegdno() {
		return regdno;
	}



	public void setRegdno(String regdno) {
		this.regdno = regdno;
	}



	public String getTotalAmt() {
		return totalAmt;
	}



	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}



	public String getPayToCureez() {
		return payToCureez;
	}



	public void setPayToCureez(String payToCureez) {
		this.payToCureez = payToCureez;
	}



	public String getPayToDoctor() {
		return payToDoctor;
	}



	public void setPayToDoctor(String payToDoctor) {
		this.payToDoctor = payToDoctor;
	}



	public String getBankName() {
		return bankName;
	}



	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	public String getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getIfscCode() {
		return ifscCode;
	}



	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
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
