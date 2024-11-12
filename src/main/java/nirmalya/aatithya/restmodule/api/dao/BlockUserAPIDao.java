package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Base64;
import java.util.Date;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.APIProjectStatusModel;
import nirmalya.aatithya.restmodule.api.model.ApiMobileVersionModel;
import nirmalya.aatithya.restmodule.api.model.BlockUserAPIModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyAddtoCartModel;
import nirmalya.aatithya.restmodule.api.model.CureeazyLabTestModel;
import nirmalya.aatithya.restmodule.api.model.ProjectApiModel;
import nirmalya.aatithya.restmodule.api.model.ProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.api.model.RestFinancialModel;
import nirmalya.aatithya.restmodule.api.model.RestFloorModel;
import nirmalya.aatithya.restmodule.api.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.StringUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class BlockUserAPIDao {
	Logger logger = LoggerFactory.getLogger(BlockUserAPIDao.class);
	
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;
	
	
	//get assigned block by user id
	
//	@SuppressWarnings("unchecked")
//	public ResponseEntity<JsonResponse<List<BlockUserAPIModel>>> getAssignedBlockByUserId(String userId) {
//		logger.info("Method : getAssignedBlockByUserIdDao starts" + userId);
//		BlockUserAPIModel blockUserAPIModel = new BlockUserAPIModel();
//		JsonResponse<List<BlockUserAPIModel>> jsonResponse = new JsonResponse<List<BlockUserAPIModel>>();
//		try {
//			System.out.println(userId);
//			List<Object[]> x = em.createNamedStoredProcedureQuery("get_assigned_block")
//					.setParameter("userId", userId)
//					.getResultList();
//			for (Object[] m : x) {
//				BlockUserAPIModel dropDownModel = new BlockUserAPIModel(m[0],m[1].toString());
//				blockUserAPIModel=(dropDownModel);
//				System.out.println(Arrays.toString(m));
//			}
//			Util.setJsonResponse(jsonResponse, blockUserAPIModel, ResponseStatus.success,
//					ApiResponseMessage.DATA_FETCH_SUCCESS);
//		} catch (Exception e) {
//			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
//		}
//		ResponseEntity<JsonResponse<List<BlockUserAPIModel>>> response = new ResponseEntity<JsonResponse<List<BlockUserAPIModel>>>(
//				jsonResponse, HttpStatus.OK);
//
//		logger.info("Method : getAssignedBlockByUserIdDao ends");
//		return response;
//	}
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<BlockUserAPIModel>>> getAssignedBlockByUserId(String userId) {
		logger.info("Method : getAssignedBlockByUserIdDao starts");

		List<BlockUserAPIModel> blockUserAPIModel = new ArrayList<BlockUserAPIModel>();
		JsonResponse<List<BlockUserAPIModel>> jsonResponse = new JsonResponse<List<BlockUserAPIModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_assigned_block")
					.setParameter("userId", userId)
					.getResultList();
			for (Object[] m : x) {
				BlockUserAPIModel dropDownModel = new BlockUserAPIModel(m[0],m[1].toString());
				blockUserAPIModel.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, blockUserAPIModel, ResponseStatus.success,
					ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
		}
		ResponseEntity<JsonResponse<List<BlockUserAPIModel>>> response = new ResponseEntity<JsonResponse<List<BlockUserAPIModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getAssignedBlockByUserIdDao ends");
		return response;
	}
	
	
	
	
	
	//get block dropdown
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getBlockDropdownList() {
		logger.info("Method : getBlockDropdownListDao starts");

		List<DropDownModel> userList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_block_dropdown_list").getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				userList.add(dropDownModel);
			}
			jsonResponse.setBody(userList);

			if (userList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getBlockDropdownListDao ends");
		return response;
	}
	
	
	//get institution dropdown
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getInstitutionList(Integer block) {
		logger.info("Method : institutionListDao starts");

		List<DropDownModel> dropDownList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("block_institution_list")
					.setParameter("block", block)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2].toString());
				dropDownList.add(dropDownModel);
			}
			Util.setJsonResponse(jsonResponse, dropDownList, ResponseStatus.success,
					ApiResponseMessage.DATA_FETCH_SUCCESS);
		} catch (Exception e) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : institutionListDao ends");
		return response;
	}
	
	
	//get catagory dropdown
	
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getCatagoryList(Integer block) {
			logger.info("Method : institutionListDao starts");

			List<DropDownModel> dropdownList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("block_catagory_list")
						.setParameter("block", block)
						.getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2].toString());
					dropdownList.add(dropDownModel);
				}
				Util.setJsonResponse(jsonResponse, dropdownList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} catch (Exception e) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
			ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
					jsonResponse, HttpStatus.OK);

			logger.info("Method : institutionListDao ends");
			return response;
		}
	
		
		//get agency dropdown
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getAgencyDropdownList() {
			logger.info("Method : getAgencyDropdownListDao starts");

			List<DropDownModel> userList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("block_agency_dropdown_list").getResultList();
				for (Object[] m : x) {

					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					userList.add(dropDownModel);
				}
				jsonResponse.setBody(userList);

				if (userList.size() > 0) {
					jsonResponse.setCode("success");
					jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Data not found");
				}

			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getAgencyDropdownListDao ends");
			return response;
		}
		
		
		//get physical update
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getphysicalUpdatedropdownList(String projectId,String floorNo) {
			logger.info("Method : getphysicalUpdatedropdownListDao starts");

			List<DropDownModel> userList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("block_physicalstatus_dropdown_list")
						//.setParameter("userId", userId)
						.setParameter("projectId", projectId)
						.setParameter("floorNo", floorNo)
						.getResultList();
				for (Object[] m : x) {

					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					userList.add(dropDownModel);
				}
				jsonResponse.setBody(userList);

				if (userList.size() > 0) {
					jsonResponse.setCode("success");
					jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Data not found");
				}

			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getphysicalUpdatedropdownListDao ends");
			return response;
		}
		
		
		//post project update
//		@SuppressWarnings("unchecked")
//		public ResponseEntity<JsonResponse<Object>> postProjectStatusApi(APIProjectStatusModel projectStatusModel) {
//			logger.info("Method : postProjectStatusApi Dao starts");
//			JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
//			Boolean validity = true;
//			logger.info("@#$%^&^%$#@#$%^^^"+projectStatusModel.getUserId());
//			if (projectStatusModel.getUniqueid() == null || projectStatusModel.getUniqueid() == "") {
//				jsonResponse.setMessage("Unique Id is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getUserId() == null || projectStatusModel.getUserId() == "") {
//				jsonResponse.setMessage("User Id is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getInstitute() == null || projectStatusModel.getInstitute() == "") {
//				jsonResponse.setMessage("Institution field is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getCategory() == null || projectStatusModel.getCategory() == "") {
//				jsonResponse.setMessage("Category field is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getProjectName() == null || projectStatusModel.getProjectName() == "") {
//				jsonResponse.setMessage("Project field is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getAgency() == null || projectStatusModel.getAgency() == "") {
//				jsonResponse.setMessage("Agency field is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getFloorNo() == null || projectStatusModel.getFloorNo() == "") {
//				jsonResponse.setMessage("Floor number is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getPhyStatus() == null || projectStatusModel.getPhyStatus() == "") {
//				jsonResponse.setMessage("Physical Status is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getCurDate() == null || projectStatusModel.getCurDate() == "") {
//				jsonResponse.setMessage("Current Date is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getImageOne() == null || projectStatusModel.getImageOne() == "") {
//				jsonResponse.setMessage("Image is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getImageTwo() == null || projectStatusModel.getImageTwo() == "") {
//				jsonResponse.setMessage("Image is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getLatitude() == null || projectStatusModel.getLatitude() == "") {
//				jsonResponse.setMessage("Latitude is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getLongitude() == null || projectStatusModel.getLongitude() == "") {
//				jsonResponse.setMessage("Longitude is required!");
//				validity = false;
//			}
//
//			
//			if (projectStatusModel.getImageOne() != null && projectStatusModel.getImageOne() != "") {
//				
//				try {
//					byte[] bytes = Base64.getDecoder().decode(projectStatusModel.getImageOne());
//					String imageName = "";
//					imageName = saveAllImage2(bytes, projectStatusModel.getExtension(), projectStatusModel.getUniqueid());
//					projectStatusModel.setImageOne(imageName);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//			if (projectStatusModel.getImageTwo() != null && projectStatusModel.getImageTwo() != "") {
//				try {
//					byte[] bytes = Base64.getDecoder().decode(projectStatusModel.getImageTwo());
//					String imageName = null;
//					imageName = saveAllImage2(bytes, projectStatusModel.getExtension(), projectStatusModel.getUniqueid());
//					projectStatusModel.setImageTwo(imageName);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//			
//			if (validity) {
//				try {
//					if (projectStatusModel.getUserId() != null || projectStatusModel.getUserId() != "") {
//						List<Object[]> x = em.createNamedStoredProcedureQuery("post_project_status_api")
//								.setParameter("uniqueid", projectStatusModel.getUniqueid())
//								.setParameter("userid", projectStatusModel.getUserId())
//								.setParameter("institute", projectStatusModel.getInstitute())
//								.setParameter("category", projectStatusModel.getCategory())
//								.setParameter("projectName", projectStatusModel.getProjectName())
//								.setParameter("agency", projectStatusModel.getAgency())
//								.setParameter("floorNo", projectStatusModel.getFloorNo())
//								.setParameter("phyStatus", projectStatusModel.getPhyStatus())
//								.setParameter("curDate", DateFormatter.getStringDate(projectStatusModel.getCurDate()))
//								.setParameter("imageOne", projectStatusModel.getImageOne())
//								.setParameter("imageTwo", projectStatusModel.getImageTwo())
//								.setParameter("blockId", projectStatusModel.getBlockId())
//								.setParameter("districtId", projectStatusModel.getDistrictId())
//								.setParameter("latitude", projectStatusModel.getLatitude())
//								.setParameter("longitude", projectStatusModel.getLongitude())
//								.getResultList();
//						jsonResponse.setBody(x.get(0));
//						
//						logger.info("@#$%^^%$#@#$^^%$#@#^%$#@#$^%$#@#$%^^%$#"+ DateFormatter.getStringDate(projectStatusModel.getCurDate()));
//						
//					}
//					jsonResponse.setCode("success");
//					jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
//				} catch (Exception e) {
//					e.printStackTrace();
//					jsonResponse.setCode("failed");
//					jsonResponse.setMessage(e.getMessage());
//				}
//			}
//			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
//					HttpStatus.OK);
//			logger.info("Method : postProjectStatusApi Dao ends"+response);
//			return response;
//		}
		
		
		
//		@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
//		public ResponseEntity<JsonResponse<Object>> postProjectStatusApi(
//				APIProjectStatusModel aPIProjectStatusModel) {
//
//			logger.info("Method : postPhysicalStatusApi starts");
//
//			JsonResponse<Object> resp = new JsonResponse<Object>();
//			APIProjectStatusModel dlist = new APIProjectStatusModel();
//
//			String physicalList = "";
//			String projectId = "";
//			if (aPIProjectStatusModel.getPhysicalList() != null) {
//				if (aPIProjectStatusModel.getPhysicalList().size() > 0) {
//					
//					for (RestProjectPhysicalStatusModel m : aPIProjectStatusModel.getPhysicalList()) {
//						if (m.getImageOne() != null && m.getImageOne() != "") {
//							try {
//								
//								byte[] bytes = Base64.getDecoder().decode(m.getImageOne());
//								String imageName = "";
//								imageName = saveAllImage2(bytes, m.getExtension(), m.getUniqueid());
//								m.setImageOne(imageName);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//
//						if (m.getImageTwo() != null && m.getImageTwo() != "") {
//							try {
//								byte[] bytes = Base64.getDecoder().decode(m.getImageTwo());
//								String imageName = null;
//								imageName = saveAllImage2(bytes, m.getExtension(), m.getUniqueid());
//								m.setImageTwo(imageName);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//						
//						physicalList = physicalList + "(\'" + m.getUniqueid() + "\',\'" + m.getUserId() + "\' ,\'" + m.getInstitute() + "\',\'" + m.getCategory()+ "\',\'" + m.getProjectName() + "\',\'" + m.getAgency() + "\',\'" + m.getFloor() + "\',\'" + m.getPhyStatus() + "\',\'" + DateFormatter.getStringDate(m.getCurDate()) + "\'"
//								+ ",\'" + m.getImageOne() + "\',\'" + m.getImageTwo() + "\'"
//										+ ",\'" + m.getBlockId() + "\',\'" + m.getDistrictId() + "\',\'" + m.getLatitude() + "\',\'" + m.getLongitude() + "\'),";
//						
//						projectId=m.getUniqueid();
//					}
//					physicalList = physicalList.substring(0, physicalList.length() - 1);
//				} else {
//					physicalList = "";
//				}
//			}
//			System.out.println("physicalList"+physicalList);
//			String projectIdList = "";
//			if (aPIProjectStatusModel.getPhysicalList() != null) {
//				if (aPIProjectStatusModel.getPhysicalList().size() > 0) {
//					
//					for (RestProjectPhysicalStatusModel m : aPIProjectStatusModel.getPhysicalList()) {
//					
//						
//						projectIdList = projectIdList + "(" + m.getUniqueid() + ",\'"+m.getPhyStatus()+"\'),";
//					}
//					projectIdList = projectIdList.substring(0, projectIdList.length() - 1);
//				} else {
//					projectIdList = "";
//				}
//			}
//			System.out.println("projectIdList"+projectIdList);
//			String projectPhysicalStatus = "";
//			if (aPIProjectStatusModel.getPhysicalList() != null) {
//				if (aPIProjectStatusModel.getPhysicalList().size() > 0) {
//					
//					for (RestProjectPhysicalStatusModel m : aPIProjectStatusModel.getPhysicalList()) {
//					
//						
//						projectPhysicalStatus = projectPhysicalStatus + "(\'" + m.getPhyStatus() + "\'),";
//					}
//					projectPhysicalStatus = projectPhysicalStatus.substring(0, projectPhysicalStatus.length() - 1);
//				} else {
//					projectPhysicalStatus = "";
//				}
//			}
//			System.out.println("projectPhysicalStatus"+projectPhysicalStatus);
//				try {
//					List<Object[]> x = em.createNamedStoredProcedureQuery("post_project_status_api")
//							.setParameter("valuess", physicalList)
//					.setParameter("projectId", projectId)
//					.setParameter("projectIdList", projectIdList)
//					.setParameter("projectPhysicalStatus", projectPhysicalStatus)
//					.getResultList();
//					logger.info("Patient" + x);
//
//					resp.setBody(x.get(0));
//					resp.setCode("success");
//					resp.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
//				} catch (Exception e) {
//					try {
//						String[] err = serverDao.errorProcedureCall(e);
//						resp.setCode(err[0]);
//						resp.setMessage(err[1]);
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
//				}
//			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
//					resp, HttpStatus.OK);
//			logger.info("Method : postPhysicalStatusApi ends");
//			return response;
//		}
		
		
		
		
		
		
		@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
		public ResponseEntity<JsonResponse<Object>> postProjectStatusApi(
				APIProjectStatusModel aPIProjectStatusModel) {

			logger.info("Method : postPhysicalStatusApi starts");

			JsonResponse<Object> resp = new JsonResponse<Object>();
			APIProjectStatusModel dlist = new APIProjectStatusModel();

			String physicalList = "";
			String projectId = "";
			String dataEntryStatus = "1";
			if (aPIProjectStatusModel.getPhysicalList() != null) {
				if (aPIProjectStatusModel.getPhysicalList().size() > 0) {
					
					for (RestProjectPhysicalStatusModel m : aPIProjectStatusModel.getPhysicalList()) {
						if (m.getImageOne() != null && m.getImageOne() != "") {
							try {
								
								byte[] bytes = Base64.getDecoder().decode(m.getImageOne());
								String imageName = "";
								imageName = saveAllImage2(bytes, m.getExtension(), m.getUniqueid());
								m.setImageOne(imageName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						if (m.getImageTwo() != null && m.getImageTwo() != "") {
							try {
								byte[] bytes = Base64.getDecoder().decode(m.getImageTwo());
								String imageName = null;
								imageName = saveAllImage2(bytes, m.getExtension(), m.getUniqueid());
								m.setImageTwo(imageName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						m.setDataEntryStatus(dataEntryStatus);
						System.out.println("!@#$%^dataEntryStatus"+dataEntryStatus);
						physicalList = physicalList + "(\'" + m.getUniqueid() + "\',\'" + m.getUserId() + "\' ,\'" + m.getInstitute() + "\',\'" + m.getCategory()+ "\',\'" + m.getProjectName() + "\',\'" + m.getAgency() + "\',\'" + m.getFloor() + "\',\'" + m.getPhyStatus() + "\',\'" + DateFormatter.getStringDate(m.getCurDate()) + "\'"
								+ ",\'" + m.getImageOne() + "\',\'" + m.getImageTwo() + "\'"
										+ ",\'" + m.getBlockId() + "\',\'" + m.getDistrictId() + "\',\'" + m.getLatitude() + "\',\'" + m.getLongitude() + "\',\'"+m.getDataEntryStatus()+"\',\'"+aPIProjectStatusModel.getUserName()+"\'),";
						
						projectId=m.getUniqueid();
						
						System.out.println("@@@@@@@@JE NAME@@@@@@@@@"+aPIProjectStatusModel.getUserName());
						
					}
					physicalList = physicalList.substring(0, physicalList.length() - 1);
				} else {
					physicalList = "";
				}
			}
			System.out.println("physicalList"+physicalList);
			String projectIdList = "";
			if (aPIProjectStatusModel.getPhysicalList() != null) {
				if (aPIProjectStatusModel.getPhysicalList().size() > 0) {
					
					for (RestProjectPhysicalStatusModel m : aPIProjectStatusModel.getPhysicalList()) {
					
						
						projectIdList = projectIdList + "(" + m.getUniqueid() + ",\'"+m.getPhyStatus()+"\'),";
					}
					projectIdList = projectIdList.substring(0, projectIdList.length() - 1);
				} else {
					projectIdList = "";
				}
			}
			System.out.println("projectIdList>>>>>>>>>>==== "+projectIdList);
			
			
			String projectFloorList = "";
			if (aPIProjectStatusModel.getPhysicalList() != null) {
				if (aPIProjectStatusModel.getPhysicalList().size() > 0) {
					
					for (RestProjectPhysicalStatusModel m : aPIProjectStatusModel.getPhysicalList()) {
					
						
						projectFloorList = projectFloorList + "(" + m.getUniqueid() + ",\'"+m.getFloor()+"\'),";
					}
					projectFloorList = projectFloorList.substring(0, projectFloorList.length() - 1);
				} else {
					projectFloorList = "";
				}
			}
			System.out.println("projectFloorList>>>>>>>>>>>= "+projectFloorList);
			
			
			String projectPhysicalStatus = "";
			if (aPIProjectStatusModel.getPhysicalList() != null) {
				if (aPIProjectStatusModel.getPhysicalList().size() > 0) {
					
					for (RestProjectPhysicalStatusModel m : aPIProjectStatusModel.getPhysicalList()) {
					
						
						projectPhysicalStatus = projectPhysicalStatus + "(\'" + m.getPhyStatus() + "\'),";
					}
					projectPhysicalStatus = projectPhysicalStatus.substring(0, projectPhysicalStatus.length() - 1);
				} else {
					projectPhysicalStatus = "";
				}
			}
			System.out.println("projectPhysicalStatus>>>>>>>>= "+projectPhysicalStatus);
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_project_status_api")
							.setParameter("valuess", physicalList)
					.setParameter("projectId", projectId)
					.setParameter("projectIdList", projectIdList)
					.setParameter("projectPhysicalStatus", projectPhysicalStatus)
					.setParameter("projectFloorList", projectFloorList)
					.getResultList();
					logger.info("Patient" + x);

					resp.setBody(x.get(0));
					resp.setCode("success");
					resp.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
				} catch (Exception e) {
					try {
						String[] err = serverDao.errorProcedureCall(e);
						resp.setCode(err[0]);
						resp.setMessage(err[1]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
					resp, HttpStatus.OK);
			logger.info("Method : postPhysicalStatusApi ends");
			return response;
		}
		
		
		
		
		
		
		
	
		
		
		//upload image
		public String saveAllImage2(byte[] imageBytes, String ext, String pId) {
			logger.info("Method : saveAllImage starts");
			String imageName ="";

			try {

				if (imageBytes != null) {
					long nowTime = new Date().getTime();
					if (ext.contentEquals("jpeg")) {
						imageName =  nowTime + ".jpg";
						System.out.println("ifimagename" + imageName);
					} else {
						imageName = nowTime + "." + ext;
						System.out.println("elseimagename" + imageName);
					}

				}

				Path path = Paths.get(env.getDocumentUpload() + imageName);
				System.out.println("path" + path);
				if (imageBytes != null) {

					if (pId != null && pId != "") {
						Files.write(path, imageBytes);

						ByteArrayInputStream in = new ByteArrayInputStream(imageBytes);
						Integer height = 600;
						Integer width = 600;

						try {
							BufferedImage img = ImageIO.read(in);
							if (height == 0) {
								height = (width * img.getHeight()) / img.getWidth();
							}
							if (width == 0) {
								width = (height * img.getWidth()) / img.getHeight();
							}

							BufferedImage outputImage = new BufferedImage(width, height, img.getType());

							Graphics2D g2d = outputImage.createGraphics();
							g2d.drawImage(img, 0, 0, width, height, null);
							g2d.dispose();
							String outputImagePath = env.getDocumentUpload()+ imageName;
							System.out.println("outputImagePath" + outputImagePath);
							ImageIO.write(outputImage, ext, new File(outputImagePath));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : saveAllImage ends");
			return imageName;
		}
		
		
		//get project list
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<DropDownModel>>> getProjectList(Integer block) {
			logger.info("Method : projectListDao starts");

			List<DropDownModel> dropdownList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("block_Project_list_api")
						.setParameter("block", block)
						.getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
					dropdownList.add(dropDownModel);
				}
				Util.setJsonResponse(jsonResponse, dropdownList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} catch (Exception e) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
			ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
					jsonResponse, HttpStatus.OK);

			logger.info("Method : projectListDao ends");
			return response;
		}
		
		
		
		//get details of the project
		
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<APIProjectStatusModel>>> getProjectDetailsList(String uniqueId) {
			logger.info("Method : projectDetailsListDao starts");

			List<APIProjectStatusModel> dropdownList = new ArrayList<APIProjectStatusModel>();
			JsonResponse<List<APIProjectStatusModel>> jsonResponse = new JsonResponse<List<APIProjectStatusModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("project_details_list_api")
						.setParameter("uniqueId", uniqueId)
						.getResultList();
				for (Object[] m : x) {
					APIProjectStatusModel dropDownModel = new APIProjectStatusModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],
							m[7],m[8],m[9],m[10],m[11],m[12]);
					dropdownList.add(dropDownModel);
				}
				Util.setJsonResponse(jsonResponse, dropdownList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} catch (Exception e) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
			ResponseEntity<JsonResponse<List<APIProjectStatusModel>>> response = new ResponseEntity<JsonResponse<List<APIProjectStatusModel>>>(
					jsonResponse, HttpStatus.OK);

			logger.info("Method : projectDetailsListDao ends"+response);
			return response;
		}
		
		
		
		//get financial details of project
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<APIProjectStatusModel>>> getProjectFinancialDetails(String uniqueId) {
			logger.info("Method : getProjectFinancialDetailsDao starts");

			List<APIProjectStatusModel> dropdownList = new ArrayList<APIProjectStatusModel>();
			JsonResponse<List<APIProjectStatusModel>> jsonResponse = new JsonResponse<List<APIProjectStatusModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("project_financial_details")
						.setParameter("uniqueId", uniqueId)
						.getResultList();
				for (Object[] m : x) {
					APIProjectStatusModel dropDownModel = new APIProjectStatusModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6]);
					dropdownList.add(dropDownModel);
				}
				Util.setJsonResponse(jsonResponse, dropdownList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} catch (Exception e) {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
			}
			ResponseEntity<JsonResponse<List<APIProjectStatusModel>>> response = new ResponseEntity<JsonResponse<List<APIProjectStatusModel>>>(
					jsonResponse, HttpStatus.OK);

			logger.info("Method : getProjectFinancialDetailsDao ends");
			return response;
		}
		
		
		//post financial details of project
//		@SuppressWarnings("unchecked")
//		public ResponseEntity<JsonResponse<Object>> postFinancialStatusApi(APIProjectStatusModel projectStatusModel) {
//			logger.info("Method : postFinancialStatusApi Dao starts"+projectStatusModel);
//			JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
//			Boolean validity = true;
//			if (projectStatusModel.getUniqueid() == null || projectStatusModel.getUniqueid() == "") {
//				jsonResponse.setMessage("Unique Id is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getUserId() == null || projectStatusModel.getUserId() == "") {
//				jsonResponse.setMessage("User Id is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getUserId() == null || projectStatusModel.getUserId() == "") {
//				jsonResponse.setMessage("Login Id is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getProjectName() == null || projectStatusModel.getProjectName() == "") {
//				jsonResponse.setMessage("Project Name is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getScheme() == null || projectStatusModel.getScheme() == "") {
//				jsonResponse.setMessage("Scheme field is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getYearOfSanction() == null || projectStatusModel.getYearOfSanction() == "") {
//				jsonResponse.setMessage("Year of sanction is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getApprovedAmount() == null || projectStatusModel.getApprovedAmount() == "") {
//				jsonResponse.setMessage("Approved amount is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getAvailableCost() == null || projectStatusModel.getAvailableCost() == "") {
//				jsonResponse.setMessage("Available cost is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getExpenditure() == null || projectStatusModel.getExpenditure() == "") {
//				jsonResponse.setMessage("Expenditure is required!");
//				validity = false;
//			}
//			if (projectStatusModel.getUcSubmitted() == null || projectStatusModel.getUcSubmitted() == "") {
//				jsonResponse.setMessage("UC submitted field is required!");
//				validity = false;
//			}
//			
//			
//			if (validity) {
//				try {
//					if (projectStatusModel.getUniqueid() != null || projectStatusModel.getUniqueid() != "") {
//						List<Object[]> x = em.createNamedStoredProcedureQuery("post_Financial_status_api")
//								.setParameter("uniqueid", projectStatusModel.getUniqueid())
//								.setParameter("userid", projectStatusModel.getUserId())
//								.setParameter("projectName", projectStatusModel.getProjectName())
//								.setParameter("scheme", projectStatusModel.getScheme())
//								.setParameter("yearOfSanction", projectStatusModel.getYearOfSanction())
//								.setParameter("approvedAmount", projectStatusModel.getApprovedAmount())
//								.setParameter("availableCost", projectStatusModel.getAvailableCost())
//								.setParameter("expenditure", projectStatusModel.getExpenditure())
//								.setParameter("ucSubmitted", projectStatusModel.getUcSubmitted())
//								.getResultList();
//						jsonResponse.setBody(x.get(0));
//					}
//					jsonResponse.setCode("success");
//					jsonResponse.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
//				} catch (Exception e) {
//					e.printStackTrace();
//					jsonResponse.setCode("failed");
//					jsonResponse.setMessage(e.getMessage());
//				}
//			}
//			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
//					HttpStatus.OK);
//			logger.info("Method : postFinancialStatusApi Dao ends");
//			return response;
//		}
		
		@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
		public ResponseEntity<JsonResponse<Object>> postFinancialStatusApi(
				APIProjectStatusModel aPIProjectStatusModel) {

			logger.info("Method : postFinancialStatusApi starts" + aPIProjectStatusModel);

			JsonResponse<Object> resp = new JsonResponse<Object>();
			APIProjectStatusModel dlist = new APIProjectStatusModel();

			String financialList = "";
			String projectId = "";
			String dataEntryStatus = "1";
			if (aPIProjectStatusModel.getFinancialList() != null) {
				if (aPIProjectStatusModel.getFinancialList().size() > 0) {
					for (RestFinancialModel m : aPIProjectStatusModel.getFinancialList()) {
 
						m.setDataEntryStatus(dataEntryStatus);
						System.out.println("!@#$%^dataEntryStatus"+dataEntryStatus);
						financialList = financialList + "(\'" + m.getUniqueid() + "\',\'" + m.getUserId() + "\' ,\'" + m.getProjectName() + "\',\'" + m.getScheme() + "\',\'" + m.getYearOfSanction() + "\',\'" + m.getApprovedAmount() + "\',\'" + m.getAvailableCost() + "\',\'" + m.getExpenditure() + "\',\'" + m.getUcSubmitted() + "\',\'"+m.getFundRelease()+"\',\'"+m.getDataEntryStatus()+"\',\'"+aPIProjectStatusModel.getUserName()+"\'),";
						System.out.println("!@#$%^dataEntryStatus@@@@@@@@@@"+m.getDataEntryStatus());
						projectId=m.getUniqueid();
					}
					financialList = financialList.substring(0, financialList.length() - 1);
				} else {
					financialList = "";
				}
			}
			
System.out.println("financialList"+financialList);
System.out.println("projectId"+projectId);
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("post_Financial_status_api")
							.setParameter("valuess", financialList)
					.setParameter("projectId", projectId).getResultList();
					logger.info("Patient" + x);

					resp.setBody(x.get(0));
					resp.setCode("success");
					resp.setMessage(ApiResponseMessage.DATA_SAVED_SUCCESSFULLY);
				} catch (Exception e) {
					try {
						String[] err = serverDao.errorProcedureCall(e);
						resp.setCode(err[0]);
						resp.setMessage(err[1]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
					resp, HttpStatus.OK);
			logger.info("Method : postFinancialStatusApi ends");
			return response;
		}
		
		//get physical status of floor
//		@SuppressWarnings("unchecked")
//		public ResponseEntity<JsonResponse<List<DropDownModel>>> getPhysicalStatusList(Integer floorNo) {
//			logger.info("Method : physicalStatusListDao starts");
//
//			List<DropDownModel> dropDownList = new ArrayList<DropDownModel>();
//			JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
//			try {
//
//				List<Object[]> x = em.createNamedStoredProcedureQuery("block_physical_status_list")
//						.setParameter("floorNo", floorNo)
//						.getResultList();
//				for (Object[] m : x) {
//					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2].toString());
//					dropDownList.add(dropDownModel);
//				}
//				Util.setJsonResponse(jsonResponse, dropDownList, ResponseStatus.success,
//						ApiResponseMessage.DATA_FETCH_SUCCESS);
//			} catch (Exception e) {
//				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.DATA_FECTH_FAILED);
//			}
//			ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
//					jsonResponse, HttpStatus.OK);
//
//			logger.info("Method : physicalStatusListDao ends");
//			return response;
//		}
		
		
		
//		@SuppressWarnings("unchecked")
//		// JsonResponse<Object> jsonResponse1 = new JsonResponse<Object>();
//		public JsonResponse<List<APIProjectStatusModel>> getBlockProjectDeatails(String userId) {
//			logger.info("Method in Dao: getrequestedbloodbanklist Dao starts");
//			List<APIProjectStatusModel> requestedBloodBankListModel = new ArrayList<APIProjectStatusModel>();
//			String blockId = "";
//			try {
//
//				List<Object[]> x = em.createNamedStoredProcedureQuery("get_je_assinged_block")
//						.setParameter("userId", userId)
//						.getResultList();
//				for (Object[] m : x) {
//
//					APIProjectStatusModel dropDownModel = new APIProjectStatusModel(m[0], m[1]);
//					requestedBloodBankListModel.add(dropDownModel);
//					System.out.println("dropDownModel"+dropDownModel);
//					blockId = dropDownModel.getAssignedBlock();
//					
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("blockId"+blockId);
//			Integer block=Integer.parseInt(blockId);
//			// Assigned block project List
//			List<DropDownModel> projectList = new ArrayList<DropDownModel>();
//			try {
//				List<Object[]> x1 = em.createNamedStoredProcedureQuery("block_Project_list_api")
//						//.setParameter("communityId", community)
//						.setParameter("block", block)
//						.getResultList();
//				for (Object[] m : x1) {
//
//					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
//					projectList.add(dropDownModel);
//					System.out.println("######" + dropDownModel);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			requestedBloodBankListModel.get(0).setProjectlist(projectList);
//			JsonResponse<List<APIProjectStatusModel>> resp = new JsonResponse<List<APIProjectStatusModel>>();
//			resp.setBody(requestedBloodBankListModel);
//			resp.setCode("success");
//			resp.setMessage("Data Fetched Successfully");
//			logger.info("Method in Dao: getrequestedbloodbanklist Dao ends");
//			return resp;
//		}
		
		
		
		
		
		
		
//		@SuppressWarnings("unchecked")
//		// JsonResponse<Object> jsonResponse1 = new JsonResponse<Object>();
//		public JsonResponse<List<APIProjectStatusModel>> getBlockProjectDeatails(String userId) {
//			logger.info("Method in Dao: getrequestedbloodbanklist Dao starts");
//			List<APIProjectStatusModel> requestedBloodBankListModel = new ArrayList<APIProjectStatusModel>();
//			JsonResponse<List<APIProjectStatusModel>> jsonResponse = new JsonResponse<List<APIProjectStatusModel>>();
//			APIProjectStatusModel bloodBank = new APIProjectStatusModel();
//			String blockId = "";
//			try {
//
//				List<Object[]> x = em.createNamedStoredProcedureQuery("get_je_assinged_block")
//						.setParameter("userId", userId)
//						.getResultList();
//				for (Object[] m : x) {
//
//					APIProjectStatusModel dropDownModel = new APIProjectStatusModel(m[0], m[1]);
//					requestedBloodBankListModel.add(dropDownModel);
//					System.out.println("dropDownModel"+dropDownModel);
//					blockId = dropDownModel.getAssignedBlock();
//					
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			//System.out.println("community");
//			// Frequent Test List
//			//Integer block=Integer.parseInt(blockId);
//			// Assigned block project List
//			List<DropDownModel> projectList = new ArrayList<DropDownModel>();
//			try {
//				List<Object[]> x1  = em.createNamedStoredProcedureQuery("api_block_Project_list")
//						//.setParameter("communityId", community)
//						.setParameter("block", blockId)
//						.getResultList();
//				for (Object[] m : x1) {
//
//					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
//					projectList.add(dropDownModel);
//					System.out.println("######" + dropDownModel);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			requestedBloodBankListModel.get(0).setProjectlist(projectList);
//			JsonResponse<List<APIProjectStatusModel>> resp = new JsonResponse<List<APIProjectStatusModel>>();
//			resp.setBody(requestedBloodBankListModel);
//			resp.setCode("success");
//			resp.setMessage("Data Fetched Successfully");
//			logger.info("Method in Dao: getrequestedbloodbanklist Dao ends");
//			return resp;
//		}
		
		
		@SuppressWarnings("unchecked")
		// JsonResponse<Object> jsonResponse1 = new JsonResponse<Object>();
		public JsonResponse<List<APIProjectStatusModel>> getBlockProjectDeatails(String userId) {
			logger.info("Method in Dao: getrequestedbloodbanklist Dao starts");
			List<APIProjectStatusModel> requestedBloodBankListModel = new ArrayList<APIProjectStatusModel>();
			String community = "";
			String physicalStatusId = "";
			String floorNo = "";
			String floorId = "";
			
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_je_assinged_block")
						.setParameter("userId", userId).getResultList();
				for (Object[] m : x) {

					APIProjectStatusModel dropDownModel = new APIProjectStatusModel(m[0], m[1]);
					community=dropDownModel.getAssignedBlock();
					logger.info("@@@@@@@@Assigned blockss----------------"+dropDownModel.getAssignedBlock());
					List<ProjectApiModel> projectList = new ArrayList<ProjectApiModel>();
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("api_block_Project_list")
							.setParameter("block", community).getResultList();
					for (Object[] m1 : x1) {

						ProjectApiModel dropDownModel1 = new ProjectApiModel(m1[0], m1[1]);
						physicalStatusId=dropDownModel1.getProjectId();
						List<ProjectPhysicalStatusModel> physicalStatusList = new ArrayList<ProjectPhysicalStatusModel>();
						List<ProjectPhysicalStatusModel> financialStatusList = new ArrayList<ProjectPhysicalStatusModel>();
						List<RestFloorModel> floorList = new ArrayList<RestFloorModel>();
						List<Object[]> x2 = em.createNamedStoredProcedureQuery("api_Project_details")
								.setParameter("uniqueId", physicalStatusId).getResultList();
						List<Object[]> x4 = em.createNamedStoredProcedureQuery("api_project_financial_details")
								.setParameter("uniqueId", physicalStatusId).getResultList();
						List<Object[]> x5 = em.createNamedStoredProcedureQuery("api_floor_dropdown").getResultList();
						for (Object[] m2 : x2) {
							ProjectPhysicalStatusModel dropDownModel2 = new ProjectPhysicalStatusModel(m2[0], m2[1], m2[2], m2[3], m2[4], m2[5], m2[6], m2[7], m2[8],
									m2[9], m2[10], m2[11],m2[12]);;
							floorNo=dropDownModel2.getFloorNo();
							physicalStatusList.add(dropDownModel2);
						
		                }
						for (Object[] m4 : x4) {
							ProjectPhysicalStatusModel dropDownModel4 = new ProjectPhysicalStatusModel(m4[0], m4[1], m4[2], m4[3], m4[4], m4[5],null);;
							financialStatusList.add(dropDownModel4);
		                }
						for (Object[] m5 : x5) {
							RestFloorModel dropDownModel5 = new RestFloorModel(m5[0], m5[1]);
							floorId=dropDownModel5.getFloorId();
							List<DropDownModel> floorWiseStatusList = new ArrayList<DropDownModel>();
							List<Object[]> x6 = em.createNamedStoredProcedureQuery("block_physicalstatus_dropdown_list")
									.setParameter("projectId", physicalStatusId)
									.setParameter("floorNo", floorId).getResultList();
							for (Object[] m6 : x6) {
								DropDownModel dropDownModel6 = new DropDownModel(m6[0].toString(), m6[1]);
								floorWiseStatusList.add(dropDownModel6);
			                }
							dropDownModel5.setFloorWiseStatusList(floorWiseStatusList);
							floorList.add(dropDownModel5);
					
						}
						dropDownModel1.setPhysicalStatuslist(physicalStatusList);
						dropDownModel1.setFinancialList(financialStatusList);
						dropDownModel1.setFloorList(floorList);
						projectList.add(dropDownModel1);
					}
					
					dropDownModel.setProjectlist(projectList);
					requestedBloodBankListModel.add(dropDownModel);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			JsonResponse<List<APIProjectStatusModel>> resp = new JsonResponse<List<APIProjectStatusModel>>();
			resp.setBody(requestedBloodBankListModel);
			resp.setCode("success");
			resp.setMessage("Data Fetched Successfully");
			logger.info("Method in Dao: getrequestedbloodbanklist Dao ends");
			return resp;
		}
		
		
		
		
		
		
		
		@SuppressWarnings("unchecked")
		// JsonResponse<Object> jsonResponse1 = new JsonResponse<Object>();
		public JsonResponse<List<APIProjectStatusModel>> getBlockProjectDeatailsDemo(String userId) {
			logger.info("Method in Dao: getrequestedbloodbanklist Dao starts");
			List<APIProjectStatusModel> requestedBloodBankListModel = new ArrayList<APIProjectStatusModel>();
			String community = "";
			String physicalStatusId = "";
			String floorNo = "";
			String floorId = "";
			
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_je_assinged_block")
						.setParameter("userId", userId).getResultList();
				for (Object[] m : x) {

					APIProjectStatusModel dropDownModel = new APIProjectStatusModel(m[0], m[1]);
					community=dropDownModel.getAssignedBlock();
					List<ProjectApiModel> projectList = new ArrayList<ProjectApiModel>();
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("api_block_Project_list")
							.setParameter("block", community).getResultList();
					for (Object[] m1 : x1) {

						ProjectApiModel dropDownModel1 = new ProjectApiModel(m1[0], m1[1]);
						physicalStatusId=dropDownModel1.getProjectId();
						List<ProjectPhysicalStatusModel> physicalStatusList = new ArrayList<ProjectPhysicalStatusModel>();
						List<ProjectPhysicalStatusModel> financialStatusList = new ArrayList<ProjectPhysicalStatusModel>();
						List<RestFloorModel> floorList = new ArrayList<RestFloorModel>();
						List<Object[]> x2 = em.createNamedStoredProcedureQuery("api_Project_details")
								.setParameter("uniqueId", physicalStatusId).getResultList();
						List<Object[]> x4 = em.createNamedStoredProcedureQuery("api_project_financial_details")
								.setParameter("uniqueId", physicalStatusId).getResultList();
						List<Object[]> x5 = em.createNamedStoredProcedureQuery("api_floor_dropdown").getResultList();
						for (Object[] m2 : x2) {
							ProjectPhysicalStatusModel dropDownModel2 = new ProjectPhysicalStatusModel(m2[0],
									m2[1], m2[2], m2[3], m2[4], m2[5], m2[6], m2[7], m2[8], m2[9], m2[10], 
									m2[11],m2[12]);;
//							floorNo=dropDownModel2.getFloorNo();
							physicalStatusList.add(dropDownModel2);
						
		                }
						for (Object[] m4 : x4) {
							ProjectPhysicalStatusModel dropDownModel4 = new ProjectPhysicalStatusModel(m4[0], m4[1], m4[2], m4[3], m4[4], m4[5],m4[6]);;
							financialStatusList.add(dropDownModel4);
							System.out.println("financialStatusList"+financialStatusList);
							
		                }
						for (Object[] m5 : x5) {
							RestFloorModel dropDownModel5 = new RestFloorModel(m5[0], m5[1]);
							floorId=dropDownModel5.getFloorId();
							List<DropDownModel> floorWiseStatusList = new ArrayList<DropDownModel>();
							List<Object[]> x6 = em.createNamedStoredProcedureQuery("block_physicalstatus_dropdown_list")
									.setParameter("projectId", physicalStatusId)
									.setParameter("floorNo", floorId)
									.getResultList();
							System.out.println("physicalStatusId"+physicalStatusId);
							System.out.println("floorId"+floorId);
							System.out.println("x6"+x6);
							for (Object[] m6 : x6) {
								DropDownModel dropDownModel6 = new DropDownModel(m6[0].toString(), m6[1]);
								floorWiseStatusList.add(dropDownModel6);
								
								System.out.println("DATA ::::"+dropDownModel6);
			                }
							dropDownModel5.setFloorWiseStatusList(floorWiseStatusList);
							floorList.add(dropDownModel5);
					
						}
						dropDownModel1.setPhysicalStatuslist(physicalStatusList);
						dropDownModel1.setFinancialList(financialStatusList);
						dropDownModel1.setFloorList(floorList);
						projectList.add(dropDownModel1);
					}
					
					dropDownModel.setProjectlist(projectList);
					requestedBloodBankListModel.add(dropDownModel);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			JsonResponse<List<APIProjectStatusModel>> resp = new JsonResponse<List<APIProjectStatusModel>>();
			resp.setBody(requestedBloodBankListModel);
			resp.setCode("success");
			resp.setMessage("Data Fetched Successfully");
			logger.info("Method in Dao: getrequestedbloodbanklist Dao ends");
			return resp;
		}
		
		
		
		// get Mobile version list
		
		// Language dropdown
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<ApiMobileVersionModel>>> getMobileVersionListDao() {
			logger.info("Method : getMobileVersionListDao Dao starts");

			List<ApiMobileVersionModel> mobileVersionList = new ArrayList<ApiMobileVersionModel>();
			JsonResponse<List<ApiMobileVersionModel>> jsonResponse = new JsonResponse<List<ApiMobileVersionModel>>();
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("get_mobile_version_list").getResultList();
				for (Object[] m : x) {

					ApiMobileVersionModel dropDownModel = new ApiMobileVersionModel(m[0], m[1],m[2],m[3],m[4],m[5]);
					mobileVersionList.add(dropDownModel);
				}
				jsonResponse.setBody(mobileVersionList);

				if (mobileVersionList.size() > 0) {
					jsonResponse.setCode("success");
					jsonResponse.setMessage(ApiResponseMessage.DATA_FETCH_SUCCESS);
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Data not found");
				}

			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage(e.getMessage());
			}
			ResponseEntity<JsonResponse<List<ApiMobileVersionModel>>> response = new ResponseEntity<JsonResponse<List<ApiMobileVersionModel>>>(
					jsonResponse, HttpStatus.OK);
			logger.info("Method : getMobileVersionListDao Dao ends");
			return response;
		}
		
}
