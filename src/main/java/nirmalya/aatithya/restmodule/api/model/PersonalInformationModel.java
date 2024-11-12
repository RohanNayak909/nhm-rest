package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonalInformationModel {

	private String firstname;
	private String lastname;
	private String uhidcardno;
	private String bloodgroup;
	private String dob;
	private String age;
	private String gender;
	private String maritalstatus;
	private String occupation;
	private String address1;
	private String address2;
	private String country;
	private String state;
	private String disctrict;
	private String city;
	private String emrgncyname;
	private String emrgncyrelation;
	private String emrgncymob;
	private String famdctrname;
	private String famdctrsepeciality;
	private String famdctrmob;
	private String height;
	private String weight;
	private String bmi;
	private String celcius;
	private String farenheit;
	private String bldpressure;
	private String systolic;
	private String pulse;
	private String respiration;
	private String oxygen;
	private String pin;
	private List<APIAllergyModel> allergies = new ArrayList<APIAllergyModel>();
	private List<APIBioMedicalModel> bioMedical = new ArrayList<APIBioMedicalModel>();

	public PersonalInformationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public PersonalInformationModel(Object firstname, Object lastname, Object uhidcardno, Object bloodgroup, Object dob,
			Object age, Object gender, Object maritalstatus, Object occupation, Object address1, Object address2,
			Object country, Object state, Object disctrict, Object city, Object emrgncyname, Object emrgncyrelation,
			Object emrgncymob, Object famdctrname, Object famdctrsepeciality, Object famdctrmob, Object height,
			Object weight, Object bmi, Object celcius, Object farenheit, Object bldpressure, Object systolic,
			Object pulse, Object respiration, Object oxygen, Object pin, Object allergies, Object bioMedical) {

		super();
		this.firstname = (String) firstname;
		this.lastname = (String) lastname;
		this.uhidcardno = (String) uhidcardno;
		this.bloodgroup = (String) bloodgroup;
		this.dob = (String) dob;
		this.age = (String) age;
		this.gender = (String) gender;
		this.maritalstatus = (String) maritalstatus;
		this.occupation = (String) occupation;
		this.address1 = (String) address1;
		this.address2 = (String) address2;
		this.country = (String) country;
		this.state = (String) state;
		this.disctrict = (String) disctrict;
		this.city = (String) city;
		this.emrgncyname = (String) emrgncyname;
		this.emrgncyrelation = (String) emrgncyrelation;
		this.emrgncymob = (String) emrgncymob;
		this.famdctrname = (String) famdctrname;
		this.famdctrsepeciality = (String) famdctrsepeciality;
		this.famdctrmob = (String) famdctrmob;
		this.height = (String) height;
		this.weight = (String) weight;
		this.bmi = (String) bmi;
		this.celcius = (String) celcius;
		this.farenheit = (String) farenheit;
		this.bldpressure = (String) bldpressure;
		this.systolic = (String) systolic;
		this.pulse = (String) pulse;
		this.respiration = (String) respiration;
		this.oxygen = (String) oxygen;
		this.pin = (String) pin;
		this.allergies = (List<APIAllergyModel>) allergies;
		this.bioMedical = (List<APIBioMedicalModel>) bioMedical;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUhidcardno() {
		return uhidcardno;
	}

	public void setUhidcardno(String uhidcardno) {
		this.uhidcardno = uhidcardno;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDisctrict() {
		return disctrict;
	}

	public void setDisctrict(String disctrict) {
		this.disctrict = disctrict;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmrgncyname() {
		return emrgncyname;
	}

	public void setEmrgncyname(String emrgncyname) {
		this.emrgncyname = emrgncyname;
	}

	public String getEmrgncyrelation() {
		return emrgncyrelation;
	}

	public void setEmrgncyrelation(String emrgncyrelation) {
		this.emrgncyrelation = emrgncyrelation;
	}

	public String getEmrgncymob() {
		return emrgncymob;
	}

	public void setEmrgncymob(String emrgncymob) {
		this.emrgncymob = emrgncymob;
	}

	public String getFamdctrname() {
		return famdctrname;
	}

	public void setFamdctrname(String famdctrname) {
		this.famdctrname = famdctrname;
	}

	public String getFamdctrsepeciality() {
		return famdctrsepeciality;
	}

	public void setFamdctrsepeciality(String famdctrsepeciality) {
		this.famdctrsepeciality = famdctrsepeciality;
	}

	public String getFamdctrmob() {
		return famdctrmob;
	}

	public void setFamdctrmob(String famdctrmob) {
		this.famdctrmob = famdctrmob;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getCelcius() {
		return celcius;
	}

	public void setCelcius(String celcius) {
		this.celcius = celcius;
	}

	public String getFarenheit() {
		return farenheit;
	}

	public void setFarenheit(String farenheit) {
		this.farenheit = farenheit;
	}

	public String getBldpressure() {
		return bldpressure;
	}

	public void setBldpressure(String bldpressure) {
		this.bldpressure = bldpressure;
	}

	public String getSystolic() {
		return systolic;
	}

	public void setSystolic(String systolic) {
		this.systolic = systolic;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getRespiration() {
		return respiration;
	}

	public void setRespiration(String respiration) {
		this.respiration = respiration;
	}

	public String getOxygen() {
		return oxygen;
	}

	public void setOxygen(String oxygen) {
		this.oxygen = oxygen;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public List<APIAllergyModel> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<APIAllergyModel> allergies) {
		this.allergies = allergies;
	}

	public List<APIBioMedicalModel> getBioMedical() {
		return bioMedical;
	}

	public void setBioMedical(List<APIBioMedicalModel> bioMedical) {
		this.bioMedical = bioMedical;
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
