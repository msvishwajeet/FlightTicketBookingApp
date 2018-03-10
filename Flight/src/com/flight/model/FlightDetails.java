package com.flight.model;

public class FlightDetails {
	private String name;
	private int id;
	private Airport to;
	private Airport from;
	private int capacity;
	private int initialFare;
	public int getInitialFare() {
		return initialFare;
	}
	public void setInitialFare(int initialFare) {
		this.initialFare = initialFare;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capaciy) {
		this.capacity = capaciy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Airport getTo() {
		return to;
	}
	public void setTo(Airport to) {
		this.to = to;
	}
	public Airport getFrom() {
		return from;
	}
	public void setFrom(Airport from) {
		this.from = from;
	}
	public TimeSlot getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(TimeSlot timeslot) {
		this.timeslot = timeslot;
	}
	private TimeSlot timeslot;

}
