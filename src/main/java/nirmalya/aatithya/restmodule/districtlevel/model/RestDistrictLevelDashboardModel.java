package nirmalya.aatithya.restmodule.districtlevel.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDistrictLevelDashboardModel {

	private String notStarted;
	private String foundationLevel;
	private String plinthLevel;
	private String lintelLevel;
	private String documentation;
	private String roofLevel;
	private String finishingStage;
	private String completed;
	private String projectId;
	private String districtId;
	private String blockId;
	private String institude;
	private String scheme;
	private String agency;
	private String sanctionYear;
	private String projectName;
	private String approvedAmt;
	private String abyaCost;
	private String floor;
	private String count;
	private String value;
	private String userId;
	private String elph;
	private String phyStatus;
	private String category;
	private String projectno;
	
	public RestDistrictLevelDashboardModel() {
		super();
	}
	
	public RestDistrictLevelDashboardModel(Object count,Object value) {
		super();
		this.count = (String)count;
		this.value = (String)value;
				}

	public RestDistrictLevelDashboardModel(Object projectno,Object notStarted, Object foundationLevel, Object plinthLevel, Object lintelLevel,
			 Object roofLevel,Object elph, Object finishingStage, Object completed) {
		super();
		this.projectno = (String)projectno;
		this.notStarted = (String)notStarted;
		this.foundationLevel = (String)foundationLevel;
		this.plinthLevel = (String)plinthLevel;
		this.lintelLevel = (String)lintelLevel;
		this.roofLevel = (String)roofLevel;
		this.elph = (String)elph;
		this.finishingStage = (String)finishingStage;
		this.completed = (String)completed;
	}



	public String getNotStarted() {
		return notStarted;
	}

	public void setNotStarted(String notStarted) {
		this.notStarted = notStarted;
	}

	public String getFoundationLevel() {
		return foundationLevel;
	}

	public void setFoundationLevel(String foundationLevel) {
		this.foundationLevel = foundationLevel;
	}

	public String getPlinthLevel() {
		return plinthLevel;
	}

	public void setPlinthLevel(String plinthLevel) {
		this.plinthLevel = plinthLevel;
	}

	public String getLintelLevel() {
		return lintelLevel;
	}

	public void setLintelLevel(String lintelLevel) {
		this.lintelLevel = lintelLevel;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getRoofLevel() {
		return roofLevel;
	}

	public void setRoofLevel(String roofLevel) {
		this.roofLevel = roofLevel;
	}

	public String getFinishingStage() {
		return finishingStage;
	}

	public void setFinishingStage(String finishingStage) {
		this.finishingStage = finishingStage;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}



	public String getProjectId() {
		return projectId;
	}



	public void setProjectId(String projectId) {
		this.projectId = projectId;
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



	public String getInstitude() {
		return institude;
	}



	public void setInstitude(String institude) {
		this.institude = institude;
	}



	public String getScheme() {
		return scheme;
	}



	public void setScheme(String scheme) {
		this.scheme = scheme;
	}



	public String getAgency() {
		return agency;
	}



	public void setAgency(String agency) {
		this.agency = agency;
	}



	public String getSanctionYear() {
		return sanctionYear;
	}



	public void setSanctionYear(String sanctionYear) {
		this.sanctionYear = sanctionYear;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public String getApprovedAmt() {
		return approvedAmt;
	}



	public void setApprovedAmt(String approvedAmt) {
		this.approvedAmt = approvedAmt;
	}



	public String getAbyaCost() {
		return abyaCost;
	}



	public void setAbyaCost(String abyaCost) {
		this.abyaCost = abyaCost;
	}



	public String getFloor() {
		return floor;
	}



	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	

	public String getElph() {
		return elph;
	}

	public void setElph(String elph) {
		this.elph = elph;
	}

	
	
	public String getPhyStatus() {
		return phyStatus;
	}

	public void setPhyStatus(String phyStatus) {
		this.phyStatus = phyStatus;
	}
	
	
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProjectno() {
		return projectno;
	}

	public void setProjectno(String projectno) {
		this.projectno = projectno;
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
