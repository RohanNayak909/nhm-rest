package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAdminInstitutionModel {
	private String institutionId;
	private String category;
	private String institutionname;
	private String latitude;
	private String longitude;
	private String status;
	private String district;
	private String block;
	private String totalPageno;
	private String pages;
	private String districtId;
	private String blockId;
	private String categoryId;
	
	public RestAdminInstitutionModel() {
		super();
	}
	
	
	

	public RestAdminInstitutionModel(Object institutionId,Object district, Object block, Object institutionname, Object category, Object latitude,
			Object longitude,Object status) {
		super();
		this.institutionId = (String)institutionId;
		this.district = (String)district;
		this.block = (String)block;
		this.institutionname = (String)institutionname;
		this.category = (String)category;
		this.latitude = (String)latitude;
		this.longitude = (String)longitude;
		this.status = (String)status;
		
	}
	
	
	
	public RestAdminInstitutionModel(Object institutionId,Object district, Object block, Object institutionname, Object category, Object latitude,
			Object longitude, Object totalPageno, Object pages) {
		super();
		this.institutionId = (String)institutionId;
		this.district = (String)district;
		this.block = (String)block;
		this.institutionname = (String)institutionname;
		this.category = (String)category;
		this.latitude = (String)latitude;
		this.longitude = (String)longitude;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
		
	}




	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInstitutionname() {
		return institutionname;
	}

	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getDistrict() {
		return district;
	}




	public void setDistrict(String district) {
		this.district = district;
	}




	public String getBlock() {
		return block;
	}




	public void setBlock(String block) {
		this.block = block;
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




	public String getBlockId() {
		return blockId;
	}




	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}




	public String getCategoryId() {
		return categoryId;
	}




	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
