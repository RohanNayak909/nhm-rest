package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiMobileVersionModel {
	private String id;
	private String androidVerName;
	private String androidVerCode;
	private String iosVerName;
	private String iosVerCode;
	private String date;
	
	public ApiMobileVersionModel() {
		super();
	}
	
	


	public ApiMobileVersionModel(Object id, Object androidVerName, Object androidVerCode, Object iosVerName,
			Object iosVerCode,Object date) {
		super();
		this.id = (String)id;
		this.androidVerName = (String)androidVerName;
		this.androidVerCode = (String)androidVerCode;
		this.iosVerName = (String)iosVerName;
		this.iosVerCode = (String)iosVerCode;
		this.date = (String)date;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getAndroidVerName() {
		return androidVerName;
	}




	public void setAndroidVerName(String androidVerName) {
		this.androidVerName = androidVerName;
	}




	public String getAndroidVerCode() {
		return androidVerCode;
	}




	public void setAndroidVerCode(String androidVerCode) {
		this.androidVerCode = androidVerCode;
	}




	public String getIosVerName() {
		return iosVerName;
	}




	public void setIosVerName(String iosVerName) {
		this.iosVerName = iosVerName;
	}




	public String getIosVerCode() {
		return iosVerCode;
	}




	public void setIosVerCode(String iosVerCode) {
		this.iosVerCode = iosVerCode;
	}




	public String getDate() {
		return date;
	}




	public void setDate(String date) {
		this.date = date;
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
