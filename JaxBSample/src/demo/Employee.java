package demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Employee")
public class Employee {

	private String id,name,address;
	private int salary;
	
	@XmlElement
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@XmlElement
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Employee(String id, String name, String address, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}
	
	public Employee() {
		super();
	
	}
	
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("Employee: [");
		bld.append("Name: ").append(getName()).append(",");
		bld.append("Salary: ").append(getSalary()).append(",");
		bld.append("]");
		return bld.toString();
	}
}
