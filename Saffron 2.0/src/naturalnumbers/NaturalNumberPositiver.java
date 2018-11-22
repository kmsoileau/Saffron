package naturalnumbers;

import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
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
 * <tt>Problem p = new NaturalNumberPositiver(X);</code>
 * </p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <tt>X > 0</code>
 * </p>
 * .
 *
 * @author Kerry Michael Soileau <blockquote>
 * 
 *         <pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre>
 * 
 *         </blockquote>
 * @version 1.0, 10/08/29
 * @see IBooleanVariable
 * @see IClause
 * @see IProblem
 * @see Problem
 */

public class NaturalNumberPositiver extends Problem implements IProblem
{
	public NaturalNumberPositiver(INaturalNumber X) throws Exception
	{
		this(X, new NaturalNumber());
	}

	public NaturalNumberPositiver(INaturalNumber X, INaturalNumber OneLess)
			throws Exception
	{
		INaturalNumber One = new NaturalNumber(1);
		IProblem add1 = new NaturalNumberAdder(OneLess, One, X);
		IProblem problem = new Conjunction(new NaturalNumberFixer(One), add1);
		this.setClauses(problem.getClauses());
	}
}
