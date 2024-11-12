package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class APIOrganDonationModel {

	private String donorName;
	private String donorType;
	private String typeUserName;
	private String dob;
	private String age;
	private String bldGr;
	private String mob;
	private String email;
	private String address;
	private String patientId;
	private String relation;
	private List<String> organList;
	private List<String> tissueList;
	private List<APIOrganDonationModel> witnessList;
	private List<DropDownModel> donatedOrganList;
	
	private String donorid;
	private String createddate;

	public APIOrganDonationModel() {
		super();
	}

	public APIOrganDonationModel(Object donorName, Object donorType, Object typeUserName, Object bldGr,
			Object patientId, Object donorid, Object createddate) {
		super();
		this.donorName = (String) donorName;
		this.donorType = (String) donorType;
		this.typeUserName = (String) typeUserName;
		this.bldGr = (String) bldGr;
		this.patientId = (String) patientId;
		this.donorid = (String) donorid;
		this.createddate = (String) createddate;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getDonorType() {
		return donorType;
	}

	public void setDonorType(String donorType) {
		this.donorType = donorType;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getTypeUserName() {
		return typeUserName;
	}

	public void setTypeUserName(String typeUserName) {
		this.typeUserName = typeUserName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBldGr() {
		return bldGr;
	}

	public void setBldGr(String bldGr) {
		this.bldGr = bldGr;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getOrganList() {
		return organList;
	}

	public void setOrganList(List<String> organList) {
		this.organList = organList;
	}

	public List<String> getTissueList() {
		return tissueList;
	}

	public void setTissueList(List<String> tissueList) {
		this.tissueList = tissueList;
	}

	public List<APIOrganDonationModel> getWitnessList() {
		return witnessList;
	}

	public void setWitnessList(List<APIOrganDonationModel> witnessList) {
		this.witnessList = witnessList;
	}

	public List<DropDownModel> getDonatedOrganList() {
		return donatedOrganList;
	}

	public void setDonatedOrganList(List<DropDownModel> donatedOrganList) {
		this.donatedOrganList = donatedOrganList;
	}

	public String getDonorid() {
		return donorid;
	}

	public void setDonorid(String donorid) {
		this.donorid = donorid;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
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
