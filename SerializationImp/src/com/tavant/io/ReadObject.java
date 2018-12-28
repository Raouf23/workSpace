package com.tavant.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadObject {

	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		ReadObject rd = new ReadObject();
		Address add1= rd.deserialzeAddress("D:\\Mydata\\sample1.txt");
		System.out.println(add1);

	}

	@SuppressWarnings("resource")
	private Address deserialzeAddress(String filename) throws IOException, ClassNotFoundException
	{
		Address add2 = null;
		FileInputStream fis= null;
		ObjectInputStream  ois= null;
		
		fis = new FileInputStream(filename);
		ois = new ObjectInputStream(fis);
		
		add2=(Address) ois.readObject();
		
		return add2;
	}

}
