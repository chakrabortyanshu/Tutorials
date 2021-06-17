package jmockit.sample;

public class SubClass extends SuperClass{

	private String data;
	
	public SubClass(final String data) {
		super(data);
		this.data = data;
	}

	public String getData() {
		return this.data;
	}
}
