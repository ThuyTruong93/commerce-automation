package katalon.model

public class BodySearchProjectScope extends BodySearch{
	List<ConditionSearch> custom_field_conditions;

	public BodySearchProjectScope() {
	}

	public BodySearchProjectScope(String type, List<ConditionSearch> conditions, List<ConditionSearch> CFconditions, int page=0, int size=30, List<String> sorts=["id,desc"]) {
		this.type = type
		this.conditions = conditions
		this.custom_field_conditions = CFconditions
		this.pagination = new Pagination(page, size, sorts)
	}
}
