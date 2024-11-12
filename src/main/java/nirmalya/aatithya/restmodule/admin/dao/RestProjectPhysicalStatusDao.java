package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestAdminAgencyModel;
import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.WhatsappMessageApi;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestProjectPhysicalStatusDao {
	Logger logger = LoggerFactory.getLogger(RestProjectPhysicalStatusDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;
	
	@Autowired
	EnvironmentVaribles env;
	
//	@Autowired
//	WhatsappMessageApi whatsappMessageApi;
//	
	WhatsappMessageApi whatsappMessageApi = new WhatsappMessageApi();
	
	
	//view agency
		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestProjectPhysicalStatusModel>> viewProjectPhysicalStatusDetails(Integer pageno,
				String district,String jeNum,String fromDate,String toDate) {
			logger.info("Method : ProjectPhysicalStatusDetailsDao starts");
			System.out.println("district"+district);
			//System.out.println("dateValue"+dateValue);
			System.out.println("jeNum"+jeNum);
			List<RestProjectPhysicalStatusModel> admin = new ArrayList<RestProjectPhysicalStatusModel>();
			JsonResponse<List<RestProjectPhysicalStatusModel>> resp = new JsonResponse<List<RestProjectPhysicalStatusModel>>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("admin_physical_status")
						.setParameter("pageno", pageno)
						.setParameter("district", district)
						.setParameter("jeNum", jeNum)
						.setParameter("fromDate", fromDate)
						.setParameter("toDate", toDate)
						.getResultList();

				for (Object[] m : x) {
					String imageOne = null;
					if (m[12] != null && m[12] != "" && m[12] != " " && !m[12].toString().equals(" ")
							&& !m[12].toString().equals(null) && !m[12].toString().equals("")) {
						imageOne = env.getBaseURL() + "nirmalyaRest/document/document/" + m[12].toString();
					} else {
						imageOne = "";
					}
					
					
					String imageTwo = null;
					if (m[13] != null && m[13] != "" && m[13] != " " && !m[13].toString().equals(" ")
							&& !m[13].toString().equals(null) && !m[13].toString().equals("")) {
						imageTwo = env.getBaseURL() + "nirmalyaRest/document/document/" + m[13].toString();
					} else {
						imageTwo = "";
					}
					
					Object date1 = null;
					if (m[7] != null) {
						date1 = DateFormatter.returnStringDateNew(m[7].toString());
					}
					RestProjectPhysicalStatusModel so = new RestProjectPhysicalStatusModel(m[0],m[1],m[2],
							m[3], m[4], m[5], m[6], date1, m[8], m[9], m[10], m[11],imageOne, imageTwo,m[14],m[15],m[16],m[17],m[18],m[19]);
					admin.add(so);
					//System.out.println("RESRTTTTTTTTTT#" + so);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(admin);
			logger.info("Method : ProjectPhysicalStatusDetailsDao ends"+resp);
			return resp;
		}
		
		
		
		//get je name
		
		@SuppressWarnings("unchecked")
		public List<DropDownModel> getJENameDao(String district) {

			logger.info("Method : getJEName Dao starts");
			List<DropDownModel> specialityList = new ArrayList<DropDownModel>();try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_je_name")
						.setParameter("district", district)
						//.setParameter("block", block)
						.getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					specialityList.add(dropDownModel);
				}


			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : getJEName Dao ends");
			return specialityList;
		}
		
		
		
		//get admin je name
		
		
		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> getAdminJeListDao(String district) {

			logger.info("Method : getAdminJENameDao starts");
			List<DropDownModel> blockList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_je_name")
						.setParameter("district", district)
						.getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					blockList.add(dropDownModel);
				}

				resp.setBody(blockList);

			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : getAdminJENameDao ends" + resp);
			return resp;
		}
		
		
		//reject project
		@SuppressWarnings("unused")
		public ResponseEntity<JsonResponse<Object>> rejectprojectDetailsDao(String id,String UserMobile) {
			logger.info("Method : rejectprojectDetailsDao starts");
			logger.info("!@#$%^&*(&^%$#@!@"+UserMobile);
			Boolean validity = true;
			JsonResponse<Object> resp = new JsonResponse<Object>();
			RestProjectPhysicalStatusModel senderDetails = new RestProjectPhysicalStatusModel();
			resp.setMessage("");
			resp.setCode("");
			String nameOfProject = "";
			String dateOfUpdate = "";
			String projectRejectedBy = "";
			String userMob = "";
			if (validity)
				try {
					@SuppressWarnings("unchecked")
					List<Object[]> x = em.createNamedStoredProcedureQuery("reject_project_details")
							.setParameter("id", id)
							.setParameter("UserMobile", UserMobile)
							.getResultList();
					
					
					for (Object[] m : x) {
						
						RestProjectPhysicalStatusModel restProjectPhysicalStatusModel =
								new RestProjectPhysicalStatusModel(m[0], m[1], m[2],m[3]);
						senderDetails = restProjectPhysicalStatusModel;
						nameOfProject = restProjectPhysicalStatusModel.getProjectName();
						dateOfUpdate = restProjectPhysicalStatusModel.getUpdatedDate();
						projectRejectedBy = "";
System.out.println("restProjectPhysicalStatusModel"+restProjectPhysicalStatusModel);

if ("23".equals(restProjectPhysicalStatusModel.getRejectedBy())) {
    projectRejectedBy = "State Admin";
} else if ("21".equals(restProjectPhysicalStatusModel.getRejectedBy())) {
    projectRejectedBy = "District Admin";
} else {
    projectRejectedBy = "Admin";
}

                 
                 System.out.println("rejectedBy"+projectRejectedBy);
                 userMob = restProjectPhysicalStatusModel.getUserMobileNo();
					}
					
//					String projectName = nameOfProject;
//					String updatedDate = dateOfUpdate;
//					String rejectedBy = projectRejectedBy;
//					String userMobileNo = "91" + userMob;
//					 System.out.println("userMobileNo"+userMobileNo);
//					// whatsapp message send
////					
//					String message = "You have uploaded for project '" + projectName + "' on date " + updatedDate + 
//							" has been rejected by " + rejectedBy + ". You are requested to upload the latest status immediately";
//					System.out.println("message" + message);
//					try {
//						String responsecode = whatsappMessageApi.sendSMSWhatsapp(userMobileNo, message,projectName,updatedDate,rejectedBy);
//						System.out.println("responsecode1" + responsecode);
//						//System.out.println("mobileNo" + mobileNo);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
					
								
				} catch (Exception e) {
					try {
						String[] err = serverDao.errorProcedureCall(e);
						resp.setCode(err[0]);
						resp.setMessage(err[1]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
					HttpStatus.CREATED);
			logger.info("Method : rejectprojectDetailsDao ends"+response);
			return response;
		}
		
		
}
