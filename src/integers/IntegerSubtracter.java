package integers;

import bits.IProblem;
import bits.Problem;

/**
 * An extension of the Problem class which implements an subtracter of two
 * Integers.
 *
 * In one way to use this class, one passes IInteger X, IInteger Y, IInteger Z
 * and IInteger C to the appropriate constructor. The IntegerSubtracter object
 * produced is a Problem, and one may manipulate it using any of the methods
 * provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p>
 * <code>Problem p = new IntegerSubtracter(X,Y,Z,C);</code>
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
 * <code>Problem p = new IntegerSubtracter(X,Y,Z);</code>
 * </p>
 *
 * @author Kerry Michael Soileau <blockquote>
 * 
 *         <pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre>
 * 
 *         </blockquote>
 * @version 1.0, 2019/02/26
 */

public class IntegerSubtracter extends Problem implements IProblem
{
	public IntegerSubtracter(IInteger X, IInteger Y, IInteger Z)
			throws Exception
	{
		this.setClauses(new IntegerAdder(Z, Y, X).getClauses());
	}

	public IntegerSubtracter(IInteger X, IInteger Y, IInteger Z, IInteger C)
			throws Exception
	{
		this.setClauses(new IntegerAdder(Z, Y, X).getClauses());
	}
}