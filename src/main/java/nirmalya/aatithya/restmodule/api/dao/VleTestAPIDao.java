package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.util.Util;
import nirmalya.aatithya.restmodule.api.model.APIPharmacyOrderModel;
import nirmalya.aatithya.restmodule.api.model.RestVenderApiTestModel;
import nirmalya.aatithya.restmodule.api.model.VleTestAPIModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse; 

@Repository
public class VleTestAPIDao {
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;
	
	Logger logger = LoggerFactory.getLogger(PharmacyRestAPIDao.class);
	
	

	@SuppressWarnings("unchecked")

	public JsonResponse<List<VleTestAPIModel>> viewVleVenderTestListDao(String patientId) {

		logger.info("Method in Dao: viewVleVenderTestList Dao starts");

		List<VleTestAPIModel> orderList = new ArrayList<VleTestAPIModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_vlevendelist")
					.getResultList();
			for (Object m : x) {

				VleTestAPIModel reqEdit = new VleTestAPIModel(null, null,null,null ,null, null,null,m);
				orderList.add(reqEdit);
				
				System.out.println("VendorData"+reqEdit);
			}
			/*
			 * Object vleId, Object patientId, Object testId, Object testName, Object
			 * status, Object venderId, Object venderName
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		if (orderList != null) {
			System.out.println("orderList"+orderList);
			for (VleTestAPIModel a : orderList) {
				List<RestVenderApiTestModel> vendorList = new ArrayList<RestVenderApiTestModel>();
				try {
					System.out.println("VenderId" + a.getType());
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("view_vlevender_testlist")
							.setParameter("bookingId", patientId).setParameter("vendorId", a.getType()).getResultList();
					for (Object[] m : x1) {

						RestVenderApiTestModel restVenderApiTestModel = new RestVenderApiTestModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6]);
						
						vendorList.add(restVenderApiTestModel);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				a.setGetvendertestLists(vendorList);
			}
		}

		JsonResponse<List<VleTestAPIModel>> resp = new JsonResponse<List<VleTestAPIModel>>();
		resp.setBody(orderList);
		
		/*
		 * if (resp.) { resp.setCode("success");
		 * resp.setMessage("Data Fetched Successfully"); } else {
		 * resp.setCode("failed"); resp.setMessage("No Data Found"); }
		 */
		
		resp.setCode("success");
		resp.setMessage("Data Fetched Successfully");
		logger.info("Method in Dao: viewVleVenderTestList Dao ends" + resp);
		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<VleTestAPIModel>> getAllLabRegdListViewDao(String id) {

		logger.info("Method : getAllLabRegdListView Dao starts");
		List<VleTestAPIModel> purchaseOrder  = new ArrayList<VleTestAPIModel>();
		JsonResponse<List<VleTestAPIModel>> resp = new JsonResponse<List<VleTestAPIModel>>();
		try {
		
			List<Object[] >x = em.createNamedStoredProcedureQuery("lab_regd_patient_list").setParameter("p_enteredby", id)
					.getResultList();
			for (Object[] m : x) {
				VleTestAPIModel so = new VleTestAPIModel(m[0], m[1], m[2], m[3], m[4], m[5],m[6],m[7],m[8]);
			
				
				purchaseOrder.add(so);
						}
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(purchaseOrder);

		resp.setCode("success");
		resp.setMessage("Data Fetched Successfully");
		logger.info("Method : getAllLabRegdListView Dao ends"+resp);

		return resp;

	}
	
	
}
