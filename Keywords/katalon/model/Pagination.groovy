package katalon.model

public class Pagination {
	int page;
	int size;
	List<String> sorts;
	
	public Pagination() {
		this.page = 0
		this.size = 30
		this.sorts = ["id,desc"]
	}

	public Pagination(int page, int size, List<String> sorts) {
		this.page = page
		this.size = size
		this.sorts = sorts
	}
}
