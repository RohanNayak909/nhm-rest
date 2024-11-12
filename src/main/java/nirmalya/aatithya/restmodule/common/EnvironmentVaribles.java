package nirmalya.aatithya.restmodule.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Nirmalya Labs
 *
 */
public class EnvironmentVaribles {

	@Value("${service.url.mobileView}")
	private String mobileView;

	@Value("${service.url.uploadEmployee}")
	private String fileUploadEmployee;

	@Value("${service.url.uploadProfile}")
	private String fileUploadProfile;

	@Value("${service.url.ecg}")
	private String ecgUrl;

	@Value("${service.url.baseURL}")
	private String baseURL;

	@Value("${service.url.userqrCode}")
	private String userQrCode;

	@Value("${service.url.documentUpload}")
	private String documentUpload;

	@Value("${service.url.diseaseInfo}")
	private String diseaseInfo;

	@Value("${service.url.govtscheme}")
	private String govtschemeFile;

	@Value("${service.url.testreport}")
	private String testreportFile;

	@Value("${service.url.mergefile}")
	private String mergedFile;

	@Value("${service.url.insuranceUpload}")
	private String insuranceUpload;

	@Value("${service.url.organisationUpload}")
	private String organisationUpload;

	@Value("${service.url.newsAndMediaUpload}")
	private String newsAndMediaUpload;

	@Value("${service.url.webURL}")
	private String webURL;

	@Value("${service.url.logfiles}")
	private String logfiles;

	@Value("${service.url.wrizto}")
	private String wriztoUrl;

	@Value("${service.url.spiro}")
	private String spiroUrl;

	@Value("${service.url.ayurythm}")
	private String ayurythmUrl;

	@Value("${service.url.prescriptionUpload}")
	private String prescriptionUpload;
	
	public String getWriztoUrl() {
		return wriztoUrl;
	}

	public EnvironmentVaribles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSpiroUrl() {
		return spiroUrl;
	}

	public String getAyurythmUrl() {
		return ayurythmUrl;
	}

	public String getWebURL() {
		return webURL;
	}

	public String getOrganisationUpload() {
		return organisationUpload;
	}

	public String getInsuranceUpload() {
		return insuranceUpload;
	}

	public String getDocumentUpload() {
		return documentUpload;
	}

	public String getGovtschemeFile() {
		return govtschemeFile;
	}

	public String getTestreportFile() {
		return testreportFile;
	}

	public String getMergedFile() {
		return mergedFile;
	}

	public String getEcgUrl() {
		return ecgUrl;
	}

	public String getMobileView() {
		return mobileView;
	}

	public String getFileUploadEmployee() {
		return fileUploadEmployee;
	}

	public String getFileUploadProfile() {
		return fileUploadProfile;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public String getUserQrCode() {
		return userQrCode;
	}

	public String getDiseaseInfo() {
		return diseaseInfo;
	}

	public String getNewsAndMediaUpload() {
		return newsAndMediaUpload;
	}

	public String getLogfiles() {
		return logfiles;
	}

	public String getPrescriptionUpload() {
		return prescriptionUpload;
	}
}
