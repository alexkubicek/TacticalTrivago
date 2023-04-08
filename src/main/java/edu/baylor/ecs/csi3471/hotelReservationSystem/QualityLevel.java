package edu.baylor.ecs.csi3471.hotelReservationSystem;

public enum QualityLevel {
	EXECUTIVE(100.0), BUSINESS(80.0), COMFORT(70.0), ECONOMY(60.0), ALL(0.0);
	private final Double maxDailyRate;
	QualityLevel(Double r){ maxDailyRate=r;}
	public Double getRate() {return maxDailyRate;}
}
