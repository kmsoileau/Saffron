package naturalnumbers;

import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * An extension of the Problem class which implements an divider of two
 * NaturalNumbers.
 *
 * In one way to use this class, one passes INaturalNumber X, INaturalNumber Y,
 * INaturalNumber Z to the appropriate constructor. The NaturalNumberDivider
 * object produced is a Problem, and one may manipulate it using any of the
 * methods provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p>
 * <code>Problem p = new NaturalNumberDivider(X,Y,Z);</code>
 * </p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <code>X == Z * Y</code>
 * </p>
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jun 2, 2008
 */
public class NaturalNumberDivider extends Problem implements IProblem
{
	public NaturalNumberDivider(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		this.setClauses(new NaturalNumberMultiplier(Z, Y, X).getClauses());
	}
}
