package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientFamilyDetailsModel {

	private String memeberName;
	private String relation;
	private String age;
	private String userid;
	private String famid;
	private String relid;
	private String memberid;
	
	public PatientFamilyDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientFamilyDetailsModel( Object memeberName, Object relation, Object age, Object userid, Object famid,
			Object relid) {
		super();
		this.memeberName = (String) memeberName;
		this.relation = (String) relation;
		this.age = (String) age;
		this.userid = (String) userid;
		this.famid = (String) famid;
		this.relid = (String) relid;
	}


	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFamid() {
		return famid;
	}

	public void setFamid(String famid) {
		this.famid = famid;
	}

	public String getRelid() {
		return relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getMemeberName() {
		return memeberName;
	}

	public void setMemeberName(String memeberName) {
		this.memeberName = memeberName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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
