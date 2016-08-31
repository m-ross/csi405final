package project;

import java.time.*;
import java.util.*;

public class PricePointList {
	private ArrayList<PricePoint> pricePoints;
	
	public PricePointList() {
		pricePoints = new ArrayList<PricePoint>();
	}
	
	public PricePointList(ArrayList<PricePoint> pricePoints) {
		this.pricePoints = pricePoints;
	}
	
	public void addPricePoint(PricePoint pricePoint) {
		pricePoints.add(pricePoint);
	}
	
	public void addPricePoint(String platingCarrier, float basePrice, float taxes, float changePenalty) {
		PricePoint pricePoint = new PricePoint(platingCarrier, basePrice, taxes, changePenalty);
		pricePoints.add(pricePoint);
	}
		
	public ArrayList<PricePoint> getPPByOrigDest(String origin, String destination) { // "get price points by origin and destination"
		ArrayList<PricePoint> list = new ArrayList<PricePoint>();
		
		for (PricePoint pricePoint: pricePoints) {
			// for (FlightOption flightOption: pricePoint.getFlightOptions()) {
				// if (flightOption.getOrigin().equals(origin) && flightOption.getDestination().equals(destination)) {
				if (pricePoint.getFlightOptions().get(0).getOrigin().equals(origin) && pricePoint.getFlightOptions().get(0).getDestination().equals(destination)) {
					list.add(pricePoint.clone());
				}
			// }
		}
		
		return list;
	}
	
	public static ArrayList<PricePoint> getPPByDeparture(ArrayList<PricePoint> pricePoints, FareList fareList, LocalDate departureDate) { // "get price points by departure date"
		for (int i = pricePoints.size() - 1; i >= 0; i--) { // for each PricePoint in the ArrayList argument
			for (String keyFare: pricePoints.get(i).getKeysFare()) { // for each keyFare in the PricePoint
				if (!fareList.getFare(keyFare).getDepartureDate().equals(departureDate)) { // if that fare does not have the same departureDate as the arguemnt
					pricePoints.remove(i); // remove the PP from the list
					break;
				}
			}
		}
		
		return pricePoints;
	}

	public static ArrayList<PricePoint> getPPByStops(ArrayList<PricePoint> pricePoints, SegmentList segmentList, int numberOfStops) { // "get price points by number of stops"
		boolean returnToOuterLoop;

		for (int i = pricePoints.size() - 1; i >= 0; i--) { // for each PricePoint in the ArrayList argument
			returnToOuterLoop = false;
		
			for (FlightOption flightOption: pricePoints.get(i).getFlightOptions()) { // for each FlightOption in the PricePoint
				for (Option option: flightOption.getOptions()) { // for each Option in the FlightOption
					for (BookingInfo bookingInfo: option.getBookingInfo()) { // for each BookingInfo in the Option
						if (segmentList.getSegment(bookingInfo.getKeySegment()).getNumberOfStops() != numberOfStops) { // if that BookingInfo does not have the same number of stops as the arguemnt
							pricePoints.remove(i); // remove PP from the list
							returnToOuterLoop = true; // because of so much nesting, this is to avoid attempting to remove the same PP twice
							break;
						}
					}
					
					if (returnToOuterLoop) break;
				}
				
				if (returnToOuterLoop) break;
			}
		}
		
		return pricePoints;
	}
}