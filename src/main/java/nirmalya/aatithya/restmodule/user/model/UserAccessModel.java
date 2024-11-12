package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserAccessModel {

	private String userId;
	private String userFirstName;
	private String userLastName;
	private String userType;
	private String userEmail;
	private String userPhone;
	private String userRole;
	private Boolean userStatus;
	private String userEmployee;
	private String createdBy;
	private String createdDate;
	private String password;
	private List<String> roleList = new ArrayList<String>();
	private String roleName;
	private String empName;
	
	public UserAccessModel() {
		super();
	}

	public UserAccessModel(Object userId, Object userFirstName, Object userLastName, Object userType, Object userEmail,
			Object userPhone, Object userRole, Object userStatus, Object userEmployee, Object createdBy,
			Object createdDate, Object roleName, Object empName) {
		super();
		this.userId = (String) userId;
		this.userFirstName = (String) userFirstName;
		this.userLastName = (String) userLastName;
		this.userType = (String) userType;
		this.userEmail = (String) userEmail;
		this.userPhone = (String) userPhone;
		this.userRole = (String) userRole;
		this.userStatus = (Boolean) userStatus;
		this.userEmployee = (String) userEmployee;
		this.createdBy = (String) createdBy;
		this.createdDate = (String) createdDate;
		this.roleName = (String) roleName;
		this.empName = (String) empName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserEmployee() {
		return userEmployee;
	}

	public void setUserEmployee(String userEmployee) {
		this.userEmployee = userEmployee;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
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
