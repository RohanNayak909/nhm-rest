package nirmalya.aatithya.restmodule.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class PushNotification {
	
	@Autowired
	RestTemplate restTemplate;
	
	public final static String AUTH_KEY_FCM = "AAAAACrX1bc:APA91bGliwwwRvtJrTlRkIysEI1aECN-QjOd8N8zHHVdfAvlsPfHnS5rrWEZyNhbWzHowMIvPAXAeUk3V_mKVivKWWAjgSUiIevbw31de7Gta2ePljWYbnl3CQa6uqSCLBP4HAdgrgcJ";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
	
	public String pushFCMNotification(String tokenId,String msg) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> notification = new HashMap<String,Object>();
 
		notification.put("body", msg);
		notification.put("title", "CureEz");
		notification.put("sound", "default");
		
		String to = "/topics/" + tokenId;
		
		map.put("to", to);
		map.put("notification", notification);
		
		JSONObject obj = new JSONObject(map); 

		System.out.println("notification==="+obj);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(API_URL_FCM);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", "key=" + AUTH_KEY_FCM);

		post.setEntity(new StringEntity(obj.toString(), "UTF-8"));
		HttpResponse response = client.execute(post);

		Reader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		StringBuilder builder = new StringBuilder();
		char[] buf = new char[1000];
		int l = 0;
		while (l >= 0) {
			builder.append(buf, 0, l);
			l = in.read(buf);
		}
		System.out.println("builder"+builder);
		JSONObject arr = new JSONObject(builder.toString());
		System.out.println("arr"+arr);
		String messageId = arr.get("message_id").toString();
		System.out.println("######################"+messageId);
		return messageId;
		
	}
	public  String pushFCMNotifications(String DeviceIdKey, String dataList) throws Exception {

		String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		String FMCurl = API_URL_FCM;

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(FMCurl);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", "key=" + authKey);

		JSONObject message = new JSONObject();
		message.put("to", DeviceIdKey.trim());
		message.put("priority", "high");


		JSONObject notification = new JSONObject();
		notification.put("title", "Cureez");
		notification.put("body", dataList);
		notification.put("sound", "default");
		notification.put("data", dataList);

		message.put("notification", notification);

		post.setEntity(new StringEntity(message.toString(), "UTF-8"));
		HttpResponse response = client.execute(post);

		Reader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		StringBuilder builder = new StringBuilder();
		System.out.println("builder"+builder);
		char[] buf = new char[1000];
		int l = 0;
		while (l >= 0) {
			builder.append(buf, 0, l);
			l = in.read(buf);
		}
		JSONObject obj = new JSONObject(builder.toString());
		System.out.println("obj"+obj);
		String responsecode2 = obj.get("success").toString();
		System.out.println("responsecode"+responsecode2);
		return responsecode2;
	}
	
	public  String pushFCMNotificationToken(String userDeviceToken,String dataListToken) throws Exception {

		String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		String FMCurl = API_URL_FCM;

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(FMCurl);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", "key=" + authKey);

		JSONObject message = new JSONObject();
		message.put("to", userDeviceToken.trim());
		message.put("priority", "high");


		JSONObject notification = new JSONObject();
		notification.put("title", "Cureez");
		notification.put("body", dataListToken);
		notification.put("sound", "default");
		notification.put("data", dataListToken);

		message.put("notification", notification);

		post.setEntity(new StringEntity(message.toString(), "UTF-8"));
		HttpResponse response = client.execute(post);

		Reader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		StringBuilder builder = new StringBuilder();
		System.out.println("builder"+builder);
		char[] buf = new char[1000];
		int l = 0;
		while (l >= 0) {
			builder.append(buf, 0, l);
			l = in.read(buf);
		}
		JSONObject obj = new JSONObject(builder.toString());
		System.out.println("obj"+obj);
		String responsecode2 = obj.get("success").toString();
		System.out.println("responsecode"+responsecode2);
		return responsecode2;
	}
	
	
	
	public  String pushFCMNotificationAppoint(String DeviceIdKeys,String dataListAppoint) throws Exception {

		String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		String FMCurl = API_URL_FCM;

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(FMCurl);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", "key=" + authKey);

		JSONObject message = new JSONObject();
		message.put("to", DeviceIdKeys.trim());
		message.put("priority", "high");


		JSONObject notification = new JSONObject();
		notification.put("title", "Cureez");
		notification.put("body", dataListAppoint);
		notification.put("sound", "default");
		notification.put("data", dataListAppoint);

		message.put("notification", notification);

		post.setEntity(new StringEntity(message.toString(), "UTF-8"));
		HttpResponse response = client.execute(post);

		Reader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		StringBuilder builder = new StringBuilder();
		System.out.println("builder"+builder);
		char[] buf = new char[1000];
		int l = 0;
		while (l >= 0) {
			builder.append(buf, 0, l);
			l = in.read(buf);
		}
		JSONObject obj = new JSONObject(builder.toString());
		System.out.println("obj"+obj);
		String responsecode2 = obj.get("success").toString();
		System.out.println("responsecode"+responsecode2);
		return responsecode2;
	}
}
