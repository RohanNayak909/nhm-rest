package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class RestAdminUserModel {

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
	private String loginType;
	private String jeIds;
	private String roleId;
	private String moduleId;
	private String moduleName;
	private String functionId;
	private String functionName;
	private String activityId;
	private String activityName;
	private String browserName;
	private String ipAddress;
	private String event;
	private String token;
	

	
	
	

	public RestAdminUserModel( Object roleId, Object moduleId, Object moduleName, Object functionId,
			Object functionName, Object activityId, Object activityName,Object status) {
		super();
		
		this.roleId = (String)roleId;
		this.moduleId = (String)moduleId;
		this.moduleName = (String)moduleName;
		this.functionId = (String)functionId;
		this.functionName = (String)functionName;
		this.activityId =(String) activityId;
		this.activityName = (String)activityName;
		this.status = (String)status;
	}







	public RestAdminUserModel(Object userType, Object userName, Object mobileNum, Object districtName,
			Object assignedBlock, Object blockid,Object totalPageno,Object pages,Object status,Object loginType) {
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
		this.loginType = (String)loginType;
	}
	
	
	




	public RestAdminUserModel(Object userType, Object blockid) {
		super();

		this.userType = (String) userType;
		this.blockid = (String) blockid;

	}

	public RestAdminUserModel(Object userType, Object districtName, Object userName, Object mobileNum, Object blockid
			,Object status,Object assignedBlock,Object adminId,Object state
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
	}

	public RestAdminUserModel() {
		super();
	}

	public String getBlockid() {
		return blockid;
	}

	public void setBlockid(String blockid) {
		this.blockid = blockid;
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

	public List<DropDownModel> getCheckedCheckboxes() {
		return checkedCheckboxes;
	}

	public void setCheckedCheckboxes(List<DropDownModel> checkedCheckboxes) {
		this.checkedCheckboxes = checkedCheckboxes;
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
	
	
	

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	
	

	public String getJeIds() {
		return jeIds;
	}

	public void setJeIds(String jeIds) {
		this.jeIds = jeIds;
	}
	
	




	public String getRoleId() {
		return roleId;
	}







	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}







	public String getModuleId() {
		return moduleId;
	}







	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}







	public String getModuleName() {
		return moduleName;
	}







	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}







	public String getFunctionId() {
		return functionId;
	}







	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}







	public String getFunctionName() {
		return functionName;
	}







	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}







	public String getActivityId() {
		return activityId;
	}







	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}







	public String getActivityName() {
		return activityName;
	}







	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}







	public String getBrowserName() {
		return browserName;
	}







	public String getIpAddress() {
		return ipAddress;
	}







	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}







	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}




	public String getEvent() {
		return event;
	}







	public void setEvent(String event) {
		this.event = event;
	}


	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
