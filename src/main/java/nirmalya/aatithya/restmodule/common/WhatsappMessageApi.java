package nirmalya.aatithya.restmodule.common;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class WhatsappMessageApi {

	public String sendSMSWhatsapp(String userMobileNo, String message, String projectName,String updatedDate,String rejectedBy) throws IOException {
	    try {
	        // Specify the API endpoint
	        String apiEndpoint = "https://wbapp.adglob.in/sendmessage";

	        // Create the JSON object as per the original method
	        JSONObject data = new JSONObject();
	        data.put("key", "867tVB9himzqfUSIctXL7gAMZSs2pqzGqk7pbh9C");
	        data.put("rcptno", userMobileNo);
	        data.put("template", "project");
	        JSONArray parameters = new JSONArray();

	        
            JSONObject param1 = new JSONObject();
            param1.put("type", "text");
            param1.put("text", projectName);
            parameters.put(param1);

            JSONObject param2 = new JSONObject();
            param2.put("type", "text");
            param2.put("text", updatedDate);
            parameters.put(param2);

            JSONObject param3 = new JSONObject();
            param3.put("type", "text");
            param3.put("text",rejectedBy);
            parameters.put(param3);
	        
	        data.put("parameters", parameters);

	        // Create an HTTP connection
	        URL url = new URL(apiEndpoint);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/json; utf-8");
	        connection.setDoOutput(true);

	        // Send the JSON data
	        try (OutputStream os = connection.getOutputStream()) {
	            byte[] input = data.toString().getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }

	        // Get the response
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
	            StringBuilder response = new StringBuilder();
	            String responseLine;
	            while ((responseLine = reader.readLine()) != null) {
	                response.append(responseLine.trim());
	            }

	            // Print the response
	            System.out.println("Response==== "+response.toString());
	        }

	        // Close the connection
	        connection.disconnect();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return message;
	}
}
