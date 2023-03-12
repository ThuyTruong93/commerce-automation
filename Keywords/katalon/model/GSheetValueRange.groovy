package katalon.model

public class GSheetValueRange {
	String range
	String majorDimension
	List<List<String>> values

	public GSheetValueRange() {
	}

	public GSheetValueRange(String majorDimension, ArrayList values) {
		this.majorDimension = majorDimension
		this.values = values
	}
}
