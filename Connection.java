package project;

import java.time.*;

public class Connection {
	private String cabinClass;
	private String origin;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;

	public Connection(String cabinClass, String origin, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime) {
		this.cabinClass = cabinClass;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}
	
	public String getCabinClass() {
		return cabinClass;
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
	
}