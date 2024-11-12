package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InstamozoApiModel {
	
	private String instaId;
	private String payment_id;
	private String payment_status;
	private String id;
	private String transaction_id;
	private String uniqueid;
	private String userId;  
	private String qutationNo;
	
	public String getInstaId() {
		return instaId;
	}
	public void setInstaId(String instaId) {
		this.instaId = instaId;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	
	
	public String getQutationNo() {
		return qutationNo;
	}
	public void setQutationNo(String qutationNo) {
		this.qutationNo = qutationNo;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public InstamozoApiModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InstamozoApiModel(Object instaId, Object payment_id, Object payment_status, Object id, Object transaction_id,
			Object uniqueid, Object qutationNo,Object userId) {
		super();
		this.instaId =(String) instaId;
		this.payment_id =(String) payment_id;
		this.payment_status =(String) payment_status;
		this.id =(String) id;
		this.transaction_id =(String) transaction_id;
		this.uniqueid =(String) uniqueid;
		this.qutationNo =(String) qutationNo;
		this.userId =(String) userId;
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
