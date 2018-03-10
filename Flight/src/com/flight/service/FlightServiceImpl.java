package com.flight.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.flight.model.Airport;
import com.flight.model.FlightDetails;
import com.flight.model.TimeSlot;

public class FlightServiceImpl implements FlightService{
	public FlightServiceImpl(Airport as,TimeSlot ts) {
		
	}
	Map< Integer ,FlightDetails > m=new HashMap<>();
	@Override
	public void addFlight(String name,int id,Airport to,Airport from,TimeSlot ts,int capacity,int initialFare) {
		FlightDetails fds=new FlightDetails();
		fds.setId(id);
		fds.setName(name);
		fds.setFrom(from);
		fds.setTo(to);
		fds.setTimeslot(ts);
		fds.setCapacity(capacity);
		fds.setInitialFare(initialFare);
		m.put(id, fds);
		System.out.println("Success");
	}
	@Override
	public List<FlightDetails> getFlightBy(String boarding, String destination) {
		List<FlightDetails> list = new ArrayList<FlightDetails>();
		Iterator<Integer> it = m.keySet().iterator();
		while (it.hasNext()) {
			FlightDetails flightDetails= m.get(it.next());
			if ((flightDetails.getTo().getCityName().equals(destination))&&
					(flightDetails.getFrom().getCityName().equals(boarding))) {
				list.add(flightDetails);
			}
		}
		return list;
	}
	@Override
	public FlightDetails getById(int id) {
		FlightDetails flightDetails = m.get(id);
		return flightDetails;
	}

}
