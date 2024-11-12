package nirmalya.aatithya.restmodule.districtlevel.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDistBlockModel {
	private String blockId;
	private String blockName;
	private String status;
	private String districtName;
	private String totalPageno;
	private String pages;
	private String districtId;
	

	public RestDistBlockModel() {
		super();
	}
	
	

	public RestDistBlockModel(Object blockId,Object districtName, Object blockName, Object totalPageno,Object pages) {
		super();
		this.blockId = (String)blockId;
		this.districtName = (String)districtName;
		this.blockName = (String)blockName;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
		
	}
	
	
	
	public RestDistBlockModel(Object blockId,Object blockName, Object status) {
		super();
		this.blockId = (String)blockId;
		this.blockName = (String)blockName;
		this.status = (String)status;
		
	}



	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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
