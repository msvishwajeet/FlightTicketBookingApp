package com.flight.service;

import java.util.List;

import com.flight.model.Airport;

public interface AirportService {
	void addAirport(String name);
	List<Airport> getAllAirport();
	Airport getAirport(String airportName);
}
