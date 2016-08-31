package project;

import java.util.*;
import java.time.*;

public class Segment {
	private String key;
	private ArrayList<String> keyFlights;
	private String carrier;
	private int flightNumber;
	private int numberOfStops;
	private int changeOfPlane;
	private String origin;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private int flightTime;
	private int distance;
	
	public Segment(String key, ArrayList<String> keyFlights, String carrier, int flightNumber, int numberOfStops, int changeOfPlane, String origin, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, int flightTime, int distance) {
		this.key = key;
		this.keyFlights = keyFlights;
		this.carrier = carrier;
		this.flightNumber = flightNumber;
		this.numberOfStops = numberOfStops;
		this.changeOfPlane = changeOfPlane;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flightTime = flightTime;
		this.distance = distance;
	}
	
	public String getKey() {
		return key;
	}

	public ArrayList<String> getKeyFlights() {
		return keyFlights;
	}

	public String getCarrier() {
		return carrier;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public int getNumberOfStops() {
		return numberOfStops;
	}

	public int getChangeOfPlane() {
		return changeOfPlane;
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

	public int getDistance() {
		return distance;
	}
}