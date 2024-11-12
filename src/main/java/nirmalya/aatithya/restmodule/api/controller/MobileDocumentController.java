package nirmalya.aatithya.restmodule.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;

/**
 * @author Nirmalya Labs
 *
 */
@Controller
public class MobileDocumentController {

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(MobileDocumentController.class);
	
	@RequestMapping(value = "document/ecg/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getDocumentECG(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : getDocumentECG controller function starts");

		File dir = ResourceUtils.getFile(env.getEcgUrl());
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else if (docname.endsWith(".mp4")) {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4")).body(bytearr);
		} else {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}
	
	@RequestMapping(value = "document/profile/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getDocumentFun(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : getDocument controller function starts");

		File dir = ResourceUtils.getFile(env.getFileUploadProfile());
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else if (docname.endsWith(".mp4")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4")).body(bytearr);
		} else {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}

	@RequestMapping(value = "document/profile/thumb/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getDocumentThumbFun(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : image controller function starts");

		File dir = ResourceUtils.getFile(env.getFileUploadProfile()+"/thumb");
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else if (docname.endsWith(".mp4")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType((MediaType) MimeType.valueOf("video/mp4")).body(bytearr);
		} else {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}
	
	@RequestMapping(value = "document/record/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getDocumentFunRecord(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : getDocument controller function starts");
		
		File dir = ResourceUtils.getFile(env.getDocumentUpload());
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else if (docname.endsWith(".mp4")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4")).body(bytearr);
		} else {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}
	
	@RequestMapping(value = "document/record/thumb/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getDocumentThumbFunRecord(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : image controller function starts");
		
		File dir = ResourceUtils.getFile(env.getDocumentUpload()+"/thumb");
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}
	
	
	@RequestMapping(value = "document/disease/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getDocumentDiseaseInfo(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : getDocument controller function starts");
		
		File dir = ResourceUtils.getFile(env.getDiseaseInfo());
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}
	
	@RequestMapping(value = "document/disease/thumb/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getDocumentThumbDiseaseInfo(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : image controller function starts");
		
		File dir = ResourceUtils.getFile(env.getDiseaseInfo()+"/thumb");
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else {
			logger.info("Method : getDocument controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}
	
	@RequestMapping(value = "document/govtscheme/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getDocumentFunGovtScheme(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : getDocumentFunGovtScheme controller function starts");
		
		File dir = ResourceUtils.getFile(env.getGovtschemeFile());
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocumentFunGovtScheme controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocumentFunGovtScheme controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocumentFunGovtScheme controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else if (docname.endsWith(".mp4")) {
			logger.info("Method : getDocumentFunGovtScheme controller function end");
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4")).body(bytearr);
		} else {
			logger.info("Method : getDocumentFunGovtScheme controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}
	
	@RequestMapping(value = "document/newsmedia/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getnewsmediaRecord(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : getDocumentfornewsmedia controller function starts");
		
		File dir = ResourceUtils.getFile(env.getNewsAndMediaUpload());
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocumentfornewsmedia controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocumentfornewsmedia controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocumentfornewsmedia controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else if (docname.endsWith(".mp4")) {
			logger.info("Method : getDocumentfornewsmedia controller function end");
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4")).body(bytearr);
		} else {
			logger.info("Method : getDocumentfornewsmedia controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}
	
	
	@RequestMapping(value = "document/document/{docname}")
	@ResponseBody
	public ResponseEntity<byte[]> getDocumentBanner(@PathVariable(value = "docname") String docname) throws IOException {
		logger.info("Method : getDocumentECG controller function starts");

		File dir = ResourceUtils.getFile(env.getDocumentUpload());
		File file = new File(dir.getAbsolutePath() + "/" + docname);
		byte[] bytearr = Files.readAllBytes(file.toPath());
		if (docname.endsWith(".png")) {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytearr);
		} else if (docname.endsWith(".jpeg") || docname.endsWith(".jpg")) {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytearr);
		} else if (docname.endsWith(".pdf")) {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytearr);
		} else if (docname.endsWith(".mp4")) {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4")).body(bytearr);
		} else {
			logger.info("Method : getDocumentECG controller function end");
			return ResponseEntity.ok().contentType(MediaType.ALL).body(bytearr);
		}
	}
	
	
}
