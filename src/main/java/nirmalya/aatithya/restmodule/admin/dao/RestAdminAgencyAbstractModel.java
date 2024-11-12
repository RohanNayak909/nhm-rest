package nirmalya.aatithya.restmodule.admin.dao;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAdminAgencyAbstractModel {
	private String districtId;
	private String district;
	private String pr;
	private String pwd;
	private String rd;
	private String zss;
	private String epil;
	private String hscl;
	private String nbcc;
	private String npcc;
	private String ophc;
	private String nhdivision;
	private String othersagency;
	private String prsum;
	private String pwdsum;
	private String rd_sum;
	private String zss_sum;
	private String epil_sum;
	private String hscl_sum;
	private String nbcc_sum;
	private String npcc_sum;
	private String ophc_sum;
	private String nhdivision_sum;
	private String otheragency_sum;
	private String projects;
	
	public RestAdminAgencyAbstractModel(Object districtId, Object district, Object pr, Object pwd, Object rd,
			Object zss, Object epil, Object hscl, Object nbcc, Object npcc, Object ophc, Object nhdivision,
			Object othersagency, Object prsum, Object pwdsum, Object rd_sum, Object zss_sum, Object epil_sum,
			Object hscl_sum, Object nbcc_sum, Object npcc_sum, Object ophc_sum, Object nhdivision_sum,
			Object otheragency_sum, Object projects) {
		super();
		this.districtId = (String)districtId;
		this.district = (String)district;
		this.pr = (String)pr;
		this.pwd = (String)pwd;
		this.rd = (String)rd;
		this.zss = (String)zss;
		this.epil = (String)epil;
		this.hscl = (String)hscl;
		this.nbcc = (String)nbcc;
		this.npcc = (String)npcc;
		this.ophc = (String)ophc;
		this.nhdivision = (String)nhdivision;
		this.othersagency = (String)othersagency;
		this.prsum = (String)prsum;
		this.pwdsum = (String)pwdsum;
		this.rd_sum = (String)rd_sum;
		this.zss_sum = (String)zss_sum;
		this.epil_sum = (String)epil_sum;
		this.hscl_sum = (String)hscl_sum;
		this.nbcc_sum = (String)nbcc_sum;
		this.npcc_sum = (String)npcc_sum;
		this.ophc_sum = (String)ophc_sum;
		this.nhdivision_sum = (String)nhdivision_sum;
		this.otheragency_sum = (String)otheragency_sum;
		this.projects = (String)projects;
	}


	
	
	public RestAdminAgencyAbstractModel() {
		super();
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

	public String getPr() {
		return pr;
	}

	public void setPr(String pr) {
		this.pr = pr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public String getZss() {
		return zss;
	}

	public void setZss(String zss) {
		this.zss = zss;
	}

	public String getEpil() {
		return epil;
	}

	public void setEpil(String epil) {
		this.epil = epil;
	}

	public String getHscl() {
		return hscl;
	}

	public void setHscl(String hscl) {
		this.hscl = hscl;
	}

	public String getNbcc() {
		return nbcc;
	}

	public void setNbcc(String nbcc) {
		this.nbcc = nbcc;
	}

	public String getNpcc() {
		return npcc;
	}

	public void setNpcc(String npcc) {
		this.npcc = npcc;
	}

	public String getOphc() {
		return ophc;
	}

	public void setOphc(String ophc) {
		this.ophc = ophc;
	}

	public String getNhdivision() {
		return nhdivision;
	}

	public void setNhdivision(String nhdivision) {
		this.nhdivision = nhdivision;
	}

	public String getOthersagency() {
		return othersagency;
	}

	public void setOthersagency(String othersagency) {
		this.othersagency = othersagency;
	}

	public String getPrsum() {
		return prsum;
	}

	public void setPrsum(String prsum) {
		this.prsum = prsum;
	}

	public String getPwdsum() {
		return pwdsum;
	}

	public void setPwdsum(String pwdsum) {
		this.pwdsum = pwdsum;
	}

	public String getRd_sum() {
		return rd_sum;
	}

	public void setRd_sum(String rd_sum) {
		this.rd_sum = rd_sum;
	}

	public String getZss_sum() {
		return zss_sum;
	}

	public void setZss_sum(String zss_sum) {
		this.zss_sum = zss_sum;
	}

	public String getEpil_sum() {
		return epil_sum;
	}

	public void setEpil_sum(String epil_sum) {
		this.epil_sum = epil_sum;
	}

	public String getHscl_sum() {
		return hscl_sum;
	}

	public void setHscl_sum(String hscl_sum) {
		this.hscl_sum = hscl_sum;
	}

	public String getNbcc_sum() {
		return nbcc_sum;
	}

	public void setNbcc_sum(String nbcc_sum) {
		this.nbcc_sum = nbcc_sum;
	}

	public String getNpcc_sum() {
		return npcc_sum;
	}

	public void setNpcc_sum(String npcc_sum) {
		this.npcc_sum = npcc_sum;
	}

	public String getOphc_sum() {
		return ophc_sum;
	}

	public void setOphc_sum(String ophc_sum) {
		this.ophc_sum = ophc_sum;
	}

	public String getNhdivision_sum() {
		return nhdivision_sum;
	}

	public void setNhdivision_sum(String nhdivision_sum) {
		this.nhdivision_sum = nhdivision_sum;
	}

	public String getOtheragency_sum() {
		return otheragency_sum;
	}

	public void setOtheragency_sum(String otheragency_sum) {
		this.otheragency_sum = otheragency_sum;
	}

	public String getProjects() {
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
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
