package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabTestViewReportModel {
	private String name;
	private String mobile;
	private String location;
	private String date;
	private String time;
	private String paymentStatus;
	private String addressLine2;
	private String selectStatus;
	private String city;
	private String testName;
	private String remarks;
	private String paymentId;
	private String addressLine1;
	private String price;
	private String pinCode;
	public LabTestViewReportModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabTestViewReportModel(Object name, Object mobile, Object location, Object date, Object time,
			Object paymentStatus, Object addressLine1, Object addressLine2, Object selectStatus, Object city, Object testName,
			Object remarks, Object paymentId,Object price,Object pinCode) {
		super();
		this.name = (String) name;
		this.mobile = (String) mobile;
		this.location = (String) location;
		this.date = (String) date;
		this.time = (String) time;
		this.paymentStatus = (String) paymentStatus;
		this.addressLine1 = (String) addressLine1;
		this.addressLine2 = (String) addressLine2;
		this.selectStatus = (String) selectStatus;
		this.city = (String) city;
		this.testName = (String) testName;
		this.remarks = (String) remarks;
		this.paymentId = (String) paymentId;
		this.price = (String) price;
		this.pinCode = (String) pinCode;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getSelectStatus() {
		return selectStatus;
	}
	public void setSelectStatus(String selectStatus) {
		this.selectStatus = selectStatus;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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
