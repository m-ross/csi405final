package project;

import java.time.*;
import java.util.*;

public class Option {
	private LocalTime travelTime;
	private ArrayList<BookingInfo> bookingInfo;
	
	public Option(LocalTime travelTime) {
		this.travelTime = travelTime;
		bookingInfo = new ArrayList<BookingInfo>();
	}
		
	public Option(LocalTime travelTime, BookingInfo bookingInfo) {
		this.travelTime = travelTime;
		this.bookingInfo = new ArrayList<BookingInfo>();
		this.bookingInfo.add(bookingInfo);
	}
	
	public Option(LocalTime travelTime, ArrayList<BookingInfo> bookingInfo) {
		this.travelTime = travelTime;
		this.bookingInfo = bookingInfo;
	}
	
	public void addBookingInfo(BookingInfo bookingInfo) {
		this.bookingInfo.add(bookingInfo);
	}
	
	public void addBookingInfo(String cabinClass, String keyFare, String keySegment) {
		bookingInfo.add(new BookingInfo(cabinClass, keyFare, keySegment));
	}
	
	public LocalTime getTravelTime() {
		return travelTime;
	}
	
	public ArrayList<BookingInfo> getBookingInfo() {
		return bookingInfo;
	}
}