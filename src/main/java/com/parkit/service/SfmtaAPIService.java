package com.parkit.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.google.gson.JsonParser;
import com.parkit.domain.ParkingPlace;



@Service
public class SfmtaAPIService {
	private CloseableHttpClient httpClient = HttpClients.createDefault();
	private CloseableHttpResponse response = null;
	private List<ParkingPlace> parkingLotList =null;
	private String json = "";
	private JsonObject jObj = null;
	private String responseString =null;
	private final String serviceURL = "http://api.sfpark.org/sfpark/rest/availabilityservice?response=json&pricing=yes";
	private InputStream is;
	
	public JSONObject getParkingDataWithPrice() {
		try {
			HttpGet request = new HttpGet(serviceURL); //&radius=3.14592");
			response = httpClient.execute(request);
			int status = response.getStatusLine().getStatusCode();
			if(status == 200) {
				HttpEntity entity = response.getEntity();
				responseString =EntityUtils.toString(entity, "UTF-8");
				is = entity.getContent();
			}
		}catch(UnsupportedEncodingException ex) {
			System.out.println("Sfmta servic Ex : " + ex.getMessage());
			ex.printStackTrace();
		}catch(ClientProtocolException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		JSONObject rootObject =null;
		JSONArray jsonAVL =null;
		
		rootObject = new JSONObject(responseString);
		String message = rootObject.getString("MESSAGE");
		jsonAVL = rootObject.getJSONArray("AVL");
		
		processResponseString(jsonAVL);
		
		
		return rootObject;
	}

	private  List<ParkingPlace> processResponseString(JSONArray jsonAvl) {
		List<ParkingPlace> parkingLotList = new ArrayList<>();
		int arrayLen = jsonAvl.length();
		for(int i=0; i<arrayLen; ++i) {
			JSONObject interestArea = jsonAvl.getJSONObject(i);
			System.out.println("  Name : " + interestArea.getString("NAME"));
			System.out.println("  Type : " + interestArea.getString("TYPE"));
			//System.out.println("  DESC : " + interestArea.getString("DESC"));
			//System.out.println("  INTER : " + interestArea.getString("INTER"));
			//System.out.println("  OPHRS : " + interestArea.getJSONObject("OPHRS"));
			System.out.println("  RATEs : " + interestArea.getJSONObject("RATES"));
			System.out.println("  Location : " + interestArea.getString("LOC"));
		}
		
		
		return parkingLotList;
		
	}
}
