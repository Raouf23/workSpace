package com.tavant.geek;

import java.util.HashMap;

public class ArrayElement 
{

	public static void main(String[] args)
	{
		int arr[] = {3,4,7,1,2,9,8};
		ArrayElement ar = new ArrayElement();
		ar.findPairs(arr);

	}

	boolean findPairs(int[] arr)
	{
		HashMap<Integer, Pair> map  = new HashMap<>();
		int n = arr.length;
		for (int i =0; i<n;i++)
		{
			for(int j= i+1;j<n;j++)
			{
				int sum = arr[i]+arr[j];
				if(!map.containsKey(sum))
					map.put(sum, new Pair(i,j));
				else
				{
					Pair p = map.get(sum);
					System.out.println("("+arr[p.first]+", "+arr[p.second]+
                            ") and ("+arr[i]+", "+arr[j]+")");
				}
			}
			
		}
		return false;
		
		
	}

}
