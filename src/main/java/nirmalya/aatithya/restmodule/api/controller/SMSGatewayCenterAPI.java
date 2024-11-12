/*
 * package nirmalya.aatithya.restmodule.api.controller; // //import
 * java.io.BufferedReader; //import java.io.IOException; //import
 * java.io.InputStreamReader; //import java.io.OutputStreamWriter; //import
 * java.net.HttpURLConnection; //import java.net.URL; //import
 * java.net.URLConnection; //import java.net.URLEncoder; //import
 * java.util.Date; // //public class SMSGatewayCenterAPI { //
 * // @SuppressWarnings("deprecation") // public static void sendSMS(String
 * mobile, String message) { // // Your apikey key // //String apiKey =
 * "YourApiKey"; // String userId = "matrujyoti"; // String password =
 * "Suce$$@121"; // // // Send Method // String sendMethod = "simpleMsg"; // //
 * // Message type text/unicode/flash // String msgType = "text"; // // //
 * Multiple mobiles numbers separated by comma // // // Your approved sender id
 * // String senderId = "SGCSMS"; // String format = "json"; // // URLConnection
 * myURLConnection = null; // URL myURL = null; // BufferedReader reader = null;
 * // // String urlencodedmsg = URLEncoder.encode(message); // // // API End
 * Point // String mainUrl = "http://www.smsgateway.center/SMSApi/rest/send?";
 * // // // API Paramters // StringBuilder sendSmsData = new
 * StringBuilder(mainUrl); // //sendSmsData.append("apiKey=" + apiKey); //
 * sendSmsData.append("userId=" + userId); // sendSmsData.append("&password=" +
 * password); // sendSmsData.append("&sendMethod=" + sendMethod); //
 * sendSmsData.append("&msgType=" + msgType); // sendSmsData.append("&mobile=" +
 * mobile); // sendSmsData.append("&senderId=" + senderId); //
 * sendSmsData.append("&msg=" + urlencodedmsg); // sendSmsData.append("&format="
 * + format); // // final string // mainUrl = sendSmsData.toString(); // try {
 * // // prepare connection // myURL = new URL(mainUrl); // myURLConnection =
 * myURL.openConnection(); // myURLConnection.connect(); // reader = new
 * BufferedReader(new InputStreamReader(myURLConnection.getInputStream())); //
 * // reading response // String response; // while ((response =
 * reader.readLine()) != null) // // print response //
 * System.out.println(response); // // // finally close connection //
 * reader.close(); // } catch (IOException e) { // e.printStackTrace(); // } //
 * } //} import java.io.BufferedReader; import java.io.InputStreamReader; import
 * java.io.OutputStreamWriter; import java.net.HttpURLConnection; import
 * java.net.URL; import java.net.URLEncoder; import java.util.Date; public class
 * SMSGatewayCenterAPI{ public static void sendSMS(String mobile, String
 * message) { try{ Date mydate = new Date(System.currentTimeMillis()); String
 * data = ""; data += "sendMethod=simpleMsg"; data += "&userId=matrujyoti"; data
 * += "&password=" + URLEncoder.encode("Suce$$@121", "UTF-8"); data += "&msg=" +
 * URLEncoder.encode("SMS Gateway message " + mydate.toString(), "UTF-8"); data
 * += "&mobile=" + URLEncoder.encode("8917225033", "UTF-8"); data +=
 * "&msgType=text"; data += "&senderId=SMSGAT"; data +=
 * "&dltEntityId=1601100000000002843"; data += "&dltTemplateId=xxxxxxxxxxxx";
 * data += "&format=json"; URL url = new
 * URL("https://www.smsgateway.center/SMSApi/rest/send?" + data);
 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 * conn.setRequestMethod("GET"); conn.setDoOutput(true); conn.setDoInput(true);
 * conn.setUseCaches(false); conn.connect(); BufferedReader rd = new
 * BufferedReader(new InputStreamReader(conn.getInputStream())); String line;
 * StringBuffer buffer = new StringBuffer(); while((line = rd.readLine()) !=
 * null){ buffer.append(line).append("\n"); }
 * System.out.println(buffer.toString()); rd.close(); conn.disconnect(); } catch
 * (Exception e){ e.printStackTrace(); } } }
 * 
 */