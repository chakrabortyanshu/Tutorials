package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

import demo.Employee;

public class EmployeeJaxb {

	public void marshall() {
		try {
			Employee employee = new Employee("1234","Anshuman", "Address", 50000);
			JAXBContext context = JAXBContext.newInstance(Employee.class);
			Marshaller ms = context.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			ms.marshal(employee, System.out);
			ms.marshal(employee, new File("src/data/Employee.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void unmarshall() {
		try {
			JAXBContext context = JAXBContext.newInstance(Employee.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Employee emp = (Employee) unmarshaller.unmarshal(new File("src/data/Employee.xml"));
			System.out.println(emp.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
