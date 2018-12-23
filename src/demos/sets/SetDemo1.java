package demos.sets;

import java.util.HashSet;

import sets.Set;

public class SetDemo1
{
	public static void main(String[] args) throws Exception
	{
		Set setA = new Set(new Object[]
		{ "A", "B", "C" });
		System.out.println(setA.getSupport());

		HashSet<Object> h = new HashSet<Object>();
		h.add("I");
		h.add("J");
		h.add("K");
		h.add("L");
		Set setB = new Set(h);
		System.out.println(setB.getSupport());
	}
}
