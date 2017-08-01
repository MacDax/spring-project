package com.parkit.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parkit.domain.ParkingMetersSFO;

public interface IReadCSVFile {
	
	public List<ParkingMetersSFO> readFile(String steetAddress) throws JsonProcessingException, IOException;
	
}
