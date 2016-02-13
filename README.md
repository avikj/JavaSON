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
	   "pet": {
	      "name": "Amber",
	      "type": "cat",
	      "age": 4
	   }
	}
	

## Motivation

Often when building a multi-platform application, it is difficult to use the same data format
across Android (Java) and web (javascript) front-ends. 

## Installation
Coming soon!

## API Reference

Coming soon!

## Tests

Example code can be found under demos.