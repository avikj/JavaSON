package co.avikj.JavaSON.demo;
import org.json.JSONException;
import org.json.JSONObject;

import co.avikj.JavaSON.JavaSONConverter;
import co.avikj.JavaSON.JavaSONException;
public class Tester {
	public static void main(String[] args) throws JSONException, JavaSONException{
			
		//////// Test converting Java object to JSON Object ////////
		Animal myCat = new Animal("cat", "Amber", 4);
		Person me = new Person("Avik Jain", 15, 4.0, myCat, new Point(1, 2));

		JSONObject jsonMe = JavaSONConverter.toJSONObject(me);
		System.out.println(jsonMe.toString(4));		
		
		
		
		/* Prints the following to the console.
			{
			    "name": "Avik Jain",
			    "gpa": 4,
			    "location": {
			        "x": 1,
			        "y": 2,
			        "class": "co.avikj.JavaSON.demo.Point"
			    },
			    "class": "co.avikj.JavaSON.demo.Person",
			    "age": 15,
			    "pet": {
			        "name": "Amber",
			        "type": "cat",
			        "class": "co.avikj.JavaSON.demo.Animal",
			        "age": 4
			    }
			}
	 */
		
		
		//////// Test converting JSON Object to Java Object ////////
		JSONObject jsonMe2 = new JSONObject("{\"name\":\"Avik Jain\",\"gpa\":4,\"location\":{\"x\":1,\"y\":2,\"class\":\"co.avikj.JavaSON.demo.Point\"},\"class\":\"co.avikj.JavaSON.demo.Person\",\"age\":15,\"pet\":{\"name\":\"Amber\",\"type\":\"cat\",\"class\":\"co.avikj.JavaSON.demo.Animal\",\"age\":4}}");
		Person me2 = JavaSONConverter.toJavaObject(jsonMe2);
		System.out.printf("name=%s, gpa=%f, age=%d, pet.name=%s", me2.getName(), me2.getGpa(), me2.getAge(), me2.getPet().getName());
		// Prints the following to the console
		// name=Avik Jain, gpa=4.000000, age=15, pet.name=Amber
	}
}
