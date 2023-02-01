package katalon.utility

import java.text.SimpleDateFormat

public class DateTimeUtility {
	public String getCurrentDateTime(String format = "ddMMyyHHmmss") {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
}
