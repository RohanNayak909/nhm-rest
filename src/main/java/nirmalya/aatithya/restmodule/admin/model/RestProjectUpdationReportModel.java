package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestProjectUpdationReportModel {
	private String district;
	private String totalProject;
	private String notStartedProject;
	private String updatedProject;
	private String perNotStartedProject;
	private String perUpdatedProject;
	//private Integer slNumber;
	private String totalProjectSum;
	private String notStartedProjectSum;
	private String updatedProjectSum;
	private String totalPageno;
	private String pages;
	
	public RestProjectUpdationReportModel() {
		super();
	}
	
	
	

	public RestProjectUpdationReportModel(Object district, Object totalProject, Object notStartedProject,
			Object updatedProject, Object perNotStartedProject, Object perUpdatedProject,
			Object totalProjectSum,Object notStartedProjectSum,Object updatedProjectSum) {
		super();
		this.district = (String)district;
		this.totalProject = (String)totalProject;
		this.notStartedProject = (String)notStartedProject;
		this.updatedProject = (String)updatedProject;
		this.perNotStartedProject = (String)perNotStartedProject;
		this.perUpdatedProject = (String)perUpdatedProject;
		this.totalProjectSum = (String)totalProjectSum;
		this.notStartedProjectSum = (String)notStartedProjectSum;
		this.updatedProjectSum = (String)updatedProjectSum;
	}




	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTotalProject() {
		return totalProject;
	}

	public void setTotalProject(String totalProject) {
		this.totalProject = totalProject;
	}

	public String getNotStartedProject() {
		return notStartedProject;
	}

	public void setNotStartedProject(String notStartedProject) {
		this.notStartedProject = notStartedProject;
	}

	public String getUpdatedProject() {
		return updatedProject;
	}

	public void setUpdatedProject(String updatedProject) {
		this.updatedProject = updatedProject;
	}

	public String getPerUpdatedProject() {
		return perUpdatedProject;
	}

	public void setPerUpdatedProject(String perUpdatedProject) {
		this.perUpdatedProject = perUpdatedProject;
	}

	public String getPerNotStartedProject() {
		return perNotStartedProject;
	}

	public void setPerNotStartedProject(String perNotStartedProject) {
		this.perNotStartedProject = perNotStartedProject;
	}

	

//	public Integer getSlNumber() {
//		return slNumber;
//	}
//
//	public void setSlNumber(Integer slNumber) {
//		this.slNumber = slNumber;
//	}
	
	
	
	
	public String getTotalProjectSum() {
		return totalProjectSum;
	}




	public void setTotalProjectSum(String totalProjectSum) {
		this.totalProjectSum = totalProjectSum;
	}




	public String getNotStartedProjectSum() {
		return notStartedProjectSum;
	}




	public void setNotStartedProjectSum(String notStartedProjectSum) {
		this.notStartedProjectSum = notStartedProjectSum;
	}




	public String getUpdatedProjectSum() {
		return updatedProjectSum;
	}




	public void setUpdatedProjectSum(String updatedProjectSum) {
		this.updatedProjectSum = updatedProjectSum;
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
