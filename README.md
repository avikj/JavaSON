#JavaSON

## Synopsis

JavaSON is a tiny Java library that allows easy conversion between JSON data and Java Objects,
using the Java Reflection package.

## Installation

Download the following .jar file and add it to your Java project's build path:

[javason.jar](https://github.com/avikj/JavaSON/releases/download/v1.0-alpha/javason.jar)

You can then import the converter and exception classes.
```java
import co.avikj.JavaSON.Converter;
import co.avikj.JavaSON.JavaSONException;
```

## Code Example

#### Converting to JSON
```java
Animal myCat = new Animal("cat", "Amber", 4);
Person me = new Person("Avik Jain", 15, 4.0, myCat, new Point(1, 2));
JSONObject jsonMe = Converter.toJSONObject(me);
```
'jsonMe' now stores the following json data:
```
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
```	
#### Converting to Java Object

```java
	JSONObject jsonMe2 = new JSONObject("{\"name\":\"Avik Jain\",\"gpa\":4,\"location\":{\"x\":1,\"y\":2,\"class\":\"co.avikj.JavaSON.demo.Point\"},\"class\":\"co.avikj.JavaSON.demo.Person\",\"age\":15,\"pet\":{\"name\":\"Amber\",\"type\":\"cat\",\"class\":\"co.avikj.JavaSON.demo.Animal\",\"age\":4}}");
	Person me2 = Converter.toJavaObject(jsonMe2)
```
The data in 'me2' is now identical to that in 'me' in the previous example.

######Complete testing code can be found [here](https://github.com/avikj/JavaSON/tree/master/demo/co/avikj/JavaSON/demo)
	
## Format Requirements

#### Converting to JSON

* A field will be included in the JSON output if and only if the field has a public accessor method following the Java naming standard (getFieldName())
* The JSON data must not contain any arrays

#### Converting to Java Object

* JSON data must include an entry for "class" that stores the value of the Java class name for an object. This name is automatically included when a JSONObject is created using Converter.toJSONObject().
* The Java class for the object must have a no-args constructor
* The fields included in the JSON data must have public mutator methods following the Java naming standard (setFieldName())
* The Java class must not include any array fields

## Coming Soon

* Support for arrays when converting from JSON to Java Objects and vice-versa
