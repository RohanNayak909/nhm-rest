
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
public class DropDownModelEmp {
	private String key;

	private String name;
	private String email;

	public DropDownModelEmp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DropDownModelEmp(Object key, Object name,Object email) {
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
			this.email = (String) email;
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
