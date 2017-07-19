package com.parkit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.parkit.domain.ParkingLots;
import com.parkit.domain.ParkingPlace;
import com.parkit.domain.UsersSerachCriteria;
import com.parkit.service.GooglePlaceAPIService;

//import com.google.maps.GeoApiContext;
@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";
	private String whereto;
	@Autowired
	ParkingPlace parkingPlace;
	@Autowired
	UsersSerachCriteria usersSerachCriteria;
	@Autowired 
	GooglePlaceAPIService googlePlaceService;
	
	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("usersSerachCriteria", new UsersSerachCriteria());
		return "welcome";
	}

	/*@RequestMapping(value="/parkhere", method=RequestMethod.POST)
	public String parkHere(@RequestParam("whereto") String whereto, Model model) throws ApiException, InterruptedException, IOException {
		model.addAttribute("where", whereto);
		return "/parkhere";
	}*/
	
	/*@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView welcome() {
		parkingPlace = new ParkingPlace();
		ModelAndView model = new ModelAndView("welcome", "parkingPlace", parkingPlace);
		return model;
	}*/
	
	@RequestMapping(value="/parkhere", method=RequestMethod.POST)
	public String submit(@ModelAttribute("usersSerachCriteria")UsersSerachCriteria usersSerachCriteria, Model model) throws ApiException, InterruptedException, IOException {
		String userDestination = usersSerachCriteria.getWhereto();
		System.out.println("whereto " + userDestination);
		ParkingLots parkingLots = new ParkingLots();
		List<ParkingPlace> parkingLotList = new ArrayList<>();
		//GeocodingResult[] results = googlePlaceService.getGeoCodingResult(userDestination);
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD_yBCEbeHzr6T71GroPsjXsApSNfjEems");
		GeocodingResult[] results =  GeocodingApi.geocode(context,  userDestination).await();  
		LatLng latLang = results[0].geometry.location;
		String address = results[0].formattedAddress;
		System.out.println(address);
		NearbySearchRequest nearBySearchRequest = PlacesApi.nearbySearchQuery(context, latLang); 
		nearBySearchRequest.radius(200);
		nearBySearchRequest.type(PlaceType.PARKING);
		PlacesSearchResponse placesSearchResponse = nearBySearchRequest.await();
		PlacesSearchResult[] placesResult = placesSearchResponse.results;
		System.out.println("lots list : " + placesResult.length);
		for(int i=0; i<placesResult.length; i++) {
			ParkingPlace parkingLot = new ParkingPlace();
			parkingLot.setAddressLine1(userDestination);
			parkingLot.setName(placesResult[i].name);
			parkingLot.setLat(placesResult[i].geometry.location.lat);
			parkingLot.setLng(placesResult[i].geometry.location.lng);
			parkingLotList.add(parkingLot);
		}
		
		parkingLots.setParkingPlaces(parkingLotList);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonParkingLots = mapper.writeValueAsString(parkingLots);
		
		
		//model.addAttribute("parkingPlace", parkingPlace);
		model.addAttribute("parkingLotsList", parkingLotList);
		model.addAttribute("jsonParkingLots", jsonParkingLots);
		
		return "/parkhere";
	}
	
}