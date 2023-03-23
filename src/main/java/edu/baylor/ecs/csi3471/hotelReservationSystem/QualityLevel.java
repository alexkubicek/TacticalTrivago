public enum QualityLevel {
  EXECUTIVE,
	BUSINESS,
	COMFORT,
	ECONOMY;

	Double getRate() {
		if(EXECUTIVE != null) {
			return 10.0; //change when decided
		} else if(BUSINESS != null) {
			return 7.0;
		} else if(COMFORT != null) {
			return 5.0;
		}else if(ECONOMY != null) {
			return 3.0;
		}
		return null;
	}
}
