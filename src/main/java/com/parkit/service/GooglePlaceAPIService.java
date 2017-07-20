package com.parkit.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

@Service
public class GooglePlaceAPIService {
	GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD_yBCEbeHzr6T71GroPsjXsApSNfjEems");
	public GeocodingResult[] getGeoCodingResult(String whereto) throws ApiException, InterruptedException, IOException {
		//GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD_yBCEbeHzr6T71GroPsjXsApSNfjEems");
		GeocodingResult[] results =  GeocodingApi.geocode(context,  whereto).await();  
		LatLng latLang = results[0].geometry.location;
		String address = results[0].formattedAddress;
		System.out.println(address);
		return results;
	}
	
	public PlacesSearchResult[]  getNearBySearchResponse(LatLng latLang) throws ApiException, InterruptedException, IOException {
		NearbySearchRequest nearBySearchRequest = PlacesApi.nearbySearchQuery(context, latLang);
		nearBySearchRequest.radius(200);
		nearBySearchRequest.type(PlaceType.PARKING);
		nearBySearchRequest.openNow(true);
		PlacesSearchResponse placesSearchResponse = nearBySearchRequest.await();
		PlacesSearchResult[] placesResult = placesSearchResponse.results;
		return placesResult;
	}
	

}
