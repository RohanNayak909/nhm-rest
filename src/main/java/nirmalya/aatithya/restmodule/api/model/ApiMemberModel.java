package nirmalya.aatithya.restmodule.api.model;

import java.util.List;

public class ApiMemberModel {
		private String memberNo;
	    private String salutation;
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String gender;
	    private String dateOfBirth;
	    private String relationCode;
	    private String maritalStatus;
	    private String height;
	    private String weight;
	    private String occupation;
	    private String primaryMember;
	    private String exactDiagnosis;
	    private String dateOfDiagnosis;
	    private String lastDateConsultation;
	    private String detailsOfTreatmentGiven;
	    private String doctorName;
	    private String hospitalName;
	    private String phoneNumberHosital;
	    private String nomineeFirstName;
	    private String nomineeLastName;
	    private String nomineeContactNumber;
	    private String nomineeHomeAddress;
	    private String nomineeRelationshipCode;
	    private String sumInsured;
	    private List<ApiMemberProductComponentModel> memberproductComponent;
	    private List<ApiMemberPedModel> memberPED;
	   
	   
		public ApiMemberModel() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getMemberNo() {
			return memberNo;
		}
		public void setMemberNo(String memberNo) {
			this.memberNo = memberNo;
		}
		public String getSalutation() {
			return salutation;
		}
		public void setSalutation(String salutation) {
			this.salutation = salutation;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getRelationCode() {
			return relationCode;
		}
		public void setRelationCode(String relationCode) {
			this.relationCode = relationCode;
		}
		public String getMaritalStatus() {
			return maritalStatus;
		}
		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
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
		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		public String getPrimaryMember() {
			return primaryMember;
		}
		public void setPrimaryMember(String primaryMember) {
			this.primaryMember = primaryMember;
		}
		public String getExactDiagnosis() {
			return exactDiagnosis;
		}
		public void setExactDiagnosis(String exactDiagnosis) {
			this.exactDiagnosis = exactDiagnosis;
		}
		public String getDateOfDiagnosis() {
			return dateOfDiagnosis;
		}
		public void setDateOfDiagnosis(String dateOfDiagnosis) {
			this.dateOfDiagnosis = dateOfDiagnosis;
		}
		public String getLastDateConsultation() {
			return lastDateConsultation;
		}
		public void setLastDateConsultation(String lastDateConsultation) {
			this.lastDateConsultation = lastDateConsultation;
		}
		public String getDetailsOfTreatmentGiven() {
			return detailsOfTreatmentGiven;
		}
		public void setDetailsOfTreatmentGiven(String detailsOfTreatmentGiven) {
			this.detailsOfTreatmentGiven = detailsOfTreatmentGiven;
		}
		public String getDoctorName() {
			return doctorName;
		}
		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}
		public String getHospitalName() {
			return hospitalName;
		}
		public void setHospitalName(String hospitalName) {
			this.hospitalName = hospitalName;
		}
		public String getPhoneNumberHosital() {
			return phoneNumberHosital;
		}
		public void setPhoneNumberHosital(String phoneNumberHosital) {
			this.phoneNumberHosital = phoneNumberHosital;
		}
		public String getNomineeFirstName() {
			return nomineeFirstName;
		}
		public void setNomineeFirstName(String nomineeFirstName) {
			this.nomineeFirstName = nomineeFirstName;
		}
		public String getNomineeLastName() {
			return nomineeLastName;
		}
		public void setNomineeLastName(String nomineeLastName) {
			this.nomineeLastName = nomineeLastName;
		}
		public String getNomineeContactNumber() {
			return nomineeContactNumber;
		}
		public void setNomineeContactNumber(String nomineeContactNumber) {
			this.nomineeContactNumber = nomineeContactNumber;
		}
		public String getNomineeHomeAddress() {
			return nomineeHomeAddress;
		}
		public void setNomineeHomeAddress(String nomineeHomeAddress) {
			this.nomineeHomeAddress = nomineeHomeAddress;
		}
		public String getNomineeRelationshipCode() {
			return nomineeRelationshipCode;
		}
		public void setNomineeRelationshipCode(String nomineeRelationshipCode) {
			this.nomineeRelationshipCode = nomineeRelationshipCode;
		}
		public String getSumInsured() {
			return sumInsured;
		}
		public void setSumInsured(String sumInsured) {
			this.sumInsured = sumInsured;
		}
		public List<ApiMemberProductComponentModel> getMemberproductComponent() {
			return memberproductComponent;
		}
		public void setMemberproductComponent(List<ApiMemberProductComponentModel> memberproductComponent) {
			this.memberproductComponent = memberproductComponent;
		}
		public List<ApiMemberPedModel> getMemberPED() {
			return memberPED;
		}
		public void setMemberPED(List<ApiMemberPedModel> memberPED) {
			this.memberPED = memberPED;
		}
		
}
