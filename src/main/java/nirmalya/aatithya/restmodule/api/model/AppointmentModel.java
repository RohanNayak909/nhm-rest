package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AppointmentModel {

	private Integer id;
	private String regNo;
	private String patientName;
	private Integer age;
	private String gender;
	private String mob;
	private String appntmntDate;
	private String appntmntTime;
	private Integer appointStatus;
	private String appntmntStatus;
	private Integer slNo;

	private String countryId;
	private String countryName;
	private String stateId;
	private String stateName;
	private String districtId;
	private String districtName;
	private String cityId;
	private String cityName;
	private String countryCode;
	private String stateCode;

	public AppointmentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppointmentModel(Object id, Object regNo, Object patientName, Object age, Object gender, Object mob,
			Object appntmntDate, Object appntmntTime, Object appointStatus, Object appntmntStatus, Object slNo) {
		super();
		this.id = (Integer) id;
		this.regNo = (String) regNo;
		this.patientName = (String) patientName;
		this.age = (Integer) age;
		this.gender = (String) gender;
		this.mob = (String) mob;
		this.appntmntDate = (String) appntmntDate;
		this.appntmntTime = (String) appntmntTime;
		this.appointStatus = (Integer) appointStatus;
		this.appntmntStatus = (String) appntmntStatus;
		this.slNo = (Integer) slNo;
	}

	public AppointmentModel(Object countryId,Object stateId,Object districtId,Object cityId, Object countryName,  Object stateName, 
			Object districtName,  Object cityName,Object countryCode,Object stateCode) {
		super();
		this.countryId = (String) countryId;
		this.countryName = (String) countryName;
		this.stateId = (String) stateId;
		this.stateName = (String) stateName;
		this.districtId = (String) districtId;
		this.districtName = (String) districtName;
		this.cityId = (String) cityId;
		this.cityName = (String) cityName;
		this.countryCode = (String) countryCode;
		this.stateCode = (String) stateCode;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getAppntmntDate() {
		return appntmntDate;
	}

	public void setAppntmntDate(String appntmntDate) {
		this.appntmntDate = appntmntDate;
	}

	public String getAppntmntTime() {
		return appntmntTime;
	}

	public void setAppntmntTime(String appntmntTime) {
		this.appntmntTime = appntmntTime;
	}

	public Integer getAppointStatus() {
		return appointStatus;
	}

	public void setAppointStatus(Integer appointStatus) {
		this.appointStatus = appointStatus;
	}

	public String getAppntmntStatus() {
		return appntmntStatus;
	}

	public void setAppntmntStatus(String appntmntStatus) {
		this.appntmntStatus = appntmntStatus;
	}

	public Integer getSlNo() {
		return slNo;
	}

	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
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
