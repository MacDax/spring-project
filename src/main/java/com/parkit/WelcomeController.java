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
import com.parkit.service.SfmtaAPIService;
import com.parkit.service.SoCrataAPIService;

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
	@Autowired 
	SfmtaAPIService sfmtaPlaceService;
	@Autowired
	SoCrataAPIService socrataService;
	
	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("usersSerachCriteria", new UsersSerachCriteria());
		socrataService.getParkingMetersInSFO();
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
		ParkingLots parkingLots = new ParkingLots();
		List<ParkingPlace> parkingLotList = new ArrayList<>();
		GeocodingResult[] results = googlePlaceService.getGeoCodingResult(userDestination);
		LatLng latLang = results[0].geometry.location;
		PlacesSearchResult[] placesResult = googlePlaceService.getNearBySearchResponse(latLang);
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
		model.addAttribute("parkingLotsList", parkingLotList);
		model.addAttribute("jsonParkingLots", jsonParkingLots);
		sfmtaPlaceService.getParkingDataWithPrice();
		return "/parkhere";
	}
	
}