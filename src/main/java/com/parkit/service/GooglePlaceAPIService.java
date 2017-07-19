package com.parkit.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Service
public class GooglePlaceAPIService {
	
	public GeocodingResult[] getGeoCodingResult(String whereto) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD_yBCEbeHzr6T71GroPsjXsApSNfjEems");
		GeocodingResult[] results =  GeocodingApi.geocode(context,  whereto).await();  
		LatLng latLang = results[0].geometry.location;
		String address = results[0].formattedAddress;
		System.out.println(address);
		return results;
	}
	
	
	

}
