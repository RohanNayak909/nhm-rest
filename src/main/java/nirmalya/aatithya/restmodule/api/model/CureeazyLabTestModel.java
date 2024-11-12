package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class CureeazyLabTestModel {
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
	
	private String packageId;
	private String packageName;
	private String packageImg;
	private String price;
	private String discountPrice;
	private String packageDesc;
	private List<PackagewiseTestListModel> getTestList;
	private List<PackagewiseTestListModel> getConditionTestList;
	public CureeazyLabTestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CureeazyLabTestModel(Object testId, Object testName, Object testImg, Object actualPrice, Object currentPrice,
			Object tat,Object testDesc, Object totalPrice, Object status) {
		super();
		this.testId = (String) testId;
		this.testName = (String) testName;
		this.testImg = (String) testImg;
		this.actualPrice = (String) actualPrice;
		this.currentPrice = (String) currentPrice;
		this.tat = (String) tat;
		this.testDesc = (String) testDesc;
		this.totalPrice = (String) totalPrice;  
		this.status = (String) status; 
	}
	
	public CureeazyLabTestModel(Object testId, Object testName) {
		super();
		this.testId = (String) testId;
		this.testName = (String) testName;
	}
	public CureeazyLabTestModel(Object packageId, Object packageName, Object packageImg, Object price,
			Object discountPrice, Object packageDesc) {
		super();
		this.packageId = (String)packageId;
		this.packageName = (String)packageName;
		this.packageImg = (String)packageImg;
		this.price = (String)price;
		this.discountPrice = (String)discountPrice;
		this.packageDesc = (String)packageDesc;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageImg() {
		return packageImg;
	}
	public void setPackageImg(String packageImg) {
		this.packageImg = packageImg;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getPackageDesc() {
		return packageDesc;
	}
	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
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

	public List<PackagewiseTestListModel> getGetTestList() {
		return getTestList;
	}
	public void setGetTestList(List<PackagewiseTestListModel> getTestList) {
		this.getTestList = getTestList;
	}
	
	public List<PackagewiseTestListModel> getGetConditionTestList() {
		return getConditionTestList;
	}
	public void setGetConditionTestList(List<PackagewiseTestListModel> getConditionTestList) {
		this.getConditionTestList = getConditionTestList;
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
