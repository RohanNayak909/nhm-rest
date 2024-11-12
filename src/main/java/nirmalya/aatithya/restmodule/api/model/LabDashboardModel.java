package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabDashboardModel {
	private String orderId;
	private String name;
	private String mobile;
	private String location;
	private String city;
	private String testName;
	private String tat;
	private String orderDate;
	private String timeSlot;
	private String price;
	private String status;
	private String labId;
	private String remark;
	private String docName;
	private String extension;
	private String collectionDate;
	
	private String order_id;
	private String paymentdate;
	private String paymenttime;
	private String userName;
	private String email;
	private String partnerName;
	private String deviceToken;
	private String userId;
	private String usertoken;
	public LabDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public LabDashboardModel(Object orderId, Object name, Object mobile, Object location, Object city, Object testName,
			Object tat, Object collectionDate, Object timeSlot, Object price, Object status, Object orderDate) {
		super();
		this.orderId = (String)orderId;
		this.name = (String)name;
		this.mobile = (String)mobile;
		this.location = (String)location;
		this.city = (String)city;
		this.testName = (String)testName;
		this.tat = (String)tat;
		this.collectionDate = (String)collectionDate;
		this.timeSlot = (String)timeSlot;
		this.price = (String)price;
		this.status = (String)status;
		this.orderDate = (String)orderDate;
	}
	public LabDashboardModel(Object order_id, Object paymentdate, Object paymenttime, Object userName
			, Object email,Object partnerName,Object deviceToken, Object status, Object mobile,Object userId) {
		super();
		this.order_id = (String)order_id;
		this.paymentdate = (String)paymentdate;
		this.paymenttime = (String)paymenttime;
		this.userName = (String)userName;
		this.email = (String)email;
		this.partnerName = (String)partnerName;
		this.deviceToken = (String)deviceToken;
		this.status = (String)status;
		this.mobile = (String)mobile;
		this.userId = (String)userId;
	}


	public LabDashboardModel(Object usertoken) {
		super();
		this.usertoken = (String)usertoken;
	}


	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getTat() {
		return tat;
	}
	public void setTat(String tat) {
		this.tat = tat;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getLabId() {
		return labId;
	}


	public void setLabId(String labId) {
		this.labId = labId;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	


	public String getDocName() {
		return docName;
	}


	public void setDocName(String docName) {
		this.docName = docName;
	}


	public String getExtension() {
		return extension;
	}


	public void setExtension(String extension) {
		this.extension = extension;
	}
	


	public String getCollectionDate() {
		return collectionDate;
	}


	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}
	


	public String getOrder_id() {
		return order_id;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	public String getPaymentdate() {
		return paymentdate;
	}


	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}


	public String getPaymenttime() {
		return paymenttime;
	}


	public void setPaymenttime(String paymenttime) {
		this.paymenttime = paymenttime;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPartnerName() {
		return partnerName;
	}


	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}


	public String getDeviceToken() {
		return deviceToken;
	}


	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	


	public String getUsertoken() {
		return usertoken;
	}


	public void setUsertoken(String usertoken) {
		this.usertoken = usertoken;
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
