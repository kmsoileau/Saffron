package demos;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bits.TwoBitAdder;

public class TwoBitAdderDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");
		IBooleanVariable c = BooleanVariable.getBooleanVariable("c");

		IProblem twoBitAdder1 = new TwoBitAdder(x, y, z, c);
		twoBitAdder1.sort();
		System.out.println(twoBitAdder1);

		IProblem compressed = ((Problem) twoBitAdder1).compress();
		System.out.println(compressed);
	}
}
// Compressed
/**************************************
 *** { $c x } { $c y } { $c $z } { x y $z } { x $y z } { $x y z } { c $y z } { $x
 * $y $z }
 ***************************************
 */
