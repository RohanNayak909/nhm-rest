package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAdminDashboardModel {
	private String projectno;
	private String notStarted;
	private String foundationLevel;
	private String plinthLevel;
	private String lintelLevel;
	private String roofLevel;
	private String elph;
	private String finishingStage;
	private String completed;
	private String count;
	private String value;
	private String windowsil;
	private String handedOver;
	private String districtId;
	private String district;
	private String notStartedSum;
	private String foundationLevelSum;
	private String plinthLevelSum;
	private String windowsilSum;
	private String lintelLevelSum;
	private String roofLevelSum;
	private String elphSum;
	private String finishingStageSum;
	private String completedSum;
	private String handedOverSum;
	private String totalProjects;
	private String nhmpip;
	private String pmabhim;
	private String ecrpii;
	private String ombadc;
	private String statebudget;
	private String xvfc;
	private String dmf;
	private String others;
	private String nhmpipsum;
	private String pmabhimsum;
	private String ecrpiisum;
	private String ombadcsum;
	private String statebudgetsum;
	private String xvfcsum;
	private String dmfsum;
	private String otherssum;
	
	
	
	public RestAdminDashboardModel(Object count, Object value) {
		super();
		this.count = (String)count;
		this.value = (String)value;
	}

	public RestAdminDashboardModel(Object projectno,Object notStarted, Object foundationLevel, Object plinthLevel, Object lintelLevel,
			 Object roofLevel,Object elph, Object finishingStage, Object completed,Object count, Object value,
			 Object windowsil, Object handedOver) {
		super();
		this.projectno=(String)projectno;
		this.notStarted = (String)notStarted;
		this.foundationLevel = (String)foundationLevel;
		this.plinthLevel = (String)plinthLevel;
		this.lintelLevel = (String)lintelLevel;
		this.roofLevel = (String)roofLevel;
		this.elph = (String)elph;
		this.finishingStage = (String)finishingStage;
		this.completed = (String)completed;
		this.count = (String)count;
		this.value = (String)value;
		this.windowsil = (String)windowsil;
		this.handedOver = (String)handedOver;
	}
	
	
	
	
	

	public RestAdminDashboardModel( Object districtId,Object district,Object notStarted, Object foundationLevel,
			Object plinthLevel, Object windowsil, Object lintelLevel,
			Object roofLevel, Object elph, Object finishingStage, Object completed,
			Object handedOver, Object notStartedSum, Object foundationLevelSum,
			Object plinthLevelSum, Object windowsilSum, Object lintelLevelSum, Object roofLevelSum, Object elphSum,
			Object finishingStageSum, Object completedSum, Object handedOverSum, Object totalProjects) {
		super();
		this.districtId = (String)districtId;
		this.district = (String)district;
		this.notStarted = (String)notStarted;
		this.foundationLevel = (String)foundationLevel;
		this.plinthLevel = (String)plinthLevel;
		this.windowsil = (String)windowsil;
		this.lintelLevel = (String)lintelLevel;
		this.roofLevel = (String)roofLevel;
		this.elph = (String)elph;
		this.finishingStage = (String)finishingStage;
		this.completed = (String)completed;
		this.handedOver = (String)handedOver;
		this.notStartedSum = (String)notStartedSum;
		this.foundationLevelSum = (String)foundationLevelSum;
		this.plinthLevelSum = (String)plinthLevelSum;
		this.windowsilSum = (String)windowsilSum;
		this.lintelLevelSum = (String)lintelLevelSum;
		this.roofLevelSum = (String)roofLevelSum;
		this.elphSum = (String)elphSum;
		this.finishingStageSum = (String)finishingStageSum;
		this.completedSum = (String)completedSum;
		this.handedOverSum = (String)handedOverSum;
		this.totalProjects = (String)totalProjects;
	}
	
	
	

	public RestAdminDashboardModel(Object districtId, Object district, Object nhmpip,
			Object pmabhim, Object ecrpii, Object ombadc, Object statebudget, Object xvfc, Object dmf, Object others,
			Object nhmpipsum, Object pmabhimsum, Object ecrpiisum, Object ombadcsum, Object statebudgetsum,
			Object xvfcsum, Object dmfsum, Object otherssum, Object totalProjects) {
		super();
		this.districtId = (String)districtId;
		this.district = (String)district;
		this.totalProjects = (String)totalProjects;
		this.nhmpip = (String)nhmpip;
		this.pmabhim = (String)pmabhim;
		this.ecrpii = (String)ecrpii;
		this.ombadc = (String)ombadc;
		this.statebudget = (String)statebudget;
		this.xvfc = (String)xvfc;
		this.dmf = (String)dmf;
		this.others = (String)others;
		this.nhmpipsum = (String)nhmpipsum;
		this.pmabhimsum = (String)pmabhimsum;
		this.ecrpiisum = (String)ecrpiisum;
		this.ombadcsum = (String)ombadcsum;
		this.statebudgetsum = (String)statebudgetsum;
		this.xvfcsum = (String)xvfcsum;
		this.dmfsum = (String)dmfsum;
		this.otherssum = (String)otherssum;
	}

	public RestAdminDashboardModel() {
		super();
	}
	
	public String getProjectno() {
		return projectno;
	}

	public void setProjectno(String projectno) {
		this.projectno = projectno;
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

	public String getRoofLevel() {
		return roofLevel;
	}

	public void setRoofLevel(String roofLevel) {
		this.roofLevel = roofLevel;
	}

	public String getElph() {
		return elph;
	}

	public void setElph(String elph) {
		this.elph = elph;
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
	
	
	
	public String getWindowsil() {
		return windowsil;
	}

	public void setWindowsil(String windowsil) {
		this.windowsil = windowsil;
	}

	public String getHandedOver() {
		return handedOver;
	}

	public void setHandedOver(String handedOver) {
		this.handedOver = handedOver;
	}
	
	

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNotStartedSum() {
		return notStartedSum;
	}

	public void setNotStartedSum(String notStartedSum) {
		this.notStartedSum = notStartedSum;
	}

	public String getFoundationLevelSum() {
		return foundationLevelSum;
	}

	public void setFoundationLevelSum(String foundationLevelSum) {
		this.foundationLevelSum = foundationLevelSum;
	}

	public String getPlinthLevelSum() {
		return plinthLevelSum;
	}

	public void setPlinthLevelSum(String plinthLevelSum) {
		this.plinthLevelSum = plinthLevelSum;
	}

	public String getWindowsilSum() {
		return windowsilSum;
	}

	public void setWindowsilSum(String windowsilSum) {
		this.windowsilSum = windowsilSum;
	}

	public String getLintelLevelSum() {
		return lintelLevelSum;
	}

	public void setLintelLevelSum(String lintelLevelSum) {
		this.lintelLevelSum = lintelLevelSum;
	}

	public String getRoofLevelSum() {
		return roofLevelSum;
	}

	public void setRoofLevelSum(String roofLevelSum) {
		this.roofLevelSum = roofLevelSum;
	}

	public String getElphSum() {
		return elphSum;
	}

	public void setElphSum(String elphSum) {
		this.elphSum = elphSum;
	}

	public String getFinishingStageSum() {
		return finishingStageSum;
	}

	public void setFinishingStageSum(String finishingStageSum) {
		this.finishingStageSum = finishingStageSum;
	}

	public String getCompletedSum() {
		return completedSum;
	}

	public void setCompletedSum(String completedSum) {
		this.completedSum = completedSum;
	}

	public String getHandedOverSum() {
		return handedOverSum;
	}

	public void setHandedOverSum(String handedOverSum) {
		this.handedOverSum = handedOverSum;
	}
	
	

	public String getTotalProjects() {
		return totalProjects;
	}

	public void setTotalProjects(String totalProjects) {
		this.totalProjects = totalProjects;
	}

	
	
	
	public String getNhmpip() {
		return nhmpip;
	}

	public void setNhmpip(String nhmpip) {
		this.nhmpip = nhmpip;
	}

	public String getPmabhim() {
		return pmabhim;
	}

	public void setPmabhim(String pmabhim) {
		this.pmabhim = pmabhim;
	}

	public String getEcrpii() {
		return ecrpii;
	}

	public void setEcrpii(String ecrpii) {
		this.ecrpii = ecrpii;
	}

	public String getOmbadc() {
		return ombadc;
	}

	public void setOmbadc(String ombadc) {
		this.ombadc = ombadc;
	}

	public String getStatebudget() {
		return statebudget;
	}

	public void setStatebudget(String statebudget) {
		this.statebudget = statebudget;
	}

	public String getXvfc() {
		return xvfc;
	}

	public void setXvfc(String xvfc) {
		this.xvfc = xvfc;
	}

	public String getDmf() {
		return dmf;
	}

	public void setDmf(String dmf) {
		this.dmf = dmf;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getNhmpipsum() {
		return nhmpipsum;
	}

	public void setNhmpipsum(String nhmpipsum) {
		this.nhmpipsum = nhmpipsum;
	}

	public String getPmabhimsum() {
		return pmabhimsum;
	}

	public void setPmabhimsum(String pmabhimsum) {
		this.pmabhimsum = pmabhimsum;
	}

	public String getEcrpiisum() {
		return ecrpiisum;
	}

	public void setEcrpiisum(String ecrpiisum) {
		this.ecrpiisum = ecrpiisum;
	}

	public String getOmbadcsum() {
		return ombadcsum;
	}

	public void setOmbadcsum(String ombadcsum) {
		this.ombadcsum = ombadcsum;
	}

	public String getStatebudgetsum() {
		return statebudgetsum;
	}

	public void setStatebudgetsum(String statebudgetsum) {
		this.statebudgetsum = statebudgetsum;
	}

	public String getXvfcsum() {
		return xvfcsum;
	}

	public void setXvfcsum(String xvfcsum) {
		this.xvfcsum = xvfcsum;
	}

	public String getDmfsum() {
		return dmfsum;
	}

	public void setDmfsum(String dmfsum) {
		this.dmfsum = dmfsum;
	}

	public String getOtherssum() {
		return otherssum;
	}

	public void setOtherssum(String otherssum) {
		this.otherssum = otherssum;
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
