package co.avikj.JavaSON;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Provides utilities for storing an Object's current state in
 * a JSONObject.
 * 
 * @author Avik Jain
 * @since 2/12/2016
 * @version 1.0
 */
public class Converter {
	/**
	 * Creates a JSONObject representing an Object's current state.
	 * @param obj   The object that's state will be stored.
	 * @return JSONObject that represents the current state of obj.
	 */
	public static JSONObject toJSONObject(Object obj) {
		Class<?> clazz = obj.getClass();
		JSONObject json = new JSONObject();
		Field[] fields = clazz.getDeclaredFields();

		// loop through all the fields in the class
		for (Field field : fields) {
			int mod = field.getModifiers();

			// if the field is public, its value can be retrieved with Field.get();
			try {
				if (Modifier.isPublic(mod)) {

					put(json, field.getName(), field.get(clazz.cast(obj)).toString());

				}

				// if it is not public, try to retrieve its value through an accessor method
				else {
					Method accessor = clazz.getMethod(getAccessorName(field.getName()));
					if (Modifier.isPublic(accessor.getModifiers()))
						put(json, field.getName(),
								accessor.invoke(clazz.cast(obj)));

				}
			} catch (Exception e) {
				// There was an error storing the data
			}
		}

		return json;
	}
	
	/**
	 * Returns the standard camelcase accessor method name given a field name.
	 * @param fieldName	name of a private/protected field
	 * @return			standard camelcase accessor method name (getFieldName)
	 */
	private static String getAccessorName(String fieldName) {
		return "get" + Character.toUpperCase(fieldName.charAt(0))
				+ fieldName.substring(1);
	}
	
	
	/**
	 * Inserts a key-value pair into a JSONObject. If the value is not of a basic
	 * type, then it is converted to a JSONObject recursively.
	 * @param json JSONObject in which to insert the entry
	 * @param key  key for the JSON object entry
	 * @param obj  value to insert
	 * @throws JSONException
	 */
	private static void put(JSONObject json, String key, Object obj)
			throws JSONException {
		if (isBasicType(obj))
			json.put(key, obj);
		else
			json.put(key, toJSONObject(obj));
	}
	
	/**
	 * Checks if a value is null or of a basic JSON type (string, number, true, false, or null)
	 * @param obj	value to check
	 * @return true if obj is of a basic type, false otherwise
	 */
	private static boolean isBasicType(Object obj) {
		if(obj == null)
			return true;
		Class<?>[] basicTypes = { Boolean.class, Byte.class, Short.class,
				Character.class, Integer.class, Double.class, Float.class,
				Long.class, String.class };
		for (Class<?> clazz : basicTypes)
			if (obj.getClass().equals(clazz))
				return true;
		return false;
	}
}
