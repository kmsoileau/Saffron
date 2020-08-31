package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 * An extension of the Problem class which implements the Linear Divisibility
 * problem.
 *
 * In one way to use this class, one passes INaturalNumber A, INaturalNumber X,
 * INaturalNumber C to the appropriate constructor. The
 * NaturalNumberLinearDivisibilitor object produced is a Problem, and one may
 * manipulate it using any of the methods provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p>
 * <code>Problem p = new NaturalNumberLinearDivisibilitor(A,X,C);</code>
 * </p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <code>(A*X+1)|C</code>
 * </p>
 * , which means the integer A*X+1 divides the integer C. A and X are
 * constrained to be positive integers.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Aug 29, 2010
 */
public class NaturalNumberLinearDivisibilitor extends Problem implements
		IProblem
{
	public NaturalNumberLinearDivisibilitor(INaturalNumber A, INaturalNumber C)
			throws Exception
	{
		this(A, new NaturalNumber(), C);
	}

	public NaturalNumberLinearDivisibilitor(INaturalNumber A, INaturalNumber X,
			INaturalNumber C) throws Exception
	{
		INaturalNumber product = new NaturalNumber();
		INaturalNumber prodplus = new NaturalNumber();
		INaturalNumber Z = new NaturalNumber();
		// A>0
		IProblem apos = new NaturalNumberPositiver(A);
		// X>0
		IProblem xpos = new NaturalNumberPositiver(X);
		// product=A*X
		IProblem mult = new NaturalNumberMultiplier(A, X, product);
		// prodplus=A*X+1
		IProblem inc = new NaturalNumberIncrementer(product, prodplus);
		// Z=C/(A*X+1)
		IProblem div = new NaturalNumberDivider(C, prodplus, Z);

		this.setClauses(new Conjunction(new IProblem[]
		{ apos, xpos, mult, inc, div }).getClauses());
	}
}
