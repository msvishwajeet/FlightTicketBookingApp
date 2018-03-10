package com.flight.service;

import java.util.*;

import com.flight.model.CustomerDetails;
import com.flight.model.FlightDetails;

public interface BookingService {
	String booking(CustomerDetails cds,FlightDetails fds);
	int seatAvailable(int flightNumber , String date);
	List<CustomerDetails> getAllPassenger(int flightNumber,  String date);
	void addBookableFlight(int flightNumber);
	int getCurrentFare(FlightDetails fds,  String date);
	CustomerDetails pnrDetails(String pnr);
}
