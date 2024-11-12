/**
 * Defines menu dropdown class
 *
 */
package nirmalya.aatithya.restmodule.common.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Nirmalya Labs
 *
 */
public class DropDownModel {
	private String key;
	private String name;
	private String code;
	private String image;
	private String language;
	private String pass;
	
	public DropDownModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DropDownModel(Object name) {
		super();
		
		try {
			this.name = (String) name;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DropDownModel(Object key, Object name) {
		super();
		try {
			this.key = (String) key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.name = (String) name;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public DropDownModel(Object key, Object name, Object code) {
		super();
		try {
			this.key = (String) key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.name = (String) name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.code = (String) code;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DropDownModel(Object key, Object name, Object code, Object image,Object language) {
		super();
		try {
			this.key = (String) key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.name = (String) name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.code = (String) code;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.image = (String) image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			this.language = (String) language;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	

	public DropDownModel(Object key, Object name, Object code, Object pass) {
		super();
		this.key = (String)key;
		this.name = (String)name;
		this.code = (String)code;
		this.pass = (String)pass;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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
