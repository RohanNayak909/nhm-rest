package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CureEasyHomeServiceOrderListModel {
	
	private String userId; 
	private String orderNo; 
	private String date; 
	private String status;
	private String name;
	private String addressOne;
	private String addressTwo;
	private String documentationStatus;
	private String payStatus;
	private String amount;
	
	public CureEasyHomeServiceOrderListModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CureEasyHomeServiceOrderListModel(Object userId, Object orderNo,  Object date,
			Object status, Object name, Object addressOne, Object addressTwo,Object documentationStatus,Object payStatus
			,Object amount) {
		super();
		this.userId = (String) userId;
		this.orderNo = (String) orderNo;
		this.date = (String) date;
		this.status = (String) status;
		this.name = (String) name;
		this.addressOne = (String) addressOne;
		this.addressTwo = (String) addressTwo;
		this.documentationStatus = (String) documentationStatus;
		this.payStatus = (String) payStatus;
		this.amount = (String) amount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddressOne() {
		return addressOne;
	}
	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}
	public String getAddressTwo() {
		return addressTwo;
	}
	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}
	
	public String getDocumentationStatus() {
		return documentationStatus;
	}
	public void setDocumentationStatus(String documentationStatus) {
		this.documentationStatus = documentationStatus;
	}
	
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
