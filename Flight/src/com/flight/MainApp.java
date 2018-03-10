package com.flight;

import java.util.*;
import com.flight.model.Airport;
import com.flight.model.CustomerDetails;
import com.flight.model.FlightDetails;
import com.flight.model.TimeSlot;
import com.flight.model.TimeSlot.Time;
import com.flight.service.AirportService;
import com.flight.service.AirportServiceImpl;
import com.flight.service.BookingService;
import com.flight.service.BookingServiceImpl;
import com.flight.service.FlightService;
import com.flight.service.FlightServiceImpl;

public class MainApp {
	public final Scanner sc=new Scanner(System.in);
	AirportService airportService;
	FlightService flightService;
	BookingService bookingSevice;
	public MainApp() {
		this.airportService=new AirportServiceImpl();
		this.flightService= new FlightServiceImpl(null, null);
		this.bookingSevice = new BookingServiceImpl(null);
	}
	
	
	public static void main(String[] args) {
		MainApp mainApp=new MainApp();
		mainApp.start();
	}

	public void start() {
		
		System.out.println("1.Add Airport");
		System.out.println("2.Add Flight");
		System.out.println("3.Search flight");
		System.out.println("4.Find Airport");
		System.out.println("5.Get flight by Id");
		System.out.println("6.Reseve a seat");
		System.out.println("7.See All Pasenger");
		System.out.println("8.Check Availibility");
		System.out.println("9.Check Current Fare");
		System.out.println("10.Check Pnr");
		System.out.println("-1. to close the app");
		System.out.println("Select your Option");
		int input = sc.nextInt();
		execute(input);
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	public void execute(int input) {
		if (input <-1 || input>10) {
			System.err.println("Wrong input");
			System.out.println();
			System.out.println("Select your Option from above only");
			start();
			return;
		}
		switch (input) {
		case -1:
			System.err.println("Application closed");
			return;
		case 1:
			addAirport();
			break;
		case 2:
			addFlight();
			break;
		case 3:
			searchFlight();
			break;
		case 4:
			getAllairport();
			break;
		case 5:
			getflight();
			break;
		case 6:
			getTicket();
			break;
		case 7:
			getAllPassenger();
			break;
		case 8:
			getAvailability();
			break;
		case 9:
			checkFare();
			break;
		case 10:
			checkPnr();
			break;
		default:
			break;
		}
	}
	private void checkPnr() {
		sc.nextLine();
		System.out.println("Enter your Pnr:");
		String pnr = sc.nextLine();
		CustomerDetails cdts = this.bookingSevice.pnrDetails(pnr);
		while (cdts == null) {
			System.out.println("Please Enter your correct Pnr");
			pnr = sc.nextLine();
			cdts = this.bookingSevice.pnrDetails(pnr);
		}
		System.out.println("Name -> "+cdts.getName());
		System.out.println("Flight Number->"+ cdts.getFlightDetails().getId());
		System.out.println("Flight Number->"+ cdts.getFlightDetails().getName());
		System.out.println("Seat No -> "+cdts.getSeatNo());
		System.out.println("Date of Journey -> "+cdts.getDate());
		System.out.println("Departure Time ->"+ cdts.getFlightDetails().getTimeslot().gettakeOffTime().getHour()+
				":"+cdts.getFlightDetails().getTimeslot().gettakeOffTime().getMinute());
		System.out.println("Date Of booking -> "+cdts.getDateOfBooking().toString());
		System.out.println("Amount Paid -> "+cdts.getAmountPaid());
		System.out.println("Status->  *****CONFIRMED*****");
		start();
	}

	private void checkFare() {
		sc.nextLine();
		System.out.println("Enter date");
		String date = sc.nextLine();
		System.out.println("Enter Flight id / Number");
		int id = sc.nextInt();
		sc.nextLine();
		FlightDetails fds = this.flightService.getById(id);
		System.out.println(this.bookingSevice.getCurrentFare(fds, date));
		start();
	}
	private void getAvailability() {
		System.out.println("Enter a Flight Number");
		int flightNumber = sc.nextInt();
		System.out.println("Enter a Date");
		sc.nextLine();
		String date = sc.nextLine();
		int result = this.bookingSevice.seatAvailable(flightNumber,date);
		if (result == -1) {
			result = this.flightService.getById(flightNumber).getCapacity();
		}
		System.out.println(result +" Seats Only");
		start();
	}
	private void getAllPassenger() {
		sc.nextLine();
		System.out.println("Enter a Date");
		String date = sc.nextLine();
		System.out.println("Enter a flight Number");
		int flightNumber = sc.nextInt();
		List<CustomerDetails> li = this.bookingSevice.getAllPassenger(flightNumber,date);
		System.out.println("Passenger travelling on "+date);
		for (CustomerDetails customerDetails : li) {
			
			System.out.println("----------------------------");
			System.out.println("Name-> "+customerDetails.getName() +" Seat Number.-> "+customerDetails.getSeatNo());
			System.out.println("----------------------------");
		}
		start();
	}


	private void getTicket() {
		sc.nextLine();
		System.out.println("Enter Date");
		String date = sc.nextLine();
		System.out.println("Enter Your Name");
		String name = sc.nextLine();
		System.out.println("Enter your Mobile Number");
		String moblie = sc.next();
		System.out.println("NEter your age");
		int age = sc.nextInt();
		CustomerDetails cds = new CustomerDetails();
		cds.setAge(age);
		cds.setContactNo(moblie);
		cds.setName(name);
		cds.setDate(date);
		System.out.println("Enter Flight Number");
		int id = sc.nextInt();
		FlightDetails fds = this.flightService.getById(id);
		int toBepaid = this.bookingSevice.getCurrentFare(fds, date);
		System.out.println("please Pay Rs. "+toBepaid+ "/- to book Your ticket");
		int amount = sc.nextInt();
		while (amount != toBepaid) {
			System.out.println("please Enter Correct amount to book your ticket ->"+toBepaid);
			amount = sc.nextInt();
		}
		cds.setAmountPaid(amount);
		String result = this.bookingSevice.booking(cds, fds);
		System.out.println();
		System.err.println("Please keep a record of your Pnr.-->"+result);
		System.out.println();
		sc.nextLine();
		start();
	}
	private void getflight() {
		System.out.println("Enter Flight Id");
		int id = sc.nextInt();
		FlightDetails flightDetails = this.flightService.getById(id);
		if (flightDetails == null) {
			System.err.println("No such Flight No found!!!!");
			System.out.println();
			sc.nextLine();
			start();
		}
		System.out.println("Name->"+flightDetails.getName()+" From->>"+flightDetails.getFrom().getCityName()+" To->>"+
		flightDetails.getTo().getCityName()+" Timimg->"+flightDetails.getTimeslot().gettakeOffTime().getHour()+
		":"+flightDetails.getTimeslot().gettakeOffTime().getMinute());
		start();
	}


	private void getAllairport() {
		List<Airport> list = this.airportService.getAllAirport();
		for (Airport airport : list) {
			System.out.println("-----------------------------------");
			System.out.println("****** "+airport.getCityName().toUpperCase()+" ******");
			System.out.println("-----------------------------------");
		}
		start();
	}


	private void searchFlight() {
		sc.nextLine();
		System.out.println("Enter Boarding Airport Name");
		String boardingAirport = sc.nextLine();
		System.out.println("Enter Destination Airport Name");
		String destinationAirport = sc.nextLine();
		List<FlightDetails> ls = this.flightService.getFlightBy(boardingAirport, destinationAirport);
		for(FlightDetails fdetails : ls) {
			System.out.println("------------------------------");
			System.out.println("Flight Number/Id-> "+fdetails.getId()+" --Name Of Flight-- "+ fdetails.getName());
			System.out.println("Take Off Timing:-> " +fdetails.getTimeslot().gettakeOffTime().getHour()+":"
					+fdetails.getTimeslot().gettakeOffTime().getHour());
			System.out.println("------------------------------");
		}
		start();
	}
	


	public void addFlight() {
		sc.nextLine();
		System.out.println("Enter Flight Name");
		String flightName = sc.nextLine();
		System.out.println("Enter Boarding Airport name");
		String from = sc.nextLine();
		while (this.airportService.getAirport(from) == null) {
			System.out.println("Please Enter available Airport Name only");
			from = sc.nextLine();
		}
		Airport airportFrom = this.airportService.getAirport(from);
		
		System.out.println("Enter Destination Airport name");
		String to = sc.nextLine();
		while (this.airportService.getAirport(to) == null) {
			System.out.println("Please Enter available Airport Name only");
			to = sc.nextLine();
		}
		Airport airportTo = this.airportService.getAirport(to);
		System.out.println("Enter TakeOff hour and Minute in HH:MM format only");
		String s[] = sc.nextLine().split(":");
		int hour = Integer.parseInt(s[0]);
		int minute = Integer.parseInt(s[1]);
		Time takeOffTime = new Time();
		takeOffTime.setMinute(minute);
		takeOffTime.setHour(hour);
		System.out.println("Enter Landing hour and Minute in HH:MM format only");
		String s1[] = sc.nextLine().split(":");
		int hourl = Integer.parseInt(s1[0]);
		int minutel = Integer.parseInt(s1[1]);
		Time landingTime = new Time();
		landingTime.setHour(hourl);
		landingTime.setMinute(minutel);
		TimeSlot timeslot = new TimeSlot(takeOffTime, landingTime);
		timeslot.setlandingTime(landingTime);
		timeslot.settakeOffTime(takeOffTime);
		System.out.println("Enter flight Id");
		int id = sc.nextInt();
		System.out.println("Enter Capacity");
		int capacity = sc.nextInt();
		System.out.println("Enter Initial-Fare");
		int initialFare = sc.nextInt();
		this.flightService.addFlight(flightName, id, airportTo, airportFrom, timeslot, capacity, initialFare);
		this.bookingSevice.addBookableFlight(id);
		start();
	}
	public void addAirport() {
		System.out.println("Enter a city name");
		sc.nextLine();
		String cityName = sc.nextLine();
		this.airportService.addAirport(cityName);
		start();
	}
}
