package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAdminAgencyModel {
	private String agencyId;
	private String agencyName;
	private String status;
	private String totalPageno;
	private String pages;
	
	public RestAdminAgencyModel () {
		super();
	}
	
	
	

	public RestAdminAgencyModel(Object agencyId, Object agencyName, Object status) {
		super();
		this.agencyId = (String)agencyId;
		this.agencyName = (String)agencyName;
		this.status = (String)status;
	}
	
	
	public RestAdminAgencyModel(Object agencyId, Object agencyName,Object totalPageno, Object pages) {
		super();
		this.agencyId = (String)agencyId;
		this.agencyName = (String)agencyName;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
	}




	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
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
