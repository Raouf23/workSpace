package com.tavant.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObject {

	public static void main(String[] args) throws IOException
	{
		WriteObject obj = new WriteObject();
		
		Address add = new Address();
		add.setStreet("Wall Street");
		add.setCountry("USA");
		obj.serialAddress(add);
	}

	private void serialAddress(Address add) throws IOException
	{
		FileOutputStream fout = null;
		ObjectOutputStream oout = null;
		
		fout = new FileOutputStream("D:\\Mydata\\sample2.ser");
		oout = new ObjectOutputStream(fout);
		
		oout.writeObject(add);
		System.out.println("Done");
		
		oout.close();
	}

}
