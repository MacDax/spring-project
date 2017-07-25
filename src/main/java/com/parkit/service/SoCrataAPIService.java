package com.parkit.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.parkit.domain.ParkingPlace;

@Service
public class SoCrataAPIService {
	
	private final String APIKey = "O5TnGmseY4pSgdwVgAO6ctKgm";
	private CloseableHttpClient httpClient = HttpClients.createDefault();
	private CloseableHttpResponse response = null;
	private List<ParkingPlace> parkingLotList =null;
	private String json = "";
	private JsonObject jObj = null;
	private String responseString =null;
	private final String serviceURL = "https://data.sfgov.org/resource/2iym-9kfb.json?$$app_token=";
	//https://data.seattle.gov/resource/3k2p-39jp.json?$$app_token=APP_TOKEN
	private InputStream is;
	
	public void getParkingMetersInSFO() {
		try {
			HttpGet request = new HttpGet(serviceURL+APIKey); 
			response = httpClient.execute(request);
			int status = response.getStatusLine().getStatusCode();
			if(status == 200) {
				HttpEntity entity = response.getEntity();
				responseString =EntityUtils.toString(entity, "UTF-8");
				//System.out.println(responseString);
				is = entity.getContent();
				//System.out.println("is : " +is);
			}
		}catch(UnsupportedEncodingException ex) {
			System.out.println("Socrata servic Ex : " + ex.getMessage());
			ex.printStackTrace();
		}catch(ClientProtocolException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		JSONObject rootObject =null;
		JSONArray jsonAVL =null;
		
		//rootObject = new JSONObject(responseString);
		//String message = rootObject.getString("MESSAGE");
		jsonAVL = new JSONArray(responseString);
		System.out.println(jsonAVL.length());
	}

}
