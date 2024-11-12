package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeliveryProcessAPIModel {
//medicine details
	private String medname;
	private String unitprice;
	private String drAssignedQuantity;
	private String amount;
	private String stripquantity;
	private String medids;
	private String orgid;
	private String patientid;
	private String stripprice;
	private String discount;
	private String orderQuantity;
	private String stock;

	public DeliveryProcessAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryProcessAPIModel(Object medids, Object medname, Object stripquantity, Object stripprice,
			Object discount, Object drAssignedQuantity,Object stock,  Object amount) {
		super();
		this.medids = (String) medids;
		this.medname = (String) medname;
		this.stripquantity = (String) stripquantity;
		this.stripprice = (String) stripprice;
		this.discount = (String) discount;
		this.drAssignedQuantity = (String) drAssignedQuantity;
		this.stock = (String) stock;
		this.amount = (String) amount;
	}


	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getMedname() {
		return medname;
	}

	public void setMedname(String medname) {
		this.medname = medname;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getDrAssignedQuantity() {
		return drAssignedQuantity;
	}

	public void setDrAssignedQuantity(String drAssignedQuantity) {
		this.drAssignedQuantity = drAssignedQuantity;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStripquantity() {
		return stripquantity;
	}

	public void setStripquantity(String stripquantity) {
		this.stripquantity = stripquantity;
	}

	public String getMedids() {
		return medids;
	}

	public void setMedids(String medids) {
		this.medids = medids;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getStripprice() {
		return stripprice;
	}

	public void setStripprice(String stripprice) {
		this.stripprice = stripprice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	//test details
	private String testpackageid;
	private String testpackagename;
	private String testid;
	private String testname;
	private String testpackageprice;
	private String packagediscount;
	private String testprice;
	private String testdiscount;
	
	public DeliveryProcessAPIModel(Object testpackageid, Object testpackagename, Object testid, Object testname, Object testpackageprice,
			Object packagediscount, Object testprice, Object testdiscount, Object object9) {
		this.testpackageid = (String) testpackageid;
		this.testpackagename = (String) testpackagename;
		this.testid = (String) testid;
		this.testname = (String) testname;
		this.testpackageprice = (String) testpackageprice;
		this.packagediscount = (String) packagediscount;
		this.testprice = (String) testprice;
		this.testdiscount = (String) testdiscount;
	}
	public String getTestpackageid() {
		return testpackageid;
	}

	public void setTestpackageid(String testpackageid) {
		this.testpackageid = testpackageid;
	}

	public String getTestpackagename() {
		return testpackagename;
	}

	public void setTestpackagename(String testpackagename) {
		this.testpackagename = testpackagename;
	}

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public String getTestpackageprice() {
		return testpackageprice;
	}

	public void setTestpackageprice(String testpackageprice) {
		this.testpackageprice = testpackageprice;
	}

	public String getPackagediscount() {
		return packagediscount;
	}

	public void setPackagediscount(String packagediscount) {
		this.packagediscount = packagediscount;
	}

	public String getTestprice() {
		return testprice;
	}

	public void setTestprice(String testprice) {
		this.testprice = testprice;
	}

	public String getTestdiscount() {
		return testdiscount;
	}

	public void setTestdiscount(String testdiscount) {
		this.testdiscount = testdiscount;
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
