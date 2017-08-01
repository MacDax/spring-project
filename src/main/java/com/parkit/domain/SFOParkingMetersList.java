package com.parkit.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SFOParkingMetersList {
	private List<ParkingMetersSFO> parkingPlaces;

	public SFOParkingMetersList() {
		super();
	}

	public List<ParkingMetersSFO> getParkingPlaces() {
		return parkingPlaces;
	}

	public void setParkingPlaces(List<ParkingMetersSFO> parkingPlaces) {
		this.parkingPlaces = parkingPlaces;
	}
	
	
}
