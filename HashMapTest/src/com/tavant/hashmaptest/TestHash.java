package com.tavant.hashmaptest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TestHash {

	public static void main(String[] args) {
		HashMap<Integer, String> hsm = new HashMap<Integer, String>();
		HashMap<String, ArrayList<Integer>> hsm1 = new HashMap<String, ArrayList<Integer>>();
		hsm.put(1, "Tesco");
		hsm.put(2, "Tavant");
		hsm.put(3, "CEB");
		hsm.put(4, "BT");
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(123);
		al.add(456);
		al.add(678);
		
		
		hsm1.put("Kitty",al) ;
		
		String value = hsm.get(2);
		System.out.println(value);
		Iterator<Integer> keysetIt = hsm.keySet().iterator();
		
		while(keysetIt.hasNext())
		{
			Integer key = keysetIt.next(); 
			System.out.println("key: " + key + " value: " + hsm.get(key));
		}
	}

}
