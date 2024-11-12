package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIMedicationModel {

	private String medname;
	private String medtype;
	private String dosage;
	private String morning;
	private String afternoon;
	private String evening;
	private String doctor;
	
	private String fromdate;
	private String todate;
	private String userid;
	private String appno;
	private String remarks;
	
	private String duration;
	private String meddate;
	private String qty;
	
	private String srlNoOne;
	private String srlNoTwo;
	
	private String medid;
	private Boolean reqstatus;
	private String status;
	
	private String testgroup;
	private String testname;
	
	private Integer quantity;
	private String drName;
	private String lab;
	
	private String reqDate;
	private String acceptDate;
	private String rejectDate;
	private String orderId;
	public APIMedicationModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public APIMedicationModel(Object orderId, Object reqDate, Object acceptDate,  Object rejectDate,  Object lab, Object status,
			Object testname,Object testgroup,Object remarks,Object userid,Object appno) {
		super();
		this.orderId = (String) orderId;
		this.reqDate = (String) reqDate;
		this.acceptDate = (String) acceptDate;
		this.rejectDate = (String) rejectDate;
		this.lab = (String) lab;
		this.status = (String) status;
		this.testname = (String) testname;
		this.testgroup = (String) testgroup;
		this.remarks = (String) remarks;
		this.userid = (String) userid;
		this.appno = (String) appno;
	}
	
	public APIMedicationModel(Object medid, Object medname, Object medtype, Object fromdate, Object todate,
			Object morning, Object afternoon, Object evening, Object userid) {
		super();
		this.medid = (String) medid;
		this.medname = (String) medname;
		this.medtype = (String) medtype;
		this.fromdate = (String) fromdate;
		this.todate = (String) todate;
		this.morning = (String) morning;
		this.afternoon = (String) afternoon;
		this.evening = (String) evening;
		this.userid = (String) userid;
	}
	
	public APIMedicationModel(Object doctor,Object fromdate,Object userid,Object appno,Object remarks,Object srlNoOne,Object srlNoTwo,
			Object reqstatus,Object testgroup,Object testname ) {
		super();
		this.doctor = (String) doctor;
		this.fromdate = (String) fromdate;
		this.userid = (String) userid;
		this.appno = (String) appno;
		this.remarks = (String) remarks;
		this.srlNoOne = (String) srlNoOne;
		this.srlNoTwo = (String) srlNoTwo;
		this.reqstatus = (Boolean) reqstatus;	
		this.testgroup = (String) testgroup;
		this.testname = (String) testname;
	}

	public APIMedicationModel(Object doctor,Object drName, Object meddate,Object appno) {
		super();
		this.doctor = (String) doctor;
		this.drName = (String) drName;
		this.meddate = (String) meddate;
		this.appno = (String) appno;
	}

	public APIMedicationModel(Object medname, Object medtype, Object dosage, Object morning, Object afternoon,
			Object evening, Object doctor) {
		super();
		this.medname = (String) medname;
		this.medtype = (String) medtype;
		this.dosage = (String) dosage;
		this.morning = (String) morning;
		this.afternoon = (String) afternoon;
		this.evening = (String) evening;
		this.doctor = (String) doctor;
	}
	
	public APIMedicationModel(Object medname, Object medtype, Object qty, Object remarks, Object doctor) {
		super();
		this.medname = (String) medname;
		this.medtype = (String) medtype;
		this.qty = (String) qty;
		this.remarks = (String) remarks;
		this.doctor = (String) doctor;
	}
	
	
	public APIMedicationModel(Object medname, Object meddate, Object dosage, Object morning, Object afternoon,
			Object evening, Object qty, Object duration, Object userid, Object srlNoOne, Object srlNoTwo, Object remarks,
			Object medid, Object reqstatus,  Object status, Object doctor,Object medtype) {
		super();
		this.medname = (String) medname;
		this.meddate = (String) meddate;
		this.dosage = (String) dosage;
		this.morning = (String) morning;
		this.afternoon = (String) afternoon;
		this.evening = (String) evening;
		this.qty = (String) qty;
		this.duration = (String) duration;
		this.userid = (String) userid;
		this.srlNoOne = (String) srlNoOne;
		this.srlNoTwo = (String) srlNoTwo;
		this.remarks = (String) remarks;
		this.medid = (String) medid;
		this.reqstatus = (Boolean) reqstatus;
		this.status = (String) status;
		this.doctor = (String) doctor;
		this.medtype = (String) medtype;
	}

	public APIMedicationModel(Object doctor,Object drName, Object meddate, Object srlNoOne, Object srlNoTwo, Object status,Object appno,Object medtype) {
		super();
		this.doctor = (String) doctor;
		this.drName = (String) drName;
		this.meddate = (String) meddate;
		this.srlNoOne = (String) srlNoOne;
		this.srlNoTwo = (String) srlNoTwo;
		this.status = (String) status;
		this.appno = (String) appno;
		this.medtype = (String) medtype;
	}
	

	public String getMedname() {
		return medname;
	}

	public void setMedname(String medname) {
		this.medname = medname;
	}

	public String getMedtype() {
		return medtype;
	}

	public void setMedtype(String medtype) {
		this.medtype = medtype;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getMorning() {
		return morning;
	}

	public void setMorning(String morning) {
		this.morning = morning;
	}

	public String getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(String afternoon) {
		this.afternoon = afternoon;
	}

	public String getEvening() {
		return evening;
	}

	public void setEvening(String evening) {
		this.evening = evening;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getMeddate() {
		return meddate;
	}

	public void setMeddate(String meddate) {
		this.meddate = meddate;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getSrlNoOne() {
		return srlNoOne;
	}

	public void setSrlNoOne(String srlNoOne) {
		this.srlNoOne = srlNoOne;
	}

	public String getSrlNoTwo() {
		return srlNoTwo;
	}

	public void setSrlNoTwo(String srlNoTwo) {
		this.srlNoTwo = srlNoTwo;
	}

	public String getMedid() {
		return medid;
	}

	public void setMedid(String medid) {
		this.medid = medid;
	}

	public Boolean getReqstatus() {
		return reqstatus;
	}

	public void setReqstatus(Boolean reqstatus) {
		this.reqstatus = reqstatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTestgroup() {
		return testgroup;
	}

	public void setTestgroup(String testgroup) {
		this.testgroup = testgroup;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(String rejectDate) {
		this.rejectDate = rejectDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
