package katalon.utility

import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.IntStream



public class CommonUtility {

	public String substringUseRegExp(String input, String regexp) {
		Pattern pattern = Pattern.compile(regexp)
		Matcher matcher = pattern.matcher(input)
		if(!matcher.find()) {
			println 'Regexp not work'
		} else {
			return matcher.group(0)
		}
	}

	static public List<String> generateRandomUUIDs(int size) {
		return IntStream.rangeClosed(1, size).boxed().map{ item -> UUID.randomUUID().toString(); }.collect(Collectors.toList())
	}

	static public List<String> convertString2ListString(String st, String separator){
		List<String>rs = st.split(separator)
		return  rs.stream().map({r -> r.trim()}).collect(Collectors.toList())
	}
}
