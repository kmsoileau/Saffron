package demos.sets;

import sets.Set;


public class SetDemo1
{
	public static void main(String[] args) throws Exception
	{
		Set.setElementNames(new String[]
		{ "I", "J", "K", "L" });

		Set setA = new Set();
		
		System.out.println(setA.getMembershipBitString());

		Set setB = new Set();

		System.out.println(setB);
	}
}
