package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPaymentGatewayModel {
	private String userId;
	private String userName;
	private String appointId;
	private String orderId;
	private String amount;
	public RestPaymentGatewayModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestPaymentGatewayModel(Object userId, Object userName, Object appointId, Object orderId, Object amount) {
		super();
		this.userId = (String)userId;
		this.userName = (String)userName;
		this.appointId = (String)appointId;
		this.orderId = (String)orderId;
		this.amount = (String)amount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAppointId() {
		return appointId;
	}
	public void setAppointId(String appointId) {
		this.appointId = appointId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
