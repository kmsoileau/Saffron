package bits.strings;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.EnhancedProblem;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

/**
 * An extension of the Problem class which imposes a sort relation on two
 * IBitString. This is just the usual lexicographical ordering with bit
 * comparisons beginning with bit 0.
 *
 * The Problem instance p defined by
 *
 * <p>
 * <code>Problem p=new BitStringOrderer(X,Y);</code>
 * </p>
 *
 * is satisfied if and only if X is previous or equal to Y in lexicographical
 * order.
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * 
 * @version 1.0
 * @since 2005/05/04
 * 
 */
public class BitStringOrderer extends Problem implements IProblem
{
	public BitStringOrderer(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() == 0)
			this.setClauses(EnhancedProblem.trivialProblem().getClauses());
		else if (Y.size() == 0)
			this.setClauses(EnhancedProblem.unsolvableProblem().getClauses());
		else
		{
			IBooleanVariable X_0 = X.getBooleanVariable(0);
			IBooleanVariable Y_0 = Y.getBooleanVariable(0);

			IBitString clippedX = new BitString(X.size() - 1);
			IBitString clippedY = new BitString(Y.size() - 1);

			IProblem problem = new Disjunction(new Conjunction(new BitFixer(X_0, false), new BitFixer(Y_0, true)),
					new Conjunction(
							new Disjunction(new Conjunction(new BitFixer(X_0, false), new BitFixer(Y_0, false)),
									new Conjunction(new BitFixer(X_0, true), new BitFixer(Y_0, true))),
							new BitStringLowPopper(Y, clippedY), new BitStringLowPopper(X, clippedX),
							new BitStringOrderer(clippedX, clippedY)));

			this.setClauses(problem.getClauses());
		}
	}
}