package demos.bits;

import bits.BitAnder;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitAnderDemo2
{
	public static void main(String[] args) throws Exception
	{
		// Create the BooleanVariables:
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");

		// Construct the object that implements the constraint x & y = z :
		IProblem bitAnder1 = new BitAnder(x, y, z);
		System.out.println(bitAnder1);

		IProblem compressed = ((Problem) bitAnder1).compress();
		System.out.println(compressed);
	}
}