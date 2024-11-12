package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class HomeServicePaymentModel {
	private String slNo;
	private String orderId;
	private String date;
	private String amount;
	private String payStatus;
	
	private List<DropDownModel> paylist;
	
	public HomeServicePaymentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public HomeServicePaymentModel(Object slNo, Object orderId, Object date, Object amount,Object payStatus) {
		super();
		this.slNo = (String)slNo;
		this.orderId = (String)orderId;
		this.date = (String)date;
		this.amount = (String)amount;
		this.payStatus = (String)payStatus;
	}


	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	public List<DropDownModel> getPaylist() {
		return paylist;
	}


	public void setPaylist(List<DropDownModel> paylist) {
		this.paylist = paylist;
	}
	


	public String getPayStatus() {
		return payStatus;
	}


	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
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
