package account.model;

public class NegativeAccBalException extends Exception {
	public NegativeAccBalException() {
		super();
	}

	public NegativeAccBalException(String s) {
		super(s);
	}
}
