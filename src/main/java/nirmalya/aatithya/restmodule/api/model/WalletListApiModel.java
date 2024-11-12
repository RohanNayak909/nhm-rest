package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WalletListApiModel {
	private String walletId;
	private String userId;
	private String transactionDate;
	private String orderId;
	private String price;
	private String userType;
	public WalletListApiModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public WalletListApiModel(Object walletId, Object userId, Object transactionDate, Object orderId, Object price, Object userType) {
		super();
		this.walletId = (String)walletId;
		this.userId = (String)userId;
		this.transactionDate = (String)transactionDate;
		this.orderId = (String)orderId;
		this.price = (String)price;
		this.userType = (String)userType;
	}


	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
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
