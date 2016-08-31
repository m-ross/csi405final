package project;

import java.util.*;
import java.time.*;

public class FareList {
	private ArrayList<Fare> fares;
	
	public FareList() {
		fares = new ArrayList<Fare>();
	}
	
	public FareList(ArrayList<Fare> fares) {
		this.fares = fares;
	}
	
	public void addFare(Fare fare) {
		fares.add(fare);
	}
	
	public void addFare(String key, String origin, String destination, LocalDate departureDate, float amount) {
		Fare fare = new Fare(key, origin, destination, departureDate, amount);
		fares.add(fare);
	}
	
	public Fare getFare(String key) {
		for (Fare fare: fares) {
			if (fare.getKey().equals(key)) {
				return fare;
			}
		}
		
		return null;
	}
}