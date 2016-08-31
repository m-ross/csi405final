package project;

import java.time.*;

public class Fare {
	private String key;
	private String origin;
	private String destination;
	private LocalDate departureDate;
	private float amount;
	
	public Fare(String key, String origin, String destination, LocalDate departureDate, float amount) {
		this.key = key;
		this.origin = origin;
		this.destination = destination;
		this.departureDate = departureDate;
		this.amount = amount;
	}
	
	public String getKey() {
		return key;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public float getAmount() {
		return amount;
	}
}