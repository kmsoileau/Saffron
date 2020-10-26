package demos.bits;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.ThreeBitAdder;

public class ThreeBitAdderDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable w = BooleanVariable.getBooleanVariable("w");
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");
		IBooleanVariable c = BooleanVariable.getBooleanVariable("c");

		IProblem threeBitAdder1 = new ThreeBitAdder(w, x, y, z, c);

		System.out.println(threeBitAdder1);

//		IProblem compressed = ((Problem) threeBitAdder1).compress();
//		System.out.println(compressed);
	}
}
// Compressed
/*************************************
 *** { $c w x } { $c w y } { $c w $z } { $c x y } { $c x $z } { $c y $z } { $w $x
 * $y z } { w x y $z } { w x $y z } { w $x y z } { c w $y z } { w $x $y $z } {
 * $w x y z } { c x $y z } { $w x $y $z } { c $x y z } { $w $x y $z } { c $x $y
 * $z }
 ***************************************
 ****************************************
 *** { $c w x } { $c w y } { $c w $z } { $c x y } { $c x $z } { $c y $z } { c w $y
 * z } { c x $y z } { c $x y z } { c $x $y $z } { $w $x $y z } { $w x y z } { $w
 * x $y $z } { $w $x y $z } { w x y $z } { w x $y z } { w $x y z } { w $x $y $z
 * }
 ***************************************
 */
