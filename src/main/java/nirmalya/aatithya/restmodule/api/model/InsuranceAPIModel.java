package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InsuranceAPIModel {
	private String patientId;
	private String patientName;
	private String insCompany;
	private String insType;
	private String healthInsType;
	private String policyNo;
	private String policyStartDt;
	private String policyEndDt;
	private String totalInsAmount;
	private String premiumDueDt;
	private String thirdPartyAdm;
	private String premiumAmount;
	private String sumAssuredAmt;

	private String strtMonthYear;
	private String strtDay;

	private String endMonthYear;
	private String endDay;

	private MultipartFile img1;
	private MultipartFile img2;
	private MultipartFile img3;
	private MultipartFile img4;
	
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;

	private String fileName1;
	private String fileName2;
	private String fileName3;
	private String fileName4;

	private String docName1;
	private String docName2;
	private String docName3;
	private String docName4;

	public InsuranceAPIModel(Object insCompany, Object insType, Object policyNo, Object policyStartDt,
			Object policyEndDt, Object totalInsAmount, Object thirdPartyAdm, Object sumAssuredAmt, Object strtMonthYear,
			Object strtDay, Object endMonthYear, Object endDay, Object healthInsType, Object premiumDueDt,
			Object premiumAmount) {
		super();
		this.insCompany = (String) insCompany;
		this.insType = (String) insType;
		this.policyNo = (String) policyNo;
		this.policyStartDt = (String) policyStartDt;
		this.policyEndDt = (String) policyEndDt;
		this.totalInsAmount = (String) totalInsAmount;
		this.thirdPartyAdm = (String) thirdPartyAdm;
		this.sumAssuredAmt = (String) sumAssuredAmt;
		this.strtMonthYear = (String) strtMonthYear;
		this.strtDay = (String) strtDay;
		this.endMonthYear = (String) endMonthYear;
		this.endDay = (String) endDay;
		this.healthInsType = (String) healthInsType;
		this.premiumDueDt = (String) premiumDueDt;
		this.premiumAmount = (String) premiumAmount;
	}

	public String getDocName1() {
		return docName1;
	}

	public void setDocName1(String docName1) {
		this.docName1 = docName1;
	}

	public String getDocName2() {
		return docName2;
	}

	public void setDocName2(String docName2) {
		this.docName2 = docName2;
	}

	public String getDocName3() {
		return docName3;
	}

	public void setDocName3(String docName3) {
		this.docName3 = docName3;
	}

	public String getDocName4() {
		return docName4;
	}

	public void setDocName4(String docName4) {
		this.docName4 = docName4;
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public String getFileName3() {
		return fileName3;
	}

	public void setFileName3(String fileName3) {
		this.fileName3 = fileName3;
	}

	public String getFileName4() {
		return fileName4;
	}

	public void setFileName4(String fileName4) {
		this.fileName4 = fileName4;
	}

	public MultipartFile getImg1() {
		return img1;
	}

	public void setImg1(MultipartFile img1) {
		this.img1 = img1;
	}

	public MultipartFile getImg2() {
		return img2;
	}

	public void setImg2(MultipartFile img2) {
		this.img2 = img2;
	}

	public MultipartFile getImg3() {
		return img3;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	public void setImg3(MultipartFile img3) {
		this.img3 = img3;
	}

	public MultipartFile getImg4() {
		return img4;
	}

	public void setImg4(MultipartFile img4) {
		this.img4 = img4;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getInsCompany() {
		return insCompany;
	}

	public void setInsCompany(String insCompany) {
		this.insCompany = insCompany;
	}

	public String getInsType() {
		return insType;
	}

	public void setInsType(String insType) {
		this.insType = insType;
	}

	public String getHealthInsType() {
		return healthInsType;
	}

	public void setHealthInsType(String healthInsType) {
		this.healthInsType = healthInsType;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolicyStartDt() {
		return policyStartDt;
	}

	public void setPolicyStartDt(String policyStartDt) {
		this.policyStartDt = policyStartDt;
	}

	public String getPolicyEndDt() {
		return policyEndDt;
	}

	public void setPolicyEndDt(String policyEndDt) {
		this.policyEndDt = policyEndDt;
	}

	public String getTotalInsAmount() {
		return totalInsAmount;
	}

	public void setTotalInsAmount(String totalInsAmount) {
		this.totalInsAmount = totalInsAmount;
	}

	public String getPremiumDueDt() {
		return premiumDueDt;
	}

	public void setPremiumDueDt(String premiumDueDt) {
		this.premiumDueDt = premiumDueDt;
	}

	public String getThirdPartyAdm() {
		return thirdPartyAdm;
	}

	public void setThirdPartyAdm(String thirdPartyAdm) {
		this.thirdPartyAdm = thirdPartyAdm;
	}

	public String getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(String premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getSumAssuredAmt() {
		return sumAssuredAmt;
	}

	public void setSumAssuredAmt(String sumAssuredAmt) {
		this.sumAssuredAmt = sumAssuredAmt;
	}

	public String getStrtMonthYear() {
		return strtMonthYear;
	}

	public void setStrtMonthYear(String strtMonthYear) {
		this.strtMonthYear = strtMonthYear;
	}

	public String getStrtDay() {
		return strtDay;
	}

	public void setStrtDay(String strtDay) {
		this.strtDay = strtDay;
	}

	public String getEndMonthYear() {
		return endMonthYear;
	}

	public void setEndMonthYear(String endMonthYear) {
		this.endMonthYear = endMonthYear;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public InsuranceAPIModel() {
		super();
		// TODO Auto-generated constructor stub
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
