/*	Program       Flight Itinerary Builder - Final Project
	Author:       Marcus Ross
	Description:  The classes used by this program were created by Worldspan, a provider of travel technology. The author takes no credit for the nonsensical, harebrained classes. This program requires four plain text files to read data from, each of which has a particular format: SegmentData.txt, FlightData.txt, FareData.txt, PricePointData.txt. All of the data available currently describes only flights from SAT (Salt Lake City) to LAX (Los Angeles) on 2016-05-13. The program constructs, using the data as well as the origin, destination, and date given by the user, every possible route to travel from the origin to destination. */

package project;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;

public class Main {
	public static void main(String args[]) {
		FlightList flightList = new FlightList();
		SegmentList segmentList = new SegmentList();
		FareList fareList = new FareList();
		PricePointList pricePointList = new PricePointList();
		
		initFlightList(flightList); // put all data in the text file into the FlightList
		initSegmentList(segmentList); // put all data in the text file into the SegmentList
		initFareList(fareList); // put all data in the text file into the FareList
		initPricePointList(pricePointList); // put all data in the text file into the PricePointList
		
		showPrompt(flightList, segmentList, fareList, pricePointList); // show prompt and display response
	}
	
	public static void initFlightList(FlightList flightList) {
		Scanner inFile = null;
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME; // yyyy-mm-dd'T'hh:mm:ss+-timezone
		
		String key;
		int onTimePerformance;
		char originTerminal;
		char destinationTerminal;
		String origin;
		String destination;
		LocalDateTime departureTime;
		LocalDateTime arrivalTime;
		int flightTime;
		int travelTime;
		
		try {
			inFile = new Scanner(new BufferedReader(new FileReader(new File("FlightData.txt"))));
		} catch(FileNotFoundException e) {
			System.out.println("FlightList file not found.");
			System.exit(-1);
		}
		
		while (inFile.hasNext()) { // until EOF is reached, read 10 lines at a time and add Flights to the FlightList
			key = inFile.nextLine();
			onTimePerformance = Integer.parseInt(inFile.nextLine());
			originTerminal = inFile.nextLine().charAt(0);
			destinationTerminal = inFile.nextLine().charAt(0);
			origin = inFile.nextLine();
			destination = inFile.nextLine();
			departureTime = LocalDateTime.parse(inFile.nextLine(), formatter);
			arrivalTime = LocalDateTime.parse(inFile.nextLine(), formatter);
			flightTime = Integer.parseInt(inFile.nextLine());
			travelTime = Integer.parseInt(inFile.nextLine());
			
			flightList.addFlight(key, onTimePerformance, originTerminal, destinationTerminal, origin, destination, departureTime, arrivalTime, flightTime, travelTime);
		}
	}
	
	public static void initSegmentList(SegmentList segmentList) {
		Scanner inFile = null;
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		
		String key;
		String keyFlight[] = new String[2]; // is an array to hold tokens
		ArrayList<String> keyFlights = new ArrayList<String>();
		String carrier;
		int flightNumber;
		int numberOfStops;
		int changeOfPlane;
		String origin;
		String destination;
		LocalDateTime departureTime;
		LocalDateTime arrivalTime;
		int flightTime;
		int distance;
		
		try {
			inFile = new Scanner(new BufferedReader(new FileReader(new File("SegmentData.txt"))));
		} catch(FileNotFoundException e) {
			System.out.println("SegmentList file not found.");
			System.exit(-1);
		}
		
		while (inFile.hasNext()) {
			key = inFile.nextLine();
			keyFlight[0] = inFile.nextLine(); // each 
			
			keyFlights = new ArrayList<String>();
			
			if (keyFlight[0].contains("\t")) { // if the line had a tab in it
				keyFlight = keyFlight[0].split("\t"); // then that tab delimited two keys
				keyFlights.add(keyFlight[1]); // so try to add a second key only in this statement
			}
			
			keyFlights.add(keyFlight[0]); // add the first key anyway
			carrier = inFile.nextLine();
			flightNumber = Integer.parseInt(inFile.nextLine());
			numberOfStops = Integer.parseInt(inFile.nextLine());
			changeOfPlane = Integer.parseInt(inFile.nextLine());
			origin = inFile.nextLine();
			destination = inFile.nextLine();
			departureTime = LocalDateTime.parse(inFile.nextLine(), formatter);
			arrivalTime = LocalDateTime.parse(inFile.nextLine(), formatter);
			flightTime = Integer.parseInt(inFile.nextLine());
			distance = Integer.parseInt(inFile.nextLine());
			
			segmentList.addSegment(key, keyFlights, carrier, numberOfStops, changeOfPlane, flightNumber, origin, destination, departureTime, arrivalTime, flightTime, distance);
		}
		
		
	}
	
	public static void initFareList(FareList fareList) {
		Scanner inFile = null;
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-mm-dd
		
		String key;
		String origin;
		String destination;
		LocalDate departureDate;
		float amount;
		
		try {
			inFile = new Scanner(new BufferedReader(new FileReader(new File("FareData.txt"))));
		} catch(FileNotFoundException e) {
			System.out.println("FareInfoList file not found.");
			System.exit(-1);
		}
		
		while (inFile.hasNext()) {
			key = inFile.nextLine();
			origin = inFile.nextLine();
			destination = inFile.nextLine();
			departureDate = LocalDate.parse(inFile.nextLine(), formatter);
			amount = Float.parseFloat(inFile.nextLine());
			
			fareList.addFare(key, origin, destination, departureDate, amount);
		}
	}
	
	public static void initPricePointList(PricePointList pricePointList) {
		Scanner inFile = null;
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
		
		// PricePoint attributes
		PricePoint pricePoint;
		String platingCarrier;
		float basePrice;
		float taxes;
		float changePenalty;
		
		// FlightOption attributes
		FlightOption flightOption;
		String origin;
		String destination;
		
		// Option attribute
		Option option;
		LocalTime travelTime;
		
		// BookingInfo attributes
		String temp[] = new String[3]; // for holding tokens
		String cabinClass;
		String keyFare;
		String keySegment;
			
		try {
			inFile = new Scanner(new BufferedReader(new FileReader(new File("PricePointData.txt"))));
		} catch(FileNotFoundException e) {
			System.out.println("PricePointList file not found.");
			System.exit(-1);
		}
		
		while (inFile.hasNext()) {
			platingCarrier = inFile.nextLine();
			basePrice = Float.parseFloat(inFile.nextLine());
			taxes = Float.parseFloat(inFile.nextLine());
			changePenalty = Float.parseFloat(inFile.nextLine());
			pricePoint = new PricePoint(platingCarrier, basePrice, taxes, changePenalty);
			
			for (int i = 0; i < 2; i++) { // there are always exactly two FlightOption per PricePoint
				origin = inFile.nextLine();
				destination = inFile.nextLine();
				flightOption = new FlightOption(origin, destination);
				
				while (true) { // there are an unknown # of Option for each FlightOption
					travelTime = LocalTime.parse(inFile.nextLine(), formatter);
					option = new Option(travelTime);
					
					while (true) { // also unknown # of BookingInfo per Option
						temp[0] = inFile.nextLine();
						temp = temp[0].split("\t"); // each BookingInfo line has three values delimited by \t
						cabinClass = temp[0];
						keyFare = temp[1];
						keySegment = temp[2];
						option.addBookingInfo(cabinClass, keyFare, keySegment);
						pricePoint.addKeyFare(keyFare);
						
						if (inFile.hasNext("Economy")) {
							continue; // if the next line has "Economy" in it, it's another BookingInfo
						}
						else { // if it doesn't have "Economy", stop looking for more BookingInfo, but put the now-complete Option into the FlightOption
							flightOption.addOption(option);
							break;
						}
					}
					
					if (inFile.hasNext(Pattern.compile("[A-Z][A-Z]")) || inFile.hasNext(Pattern.compile("[A-Z][A-Z][A-Z]")) || !inFile.hasNext()) { // if the next line has exactly 2 or exactly 3 uppercase letters, we know it's either the origin of the next FlightOption or the carrier of the next PricePoint, so add the now-complete FlightOption to the current PP
						pricePoint.addFlightOption(flightOption);
						break;
					}
				}
			}
			
			pricePointList.addPricePoint(pricePoint);
		}
	}
	
	public static void showPrompt(FlightList flightList, SegmentList segmentList, FareList fareList, PricePointList pricePointList) {
		Scanner inKbd = new Scanner(System.in);
		String origin;
		String destination;
		int numberOfStops;
		LocalDate departureDate;
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		
		System.out.printf("Airline Itinerary Builder by Marcus Ross\n\n");
		System.out.printf("Enter an origin airport code: [please enter SAT or LAX] ");
		origin = inKbd.nextLine();
		System.out.printf("Enter a destination airport code: [please enter LAX or SAT] ");
		destination = inKbd.nextLine();
		// System.out.printf("Enter a desired number of stops: ");
		// numberOfStops = Integer.parseInt(inKbd.nextLine());
		numberOfStops = 0;
		System.out.printf("Enter a desired day of departure (yyyy-mm-dd): [please enter 2016-05-13] ");
		departureDate = LocalDate.parse(inKbd.nextLine(), formatter);
		
		displayItineraries(flightList, segmentList, fareList, pricePointList, origin, destination, numberOfStops, departureDate);
		inKbd.close();
	}
	
	public static void displayItineraries(FlightList flightList, SegmentList segmentList, FareList fareList, PricePointList pricePointList, String origin, String destination, int numberOfStops, LocalDate departureDate) {
		ArrayList<PricePoint> pricePoints;
		ArrayList<Itinerary> itineraries;
		
		pricePoints = pricePointList.getPPByOrigDest(origin, destination); // this begins the search by gathering all the PricePoints with matching origin and destination
		pricePoints = PricePointList.getPPByDeparture(pricePoints, fareList, departureDate); // this refines the search by removing all PricePoints without the matching departure date
		pricePoints = PricePointList.getPPByStops(pricePoints, segmentList, numberOfStops); // this refines the search by removing all PP without the matching number of stops
		
		if (pricePoints.size() == 0) { // if pricePoints has been reduced to nothing
			System.out.printf("\nNo matching itineraries found.\n"); // then program is done
			return;
		}
		else {
			itineraries = createItineraries(flightList, segmentList, fareList, pricePoints);
			printItineraries(itineraries);
		}
	}
	
	public static ArrayList<Itinerary> createItineraries(FlightList flightList, SegmentList segmentList, FareList fareList, ArrayList<PricePoint> pricePoints) {
		ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
		Itinerary itinerary;
		float totalPrice;
		LocalTime travelTime;
		String cabinClass = null;
		String origin = null;
		String destination = null;
		LocalDateTime departureTime = null;
		LocalDateTime arrivalTime = null;
		
		for (PricePoint pricePoint: pricePoints) {
			totalPrice = pricePoint.getTotalPrice();
			
			// for (FlightOption flightOption: pricePoint.getFlightOptions()) {
			for (Option option: pricePoint.getFlightOptions().get(0).getOptions()) {
				travelTime = option.getTravelTime();
				itinerary = new Itinerary(totalPrice, travelTime);
				
				for (BookingInfo bookingInfo: option.getBookingInfo()) {
					cabinClass = bookingInfo.getCabinClass();
					
					// keyFlights = segmentList.getSegment(bookingInfo.getKeySegment()).getKeyFlights();
					for (String keyFlight: segmentList.getSegment(bookingInfo.getKeySegment()).getKeyFlights()) {
						origin = flightList.getFlight(keyFlight).getOrigin();
						destination = flightList.getFlight(keyFlight).getDestination();
						departureTime = flightList.getFlight(keyFlight).getDepartureTime();
						arrivalTime = flightList.getFlight(keyFlight).getArrivalTime();
						itinerary.addConnection(cabinClass, origin, destination, departureTime, arrivalTime);
					}
					
					itinerary.addAmount(fareList.getFare(bookingInfo.getKeyFare()).getAmount());
					itineraries.add(itinerary);
				}
				
			}
			// }
			
			
		}
		
		return itineraries;
	}
	
	public static void printItineraries(ArrayList<Itinerary> itineraries) {
		int index = 1;
	
		for (Itinerary itinerary: itineraries) {
			if (index > 25) { // display only first 25 results, as they are generally sorted by price
				break;
			}
			
			System.out.printf("\n%3d:%3s$%7.2f%3s Travel time: %s\n", index++, "", itinerary.getTotalPrice(), "", itinerary.getTravelTime().toString()); // heading for each itinerary
			
			for (Connection connection: itinerary.getConnections()) {
				System.out.printf("%18s%s to %s%3sDeparture at %s%3sArrival at %s%3sCabin: %s\n", "", connection.getOrigin(), connection.getDestination(), "", connection.getDepartureTime().toString(), "", connection.getArrivalTime().toString(), "", connection.getCabinClass()); // plus another line for each leg of the trip
			}
			
		}
	}
}