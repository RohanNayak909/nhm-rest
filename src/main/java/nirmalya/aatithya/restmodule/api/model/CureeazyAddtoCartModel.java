package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class CureeazyAddtoCartModel {
	private String userId; 
	private String testId; 
	private String price;
	private String testName;
	private String cartId;
	private String discountPrice;
	private String tat;
	private String description;
	private String image;
	private String type;
	
	public CureeazyAddtoCartModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CureeazyAddtoCartModel(Object userId, Object testId,  Object testName,Object price,
			Object cartId,Object discountPrice,Object tat,Object description,Object image) {
		super();
		this.userId = (String) userId;
		this.testId = (String) testId;
		this.testName = (String) testName;
		this.price = (String) price;
		this.cartId = (String) cartId;
		this.discountPrice = (String) discountPrice;
		this.tat = (String) tat;
		this.description = (String) description;
		this.image = (String) image;
	
	
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	} 
	
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	
	public String getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getTat() {
		return tat;
	}
	public void setTat(String tat) {
		this.tat = tat;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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
