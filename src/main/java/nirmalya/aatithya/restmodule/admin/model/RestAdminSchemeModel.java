package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAdminSchemeModel {
	private String schemeId;
	private String schemeName;
	private String status;
	private String totalPageno;
	private String pages;
	
	public RestAdminSchemeModel() {
		super();
	}
	
	
	
	

	public RestAdminSchemeModel(Object schemeId, Object schemeName, Object status) {
		super();
		
		this.schemeId = (String)schemeId;
		this.schemeName = (String)schemeName;
		this.status = (String)status;
	}
	
	
	public RestAdminSchemeModel(Object schemeId, Object schemeName, Object totalPageno, Object pages) {
		super();
		this.schemeId = (String)schemeId;
		this.schemeName = (String)schemeName;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
	}




	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
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
