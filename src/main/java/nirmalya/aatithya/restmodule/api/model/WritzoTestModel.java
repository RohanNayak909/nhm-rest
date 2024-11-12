package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.util.Util;

public class WritzoTestModel {

	private String patientId;
	private String fileName;
	private String filePath;
	private String screeningDate;
//	private String screeningDetails;
	private List<FileModel> fileDetails;
	private List<FileModel> screeningDetails;

	public WritzoTestModel() {
		super();
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getScreeningDate() {
		return screeningDate;
	}

	public void setScreeningDate(String screeningDate) {
		this.screeningDate = Util.timeStampToDate(Long.parseLong(screeningDate));
	}

	public List<FileModel> getScreeningDetails() {
		return screeningDetails;
	}

	public void setScreeningDetails(List<FileModel> screeningDetails) {
		this.screeningDetails = screeningDetails;
	}

	public List<FileModel> getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(List<FileModel> fileDetails) {
		this.fileDetails = fileDetails;
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
