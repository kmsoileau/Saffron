/*
 * BitStringOrer.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.strings;

import bits.BitOrer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringOrerException;

public class BitStringOrer extends Problem implements IProblem
{
	public BitStringOrer(IBitString X, IBitString Y, IBitString Z) throws Exception
	{
		if (X.size() != Y.size() || X.size() != Z.size())
			throw new BitStringOrerException("X, Y and Z are not of equal size.");
		// this.setClauses(EnhancedProblem.unsolvableProblem().getClauses());
		else
		{
			int commonsize = X.size();
			IProblem[] p = new IProblem[commonsize];
			int count = 0;
			for (int i = 0; i < commonsize; i++)
				p[count++] = new BitOrer(X.getBooleanVariable(i), Y.getBooleanVariable(i), Z.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}

	public BitStringOrer(IBitString[] X, IBitString Z) throws Exception
	{
		int bits = X[0].size();

		IProblem[] p = new IProblem[X.length - 1];
		IBitString[] S = new IBitString[X.length];
		for (int i = 0; i < X.length; i++)
			S[i] = new BitString(bits);

		IProblem q1 = new BitStringEqualizer(S[0], X[0]);

		for (int i = 0; i < X.length - 1; i++)
			p[i] = new BitStringOrer(S[i], X[i + 1], S[i + 1]);

		IProblem q2 = new BitStringEqualizer(Z, S[X.length - 1]);

		IProblem problem = new Conjunction(q1, new Conjunction(p), q2);
		this.setClauses(problem.getClauses());
	}
}