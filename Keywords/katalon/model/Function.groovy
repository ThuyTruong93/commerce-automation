package katalon.model

public class Function {
	String key;
	String function;
	List<String> parameters;
	
	public Function(){ 
		
	}
	
	public Function(String key, String function, List<String> parameters) {
		this.key = key
		this.function = function
		this.parameters = parameters
	}
}
