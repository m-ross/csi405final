package project;

import java.time.*;

public class Flight {
	private String key;
	private int onTimePerformance;
	private char originTerminal;
	private char destinationTerminal;
	private String origin;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private int flightTime;
	private int travelTime;
	
	public Flight(String key, int onTimePerformance, char originTerminal, char destinationTerminal,	String origin, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, int flightTime, int travelTime) {
		this.key = key;
		this.onTimePerformance = onTimePerformance;
		this.originTerminal = originTerminal;
		this.destinationTerminal = destinationTerminal;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flightTime = flightTime;
		this.travelTime = travelTime;
	}
	
	public String getKey() {
		return key;
	}
	
	public int getOnTimePerformance() {
		return onTimePerformance;
	}
	
	public char getOriginTerminal() {
		return originTerminal;
	}
	
	public char getDestinationTerminal() {
		return destinationTerminal;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public int getFlightTime() {
		return flightTime;
	}

	public int getTravelTime() {
		return travelTime;
	}
	
	public Flight clone() {
		Flight flight = new Flight(key, onTimePerformance, originTerminal, destinationTerminal,	origin, destination, departureTime, arrivalTime, flightTime, travelTime);
		return flight;
	}
}