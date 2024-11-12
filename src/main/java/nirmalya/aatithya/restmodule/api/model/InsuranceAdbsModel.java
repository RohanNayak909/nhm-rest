package nirmalya.aatithya.restmodule.api.model;

import java.util.ArrayList;
import java.util.List;

public class InsuranceAdbsModel {
	private String member_Customer_ID;
	private String salutation;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateofBirth;
	private String genderId;
	private String gender;
	private String educationalQualification;
	private String pinCode;
	private String uidNo;
	private String maritalStatus;
	private String nationality;
	private String occupationId;
	private String occupation;
	private String primaryEmailID;
	private String contactMobileNo;
	private String stdLandlineNo;
	private String panNo;
	private String passportNumber;
	private String contactPerson;
	private String annualIncome;

	private String remarks;
	private String startDate;
	private String endDate;
	private String IdProof;
	private String residenceProof;

	private String ageProof;
	private String others;

	private String homeAddressLine1;
    private String homeAddressLine2;
	private String homeAddressLine3;
	private String homePinCode;
	private String homeArea;
    private String homeContactMobileNo;
	private String homeContactMobileNo2;
	private String homeSTDLandlineNo;
	private String homeFaxNo;
    private String sameAsHomeAddress;

	private String mailingAddressLine1;
	private String mailingAddressLine2;
	private String mailingAddressLine3;
	private String mailingPinCode;
    private String mailingArea;
    
    private String mailingContactMobileNo;
    private String mailingContactMobileNo2;
	private String mailingSTDLandlineNo;
	private String mailingSTDLandlineNo2;
	private String mailingFaxNo;
    private String bankAccountType;
	private String bankAccountNo;
	private String ifscCode;
	private String gSTIN;
    private String gSTRegistrationStatus;

	private String isEIAavailable;
	private String applyEIA;

	private String eIAAccountNo;
    private String eIAWith;
    
    private String accountType;
	private String addressProof;

	private String dOBProof;
    private String identityProof;
    
    
    private String quotationNumber;
    private String msterPolicyNumber;
    private String imdCode;
    private String productCode;
    private String imdBranchCode;
    private String smCode;
    private String smName;
    private String groupId;
    private String product_Code;
    private String intermediaryCode;
    private String autoRenewal ;
    private String intermediaryBranchCode;
    private String agentSignatureDate ;
    private String customer_Signature_Date ;
    private String businessSourceChannel ;
    private String assignPolicy;
    private String assigneeName ;
    private String leadID;
    private String source_Name;
    private String sPID ;
    private String tCN ;
    private String cRTNO ;
    private String refCode1 ;
    private String refCode2 ;
    private String employee_Number ;
    private String enumIsEmployeeDiscount ;
    private String quoteDate ;
    private String isPayment;
    private String paymentMode ;
    private List<ApiPolicyProductComponents> policyproductComponents;
    private List<ApiMemberModel> member;
    private String receiptCreation;
    
    
    
   
	public String getMember_Customer_ID() {
		return member_Customer_ID;
	}
	public void setMember_Customer_ID(String member_Customer_ID) {
		this.member_Customer_ID = member_Customer_ID;
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
	
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	public String getGenderId() {
		return genderId;
	}
	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEducationalQualification() {
		return educationalQualification;
	}
	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getUidNo() {
		return uidNo;
	}
	public void setUidNo(String uidNo) {
		this.uidNo = uidNo;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getPrimaryEmailID() {
		return primaryEmailID;
	}
	public void setPrimaryEmailID(String primaryEmailID) {
		this.primaryEmailID = primaryEmailID;
	}
	public String getContactMobileNo() {
		return contactMobileNo;
	}
	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}
	public String getStdLandlineNo() {
		return stdLandlineNo;
	}
	public void setStdLandlineNo(String stdLandlineNo) {
		this.stdLandlineNo = stdLandlineNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getIdProof() {
		return IdProof;
	}
	public void setIdProof(String idProof) {
		IdProof = idProof;
	}
	public String getResidenceProof() {
		return residenceProof;
	}
	public void setResidenceProof(String residenceProof) {
		this.residenceProof = residenceProof;
	}
	public String getAgeProof() {
		return ageProof;
	}
	public void setAgeProof(String ageProof) {
		this.ageProof = ageProof;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getHomeAddressLine1() {
		return homeAddressLine1;
	}
	public void setHomeAddressLine1(String homeAddressLine1) {
		this.homeAddressLine1 = homeAddressLine1;
	}
	public String getHomeAddressLine2() {
		return homeAddressLine2;
	}
	public void setHomeAddressLine2(String homeAddressLine2) {
		this.homeAddressLine2 = homeAddressLine2;
	}
	public String getHomeAddressLine3() {
		return homeAddressLine3;
	}
	public void setHomeAddressLine3(String homeAddressLine3) {
		this.homeAddressLine3 = homeAddressLine3;
	}
	public String getHomePinCode() {
		return homePinCode;
	}
	public void setHomePinCode(String homePinCode) {
		this.homePinCode = homePinCode;
	}
	public String getHomeArea() {
		return homeArea;
	}
	public void setHomeArea(String homeArea) {
		this.homeArea = homeArea;
	}
	public String getHomeContactMobileNo() {
		return homeContactMobileNo;
	}
	public void setHomeContactMobileNo(String homeContactMobileNo) {
		this.homeContactMobileNo = homeContactMobileNo;
	}
	public String getHomeContactMobileNo2() {
		return homeContactMobileNo2;
	}
	public void setHomeContactMobileNo2(String homeContactMobileNo2) {
		this.homeContactMobileNo2 = homeContactMobileNo2;
	}
	public String getHomeSTDLandlineNo() {
		return homeSTDLandlineNo;
	}
	public void setHomeSTDLandlineNo(String homeSTDLandlineNo) {
		this.homeSTDLandlineNo = homeSTDLandlineNo;
	}
	public String getHomeFaxNo() {
		return homeFaxNo;
	}
	public void setHomeFaxNo(String homeFaxNo) {
		this.homeFaxNo = homeFaxNo;
	}
	public String getSameAsHomeAddress() {
		return sameAsHomeAddress;
	}
	public void setSameAsHomeAddress(String sameAsHomeAddress) {
		this.sameAsHomeAddress = sameAsHomeAddress;
	}
	public String getMailingAddressLine1() {
		return mailingAddressLine1;
	}
	public void setMailingAddressLine1(String mailingAddressLine1) {
		this.mailingAddressLine1 = mailingAddressLine1;
	}
	public String getMailingAddressLine2() {
		return mailingAddressLine2;
	}
	public void setMailingAddressLine2(String mailingAddressLine2) {
		this.mailingAddressLine2 = mailingAddressLine2;
	}
	public String getMailingPinCode() {
		return mailingPinCode;
	}
	public void setMailingPinCode(String mailingPinCode) {
		this.mailingPinCode = mailingPinCode;
	}
	public String getMailingArea() {
		return mailingArea;
	}
	public void setMailingArea(String mailingArea) {
		this.mailingArea = mailingArea;
	}
	public String getMailingContactMobileNo() {
		return mailingContactMobileNo;
	}
	public void setMailingContactMobileNo(String mailingContactMobileNo) {
		this.mailingContactMobileNo = mailingContactMobileNo;
	}
	public String getMailingContactMobileNo2() {
		return mailingContactMobileNo2;
	}
	public void setMailingContactMobileNo2(String mailingContactMobileNo2) {
		this.mailingContactMobileNo2 = mailingContactMobileNo2;
	}
	public String getMailingSTDLandlineNo() {
		return mailingSTDLandlineNo;
	}
	public void setMailingSTDLandlineNo(String mailingSTDLandlineNo) {
		this.mailingSTDLandlineNo = mailingSTDLandlineNo;
	}
	public String getMailingSTDLandlineNo2() {
		return mailingSTDLandlineNo2;
	}
	public void setMailingSTDLandlineNo2(String mailingSTDLandlineNo2) {
		this.mailingSTDLandlineNo2 = mailingSTDLandlineNo2;
	}
	public String getMailingFaxNo() {
		return mailingFaxNo;
	}
	public void setMailingFaxNo(String mailingFaxNo) {
		this.mailingFaxNo = mailingFaxNo;
	}
	public String getBankAccountType() {
		return bankAccountType;
	}
	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getgSTIN() {
		return gSTIN;
	}
	public void setgSTIN(String gSTIN) {
		this.gSTIN = gSTIN;
	}
	public String getgSTRegistrationStatus() {
		return gSTRegistrationStatus;
	}
	public void setgSTRegistrationStatus(String gSTRegistrationStatus) {
		this.gSTRegistrationStatus = gSTRegistrationStatus;
	}
	public String getIsEIAavailable() {
		return isEIAavailable;
	}
	public void setIsEIAavailable(String isEIAavailable) {
		this.isEIAavailable = isEIAavailable;
	}
	public String getApplyEIA() {
		return applyEIA;
	}
	public void setApplyEIA(String applyEIA) {
		this.applyEIA = applyEIA;
	}
	public String geteIAAccountNo() {
		return eIAAccountNo;
	}
	public void seteIAAccountNo(String eIAAccountNo) {
		this.eIAAccountNo = eIAAccountNo;
	}
	public String geteIAWith() {
		return eIAWith;
	}
	public void seteIAWith(String eIAWith) {
		this.eIAWith = eIAWith;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAddressProof() {
		return addressProof;
	}
	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}
	public String getdOBProof() {
		return dOBProof;
	}
	public void setdOBProof(String dOBProof) {
		this.dOBProof = dOBProof;
	}
	public String getIdentityProof() {
		return identityProof;
	}
	public void setIdentityProof(String identityProof) {
		this.identityProof = identityProof;
	}
	public String getMailingAddressLine3() {
		return mailingAddressLine3;
	}
	public void setMailingAddressLine3(String mailingAddressLine3) {
		this.mailingAddressLine3 = mailingAddressLine3;
	}
	public String getQuotationNumber() {
		return quotationNumber;
	}
	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}
	public String getMsterPolicyNumber() {
		return msterPolicyNumber;
	}
	public void setMsterPolicyNumber(String msterPolicyNumber) {
		this.msterPolicyNumber = msterPolicyNumber;
	}
	public String getImdCode() {
		return imdCode;
	}
	public void setImdCode(String imdCode) {
		this.imdCode = imdCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getImdBranchCode() {
		return imdBranchCode;
	}
	public void setImdBranchCode(String imdBranchCode) {
		this.imdBranchCode = imdBranchCode;
	}
	public String getSmCode() {
		return smCode;
	}
	public void setSmCode(String smCode) {
		this.smCode = smCode;
	}
	public String getSmName() {
		return smName;
	}
	public void setSmName(String smName) {
		this.smName = smName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getProduct_Code() {
		return product_Code;
	}
	public void setProduct_Code(String product_Code) {
		this.product_Code = product_Code;
	}
	public String getIntermediaryCode() {
		return intermediaryCode;
	}
	public void setIntermediaryCode(String intermediaryCode) {
		this.intermediaryCode = intermediaryCode;
	}
	public String getAutoRenewal() {
		return autoRenewal;
	}
	public void setAutoRenewal(String autoRenewal) {
		this.autoRenewal = autoRenewal;
	}
	public String getIntermediaryBranchCode() {
		return intermediaryBranchCode;
	}
	public void setIntermediaryBranchCode(String intermediaryBranchCode) {
		this.intermediaryBranchCode = intermediaryBranchCode;
	}
	public String getAgentSignatureDate() {
		return agentSignatureDate;
	}
	public void setAgentSignatureDate(String agentSignatureDate) {
		this.agentSignatureDate = agentSignatureDate;
	}
	public String getCustomer_Signature_Date() {
		return customer_Signature_Date;
	}
	public void setCustomer_Signature_Date(String customer_Signature_Date) {
		this.customer_Signature_Date = customer_Signature_Date;
	}
	public String getBusinessSourceChannel() {
		return businessSourceChannel;
	}
	public void setBusinessSourceChannel(String businessSourceChannel) {
		this.businessSourceChannel = businessSourceChannel;
	}
	public String getAssignPolicy() {
		return assignPolicy;
	}
	public void setAssignPolicy(String assignPolicy) {
		this.assignPolicy = assignPolicy;
	}
	public String getAssigneeName() {
		return assigneeName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	public String getLeadID() {
		return leadID;
	}
	public void setLeadID(String leadID) {
		this.leadID = leadID;
	}
	public String getSource_Name() {
		return source_Name;
	}
	public void setSource_Name(String source_Name) {
		this.source_Name = source_Name;
	}
	public String getsPID() {
		return sPID;
	}
	public void setsPID(String sPID) {
		this.sPID = sPID;
	}
	public String gettCN() {
		return tCN;
	}
	public void settCN(String tCN) {
		this.tCN = tCN;
	}
	public String getcRTNO() {
		return cRTNO;
	}
	public void setcRTNO(String cRTNO) {
		this.cRTNO = cRTNO;
	}
	public String getRefCode1() {
		return refCode1;
	}
	public void setRefCode1(String refCode1) {
		this.refCode1 = refCode1;
	}
	public String getRefCode2() {
		return refCode2;
	}
	public void setRefCode2(String refCode2) {
		this.refCode2 = refCode2;
	}
	public String getEmployee_Number() {
		return employee_Number;
	}
	public void setEmployee_Number(String employee_Number) {
		this.employee_Number = employee_Number;
	}
	public String getEnumIsEmployeeDiscount() {
		return enumIsEmployeeDiscount;
	}
	public void setEnumIsEmployeeDiscount(String enumIsEmployeeDiscount) {
		this.enumIsEmployeeDiscount = enumIsEmployeeDiscount;
	}
	public String getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(String quoteDate) {
		this.quoteDate = quoteDate;
	}
	public String getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(String isPayment) {
		this.isPayment = isPayment;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public List<ApiPolicyProductComponents> getPolicyproductComponents() {
		return policyproductComponents;
	}
	public void setPolicyproductComponents(List<ApiPolicyProductComponents> policyproductComponents) {
		this.policyproductComponents = policyproductComponents;
	}
	public List<ApiMemberModel> getMember() {
		return member;
	}
	public void setMember(List<ApiMemberModel> member) {
		this.member = member;
	}
	public String getReceiptCreation() {
		return receiptCreation;
	}
	public void setReceiptCreation(String receiptCreation) {
		this.receiptCreation = receiptCreation;
	}
 


}
