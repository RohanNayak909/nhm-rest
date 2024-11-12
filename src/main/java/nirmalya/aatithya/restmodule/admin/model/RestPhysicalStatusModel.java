package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPhysicalStatusModel {
	private String physicalId;
	private String physicalName;
	private String status;
	private String totalPageno;
	private String pages;
	
	public RestPhysicalStatusModel() {
		super();
	}

	
	
	
	public RestPhysicalStatusModel(Object physicalId, Object physicalName, Object status) {
		super();
		this.physicalId = (String)physicalId;
		this.physicalName = (String)physicalName;
		this.status = (String)status;
	}
	
	
	
	public RestPhysicalStatusModel(Object physicalId, Object physicalName, Object totalPageno,Object pages) {
		super();
		this.physicalId = (String)physicalId;
		this.physicalName = (String)physicalName;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
	}




	public String getPhysicalId() {
		return physicalId;
	}




	public void setPhysicalId(String physicalId) {
		this.physicalId = physicalId;
	}




	public String getPhysicalName() {
		return physicalName;
	}




	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}


	public String getTotalPageno() {
		return totalPageno;
	}




	public void setTotalPageno(String totalPageno) {
		this.totalPageno = totalPageno;
	}




	public String getPages() {
		return pages;
	}




	public void setPages(String pages) {
		this.pages = pages;
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
