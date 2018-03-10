package com.flight.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.flight.model.Airport;

public class AirportServiceImpl implements AirportService {
	List<Airport> li= new ArrayList<>();
	@Override
	public void addAirport(String name) {
		Airport ar=new Airport();
		ar.setCityName(name);
		li.add(ar);
	}

	@Override
	public List<Airport> getAllAirport() {
		return li;
	}

	@Override
	public Airport getAirport(String airportName) {
		Iterator<Airport> ie = li.iterator();
		while (ie.hasNext()) {
			Airport airport = ie.next();
			if (airport.getCityName().equals(airportName)) {
				return airport;
			}
		}
		return null;
	}

}
