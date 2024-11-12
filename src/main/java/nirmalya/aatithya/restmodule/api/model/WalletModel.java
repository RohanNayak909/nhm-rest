package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class WalletModel {
	private String walletId;
	private String appointId;
	private String userId;
	private String totalprice;
	private String reason;
	private String cureezCash;
	private String cureezPoint;
	private List<WalletListApiModel> walletDesc;
	public WalletModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WalletModel(Object walletId, Object appointId, Object userId, Object totalprice, Object cureezCash,Object cureezPoint) {
		super();
		this.walletId = (String)walletId;
		this.appointId = (String)appointId;
		this.userId = (String)userId;
		this.totalprice = (String)totalprice;
		this.cureezCash = (String)cureezCash;
		this.cureezPoint = (String)cureezPoint;
	}
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public String getAppointId() {
		return appointId;
	}
	public void setAppointId(String appointId) {
		this.appointId = appointId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	

	public List<WalletListApiModel> getWalletDesc() {
		return walletDesc;
	}
	public void setWalletDesc(List<WalletListApiModel> walletDesc) {
		this.walletDesc = walletDesc;
	}
	
	public String getCureezCash() {
		return cureezCash;
	}
	public void setCureezCash(String cureezCash) {
		this.cureezCash = cureezCash;
	}
	public String getCureezPoint() {
		return cureezPoint;
	}
	public void setCureezPoint(String cureezPoint) {
		this.cureezPoint = cureezPoint;
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
