package com.parkit.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SFOParkingMetersList {
	private List<SFParkingMeter> parkingPlaces;

	public SFOParkingMetersList() {
		super();
	}

	public List<SFParkingMeter> getParkingPlaces() {
		return parkingPlaces;
	}

	public void setParkingPlaces(List<SFParkingMeter> parkingPlaces) {
		this.parkingPlaces = parkingPlaces;
	}
	
}
