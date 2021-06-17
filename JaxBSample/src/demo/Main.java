package demo;

import jaxb.EmployeeJaxb;

public class Main {
	public static void main(String[] args) {
		EmployeeJaxb jaxb = new EmployeeJaxb();
		jaxb.marshall();
		jaxb.unmarshall();
	}
}
