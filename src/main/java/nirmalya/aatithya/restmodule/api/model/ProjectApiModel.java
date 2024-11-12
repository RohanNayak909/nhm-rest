package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class ProjectApiModel {
	private String projectId;
	private String projectName;
	private List<ProjectPhysicalStatusModel> physicalStatuslist;
	private List<ProjectPhysicalStatusModel> financialList;
	private List<DropDownModel> physicalDropdownlist;
	private List<RestFloorModel> floorList;
	
	public ProjectApiModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectApiModel(Object projectId, Object projectName) {
		super();
		this.projectId = (String)projectId;
		this.projectName = (String)projectName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	public List<ProjectPhysicalStatusModel> getPhysicalStatuslist() {
		return physicalStatuslist;
	}
	public void setPhysicalStatuslist(List<ProjectPhysicalStatusModel> physicalStatuslist) {
		this.physicalStatuslist = physicalStatuslist;
	}
	
	public List<ProjectPhysicalStatusModel> getFinancialList() {
		return financialList;
	}
	public void setFinancialList(List<ProjectPhysicalStatusModel> financialList) {
		this.financialList = financialList;
	}
	public List<DropDownModel> getPhysicalDropdownlist() {
		return physicalDropdownlist;
	}
	public void setPhysicalDropdownlist(List<DropDownModel> physicalDropdownlist) {
		this.physicalDropdownlist = physicalDropdownlist;
	}
	

	public List<RestFloorModel> getFloorList() {
		return floorList;
	}
	public void setFloorList(List<RestFloorModel> floorList) {
		this.floorList = floorList;
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
