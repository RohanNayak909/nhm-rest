package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
public class VleTestAPIModel {

	private String vleId;
	private String patientId;
	private String testId;
	private String testName;
	private String status;
	private String venderId;
	private String venderName;
	
	private String patientName;
	private String gender;
	private String regDate;
	private String enterdby;
	private String mob;
	private String date;
	private String age;
	
	private String type;
	
	private List<RestVenderApiTestModel> getvendertestLists;
	
	
	public VleTestAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VleTestAPIModel(Object vleId, Object patientId, Object testId, Object testName, Object status,
			Object venderId, Object venderName,Object type) {
		super();
		this.vleId = (String) vleId;
		this.patientId = (String) patientId;
		this.testId = (String) testId;
		this.testName = (String) testName;
		this.status = (String) status;
		this.venderId = (String) venderId;
		this.venderName = (String) venderName;
		this.type = (String) type;
	}
	
	
	public VleTestAPIModel(Object vleId, Object patientId,Object patientName, Object gender,
			Object regDate, Object enterdby, Object mob,Object date,Object age) {
		super();
		this.vleId = (String) vleId;
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.gender = (String) gender;
		this.regDate = (String) regDate;
		this.enterdby = (String) enterdby;
		this.mob = (String) mob;
		this.date = (String) date;
		this.age = (String) age;
	}


	public String getVleId() {
		return vleId;
	}
	public void setVleId(String vleId) {
		this.vleId = vleId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public List<RestVenderApiTestModel> getGetvendertestLists() {
		return getvendertestLists;
	}


	public void setGetvendertestLists(List<RestVenderApiTestModel> getvendertestLists) {
		this.getvendertestLists = getvendertestLists;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	public String getEnterdby() {
		return enterdby;
	}


	public void setEnterdby(String enterdby) {
		this.enterdby = enterdby;
	}


	public String getMob() {
		return mob;
	}


	public void setMob(String mob) {
		this.mob = mob;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
