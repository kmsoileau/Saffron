package naturalnumbers;

import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 * An extension of the Problem class which implements an subtracter of two
 * NaturalNumbers.
 *
 * In one way to use this class, one passes INaturalNumber X, INaturalNumber Y,
 * INaturalNumber Z and INaturalNumber C to the appropriate constructor. The
 * NaturalNumberSubtracter object produced is a Problem, and one may manipulate
 * it using any of the methods provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p>
 * <code>Problem p = new NaturalNumberSubtracter(X,Y,Z,C);</code>
 * </p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <code>X == Z + Y</code>
 * </p>
 *
 * and the carry bits will be found in C.
 *
 * If the carry bits are of no interest, one may instead write
 *
 * <p>
 * <code>Problem p = new NaturalNumberSubtracter(X,Y,Z);</code>
 * </p>
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 8, 2006
 */
public class NaturalNumberSubtracter extends Problem implements IProblem
{
	public NaturalNumberSubtracter(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		this.setClauses(new NaturalNumberAdder(Z, Y, X).getClauses());
	}

	public NaturalNumberSubtracter(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z, INaturalNumber C) throws Exception
	{
		this.setClauses(new NaturalNumberAdder(Z, Y, X, C).getClauses());
	}
}