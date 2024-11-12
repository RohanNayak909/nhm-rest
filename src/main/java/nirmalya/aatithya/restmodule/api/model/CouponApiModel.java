package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CouponApiModel {
	private String couponId;
	private String couponName;
	private String discountPrice;
	private String couponDescription;
	private String image;
	private String startDate;
	private String endDate;
	private String expiryDate;
	private String status;
	private String percentage;
	public CouponApiModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CouponApiModel(Object couponId, Object couponName, Object discountPrice, Object couponDescription,
			Object image, Object startDate, Object endDate, Object expiryDate, Object status, Object percentage) {
		super();
		this.couponId = (String)couponId;
		this.couponName = (String)couponName;
		this.discountPrice = (String)discountPrice;
		this.couponDescription = (String)couponDescription;
		this.image = (String)image;
		this.startDate = (String)startDate;
		this.endDate = (String)endDate;
		this.expiryDate = (String)expiryDate;
		this.status = (String)status;
		this.percentage = (String)percentage;
	}
	public CouponApiModel(Object couponId, Object couponName, Object percentage, Object couponDescription) {
		this.couponId = (String)couponId;
		this.couponName = (String)couponName;
		this.percentage = (String)percentage;
		this.couponDescription = (String)couponDescription;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getCouponDescription() {
		return couponDescription;
	}
	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
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
