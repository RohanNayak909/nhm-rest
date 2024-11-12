package nirmalya.aatithya.restmodule.api.dao;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import javax.persistence.EntityManager;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.APIAiplMasterNadiModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentModel;
import nirmalya.aatithya.restmodule.api.model.AyurythmFoodModel;
import nirmalya.aatithya.restmodule.api.model.AyurythmHerbModel;
import nirmalya.aatithya.restmodule.api.model.AyurythmModel;
import nirmalya.aatithya.restmodule.api.model.FileModel;
import nirmalya.aatithya.restmodule.api.model.MedTelModel;
import nirmalya.aatithya.restmodule.api.model.SearchModel;
import nirmalya.aatithya.restmodule.api.model.SparshnaActivitiesModel;
import nirmalya.aatithya.restmodule.api.model.SparshnaResultDesc;
import nirmalya.aatithya.restmodule.api.model.TestGroupModel;
import nirmalya.aatithya.restmodule.api.model.TestModel;
import nirmalya.aatithya.restmodule.api.model.TestReportAPIModel;
import nirmalya.aatithya.restmodule.api.model.WritzoTestModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;
import nirmalya.aatithya.restmodule.util.ApiResponseMessage;
import nirmalya.aatithya.restmodule.util.CurrentDateTime;
import nirmalya.aatithya.restmodule.util.FileWrite;
import nirmalya.aatithya.restmodule.util.GenerateRandomValue;
import nirmalya.aatithya.restmodule.util.StringUtil;
import nirmalya.aatithya.restmodule.util.Util;

@Repository
public class TestAppointmentDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(TestAppointmentDao.class);

	private static final DecimalFormat df = new DecimalFormat("0.00");

	public static ByteBuffer getAsByteArray(URL url) throws IOException {
		ByteArrayOutputStream tmpOut = new ByteArrayOutputStream();

		URLConnection connection = url.openConnection();
		int contentLength = connection.getContentLength();
		InputStream in = url.openStream();
		byte[] buf = new byte[512];
		int len;
		while (true) {
			len = in.read(buf);
			if (len == -1) {
				break;
			}
			tmpOut.write(buf, 0, len);
		}
		tmpOut.close();
		ByteBuffer bb = ByteBuffer.wrap(tmpOut.toByteArray(), 0, tmpOut.size());
		// Lines below used to test if file is corrupt
		// FileOutputStream fos = new FileOutputStream("C:\\abc.pdf");
		// fos.write(tmpOut.toByteArray());
		return bb;
	}

	public ResponseEntity<JsonResponse<Object>> screeningReportByMedTel(MedTelModel data) {
		logger.info("Method : screeningReportByMedTel DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		if (data != null) {

			String jsondata = data.toString();
			System.out.println(jsondata);

			if (data.getScreening_details().size() > 0) {
				JSONArray jsArray = new JSONArray(data.getScreening_details());
				for (int i = 0; i < jsArray.length(); i++) {
					JSONObject jsonObj = null;
					try {
						jsonObj = jsArray.getJSONObject(i);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JSONObject m = null;
					try {
						m = jsonObj.getJSONObject("pocResult");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String k = (String) jsonObj.keys().next();

					Iterator<?> keys = m.keys();

					while (keys.hasNext()) {
						String key = (String) keys.next();

						// System.out.println("Info, Key: " + k + ", value: " + jsonObj.getString(k) + "
						// * " + key + "---" + m.get(key) + " ///// ");

						try {
							if (jsonObj.getString(k) != null && jsonObj.getString(k) != "" && m.get(key) != null
									&& m.get(key) != "") {
								try {

									if (jsonObj.getString(k).equals("ECG")) {
										System.out.println(key + "-----" + m.get(key).toString());
										String url = m.get(key).toString();
										if (key.equals("url") && url != null && url != "") {
											String sdate = data.getScreening_date();
											String data1 = null;
											String[] arr1 = sdate.split("-");
											data1 = arr1[0] + arr1[1] + arr1[2];
											String filedata = data.getPatient_uniqueid() + "_" + data1 + ".pdf";
											System.out.println(env.getEcgUrl() + filedata);
											Path path = Paths.get(env.getEcgUrl() + filedata);
											URL url1 = new URL(url);
											ByteBuffer bb = getAsByteArray(url1);
											byte[] arr = bb.array();
											try {
												Files.write(path, arr);
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}

									@SuppressWarnings("unchecked")
									List<Object[]> x = em.createNamedStoredProcedureQuery("saveMedTelApiData")
											.setParameter("regNo", data.getPatient_uniqueid())
											.setParameter("medTelId", data.getMedteluniqueid())
											.setParameter("thpId", data.getThp_id())
											.setParameter("thpName", data.getThp_name())
											.setParameter("pName", data.getName())
											.setParameter("pMobile", data.getMobile())
											.setParameter("pGender", data.getGender())
											.setParameter("pAge", data.getAge())
											.setParameter("screeningDate", data.getScreening_date())
											.setParameter("reportUrl", data.getReport_url())
											.setParameter("testGroup", jsonObj.getString(k))
											.setParameter("testName", key).setParameter("testResult", m.get(key))
											.setParameter("jsondata", jsondata).getResultList();
									jsonResponse.setBody(x.get(0));
									jsonResponse.setCode("success");
									jsonResponse.setMessage("Data Saved Successfully");

								} catch (Exception e) {
									e.printStackTrace();
									jsonResponse.setCode("failed");
									jsonResponse.setMessage("Something went wrong.");
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

				if (jsonResponse.getCode().equals("success")) {
					byte[] pId = Base64.getEncoder().encode(data.getPatient_uniqueid().getBytes());

					String userid = new String(pId);

					String date = DateFormatter.returnStringDateNew(data.getScreening_date());

					/*
					 * String Url = env.getWebURL() +
					 * "user/view-patient-test-report-pdf-download?id=" + userid + "&id2=" + date;
					 */

					String Url = env.getWebURL() + "user/view-patient-test-report-merge?id=" + userid + "&id2=" + date;

					String msg = "Dear " + data.getName() + ",\n" + "\n"
							+ "Your Report has been generated successfully! Download from link " + Url + "\n\n"
							+ "Regards,\r\n" + "eHealthSystem Team";
					String encodemsg = null;
					try {
						encodemsg = URLEncoder.encode(msg, "UTF-8");
					} catch (UnsupportedEncodingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					Integer smsstatus = 0;
					try {
						smsstatus = CommonUsed.sendSMSReturnResponse(data.getMobile(), encodemsg);

					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : screeningReportByMedTel DAO ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> manualScreeningReportByMedTel(MedTelModel data) {
		logger.info("Method : manualScreeningReportByMedTel DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		if (data != null) {
			if (data.getScreening_details().size() > 0) {
				JSONArray jsArray = new JSONArray(data.getScreening_details());
				for (int i = 0; i < jsArray.length(); i++) {
					JSONObject jsonObj = null;
					try {
						jsonObj = jsArray.getJSONObject(i);
					} catch (JSONException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					JSONObject m = null;
					try {
						m = jsonObj.getJSONObject("pocResult");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String k = (String) jsonObj.keys().next();

					Iterator<?> keys = m.keys();

					while (keys.hasNext()) {
						String key = (String) keys.next();

						// System.out.println("Info, Key: " + k + ", value: " + jsonObj.getString(k) + "
						// * " + key + "---" + m.get(key) + " ///// ");

						try {
							if (jsonObj.getString(k) != null && jsonObj.getString(k) != "" && m.get(key) != null
									&& m.get(key) != "") {
								try {

									@SuppressWarnings("unchecked")
									List<Object[]> x = em.createNamedStoredProcedureQuery("saveMedTelApiData")
											.setParameter("regNo", data.getPatient_uniqueid())
											.setParameter("medTelId", data.getMedteluniqueid())
											.setParameter("thpId", data.getThp_id())
											.setParameter("thpName", data.getThp_name())
											.setParameter("pName", data.getName())
											.setParameter("pMobile", data.getMobile())
											.setParameter("pGender", data.getGender())
											.setParameter("pAge", data.getAge())
											.setParameter("screeningDate", data.getScreening_date())
											.setParameter("reportUrl", data.getReport_url())
											.setParameter("testGroup", jsonObj.getString(k))
											.setParameter("testName", key).setParameter("testResult", m.get(key))
											.getResultList();
									jsonResponse.setBody(x.get(0));
									jsonResponse.setCode("success");
									jsonResponse.setMessage("Data Saved Successfully");
								} catch (Exception e) {
									e.printStackTrace();
									jsonResponse.setCode("failed");
									jsonResponse.setMessage("Something went wrong.");
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : manualScreeningReportByMedTel DAO ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> systemScreeningReport(MedTelModel data) {
		logger.info("Method : systemScreeningReport DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		if (data != null) {

			String jsondata = data.toString();

			String dbilresult = null;
			String tbilresult = null;
			String tpresult = null;
			String abresult = null;
			String chresult = null;
			String tgresult = null;

			String fullname = null;
			String mobile = null;

			if (data.getScreening_details().size() > 0) {
				JSONArray jsArray = new JSONArray(data.getScreening_details());
				for (int i = 0; i < jsArray.length(); i++) {
					JSONObject jsonObj = null;
					try {
						jsonObj = jsArray.getJSONObject(i);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String testId = null;
					try {
						testId = jsonObj.getString("Test ID");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					String testName = jsonObj.getString("TestName");
					String testResult = null;
					try {
						testResult = jsonObj.getString("result");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String unit = null;
					try {
						unit = jsonObj.getString("Unit");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String sampleNo = null;
					try {
						sampleNo = jsonObj.getString("sample number");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String refRange = null;
					try {
						refRange = jsonObj.getString("Reference range");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (testId.equals("3")) {
						tbilresult = testResult;
					}
					if (testId.equals("4")) {
						dbilresult = testResult;
					}
					if (testId.equals("5")) {
						tpresult = testResult;
					}
					if (testId.equals("6")) {
						abresult = testResult;
					}
					if (testId.equals("13")) {
						chresult = testResult;
					}
					if (testId.equals("16")) {
						tgresult = testResult;
					}

					try {

						@SuppressWarnings("unchecked")
						List<Object[]> x = em.createNamedStoredProcedureQuery("saveOtherSystemAPIData")
								.setParameter("regNo", data.getUniqueid()).setParameter("pName", data.getName())
								.setParameter("pMobile", data.getMobile())
								.setParameter("screeningDate", data.getScreening_date())
								.setParameter("testResult", testResult).setParameter("testUnit", unit)
								.setParameter("testId", testId).setParameter("refRange", refRange)
								.setParameter("sampleNo", sampleNo).setParameter("jsondata", jsondata).getResultList();
						jsonResponse.setBody(x.get(0)[0]);
						fullname = x.get(0)[1].toString();
						mobile = x.get(0)[0].toString();
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Data Saved Successfully");
					} catch (Exception e) {
						e.printStackTrace();
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Something went wrong.");
					}

				}
				Boolean booleanval = true;
				if (booleanval) {

					try {

						for (int j = 0; j < 7; j++) {

							String idbil = null;
							String test_id = null;
							if (j == 0) {
								if (tbilresult != null && tbilresult != "" && dbilresult != null && dbilresult != "") {
									Double res = Double.valueOf(tbilresult) - Double.valueOf(dbilresult);
									idbil = df.format(res);
									test_id = "40";
								} else {
									idbil = null;
									test_id = "40";
								}

							}
							if (j == 1) {
								if (tpresult != null && tpresult != "" && abresult != null && abresult != "") {
									Double res = Double.valueOf(tpresult) - Double.valueOf(abresult);
									idbil = df.format(res);
									test_id = "38";
								} else {
									idbil = null;
									test_id = "38";
								}
							}
							if (j == 2) {
								if (tpresult != null && tpresult != "" && abresult != null && abresult != "") {
									Double res = Double.valueOf(abresult)
											/ (Double.valueOf(tpresult) - Double.valueOf(abresult));
									idbil = df.format(res);
									test_id = "39";
								} else {
									idbil = null;
									test_id = "39";
								}
							}
							if (j == 3) {
								if (chresult != null && chresult != "") {
									Double res = Double.valueOf(chresult) / 4;
									idbil = df.format(res);
									test_id = "14";
								} else {
									idbil = null;
									test_id = "14";
								}
							}
							if (j == 4) {
								if (chresult != null && chresult != "") {
									Double res = Double.valueOf(chresult) / (Double.valueOf(chresult) / 4);
									idbil = df.format(res);
									test_id = "105";
								} else {
									idbil = null;
									test_id = "105";
								}
							}
							if (j == 5) {
								if (tgresult != null && tgresult != "") {
									Double res = Double.valueOf(tgresult) / 5;
									idbil = df.format(res);
									test_id = "106";
								} else {
									idbil = null;
									test_id = "106";
								}
							}
							if (j == 6) {
								if (chresult != null && chresult != "" && tgresult != null && tgresult != "") {
									Double res = Double.valueOf(chresult) - (Double.valueOf(chresult) / 4)
											- (Double.valueOf(tgresult) / 5);
									idbil = df.format(res);
									test_id = "15";
								} else {
									idbil = null;
									test_id = "15";
								}
							}

							@SuppressWarnings("unchecked")
							List<Object[]> x = em.createNamedStoredProcedureQuery("saveOtherSystemAPIData")
									.setParameter("regNo", data.getUniqueid()).setParameter("pName", data.getName())
									.setParameter("pMobile", data.getMobile())
									.setParameter("screeningDate", data.getScreening_date())
									.setParameter("testResult", idbil).setParameter("testUnit", null)
									.setParameter("testId", test_id).setParameter("refRange", null)
									.setParameter("sampleNo", null).setParameter("jsondata", jsondata).getResultList();
							jsonResponse.setBody(x.get(0)[0]);
							jsonResponse.setCode("success");
							jsonResponse.setMessage("Data Saved Successfully");
						}

					} catch (Exception e) {
						e.printStackTrace();
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Something went wrong.");
					}
				}

				if (jsonResponse.getCode().equals("success")) {
					byte[] pId = Base64.getEncoder().encode(data.getUniqueid().getBytes());

					String userid = new String(pId);

					String date = DateFormatter.returnStringDateNew(data.getScreening_date());

					/*
					 * String Url = env.getWebURL() +
					 * "user/view-patient-test-report-pdf-download?id=" + userid + "&id2=" + date;
					 */

					String Url = env.getWebURL() + "user/view-patient-test-report-merge?id=" + userid + "&id2=" + date;

					String msg = "Dear " + fullname + ",\n" + "\n"
							+ "Your Report has been generated successfully! Download from link " + Url + "\n\n"
							+ "Regards,\r\n" + "eHealthSystem Team";
					String encodemsg = null;
					try {
						encodemsg = URLEncoder.encode(msg, "UTF-8");
					} catch (UnsupportedEncodingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					Integer smsstatus = 0;
					try {
						smsstatus = CommonUsed.sendSMSReturnResponse(mobile, encodemsg);

					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : systemScreeningReport DAO ends");
		return response;
	}
//	public ResponseEntity<JsonResponse<Object>> systemScreeningReport(MedTelModel data) {
//		logger.info("Method : systemScreeningReport DAO starts");
//		
//		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
//		if (data != null) {
//			
//			String jsondata = data.toString();
//			
//			if (data.getScreening_details().size() > 0) {
//				JSONArray jsArray = new JSONArray(data.getScreening_details());
//				for (int i = 0; i < jsArray.length(); i++) {
//					JSONObject jsonObj = null;
//					try {
//						jsonObj = jsArray.getJSONObject(i);
//					} catch (JSONException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
//					String testId = null;
//					try {
//						testId = jsonObj.getString("Test ID");
//					} catch (JSONException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
////					String testName = jsonObj.getString("TestName");
//					String testResult = null;
//					try {
//						testResult = jsonObj.getString("result");
//					} catch (JSONException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					String unit = null;
//					try {
//						unit = jsonObj.getString("Unit");
//					} catch (JSONException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					String sampleNo = null;
//					try {
//						sampleNo = jsonObj.getString("sample number");
//					} catch (JSONException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					String refRange = null;
//					try {
//						refRange = jsonObj.getString("Reference range");
//					} catch (JSONException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
//					try {
//						
//						@SuppressWarnings("unchecked")
//						List<Object[]> x = em.createNamedStoredProcedureQuery("saveOtherSystemAPIData")
//						.setParameter("regNo", data.getUniqueid()).setParameter("pName", data.getName())
//						.setParameter("pMobile", data.getMobile())
//						.setParameter("screeningDate", data.getScreening_date())
//						.setParameter("testResult", testResult).setParameter("testUnit", unit)
//						.setParameter("testId", testId).setParameter("refRange", refRange)
//						.setParameter("sampleNo", sampleNo)
//						.setParameter("jsondata", jsondata)
//						.getResultList();
//						jsonResponse.setBody(x.get(0));
//						jsonResponse.setCode("success");
//						jsonResponse.setMessage("Data Saved Successfully");
//					} catch (Exception e) {
//						e.printStackTrace();
//						jsonResponse.setCode("failed");
//						jsonResponse.setMessage("Something went wrong.");
//					}
//					
//				}
//				
//				if(jsonResponse.getCode().equals("success")) {
//					byte[] pId = Base64.getEncoder().encode(data.getUniqueid().getBytes());
//					
//					String userid = new String(pId);
//					
//					String date = DateFormatter.returnStringDateNew(data.getScreening_date());
//					
//					String Url = "https://ehealthsystem.com/user/view-patient-test-report-pdf-download?id=" + userid + "&id2=" + date;
//					
//					String msg = "Dear " + data.getName() + ",\n"
//							+ "\n"
//							+ "Your Report has been generated successfully! Download from link " + Url
//							+ "\n\n"
//							+ "Regards,\r\n"
//							+ "eHealthSystem Team";
//					String encodemsg = null;
//					try {
//						encodemsg = URLEncoder.encode(msg, "UTF-8");
//					} catch (UnsupportedEncodingException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//					
//					Integer smsstatus = 0;
//					try {
//						smsstatus = CommonUsed.sendSMSReturnResponse(data.getMobile(), encodemsg);
//						
//					} catch (ClientProtocolException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			}
//		}
//		
//		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
//				HttpStatus.OK);
//		
//		logger.info("Method : systemScreeningReport DAO ends");
//		return response;
//	}

	public ResponseEntity<JsonResponse<Object>> systemScreeningReportUKD(MedTelModel data) {
		logger.info("Method : systemScreeningReportUKD DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		if (data != null) {

			String jsondata = data.toString();

			String dbilresult = null;
			String tbilresult = null;
			String tpresult = null;
			String abresult = null;
			String chresult = null;
			String tgresult = null;

			String fullname = null;
			String mobile = null;

			if (data.getScreening_details().size() > 0) {
				JSONArray jsArray = new JSONArray(data.getScreening_details());
				for (int i = 0; i < jsArray.length(); i++) {
					JSONObject jsonObj = null;
					try {
						jsonObj = jsArray.getJSONObject(i);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String testId = null;
					try {
						testId = jsonObj.getString("Test ID");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					String testName = jsonObj.getString("TestName");
					String testResult = null;
					try {
						testResult = jsonObj.getString("result");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String unit = null;
					try {
						unit = jsonObj.getString("Unit");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String sampleNo = null;
					try {
						sampleNo = jsonObj.getString("sample number");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String refRange = null;
					try {
						refRange = jsonObj.getString("Reference range");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (testId.equals("3")) {
						tbilresult = testResult;
					}
					if (testId.equals("4")) {
						dbilresult = testResult;
					}
					if (testId.equals("5")) {
						tpresult = testResult;
					}
					if (testId.equals("6")) {
						abresult = testResult;
					}
					if (testId.equals("13")) {
						chresult = testResult;
					}
					if (testId.equals("16")) {
						tgresult = testResult;
					}

					try {

						@SuppressWarnings("unchecked")
						List<Object[]> x = em.createNamedStoredProcedureQuery("saveOtherSystemAPIDataUKD")
								.setParameter("regNo", data.getUniqueid()).setParameter("pName", data.getName())
//						.setParameter("pMobile", data.getMobile())
								.setParameter("screeningDate", data.getScreening_date())
								.setParameter("testResult", testResult).setParameter("testUnit", unit)
								.setParameter("testId", testId).setParameter("refRange", refRange)
								.setParameter("sampleNo", sampleNo).setParameter("jsondata", jsondata).getResultList();
						jsonResponse.setBody(x.get(0)[0]);
						fullname = x.get(0)[1].toString();
						mobile = x.get(0)[0].toString();
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Data Saved Successfully");
					} catch (Exception e) {
						e.printStackTrace();
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Something went wrong.");
					}

				}

				Boolean booleanval = true;
				if (booleanval) {

					try {

						for (int j = 0; j < 7; j++) {

							String idbil = null;
							String test_id = null;
							if (j == 0) {
								if (tbilresult != null && tbilresult != "" && dbilresult != null && dbilresult != "") {
									Double res = Double.valueOf(tbilresult) - Double.valueOf(dbilresult);
									idbil = df.format(res);
									test_id = "40";
								} else {
									idbil = null;
									test_id = "40";
								}

							}
							if (j == 1) {
								if (tpresult != null && tpresult != "" && abresult != null && abresult != "") {
									Double res = Double.valueOf(tpresult) - Double.valueOf(abresult);
									idbil = df.format(res);
									test_id = "38";
								} else {
									idbil = null;
									test_id = "38";
								}
							}
							if (j == 2) {
								if (tpresult != null && tpresult != "" && abresult != null && abresult != "") {
									Double res = Double.valueOf(abresult)
											/ (Double.valueOf(tpresult) - Double.valueOf(abresult));
									idbil = df.format(res);
									test_id = "39";
								} else {
									idbil = null;
									test_id = "39";
								}
							}
							if (j == 3) {
								if (chresult != null && chresult != "") {
									Double res = Double.valueOf(chresult) / 4;
									idbil = df.format(res);
									test_id = "14";
								} else {
									idbil = null;
									test_id = "14";
								}
							}
							if (j == 4) {
								if (chresult != null && chresult != "") {
									Double res = Double.valueOf(chresult) / (Double.valueOf(chresult) / 4);
									idbil = df.format(res);
									test_id = "105";
								} else {
									idbil = null;
									test_id = "105";
								}
							}
							if (j == 5) {
								if (tgresult != null && tgresult != "") {
									Double res = Double.valueOf(tgresult) / 5;
									idbil = df.format(res);
									test_id = "106";
								} else {
									idbil = null;
									test_id = "106";
								}
							}
							if (j == 6) {
								if (chresult != null && chresult != "" && tgresult != null && tgresult != "") {
									Double res = Double.valueOf(chresult) - (Double.valueOf(chresult) / 4)
											- (Double.valueOf(tgresult) / 5);
									idbil = df.format(res);
									test_id = "15";
								} else {
									idbil = null;
									test_id = "15";
								}
							}

							@SuppressWarnings("unchecked")
							List<Object[]> x = em.createNamedStoredProcedureQuery("saveOtherSystemAPIDataUKD")
									.setParameter("regNo", data.getUniqueid()).setParameter("pName", data.getName())
									.setParameter("screeningDate", data.getScreening_date())
									.setParameter("testResult", idbil).setParameter("testUnit", null)
									.setParameter("testId", test_id).setParameter("refRange", null)
									.setParameter("sampleNo", null).setParameter("jsondata", jsondata).getResultList();
							jsonResponse.setBody(x.get(0)[0]);
							jsonResponse.setCode("success");
							jsonResponse.setMessage("Data Saved Successfully");
						}

					} catch (Exception e) {
						e.printStackTrace();
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Something went wrong.");
					}
				}

				if (jsonResponse.getCode().equals("success")) {
					byte[] pId = Base64.getEncoder().encode(data.getUniqueid().getBytes());

					String userid = new String(pId);

					String date = DateFormatter.returnStringDateNew(data.getScreening_date());

					/*
					 * String Url = env.getWebURL() +
					 * "user/view-patient-test-report-pdf-download?id=" + userid + "&id2=" + date;
					 */

					String Url = env.getWebURL() + "user/view-patient-test-report-merge?id=" + userid + "&id2=" + date;

					String msg = "Dear " + fullname + ",\n" + "\n"
							+ "Your Report has been generated successfully! Download from link " + Url + "\n\n"
							+ "Regards,\r\n" + "eHealthSystem Team";
					String encodemsg = null;
					try {
						encodemsg = URLEncoder.encode(msg, "UTF-8");
					} catch (UnsupportedEncodingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					Integer smsstatus = 0;
					try {
						System.out.println(mobile);
						smsstatus = CommonUsed.sendSMSReturnResponse(mobile, encodemsg);

					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : systemScreeningReportUKD DAO ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> saveTestReport(TestReportAPIModel data) {
		logger.info("Method : saveTestReport DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		if (data != null) {
			if (data.getScreentestreportlist().size() > 0) {

				String sdate = null;
				String cdate = null;

				sdate = DateFormatter.getStringDateNew(data.getScreentestdate());
				cdate = DateFormatter.getStringDateNew(data.getCreateddate());

				for (int i = 0; i < data.getScreentestreportlist().size(); i++) {

					try {

						@SuppressWarnings("unchecked")
						List<Object[]> x = em.createNamedStoredProcedureQuery("save_screen_test_report_data")
								.setParameter("uniqueid", data.getUniqueid()).setParameter("name", data.getName())
								.setParameter("mobile", data.getMobile()).setParameter("aadhaar", data.getAadhaar())
								.setParameter("gender", data.getGender()).setParameter("age", data.getAge())
								.setParameter("address", data.getAddress()).setParameter("phc", data.getPhc())
								.setParameter("screentestdate", sdate).setParameter("createddate", cdate)
								.setParameter("testgroup", data.getScreentestreportlist().get(i).getTestgroup())
								.setParameter("testname", data.getScreentestreportlist().get(i).getTestname())
								.setParameter("testresult", data.getScreentestreportlist().get(i).getTestresult())
								.setParameter("unit", data.getScreentestreportlist().get(i).getUnit())
								.setParameter("fromrange", data.getScreentestreportlist().get(i).getFromrange())
								.setParameter("torange", data.getScreentestreportlist().get(i).getTorange())
								.setParameter("refrange", data.getScreentestreportlist().get(i).getRefrange())
								.setParameter("status", data.getScreentestreportlist().get(i).getStatus())
								.getResultList();
						jsonResponse.setBody(x.get(0));
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Data Saved Successfully");
					} catch (Exception e) {
						e.printStackTrace();
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Something went wrong.");
					}

				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : systemScreeningReport DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<SearchModel>>> getDataByPatId(String regNo) {
		logger.info("Method : getDataByPatId Dao starts");

		System.out.println(regNo);

		List<SearchModel> countryList = new ArrayList<SearchModel>();
		JsonResponse<List<SearchModel>> jsonResponse = new JsonResponse<List<SearchModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("searchByPatientId").setParameter("regNo", regNo)
					.getResultList();
			for (Object[] m : x) {

				String gender = null;

				if (m[4] != null) {
					if (m[4].toString().contentEquals("1")) {
						gender = "Male";
					} else if (m[4].toString().contentEquals("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				SearchModel dropDownModel = new SearchModel(m[0], m[1], m[2], m[3], gender);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<SearchModel>>> response = new ResponseEntity<JsonResponse<List<SearchModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getDataByPatId Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> screenTestEntry(TestModel data) {
		logger.info("Method : screenTestEntry DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getRegNo() == null || data.getRegNo() == "") {
			jsonResponse.setMessage("Registration Number Required");
			validity = false;
		} else if (data.getAppontdt() == null || data.getAppontdt() == "") {
			jsonResponse.setMessage("Appointment Date Required");
			validity = false;
		} else if (data.getApponttime() == null || data.getApponttime() == "") {
			jsonResponse.setMessage("Appointment Time Required");
			validity = false;
		}

		if (validity) {
			try {
				String date = null;
				if (data.getAppontdt() != null && data.getAppontdt() != "") {
					date = DateFormatter.getStringDateNew(data.getAppontdt());
				}

				List<Object[]> x = em.createNamedStoredProcedureQuery("screeningTestAppointment")
						.setParameter("regNo", data.getRegNo()).setParameter("testDate", date)
						.setParameter("testTime", data.getApponttime()).getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Appointment Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong. Appointment failed.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : screenTestEntry DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> screenCheckUpEntry(TestModel data) {
		logger.info("Method : screenCheckUpEntry DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getRegNo() == null || data.getRegNo() == "") {
			jsonResponse.setMessage("Registration Number Required");
			validity = false;
		} else if (data.getAppontdt() == null || data.getAppontdt() == "") {
			jsonResponse.setMessage("Appointment Date Required");
			validity = false;
		} else if (data.getApponttime() == null || data.getApponttime() == "") {
			jsonResponse.setMessage("Appointment Time Required");
			validity = false;
		}

		if (validity) {
			try {
				String date = null;
				if (data.getAppontdt() != null && data.getAppontdt() != "") {
					date = DateFormatter.getStringDateNew(data.getAppontdt());
				}

				List<Object[]> x = em.createNamedStoredProcedureQuery("screeningChkUpAppointment")
						.setParameter("regNo", data.getRegNo()).setParameter("testDate", date)
						.setParameter("testTime", data.getApponttime()).getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Appointment Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong. Appointment failed.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : screenCheckUpEntry DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> getScreeningTestListAppointmentByDate(
			String appointDate, String labid) {
		logger.info("Method : getScreeningTestListAppointmentByDate Dao starts");

		logger.info("labid" + labid);

		List<AppointmentModel> countryList = new ArrayList<AppointmentModel>();
		JsonResponse<List<AppointmentModel>> jsonResponse = new JsonResponse<List<AppointmentModel>>();
		try {

			if (appointDate != null && appointDate != "") {
				appointDate = DateFormatter.getStringDateNew(appointDate);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("appointmentScreenTestListData")
					.setParameter("appointDate", appointDate).setParameter("labid", labid).getResultList();
			for (Object[] m : x) {

				String gender = null;
				if (m[4] != null) {
					if (m[4].toString().contentEquals("1")) {
						gender = "Male";
					} else if (m[4].toString().contentEquals("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				String appStatusName = null;
				if (m[8] != null) {
					if (m[8].toString().contentEquals("0")) {
						appStatusName = "Booked";
					} else if (m[8].toString().contentEquals("1")) {
						appStatusName = "In Progress";
					} else {
						appStatusName = "Completed";
					}
				}

				AppointmentModel dropDownModel = new AppointmentModel(m[0], m[1], m[2], m[3], gender, m[5], m[6], m[7],
						m[8], appStatusName, null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				Util.setJsonResponse(jsonResponse, countryList, ResponseStatus.success,
						ApiResponseMessage.DATA_FETCH_SUCCESS);
			} else {
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AppointmentModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getScreeningTestListAppointmentByDate Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> getScreeningCheckUpListAppointmentByDate(
			String appointDate) {
		logger.info("Method : getScreeningCheckUpListAppointmentByDate Dao starts");

		List<AppointmentModel> countryList = new ArrayList<AppointmentModel>();
		JsonResponse<List<AppointmentModel>> jsonResponse = new JsonResponse<List<AppointmentModel>>();
		try {

			if (appointDate != null && appointDate != "") {
				appointDate = DateFormatter.getStringDateNew(appointDate);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("appointmentScreenChkUpListData")
					.setParameter("appointDate", appointDate).getResultList();
			for (Object[] m : x) {

				String gender = null;
				if (m[4] != null) {
					if (m[4].toString().contentEquals("1")) {
						gender = "Male";
					} else if (m[4].toString().contentEquals("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				String appStatusName = null;
				if (m[8] != null) {
					if (m[8].toString().contentEquals("0")) {
						appStatusName = "Booked";
					} else if (m[8].toString().contentEquals("1")) {
						appStatusName = "In Progress";
					} else {
						appStatusName = "Completed";
					}
				}

				AppointmentModel dropDownModel = new AppointmentModel(m[0], m[1], m[2], m[3], gender, m[5], m[6], m[7],
						m[8], appStatusName, null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AppointmentModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getScreeningCheckUpListAppointmentByDate Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changeScreentTestStatus(TestModel data) {
		logger.info("Method : changeScreentTestStatus DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getId() == null || data.getId() == "") {
			jsonResponse.setMessage("Screening Test ID Required");
			validity = false;
		} else if (data.getAppontstatus() == null || data.getAppontstatus() == "") {
			jsonResponse.setMessage("Appointment Status Required");
			validity = false;
		}

		if (validity) {
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("changeScreeningTestStatus")
						.setParameter("screenTestId", data.getId())
						.setParameter("screenTestStatus", data.getAppontstatus()).getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Status Changed Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : changeScreentTestStatus DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changeCheckUpStatus(TestModel data) {
		logger.info("Method : changeCheckUpStatus DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getId() == null || data.getId() == "") {
			jsonResponse.setMessage("Health Check Up ID Required");
			validity = false;
		} else if (data.getAppontstatus() == null || data.getAppontstatus() == "") {
			jsonResponse.setMessage("Appointment Status Required");
			validity = false;
		}

		if (validity) {
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("changeCheckUpStatus")
						.setParameter("screenTestId", data.getId())
						.setParameter("screenTestStatus", data.getAppontstatus()).getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Status Changed Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : changeCheckUpStatus DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> updateSMSStatus(List<MedTelModel> data) {
		logger.info("Method : updateSMSStatus DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (validity) {
			try {

				for (MedTelModel m : data) {

					byte[] pId = Base64.getEncoder().encode(m.getPatient_uniqueid().getBytes());

					String userid = new String(pId);

					String date = DateFormatter.returnStringDateNew(m.getScreening_date());

					/*
					 * String Url = env.getWebURL() +
					 * "user/view-patient-test-report-pdf-download?id=" + userid + "&id2=" + date;
					 */

					String Url = env.getWebURL() + "user/view-patient-test-report-merge?id=" + userid + "&id2=" + date;

					String msg = "Dear " + m.getName() + ",\n" + "\n"
							+ "Your Report has been generated successfully! Download from link " + Url + "\n\n"
							+ "Regards,\r\n" + "eHealthSystem Team";
					String encodemsg = null;
					try {
						encodemsg = URLEncoder.encode(msg, "UTF-8");
					} catch (UnsupportedEncodingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					Integer smsstatus = 0;
					try {
						smsstatus = CommonUsed.sendSMSReturnResponse(m.getMobile(), encodemsg);

					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (smsstatus == 200) {
						try {
							String status = "Yes";

							List<Object[]> x = em.createNamedStoredProcedureQuery("lab_update_sms_status")
									.setParameter("patientid", m.getPatient_uniqueid())
									.setParameter("testdate", m.getScreening_date()).setParameter("smsstatus", status)
									.getResultList();

							jsonResponse.setCode("success");
							jsonResponse.setMessage("Status Changed Successfully");
						} catch (Exception e) {
							e.printStackTrace();
							jsonResponse.setCode("failed");
							jsonResponse.setMessage("Something went wrong.");
						}

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : updateSMSStatus DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> viewMedTeltestListdata(String userid, String page,
			String search) {
		logger.info("Method : viewMedTeltestListdata Dao starts");

		List<MedTelModel> countryList = new ArrayList<MedTelModel>();
		JsonResponse<List<MedTelModel>> jsonResponse = new JsonResponse<List<MedTelModel>>();
		try {
			Integer pageno = Integer.parseInt(page);
			System.out.println(pageno + "------" + userid + "-----------" + search);
			List<Object[]> x = em.createNamedStoredProcedureQuery("viewMedTeltestList").setParameter("userid", userid)
					.setParameter("start", pageno).setParameter("search", search).getResultList();
			for (Object[] m : x) {

				System.out.println(m[7]);

				String url = null;
				if (m[8] != null) {
					byte[] encodeId = Base64.getEncoder().encode(m[8].toString().getBytes());
					String id = new String(encodeId);
					String ndate = null;
					if (m[7] != null) {
						ndate = DateFormatter.returnStringDateNew(m[7].toString());
					}
					// url = env.getWebURL() + "user/view-patient-test-report-pdf-download?id=" + id
					// + "&id2=" + ndate;

					url = env.getWebURL() + "user/view-patient-test-report-merge?id=" + id + "&id2=" + ndate;
//					url = "http://ehealthsystem.com/user/view-patient-test-report-pdf-download?id="+id;
				}

				MedTelModel dropDownModel = new MedTelModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], url,
						m[10]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<MedTelModel>>> response = new ResponseEntity<JsonResponse<List<MedTelModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : viewMedTeltestListdata Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> userViewTestListdata(String page, String search) {
		logger.info("Method : userViewTestListdata Dao starts");

		List<MedTelModel> countryList = new ArrayList<MedTelModel>();
		JsonResponse<List<MedTelModel>> jsonResponse = new JsonResponse<List<MedTelModel>>();
		try {
			Integer pageno = Integer.parseInt(page);
			System.out.println(pageno);
			List<Object[]> x = em.createNamedStoredProcedureQuery("userViewTestList").setParameter("start", pageno)
					.setParameter("search", search).getResultList();
			for (Object[] m : x) {

				String url = null;
				if (m[0] != null) {
					byte[] encodeId = Base64.getEncoder().encode(m[0].toString().getBytes());
					String id = new String(encodeId);
					Object date = null;
					String ndate = null;
					if (m[2] != null) {
						// date = DateFormatter.returnStringDate(m[2]);
						ndate = m[2].toString();
					}
					/*
					 * url = env.getWebURL() + "user/view-patient-test-report-pdf-download?id=" + id
					 * + "&id2=" + ndate;
					 */

					url = env.getWebURL() + "user/view-patient-test-report-merge?id=" + id + "&id2=" + ndate;

//					url = "http://ehealthsystem.com/user/view-patient-test-report-pdf-download?id="+id;
				}

				MedTelModel dropDownModel = new MedTelModel(m[0], m[1], m[2], m[3], url, m[4]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<MedTelModel>>> response = new ResponseEntity<JsonResponse<List<MedTelModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : userViewTestListdata Dao ends");
		return response;
	}

	/*
	 * (through user id)
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> viewMedTeltestListdataId(String regno) {
		logger.info("Method : viewMedTeltestListdataId Dao starts");

		List<MedTelModel> countryList = new ArrayList<MedTelModel>();
		JsonResponse<List<MedTelModel>> jsonResponse = new JsonResponse<List<MedTelModel>>();
		try {
			// String regno1="9121448674403477" ;
			List<Object[]> x = em.createNamedStoredProcedureQuery("viewMedTeltestListId").setParameter("regno", regno)
					.getResultList();
			for (Object[] m : x) {
				String url = null;
				if (m[8] != null) {
					byte[] encodeId = Base64.getEncoder().encode(m[8].toString().getBytes());
					String id = new String(encodeId);
					Object date = null;
					String ndate = null;
					if (m[7] != null) {
						// date = DateFormatter.returnStringDate(m[7]);
						ndate = m[7].toString();
					}
					/*
					 * url =env.getWebURL()+"user/view-patient-test-report-pdf-download?id=" + id +
					 * "&id2=" + ndate;
					 */

					url = env.getWebURL() + "user/view-patient-test-report-merge?id=" + id + "&id2=" + ndate;
//					url = "http://ehealthsystem.com/user/view-patient-test-report-pdf-download?id="+id;
				}

				MedTelModel dropDownModel = new MedTelModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], url,
						m[10]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<MedTelModel>>> response = new ResponseEntity<JsonResponse<List<MedTelModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : viewMedTeltestListdataId Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> viewMedTelTestDetails(String medtelid) {
		logger.info("Method : viewMedTelTestDetails Dao starts");

		List<TestGroupModel> countryList = new ArrayList<TestGroupModel>();
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("viewMedTeltestDetails")
					.setParameter("medteluniqueid", medtelid).getResultList();
			for (Object[] m : x) {
				TestGroupModel dropDownModel = new TestGroupModel(m[0], m[1], m[2]);
				countryList.add(dropDownModel);
			}
			List<String> groupList = new ArrayList<String>();
			for (TestGroupModel m : countryList) {
				groupList.add(m.getTestgroup());
			}
			HashSet<String> hset = new HashSet<String>(groupList);
			System.out.println(hset);

			HashMap<String, HashMap<String, String>> map = new HashMap<>();

			for (String m : hset) {
				HashMap<String, String> map1 = new HashMap<>();
				for (TestGroupModel a : countryList) {
					if (m.contentEquals(a.getTestgroup())) {
						map1.put(a.getTestname(), a.getTestresult());
					}
				}
				map.put(m, map1);
			}

			System.out.println(map);
			jsonResponse.setBody(map);
			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : viewMedTelTestDetails Dao ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> systemScreeningReportWithECG(String data, String patientId,
			String screenDate, JSONArray jsArray) {
		logger.info("Method : systemScreeningReportWithECG DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		if (!StringUtil.isNull(patientId)) {

			String dbilresult = null;
			String tbilresult = null;
			String tpresult = null;
			String abresult = null;
			String chresult = null;
			String tgresult = null;

			String fullname = null;
			String mobile = null;

			if (jsArray.length() > 0) {
				for (int i = 0; i < jsArray.length(); i++) {
					JSONObject jsonObj = null;
					try {
						jsonObj = jsArray.getJSONObject(i);
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String testId = null;
					try {
						testId = jsonObj.getString("Test ID");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String testResult = null;
					try {
						testResult = jsonObj.getString("result");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String unit = null;
					try {
						unit = jsonObj.getString("Unit");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String sampleNo = null;
					try {
						sampleNo = jsonObj.getString("sample number");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String refRange = null;
					try {
						refRange = jsonObj.getString("Reference range");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					if (testId.equals("3")) {
						tbilresult = testResult;
					}
					if (testId.equals("4")) {
						dbilresult = testResult;
					}
					if (testId.equals("5")) {
						tpresult = testResult;
					}
					if (testId.equals("6")) {
						abresult = testResult;
					}
					if (testId.equals("13")) {
						chresult = testResult;
					}
					if (testId.equals("16")) {
						tgresult = testResult;
					}

					try {

						logger.info(i + "  " + testId + " ** " + testResult);

						@SuppressWarnings("unchecked")
						List<Object[]> x = em.createNamedStoredProcedureQuery("saveOtherSystemAPIDataUKD")
								.setParameter("regNo", patientId).setParameter("pName", null)
								.setParameter("screeningDate", screenDate).setParameter("testResult", testResult)
								.setParameter("testUnit", unit).setParameter("testId", testId)
								.setParameter("refRange", refRange).setParameter("sampleNo", sampleNo)
								.setParameter("jsondata", data).getResultList();
						fullname = x.get(0)[1].toString();
						mobile = x.get(0)[0].toString();
						Util.setJsonResponse(jsonResponse, x.get(0)[0], ResponseStatus.success,
								ApiResponseMessage.SAVED_SUCCESSFULLY);
					} catch (Exception e) {
						e.printStackTrace();
						Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
								ApiResponseMessage.UNKNOWN_EXCEPTION);
					}

				}
				Boolean booleanval = true;
				if (booleanval) {

					try {

						for (int j = 0; j < 7; j++) {

							String idbil = null;
							String test_id = null;
							if (j == 0) {
								if (!StringUtil.isNull(tbilresult) && !StringUtil.isNull(dbilresult)) {
									Double res = Double.valueOf(tbilresult) - Double.valueOf(dbilresult);
									idbil = df.format(res);
									test_id = "40";
								} else {
									idbil = null;
									test_id = "40";
								}

							}
							if (j == 1) {
								if (!StringUtil.isNull(tpresult) && !StringUtil.isNull(abresult)) {
									Double res = Double.valueOf(tpresult) - Double.valueOf(abresult);
									idbil = df.format(res);
									test_id = "38";
								} else {
									idbil = null;
									test_id = "38";
								}
							}
							if (j == 2) {
								if (!StringUtil.isNull(tpresult) && !StringUtil.isNull(abresult)) {
									Double res = Double.valueOf(abresult)
											/ (Double.valueOf(tpresult) - Double.valueOf(abresult));
									idbil = df.format(res);
									test_id = "39";
								} else {
									idbil = null;
									test_id = "39";
								}
							}
							if (j == 3) {
								if (!StringUtil.isNull(chresult)) {
									Double res = Double.valueOf(chresult) / 4;
									idbil = df.format(res);
									test_id = "14";
								} else {
									idbil = null;
									test_id = "14";
								}
							}
							if (j == 4) {
								if (!StringUtil.isNull(chresult)) {
									Double res = Double.valueOf(chresult) / (Double.valueOf(chresult) / 4);
									idbil = df.format(res);
									test_id = "105";
								} else {
									idbil = null;
									test_id = "105";
								}
							}
							if (j == 5) {
								if (!StringUtil.isNull(tgresult)) {
									Double res = Double.valueOf(tgresult) / 5;
									idbil = df.format(res);
									test_id = "106";
								} else {
									idbil = null;
									test_id = "106";
								}
							}
							if (j == 6) {
								if (!StringUtil.isNull(chresult) && !StringUtil.isNull(tgresult)) {
									Double res = Double.valueOf(chresult) - (Double.valueOf(chresult) / 4)
											- (Double.valueOf(tgresult) / 5);
									idbil = df.format(res);
									test_id = "15";
								} else {
									idbil = null;
									test_id = "15";
								}
							}

							logger.info(j + "  " + test_id + "  **  " + idbil);

							@SuppressWarnings("unchecked")
							List<Object[]> x = em.createNamedStoredProcedureQuery("saveOtherSystemAPIDataUKD")
									.setParameter("regNo", patientId).setParameter("pName", null)
									.setParameter("screeningDate", screenDate).setParameter("testResult", idbil)
									.setParameter("testUnit", null).setParameter("testId", test_id)
									.setParameter("refRange", null).setParameter("sampleNo", null)
									.setParameter("jsondata", data).getResultList();
							Util.setJsonResponse(jsonResponse, x.get(0)[0], ResponseStatus.success,
									ApiResponseMessage.SAVED_SUCCESSFULLY);
						}

					} catch (Exception e) {
						e.printStackTrace();
						Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
								ApiResponseMessage.UNKNOWN_EXCEPTION);
					}
				}

				if (jsonResponse.getCode().equals("success")) {
					byte[] pId = Base64.getEncoder().encode(patientId.getBytes());

					String userid = new String(pId);

					String date = DateFormatter.returnStringDateNew(screenDate);

					/*
					 * String Url = env.getWebURL()+"user/view-patient-test-report-pdf-download?id="
					 * + userid + "&id2=" + date;
					 */

					String Url = env.getWebURL() + "user/view-patient-test-report-merge?id=" + userid + "&id2=" + date;

					String msg = "Dear " + fullname + ",\n" + "\n"
							+ "Your Report has been generated successfully! Download from link " + Url + "\n\n"
							+ "Regards,\r\n" + "eHealthSystem Team";
					String encodemsg = null;
					try {
						encodemsg = URLEncoder.encode(msg, "UTF-8");
					} catch (UnsupportedEncodingException e2) {
						e2.printStackTrace();
					}

					Integer smsstatus = 0;
					try {
						smsstatus = CommonUsed.sendSMSReturnResponse(mobile, encodemsg);

					} catch (ClientProtocolException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : systemScreeningReportWithECG DAO ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> postECGTestReportNasan(String data, String patientId, String screenDate,
			Map<String, Object> obj) {
		logger.info("Method : postECGTestReportNasan DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		String filePath = env.getLogfiles() + "ecg_log.txt";

		if (!StringUtil.isNull(patientId)) {

			if (validity) {
				try {

					logger.info(data + "  " + patientId + " ** " + screenDate);

					@SuppressWarnings("unchecked")
					List<Object[]> x = em.createNamedStoredProcedureQuery("save_nasan_test_report_data")
							.setParameter("regNo", patientId).setParameter("screeningDate", screenDate)
							.setParameter("testResult", data).setParameter("testUnit", null)
							.setParameter("testId", "1000").setParameter("refRange", null)
							.setParameter("sampleNo", null).setParameter("jsondata", obj.toString()).getResultList();

					FileWrite.ecgLogWrite(filePath, patientId, ApiResponseMessage.SAVED_SUCCESSFULLY);
					Util.setJsonResponse(jsonResponse, x.get(0)[0], ResponseStatus.success,
							ApiResponseMessage.SAVED_SUCCESSFULLY);
				} catch (Exception e) {
					e.printStackTrace();
					FileWrite.ecgLogWrite(filePath, patientId, e.getMessage());
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
							ApiResponseMessage.UNKNOWN_EXCEPTION);
				}
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postECGTestReportNasan DAO ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> postTestReportNasan(MedTelModel data) {
		logger.info("Method : postTestReportNasan DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		if (data != null) {

			String jsondata = data.toString();

			String fullname = null;
			String mobile = null;

			String bpSys = null;
			String bpDia = null;

			if (data.getScreening_details().size() > 0) {
				JSONArray jsArray = new JSONArray(data.getScreening_details());
				for (int i = 0; i < jsArray.length(); i++) {
					JSONObject jsonObj = null;
					try {
						jsonObj = jsArray.getJSONObject(i);
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String testId = null;
					try {
						testId = jsonObj.getString("testId");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String testResult = null;
					try {
						testResult = jsonObj.getString("testResult");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String unit = null;
					try {
						unit = jsonObj.getString("testUnit");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String sampleNo = null;
					try {
						sampleNo = jsonObj.getString("sampleNumber");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					String refRange = null;
					try {
						refRange = jsonObj.getString("refRange");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}

					if (testId.equals("3")) {
						bpSys = testResult;
					}
					if (testId.equals("4")) {
						bpDia = testResult;
					}

					try {

						@SuppressWarnings("unchecked")
						List<Object[]> x = em.createNamedStoredProcedureQuery("save_nasan_test_report_data")
								.setParameter("regNo", data.getUniqueid())
								.setParameter("screeningDate", data.getScreening_date())
								.setParameter("testResult", testResult).setParameter("testUnit", unit)
								.setParameter("testId", testId).setParameter("refRange", refRange)
								.setParameter("sampleNo", sampleNo).setParameter("jsondata", jsondata).getResultList();
						jsonResponse.setBody(x.get(0)[0]);
						fullname = x.get(0)[1].toString();
						mobile = x.get(0)[0].toString();
						Util.setJsonResponse(jsonResponse, x.get(0)[0], ResponseStatus.success,
								ApiResponseMessage.SAVED_SUCCESSFULLY);
//						jsonResponse.setCode("success");
//						jsonResponse.setMessage("Data Saved Successfully");
					} catch (Exception e) {
						e.printStackTrace();
						Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
//						jsonResponse.setCode("failed");
//						jsonResponse.setMessage("Something went wrong.");
					}

				}

				try {

					String testResult = bpSys + "/" + bpDia;

					@SuppressWarnings("unchecked")
					List<Object[]> x = em.createNamedStoredProcedureQuery("save_nasan_test_report_data")
							.setParameter("regNo", data.getUniqueid())
							.setParameter("screeningDate", data.getScreening_date())
							.setParameter("testResult", testResult).setParameter("testUnit", "mmHg")
							.setParameter("testId", "999").setParameter("refRange", "> 120/80")
							.setParameter("sampleNo", "1").setParameter("jsondata", jsondata).getResultList();
					jsonResponse.setBody(x.get(0)[0]);
					fullname = x.get(0)[1].toString();
					mobile = x.get(0)[0].toString();
					Util.setJsonResponse(jsonResponse, x.get(0)[0], ResponseStatus.success,
							ApiResponseMessage.SAVED_SUCCESSFULLY);
//					jsonResponse.setCode("success");
//					jsonResponse.setMessage("Data Saved Successfully");
				} catch (Exception e) {
					e.printStackTrace();
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, e.getMessage());
//					jsonResponse.setCode("failed");
//					jsonResponse.setMessage("Something went wrong.");
				}

				if (jsonResponse.getCode().equals("success")) {
					byte[] pId = Base64.getEncoder().encode(data.getUniqueid().getBytes());

					String userid = new String(pId);

					String date = DateFormatter.returnStringDateNew(data.getScreening_date());

					String Url = env.getWebURL() + "user/view-patient-test-report-merge?id=" + userid + "&id2=" + date;

					String msg = "Dear " + fullname + ",\n" + "\n"
							+ "Your Report has been generated successfully! Download from link " + Url + "\n\n"
							+ "Regards,\r\n" + "eHealthSystem Team";
					String encodemsg = null;
					try {
						encodemsg = URLEncoder.encode(msg, "UTF-8");
					} catch (UnsupportedEncodingException e2) {
						e2.printStackTrace();
					}

					Integer smsstatus = 0;
					try {
						smsstatus = CommonUsed.sendSMSReturnResponse(mobile, encodemsg);

					} catch (ClientProtocolException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postTestReportNasan DAO ends");
		return response;
	}

	// Get Lab technician details
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getLabTechnicianDetailsDao(String labTechId) {
		logger.info("Method : getLabTechnicianDetailsDao Dao starts");

		List<AppointmentModel> labDetailsList = new ArrayList<AppointmentModel>();
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("viewLabTechtDetails")
					.setParameter("labid", labTechId).getResultList();
			for (Object[] m : x) {
				AppointmentModel dropDownModel = new AppointmentModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9]);
				labDetailsList.add(dropDownModel);
				jsonResponse.setBody(labDetailsList);
			}
			if (labDetailsList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data fetched successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			e.printStackTrace();
			jsonResponse.setMessage(e.getMessage());
			jsonResponse.setMessage("Data not found");
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getLabTechnicianDetailsDao Dao ends");
		return response;
	}

	/*
	 * @SuppressWarnings({ "unchecked", "unused" }) public
	 * ResponseEntity<JsonResponse<Object>> postTestReportWrizto(WritzoTestModel
	 * writzoTestModel) { logger.info("Method : postTestReportWrizto DAO starts");
	 * 
	 * JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
	 * 
	 * String fullname = null; String mobile = null;
	 * 
	 * String filePath = env.getLogfiles() + "ecg_log.txt";
	 * 
	 * if (writzoTestModel.getScreeningDetails().size() > 0) { for (FileModel p :
	 * writzoTestModel.getScreeningDetails()) {
	 * 
	 * String testName = p.getVitalName(); String testResult = p.getResult();
	 * 
	 * if (!StringUtil.isNull(testResult)) { try { List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("save_wrizto_test_report_data")
	 * .setParameter("regNo", writzoTestModel.getPatientId())
	 * .setParameter("screeningDate", writzoTestModel.getScreeningDate())
	 * .setParameter("testName", testName).setParameter("testResult", testResult)
	 * .setParameter("jsondata", writzoTestModel.toString()).getResultList();
	 * jsonResponse.setBody(x.get(0)[0]); fullname = x.get(0)[1].toString(); mobile
	 * = x.get(0)[0].toString(); FileWrite.ecgLogWrite(filePath,
	 * writzoTestModel.getPatientId(), testName + " - " +
	 * ApiResponseMessage.SAVED_SUCCESSFULLY); Util.setJsonResponse(jsonResponse,
	 * x.get(0)[0], ResponseStatus.success, ApiResponseMessage.SAVED_SUCCESSFULLY);
	 * } catch (Exception e) { e.printStackTrace(); FileWrite.ecgLogWrite(filePath,
	 * writzoTestModel.getPatientId(), e.getMessage());
	 * Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
	 * ApiResponseMessage.UNKNOWN_EXCEPTION); } } else {
	 * Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
	 * ApiResponseMessage.TEST_RESULT_IS_NULL); } }
	 * 
	 * } else { Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
	 * ApiResponseMessage.TEST_DETAILS_IS_EMPTY); }
	 * 
	 * if (jsonResponse.getCode().equals("success")) { byte[] pId =
	 * Base64.getEncoder().encode(writzoTestModel.getPatientId().getBytes());
	 * 
	 * String userid = new String(pId);
	 * 
	 * String date =
	 * DateFormatter.returnStringDateNew(writzoTestModel.getScreeningDate());
	 * 
	 * String Url = env.getWebURL() + "user/view-patient-test-report-merge?id=" +
	 * userid + "&id2=" + date;
	 * 
	 * String msg = "Dear " + fullname + ",\n" + "\n" +
	 * "Your Report has been generated successfully! Download from link " + Url +
	 * "\n\n" + "Regards,\r\n" + "eHealthSystem Team"; String encodemsg = null; try
	 * { encodemsg = URLEncoder.encode(msg, "UTF-8"); } catch
	 * (UnsupportedEncodingException e2) { e2.printStackTrace(); }
	 * 
	 * Integer smsstatus = 0; try { smsstatus =
	 * CommonUsed.sendSMSReturnResponse(mobile, encodemsg);
	 * 
	 * } catch (ClientProtocolException e1) { e1.printStackTrace(); } catch
	 * (IOException e1) { e1.printStackTrace(); } }
	 * 
	 * ResponseEntity<JsonResponse<Object>> response = new
	 * ResponseEntity<JsonResponse<Object>>(jsonResponse, HttpStatus.OK);
	 * 
	 * logger.info("Method : postTestReportWrizto DAO ends"); return response; }
	 */

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<JsonResponse<Object>> postTestReportWrizto(WritzoTestModel writzoTestModel,
			List<String> imgFileList) {
		logger.info("Method : postTestReportWrizto DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		String fullname = null;
		String mobile = null;

		String filePath = env.getLogfiles() + "ecg_log.txt";

		if (writzoTestModel.getScreeningDetails().size() > 0) {
			for (FileModel p : writzoTestModel.getScreeningDetails()) {

				String testName = p.getVitalName();
				String testResult = p.getResult();

				if (!StringUtil.isNull(testResult)) {
					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("save_wrizto_test_report_data")
								.setParameter("regNo", writzoTestModel.getPatientId())
								.setParameter("screeningDate", writzoTestModel.getScreeningDate())
								.setParameter("testName", testName).setParameter("testResult", testResult)
								.setParameter("jsondata", writzoTestModel.toString()).getResultList();
						jsonResponse.setBody(x.get(0)[0]);
						fullname = x.get(0)[1].toString();
						mobile = x.get(0)[0].toString();
						FileWrite.ecgLogWrite(filePath, writzoTestModel.getPatientId(),
								testName + " - " + ApiResponseMessage.SAVED_SUCCESSFULLY);
						Util.setJsonResponse(jsonResponse, x.get(0)[0], ResponseStatus.success,
								ApiResponseMessage.SAVED_SUCCESSFULLY);
					} catch (Exception e) {
						e.printStackTrace();
						FileWrite.ecgLogWrite(filePath, writzoTestModel.getPatientId(), e.getMessage());
						Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
								ApiResponseMessage.UNKNOWN_EXCEPTION);
					}
				} else {
					Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed,
							ApiResponseMessage.TEST_RESULT_IS_NULL);
				}
			}

		} else {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.TEST_DETAILS_IS_EMPTY);
		}

		if (jsonResponse.getCode().equals("success")) {
			byte[] pId = Base64.getEncoder().encode(writzoTestModel.getPatientId().getBytes());

			String userid = new String(pId);

			String date = DateFormatter.returnStringDateNew(writzoTestModel.getScreeningDate());

			String Url = env.getWebURL() + "user/view-patient-test-report-wrizto-download?id=" + userid + "&id2=" + date;

			String msg = "Dear " + fullname + ",\n" + "\n"
					+ "Your Report has been generated successfully! Download from link " + Url + "\n\n" + "Regards,\r\n"
					+ "eHealthSystem Team";

			System.out.println("MSG"+msg);
			String encodemsg = null;
			try {
				encodemsg = URLEncoder.encode(msg, "UTF-8");
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}

			Integer smsstatus = 0;
			try {
				smsstatus = CommonUsed.sendSMSReturnResponse(mobile, encodemsg);

			} catch (ClientProtocolException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("FileList=====" + imgFileList);
			if (imgFileList != null && imgFileList.size() > 0) {
				for (String m : imgFileList) {
					String pngFile = env.getWriztoUrl() + m;
					String pdfFile = pngFile.replace(".png", ".pdf").replace(".jpg", ".pdf").replace(".jpeg", ".pdf");
					System.out.println("pngFile==" + pngFile);
					System.out.println("pdfFile==" + pdfFile);
					Document document = new Document(PageSize.A4, 20, 20, 20, 20);
					try {
						PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (DocumentException e) {
						e.printStackTrace();
					}
					document.open();
					Image image = null;
					try {
						image = Image.getInstance(pngFile);
//					image = Image.getInstance(getClass().getResource(pngFile));
					} catch (BadElementException e) {
						e.printStackTrace();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						document.add(image);
					} catch (DocumentException e) {
						e.printStackTrace();
					}
					document.close();
				}
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postTestReportWrizto DAO ends");
		return response;
	}

	// Rest Merge Document Data

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> writzoPdfLists(String id, String id2) {

		logger.info("Method : writzoPdfList Dao starts");
		List<DropDownModel> writzo = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		System.out.println("====>>>>" + id);
		System.out.println("====>>>>" + id2);
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_writzo_pdf_data").setParameter("patientId", id)
					.setParameter("screen_date", id2).getResultList();
			for (Object[] m : x) {

				DropDownModel ro = new DropDownModel(m[0], m[1]);
				writzo.add(ro);
				resp.setBody(writzo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("writzoPdfList" + resp);
		logger.info("Method : writzoPdfList Dao ends" + resp);
		return resp;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postTestReportAyurythm(AyurythmModel ayurythmModel) {
		logger.info("Method : postTestReportAyurythm DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		String fullname = null;
		String mobile = null;
		String createdDate = null;

		if (StringUtil.isNull(ayurythmModel.getData().getUserId())) {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.PATIENT_ID_REQUIRED);
			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
					HttpStatus.OK);
			return response;
		}

		if (!StringUtil.isNull(ayurythmModel.getData())) {

			/** PRIMARY KEY GENERATED **/
			String ayurythmId = null;
			if (!StringUtil.isNull(ayurythmModel.getData().getUserId())) {
				String random = GenerateRandomValue.generateRandom(12);
				ayurythmId = ayurythmModel.getData().getUserId().substring(0, 4).concat(random);
			}

			/** SPARSHNA MASTER DATA **/
			String dataDetails = "(\"" + ayurythmId + "\",\"" + ayurythmModel.getData().getUserId()
					+ "\",fullname,gndr,pmobile,\"" + ayurythmModel.getData().getType() + "\",\""
					+ ayurythmModel.getData().getSparshna_master().getTitle() + "\",\""
					+ ayurythmModel.getData().getSparshna_master().getWhat_it_means() + "\",\""
					+ ayurythmModel.getData().getSparshna_master().getWhat_to_do()
					+ "\",created_by,mt_type,cntry,st,rev,cty)";

			/** SPARSHNA TEST DATA **/
			String sparshnaData = "";
			if (!StringUtil.isNull(ayurythmModel.getSparshna())) {
				sparshnaData = "(\"" + ayurythmId + "\",\"" + ayurythmModel.getData().getUserId() + "\","
						+ ayurythmModel.getSparshna().getBmr() + "," + ayurythmModel.getSparshna().getDp() + ","
						+ ayurythmModel.getSparshna().getRythm() + "," + ayurythmModel.getSparshna().getO2r() + ","
						+ ayurythmModel.getSparshna().getPitta2() + "," + ayurythmModel.getSparshna().getVata2() + ","
						+ ayurythmModel.getSparshna().getKath() + "," + ayurythmModel.getSparshna().getPbreath() + ","
						+ ayurythmModel.getSparshna().getBala() + "," + ayurythmModel.getSparshna().getTbpm() + ","
						+ ayurythmModel.getSparshna().getSp() + "," + ayurythmModel.getSparshna().getKapha2() + ","
						+ ayurythmModel.getSparshna().getKapha2() + ",\"" + ayurythmModel.getSparshna().getGati()
						+ "\",created_by,mt_type,cntry,st,rev,cty)";
			}

			/** VIKRITI AND PRAKRITI DATA **/
			String vikritiPrakritiData = "";
			String typeV = "Vikriti";
			String typeP = "Prakriti";
			if (!StringUtil.isNull(ayurythmModel.getVikriti())) {
				vikritiPrakritiData = vikritiPrakritiData + "(\"" + ayurythmId + "\",\""
						+ ayurythmModel.getData().getUserId() + "\",\"" + typeV + "\",\""
						+ ayurythmModel.getVikriti().getKapha() + "\",\"" + ayurythmModel.getVikriti().getPitta()
						+ "\",\"" + ayurythmModel.getVikriti().getVata() + "\",created_by,mt_type,cntry,st,rev,cty),";
			}
			if (!StringUtil.isNull(ayurythmModel.getPrakriti())) {
				vikritiPrakritiData = vikritiPrakritiData + "(\"" + ayurythmId + "\",\""
						+ ayurythmModel.getData().getUserId() + "\",\"" + typeP + "\",\""
						+ ayurythmModel.getPrakriti().getKapha() + "\",\"" + ayurythmModel.getPrakriti().getPitta()
						+ "\",\"" + ayurythmModel.getPrakriti().getVata() + "\",created_by,mt_type,cntry,st,rev,cty),";
			}

			if (vikritiPrakritiData != "") {
				vikritiPrakritiData = vikritiPrakritiData.substring(0, vikritiPrakritiData.length() - 1);
			}

			/** SPARSHNA RESULT DESCRIPTION **/
			String sprashna_result_desc = "";
			if (ayurythmModel.getData().getSparshna_result_desc().size() > 0) {
				for (SparshnaResultDesc m : ayurythmModel.getData().getSparshna_result_desc()) {
					sprashna_result_desc = sprashna_result_desc + "(\"" + ayurythmId + "\",\""
							+ ayurythmModel.getData().getUserId() + "\",\"" + m.getTitle() + "\",\""
							+ m.getShort_description() + "\",\"" + m.getWhat_it_means()
							+ "\",created_by,mt_type,cntry,st,rev,cty),";
				}
			}

			if (sprashna_result_desc != "") {
				sprashna_result_desc = sprashna_result_desc.substring(0, sprashna_result_desc.length() - 1);
			}

			/** FOOD TYPES **/
			String food_types = "";
			if (ayurythmModel.getData().getFood_types().size() > 0) {
				for (AyurythmFoodModel m : ayurythmModel.getData().getFood_types()) {
					food_types = food_types + "(\"" + ayurythmId + "\",\"" + ayurythmModel.getData().getUserId()
							+ "\",\"" + m.getFood_type() + "\",\"" + m.getFavour_foods() + "\",\"" + m.getAvoid_foods()
							+ "\",created_by,mt_type,cntry,st,rev,cty),";
				}
			}

			if (food_types != "") {
				food_types = food_types.substring(0, food_types.length() - 1);
			}

			/** HERBS **/
			String herbs = "";
			if (ayurythmModel.getData().getHerbs().size() > 0) {
				for (AyurythmHerbModel m : ayurythmModel.getData().getHerbs()) {
					herbs = herbs + "(\"" + ayurythmId + "\",\"" + ayurythmModel.getData().getUserId() + "\",\""
							+ m.getHerb_type() + "\",\"" + m.getHerbs_list()
							+ "\",created_by,mt_type,cntry,st,rev,cty),";
				}
			}

			if (herbs != "") {
				herbs = herbs.substring(0, herbs.length() - 1);
			}

			/** SPARSHNA ACTIVITIES **/
			String sparhsna_activities = "";
			String typeK = "kriya";
			String typeM = "mudra";
			String typeMe = "meditation";
			String typePr = "pranayama";
			String typeY = "yogasana";

			/** KRIYA **/
			if (ayurythmModel.getData().getKriya().size() > 0) {
				for (SparshnaActivitiesModel m : ayurythmModel.getData().getKriya()) {
					sparhsna_activities = sparhsna_activities + "(\"" + ayurythmId + "\",\""
							+ ayurythmModel.getData().getUserId() + "\",\"" + typeK + "\",\"" + m.getType() + "\",\""
							+ m.getName() + "\",\"" + m.getShortdescription() + "\",\"" + m.getSteps() + "\",\""
							+ m.getBenefit_description() + "\",\"" + m.getPrecautions()
							+ "\",created_by,mt_type,cntry,st,rev,cty),";
				}
			}

			/** MUDRA **/
			if (ayurythmModel.getData().getMudra().size() > 0) {
				for (SparshnaActivitiesModel m : ayurythmModel.getData().getKriya()) {
					sparhsna_activities = sparhsna_activities + "(\"" + ayurythmId + "\",\""
							+ ayurythmModel.getData().getUserId() + "\",\"" + typeM + "\",\"" + m.getType() + "\",\""
							+ m.getName() + "\",\"" + m.getShortdescription() + "\",\"" + m.getSteps() + "\",\""
							+ m.getBenefit_description() + "\",\"" + m.getPrecautions()
							+ "\",created_by,mt_type,cntry,st,rev,cty),";
				}
			}

			/** MEDITATION **/
			if (ayurythmModel.getData().getMeditation().size() > 0) {
				for (SparshnaActivitiesModel m : ayurythmModel.getData().getKriya()) {
					sparhsna_activities = sparhsna_activities + "(\"" + ayurythmId + "\",\""
							+ ayurythmModel.getData().getUserId() + "\",\"" + typeMe + "\",\"" + m.getType() + "\",\""
							+ m.getName() + "\",\"" + m.getShortdescription() + "\",\"" + m.getSteps() + "\",\""
							+ m.getBenefit_description() + "\",\"" + m.getPrecautions()
							+ "\",created_by,mt_type,cntry,st,rev,cty),";
				}
			}

			/** PRANAYAM **/
			if (ayurythmModel.getData().getPranayama().size() > 0) {
				for (SparshnaActivitiesModel m : ayurythmModel.getData().getKriya()) {
					sparhsna_activities = sparhsna_activities + "(\"" + ayurythmId + "\",\""
							+ ayurythmModel.getData().getUserId() + "\",\"" + typePr + "\",\"" + m.getType() + "\",\""
							+ m.getName() + "\",\"" + m.getShortdescription() + "\",\"" + m.getSteps() + "\",\""
							+ m.getBenefit_description() + "\",\"" + m.getPrecautions()
							+ "\",created_by,mt_type,cntry,st,rev,cty),";
				}
			}

			/** YOGASANA **/
			if (ayurythmModel.getData().getYogasana().size() > 0) {
				for (SparshnaActivitiesModel m : ayurythmModel.getData().getKriya()) {
					sparhsna_activities = sparhsna_activities + "(\"" + ayurythmId + "\",\""
							+ ayurythmModel.getData().getUserId() + "\",\"" + typeY + "\",\"" + m.getType() + "\",\""
							+ m.getName() + "\",\"" + m.getShortdescription() + "\",\"" + m.getSteps() + "\",\""
							+ m.getBenefit_description() + "\",\"" + m.getPrecautions()
							+ "\",created_by,mt_type,cntry,st,rev,cty),";
				}
			}

			if (sparhsna_activities != "") {
				sparhsna_activities = sparhsna_activities.substring(0, sparhsna_activities.length() - 1);
			}

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("save_ayurythm_test_report_data")
						.setParameter("primary_key", ayurythmId)
						.setParameter("patient_id", ayurythmModel.getData().getUserId())
						.setParameter("data_details", dataDetails).setParameter("sparshna_data", sparshnaData)
						.setParameter("vikriti_prakriti", vikritiPrakritiData)
						.setParameter("sprashna_result_desc", sprashna_result_desc)
						.setParameter("food_types", food_types).setParameter("herbs", herbs)
						.setParameter("sparhsna_activities", sparhsna_activities)
						.setParameter("jsondata", ayurythmModel.toString()).getResultList();
				jsonResponse.setBody(x.get(0)[0]);
				fullname = x.get(0)[1].toString();
				mobile = x.get(0)[0].toString();
				createdDate = x.get(0)[2].toString();
				Util.setJsonResponse(jsonResponse, x.get(0)[0], ResponseStatus.success,
						ApiResponseMessage.SAVED_SUCCESSFULLY);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", ayurythmId);
				map.put("user_id", ayurythmModel.getData().getUserId());
				map.put("name", fullname);
				map.put("mobile", mobile);
				map.put("createdDate", createdDate);

				Util.setJsonResponse(jsonResponse, map, ResponseStatus.success, ApiResponseMessage.SAVED_SUCCESSFULLY);
			} catch (Exception e) {
				e.printStackTrace();
				Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.UNKNOWN_EXCEPTION);
			}
			if (jsonResponse.getCode().equals("success")) {
				byte[] pId = Base64.getEncoder().encode(ayurythmModel.getData().getUserId().getBytes());

				String userid = new String(pId);

				/*
				 * String Url = env.getWebURL() +
				 * "user/view-patient-test-report-pdf-download?id=" + userid + "&id2=" + date;
				 */

				String Url = env.getWebURL() + "user/view-patient-ayurythm-test-report-merge?id=" + userid + "&id2="
						+ createdDate;

				String msg = "Dear " + fullname + ",\n" + "\n"
						+ "Your Report has been generated successfully! Download from link " + Url + "\n\n"
						+ "Regards,\r\n" + "eHealthSystem Team";
				String encodemsg = null;
				try {
					encodemsg = URLEncoder.encode(msg, "UTF-8");
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				Integer smsstatus = 0;
				try {
					smsstatus = CommonUsed.sendSMSReturnResponse(mobile, encodemsg);

				} catch (ClientProtocolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.AYURYTHM_DATA_IS_EMPTY);
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postTestReportAyurythm DAO ends");
		return response;
	}
	
	
	@SuppressWarnings({ "unchecked", "unused" })

	public ResponseEntity<JsonResponse<Object>> postTestReportNadi(APIAiplMasterNadiModel data) {
		logger.info("Method : postTestReportNadi Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String jsondata = data.toString();
		String fullname = null;
		String mobile = null;
		String createdDate = null;

		Boolean validation = true;
		if (data.getEhsJsonReport().getUh_Id() == null || data.getEhsJsonReport().getUh_Id() == "") {
			validation = false;
			jsonResponse.setMessage("User id Required");
		}

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("save_nadi_test_report_datas")
					.setParameter("p_UHId", data.getEhsJsonReport().getUh_Id())
					.setParameter("p_pType", data.getEhsJsonReport().getPrakruti_Type())
					.setParameter("jsondata", data.getEhsJsonReport().getPrakruti_Detail())
					.setParameter("p_pVata", data.getEhsJsonReport().getPrakruti_Vata())
					.setParameter("p_pPitta", data.getEhsJsonReport().getPrakruti_Pitta())
					.setParameter("p_pKapha", data.getEhsJsonReport().getPrakruti_Kapha())
					.setParameter("p_pDosha", data.getEhsJsonReport().getDosha())
					.setParameter("p_pPulse", data.getEhsJsonReport().getPulse_Rate())
					.setParameter("p_pRhytm", data.getEhsJsonReport().getRhythm())
					.setParameter("p_pDscore", data.getEhsJsonReport().getDigestion_Score())
					.setParameter("p_pSscore", data.getEhsJsonReport().getStress_Score())
					.setParameter("p_TSscore", data.getEhsJsonReport().getToxins_Score())
					.setParameter("p_Bimunity", data.getEhsJsonReport().getBody_Immunity())
					.setParameter("p_Hscore", data.getEhsJsonReport().getHydration_Score())
					.setParameter("p_bala", data.getEhsJsonReport().getBala())
					.setParameter("p_agni", data.getEhsJsonReport().getAgni())
					.setParameter("p_Bdesc", data.getEhsJsonReport().getBala_Description())
					.setParameter("p_Adesc", data.getEhsJsonReport().getAgni_Description())
					.setParameter("p_Laghu", data.getEhsJsonReport().getLaghu())
					.setParameter("p_Guru", data.getEhsJsonReport().getGuru())
					.setParameter("p_LGdesc", data.getEhsJsonReport().getLaghu_Guru_Description())
					.setParameter("p_kathina", data.getEhsJsonReport().getKathina())
					.setParameter("p_murdu", data.getEhsJsonReport().getMrudu())
					.setParameter("p_Mdesc", data.getEhsJsonReport().getKathina_Mrudu_Description())
					.setParameter("p_Sthula", data.getEhsJsonReport().getSthula())
					.setParameter("p_Sukshma", data.getEhsJsonReport().getSukshma())
					.setParameter("p_SSDesc", data.getEhsJsonReport().getSthula_Sukshma_Description())
					.setParameter("p_Tikshna", data.getEhsJsonReport().getTikshna())
					.setParameter("p_Manda", data.getEhsJsonReport().getManda())
					.setParameter("p_BalaDesc", data.getEhsJsonReport().getBala_Description())
					.setParameter("p_Snigdha", data.getEhsJsonReport().getSnigdha())
					.setParameter("p_Ruksha", data.getEhsJsonReport().getRuksha())
					.setParameter("p_SRDesc", data.getEhsJsonReport().getSnigdha_Ruksha_Description())
					.setParameter("p_WPDesc", data.getEhsJsonReport().getWellnessParameter_Description())
					.setParameter("p_Thoughts", data.getEhsJsonReport().getThoughts())
					.setParameter("p_TDesc", data.getEhsJsonReport().getThought_Description())
					.setParameter("p_Stress", data.getEhsJsonReport().getStress())
					.setParameter("p_SDesc", data.getEhsJsonReport().getStress_Description())
					.setParameter("p_Summary", data.getEhsJsonReport().getSummary()).getResultList();

			jsonResponse.setBody(x.get(0));
			System.out.println("===>>>" + jsonResponse);

			mobile = x.get(0)[0].toString();
			fullname = x.get(0)[1].toString();
			createdDate = x.get(0)[2].toString();

			Util.setJsonResponse(jsonResponse, x.get(0)[0], ResponseStatus.success,
					ApiResponseMessage.SAVED_SUCCESSFULLY);

			System.out.println(data);

			jsonResponse.setCode("success");
			jsonResponse.setMessage("User Updated Successfully");
		}

		catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
			e.printStackTrace();
		}

		if (jsonResponse.getCode().equals("success")) {
			byte[] pId = Base64.getEncoder().encode(data.getEhsJsonReport().getUh_Id().getBytes());

			String userid = new String(pId);

			String Url = env.getWebURL() + "user/view-patient-test-report-nadi-download?id=" + userid + "&id2="
					+ createdDate;

			String msg = "Dear " + fullname + ",\n" + "\n"
					+ "Your Report has been generated successfully! Download from link " + Url + "\n\n" + "Regards,\r\n"
					+ "eHealthSystem Team";

			String encodemsg = null;
			try {
				encodemsg = URLEncoder.encode(msg, "UTF-8");
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			Integer smsstatus = 0;
			try {
				smsstatus = CommonUsed.sendSMSReturnResponse(mobile, encodemsg);

			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		else {
			Util.setJsonResponse(jsonResponse, null, ResponseStatus.failed, ApiResponseMessage.TEST_DETAILS_IS_EMPTY);
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postTestReportNadi Dao ends");
		return response;
	}

}
