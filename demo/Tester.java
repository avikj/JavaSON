import java.awt.Point;

import org.json.JSONException;
import org.json.JSONObject;

import co.avikj.JavaSON.Converter;
public class Tester {
	public static void main(String[] args) throws JSONException{
			
		//////// Test converting Java object to JSON Object ////////
		Animal myCat = new Animal("cat", "Amber", 4);
		Person me = new Person("Avik Jain", 15, 4.0, myCat, new Point(1, 2));

		JSONObject jsonMe = Converter.toJSONObject(me);
		System.out.println(jsonMe.toString(4));		
		
		/* Prints the following to the console.
		{
		    "name": "Avik Jain",
		    "gpa": 4,
		    "location": {
		        "x": "1",
		        "y": "2",
		        "class": "java.awt.Point"
		    },
		    "class": "Person",
		    "age": 15,
		    "pet": {
		        "name": "Amber",
		        "type": "cat",
		        "class": "Animal",
		        "age": 4
		    }
		}
	 */
		
		
		//////// Test converting JSON Object to Java Object ////////
		JSONObject jsonMe2 = new JSONObject("{\"name\":\"Avik Jain\",\"gpa\":4,\"class\":\"Person\",\"age\":15,\"pet\":{\"name\":\"Amber\",\"type\":\"cat\",\"class\":\"Animal\",\"age\":4}}");
		Person me2 = Converter.toJavaObject(jsonMe2);
		System.out.println(me2.getAge());

		
	}
}
