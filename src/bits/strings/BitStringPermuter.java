/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 7, 2019
 */
package bits.strings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class BitStringPermuter extends Problem implements IProblem
{
	public BitStringPermuter(IBitString[] M) throws Exception
	{
		int bits = M[0].size();
		IBitString orBS = new BitString(bits);
		IBitString allOnes = new BitString(bits);
		for (int i = 0; i < bits; i++)
			allOnes.getBooleanVariable(i).setValue(true);

		IProblem[] p = new IProblem[bits];
		for (int i = 0; i < bits; i++)
			p[i] = new BitStringSingleSetter(M[i]);

		IProblem problem = new Conjunction(new IProblem[]
		{ new Conjunction(p), new BitStringOrer(M, orBS),
				new BitStringFixer(allOnes),
				new BitStringEqualizer(orBS, allOnes) });

		this.setClauses(problem.getClauses());
	}
}
