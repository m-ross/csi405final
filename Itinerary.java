package project;

import java.time.*;
import java.util.*;

public class Itinerary {
	private float totalPrice;
	private LocalTime travelTime;
	private ArrayList<Connection> connections;
	
	public Itinerary(float totalPrice, LocalTime travelTime) {
		this.totalPrice = totalPrice;
		this.travelTime = travelTime;
		connections = new ArrayList<Connection>();
	}
	
	public void addAmount(float amount) { // for adding the price ("amount") on a fare to the totalPrice dictated by the PricePoint
		totalPrice += amount;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}
	
	public LocalTime getTravelTime() {
		return travelTime;
	}
	
	public void addConnection(String cabinClass, String origin, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime) {
		connections.add(new Connection(cabinClass, origin, destination, departureTime, arrivalTime));
	}
	
	public ArrayList<Connection> getConnections() {
		return connections;
	}
}
