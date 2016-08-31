package project;

import java.util.ArrayList;
import java.time.*;

public class FlightOption {
	private String origin;
	private String destination;
	private ArrayList<Option> options;
	
	public FlightOption(String origin, String destination) {
		this.origin = origin;
		this.destination = destination;
		options = new ArrayList<Option>();
	}
	
	public FlightOption(String origin, String destination, ArrayList<Option> options) {
		this.origin = origin;
		this.destination = destination;
		this.options = options;
	}
	
	public void addOption(Option option) {
		options.add(option);
	}
	
	public void addOption(LocalTime travelTime, BookingInfo bookingInfo) {
		options.add(new Option(travelTime, bookingInfo));
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public ArrayList<Option> getOptions() {
		return options;
	}
}