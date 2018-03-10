package com.flight.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.flight.model.Booking;
import com.flight.model.CustomerDetails;
import com.flight.model.FlightDetails;

public class BookingServiceImpl implements BookingService {
	public BookingServiceImpl(CustomerDetails cdtls ) {
	}
	Map<Integer, Map<String,Booking>> bookingRegister = new HashMap<>();
	Map<String, CustomerDetails> pnrDetails = new HashMap<>();
	@Override
	public String booking(CustomerDetails cds, FlightDetails fds) {
		String pnr;
		if (bookingRegister.get(fds.getId()) == null) {
			Booking booking = new Booking();
			booking.setFlightId(fds.getId());
			cds.setDateOfBooking(new Date());
			booking.setCapacity(fds.getCapacity()-1);
			cds.setSeatNo(booking.getCapacity()+1);
			String sett="";
			if (cds.getSeatNo() % 3 == 0) {
				sett= "side";
			}
			else if (cds.getSeatNo() % 2 != 0 && cds.getSeatNo() % 3 != 0) {
				sett= "middle";
			}
			else {
				sett="Window";
			}
			cds.setSeatName(sett);
			pnr = fds.getId()+fds.getName().substring(0, 2).toUpperCase()+booking.getCapacity()
			+cds.getName().substring(0, 2).toUpperCase();
			cds.setPnr(pnr);
			booking.setCdtailsList(new ArrayList<>());
			booking.getCdtailsList().add(cds);
			Map<String , Booking> bookingRegDate = new HashMap<>();
			bookingRegDate.put(cds.getDate(), booking);
			bookingRegister.put(fds.getId(), bookingRegDate);
			cds.setFlightDetails(fds);
			pnrDetails.put(pnr, cds);
			return pnr;
		}
		else {
			if (bookingRegister.get(fds.getId()).get(cds.getDate()) == null) {
				Map<String , Booking> bookingRegDate = new HashMap<>();
				Booking booking = new Booking();
				booking.setCapacity(fds.getCapacity()-1);
				booking.setFlightId(fds.getId());
				cds.setDateOfBooking(new Date());
				cds.setSeatNo(booking.getCapacity()+1);
				pnr = fds.getId()+fds.getName().substring(0, 2).toUpperCase()+booking.getCapacity()
				+cds.getName().substring(0, 2).toUpperCase();
				cds.setPnr(pnr);
				booking.setCdtailsList(new ArrayList<>());
				booking.getCdtailsList().add(cds);
				bookingRegDate.put(cds.getDate(), booking);
				bookingRegister.get(fds.getId()).put(cds.getDate(), booking);
				cds.setFlightDetails(fds);
				pnrDetails.put(pnr, cds);
				return pnr;
			}
			else {
				Booking booking = bookingRegister.get(fds.getId()).get(cds.getDate());
				cds.setDateOfBooking(new Date());
				cds.setSeatNo(booking.getCapacity());
				booking.setCapacity(booking.getCapacity()-1);
				pnr = fds.getId()+fds.getName().substring(0, 2).toUpperCase()+booking.getCapacity()
				+cds.getName().substring(0, 2).toUpperCase();
				cds.setPnr(pnr);
				cds.setFlightDetails(fds);
				booking.getCdtailsList().add(cds);
				pnrDetails.put(pnr, cds);
			}
		}
		return pnr;
	}

	@Override
	public int seatAvailable(int flightNumber, String date) {
		if (bookingRegister.get(flightNumber).get(date) == null) {
			return -1;
		}
		Booking booking = bookingRegister.get(flightNumber).get(date);
		return booking.getCapacity();
	}

	@Override
	public List<CustomerDetails> getAllPassenger(int flightNumber, String date) {
		List<CustomerDetails> li = new ArrayList<>();
		Booking booking = bookingRegister.get(flightNumber).get(date);
		Iterator<CustomerDetails > itr = booking.getCdtailsList().iterator();
		while (itr.hasNext()) {
			li.add(itr.next());
		}
		return li;
	}

	@Override
	public void addBookableFlight(int flightNumber) {
		Map<String , Booking> bookingRegDate = new HashMap<>();
		bookingRegister.put(flightNumber,bookingRegDate );
	}

	@Override
	public int getCurrentFare(FlightDetails fds, String date) {
		int flightNumber = fds.getId();
		int bookingcap = 0;
		if (bookingRegister.get(flightNumber).get(date) == null) {
			bookingcap = fds.getCapacity();
		}
		else {
			Booking booking = bookingRegister.get(flightNumber).get(date);
			bookingcap = booking.getCapacity();
		}
		int totalcapacityOfFlight = fds.getCapacity();
		int finalResult = fds.getInitialFare()+((totalcapacityOfFlight - bookingcap)*10);
		return finalResult;
	}

	@Override
	public CustomerDetails pnrDetails(String pnr) {
		return pnrDetails.get(pnr);
	}

}
