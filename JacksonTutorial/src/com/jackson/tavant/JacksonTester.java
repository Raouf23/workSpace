package com.jackson.tavant;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class JacksonTester {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";
		
		Student  student = mapper.readValue(jsonString, Student.class);
		System.out.println(student);
		
		mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		jsonString = mapper.writeValueAsString(student);
		
		System.out.println(jsonString);
	}

}
