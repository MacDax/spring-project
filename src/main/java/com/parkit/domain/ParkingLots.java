package com.parkit.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ParkingLots {
	
	private List<ParkingPlace> parkingPlaces;
	
	public ParkingLots() {}

	public List<ParkingPlace> getParkingPlaces() {
		return parkingPlaces;
	}

	public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
		this.parkingPlaces = parkingPlaces;
	}
	
	
}
