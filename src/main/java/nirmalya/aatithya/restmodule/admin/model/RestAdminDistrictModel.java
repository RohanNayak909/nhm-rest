package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAdminDistrictModel {
	private String districtId;
	private String districtName;
	private String status;
	private String totalPageno;
	private String pages;
	
	public RestAdminDistrictModel() {
		super();
	}
	
	
	

	public RestAdminDistrictModel(Object districtId, Object districtName, Object totalPageno,Object pages) {
		super();
		this.districtId = (String)districtId;
		this.districtName = (String)districtName;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
	}

	public RestAdminDistrictModel(Object districtId, Object districtName,Object status) {
		super();
		
		this.districtId = (String)districtId;
		this.districtName = (String)districtName;
		this.status = (String)status;
	
	}



	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
