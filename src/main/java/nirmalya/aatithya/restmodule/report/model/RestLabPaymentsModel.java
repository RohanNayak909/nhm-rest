package nirmalya.aatithya.restmodule.report.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestLabPaymentsModel {
	
	private String labName ;
	private String testChargePaid ;
	private String totalSumReceived ;
	private String labToLabCharges ;
	private String labToLabCollectionCharges ;
	private String cureezAmount ;
	private String labPartnerBankName ;
	private String labPartneraccountNumber ;
	private String ifscCode ;
	private String testName  ;
	private String orderNumber  ;
	private String userName  ;
	private String phoneNumber  ;
	private String testOrderDate  ;
	private String testCompleteDate  ;
	private String labid ;
	
	
	public  RestLabPaymentsModel() {
		super();
	}

	
	
	
	public RestLabPaymentsModel(Object labid,Object labName, Object testChargePaid, Object totalSumReceived, Object labToLabCharges,
			Object labToLabCollectionCharges, Object cureezAmount, Object labPartnerBankName,
			Object labPartneraccountNumber, Object ifscCode) {
		super();
		this.labid = (String)labid;
		this.labName = (String)labName;
		this.testChargePaid = (String)testChargePaid;
		this.totalSumReceived = (String)totalSumReceived;
		this.labToLabCharges = (String)labToLabCharges;
		this.labToLabCollectionCharges = (String)labToLabCollectionCharges;
		this.cureezAmount = (String)cureezAmount;
		this.labPartnerBankName = (String)labPartnerBankName;
		this.labPartneraccountNumber = (String)labPartneraccountNumber;
		this.ifscCode = (String)ifscCode;
	}

	public RestLabPaymentsModel(Object labid,Object labName, Object testName, Object orderNumber, Object userName,
			Object phoneNumber, Object testOrderDate, 
			Object testChargePaid, Object totalSumReceived,  Object labToLabCharges,  Object labToLabCollectionCharges,
			 Object cureezAmount) {
		super();
		this.labid = (String)labid;
		this.labName = (String)labName;
		this.testName = (String)testName;
		this.orderNumber = (String)orderNumber;
		this.userName = (String)userName;
		this.phoneNumber = (String)phoneNumber;
		this.testOrderDate = (String)testOrderDate;
		//this.testCompleteDate = (String)testCompleteDate;
		this.testChargePaid = (String)testChargePaid;
		this.totalSumReceived = (String)totalSumReceived;
		this.labToLabCharges = (String)labToLabCharges;
		this.labToLabCollectionCharges = (String)labToLabCollectionCharges;
		this.cureezAmount = (String)cureezAmount;
	}


	public String getLabName() {
		return labName;
	}


	public void setLabName(String labName) {
		this.labName = labName;
	}


	public String getTestChargePaid() {
		return testChargePaid;
	}


	public void setTestChargePaid(String testChargePaid) {
		this.testChargePaid = testChargePaid;
	}


	public String getTotalSumReceived() {
		return totalSumReceived;
	}


	public void setTotalSumReceived(String totalSumReceived) {
		this.totalSumReceived = totalSumReceived;
	}


	public String getLabToLabCharges() {
		return labToLabCharges;
	}


	public void setLabToLabCharges(String labToLabCharges) {
		this.labToLabCharges = labToLabCharges;
	}


	public String getLabToLabCollectionCharges() {
		return labToLabCollectionCharges;
	}


	public void setLabToLabCollectionCharges(String labToLabCollectionCharges) {
		this.labToLabCollectionCharges = labToLabCollectionCharges;
	}


	public String getCureezAmount() {
		return cureezAmount;
	}


	public void setCureezAmount(String cureezAmount) {
		this.cureezAmount = cureezAmount;
	}


	public String getLabPartnerBankName() {
		return labPartnerBankName;
	}


	public void setLabPartnerBankName(String labPartnerBankName) {
		this.labPartnerBankName = labPartnerBankName;
	}


	public String getLabPartneraccountNumber() {
		return labPartneraccountNumber;
	}


	public void setLabPartneraccountNumber(String labPartneraccountNumber) {
		this.labPartneraccountNumber = labPartneraccountNumber;
	}


	public String getIfscCode() {
		return ifscCode;
	}


	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getTestOrderDate() {
		return testOrderDate;
	}


	public void setTestOrderDate(String testOrderDate) {
		this.testOrderDate = testOrderDate;
	}


	public String getTestCompleteDate() {
		return testCompleteDate;
	}


	public void setTestCompleteDate(String testCompleteDate) {
		this.testCompleteDate = testCompleteDate;
	}

	

	public String getLabid() {
		return labid;
	}




	public void setLabid(String labid) {
		this.labid = labid;
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
