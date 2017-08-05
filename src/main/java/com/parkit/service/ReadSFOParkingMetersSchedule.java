package com.parkit.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.maps.model.LatLng;
import com.parkit.domain.ParkingMetersSFO;
import com.parkit.domain.SFParkingMeter;


@Service
public class ReadSFOParkingMetersSchedule {
	private List<ParkingMetersSFO> SFOParkingMetersList;
	
	private List<SFParkingMeter> SFOParkingMeters;
	
	public List<SFParkingMeter> getParkingMeters(final String formattedAddress, final LatLng location) throws JsonProcessingException, IOException {
		String inputStreet = formatInputParameter(formattedAddress);
		SFOParkingMetersList = readFile(inputStreet);
		SFOParkingMeters = findNearByParkingMetersLocations(SFOParkingMetersList, location);
		return SFOParkingMeters;
	}
	
	public String formatInputParameter(final String formattedAddress) throws JsonProcessingException, IOException {
		String inputStreet = null;
		if(Character.isDigit(formattedAddress.charAt(0)) && !(Character.isDigit(formattedAddress.charAt(1)))) {
			inputStreet = "0" + formattedAddress;
		}else {
			String[] formatInput = formattedAddress.split(" ");
			inputStreet = formatInput[1] + " " + "St";
		}
		//System.out.println("format inputStret : " + inputStreet);
		SFOParkingMetersList = readFile(inputStreet);
		System.out.println("whole list " + SFOParkingMetersList.size());
		
		//List<ParkingMetersSFO> SFOParkingMeters = findNearByParkingMetersLocations(SFOParkingMetersList, location);
		//System.out.println(" filetered list " + SFOParkingMeters.size());
		//return SFOParkingMeters;
		return inputStreet;
	}
	
	private List<SFParkingMeter> findNearByParkingMetersLocations(List<ParkingMetersSFO> sFOParkingMetersList2, final LatLng location) {
		double lat2 = location.lat;
		double lng2 = location.lng;
		List<SFParkingMeter> nearbyMeters = new ArrayList<>();
		for(ParkingMetersSFO parkingMeter : sFOParkingMetersList2) {
		//for(int i=0; i<3; i++) {
			String locationStr = parkingMeter.getLocation();
			//String locationStr = sFOParkingMetersList2.get(i).getLocation();
			locationStr = locationStr.substring(1, locationStr.length()-1);
			String[] locs = locationStr.split(",");
			
			double lat1 = Double.parseDouble(locs[0]);
			double lng1 = Double.parseDouble(locs[1]);
			//System.out.println("lat " + lat1 + "   lng1  " + lng1 + " name  " + sFOParkingMetersList2.get(i).getStreetName());
			if(distance(lat2, lng2, lat1, lng1) < 0.600) {
				//System.out.println("latin " + lat1 + "   lng1in  " + lng1);
				SFParkingMeter meter = new SFParkingMeter();
				meter.setStreetName(parkingMeter.getStreetName());
				/*String[] latlng = parkingMeter.getLocation().split(","); 
				meter.setLat(Double.parseDouble(latlng[0].substring(0)));
				meter.setLng(Double.parseDouble(latlng[1].substring(latlng[1].length()-1)));*/
				meter.setLat(lat1);
				meter.setLng(lng1);
				nearbyMeters.add(meter);
				//nearbyMeters.add(sFOParkingMetersList2.get(i));
			}
			
		}
		return nearbyMeters;
	}
	
	private double distance(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 3958.75; //in miles, change to 6371 for km
		double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double sindLat = Math.sin(dLat/2);
		double sindLng = Math.sin(dLng/2);
		System.out.println("lat " + lat1 + "   lng1  " + lng1);
		System.out.println("lat2 " + lat2 + "   lng2  " + lng2);
		double a = Math.pow(sindLat, 2)+Math.pow(sindLng, 2)*Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2));
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = earthRadius * c;
		System.out.println("dist : " + dist);
		return dist;
	}

	public List<ParkingMetersSFO> readFile(final String streetName) throws JsonProcessingException, IOException {
		File file = new File("E:/ParkCOMPASS/ParkCompass/spring-boot-web-jsp/src/main/resources/services/sfdata/Parking_meters.csv");
		CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
		
		ObjectMapper mapper = new CsvMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		//mapper.readerFor(ParkingMetersSFO.class).with(bootstrapSchema).readValue(file);
		//MappingIterator<ParkingMetersSFO> it = mapper.readerFor(ParkingMetersSFO.class).with(bootstrapSchema).readValues(file);
		MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class).with(bootstrapSchema).readValues(file);
		
		SFOParkingMetersList = new ArrayList<>();
		//System.out.println("input " + streetName) ;
		/*Map<String, String> rowAsMap = it.next();
		ParkingMetersSFO parkingMeter = new ParkingMetersSFO();
		parkingMeter.setLocation(rowAsMap.get("LOCATION"));
		parkingMeter.setStreetName(rowAsMap.get("STREETNAME"));
		parkingMeter.setLocation(rowAsMap.get("LOCATION"));
		SFOParkingMetersList.add(parkingMeter);*/
		//ParkingMetersSFO row = it.next();
		//System.out.println("row " + row.getCapColor() + "  " + row.getLocation());
		while(it.hasNext()) {
			Map<String, String> rowAsMap = it.next();
			/*if(streetName.compareTo(rowAsMap.get("STREETNAME"))==1) {
				break;
			} else 	*/
	if(streetName.equalsIgnoreCase(rowAsMap.get("STREETNAME")) && "Grey".equalsIgnoreCase(rowAsMap.get("CAP_COLOR")) && "ON".equalsIgnoreCase(rowAsMap.get("ON_OFF_STR"))) {
				ParkingMetersSFO parkingMeter = new ParkingMetersSFO();
				parkingMeter.setLocation(rowAsMap.get("LOCATION"));
				parkingMeter.setStreetName(rowAsMap.get("STREETNAME"));
				parkingMeter.setLocation(rowAsMap.get("LOCATION"));
				SFOParkingMetersList.add(parkingMeter);
			}
		}
		return SFOParkingMetersList;
	}
	
}
