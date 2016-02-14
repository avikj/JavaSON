package co.avikj.JavaSON;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Provides utilities for storing an Object's current state in a JSONObject.
 * 
 * @author Avik Jain
 * @since 2/12/2016
 * @version 1.0
 */
public class JavaSONConverter {
	/**
	 * Creates a Java object using data from a JSON Object
	 * 
	 * @param json
	 *            JSONObject representing object of class clazz
	 * @param clazz
	 *            Class of returned object (Can be accessed by ClassName.class).
	 * @return Object of class clazz with data from JSONObject json.
	 * @throws JavaSONException 
	 */
	public static <T> T toJavaObject(JSONObject json) throws JavaSONException {
		Class<T> clazz = null;
		String clazzName = "";
		try {
			clazzName = json.getString("class");
		} catch (JSONException e2) {
			throw new JavaSONException("JSON data does not include entry for 'class'.");
		}

		System.out.println("class: "+clazzName);
		
		try {
			clazz = (Class<T>) Class.forName(clazzName);
		} catch (ClassNotFoundException e1) {
			throw new JavaSONException("class "+clazzName+" not found. ");
		} 
		Constructor<T> objConstructor = null;
		T obj = null;
		try {
			objConstructor = clazz.getConstructor();
		} catch(IllegalArgumentException | NoSuchMethodException e){
			throw new JavaSONException("Class "+clazzName+" does not contain nullary (no-args) constructor.");
		}

		try{
			obj = objConstructor.newInstance();
		}catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			throw new JavaSONException("Class "+clazzName+" could not be instantiated using nullary constructor.");
		}
		
		Field[] fields = clazz.getDeclaredFields();

		// loop through all the fields in the class
		for (Field field : fields) {
			int mod = field.getModifiers();

			// if the field is public, its value can be set with Field.set();
			if (Modifier.isPublic(mod)) {
				try {
					field.set(obj, json.get(field.getName()));
				} catch (IllegalAccessException|JSONException e) {
					throw new JavaSONException("Could not set value for public field "+field.getName());
				}
			}

			// if it is not public, try to set its value through a mutator
			// method
			else {
				try{
					Method setter = clazz.getMethod(getMutatorName(field.getName()), field.getType());
					set(setter, clazz.cast(obj), json.get(field.getName()));
				}catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | JSONException | NoSuchMethodException | SecurityException e){
					throw new JavaSONException("Could not set value for private field "+field.getName()+" due to lack of mutator method.");
				}
			}
			
		}

		return obj;
	}
	
	private static void set(Method setter, Object obj, Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, JavaSONException{
		if(isBasicType(value)){
			setter.invoke(obj, value);
		}else{
			JSONObject jsonValue = (JSONObject)value;
			try{
				String className = jsonValue.getString("class");
			}catch(JSONException e){
				throw new JavaSONException("JSON data does not include entry for 'class'.");
			}
			setter.invoke(obj, toJavaObject(jsonValue));
		}
	}
	
	private static String getMutatorName(String fieldName) {
		return "set" + Character.toUpperCase(fieldName.charAt(0))
				+ fieldName.substring(1);
	}

	/**
	 * Creates a JSONObject representing an Object's current state.
	 * 
	 * @param obj
	 *            The object that's state will be stored.
	 * @return JSONObject that represents the current state of obj.
	 */
	public static JSONObject toJSONObject(Object obj) {
		Class<?> clazz = obj.getClass();
		JSONObject json = new JSONObject();
		try {
			json.put("class", clazz.getName());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		Field[] fields = clazz.getDeclaredFields();

		// loop through all the fields in the class
		for (Field field : fields) {
			int mod = field.getModifiers();

			// if the field is public, its value can be retrieved with
			// Field.get();
			try {
				if (Modifier.isPublic(mod)) {

					put(json, field.getName(), field.get(clazz.cast(obj)).toString());

				}

				// if it is not public, try to retrieve its value through an
				// accessor method
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
	 * 
	 * @param fieldName
	 *            name of a private/protected field
	 * @return standard camelcase accessor method name (getFieldName)
	 */
	private static String getAccessorName(String fieldName) {
		return "get" + Character.toUpperCase(fieldName.charAt(0))
				+ fieldName.substring(1);
	}

	/**
	 * Inserts a key-value pair into a JSONObject. If the value is not of a
	 * basic type, then it is converted to a JSONObject recursively.
	 * 
	 * @param json
	 *            JSONObject in which to insert the entry
	 * @param key
	 *            key for the JSON object entry
	 * @param obj
	 *            value to insert
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
	 * Checks if a value is null or of a basic JSON type (string, number, true,
	 * false, or null)
	 * 
	 * @param obj
	 *            value to check
	 * @return true if obj is of a basic type, false otherwise
	 */
	private static boolean isBasicType(Object obj) {
		if (obj == null)
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
