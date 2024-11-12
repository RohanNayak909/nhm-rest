package nirmalya.aatithya.restmodule.districtlevel.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class RestDistUserModel {
	private String selectedName;
	private String districtName;
	private String userType;
	private String district;
	private String userName;
	private String mobileNum;
	private String userId;
	private String adminId;
	private String assignedBlock;
	private String blockid;
	private List<DropDownModel> checkedCheckboxes;
	private String status;
	private String totalPageno;
	private String pages;
	private String state;
	private String stateName;
	
	
	public RestDistUserModel(Object userType, Object userName, Object mobileNum, Object districtName,
			Object assignedBlock, Object blockid,Object totalPageno,Object pages,Object status) {
		super();

		this.userType = (String) userType;
		this.userName = (String) userName;
		this.mobileNum = (String) mobileNum;
		this.districtName = (String) districtName;
		this.assignedBlock = (String) assignedBlock;
		this.blockid = (String) blockid;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
		this.status = (String)status;
	}
	
	public RestDistUserModel(Object userType, Object districtName, Object userName, Object mobileNum, Object blockid
			,Object status,Object assignedBlock,Object adminId,Object state,Object district
			) {
		super();

		this.userType = (String) userType;
		this.districtName = (String) districtName;
		this.userName = (String) userName;
		this.mobileNum = (String) mobileNum;
		this.blockid = (String) blockid;
		this.status = (String) status;
		this.assignedBlock = (String) assignedBlock;
		this.adminId = (String) adminId;
		this.state = (String) state;
		this.district = (String) district;
	}
	
	
	
	public String getSelectedName() {
		return selectedName;
	}




	public void setSelectedName(String selectedName) {
		this.selectedName = selectedName;
	}




	public String getDistrictName() {
		return districtName;
	}




	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}




	public String getUserType() {
		return userType;
	}




	public void setUserType(String userType) {
		this.userType = userType;
	}




	public String getDistrict() {
		return district;
	}




	public void setDistrict(String district) {
		this.district = district;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getMobileNum() {
		return mobileNum;
	}




	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getAdminId() {
		return adminId;
	}




	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}




	public String getAssignedBlock() {
		return assignedBlock;
	}




	public void setAssignedBlock(String assignedBlock) {
		this.assignedBlock = assignedBlock;
	}




	public String getBlockid() {
		return blockid;
	}




	public void setBlockid(String blockid) {
		this.blockid = blockid;
	}




	public List<DropDownModel> getCheckedCheckboxes() {
		return checkedCheckboxes;
	}




	public void setCheckedCheckboxes(List<DropDownModel> checkedCheckboxes) {
		this.checkedCheckboxes = checkedCheckboxes;
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




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public String getStateName() {
		return stateName;
	}




	public void setStateName(String stateName) {
		this.stateName = stateName;
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
