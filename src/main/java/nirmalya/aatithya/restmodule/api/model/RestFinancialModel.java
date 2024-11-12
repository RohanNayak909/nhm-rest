package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestFinancialModel {
	private String uniqueid;
	private String userId;
	private String projectName;
	private String scheme;
	private String yearOfSanction;
	private String approvedAmount;
	private String availableCost;
	private String expenditure;
	private String ucSubmitted;
	private String fundRelease;
	private String userName;
	private String dataEntryStatus;
	
	
	public RestFinancialModel() {
		super();
	}


	
	public RestFinancialModel(Object uniqueid, Object projectName) {
		super();
		this.uniqueid = (String)uniqueid;
		this.projectName = (String)projectName;
	}



	public String getUniqueid() {
		return uniqueid;
	}


	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getScheme() {
		return scheme;
	}


	public void setScheme(String scheme) {
		this.scheme = scheme;
	}


	public String getYearOfSanction() {
		return yearOfSanction;
	}


	public void setYearOfSanction(String yearOfSanction) {
		this.yearOfSanction = yearOfSanction;
	}


	public String getApprovedAmount() {
		return approvedAmount;
	}


	public void setApprovedAmount(String approvedAmount) {
		this.approvedAmount = approvedAmount;
	}


	public String getAvailableCost() {
		return availableCost;
	}


	public void setAvailableCost(String availableCost) {
		this.availableCost = availableCost;
	}


	public String getExpenditure() {
		return expenditure;
	}


	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}


	public String getUcSubmitted() {
		return ucSubmitted;
	}


	public void setUcSubmitted(String ucSubmitted) {
		this.ucSubmitted = ucSubmitted;
	}
	
	
	
	public String getFundRelease() {
		return fundRelease;
	}



	public void setFundRelease(String fundRelease) {
		this.fundRelease = fundRelease;
	}
	



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getDataEntryStatus() {
		return dataEntryStatus;
	}



	public void setDataEntryStatus(String dataEntryStatus) {
		this.dataEntryStatus = dataEntryStatus;
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
