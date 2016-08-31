package project;

public class BookingInfo {
	private String cabinClass;
	private String keyFare;
	private String keySegment;
	
	public BookingInfo(String cabinClass, String keyFare, String keySegment) {
		this.cabinClass = cabinClass;
		this.keyFare = keyFare;
		this.keySegment = keySegment;
	}
	
	public String getCabinClass() {
		return cabinClass;
	}

	public String getKeyFare() {
		return keyFare;
	}

	public String getKeySegment() {
		return keySegment;
	}
}