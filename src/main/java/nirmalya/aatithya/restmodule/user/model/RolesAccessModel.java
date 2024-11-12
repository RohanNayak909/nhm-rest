package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RolesAccessModel {

	private String roleId;
	private String roleName;
	private String roleDesc;
	private Boolean roleStatus;
	private String createdBy;
	private String createdDate;
	
	public RolesAccessModel() {
		super();
	}

	public RolesAccessModel(Object roleId, Object roleName, Object roleDesc, Object roleStatus, Object createdBy,
			Object createdDate) {
		super();
		this.roleId = (String) roleId;
		this.roleName = (String) roleName;
		this.roleDesc = (String) roleDesc;
		this.roleStatus = (Boolean) roleStatus;
		this.createdBy = (String) createdBy;
		this.createdDate = (String) createdDate;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Boolean getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Boolean roleStatus) {
		this.roleStatus = roleStatus;
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
