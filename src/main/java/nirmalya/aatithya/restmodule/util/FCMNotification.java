package nirmalya.aatithya.restmodule.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class FCMNotification {

	public final static String AUTH_KEY_FCM = "AAAAES5j-YA:APA91bGiyZ3MUS9BEt2y6om5T8e_kStvlV9JD2YZ20uDFOT_z4NWqs6wplhMpyBmxSycIKCa3ifAb8qTZOT0jIG9GLIT6heXKFg156eIN_c-fsHg7KZHLYYQgKAfcdT9grq6pJFmzw7r";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public static int pushFCMNotification(String DeviceIdKey, Object dataList) throws Exception {

		String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		String FMCurl = API_URL_FCM;

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(FMCurl);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", "key=" + authKey);

		JSONObject message = new JSONObject();
		message.put("to", DeviceIdKey.trim());
		message.put("priority", "high");

		JSONObject d1 = new JSONObject(dataList.toString());


		String msg = "Hey! " + d1.getString("userName") + ", It's time to take your medicine - "
				+ d1.getString("meddtls");

		JSONObject notification = new JSONObject();
		notification.put("title", "eHealthSystem");
		notification.put("body", msg);
		notification.put("sound", "default");
		notification.put("data", dataList);

		message.put("notification", notification);

		post.setEntity(new StringEntity(message.toString(), "UTF-8"));
		HttpResponse response = client.execute(post);

		Reader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		StringBuilder builder = new StringBuilder();
		char[] buf = new char[1000];
		int l = 0;
		while (l >= 0) {
			builder.append(buf, 0, l);
			l = in.read(buf);
		}
		JSONObject obj = new JSONObject(builder.toString());

		Integer responsecode = Integer.parseInt(obj.get("success").toString());
		
		return responsecode;
	}
}
