package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientReportModel {
	private String docotorName;
	private String consultType;
	private String purpose;
	private String appointId;
	private String image;
	
	
	
	private String testname;
	private String testDate;
	private String remark;
	public PatientReportModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PatientReportModel(Object docotorName, Object consultType, Object purpose, Object appointId, Object image) {
		super();
		this.docotorName = (String)docotorName;
		this.consultType = (String)consultType;
		this.purpose = (String)purpose;
		this.appointId = (String)appointId;
		this.image = (String)image;
	}
	
	public PatientReportModel(Object testname, Object testDate, Object remark, Object image) {
		super();
		this.testname = (String)testname;
		this.testDate = (String)testDate;
		this.remark = (String)remark;
		this.image = (String)image;
	}
	public String getDocotorName() {
		return docotorName;
	}
	public void setDocotorName(String docotorName) {
		this.docotorName = docotorName;
	}
	public String getConsultType() {
		return consultType;
	}
	public void setConsultType(String consultType) {
		this.consultType = consultType;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
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
	
	
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
