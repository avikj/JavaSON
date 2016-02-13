import java.awt.Point;

public class Person {
	private String name;
	private int age;
	private double gpa;
	private Animal pet;
	private Point location;
	public Person() {
		
	}

	public Person(String name, int age, double gpa, Animal pet, Point location) {
		this.name = name;
		this.age = age;
		this.gpa = gpa;
		this.pet = pet;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getGpa() {
		return gpa;
	}

	public Animal getPet() {
		return pet;
	}

	public Point getLocation(){
		return location;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setGpa(double gpa){
		this.gpa = gpa;
	}
	
	public void setPet(Animal pet){
		this.pet = pet;
	}
	
	public void setLocation(Point location){
		this.location = location;
	}
}
