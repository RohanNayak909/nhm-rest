package nirmalya.aatithya.restmodule.admin.dao;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class RestViewProjectReportDao {
	Logger logger = LoggerFactory.getLogger(RestViewProjectReportDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;
	
	
//	@SuppressWarnings("unchecked")
//	public JsonResponse<List<RestProjectPhysicalStatusModel>> ProjectReportDeatailsDao(String id) {
//		logger.info("Method : getVerifyPLPDetailsPdf Dao starts"+id);
//		List<RestProjectPhysicalStatusModel> restProjectPhysicalStatusModel = new ArrayList<RestProjectPhysicalStatusModel>();
//		
//		JsonResponse<List<RestProjectPhysicalStatusModel>> resp = new JsonResponse<List<RestProjectPhysicalStatusModel>>();
//
//		try {
//
//			List<Object[]> x = em.createNamedStoredProcedureQuery("project_report_details_view")
//					.setParameter("p_id", id)
//					.getResultList();
//
//			for (Object[] m : x) {
//				String imageOne = null;
//				if (m[12] != null && m[12] != "" && m[12] != " " && !m[12].toString().equals(" ")
//						&& !m[12].toString().equals(null) && !m[12].toString().equals("")) {
//					imageOne = env.getBaseURL() + "nirmalyaRest/document/document/" + m[12].toString();
//				} else {
//					imageOne = "";
//				}
//				 //BufferedImage resizedImageOne = resizeImage(imageOne, 50, 50);
//				
//				
//				String imageTwo = null;
//				if (m[13] != null && m[13] != "" && m[13] != " " && !m[13].toString().equals(" ")
//						&& !m[13].toString().equals(null) && !m[13].toString().equals("")) {
//					imageTwo = env.getBaseURL() + "nirmalyaRest/document/document/" + m[13].toString();
//				} else {
//					imageTwo = "";
//				}
//				
//				Object date = null;
//				if (m[7] != null) {
//					date = DateFormatter.returnStringDateNew(m[7].toString());
//				}
//				RestProjectPhysicalStatusModel reqEdit = new RestProjectPhysicalStatusModel(m[0], m[1], m[2],m[3],m[4],m[5],m[6],date,m[8],m[9],m[10],m[11],imageOne
//						,imageTwo,m[14]);
//				System.out.println("reqEdit" + reqEdit);
//				restProjectPhysicalStatusModel.add(reqEdit);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(restProjectPhysicalStatusModel);
//
//		resp.setBody(restProjectPhysicalStatusModel);
//		System.out.println("DaOOOOO================" + resp);
//		logger.info("Method : getVerifyPLPDetailsPdf Dao ends");
//
//		return resp;
//	}
	
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestProjectPhysicalStatusModel>>> ProjectReportDeatailsDao(String id) {
	    logger.info("Method : ProjectReportDeatailsDao starts with ID: " + id);

	    JsonResponse<List<RestProjectPhysicalStatusModel>> resp = new JsonResponse<>();
	    List<RestProjectPhysicalStatusModel> restProjectPhysicalStatusModel = new ArrayList<>();

	    try {
	        // Fetch project details using a stored procedure query
	        List<Object[]> resultSet = em.createNamedStoredProcedureQuery("project_report_details_view")
	                                     .setParameter("p_id", id)
	                                     .getResultList();

	        // Iterate over the result and populate the model
	        for (Object[] result : resultSet) {
	            String imageOne = (result[12] != null && !result[12].toString().trim().isEmpty()) 
	                                ? env.getBaseURL() + "nirmalyaRest/document/document/" + result[12].toString() 
	                                : "";

	            String imageTwo = (result[13] != null && !result[13].toString().trim().isEmpty()) 
	                                ? env.getBaseURL() + "nirmalyaRest/document/document/" + result[13].toString() 
	                                : "";

	            Object date = (result[7] != null) ? DateFormatter.returnStringDateNew(result[7].toString()) : null;

	            // Create the model instance and add to the list
	            RestProjectPhysicalStatusModel projectStatus = new RestProjectPhysicalStatusModel(
	                result[0], result[1], result[2], result[3], result[4], result[5], 
	                result[6], date, result[8], result[9], result[10], result[11], 
	                imageOne, imageTwo, result[14]
	            );
	            System.out.println("Image1====="+result[13]);
	            System.out.println("Image2====="+result[12]);
	            restProjectPhysicalStatusModel.add(projectStatus);
	        }

	        // Set the result to JsonResponse
	        resp.setBody(restProjectPhysicalStatusModel);

	    } catch (Exception e) {
	        logger.error("Error occurred in ProjectReportDeatailsDao:", e);
	        e.printStackTrace();
	        resp.setMessage("An error occurred: " + e.getMessage());
	        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    logger.info("Method : ProjectReportDeatailsDao ends with response: " + resp);
	    
	    return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}


	
	
	
	//resize image
	
//	private BufferedImage resizeImage(String imageUrl, int targetWidth, int targetHeight) {
//	    if (imageUrl != null && !imageUrl.isEmpty()) {
//	        try {
//	            URL url = new URL(imageUrl);
//	            BufferedImage originalImage = ImageIO.read(url);
//
//	            Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
//	            BufferedImage bufferedResizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
//	            bufferedResizedImage.getGraphics().drawImage(resizedImage, 0, 0, null);
//
//	            return bufferedResizedImage;
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            // Handle or log the exception, possibly return a default image
//	            return null;
//	        }
//	    } else {
//	        return null;
//	    }
//	}

	
	
}
