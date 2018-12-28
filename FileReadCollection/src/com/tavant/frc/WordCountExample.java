package com.tavant.frc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class WordCountExample {

	public static void main(String[] args) throws IOException
	{
		final String FileName = "D:\\Mydata\\WordCount.txt";
		BufferedReader br = new BufferedReader(new FileReader(FileName));
		String ch;
		HashMap<String, Integer> hs = new HashMap<>();
		
		while((ch = br.readLine()) != null)
		{
			String[] splitted = ch.split(" ");
			for(String str: splitted)
			{
				Integer wordCountAsValue =1;
				if(hs.containsKey(str))
				{
					wordCountAsValue = hs.get(str);
					hs.put(str, ++wordCountAsValue);
				}
				else
				{
					hs.put(str, wordCountAsValue);
				}
			}
		}
		for(Entry<String, Integer> entry: hs.entrySet())
		{
			String char1 =entry.getKey();
			int value1 = entry.getValue();
			System.out.println(char1 + "\t" + value1);
		}
		
		br.close();

	}

}
