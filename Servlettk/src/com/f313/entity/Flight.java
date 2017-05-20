package com.f313.entity;



public class Flight {

	String FlightId;
	String StartTime;
	String StartPoint;
	String Destination;
	int RemainSead;
	float Price;
	
	public String getFlightId() {
		return FlightId;
	}
	public void setFlightId(String flightId) {
		FlightId = flightId;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getStartPoint() {
		return StartPoint;
	}
	public void setStartPoint(String startPoint) {
		StartPoint = startPoint;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public int getRemainSead() {
		return RemainSead;
	}
	public void setRemainSead(int remainSead) {
		RemainSead = remainSead;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	
	
}
