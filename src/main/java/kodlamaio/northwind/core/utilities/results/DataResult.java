package kodlamaio.northwind.core.utilities.results;

public class DataResult<T> extends Result {
	private final T data; // herhangi bir turde data dondurmek icin generic kullandik

	public DataResult(final T data, final boolean success) {
		super(success);
		this.data = data;
	}

	public DataResult(final T data, final boolean success, final String message) {
		super(success, message);
		this.data = data;
	}

	public T getData() {
		return data;
	}
}
