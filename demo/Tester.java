import org.json.JSONException;
import org.json.JSONObject;

import co.avikj.JavaSON.Converter;
public class Tester {
	public static void main(String[] args) throws JSONException{
		Animal myCat = new Animal("cat", "Amber", 4);
		Person me = new Person("Avik Jain", 15, 4.0, myCat);
		
		JSONObject jsonMe = Converter.toJSONObject(me);
		System.out.println(jsonMe.toString(3));
		
		/* Prints the following to the console.
		 * 
		 * {
		 *	   "name": "Avik Jain",
		 *	   "gpa": 4,
		 *	   "age": 15,
		 *	   "pet": {
		 *	      "name": "Amber",
		 *	      "type": "cat",
		 *	      "age": 4
		 *	   }
		 *	}
		 * 
		 */
	}
}
