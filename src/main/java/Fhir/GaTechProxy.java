package Fhir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import Entities.Entity;

public class GaTechProxy {

	private static String http = "http";
	private static String colon = ":";
	private static String slash = "/";
	private static String mainUrl = "polaris.i3l.gatech.edu";
	private static String mainPort = "8080";
	private static String mainApp = "gt-fhir-webapp";
	private static String base = "base";
	private static String jsonFormat = "?_format=json";
	
	public static String get(String resourceType, String id) {
		StringBuilder sb = new StringBuilder();
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			String url = getURL(resourceType, id);
			HttpGet getRequest = new HttpGet(url);
			getRequest.setHeader("Accept", "application/json");
			getRequest.setHeader("Content-Type", "application/json+fhir");
			HttpResponse response = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String post(Entity entity) {
		//StringBuilder sb = new StringBuilder();
		String id = "";
		try {
		    StringEntity se = new StringEntity(entity.getJSONObject().toString());
			HttpClient httpClient = HttpClientBuilder.create().build();
			String url = postURL(entity.getResourceType());
		    HttpPost httpPost = new HttpPost(url);
		    httpPost.setEntity(se);
		    httpPost.setHeader("Content-type", "application/json");
			HttpResponse response = httpClient.execute(httpPost);
//		    for (Header header : response.getAllHeaders()) {
//				sb.append(header.getName()).append(" ").append(header.getValue()).append(" ");
//			}
		    id =  response.getHeaders("Location")[0].getValue().replace("http://polaris.i3l.gatech.edu:8080/gt-fhir-webapp/base/"+entity.getResourceType()+"/", "");
		    //sb.append("ID: ").append(id);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return sb.toString();
		return id;
	}
	
	private static String getURL(String resource, String id) {
	
		StringBuilder sb = new StringBuilder();
		sb.append(http).append(colon).append(slash).append(slash).append(mainUrl).append(colon).append(mainPort).append(slash);
		sb.append(mainApp).append(slash);
		sb.append(base).append(slash);
		sb.append(resource).append(slash).append(id).append(jsonFormat);
		return sb.toString();
		
	}
	
	private static String postURL(String resource) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(http).append(colon).append(slash).append(slash).append(mainUrl).append(colon).append(mainPort).append(slash);
		sb.append(mainApp).append(slash);
		sb.append(base).append(slash);
		sb.append(resource).append(jsonFormat);
		return sb.toString();
		
	}
}
