package katalon.model

public class BodySearch {
	String type;
	List<ConditionSearch> conditions;
	boolean ignoreAutoFill;
	Pagination pagination;
	List<Function> functions;
	List<String> groupBys;

	public BodySearch() {
	}

	public BodySearch(String type, List<ConditionSearch> conditions, int page=0, int size=30, List<String> sorts=["id,desc"]) {
		this.type = type
		this.conditions = conditions
		this.pagination = new Pagination(page, size, sorts)
	}

	public BodySearch(String type, List<ConditionSearch> conditions, ignoreAutoFill, int page=0, int size=30, List<String> sorts=["id,desc"]) {
		this.type = type
		this.conditions = conditions
		this.ignoreAutoFill = ignoreAutoFill
		this.pagination = new Pagination(page, size, sorts)
	}
}
