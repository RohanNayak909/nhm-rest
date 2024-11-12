package nirmalya.aatithya.restmodule.districtlevel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.dao.RestAdminUserDao;
import nirmalya.aatithya.restmodule.admin.model.RestAdminUserModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.districtlevel.model.RestDistUserModel;

@Repository
public class RestDistrictManageUserDao {
	Logger logger = LoggerFactory.getLogger(RestAdminUserDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	@Autowired
	EnvironmentVaribles env;

	
	//view dist user details

		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestDistUserModel>> restViewUserDistDetails(Integer pageno,String userId) {
			logger.info("Method : viewUserDetailsDistDao starts"+userId);
			List<RestDistUserModel> admin = new ArrayList<RestDistUserModel>();
			JsonResponse<List<RestDistUserModel>> resp = new JsonResponse<List<RestDistUserModel>>();
//			String type="";
//			if(userType.equals("0")) {
//				 type="19";
//				 System.out.println("19");
//			}else if(userType.equals("2")) {
//				 type="21";
//				 System.out.println("21");
//			}
//			else if(userType.equals("3")){
//				 type="22";
//				 System.out.println("22");
//			}
//			else {
//				type="";
//				 System.out.println("sxfvgb");
//			}
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("district_user_view")
						.setParameter("pageno", pageno)
						.setParameter("districtidsss", userId)
						.getResultList();

				for (Object[] m : x) {

					RestDistUserModel so = new RestDistUserModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7],m[8]);
					admin.add(so);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(admin);
			logger.info("Method : viewUserDetailsDistDao ends" + resp);
			return resp;
		}
	
		
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<RestDistUserModel>>> editUserDetailsDist(String id, String mob,String userId) {
			logger.info("Method : editUserDetails starts"+mob);

			JsonResponse<List<RestDistUserModel>> resp = new JsonResponse<List<RestDistUserModel>>();
			List<RestDistUserModel> rs = new ArrayList<RestDistUserModel>();

			try {
				if (id.equalsIgnoreCase("Junior Engineer")) {
					System.out.println("Junior Engineer");
					List<Object[]> x = em.createNamedStoredProcedureQuery("edit_dist_user_je").setParameter("p_id", id)
							.setParameter("p_mob", mob)
							.setParameter("p_distid", userId)
							.getResultList();
					for (Object[] m : x) {

						RestDistUserModel reqemp = new RestDistUserModel(m[0], m[1], m[2], m[3], m[4], m[5], null,m[6],null,m[7]);
						System.out.println("JE Edit Data-" + reqemp);
						rs.add(reqemp);
					}
				} else if (id.equalsIgnoreCase("District Admin")) {
				
					List<Object[]> n = em.createNamedStoredProcedureQuery("edit_user_district").setParameter("p_id", id)
							.setParameter("p_mob", mob).getResultList();
					for (Object[] m : n) {

						RestDistUserModel reqemp = new RestDistUserModel(m[0], m[1], m[2], m[3], null, null, null,null,null,null);
						rs.add(reqemp);
						System.out.println("aaaaaaaaaaaaaaaaaaaaa"+reqemp);
					}
				} else {
					List<Object[]> n = em.createNamedStoredProcedureQuery("edit_user_admin").setParameter("p_id", id)
							.setParameter("p_mob", mob).getResultList();
					for (Object[] m : n) {

						RestDistUserModel reqemp = new RestDistUserModel(m[0], m[1], m[2], m[3], null, null, null,null,null,null);
						rs.add(reqemp);
					}

					System.out.println(rs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(rs);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");

			ResponseEntity<JsonResponse<List<RestDistUserModel>>> response = new ResponseEntity<JsonResponse<List<RestDistUserModel>>>(
					resp, responseHeaders, HttpStatus.CREATED);

			logger.info("Method : editPartSupplier ends"+response);
			return response;
		}
	
	
}
