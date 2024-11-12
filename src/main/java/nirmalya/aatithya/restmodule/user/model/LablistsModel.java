package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LablistsModel {

	private String labId;
	private String orderNo;
	private String date;
	private String status;
	private String name;
	private String address1;
	private String address2;
	private String image;
	
	
	public LablistsModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public LablistsModel(Object labId, Object orderNo, Object date, Object status, Object name, Object address1,
			Object address2,Object image) {
		super();
		this.labId = (String) labId;
		this.orderNo = (String) orderNo;
		this.date = (String) date;
		this.status = (String) status;
		this.name = (String) name;
		this.address1 = (String) address1;
		this.address2 = (String) address2;
		this.image = (String) image;
	}
	
	



	public String getLabId() {
		return labId;
	}



	public void setLabId(String labId) {
		this.labId = labId;
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



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
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
