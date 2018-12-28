package com.jackson.tavant;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonSimpleBinding {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException
	{
		JacksonSimpleBinding tester = new JacksonSimpleBinding();
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> studentDataMap = new HashMap<String,Object>();
		
		int[] marks = {10,20,30};
		Student student = new Student();
		student.setAge(26);
		student.setName("jarKonda");
		
		//JAVA OBJECT
		studentDataMap.put("student", student);
		
		//JAVA String
		studentDataMap.put("name", "Hima Markonda");
		
		//JAVA Boolean
		studentDataMap.put("verified", Boolean.FALSE);
		
		//JAVA Array
		studentDataMap.put("Marks", marks);
		
		mapper.writeValue(new File("E:\\logs\\Student.json"), studentDataMap);
		
		studentDataMap = mapper.readValue(new File("E:\\logs\\Student.json"), Map.class);
		System.out.println(studentDataMap.get("student"));
		System.out.println(studentDataMap.get("name"));

	}

}