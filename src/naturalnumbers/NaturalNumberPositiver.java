package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 * An extension of the Problem class which constrains an INaturalNumber to be
 * positive.
 *
 * In one way to use this class, one passes INaturalNumber X to the appropriate
 * constructor. The NaturalNumberPositiver object produced is a Problem, and one
 * may manipulate it using any of the methods provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p>
 * <code>Problem p = new NaturalNumberPositiver(X);</code>
 * </p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <code>X &gt; 0</code>
 * </p>
 * .
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Aug 29, 2010
 */
public class NaturalNumberPositiver extends Problem implements IProblem
{
	public NaturalNumberPositiver(INaturalNumber X) throws Exception
	{
		this(X, new NaturalNumber());
	}

	public NaturalNumberPositiver(INaturalNumber X, INaturalNumber OneLess) throws Exception
	{
		INaturalNumber One = new NaturalNumber(1);
		this.setClauses(
				new Conjunction(new NaturalNumberFixer(One), new NaturalNumberAdder(OneLess, One, X)).getClauses());
	}
}
