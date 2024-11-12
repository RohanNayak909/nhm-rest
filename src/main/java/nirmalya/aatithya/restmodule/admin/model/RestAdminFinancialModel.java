package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAdminFinancialModel {
	
	private String scheme;
	private String yearOfSanction;
	private String approvedAmount;
	private String availableCost;
	private String fundRelease;
	
	public RestAdminFinancialModel() {
		super();
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
	
	public String getFundRelease() {
		return fundRelease;
	}

	public void setFundRelease(String fundRelease) {
		this.fundRelease = fundRelease;
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
