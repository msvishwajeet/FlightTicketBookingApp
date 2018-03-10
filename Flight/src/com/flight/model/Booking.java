package com.flight.model;

//import java.util.Date;
import java.util.List;

public class Booking {
	private List<CustomerDetails> cdtailsList;
	private int flightId;
	private int capacity;
	public List<CustomerDetails> getCdtailsList() {
		return cdtailsList;
	}
	public void setCdtailsList(List<CustomerDetails> cdtailsList) {
		this.cdtailsList = cdtailsList;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	//private Date d;
	
	/*public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}*/
	
}
