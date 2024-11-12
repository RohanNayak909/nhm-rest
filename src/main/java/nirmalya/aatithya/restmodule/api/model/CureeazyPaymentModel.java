package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class CureeazyPaymentModel {
	private String date;
	private String time;
	private String addressOne;
	private String addressTwo;
	private String location;
	private String city;
	private String pincode;
	private String othercharges;
	private String coupon;
	private String totalprice;
	private String orderid;
	private String userid;
	private List<CureeazyAddtoCartModel> testListDetails;
	
	
	
	public CureeazyPaymentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CureeazyPaymentModel(Object date, Object time,  Object addressOne,Object addressTwo,
			Object location,Object city,Object pincode,Object othercharges,Object coupon,Object totalprice) {
		super();
		this.date = (String) date;
		this.time = (String) time;
		this.addressOne = (String) addressOne;
		this.addressTwo = (String) addressTwo;
		this.location = (String) location;
		this.city = (String) city;
		this.pincode = (String) pincode;
		this.othercharges = (String) othercharges;
		this.coupon = (String) coupon;
		this.totalprice = (String) totalprice;
	
	
	}
	
	public CureeazyPaymentModel(Object totalprice, Object orderid) {
		super();
		this.totalprice = (String)totalprice;
		this.orderid = (String)orderid;
	}
	
	public CureeazyPaymentModel(Object pincode, Object location, Object orderid) {
		super();
		this.pincode = (String)pincode;
		this.location = (String)location;
		this.orderid = (String)orderid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getOthercharges() {
		return othercharges;
	}
	public void setOthercharges(String othercharges) {
		this.othercharges = othercharges;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public List<CureeazyAddtoCartModel> getTestListDetails() {
		return testListDetails;
	}
	public void setTestListDetails(List<CureeazyAddtoCartModel> testListDetails) {
		this.testListDetails = testListDetails;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
