package com.tavant.frc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
//import java.util.HashMap;

public class capitolsCollections {

	public static void main(String[] args) throws IOException 
	{
		final String FILENAME = "D:\\Mydata\\sample.txt";
		BufferedReader br = new BufferedReader( new FileReader(FILENAME));
		String ch;
		HashMap<String, String> cd = new HashMap<>();
		while ((ch=br.readLine()) != null)
		{
			
			String ar[] = ch.toString().split("\t");
//			System.out.println(ar.length);
			cd.put(ar[0], ar[2]);			
		}
		br.close();
		
		for(Entry<String, String> m:cd.entrySet()){ 
			if(Integer.parseInt(m.getKey())==27)
				   System.out.println(m.getKey()+" "+m.getValue());  
			  }

	}

}
