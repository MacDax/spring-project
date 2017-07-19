package com.parkit.domain;

import org.springframework.stereotype.Component;

@Component
public class ParkingPlace {

	private String addressLine1;
	private String name;
	private double lat;
	private double lng;
	//private ParkingPlaceGeometry parkingPlaceGeometry;
	
	public ParkingPlace() {	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	/*public ParkingPlaceGeometry getParkingPlaceGeometry() {
		return parkingPlaceGeometry;
	}

	public void setParkingPlaceGeometry(ParkingPlaceGeometry geometry) {
		this.parkingPlaceGeometry = geometry;
	}
*/
	
}
