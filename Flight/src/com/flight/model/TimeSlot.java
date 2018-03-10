package com.flight.model;
public class TimeSlot {
	public TimeSlot(Time takeOffTime,Time landingTime) {
	}
	private Time takeOffTime;
	private Time landingTime;
	
	public Time gettakeOffTime() {
		return takeOffTime;
	}

	public void settakeOffTime(Time takeOffTime) {
		this.takeOffTime = takeOffTime;
	}

	public Time getlandingTime() {
		return landingTime;
	}

	public void setlandingTime(Time landingTime) {
		this.landingTime = landingTime;
	}

	public static class Time {
		private int hour;
		private int minute;
		public int getHour() {
			return hour;
		}
		public void setHour(int hour) {
			this.hour = hour;
		}
		public int getMinute() {
			return minute;
		}
		public void setMinute(int minute) {
			this.minute = minute;
		}
		
	}
}
