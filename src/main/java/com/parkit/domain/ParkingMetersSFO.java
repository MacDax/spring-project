package com.parkit.domain;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Component
@JsonPropertyOrder({"POST_ID", "MS_ID", "MS_SPACEID", "CAP_COLOR", "METER_TYPE", "SMART_METE", "ACTIVESENS", "JURISDICTI", "ON_OFF_STR", "OSP_ID", "STREET_NUM", "STREETNAME", "STREET_SEG", "RATEAREA", "SFPARKAREA", "LOCATION"})
public class ParkingMetersSFO {
	private String postId;
	private int msId;
	private String msspaceId;
	private String capColor;
	private String meterType;
	private String smartMeter;
	private String activeSensor;
	private String jurisdiction;
	private String onoffStreet;
	private String ospId;
	private String streetNumber;
	private String streetName;
	private String streetSegment;
	private String rateArea;
	private String sfparkArea;
	private String location;
 
	public ParkingMetersSFO() { super(); }

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public int getMsId() {
		return msId;
	}

	public void setMsId(int msId) {
		this.msId = msId;
	}

	public String getMsspaceId() {
		return msspaceId;
	}

	public void setMsspaceId(String msspaceId) {
		this.msspaceId = msspaceId;
	}

	public String getCapColor() {
		return capColor;
	}

	public void setCapColor(String capColor) {
		this.capColor = capColor;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getSmartMeter() {
		return smartMeter;
	}

	public void setSmartMeter(String smartMeter) {
		this.smartMeter = smartMeter;
	}

	public String getActiveSensor() {
		return activeSensor;
	}

	public void setActiveSensor(String activeSensor) {
		this.activeSensor = activeSensor;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getOnoffStreet() {
		return onoffStreet;
	}

	public void setOnoffStreet(String onoffStreet) {
		this.onoffStreet = onoffStreet;
	}

	public String getOspId() {
		return ospId;
	}

	public void setOspId(String ospId) {
		this.ospId = ospId;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetSegment() {
		return streetSegment;
	}

	public void setStreetSegment(String streetSegment) {
		this.streetSegment = streetSegment;
	}

	public String getRateArea() {
		return rateArea;
	}

	public void setRateArea(String rateArea) {
		this.rateArea = rateArea;
	}

	public String getSfparkArea() {
		return sfparkArea;
	}

	public void setSfparkArea(String sfparkArea) {
		this.sfparkArea = sfparkArea;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
