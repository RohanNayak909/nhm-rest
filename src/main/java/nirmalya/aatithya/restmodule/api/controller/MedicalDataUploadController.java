package nirmalya.aatithya.restmodule.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.api.dao.MedicalDataUploadDao;
import nirmalya.aatithya.restmodule.api.model.MedicalDataUploadModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class MedicalDataUploadController {

	@Autowired
	MedicalDataUploadDao medicalDataUploadDao;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(MedicalDataUploadController.class);

	@GetMapping(value = "/get-document-type-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> documentTypeList() {
		logger.info("Method : documentTypeList starts");

		logger.info("Method : documentTypeList ends");
		return medicalDataUploadDao.documentTypeList();
	}

	// Medical data upload image function
	public String saveAllMedicalDocuments(byte[] imageBytes, String ext, String pId, String filetype) {
		logger.info("Method : saveAllMedicalDocuments starts");

		String imageName = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				
				if(filetype.equals("vdo")) {
					ext = "mp4";
				}
				
				if (ext.contentEquals("jpeg")) {
					imageName = pId + "_" + nowTime + ".jpg";
				} else {
					imageName = pId + "_" + nowTime + "." + ext;
				}
			}

			Path path = Paths.get(env.getDocumentUpload() + imageName);
			if (imageBytes != null) {
				if (pId != null && pId != "") {
					Files.write(path, imageBytes);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : saveAllMedicalDocuments ends");
		return imageName;
	}

	@PostMapping(value = "/post-medical-dataupload-api", headers = "content-type=multipart/*", consumes = {
			"application/*" })
	public ResponseEntity<JsonResponse<Object>> medicalDataUpload(MedicalDataUploadModel medicalDataUploadModel) {
		logger.info("Method : medicalDataUpload starts");

		MultipartFile x = medicalDataUploadModel.getMulFile();
		String fileName = null;
		if (x != null) {
			byte[] bytes = null;
			try {
				bytes = x.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileName = saveAllMedicalDocuments(bytes, medicalDataUploadModel.getExtension(),
					medicalDataUploadModel.getUserid(), medicalDataUploadModel.getFiletype());
		}
		medicalDataUploadModel.setFileName(fileName);

		logger.info("Method : medicalDataUpload ends");
		return medicalDataUploadDao.medicalDataUpload(medicalDataUploadModel);
	}

	@GetMapping(value = "/get-medical-upload-details-api")
	public ResponseEntity<JsonResponse<List<MedicalDataUploadModel>>> getMedicalDataUploadList(
			@RequestParam String userid, @RequestParam String page, String typeid) {
		logger.info("Method : getMedicalDataUploadList starts");

		logger.info("Method : getMedicalDataUploadList ends");
		return medicalDataUploadDao.getMedicalDataUploadList(userid, page, typeid);
	}
	
	@GetMapping(value = "/get-documents-using-limit")
	public ResponseEntity<JsonResponse<List<MedicalDataUploadModel>>> getDocumentListByLimit(@RequestParam String userid) {
		logger.info("Method : getDocumentListByLimit starts");

		logger.info("Method : getDocumentListByLimit ends");
		return medicalDataUploadDao.getDocumentListByLimit(userid);
	}
}
