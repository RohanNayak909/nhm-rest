package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CaseStudyModel {
	private String orderId;
	private String date;
	private String time;
	private String userId;
	private String docName;
	public CaseStudyModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CaseStudyModel(Object orderId, Object date, Object time, Object userId,Object docName) {
		super();
		this.orderId = (String)orderId;
		this.date = (String)date;
		this.time = (String)time;
		this.userId = (String)userId;
		this.docName = (String)docName;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
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
