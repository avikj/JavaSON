#JavaSON

## Synopsis

JavaSON is a tiny Java library that allows easy conversion between JSON data and Java Objects,
using the Java Reflection package.

## Code Example
	Animal myCat = new Animal("cat", "Amber", 4);
	Person me = new Person("Avik Jain", 15, 4.0, myCat);	
	JSONObject jsonMe = Converter.toJSONObject(me);

'jsonMe' now stores the following json data:

	{
	   "name": "Avik Jain",
	   "gpa": 4,
	   "age": 15,
	   "class": "Person",
	   "pet": {
	      "name": "Amber",
	      "type": "cat",
	      "class": "Animal",
	      "age": 4
	   }
	}
	
## Format Requirements

#### Converting to JSON

* A field will be included in the JSON output if and only if the field has a public accessor method following the Java naming standard (getFieldName())

#### Converting to Java Object

* JSON data must include an entry for "class" that stores the value of the Java class name for an object. This name is automatically included when a JSONObject is created using Converter.toJSONObject().
* The Java class for the object must have a no-args constructor
* The fields included in the JSON data must have public mutator methods following the Java naming standard (setFieldName())



## Tests

Example code can be found under demos.