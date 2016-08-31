package project;

import java.util.*;

public class PricePoint {
	private String platingCarrier;
	private float totalPrice; // basePrices + taxes
	private float basePrice;
	private float taxes;
	private float changePenalty; // fee for changing flights
	private ArrayList<String> keysFare; // keys for referring to Fares
	private ArrayList<FlightOption> flightOptions;
	
	public PricePoint(String platingCarrier, float basePrice, float taxes, float changePenalty) {
		this.platingCarrier = platingCarrier;
		this.totalPrice = basePrice + taxes;
		this.basePrice = basePrice;
		this.taxes = taxes;
		this.changePenalty = changePenalty;
		keysFare = new ArrayList<String>();
		flightOptions = new ArrayList<FlightOption>();
	}
	
	public PricePoint(String platingCarrier, float basePrice, float taxes, float changePenalty, ArrayList<String> keysFare, ArrayList<FlightOption> flightOptions) {
		this.platingCarrier = platingCarrier;
		this.totalPrice = basePrice + taxes;
		this.basePrice = basePrice;
		this.taxes = taxes;
		this.changePenalty = changePenalty;
		this.keysFare = keysFare;
		this.flightOptions = flightOptions;
	}
	
	public void addKeyFare(String keyFare) {
		keysFare.add(keyFare);
	}
	
	public void addFlightOption(FlightOption flightOption) {
		flightOptions.add(flightOption);
	}
	
	public void addFlightOption(String origin, String destination, ArrayList<Option> options) {
		flightOptions.add(new FlightOption(origin, destination, options));
	}
	
	public String getPlatingCarrier() {
		return platingCarrier;
	}
		
	public float getTotalPrice() {
		return totalPrice;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public float getTaxes() {
		return taxes;
	}

	public ArrayList<String> getKeysFare() {
		return keysFare;
	}

	public float getChangePenalty() {
		return changePenalty;
	}

	public ArrayList<FlightOption> getFlightOptions() {
		return flightOptions;
	}
	
	public PricePoint clone() {
		PricePoint pricePoint = new PricePoint(platingCarrier, basePrice, taxes, changePenalty, keysFare, flightOptions);
		return pricePoint;
	}
}