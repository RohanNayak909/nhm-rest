package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CureEasyRequestToServiceModel {
	private String userId;
	private String serviceId;
	private String addressOne;
	private String addressTwo;
	private String locality;
	private String city;
	private String pinCode; 
	public CureEasyRequestToServiceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CureEasyRequestToServiceModel(Object userId,Object serviceId,Object addressOne,Object addressTwo,
			Object locality,Object city,Object pinCode) {
		super();
		this.userId = (String) userId; 
		this.serviceId = (String) serviceId; 
		this.addressOne = (String) addressOne; 
		this.addressTwo = (String) addressTwo; 
		this.locality = (String) locality; 
		this.city = (String) city;
		this.pinCode = (String) pinCode; 
}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
