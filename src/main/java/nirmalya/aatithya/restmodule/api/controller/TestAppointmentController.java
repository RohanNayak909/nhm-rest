package nirmalya.aatithya.restmodule.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nirmalya.aatithya.restmodule.api.dao.TestAppointmentDao;
import nirmalya.aatithya.restmodule.api.model.APIAiplMasterNadiModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentModel;
import nirmalya.aatithya.restmodule.api.model.AyurythmModel;
import nirmalya.aatithya.restmodule.api.model.FileModel;
import nirmalya.aatithya.restmodule.api.model.MedTelModel;
import nirmalya.aatithya.restmodule.api.model.SearchModel;
import nirmalya.aatithya.restmodule.api.model.TestModel;
import nirmalya.aatithya.restmodule.api.model.TestReportAPIModel;
import nirmalya.aatithya.restmodule.api.model.WritzoTestModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.CurrentDateTime;
import nirmalya.aatithya.restmodule.util.DocumentUpload;
import nirmalya.aatithya.restmodule.util.FileWrite;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@RestController
@RequestMapping(value = "api")
public class TestAppointmentController {

	@Autowired
	TestAppointmentDao testAppointmentDao;
	
	@Autowired
	DocumentUpload uploadDocument;
	
	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(TestAppointmentController.class);

	/** MedTel Test Report **/
	@PostMapping(value = "/medtel-screening-test-report")
	public ResponseEntity<JsonResponse<Object>> screeningReportByMedTel(@RequestBody MedTelModel data) {
		logger.info("Method : screeningReportByMedTel starts");
		System.out.println(data);
		logger.info("Method : screeningReportByMedTel ends");
		return testAppointmentDao.screeningReportByMedTel(data);
	}

	@PostMapping(value = "/manual-screening-test-report")
	public ResponseEntity<JsonResponse<Object>> manualScreeningReportByMedTel(@RequestBody MedTelModel data) {
		logger.info("Method : manualScreeningReportByMedTel starts");
		System.out.println(data);
		logger.info("Method : manualScreeningReportByMedTel ends");
		return testAppointmentDao.manualScreeningReportByMedTel(data);
	}

	/** Others Test Report **/
	@PostMapping(value = "/system-screening-test-report")
	public ResponseEntity<JsonResponse<Object>> systemScreeningReport(@RequestBody MedTelModel data) {
		logger.info("Method : systemScreeningReport starts");

		logger.info("Method : systemScreeningReport ends");
		return testAppointmentDao.systemScreeningReport(data);
	}

	@PostMapping(value = "/save-screen-test-report")
	public ResponseEntity<JsonResponse<Object>> saveTestReport(@RequestBody TestReportAPIModel data) {
		logger.info("Method : saveTestReport starts");
		System.out.println(data);
		logger.info("Method : saveTestReport ends");
		return testAppointmentDao.saveTestReport(data);
	}

	/** Search Patient Id **/
	@GetMapping(value = "/get-regDetails")
	public ResponseEntity<JsonResponse<List<SearchModel>>> getDataByPatId(@RequestParam String regNo) {
		logger.info("Method : getDataByPatId starts");

		logger.info("Method : getDataByPatId ends");
		return testAppointmentDao.getDataByPatId(regNo);
	}

	/** Screen Test Entry **/
	@PostMapping(value = "/post-addLabAppointment")
	public ResponseEntity<JsonResponse<Object>> screenTestEntry(@RequestBody TestModel data) {
		logger.info("Method : screenTestEntry starts");

		logger.info("Method : screenTestEntry ends");
		return testAppointmentDao.screenTestEntry(data);
	}

	/** Screen Check Up Entry **/
	@PostMapping(value = "/post-addchkupAppointment")
	public ResponseEntity<JsonResponse<Object>> screenCheckUpEntry(@RequestBody TestModel data) {
		logger.info("Method : screenCheckUpEntry starts");

		logger.info("Method : screenCheckUpEntry ends");
		return testAppointmentDao.screenCheckUpEntry(data);
	}

	/* Appointment List By Date - Health Screening */
	@GetMapping(value = "/view-labAppointmentlist")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> screeningTestListAppointmentByDate(
			@RequestParam String appontdt,@RequestParam String labid) {
		logger.info("Method : screeningTestListAppointmentByDate starts");

		logger.info("Method : screeningTestListAppointmentByDate ends");
		return testAppointmentDao.getScreeningTestListAppointmentByDate(appontdt,labid);
	}

	/** Appointment List By Date - Health CheckUp **/
	@GetMapping(value = "/view-chkupAppointmentlist")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> screeningCheckUpListAppointmentByDate(
			@RequestParam String appontdt) {
		logger.info("Method : screeningCheckUpListAppointmentByDate starts");

		logger.info("Method : screeningCheckUpListAppointmentByDate ends");
		return testAppointmentDao.getScreeningCheckUpListAppointmentByDate(appontdt);
	}

	/** Change Appointment Status- Screening Test **/
	@PostMapping(value = "/post-appointmentStatus")
	public ResponseEntity<JsonResponse<Object>> changeScreentTestStatus(@RequestBody TestModel data) {
		logger.info("Method : changeScreentTestStatus starts");

		logger.info("Method : changeScreentTestStatus ends");
		return testAppointmentDao.changeScreentTestStatus(data);
	}

	/** Change Appointment Status- Health Check Up **/
	@PostMapping(value = "/post-chkupAppointmentStatus")
	public ResponseEntity<JsonResponse<Object>> changeCheckUpStatus(@RequestBody TestModel data) {
		logger.info("Method : changeCheckUpStatus starts");

		logger.info("Method : changeCheckUpStatus ends");
		return testAppointmentDao.changeCheckUpStatus(data);
	}

	@PostMapping(value = "/post-update-report-sms-status")
	public ResponseEntity<JsonResponse<Object>> updateSMSStatus(@RequestBody List<MedTelModel> data) {
		logger.info("Method : updateSMSStatus starts");

		logger.info("Method : updateSMSStatus ends");
		return testAppointmentDao.updateSMSStatus(data);
	}

	/** Health Screening - MedTel Test Data - Patient List **/
	@GetMapping(value = "/view-medteltest-list")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> viewMedTeltestListdata(@RequestParam String userid,
			@RequestParam String page, @RequestParam String search) {
		logger.info("Method : viewMedTeltestListdata starts");

		logger.info("Method : viewMedTeltestListdata ends");
		return testAppointmentDao.viewMedTeltestListdata(userid, page, search);
	}

	@GetMapping(value = "/user-view-test-list")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> userViewTestListdata(@RequestParam String page,
			@RequestParam String search) {
		logger.info("Method : userViewTestListdata starts");

		logger.info("Method : userViewTestListdata ends");
		return testAppointmentDao.userViewTestListdata(page, search);
	}

	@GetMapping(value = "/view-medteltest-list-throughId")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> viewMedTeltestListdataId(@RequestParam String id) {
		logger.info("Method : viewMedTeltestListdata starts");

		logger.info("Method : viewMedTeltestListdata ends");
		return testAppointmentDao.viewMedTeltestListdataId(id);
	}

	/** Health Screening - MedTel Test Data - Details **/
	@GetMapping(value = "/view-medteltest-details")
	public ResponseEntity<JsonResponse<Object>> viewMedTelTestDetails(@RequestParam String medtelid) {
		logger.info("Method : viewMedTelTestDetails starts");

		logger.info("Method : viewMedTelTestDetails ends");
		return testAppointmentDao.viewMedTelTestDetails(medtelid);
	}

	/* Others Test Report - UTTARAKHAND */
	@PostMapping(value = "/ukd-system-screening-test-report")
	public ResponseEntity<JsonResponse<Object>> systemScreeningReportUKD(@RequestBody MedTelModel data) {
		logger.info("Method : systemScreeningReportUKD starts");

		logger.info("Method : systemScreeningReportUKD ends");
		return testAppointmentDao.systemScreeningReportUKD(data);
	}
	
	@PostMapping(value = "/nasan-system-screening-test-report", headers = "content-type=multipart/*", 
			consumes = { "application/*" })
	public ResponseEntity<JsonResponse<Object>> systemScreeningReportWithECG(@RequestParam("data") String data,@RequestParam("ecg") MultipartFile file) {
		logger.info("Method : systemScreeningReportWithECG starts");
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(data);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String patientId = null;
		String screeningDate = null;
		
		if(!jsonObj.isNull("uniqueid")) {
			try {
				patientId = jsonObj.getString("uniqueid");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!jsonObj.isNull("screening_date")) {
			try {
				screeningDate = jsonObj.getString("screening_date");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String[] sdate = screeningDate.split("-");
		String newDate = sdate[0]+sdate[1]+sdate[2];
		String id = patientId+"_"+newDate;
		
		MultipartFile x = file;
		
		if (x != null) {
			byte[] bytes = null;
			try {
				bytes = x.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			uploadDocument.saveEcgFile(bytes, "pdf", id, "ECG");
			
		}
		
		JSONArray jsonArray = null;
		try {
			jsonArray = jsonObj.getJSONArray("screening_details");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Method : systemScreeningReportWithECG ends");
		return testAppointmentDao.systemScreeningReportWithECG(data,patientId,screeningDate,jsonArray);
	}
	/** NASAN Test Report With ECG **/
	@PostMapping(value = "/post-ecg-test-report-nasan", headers = "content-type=multipart/*", consumes = {
			"application/*" })
	public ResponseEntity<JsonResponse<Object>> postECGTestReportNasan(@RequestParam("ecg") MultipartFile file,
			@RequestParam("patient_id") String patientId, @RequestParam("screening_date") String screeningDate) {
		logger.info("Method : postECGTestReportNasan starts");
		
		String filePath1 = env.getLogfiles() + "ecg_log.txt"; 
		
		FileWrite.ecgLogWrite(filePath1, patientId, ApiResponseMessage.ECG_API_CALL);

		String[] sdate = null;
		if (!StringUtil.isNull(screeningDate)) {
			screeningDate = screeningDate.substring(0, 10);
			sdate = screeningDate.split("-");
		}

		Map<String, Object> object = new HashMap<String, Object>();
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		object.put("patient_id", patientId);
		object.put("screening_date", screeningDate);

		if (StringUtil.isNull(patientId)) {

			FileWrite.ecgLogWrite(filePath1, patientId, ApiResponseMessage.PATIENT_ID_REQUIRED);
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.PATIENT_ID_REQUIRED);
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
					HttpStatus.OK);
			return response;
		}

		if (StringUtil.isNull(screeningDate)) {
			FileWrite.ecgLogWrite(filePath1, patientId, ApiResponseMessage.SCREENING_DATE_REQUIRED);
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.SCREENING_DATE_REQUIRED);
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
					HttpStatus.OK);
			return response;
		}

		String newDate = sdate[0] + sdate[1] + sdate[2];
		String id = patientId + "_" + newDate;

		MultipartFile x = file;

		if (!x.isEmpty()) {
			byte[] bytes = null;
			try {
				bytes = x.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
				FileWrite.ecgLogWrite(filePath1, patientId, e.getMessage());
			}

			uploadDocument.saveEcgFile(bytes, "pdf", id, "ECG");

		} else {

			FileWrite.ecgLogWrite(filePath1, patientId, ApiResponseMessage.FILE_INVALID);
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.FILE_INVALID);
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
					HttpStatus.OK);
			return response;
		}

		String data = id + ".pdf";

		object.put("ecg", data);

		logger.info("Method : postECGTestReportNasan ends");
		return testAppointmentDao.postECGTestReportNasan(data, patientId, screeningDate, object);
	}
	
	
	@PostMapping(value = "/post-test-report-nasan")
	public ResponseEntity<JsonResponse<Object>> postTestReportNasan(@RequestBody MedTelModel data) {
		logger.info("Method : postTestReportNasan starts");
		
		logger.info("Method : postTestReportNasan ends");
		return testAppointmentDao.postTestReportNasan(data);
	}
	
	/** Get Lab technician State and dist other details **/
	@GetMapping(value = "/view-lab-technician-details")
	public ResponseEntity<JsonResponse<Object>> getLabTechnicianDetails(@RequestParam String labTechId) {
		logger.info("Method : getLabTechnicianDetails starts");

		logger.info("Method : getLabTechnicianDetails ends");
		return testAppointmentDao.getLabTechnicianDetailsDao(labTechId);
	}
	/* WRIZTO Vendor */
	/*
	 * @PostMapping(value = "/post-test-report-wrizto", headers =
	 * "content-type=multipart/*", consumes = { "application/*" }) public
	 * ResponseEntity<JsonResponse<Object>> postTestReportWrizto(WritzoTestModel
	 * writzoTestModel) { logger.info("Method : postTestReportWrizto starts");
	 * 
	 * String filePath1 = env.getLogfiles() + "ecg_log.txt";
	 * 
	 * logger.info("Method : postTestReportWrizto starts"+writzoTestModel.
	 * getScreeningDate());
	 * 
	 * 
	 * JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
	 * 
	 * if (StringUtil.isNull(writzoTestModel.getPatientId())) {
	 * 
	 * FileWrite.ecgLogWrite(filePath1, writzoTestModel.getPatientId(),
	 * ApiResponseMessage.PATIENT_ID_REQUIRED); Util.setJsonResponse(jsonResponse,
	 * null, ResponseStatus.failed, ApiResponseMessage.PATIENT_ID_REQUIRED);
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK); return
	 * response; }
	 * 
	 * if (StringUtil.isNull(writzoTestModel.getScreeningDate())) {
	 * FileWrite.ecgLogWrite(filePath1, writzoTestModel.getPatientId(),
	 * ApiResponseMessage.SCREENING_DATE_REQUIRED);
	 * Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
	 * ApiResponseMessage.SCREENING_DATE_REQUIRED);
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK); return
	 * response; }
	 * 
	 * writzoTestModel.setPatientId(AESUtils.decrypt(writzoTestModel.getPatientId())
	 * );
	 * 
	 * FileWrite.ecgLogWrite(filePath1, writzoTestModel.getPatientId(),
	 * ApiResponseMessage.FILE_API_CALL);
	 * 
	 * String[] sdate = null; if
	 * (!StringUtil.isNull(writzoTestModel.getScreeningDate())) { String
	 * screeningDate = writzoTestModel.getScreeningDate().substring(0, 10); sdate =
	 * screeningDate.split("-"); }
	 * 
	 * 
	 * String newDate = sdate[0] + sdate[1] + sdate[2];
	 * 
	 * if(writzoTestModel.getFileDetails()!=null &&
	 * writzoTestModel.getFileDetails().size() > 0) { for(FileModel m :
	 * writzoTestModel.getFileDetails()) {
	 * 
	 * String id = writzoTestModel.getPatientId()+ "_" + m.getKey() + "_" + newDate;
	 * 
	 * MultipartFile x = m.getFile();
	 * 
	 * if (!x.isEmpty()) { byte[] bytes = null; try { bytes = x.getBytes(); } catch
	 * (IOException e) { e.printStackTrace(); FileWrite.ecgLogWrite(filePath1,
	 * writzoTestModel.getPatientId(), e.getMessage()); }
	 * 
	 * uploadDocument.saveWriztoFile(bytes, m.getExt(), id, "WRIZTO");
	 * 
	 * 
	 * logger.info("file"+filePath1);
	 * 
	 * String data = null; if (m.getExt().contentEquals("jpeg")) { data = id +
	 * ".jpg"; } else { data = id + "." + m.getExt(); }
	 * 
	 * logger.info("datas are"+data); FileModel fileModel = new FileModel();
	 * 
	 * fileModel.setVitalName("ecgPdf"); fileModel.setResult(data);
	 * fileModel.setType("value");
	 * 
	 * writzoTestModel.getScreeningDetails().add(fileModel);
	 * 
	 * logger.info("model"+writzoTestModel);
	 * 
	 * } else {
	 * 
	 * FileWrite.ecgLogWrite(filePath1, writzoTestModel.getPatientId(),
	 * ApiResponseMessage.FILE_INVALID); Util.setJsonResponse(jsonResponse, null,
	 * ResponseStatus.failed, ApiResponseMessage.FILE_INVALID);
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK); return
	 * response; } } }
	 * 
	 * 
	 * logger.info("Method : postTestReportWrizto ends"); // return null; return
	 * testAppointmentDao.postTestReportWrizto(writzoTestModel); }
	 */
	
	// WRIZTO Vendor /
	@PostMapping(value = "/post-test-report-wrizto", headers = "content-type=multipart/*", consumes = {

	"application/*" })
	public ResponseEntity<JsonResponse<Object>> postTestReportWrizto(WritzoTestModel writzoTestModel) {
		logger.info("Method : postTestReportWrizto starts");
		
		String filePath1 = env.getLogfiles() + "ecg_log.txt";
		
		logger.info("Method : postTestReportWrizto starts"+writzoTestModel.getScreeningDate());
		
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		
		if (StringUtil.isNull(writzoTestModel.getPatientId())) {

			FileWrite.ecgLogWrite(filePath1, writzoTestModel.getPatientId(), ApiResponseMessage.PATIENT_ID_REQUIRED);
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.PATIENT_ID_REQUIRED);
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
					HttpStatus.OK);
			return response;
		}

		if (StringUtil.isNull(writzoTestModel.getScreeningDate())) {
			FileWrite.ecgLogWrite(filePath1, writzoTestModel.getPatientId(), ApiResponseMessage.SCREENING_DATE_REQUIRED);
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.SCREENING_DATE_REQUIRED);
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
					HttpStatus.OK);
			return response;
		}
		
//		writzoTestModel.setPatientId(AESUtils.decrypt(writzoTestModel.getPatientId()));
		
		FileWrite.ecgLogWrite(filePath1, writzoTestModel.getPatientId(), ApiResponseMessage.FILE_API_CALL);

		String[] sdate = null;
		if (!StringUtil.isNull(writzoTestModel.getScreeningDate())) {
			String screeningDate = writzoTestModel.getScreeningDate().substring(0, 10);
			sdate = screeningDate.split("-");
		}


		String newDate = sdate[0] + sdate[1] + sdate[2];
		List<String> imgFileList = new ArrayList<String>();
		if(writzoTestModel.getFileDetails()!=null && writzoTestModel.getFileDetails().size() > 0) {
			for(FileModel m : writzoTestModel.getFileDetails()) {
				
				String id = writzoTestModel.getPatientId()+ "_" + m.getKey() + "_" + newDate;
				
				MultipartFile x = m.getFile();
				
				if (!x.isEmpty()) {
					byte[] bytes = null;
					try {
						bytes = x.getBytes();
					} catch (IOException e) {
						e.printStackTrace();
						FileWrite.ecgLogWrite(filePath1, writzoTestModel.getPatientId(), e.getMessage());
					}

					uploadDocument.saveWriztoFile(bytes, m.getExt(), id, "WRIZTO");
					
					
					logger.info("file"+filePath1);
					
					String data = null;
					if (m.getExt().contentEquals("jpeg")) {
						data = id + ".jpg";
					} else {
						data = id + "." + m.getExt();
					}
					
					if (!m.getExt().equals("pdf")) {
						imgFileList.add(data);
					}
					
					logger.info("datas are"+data);
					FileModel fileModel = new FileModel();
					
					fileModel.setVitalName("ecgPdf");
					fileModel.setResult(data);
					fileModel.setType("value");
					
					writzoTestModel.getScreeningDetails().add(fileModel);
					
					logger.info("model"+writzoTestModel);

				} else {

					FileWrite.ecgLogWrite(filePath1, writzoTestModel.getPatientId(), ApiResponseMessage.FILE_INVALID);
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.FILE_INVALID);
					ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
							HttpStatus.OK);
					return response;
				}
			}
		}


		logger.info("Method : postTestReportWrizto ends");
//		return null;
		return testAppointmentDao.postTestReportWrizto(writzoTestModel,imgFileList);
	}
	@GetMapping(value = "rest-writzo-pdf-list")
	public JsonResponse<List<DropDownModel>> writzoPdfList(@RequestParam String id,@RequestParam String id2) {
		logger.info("Method : writzoPdfList starts");

		logger.info("Method :writzoPdfList endss");
		return testAppointmentDao.writzoPdfLists(id,id2);
	}
	
	@PostMapping(value = "post-test-report-ayurythm")
	public ResponseEntity<JsonResponse<Object>> postTestReportAyurythm(@RequestBody AyurythmModel ayurythmModel) {
		logger.info("Method : postTestReportAyurythm starts");
			
		logger.info("Method : postTestReportAyurythm ends");
		return testAppointmentDao.postTestReportAyurythm(ayurythmModel);
	}

	@PostMapping(value = "/post-test-report-nadi")
	public ResponseEntity<JsonResponse<Object>> postTestReportNadi(@RequestBody APIAiplMasterNadiModel data)
			throws IOException {
		logger.info("Method : postTestReportNadi starts");

		System.out.println("datasss"+data);
		logger.info("Method : postTestReportNadi ends");
		return testAppointmentDao.postTestReportNadi(data);
	}


}
