package demos;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.TwoBitAdder;

public class TwoBitAdderDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");
		IBooleanVariable c = BooleanVariable.getBooleanVariable("c");

		long start1 = System.currentTimeMillis();
		IProblem twoBitAdder1 = new TwoBitAdder(x, y, z, c);
		long finish1 = System.currentTimeMillis();
		System.out.println(finish1 - start1);
		
		long start2 = System.currentTimeMillis();
		IProblem twoBitAdder2 = new TwoBitAdder(x, y, z, c);
		long finish2 = System.currentTimeMillis();
		System.out.println(finish2 - start2);

		/*
		twoBitAdder1.sort();
		System.out.println(twoBitAdder1);

		IProblem compressed = ((Problem) twoBitAdder1).compress();
		System.out.println(compressed);
		*/
	}
}
// Compressed
/**************************************
 *** 
 * <p>
 * { $c x }
 * <p>
 * { $c y }
 * <p>
 * { $c $z }
 * <p>
 * { x y $z }
 * <p>
 * { x $y z }
 * <p>
 * { $x y z }
 * <p>
 * { c $y z }
 * <p>
 * { $x $y $z }
 ***************************************
 */
