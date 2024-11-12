package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIOrganizationModel {

	private String orgid;
	private String orgname;
	private String licno;
	private String address;
	private String country;
	private String state;
	private String dist;
	private String city;
	private String pincode;
	private String gst;
	private String healthprovider;

	private MultipartFile fileone;
	private MultipartFile filetwo;
	private MultipartFile filethree;
	private MultipartFile filefour;

	private String extone;
	private String exttwo;
	private String extthree;
	private String extfour;

	private String docnameone;
	private String docnametwo;
	private String docnamethree;
	private String docnamefour;

	private String docone;
	private String doctwo;
	private String docthree;
	private String docfour;

	public APIOrganizationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getLicno() {
		return licno;
	}

	public void setLicno(String licno) {
		this.licno = licno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getHealthprovider() {
		return healthprovider;
	}

	public void setHealthprovider(String healthprovider) {
		this.healthprovider = healthprovider;
	}

	public MultipartFile getFileone() {
		return fileone;
	}

	public void setFileone(MultipartFile fileone) {
		this.fileone = fileone;
	}

	public MultipartFile getFiletwo() {
		return filetwo;
	}

	public void setFiletwo(MultipartFile filetwo) {
		this.filetwo = filetwo;
	}

	public MultipartFile getFilethree() {
		return filethree;
	}

	public void setFilethree(MultipartFile filethree) {
		this.filethree = filethree;
	}

	public MultipartFile getFilefour() {
		return filefour;
	}

	public void setFilefour(MultipartFile filefour) {
		this.filefour = filefour;
	}

	public String getExtone() {
		return extone;
	}

	public void setExtone(String extone) {
		this.extone = extone;
	}

	public String getExttwo() {
		return exttwo;
	}

	public void setExttwo(String exttwo) {
		this.exttwo = exttwo;
	}

	public String getExtthree() {
		return extthree;
	}

	public void setExtthree(String extthree) {
		this.extthree = extthree;
	}

	public String getExtfour() {
		return extfour;
	}

	public void setExtfour(String extfour) {
		this.extfour = extfour;
	}

	public String getDocnameone() {
		return docnameone;
	}

	public void setDocnameone(String docnameone) {
		this.docnameone = docnameone;
	}

	public String getDocnametwo() {
		return docnametwo;
	}

	public void setDocnametwo(String docnametwo) {
		this.docnametwo = docnametwo;
	}

	public String getDocnamethree() {
		return docnamethree;
	}

	public void setDocnamethree(String docnamethree) {
		this.docnamethree = docnamethree;
	}

	public String getDocnamefour() {
		return docnamefour;
	}

	public void setDocnamefour(String docnamefour) {
		this.docnamefour = docnamefour;
	}

	public String getDocone() {
		return docone;
	}

	public void setDocone(String docone) {
		this.docone = docone;
	}

	public String getDoctwo() {
		return doctwo;
	}

	public void setDoctwo(String doctwo) {
		this.doctwo = doctwo;
	}

	public String getDocthree() {
		return docthree;
	}

	public void setDocthree(String docthree) {
		this.docthree = docthree;
	}

	public String getDocfour() {
		return docfour;
	}

	public void setDocfour(String docfour) {
		this.docfour = docfour;
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
