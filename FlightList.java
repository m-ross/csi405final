package project;

import java.util.*;
import java.time.*;

public class FlightList {
	private ArrayList<Flight> flights;
	
	public FlightList() {
		flights = new ArrayList<Flight>();
	}
	
	public FlightList(ArrayList<Flight> flights) {
		this.flights = flights;
	}
	
	public void addFlight(Flight flight) {
		flights.add(flight.clone());
	}
	
	public void addFlight(String key, int onTimePerformance, char originTerminal, char destinationTerminal, String origin, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, int flightTime, int travelTime) {
		Flight flight = new Flight(key, onTimePerformance, originTerminal, destinationTerminal, origin, destination, departureTime, arrivalTime, flightTime, travelTime);
		flights.add(flight);
	}
	
	public Flight getFlight(String key) {
		for (Flight flight: flights) {
			if (flight.getKey().equals(key)) {
				return flight.clone();
			}
		}
		
		return null;
	}
}