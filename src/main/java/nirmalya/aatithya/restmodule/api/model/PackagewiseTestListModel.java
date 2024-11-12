package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PackagewiseTestListModel {
	private String testId;
	private String testName;
	private String testImg;
	private String actualPrice;
	private String currentPrice;
	private String testDesc;
	private String tat;
	private String totalPrice;
	private String status;
	private String userId;
	public PackagewiseTestListModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PackagewiseTestListModel(Object testId, Object testName, Object testImg, Object actualPrice,
			Object currentPrice, Object testDesc, Object tat, Object totalPrice, Object status, Object userId) {
		super();
		this.testId = (String)testId;
		this.testName = (String)testName;
		this.testImg = (String)testImg;
		this.actualPrice = (String)actualPrice;
		this.currentPrice = (String)currentPrice;
		this.testDesc = (String)testDesc;
		this.tat = (String)tat;
		this.totalPrice = (String)totalPrice;
		this.status = (String)status;
		this.userId = (String)userId;
	}


	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestImg() {
		return testImg;
	}
	public void setTestImg(String testImg) {
		this.testImg = testImg;
	}
	public String getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}
	public String getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getTestDesc() {
		return testDesc;
	}
	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}
	public String getTat() {
		return tat;
	}
	public void setTat(String tat) {
		this.tat = tat;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
