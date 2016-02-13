
public class Person {
	private String name;
	private int age;
	private double gpa;
	private Animal pet;
	public Person(String name, int age, double gpa, Animal pet){
		this.name = name;
		this.age = age;
		this.gpa = gpa;
		this.pet = pet;
	}
	public String getName(){
		return name;	
	}
	public int getAge(){
		return age;
	}
	public double getGpa(){
		return gpa;
	}
	public Animal getPet(){
		return pet;
	}
}
