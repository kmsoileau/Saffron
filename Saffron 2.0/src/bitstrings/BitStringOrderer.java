/*
 * BitStringOrderer.java	1.0 05/05/04
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */
/**
 * An extension of the Problem class which imposes a sort relation on two
 * IBitString. This is just the usual lexicographical ordering with bit
 * comparisons beginning with bit 0.
 *
 * The Problem instance p defined by
 *
 * <p><tt>Problem p=new BitStringOrderer(X,Y);</code></p>
 *
 * is satisfied if and only if X is previous or equal to Y in lexicographical
 * order.
 *
 * @author  Kerry Michael Soileau
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * @version 1.0, 05/05/04
 * @see BitFixer
 * @see BitString
 * @see BitStringException
 * @see BitStringLowPopper
 * @see BooleanLiteralException
 * @see Conjunction
 * @see Disjunction
 * @see IBitString
 * @see IBooleanVariable
 * @see IProblem
 * @see Problem
 */

package bitstrings;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import exceptions.bits.BooleanLiteralException;
import exceptions.bitstrings.BitStringException;

@SuppressWarnings("unused")
public class BitStringOrderer extends Problem implements IProblem
{
	public BitStringOrderer(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() == 0)
			this.setClauses(Problem.trivialProblem().getClauses());
		else if (Y.size() == 0)
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IBooleanVariable X_0 = X.getBooleanVariable(0);
			IBooleanVariable Y_0 = Y.getBooleanVariable(0);

			IBitString clippedX = new BitString(X.size() - 1);
			IBitString clippedY = new BitString(Y.size() - 1);

			IProblem problem = new Disjunction(new Conjunction(new BitFixer(
					X_0, false), new BitFixer(Y_0, true)), new Conjunction(
					new Disjunction(new Conjunction(new BitFixer(X_0, false),
							new BitFixer(Y_0, false)), new Conjunction(
							new BitFixer(X_0, true), new BitFixer(Y_0, true))),
					new BitStringLowPopper(Y, clippedY),
					new BitStringLowPopper(X, clippedX), new BitStringOrderer(
							clippedX, clippedY)));

			this.setClauses(problem.getClauses());
		}
	}
}