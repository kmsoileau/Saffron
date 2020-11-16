/*
 * BitStringUnequalizer.java	1.0 05/04/03
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits.strings;

import bits.BitUnequalizer;
import bits.Disjunction;
import bits.EnhancedProblem;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

public class BitStringUnequalizer extends Problem implements IProblem
{
	public BitStringUnequalizer(IBitString X, IBitString Y) throws Exception
	{
		if (X.size() == Y.size())
		{
			BitUnequalizer[] thba = new BitUnequalizer[X.size()];
			for (int i = 0; i < X.size(); i++)
				thba[i] = new BitUnequalizer(X.getBooleanVariable(i), Y.getBooleanVariable(i));
			IProblem p1 = new Disjunction(thba);
			this.setClauses(p1.getClauses());
		}
		else
			this.setClauses(EnhancedProblem.trivialProblem().getClauses());
	}
}