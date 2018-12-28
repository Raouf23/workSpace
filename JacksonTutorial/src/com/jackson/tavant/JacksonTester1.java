package com.jackson.tavant;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonTester1 {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException
	{
		JacksonTester1 tester = new JacksonTester1();
		Student student1 = new Student();
		student1.setAge(23);
		student1.setName("Markonda");
		tester.writeJSON(student1);
		
		Student student2 = tester.readJSON();
		System.out.println(student2);
		


	}

	private Student readJSON() throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Student student =mapper.readValue(new File("D:\\Mydata\\student.json"), Student.class);
		return student;
	}

	private void writeJSON(Student student1) throws JsonGenerationException, JsonMappingException, IOException 
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("D:\\Mydata\\student.json"), student1);

	}

}
