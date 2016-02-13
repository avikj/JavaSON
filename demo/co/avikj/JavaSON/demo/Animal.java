package co.avikj.JavaSON.demo;
public class Animal {
	private String type;
	private String name;
	private int age;

	public Animal() {
	}

	public Animal(String type, String name, int age) {
		this.type = type;
		this.name = name;
		this.age = age;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
