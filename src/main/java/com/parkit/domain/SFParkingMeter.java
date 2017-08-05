package com.parkit.domain;

public class SFParkingMeter {
	private String streetName;
	private String postId;
	private String streeNumber;
	private String capColor;
	private double lat;
	private double lng;
	
	public SFParkingMeter() { 	super(); 	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCapColor() {
		return capColor;
	}

	public void setCapColor(String capColor) {
		this.capColor = capColor;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getStreeNumber() {
		return streeNumber;
	}

	public void setStreeNumber(String streeNumber) {
		this.streeNumber = streeNumber;
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

	@Override
	public String toString() {
		return "SFParkingMeter [streetName=" + streetName + ", postId=" + postId + ", streeNumber=" + streeNumber
				+ ", lat=" + lat + ", lng=" + lng + "]";
	}
	
	
	
}
