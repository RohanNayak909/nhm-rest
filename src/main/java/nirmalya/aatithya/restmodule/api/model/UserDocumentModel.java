package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDocumentModel {
	private String userId;
	private String docId;
	private String docname;
	private String docTypes;
	private String additionalNotes;
	private String extension;
	private String image;
	public UserDocumentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserDocumentModel(Object userId, Object docId, Object docname, Object docTypes, Object image, Object extension,
			Object additionalNotes) {
		super();
		this.userId = (String)userId;
		this.docId = (String)docId;
		this.docname = (String)docname;
		this.docTypes = (String)docTypes;
		this.image = (String)image;
		this.extension = (String)extension;
		this.additionalNotes = (String)additionalNotes;
		
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	public String getDocTypes() {
		return docTypes;
	}
	public void setDocTypes(String docTypes) {
		this.docTypes = docTypes;
	}
	public String getAdditionalNotes() {
		return additionalNotes;
	}
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	

	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getDocId() {
		return docId;
	}


	public void setDocId(String docId) {
		this.docId = docId;
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
