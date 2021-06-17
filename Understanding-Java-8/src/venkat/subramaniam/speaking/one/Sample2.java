package venkat.subramaniam.speaking.one;


interface Fly {
	default public void takeOff() { System.out.println("Fly::takeOff");}
	default public void turn() { System.out.println("Fly::turn");}
	default public void cruise() { System.out.println("Fly::cruise");}
	default public void land() { System.out.println("Fly::land");}
}

interface FastFly extends Fly{
	default public void takeOff() { System.out.println("FastFly::takeOff");}
}

interface Sail{
	default public void cruise() { System.out.println("Sail::cruise");}
}

class Vehicle {
	public void land() {System.out.println("Vehicle::land");}
}

class SeaPlane extends Vehicle implements FastFly, Sail{
	@Override
	public void cruise() {
		System.out.println("SeaPlanne::cruise");
		FastFly.super.cruise();
	}
}

public class Sample2 {
	
	public static void main(String[] args) {
		new Sample2().use();
	}

	private void use() {
		SeaPlane seaPlane = new SeaPlane();
		seaPlane.takeOff();
		seaPlane.turn();
		seaPlane.cruise();
		seaPlane.land();
	}
}
