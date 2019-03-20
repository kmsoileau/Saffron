/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 20, 2018
 */
package bitstrings;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class TestSetter extends Problem implements IProblem
{
	public TestSetter(IBitString[] C, IBitString includedInTestSet)
			throws Exception
	{
		int cLength = C.length;
		int cSize = C[0].size();

		int problemIndex = 0;
		IProblem[] problemArray = new IProblem[cSize * cSize - cSize];

		for (int i = 0; i < cSize; i++)
			for (int j = 0; j < cSize; j++)
			{
				if (i == j)
					continue;
				IProblem[] parray = new IProblem[cLength];
				for (int k = 0; k < cLength; k++)
				{
					// (C[k][i] && !C[k][j]) || (!C[k][i] && C[k][j])
					IBooleanVariable ci = C[k].getBooleanVariable(i);
					IBooleanVariable cj = C[k].getBooleanVariable(j);

					parray[k] = new Disjunction(new Conjunction(new BitFixer(
							ci, true), new BitFixer(cj, false)),
							new Conjunction(new BitFixer(ci, false),
									new BitFixer(cj, true)));
				}
				problemArray[problemIndex++] = new ConditionalDisjunction(
						parray, includedInTestSet);
			}
		this.setClauses(new Conjunction(problemArray).getClauses());
	}
}
