package demos;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.ThreeBitAdder;

public class ThreeBitAdderDemo3
{
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable w = BooleanVariable.getBooleanVariable("w");
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");
		IBooleanVariable c = BooleanVariable.getBooleanVariable("c");

		long start1 = System.currentTimeMillis();
		IProblem threeBitAdder1 = new ThreeBitAdder(w, x, y, z, c);
		long finish1 = System.currentTimeMillis();
		System.out.println(finish1 - start1);

		long start2 = System.currentTimeMillis();
		IProblem threeBitAdder2 = new ThreeBitAdder(w, x, y, z, c);
		long finish2 = System.currentTimeMillis();
		System.out.println(finish2 - start2);

		long start3 = System.currentTimeMillis();
		IProblem threeBitAdder3 = new ThreeBitAdder(w, x, y, z, c);
		long finish3 = System.currentTimeMillis();
		System.out.println(finish3 - start3);

		long start4 = System.currentTimeMillis();
		IProblem threeBitAdder4 = new ThreeBitAdder(w, x, y, z, c);
		long finish4 = System.currentTimeMillis();
		System.out.println(finish4 - start4);
		
		long start5 = System.currentTimeMillis();
		IProblem threeBitAdder5 = new ThreeBitAdder(w, x, y, z, c);
		long finish5 = System.currentTimeMillis();
		System.out.println(finish5 - start5);

		long start6 = System.currentTimeMillis();
		IProblem threeBitAdder6 = new ThreeBitAdder(w, x, y, z, c);
		long finish6 = System.currentTimeMillis();
		System.out.println(finish6 - start6);
	}
}
