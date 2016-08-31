package project;

import java.util.*;
import java.time.*;

public class SegmentList {
	private ArrayList<Segment> segments;
	
	public SegmentList() {
		segments = new ArrayList<Segment>();
	}
	
	public SegmentList(ArrayList<Segment> segments) {
		this.segments = segments;
	}
	
	public void addSegment(Segment segment) {
		segments.add(segment);
	}
	
	public void addSegment(String key, ArrayList<String> keyFlights, String carrier, int numberOfStops, int changeOfPlane, int flightNumber, String origin, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, int flightTime, int distance) {
		Segment segment = new Segment(key, keyFlights, carrier, numberOfStops, changeOfPlane, flightNumber, origin, destination, departureTime, arrivalTime, flightTime, distance);
		segments.add(segment);
	}
	
	public Segment getSegment(String key) {
		for (Segment segment: segments) {
			if (segment.getKey().equals(key)) {
				return segment;
			}
		}
		
		return null;
	}
}