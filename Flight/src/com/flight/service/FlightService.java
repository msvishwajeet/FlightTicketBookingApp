package com.flight.service;

import java.util.List;

import com.flight.model.Airport;
import com.flight.model.FlightDetails;
import com.flight.model.TimeSlot;

public interface FlightService {
	void addFlight(String name,int id,Airport to,Airport from,TimeSlot ts,int capacity,int initialFare);
	FlightDetails getById(int id);
	List<FlightDetails> getFlightBy(String boarding , String destination);
}
