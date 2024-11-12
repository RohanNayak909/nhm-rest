package nirmalya.aatithya.restmodule.admin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.admin.model.RestProjectPhysicalStatusModel;
import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestAdminAddressReportDao {
	Logger logger = LoggerFactory.getLogger(RestAdminAddressReportDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;
	
	@Autowired
	EnvironmentVaribles env;
	
	
	
	//longitude latitude report
			@SuppressWarnings("unchecked")
			public JsonResponse<List<RestProjectPhysicalStatusModel>> ProjectAddressDetailsDao(Integer pageno,
					String district,String jeNum) {
				logger.info("Method : ProjectAddressDetailsDao starts");
				System.out.println("district"+district);
				//System.out.println("block"+block);
				System.out.println("jeNum"+jeNum);
				List<RestProjectPhysicalStatusModel> admin = new ArrayList<RestProjectPhysicalStatusModel>();
				JsonResponse<List<RestProjectPhysicalStatusModel>> resp = new JsonResponse<List<RestProjectPhysicalStatusModel>>();

				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("longitude_latitude_report")
							.setParameter("pageno", pageno)
							.setParameter("district", district)
							//.setParameter("block", block)
							.setParameter("jeNum", jeNum)
							.getResultList();

					for (Object[] m : x) {
						String imageOne = null;
						if (m[6] != null && m[6] != "" && m[6] != " " && !m[6].toString().equals(" ")
								&& !m[6].toString().equals(null) && !m[6].toString().equals("")) {
							imageOne = env.getBaseURL() + "nirmalyaRest/document/document/" + m[6].toString();
						} else {
							imageOne = "";
						}
						
						
						String imageTwo = null;
						if (m[7] != null && m[7] != "" && m[7] != " " && !m[7].toString().equals(" ")
								&& !m[7].toString().equals(null) && !m[7].toString().equals("")) {
							imageTwo = env.getBaseURL() + "nirmalyaRest/document/document/" + m[7].toString();
						} else {
							imageTwo = "";
						}
						
						RestProjectPhysicalStatusModel so = new RestProjectPhysicalStatusModel(m[0],m[1],m[2],
								m[3], m[4], m[5],imageOne, imageTwo,m[8],m[9]);
						admin.add(so);
						//System.out.println("RESRTTTTTTTTTT#" + so);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				resp.setBody(admin);
				logger.info("Method : ProjectAddressDetailsDao ends"+resp);
				return resp;
			}
	

}
